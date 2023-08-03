
CREATE OR REPLACE PROCEDURE SP_CONTRATO_DESEMBOLSO (
   P_ID_OPERACION    IN     NUMBER,
   P_ID_RESOLUCION   IN     INTEGER,
   P_ID_MONEDA       IN     NUMBER,
   P_ID_PREPAGO      IN     NUMBER,
   P_F_RENOVACION    IN     DATE,
   P_F_PREPAGO       IN     DATE,
   P_CODIGO_RES         OUT NUMBER,
   P_MENSAJE            OUT VARCHAR2,
   RECORDSET            OUT SYS_REFCURSOR)
AS
   V_ID_RESOLUCION   NUMBER;
   V_CAP_NV_F_P      DATE;
   V_FECHA_FIRMA     DATE;
   V_FECHA_RES       DATE;
   V_F_PREPAGO       DATE;
   V_F_RENOVACION    DATE;
BEGIN
   V_CAP_NV_F_P := P_F_PREPAGO;
   V_F_RENOVACION := P_F_RENOVACION;

   IF P_ID_RESOLUCION = 1 AND (P_F_PREPAGO IS NOT NULL OR P_F_PREPAGO <> '')
   THEN
      V_F_PREPAGO := NULL;
   ELSE
      V_F_PREPAGO := P_F_PREPAGO;
   END IF;


   IF P_ID_RESOLUCION = 3
   THEN
      V_ID_RESOLUCION := NULL;
      V_F_PREPAGO := NULL;
      V_F_RENOVACION := NULL;
   ELSE
      V_ID_RESOLUCION := P_ID_RESOLUCION;
   END IF;


   IF (P_ID_PREPAGO IS NULL OR P_ID_PREPAGO = '')
      AND (V_F_RENOVACION IS NULL OR V_F_RENOVACION = '')
   THEN
      OPEN RECORDSET FOR
         SELECT *
           FROM (SELECT LC.ID AS ID_LINEA_CREDITO,
                        LC.ID_CONTRATO,
                        VCD.CODIGO_LINEA_CREDITO NUMERO_LINEA_CREDITO,
                        LC.DESCRIPCION_LINEA,
                        LC.ID_FLEXCUBE,
                        LC.MONTO_LINEA,
                        NULL AS ID_CONTRATO_DESEMBOLSO,
                        VCD.NUMERO_CONTRATO AS CONTRATO_DESEMBOLSO,
                        F_ID_RESOLUCION (CON.FECHA_FIRMA) AS ID_RESOLUCION,
                        (SELECT DESCRIPCION
                           FROM TCA_TIPO_RESOLUCION
                          WHERE F_ID_RESOLUCION (CON.FECHA_FIRMA) = ID)
                           AS RESOLUCION,
                        VCD.FECHA_APERTURA AS FECHA_EFECTIVA,
                        VCD.FECHA_VENCIMIENTO AS VENCIMIENTO,
                        (SELECT FECHA_PROXIMO_PAGO
                           FROM VTA_FECHA_PROXIMO_PAGO FPPAGO
                          WHERE FPPAGO.CONTRACT_REF_NO = VCD.NUMERO_CONTRATO)
                           AS PROXIMO_PAGO,
                        VCD.CODIGO_MONEDA AS MONEDA,
                        TTM.ID AS ID_MONEDA,
                        NULL AS MONTO_PREPAGO,           --revisar informacion
                        NULL AS ES_PAGO_TOTAL,
                        LC.FONDO FONDO_CONTABLE,
                        CON.FECHA_FIRMA ESCRITURACION,
                        (SELECT calc.basis_amount
                           FROM middle.fc_v_Contract_ICCF_Calc calc
                          WHERE calc.Contract_Ref_No = VCD.NUMERO_CONTRATO
                                AND calc.Component LIKE '%INT'
                                AND calc.start_date =
                                      (SELECT MAX (calc2.start_date)
                                         FROM middle.fc_v_Contract_ICCF_Calc calc2,
                                              middle.fc_v_Fecha_Sistema
                                        WHERE calc2.contract_ref_no =
                                                 calc.contract_ref_no
                                              AND calc2.component =
                                                    calc.component
                                              AND calc2.start_date <
                                                    NVL (V_CAP_NV_F_P,
                                                         calc.start_date)
                                              AND calc2.schedule_date > today))
                           AS CAPITAL_NV_FECHA_PREPA,
                           VCD.USER_REF_NO
                   FROM CONTRATO CON,
                        LINEA_CREDITO LC,
                        (  SELECT cad.Contract_Ref_No,
                                  MIN (due_date) Fecha_Proximo_Pago
                             FROM middle.Fc_v_Plan_Pago cad,
                                  MIDDLE.FC_V_FECHA_SISTEMA cal
                            WHERE     cad.Due_Date >= cal.Today
                                  AND NVL (cad.Amount_Due, 0)
                                     - NVL (cad.Amount_Settled, 0) > 0
                                  AND due_date = NVL (V_F_PREPAGO, due_date)
                         GROUP BY cad.Contract_Ref_No) VFP, --    VTA_FECHA_PROXIMO_PAGO VFP,
                        TCA_TIPO_MONEDA TTM,
                        VTA_CONTRATO_DESEMBOLSO VCD
                  WHERE     CON.ID = LC.ID_CONTRATO
                        AND VFP.CONTRACT_REF_NO = VCD.NUMERO_CONTRATO
                        AND CON.ID_OPERACION = P_ID_OPERACION
                        AND TTM.COD_EXTERNO = VCD.CODIGO_MONEDA
                        AND VCD.CODIGO_LINEA_CREDITO =
                              LC.NUMERO_LINEA_CREDITO
                        AND TTM.ID = NVL (P_ID_MONEDA, TTM.ID)
                        AND VCD.MODULE_CODE = 'LD'
                        AND VCD.PRODUCT_TYPE = 'L' --                        AND VFP.FECHA_PROXIMO_PAGO =                              NVL (V_F_PREPAGO, VFP.FECHA_PROXIMO_PAGO)
                                                  )
          WHERE ID_RESOLUCION = NVL (V_ID_RESOLUCION, ID_RESOLUCION);

      P_CODIGO_RES := 0;

      INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                         NOMBRE_INSUMO,
                                         DESCRIPCION_ERROR,
                                         FECHA_REGISTRO)
          VALUES ('SP',
                  'SP_CONTRATO_DESEMBOLSO',
                     'por fecha escrituracion 1'
                  || 'P_ID_OPERACION'
                  || P_ID_OPERACION
                  || '  '
                  || 'P_ID_RESOLUCION'
                  || P_ID_RESOLUCION
                  || '  '
                  || 'P_ID_MONEDA '
                  || P_ID_MONEDA
                  || 'P_ID_PREPAGO'
                  || P_ID_PREPAGO
                  || 'P_F_RENOVACION'
                  || P_F_RENOVACION
                  || 'P_F_PREPAGO'
                  || P_F_PREPAGO,
                  SYSDATE);

      COMMIT;
   END IF;


   IF (P_ID_PREPAGO IS NOT NULL OR P_ID_PREPAGO <> '')
   --      AND (V_F_RENOVACION IS NOT NULL OR V_F_RENOVACION <> '')
   --      AND V_ID_RESOLUCION = 1
   THEN
      V_ID_RESOLUCION := NULL;

      OPEN RECORDSET FOR
         SELECT *
           FROM (SELECT LC.ID AS ID_LINEA_CREDITO,
                        LC.ID_CONTRATO,
                        VCD.CODIGO_LINEA_CREDITO AS NUMERO_LINEA_CREDITO,
                        LC.DESCRIPCION_LINEA,
                        LC.ID_FLEXCUBE,
                        LC.MONTO_LINEA,
                        NULL AS ID_CONTRATO_DESEMBOLSO,
                        VCD.NUMERO_CONTRATO AS CONTRATO_DESEMBOLSO,
                        F_ID_RESOLUCION (CON.FECHA_FIRMA) AS ID_RESOLUCION,
                        (SELECT DESCRIPCION
                           FROM TCA_TIPO_RESOLUCION
                          WHERE F_ID_RESOLUCION (CON.FECHA_FIRMA) = ID)
                           AS RESOLUCION,
                        VCD.FECHA_APERTURA AS FECHA_EFECTIVA,
                        VCD.FECHA_VENCIMIENTO AS VENCIMIENTO,
                        --  VFP.FECHA_PROXIMO_PAGO AS PROXIMO_PAGO,
                        (SELECT FECHA_PROXIMO_PAGO
                           FROM VTA_FECHA_PROXIMO_PAGO FPPAGO
                          WHERE FPPAGO.CONTRACT_REF_NO = VCD.NUMERO_CONTRATO)
                           AS PROXIMO_PAGO,
                        VCD.CODIGO_MONEDA AS MONEDA,
                        TTM.ID AS ID_MONEDA,
                        TPC.MONTO_PREPAGO AS MONTO_PREPAGO, --revisar informacion
                        TPC.ES_PAGO_TOTAL AS ES_PAGO_TOTAL,
                        LC.FONDO FONDO_CONTABLE,
                        CON.FECHA_FIRMA ESCRITURACION,
                        (SELECT calc.basis_amount
                           FROM middle.fc_v_Contract_ICCF_Calc calc
                          WHERE calc.Contract_Ref_No = VCD.NUMERO_CONTRATO
                                AND calc.Component LIKE '%INT'
                                AND calc.start_date =
                                      (SELECT MAX (calc2.start_date)
                                         FROM middle.fc_v_Contract_ICCF_Calc calc2,
                                              middle.fc_v_Fecha_Sistema
                                        WHERE calc2.contract_ref_no =
                                                 calc.contract_ref_no
                                              AND calc2.component =
                                                    calc.component
                                              AND calc2.start_date <
                                                    NVL (V_CAP_NV_F_P,
                                                         calc.start_date)
                                              AND calc2.schedule_date > today))
                           AS CAPITAL_NV_FECHA_PREPA,
                                    VCD.USER_REF_NO
                   FROM CONTRATO CON,
                        LINEA_CREDITO LC,
                        (  SELECT cad.Contract_Ref_No,
                                  MIN (due_date) Fecha_Proximo_Pago
                             FROM middle.Fc_v_Plan_Pago cad,
                                  MIDDLE.FC_V_FECHA_SISTEMA cal
                            WHERE     cad.Due_Date >= cal.Today
                                  AND NVL (cad.Amount_Due, 0)
                                     - NVL (cad.Amount_Settled, 0) > 0
                                  AND due_date = NVL (V_F_PREPAGO, due_date)
                         GROUP BY cad.Contract_Ref_No) VFP, ---    VTA_FECHA_PROXIMO_PAGO VFP,
                        TCA_TIPO_MONEDA TTM,
                        --                        TCA_TIPO_RESOLUCION TRS,
                        VTA_CONTRATO_DESEMBOLSO VCD,
                        TRE_PREPAGO_CONTRATO TPC
                  WHERE     CON.ID = LC.ID_CONTRATO
                        AND VFP.CONTRACT_REF_NO = VCD.NUMERO_CONTRATO -- LC.ID_FLEXCUBE
                        AND CON.ID_OPERACION = P_ID_OPERACION
                        AND TTM.COD_EXTERNO = VCD.CODIGO_MONEDA
                        AND VCD.CODIGO_LINEA_CREDITO =
                              LC.NUMERO_LINEA_CREDITO
                        AND TTM.ID = NVL (P_ID_MONEDA, TTM.ID)
                        AND TPC.CONTRATO_FLEXCUBE(+) = VCD.NUMERO_CONTRATO
                        AND TPC.ID_PREPAGO =
                              NVL (P_ID_PREPAGO, TPC.ID_PREPAGO)
                        AND VCD.MODULE_CODE = 'LD'
                        AND VCD.PRODUCT_TYPE = 'L')
          WHERE ID_RESOLUCION = NVL (V_ID_RESOLUCION, ID_RESOLUCION);

      P_CODIGO_RES := 0;

      INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                         NOMBRE_INSUMO,
                                         DESCRIPCION_ERROR,
                                         FECHA_REGISTRO)
          VALUES ('SP',
                  'SP_CONTRATO_DESEMBOLSO',
                     'por fecha escrituracion 2'
                  || 'P_ID_OPERACION'
                  || P_ID_OPERACION
                  || '  '
                  || 'P_ID_RESOLUCION'
                  || P_ID_RESOLUCION
                  || '  '
                  || 'P_ID_MONEDA '
                  || P_ID_MONEDA
                  || 'P_ID_PREPAGO'
                  || P_ID_PREPAGO
                  || 'P_F_RENOVACION'
                  || P_F_RENOVACION
                  || 'P_F_PREPAGO'
                  || P_F_PREPAGO,
                  SYSDATE);

      COMMIT;
   END IF;



   IF     (P_ID_PREPAGO IS NULL OR P_ID_PREPAGO = '')
      AND (V_F_RENOVACION IS NOT NULL OR V_F_RENOVACION <> '')
      AND V_ID_RESOLUCION = 1
   THEN
      V_ID_RESOLUCION := NULL;



      OPEN RECORDSET FOR
         SELECT *
           FROM (SELECT LC.ID AS ID_LINEA_CREDITO,
                        LC.ID_CONTRATO,
                        VCD.CODIGO_LINEA_CREDITO NUMERO_LINEA_CREDITO,
                        LC.DESCRIPCION_LINEA,
                        LC.ID_FLEXCUBE,
                        LC.MONTO_LINEA,
                        NULL AS ID_CONTRATO_DESEMBOLSO,
                        VCD.NUMERO_CONTRATO AS CONTRATO_DESEMBOLSO,
                        F_ID_RESOLUCION (CON.FECHA_FIRMA) AS ID_RESOLUCION,
                        (SELECT DESCRIPCION
                           FROM TCA_TIPO_RESOLUCION
                          WHERE F_ID_RESOLUCION (CON.FECHA_FIRMA) = ID)
                           AS RESOLUCION,
                        VCD.FECHA_APERTURA AS FECHA_EFECTIVA,
                        VCD.FECHA_VENCIMIENTO AS VENCIMIENTO,
                        (SELECT FECHA_PROXIMO_PAGO
                           FROM VTA_FECHA_PROXIMO_PAGO FPPAGO
                          WHERE FPPAGO.CONTRACT_REF_NO = VCD.NUMERO_CONTRATO)
                           AS PROXIMO_PAGO, -- VFP.FECHA_PROXIMO_PAGO AS PROXIMO_PAGO,
                        VCD.CODIGO_MONEDA AS MONEDA,
                        TTM.ID AS ID_MONEDA,
                        NULL AS MONTO_PREPAGO,           --revisar informacion
                        NULL AS ES_PAGO_TOTAL,
                        LC.FONDO FONDO_CONTABLE,
                        CON.FECHA_FIRMA ESCRITURACION,
                        (SELECT calc.basis_amount
                           FROM middle.fc_v_Contract_ICCF_Calc calc
                          WHERE calc.Contract_Ref_No = VCD.NUMERO_CONTRATO
                                AND calc.Component LIKE '%INT'
                                AND calc.start_date =
                                      (SELECT MAX (calc2.start_date)
                                         FROM middle.fc_v_Contract_ICCF_Calc calc2,
                                              middle.fc_v_Fecha_Sistema
                                        WHERE calc2.contract_ref_no =
                                                 calc.contract_ref_no
                                              AND calc2.component =
                                                    calc.component
                                              AND calc2.start_date <
                                                    NVL (V_CAP_NV_F_P,
                                                         calc.start_date)
                                              AND calc2.schedule_date > today))
                           AS CAPITAL_NV_FECHA_PREPA,
                                    VCD.USER_REF_NO
                   FROM CONTRATO CON,
                        LINEA_CREDITO LC,
                        (  SELECT cad.Contract_Ref_No,
                                  MIN (due_date) Fecha_Proximo_Pago
                             FROM middle.Fc_v_Plan_Pago cad,
                                  MIDDLE.FC_V_FECHA_SISTEMA cal
                            WHERE     cad.Due_Date >= cal.Today
                                  AND NVL (cad.Amount_Due, 0)
                                     - NVL (cad.Amount_Settled, 0) > 0
                                  AND due_date = NVL (V_F_PREPAGO, due_date)
                         GROUP BY cad.Contract_Ref_No) VFP, ---  VTA_FECHA_PROXIMO_PAGO VFP,
                        TCA_TIPO_MONEDA TTM,
                        VTA_CONTRATO_DESEMBOLSO VCD
                  WHERE     CON.ID = LC.ID_CONTRATO
                        AND VFP.CONTRACT_REF_NO = VCD.NUMERO_CONTRATO
                        AND CON.ID_OPERACION = P_ID_OPERACION
                        AND TTM.COD_EXTERNO = VCD.CODIGO_MONEDA
                        AND VCD.CODIGO_LINEA_CREDITO =
                              LC.NUMERO_LINEA_CREDITO
                        AND TTM.ID = NVL (P_ID_MONEDA, TTM.ID)
                        AND VCD.MODULE_CODE = 'LD'
                        AND VCD.PRODUCT_TYPE = 'L'
                        --                        AND VFP.FECHA_PROXIMO_PAGO =        NVL (V_F_PREPAGO, VFP.FECHA_PROXIMO_PAGO)
                        AND TO_DATE (VCD.FECHA_APERTURA, 'dd/mm/yy') >=
                              TO_DATE (V_F_RENOVACION, 'dd/mm/yy'))
          WHERE ID_RESOLUCION = NVL (V_ID_RESOLUCION, ID_RESOLUCION);

      P_CODIGO_RES := 0;


      INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                         NOMBRE_INSUMO,
                                         DESCRIPCION_ERROR,
                                         FECHA_REGISTRO)
          VALUES ('SP',
                  'SP_CONTRATO_DESEMBOLSO',
                     'por fecha escrituracion 3'
                  || 'P_ID_OPERACION'
                  || P_ID_OPERACION
                  || '  '
                  || 'P_ID_RESOLUCION'
                  || P_ID_RESOLUCION
                  || '  '
                  || 'P_ID_MONEDA '
                  || P_ID_MONEDA
                  || 'P_ID_PREPAGO'
                  || P_ID_PREPAGO
                  || 'P_F_RENOVACION'
                  || P_F_RENOVACION
                  || 'P_F_PREPAGO'
                  || P_F_PREPAGO,
                  SYSDATE);

      COMMIT;
   END IF;


   IF     (P_ID_PREPAGO IS NULL OR P_ID_PREPAGO = '')
      AND (V_F_RENOVACION IS NOT NULL OR V_F_RENOVACION <> '')
      AND V_ID_RESOLUCION = 2
   THEN
      V_ID_RESOLUCION := NULL;

      OPEN RECORDSET FOR
         SELECT *
           FROM (SELECT LC.ID AS ID_LINEA_CREDITO,
                        LC.ID_CONTRATO,
                        VCD.CODIGO_LINEA_CREDITO NUMERO_LINEA_CREDITO,
                        LC.DESCRIPCION_LINEA,
                        LC.ID_FLEXCUBE,
                        LC.MONTO_LINEA,
                        NULL AS ID_CONTRATO_DESEMBOLSO,
                        VCD.NUMERO_CONTRATO AS CONTRATO_DESEMBOLSO,
                        F_ID_RESOLUCION (CON.FECHA_FIRMA) AS ID_RESOLUCION,
                        (SELECT DESCRIPCION
                           FROM TCA_TIPO_RESOLUCION
                          WHERE F_ID_RESOLUCION (CON.FECHA_FIRMA) = ID)
                           AS RESOLUCION,
                        VCD.FECHA_APERTURA AS FECHA_EFECTIVA,
                        VCD.FECHA_VENCIMIENTO AS VENCIMIENTO,
                        (SELECT FECHA_PROXIMO_PAGO
                           FROM VTA_FECHA_PROXIMO_PAGO FPPAGO
                          WHERE FPPAGO.CONTRACT_REF_NO = VCD.NUMERO_CONTRATO)
                           AS PROXIMO_PAGO, -- VFP.FECHA_PROXIMO_PAGO AS PROXIMO_PAGO,
                        VCD.CODIGO_MONEDA AS MONEDA,
                        TTM.ID AS ID_MONEDA,
                        NULL AS MONTO_PREPAGO,           --revisar informacion
                        NULL AS ES_PAGO_TOTAL,
                        LC.FONDO FONDO_CONTABLE,
                        CON.FECHA_FIRMA ESCRITURACION,
                        (SELECT calc.basis_amount
                           FROM middle.fc_v_Contract_ICCF_Calc calc
                          WHERE calc.Contract_Ref_No = VCD.NUMERO_CONTRATO
                                AND calc.Component LIKE '%INT'
                                AND calc.start_date =
                                      (SELECT MAX (calc2.start_date)
                                         FROM middle.fc_v_Contract_ICCF_Calc calc2,
                                              middle.fc_v_Fecha_Sistema
                                        WHERE calc2.contract_ref_no =
                                                 calc.contract_ref_no
                                              AND calc2.component =
                                                    calc.component
                                              AND calc2.start_date <
                                                    NVL (V_CAP_NV_F_P,
                                                         calc.start_date)
                                              AND calc2.schedule_date > today))
                           AS CAPITAL_NV_FECHA_PREPA,
                                    VCD.USER_REF_NO
                   FROM CONTRATO CON,
                        LINEA_CREDITO LC,
                        (  SELECT cad.Contract_Ref_No,
                                  MIN (due_date) Fecha_Proximo_Pago
                             FROM middle.Fc_v_Plan_Pago cad,
                                  MIDDLE.FC_V_FECHA_SISTEMA cal
                            WHERE     cad.Due_Date >= cal.Today -- Este cambio es temporal
                                  AND NVL (cad.Amount_Due, 0)
                                     - NVL (cad.Amount_Settled, 0) > 0
                                  AND due_date = NVL (V_F_PREPAGO, due_date)
                         GROUP BY cad.Contract_Ref_No) VFP, ---  VTA_FECHA_PROXIMO_PAGO VFP,
                        TCA_TIPO_MONEDA TTM,
                        VTA_CONTRATO_DESEMBOLSO VCD
                  WHERE     CON.ID = LC.ID_CONTRATO
                        AND VFP.CONTRACT_REF_NO = VCD.NUMERO_CONTRATO
                        AND CON.ID_OPERACION = P_ID_OPERACION
                        AND TTM.COD_EXTERNO = VCD.CODIGO_MONEDA
                        AND VCD.CODIGO_LINEA_CREDITO =
                              LC.NUMERO_LINEA_CREDITO
                        AND TTM.ID = NVL (P_ID_MONEDA, TTM.ID)
                        AND VCD.MODULE_CODE = 'LD'
                        AND VCD.PRODUCT_TYPE = 'L'
                        --                        AND VFP.FECHA_PROXIMO_PAGO =                              NVL (V_F_PREPAGO, VFP.FECHA_PROXIMO_PAGO)
                        AND VCD.FECHA_APERTURA <= V_F_RENOVACION)
          WHERE ID_RESOLUCION = NVL (V_ID_RESOLUCION, ID_RESOLUCION);

      P_CODIGO_RES := 0;

      INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                         NOMBRE_INSUMO,
                                         DESCRIPCION_ERROR,
                                         FECHA_REGISTRO)
          VALUES ('SP',
                  'SP_CONTRATO_DESEMBOLSO',
                     'por fecha escrituracion 4'
                  || 'P_ID_OPERACION'
                  || P_ID_OPERACION
                  || '  '
                  || 'P_ID_RESOLUCION'
                  || P_ID_RESOLUCION
                  || '  '
                  || 'P_ID_MONEDA '
                  || P_ID_MONEDA
                  || 'P_ID_PREPAGO'
                  || P_ID_PREPAGO
                  || 'P_F_RENOVACION'
                  || P_F_RENOVACION
                  || 'P_F_PREPAGO'
                  || P_F_PREPAGO,
                  SYSDATE);

      COMMIT;
   END IF;
      /*
**********************************************************************
**************************Comentarios***********************************
-capital no vencido a fecha de prepago
Para el capital vencido se debe utilizar la vista,  ,  middle.fc_v_Contract_Balance, el contract_ref_no
es el BHQ del desembolso y el saldo en USD es LCY_Principal_Outstandin_Bal

**********************************************************************
**********************************************************************
      */
--Se coloca filtro
--David 22 May 17, 11:40
--Los campos para realizar el filtro son: MODULE_CODE = 'LD' y PRODUCT_TYPE = C o L; donde C es commitment y L es Loan

--NOTA: Cuando se implemente lo de formalizaciones parciales , para el caso de las IFI se tomara la fecha de renovacion

EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
      P_CODIGO_RES := SQLCODE;

      P_MENSAJE := SQLERRM;
      DBMS_OUTPUT.PUT_LINE (SQLERRM);
   WHEN OTHERS
   THEN
      P_CODIGO_RES := SQLCODE;

      P_MENSAJE := SQLERRM;
      DBMS_OUTPUT.PUT_LINE (SQLERRM);

      INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
                                         NOMBRE_INSUMO,
                                         DESCRIPCION_ERROR,
                                         FECHA_REGISTRO)
          VALUES ('SP',
                  'SP_CONTRATO_DESEMBOLSO',
                  P_MENSAJE,
                  SYSDATE);

      COMMIT;
END;

/