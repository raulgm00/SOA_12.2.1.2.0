-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración del programa-producto, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar la configuración de producto-programa. (TCA_PRODUCTO_PROGRAMA)
*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_TCA_PRODUCTO_PROGRAMA(
	P_ACCION                                IN NUMBER   := 0,		-- Identificador de la transacción a ejecutar
	P_ID_TCA_PRODUCTO_PROGRAMA				IN NUMBER   := NULL,	-- Identificador del registro del catálogo del producto-programa (TCA_PRODUCTO_PROGRAMA)
	P_ID_PRODUCTO_OPERACION                 IN NUMBER   := NULL,	-- Identificador del producto de la operación (CAT_PRODUCTO)
	P_VALOR_SECTOR_INSTITUCIONAL 		    IN NUMBER   := NULL,	-- Identificador del sector institucional (CAT_SECTOR_INSTITUCIONAL)
	P_VALOR_TIPO_TASA                       IN NUMBER   := NULL,	-- Identificador del tipo de tasa (TCA_TIPO_TASA_DESEMBOLSO)
	P_ES_REVOLVENTE                         IN NUMBER   := NULL,	-- Bandera que indica si es o no revolvente (1 - Sí, 0 - No)
	P_CODIGO_PROGRAMA                       IN VARCHAR2 := NULL,	-- Código de programa
	P_ES_DEFAULT                            IN NUMBER   := NULL, 	-- Bandera que indica si es un valor por default o no (1 - Sí, 0 - No) 
	P_TODOS_VALORES_SECTOR_INS    			IN NUMBER   := 0,		-- Bandera que indica que se deben considerar todos los valores posibles para el sector institucional.  (1 - Sí, 0 - No)
	P_TODOS_VALORES_TIPO_TASA          		IN NUMBER   := 0,		-- Bandera que indica que se deben considerar todos los valores posibles para el tipo de tasa.  (1 - Sí, 0 - No)
	P_TODOS_VALORES_REVOLVENTE       		IN NUMBER   := 0,		-- Bandera que indica que se deben considerar todos los valores posibles para indicar si es o no revolvente.  (1 - Sí, 0 - No)
	P_CODIGO_RES                            OUT NUMBER)				-- Indica el código de respuesta de la transacción.  (0 - Incorrecto, 1 - Correcto).

AS
	err_num		NUMBER;
	err_msg		VARCHAR2 (255);
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA';
	v_Error_msg	VARCHAR2(100) := '';
	TYPE_TCA_PROD_PROG TCA_PRODUCTO_PROGRAMA%ROWTYPE;
	v_ID_TCA_PROD_PROG 	NUMBER; 
    STR_CADENA  VARCHAR2 (4000);
    type array_revolvente is varray(2) of number;
    v_array_revolvente array_revolvente := array_revolvente(0,1);
    
    CURSOR c_SectorIns IS 
		SELECT ID, DESCRIPCION FROM CAT_SECTOR_INSTITUCIONAL WHERE  ID IN (1, 2) AND BAN_ESTATUS = 1 ORDER BY 1;
	
    CURSOR c_TipoTasa IS 	
        SELECT ID, DESCRIPCION FROM TCA_TIPO_TASA_DESEMBOLSO WHERE  ID IN (2, 3) AND BAN_ESTATUS = 1 ORDER BY 1;
BEGIN
	P_CODIGO_RES := 0;
	
    IF (P_ACCION = 1)	-- Se agrega una nueva configuración para el producto-programa
	THEN
		IF (P_TODOS_VALORES_SECTOR_INS = 1 )	-- Realiza un ciclo para insertar todos los valores de sector institucional
		THEN
			 -- Inicia primer cursor
			FOR  var_SectorIns IN c_SectorIns
			LOOP
				IF (P_TODOS_VALORES_TIPO_TASA = 1 )	-- Realiza un ciclo para insertar todos los valores de tipo tasa
				THEN	
					-- Inica segundo cursor
					FOR  var_TipoTasa IN c_TipoTasa
					LOOP
						IF (P_TODOS_VALORES_REVOLVENTE = 1 )	-- Realiza un ciclo para insertar todos los valores de la bandera revolvente
						THEN
							FOR i IN 1 .. v_array_revolvente.COUNT
							LOOP
								SELECT MAX (ID) INTO v_ID_TCA_PROD_PROG FROM TCA_PRODUCTO_PROGRAMA;
								v_ID_TCA_PROD_PROG := v_ID_TCA_PROD_PROG + 1;
		
								STR_CADENA := 'INSERT INTO TCA_PRODUCTO_PROGRAMA ( ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) 
								VALUES (' || v_ID_TCA_PROD_PROG || ', ' || P_ID_PRODUCTO_OPERACION || ', ' || var_SectorIns.ID  || ', ' || v_array_revolvente(i) || ', ' || var_TipoTasa.ID || ', ''' || P_CODIGO_PROGRAMA || ''', ' || P_ES_DEFAULT || ')';
								
								EXECUTE IMMEDIATE STR_CADENA;
								
								DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');								
								DBMS_OUTPUT.PUT_LINE ('lAS TRES BANDERAS OPCIONALES DICEN Q SIP (SECTOR, TIPO TASA, REVOLVENTE) IMPRIME: ' || STR_CADENA );		
								DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');																
								
							END LOOP;	-- Fin del for del tipo v_array_revolvente
							
						ELSE	-- Inserta un valor en específico para la bandera de revolvente (P_ES_REVOLVENTE)
							SELECT MAX (ID) INTO v_ID_TCA_PROD_PROG FROM TCA_PRODUCTO_PROGRAMA;
							v_ID_TCA_PROD_PROG := v_ID_TCA_PROD_PROG + 1;
		
							STR_CADENA := 'INSERT INTO TCA_PRODUCTO_PROGRAMA ( ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) 
							VALUES (' || v_ID_TCA_PROD_PROG || ', ' || P_ID_PRODUCTO_OPERACION || ', ' || var_SectorIns.ID  || ', ' || P_ES_REVOLVENTE || ', ' || var_TipoTasa.ID || ', ''' || P_CODIGO_PROGRAMA || ''', ' || P_ES_DEFAULT || ')';
								
							EXECUTE IMMEDIATE STR_CADENA;
							
							DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');								
							DBMS_OUTPUT.PUT_LINE ('lAS DOS BANDERAS OPCIONALES DICEN Q SIP (SECTOR, TIPO TASA), EXCEPTO REVOLVENTE, IMPRIME: ' || STR_CADENA );
							DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');								
                            
						END IF; -- Fin del if de P_TODOS_VALORES_REVOLVENTE
					
					END LOOP;  -- Fin del for del cursor c_TipoTasa
				
				ELSE	-- Inserta un valor en específico para el tipo de tasa (P_VALOR_TIPO_TASA)
				
					IF (P_TODOS_VALORES_REVOLVENTE = 1 )	-- Realiza un ciclo para insertar todos los valores de la bandera revolvente
					THEN
						FOR i IN 1 .. v_array_revolvente.COUNT
						LOOP
							SELECT MAX (ID) INTO v_ID_TCA_PROD_PROG FROM TCA_PRODUCTO_PROGRAMA;
							v_ID_TCA_PROD_PROG := v_ID_TCA_PROD_PROG + 1;
		
							STR_CADENA := 'INSERT INTO TCA_PRODUCTO_PROGRAMA ( ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) 
							VALUES (' || v_ID_TCA_PROD_PROG || ', ' || P_ID_PRODUCTO_OPERACION || ', ' || var_SectorIns.ID  || ', ' || v_array_revolvente(i) || ', ' || P_VALOR_TIPO_TASA || ', ''' || P_CODIGO_PROGRAMA || ''', ' || P_ES_DEFAULT || ')';
								
							EXECUTE IMMEDIATE STR_CADENA;
								
							DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');								
							DBMS_OUTPUT.PUT_LINE ('SOLO DOS BANDERAS OPCI DICEN Q SIP (SECTOR Y REVOLVENTE), EXCEPTO TASA, IMPRIME: ' || STR_CADENA );		
							DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');																
								
						END LOOP;	-- Fin del for del tipo v_array_revolvente
							
					ELSE	-- Inserta un valor en específico para la bandera de revolvente (P_ES_REVOLVENTE)
						SELECT MAX (ID) INTO v_ID_TCA_PROD_PROG FROM TCA_PRODUCTO_PROGRAMA;
						v_ID_TCA_PROD_PROG := v_ID_TCA_PROD_PROG + 1;
		
						STR_CADENA := 'INSERT INTO TCA_PRODUCTO_PROGRAMA ( ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) 
						VALUES (' || v_ID_TCA_PROD_PROG || ', ' || P_ID_PRODUCTO_OPERACION || ', ' || var_SectorIns.ID  || ', ' || P_ES_REVOLVENTE || ', ' || P_VALOR_TIPO_TASA || ', ''' || P_CODIGO_PROGRAMA || ''', ' || P_ES_DEFAULT || ')';
								
						EXECUTE IMMEDIATE STR_CADENA;
							
						DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');								
						DBMS_OUTPUT.PUT_LINE ('SOLO UNA BANDERAS OPCI DICEN Q SIP (SECTOR), EXCEPTO TASA Y REVOLVENTE, IMPRIME: ' || STR_CADENA );		
						DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');	
							
					END IF; -- Fin del if de P_TODOS_VALORES_REVOLVENTE
				
				END IF;	-- Fin del if de P_TODOS_VALORES_TIPO_TASA
			
			END LOOP;  -- Fin del for del cursor c_SectorIns
							
		ELSE	-- Inserta un valor en específico para el sector institucional		
			--- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			IF (P_TODOS_VALORES_TIPO_TASA = 1 )	-- Realiza un ciclo para insertar todos los valores de tipo tasa
			THEN	
				-- Inica segundo cursor
				FOR  var_TipoTasa IN c_TipoTasa
				LOOP				
					IF (P_TODOS_VALORES_REVOLVENTE = 1 )	-- Realiza un ciclo para insertar todos los valores de la bandera revolvente
					THEN
						FOR i IN 1 .. v_array_revolvente.COUNT
						LOOP
							SELECT MAX (ID) INTO v_ID_TCA_PROD_PROG FROM TCA_PRODUCTO_PROGRAMA;
							v_ID_TCA_PROD_PROG := v_ID_TCA_PROD_PROG + 1;
		
							STR_CADENA := 'INSERT INTO TCA_PRODUCTO_PROGRAMA ( ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) 
							VALUES (' || v_ID_TCA_PROD_PROG || ', ' || P_ID_PRODUCTO_OPERACION || ', ' || P_VALOR_SECTOR_INSTITUCIONAL  || ', ' || v_array_revolvente(i) || ', ' || var_TipoTasa.ID || ', ''' || P_CODIGO_PROGRAMA || ''', ' || P_ES_DEFAULT || ')';
							
							EXECUTE IMMEDIATE STR_CADENA;
							
							DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');								
							DBMS_OUTPUT.PUT_LINE ('lAS DOS BANDERAS OPCIONALES DICEN Q SIP (TIPO TASA, REVOLVENTE) EXCEPTO SECTOR IMPRIME: ' || STR_CADENA );	
							DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');																
							
						END LOOP;	-- Fin del for del tipo v_array_revolvente
						
					ELSE	-- Inserta un valor en específico para la bandera de revolvente (P_ES_REVOLVENTE)
						SELECT MAX (ID) INTO v_ID_TCA_PROD_PROG FROM TCA_PRODUCTO_PROGRAMA;
						v_ID_TCA_PROD_PROG := v_ID_TCA_PROD_PROG + 1;
		
						STR_CADENA := 'INSERT INTO TCA_PRODUCTO_PROGRAMA ( ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) 
						VALUES (' || v_ID_TCA_PROD_PROG || ', ' || P_ID_PRODUCTO_OPERACION || ', ' || P_VALOR_SECTOR_INSTITUCIONAL  || ', ' || P_ES_REVOLVENTE || ', ' || var_TipoTasa.ID || ', ''' || P_CODIGO_PROGRAMA || ''', ' || P_ES_DEFAULT || ')';
							
						EXECUTE IMMEDIATE STR_CADENA;
						
						DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');								
						DBMS_OUTPUT.PUT_LINE ('SOLO UNA  BANDERAS OPCIONALES DICEN Q SIP (TIPO TASA), EXCEPTO SECTOR, REVOLVENTE, IMPRIME: ' || STR_CADENA );	
                        DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');	
						
					END IF; -- Fin del if de P_TODOS_VALORES_REVOLVENTE
				
				END LOOP;  -- Fin del for del cursor c_TipoTasa
			
			ELSE	-- Inserta un valor en específico para el tipo de tasa (P_VALOR_TIPO_TASA)
			
				IF (P_TODOS_VALORES_REVOLVENTE = 1 )	-- Realiza un ciclo para insertar todos los valores de la bandera revolvente
				THEN
					FOR i IN 1 .. v_array_revolvente.COUNT
					LOOP
						SELECT MAX (ID) INTO v_ID_TCA_PROD_PROG FROM TCA_PRODUCTO_PROGRAMA;
						v_ID_TCA_PROD_PROG := v_ID_TCA_PROD_PROG + 1;
		
						STR_CADENA := 'INSERT INTO TCA_PRODUCTO_PROGRAMA ( ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) 
						VALUES (' || v_ID_TCA_PROD_PROG || ', ' || P_ID_PRODUCTO_OPERACION || ', ' || P_VALOR_SECTOR_INSTITUCIONAL || ', ' || v_array_revolvente(i) || ', ' || P_VALOR_TIPO_TASA || ', ''' || P_CODIGO_PROGRAMA || ''', ' || P_ES_DEFAULT || ')';
							
						EXECUTE IMMEDIATE STR_CADENA;
							
						DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');								
						DBMS_OUTPUT.PUT_LINE ('SOLO UNA BANDERAS OPCI DICEN Q SIP ( REVOLVENTE), EXCEPTO SECTOR, TASA, IMPRIME: ' || STR_CADENA );		
						DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');																
							
					END LOOP;	-- Fin del for del tipo v_array_revolvente
						
				ELSE	-- Inserta un valor en específico para la bandera de revolvente (P_ES_REVOLVENTE)
					SELECT MAX (ID) INTO v_ID_TCA_PROD_PROG FROM TCA_PRODUCTO_PROGRAMA;
					v_ID_TCA_PROD_PROG := v_ID_TCA_PROD_PROG + 1;
		
					STR_CADENA := 'INSERT INTO TCA_PRODUCTO_PROGRAMA ( ID, ID_CAT_PRODUCTO, ID_CAT_SECTOR_INSTITUCIONAL, ES_REVOLVENTE, ID_TCA_TIPO_TASA_DESEMBOLSO, CODIGO_PROGRAMA, ES_VALOR_POR_DEFAULT) 
					VALUES (' || v_ID_TCA_PROD_PROG || ', ' || P_ID_PRODUCTO_OPERACION || ', ' || P_VALOR_SECTOR_INSTITUCIONAL || ', ' || P_ES_REVOLVENTE || ', ' || P_VALOR_TIPO_TASA || ', ''' || P_CODIGO_PROGRAMA || ''', ' || P_ES_DEFAULT || ')';
							
					EXECUTE IMMEDIATE STR_CADENA;
						
					DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');								
					DBMS_OUTPUT.PUT_LINE ('TODOS LOS VALORES SON ESPECIFICOS, NINGUNA BANDERA ACTIVA (SECTOR, TASA Y REVOLVENTE), IMPRIME: ' || STR_CADENA );		
					DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');	
						
				END IF; -- Fin del if de P_TODOS_VALORES_REVOLVENTE
				
			END IF;	-- Fin del if de P_TODOS_VALORES_TIPO_TASA				
				
		END IF;	-- Fin del if de P_TODOS_VALORES_SECTOR_INS
			
	
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';
		
	ELSIF (P_ACCION = 2)	-- Se modifican los datos de la configuración para el producto-programa
	THEN
		IF  (P_ID_TCA_PRODUCTO_PROGRAMA > 0) THEN
			SELECT * INTO TYPE_TCA_PROD_PROG FROM TCA_PRODUCTO_PROGRAMA
			WHERE ID = P_ID_TCA_PRODUCTO_PROGRAMA;
			
			UPDATE TCA_PRODUCTO_PROGRAMA SET
				ID_CAT_PRODUCTO = NVL(P_ID_PRODUCTO_OPERACION, TYPE_TCA_PROD_PROG.ID_CAT_PRODUCTO),
				ID_CAT_SECTOR_INSTITUCIONAL = NVL(P_VALOR_SECTOR_INSTITUCIONAL, TYPE_TCA_PROD_PROG.ID_CAT_SECTOR_INSTITUCIONAL), 
				ES_REVOLVENTE = NVL(P_ES_REVOLVENTE, TYPE_TCA_PROD_PROG.ES_REVOLVENTE), 
				ID_TCA_TIPO_TASA_DESEMBOLSO = NVL(P_VALOR_TIPO_TASA, TYPE_TCA_PROD_PROG.ID_TCA_TIPO_TASA_DESEMBOLSO),
				CODIGO_PROGRAMA = NVL(P_CODIGO_PROGRAMA, TYPE_TCA_PROD_PROG.CODIGO_PROGRAMA),
				ES_VALOR_POR_DEFAULT = NVL(P_ES_DEFAULT, TYPE_TCA_PROD_PROG.ES_VALOR_POR_DEFAULT)
			WHERE ID = P_ID_TCA_PRODUCTO_PROGRAMA;			
			
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
		
    ELSIF (P_ACCION = 3)  -- Deshabilita los datos de la configuración para el producto-programa
    THEN
		IF  (P_ID_TCA_PRODUCTO_PROGRAMA > 0) THEN
			UPDATE TCA_PRODUCTO_PROGRAMA
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_TCA_PRODUCTO_PROGRAMA;		
			
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
	
	ELSIF (P_ACCION = 4)  -- Habilita los datos de la configuración para el producto-programa
    THEN
		IF  (P_ID_TCA_PRODUCTO_PROGRAMA > 0) THEN
			UPDATE TCA_PRODUCTO_PROGRAMA
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_TCA_PRODUCTO_PROGRAMA;		
			
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
	
	ELSIF (P_ACCION = 5)		-- Listar datos de la configuración para el producto-programa
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		FOR v_rec IN (
			SELECT TPP.ID, CP.DESCRIPCION AS PRODUCTO, CSI.DESCRIPCION AS SECTOR_INS, TPP.ES_REVOLVENTE, TTD.DESCRIPCION AS TIPO_TASA,  
			TPP.CODIGO_PROGRAMA, TPP.ES_VALOR_POR_DEFAULT, TPP.BAN_ESTATUS, TPP.FECHA_REGISTRO
			FROM TCA_PRODUCTO_PROGRAMA TPP
			INNER JOIN CAT_PRODUCTO CP ON TPP.ID_CAT_PRODUCTO = CP.ID
			INNER JOIN CAT_SECTOR_INSTITUCIONAL CSI ON TPP.ID_CAT_SECTOR_INSTITUCIONAL = CSI.ID
			INNER JOIN TCA_TIPO_TASA_DESEMBOLSO TTD ON TPP.ID_TCA_TIPO_TASA_DESEMBOLSO = TTD.ID
			ORDER BY CP.DESCRIPCION, CSI.ID, TPP.ES_REVOLVENTE, TTD.ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_TCA_PRODUCTO_PROGRAMA = ' || v_rec.ID || ', PRODUCTO DE LA OPERACIÓN = ' || v_rec.PRODUCTO || ', SECTOR INSTITUCIONAL = ' || v_rec.SECTOR_INS || ', REVOLVENTE = ' || v_rec.ES_REVOLVENTE || ', TIPO DE TASA = ' || v_rec.TIPO_TASA || ', PROGRAMA = ' || v_rec.CODIGO_PROGRAMA || ', DEFAULT = ' || v_rec.ES_VALOR_POR_DEFAULT || ',  BAN_ESTATUS = ' || v_rec.BAN_ESTATUS || ', FECHA REGISTRO = ' || v_rec.FECHA_REGISTRO);			
		END LOOP;
		
		P_CODIGO_RES := 1;
		v_accion := 'LISTAR LA CONFIGURACIÓN PARA EL PRODUCTO-PROGRAMA';
	
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
				'SP_CONF_TCA_PRODUCTO_PROGRAMA',
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
                'SP_CONF_TCA_PRODUCTO_PROGRAMA',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        