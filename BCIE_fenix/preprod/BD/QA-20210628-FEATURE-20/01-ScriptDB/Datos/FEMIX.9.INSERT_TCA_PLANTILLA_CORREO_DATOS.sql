DECLARE
id_plantilla_correo INT;
id_plantilla_bpm    INT;
id_plantilla_user   INT;
BEGIN
id_plantilla_correo :=0;
id_plantilla_bpm    :=0;
id_plantilla_user   :=0;

    SELECT MAX(ID) INTO id_plantilla_correo FROM FENIX.TCA_PLANTILLA_CORREO;
    id_plantilla_correo := id_plantilla_correo+1;
    
    INSERT INTO FENIX.tca_plantilla_correo (
        ID,descripcion, remitente, mensaje, asunto,  FECHA_REGISTRO, BAN_ESTATUS, id_tca_categoria_evento
        --,emoji_asunto, emoji_contenido
    ) VALUES (
        id_plantilla_correo
        ,'PLANTILLA_FORMULARIO_IMPLICITO'
        ,'BCIE'
        ,'[OPERACION_TRANS]<p></p> [EJECUTOR_REGISTRAR_LINEA] <p></p>[FORMULARIO_DERIVADOS]'
        ,'Notificaci�n - Validaci�n de Condiciones Especiales por GERIES Finalizada:[OPERACION]'
        , CURRENT_DATE
        ,1
        ,1    
    );
    
    SELECT MAX(id) INTO id_plantilla_bpm FROM FENIX.TRE_PLANTILLA_CORREO_BPM;
    id_plantilla_bpm := id_plantilla_bpm + 1;
    
    SELECT id INTO ID_COPRES FROM FENIX.tca_rol_bpm WHERE descripcion_corta = 'FENIX_ANALISTA_COPRES';
    SELECT id INTO ID_GERIES FROM FENIX.tca_rol_bpm WHERE descripcion_corta = 'FENIX_RIESGOS';
    
    INSERT INTO FENIX.TRE_PLANTILLA_CORREO_BPM (id,id_tca_plantilla_correo, id_tca_rol_bpm, ban_estatus) VALUES (
        id_plantilla_bpm,    id_plantilla_correo,	ID_COPRES,	1);
        
    id_plantilla_bpm := id_plantilla_bpm + 1;
    INSERT INTO FENIX.TRE_PLANTILLA_CORREO_BPM (id,id_tca_plantilla_correo, id_tca_rol_bpm, ban_estatus) VALUES (
        id_plantilla_bpm,    id_plantilla_correo,	ID_GERIES,	1);
            
    SELECT FENIX.tre_plantilla_correo_user_seq INTO id_plantilla_user FROM DUAL;
    
    INSERT INTO FENIX.tre_plantilla_correo_user ( id,id_tca_plantilla_correo,login_usuario,ban_estatus,tipo_usuario) VALUES (
        id_plantilla_user, id_plantilla_correo,	LRIVAS,	1,	CC);
    
    INSERT INTO FENIX.tre_plantilla_correo_user ( id,id_tca_plantilla_correo,login_usuario,ban_estatus,tipo_usuario) VALUES (
        id_plantilla_user, id_plantilla_correo,	RODRIGUEZM,	1,	CC);
    
    COMMIT;
EXCEPTION
   WHEN OTHERS THEN
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_backtrace); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_call_stack); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_stack); 
   ROLLBACK;
END;
