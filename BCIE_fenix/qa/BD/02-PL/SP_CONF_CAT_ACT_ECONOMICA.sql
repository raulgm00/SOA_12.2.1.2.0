-- ##--------------------------------------------------------------------------------------------##
-- Descripci�n: Script que permite agregar, modificar, desactivar/activar la configuraci�n del  cat�logo de actividad econ�mica, as� mismo, permite listar la informaci�n de dicho cat�logo.  
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar datos de la actividad econ�mica por c�digo externo. (CAT_ACTIVIDAD_ECONOMICA)
6 - Listar datos de las actividades econ�micas por actividad econ�mica padre (CAT_ACTIVIDAD_ECONOMICA)
7 - Listar datos de las actividades econ�micas padre (CAT_ACTIVIDAD_ECONOMICA)
*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_CAT_ACT_ECONOMICA(
    P_ACCION            		IN NUMBER := 0,						-- Identificador de la transacci�n a ejecutar
    P_ID                		IN NUMBER := 0,						-- Id del registro del cat�logo de actividad econ�mica. (CAT_ACTIVIDAD_ECONOMICA)
    P_DESCRIPCION       		IN VARCHAR2 := NULL,			   	-- Descripci�n del elemento del cat�logo.
    P_DESCRIPCION_CORTA 		IN VARCHAR2 := NULL,				-- Descripci�n corta del elemento del cat�logo.    				-- 
    P_COD_EXTERNO       		IN VARCHAR2 := NULL,				-- Identificador del c�digo externo
    P_ID_TIPO_OPERACION     	IN NUMBER := 0,						-- Identificador del tipo de operaci�n. (1 = Proyecto, 2 = Intermediaci�n) 
	P_ES_ACTIVIDAD_PADRE 	    IN NUMBER := 0,						-- Bandera que identifica si es una actividad econ�mica padre      
    P_ID_CATALOGO_ACTIVIDAD_PADRE   IN NUMBER := NULL,				-- Identificador del cat�logo de actividad econ�mica padre (CAT_ACTIVIDAD_ECONOMICA)
    P_CODIGO_RES           		OUT NUMBER							-- C�digo de respuesta de la transacci�n
) AS 

    err_num     		NUMBER;
    err_msg     		VARCHAR2(255);
    v_accion    		VARCHAR2(250) := 'NO IDENTIFICADA';    
    v_id_table  		NUMBER;    
	v_Error_msg	 		VARCHAR2(100) := '';
	v_id_Act_Padre		NUMBER := NULL;    
    
    TYPE_CAT_ACTIVIDAD_ECONOMICA CAT_ACTIVIDAD_ECONOMICA%ROWTYPE;

BEGIN

    P_CODIGO_RES := 0;     
    IF(P_ACCION = 1)	-- Agrega una nueva actividad econ�mica
    THEN        
		SELECT MAX(ID) INTO v_id_table FROM CAT_ACTIVIDAD_ECONOMICA ;
        v_id_table := v_id_table + 1;
		
		DBMS_OUTPUT.PUT_LINE ('BANDERA: ' || P_ES_ACTIVIDAD_PADRE);
		DBMS_OUTPUT.PUT_LINE ('ID ACT PADRE: ' || P_ID_CATALOGO_ACTIVIDAD_PADRE);
		DBMS_OUTPUT.PUT_LINE ('P_ID_TIPO_OPERACION: ' || P_ID_TIPO_OPERACION);
		
		
		-- Valida la bandera de si el reg es una actividad econ�mica padre
		IF (P_ES_ACTIVIDAD_PADRE = 1)   -- Si es 1, el campo  ID e ID_CATALOGO_ACTIVIDAD_PADRE, son iguales  		
		THEN
			v_id_Act_Padre := v_id_table;			
		ELSIF (P_ES_ACTIVIDAD_PADRE = 0)  AND (P_ID_CATALOGO_ACTIVIDAD_PADRE IS NOT NULL)  
		THEN
			v_id_Act_Padre := P_ID_CATALOGO_ACTIVIDAD_PADRE;			
		ELSE
			v_Error_msg := 'Favor de ingresar el indicador de la actividad econ�mica padre.';
		END IF;
		
		IF (v_id_Act_Padre IS NOT NULL)  --- INSERTA REGISTRO EN CAT_ACTIVIDAD_ECONOMICA
		THEN			 
			INSERT INTO CAT_ACTIVIDAD_ECONOMICA (ID,
													DESCRIPCION,
													DESCRIPCION_CORTA,
													FECHA_REGISTRO,
													BAN_ESTATUS,
													COD_EXTERNO,
													ID_TIPO_OPERACION,
													ID_CATALOGO_ACTIVIDAD_PADRE)
			VALUES (v_id_table,
						P_DESCRIPCION,
						P_DESCRIPCION_CORTA,
						CURRENT_DATE,
						1,
						P_COD_EXTERNO,
						P_ID_TIPO_OPERACION,
						v_id_Act_Padre);
			
			COMMIT;            
			P_CODIGO_RES := 1;    
		END IF;
		v_accion := 'AGREGAR';    
     
	ELSIF (P_ACCION = 2)	-- Se modifican los datos de la actividad econ�mica indicada.
	THEN    
        IF(P_ID > 0) THEN
            
			SELECT * INTO TYPE_CAT_ACTIVIDAD_ECONOMICA FROM CAT_ACTIVIDAD_ECONOMICA
			WHERE ID = P_ID;
            
            UPDATE CAT_ACTIVIDAD_ECONOMICA SET                    
                    DESCRIPCION = NVL(P_DESCRIPCION,TYPE_CAT_ACTIVIDAD_ECONOMICA.DESCRIPCION),
                    DESCRIPCION_CORTA = NVL(P_DESCRIPCION_CORTA,TYPE_CAT_ACTIVIDAD_ECONOMICA.DESCRIPCION_CORTA),                    
                    COD_EXTERNO = NVL(P_COD_EXTERNO,TYPE_CAT_ACTIVIDAD_ECONOMICA.COD_EXTERNO),
                    ID_TIPO_OPERACION = NVL(P_ID_TIPO_OPERACION,TYPE_CAT_ACTIVIDAD_ECONOMICA.ID_TIPO_OPERACION),
                    ID_CATALOGO_ACTIVIDAD_PADRE = NVL(P_ID_CATALOGO_ACTIVIDAD_PADRE,TYPE_CAT_ACTIVIDAD_ECONOMICA.ID_CATALOGO_ACTIVIDAD_PADRE)
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
    
	ELSIF(P_ACCION = 3)		-- Deshabilita los datos de la actividad econ�mica indicada. 
	THEN  
		IF (P_ID > 0) THEN
			UPDATE CAT_ACTIVIDAD_ECONOMICA SET BAN_ESTATUS = 0 WHERE ID = P_ID ;
            
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
        
	ELSIF(P_ACCION = 4)	  -- Habilita los datos de la actividad econ�mica indicada.  
	THEN  
		IF (P_ID > 0) THEN            
            UPDATE CAT_ACTIVIDAD_ECONOMICA SET BAN_ESTATUS = 1 WHERE ID = P_ID ;
			
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
        
	ELSIF (P_ACCION = 5)  -- Listar datos de la actividad econ�mica por c�digo externo.
	THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF (P_COD_EXTERNO IS NOT NULL)	THEN 		
			FOR v_rec IN (
				SELECT T1.ID, T1.DESCRIPCION, T1.DESCRIPCION_CORTA,
					T1.FECHA_REGISTRO, T1.BAN_ESTATUS, T1.COD_EXTERNO,
					T1.ID_CATALOGO_ACTIVIDAD_PADRE, T1.ID_TIPO_OPERACION,
					CASE T1.ID_TIPO_OPERACION WHEN 1 THEN 'Proyecto'
					WHEN 2 THEN 'Intermediaci�n'
					ELSE 'No definido'
					END AS	DESC_TIPO_OPERACION,
					T2.DESCRIPCION AS DESC_ACT_ECONOMICA_PADRE, T2.COD_EXTERNO AS COD_EXTERNO_PADRE					
					FROM CAT_ACTIVIDAD_ECONOMICA T1
					LEFT JOIN CAT_ACTIVIDAD_ECONOMICA T2 ON T1.ID_CATALOGO_ACTIVIDAD_PADRE = T2.ID
					WHERE T1.COD_EXTERNO LIKE '%' || P_COD_EXTERNO || '%'
					ORDER BY 1
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('COD_EXTERNO PADRE = ' || v_rec.COD_EXTERNO_PADRE ||
										', DESCRIPCION DE LA ACTIVIDAD ECONOMICA PADRE = ' || v_rec.DESC_ACT_ECONOMICA_PADRE ||
										', ID_CAT_ACTIVIDAD_ECONOMICA = ' || v_rec.ID || 
										', DESCRIPCION = ' || v_rec.DESCRIPCION ||
										', DESCRIPCION_CORTA = ' || v_rec.DESCRIPCION_CORTA || 
										', FECHA_REGISTRO = ' || v_rec.FECHA_REGISTRO ||
										', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS ||
										', COD_EXTERNO = ' || v_rec.COD_EXTERNO ||												
										', ID_TIPO_OPERACION = ' || v_rec.ID_TIPO_OPERACION ||										
										', DESCRIPCION TIPO OPERACION = ' || v_rec.DESC_TIPO_OPERACION );
			END LOOP;
			P_CODIGO_RES := 1;
        ELSE
			v_Error_msg := 'No se ha indicado el identificador del c�digo externo';			
		END IF;	
		
		v_accion := 'LISTAR DATOS DE LA ACTIVIDAD_ECONOMICA POR CODIGO EXTERNO'; 
    
	ELSIF (P_ACCION = 6)  -- Listar datos de la actividad econ�mica por actividad econ�mica padre
	THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF (P_COD_EXTERNO IS NOT NULL)	THEN 		-- Se requiere COD_EXTERNO de la actividad econ�mica padre
			FOR v_rec IN (
				SELECT T1.ID, T1.DESCRIPCION, T1.DESCRIPCION_CORTA,
				T1.FECHA_REGISTRO, T1.BAN_ESTATUS, T1.COD_EXTERNO,
				T1.ID_CATALOGO_ACTIVIDAD_PADRE, T1.ID_TIPO_OPERACION,                    
                CASE T1.ID_TIPO_OPERACION WHEN 1 THEN 'Proyecto'
				WHEN 2 THEN 'Intermediaci�n'
				ELSE 'No definido'
				END AS	DESC_TIPO_OPERACION,				
				T2.DESCRIPCION AS DESC_ACT_ECONOMICA_PADRE, T2.COD_EXTERNO AS COD_EXTERNO_PADRE
				FROM CAT_ACTIVIDAD_ECONOMICA T1
                LEFT JOIN CAT_ACTIVIDAD_ECONOMICA T2 ON T1.ID_CATALOGO_ACTIVIDAD_PADRE = T2.ID
				WHERE T2.COD_EXTERNO LIKE '%' || P_COD_EXTERNO || '%'
				ORDER BY 1                    
			) LOOP
				DBMS_OUTPUT.PUT_LINE (  'COD_EXTERNO PADRE = ' || v_rec.COD_EXTERNO_PADRE ||
										', DESCRIPCION DE LA ACTIVIDAD ECONOMICA PADRE = ' || v_rec.DESC_ACT_ECONOMICA_PADRE ||
										', ID_CAT_ACTIVIDAD_ECONOMICA = ' || v_rec.ID || 
										', DESCRIPCION = ' || v_rec.DESCRIPCION ||
										', DESCRIPCION_CORTA = ' || v_rec.DESCRIPCION_CORTA || 
										', FECHA_REGISTRO = ' || v_rec.FECHA_REGISTRO ||
										', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS ||
										', COD_EXTERNO = ' || v_rec.COD_EXTERNO ||
										', ID_TIPO_OPERACION = ' || v_rec.ID_TIPO_OPERACION ||										
										', DESCRIPCION TIPO OPERACION = ' || v_rec.DESC_TIPO_OPERACION );			
			END LOOP;
			P_CODIGO_RES := 1;
        ELSE
			v_Error_msg := 'No se ha indicado el c�digo externo de la actividad econ�mica padre.';			
		END IF;	
		
		v_accion := 'LISTAR DATOS DE LA ACTIVIDAD ECONOMICA POR CODIGO EXTERNO DE LA ACTIVIDAD ECONOMICA PADRE'; 
    
    ELSIF (P_ACCION = 7)		-- Listar datos de las actividades econ�micas padre. 
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		FOR v_rec IN (
			SELECT ID,DESCRIPCION,DESCRIPCION_CORTA,
				FECHA_REGISTRO,BAN_ESTATUS,COD_EXTERNO,
				ID_CATALOGO_ACTIVIDAD_PADRE, ID_TIPO_OPERACION,
				CASE ID_TIPO_OPERACION WHEN 1 THEN 'Proyecto'
				WHEN 2 THEN 'Intermediaci�n'
				ELSE 'No definido'
				END AS	DESC_TIPO_OPERACION				
				FROM CAT_ACTIVIDAD_ECONOMICA
				WHERE ID = ID_CATALOGO_ACTIVIDAD_PADRE
				ORDER BY 1
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID CAT ACTIVIDAD ECONOMICA = ' || v_rec.ID || ',DESCRIPCION = ' || v_rec.DESCRIPCION ||
								', DESCRIPCION_CORTA = ' || v_rec.DESCRIPCION_CORTA || 
								', FECHA_REGISTRO = ' || v_rec.FECHA_REGISTRO ||
								', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS ||
								', COD_EXTERNO = ' || v_rec.COD_EXTERNO ||
								', ID_TIPO_OPERACION = ' || v_rec.ID_TIPO_OPERACION ||										
								', DESCRIPCION TIPO OPERACION = ' || v_rec.DESC_TIPO_OPERACION ||
								', ID_CATALOGO_ACTIVIDAD_PADRE = ' || v_rec.ID_CATALOGO_ACTIVIDAD_PADRE);			
		END LOOP;
		
		P_CODIGO_RES := 1;					
		v_accion := 'LISTAR DATOS DE LA ACTIVIDAD_ECONOMICA PADRE'; 
        
	END IF;
	 

    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    IF (P_CODIGO_RES = 1)
    THEN
		DBMS_OUTPUT.PUT_LINE ('La transacci�n: ' || P_ACCION || ' (' || v_accion || ') se realiz� correctamente.');
	ELSE
		DBMS_OUTPUT.PUT_LINE ('La transacci�n: ' || P_ACCION || ' (' || v_accion || '), no se realiz� correctamente.  ' || v_Error_msg);
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
				'SP_CONF_CAT_ACT_ECONOMICA',
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
                'SP_CONF_CAT_ACT_ECONOMICA',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;

END;
