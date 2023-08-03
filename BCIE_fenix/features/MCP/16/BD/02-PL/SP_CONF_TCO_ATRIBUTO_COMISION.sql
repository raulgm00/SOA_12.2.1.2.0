-- ##--------------------------------------------------------------------------------------------##
-- Descripci�n: Script que permite agregar, modificar, desactivar/activar la configuraci�n de los atributos de la  Comisi�n, as� mismo, permite listar la informaci�n de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar los tipos de Comisi�n (TCA_TIPO_ COMISION)
6 - Listar las Comisiones de acuerdo al tipo de Comisi�n (TCA_ COMISION)
7 � Listar la configuraci�n por Comisi�n (TCO_ATRIBUTO_TCC)

*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_TCO_ATRIBUTO_COMISION (
	P_ACCION               IN NUMBER   := 0,			 -- Indica el tipo de acci�n a ejecutar    
	P_COLUMNA              IN VARCHAR2 := NULL,			 -- Nombre de la columna, de la tabla de Comisi�n (COMISION)
	P_ORDENAMIENTO         IN NUMBER   := NULL,			 -- Orden de aparici�n del atributo.  
	P_SOLO_LECTURA         IN NUMBER   := NULL,     	 -- Bandera que indica si el atributo es de s�lo lectura (1- S�, 0 - No)
	P_ES_OBLIGATORIO       IN NUMBER   := NULL,			 -- Bandera que indica si el atributo es obligatorio (1- S�, 0 - No) 
	P_ETIQUETA         	   IN VARCHAR2 := NULL,			 -- Descripci�n de la etiqueta, correspondiente al atributo. 
	-- P_ID_TCA_TERMINO    IN NUMBER   := NULL,	
	-- P_ID_TCA_CONDICION  IN NUMBER   := NULL,
    P_ID_TCA_COMISION  	   IN NUMBER   := NULL,         -- Id. del cat�logo de Comisi�n (TCA_COMISION)
	P_TIPO_VALOR       	   IN VARCHAR2 := NULL,			-- Tipo de atributo (campo)
	P_ID_TCO_ATRIBUTO_TCC  IN NUMBER   := NULL,			--  Id. del  registro de la entidad (TCO_ATRIBUTO_TCC)
	P_ID_TCA_TIPO_COMISION IN NUMBER   := NULL,			-- Id. del registro de comisi�n. (TCA_ COMISION) 
	P_CODIGO_RES       	   OUT NUMBER					-- Indica el c�digo de respuesta de la transacci�n. (0 - Incorrecto, 1 - Correcto).

	)
AS	
	err_num     			NUMBER;
	err_msg     			VARCHAR2 (255);
	v_ID_TCO_ATRIBUTO_TCC   NUMBER;   
	
	TYPE_TCO_ATRIBUTO_TCC TCO_ATRIBUTO_TCC%ROWTYPE;	
	
	v_nombreCOMISION		VARCHAR2(256);
	v_accion				VARCHAR2 (250) := 'NO IDENTIFICADA';
	v_Error_msg	 	        VARCHAR2(100) := '';
	
BEGIN
	P_CODIGO_RES := 0;
	
	IF (P_ACCION = 1)	-- Inserta datos en TCO_ATRIBUTO_TCC
	THEN
		SELECT MAX (ID) INTO v_ID_TCO_ATRIBUTO_TCC FROM TCO_ATRIBUTO_TCC;

		v_ID_TCO_ATRIBUTO_TCC := v_ID_TCO_ATRIBUTO_TCC + 1;

		IF  (P_ID_TCA_COMISION > 0) THEN
			INSERT INTO TCO_ATRIBUTO_TCC (ID,
								COLUMNA,
								ORDENAMIENTO,
								SOLO_LECTURA, 
								ES_OBLIGATORIO,
								ETIQUETA,
								ID_TCA_TERMINO,
								ID_TCA_CONDICION,
								ID_TCA_COMISION,
								TIPO_VALOR,
								BAN_ESTATUS)
			VALUES (v_ID_TCO_ATRIBUTO_TCC,
				P_COLUMNA,
				P_ORDENAMIENTO,
				P_SOLO_LECTURA,
				P_ES_OBLIGATORIO,
				P_ETIQUETA,
				NULL,	-- P_ID_TCA_TERMINO,
				NULL,	-- P_ID_TCA_CONDICION,
				P_ID_TCA_COMISION,
				P_TIPO_VALOR,
				1
			);

			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;			
				COMMIT;
			ELSE	
				ROLLBACK;				
			END IF;			            
		ELSE
			v_Error_msg := 'No se ha indicado el Id. del registro del cat�logo de Comisi�n (TCA_COMISION).';
		END IF;
		
		v_accion := 'AGREGAR';       
		
	ELSIF (P_ACCION = 2)	-- Modificar datos de TCO_ATRIBUTO_TCC
	THEN
		IF  (P_ID_TCO_ATRIBUTO_TCC > 0) THEN
			SELECT * INTO TYPE_TCO_ATRIBUTO_TCC FROM TCO_ATRIBUTO_TCC
			WHERE ID = P_ID_TCO_ATRIBUTO_TCC;
			
			UPDATE TCO_ATRIBUTO_TCC SET
				COLUMNA = NVL(P_COLUMNA, TYPE_TCO_ATRIBUTO_TCC.COLUMNA),
				ORDENAMIENTO = NVL(P_ORDENAMIENTO, TYPE_TCO_ATRIBUTO_TCC.ORDENAMIENTO),
				SOLO_LECTURA = NVL(P_SOLO_LECTURA, TYPE_TCO_ATRIBUTO_TCC.SOLO_LECTURA),
				ES_OBLIGATORIO = NVL(P_ES_OBLIGATORIO, TYPE_TCO_ATRIBUTO_TCC.ES_OBLIGATORIO),
				ETIQUETA = NVL(P_ETIQUETA, TYPE_TCO_ATRIBUTO_TCC.ETIQUETA),
				-- ID_TCA_TERMINO = NVL(P_ID_TCA_TERMINO, TYPE_TCO_ATRIBUTO_TCC.ID_TCA_TERMINO),
				-- ID_TCA_CONDICION = NVL(P_ID_TCA_CONDICION, TYPE_TCO_ATRIBUTO_TCC.ID_TCA_CONDICION),
				-- ID_TCA_COMISION = NVL(P_ID_TCA_COMISION, TYPE_TCO_ATRIBUTO_TCC.ID_TCA_COMISION),
				TIPO_VALOR = NVL(P_TIPO_VALOR, TYPE_TCO_ATRIBUTO_TCC.TIPO_VALOR)
			WHERE ID = P_ID_TCO_ATRIBUTO_TCC;	
			
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

	ELSIF (P_ACCION = 3)	-- Deshabilita el registro de TCO_ATRIBUTO_TCC 
    THEN
		IF  (P_ID_TCO_ATRIBUTO_TCC > 0) THEN
			UPDATE TCO_ATRIBUTO_TCC
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_TCO_ATRIBUTO_TCC;
			
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
	
	ELSIF (P_ACCION = 4) 	-- Habilita el registro de TCO_ATRIBUTO_TCC 
    THEN
		IF  (P_ID_TCO_ATRIBUTO_TCC > 0) THEN
			UPDATE TCO_ATRIBUTO_TCC
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_TCO_ATRIBUTO_TCC;
			
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
	
	ELSIF (P_ACCION = 5) 	 -- Listar los tipos de Comisi�n (TCA_TIPO_ COMISION)
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT ID, DESCRIPCION, BAN_ESTATUS FROM TCA_TIPO_COMISION ORDER BY 1			
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_TCA_TIPO_COMISION = ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP; 
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR LOS TIPOS DE COMISION (SUBTIPO)';		
	
	ELSIF (P_ACCION = 6) 	-- Listar las Comisiones de acuerdo al tipo de Comisi�n.
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		IF (P_ID_TCA_TIPO_COMISION > 0) THEN
			
			SELECT  ID || ' - ' || DESCRIPCION INTO v_nombreCOMISION FROM TCA_TIPO_COMISION WHERE ID = P_ID_TCA_TIPO_COMISION;
			DBMS_OUTPUT.PUT_LINE ('Nombre del tipo de comisi�n (Subtipo): '); 
			DBMS_OUTPUT.PUT_LINE (v_nombreCOMISION);
			DBMS_OUTPUT.PUT_LINE ('......................................................................');
			
			FOR v_rec IN (
				SELECT ID, DESCRIPCION, DESCRIPCION_CORTA, ES_EDITABLE_EN_FORMALIZACION, REQUIERE_VALIDAR_COFI, SE_PUEDE_DISPENSAR, ES_PLANTILLA, 
				SE_REGISTRA_FLEXCUBE, COD_EXTERNO, ES_UNICO_EN_OPERACION, BAN_ESTATUS				
				FROM TCA_COMISION
				WHERE ID_TCA_TIPO_COMISION = P_ID_TCA_TIPO_COMISION
				ORDER BY 1
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID_TCA_COMISION = ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', DESCRIPCION_CORTA = ' || v_rec.DESCRIPCION_CORTA ||
				', ES_EDITABLE_EN_FORMALIZACION = ' || v_rec.ES_EDITABLE_EN_FORMALIZACION || ', REQUIERE_VALIDAR_COFI = ' || v_rec.REQUIERE_VALIDAR_COFI || ', SE_PUEDE_DISPENSAR = ' || v_rec.SE_PUEDE_DISPENSAR ||
				', ES_PLANTILLA = ' || v_rec.ES_PLANTILLA || ', SE_REGISTRA_FLEXCUBE = ' || v_rec.SE_REGISTRA_FLEXCUBE || ', COD_EXTERNO = ' || v_rec.COD_EXTERNO || 
				', ES_UNICO_EN_OPERACION = ' || v_rec.ES_UNICO_EN_OPERACION || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
			END LOOP; 
		
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;
		v_accion := 'LISTAR LAS COMISIONES DE ACUERDO AL TIPO DE COMISION (SUBTIPO)';		
		
	ELSIF (P_ACCION = 7) 	--  Listar la configuraci�n por Comisi�n (TCO_ATRIBUTO_TCC)
    THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF (P_ID_TCA_COMISION > 0) THEN 
		
			SELECT  ID || ' - ' || DESCRIPCION || '(' || DESCRIPCION_CORTA || ')' INTO v_nombreCOMISION FROM TCA_COMISION WHERE ID = P_ID_TCA_COMISION;
			DBMS_OUTPUT.PUT_LINE ('Nombre de la comisi�n: '); 
			DBMS_OUTPUT.PUT_LINE (v_nombreCOMISION);
			DBMS_OUTPUT.PUT_LINE ('......................................................................');
			
			FOR v_rec IN (
				SELECT ID, COLUMNA, ORDENAMIENTO, SOLO_LECTURA, ES_OBLIGATORIO, ETIQUETA, TIPO_VALOR, BAN_ESTATUS 
				FROM TCO_ATRIBUTO_TCC 
				WHERE ID_TCA_COMISION = P_ID_TCA_COMISION
				ORDER BY 1
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID_TCO_ATRIBUTO_TCC = ' || v_rec.ID || ', COLUMNA = ' || v_rec.COLUMNA || ', ORDENAMIENTO = ' || v_rec.ORDENAMIENTO || ', SOLO_LECTURA = ' || v_rec.SOLO_LECTURA || ', ES_OBLIGATORIO = ' || v_rec.ES_OBLIGATORIO || ', ETIQUETA = ' || v_rec.ETIQUETA || ', TIPO_VALOR = ' || v_rec.TIPO_VALOR || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);	
			END LOOP; 
		
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
		END IF;
		v_accion := 'LISTAR LA CONFIGURACI�N POR COMISI�N';		
	
	END IF;


    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    IF (P_CODIGO_RES = 1)
    THEN		
		DBMS_OUTPUT.PUT_LINE ('La transacci�n: ' || P_ACCION || ' (' || v_accion || ') se realiz� correctamente.');
	ELSE
		DBMS_OUTPUT.PUT_LINE ('La transacci�n: ' || P_ACCION || ' (' || v_accion || '), no se realiz� correctamente. ' || v_Error_msg);
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
				'SP_CONF_TCO_ATRIBUTO_COMISION',
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
                'SP_CONF_TCO_ATRIBUTO_COMISION',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        
	
/
