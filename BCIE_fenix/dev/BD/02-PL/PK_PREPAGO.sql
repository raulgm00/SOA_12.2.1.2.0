CREATE OR REPLACE PACKAGE PK_PREPAGO
IS
   PROCEDURE OBTENER_TABLAS_PREPAGO (P_ID_PREPAGO    IN       NUMBER,
                                     P_OBSERVACIONES      OUT SYS_REFCURSOR,
                                     P_PENALIDAD          OUT SYS_REFCURSOR,
                                     P_CONDICIONES        OUT SYS_REFCURSOR,
                                     P_COBERTURAS         OUT SYS_REFCURSOR,
                                     P_VENTA_CARTERA      OUT SYS_REFCURSOR,
                                     P_FUENTES_EXTERNAS   OUT SYS_REFCURSOR);

                                    
                                     
                                     
PROCEDURE OBTENER_INFORMACION_PREPAGO (P_ID_PREPAGO    IN       NUMBER,
                                     P_PREPAGO     OUT SYS_REFCURSOR,
                                      P_CARGOS     OUT SYS_REFCURSOR,
                                      P_BANCOS    OUT SYS_REFCURSOR,
                                      P_INTERESES OUT NUMBER );

END PK_PREPAGO;
/

CREATE OR REPLACE PACKAGE BODY FENIX.PK_PREPAGO
IS
   PROCEDURE OBTENER_TABLAS_PREPAGO (
      P_ID_PREPAGO         IN     NUMBER,
      P_OBSERVACIONES         OUT SYS_REFCURSOR,
      P_PENALIDAD             OUT SYS_REFCURSOR,
      P_CONDICIONES           OUT SYS_REFCURSOR,
      P_COBERTURAS            OUT SYS_REFCURSOR,
      P_VENTA_CARTERA         OUT SYS_REFCURSOR,
      P_FUENTES_EXTERNAS      OUT SYS_REFCURSOR)
   IS
   BEGIN
      /*Observaciones*/
      OPEN P_OBSERVACIONES FOR
         SELECT ROL.DESCRIPCION_CORTA,
                OB.NOMBRE_USUARIO,
                OB.FECHA_REGISTRO,
                TO_CHAR (OB.FECHA_REGISTRO, 'HH24:MM') AS HORA_REGISTRO,
                OB.OBSERVACION
           FROM OBSERVACION_PREPAGO OB
                JOIN CARGO_PREPAGO CP
                   ON OB.ID = CP.ID_OBSERVACION
                JOIN PREPAGO PR
                   ON CP.ID_PREPAGO = PR.ID
                JOIN TCA_ROL_BPM ROL
                   ON CP.ID_TCA_ROL_BPM = ROL.ID
          WHERE PR.ID = P_ID_PREPAGO;

      /* Detalle de penalidad */

      OPEN P_PENALIDAD FOR
         SELECT VCD.NUMERO_TESORERIA,
                VCD.NUMERO_CONTRATO,
                TTM.DESCRIPCION_CORTA MONEDA,
                TTR.DESCRIPCION RESOLUCION,
                DP.FECHA_INICIO,
                DP.FECHA_FIN,
                DP.PLAZO PLAZO_TRANSCURRIDO,
                DP.FRACCION_LIBOR,
                NVL (DP.SPREAD, 0) SPREAD,
                NVL (DP.TASA_PREPAGO, 0) TASA_PREPAGO,
                NVL (TPC.MONTO_PREPAGO, 0) MONTO_PREPAGO,
                NVL (DP.MONTO_PENALIDAD, 0) PENALIDAD,
                NVL (DP.INTERESES, 0) INTERESES,
                TPC.FECHA_PROXIMO_PAGO PROXIMO_PAGO,
                CASE (SELECT COUNT (VFE.CONTRATO_DESEMBOLSO)
                        FROM VTA_FUENTES_EXTERNAS VFE
                       WHERE VFE.CONTRATO_DESEMBOLSO = VCD.NUMERO_CONTRATO
                      -- AND CODIGO_LINEA_PASIVA NOT IN ('LP-588', 'LP-587'  )
					  )
                   WHEN 0 THEN 'N'
                   ELSE 'S'
                END
                   CON_FUENTES
           FROM PREPAGO PR
                JOIN TRE_PREPAGO_CONTRATO TPC
                   ON (PR.ID = TPC.ID_PREPAGO)
                JOIN VTA_CONTRATO_DESEMBOLSO VCD
                   ON (TPC.CONTRATO_FLEXCUBE = VCD.NUMERO_CONTRATO)
                JOIN DETALLE_PENALIDAD DP
                   ON (TPC.ID = DP.ID_TRE_PREPAGO_CONTRATO)
                JOIN TCA_TIPO_MONEDA TTM
                   ON (TPC.ID_TCA_TIPO_MONEDA = TTM.ID)
                JOIN TCA_TIPO_RESOLUCION TTR
                   ON (TTR.ID = PR.ID_TCA_TIPO_RESOLUCION)
          WHERE PR.ID = P_ID_PREPAGO;


      /* Condiciones Especiales */
      OPEN P_CONDICIONES FOR
         SELECT LC.NUMERO_LINEA_CREDITO,
                LC.DESCRIPCION_COND_ESPECIAL DESCRIPCION_CONDICION
           FROM TRE_PREPAGO_CONTRATO TPC
                JOIN PREPAGO PR
                   ON (TPC.ID_PREPAGO = PR.ID)
                JOIN VTA_CONTRATO_DESEMBOLSO VCD
                   ON (TPC.CONTRATO_FLEXCUBE = VCD.NUMERO_CONTRATO)
                JOIN LINEA_CREDITO LC
                   ON (VCD.CODIGO_LINEA_CREDITO = LC.ID_FLEXCUBE)
          WHERE PR.ID = P_ID_PREPAGO;


      /* Coberturas */
      OPEN P_COBERTURAS FOR
         SELECT LC.NUMERO_LINEA_CREDITO,
                VC.CONTRATO_DESEMBOLSO,
                VC.CODIGO_COBERTURA,
                VC.CONTRAPARTE,
                VC.FECHA_EFECTIVA,
                VC.FECHA_VENCIMIENTO
           FROM VTA_COBERTURAS VC
                LEFT JOIN VTA_CONTRATO_DESEMBOLSO VCD
                   ON (VC.CONTRATO_DESEMBOLSO = VCD.NUMERO_CONTRATO)
                JOIN LINEA_CREDITO LC
                   ON (VCD.CODIGO_LINEA_CREDITO = LC.ID_FLEXCUBE)
                JOIN TRE_PREPAGO_CONTRATO TPC
                   ON (VCD.NUMERO_CONTRATO = TPC.CONTRATO_FLEXCUBE)
                JOIN PREPAGO PR
                   ON (PR.ID = TPC.ID_PREPAGO) AND PR.ID = P_ID_PREPAGO;


      /* Venta de Cartera */
      OPEN P_VENTA_CARTERA FOR
         SELECT LC.NUMERO_LINEA_CREDITO,
                VCD.NUMERO_CONTRATO CONTRATO_DESEMBOLSO,
                VCD.CODIGO_FONDO FONDO
           FROM PREPAGO PR
                JOIN TRE_PREPAGO_CONTRATO TPC
                   ON (PR.ID = TPC.ID_PREPAGO)
                JOIN VTA_CONTRATO_DESEMBOLSO VCD
                   ON (TPC.CONTRATO_FLEXCUBE = VCD.NUMERO_CONTRATO)
                JOIN LINEA_CREDITO LC
                   ON (VCD.CODIGO_LINEA_CREDITO = LC.ID_FLEXCUBE)
          WHERE PR.ID = P_ID_PREPAGO AND VCD.CODIGO_FONDO = '98';

      /* Fuente Externa*/
      OPEN P_FUENTES_EXTERNAS FOR
         SELECT LC.NUMERO_LINEA_CREDITO,
                VCD.NUMERO_CONTRATO CONTRATO_DESEMBOLSO,
                FUE.DESC_FUENTE,
                FUE.CODIGO_LINEA_PASIVA,
                NVL (FUE.MONTO, 0) MONTO
           FROM VTA_FUENTES_EXTERNAS FUE
                LEFT JOIN VTA_CONTRATO_DESEMBOLSO VCD
                   ON FUE.CONTRATO_DESEMBOLSO = VCD.NUMERO_CONTRATO
                JOIN LINEA_CREDITO LC
                   ON (VCD.CODIGO_LINEA_CREDITO = LC.ID_FLEXCUBE)
                JOIN TRE_PREPAGO_CONTRATO TPC
                   ON (VCD.NUMERO_CONTRATO = TPC.CONTRATO_FLEXCUBE)
                JOIN PREPAGO PR
                   ON (PR.ID = TPC.ID_PREPAGO)
          WHERE PR.ID = P_ID_PREPAGO;
   END OBTENER_TABLAS_PREPAGO;


   PROCEDURE OBTENER_INFORMACION_PREPAGO (
      P_ID_PREPAGO   IN     NUMBER,
      P_PREPAGO         OUT SYS_REFCURSOR,
      P_CARGOS          OUT SYS_REFCURSOR,
      P_BANCOS          OUT SYS_REFCURSOR,
      P_INTERESES       OUT NUMBER)
   IS
   BEGIN
      OPEN P_PREPAGO FOR
           SELECT PR.ID ID_PREPAGO,
                  PAIS.DESCRIPCION_CORTA PAIS,
                  CLI.ID_FLEXCUBE,
                  CLI.RAZON_SOCIAL,
                  OPE.ID_OPERACION,
                  OPE.NOMBRE,
                  TTM.DESCRIPCION DESCRIPCION_MONEDA,
                  PR.FECHA_SOLICITUD,
                  PR.FECHA_PREPAGO,
                  TTP.DESCRIPCION_CORTA TIPO_PREPAGO,
                  NVL (PR.MONTO_PREPAGO, 0) MONTO_PREPAGO,
                  TTM.DESCRIPCION_CORTA MONEDA,
                  PR.ID_TCA_TIPO_RESOLUCION,
                  TTR.DESCRIPCION_CORTA DESCRIPCION_RESOLUCION
             FROM TRE_PREPAGO_CONTRATO TPC
                  JOIN PREPAGO PR
                     ON (TPC.ID_PREPAGO = PR.ID)
                  JOIN TCA_TIPO_MONEDA TTM
                     ON (PR.ID_TCA_TIPO_MONEDA = TTM.ID)
                  JOIN TCA_TIPO_PREPAGO TTP
                     ON (PR.ID_TCA_TIPO_PREPAGO = TTP.ID)
                  JOIN VTA_CONTRATO_DESEMBOLSO VCD
                     ON (TPC.CONTRATO_FLEXCUBE = VCD.NUMERO_CONTRATO)
                  JOIN LINEA_CREDITO LC
                     ON (VCD.CODIGO_LINEA_CREDITO = LC.NUMERO_LINEA_CREDITO)
                  JOIN CONTRATO C
                     ON (LC.ID_CONTRATO = C.ID)
                  JOIN OPERACION OPE
                     ON (C.ID_OPERACION = OPE.ID_OPERACION)
                      and TO_CHAR(OPE.ID_OPERACION) =VCD.OPERACION
                  JOIN CLIENTES CLI
                     ON (OPE.ID_CLIENTE = CLI.ID_CLIENTE)
                  JOIN CAT_PAISES PAIS
                     ON (PAIS.ID = CLI.PAIS)
                  LEFT JOIN TCA_TIPO_RESOLUCION TTR
                     ON (TTR.ID = PR.ID_TCA_TIPO_RESOLUCION)
            WHERE PR.ID = P_ID_PREPAGO
         GROUP BY PR.ID,
                  PAIS.DESCRIPCION_CORTA,
                  CLI.ID_FLEXCUBE,
                  CLI.RAZON_SOCIAL,
                  OPE.ID_OPERACION,
                  OPE.NOMBRE,
                  TTM.DESCRIPCION,
                  PR.FECHA_SOLICITUD,
                  PR.FECHA_PREPAGO,
                  TTP.DESCRIPCION_CORTA,
                  PR.MONTO_PREPAGO,
                  TTM.DESCRIPCION_CORTA,
                  PR.ID_TCA_TIPO_RESOLUCION,
                  TTR.DESCRIPCION_CORTA;



      OPEN P_CARGOS FOR
         SELECT TRB.ID,
                TRB.DESCRIPCION,
                NVL (CP.MONTO_CARGO_TRAMITE, 0) CARGOS_TRAMITE,
                (SELECT TTM.DESCRIPCION_CORTA
                   FROM TCA_TIPO_MONEDA TTM
                  WHERE TTM.ID = CP.ID_TCA_TIPO_MONEDA_TRAMITE)
                   MONEDA_CARGOS_TRAMITE,
                NVL (CP.MONTO_CARGO, 0) OTROS_CARGOS,
                (SELECT TTM.DESCRIPCION_CORTA
                   FROM TCA_TIPO_MONEDA TTM
                  WHERE TTM.ID = CP.ID_TCA_TIPO_MONEDA)
                   MONEDA_OTROS_CARGOS
           FROM CARGO_PREPAGO CP
                JOIN TCA_ROL_BPM TRB
                   ON (CP.ID_TCA_ROL_BPM = TRB.ID)
                JOIN PREPAGO PR
                   ON (CP.ID_PREPAGO = PR.ID)
          WHERE PR.ID = P_ID_PREPAGO;


      OPEN P_BANCOS FOR
         SELECT VCP.CUENTA,
                VCP.CUENTA_NOSTRO,
                VCP.NOMBRE_BANCO,
                VCP.CLIENTE,
                VCP.CODIGO_BIC,
                LC.NUMERO_LINEA_CREDITO AS REFERENCIA
           FROM VTA_CUENTAS_PAGO VCP,
                                           TRE_PREPAGO_CONTRATO TPC
                                        JOIN
                                           PREPAGO PR
                                        ON (TPC.ID_PREPAGO = PR.ID)
                                     JOIN
                                        TCA_TIPO_MONEDA TTM
                                     ON (PR.ID_TCA_TIPO_MONEDA = TTM.ID)
                                  JOIN
                                     TCA_TIPO_PREPAGO TTP
                                  ON (PR.ID_TCA_TIPO_PREPAGO = TTP.ID)
                               JOIN
                                  VTA_CONTRATO_DESEMBOLSO VCD
                               ON (TPC.CONTRATO_FLEXCUBE =
                                      VCD.NUMERO_CONTRATO)
                            JOIN
                               LINEA_CREDITO LC
                                      ON (VCD.CODIGO_LINEA_CREDITO = LC.NUMERO_LINEA_CREDITO)
                         JOIN
                            CONTRATO C
                         ON (LC.ID_CONTRATO = C.ID)
                      JOIN
                         OPERACION OPE
                      ON (C.ID_OPERACION = OPE.ID_OPERACION)
                   JOIN
                      CLIENTES CLI
                   ON (OPE.ID_CLIENTE = CLI.ID_CLIENTE)
                JOIN
                   CAT_PAISES PAIS
                ON (PAIS.ID = CLI.PAIS)
          WHERE VCP.CODIGO_MONEDA = VCD.CODIGO_MONEDA
                AND VCP.CODIGO_PAIS = PAIS.COD_EXTERNO
                AND VCP.CODIGO_FONDO =
                      (SELECT CAST (VCD.CODIGO_FONDO AS NUMBER) FROM DUAL)
                AND PR.ID = P_ID_PREPAGO;


   INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                      NOMBRE_INSUMO,
                                      DESCRIPCION_ERROR,
                                      FECHA_REGISTRO)
       VALUES ('SP',
               'paquete prepago',
             P_ID_PREPAGO,
               SYSDATE);

   COMMIT;


      SELECT NVL (SUM (CI.INTERESES), 0) MONTO_INTERESES
        INTO P_INTERESES
        FROM CALCULO_INTERESES CI
             JOIN TRE_PREPAGO_CONTRATO TPC
                ON (CI.ID_TRE_PREPAGO_CONTRATO = TPC.ID)
             JOIN PREPAGO PR
                ON (PR.ID = TPC.ID_PREPAGO)
       WHERE PR.ID = P_ID_PREPAGO;
   END OBTENER_INFORMACION_PREPAGO;
END PK_PREPAGO;
/
