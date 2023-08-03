-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración de los países, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar los países
(CAT_PAISES)

*/
-- ##--------------------------------------------------------------------------------------------##



CREATE OR REPLACE PROCEDURE SP_CONF_CAT_PAISES(
	P_ACCION                        IN NUMBER   := 0,		-- Indica el tipo de acción a ejecutar:
	P_ID                            IN NUMBER   := NULL,	-- Id del  registro de países. (CAT_PAISES)
	P_DESCRIPCION 	  				IN VARCHAR2 := NULL,	-- Descripción del país (detalle).  
	P_DESCRIPCION_CORTA 			IN VARCHAR2 := NULL,	-- Descripción corta del país (detalle).  
	P_COD_EXTERNO       			IN VARCHAR2 := NULL,	-- Código externo del país  (A tres posiciones)
	P_COD		  					IN VARCHAR2 := NULL,	-- Código externo del país  (A dos posiciones)
	P_CODIGO_RES                    OUT NUMBER)				-- Indica el código de respuesta de la transacción.   (0 - Incorrecto, 1 - Correcto).

AS
	err_num		NUMBER;
	err_msg		VARCHAR2 (255);
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA';
	
	TYPE_CAT_PAISES CAT_PAISES%ROWTYPE;
	v_ID 		NUMBER; 
	v_Error_msg VARCHAR2(100) := ''; 
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)		-- Se agrega un nuevo país
	THEN
		SELECT MAX (ID) INTO v_ID FROM CAT_PAISES;
		v_ID := v_ID + 1;	
		INSERT INTO CAT_PAISES (ID, 
								DESCRIPCION,      
								DESCRIPCION_CORTA,
								FECHA_REGISTRO,
								BAN_ESTATUS,
								COD_EXTERNO,
								COD) 
		VALUES (v_ID,
			P_DESCRIPCION,
			P_DESCRIPCION_CORTA,
			SYSDATE,
			1,
			P_COD_EXTERNO,
			P_COD);      
		COMMIT;
		
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';
		
	ELSIF (P_ACCION = 2)		-- Se modifican los datos del país
	THEN
		IF  (P_ID > 0) THEN
			SELECT * INTO TYPE_CAT_PAISES FROM CAT_PAISES
			WHERE ID = P_ID;
			
			UPDATE CAT_PAISES SET
				DESCRIPCION = NVL(P_DESCRIPCION, TYPE_CAT_PAISES.DESCRIPCION),
				DESCRIPCION_CORTA = NVL(P_DESCRIPCION_CORTA, TYPE_CAT_PAISES.DESCRIPCION_CORTA), 
				COD_EXTERNO = NVL(P_COD_EXTERNO, TYPE_CAT_PAISES.COD_EXTERNO), 
				COD = NVL(P_COD, TYPE_CAT_PAISES.COD)
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
		
        v_accion := 'MODIFICAR';              
		
    ELSIF (P_ACCION = 3) 	-- Deshabilita los datos del país
    THEN
		IF  (P_ID > 0) THEN
			UPDATE CAT_PAISES
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
	
	ELSIF (P_ACCION = 4) 	-- Habilita los datos del país
    THEN
		IF  (P_ID > 0) THEN
			UPDATE CAT_PAISES
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
	
	ELSIF (P_ACCION = 5)		-- Listar datos del país
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT ID,  DESCRIPCION, DESCRIPCION_CORTA, COD_EXTERNO, COD, BAN_ESTATUS
			FROM CAT_PAISES 
			ORDER BY ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_CAT_PAISES = ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', DESCRIPCION_CORTA = ' || v_rec.DESCRIPCION_CORTA || ', COD_EXTERNO = ' || v_rec.COD_EXTERNO || ', COD = ' || v_rec.COD || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR CATALOGO DE PAISES';
	
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
				'SP_CONF_CAT_PAISES',
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
                'SP_CONF_CAT_PAISES',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END; 