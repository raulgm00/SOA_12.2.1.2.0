xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaTareaBO";

declare namespace reg1 = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) external;
declare variable $outValidarPlazoCapitalMinimoMaximoGracia.response as element() (:: schema-element(des:ValidarPlazoResponse) ::) external;

declare function local:funcXq_responseplazocapitalminimomaximogracia($inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::), 
                                                                     $outValidarPlazoCapitalMinimoMaximoGracia.response as element() (:: schema-element(des:ValidarPlazoResponse) ::)) 
                                                                     as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) {
    <des:ValidarReglasDesembolsoResponse>
        <des:TareaReglas>
            <reg:Proceso>{fn:data($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Resultado/res:result)}</reg:Proceso>
            <reg:Descripcion>{fn:data($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Resultado/res:message)}</reg:Descripcion>
            {
                for $ReglasEvaluacion in $inputVariable.request/des:TareaReglas/reg:ReglasEvaluacion
                let $estadoRegla := if ($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Regla[reg1:Transaccion/text() = $ReglasEvaluacion/reg1:Transaccion/text()])
                                            then fn:data($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Regla[reg1:Transaccion/text() = $ReglasEvaluacion/reg1:Transaccion/text()]/reg1:Estado/cat:DescripcionCorta)
                                            else ''
                where $ReglasEvaluacion/reg1:Transaccion = 'PLAZO_CAPITAL_MINIMO' or $ReglasEvaluacion/reg1:Transaccion = 'PLAZO_CAPITAL_MAXIMO' or $ReglasEvaluacion/reg1:Transaccion = 'PERIODO_GRACIA'
                return 
                <reg:ReglasEvaluacion>
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
                                <cat:DescripcionCorta>
                                  { if (xs:string($ReglasEvaluacion/reg1:Exceptuado/cat:estatus) = 'true')
                                      then 'EXCEPTUADA'
                                    else if (xs:string($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Resultado/res:result) = 'ERROR')
                                      then 'ERROR'
                                    else if (fn:string-length($estadoRegla) > 0)
                                      then fn:data($estadoRegla)
                                    else if (fn:string-length($estadoRegla) = 0)
                                      then 'NO_EVALUADA'
                                    else ()
                                  }
                                </cat:DescripcionCorta>
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
                    {
                        for $DetalleRegla in $ReglasEvaluacion/reg1:DetalleRegla
                        return 
                        <reg1:DetalleRegla>
                            {
                                if ($DetalleRegla/atr:id)
                                then <atr:id>{fn:data($DetalleRegla/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($DetalleRegla/atr:descripcion)
                                then <atr:descripcion>{fn:data($DetalleRegla/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($DetalleRegla/atr:estatus)
                                then <atr:estatus>{fn:data($DetalleRegla/atr:estatus)}</atr:estatus>
                                else ()
                            }
                        </reg1:DetalleRegla>
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasEvaluacion/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasEvaluacion/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasEvaluacion/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        <cat:Descripcion>
                        { if (xs:string($ReglasEvaluacion/reg1:Exceptuado/cat:estatus) = 'true')
                            then 'EXCEPTUADA'
                          else if (xs:string($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Resultado/res:result) = 'ERROR')
                            then fn:data($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Resultado/res:message)
                          else if (count($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Regla) = 1)
                            then fn:data($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Regla/reg1:Estado/cat:Descripcion)
                          else if (fn:string-length($estadoRegla) > 0)
                            then fn:data($outValidarPlazoCapitalMinimoMaximoGracia.response/des:Regla[reg1:Transaccion = $ReglasEvaluacion/reg1:Transaccion]/reg1:Estado/cat:Descripcion)
                          else if (fn:string-length($estadoRegla) = 0)
                            then 'CUMPLIDA'
                          else ()
                        }
                        </cat:Descripcion>
                        {
                            if ($ReglasEvaluacion/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasEvaluacion/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasEvaluacion/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
            }
        </des:TareaReglas>
    </des:ValidarReglasDesembolsoResponse>
};

local:funcXq_responseplazocapitalminimomaximogracia($inputVariable.request, $outValidarPlazoCapitalMinimoMaximoGracia.response)
