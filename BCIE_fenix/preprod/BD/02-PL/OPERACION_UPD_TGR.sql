CREATE OR REPLACE TRIGGER FENIX.OPERACION_UPD_TGR 
	AFTER UPDATE ON FENIX.OPERACION FOR EACH ROW
	DECLARE 
	v_id_tbi_operacion NUMBER;	
	err_num NUMBER;
	err_msg VARCHAR2(255);

	BEGIN

		SELECT TBI_OPERACION_SEQ.NEXTVAL INTO v_id_tbi_operacion FROM DUAL;	  
		
		INSERT INTO TBI_OPERACION (ID, ID_OPERACION, TIPO_ACCION, FECHA_REGISTRO) VALUES (v_id_tbi_operacion, :NEW.ID_OPERACION, 'MODIFICAR_OPERACION', SYSDATE);  
				
		IF UPDATING ('USUARIO') THEN
		  INSERT INTO TBI_OPERACION_CAMPO (ID_BITACORA, CAMPO, VALOR_NUEVO, VALOR_ANTERIOR) VALUES (v_id_tbi_operacion,'USUARIO',:NEW.USUARIO,:OLD.USUARIO);
		END IF;  
		IF UPDATING ('NOMBRE')THEN
		  INSERT INTO TBI_OPERACION_CAMPO (ID_BITACORA, CAMPO, VALOR_NUEVO, VALOR_ANTERIOR) VALUES(v_id_tbi_operacion,'NOMBRE',:NEW.NOMBRE,:OLD.NOMBRE);
		END IF;  
		IF UPDATING('ID_CLIENTE')THEN
		  INSERT INTO TBI_OPERACION_CAMPO (ID_BITACORA, CAMPO, VALOR_NUEVO, VALOR_ANTERIOR) VALUES(v_id_tbi_operacion,'ID_CLIENTE',:NEW.ID_CLIENTE,:OLD.ID_CLIENTE);
		END IF;  
		IF UPDATING('ID_PRODUCTO')THEN
		  INSERT INTO TBI_OPERACION_CAMPO (ID_BITACORA, CAMPO, VALOR_NUEVO, VALOR_ANTERIOR) VALUES(v_id_tbi_operacion,'ID_PRODUCTO',:NEW.ID_PRODUCTO,:OLD.ID_PRODUCTO);
		END IF;  
		IF UPDATING('FECHA_INICIO')THEN
		  INSERT INTO TBI_OPERACION_CAMPO (ID_BITACORA, CAMPO, VALOR_NUEVO, VALOR_ANTERIOR) VALUES(v_id_tbi_operacion,'FECHA_INICIO',:NEW.FECHA_INICIO,:OLD.FECHA_INICIO);
		END IF;  
		IF UPDATING('SCR')THEN
		  INSERT INTO TBI_OPERACION_CAMPO (ID_BITACORA, CAMPO, VALOR_NUEVO, VALOR_ANTERIOR) VALUES(v_id_tbi_operacion,'SCR',:NEW.SCR,:OLD.SCR);
		END IF;  
		IF UPDATING('SCR_ESTATUS')THEN  
		  INSERT INTO TBI_OPERACION_CAMPO (ID_BITACORA, CAMPO, VALOR_NUEVO, VALOR_ANTERIOR) VALUES(v_id_tbi_operacion,'SCR_ESTATUS',:NEW.SCR_ESTATUS,:OLD.SCR_ESTATUS);
		END IF;
		IF UPDATING('ETAPA')THEN  
			INSERT INTO TBI_OPERACION_CAMPO (ID_BITACORA, CAMPO, VALOR_NUEVO, VALOR_ANTERIOR) VALUES (v_id_tbi_operacion,'ETAPA',:NEW.ETAPA,:OLD.ETAPA);
		END IF;
		IF UPDATING('ESTADO')THEN  
			INSERT INTO TBI_OPERACION_CAMPO (ID_BITACORA, CAMPO, VALOR_NUEVO, VALOR_ANTERIOR) VALUES (v_id_tbi_operacion,'ESTADO',:NEW.ESTADO,:OLD.ESTADO);
		END IF;
	
	
	EXCEPTION
	WHEN OTHERS THEN
		err_num := SQLCODE;
		err_msg := SUBSTR(SQLERRM,1,250);
		
		
		DBMS_OUTPUT.put_line('Error al actualizar en la tabla de bitácora de operación');
		DBMS_OUTPUT.put_line('Error: '||TO_CHAR(err_num));
		DBMS_OUTPUT.put_line(err_msg);
		
        
		INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,NOMBRE_INSUMO,DESCRIPCION_ERROR,FECHA_REGISTRO)
		VALUES('TRG','OPERACION_UPD_TGR','Error: '||TO_CHAR(err_num)||' '|| err_msg,TO_DATE(SYSDATE,'DD-MM-YYYY HH24:MI:SS'));
		COMMIT;
				
	END;