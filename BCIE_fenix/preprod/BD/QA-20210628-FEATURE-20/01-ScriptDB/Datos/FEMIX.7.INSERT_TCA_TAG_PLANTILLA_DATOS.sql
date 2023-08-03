DECLARE
id_tag_plantilla INT;
BEGIN
id_tag_plantilla :=0;
        
        SELECT MAX(id) INTO id_tag_plantilla FROM FENIX.tca_tag_plantilla;
            id_tag_plantilla := id_tag_plantilla + 1;
        
        --INSERT TAG OPERACION_TRANS
        INSERT INTO FENIX.tca_tag_plantilla (id, descripcion_tag, sql_query, fecha_registro, ban_estatus, ban_generico, tipo_plantilla,id_xsl,id_sql
        ) VALUES (
            id_tag_plantilla,
            'OPERACION_TRANS',
            '              ',
            SYSDATE,
            1,
            0,
            'NOTIFICACION CORREO',
            'OPERACION_XSL',
            'OPERACION_SQL'
        );
        
        id_tag_plantilla := id_tag_plantilla + 1;
        
        --INSERT TAG EJECUTOR_REGISTRAR_LINEA
        INSERT INTO FENIX.tca_tag_plantilla (id, descripcion_tag, sql_query, fecha_registro, ban_estatus, ban_generico, tipo_plantilla,id_xsl,id_sql
        ) VALUES (
            id_tag_plantilla,
            'EJECUTOR_REGISTRAR_LINEA',
            '              ',
            SYSDATE,
            1,
            0,
            'NOTIFICACION CORREO',
            'FNX_REGLINEA_XSL',
            'FNX_REGLINEA_SQL'
        );
        
        id_tag_plantilla := id_tag_plantilla + 1;
        
        --INSERT TAG FORMULARIO_DERIVADOS
        INSERT INTO FENIX.tca_tag_plantilla (id, descripcion_tag, sql_query, fecha_registro, ban_estatus, ban_generico, tipo_plantilla,id_xsl,id_sql
        ) VALUES (
            id_tag_plantilla,
            'FORMULARIO_DERIVADOS',
            '              ',
            SYSDATE,
            1,
            0,
            'NOTIFICACION CORREO',
            'FNX_FORDERI_XSL',
            'FNX_FORDERI_XQL'
        );

COMMIT;
EXCEPTION
   WHEN OTHERS THEN
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_backtrace); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_call_stack); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_stack); 
   ROLLBACK;
END;
