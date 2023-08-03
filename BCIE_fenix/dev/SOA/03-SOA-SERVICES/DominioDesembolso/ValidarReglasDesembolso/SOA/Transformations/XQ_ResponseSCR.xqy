xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaTareaBO";

declare namespace reg1 = "http://www.bcie.org/ReglaBO";

declare variable $inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) external;
declare variable $inputvariableSCR.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) external;

declare function local:funcXq_responsescr($inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::), 
                                          $inputvariableSCR.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::)) 
                                          as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) {
    <des:ValidarReglasDesembolsoResponse>
        <des:TareaReglas>
            <reg:IdTarea></reg:IdTarea>
            <reg:Proceso></reg:Proceso>
            <reg:Descripcion></reg:Descripcion>
            <reg:Estatus></reg:Estatus>
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($inputvariableSCR.request/des:TareaReglas/reg:ReglasEvaluacion/reg1:Id)}</reg1:Id>
                    {
                        if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion[reg1:Transaccion = 'SCR'][1]/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
        </des:TareaReglas>
    </des:ValidarReglasDesembolsoResponse>
};

local:funcXq_responsescr($inputVariable.request, $inputvariableSCR.request)
