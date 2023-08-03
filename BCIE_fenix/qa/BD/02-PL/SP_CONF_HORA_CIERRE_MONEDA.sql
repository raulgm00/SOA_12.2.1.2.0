-- ##--------------------------------------------------------------------------------------------##
-- Descripción:  Script que permite agregar, modificar, desactivar/activar la configuración de la hora de cierre por moneda, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar las horas de cierre por moneda (TCA_HORA_CIERRE_MONEDA)

*/
-- ##--------------------------------------------------------------------------------------------##



CREATE OR REPLACE PROCEDURE SP_CONF_HORA_CIERRE_MONEDA(
	P_ACCION                        IN NUMBER   := 0,			-- Identificador de la transacción a ejecutar
	P_ID                            IN NUMBER   := NULL,		-- Id del  registro del catálogo de hora de cierre por moneda. (TCA_HORA_CIERRE_MONEDA)
	P_ID_TCA_TIPO_MONEDA       		IN NUMBER   := NULL,		-- Id tipo de moneda.  (TCA_TIPO_MONEDA)
	P_HORA_CIERRE					IN VARCHAR2 := NULL,		-- Hora de cierre de la Banca del país  de referencia, indicándola en formato de 24 hrs (HH:MM:SS)
	P_REFERENCIA					IN VARCHAR2 := NULL,		-- Zona horaria en donde se encuentran las cuentas bancarias.
	P_UTC							IN VARCHAR2 := NULL,		-- Diferencia en horas con respecto a la zona horaria de la referencia, en donde se debe colocar como prefijo (-PT), horas y sufijo (H)
	P_CODIGO_RES                    OUT NUMBER)					-- Indica el código de respuesta de la transacción.  (0 - Incorrecto, 1 - Correcto)

AS
	err_num			NUMBER;
	err_msg			VARCHAR2(255);
	v_accion		VARCHAR2(250) := 'NO IDENTIFICADA';
	v_Error_msg	 	VARCHAR2(100) := '';
	TYPE_TCA_HORA_CIERRE_MONEDA TCA_HORA_CIERRE_MONEDA%ROWTYPE;
	v_ID 			NUMBER; 
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)	-- Se agrega una nueva hora de cierre
	THEN
		SELECT MAX (ID) INTO v_ID FROM TCA_HORA_CIERRE_MONEDA;
		v_ID := v_ID + 1;
		
		INSERT INTO TCA_HORA_CIERRE_MONEDA (ID, 
											ID_TCA_TIPO_MONEDA, 
											HORA_CIERRE, 
											REFERENCIA, 
											UTC, 
											BAN_ESTATUS) 
		VALUES (v_ID,
			P_ID_TCA_TIPO_MONEDA,
			P_HORA_CIERRE,
			P_REFERENCIA,
			P_UTC,
			1);      
		COMMIT;
		
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';
		
	ELSIF (P_ACCION = 2)	-- Se modifican los datos de la hora de cierre
	THEN
		IF  (P_ID > 0) THEN
			SELECT * INTO TYPE_TCA_HORA_CIERRE_MONEDA FROM TCA_HORA_CIERRE_MONEDA
			WHERE ID = P_ID;
			
			UPDATE TCA_HORA_CIERRE_MONEDA SET
				ID_TCA_TIPO_MONEDA = NVL(P_ID_TCA_TIPO_MONEDA, TYPE_TCA_HORA_CIERRE_MONEDA.ID_TCA_TIPO_MONEDA),
				HORA_CIERRE = NVL(P_HORA_CIERRE, TYPE_TCA_HORA_CIERRE_MONEDA.HORA_CIERRE), 
				REFERENCIA = NVL(P_REFERENCIA, TYPE_TCA_HORA_CIERRE_MONEDA.REFERENCIA), 
				UTC = NVL(P_UTC, TYPE_TCA_HORA_CIERRE_MONEDA.UTC)
			WHERE ID = P_ID;			
			
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;			
				COMMIT;
			ELSE	
				ROLLBACK;				
			END IF;			            
		ELSE
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
		
        v_accion := 'MODIFICAR';              
		
    ELSIF (P_ACCION = 3)  -- Deshabilita los datos de la hora de cierre
    THEN
		IF  (P_ID > 0) THEN
			UPDATE TCA_HORA_CIERRE_MONEDA
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID;		
			
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;
				COMMIT;
			ELSE
				ROLLBACK;
			END IF;		
		ELSE
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
	   
		v_accion := 'DESACTIVAR';
	
	ELSIF (P_ACCION = 4)  -- Habilita los datos de la hora de cierre
    THEN
		IF  (P_ID > 0) THEN
			UPDATE TCA_HORA_CIERRE_MONEDA
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID;		
			
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;
				COMMIT;
			ELSE
				ROLLBACK;
			END IF;		
        ELSE
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
        END IF;
	   
		v_accion := 'ACTIVAR';
	
	ELSIF (P_ACCION = 5)		-- Listar datos de la hora de cierre
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT HCM.ID, HCM.ID_TCA_TIPO_MONEDA, TTM.DESCRIPCION || '(' || TTM.DESCRIPCION_CORTA || ')' AS MONEDA,  HCM.HORA_CIERRE, HCM.REFERENCIA, HCM.UTC, HCM.BAN_ESTATUS 
			FROM TCA_HORA_CIERRE_MONEDA HCM 
			INNER JOIN TCA_TIPO_MONEDA TTM on HCM.ID_TCA_TIPO_MONEDA = ttm.id
			ORDER BY HCM.ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_HORA_CIERRE_MONEDA = ' || v_rec.ID || ', MONEDA = ' || v_rec.MONEDA || ', HORA_CIERRE = ' || v_rec.HORA_CIERRE || ', REFERENCIA = ' || v_rec.REFERENCIA || ', UTC = ' || v_rec.UTC || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR HORAS DE CIERRE POR MONEDA';
	
	END IF;
   
    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    
	IF (P_CODIGO_RES = 1)
    THEN
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || ') se realizó correctamente.');
	ELSE
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || '), no se realizó correctamente.  ' || v_Error_msg);
	END IF;

   
EXCEPTION
	WHEN NO_DATA_FOUND
	THEN
		DBMS_OUTPUT.PUT_LINE ('No existen Datos.');
		DBMS_OUTPUT.PUT_LINE ('Error: ' || SQLCODE || ', ' || SQLERRM);
		err_num := SQLCODE;
		err_msg := SUBSTR(SQLERRM,1,250);

		INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
										NOMBRE_INSUMO,
                                        DESCRIPCION_ERROR,
                                        FECHA_REGISTRO)
        VALUES ('SP',
				'SP_CONF_HORA_CIERRE_MONEDA',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
				SYSDATE);
		COMMIT;
	WHEN OTHERS
	THEN
		DBMS_OUTPUT.PUT_LINE ('Error: ' || SQLCODE || ', ' || SQLERRM);
		err_num := SQLCODE;
		err_msg := SUBSTR(SQLERRM,1,250);	  
    
		INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
										NOMBRE_INSUMO,
                                        DESCRIPCION_ERROR,
                                        FECHA_REGISTRO)
		VALUES ('SP',
                'SP_CONF_HORA_CIERRE_MONEDA',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        