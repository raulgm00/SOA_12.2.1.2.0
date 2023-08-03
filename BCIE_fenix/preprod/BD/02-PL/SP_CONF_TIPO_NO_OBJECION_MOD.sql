-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, desactivar/activar los tipos de no objeción de acuerdo a la modalidad de la adquisición, así mismo, permite listar la información de las tablas que componen la relación.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
3 - Desactivar
4 - Activar
5 - Listar tipo no objeción-modalidad
(TRE_TIPO_NO_OBJECION_MODALIDAD)
6 - Listar tipo no objeción
(TCA_TIPO_NO_OBJECION)
7 - Listar tipo modalidad del proceso
(TCA_TIPO_MODALIDAD_PROCESO)
*/

-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_TIPO_NO_OBJECION_MOD(
	P_ACCION                        IN NUMBER  := 0,		-- Indica el tipo de acción a ejecutar.
	P_ID                            IN NUMBER  := NULL,		-- Id de relación entre no objeción y modalidad. 
	P_ID_TCA_TIPO_MODALIDAD_PRO		IN NUMBER  := NULL,		-- Id tipo de modalidad de proceso. 
	P_ID_TCA_TIPO_NO_OBJECION       IN NUMBER  := NULL,		-- Id tipo no objeción. 
	P_CODIGO_RES                    OUT NUMBER)				-- Indica el código de respuesta de la transacción.
AS	
	err_num      NUMBER;
	err_msg      VARCHAR2 (255);
	v_accion	 VARCHAR2(250) := 'NO IDENTIFICADA';
	v_codeBlock	 VARCHAR2(250);
    v_tabla 	 VARCHAR2(40);
	v_Error_msg  VARCHAR2(100) := ''; 
	v_ID 		 NUMBER; 
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)	-- Agrega un registro a la tbl TRE_TIPO_NO_OBJECION_MODALIDAD
	THEN
		SELECT MAX (ID) INTO v_ID FROM TRE_TIPO_NO_OBJECION_MODALIDAD;
		v_ID := v_ID + 1;
		
        INSERT INTO TRE_TIPO_NO_OBJECION_MODALIDAD(ID,
												ID_TCA_TIPO_MODALIDAD_PROCESO,
                                                ID_TCA_TIPO_NO_OBJECION,
                                                BAN_ESTATUS)
		VALUES(v_ID,
			P_ID_TCA_TIPO_MODALIDAD_PRO,
			P_ID_TCA_TIPO_NO_OBJECION,
            1);
        COMMIT;
        
		P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';                                               
		
    ELSIF (P_ACCION = 3)	--  Desactiva (Deshabilita) el campo BAN_ESTATUS de un registro específico de la tbl TRE_TIPO_NO_OBJECION_MODALIDAD
    THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_TIPO_NO_OBJECION_MODALIDAD
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID;
			COMMIT;
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;	   
		
		v_accion := 'DESACTIVAR';
		
	ELSIF (P_ACCION = 4)	--  Activa (Habilita) el campo BAN_ESTATUS de un registro específico de la tbl TRE_TIPO_NO_OBJECION_MODALIDAD 
    THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_TIPO_NO_OBJECION_MODALIDAD
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID;
			COMMIT;
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;	   
		
		v_accion := 'ACTIVAR';   
		
	ELSIF (P_ACCION = 5)	-- Lista los registros de la tabla TRE_TIPO_NO_OBJECION_MODALIDAD, con sus respectivas tablas de relación.
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT TTNOM.ID AS ID_NO_OBJECION_MODALIDAD, TTMP.ID AS ID_TIPO_MODALIDAD_PROCESO, TTMP.DESCRIPCION AS TIPO_MODALIDAD_PROCESO, TTNO.ID AS ID_TIPO_NO_OBJECION, TTNO.DESCRIPCION AS TIPO_NO_OBJECION, TTNOM.BAN_ESTATUS
			FROM TRE_TIPO_NO_OBJECION_MODALIDAD TTNOM
			INNER JOIN TCA_TIPO_NO_OBJECION TTNO ON TTNOM.ID_TCA_TIPO_NO_OBJECION = TTNO.ID
			INNER JOIN TCA_TIPO_MODALIDAD_PROCESO TTMP ON TTNOM.ID_TCA_TIPO_MODALIDAD_PROCESO = TTMP.ID
			ORDER BY TTNOM.ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_NO_OBJECION_MODALIDAD = ' || v_rec.ID_NO_OBJECION_MODALIDAD || ', ID_TIPO_MODALIDAD_PROCESO = ' || v_rec.ID_TIPO_MODALIDAD_PROCESO || ', TIPO_MODALIDAD_PROCESO = ' || v_rec.TIPO_MODALIDAD_PROCESO || ', ID_TIPO_NO_OBJECION = ' || v_rec.ID_TIPO_NO_OBJECION || ', TIPO_NO_OBJECION = ' || v_rec.TIPO_NO_OBJECION || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);
		END LOOP;
	
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR TIPO_NO_OBJECION_MODALIDAD';	   
		
	ELSIF (P_ACCION = 6) OR (P_ACCION = 7)	-- Lista los registros de los catálogos TCA_TIPO_NO_OBJECION y TCA_TIPO_MODALIDAD_PROCESO que componen la relación 
    THEN
		CASE P_ACCION
			WHEN 6 THEN
				v_tabla := 'TCA_TIPO_NO_OBJECION';
				v_accion := 'LISTAR TIPO_NO_OBJECION';				
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
				'SP_CONF_TIPO_NO_OBJECION_MOD',
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
                'SP_CONF_TIPO_NO_OBJECION_MOD',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        
