BEGIN
    -- INSERT INTO FENIX.TCA_TIPO_CUESTIONARIO (descripcion,descripcion_corta,ban_estatus) VALUES (
        -- 'Cuestionario de Elegibilidad'
        -- , 'Cuestionario de Elegibilidad'
        -- ,1);
    INSERT INTO FENIX.TCA_TIPO_CUESTIONARIO (descripcion,descripcion_corta,ban_estatus) VALUES (
        'Formulario de Derivados Impl�citos'
        , 'Formulario de Derivados Impl�citos'
        ,1); 
    COMMIT;
EXCEPTION
   WHEN OTHERS THEN
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_backtrace); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_call_stack); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_stack); 
   ROLLBACK;
END;