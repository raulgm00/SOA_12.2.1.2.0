
CREATE OR REPLACE TYPE T_EXCEPCION_SOLICITUD FORCE AS OBJECT
 (
 ID                 NUMBER(12) ,
  EXCEPTUADO   NUMBER(12),
  INSTANCIA_PROCESO       VARCHAR2(50),
  EN_PROCESO_EXCEPCION       NUMBER (1)
  );
/

CREATE OR REPLACE TYPE T_LISTA_EXCEPCION_S AS TABLE  OF T_EXCEPCION_SOLICITUD;
/

CREATE OR REPLACE PROCEDURE SP_TRE_EXCEPCION_SOLICITUD (
   P_LISTA_EXCEPCION_S   IN      T_LISTA_EXCEPCION_S,
   P_CODIGO_RES             OUT NUMBER,
   P_MENSAJE                OUT VARCHAR2)
AS
   V_ID_SOLICITUD           NUMBER (12);
   V_EXCEPTUADO             NUMBER (12);
err_num NUMBER;
   V_INSTANCIA_PROCESO      VARCHAR2 (50);
   V_EN_PROCESO_EXCEPCION   NUMBER (1);
   
   V_ID_REGLA NUMBER;
   
BEGIN



   IF (P_LISTA_EXCEPCION_S.COUNT > 0)
   THEN
      FOR IN_LIS IN 1 .. P_LISTA_EXCEPCION_S.COUNT
      LOOP
         IF P_LISTA_EXCEPCION_S (IN_LIS).ID IS NOT NULL
         THEN
         
         SELECT ID_SOLICITUD , ID_TCA_REGLA INTO V_ID_SOLICITUD , V_ID_REGLA FROM TRE_EXCEPCION_SOLICITUD 
         WHERE  ID = P_LISTA_EXCEPCION_S (IN_LIS).ID;
         
         
--            V_ID_SOLICITUD := P_LISTA_EXCEPCION_S (IN_LIS).ID;

            V_EXCEPTUADO := P_LISTA_EXCEPCION_S (IN_LIS).EXCEPTUADO;

            UPDATE TRE_EXCEPCION_SOLICITUD
               SET EXCEPTUADO = NVL(P_LISTA_EXCEPCION_S (IN_LIS).EXCEPTUADO ,EXCEPTUADO)  ,
                   INSTANCIA_PROCESO =NVL( P_LISTA_EXCEPCION_S (IN_LIS).INSTANCIA_PROCESO, INSTANCIA_PROCESO ),
                   EN_PROCESO_EXCEPCION = NVL(                      P_LISTA_EXCEPCION_S (IN_LIS).EN_PROCESO_EXCEPCION, EN_PROCESO_EXCEPCION)
             WHERE ID = P_LISTA_EXCEPCION_S (IN_LIS).ID;



IF V_EXCEPTUADO IS NOT NULL  THEN 

            UPDATE TRE_REGLA_DESEMBOLSO
               SET EXCEPTUADO = V_EXCEPTUADO,
                   FECHA_EXCEPCION = SYSDATE,
                   USUARIOEXCEPTUA =
                      (SELECT USUARIO
                         FROM TBI_PROCESO_OPERACION
                        WHERE INSTANCIA_PROCESO =
                                 P_LISTA_EXCEPCION_S (
                                    IN_LIS).INSTANCIA_PROCESO
                              AND ID_TAREA = 165
                              AND BAN_ES_FIN_ACTIVIDAD = 1)
             WHERE ID_DESEMBOLSO IN (SELECT ID_CONTRATO_DESEMBOLSO
                                       FROM TRE_SOLICITUD_LINEA_CREDITO
                                      WHERE ID_SOLICITUD = V_ID_SOLICITUD)
                                      AND ID_TCA_REGLA =V_ID_REGLA ;
                                      
                                      END IF;
         END IF;
      END LOOP;
   END IF;
   
P_CODIGO_RES := 1;
    P_MENSAJE := 'Procedimiento  ejecutado  correctamente !';  
    
    
    EXCEPTION

   WHEN OTHERS
   THEN
   
   P_CODIGO_RES := 0;
      err_num := SQLCODE;
      P_MENSAJE := SQLERRM;

      INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                         NOMBRE_INSUMO,
                                         DESCRIPCION_ERROR,
                                         FECHA_REGISTRO)
          VALUES ('SP',
                  'TRE_EXCEPCION_SOLICITUD_SP',
                     'Solicitud: '
                  || NULL
                  || ' Error:'
                  || TO_CHAR (err_num)
                  || ' '
                  || SUBSTR (P_MENSAJE, 1, 520),
                  TO_DATE (SYSDATE, 'DD-MM-YY HH24:MI'));
END;
/
