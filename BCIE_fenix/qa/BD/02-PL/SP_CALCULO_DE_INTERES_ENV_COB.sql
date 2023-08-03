create or replace PROCEDURE       SP_CALCULO_DE_INTERES_ENV_COB (
   p_contrato        VARCHAR2,
   P_fechaIntCalcAl  DATE,
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
         WHERE ID_SOLICITUD_PREPAGO IS NULL AND CONTRACT_REF_NO = p_contrato;

   COMMIT;

    
   FOR REC IN (SELECT FECHA_EFECTIVA, CONTRATO_FLEXCUBE
                 FROM contrato_desembolso
                WHERE CONTRATO_FLEXCUBE = p_contrato)
     
   LOOP
      middle.fc_p_proyeccion_interes (REC.CONTRATO_FLEXCUBE,
                                      P_fechaIntCalcAl,
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
                     NULL);

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
      SELECT CONTRACT_REF_NO Contract_desembolso,
             base,
             tasa,
             fecha_desde,
             fecha_hasta,
             dias_calculados,
             moneda,
             proyectado AS intereses,
             INTERESES_POR_DIA,
             DIAS_CALCULADOS
        FROM TEM_CALCULO_INTER
       WHERE CONTRACT_REF_NO = p_contrato;
END;