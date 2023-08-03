-- ##--------------------------------------------------------------------------------------------##
-- Descripción:  Script que permite agregar, modificar, desactivar/activar la configuración del  catálogo de preguntas, así mismo, permite listar la información de dicho catálogo.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar los datos del catálogo solicitado (CAT_PREGUNTAS)
6 - Listar el catálogo de preguntas por criterio (CAT_PREGUNTAS)
*/
-- ##--------------------------------------------------------------------------------------------##


CREATE OR REPLACE PROCEDURE SP_CONF_CAT_PREGUNTA(	
	P_ACCION                IN NUMBER  := 0,		-- Identificador de la transacción a ejecutar
	P_ID_CAT_PREGUNTA 		IN NUMBER := 0,			-- Id del  catálogo de pregunta. (CAT_PREGUNTAS)
	P_PREGUNTA        		IN VARCHAR2 := NULL,	-- Descripción de la pregunta.	
	P_ID_RESPONSABLE  		IN NUMBER := NULL,		-- Id del Rol BPM (TCA_ROL_BPM), responsable de contestar la pregunta. 
	P_ID_CRITERIO     		IN NUMBER := NULL,		-- Id del criterio (CAT_CRITERIO)
	P_NUM_ORDEN				IN NUMBER := NULL,   	-- Num. de orden de la pregunta.
	P_CODIGO_RES            OUT NUMBER)				-- Código de respuesta de la transacción
AS
	err_num			NUMBER;
	err_msg			VARCHAR2 (255);
	v_accion		VARCHAR2 (250) := 'NO IDENTIFICADA';
	v_ID_CAT_PREGUNTAS NUMBER;   
	v_ID_CAT_PREGUNTA_OPCION NUMBER;   
	v_ID_PRINCIPIO 	NUMBER := NULL;   
	v_Error_msg	 	VARCHAR2(100) := ''; 
	TYPE_CAT_PREGUNTAS CAT_PREGUNTAS%ROWTYPE;
	v_Bandera		NUMBER := 0;   
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)	-- Agrega una nueva pregunta al catálogo de preguntas
	THEN
	
		SELECT MAX (ID) INTO v_ID_CAT_PREGUNTAS FROM CAT_PREGUNTAS;
		v_ID_CAT_PREGUNTAS := v_ID_CAT_PREGUNTAS + 1;
		
		IF (P_ID_CRITERIO IS NOT NULL) THEN SELECT ID_PRINCIPIO INTO v_ID_PRINCIPIO FROM CAT_CRITERIO WHERE ID = P_ID_CRITERIO; END IF;
		
		IF(P_ID_RESPONSABLE = 11 OR P_ID_RESPONSABLE = 16 OR P_ID_RESPONSABLE = 1)
        THEN 
		
			INSERT INTO CAT_PREGUNTAS (ID, 
									PREGUNTA,
									STATUS,								
									ID_RESPONSABLE,
									ID_TIPO_PREGUNTA,
									ID_PRINCIPIO,
									ID_CRITERIO,
									NUM_ORDEN,
									FECHA_REGISTRO ) 
			VALUES (v_ID_CAT_PREGUNTAS,
					P_PREGUNTA,
					'1',				
					P_ID_RESPONSABLE,
					3,
					v_ID_PRINCIPIO,
					P_ID_CRITERIO,
					P_NUM_ORDEN,   
					SYSDATE);      

			
			-- Se agregan las Opciones de la pregunta TRE_PREGUNTA_OPCION
		
			SELECT MAX (ID) INTO v_ID_CAT_PREGUNTA_OPCION FROM TRE_PREGUNTA_OPCION;
			v_ID_CAT_PREGUNTA_OPCION := v_ID_CAT_PREGUNTA_OPCION + 1;
		
			-- Ingresa las opciones de TRE_PREGUNTA_OPCION
			INSERT INTO TRE_PREGUNTA_OPCION (ID, ID_PREGUNTA, DESC_OPCION, FEC_CREACION, BAN_ESTATUS)
			VALUES ( v_ID_CAT_PREGUNTA_OPCION, v_ID_CAT_PREGUNTAS, 'Sí', CURRENT_DATE, 1);
			
			SELECT MAX (ID) INTO v_ID_CAT_PREGUNTA_OPCION FROM TRE_PREGUNTA_OPCION;
			v_ID_CAT_PREGUNTA_OPCION := v_ID_CAT_PREGUNTA_OPCION + 1;
			
			INSERT INTO TRE_PREGUNTA_OPCION (ID, ID_PREGUNTA, DESC_OPCION, FEC_CREACION, BAN_ESTATUS)
			VALUES ( v_ID_CAT_PREGUNTA_OPCION, v_ID_CAT_PREGUNTAS, 'No', CURRENT_DATE, 1);
			
			SELECT MAX (ID) INTO v_ID_CAT_PREGUNTA_OPCION FROM TRE_PREGUNTA_OPCION;
			v_ID_CAT_PREGUNTA_OPCION := v_ID_CAT_PREGUNTA_OPCION + 1;
			
			INSERT INTO TRE_PREGUNTA_OPCION (ID, ID_PREGUNTA, DESC_OPCION, FEC_CREACION, BAN_ESTATUS)
			VALUES ( v_ID_CAT_PREGUNTA_OPCION, v_ID_CAT_PREGUNTAS, 'No Aplica', CURRENT_DATE, 1);
			
			COMMIT;			
			
			P_CODIGO_RES := 1;
		ELSE
			DBMS_OUTPUT.PUT_LINE ('El P_ID_RESPONSABLE: '||P_ID_RESPONSABLE ||' no es válido, debes ingresar uno de los siguientes Id Rol: 1, 11 o 16');
		END IF;
        v_accion := 'AGREGAR';
		
	ELSIF (P_ACCION = 2)		-- Se modifican los datos del registro de PREGUNTA
	THEN
		IF  (P_ID_CAT_PREGUNTA > 0) THEN
			SELECT * INTO TYPE_CAT_PREGUNTAS FROM CAT_PREGUNTAS
			WHERE ID = P_ID_CAT_PREGUNTA;
			
			IF (P_ID_CRITERIO IS NOT NULL) THEN SELECT ID_PRINCIPIO INTO v_ID_PRINCIPIO FROM CAT_CRITERIO WHERE ID = P_ID_CRITERIO; END IF;
			
			IF (P_ID_RESPONSABLE IS NOT NULL)  THEN 
				IF (P_ID_RESPONSABLE = 11 OR P_ID_RESPONSABLE = 16 OR P_ID_RESPONSABLE = 1) THEN 
					v_Bandera := 1;
				ELSE
					v_Bandera := 0;	
					DBMS_OUTPUT.PUT_LINE ('El P_ID_RESPONSABLE: '||P_ID_RESPONSABLE ||' no es válido, debes ingresar uno de los siguientes Id Rol: 1, 11 o 16');
				END IF;
			ELSE
				v_Bandera := 1;
			END IF;
			
			IF (v_Bandera = 1) THEN
				UPDATE CAT_PREGUNTAS SET
										PREGUNTA = NVL(P_PREGUNTA, TYPE_CAT_PREGUNTAS.PREGUNTA),									
										ID_RESPONSABLE = NVL(P_ID_RESPONSABLE, TYPE_CAT_PREGUNTAS.ID_RESPONSABLE),									
										ID_PRINCIPIO = NVL(v_ID_PRINCIPIO, TYPE_CAT_PREGUNTAS.ID_PRINCIPIO),
										ID_CRITERIO = NVL(P_ID_CRITERIO, TYPE_CAT_PREGUNTAS.ID_CRITERIO),
										NUM_ORDEN = NVL(P_NUM_ORDEN, TYPE_CAT_PREGUNTAS.NUM_ORDEN)			
				WHERE ID = P_ID_CAT_PREGUNTA;		

				IF SQL%FOUND THEN
					P_CODIGO_RES := 1;			
					COMMIT;
				ELSE	
					ROLLBACK;								
				END IF;														
			END IF;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;
		
        v_accion := 'MODIFICAR';              
		
    ELSIF (P_ACCION = 3)		--  Desactiva (Deshabilita) el campo BAN_ESTATUS de un registro específico de la tbl CAT_PREGUNTAS
    THEN
		IF  (P_ID_CAT_PREGUNTA > 0) THEN
			UPDATE CAT_PREGUNTAS
			SET STATUS = '0'
			WHERE ID = P_ID_CAT_PREGUNTA;		
			
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
	   
		v_accion := 'DESACTIVAR';
	
	ELSIF (P_ACCION = 4) 		--  Activa (Habilita) el campo BAN_ESTATUS de un registro específico de la tbl CAT_PREGUNTAS
    THEN
		IF  (P_ID_CAT_PREGUNTA > 0) THEN
			UPDATE CAT_PREGUNTAS
			SET STATUS = '1'
			WHERE ID = P_ID_CAT_PREGUNTA;		
			
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
	   
		v_accion := 'ACTIVAR';
	
	ELSIF (P_ACCION = 5)		-- Lista los registros de la tabla CAT_PREGUNTAS, con sus respectivas tablas de relación.
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (			
			SELECT 
			CATPRI.ID AS ID_PRINCIPIO,  CATPRI.ID  || '. ' ||CATPRI.DESCRIPCION AS PRINCIPIO,
			CATCRI.ID AS ID_CRITERIO, 
			CATPRI.ID || '.' || CATCRI.ID || '. ' || CATCRI.DESCRIPCION AS CRITERIO, 
			CATPREG.NUM_ORDEN, CATPREG.PREGUNTA, CATPREG.STATUS, CATPREG.ID AS ID_CATPREG
			FROM  CAT_PREGUNTAS CATPREG
			INNER JOIN CAT_CRITERIO CATCRI ON CATPREG.ID_CRITERIO = CATCRI.ID
			INNER JOIN CAT_PRINCIPIO CATPRI ON CATCRI.ID_PRINCIPIO = CATPRI.ID
			--WHERE CATPROD.ID=3
			ORDER BY CATPRI.ID, CATCRI.ID,CATPREG.NUM_ORDEN 
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID CAT PREGUNTA = ' || v_rec.ID_CATPREG || ', PRINCIPIO = ' || v_rec.PRINCIPIO || ', CRITERIO = ' || v_rec.CRITERIO || ', NUM_ORDEN = ' || v_rec.NUM_ORDEN || ', PREGUNTA = ' || v_rec.PREGUNTA || ', STATUS = ' || v_rec.STATUS);			
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR EL CATÁLOGO DE PREGUNTAS';
		
	ELSIF (P_ACCION = 6)		-- Lista los registros de la tabla CAT_PREGUNTAS por criterio, con sus respectivas tablas de relación.
    THEN
		IF (P_ID_CRITERIO IS NOT NULL) THEN
			DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
			FOR v_rec IN (			
				SELECT 
				CATPRI.ID AS ID_PRINCIPIO,  CATPRI.ID  || '. ' ||CATPRI.DESCRIPCION AS PRINCIPIO,
				CATCRI.ID AS ID_CRITERIO, 
				CATPRI.ID || '.' || CATCRI.ID || '. ' || CATCRI.DESCRIPCION AS CRITERIO, 
				CATPREG.NUM_ORDEN, CATPREG.PREGUNTA, CATPREG.STATUS, CATPREG.ID AS ID_CATPREG
				FROM  CAT_PREGUNTAS CATPREG
				INNER JOIN CAT_CRITERIO CATCRI ON CATPREG.ID_CRITERIO = CATCRI.ID
				INNER JOIN CAT_PRINCIPIO CATPRI ON CATCRI.ID_PRINCIPIO = CATPRI.ID
				WHERE CATCRI.ID = P_ID_CRITERIO
				ORDER BY CATPRI.ID, CATCRI.ID,CATPREG.NUM_ORDEN 
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID CAT PREGUNTA = ' || v_rec.ID_CATPREG || ', PRINCIPIO = ' || v_rec.PRINCIPIO || ', CRITERIO = ' || v_rec.CRITERIO || ', NUM_ORDEN = ' || v_rec.NUM_ORDEN || ', PREGUNTA = ' || v_rec.PREGUNTA || ', STATUS = ' || v_rec.STATUS);			
			END LOOP;
			P_CODIGO_RES := 1;	
		ELSE
			v_Error_msg := 'No se ha indicado el identificador del criterio a filtrar.';
		END IF;
		
		v_accion := 'LISTAR CATÁLOGO DE PREGUNTAS POR CRITERIO';
	
	END IF;
   
    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    IF (P_CODIGO_RES = 1)
    THEN
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || ') se realizó correctamente.');
	ELSE
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || '), no se realizó correctamente. ' || v_Error_msg);
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
				'SP_CONF_CAT_PREGUNTA',
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
                'SP_CONF_CAT_PREGUNTA',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        