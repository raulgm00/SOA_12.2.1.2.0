-- ##--------------------------------------------------------------------------------------------##
-- Descripción:  Script que permite modificar la descripción de las reglas del proceso de desembolso, así mismo, permite listar la información de dicha tabla.
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_REGLA_PROCESO(
	P_ACCION                        IN NUMBER   := 0,			-- Indica el tipo de acción a ejecutar.
	P_ID                            IN NUMBER   := NULL,		-- Id del registro del catálogo de las reglas del proceso. 
	P_DESCRIPCION					IN VARCHAR2 := NULL,		-- Nueva descripción a sustituir.
	P_CODIGO_RES                    OUT NUMBER )				-- Indica el código de respuesta de la transacción.
AS
	err_num		NUMBER;
	err_msg		VARCHAR2 (255);
	v_accion	VARCHAR2 (250) := 'NO IDENTIFICADA';
	v_Error_msg VARCHAR2(100) := ''; 
	TYPE_TCA_REGLA_PROCESO TCA_REGLA_PROCESO%ROWTYPE;
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 2)
	THEN
		IF  (P_ID > 0) THEN
			SELECT * INTO TYPE_TCA_REGLA_PROCESO FROM TCA_REGLA_PROCESO
			WHERE ID = P_ID;
			
			UPDATE TCA_REGLA_PROCESO SET
				DESCRIPCION = NVL(P_DESCRIPCION, TYPE_TCA_REGLA_PROCESO.DESCRIPCION)				
			WHERE ID = P_ID;	
			
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;			
				COMMIT;
			ELSE	
				ROLLBACK;								
			END IF;					
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
		
        v_accion := 'MODIFICAR';              		
	
	ELSIF (P_ACCION = 5)
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT TRP.ID, TRP.DESCRIPCION, TRP.TRANSACCION, TRP.SE_PUEDE_EXCEPTUAR, TRP.BAN_ESTATUS
			FROM TCA_REGLA_PROCESO TRP			
			ORDER BY TRP.ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_REGLA_PROCESO= ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', TRANSACCION = ' || v_rec.TRANSACCION || ', SE_PUEDE_EXCEPTUAR = ' || v_rec.SE_PUEDE_EXCEPTUAR || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR REGLAS DEL PROCESO DE DESEMBOLSO';
	
	END IF;
   
    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    IF (P_CODIGO_RES = 1)
    THEN
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || ') se realizó correctamente.');
	ELSE
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || '), no se realizó correctamente. '  || v_Error_msg);
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
				'SP_CONF_REGLA_PROCESO',
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
                'SP_CONF_REGLA_PROCESO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        