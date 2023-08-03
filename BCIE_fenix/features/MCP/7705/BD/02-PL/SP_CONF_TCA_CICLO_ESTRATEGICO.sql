-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, modificar, desactivar/activar la configuración del  catálogo de ciclo estratégico, así­ mismo, permite listar la información de dicho catálogo.  
/*
Acciones permitidas (P_ACCION):
1 - Agregar
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar los datos del catálogo solicitado (TCA_CICLO_ESTRATEGICO)
*/
-- ##--------------------------------------------------------------------------------------------##


create or replace PROCEDURE SP_CONF_TCA_CICLO_ESTRATEGICO(
   P_ACCION             IN     NUMBER := 0,		-- Indica el tipo de acción a ejecutar.
   P_ID                 IN     NUMBER := 0,		-- Id del registro del catálogo de ciclo estratégico (TCA_CICLO_ESTRATEGICO)
   P_DESCRIPCION        IN     VARCHAR2 := NULL,		-- Descripción del elemento del catálogo.
   P_DESCRIPCION_CORTA  IN     VARCHAR2 := NULL,		-- Descripción corta del elemento del catálogo.
   P_COD_EXTERNO        IN     VARCHAR2 := NULL,		-- Identificador del código externo.
   P_FECHA_INICIO       IN     DATE := CURRENT_DATE,		-- Fecha de inicio del periodo.
   P_FECHA_FIN          IN     DATE := CURRENT_DATE,		-- Fecha de fin del periodo.
   P_CODIGO_RES         OUT NUMBER)			-- Indica el código de respuesta de la transacción.
AS  
   err_num      		NUMBER;
   err_msg      		VARCHAR2 (255);   
   v_accion    			VARCHAR2(250) := 'NO IDENTIFICADA';
   v_Error_msg	 		VARCHAR2(100) := '';
   TYPE_CICLO_ESTRATEGICO  TCA_CICLO_ESTRATEGICO%ROWTYPE;
   v_ID 				NUMBER; 

BEGIN
	P_CODIGO_RES := 0;     
    IF (P_ACCION = 1)		-- Agrega un nuevo ciclo estratégico
    THEN
		SELECT MAX (ID) INTO v_ID FROM TCA_CICLO_ESTRATEGICO;
		v_ID := v_ID + 1;
		INSERT INTO TCA_CICLO_ESTRATEGICO(ID,
                                           DESCRIPCION,
                                           DESCRIPCION_CORTA,
                                           FECHA_REGISTRO,
                                           BAN_ESTATUS,
                                           COD_EXTERNO,
                                           FECHA_INICIO,
                                           FECHA_FIN)
                        VALUES(v_ID,
                               P_DESCRIPCION,
                               P_DESCRIPCION_CORTA,
                               SYSDATE,
                               1,
                               P_COD_EXTERNO,
                               P_FECHA_INICIO,
                               P_FECHA_FIN);
         COMMIT;
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';
    ELSIF (P_ACCION = 2)		-- Se modifican los datos del ciclo estratégico indicado.
    THEN
		IF(P_ID > 0) THEN
			SELECT * INTO TYPE_CICLO_ESTRATEGICO FROM TCA_CICLO_ESTRATEGICO
			WHERE ID = P_ID;
			
			UPDATE TCA_CICLO_ESTRATEGICO  SET
						 DESCRIPCION = NVL(P_DESCRIPCION,TYPE_CICLO_ESTRATEGICO.DESCRIPCION),
						 DESCRIPCION_CORTA = NVL(P_DESCRIPCION_CORTA, TYPE_CICLO_ESTRATEGICO.DESCRIPCION_CORTA),
						 COD_EXTERNO = NVL(P_COD_EXTERNO, TYPE_CICLO_ESTRATEGICO.COD_EXTERNO),
						 FECHA_INICIO = NVL(P_FECHA_INICIO, TYPE_CICLO_ESTRATEGICO.FECHA_INICIO),
						 FECHA_FIN = NVL(P_FECHA_FIN, TYPE_CICLO_ESTRATEGICO.FECHA_FIN)
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
		
    ELSIF(P_ACCION = 3)		  	-- Deshabilita los datos del ciclo estratégico indicado.
    THEN
		IF(P_ID > 0) THEN
			UPDATE TCA_CICLO_ESTRATEGICO SET BAN_ESTATUS = 0  WHERE ID = P_ID;

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
	
	ELSIF(P_ACCION = 4)	  -- Habilita los datos del ciclo estratégico indicado.
	THEN  
		IF (P_ID > 0) THEN            
            UPDATE TCA_CICLO_ESTRATEGICO SET BAN_ESTATUS = 1 WHERE ID = P_ID ;
			
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
	
	ELSIF (P_ACCION = 5)  --  Listar los datos del catálogo solicitado (TCA_CICLO_ESTRATEGICO)
	THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
			FOR v_rec IN (
				SELECT ID, DESCRIPCION, DESCRIPCION_CORTA, FECHA_REGISTRO, BAN_ESTATUS, COD_EXTERNO, FECHA_INICIO, FECHA_FIN
				FROM TCA_CICLO_ESTRATEGICO
				ORDER BY 1
			) LOOP
				DBMS_OUTPUT.PUT_LINE (  'ID CICLO ESTRATEGICO = ' || v_rec.ID ||
										', DESCRIPCION DEL CICLO ESTRATEGICO = ' || v_rec.DESCRIPCION ||
										', DESCRIPCION CORTA DEL CICLO ESTRATEGICO = ' || v_rec.DESCRIPCION_CORTA ||
										', FECHA REGISTRO = ' || v_rec.FECHA_REGISTRO ||
										', BAN ESTATUS = ' || v_rec.BAN_ESTATUS ||
										', COD EXTERNO = ' || v_rec.COD_EXTERNO ||
										', FECHA INICIO = ' || v_rec.FECHA_INICIO ||
										', FECHA FIN = ' || v_rec.FECHA_FIN);			
			END LOOP;
			P_CODIGO_RES := 1;
       
		v_accion := 'LISTAR DATOS DEL CICLO ESTRATEGICO'; 
		
	
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
				'SP_CONF_TCA_CICLO_ESTRATEGICO',
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
                'SP_CONF_TCA_CICLO_ESTRATEGICO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;

END;
