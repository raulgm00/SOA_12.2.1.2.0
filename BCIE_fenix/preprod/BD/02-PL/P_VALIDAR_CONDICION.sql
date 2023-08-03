create or replace PROCEDURE P_VALIDAR_CONDICION (  
               P_CONDICION IN OUT NUMBER ,
               P_OBSERVACION IN OUT VARCHAR2,
               P_LOGIN_USUARIO IN OUT VARCHAR2,
               P_NOMBRE_USUARIO IN OUT VARCHAR2,
               P_FECHA_REGISTRO IN OUT TIMESTAMP,
               P_BAN_ESTATUS IN OUT NUMBER ,
               P_ID_TCA_TAREA_BPM IN OUT NUMBER ,
               P_ES_PRINCIPAL IN OUT NUMBER ,
               P_ROL IN OUT NUMBER , 
               P_ESTADO IN OUT NUMBER, 
               P_ES_VALIDADOR IN OUT NUMBER, 
               P_AGRUPADOR IN NUMBER,
               P_MENSAJE OUT VARCHAR2,
               P_CODE OUT VARCHAR2) AS


V_ID_OC NUMBER;
v_exit NUMBER;
V_ID_EVENTO_CONDICION NUMBER;

BEGIN

    IF P_OBSERVACION IS NOT NULL THEN 
    
      SELECT OBSERVACION_CONDICION_SEQ.NEXTVAL INTO V_ID_OC FROM DUAL;               --V_ID_OC
      
       INSERT INTO OBSERVACION_CONDICION (ID, ID_CONDICION, OBSERVACION, LOGIN_USUARIO, NOMBRE_USUARIO,  FECHA_REGISTRO, BAN_ESTATUS, ID_TCA_TAREA_BPM, ES_PRINCIPAL, AGRUPADOR)
           VALUES (V_ID_OC,
                   P_CONDICION,
                   P_OBSERVACION,
                   P_LOGIN_USUARIO,
                   P_NOMBRE_USUARIO,
                   P_FECHA_REGISTRO,
                   P_BAN_ESTATUS,
                   P_ID_TCA_TAREA_BPM,
                   P_ES_PRINCIPAL,
                   P_AGRUPADOR);
                  
    ELSE 
           
             DBMS_OUTPUT.PUT_LINE ('No se registran Observacion Condiciones ');
           
    END IF;
           
           
       SELECT COUNT ( * ) INTO v_exit
         FROM VALIDACION_CONDICION
        WHERE     ID_CONDICION = P_CONDICION
              AND  ID_ROL_BPM = P_ROL
              AND LOGIN_USUARIO = P_LOGIN_USUARIO
              AND AGRUPADOR = P_AGRUPADOR; --Cuenta si existe una relacion en la BD
    
       IF v_exit > 0 THEN
          UPDATE VALIDACION_CONDICION
             SET ID_OBSERVACION =V_ID_OC,
             ES_VALIDADOR=P_ES_VALIDADOR,
            ESTADO=P_ESTADO,
             FECHA_REGISTRO =P_FECHA_REGISTRO
           WHERE     ID_CONDICION = P_CONDICION
                 AND ID_ROL_BPM = P_ROL
                 AND LOGIN_USUARIO = P_LOGIN_USUARIO
                 AND AGRUPADOR = P_AGRUPADOR;
       ELSE
          INSERT INTO VALIDACION_CONDICION (ID_CONDICION, ID_ROL_BPM, LOGIN_USUARIO, NOMBRE_USUARIO, ID_OBSERVACION, ES_VALIDADOR, ESTADO, FECHA_REGISTRO,AGRUPADOR)
              VALUES (P_CONDICION,
                      P_ROL,
                      P_LOGIN_USUARIO,
                      P_NOMBRE_USUARIO,
                      V_ID_OC,
                      P_ES_VALIDADOR,
                      P_ESTADO,
                      P_FECHA_REGISTRO,
                      P_AGRUPADOR);
       END IF;
      
        IF P_ES_VALIDADOR = 0 AND P_ESTADO = 0
         THEN
            UPDATE TRE_TRANSACCION_CONDICION
               SET ID_TCA_ESTADO_TCC = 23 ,ID_TCA_SUB_ESTADO_TCC=  29
             WHERE ID_CONDICION = P_CONDICION AND AGRUPADOR = P_AGRUPADOR;
             
             COMMIT;
         ELSE
            IF P_ES_VALIDADOR = 0 AND P_ESTADO = 1
            THEN
               UPDATE TRE_TRANSACCION_CONDICION
                  SET ID_TCA_ESTADO_TCC = 23 , ID_TCA_SUB_ESTADO_TCC =  21
                WHERE ID_CONDICION = P_CONDICION AND AGRUPADOR = P_AGRUPADOR;
            ELSE
               IF P_ES_VALIDADOR = 1 AND P_ESTADO = 0
               THEN
                  UPDATE TRE_TRANSACCION_CONDICION
                     SET ID_TCA_ESTADO_TCC = 23, ID_TCA_SUB_ESTADO_TCC =  21
                   WHERE ID_CONDICION = P_CONDICION AND AGRUPADOR = P_AGRUPADOR;
               ELSE
                  UPDATE TRE_TRANSACCION_CONDICION
                     SET ID_TCA_ESTADO_TCC = 23, ID_TCA_SUB_ESTADO_TCC = 25
                   WHERE ID_CONDICION = P_CONDICION AND AGRUPADOR = P_AGRUPADOR;
               END IF;
            END IF;
         END IF;
      
      P_CODE := 0;
      
   EXCEPTION
WHEN NO_DATA_FOUND THEN

  DBMS_OUTPUT.PUT_LINE ('Se ejecuta cuando ocurre una excepcion de tipo NO_DATA_FOUND ');
  P_CODE := 1;
  P_MENSAJE := SQLERRM;

WHEN OTHERS THEN

 P_MENSAJE := SQLERRM;  
 P_CODE := 1;
 DBMS_OUTPUT.PUT_LINE ('Error ');

END;