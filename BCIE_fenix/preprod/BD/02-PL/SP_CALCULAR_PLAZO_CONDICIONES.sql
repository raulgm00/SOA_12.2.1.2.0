create or replace PROCEDURE SP_CALCULAR_PLAZO_CONDICIONES (
   P_CONDICIONES   IN     T_CONDICION, 
   P_ID_OPERACION  IN          NUMBER,
   P_ESTADO            OUT VARCHAR2,
   P_CODIGO            OUT VARCHAR2,
   P_MENSAJE          OUT VARCHAR2)
IS

V_FECHA_INICIO DATE;
V_FECHA_FINAL  DATE;
V_PLAZO NUMBER(3);
V_FRECUENCIA_PLAZO NUMBER(5);
V_PLAZO_FECHA_INICIAL DATE;
V_PLAZO_FECHA_FINAL DATE;
V_CON_PLAZO_ACTUAL NUMBER(1):=0;
V_FECHA_INICIO_PLAZO_ACTUAL DATE;
V_FECHA_FINAL_PLAZO_ACTUAL DATE;
V_CONTADOR_VALIDADORES NUMBER(2):=0;
V_ESTADO VARCHAR2(11);

CURSOR C_PLAZOS
IS
SELECT FECHA_INICIO_PLAZO, FECHA_FINAL_PLAZO
  FROM TMP_PLAZO_CONDICION;

BEGIN

FOR IND IN 1 .. P_CONDICIONES.COUNT 
    LOOP
    
    DELETE FROM  TMP_PLAZO_CONDICION;
    COMMIT;
    
    V_CON_PLAZO_ACTUAL := 0;

/* si la FECHA_VIGENCIA existe y  es mayor o igual a la fecha actual*/

   IF  P_CONDICIONES(IND).FECHA_VIGENCIA IS NOT NULL 
   AND 
   P_CONDICIONES(IND).FECHA_VIGENCIA >= TRUNC(SYSDATE)
    THEN
        V_ESTADO:= 'CUMPLIDA';

   ELSE  

        /*Si la condicion no tiene FECHA_VIGENCIA o no se cumple la condicion anterior 
        Se debe validar el  TIPO_FECHA_INICIO  para determinar como se obtendra la FECHA_INICIO*/
                 
        
        IF P_CONDICIONES(IND).ID_TCA_TIPO_FECHA_INICIO =  1  -- Aprobación
        THEN
                  SELECT T.FECHA
                   INTO V_FECHA_INICIO
                  FROM TERMINO T LEFT JOIN TCA_TERMINO TT
                          ON T.ID_TCA_TERMINO = TT.ID
                 WHERE  T.ID_OPERACION = P_ID_OPERACION
                       AND T.BAN_ESTATUS = 1                                -- Termino Activo
                       AND ID_TCA_ESTADO_TCC NOT IN (30, 22)      -- Estados TCC que No Aplican
                       AND TT.DESCRIPCION_CORTA = 'T602';            -- Tipo Termino Aprobación
         

        ELSIF P_CONDICIONES(IND).ID_TCA_TIPO_FECHA_INICIO =  3  -- Primer desembolso
        THEN
                SELECT MIN(CD.FECHA_EFECTIVA) FECHA_EFECTIVA
                INTO V_FECHA_INICIO
                  FROM OPERACION O
                       JOIN SOLICITUD_DESEMBOLSO SD
                          ON (O.ID_OPERACION = SD.ID_OPERACION)
                       JOIN TRE_SOLICITUD_LINEA_CREDITO TSLC
                          ON (SD.ID = TSLC.ID_SOLICITUD)
                       JOIN CONTRATO_DESEMBOLSO CD
                          ON (TSLC.ID_CONTRATO_DESEMBOLSO = CD.ID)
                 WHERE O.ID_OPERACION = P_ID_OPERACION
                 AND CD.CONTRATO_FLEXCUBE IS NOT NULL;


        ELSIF P_CONDICIONES(IND).ID_TCA_TIPO_FECHA_INICIO =  2  -- Escrituración
        THEN
                SELECT T.FECHA
                INTO V_FECHA_INICIO
                  FROM TERMINO T LEFT JOIN TCA_TERMINO TT
                          ON T.ID_TCA_TERMINO = TT.ID
                 WHERE T.ID_OPERACION = P_ID_OPERACION
                       AND T.BAN_ESTATUS = 1                                 -- Termino Activo
                       AND ID_TCA_ESTADO_TCC NOT IN (30, 22)      --Estados TCC que No Aplican
                       AND TT.DESCRIPCION_CORTA = 'T603';             --Tipo Termino Firma del Contrato

        ELSIF P_CONDICIONES(IND).ID_TCA_TIPO_FECHA_INICIO =  7  --Fecha específica
        THEN
                SELECT FECHA_INICIO
                  INTO V_FECHA_INICIO
                  FROM CONDICION
                 WHERE ID = P_CONDICIONES (IND).ID;

         END IF;

        /*Si las condiciones no tienen fecha final, se tomara la fecha actual como fecha final, 
        ademas se obtiene la fecha final del primer periodo*/
      
        SELECT
                 NVL (FECHA_FINAL, TRUNC (SYSDATE)), PLAZO,
                   ID_TCA_FRECUENCIA_PLAZO,
                   (CASE
                       WHEN ID_TCA_FRECUENCIA_PLAZO = 1
                       THEN
                         V_FECHA_INICIO + PLAZO
                       WHEN ID_TCA_FRECUENCIA_PLAZO = 2
                       THEN
                          ADD_MONTHS ( V_FECHA_INICIO , PLAZO)
                       WHEN ID_TCA_FRECUENCIA_PLAZO = 3
                       THEN
                          ADD_MONTHS ( V_FECHA_INICIO , (PLAZO * 12))
                    END)
              INTO V_FECHA_FINAL,
                   V_PLAZO,
                   V_FRECUENCIA_PLAZO,
                   V_PLAZO_FECHA_FINAL
              FROM CONDICION
             WHERE ID = P_CONDICIONES (IND).ID;


         /*Se inserta el primer plazo*/
         
         IF V_PLAZO_FECHA_FINAL <= V_FECHA_FINAL
         THEN

            INSERT INTO TMP_PLAZO_CONDICION
                VALUES (V_FECHA_INICIO, V_PLAZO_FECHA_FINAL);

            COMMIT;
         ELSE 
         
         INSERT INTO TMP_PLAZO_CONDICION
                VALUES (V_FECHA_INICIO, V_FECHA_FINAL);

            COMMIT;
         
        END IF;
         
           
        V_PLAZO_FECHA_INICIAL :=  V_PLAZO_FECHA_FINAL;
        

         /*Se genera ciclo para generar los plazos*/
        
        WHILE V_PLAZO_FECHA_FINAL <= V_FECHA_FINAL
        LOOP

            SELECT
               (CASE
                   WHEN ID_TCA_FRECUENCIA_PLAZO = 1
                   THEN
                     V_PLAZO_FECHA_INICIAL + PLAZO
                   WHEN ID_TCA_FRECUENCIA_PLAZO = 2
                   THEN
                      ADD_MONTHS ( V_PLAZO_FECHA_INICIAL , PLAZO)
                   WHEN ID_TCA_FRECUENCIA_PLAZO = 3
                   THEN
                      ADD_MONTHS ( V_PLAZO_FECHA_INICIAL , (PLAZO * 12))
                END)
          INTO V_PLAZO_FECHA_FINAL
          FROM CONDICION
         WHERE ID = P_CONDICIONES (IND).ID;
            
            IF V_PLAZO_FECHA_FINAL <= V_FECHA_FINAL
            THEN
            
            INSERT INTO TMP_PLAZO_CONDICION
            VALUES (V_PLAZO_FECHA_INICIAL, V_PLAZO_FECHA_FINAL);

             V_PLAZO_FECHA_INICIAL:=  V_PLAZO_FECHA_FINAL;

         ELSE 

          INSERT INTO TMP_PLAZO_CONDICION
            VALUES (V_PLAZO_FECHA_INICIAL, V_FECHA_FINAL);
            
         END IF;
         
        END LOOP;
        
        COMMIT;
        
        
       /*Se obtiene plazo actual*/
       
        FOR PLAZO IN C_PLAZOS
        LOOP

            IF TRUNC(SYSDATE) >=PLAZO.FECHA_INICIO_PLAZO AND  TRUNC(SYSDATE) <= PLAZO.FECHA_FINAL_PLAZO
            THEN
                V_FECHA_INICIO_PLAZO_ACTUAL := PLAZO.FECHA_INICIO_PLAZO;
                V_FECHA_FINAL_PLAZO_ACTUAL := PLAZO. FECHA_FINAL_PLAZO;
                V_CON_PLAZO_ACTUAL := 1;
            END IF;
         
        END LOOP;
        
        
          SELECT COUNT(FECHA_REGISTRO)
          INTO  V_CONTADOR_VALIDADORES
              FROM VALIDACION_CONDICION
             WHERE     ID_CONDICION = P_CONDICIONES (IND).ID
                   AND ESTADO = 1
                   AND FINALIZO_TAREA = 1
                   AND ES_VALIDADOR = 1;
           
    
           /*Se revisa que a fecha de registro de cada uno de los validadores este dentro del plazo actual*/

            IF V_CONTADOR_VALIDADORES > 0
            THEN
            
                FOR VALIDADOR IN 
                 (
                 SELECT FECHA_REGISTRO
                  FROM VALIDACION_CONDICION
                 WHERE     ID_CONDICION = P_CONDICIONES (IND).ID
                       AND ESTADO = 1
                       AND FINALIZO_TAREA = 1
                       AND ES_VALIDADOR = 1
                      )
                LOOP
                IF V_FECHA_FINAL < TRUNC(SYSDATE)
                THEN
                V_ESTADO:= 'CUMPLIDA';
                
                ELSE
                
                    IF TRUNC(VALIDADOR.FECHA_REGISTRO)>=V_FECHA_INICIO_PLAZO_ACTUAL AND TRUNC(VALIDADOR.FECHA_REGISTRO)<=V_FECHA_FINAL_PLAZO_ACTUAL
                    THEN
                        V_ESTADO:= 'CUMPLIDA'; 

                    ELSE
                         V_ESTADO:= 'NO_CUMPLIDA'; 
                         
                    END IF;
                    
                END IF; 
                
                     EXIT WHEN  V_ESTADO= 'NO_CUMPLIDA'; 
                END LOOP;
                
            /*En caso de que no existan validadores, con base a la fecha actual se determina el estado*/
           ELSE
                
                IF V_CON_PLAZO_ACTUAL = 1
                THEN   
                    V_ESTADO:= 'NO_CUMPLIDA';  
                    
                 ELSIF TRUNC(SYSDATE) > V_FECHA_FINAL
                 THEN 
                     V_ESTADO:= 'CUMPLIDA';   
                        
                 ELSIF TRUNC(SYSDATE) < V_FECHA_INICIO
                 THEN
                    V_ESTADO:= 'CUMPLIDA';   
				
				 ELSE
                    V_ESTADO:= 'NO_CUMPLIDA'; 
                    
                 END IF;
            

           END IF;
           
   END IF;

 EXIT WHEN  V_ESTADO= 'NO_CUMPLIDA'; 
  
END LOOP;

    P_ESTADO:= V_ESTADO;
    P_CODIGO := 0;
    P_MENSAJE := 'Procedimiento  ejecutado  correctamente';
  
  EXCEPTION 
  WHEN OTHERS
    THEN 
      P_CODIGO := 1;
      P_MENSAJE := SQLERRM;
      
END;
/
  