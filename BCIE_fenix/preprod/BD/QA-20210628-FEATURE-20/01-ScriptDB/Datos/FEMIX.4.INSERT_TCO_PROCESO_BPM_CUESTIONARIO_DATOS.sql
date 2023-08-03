DECLARE
id_elegibilidad     INT;
id_formalizacion    INT;
id_cuestionario     INT;
id_formulario       INT;
BEGIN
id_elegibilidad  :=0;
id_formalizacion :=0;
id_cuestionario :=0;
id_formulario :=0;

    -- SELECT ID INTO id_cuestionario FROM FENIX.TCA_TIPO_CUESTIONARIO WHERE DESCRIPCION = 'Cuestionario de Elegibilidad';
    SELECT ID INTO id_formulario FROM FENIX.TCA_TIPO_CUESTIONARIO WHERE DESCRIPCION = 'Formulario de Derivados Impl�citos';
    -- SELECT ID INTO id_elegibilidad FROM FENIX.TCA_PROCESO_BPM WHERE DESCRIPCION = 'Elegibilidad';
    SELECT ID INTO id_formalizacion FROM FENIX.TCA_PROCESO_BPM WHERE DESCRIPCION = 'Formalizaci�n';

    -- INSERT INTO FENIX.TCO_PROCESO_BPM_CUESTIONARIO (ID_TCA_PROCESO_BPM, ID_TCA_TIPO_CUESTIONARIO) VALUES (
        -- id_elegibilidad, id_cuestionario);
    INSERT INTO FENIX.TCO_PROCESO_BPM_CUESTIONARIO (ID_TCA_PROCESO_BPM, ID_TCA_TIPO_CUESTIONARIO) VALUES (
        id_formalizacion, id_formulario); 
    COMMIT;
EXCEPTION
   WHEN OTHERS THEN
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_backtrace); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_call_stack); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_stack); 
   ROLLBACK;
END;