-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración del  tipo de documento, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar los tipos de documento (TCA_DOCUMENTO)
6 - Listar la  configuración onBase por tipo de documento
7 - Listar los permisos del tipo de documento por tarea BPM
8 - Agregar configuración onbase de tipo de documento por país. 
*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE FENIX.SP_CONF_DOCUMENTO (
	P_ACCION              IN NUMBER   := 0,			-- Identificador de la transacción a ejecutar
	P_DESCRIPCION         IN VARCHAR2 := NULL,		-- Descripción del tipo de documento		
	P_CATEGORIA_DOCTO     IN NUMBER   := 0,		    -- Categoría del tipo de documento que puede ser por CLIENTE (1) u OPERACION (2)
	P_ID_TCA_DOCUMENTO    IN NUMBER   := NULL,		-- Identificador del tipo de documento
	P_ID_TIPO_ON_BASE     IN NUMBER   := NULL,		-- Identificador del tipo OnBase (ITEMTYPENUM), que cambia de acuerdo al ambiente (DEV, QA, prePROD, PROD) en donde se ingrese el tipo de documento
	P_ID_CAT_PAISES		  IN NUMBER   := NULL,		-- Identificador del tipo de país
	P_CODIGO_RES          OUT NUMBER)				-- Código de respuesta de la transacción
AS   
	err_num     			NUMBER;
	err_msg     			VARCHAR2(255);
	v_accion				VARCHAR2(250) := 'NO IDENTIFICADA';
	v_codeBlock				VARCHAR2(250);
	v_ID_TCA_DOCUMENTO      NUMBER;
	v_nombreDOCTO			VARCHAR2(256) := NULL;
	v_Error_msg             VARCHAR2(100) := ''; 
		
BEGIN
	P_CODIGO_RES := 0;
	SAVEPOINT punto_INICIO;
	
	IF (P_ACCION = 1)  -- Agrega un nuevo tipo de documento, la configuración OnBase y la asignación de permisos de sólo lectura del nuevo tipo de docto a todas las tareas
	THEN
		SELECT MAX (ID) INTO v_ID_TCA_DOCUMENTO FROM TCA_DOCUMENTO;
				
		v_ID_TCA_DOCUMENTO := v_ID_TCA_DOCUMENTO + 1;
		
		-- 1) Insertar el tipo de documento en el catálogo de documentos:  TCA_DOCUMENTO
		INSERT INTO TCA_DOCUMENTO (ID,
								DESCRIPCION,
                                BAN_ESTATUS,
                                FECHA_REGISTRO,
                                DESCRIPCION_CORTA,
                                COD_EXTERNO)
		VALUES (v_ID_TCA_DOCUMENTO,
				P_DESCRIPCION,
                1, 
				CURRENT_DATE, 
				SUBSTR(P_DESCRIPCION,1,60),
                NULL);

		-- COMMIT;
		IF SQL%FOUND THEN
			P_CODIGO_RES := 1;
		END IF;

		-- 2) Insertar el tipo de documento en la tabla de configuración de onBase: TCO_CONFIGURAR_TIPO_ONBASE
		FOR ind IN (SELECT ID FROM CAT_PAISES WHERE BAN_ESTATUS = 1 ORDER BY 1)
		LOOP
			IF P_CATEGORIA_DOCTO =  2 --  Verifica el tipo de categoría del docto 2 = 'OPERACION'
			THEN
				-- INSERTA VALORES EN TCO_CONFIGURAR_TIPO_ONBASE PARA TIPOS DE DOCUMENTOS POR OPERACION Y TIPO PROYECTO (PRY) 
				INSERT INTO TCO_CONFIGURAR_TIPO_ONBASE (ID,
													ID_TCA_DOCUMENTO,
                                                    ID_CAT_PAISES,
                                                    ID_TCA_TIPO_OPERACION,
                                                    ID_TIPO_ON_BASE,
                                                    ID_GRUPO_ON_BASE,
                                                    DESCRIPCION,
                                                    FECHA_REGISTRO,
                                                    BAN_STATUS)
				VALUES (TCO_CONFIGURAR_TIPO_ONBASE_SEQ.NEXTVAL,
					v_ID_TCA_DOCUMENTO,
					ind.ID,
                    1,  --1 - Proyecto PRY     y     2 - Intermediación  LGC
					P_ID_TIPO_ON_BASE,
                    327,
					'APP_DOCUMENTO_OPERACION_TYPE',
                    CURRENT_DATE,
                    1);
				-- COMMIT;
			
				-- INSERTA VALORES EN TCO_CONFIGURAR_TIPO_ONBASE PARA TIPOS DE DOCUMENTOS POR OPERACION Y TIPO INTERMEDIACIÓN (LGC)
				INSERT INTO TCO_CONFIGURAR_TIPO_ONBASE (ID,
													ID_TCA_DOCUMENTO,
                                                    ID_CAT_PAISES,
                                                    ID_TCA_TIPO_OPERACION,
                                                    ID_TIPO_ON_BASE,
                                                    ID_GRUPO_ON_BASE,
                                                    DESCRIPCION,
                                                    FECHA_REGISTRO,
                                                    BAN_STATUS)
				VALUES (TCO_CONFIGURAR_TIPO_ONBASE_SEQ.NEXTVAL,
					v_ID_TCA_DOCUMENTO,
					ind.ID,
                    2,  --1 - Proyecto PRY     y     2 - Intermediación  LGC
					P_ID_TIPO_ON_BASE,
                    327,
					'APP_DOCUMENTO_OPERACION_TYPE',
                    CURRENT_DATE,
                    1);
				-- COMMIT;
				
			ELSIF P_CATEGORIA_DOCTO = 1  --  Verifica el tipo de categoría del docto 1 = 'CLIENTE' 	
            THEN
				-- INSERTA VALORES EN TCO_CONFIGURAR_TIPO_ONBASE PARA TIPOS DE DOCUMENTOS POR CLIENTE
				INSERT INTO TCO_CONFIGURAR_TIPO_ONBASE (ID,
													ID_TCA_DOCUMENTO,
                                                    ID_CAT_PAISES,
                                                    ID_TIPO_ON_BASE,
                                                    ID_GRUPO_ON_BASE,
                                                    DESCRIPCION,
                                                    FECHA_REGISTRO,
                                                    BAN_STATUS)
                VALUES (TCO_CONFIGURAR_TIPO_ONBASE_SEQ.NEXTVAL,
					v_ID_TCA_DOCUMENTO,
					ind.ID,
                    P_ID_TIPO_ON_BASE,
					326,
                    'APP_DOCUMENTO_CLIENTE_TYPE',
					CURRENT_DATE,
					1);
				-- COMMIT;
			ELSE
				P_CODIGO_RES := 0;
								
			END IF;
			
			EXIT WHEN P_CODIGO_RES = 0;
		END LOOP;
		
		--- Inserta los permisos de lectura para el nuevo documento	
		IF P_CODIGO_RES = 1 THEN
			BEGIN
				P_REGE_TCO_DOCUMENTO_TAREA;
			END;
		END IF;
		
        v_accion := 'AGREGAR';                                               
		
	ELSIF (P_ACCION = 2)   -- Modifica la descripción del tipo de documento
	THEN
		IF (P_ID_TCA_DOCUMENTO > 0) THEN
			UPDATE TCA_DOCUMENTO
			SET DESCRIPCION = P_DESCRIPCION
			WHERE ID = P_ID_TCA_DOCUMENTO;
			-- COMMIT;
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;
			END IF;			
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';
			
		END IF;
		
        v_accion := 'MODIFICAR';
	
	ELSIF (P_ACCION = 3)   -- Deshabilita el tipo de documento del catálogo de documento
	THEN
		IF (P_ID_TCA_DOCUMENTO > 0) THEN
			UPDATE TCA_DOCUMENTO
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_TCA_DOCUMENTO;
			-- COMMIT;
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;
			END IF;			
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
		v_accion := 'DESACTIVAR';
	
	ELSIF (P_ACCION = 4)   -- Activa el tipo de documento del catálogo de documento
	THEN
		IF (P_ID_TCA_DOCUMENTO > 0) THEN
			UPDATE TCA_DOCUMENTO
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_TCA_DOCUMENTO;
			-- COMMIT;
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;
			END IF;			
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar.';			
		END IF;
		v_accion := 'ACTIVAR';
		
	ELSIF (P_ACCION = 5)	-- Lista los tipos de documento
	THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT ID, DESCRIPCION, BAN_ESTATUS
			FROM TCA_DOCUMENTO
			WHERE ID >999 OR ID=0
			ORDER BY ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_TIPO_DOCUMENTO= ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR TIPO DE DOCUMENTOS';		
		
	ELSIF (P_ACCION = 6)
	THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		IF (P_ID_TCA_DOCUMENTO > 0) THEN
		
			SELECT  ID || ' - ' || DESCRIPCION INTO v_nombreDOCTO FROM TCA_DOCUMENTO WHERE ID = P_ID_TCA_DOCUMENTO;
			DBMS_OUTPUT.PUT_LINE ('Configuración onBase para el tipo de documento: '); 
			DBMS_OUTPUT.PUT_LINE (v_nombreDOCTO);
			DBMS_OUTPUT.PUT_LINE ('......................................................................');
			
			FOR v_rec IN (
				SELECT  CATPA.DESCRIPCION AS PAIS, TCAOP.DESCRIPCION AS TIPO_OPER,  TCOOB.ID_TIPO_ON_BASE, TCOOB.ID_GRUPO_ON_BASE, TCOOB.DESCRIPCION, TCOOB.BAN_STATUS
				FROM TCO_CONFIGURAR_TIPO_ONBASE TCOOB
				INNER JOIN CAT_PAISES CATPA ON TCOOB.ID_CAT_PAISES = CATPA.ID
				LEFT JOIN TCA_TIPO_OPERACION TCAOP ON TCOOB.ID_TCA_TIPO_OPERACION = TCAOP.ID
				WHERE  TCOOB.ID_TCA_DOCUMENTO = P_ID_TCA_DOCUMENTO
				ORDER BY 1,2
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('PAIS = ' || v_rec.PAIS || ', TIPO_OPERACION = ' || v_rec.TIPO_OPER || ', ID_TIPO_ON_BASE (ITEMTYPENUM) = ' || v_rec.ID_TIPO_ON_BASE || ', ID_GRUPO_ON_BASE (ITEMTYPEGROUPNUM) = ' || v_rec.ID_GRUPO_ON_BASE || ', CARPETA_ONBASE = ' || v_rec.DESCRIPCION  || ', BAN_STATUS = ' || v_rec.BAN_STATUS);			
			END LOOP;
			
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del tipo de documento.';
		END IF;
		
		v_accion := 'LISTAR LA CONFIGURACION ONBASE POR TIPO DE DOCUMENTO';
		
	ELSIF (P_ACCION = 7)
	THEN
		DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		IF (P_ID_TCA_DOCUMENTO > 0) THEN
		
			SELECT  ID || ' - ' || DESCRIPCION INTO v_nombreDOCTO FROM TCA_DOCUMENTO WHERE ID = P_ID_TCA_DOCUMENTO;
			DBMS_OUTPUT.PUT_LINE ('Lista de permisos para el tipo de documento: '); 
			DBMS_OUTPUT.PUT_LINE (v_nombreDOCTO);
			DBMS_OUTPUT.PUT_LINE ('......................................................................');
		
			FOR v_rec IN (
				SELECT TPBPM.DESCRIPCION AS PROCESO, TTBPM.ID || ' - ' || TTBPM.DESCRIPCION AS TAREA_BPM, TDT.PUEDE_CREAR, TDT.PUEDE_MODIFICAR, TDT.PUEDE_BORRAR, TDT.PUEDE_CONSULTAR, TDT.BAN_ESTATUS  
				FROM TCO_DOCUMENTO_TAREA TDT
				INNER JOIN TCA_TAREA_BPM TTBPM ON TDT.ID_TAREA_BPM = TTBPM.ID
				INNER JOIN TCA_PROCESO_BPM TPBPM ON TTBPM.ID_PROCESO_BPM = TPBPM.ID
				WHERE  TDT.ID_TCA_DOCUMENTO = P_ID_TCA_DOCUMENTO
				ORDER BY 1,2
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('PROCESO = ' || v_rec.PROCESO || ', TAREA_BPM = ' || v_rec.TAREA_BPM || ', PUEDE_CREAR = ' || v_rec.PUEDE_CREAR || ', PUEDE_MODIFICAR = ' || v_rec.PUEDE_MODIFICAR || ', PUEDE_BORRAR = ' || v_rec.PUEDE_BORRAR || ', PUEDE_CONSULTAR = ' || v_rec.PUEDE_CONSULTAR || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
			END LOOP;
		
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del tipo de documento.';
		END IF;
		
		v_accion := 'LISTAR LOS PERMISOS DEL TIPO DE DOCUMENTO POR TAREA';
	
	ELSIF (P_ACCION = 8)  -- Agregar configuración onbase de tipo de documento por país. 
	THEN
		IF P_CATEGORIA_DOCTO =  2 --  Verifica el tipo de categoría del docto 2 = 'OPERACION'
		THEN
			-- INSERTA VALORES EN TCO_CONFIGURAR_TIPO_ONBASE PARA TIPOS DE DOCUMENTOS POR OPERACION Y TIPO PROYECTO (PRY) 
			INSERT INTO TCO_CONFIGURAR_TIPO_ONBASE (ID,
												ID_TCA_DOCUMENTO,
												ID_CAT_PAISES,
												ID_TCA_TIPO_OPERACION,
												ID_TIPO_ON_BASE,
												ID_GRUPO_ON_BASE,
												DESCRIPCION,
												FECHA_REGISTRO,
												BAN_STATUS)
			VALUES (TCO_CONFIGURAR_TIPO_ONBASE_SEQ.NEXTVAL,
				P_ID_TCA_DOCUMENTO,
				P_ID_CAT_PAISES,
				1,  --1 - Proyecto PRY     y     2 - Intermediación  LGC
				P_ID_TIPO_ON_BASE,
				327,
				'APP_DOCUMENTO_OPERACION_TYPE',
				CURRENT_DATE,
				1);
			
			-- COMMIT;
		
			-- INSERTA VALORES EN TCO_CONFIGURAR_TIPO_ONBASE PARA TIPOS DE DOCUMENTOS POR OPERACION Y TIPO INTERMEDIACIÓN (LGC)
			INSERT INTO TCO_CONFIGURAR_TIPO_ONBASE (ID,
												ID_TCA_DOCUMENTO,
												ID_CAT_PAISES,
												ID_TCA_TIPO_OPERACION,
												ID_TIPO_ON_BASE,
												ID_GRUPO_ON_BASE,
												DESCRIPCION,
												FECHA_REGISTRO,
												BAN_STATUS)
			VALUES (TCO_CONFIGURAR_TIPO_ONBASE_SEQ.NEXTVAL,
				P_ID_TCA_DOCUMENTO,
				P_ID_CAT_PAISES,
				2,  --1 - Proyecto PRY     y     2 - Intermediación  LGC
				P_ID_TIPO_ON_BASE,
				327,
				'APP_DOCUMENTO_OPERACION_TYPE',
				CURRENT_DATE,
				1);
			
			-- COMMIT;
			
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;
			END IF;
			
		ELSIF P_CATEGORIA_DOCTO = 1  --  Verifica el tipo de categoría del docto 1 = 'CLIENTE' 	
		THEN
			-- INSERTA VALORES EN TCO_CONFIGURAR_TIPO_ONBASE PARA TIPOS DE DOCUMENTOS POR CLIENTE
			INSERT INTO TCO_CONFIGURAR_TIPO_ONBASE (ID,
												ID_TCA_DOCUMENTO,
												ID_CAT_PAISES,
												ID_TIPO_ON_BASE,
												ID_GRUPO_ON_BASE,
												DESCRIPCION,
												FECHA_REGISTRO,
												BAN_STATUS)
			VALUES (TCO_CONFIGURAR_TIPO_ONBASE_SEQ.NEXTVAL,
				P_ID_TCA_DOCUMENTO,
				P_ID_CAT_PAISES,
				P_ID_TIPO_ON_BASE,
				326,
				'APP_DOCUMENTO_CLIENTE_TYPE',
				CURRENT_DATE,
				1);
			
			-- COMMIT;
			
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;
			END IF;
			
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado la categoría de documento correcto.';
		END IF;
		
		v_accion := 'Agregar configuración de tipo de documento por país';    
			
	END IF;
   
    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    IF (P_CODIGO_RES = 1)
    THEN
		COMMIT;
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || ') se realizó correctamente.');
	ELSE
		ROLLBACK TO punto_INICIO;
		DBMS_OUTPUT.PUT_LINE ('La transacción: ' || P_ACCION || ' (' || v_accion || '), no se realizó correctamente. ' || v_Error_msg);
	END IF;
   
EXCEPTION
	WHEN NO_DATA_FOUND
	THEN
		ROLLBACK TO punto_INICIO;
		
		DBMS_OUTPUT.PUT_LINE ('No existen Datos.');
		DBMS_OUTPUT.PUT_LINE ('Error: ' || SQLCODE || ', ' || SQLERRM);
		err_num := SQLCODE;
		err_msg := SUBSTR(SQLERRM,1,250);
		
		INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
										NOMBRE_INSUMO,
                                        DESCRIPCION_ERROR,
                                        FECHA_REGISTRO)
        VALUES ('SP',
				'SP_CONF_DOCUMENTO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
				SYSDATE);
		COMMIT;
	WHEN OTHERS
	THEN
		ROLLBACK TO punto_INICIO;
		
		DBMS_OUTPUT.PUT_LINE ('Error: ' || SQLCODE || ', ' || SQLERRM);
		err_num := SQLCODE;
		err_msg := SUBSTR(SQLERRM,1,250);	  
    
		INSERT INTO TBI_SEGUIMIENTO_ERROR (TIPO_INSUMO,
										NOMBRE_INSUMO,
                                        DESCRIPCION_ERROR,
                                        FECHA_REGISTRO)
		VALUES ('SP',
                'SP_CONF_DOCUMENTO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        
	
/
