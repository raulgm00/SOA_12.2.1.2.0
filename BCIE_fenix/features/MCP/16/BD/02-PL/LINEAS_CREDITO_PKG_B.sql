CREATE OR REPLACE PACKAGE BODY LINEAS_CREDITO_PKG
IS
   PROCEDURE ACTUALIZAR_LINEAS (
      P_ID_APROBACION    IN     NUMBER,
      P_ID_CONTRATO      IN     NUMBER,
      P_LINEAS_CREDITO   IN     T_LINEASCREDITO_APRO,
      P_RESULTADO           OUT VARCHAR2,
      P_MENSAJE             OUT VARCHAR2)
   IS
      V_ID_LINEA_SEQ    NUMBER (12);

      V_ID_LINEA_CRED   NUMBER (12);
   BEGIN
      
      FOR I IN 1 .. P_LINEAS_CREDITO.COUNT
      LOOP
         IF TO_CHAR (P_LINEAS_CREDITO (I).ID) IS NULL
         THEN
            SELECT LINEA_CREDITO_SEQ.NEXTVAL INTO V_ID_LINEA_SEQ FROM DUAL;

            INSERT INTO LINEA_CREDITO (ID,
                                       ID_CONTRATO,
                                       NUMERO_LINEA_CREDITO,
                                       DESCRIPCION_LINEA,
                                       MONTO_LINEA,
                                       FECHA_REGISTRO,
                                       BAN_ESTATUS)
                VALUES (V_ID_LINEA_SEQ,
                        P_ID_CONTRATO,
                        P_LINEAS_CREDITO (I).NUMERO_LINEA_CREDITO,
                        P_LINEAS_CREDITO (I).DESCRIPCION_LINEA,
                        P_LINEAS_CREDITO (I).MONTO_LINEA,
                        trunc(SYSDATE),
                        1);

            INSERT INTO TRE_LINEA_CREDITO_APROBACION (ID,
                                                      ID_LINEA_CREDITO,
                                                      ID_APROBACION)
                VALUES (TRE_LINEA_CREDITO_APROBA_SEQ.NEXTVAL,
                        V_ID_LINEA_SEQ,
                        P_ID_APROBACION);
         ELSIF P_LINEAS_CREDITO (I).ESTATUS = 1
               AND TO_CHAR (P_LINEAS_CREDITO (I).ID) IS NOT NULL
         THEN
            UPDATE LINEA_CREDITO
               SET NUMERO_LINEA_CREDITO =
                      P_LINEAS_CREDITO (I).NUMERO_LINEA_CREDITO,
                   DESCRIPCION_LINEA = P_LINEAS_CREDITO (I).DESCRIPCION_LINEA,
                   MONTO_LINEA = P_LINEAS_CREDITO (I).MONTO_LINEA
             WHERE ID = P_LINEAS_CREDITO (I).ID;
         ELSIF P_LINEAS_CREDITO (I).ESTATUS = 0
               AND TO_CHAR (P_LINEAS_CREDITO (I).ID) IS NOT NULL
         THEN
            DELETE TRE_LINEA_CREDITO_APROBACION
             WHERE ID_LINEA_CREDITO = P_LINEAS_CREDITO (I).ID
                   AND ID_APROBACION = P_ID_APROBACION;

            DELETE LINEA_CREDITO
             WHERE ID = P_LINEAS_CREDITO (I).ID;
         END IF;

         P_RESULTADO := 'OK';
         P_MENSAJE := 'La actualizacion se ha realizado exitosamente';
      END LOOP;
   
   END ACTUALIZAR_LINEAS;
END LINEAS_CREDITO_PKG;
/