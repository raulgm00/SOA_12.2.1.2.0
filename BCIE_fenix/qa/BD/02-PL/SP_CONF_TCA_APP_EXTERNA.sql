-- ##--------------------------------------------------------------------------------------------##

-- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración de las aplicaciones externas, así mismo, permite listar la información de dicha tabla.


/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar las aplicaciones externas (TCA_APP_EXTERNA)

*/
-- ##--------------------------------------------------------------------------------------------##


CREATE OR REPLACE PROCEDURE SP_CONF_TCA_APP_EXTERNA(
	P_ACCION                        IN NUMBER   := 0,		-- Identificador de la transacción a ejecutar
	P_ID_TCA_APP_EXTERNA            IN NUMBER   := NULL,	-- Id tipo de aplicación externa.  (TCA_APP_EXTERNA)
	P_DESCRIPCION        			IN VARCHAR2 := NULL,	-- URL de la aplicación externa (URL), con su respectivo parámetro y valor.  
	P_DESCRIPCION_CORTA      		IN VARCHAR2 := NULL,	-- Descripción corta de la aplicación externa (Nombre del aplicativo).  
	P_REQUIERE_NUEVA_VENTANA 		IN NUMBER   := NULL,	-- Bandera que indica si requiere nueva ventana (1 - Sí, 0 - No)
	P_CODIGO_RES                    OUT NUMBER)				-- Indica el código de respuesta de la transacción.  (0 - Incorrecto, 1 - Correcto).

AS
	err_num		NUMBER;
	err_msg		VARCHAR2 (255);
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA';
	v_Error_msg	VARCHAR2(100) := '';
	TYPE_TCA_APP_EXTERNA TCA_APP_EXTERNA%ROWTYPE;
	v_ID_TCA_APP_EXTERNA 	NUMBER; 
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)			-- Se agrega una nueva app externa
	THEN
		SELECT MAX (ID) INTO v_ID_TCA_APP_EXTERNA FROM TCA_APP_EXTERNA;
		v_ID_TCA_APP_EXTERNA := v_ID_TCA_APP_EXTERNA + 1;
		
		INSERT INTO TCA_APP_EXTERNA (ID, 
									DESCRIPCION,
									DESCRIPCION_CORTA,
									BAN_ESTATUS,
									FECHA_REGISTRO,									
									REQUIERE_NUEVA_VENTANA) 
		VALUES (v_ID_TCA_APP_EXTERNA,
			P_DESCRIPCION,
			P_DESCRIPCION_CORTA,
			1,
			SYSDATE,
			P_REQUIERE_NUEVA_VENTANA);      
		COMMIT;
		
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';
		
	ELSIF (P_ACCION = 2)		-- Se modifica una nueva app externa
	THEN
		IF  (P_ID_TCA_APP_EXTERNA > 0) THEN
			SELECT * INTO TYPE_TCA_APP_EXTERNA FROM TCA_APP_EXTERNA
			WHERE ID = P_ID_TCA_APP_EXTERNA;
			
			UPDATE TCA_APP_EXTERNA SET
				DESCRIPCION = NVL(P_DESCRIPCION, TYPE_TCA_APP_EXTERNA.DESCRIPCION),
				DESCRIPCION_CORTA = NVL(P_DESCRIPCION_CORTA, TYPE_TCA_APP_EXTERNA.DESCRIPCION_CORTA), 
				REQUIERE_NUEVA_VENTANA = NVL(P_REQUIERE_NUEVA_VENTANA, TYPE_TCA_APP_EXTERNA.REQUIERE_NUEVA_VENTANA)				
			WHERE ID = P_ID_TCA_APP_EXTERNA;			
			
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;			
				COMMIT;
			ELSE	
				ROLLBACK;				
			END IF;			   			
		ELSE
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
		
        v_accion := 'MODIFICAR';              
		
    ELSIF (P_ACCION = 3) 		--  Deshabilita los datos de la app externa
    THEN
		IF  (P_ID_TCA_APP_EXTERNA > 0) THEN
			UPDATE TCA_APP_EXTERNA
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_TCA_APP_EXTERNA;		
			
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
	
	ELSIF (P_ACCION = 4) 	--  Habilita los datos de la app externa
    THEN
		IF  (P_ID_TCA_APP_EXTERNA > 0) THEN
			UPDATE TCA_APP_EXTERNA
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_TCA_APP_EXTERNA;		
			
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
	
	ELSIF (P_ACCION = 5)		-- Listar datos de la app externa
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT ID, DESCRIPCION, DESCRIPCION_CORTA, REQUIERE_NUEVA_VENTANA, BAN_ESTATUS FROM TCA_APP_EXTERNA ORDER BY 1
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_TCA_APP_EXTERNA = ' || v_rec.ID || ', URL = ' || v_rec.DESCRIPCION || ', DESCRIPCION = ' || v_rec.DESCRIPCION_CORTA || ', REQUIERE NUEVA VENTANA = ' || v_rec.REQUIERE_NUEVA_VENTANA || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR CATÁLOGO DE APLICATIVOS';
	
    	P_CODIGO_RES := 1;
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
				'SP_CONF_TCA_APP_EXTERNA',
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
                'SP_CONF_TCA_APP_EXTERNA',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        