-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, desactivar/activar la configuración del responsable de atender la acción por categoría, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
3 - Desactivar
4 - Activar
5 - Listar responsables atender acción por categoría (TRE_CATEGORIA_ACCION_ROL_BPM)

*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_RESPONSABLE_ACCION(
	P_ACCION                        IN NUMBER   := 0,		-- Indica el tipo de acción a ejecutar.
	P_ID                            IN NUMBER   := NULL,	-- Id de relación entre responsable y categoría acción.
	P_ID_TCA_ROL_BPM	       		IN NUMBER   := NULL,	-- Id del  Rol BPM. 
	P_ID_TCA_CATEGORIA_ACCION  		IN NUMBER   := NULL,	-- Id categoría de acción. 
	P_CODIGO_RES                    OUT NUMBER)				-- Indica el código de respuesta de la transacción. 
AS
	err_num		NUMBER;
	err_msg		VARCHAR2 (255);
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA';
	v_codeBlock	VARCHAR2(250);
    v_tabla 	VARCHAR2(40);
	v_ID 		NUMBER; 
	v_Error_msg	VARCHAR2(100) := ''; 
	
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)
	THEN
	
		SELECT MAX (ID) INTO v_ID FROM TRE_CATEGORIA_ACCION_ROL_BPM;
		v_ID := v_ID + 1;
		
		--Crea un nuevo registro en la tabla TRE_CATEGORIA_ACCION_ROL_BPM
        INSERT INTO TRE_CATEGORIA_ACCION_ROL_BPM(ID,
                                                 ID_TCA_CATEGORIA_ACCION,
                                                 ID_TCA_ROL_BPM,
                                                 BAN_ESTATUS)
		VALUES(v_ID,
			P_ID_TCA_CATEGORIA_ACCION,
            P_ID_TCA_ROL_BPM,
            1);
		COMMIT;
		
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';
		
	ELSIF (P_ACCION = 3) 
    THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_CATEGORIA_ACCION_ROL_BPM
			SET BAN_ESTATUS = 0
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
	   
		v_accion := 'DESACTIVAR';
	
	ELSIF (P_ACCION = 4) 
    THEN
		IF  (P_ID > 0) THEN
			UPDATE TRE_CATEGORIA_ACCION_ROL_BPM
			SET BAN_ESTATUS = 1
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
	   
		v_accion := 'ACTIVAR';
	
	ELSIF (P_ACCION = 5)
    THEN	
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT TCAR.ID AS ID_CAT_ACCION_RESPONSABLE, TCA.ID AS ID_CATEGORIA_ACCION, TCA.DESCRIPCION AS CATEGORIA_ACCION, TRB.ID AS ID_RESPONSABLE, TRB.DESCRIPCION AS RESPONSABLE, TCAR.BAN_ESTATUS
			FROM TRE_CATEGORIA_ACCION_ROL_BPM TCAR
			INNER JOIN TCA_CATEGORIA_ACCION TCA ON TCAR.ID_TCA_CATEGORIA_ACCION = TCA.ID
			INNER JOIN  TCA_ROL_BPM TRB ON TCAR.ID_TCA_ROL_BPM = TRB.ID
			ORDER BY TCAR.ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_CAT_ACCION_RESPONSABLE = ' || v_rec.ID_CAT_ACCION_RESPONSABLE || ', ID_CATEGORIA_ACCION = ' || v_rec.ID_CATEGORIA_ACCION || ', CATEGORIA_ACCION = ' || v_rec.CATEGORIA_ACCION || ', ID_RESPONSABLE (ROL BPM) = ' || v_rec.ID_RESPONSABLE || ', RESPONSABLE = ' || v_rec.RESPONSABLE || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR RESPONSABLES DE ATENDER LA ACCIÓN POR CATEGORIA';
	
	ELSIF (P_ACCION = 6) OR (P_ACCION = 7)
    THEN
		CASE P_ACCION
			WHEN 6 THEN
				v_tabla := 'TCA_CATEGORIA_ACCION';
				v_accion := 'LISTAR CATEGORIAS DE ACCION';				
			WHEN 7 THEN
				v_tabla := 'TCA_ROL_BPM';
				v_accion := 'LISTAR ROLES BPM';
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
				'SP_CONF_RESPONSABLE_ACCION',
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
                'SP_CONF_RESPONSABLE_ACCION',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        