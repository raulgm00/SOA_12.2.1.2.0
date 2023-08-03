-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración del catálogo simple, así mismo, permite listar la información de dicha tabla.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar datos del catalogo simple (Nombre de la tabla)
*/
-- ##--------------------------------------------------------------------------------------------##


CREATE OR REPLACE PROCEDURE SP_CONF_CAT_SIMPLE(
	P_ACCION            IN NUMBER   := 0,			-- Indica el tipo de acción a ejecutar.
    P_TABLA             IN VARCHAR2 := NULL,		-- Nombre de la tabla a configurar.
    P_ID                IN NUMBER 	:= 0,			-- Id del  registro del catálogo simple.	
    P_DESCRIPCION       IN VARCHAR2 := NULL,		-- Descripción del elemento catálogo simple. 
    P_DESCRIPCION_CORTA IN VARCHAR2 := NULL,		-- Descripción corta del elemento del catálogo simple.
    P_COD_EXTERNO       IN VARCHAR2 := NULL,		-- En algunos casos, se indica el identificador de flexcube.
    P_COD_RES           OUT NUMBER					-- Indica el código de respuesta de la transacción.
)
AS  
    err_num     NUMBER;
    err_msg     VARCHAR2(255);
    v_codeBlock VARCHAR2(1000) := '';
    v_Error_msg VARCHAR2(100) := ''; 
    v_id_table  NUMBER := 0;
    v_tmp 		VARCHAR2(255) := '';
    v_fecha 	DATE := CURRENT_DATE;
    v_tmp_2 	VARCHAR2(255) := '';
	v_tmp_3 	VARCHAR2(255) := '';
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA O SIN TABLA DEFINIDA';
    

BEGIN
    P_COD_RES := 0;
    
    IF (P_ACCION = 1) 
	THEN
		IF (P_TABLA IS NOT NULL)
		THEN
			v_codeBlock := 'SELECT MAX(ID) FROM ' || P_TABLA ;
		
			EXECUTE IMMEDIATE v_codeBlock
			INTO v_id_table; 
				
			v_id_table := v_id_table + 1 ;
			
			
			v_tmp := NVL('''' || P_COD_EXTERNO ||'''','NULL');
			v_tmp_2 := NVL('''' || P_DESCRIPCION ||'''','NULL');
			v_tmp_3 := NVL('''' || P_DESCRIPCION_CORTA ||'''','NULL');

			v_codeBlock := 'INSERT INTO ' || P_TABLA || ' (ID, DESCRIPCION, DESCRIPCION_CORTA,BAN_ESTATUS,FECHA_REGISTRO,COD_EXTERNO) 
					VALUES (' || v_id_table || ',' || v_tmp_2 || ',' || v_tmp_3 || ',' || 1 || ','''
					|| v_fecha || ''', ' ||v_tmp || ')';
		
			--  DBMS_OUTPUT.PUT_LINE ('SQL: ' || v_codeBlock);        
			EXECUTE IMMEDIATE v_codeBlock ;
				   
			COMMIT;                
			P_COD_RES := 1;     		
		ELSE
		    P_COD_RES := 0;
			v_Error_msg := 'No se ha indicado el nombre de la tabla a afectar.';
		END IF;
		
		v_accion := 'AGREGAR';   

    ELSIF (P_ACCION = 2) 
	THEN    
		IF(P_ID > 0)  AND (P_TABLA IS NOT NULL) THEN
        
			v_codeBlock := 'TRUNCATE TABLE AUX_SP_CAT_SIMPLE';
			EXECUTE IMMEDIATE v_codeBlock;
			COMMIT;
        
			v_codeBlock := 'INSERT INTO AUX_SP_CAT_SIMPLE (ID,DESCRIPCION,DESCRIPCION_CORTA,BAN_ESTATUS,FECHA_REGISTRO,COD_EXTERNO)
				SELECT ID,DESCRIPCION,DESCRIPCION_CORTA,BAN_ESTATUS,FECHA_REGISTRO,COD_EXTERNO FROM ' || P_TABLA 
				|| ' WHERE ID = ' || P_ID;
                        
			EXECUTE IMMEDIATE v_codeBlock ;
			COMMIT;  
        
			DECLARE
			v_aux AUX_SP_CAT_SIMPLE%ROWTYPE; 
        
			BEGIN
        
				SELECT * INTO v_aux FROM AUX_SP_CAT_SIMPLE;
                		
                IF (P_COD_EXTERNO IS NULL) THEN
                    v_tmp := NVL('''' || v_aux.COD_EXTERNO || '''', 'NULL');
                ELSE 
                    v_tmp := '''' || P_COD_EXTERNO || ''''; 
                END IF;
                
                IF (P_DESCRIPCION IS NULL) THEN
                    v_tmp_2 := NVL('''' || v_aux.DESCRIPCION || '''', 'NULL');
                ELSE
                    v_tmp_2 := '''' || P_DESCRIPCION || '''';
                END IF;
                
                IF (P_DESCRIPCION_CORTA IS NULL) THEN
                    v_tmp_3 := NVL('''' || v_aux.DESCRIPCION_CORTA || '''', 'NULL');
                ELSE
                    v_tmp_3 := '''' || P_DESCRIPCION_CORTA || '''';
                END IF;
				
				v_codeBlock :=
				'UPDATE ' || P_TABLA || ' SET 
					  DESCRIPCION = ' || v_tmp_2 || ',
					  DESCRIPCION_CORTA = ' || v_tmp_3 || ',
					  COD_EXTERNO = ' || v_tmp  || '
				WHERE ID = ' || P_ID ;
        		
			END;		
			EXECUTE IMMEDIATE v_codeBlock;            
			COMMIT;
            P_COD_RES := 1;
        ELSE
            P_COD_RES := 0;			
			v_Error_msg := 'No se ha indicado el nombre de la tabla o el identificador del registro a afectar.';
        END IF;
        v_accion := 'MODIFICAR';
    

    ELSIF (P_ACCION = 3) 
	THEN
		IF (P_ID > 0)  AND (P_TABLA IS NOT NULL)  THEN
			v_codeBlock := 'UPDATE ' || P_TABLA || ' SET BAN_ESTATUS = 0 WHERE ID = ' || P_ID ;        
			EXECUTE IMMEDIATE v_codeBlock;
			COMMIT;
            P_COD_RES := 1;
        ELSE
			P_COD_RES := 0;			
			v_Error_msg := 'No se ha indicado el nombre de la tabla o el identificador del registro a afectar.';
        END IF;
			v_accion := 'DESACTIVAR';
           
	ELSIF (P_ACCION = 4)
	THEN
		IF (P_ID > 0)  AND (P_TABLA IS NOT NULL) THEN
            v_codeBlock := 'UPDATE ' || P_TABLA || ' SET BAN_ESTATUS = 1 WHERE ID = ' || P_ID ;        
            EXECUTE IMMEDIATE v_codeBlock;            
            COMMIT;
            P_COD_RES := 1;
		ELSE 
            P_COD_RES := 0;
			v_Error_msg := 'No se ha indicado el nombre de la tabla o el identificador del registro a afectar.';
		END IF;

		v_accion := 'ACTIVAR';        

	ELSIF (P_ACCION = 5)
	THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
			-- Se valida que se ingrese el nombre de la tabla
			IF (P_TABLA IS NOT NULL)
			THEN 
				v_codeBlock :=
				'BEGIN
				 FOR v_rec IN (
					SELECT TBL.ID,
					TBL.DESCRIPCION,
					TBL.DESCRIPCION_CORTA,
					TBL.BAN_ESTATUS,
					TBL.FECHA_REGISTRO,
					TBL.COD_EXTERNO 
					FROM ' || P_TABLA || ' TBL
					ORDER BY TBL.ID
				) LOOP
					DBMS_OUTPUT.PUT_LINE (''ID = '' || v_rec.ID || '', DESCRIPCION = '' || v_rec.DESCRIPCION 
					|| '', DESCRIPCION_CORTA = '' || v_rec.DESCRIPCION_CORTA 
					|| '', BAN_ESTATUS = '' || v_rec.BAN_ESTATUS || '', FECHA_REGISTRO = '' || v_rec.FECHA_REGISTRO 
					|| '', COD_EXTERNO = '' || v_rec.COD_EXTERNO);			
				END LOOP;
				END;';
				EXECUTE IMMEDIATE v_codeBlock;
			
				P_COD_RES := 1;
			ELSE
				P_COD_RES := 0;				
				v_Error_msg := 'No se ha indicado el nombre de la tabla a afectar.';
			END IF;
			v_accion := 'LISTAR VALORES TABLA ' || P_TABLA; 

	END IF;

    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
    IF (P_COD_RES = 1)
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
				'SP_CONF_CAT_SIMPLE',
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
                'SP_CONF_CAT_SIMPLE',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
        
END;                        

