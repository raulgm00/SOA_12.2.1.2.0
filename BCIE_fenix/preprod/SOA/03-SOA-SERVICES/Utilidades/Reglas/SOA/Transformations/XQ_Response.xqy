xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace reg="http://www.bcie.org/ReglaTareaMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ReglaTarea/V1/Schema/ReglaTareaMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg1 = "http://www.bcie.org/ReglaTareaBO";

declare namespace reg2 = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $inputVariable.request as element() (:: schema-element(reg:ValidarReglasRequest) ::) external;

declare function local:funcXq_response($inputVariable.request as element() (:: schema-element(reg:ValidarReglasRequest) ::)) as element() (:: schema-element(reg:ValidarReglasResponse) ::) {
    <reg:ValidarReglasResponse>
        <reg:TareaReglas>
            <reg1:IdTarea></reg1:IdTarea>
            <reg1:Proceso></reg1:Proceso>
            <reg1:Descripcion></reg1:Descripcion>
            <reg1:Estatus></reg1:Estatus>
            {
                for $Reglas in $inputVariable.request/reg:Reglas
                return 
                <reg1:ReglasEvaluacion>
                    <reg2:Id>{fn:data($Reglas/reg2:Id)}</reg2:Id>
                    {
                        if ($Reglas/reg2:Descripcion)
                        then <reg2:Descripcion>{fn:data($Reglas/reg2:Descripcion)}</reg2:Descripcion>
                        else ()
                    }
                    {
                        if ($Reglas/reg2:Transaccion)
                        then <reg2:Transaccion>{fn:data($Reglas/reg2:Transaccion)}</reg2:Transaccion>
                        else ()
                    }
                    {
                        if ($Reglas/reg2:Tipo)
                        then 
                            <reg2:Tipo>
                                {
                                    if ($Reglas/reg2:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($Reglas/reg2:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Reglas/reg2:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Reglas/reg2:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($Reglas/reg2:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Reglas/reg2:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg2:Tipo>
                        else ()
                    }
                    {
                        if ($Reglas/reg2:PosibleExceptuar)
                        then <reg2:PosibleExceptuar>{fn:data($Reglas/reg2:PosibleExceptuar)}</reg2:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($Reglas/reg2:Exceptuado)
                        then 
                            <reg2:Exceptuado>
                                {
                                    if ($Reglas/reg2:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($Reglas/reg2:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Reglas/reg2:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Reglas/reg2:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($Reglas/reg2:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Reglas/reg2:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg2:Exceptuado>
                        else ()
                    }
                    {
                        if ($Reglas/reg2:UsuarioExceptua)
                        then <reg2:UsuarioExceptua>{fn:data($Reglas/reg2:UsuarioExceptua)}</reg2:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($Reglas/reg2:FechaExcepcion)
                        then <reg2:FechaExcepcion>{fn:data($Reglas/reg2:FechaExcepcion)}</reg2:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($Reglas/reg2:Estado)
                        then 
                            <reg2:Estado>
                                {
                                    if ($Reglas/reg2:Estado/cat:Id)
                                    then <cat:Id>{fn:data($Reglas/reg2:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Reglas/reg2:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Reglas/reg2:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($Reglas/reg2:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Reglas/reg2:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Reglas/reg2:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg2:Estado>
                        else ()
                    }
                    {
                        if ($Reglas/reg2:Estatus)
                        then <reg2:Estatus>{fn:data($Reglas/reg2:Estatus)}</reg2:Estatus>
                        else ()
                    }
                    <reg1:EsAdvertencia>{fn:data($Reglas/reg1:EsAdvertencia)}</reg1:EsAdvertencia>
                    <reg1:EsError>{fn:data($Reglas/reg1:EsError)}</reg1:EsError>
                    <reg1:Mensaje>
                        {
                            if ($Reglas/reg1:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($Reglas/reg1:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Reglas/reg1:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Reglas/reg1:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Reglas/reg1:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Reglas/reg1:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Reglas/reg1:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($Reglas/reg1:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Reglas/reg1:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Reglas/reg1:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg1:Mensaje>
                </reg1:ReglasEvaluacion>
            }
        </reg:TareaReglas>
        <reg:Resultado>
            <res:result>ERROR</res:result>
            <res:message>ID DESEMBOLSO</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </reg:Resultado>
    </reg:ValidarReglasResponse>
};

local:funcXq_response($inputVariable.request)
