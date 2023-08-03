DECLARE
id_cuestionario NUMBER(2,0);
id_formulario   NUMBER(2,0);
id_tvc          NUMBER(2,0);
BEGIN
id_formulario := 0;
id_tvc := 0;

    SELECT ID INTO id_formulario FROM FENIX.TCA_TIPO_CUESTIONARIO WHERE DESCRIPCION = 'Formulario de Derivados Impl�citos';
    
    UPDATE FENIX.CUESTIONARIO SET ID_TCA_TIPO_CUESTIONARIO = (
        SELECT ID FROM FENIX.TCA_TIPO_CUESTIONARIO WHERE DESCRIPCION = 'Cuestionario de Elegibilidad'
    );
    UPDATE FENIX.TCA_VERSION_CUESTIONARIO SET ID_TCA_TIPO_CUESTIONARIO = (
        SELECT ID FROM FENIX.TCA_TIPO_CUESTIONARIO WHERE DESCRIPCION = 'Cuestionario de Elegibilidad'
    );

    SELECT FENIX.TCA_VERSION_CUESTIONARIO_SEQ.NEXTVAL INTO id_tvc FROM DUAL;

    INSERT INTO FENIX.TCA_VERSION_CUESTIONARIO (id,descripcion, descripcion_corta, fecha_registro, ban_estatus,id_tca_tipo_cuestionario) VALUES (
        id_tvc,'FORMULARIO DERIVADOS IMPL�CITOS VERSION 1','FDI VERSION 1',CURRENT_DATE,1,id_formulario);

COMMIT;
EXCEPTION
   WHEN OTHERS THEN
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_backtrace); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_call_stack); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_stack); 
   ROLLBACK;
END;