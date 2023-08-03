xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace reg="http://www.bcie.org/ReglaTareaMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ReglaTarea/V1/Schema/ReglaTareaMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg1 = "http://www.bcie.org/ReglaBO";

declare namespace reg2 = "http://www.bcie.org/ReglaTareaBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $inputVariable.request as element() (:: schema-element(reg:ValidarTareaReglasRequest) ::) external;
declare variable $outConsultarReglasTarea.response as element() (:: schema-element(reg:ConsultarReglasTareaResponse) ::) external;

declare function local:funcXq_consultarreglastarea_to_validarreglas($inputVariable.request as element() (:: schema-element(reg:ValidarTareaReglasRequest) ::), 
                                                                    $outConsultarReglasTarea.response as element() (:: schema-element(reg:ConsultarReglasTareaResponse) ::)) 
                                                                    as element() (:: schema-element(reg:ValidarReglasRequest) ::) {
    <reg:ValidarReglasRequest>
        {
            for $ReglasEvaluacion in $outConsultarReglasTarea.response/reg:TareaReglas/reg2:ReglasEvaluacion
            return 
            <reg:Reglas>
                <reg1:Id>{fn:data($ReglasEvaluacion/reg1:Id)}</reg1:Id>
                {
                    if ($ReglasEvaluacion/reg1:Descripcion)
                    then <reg1:Descripcion>{fn:data($ReglasEvaluacion/reg1:Descripcion)}</reg1:Descripcion>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg1:Transaccion)
                    then <reg1:Transaccion>{fn:data($ReglasEvaluacion/reg1:Transaccion)}</reg1:Transaccion>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg1:Tipo)
                    then 
                        <reg1:Tipo>
                            {
                                if ($ReglasEvaluacion/reg1:Tipo/cat:Id)
                                then <cat:Id>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Tipo/cat:estatus)
                                then <cat:estatus>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </reg1:Tipo>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg1:PosibleExceptuar)
                    then <reg1:PosibleExceptuar>{fn:data($ReglasEvaluacion/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg1:Exceptuado)
                    then 
                        <reg1:Exceptuado>
                            {
                                if ($ReglasEvaluacion/reg1:Exceptuado/cat:Id)
                                then <cat:Id>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Exceptuado/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Exceptuado/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Exceptuado/cat:estatus)
                                then <cat:estatus>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Exceptuado/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </reg1:Exceptuado>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg1:UsuarioExceptua)
                    then <reg1:UsuarioExceptua>{fn:data($ReglasEvaluacion/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg1:FechaExcepcion)
                    then <reg1:FechaExcepcion>{fn:data($ReglasEvaluacion/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg1:Estado)
                    then 
                        <reg1:Estado>
                            {
                                if ($ReglasEvaluacion/reg1:Estado/cat:Id)
                                then <cat:Id>{fn:data($ReglasEvaluacion/reg1:Estado/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Estado/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Estado/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Estado/cat:estatus)
                                then <cat:estatus>{fn:data($ReglasEvaluacion/reg1:Estado/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ReglasEvaluacion/reg1:Estado/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </reg1:Estado>
                    else ()
                }
                {
                    if ($ReglasEvaluacion/reg1:Estatus)
                    then <reg1:Estatus>{fn:data($ReglasEvaluacion/reg1:Estatus)}</reg1:Estatus>
                    else ()
                }
                <reg2:EsAdvertencia>{fn:data($ReglasEvaluacion/reg2:EsAdvertencia)}</reg2:EsAdvertencia>
                <reg2:EsError>{fn:data($ReglasEvaluacion/reg2:EsError)}</reg2:EsError>
                <reg2:Mensaje>
                    {
                        if ($ReglasEvaluacion/reg2:Mensaje/cat:Id)
                        then <cat:Id>{fn:data($ReglasEvaluacion/reg2:Mensaje/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:Mensaje/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg2:Mensaje/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:Mensaje/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg2:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:Mensaje/cat:estatus)
                        then <cat:estatus>{fn:data($ReglasEvaluacion/reg2:Mensaje/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:Mensaje/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg2:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </reg2:Mensaje>
            </reg:Reglas>
        }
        {
            for $Parametros in $inputVariable.request/reg:Parametros
            return 
            <reg:Parametros>
                <com:name>{fn:data($Parametros/com:name)}</com:name>
                <com:value>{fn:data($Parametros/com:value)}</com:value>
            </reg:Parametros>
        }
            <reg:Parametros>
                <com:name>ID_TAREA</com:name>
                <com:value>{fn:data($inputVariable.request/reg:idTarea)}</com:value>
            </reg:Parametros>
            <reg:Parametros>
                <com:name>PROCESO</com:name>
                <com:value>{fn:data($outConsultarReglasTarea.response/reg:TareaReglas/reg2:Proceso)}</com:value>
            </reg:Parametros>
    </reg:ValidarReglasRequest>
};

local:funcXq_consultarreglastarea_to_validarreglas($inputVariable.request, $outConsultarReglasTarea.response)
