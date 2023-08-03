-- ##--------------------------------------------------------------------------------------------##
-- Descripción:   Script que permite agregar, desactivar/activar la configuración de las preguntas por producto, así mismo, permite listar la información de las preguntas por producto.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
3 - Desactivar
4 - Activar
5 - Listar las preguntas por producto (PREGUNTA_PRODUCTO)

*/
-- ##--------------------------------------------------------------------------------------------##


CREATE OR REPLACE PROCEDURE SP_CONF_PREGUNTA_PRODUCTO(
	P_ACCION                IN NUMBER := 0,  	 -- Indica el tipo de acción a ejecutar
	P_ID_PREG_PROD			IN NUMBER := 0,		 -- Id de relación entre pregunta y producto. (PREGUNTA_PRODUCTO)
	P_ID_CAT_PREGUNTA 		IN NUMBER := 0,		 -- Id del catálogo de pregunta  (CAT_PREGUNTAS)
	P_ID_CAT_PRODUCTO 		IN NUMBER := 0,		 --  Id del catálogo de producto (CAT_PRODUCTO)
	P_CODIGO_RES            OUT NUMBER			 -- Indica el código de respuesta de la transacción.
	)
AS
	err_num		NUMBER;
	err_msg		VARCHAR2 (255);
	v_accion	VARCHAR2(250) := 'NO IDENTIFICADA';
	v_codeBlock	VARCHAR2(250);    
	v_ID_PREGUNTA_PRODUCTO NUMBER; 
	v_Error_msg VARCHAR2(100) := ''; 
   
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)	-- Agrega un registro a la tbl PREGUNTA_PRODUCTO
	THEN
		SELECT MAX (ID) INTO v_ID_PREGUNTA_PRODUCTO FROM PREGUNTA_PRODUCTO;
		v_ID_PREGUNTA_PRODUCTO := v_ID_PREGUNTA_PRODUCTO + 1;
		
        INSERT INTO PREGUNTA_PRODUCTO(ID,
									ID_PREGUNTA,
                                    ID_PRODUCTO,
									FEC_CREACION,
                                    BAN_ESTATUS)
		VALUES(v_ID_PREGUNTA_PRODUCTO,
			P_ID_CAT_PREGUNTA,
            P_ID_CAT_PRODUCTO,
			SYSDATE,
            1);
        COMMIT;
		
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';                                               
		
    ELSIF (P_ACCION = 3) 		--  Desactiva (Deshabilita) el campo BAN_ESTATUS de un registro específico de la tbl PREGUNTA_PRODUCTO
    THEN
		IF  (P_ID_PREG_PROD > 0) THEN
			UPDATE PREGUNTA_PRODUCTO
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_PREG_PROD;	

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
	   
		v_accion := 'DESACTIVAR';
	
	ELSIF (P_ACCION = 4) 		 --  Activa (Habilita) el campo BAN_ESTATUS de un registro específico de la tbl PREGUNTA_PRODUCTO
    THEN
		IF  (P_ID_PREG_PROD > 0) THEN
			UPDATE PREGUNTA_PRODUCTO
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_PREG_PROD;		
			
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
	   
		v_accion := 'ACTIVAR';
	
	ELSIF (P_ACCION = 5)
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		IF  (P_ID_CAT_PRODUCTO > 0) THEN		
			FOR v_rec IN (				
				SELECT PREGPROD.ID AS IDPREG, CATPROD.ID AS ID_PRODUCTO,  CATPROD.DESCRIPCION AS PRODUCTO, 
				CATPRI.ID AS ID_PRINCIPIO,  CATPRI.ID  || '. ' ||CATPRI.DESCRIPCION AS PRINCIPIO,
				CATCRI.ID AS ID_CRITERIO, 
				CATPRI.ID || '.' || CATCRI.ID || '. ' || CATCRI.DESCRIPCION AS CRITERIO, 
				CATPREG.NUM_ORDEN, CATPREG.PREGUNTA, PREGPROD.BAN_ESTATUS AS STATUS
				FROM CAT_PRODUCTO CATPROD
				INNER JOIN PREGUNTA_PRODUCTO PREGPROD ON CATPROD.ID = PREGPROD.ID_PRODUCTO
				INNER JOIN CAT_PREGUNTAS CATPREG ON PREGPROD.ID_PREGUNTA = CATPREG.ID
				INNER JOIN CAT_CRITERIO CATCRI ON CATPREG.ID_CRITERIO = CATCRI.ID
				INNER JOIN CAT_PRINCIPIO CATPRI ON CATCRI.ID_PRINCIPIO = CATPRI.ID
				WHERE CATPROD.ID = P_ID_CAT_PRODUCTO
				ORDER BY CATPRI.ID, CATCRI.ID,CATPREG.NUM_ORDEN
			) LOOP
                
				DBMS_OUTPUT.PUT_LINE ('ID PREGUNTA_PRODUCTO = ' || v_rec.IDPREG || ', ID_PRODUCTO = ' || v_rec.ID_PRODUCTO || ', PRODUCTO = ' ||  v_rec.PRODUCTO || ', PRINCIPIO = ' || v_rec.PRINCIPIO || ', CRITERIO = ' || v_rec.CRITERIO || ', NUM_ORDEN = ' || v_rec.NUM_ORDEN || ', PREGUNTA = ' || v_rec.PREGUNTA || ', STATUS = ' || v_rec.STATUS);
			END LOOP;
			
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			DBMS_OUTPUT.PUT_LINE ('No se ha indicado el identificador del poducto a afectar.');
		END IF;
		
		v_accion := 'LISTAR LAS PREGUNTAS POR PRODUCTO';		
	
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
				'SP_CONF_PREGUNTA_PRODUCTO',
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
                'SP_CONF_PREGUNTA_PRODUCTO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        