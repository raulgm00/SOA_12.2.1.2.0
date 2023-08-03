xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace reg="http://www.bcie.org/ReglaTareaMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ReglaTarea/V1/Schema/ReglaTareaMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg1 = "http://www.bcie.org/ReglaTareaBO";

declare namespace reg2 = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outValidarReglas.response as element() (:: schema-element(reg:ValidarReglasResponse) ::) external;
declare variable $outConsultarReglasTarea.response as element() (:: schema-element(reg:ConsultarReglasTareaResponse) ::) external;

declare function local:funcXq_responsevalidarreglastarea($outValidarReglas.response as element() (:: schema-element(reg:ValidarReglasResponse) ::), 
                                                         $outConsultarReglasTarea.response as element() (:: schema-element(reg:ConsultarReglasTareaResponse) ::)) 
                                                         as element() (:: schema-element(reg:ValidarTareaReglasResponse) ::) {
    <reg:ValidarTareaReglasResponse>
        <reg:ReglaTarea>
            <reg1:IdTarea>{fn:data($outConsultarReglasTarea.response/reg:TareaReglas/reg1:IdTarea)}</reg1:IdTarea>
            {
                if ($outConsultarReglasTarea.response/reg:TareaReglas/reg1:Proceso)
                then <reg1:Proceso>{fn:data($outConsultarReglasTarea.response/reg:TareaReglas/reg1:Proceso)}</reg1:Proceso>
                else ()
            }
            {
                if ($outConsultarReglasTarea.response/reg:TareaReglas/reg1:Descripcion)
                then <reg1:Descripcion>{fn:data($outConsultarReglasTarea.response/reg:TareaReglas/reg1:Descripcion)}</reg1:Descripcion>
                else ()
            }
            {
                if ($outConsultarReglasTarea.response/reg:TareaReglas/reg1:Estatus)
                then <reg1:Estatus>{fn:data($outConsultarReglasTarea.response/reg:TareaReglas/reg1:Estatus)}</reg1:Estatus>
                else ()
            }
            {
                for $ReglasEvaluacion in $outValidarReglas.response/reg:TareaReglas/reg1:ReglasEvaluacion
                return 
                <reg1:ReglasEvaluacion>
                    <reg2:Id>{fn:data($ReglasEvaluacion/reg2:Id)}</reg2:Id>
                    {
                        if ($ReglasEvaluacion/reg2:Descripcion)
                        then <reg2:Descripcion>{fn:data($ReglasEvaluacion/reg2:Descripcion)}</reg2:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:Transaccion)
                        then <reg2:Transaccion>{fn:data($ReglasEvaluacion/reg2:Transaccion)}</reg2:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:Tipo)
                        then 
                            <reg2:Tipo>
                                {
                                    if ($ReglasEvaluacion/reg2:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasEvaluacion/reg2:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg2:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg2:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasEvaluacion/reg2:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg2:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg2:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:PosibleExceptuar)
                        then <reg2:PosibleExceptuar>{fn:data($ReglasEvaluacion/reg2:PosibleExceptuar)}</reg2:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:Exceptuado)
                        then 
                            <reg2:Exceptuado>
                                {
                                    if ($ReglasEvaluacion/reg2:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasEvaluacion/reg2:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg2:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg2:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasEvaluacion/reg2:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg2:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg2:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:UsuarioExceptua)
                        then <reg2:UsuarioExceptua>{fn:data($ReglasEvaluacion/reg2:UsuarioExceptua)}</reg2:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:FechaExcepcion)
                        then <reg2:FechaExcepcion>{fn:data($ReglasEvaluacion/reg2:FechaExcepcion)}</reg2:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:Estado)
                        then 
                            <reg2:Estado>
                                {
                                    if ($ReglasEvaluacion/reg2:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasEvaluacion/reg2:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg2:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg2:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasEvaluacion/reg2:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasEvaluacion/reg2:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg2:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg2:Estado>
                        else ()
                    }
                    {
                        if ($ReglasEvaluacion/reg2:Estatus)
                        then <reg2:Estatus>{fn:data($ReglasEvaluacion/reg2:Estatus)}</reg2:Estatus>
                        else ()
                    }
                    <reg1:EsAdvertencia>{fn:data($outConsultarReglasTarea.response/reg:TareaReglas/reg1:ReglasEvaluacion[reg2:Id = $ReglasEvaluacion/reg2:Id]/reg1:EsAdvertencia)}</reg1:EsAdvertencia>
                    <reg1:EsError>{fn:data($outConsultarReglasTarea.response/reg:TareaReglas/reg1:ReglasEvaluacion[reg2:Id = $ReglasEvaluacion/reg2:Id]/reg1:EsError)}</reg1:EsError>
                    <reg1:Mensaje>
                        {
                            if ($ReglasEvaluacion/reg1:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasEvaluacion/reg1:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasEvaluacion/reg1:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasEvaluacion/reg1:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasEvaluacion/reg1:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg1:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg1:Mensaje>
                </reg1:ReglasEvaluacion>
            }
        </reg:ReglaTarea>
        <reg:Resultado>
            <res:result>OK</res:result>
            <res:message>Validación Exitosa</res:message>
        </reg:Resultado>
    </reg:ValidarTareaReglasResponse>
};

local:funcXq_responsevalidarreglastarea($outValidarReglas.response, $outConsultarReglasTarea.response)
