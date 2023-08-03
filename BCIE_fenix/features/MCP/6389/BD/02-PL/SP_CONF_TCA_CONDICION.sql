-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración de la Condición, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar los tipos de Condición
(TCA_TIPO_CONDICION)
6 - Listar las Condiciones de acuerdo al tipo de Condición
7 - Listar la configuración por Condición
(TCO_ATRIBUTO_TCC)


*/
-- ##--------------------------------------------------------------------------------------------##



CREATE OR REPLACE PROCEDURE SP_CONF_TCA_CONDICION (
	P_ACCION                 		IN NUMBER   := 0,			-- Indica el tipo de acción a ejecutar
	P_DESCRIPCION            		IN VARCHAR2 := NULL,		-- Nombre de la Condición (Descripción)
	P_DESCRIPCION_CORTA      		IN VARCHAR2 := NULL,		-- Id de la Condición (Descripción corta)
	P_ID_TCA_TIPO_CONDICION			IN NUMBER   := NULL,		-- Id tipo de Condición (Subtipo)
	P_ES_EDITABLE_EN_FORMALIZACION	IN NUMBER   := NULL,		-- Bandera que indica si es editable en formalización (1- Sí, 0 - No)
	P_REQUIERE_VALIDAR_COFI         IN NUMBER   := NULL,		-- Bandera que indica si requiere validación de COFI  (1- Sí, 0 - No) 
	P_SE_PUEDE_DISPENSAR            IN NUMBER   := NULL,		-- Bandera que indica si se puede dispensar en una enmienda (1- Sí, 0 - No)
	P_ES_PLANTILLA               	IN NUMBER   := NULL,		-- Bandera que indica si es plantilla (1- Sí, 0 - No)	
	P_COD_EXTERNO              		IN VARCHAR2 := NULL,		-- Código externo (Identificador de flexcube)
	P_ES_UNICO_EN_OPERACION			IN NUMBER   := NULL,		-- Bandera que indica si es único en operación (1- Sí, 0 - No)
	P_ID_TCA_CONDICION 				IN NUMBER   := NULL,		-- Id. del registro del catálogo de condición. (TCA_CONDICION)
	P_CODIGO_RES                	OUT NUMBER					-- Indica el código de respuesta de la transacción. (0 - Incorrecto, 1 - Correcto).
	)
AS
	err_num     		NUMBER;
	err_msg     		VARCHAR2 (255);
	v_ID_TCA_CONDICION   NUMBER;   
	TYPE_TCA_CONDICION TCA_CONDICION%ROWTYPE;
	v_nombreCONDICION	VARCHAR2 (256) := NULL;
	v_accion			VARCHAR2 (250) := 'NO IDENTIFICADA';
	v_Error_msg	 	    VARCHAR2(100) := '';
	
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)	-- Inserta datos en TCA_CONDICION
	THEN
		SELECT MAX (ID) INTO v_ID_TCA_CONDICION FROM TCA_CONDICION;

		v_ID_TCA_CONDICION := v_ID_TCA_CONDICION + 1;

		INSERT INTO TCA_CONDICION (ID,
							DESCRIPCION,
                            DESCRIPCION_CORTA,
                            ID_TCA_TIPO_CONDICION,
                            ES_EDITABLE_EN_FORMALIZACION,
                            REQUIERE_VALIDAR_COFI,
                            SE_PUEDE_DISPENSAR,														
                            ES_PLANTILLA,							
							FECHA_REGISTRO,
                            BAN_ESTATUS,
                            COD_EXTERNO,
							ES_UNICO_EN_OPERACION							
                            )
		VALUES (v_ID_TCA_CONDICION,
			P_DESCRIPCION,
			P_DESCRIPCION_CORTA,
			P_ID_TCA_TIPO_CONDICION,			
			P_ES_EDITABLE_EN_FORMALIZACION,
			P_REQUIERE_VALIDAR_COFI,
			P_SE_PUEDE_DISPENSAR,			
			P_ES_PLANTILLA,			
			SYSDATE,
			1,
			P_COD_EXTERNO,						
			P_ES_UNICO_EN_OPERACION			
		);

		COMMIT;
		
		P_CODIGO_RES := 1;
		v_accion := 'AGREGAR';       
		
	ELSIF (P_ACCION = 2)	-- Modificar datos de TCA_CONDICION
	THEN
		IF  (P_ID_TCA_CONDICION > 0) THEN
			SELECT * INTO TYPE_TCA_CONDICION FROM TCA_CONDICION
			WHERE ID = P_ID_TCA_CONDICION;
			
			UPDATE TCA_CONDICION SET
				DESCRIPCION = NVL(P_DESCRIPCION, TYPE_TCA_CONDICION.DESCRIPCION),
				DESCRIPCION_CORTA = NVL(P_DESCRIPCION_CORTA, TYPE_TCA_CONDICION.DESCRIPCION_CORTA),				
				ID_TCA_TIPO_CONDICION = NVL(P_ID_TCA_TIPO_CONDICION, TYPE_TCA_CONDICION.ID_TCA_TIPO_CONDICION),
				ES_EDITABLE_EN_FORMALIZACION = NVL(P_ES_EDITABLE_EN_FORMALIZACION, TYPE_TCA_CONDICION.ES_EDITABLE_EN_FORMALIZACION),
				REQUIERE_VALIDAR_COFI = NVL(P_REQUIERE_VALIDAR_COFI, TYPE_TCA_CONDICION.REQUIERE_VALIDAR_COFI),
				SE_PUEDE_DISPENSAR = NVL(P_SE_PUEDE_DISPENSAR, TYPE_TCA_CONDICION.SE_PUEDE_DISPENSAR),				
				ES_PLANTILLA = NVL(P_ES_PLANTILLA, TYPE_TCA_CONDICION.ES_PLANTILLA),				
				COD_EXTERNO = NVL(P_COD_EXTERNO, TYPE_TCA_CONDICION.COD_EXTERNO),
				ES_UNICO_EN_OPERACION = NVL(P_ES_UNICO_EN_OPERACION, TYPE_TCA_CONDICION.ES_UNICO_EN_OPERACION)
			WHERE ID = P_ID_TCA_CONDICION;			
			
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

	ELSIF (P_ACCION = 3)	-- Deshabilita el registro de TCA_CONDICION
    THEN
		IF  (P_ID_TCA_CONDICION > 0) THEN
			UPDATE TCA_CONDICION
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_TCA_CONDICION;
			
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
	
	ELSIF (P_ACCION = 4) 	-- Habilita el registro de TCA_CONDICION
    THEN
		IF  (P_ID_TCA_CONDICION > 0) THEN
			UPDATE TCA_CONDICION
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_TCA_CONDICION;
			
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
	
	ELSIF (P_ACCION = 5) 	-- Listar los tipos de Condición (TCA_TIPO_CONDICION)

    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT ID, DESCRIPCION, BAN_ESTATUS FROM TCA_TIPO_CONDICION ORDER BY 1			
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_TCA_TIPO_CONDICION = ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP; 
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR LOS TIPOS DE CONDICION (SUBTIPO)';		
	
	ELSIF (P_ACCION = 6) 	-- Listar las Condiciones de acuerdo al tipo de Condición
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		IF (P_ID_TCA_TIPO_CONDICION > 0) THEN
			
			SELECT  ID || ' - ' || DESCRIPCION INTO v_nombreCONDICION FROM TCA_TIPO_CONDICION WHERE ID = P_ID_TCA_TIPO_CONDICION;
			DBMS_OUTPUT.PUT_LINE ('Nombre del tipo de condición (Subtipo): '); 
			DBMS_OUTPUT.PUT_LINE (v_nombreCONDICION);
			DBMS_OUTPUT.PUT_LINE ('......................................................................');
			
			FOR v_rec IN (
				SELECT ID, DESCRIPCION, DESCRIPCION_CORTA, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, ID_CONDICION_PRECARGA, 
				COD_EXTERNO, ES_UNICO_EN_OPERACION, BAN_ESTATUS				
				FROM TCA_CONDICION
				WHERE ID_TCA_TIPO_CONDICION = P_ID_TCA_TIPO_CONDICION
				ORDER BY 1
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID_TCA_CONDICION = ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', DESCRIPCION_CORTA = ' || v_rec.DESCRIPCION_CORTA ||
				', ES_EDITABLE_EN_FORMALIZACION = ' || v_rec.ES_EDITABLE_EN_FORMALIZACION || ', REQUIERE_VALIDAR_COFI = ' || v_rec.REQUIERE_VALIDAR_COFI || ', SE_PUEDE_DISPENSAR = ' || v_rec.SE_PUEDE_DISPENSAR ||
				', ES_PLANTILLA = ' || v_rec.ES_PLANTILLA  || ', COD_EXTERNO = ' || v_rec.COD_EXTERNO || 
				', ES_UNICO_EN_OPERACION = ' || v_rec.ES_UNICO_EN_OPERACION || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
			END LOOP; 
		
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;
		
		v_accion := 'LISTAR EL CATÁLOGO DE CONDICIONES DE ACUERDO AL TIPO DE CONDICION (SUBTIPO)';		
		
	ELSIF (P_ACCION = 7) 		-- Listar la configuración por Condición (TCO_ATRIBUTO_TCC)

    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF (P_ID_TCA_CONDICION > 0) THEN 
		
			SELECT  ID || ' - ' || DESCRIPCION || '(' || DESCRIPCION_CORTA || ')' INTO v_nombreCONDICION FROM TCA_CONDICION WHERE ID = P_ID_TCA_CONDICION;
			DBMS_OUTPUT.PUT_LINE ('Nombre de la condición: '); 
			DBMS_OUTPUT.PUT_LINE (v_nombreCONDICION);
			DBMS_OUTPUT.PUT_LINE ('......................................................................');
			
			FOR v_rec IN (
				SELECT ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, TIPO_VALOR, BAN_ESTATUS 
				FROM TCO_ATRIBUTO_TCC 
				WHERE ID_TCA_CONDICION = P_ID_TCA_CONDICION
				ORDER BY 1
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID_TCO_ATRIBUTO_TCC = ' || v_rec.ID || ', COLUMNA = ' || v_rec.COLUMNA || ', ORDENAMIENTO = ' || v_rec.ORDENAMIENTO || ', SOLO_LECTURA = ' || v_rec.SOLO_LECTURA || ', ES_OBLIGATORIO = ' || v_rec.ES_OBLIGATORIO || ', ETIQUETA = ' || v_rec.ETIQUETA || ', TIPO_VALOR = ' || v_rec.TIPO_VALOR || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);	
			END LOOP; 
		
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;
		v_accion := 'LISTAR LA CONFIGURACIÓN POR CONDICION';		
	
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
				'SP_CONF_TCA_CONDICION',
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
                'SP_CONF_TCA_CONDICION',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        
	
/
