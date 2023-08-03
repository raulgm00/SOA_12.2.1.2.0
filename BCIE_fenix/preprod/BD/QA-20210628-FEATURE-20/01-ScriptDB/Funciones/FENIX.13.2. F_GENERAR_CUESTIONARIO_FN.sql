create or replace FUNCTION F_GENERAR_FORMULARIO (
    Num_Operacion           IN     INT,
    Version_cuestionario    IN     INT,
    Num_Cuestionario        IN     INT)
    RETURN INT
AS
    Producto                INT;
    Num_Cliente             INT;
    
BEGIN

    Select fOp.Id_producto, fOp.Id_cliente
        Into Producto, Num_Cliente 
        From Operacion  fOp             
        Where fOp.ID_Operacion = Num_Operacion;
    
    DBMS_OUTPUT.PUT_LINE('PRODUCTO' ||Producto);
    DBMS_OUTPUT.PUT_LINE('VERSION CUESTIONARIO' ||Version_cuestionario);

    -- INSERTAR PREGUNTAS DE ACUERDO AL TIPO DE CUESTIONARIO
        INSERT Into Pregunta (
            Pregunta
            , Respuesta
            , Justificacion
            , Evidencia
            , Usuario
            , Fecha_Registro
            , Id_Cat_Pregunta
            , Id_Cuestionario
        )
            Select fPg.Pregunta        Pregunta
                 , Null                Respuesta
                , Null                Justificacion
                , Num_Cliente         Evidencia
                , fRol.Descripcion    Usuario
                , SYSDATE             Fecha_Registro
                , fPg.ID
                , Num_Cuestionario
            From cat_Preguntas fPg
            Join tre_Pregunta_Version fPgVer
                On fPg.ID = fPgVer.id_Pregunta
            Join tca_Version_Cuestionario fVerCue
                on fPgVer.id_Version = fVerCue.id
            Join Pregunta_Producto fPgProd
                On fPg.ID = fPgProd.id_Pregunta
            Join tca_Rol_BPM fRol
                On fPg.Id_Responsable = fRol.ID
            Where fPgVer.id_Version = Version_cuestionario
                And fPgProd.id_Producto = Producto            
                And fPg.Status = '1'
                And fPgProd.Ban_Estatus = '1'
            Order By Num_Orden;
    COMMIT;
 
    RETURN 1;

EXCEPTION
   WHEN OTHERS THEN
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_backtrace); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_call_stack); 
     DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_stack); 
   rollback;
   RETURN 0;

END F_GENERAR_FORMULARIO;