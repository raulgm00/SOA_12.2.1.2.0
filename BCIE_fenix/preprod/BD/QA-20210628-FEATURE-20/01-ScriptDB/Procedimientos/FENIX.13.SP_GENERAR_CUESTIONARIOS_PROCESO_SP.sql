create or replace PROCEDURE       SP_GENERAR_CUESTIONARIO_PROCESO (
    Num_Operacion       IN     INTEGER
  , Num_Proceso_BPM     IN     INTEGER
  , Regenerar           IN     NUMBER
  , RESPUESTA           OUT    VARCHAR2)
AS
    id_Version_Variable        INT;
    Producto                   INT;
    Tipo_Cuestionario          INT;
    Sector                     INT;
    Tipo_Garantia              INT;
    Tipo_Institucion           INT;
    Num_Cliente                INT;
    v_Regenerar                INT;
    V_ID_CUESTIONARIO          INT;
    RESULTADO                  INT;
    RESULTADO_INVALIDO        EXCEPTION; 
BEGIN
    --Recuperar Tipo de Cuestionario de acuerdo a la Tarea BPM
    RESULTADO := 0;
    
    Select ID_TCA_TIPO_CUESTIONARIO
        INTO Tipo_Cuestionario
        FROM tco_proceso_bpm_cuestionario
        WHERE ID_TCA_PROCESO_BPM = Num_Proceso_BPM;
    DBMS_OUTPUT.PUT_LINE('ID TIPO CUESTIONARIO');
    DBMS_OUTPUT.PUT_LINE(Tipo_Cuestionario);
    
    IF Regenerar = 1
    THEN
        --TOMA EL ID DEL Cuestionario ACTIVO
        DBMS_OUTPUT.PUT_LINE('REGNERAR');
        Select Id_Cuestionario
          Into v_Regenerar
          From Cuestionario
         Where Ban_Estatus = 1
           And id_Operacion = Num_Operacion
           And ID_TCA_TIPO_CUESTIONARIO = Tipo_Cuestionario;
    END IF;

    -- INACTIVAR EL Cuestionario ACTUAL
    Update Cuestionario
       Set Ban_Estatus = 0
     Where ID_Operacion = Num_Operacion
        And ID_TCA_TIPO_CUESTIONARIO = Tipo_Cuestionario;

    --OBTENER LA VERSION ACTUAL DEL Cuestionario
    Select ID
      Into id_Version_Variable
      From Tca_Version_Cuestionario
     Where Ban_Estatus = 1
     And ID_TCA_TIPO_CUESTIONARIO = Tipo_Cuestionario;
    DBMS_OUTPUT.PUT_LINE('TCA VERSION CUESTIONARIO');
    DBMS_OUTPUT.PUT_LINE(id_Version_Variable);
    
    /*--ASIGNANDO PRODUCTO, GARANTIA Y SECTOR DE LA OPERACION
    -- TIPO DE INSTITUCION DEL CLIENTE
    Select fOp.Id_producto
         , fCl.Tipo_Institucion
         , nvl(fOp.id_Sector_Institucional, fCl.Sector) Sector
         , fCl.Id_Cliente
         , NVL (fOp.Tipo_Garantia, 0)
      Into Producto
         , Tipo_Institucion
         , Sector
         , Num_Cliente
         , Tipo_Garantia
      From Operacion  fOp
      Join Clientes fCl
        ON fOp.Id_Cliente = fCl.Id_Cliente
     Where fOp.ID_Operacion = Num_Operacion;*/

    --INSERTANDO EN Cuestionario
    Select Cuestionario_SEQ.NextVal
      Into V_ID_CUESTIONARIO
      From DUAL;
    
    DBMS_OUTPUT.PUT_LINE('ID NUEVO CUESTIONARIO');
    DBMS_OUTPUT.PUT_LINE(V_ID_CUESTIONARIO);
    --
    INSERT Into Cuestionario (
        id_Cuestionario
      , id_Operacion
      , Fecha_Registro
      , Ban_Estatus
      , ID_TCA_TIPO_CUESTIONARIO
    ) VALUES (
        V_ID_CUESTIONARIO
      , Num_Operacion
      , CURRENT_DATE
      , 1
      , Tipo_Cuestionario
    );
    DBMS_OUTPUT.PUT_LINE('NSERTO NUEVO CUESTIONARIO');
    DBMS_OUTPUT.PUT_LINE(V_ID_CUESTIONARIO);
    /*-- INSERTAR PREGUNTAS DE ACUERDO AL TIPO DE CUESTIONARIO
    IF Tipo_Cuestionario = 1 THEN
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
                , V_ID_CUESTIONARIO
            From cat_Preguntas fPg
            Join tre_Pregunta_Version fPgVer
                On fPg.ID = fPgVer.id_Pregunta
            Join tca_Version_Cuestionario fVerCue
                on fPgVer.id_Version = fVerCue.id
            Join Pregunta_Sector_Garantia fPgSecGar
                On fPg.ID = fPgSecGar.id_Pregunta
            Join tre_Pregunta_Tipo_Institucion fPgInst
                On fPg.ID = fPgInst.id_Pregunta
            Join Pregunta_Producto fPgProd
                On fPg.ID = fPgProd.id_Pregunta
            Join tca_Rol_BPM fRol
                On fPg.Id_Responsable = fRol.ID
            Where fPgVer.id_Version = id_Version_Variable
                And fPgProd.id_Producto = Producto
                And fPgInst.id_Tipo_Institucion = Tipo_Institucion
                And fPgSecGar.id_Sector = Sector
                And nvl(fPgSecGar.id_Garantia,0) = Tipo_Garantia
                And fRol.ID not in (
                    Select distinct id_Rol_BPM
                    From tco_Pregunta_Omitir_Rol
                    Where id_Sector_Institucional = Sector
                        And id_Tipo_Institucion = Tipo_Institucion
                        And Ban_Estatus = 1
                    )
                And fPg.Status = '1'
            Order By id_Principio, id_Criterio, Num_Orden;
      
      ELSIF Tipo_Cuestionario = 2 THEN
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
                , V_ID_CUESTIONARIO
            From cat_Preguntas fPg
            Join tre_Pregunta_Version fPgVer
                On fPg.ID = fPgVer.id_Pregunta
            Join tca_Version_Cuestionario fVerCue
                on fPgVer.id_Version = fVerCue.id
            Join Pregunta_Producto fPgProd
                On fPg.ID = fPgProd.id_Pregunta
            Join tca_Rol_BPM fRol
                On fPg.Id_Responsable = fRol.ID
            Where fPgVer.id_Version = id_Version_Variable
                And fPgProd.id_Producto = Producto            
                And fPg.Status = '1'
                And fPgProd.Status = '1'
            Order By Num_Orden;
    
      END IF;*/
    IF Tipo_Cuestionario = 1 THEN
        RESULTADO := F_GENERAR_CUESTIONARIO_ELEGIBILIDAD(Num_Operacion, id_Version_Variable, V_ID_CUESTIONARIO);
        DBMS_OUTPUT.PUT_LINE('GENERO ELEGIBILIDAD CON RESULTADO');
        DBMS_OUTPUT.PUT_LINE(RESULTADO);
    ELSIF Tipo_Cuestionario = 2 THEN
        RESULTADO := F_GENERAR_FORMULARIO(Num_Operacion, id_Version_Variable, V_ID_CUESTIONARIO);
        DBMS_OUTPUT.PUT_LINE('GENERO FORMALIZACION CON RESULTADO');
        DBMS_OUTPUT.PUT_LINE(RESULTADO);
    END IF;

    IF RESULTADO = 0 THEN
        RAISE RESULTADO_INVALIDO;
    END IF;

    IF Regenerar = 1
    THEN
        Update Pregunta PA
           Set (Respuesta, Justificacion) = (
                Select Respuesta, Justificacion
                  From Pregunta PR
                 Where PR.Id_Cuestionario = v_Regenerar
                   And PR.Pregunta = PA.Pregunta
               )
         Where Id_Cuestionario = V_ID_CUESTIONARIO;
    END IF;
    RESPUESTA := 'OK';
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('FIN');
/*
    --RETORNAR PREGUNTAS
    OPEN RECORDSET FOR
    Select fPg.id_Pregunta ID
         , fPg.Pregunta Pregunta
         , fPg.USUARIO RESPONSABLE
      From Pregunta  fPg
      Join Cuestionario fCu
        ON fPg.Id_Cuestionario = fCu.Id_Cuestionario
       AND fCu.ID_Operacion = Num_Operacion
       And fCu.id_tipo_cuestionario = Tipo_Cuestionario;
*/
EXCEPTION
    WHEN RESULTADO_INVALIDO THEN
        RESPUESTA := 'ERROR';
        DBMS_OUTPUT.PUT_LINE('NO SE GENERARON PREGUNTAS PARA EL CUESTIONARIO'); 

    WHEN OTHERS THEN
        RESPUESTA := 'ERROR';
        DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_backtrace); 
        DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_call_stack); 
        DBMS_OUTPUT.PUT_LINE(DBMS_UTILITY.format_error_stack); 
    
    rollback;
END SP_GENERAR_CUESTIONARIO_PROCESO;