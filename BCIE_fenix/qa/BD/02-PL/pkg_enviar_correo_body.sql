create or replace PACKAGE BODY       PKG_ENVIAR_CORREO
IS
   PROCEDURE P_CONSTRUIR_CORREO (P_ID_PLATILLA     IN     NUMBER,
                                 P_ID_OPERACION    IN     NUMBER,
                                 P_ID_CLIENTE      IN     NUMBER,
                                 P_TAGS            IN     T_TAGS,
                                 P_ASUNTO          IN OUT VARCHAR2,
                                 P_MENSAJE         IN OUT VARCHAR2,
                                 P_DESTINATARIOS   IN OUT VARCHAR2,
                                 P_CC              IN OUT VARCHAR2)
   IS
      V_QUERY             VARCHAR2 (4000);
      V_QUERY_COMPLETE    VARCHAR2 (4000);
      V_QUERY_REPLACE     VARCHAR2 (4000);
      V_RESULT            VARCHAR2 (4000);
      V_MENSAJE           VARCHAR2 (5072);
      V_EXIST             NUMBER (1);
      V_CATEGORIA         VARCHAR2 (32);
      V_VALOR_CATEGORIA   NUMBER (12);
      v_QUERY_COUNTER     VARCHAR2 (4000);
      V_COUNT             NUMBER (12);
      V_BAN_VALIDA_TAGS   NUMBER (1);
      V_TAGS              T_TAGS;
      V_NO_ACCIONES       NUMBER (5);
      V_RESULT_ACCIONES   VARCHAR2 (1500):= '<br>';
      V_CONTADOR          NUMBER (5) := 0;
      TYPE NOMBRE_TERMINO IS TABLE OF VARCHAR2(256);
      V_TERMINOS NOMBRE_TERMINO;
      V_RESULT_ENMIENDAS  VARCHAR2 (1500):= '<br>';
      V_OPERACION         VARCHAR2 (100);
	  V_TAREA        VARCHAR2 (100);


/*Cursor para obtener los tags genericos*/
      CURSOR TAGS_GENERICOS (
         P_CATEGORIA VARCHAR2)
      IS
         SELECT DESCRIPCION_TAG, SQL_QUERY
           FROM TCA_TAG_PLANTILLA
          WHERE BAN_GENERICO = 1
                AND (CATEGORIA_PLANTILLA = P_CATEGORIA
                     OR CATEGORIA_PLANTILLA IS NULL) ORDER BY FECHA_REGISTRO ASC;
                     
   BEGIN

       /*Se llena variable para determinar si dentro de los parametros existe
       el tags  ID_ACCION */
       
      SELECT COUNT (TAG)
        INTO V_NO_ACCIONES
        FROM TABLE (CAST (P_TAGS AS T_TAGS))
       WHERE TAG = 'ID_ACCION';
       
      /*Se obtiene la información de la plantilla*/
      SELECT NVL (P_ASUNTO, ASUNTO),
             NVL (P_DESTINATARIOS, DESTINATARIOS),
             NVL (P_CC, CC_DESTINATARIOS),
             MENSAJE,
             BAN_VALIDA_TAGS
        INTO P_ASUNTO,
             P_DESTINATARIOS,
             P_CC,
             V_MENSAJE,
             V_BAN_VALIDA_TAGS
        FROM TCA_PLANTILLA_CORREO
       WHERE ID = P_ID_PLATILLA;
      
       /*Se agrega un salto de liínea al mensaje */
      P_MENSAJE := P_MENSAJE || CHR (10) || CHR (10);

      /*Si asigna valor a la categoria en base al parametro P_ID_OPERACION*/
      IF P_ID_OPERACION IS NOT NULL
      THEN
         V_CATEGORIA := 'OPERACION';
         V_VALOR_CATEGORIA := P_ID_OPERACION;
      ELSIF P_ID_CLIENTE IS NOT NULL
      THEN
         V_CATEGORIA := 'CLIENTE';
         V_VALOR_CATEGORIA := P_ID_CLIENTE;
      END IF;


    /*Si ciclan tags genericos  (tags que soy repetitivos en las plantillas y que el parametros 
    que recibe es ID_OPERACION o ID_CLIENTE)  y se le pasa como parametro la categoria*/
      FOR GENERICO IN TAGS_GENERICOS (V_CATEGORIA)
      LOOP

         /*Se llena variable con el query a ejecutar, reemplazando el valor de 
         ID_PARAMETRO por el  id del cliente o de la operacion */    
         V_QUERY_REPLACE :=
            REPLACE (GENERICO.SQL_QUERY, ':ID_PARAMETRO', V_VALOR_CATEGORIA);
        
              /*Se llena variable para contar los registros del query y se ejecuta*/    
         V_QUERY_COUNTER := 'SELECT COUNT(1) FROM (' || V_QUERY_REPLACE || ')';

         EXECUTE IMMEDIATE V_QUERY_COUNTER INTO V_COUNT;

          /*Si existen registros se continua con el flujo*/    
         IF (V_COUNT = 1)
         THEN
        V_CONTADOR := V_CONTADOR + 1;
                /*Si ejecuta el query del tag y  el valor que retorna se sustituye en le 
                mensaje o variable de cuerpo del correo*/ 
            EXECUTE IMMEDIATE V_QUERY_REPLACE INTO V_RESULT;
 
            V_MENSAJE :=
               REPLACE (V_MENSAJE,
                        '[' || GENERICO.DESCRIPCION_TAG || ']',
                        V_RESULT); 
            IF (V_CONTADOR = 1)
            THEN
            EXECUTE IMMEDIATE V_QUERY_REPLACE INTO V_OPERACION;
            P_ASUNTO := P_ASUNTO ||' - '||V_OPERACION;
            END IF;
         END IF;
         
      END LOOP;

        V_CONTADOR := 0;
        /*Si la plantilla tiene BAN_VALIDA_TAGS en 1 se deben de ir a consultar TAGS*/
      IF V_BAN_VALIDA_TAGS = 1
      THEN

         /*Se obtienen los TAGS no genericos, no obligatorios  (tags que no vendran en los parametros de entrada) */
         SELECT CAST (
                   COLLECT (
                     -- T_PARAM_CORREO (TTPL.DESCRIPCION_TAG, V_VALOR_CATEGORIA)) AS T_TAGS)
                     T_TAGS_DEFINICION (TTPL.DESCRIPCION_TAG, V_VALOR_CATEGORIA)) AS T_TAGS)
           INTO V_TAGS
           FROM TRE_TAGS_PLANTILLA TTP JOIN TCA_TAG_PLANTILLA TTPL
                   ON (TTP.ID_TCA_TAG_PLANTILLA = TTPL.ID)
          WHERE     TTP.ID_TCA_PLANTILLA_CORREO = P_ID_PLATILLA
                AND TTPL.BAN_GENERICO = 0
                AND TTP.REQUIERE_TAG = 0;

          /*Se ciclan los TAGS no genericos, no obligatorios */
         FOR IND IN 1 .. V_TAGS.COUNT
         LOOP
            
            /*Se obtiene el  SQL_QUERY por cada TAG*/
            SELECT SQL_QUERY
              INTO V_QUERY
              FROM TCA_TAG_PLANTILLA
             WHERE DESCRIPCION_TAG = V_TAGS (IND).TAG;
       
                /*Se llena variable con el query a ejecutar, reemplazando el valor de 
                ID_PARAMETRO por el  id del cliente o de la operacion*/    
            V_QUERY_REPLACE :=
               REPLACE (V_QUERY, ':ID_PARAMETRO', V_TAGS (IND).VALOR);
            
            /*Se llena variable para contar los registros del query y se ejecuta*/    
            V_QUERY_COUNTER :=
               'SELECT COUNT(1) FROM (' || V_QUERY_REPLACE || ')';

            EXECUTE IMMEDIATE V_QUERY_COUNTER INTO V_COUNT;

            /*Si existen registros se continua con el flujo*/    
            IF (V_COUNT = 1)
            THEN
            
                   /*Si ejecuta el query del tag y  el valor que retorna se sustituye en le 
                mensaje o variable de cuerpo del correo*/ 
               EXECUTE IMMEDIATE V_QUERY_REPLACE INTO V_RESULT;
             
            
               V_MENSAJE :=
                  REPLACE (V_MENSAJE,
                           '[' || V_TAGS (IND).TAG || ']',
                           V_RESULT);
            END IF;

         END LOOP;
         
      END IF;

       /*Se ciclan los tags no genericos, obligatorios (tags que llegaran en los parametros de entrada)*/
      FOR IND IN 1 .. P_TAGS.COUNT
      LOOP
           
           /*Se valida la existencia de los tags que llegan*/
         SELECT COUNT (DESCRIPCION_TAG)
           INTO V_EXIST
           FROM TCA_TAG_PLANTILLA
          WHERE DESCRIPCION_TAG = P_TAGS (IND).TAG
          /*validacion para evitar que falle si se llega a enviar un tag generico en los parametros*/
              AND BAN_GENERICO != 1;


         IF V_EXIST > 0
         THEN
            
              /*Se obtiene el  SQL_QUERY por cada TAG*/
            SELECT SQL_QUERY
              INTO V_QUERY
              FROM TCA_TAG_PLANTILLA
             WHERE DESCRIPCION_TAG = P_TAGS (IND).TAG;
            
             /*Se llena variable con el query a ejecutar, reemplazando el valor de 
                ID_PARAMETRO por el VALOR de los tags de entrada*/    
            V_QUERY_REPLACE :=
               REPLACE (V_QUERY, ':ID_PARAMETRO', P_TAGS (IND).VALOR);
            
            /*Se llena variable para contar los registros del query y se ejecuta*/    
            V_QUERY_COUNTER :=
               'SELECT COUNT(1) FROM (' || V_QUERY_REPLACE || ')';

            EXECUTE IMMEDIATE V_QUERY_COUNTER INTO V_COUNT;

              /*Si existen registros se continua con el flujo*/  
            IF (V_COUNT > 0)
            THEN
                
                /*Si existen enmiendas los datos que retorna la ejecucion de la sentencia se almacenan en un type table*/
                 IF  P_TAGS (IND).TAG = 'ID_ENMIENDA_TCC'
                 THEN

                 EXECUTE IMMEDIATE V_QUERY_REPLACE BULK COLLECT INTO V_TERMINOS;
                 
                  /*Si recorre el cursos y se concatenan los valores en una variable*/
                 FOR IND IN 1 .. V_TERMINOS.COUNT
                 LOOP
                    
                    V_RESULT_ENMIENDAS := V_RESULT_ENMIENDAS || V_TERMINOS(IND) || '<br>';

                 END LOOP;
                 
                 /*Se sustituye el tag de ID_ENMIENDA por la concatenacion anterior*/
                 V_MENSAJE :=
                        REPLACE (V_MENSAJE,
                                 '[' || P_TAGS (IND).TAG || ']',
                                 V_RESULT_ENMIENDAS);

                /*Si el numero de los tags ID_ACCION es mayor a 1 y si ademas el tag actual es ID_ACCION */
                ELSIF V_NO_ACCIONES > 1 AND P_TAGS (IND).TAG = 'ID_ACCION'
                THEN
                
                /*Se ejecuta el query del TAG de ACCION* y se concatenan los resultados por todos los TAGS*/
                  EXECUTE IMMEDIATE V_QUERY_REPLACE INTO V_RESULT;
                  
                  V_RESULT_ACCIONES := V_RESULT_ACCIONES || V_RESULT || '<br>';

                  V_CONTADOR := V_CONTADOR + 1;

                    /*Si el numero de parametros ID_ACCION es igual al numero de vueltas que ha dado el ciclo 
                    se sustituye el tag ID_ACCION por la concatenacion anterior*/
                  IF V_NO_ACCIONES = V_CONTADOR
                  THEN
                     V_MENSAJE :=
                        REPLACE (V_MENSAJE,
                                 '[' || P_TAGS (IND).TAG || ']',
                                 V_RESULT_ACCIONES);
                  END IF;
                  
                  /*Si no se da ninguno de los casos anteriores, se ejecuta el query y se sustituye en el mensaje*/
               ELSE
               V_CONTADOR := V_CONTADOR + 1;
                  EXECUTE IMMEDIATE V_QUERY_REPLACE INTO V_RESULT;
                 
                  V_MENSAJE :=
                     REPLACE (V_MENSAJE,
                              '[' || P_TAGS (IND).TAG || ']',
                              V_RESULT);
                IF V_CONTADOR = 1
                THEN
                               EXECUTE IMMEDIATE V_QUERY_REPLACE INTO V_TAREA;
                               P_ASUNTO := P_ASUNTO || ' - ' || V_TAREA;
                END IF;
               END IF;
               
            END IF;
            
         END IF;
         
      END LOOP;

     /*Si asigna la variable V_MENSAJE al parametro de salida P_MENSAJE*/
      P_MENSAJE := P_MENSAJE || V_MENSAJE;
      p_MENSAJE :=  regexp_replace(P_MENSAJE,'\[([_,a-z,0-9,A-Z,-]+)\]', '');
      
   END P_CONSTRUIR_CORREO;
   

PROCEDURE P_CONSULTAR_ROLES_USUARIOS (P_DESCRIPCION_PLANTILLA   IN     VARCHAR2,
                                      P_ID_OPERACION    IN     NUMBER,
                                      P_ID_CLIENTE      IN     NUMBER,
                                      P_ID_PROCESO IN     NUMBER,
                                      P_BANDERAS          IN     T_TAGS,
                                      P_ACCIONES          IN     T_TAGS,
                                      P_PARA_USUARIOS  OUT SYS_REFCURSOR,
                                      P_ERROR_USUARIOS   OUT SYS_REFCURSOR)
IS

 V_NO_ACCIONES       NUMBER (5);

BEGIN

     /*Se vacian tablas Temporales*/
     
         EXECUTE IMMEDIATE 'DELETE FROM TMP_PARA_USUARIOS';
         
         EXECUTE IMMEDIATE 'DELETE FROM TMP_ERROR_USUARIOS';


    /*Se agrega variable para determinar si  llegaron ACCIONES*/
    
     SELECT COUNT (TAG)
        INTO V_NO_ACCIONES
        FROM TABLE (CAST (P_ACCIONES AS T_TAGS))
       WHERE TAG = 'ID_ACCION';


     /*Se obtienen los ROLES y USUARIOS relacionados a la plantilla*/
        
    INSERT INTO TMP_PARA_USUARIOS (USUARIO_ROL)
       SELECT TRB.DESCRIPCION_CORTA
         FROM TCA_PLANTILLA_CORREO TPC
              JOIN TRE_PLANTILLA_CORREO_ROL_BPM TPCRB
                 ON (TPC.ID = TPCRB.ID_TCA_PLANTILLA_CORREO)
              JOIN TCA_ROL_BPM TRB
                 ON (TRB.ID = TPCRB.ID_TCA_ROL_BPM)
        WHERE TPC.DESCRIPCION = P_DESCRIPCION_PLANTILLA
        AND TPC.BAN_ESTATUS = 1;


     INSERT INTO TMP_PARA_USUARIOS (USUARIO_ROL)
        SELECT TPCU.LOGIN_USUARIO
          FROM TCA_PLANTILLA_CORREO TPC 
             JOIN TRE_PLANTILLA_CORREO_USER TPCU
                ON (TPC.ID = TPCU.ID_TCA_PLANTILLA_CORREO)
            WHERE TPC.DESCRIPCION = P_DESCRIPCION_PLANTILLA
             AND TPC.BAN_ESTATUS = 1;
             

     /*Se obtienen USUARIOS a notificación de ERROR*/
     
         INSERT INTO TMP_ERROR_USUARIOS (USUARIO_ROL)
        SELECT TPCU.LOGIN_USUARIO
          FROM TCA_PLANTILLA_CORREO TPC 
              JOIN TRE_PLANTILLA_CORREO_USER TPCU
                ON (TPC.ID = TPCU.ID_TCA_PLANTILLA_CORREO)
         WHERE TPC.DESCRIPCION = 'PLANTILLA_ERROR_ENVIO_CORREO'
          AND TPC.BAN_ESTATUS = 1;

   
    /*Se ciclan banderas*/
    FOR IND IN 1 .. P_BANDERAS.COUNT
    LOOP

        IF P_BANDERAS(IND).TAG = 'BAN_VALIDA_ET' AND P_BANDERAS(IND).VALOR = 1
        THEN
           
            IF P_ID_OPERACION IS NOT NULL OR P_ID_CLIENTE IS NOT NULL
            THEN

             /*Se obtiene roles del equipo de trabajo*/
                
            INSERT INTO TMP_PARA_USUARIOS (USUARIO_ROL)
            SELECT ET.USUARIO
              FROM EQUIPO_TRABAJO ET
                   JOIN TCA_PROCESO_BPM TPB
                      ON (ET.ID_TCA_PROCESO_BPM = TPB.ID)
                   LEFT JOIN OPERACION O
                      ON (ET.ID_OPERACION = O.ID_OPERACION)
                   LEFT JOIN CLIENTES C
                      ON (ET.ID_CLIENTE = C.ID_CLIENTE)
             WHERE ET.ID_TCA_PROCESO_BPM = P_ID_PROCESO
                   AND NVL (ET.ID_OPERACION, 0) =
                         NVL (NVL (P_ID_OPERACION, ET.ID_OPERACION), 0)
                   AND NVL (ET.ID_CLIENTE, 0) = NVL (NVL (P_ID_CLIENTE, ET.ID_CLIENTE), 0);
             
             END IF;
              
            
        ELSIF P_BANDERAS(IND).TAG = 'BAN_VALIDA_MCC' AND P_BANDERAS(IND).VALOR = 1
        THEN

             /*Se obtiene a los miembros CC */

            INSERT INTO TMP_PARA_USUARIOS (USUARIO_ROL)
               SELECT  URA.LOGIN_USUARIO
                 FROM USUARIO_REUNION_APROBACION URA
                      JOIN SOLICITUD_APROBACION SA
                         ON (URA.ID_SOLICITUD_APROBACION = SA.ID)
                      JOIN OPERACION O
                         ON (SA.ID_OPERACION = O.ID_OPERACION)
                WHERE     O.ID_OPERACION = P_ID_OPERACION
                      AND URA.CON_NOTIFICACION = 1
                      AND SA.ID_TCA_TIPO_SOLICITUD = 1 -- Tipo Operacion
                      AND SA.BAN_ESTATUS = 1;
                      


            ELSIF  P_BANDERAS(IND).TAG = 'BAN_VALIDA_RA' AND P_BANDERAS(IND).VALOR = 1 AND V_NO_ACCIONES > 0
            THEN

                  /*Se obtienen validadores de las acciones*/
                  
                  INSERT INTO TMP_PARA_USUARIOS (USUARIO_ROL)
                    SELECT TRB.DESCRIPCION_CORTA
                  FROM ACCION A JOIN TCA_ROL_BPM TRB
                          ON (A.ID_TCA_ROL_ASIGNADO = TRB.ID)
                 WHERE A.ID IN (SELECT VALOR
                                FROM TABLE (CAST (P_ACCIONES AS T_TAGS))
                               WHERE TAG = 'ID_ACCION');
         

            ELSIF  P_BANDERAS(IND).TAG = 'BAN_VALIDA_VA' AND P_BANDERAS(IND).VALOR = 1 AND V_NO_ACCIONES > 0
            THEN

                  /*Se obtienen responsables de las accion*/
                  
            INSERT INTO TMP_PARA_USUARIOS (USUARIO_ROL)
               SELECT TRB.DESCRIPCION_CORTA
                 FROM ACCION A
                      JOIN TCA_CATEGORIA_ACCION TCA
                         ON (TCA.ID = A.ID_TCA_CATEGORIA_ACCION)
                      JOIN TRE_CATEGORIA_VALIDADOR_ROL TRCVR
                         ON (TCA.ID = TRCVR.ID_TCA_CATEGORIA_ACCION)
                      JOIN TCA_ROL_BPM TRB
                         ON (TRB.ID = TRCVR.ID_TCA_ROL_BPM)
                WHERE A.ID IN (SELECT VALOR
                                 FROM TABLE (CAST (P_ACCIONES AS T_TAGS))
                                WHERE TAG = 'ID_ACCION');

           ELSIF  P_BANDERAS(IND).TAG = 'BAN_VALIDA_RO' AND P_BANDERAS(IND).VALOR = 1 
            THEN
                  /*Se obtiene al responsable de la operacion o del cliente*/
                    
                IF P_ID_OPERACION IS NOT NULL
                THEN 

                    INSERT INTO TMP_PARA_USUARIOS (USUARIO_ROL)
                        SELECT O.USUARIO
                            FROM OPERACION O
                        WHERE O.ID_OPERACION = P_ID_OPERACION;
                ELSE
                     INSERT INTO TMP_PARA_USUARIOS (USUARIO_ROL)
                         SELECT C.EJECUTIVO 
                            FROM CLIENTES C
                         WHERE C.ID_CLIENTE = P_ID_CLIENTE;

                END IF;

        END IF;
        
        
        COMMIT;
       
    END LOOP;

/*Se pasa la informacion de los usuarios o roles a los  SYS_REFCURSOR*/

    OPEN P_PARA_USUARIOS FOR
    SELECT USUARIO_ROL FROM TMP_PARA_USUARIOS;

    OPEN P_ERROR_USUARIOS FOR
    SELECT USUARIO_ROL FROM TMP_ERROR_USUARIOS;


END P_CONSULTAR_ROLES_USUARIOS;

PROCEDURE SP_VALIDAR_ENVIO_SUPERV_TCC (
  P_ID_SUPERVISION IN NUMBER,
  P_DESC_PLANTILLA IN VARCHAR,
  P_ID_OPERACION IN NUMBER,
  P_ESTADO            OUT VARCHAR2,
  P_CODIGO            OUT VARCHAR2,
  P_MENSAJE          OUT VARCHAR2)
IS 
  V_ESTADO VARCHAR2(5);
/*--Variables para evaliación de plantilla PLANTILLA_ACTUALIZACION_AVANCE_FISICIO_FINANCIERO--*/
  V_PLANTILLA_ACT_AVANCE_FIS_FIN VARCHAR2(256):='PLANTILLA_ACTUALIZACION_AVANCE_FISICIO_FINANCIERO';
  V_TBI_AVANCE_FISICO VARCHAR2(5);
  V_TBI_AVANCE_FINANCIERO VARCHAR(5);
  V_TIPO_ACCION VARCHAR2(32);
  V_AVANCE_FISICO NUMBER;
  V_AVANCE_FINANCIERO NUMBER;
/*--Variables para evaliación de plantilla PLANTILLA_ INICIO_SEGUIMIENTO_SIEMAS, PLANTILLA_ INICIO_EVALUACION_EXPOST,
PLANTILLA_ INICIO_EVALUACION_MEDIO_TERMINO--*/
  V_PLANTILLA_SEG_SIEMAS VARCHAR2(256):= 'PLANTILLA_ INICIO_SEGUIMIENTO_SIEMAS';
  V_PLANTILLA_SEG_EXPOST VARCHAR2(256):='PLANTILLA_ INICIO_EVALUACION_EXPOST';
  V_PLANTILLA_SEG_MED_TERM VARCHAR2(256):= 'PLANTILLA_ INICIO_EVALUACION_MEDIO_TERMINO';
  V_DESCRIPCION_CORTA VARCHAR2(64);
  V_TERMINO_ID_TCA_TIPO_AVANCE NUMBER (5,0);
  V_TERMINO_ID_TCA_TIPO_PORCENT NUMBER (5,0);
  V_TERMINO_PORCENTAJE NUMBER (7,4);
  V_TERMINO_PORCENTAJE_INICIAL NUMBER (7,4);
  V_TERMINO_PORCENTAJE_FINAL NUMBER (7,4);
  
BEGIN 

--Se obtienen los valores actuales de la supervisión
    SELECT AVANCE_FISICO, AVANCE_FINANCIERO
    INTO V_AVANCE_FISICO,V_AVANCE_FINANCIERO
    FROM SUPERVISION 
    WHERE ID=P_ID_SUPERVISION;
    



IF P_DESC_PLANTILLA = V_PLANTILLA_ACT_AVANCE_FIS_FIN 
 THEN 
  --Se verifica que la descripción de la plantilla corresponda a la actualización del avance físico y financiero
          --Se obtienen los valores anteriores del id de supervisión
          SELECT TSC.VALOR_ANTERIOR 
          INTO V_TBI_AVANCE_FISICO 
          FROM TBI_SUPERVISION_CAMPO TSC RIGHT JOIN  TBI_SUPERVISION TS
          ON TSC.ID_BITACORA=TS.ID
          WHERE TSC.CAMPO='AVANCE_FISICO' 
          AND TSC.ID_BITACORA=(SELECT MAX(ID) FROM TBI_SUPERVISION WHERE ID_SUPERVISION=P_ID_SUPERVISION AND TIPO_ACCION='MODIFICAR_SUPERVISION')
          AND TS.ID_SUPERVISION=P_ID_SUPERVISION;  
          
          SELECT TSC.VALOR_ANTERIOR 
          INTO V_TBI_AVANCE_FISICO 
          FROM TBI_SUPERVISION_CAMPO TSC RIGHT JOIN  TBI_SUPERVISION TS
          ON TSC.ID_BITACORA=TS.ID
          WHERE TSC.CAMPO='AVANCE_FINANCIERO' 
          AND TSC.ID_BITACORA=(SELECT MAX(ID) FROM TBI_SUPERVISION WHERE ID_SUPERVISION=P_ID_SUPERVISION AND TIPO_ACCION='MODIFICAR_SUPERVISION')
          AND TS.ID_SUPERVISION=P_ID_SUPERVISION; 
                    
          --Se evalúa si el avance físico actual o el financiero actual son diferentes a los valores anteriores 
          IF (V_AVANCE_FISICO != V_TBI_AVANCE_FISICO OR V_AVANCE_FINANCIERO != V_TBI_AVANCE_FINANCIERO)
          THEN 
            V_ESTADO := 'TRUE'; --Si son iguales se regresa el estado true para indicar que se debe realizar la notificación
          
          ELSE 
            V_ESTADO := 'FALSE'; --Si ninguno de los avances es diferente se regresa el estado false
            
          END IF;
       
    
    ELSE
  
     IF P_DESC_PLANTILLA = V_PLANTILLA_SEG_SIEMAS --Se verifica que la descripción de la plantilla corresponda a la verificación del porcentaje de inicio seguimiento siemas
      THEN
        V_DESCRIPCION_CORTA:='T801'; --Se asigna la descripción corta del termino de seguimiento siemas
      
     ELSIF P_DESC_PLANTILLA = V_PLANTILLA_SEG_EXPOST --Se verifica que la descripción de la plantilla corresponda a la verificación del porcentaje de inicio seguimiento EX POST
      THEN
        V_DESCRIPCION_CORTA:='T802'; --Se asigna la descripción corta del termino de seguimiento ex post
      
      ELSIF P_DESC_PLANTILLA = V_PLANTILLA_SEG_MED_TERM --Se verifica que la descripción de la plantilla corresponda a la verificación del porcentaje de inicio seguimiento medio término
      THEN
        V_DESCRIPCION_CORTA:='T803';
        
      END IF;
      
      SELECT T.ID_TCA_TIPO_AVANCE, T.ID_TCA_TIPO_PORCENTAJE, PORCENTAJE, PORCENTAJE_INICIAL, PORCENTAJE_FINAL
      INTO V_TERMINO_ID_TCA_TIPO_AVANCE, V_TERMINO_ID_TCA_TIPO_PORCENT, V_TERMINO_PORCENTAJE,V_TERMINO_PORCENTAJE_INICIAL,V_TERMINO_PORCENTAJE_FINAL
      FROM TERMINO T 
      LEFT OUTER JOIN TCA_TERMINO TT
      ON T.ID_TCA_TERMINO=TT.ID
      WHERE TT.DESCRIPCION_CORTA=V_DESCRIPCION_CORTA
      AND T.ID_OPERACION=P_ID_OPERACION
      AND T.BAN_ESTATUS = 1
      AND T.ID_TCA_ESTADO_TCC NOT IN (30, 22); -- Se obtienen los valores para las variables que sirven para verificar si se envía o no la notificación
      
      CASE V_TERMINO_ID_TCA_TIPO_PORCENT
          WHEN 1 THEN --Único
              CASE V_TERMINO_ID_TCA_TIPO_AVANCE 
                  WHEN 1 THEN --Avance físico
                      IF V_AVANCE_FISICO=V_TERMINO_PORCENTAJE
                      THEN 
                          V_ESTADO := 'TRUE'; --Si son iguales se regresa el estado true para indicar que se debe realizar la notificación
                          
                      ELSE 
                          V_ESTADO := 'FALSE'; --Si ninguno de los avances es diferente se regresa el estado false
                      END IF;
                      
                  WHEN 2 THEN  --Avance financiero
                      IF V_AVANCE_FINANCIERO=V_TERMINO_PORCENTAJE
                      THEN 
                          V_ESTADO := 'TRUE'; --Si son iguales se regresa el estado true para indicar que se debe realizar la notificación
                          
                      ELSE 
                          V_ESTADO := 'FALSE'; --Si ninguno de los avances es diferente se regresa el estado false
                      END IF; 
          
              END CASE;
          WHEN 2 THEN  --Rango       
              CASE V_TERMINO_ID_TCA_TIPO_AVANCE 
                  WHEN 1 THEN --Avance físico
                      IF V_AVANCE_FISICO=V_TERMINO_PORCENTAJE
                      THEN 
                          V_ESTADO := 'TRUE'; --Si son iguales se regresa el estado true para indicar que se debe realizar la notificación
                          
                      ELSE 
                          V_ESTADO := 'FALSE'; --Si ninguno de los avances es diferente se regresa el estado false
                      END IF;
                      
                  WHEN 2 THEN  --Avance financiero
                      IF V_AVANCE_FINANCIERO=V_TERMINO_PORCENTAJE
                      THEN 
                          V_ESTADO := 'TRUE'; --Si son iguales se regresa el estado true para indicar que se debe realizar la notificación
                          
                      ELSE 
                          V_ESTADO := 'FALSE'; --Si ninguno de los avances es diferente se regresa el estado false
                      END IF; 
          
              END CASE;
      END CASE;
      
    END IF;
  
  P_ESTADO:=V_ESTADO;
  P_CODIGO := 0;
  P_MENSAJE := 'Procedimiento  ejecutado  correctamente';
  
  EXCEPTION 
  WHEN OTHERS THEN 
      P_ESTADO:='FALSE';
      P_CODIGO := 1;
      P_MENSAJE := SQLERRM;
  
END SP_VALIDAR_ENVIO_SUPERV_TCC;

END PKG_ENVIAR_CORREO;