-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar,  desactivar/activar la configuración de la asignación de permisos por rol BPM sobre compontes comunes, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
3 - Desactivar
4 - Activar
5 - Listar la asignación de permisos por acción (TRE_ACCION_ROL_BPM)

*/
-- ##--------------------------------------------------------------------------------------------##


CREATE OR REPLACE PROCEDURE SP_CONF_PERMISOS_ACCION_ROL(
	P_ACCION                    IN NUMBER  := 0,		-- Identificador de la transacción a ejecutar
	P_ID                        IN NUMBER  := NULL,		-- Id del registro del catálogo asignación de permisos por rol. (TRE_ACCION_ROL_BPM)
	P_ID_TCA_ACCION_ROL_BPM		IN NUMBER  := NULL,		-- Id tipo de acción permitida.  (TCA_ ACCION_ROL_BPM)

	P_ID_TCA_ROL_BPM            IN NUMBER  := NULL,		-- Id tipo de rol BPM.  (TCA_ROL_BPM)
	P_CODIGO_RES                OUT NUMBER)				-- Indica el código de respuesta de la transacción.  (0 - Incorrecto, 1 - Correcto).

AS   
	err_num     NUMBER;
	err_msg     VARCHAR2 (255);
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA';
	v_codeBlock	VARCHAR2(250);
	v_tabla 	VARCHAR2(40);
	v_id_permiso NUMBER;
	v_Error_msg	 VARCHAR2(100) := '';
	
   
BEGIN
	P_CODIGO_RES := 0;
    IF (P_ACCION = 1)		-- Se agrega un nuevo permiso sobre compontes comunes
    THEN
		SELECT MAX (ID) INTO v_id_permiso FROM TRE_ACCION_ROL_BPM;
		v_id_permiso := v_id_permiso + 1;
        
		INSERT INTO TRE_ACCION_ROL_BPM (ID,
                                        ID_TCA_ACCION_ROL_BPM,
                                        ID_TCA_ROL_BPM,
                                        BAN_ESTATUS)
			VALUES(v_id_permiso,
				P_ID_TCA_ACCION_ROL_BPM,
                P_ID_TCA_ROL_BPM,
                1);
            COMMIT;
			
        P_CODIGO_RES := 1;
		v_accion := 'AGREGAR';
		 
    ELSIF (P_ACCION = 3)	-- Se deshabilita un nuevo permiso sobre compontes comunes
	THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_ACCION_ROL_BPM
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
	
	ELSIF (P_ACCION = 4)	-- Habilitar un nuevo permiso sobre compontes comunes
	THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_ACCION_ROL_BPM
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
	
	ELSIF (P_ACCION = 5)		-- Listar la asignación de permisos por acción
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
		
			SELECT TARBPM.ID AS ID_PERMISO_ACCION_ROL_BPM,  TACCION.ID AS ID_PERMISO, TACCION.DESCRIPCION AS PERMISO, TROL.ID AS ID_ROL_BPM, TROL.DESCRIPCION AS ROL_BPM, TARBPM.BAN_ESTATUS
			FROM TRE_ACCION_ROL_BPM TARBPM
			INNER JOIN TCA_ACCION_ROL_BPM TACCION ON TARBPM.ID_TCA_ACCION_ROL_BPM = TACCION.ID
			INNER JOIN TCA_ROL_BPM TROL ON TARBPM.ID_TCA_ROL_BPM = TROL.ID
			ORDER BY TARBPM.ID
		
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_PERMISO_ACCION_ROL_BPM = ' || v_rec.ID_PERMISO_ACCION_ROL_BPM || ', ID_PERMISO = ' || v_rec.ID_PERMISO || ', PERMISO = ' || v_rec.PERMISO || ', ID_ROL_BPM = ' || v_rec.ID_ROL_BPM || ', ROL_BPM = ' || v_rec.ROL_BPM || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR LOS PERMISOS POR ROLES SOBRE COMPONENTE COMUN';
	
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
				'SP_CONF_PERMISOS_ACCION_ROL',
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
                'SP_CONF_PERMISOS_ACCION_ROL',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        