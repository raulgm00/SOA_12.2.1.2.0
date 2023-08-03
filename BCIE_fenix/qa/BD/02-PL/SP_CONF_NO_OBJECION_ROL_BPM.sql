-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, desactivar/activar  las áreas participantes obligatorias por tipo de no objeción, así mismo, permite listar la información de las tablas que componen la relación.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
3 - Desactivar
4 - Activar
5 - Listar tipo no objeción-Rol BPM
(TRE_NO_OBJECION_ROL_BPM)
6 - Listar tipo no objeción
(TCA_TIPO_NO_OBJECION)
7 - Listar Rol BPM
(TCA_ROL_BPM)

*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_NO_OBJECION_ROL_BPM(
	P_ACCION                    IN NUMBER  := 0,		-- Indica el tipo de acción a ejecutar.
	P_ID                        IN NUMBER  := NULL,		-- Id de relación entre no objeción y tipo de participante.
	P_ID_TCA_TIPO_NO_OBJECION	IN NUMBER  := NULL,		-- Id tipo no objeción. 
	P_ID_TCA_ROL_BPM            IN NUMBER  := NULL,		-- Id del Rol BPM. 
	P_CODIGO_RES                OUT NUMBER)				-- Indica el código de respuesta de la transacción.
AS   
	err_num     NUMBER;
	err_msg     VARCHAR2 (255);
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA';
	v_codeBlock	VARCHAR2(250);
	v_tabla 	VARCHAR2(40);
	v_Error_msg VARCHAR2(100) := ''; 
	v_ID 		NUMBER; 
   
BEGIN
	P_CODIGO_RES := 0;
    IF (P_ACCION = 1)	-- Agrega un registro a la tbl TRE_NO_OBJECION_ROL_BPM
    THEN		
		
        IF(P_ID_TCA_ROL_BPM = 11 OR P_ID_TCA_ROL_BPM = 6 OR P_ID_TCA_ROL_BPM = 49)
        THEN 
		
			SELECT MAX (ID) INTO v_ID FROM TRE_NO_OBJECION_ROL_BPM;
			v_ID := v_ID + 1;
			
            INSERT INTO TRE_NO_OBJECION_ROL_BPM(ID,                                           
                                                ID_TCA_TIPO_NO_OBJECION,
                                                ID_TCA_ROL_BPM,
                                                BAN_ESTATUS)
			VALUES(v_ID,
				P_ID_TCA_TIPO_NO_OBJECION,
                P_ID_TCA_ROL_BPM,
                1);
            COMMIT;
			
            P_CODIGO_RES := 1;
           
        ELSE 
            DBMS_OUTPUT.PUT_LINE ('El P_ID_TCA_ROL_BPM: '||P_ID_TCA_ROL_BPM ||' no es válido, debes ingresar uno de los siguientes Id Rol: 6, 11 o 49');
        END IF;
		 v_accion := 'AGREGAR';
		 
    ELSIF (P_ACCION = 3)	--  Desactiva (Deshabilita) el campo BAN_ESTATUS de un registro específico de la tbl TRE_NO_OBJECION_ROL_BPM
	THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_NO_OBJECION_ROL_BPM
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID;			
			COMMIT;
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;
       
		v_accion := 'DESACTIVAR';
	
	ELSIF (P_ACCION = 4)	--  Activa (Habilita) el campo BAN_ESTATUS de un registro específico de la tbl TRE_NO_OBJECION_ROL_BPM 
	THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_NO_OBJECION_ROL_BPM
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID;			
			COMMIT;
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;
       
		v_accion := 'ACTIVAR';
	
	ELSIF (P_ACCION = 5)	-- Lista los registros de la tabla TRE_NO_OBJECION_ROL_BPM, con sus respectivas tablas de relación.
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT TNOR.ID AS ID_NO_OBJECION_ROL_BPM,  TTNO.ID AS ID_TIPO_NO_OBJECION, TTNO.DESCRIPCION AS TIPO_NO_OBJECION, TROL.ID AS ID_ROL_BPM, TROL.DESCRIPCION AS ROL_BPM, TNOR.BAN_ESTATUS
			FROM TRE_NO_OBJECION_ROL_BPM TNOR
			INNER JOIN TCA_TIPO_NO_OBJECION TTNO ON TNOR.ID_TCA_TIPO_NO_OBJECION = TTNO.ID
			INNER JOIN TCA_ROL_BPM TROL ON TNOR.ID_TCA_ROL_BPM = TROL.ID
			ORDER BY TNOR.ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_NO_OBJECION_ROL_BPM = ' || v_rec.ID_NO_OBJECION_ROL_BPM || ', ID_TIPO_NO_OBJECION = ' || v_rec.ID_TIPO_NO_OBJECION || ', TIPO_NO_OBJECION = ' || v_rec.TIPO_NO_OBJECION || ', ID_ROL_BPM = ' || v_rec.ID_ROL_BPM || ', ROL_BPM = ' || v_rec.ROL_BPM || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR NO_OBJECION_ROL_BPM ';
	
	ELSIF (P_ACCION = 6) OR (P_ACCION = 7)	-- Lista los registros de los catálogos TCA_TIPO_NO_OBJECION y TCA_ROL_BPM que componen la relación 
    THEN
		CASE P_ACCION
			WHEN 6 THEN
				v_tabla := 'TCA_TIPO_NO_OBJECION';
				v_accion := 'LISTAR TIPO_NO_OBJECION';				
			WHEN 7 THEN
				v_tabla := 'TCA_ROL_BPM';
				v_accion := 'LISTAR ROL_BPM';
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
				'SP_CONF_NO_OBJECION_ROL_BPM',
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
                'SP_CONF_NO_OBJECION_ROL_BPM',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        