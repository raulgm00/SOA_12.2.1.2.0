CREATE OR REPLACE PROCEDURE SP_DUPLICAR_LINEA_CREDITO (
   P_N_LINEA      IN     VARCHAR2,
   P_DESCRIP      IN     VARCHAR2,
   P_MONTO        IN     NUMBER,
   P_N_CONTRATO   IN     NUMBER,
   P_CODIGO_RES      OUT NUMBER,
   P_MENSAJE         OUT VARCHAR2)
IS
   V_ID_LINEA_CREDITO   NUMBER;
   V_N_LINEA_CR         NUMBER;
   V_N_APROBA           NUMBER;
   V_ID_TERMINO         NUMBER;
BEGIN
   SELECT LINEA_CREDITO_SEQ.NEXTVAL INTO V_N_LINEA_CR FROM DUAL;

   SELECT LN.ID, TR.ID_APROBACION
     INTO V_ID_LINEA_CREDITO, V_N_APROBA
     FROM LINEA_CREDITO LN, TRE_LINEA_CREDITO_APROBACION TR
    WHERE     ID_CONTRATO = P_N_CONTRATO
          AND LN.ID = TR.ID_LINEA_CREDITO
          AND ROWNUM = 1;

   --   SELECT ID_APROBACION
   --     INTO V_N_APROBA
   --     FROM TRE_LINEA_CREDITO_APROBACION
   --    WHERE ID_LINEA_CREDITO = V_ID_LINEA_CREDITO;

   INSERT INTO LINEA_CREDITO (ID,
                              ID_CONTRATO,
                              NUMERO_LINEA_CREDITO,
                              DESCRIPCION_LINEA,
                              MONTO_LINEA,
                              FECHA_REGISTRO,
                              BAN_ESTATUS)
       VALUES (V_N_LINEA_CR,
               P_N_CONTRATO,
               P_N_LINEA,
               P_DESCRIP,
               P_MONTO,
               SYSDATE,
               1);


   INSERT INTO TRE_LINEA_CREDITO_APROBACION (ID,
                                             ID_LINEA_CREDITO,
                                             ID_APROBACION)
       VALUES (TRE_LINEA_CREDITO_APROBA_SEQ.NEXTVAL, V_N_LINEA_CR, V_N_APROBA);

   COMMIT;

   FOR REC IN (SELECT ID_TERMINO
                 FROM TRE_LINEA_CREDITO_TCC TrE
                WHERE TRe.ID_LINEA_CREDITO = V_ID_LINEA_CREDITO)
   LOOP
      SELECT TERMINO_SEQ.NEXTVAL INTO V_ID_TERMINO FROM DUAL;

      INSERT INTO TERMINO (ID,
                           ID_OPERACION,
                           NOMBRE,
                           DESCRIPCION,
                           ID_TCA_TERMINO,
                           ID_TCA_TIPO_FECHA_INICIO,
                           FECHA_INICIO,
                           PLAZO,
                           ID_TCA_FRECUENCIA_PLAZO,
                           FECHA_VENCIMIENTO, --10
                     ID_TCA_MONEDA,
                     MONTO,
                     TASA,
                     ID_TCA_TIPO_TASA,
                     FECHA,
                     FRECUENCIA_REVISION,
                     ID_TCA_FRECUENCIA_REVISION,
                     FECHA_INICIO_REVISION,
                     FRECUENCIA_PAGO_INTERES,
                     ID_TCA_FRECUENCIA_PAGO_INTERES,--20
                     FECHA_INICIO_PAGO_INTERES ,
                     FRECUENCIA_AMORTIZACION,
                     ID_TCA_FRECUENCIA_AMORTIZACION,
                     MORA,
                     PORCENTAJE_COBERTURA,
                     SE_APLICAN_RECURSOS_CONCESION,
                     SE_APLICAN_RECURSOS_EXTERNOS,
                     TIPO_CONTRAPARTE,
                     MONTO_MINIMO_DESEMBOLSO,
                     MONTO_MAXIMO_DESEMBOLSO,
                     TASA_MINIMA_DESEMBOLSO,
                     TASA_MAXIMA_DESEMBOLSO, --32
                     ID_TCA_ESTADO_TCC,
                     ID_TCA_SUB_ESTADO_TCC,
                     FECHA_REGISTRO,
                     BAN_ESTATUS,
                     ID_TERMINO_ENMENDADO,
                     FECHA_ENMIENDA, CLIENTE_GESTIONA_CONTRATACION)                    
         (SELECT V_ID_TERMINO,
                 ID_OPERACION,
                 NOMBRE,
                 DESCRIPCION,
                 ID_TCA_TERMINO,
                 ID_TCA_TIPO_FECHA_INICIO,
                 FECHA_INICIO,
                 PLAZO,
                 ID_TCA_FRECUENCIA_PLAZO,
                 FECHA_VENCIMIENTO,
                 ID_TCA_MONEDA,
                 MONTO,
                 TASA,
                 ID_TCA_TIPO_TASA,
                 FECHA,
                 FRECUENCIA_REVISION,
                 ID_TCA_FRECUENCIA_REVISION,
                 FECHA_INICIO_REVISION,
                 FRECUENCIA_PAGO_INTERES,
                 ID_TCA_FRECUENCIA_PAGO_INTERES,
                 FECHA_INICIO_PAGO_INTERES,
                 FRECUENCIA_AMORTIZACION,
                 ID_TCA_FRECUENCIA_AMORTIZACION,
                 MORA,
                 PORCENTAJE_COBERTURA,
                 SE_APLICAN_RECURSOS_CONCESION,
                 SE_APLICAN_RECURSOS_EXTERNOS,
                 TIPO_CONTRAPARTE,
                 MONTO_MINIMO_DESEMBOLSO,
                 MONTO_MAXIMO_DESEMBOLSO,
                 TASA_MINIMA_DESEMBOLSO,
                 TASA_MAXIMA_DESEMBOLSO,
                 ID_TCA_ESTADO_TCC,
                 ID_TCA_SUB_ESTADO_TCC,
                 SYSDATE,
                 BAN_ESTATUS,
                 ID_TERMINO_ENMENDADO,
                 FECHA_ENMIENDA,
                 CLIENTE_GESTIONA_CONTRATACION
            FROM TERMINO
           WHERE ID = REC.ID_TERMINO);

      COMMIT;

      INSERT INTO TRE_LINEA_CREDITO_TCC (ID,
                                         ID_LINEA_CREDITO,
                                         ID_TERMINO,
                                         FECHA_REGISTRO,
                                         BAN_ESTATUS)
          VALUES (TRE_LINEA_CREDITO_TCC_SEQ.NEXTVAL,
                  V_N_LINEA_CR,
                  V_ID_TERMINO,
                  SYSDATE,
                  1);

      INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                         NOMBRE_INSUMO,
                                         DESCRIPCION_ERROR,
                                         FECHA_REGISTRO)
          VALUES ('SP',
                  'SP_DUPLICAR_LINEA_CREDITO',
                  '-0' || P_MENSAJE,
                  SYSDATE);

      COMMIT;


      COMMIT;
   END LOOP;



   P_CODIGO_RES := 0;
   P_MENSAJE := 'insert exitoso ';
EXCEPTION
   WHEN OTHERS
   THEN
      P_CODIGO_RES := 1;
      P_MENSAJE := SQLERRM;

      INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                         NOMBRE_INSUMO,
                                         DESCRIPCION_ERROR,
                                         FECHA_REGISTRO)
          VALUES ('SP',
                  'SP_DUPLICAR_LINEA_CREDITO',
                  P_MENSAJE,
                  SYSDATE);

      COMMIT;
END;
/