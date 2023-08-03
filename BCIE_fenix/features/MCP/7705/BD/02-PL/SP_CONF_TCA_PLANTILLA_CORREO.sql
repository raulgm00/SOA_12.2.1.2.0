-- ##--------------------------------------------------------------------------------------------##
-- Descripción:  Script que permite modificar, desactivar/activar el catálogo de las notificaciones, así mismo, permite listar la información de dicho catálogo. 
/*
Acciones permitidas (P_ACCION):
2 - Modificar
3 - Desactivar
4 - Activar
5 - Listar los datos de una plantilla en específico. (TCA_PLANTILLA_CORREO)

*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_TCA_PLANTILLA_CORREO(	
	P_ACCION                	IN NUMBER  	:= 0,			-- Identificador de la transacción a ejecutar
	P_ID_TCA_PLANTILLA_CORREO   IN NUMBER	:= 0,			-- Id del registro de la notificación (TCA_PLANTILLA_CORREO)
	P_MENSAJE                   IN VARCHAR2 := NULL,		-- Descripción del mensaje en formato HTML.
	P_ASUNTO                    IN VARCHAR2 := NULL,		-- Descripción del asunto del correo de la notificación.
	P_DESTINATARIOS             IN VARCHAR2 := NULL,		-- Lista de correos de los Destinatarios, separados por (;).
	P_CC_DESTINATARIOS          IN VARCHAR2 := NULL,		-- Lista de correos  con copia a los Destinatarios, separados por (;).
	P_BAN_VALIDA_ET             IN NUMBER	:= 0,			-- Bandera que indica si la notificación se enviará al equipo de trabajo.
	P_BAN_VALIDA_MCC            IN NUMBER 	:= 0,			-- Bandera que indica si la notificación se enviará a miembros del comité de crédito.
	P_BAN_VALIDA_VA             IN NUMBER 	:= 0,			-- Bandera que indica si la notificación se enviará a los validadores de acción.
	P_BAN_VALIDA_RA             IN NUMBER 	:= 0,			-- Bandera que indica si la notificación se enviará a los responsables de acción.
	P_BAN_VALIDA_RO  		    IN NUMBER 	:= 0,			-- Bandera que indica si la notificación se enviará a los responsables de operación.
	P_CODIGO_RES            	OUT NUMBER)					-- Indica el código de respuesta de la transacción. (0 - Incorrecto, 1 - Correcto).

AS
	err_num			NUMBER;
	err_msg			VARCHAR2 (255);
	v_accion		VARCHAR2(250) := 'NO IDENTIFICADA';
	v_Error_msg	 	VARCHAR2(250) := '';
	TYPE_TCA_PLANTILLA_CORREO TCA_PLANTILLA_CORREO%ROWTYPE;
   
BEGIN
	P_CODIGO_RES := 0;
	
	-- Solo se podrán modificar las notificaciones que se enviaron en el archivo excel 
	IF (P_ACCION = 2)	
	THEN
		IF  (P_ID_TCA_PLANTILLA_CORREO > 91) THEN
			SELECT * INTO TYPE_TCA_PLANTILLA_CORREO FROM TCA_PLANTILLA_CORREO
			WHERE ID = P_ID_TCA_PLANTILLA_CORREO;
			
			UPDATE TCA_PLANTILLA_CORREO SET												
											MENSAJE = NVL(P_MENSAJE,TYPE_TCA_PLANTILLA_CORREO.MENSAJE),
											ASUNTO = NVL(P_ASUNTO,TYPE_TCA_PLANTILLA_CORREO.ASUNTO),
											DESTINATARIOS = NVL(P_DESTINATARIOS,TYPE_TCA_PLANTILLA_CORREO.DESTINATARIOS),
											CC_DESTINATARIOS = NVL(P_CC_DESTINATARIOS,TYPE_TCA_PLANTILLA_CORREO.CC_DESTINATARIOS),
											BAN_VALIDA_ET = NVL(P_BAN_VALIDA_ET,TYPE_TCA_PLANTILLA_CORREO.BAN_VALIDA_ET),
											BAN_VALIDA_MCC = NVL(P_BAN_VALIDA_MCC,TYPE_TCA_PLANTILLA_CORREO.BAN_VALIDA_MCC),
											BAN_VALIDA_VA = NVL(P_BAN_VALIDA_VA,TYPE_TCA_PLANTILLA_CORREO.BAN_VALIDA_VA),
											BAN_VALIDA_RA = NVL(P_BAN_VALIDA_RA,TYPE_TCA_PLANTILLA_CORREO.BAN_VALIDA_RA),
											BAN_VALIDA_RO = NVL(P_BAN_VALIDA_RO,TYPE_TCA_PLANTILLA_CORREO.BAN_VALIDA_RO)
			WHERE ID = P_ID_TCA_PLANTILLA_CORREO;			
		
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;			
				COMMIT;
			ELSE	
				ROLLBACK;				
			END IF;			            
		ELSE
			v_Error_msg := 'No se ha indicado el identificador del registro a afectar o el identificador de la plantilla no corresponde a la relación de plantillas parametrizables.';			
		END IF;
        v_accion := 'MODIFICAR';              
		
    ELSIF (P_ACCION = 3) -- Deshabilita los datos de la plantilla
    THEN
		IF  (P_ID_TCA_PLANTILLA_CORREO > 0) THEN
			UPDATE TCA_PLANTILLA_CORREO
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_TCA_PLANTILLA_CORREO;		
		
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
	
	ELSIF (P_ACCION = 4) 	-- Habilita los datos de la plantilla
    THEN
		IF  (P_ID_TCA_PLANTILLA_CORREO > 0) THEN
			UPDATE TCA_PLANTILLA_CORREO
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_TCA_PLANTILLA_CORREO;		
		
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
	
	ELSIF (P_ACCION = 5)  -- Listar datos de la plantilla específica
    THEN
	
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF  (P_ID_TCA_PLANTILLA_CORREO > 0) THEN
			FOR v_rec IN (			
				SELECT 
					ID, DESCRIPCION, REMITENTE, MENSAJE, ASUNTO, BAN_ESTATUS, CC_DESTINATARIOS, BAN_VALIDA_ET, BAN_VALIDA_MCC, BAN_VALIDA_VA, BAN_VALIDA_RA, BAN_VALIDA_RO
				FROM TCA_PLANTILLA_CORREO
				WHERE ID = P_ID_TCA_PLANTILLA_CORREO
				ORDER BY 1			
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID_PLANTILLA = ' || v_rec.ID); 
				DBMS_OUTPUT.PUT_LINE ('DESCRIPCION = ' || v_rec.DESCRIPCION); 
				DBMS_OUTPUT.PUT_LINE ('REMITENTE = ' || v_rec.REMITENTE); 
				DBMS_OUTPUT.PUT_LINE ('MENSAJE = ' || v_rec.MENSAJE); 
				DBMS_OUTPUT.PUT_LINE ('ASUNTO = ' || v_rec.ASUNTO); 				
				DBMS_OUTPUT.PUT_LINE ('BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);
				DBMS_OUTPUT.PUT_LINE ('CC_DESTINATARIOS = ' || v_rec.CC_DESTINATARIOS); 
				DBMS_OUTPUT.PUT_LINE ('SE ENVIA A EQUIPO DE TRABAJO = ' || v_rec.BAN_VALIDA_ET); 
				DBMS_OUTPUT.PUT_LINE ('SE ENVIA A MIEMBROS COMITE CRÉDITO = ' || v_rec.BAN_VALIDA_MCC); 
				DBMS_OUTPUT.PUT_LINE ('SE ENVIA A VALIDADOR ACCION = ' || v_rec.BAN_VALIDA_VA); 
				DBMS_OUTPUT.PUT_LINE ('SE ENVIA A RESPONSABLE ACCION = ' || v_rec.BAN_VALIDA_RA); 
				DBMS_OUTPUT.PUT_LINE ('SE ENVIA A RESPONSABLE OPERACION = ' || v_rec.BAN_VALIDA_RO); 										
			END LOOP;
			
			P_CODIGO_RES := 1;
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador del registro a mostrar.';			
		END IF;	
		
		v_accion := 'LISTAR DATOS DE LA PLANTILLA DE CORREO ESPECIFICA';
	
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
				'SP_CONF_TCA_PLANTILLA_CORREO',
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
                'SP_CONF_TCA_PLANTILLA_CORREO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        