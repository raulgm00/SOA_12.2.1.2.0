-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración del  catálogo de iniciativa estratégica, así mismo, permite listar la información de dicho catálogo.   
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar los datos del catálogo solicitado (CAT_INICIATIVA_ESTRATEGICA) por código externo.
6 - Listar los datos del catálogo solicitado (CAT_INICIATIVA_ESTRATEGICA) por  código externo de la actividad económica.
*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_CAT_INI_ESTRATEGICA(
   P_ACCION             IN    NUMBER := 0,				-- Identificador de la transacción a ejecutar
   P_ID                 IN    NUMBER := 0,				-- Id. del registro del catálogo de iniciativa estratégica (CAT_INICIATIVA_ESTRATEGICA)						
   P_ID_ACTIVIDAD       IN    NUMBER := NULL,				-- Id. de la actividad económica* (CAT_ACTIVIDAD_ECONOMICA)
   P_DESCRIPCION        IN    VARCHAR2 := NULL,			-- Descripción del elemento
   P_DESCRIPCION_CORTA  IN    VARCHAR2 := NULL,			-- Descripción corta del elemento
   P_COD_EXTERNO        IN    VARCHAR2 := NULL,			-- Código externo  (Identificador de flexcube)
   P_TIPO_VARIABLE      IN    NUMBER := 0,				-- Identificador del tipo de operación. (1 = (A) Aprobación-Operación, 2 = (D) Desembolso)			
   P_CODIGO_RES         OUT   NUMBER)
AS   
   err_num      		NUMBER;
   err_msg      		VARCHAR2 (255);
   v_accion     		VARCHAR2 (250) := 'NO IDENTIFICADA';
   TYPE_INI_ESTRATEGICA  CAT_INICIATIVA_ESTRATEGICA%ROWTYPE;
   v_ID 				NUMBER; 
   v_TipoVariable 		VARCHAR2(10) := NULL;
   v_Error_msg	 		VARCHAR2(100) := '';
   
BEGIN

	P_CODIGO_RES := 0;   

	IF P_TIPO_VARIABLE = 1 THEN
		v_TipoVariable := 'A';  	-- Iniciativa estratégica aplicable a (A = Aprobación (Operación))
	ELSIF P_TIPO_VARIABLE = 2 THEN
		v_TipoVariable := 'D';		-- Iniciativa estratégica aplicable a (D = Desembolso)
	ELSE
		v_TipoVariable := NULL;		
		IF (P_ACCION = 1) OR (P_ACCION = 2)
		THEN
			DBMS_OUTPUT.PUT_LINE ('Se debe ingresar un tipo de variable válido(1 = (A) Aprobación-Operación, 2 = (D) Desembolso)');
		END IF;
	END IF;
		
	IF (P_ACCION = 1)	-- Agrega una nueva iniciativa estratégica
	THEN
		SELECT MAX (ID) INTO v_ID FROM CAT_INICIATIVA_ESTRATEGICA;
		v_ID := v_ID + 1;
		
		INSERT INTO CAT_INICIATIVA_ESTRATEGICA(ID,
                                              ID_ACTIVIDAD,
                                              DESCRIPCION,
                                              DESCRIPCION_CORTA,
                                              FECHA_REGISTRO,
                                              BAN_ESTATUS,
                                              COD_EXTERNO,
                                              TIPO_VARIABLE)
                                VALUES(v_ID,
                                       P_ID_ACTIVIDAD,
                                       P_DESCRIPCION,
                                       P_DESCRIPCION_CORTA,
                                       SYSDATE,
                                       1,
                                       P_COD_EXTERNO,
                                       v_TipoVariable);        
        COMMIT;
		
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';
		
    ELSIF (P_ACCION = 2)	-- Se modifican los datos de la iniciativa estratégica indicada.
	THEN
		IF(P_ID > 0) THEN
			 
			SELECT * INTO TYPE_INI_ESTRATEGICA FROM CAT_INICIATIVA_ESTRATEGICA
			WHERE ID = P_ID;
			
			 UPDATE CAT_INICIATIVA_ESTRATEGICA  SET
						 ID_ACTIVIDAD = NVL(P_ID_ACTIVIDAD, TYPE_INI_ESTRATEGICA.ID_ACTIVIDAD),
						 DESCRIPCION = NVL(P_DESCRIPCION,TYPE_INI_ESTRATEGICA.DESCRIPCION),
						 DESCRIPCION_CORTA = NVL(P_DESCRIPCION_CORTA, TYPE_INI_ESTRATEGICA.DESCRIPCION_CORTA),
						 COD_EXTERNO = NVL(P_COD_EXTERNO, TYPE_INI_ESTRATEGICA.COD_EXTERNO),
						 TIPO_VARIABLE = NVL(v_TipoVariable, TYPE_INI_ESTRATEGICA.TIPO_VARIABLE)
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
			
			
    ELSIF(P_ACCION = 3)  	-- Deshabilita los datos de la iniciativa estratégica indicada.
    THEN
		IF(P_ID > 0) THEN
			UPDATE CAT_INICIATIVA_ESTRATEGICA SET BAN_ESTATUS = 0  WHERE ID = P_ID;

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
	ELSIF(P_ACCION = 4)	  -- Habilita los datos de la iniciativa estratégica indicada.
	THEN  
		IF (P_ID > 0) THEN            
            UPDATE CAT_INICIATIVA_ESTRATEGICA SET BAN_ESTATUS = 1 WHERE ID = P_ID ;
			
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
	
	ELSIF (P_ACCION = 5)  -- Listar datos de la iniciativa estratégica por código externo
	THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF (P_COD_EXTERNO IS NOT NULL)	THEN 		-- Se requiere COD_EXTERNO de la iniciativa estratégica
			FOR v_rec IN (
				SELECT T1.ID AS ID_INI, T1.DESCRIPCION AS DESC_INI,  T1.DESCRIPCION_CORTA AS DESC_CORTA_INI, T1.BAN_ESTATUS, T1.FECHA_REGISTRO, T1.COD_EXTERNO,
				CASE T1.TIPO_VARIABLE WHEN 'A' THEN 'APROBACIÓN-OPERACIÓN' WHEN 'D' THEN 'DESEMBOLSO' ELSE 'INDEFINIDO'  END AS TIPO, 
				T2.DESCRIPCION AS DESC_ACT_ECONO, T2.COD_EXTERNO AS COD_EXT_ACT_ECONO
				FROM CAT_INICIATIVA_ESTRATEGICA T1
				INNER JOIN CAT_ACTIVIDAD_ECONOMICA T2
				ON T1.ID_ACTIVIDAD = T2.ID
				WHERE  T1.COD_EXTERNO LIKE '%' || P_COD_EXTERNO || '%'  ORDER BY 1				
			) LOOP
				DBMS_OUTPUT.PUT_LINE (  'COD_INICIATIVA_ESTRATEGIA = ' || v_rec.ID_INI ||
										', DESCRIPCION DE LA INICIATIVA_ESTRATEGIA = ' || v_rec.DESC_INI ||
										', DESCRIPCION CORTA DE LA INICIATIVA_ESTRATEGIA = ' || v_rec.DESC_CORTA_INI ||
										', FECHA REGISTRO = ' || v_rec.FECHA_REGISTRO ||
										', BAN ESTATUS = ' || v_rec.BAN_ESTATUS ||
										', COD EXTERNO = ' || v_rec.COD_EXTERNO ||
										', TIPO VARIABLE = ' || v_rec.TIPO ||
										', DESCRIPCION DE LA ACTIVIDAD ECONOMICA = ' || v_rec.DESC_ACT_ECONO ||
										', COD EXTERNO DE LA ACTIVIDAD ECONOMICA = ' || v_rec.COD_EXT_ACT_ECONO);			
			END LOOP;
			P_CODIGO_RES := 1;
        ELSE
			v_Error_msg := 'No se ha indicado el COD_EXTERNO de la iniciativa estratégica';			
		END IF;	
		
		v_accion := 'LISTAR DATOS DE LA INICIATIVA ESTRATEGICA POR CODIGO EXTERNO'; 
	
    ELSIF (P_ACCION = 6)  -- Listar datos de la iniciativa estratégica por código externo de la actividad económica
	THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF (P_COD_EXTERNO IS NOT NULL)	THEN 		-- Se requiere COD_EXTERNO de la actividad económica
			FOR v_rec IN (
				SELECT T1.ID AS ID_INI, T1.DESCRIPCION AS DESC_INI,  T1.DESCRIPCION_CORTA AS DESC_CORTA_INI, T1.BAN_ESTATUS, T1.FECHA_REGISTRO, T1.COD_EXTERNO,
				CASE T1.TIPO_VARIABLE WHEN 'A' THEN 'APROBACIÓN-OPERACIÓN' WHEN 'D' THEN 'DESEMBOLSO' ELSE 'INDEFINIDO'  END AS TIPO, 
				T2.DESCRIPCION AS DESC_ACT_ECONO, T2.COD_EXTERNO AS COD_EXT_ACT_ECONO
				FROM CAT_INICIATIVA_ESTRATEGICA T1
				INNER JOIN CAT_ACTIVIDAD_ECONOMICA T2
				ON T1.ID_ACTIVIDAD = T2.ID
				WHERE  T2.COD_EXTERNO LIKE '%' || P_COD_EXTERNO || '%'  ORDER BY 1				
			) LOOP
				DBMS_OUTPUT.PUT_LINE (  'COD_INICIATIVA_ESTRATEGIA = ' || v_rec.ID_INI ||
										', DESCRIPCION DE LA INICIATIVA_ESTRATEGIA = ' || v_rec.DESC_INI ||
										', DESCRIPCION CORTA DE LA INICIATIVA_ESTRATEGIA = ' || v_rec.DESC_CORTA_INI ||
										', FECHA REGISTRO = ' || v_rec.FECHA_REGISTRO ||
										', BAN ESTATUS = ' || v_rec.BAN_ESTATUS ||
										', COD EXTERNO = ' || v_rec.COD_EXTERNO ||
										', TIPO VARIABLE = ' || v_rec.TIPO ||
										', DESCRIPCION DE LA ACTIVIDAD ECONOMICA = ' || v_rec.DESC_ACT_ECONO ||
										', COD EXTERNO DE LA ACTIVIDAD ECONOMICA = ' || v_rec.COD_EXT_ACT_ECONO);			
			END LOOP;
			P_CODIGO_RES := 1;
        ELSE
			v_Error_msg := 'No se ha indicado el COD_EXTERNO de la actividad económica';			
		END IF;	
		
		v_accion := 'LISTAR DATOS DE LA INICIATIVA ESTRATÉGICA POR CODIGO EXTERNO DE LA ACTIVIDAD ECONÓMICA'; 
	
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
				'SP_CONF_CAT_INI_ESTRATEGICA',
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
                'SP_CONF_CAT_INI_ESTRATEGICA',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;

END;
