-
--- ##--------------------------------------------------------------------------------------------##
--- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración del  tipo de documento, así mismo, permite listar la información de dicha tabla.
-/*
-Acciones permitidas (P_ACCION):
-2 - Modificar
-5 - Listar los tipos de documento(TCA_DOCUMENTO)
-6 - Listar los permisos del tipo de documento por tarea BPM. En donde se requiere que se ingrese el tipo de documento (uno o varios)
-7 - Listar los permisos del tipo de documento por tarea BPM específica. En donde se requiere que se ingrese el tipo de documento (uno o varios) y el Identificador de la tarea BPM (uno o varios)
-
-	
-*/
--- ##--------------------------------------------------------------------------------------------##
-
-


CREATE OR REPLACE PROCEDURE SP_CONF_PERMISOS_DOCUMENTO (
	P_ACCION             IN NUMBER := 0,								-- Identificador de la transacción a ejecutar
	P_ID_DOCUMENTO 		 IN T_ID_DOCUMENTO  := T_ID_DOCUMENTO(),		-- Array del Identificador del tipo de documento
	P_ID_TAREA_BPM 		 IN T_ID_TAREA_BPM  := T_ID_TAREA_BPM(),		-- Array del Identificador de la tarea	
	P_PUEDE_CREAR        IN NUMBER := NULL,		-- Bandera que indica el tipo de permiso para crear
	P_PUEDE_MODIFICAR    IN NUMBER := NULL,		-- Bandera que indica el tipo de permiso para modificar
	P_PUEDE_BORRAR       IN NUMBER := NULL,		-- Bandera que indica el tipo de permiso para borrar
	P_PUEDE_CONSULTAR    IN NUMBER := NULL,		-- Bandera que indica el tipo de permiso para consultar
	P_CODIGO_RES         OUT NUMBER	)
AS
	err_num     		NUMBER;
	err_msg     		VARCHAR2(255);
	v_accion			VARCHAR2(250) := 'NO IDENTIFICADA';
	v_codeBlock			VARCHAR2 (4000);	
	v_nombreDOCTO		VARCHAR2(256) := NULL;	
	v_id_documento 		NUMBER;
	v_id_tarea_bpm 		NUMBER;
	v_List_Id_tarea 	VARCHAR (4000);
	
	TYPE_TCO_DOCUMENTO_TAREA TCO_DOCUMENTO_TAREA%ROWTYPE;
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 2)	--  Modifica los permisos del tipo de documento en la tarea
	THEN
		-- Recorre el array de los tipos de documento
		FOR ind IN 1 .. P_ID_DOCUMENTO.COUNT
		LOOP
			v_id_documento := P_ID_DOCUMENTO(ind);
			
			FOR ind2 IN 1 .. P_ID_TAREA_BPM.COUNT
			LOOP
				v_id_tarea_bpm :=  P_ID_TAREA_BPM(ind2);				
				
				SELECT * INTO TYPE_TCO_DOCUMENTO_TAREA FROM TCO_DOCUMENTO_TAREA 
				WHERE ID_TCA_DOCUMENTO = v_id_documento
				AND ID_TAREA_BPM = v_id_tarea_bpm;
				
				UPDATE TCO_DOCUMENTO_TAREA
				SET PUEDE_CREAR = NVL(P_PUEDE_CREAR, TYPE_TCO_DOCUMENTO_TAREA.PUEDE_CREAR),
					PUEDE_MODIFICAR = NVL(P_PUEDE_MODIFICAR, TYPE_TCO_DOCUMENTO_TAREA.PUEDE_MODIFICAR),
					PUEDE_BORRAR = NVL(P_PUEDE_BORRAR, TYPE_TCO_DOCUMENTO_TAREA.PUEDE_BORRAR),
					PUEDE_CONSULTAR = NVL(P_PUEDE_CONSULTAR, TYPE_TCO_DOCUMENTO_TAREA.PUEDE_CONSULTAR)
				WHERE ID_TCA_DOCUMENTO = v_id_documento
				AND ID_TAREA_BPM = v_id_tarea_bpm;

			END LOOP;		
		END LOOP;
		
		IF SQL%FOUND THEN
			P_CODIGO_RES := 1;
			COMMIT;
		END IF;
	
		v_accion := 'MODIFICAR';         
	
    ELSIF (P_ACCION = 5)	-- Lista los tipos de documento
	THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT ID, DESCRIPCION, BAN_ESTATUS
			FROM TCA_DOCUMENTO
			WHERE ID >999 OR ID=0
			ORDER BY ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_TIPO_DOCUMENTO= ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR TIPO DE DOCUMENTOS';		
	
	ELSIF (P_ACCION = 6)		-- Lista los permisos del tipo de documento específicado que tiene asignado por tarea
	THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		FOR ind IN 1 .. P_ID_DOCUMENTO.COUNT
		LOOP
			v_id_documento := P_ID_DOCUMENTO(ind);
			SELECT  ID || ' - ' || DESCRIPCION INTO v_nombreDOCTO FROM TCA_DOCUMENTO WHERE ID = v_id_documento;
			DBMS_OUTPUT.PUT_LINE ('Lista de permisos para el tipo de documento: '); 
			DBMS_OUTPUT.PUT_LINE (v_nombreDOCTO);
			DBMS_OUTPUT.PUT_LINE ('......................................................................');
		
			FOR v_rec IN (
				SELECT TPBPM.DESCRIPCION AS PROCESO, TTBPM.ID || ' - ' || TTBPM.DESCRIPCION AS TAREA_BPM, TDT.PUEDE_CREAR, TDT.PUEDE_MODIFICAR, TDT.PUEDE_BORRAR, TDT.PUEDE_CONSULTAR, TDT.BAN_ESTATUS  
				FROM TCO_DOCUMENTO_TAREA TDT
				INNER JOIN TCA_TAREA_BPM TTBPM ON TDT.ID_TAREA_BPM = TTBPM.ID
				INNER JOIN TCA_PROCESO_BPM TPBPM ON TTBPM.ID_PROCESO_BPM = TPBPM.ID
				WHERE  TDT.ID_TCA_DOCUMENTO = v_id_documento
				ORDER BY 1,2
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('PROCESO = ' || v_rec.PROCESO || ', TAREA_BPM = ' || v_rec.TAREA_BPM || ', PUEDE_CREAR = ' || v_rec.PUEDE_CREAR || ', PUEDE_MODIFICAR = ' || v_rec.PUEDE_MODIFICAR || ', PUEDE_BORRAR = ' || v_rec.PUEDE_BORRAR || ', PUEDE_CONSULTAR = ' || v_rec.PUEDE_CONSULTAR || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
			END LOOP;
            
        END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR LOS PERMISOS DEL TIPO DE DOCUMENTO POR TAREA';
		
	ELSIF (P_ACCION = 7)	-- Lista los permisos del tipo de documento específicado en las tareas indicadas.
	THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		FOR IND IN 1 .. P_ID_DOCUMENTO.COUNT
		LOOP
			v_id_documento := P_ID_DOCUMENTO(ind);
			SELECT ID || ' - ' || DESCRIPCION INTO v_nombreDOCTO FROM TCA_DOCUMENTO WHERE ID = v_id_documento;
			DBMS_OUTPUT.PUT_LINE ('Lista de permisos para el tipo de documento: '); 
			DBMS_OUTPUT.PUT_LINE (v_nombreDOCTO);
			
			v_List_Id_tarea := '';
            
			FOR IND IN 1 .. P_ID_TAREA_BPM.COUNT
			LOOP
				v_List_Id_tarea :=  v_List_Id_tarea ||  P_ID_TAREA_BPM(IND) || ', ';
			END LOOP;
			
			IF (LENGTH(v_List_Id_tarea) > 0) THEN
				v_List_Id_tarea := LPAD(v_List_Id_tarea, LENGTH(v_List_Id_tarea)-2);
			END IF;
			
			v_codeBlock := '
				BEGIN
					FOR v_rec IN (
						SELECT TPBPM.DESCRIPCION AS PROCESO, TTBPM.ID || '' - '' || TTBPM.DESCRIPCION AS TAREA_BPM, TDT.PUEDE_CREAR, TDT.PUEDE_MODIFICAR, TDT.PUEDE_BORRAR, TDT.PUEDE_CONSULTAR, TDT.BAN_ESTATUS  
						FROM TCO_DOCUMENTO_TAREA TDT
						INNER JOIN TCA_TAREA_BPM TTBPM ON TDT.ID_TAREA_BPM = TTBPM.ID
						INNER JOIN TCA_PROCESO_BPM TPBPM ON TTBPM.ID_PROCESO_BPM = TPBPM.ID
						WHERE  TDT.ID_TCA_DOCUMENTO = ' || v_id_documento ||
						' AND TDT.ID_TAREA_BPM IN (' || v_List_Id_tarea || ')
						ORDER BY 1,2						
					) LOOP
						DBMS_OUTPUT.PUT_LINE (''PROCESO = '' || v_rec.PROCESO || '', TAREA_BPM = '' || v_rec.TAREA_BPM || '', PUEDE_CREAR = '' || v_rec.PUEDE_CREAR || '', PUEDE_MODIFICAR = '' || v_rec.PUEDE_MODIFICAR || '', PUEDE_BORRAR = '' || v_rec.PUEDE_BORRAR || '', PUEDE_CONSULTAR = '' || v_rec.PUEDE_CONSULTAR || '', BAN_STATUS = '' || v_rec.BAN_ESTATUS);			
					END LOOP;
				END; ';
			EXECUTE IMMEDIATE v_codeBlock;
			
			DBMS_OUTPUT.PUT_LINE ('......................................................................');
		END LOOP;
			
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR LOS PERMISOS DEL TIPO DE DOCUMENTO POR TAREA ESPECIFICA';
		
	END IF;
   
    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    IF (P_CODIGO_RES = 1)
    THEN		
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || ') se realizó correctamente.');
	ELSE		
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || '), no se realizó correctamente.');
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
				'SP_CONF_PERMISOS_DOCUMENTO',
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
                'SP_CONF_PERMISOS_DOCUMENTO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        
	
/
