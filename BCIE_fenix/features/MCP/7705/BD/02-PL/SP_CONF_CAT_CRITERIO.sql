-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración del  catálogo de critero, así mismo, permite listar la información de dicho catálogo. 
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar los datos del catálogo solicitado (CAT_CRITERIO)
*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_CAT_CRITERIO(
	P_ACCION             IN NUMBER   := 0,			-- Identificador de la transacción a ejecutar
	P_ID_CAT_CRITERIO    IN NUMBER 	 := NULL,		-- Id del catálogo de criterio. (CAT_CRITERIO)
	P_ID_PRINCIPIO       IN NUMBER	 := NULL,		-- Id del catálogo de principio. (CAT_PRINCIPIO)
	P_DESCRIPCION_CORTA  IN VARCHAR2 := NULL,		-- Descripción corta del elemento del catálogo.
	P_DESCRIPCION        IN VARCHAR2 := NULL,		-- Descripción del elemento del catálogo.
    P_COD_EXTERNO        IN VARCHAR2 := NULL,		-- Identificador del código externo.
	P_CODIGO_RES         OUT NUMBER)				-- Código de respuesta de la transacción
AS
   
	err_num      NUMBER;
	err_msg      VARCHAR2(255);
	v_accion     VARCHAR2(250) := 'NO IDENTIFICADA';
	TYPE_CAT_CRITERIO  CAT_CRITERIO%ROWTYPE;
	v_ID 		 NUMBER; 
	v_Error_msg	 VARCHAR2(100) := ''; 
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1) 		-- Agrega un nuevo criterio al catálogo
	THEN
		SELECT MAX (ID) INTO v_ID FROM CAT_CRITERIO;
		v_ID := v_ID + 1;
		
        INSERT INTO CAT_CRITERIO(ID,
                            ID_PRINCIPIO,
                            DESCRIPCION_CORTA,
                            DESCRIPCION,
                            BAN_ESTATUS,
                            FECHA_REGISTRO,
                            COD_EXTERNO)                             
		VALUES(v_ID,
            P_ID_PRINCIPIO,
            P_DESCRIPCION_CORTA,
            P_DESCRIPCION,
            1,
			CURRENT_DATE,
            NVL(P_COD_EXTERNO,'0'));
        COMMIT;
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';
        
	ELSIF (P_ACCION = 2)	-- Se modifican los datos del registro de criterio
    THEN
		IF (P_ID_CAT_CRITERIO > 0) THEN
			SELECT * INTO TYPE_CAT_CRITERIO FROM  CAT_CRITERIO WHERE ID = P_ID_CAT_CRITERIO;
    
			UPDATE CAT_CRITERIO  SET
				ID_PRINCIPIO = NVL(P_ID_PRINCIPIO , TYPE_CAT_CRITERIO.ID_PRINCIPIO),
                DESCRIPCION_CORTA= NVL(P_DESCRIPCION_CORTA , TYPE_CAT_CRITERIO.DESCRIPCION_CORTA),
                DESCRIPCION =  NVL(P_DESCRIPCION ,TYPE_CAT_CRITERIO.DESCRIPCION),
                COD_EXTERNO = NVL(P_COD_EXTERNO , TYPE_CAT_CRITERIO.COD_EXTERNO)
			WHERE ID = P_ID_CAT_CRITERIO;      			
      
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
		
    ELSIF(P_ACCION = 3)		-- Deshabilita el registro del criterio
    THEN
		IF (P_ID_CAT_CRITERIO > 0) THEN
			UPDATE CAT_CRITERIO
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_CAT_CRITERIO;
			
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
		
	ELSIF(P_ACCION = 4)		-- Habilita el registro del criterio
    THEN
		IF (P_ID_CAT_CRITERIO > 0) THEN
			UPDATE CAT_CRITERIO
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_CAT_CRITERIO;
			
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
		
	ELSIF (P_ACCION = 5)   -- Lista los criterios por principio
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF (P_ID_PRINCIPIO IS NOT NULL)	THEN 			
			FOR v_rec IN (
				SELECT T1.ID, T1.ID_PRINCIPIO, T2.DESCRIPCION AS DESC_PRINCIPIO, T1.DESCRIPCION_CORTA, T1.DESCRIPCION, T1.BAN_ESTATUS
				FROM CAT_CRITERIO T1
				INNER JOIN CAT_PRINCIPIO T2 ON T1.ID_PRINCIPIO = T2.ID
				WHERE T1.ID_PRINCIPIO = P_ID_PRINCIPIO
				ORDER BY T1.ID


			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID PRINCIPIO = ' || v_rec.ID_PRINCIPIO || ', PRINCIPIO = ' || v_rec.DESC_PRINCIPIO || ', ID CRITERIO = '  || v_rec.ID || ', DESCRIPCION (CRITERIO) = ' || v_rec.DESCRIPCION || ', DESCRIPCION CORTA (CRITERIO)  = ' || v_rec.DESCRIPCION_CORTA ||  ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
			END LOOP;
			P_CODIGO_RES := 1;	
		ELSE
			v_Error_msg := 'No se ha indicado el identificador del principio.';			
		END IF;	
		
		v_accion := 'LISTAR CRITERIOS POR PRINCIPIO';		
		
	ELSIF (P_ACCION = 6)		-- Lista todos los criterios
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		FOR v_rec IN (
			SELECT T1.ID, T1.ID_PRINCIPIO, T2.DESCRIPCION AS DESC_PRINCIPIO, T1.DESCRIPCION_CORTA, T1.DESCRIPCION, T1.BAN_ESTATUS
			FROM CAT_CRITERIO T1
			INNER JOIN CAT_PRINCIPIO T2 ON T1.ID_PRINCIPIO = T2.ID			
			ORDER BY T1.ID
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID PRINCIPIO = ' || v_rec.ID_PRINCIPIO || ', PRINCIPIO = ' || v_rec.DESC_PRINCIPIO || ', ID CRITERIO = '  || v_rec.ID || ', DESCRIPCION (CRITERIO) = ' || v_rec.DESCRIPCION || ', DESCRIPCION CORTA (CRITERIO)  = ' || v_rec.DESCRIPCION_CORTA ||  ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
		END LOOP;
		
		P_CODIGO_RES := 1;					
		v_accion := 'LISTAR TODOS LOS CRITERIOS';		
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
				'SP_CONF_CAT_CRITERIO',
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
                'SP_CONF_CAT_CRITERIO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        