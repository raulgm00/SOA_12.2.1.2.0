create or replace PROCEDURE       SP_CALCULA_FECHA_VENCIMIENTO (
   P_ID_OPERACION        IN     NUMBER,
   P_ID_ID_TCA_TERMINO   IN     NUMBER,
   v_fecha_inicio        IN     DATE,
   P_LOGIN_MODIFICA      IN     VARCHAR2,
   P_NOMBRE_MODIFICA     IN     VARCHAR2,
   RESUL                    OUT NUMBER)
AS
   Resultado   VARCHAR2 (250);
   
     err_num NUMBER;
   err_msg VARCHAR2(255);
   
BEGIN


   IF P_ID_ID_TCA_TERMINO = 29
   THEN
      UPDATE TERMINO TERUP
SET (TERUP.FECHA_INICIO, TERUP.FECHA_VENCIMIENTO, TERUP.LOGIN_USUARIO_ULTIMO_CAMBIO,
        TERUP.NOMBRE_USUARIO_ULTIMO_CAMBIO, TERUP.FECHA_ULTIMO_CAMBIO)=(
            SELECT v_fecha_inicio,
                CASE
                    WHEN TTF.DESCRIPCION = 'Días'
                 THEN
                    v_fecha_inicio + TER.PLAZO
                WHEN TTF.DESCRIPCION = 'Meses'
                THEN
                    ADD_MONTHS (v_fecha_inicio, TER.PLAZO)
                WHEN TTF.DESCRIPCION = 'Años'
                THEN
                ADD_MONTHS (v_fecha_inicio, (TER.PLAZO * 12))
                END,P_LOGIN_MODIFICA,P_NOMBRE_MODIFICA ,SYSDATE
            FROM TERMINO TER INNER JOIN TCA_TIPO_FRECUENCIA TTF
            ON TER.ID_TCA_FRECUENCIA_PLAZO = TTF.ID
    WHERE     
 --TER.ID_TCA_TERMINO = 1                                -- tipo plazo
     TER.ID_TCA_TIPO_FECHA_INICIO = 8  -- inicio Aprobación para TERMINO
       AND TER.BAN_ESTATUS = 1                            -- Que estén activas
      AND TER.ID_OPERACION = P_ID_OPERACION
       AND TERUP.ID=TER.ID)
       WHERE  TERUP.ID_OPERACION = P_ID_OPERACION AND TERUP.ID_TCA_TIPO_FECHA_INICIO = 8  -- inicio Aprobación para TERMINO
       AND TERUP.BAN_ESTATUS = 1  ;
      
        Resultado:=1;
        
        RESUL:=Resultado;
        
   END IF;

   IF P_ID_ID_TCA_TERMINO = 30
   THEN
      UPDATE TERMINO TERUP
         SET
             (TERUP.FECHA_INICIO,
             TERUP.FECHA_VENCIMIENTO,
             TERUP.LOGIN_USUARIO_ULTIMO_CAMBIO,
             TERUP.NOMBRE_USUARIO_ULTIMO_CAMBIO, 
             TERUP.FECHA_ULTIMO_CAMBIO
             ) =
                (SELECT v_fecha_inicio,
                        CASE
                           WHEN TTF.DESCRIPCION = 'Días'
                           THEN
                              v_fecha_inicio + TER.PLAZO
                           WHEN TTF.DESCRIPCION = 'Meses'
                           THEN
                              ADD_MONTHS (v_fecha_inicio, TER.PLAZO)
                           WHEN TTF.DESCRIPCION = 'Años'
                           THEN
                              ADD_MONTHS (v_fecha_inicio, (TER.PLAZO * 12))
                        END,P_LOGIN_MODIFICA , P_NOMBRE_MODIFICA ,SYSDATE
                   FROM TERMINO TER INNER JOIN TCA_TIPO_FRECUENCIA TTF
                           ON TER.ID_TCA_FRECUENCIA_PLAZO = TTF.ID
                  WHERE     TER.ID_TCA_TERMINO = 1               -- tipo plazo
                        AND TER.ID_TCA_TIPO_FECHA_INICIO = 9 -- inicio Aprobación para TERMINO
                        AND TER.BAN_ESTATUS = 1           -- Que estén activas
                        AND TER.ID_OPERACION = P_ID_OPERACION
                        AND TERUP.ID = TER.ID)
       WHERE TERUP.ID_OPERACION = P_ID_OPERACION and TERUP.ID_TCA_TERMINO = 1               -- tipo plazo
                        AND TERUP.ID_TCA_TIPO_FECHA_INICIO = 9 -- inicio Aprobación para TERMINO
                        AND TERUP.BAN_ESTATUS = 1    
       
       
       ;

      Resultado := 1;
      RESUL := Resultado;
   END IF;


   IF P_ID_ID_TCA_TERMINO = 31
   THEN
      UPDATE TERMINO TERUP
         SET
             (TERUP.FECHA_INICIO,
             TERUP.FECHA_VENCIMIENTO,
             TERUP.LOGIN_USUARIO_ULTIMO_CAMBIO,
             TERUP.NOMBRE_USUARIO_ULTIMO_CAMBIO,
             TERUP.FECHA_ULTIMO_CAMBIO
             ) =
                (SELECT v_fecha_inicio,
                        CASE
                           WHEN TTF.DESCRIPCION = 'Días'
                           THEN
                              v_fecha_inicio + TER.PLAZO
                           WHEN TTF.DESCRIPCION = 'Meses'
                           THEN
                              ADD_MONTHS (v_fecha_inicio, TER.PLAZO)
                           WHEN TTF.DESCRIPCION = 'Años'
                           THEN
                              ADD_MONTHS (v_fecha_inicio, (TER.PLAZO * 12))
                        END,P_LOGIN_MODIFICA,P_NOMBRE_MODIFICA ,SYSDATE
                   FROM TERMINO TER INNER JOIN TCA_TIPO_FRECUENCIA TTF
                           ON TER.ID_TCA_FRECUENCIA_PLAZO = TTF.ID
                  WHERE     TER.ID_TCA_TERMINO = 1               -- tipo plazo
                        AND TER.ID_TCA_TIPO_FECHA_INICIO = 13 -- inicio Aprobación para TERMINO
                        AND TER.BAN_ESTATUS = 1           -- Que estén activas
                        AND TER.ID_OPERACION = P_ID_OPERACION
                        AND TERUP.ID = TER.ID)
       WHERE TERUP.ID_OPERACION = P_ID_OPERACION AND 
        TERUP.ID_TCA_TERMINO = 1               -- tipo plazo
                        AND TERUP.ID_TCA_TIPO_FECHA_INICIO = 13 -- inicio Aprobación para TERMINO
                        AND TERUP.BAN_ESTATUS = 1
       ;

      Resultado := 1;
      RESUL := Resultado;
   END IF;
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
      DBMS_OUTPUT.PUT_LINE ('No existen Datos ');


      Resultado := 0;
      RESUL := Resultado;
      
     err_num := SQLCODE;
     err_msg := SQLERRM;

     INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,NOMBRE_INSUMO,DESCRIPCION_ERROR,FECHA_REGISTRO)
             VALUES('SP','SP_CALCULA_FECHA_VENCIMIENTO','Contrato: '||' Error:'||TO_CHAR(err_num)||' '||SUBSTR(err_msg,1,520),TO_DATE(SYSDATE,'DD-MM-YY 
HH24:MI'));

COMMIT;

  WHEN OTHERS THEN
    
     Resultado := 0;
     RESUL := Resultado;
     
     err_num := SQLCODE;
     err_msg := SQLERRM;

     INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,NOMBRE_INSUMO,DESCRIPCION_ERROR,FECHA_REGISTRO)
             VALUES('SP','SP_CALCULA_FECHA_VENCIMIENTO','Contrato: '||' Error:'||TO_CHAR(err_num)||' '||SUBSTR(err_msg,1,520),TO_DATE(SYSDATE,'DD-MM-YY 
HH24:MI'));

COMMIT;

END;