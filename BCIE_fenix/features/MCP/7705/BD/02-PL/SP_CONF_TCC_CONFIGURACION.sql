-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar,  modificar, desactivar/activar la configuración de la configuración de TCC producto, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar la configuración de TCC por producto (TRE_TCC_CONFIGURACION)

*/
-- ##--------------------------------------------------------------------------------------------##


CREATE OR REPLACE PROCEDURE SP_CONF_TCC_CONFIGURACION(
	P_ACCION                        IN NUMBER   := 0,		-- Indica el tipo de acción a ejecutar
	P_ID_TRE_TCC_CONFIGURACION      IN NUMBER   := NULL,	-- Identificador del registro del catálogo de configuración de TCC producto (TRE_TCC_CONFIGURACION)
	P_ID_CAT_PRODUCTO				IN NUMBER   := NULL,	-- Identificador del Producto (CAT_PRODUCTO)
	P_ID_CAT_TIPO_GARANTIA			IN NUMBER   := NULL,	-- Identificador del Tipo Garantía (CAT_TIPO_GARANTIA)
	P_ID_CAT_SECTOR_INST			IN NUMBER   := NULL,	-- Identificador del Sector Institucional  (CAT_SECTOR_ INSTITUCIONAL)
	P_ID_TCA_PROCESO_BPM			IN NUMBER   := NULL,	-- Identificador del Proceso BPM (TCA_PROCESO_BPM)
	P_ES_MANDATORIO					IN NUMBER   := NULL,	-- Bandera que indica si es Mandatorio (ES_MANDATORIO)  (1= Es mandatorio (M), 0 = Es sugerido (S))
	P_ES_TERMINO					IN NUMBER   := 0,		-- Bandera que indica si se va a agregar un TCC de tipo término  (1 = Sí, 0 = No)
	P_ES_CONDICION					IN NUMBER   := 0,		-- Bandera que indica si se va a agregar un TCC de tipo condición  (1 = Sí, 0 = No)
	P_ES_COMISION					IN NUMBER   := 0,		-- Bandera que indica si se va a agregar un TCC de tipo comisión  (1 = Sí, 0 = No)
	P_ID_TCC						IN NUMBER   := NULL,	-- Identificador del Término(TCA_TERMINO), Condición(TCA_CONDICION) ó Comisión (TCA_COMISION) a agregar
	
	/*
	P_ID_TCA_TERMINO				IN NUMBER   := NULL,
	P_ID_TCA_CONDICION				IN NUMBER   := NULL,
	P_ID_TCA_COMISION				IN NUMBER   := NULL,
	*/
	
	P_CODIGO_RES                    OUT NUMBER)				-- Indica el código de respuesta de la transacción.  (0 - Incorrecto, 1 - Correcto, 2 - Sin datos en banderas).
AS
	err_num		NUMBER;
	err_msg		VARCHAR2 (255);
	v_accion	VARCHAR2 (250) := 'NO IDENTIFICADA';
	v_id_TCC    NUMBER;
	v_sqlCadena VARCHAR2 (4000);
	v_Error_msg	VARCHAR2 (4000) := '';
	
	TYPE_TRE_TCC_CONFIGURACION TRE_TCC_CONFIGURACION%ROWTYPE;
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)		-- Se agrega una nueva configuración de TCC Producto
	THEN
		SELECT MAX (ID) INTO v_id_TCC FROM TRE_TCC_CONFIGURACION;
		v_id_TCC := v_id_TCC + 1;
        
		v_sqlCadena := 'INSERT INTO TRE_TCC_CONFIGURACION (ID, 
								ID_CAT_PRODUCTO,
								ID_CAT_TIPO_GARANTIA,
								ID_CAT_SECTOR_INST,								
								ID_TCA_PROCESO_BPM,
								ES_MANDATORIO, ';
								
		-- VALIDA LAS BANDERAS DE TCC, PARA DETERMINAR SI SE INGRESA UN  TERMINO, CONDICION O COMISION
		IF (P_ES_TERMINO = 1)  -- Bandera que indica si se trata de un Id de termino a agregar
		THEN 
			v_sqlCadena := v_sqlCadena || 'ID_TCA_TERMINO';
		ELSE
			IF (P_ES_CONDICION = 1)  -- Bandera que indica si se trata de un Id de condición a agregar
			THEN 
				v_sqlCadena := v_sqlCadena || 'ID_TCA_CONDICION';
			ELSE				
				IF (P_ES_COMISION = 1)  -- Bandera que indica si se trata de un Id de comisión a agregar
				THEN 				
					v_sqlCadena := v_sqlCadena || 'ID_TCA_COMISION';				
				ELSE
					v_Error_msg := 'Favor de activar la bandera (P_ES_TERMINO, P_ES_CONDICION ó P_ES_COMISION) que indica si se va a agregar un TCC de TERMINO, CONDICION ó COMISION.';					
					P_CODIGO_RES := 2;
				END IF;		-- Fin del if (P_ES_COMISION)
			END IF;		-- Fin del if (P_ES_CONDICION)
		END IF;		-- Fin del if (P_ES_TERMINO)
		
		-- Continua con el flujo de la inserción
		IF (P_CODIGO_RES <> 2) AND (P_ID_TCC IS NOT NULL)  -- Bandera que indica que puede seguir el flujo de inserción
		THEN 		
			v_sqlCadena := v_sqlCadena || ') ' ||
							'VALUES (' || v_id_TCC || ', '||
										P_ID_CAT_PRODUCTO || ', '||
										P_ID_CAT_TIPO_GARANTIA || ', '||
										P_ID_CAT_SECTOR_INST || ', '|| 
										P_ID_TCA_PROCESO_BPM || ', '||
										P_ES_MANDATORIO || ', '||
										P_ID_TCC || ')';

            EXECUTE IMMEDIATE v_sqlCadena;
		
			P_CODIGO_RES := 1;		
			DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');
			DBMS_OUTPUT.PUT_LINE ('Valor de cadena insertar: ' || v_sqlCadena);
			DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');
			
		ELSE  --CHR(10) ||
			v_Error_msg := v_Error_msg  ||  CHR(13) || 'Favor de indicar el identificador del TCC de TERMINO, CONDICION ó COMISION a agregar.';
		END IF;
		
        v_accion := 'AGREGAR';
		
	ELSIF (P_ACCION = 2)	-- Se modifica una nueva configuración de TCC Producto
	THEN
		IF  (P_ID_TRE_TCC_CONFIGURACION > 0) THEN
			SELECT * INTO TYPE_TRE_TCC_CONFIGURACION FROM TRE_TCC_CONFIGURACION
			WHERE ID = P_ID_TRE_TCC_CONFIGURACION;
            
			v_sqlCadena :=  'UPDATE TRE_TCC_CONFIGURACION SET ' ||
				'ID_CAT_PRODUCTO = ' || NVL (P_ID_CAT_PRODUCTO, TYPE_TRE_TCC_CONFIGURACION.ID_CAT_PRODUCTO) || ', ' || 
				'ID_CAT_TIPO_GARANTIA = ' || NVL (P_ID_CAT_TIPO_GARANTIA, TYPE_TRE_TCC_CONFIGURACION.ID_CAT_TIPO_GARANTIA) || ', ' || 
				'ID_CAT_SECTOR_INST = ' || NVL (P_ID_CAT_SECTOR_INST, TYPE_TRE_TCC_CONFIGURACION.ID_CAT_SECTOR_INST) || ', ' || 
				'ID_TCA_PROCESO_BPM = ' || NVL (P_ID_TCA_PROCESO_BPM, TYPE_TRE_TCC_CONFIGURACION.ID_TCA_PROCESO_BPM) || ', ' || 
				'ES_MANDATORIO = ' || NVL (P_ES_MANDATORIO, TYPE_TRE_TCC_CONFIGURACION.ES_MANDATORIO);
			
			-- VALIDA LAS BANDERAS DE TCC, PARA DETERMINAR SI SE INGRESA UN  TERMINO, CONDICION O COMISION
			IF (P_ES_TERMINO = 1)  -- Bandera que indica si se trata de un Id de termino a modificar
			THEN 
				v_sqlCadena := v_sqlCadena || ', ID_TCA_TERMINO = ' || NVL (P_ID_TCC, TYPE_TRE_TCC_CONFIGURACION.ID_TCA_TERMINO);
			ELSE
				IF (P_ES_CONDICION = 1)  -- Bandera que indica si se trata de un Id de condición a modificar
				THEN 
					v_sqlCadena := v_sqlCadena || ', ID_TCA_CONDICION = ' || NVL (P_ID_TCC, TYPE_TRE_TCC_CONFIGURACION.ID_TCA_CONDICION);
				ELSE				
					IF (P_ES_COMISION = 1)  -- Bandera que indica si se trata de un Id de comisión a modificar
					THEN 				
						v_sqlCadena := v_sqlCadena || ', ID_TCA_COMISION = ' || NVL (P_ID_TCC, TYPE_TRE_TCC_CONFIGURACION.ID_TCA_COMISION);				
					ELSE
						v_Error_msg := 'Favor de activar la bandera (P_ES_TERMINO, P_ES_CONDICION ó P_ES_COMISION) que indica si se va a modificar un TCC de TERMINO, CONDICION ó COMISION.';
						P_CODIGO_RES := 2;
					END IF;		-- Fin del if (P_ES_COMISION)
				END IF;		-- Fin del if (P_ES_CONDICION)
			END IF;		-- Fin del if (P_ES_TERMINO)
			
			v_sqlCadena := v_sqlCadena || ' WHERE ID = ' || P_ID_TRE_TCC_CONFIGURACION;

			EXECUTE IMMEDIATE v_sqlCadena;
		
			DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');
			DBMS_OUTPUT.PUT_LINE ('Valor de cadena modificar: ' || v_sqlCadena);
			DBMS_OUTPUT.PUT_LINE ('-------------------------------------------- ');
		
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
		
        v_accion := 'MODIFICAR';              
		
    ELSIF (P_ACCION = 3) 	--  Deshabilita los datos de la configuración de TCC Producto
    THEN
		IF  (P_ID_TRE_TCC_CONFIGURACION > 0) THEN
			UPDATE TRE_TCC_CONFIGURACION
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_TRE_TCC_CONFIGURACION;		
			
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
	
	ELSIF (P_ACCION = 4) 	--  Habilita los datos de la configuración de TCC Producto
    THEN
		IF  (P_ID_TRE_TCC_CONFIGURACION > 0) THEN
			UPDATE TRE_TCC_CONFIGURACION
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_TRE_TCC_CONFIGURACION;		
			
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
	
	ELSIF (P_ACCION = 5)	--  Listar la configuración de TCC por producto (TRE_TCC_CONFIGURACION)
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF (P_ID_CAT_PRODUCTO > 0) THEN 
		
			FOR v_rec IN (
				SELECT TCC.ID, CTG.DESCRIPCION AS TIPO_GARANTIA, CSI.DESCRIPCION AS SECTOR_INSTITUCIONAL,  CP.ID AS ID_PRODUCTO,  CP.DESCRIPCION AS PRODUCTO, 
				TPBPM.DESCRIPCION AS PROCESO, TCOM.DESCRIPCION || ' - ' || TCOM.DESCRIPCION_CORTA AS COMISION,
				TCOND.DESCRIPCION || ' - ' || TCOND.DESCRIPCION_CORTA AS CONDICION,
				TTERM.DESCRIPCION || ' - ' || TTERM.DESCRIPCION_CORTA AS TERMINO,
				TCC.ES_MANDATORIO, TCC.BAN_ESTATUS 
				FROM  TRE_TCC_CONFIGURACION TCC
				INNER JOIN CAT_TIPO_GARANTIA CTG ON  TCC.ID_CAT_TIPO_GARANTIA = CTG.ID
				INNER JOIN CAT_PRODUCTO CP ON TCC.ID_CAT_PRODUCTO = CP.ID
				INNER JOIN CAT_SECTOR_INSTITUCIONAL CSI ON  TCC.ID_CAT_SECTOR_INST = CSI.ID
				INNER JOIN TCA_PROCESO_BPM TPBPM ON TCC.ID_TCA_PROCESO_BPM = TPBPM.ID
				LEFT JOIN TCA_COMISION TCOM ON TCC.ID_TCA_COMISION = TCOM.ID
				LEFT JOIN	TCA_CONDICION TCOND  ON TCC.ID_TCA_CONDICION = TCOND.ID 		
				LEFT JOIN TCA_TERMINO TTERM ON TCC.ID_TCA_TERMINO = TTERM.ID
				WHERE CP.ID = P_ID_CAT_PRODUCTO
				ORDER BY TCC.ID
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID_CONFIGURACION_PROD = ' || v_rec.ID || ', TIPO_GARANTIA = ' || v_rec.TIPO_GARANTIA || ', SECTOR_INSTITUCIONAL = ' || v_rec.SECTOR_INSTITUCIONAL || ', ID_PRODUCTO = '  || v_rec.ID_PRODUCTO || ', PRODUCTO = ' || v_rec.PRODUCTO || ', PROCESO = ' || v_rec.PROCESO || ', COMISION = ' || v_rec.COMISION || ', TERMINO = ' || v_rec.TERMINO || ', CONDICION = ' || v_rec.CONDICION || ', ES_MANDATORIO = ' || v_rec.ES_MANDATORIO || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
			END LOOP;
		
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a mostrar.';
		END IF;
		
		v_accion := 'LISTAR LA CONFIGURACION TCC POR PRODUCTO';
	
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
				'SP_CONF_TCC_CONFIGURACION',
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
                'SP_CONF_TCC_CONFIGURACION',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        