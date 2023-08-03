
	--  20180209  Se crea trigger que identifica si existe el código de programa en la HCE (TRE_HERRAMIENTA)

	CREATE OR REPLACE TRIGGER TCA_PRODUCTO_PROGRAMA_TGR
		BEFORE INSERT ON TCA_PRODUCTO_PROGRAMA FOR EACH ROW
		DECLARE
			v_count NUMBER;
		BEGIN
			SELECT COUNT(*) INTO v_count FROM TRE_HERRAMIENTA WHERE CODIGO_PROGRAMA = :NEW.CODIGO_PROGRAMA;
			IF (v_count = 0 )THEN
				DBMS_OUTPUT.put_line('Advertencia: El código de programa: ''' || :NEW.CODIGO_PROGRAMA || ''', no existe en la tabla de configuración de la herramienta de clasificación estratégica (HCE)-(TRE_HERRAMIENTA)');		
			END IF;
		
		END;
		