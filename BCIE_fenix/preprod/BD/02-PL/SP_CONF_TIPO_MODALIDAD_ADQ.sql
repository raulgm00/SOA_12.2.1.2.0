-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, desactivar/activar la modalidad del proceso de acuerdo a los tipos de adquisición, así mismo, permite listar la información de las tablas que componen la relación.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
3 - Desactivar
4 - Activar
5 - Listar tipo modalidad-adquisición
(TRE_TIPO_MODALIDAD_ADQUISICION)
6 - Listar tipo adquisición
(TCA_TIPO_ADQUISICION)
7 - Listar tipo modalidad del proceso
(TCA_TIPO_MODALIDAD_PROCESO)
*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_TIPO_MODALIDAD_ADQ(
	P_ACCION                        IN NUMBER  := 0,		-- Indica el tipo de acción a ejecutar.
	P_ID                            IN NUMBER  := NULL,		-- Id de relación entre modalidad y adquisición. 
	P_ID_TCA_TIPO_ADQUISICION       IN NUMBER  := NULL,		-- Id tipo de adquisición.
	P_ID_TCA_TIPO_MODALIDAD_PRO		IN NUMBER  := NULL,		-- Id tipo de modalidad de proceso. 
	P_CODIGO_RES                    OUT NUMBER)				-- Indica el código de respuesta de la transacción.
AS
	err_num		NUMBER;
	err_msg		VARCHAR2(255);
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA';
	v_codeBlock	VARCHAR2(250);
    v_tabla 	VARCHAR2(40);
	v_Error_msg VARCHAR2(100) := ''; 
	v_ID 		NUMBER; 
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)	-- Agrega un registro a la tbl TRE_TIPO_MODALIDAD_ADQUISICION
	THEN
		SELECT MAX (ID) INTO v_ID FROM TRE_TIPO_MODALIDAD_ADQUISICION;
		v_ID := v_ID + 1;
		
        INSERT INTO TRE_TIPO_MODALIDAD_ADQUISICION(ID,
												ID_TCA_TIPO_ADQUISICION,
                                                ID_TCA_TIPO_MODALIDAD_PROCESO,
                                                BAN_ESTATUS)
		VALUES(v_ID,
			P_ID_TCA_TIPO_ADQUISICION,
            P_ID_TCA_TIPO_MODALIDAD_PRO,
            1);
        COMMIT;
		
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';                                               
		
    ELSIF (P_ACCION = 3) 	--  Desactiva (Deshabilita) el campo BAN_ESTATUS de un registro específico de la tbl TRE_TIPO_MODALIDAD_ADQUISICION
    THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_TIPO_MODALIDAD_ADQUISICION
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID;		
			COMMIT;
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;
	   
		v_accion := 'DESACTIVAR';
	
	ELSIF (P_ACCION = 4)	--  Activa (Habilita) el campo BAN_ESTATUS de un registro específico de la tbl TRE_TIPO_MODALIDAD_ADQUISICION 
    THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_TIPO_MODALIDAD_ADQUISICION
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID;		
			COMMIT;
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;
	   
		v_accion := 'ACTIVAR';
	
	ELSIF (P_ACCION = 5)	-- Lista los registros de la tabla TRE_TIPO_MODALIDAD_ADQUISICION, con sus respectivas tablas de relación.
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT TTMA.ID as ID_MODALIDAD_ADQUISICION,TTA.ID as ID_TIPO_ADQUISICION, TTA.DESCRIPCION as TIPO_ADQUISICION , TTMP.ID as ID_TIPO_MODALIDAD_PROCESO, TTMP.DESCRIPCION as TIPO_MODALIDAD_PROCESO,  TTMA.BAN_ESTATUS
			FROM TRE_TIPO_MODALIDAD_ADQUISICION TTMA
			INNER JOIN TCA_TIPO_ADQUISICION TTA ON TTMA.ID_TCA_TIPO_ADQUISICION = TTA.ID
			INNER JOIN TCA_TIPO_MODALIDAD_PROCESO TTMP ON TTMA.ID_TCA_TIPO_MODALIDAD_PROCESO = TTMP.ID
			ORDER BY TTMA.ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_MODALIDAD_ADQUISICION = ' || v_rec.ID_MODALIDAD_ADQUISICION || ', ID_TIPO_ADQUISICION = ' || v_rec.ID_TIPO_ADQUISICION || ', TIPO_ADQUISICION = ' || v_rec.TIPO_ADQUISICION || ', ID_TIPO_MODALIDAD_PROCESO = ' || v_rec.ID_TIPO_MODALIDAD_PROCESO || ', TIPO_MODALIDAD_PROCESO = ' || v_rec.TIPO_MODALIDAD_PROCESO || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR TIPO_MODALIDAD_ADQUISICION';
		
	ELSIF (P_ACCION = 6) OR (P_ACCION = 7)	-- Lista los registros de los catálogos TCA_TIPO_ADQUISICION y TCA_TIPO_MODALIDAD_PROCESO que componen la relación 
    THEN
		CASE P_ACCION
			WHEN 6 THEN
				v_tabla := 'TCA_TIPO_ADQUISICION';
				v_accion := 'LISTAR TIPO_ADQUISICION';				
			WHEN 7 THEN
				v_tabla := 'TCA_TIPO_MODALIDAD_PROCESO';
				v_accion := 'LISTAR TIPO_MODALIDAD_PROCESO';
		END CASE;
        
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		v_codeBlock := '
				BEGIN
					FOR v_rec IN (
						SELECT ID, DESCRIPCION 
						FROM ' || v_tabla || '
						ORDER BY ID
					) LOOP
						DBMS_OUTPUT.PUT_LINE (''ID = '' || v_rec.ID || '', DESCRIPCION = '' || v_rec.DESCRIPCION );			
					END LOOP;
				END; ';
		-- DBMS_OUTPUT.PUT_LINE (v_codeBlock);
		EXECUTE IMMEDIATE v_codeBlock;
        
		P_CODIGO_RES := 1;
	
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
				'SP_CONF_TIPO_MODALIDAD_ADQ',
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
                'SP_CONF_TIPO_MODALIDAD_ADQ',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        