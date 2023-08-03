-- ##--------------------------------------------------------------------------------------------##
-- Descripción: Script que permite agregar, desactivar/activar las notificaciones de acuerdo a los usuarios a los cuales se les enviará dicha notificación, así mismo, permite listar la información de los catálogos.
/*
Acciones permitidas (P_ACCION):
1 - Agregar
3 - Desactivar
4 - Activar
5 – Listar el catálogo de plantillas (TCA_PLANTILLA_CORREO)
6 – Listar una plantilla en específico (TCA_PLANTILLA_CORREO)
7 - Listar los usuarios  de acuerdo a una plantilla en específico
(TRE_PLANTILLA_CORREO_USER)

*/
-- ##--------------------------------------------------------------------------------------------##

CREATE OR REPLACE PROCEDURE SP_CONF_PLANTILLA_USUARIO(
	P_ACCION                    IN NUMBER    := 0,			-- Identificador de la transacción a ejecutar
	P_ID_TRE_PLANTILLA_USER 	IN NUMBER    := NULL,		-- Id de relación entre la plantilla y el usuario (TRE_PLANTILLA_CORREO_USER)
	P_ID_TCA_PLANTILLA_CORREO   IN NUMBER    := NULL,		-- Id de la plantilla de correo. (TCA_PLANTILLA_CORREO)
	P_LOGIN_USUARIO				IN VARCHAR2  := NULL,		-- Login del usuario a   quien se le enviará la notificación.
	P_CODIGO_RES                OUT NUMBER)					-- Indica el código de respuesta de la transacción. (0 - Incorrecto, 1 - Correcto).
AS
	err_num						NUMBER;
	err_msg						VARCHAR2(255);
	v_accion					VARCHAR2(250) := 'NO IDENTIFICADA';
	v_Error_msg	 				VARCHAR2(100) := '';
	v_ID_TRE_PLANTILLA_USER_BPM NUMBER; 
	
BEGIN
	P_CODIGO_RES := 0;
	IF (P_ACCION = 1)		-- Se agrega una nueva relación entre plantilla y usuario
	THEN
	
		SELECT MAX (ID) INTO v_ID_TRE_PLANTILLA_USER_BPM FROM TRE_PLANTILLA_CORREO_USER;
		v_ID_TRE_PLANTILLA_USER_BPM := v_ID_TRE_PLANTILLA_USER_BPM + 1;
		
        INSERT INTO TRE_PLANTILLA_CORREO_USER(ID,
												ID_TCA_PLANTILLA_CORREO,
                                                LOGIN_USUARIO,
                                                BAN_ESTATUS)
		VALUES(v_ID_TRE_PLANTILLA_USER_BPM,
			P_ID_TCA_PLANTILLA_CORREO,
            P_LOGIN_USUARIO,
            1);
        COMMIT;
		
        P_CODIGO_RES := 1;
        v_accion := 'AGREGAR';                                               
		
    ELSIF (P_ACCION = 3) 		-- Deshabilita los datos de relación entre plantilla y usuario
    THEN
		IF  (P_ID_TRE_PLANTILLA_USER > 0) THEN
			UPDATE TRE_PLANTILLA_CORREO_USER
			SET BAN_ESTATUS = 0
			WHERE ID = P_ID_TRE_PLANTILLA_USER;		
		
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
	
	ELSIF (P_ACCION = 4) 		-- Habilita los datos de relación entre plantilla y usuario
    THEN
		IF  (P_ID_TRE_PLANTILLA_USER > 0) THEN
			UPDATE TRE_PLANTILLA_CORREO_USER
			SET BAN_ESTATUS = 1
			WHERE ID = P_ID_TRE_PLANTILLA_USER;		
			
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
	
	ELSIF (P_ACCION = 5)		 -- Lista el catálogo de plantillas (TCA_PLANTILLA_CORREO)	
    THEN
	    DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		
		FOR v_rec IN (			
			SELECT 
				ID, DESCRIPCION,  ASUNTO, BAN_ESTATUS
			FROM TCA_PLANTILLA_CORREO			
			ORDER BY 1			
		) LOOP
			DBMS_OUTPUT.PUT_LINE ('ID_PLANTILLA = ' || v_rec.ID || ', DESCRIPCION = ' || v_rec.DESCRIPCION || ', ASUNTO = ' || v_rec.ASUNTO || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS); 
		END LOOP;
			
		P_CODIGO_RES := 1;		
		v_accion := 'LISTAR EL CATÁLOGO DE PLANTILLAS';
		
	ELSIF (P_ACCION = 6)	--  Lista una plantilla en específico (TCA_PLANTILLA_CORREO)
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
			v_Error_msg := 'No se ha indicado el identificador de la plantilla a mostrar.';			
		END IF;	
		
		v_accion := 'LISTAR UNA PLANTILLA EN ESPECÍFICO';

	ELSIF (P_ACCION = 7)		--  Lista los login de los usuarios a enviar una plantilla de acuerdo a una plantilla en específico (TRE_PLANTILLA_CORREO_ROL_BPM)
    THEN
        DBMS_OUTPUT.PUT_LINE ('# -------------------------------------------------------------------------------------------- #');
		IF  (P_ID_TCA_PLANTILLA_CORREO > 0) THEN
			FOR v_rec IN (
				SELECT TPCUSER.ID AS ID_TRE_PLANTILLA_USUARIO, TPC.ID AS ID_PLANTILLA, TPC.DESCRIPCION, TPC.ASUNTO, TPCUSER.LOGIN_USUARIO, TPCUSER.BAN_ESTATUS
				FROM TRE_PLANTILLA_CORREO_USER TPCUSER
				INNER JOIN TCA_PLANTILLA_CORREO TPC ON TPCUSER.ID_TCA_PLANTILLA_CORREO = TPC.ID				
				WHERE TPC.ID = P_ID_TCA_PLANTILLA_CORREO
				ORDER BY 1
			) LOOP
				DBMS_OUTPUT.PUT_LINE ('ID_TRE_PLANTILLA_USUARIO = ' || v_rec.ID_TRE_PLANTILLA_USUARIO || ', ID_PLANTILLA = ' || v_rec.ID_PLANTILLA || ', PLANTILLA = ' || v_rec.DESCRIPCION || ', ASUNTO = ' || v_rec.ASUNTO || ', LOGIN_USUARIO = ' || v_rec.LOGIN_USUARIO || ', BAN_ESTATUS = ' || v_rec.BAN_ESTATUS);			
			END LOOP;
			P_CODIGO_RES := 1;				
			/*
			IF SQL%FOUND THEN
				P_CODIGO_RES := 1;				
			ELSE
				P_CODIGO_RES := 0;
                v_Error_msg := 'No se encontraron datos que mostrar.';			
			END IF;		
			*/
		ELSE
			P_CODIGO_RES := 0;
			v_Error_msg := 'No se ha indicado el identificador de la plantilla a mostrar.';			
		END IF;
		
		v_accion := 'LISTAR EL >>LOGIN USUARIO<< A LOS CUALES SE ENVIA LA PLANTILLA DEFINIDA';		
	
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
				'SP_CONF_PLANTILLA_USUARIO',
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
                'SP_CONF_PLANTILLA_USUARIO',
                'Error: '
                || TO_CHAR (err_num)
                || ' '
                || SUBSTR (err_msg, 1, 520),
                SYSDATE);
		COMMIT;
END;                        