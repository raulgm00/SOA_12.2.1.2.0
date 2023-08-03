-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, desactivar/activar la configuración de los validadores de condicion, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
3 - Desactivar
4 - Activar
5 - Listar los validadores de condición (TRE_CATEGORIA_ROL_BPM)

*/
-- ##--------------------------------------------------------------------------------------------##



CREATE OR REPLACE PROCEDURE SP_CONF_VALIDADORES_CONDICION(
    P_ACCION           IN NUMBER := NULL,		-- Indica el tipo de acción a ejecutar
    P_ID               IN NUMBER := NULL,		-- Id del  registro de los validadores de condición (TRE_CATEGORIA_ROL_BPM)
    P_ID_TCA_CATEGORIA IN NUMBER := NULL,		-- Id del registro de Categoría de condición. (TCA_CATEGORIA_CONDICION)
    P_ID_TCA_ROL_BPM   IN NUMBER := NULL,		-- Id del registro de Rol BPM.  (TCA_ROL_BPM)
    P_ID_CAT_PRODUCTO  IN NUMBER := NULL,		-- Id del registro de del Producto. (CAT_PRODUCTO)    
    P_CODIGO_RES       OUT NUMBER			    -- Indica el código de respuesta de la transacción.  (0 - Incorrecto, 1 - Correcto).
)
AS
    err_num     NUMBER;
    err_msg     VARCHAR2(255);    
    v_codeBlock VARCHAR2(1000);
    v_id_table  NUMBER;
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA';
	v_Error_msg	VARCHAR2(500) := '';
	
BEGIN
	P_CODIGO_RES := 0;    
    IF (P_ACCION = 1)	-- Se agrega una nueva relación de validadores condición 
    THEN 
    
        IF (P_ID_TCA_ROL_BPM NOT IN (2,4,7,10,11,13,14,21,24,49,50,63))
        THEN
            P_CODIGO_RES := 0;    
            v_Error_msg := 'Se ha enviado un Id. de rol BPM incorrecto, favor de validar. Los Id de Rol BPM válidos son: 2,4,7,10,11,13,14,21,24,49,50,63';
        ELSE
             SELECT MAX(ID) INTO v_id_table FROM TRE_CATEGORIA_ROL_BPM ;

            v_id_table := v_id_table + 1 ;
            
            INSERT INTO TRE_CATEGORIA_ROL_BPM(ID, 
                                                ID_TCA_CATEGORIA,
                                                ID_TCA_ROL_BPM,
                                                ID_CAT_PRODUCTO,
                                                BAN_ESTATUS)
            VALUES (v_id_table,
                    P_ID_TCA_CATEGORIA,
                    P_ID_TCA_ROL_BPM,
                    P_ID_CAT_PRODUCTO,
                    1);
        
			COMMIT;            
			P_CODIGO_RES := 1;    			
        END IF;
		
		v_accion := 'AGREGAR';         
        
	ELSIF(P_ACCION = 3)		-- Se deshabilita la relación de validadores condición
	THEN  
		IF (P_ID > 0) THEN            
			UPDATE TRE_CATEGORIA_ROL_BPM SET BAN_ESTATUS = 0 WHERE ID = P_ID ;
        
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
        
	ELSIF(P_ACCION = 4)  -- Se habilita la relación de validadores condición
	THEN  
		IF (P_ID > 0) THEN
            UPDATE TRE_CATEGORIA_ROL_BPM SET BAN_ESTATUS = 1 WHERE ID = P_ID ;
            
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
        
	ELSIF (P_ACCION = 5)  --  Listar los validadores de condición (TRE_CATEGORIA_ROL_BPM)
	THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
  
         FOR v_rec IN (
			SELECT CAT.ID AS ID_CATEGORIA
            , CAT.DESCRIPCION AS CATEGORIA_DESCRIPCION
            , ROL.ID AS ID_ROL
            , ROL.DESCRIPCION AS ROL_DESCRIPCION
            , PRO.ID AS ID_PRODUCTO
            , PRO.DESCRIPCION AS PORDUCTO_DESCRIPCION
            FROM TRE_CATEGORIA_ROL_BPM TRE
            INNER JOIN TCA_CATEGORIA_CONDICION CAT ON TRE.ID_TCA_CATEGORIA = CAT.ID
            INNER JOIN TCA_ROL_BPM ROL ON TRE.ID_TCA_ROL_BPM = ROL.ID
            INNER JOIN CAT_PRODUCTO PRO ON TRE.ID_CAT_PRODUCTO = PRO.ID
            WHERE CAT.ID  = P_ID_TCA_CATEGORIA 
            AND PRO.ID = P_ID_CAT_PRODUCTO
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_CATEGORIA = ' || v_rec.ID_CATEGORIA || 
                                    ',CATEGORIA_DESCRIPCION = ' || v_rec.CATEGORIA_DESCRIPCION ||
                                    ',ID_ROL = ' || v_rec.ROL_DESCRIPCION || 
                                    ',ROL_DESCRIPCION = ' || v_rec.ROL_DESCRIPCION ||
                                    ',ID_PRODUCTO = ' || v_rec.ID_PRODUCTO ||
                                    ',PORDUCTO_DESCRIPCION = ' || v_rec.PORDUCTO_DESCRIPCION);			
		END LOOP;
          
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR VALORES TABLA '; 
        
    END IF;

    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    IF (P_CODIGO_RES = 1)
    THEN
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || ') se realizó correctamente.');
	ELSE
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || '), no se realizó correctamente.  ' || v_Error_msg);
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
				'SP_CONF_VALIDADORES_CONDICION',
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
                'SP_CONF_VALIDADORES_CONDICION',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;

END;