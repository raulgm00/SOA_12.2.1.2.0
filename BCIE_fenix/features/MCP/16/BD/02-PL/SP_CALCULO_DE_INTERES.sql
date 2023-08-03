CREATE OR REPLACE PROCEDURE SP_CALCULO_DE_INTERES (
   p_prepago         NUMBER,
   p_Recordset   OUT SYS_REFCURSOR)
AS
   l_cursor            SYS_REFCURSOR;
   pvContrato          VARCHAR2 (100);
   pdFechaHasta        DATE;
   Contract_Ref_No     VARCHAR2 (100);
   Numero_Tesoreria    VARCHAR2 (100);
   base                VARCHAR2 (100);
   tasa                NUMBER;
   saldo_acumulado     NUMBER;
   fecha_desde         DATE;
   fecha_hasta         DATE;
   intereses_por_dia   NUMBER;
   dias_calculados     NUMBER;
   proyectado          NUMBER;
   moneda              VARCHAR2 (50);
BEGIN
   DELETE FROM TEM_CALCULO_INTER
         WHERE ID_SOLICITUD_PREPAGO = p_prepago;

   COMMIT;

   FOR REC
      IN (SELECT DISTINCT
                 PRE.MONTO_PREPAGO,
                 PRE.FECHA_PREPAGO AS pdFechaHasta,
                 TPC.CONTRATO_FLEXCUBE AS V_CONTRATO
            FROM PREPAGO PRE, TRE_PREPAGO_CONTRATO TPC
           WHERE PRE.ID = TPC.ID_PREPAGO
                 AND TPC.CONTRATO_FLEXCUBE IS NOT NULL 
                 AND PRE.ID=p_prepago
                 )
   LOOP
      middle.fc_p_proyeccion_interes (REC.V_CONTRATO,
                                      REC.pdFechaHasta,
                                      p_recordset   => l_cursor);

      LOOP
         FETCH l_cursor
         INTO Contract_Ref_No,
              Numero_Tesoreria,
              base,
              tasa,
              saldo_acumulado,
              fecha_desde,
              fecha_hasta,
              intereses_por_dia,
              dias_calculados,
              proyectado,
              moneda;

         EXIT WHEN l_cursor%NOTFOUND;



         INSERT INTO TEM_CALCULO_INTER
             VALUES (Contract_Ref_No,
                     Numero_Tesoreria,
                     base,
                     tasa,
                     saldo_acumulado,
                     fecha_desde,
                     fecha_hasta,
                     intereses_por_dia,
                     dias_calculados,
                     proyectado,
                     moneda,
                     p_prepago);

         COMMIT;
      DBMS_OUTPUT.PUT_LINE(   Contract_Ref_No
                               || ' '
                               || Numero_Tesoreria
                               || ' '
                               || base
                               || ' '
                              || tasa
                               || ' '
                               || saldo_acumulado
                               || ''
                               || fecha_desde
                               || ' '
                               || fecha_hasta
                              || '        ----  '
                              || intereses_por_dia
                              || '     '
                              || dias_calculados
                               || ' '
                               || proyectado
                              || ' '
                               || moneda);
      END LOOP;

      CLOSE l_cursor;
   END LOOP;

   OPEN p_Recordset FOR
      SELECT TPC.CONTRATO_FLEXCUBE Contract_desembolso,
             base,
             tasa,
             tpc.MONTO_PREPAGO monto_prepagar,
             fecha_desde,
             PRE.FECHA_PREPAGO fecha_hasta,
             dias_calculados,
             moneda,
             proyectado AS intereses,
             TPC.ID ID_TRE_PRE_CONT,
            TPC.ES_PAGO_TOTAL,
            TPC.FECHA_PROXIMO_PAGO
        FROM TEM_CALCULO_INTER TEMCI, PREPAGO PRE, TRE_PREPAGO_CONTRATO TPC
       WHERE     PRE.ID = TPC.ID_PREPAGO
             AND TEMCI.ID_SOLICITUD_PREPAGO = PRE.ID
             AND TEMCI.CONTRACT_REF_NO = TPC.CONTRATO_FLEXCUBE
             AND PRE.ID = p_prepago;
END;
/

