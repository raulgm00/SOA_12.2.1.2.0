xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaTareaBO";

declare namespace reg1 = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare variable $outputVariable.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outputVariableProgramacion.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableLAFT.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableSCR.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outputVariableMora.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableLimites.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableTotalLC.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableLimiteGlobal.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableProxHoraCierre.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariablePreviaHoraCierre.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariablePresentacionF1.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableValidarComponenteInteres.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableValidarPlazoCapitalMinimoMaximoGracia.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableCondiciones.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outvariableTotalContratoDesembolso.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outVariableValidacionTempranaFlexcube.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outVariableValidarMontoMaximoDesembolso.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;
declare variable $outVariableValidarFechaVencimiento.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) external;



declare function local:funcXq_responsevalidarreglasdesembolso($outputVariable.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::), 
                                                              $outputVariableProgramacion.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::), 
                                                              $outvariableLAFT.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::), 
                                                              $outvariableSCR.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::), 
                                                              $outputVariableMora.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                              $outvariableLimites.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                              $outvariableTotalLC.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                              $outvariableLimiteGlobal.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                              $outvariableProxHoraCierre.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                              $outvariablePreviaHoraCierre.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                              $outvariablePresentacionF1.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::), 
                                                               $outvariableValidarComponenteInteres.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::), 
                                                               $outvariableValidarPlazoCapitalMinimoMaximoGracia.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                               $outvariableTotalContratoDesembolso.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                               $outvariableCondiciones.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                               $outVariableValidacionTempranaFlexcube.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::),
                                                               $outVariableValidarMontoMaximoDesembolso.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::), 
                                                               $outVariableValidarFechaVencimiento.response as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::))
                                                               as element() (:: schema-element(des:ValidarReglasDesembolsoResponse) ::) {
    <des:ValidarReglasDesembolsoResponse>
        <des:TareaReglas>
            <reg:IdTarea>{fn:data($outputVariable.response/des:TareaReglas/reg:IdTarea)}</reg:IdTarea>
            {
                if ($outputVariable.response/des:TareaReglas/reg:Proceso)
                then <reg:Proceso>{fn:data($outputVariable.response/des:TareaReglas/reg:Proceso)}</reg:Proceso>
                else ()
            }
            {
                if ($outputVariable.response/des:TareaReglas/reg:Descripcion)
                then <reg:Descripcion>{fn:data($outputVariable.response/des:TareaReglas/reg:Descripcion)}</reg:Descripcion>
                else ()
            }
            {
                if ($outputVariable.response/des:TareaReglas/reg:Estatus)
                then <reg:Estatus>{fn:data($outputVariable.response/des:TareaReglas/reg:Estatus)}</reg:Estatus>
                else ()
            }
            {
                for $ReglasProgramacion in $outputVariableProgramacion.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasProgramacion/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasProgramacion/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasProgramacion/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasProgramacion/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasProgramacion/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasProgramacion/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasProgramacion/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasProgramacion/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasProgramacion/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasProgramacion/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasProgramacion/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasProgramacion/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasProgramacion/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasProgramacion/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasProgramacion/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasProgramacion/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasProgramacion/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasProgramacion/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasProgramacion/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasProgramacion/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasProgramacion/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasProgramacion/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasProgramacion/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasProgramacion/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasProgramacion/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasProgramacion/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasProgramacion/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasProgramacion/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasProgramacion/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasProgramacion/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasProgramacion/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasProgramacion/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasProgramacion/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasProgramacion/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasProgramacion/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasProgramacion/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasProgramacion/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasProgramacion/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasProgramacion/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasProgramacion/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasProgramacion/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasProgramacion/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasProgramacion/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasProgramacion/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasProgramacion/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasProgramacion/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasProgramacion/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasProgramacion/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
            }
                        {
                for $ReglasLAFT in $outvariableLAFT.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasLAFT/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasLAFT/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasLAFT/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasLAFT/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasLAFT/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasLAFT/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasLAFT/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasLAFT/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLAFT/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLAFT/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLAFT/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLAFT/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLAFT/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasLAFT/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasLAFT/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasLAFT/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasLAFT/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLAFT/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLAFT/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLAFT/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLAFT/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLAFT/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasLAFT/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasLAFT/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasLAFT/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasLAFT/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasLAFT/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasLAFT/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLAFT/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLAFT/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLAFT/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLAFT/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLAFT/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLAFT/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasLAFT/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasLAFT/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasLAFT/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasLAFT/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasLAFT/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasLAFT/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasLAFT/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasLAFT/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasLAFT/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasLAFT/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasLAFT/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasLAFT/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasLAFT/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasLAFT/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
            }
            {
                for $ReglasSCR in $outvariableSCR.response/des:TareaReglas/reg:ReglasEvaluacion
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasSCR/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasSCR/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasSCR/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasSCR/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasSCR/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasSCR/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasSCR/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasSCR/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasSCR/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasSCR/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasSCR/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasSCR/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasSCR/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasSCR/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasSCR/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasSCR/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasSCR/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasSCR/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasSCR/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasSCR/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasSCR/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasSCR/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasSCR/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasSCR/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasSCR/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasSCR/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasSCR/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasSCR/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasSCR/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasSCR/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasSCR/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasSCR/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasSCR/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasSCR/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasSCR/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasSCR/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasSCR/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasSCR/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasSCR/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasSCR/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasSCR/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasSCR/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasSCR/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasSCR/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasSCR/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasSCR/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasSCR/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
            }
            {
                for $ReglasMora in $outputVariableMora.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasMora/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasMora/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasMora/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasMora/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasMora/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasMora/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasMora/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasMora/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasMora/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasMora/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasMora/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasMora/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasMora/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasMora/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasMora/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasMora/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasMora/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasMora/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasMora/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasMora/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasMora/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasMora/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasMora/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasMora/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasMora/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasMora/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasMora/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasMora/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasMora/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasMora/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasMora/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasMora/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasMora/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasMora/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasMora/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasMora/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasMora/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasMora/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasMora/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasMora/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasMora/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasMora/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasMora/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasMora/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasMora/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasMora/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasMora/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasMora/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
            }
            {
                for $ReglasLimites in $outvariableLimites.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasLimites/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasLimites/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasLimites/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasLimites/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasLimites/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasLimites/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasLimites/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasLimites/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLimites/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLimites/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLimites/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLimites/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLimites/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasLimites/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasLimites/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasLimites/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasLimites/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLimites/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLimites/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLimites/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLimites/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLimites/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasLimites/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasLimites/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasLimites/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasLimites/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasLimites/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasLimites/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLimites/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLimites/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLimites/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLimites/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLimites/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLimites/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasLimites/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasLimites/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasLimites/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasLimites/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasLimites/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasLimites/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasLimites/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasLimites/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasLimites/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasLimites/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasLimites/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasLimites/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasLimites/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasLimites/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                {
                for $ReglasTotalLC in $outvariableTotalLC.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasTotalLC/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasTotalLC/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasTotalLC/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasTotalLC/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasTotalLC/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasTotalLC/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasTotalLC/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasTotalLC/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasTotalLC/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasTotalLC/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasTotalLC/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasTotalLC/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasTotalLC/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasTotalLC/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasTotalLC/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasTotalLC/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasTotalLC/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasTotalLC/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasTotalLC/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasTotalLC/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasTotalLC/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasTotalLC/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasTotalLC/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasTotalLC/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasTotalLC/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasTotalLC/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasTotalLC/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasTotalLC/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasTotalLC/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasTotalLC/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasTotalLC/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasTotalLC/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasTotalLC/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasTotalLC/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasTotalLC/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasTotalLC/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasTotalLC/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasTotalLC/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasTotalLC/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasTotalLC/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasTotalLC/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasTotalLC/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasTotalLC/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasTotalLC/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasTotalLC/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasTotalLC/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasTotalLC/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasTotalLC/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                                                                                {
                for $ReglasLimiteGlobal in $outvariableLimiteGlobal.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasLimiteGlobal/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasLimiteGlobal/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasLimiteGlobal/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasLimiteGlobal/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasLimiteGlobal/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasLimiteGlobal/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasLimiteGlobal/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasLimiteGlobal/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLimiteGlobal/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLimiteGlobal/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLimiteGlobal/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLimiteGlobal/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLimiteGlobal/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasLimiteGlobal/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasLimiteGlobal/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasLimiteGlobal/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasLimiteGlobal/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLimiteGlobal/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLimiteGlobal/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLimiteGlobal/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLimiteGlobal/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLimiteGlobal/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasLimiteGlobal/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasLimiteGlobal/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasLimiteGlobal/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasLimiteGlobal/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasLimiteGlobal/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasLimiteGlobal/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLimiteGlobal/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLimiteGlobal/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLimiteGlobal/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLimiteGlobal/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteGlobal/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLimiteGlobal/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasLimiteGlobal/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasLimiteGlobal/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasLimiteGlobal/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasLimiteGlobal/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasLimiteGlobal/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasLimiteGlobal/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasLimiteGlobal/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasLimiteGlobal/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasLimiteGlobal/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasLimiteGlobal/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasLimiteGlobal/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasLimiteGlobal/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasLimiteGlobal/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasLimiteGlobal/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                {
                for $ReglasProxHoraCierre in $outvariableProxHoraCierre.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasProxHoraCierre/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasProxHoraCierre/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasProxHoraCierre/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasProxHoraCierre/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasProxHoraCierre/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasProxHoraCierre/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasProxHoraCierre/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasProxHoraCierre/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasProxHoraCierre/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasProxHoraCierre/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasProxHoraCierre/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasProxHoraCierre/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasProxHoraCierre/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasProxHoraCierre/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasProxHoraCierre/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasProxHoraCierre/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasProxHoraCierre/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasProxHoraCierre/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasProxHoraCierre/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasProxHoraCierre/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasProxHoraCierre/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasProxHoraCierre/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasProxHoraCierre/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasProxHoraCierre/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasProxHoraCierre/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasProxHoraCierre/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasProxHoraCierre/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasProxHoraCierre/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasProxHoraCierre/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasProxHoraCierre/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasProxHoraCierre/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasProxHoraCierre/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasProxHoraCierre/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasProxHoraCierre/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasProxHoraCierre/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasProxHoraCierre/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasProxHoraCierre/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasProxHoraCierre/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasProxHoraCierre/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasProxHoraCierre/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasProxHoraCierre/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasProxHoraCierre/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasProxHoraCierre/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasProxHoraCierre/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasProxHoraCierre/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasProxHoraCierre/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasProxHoraCierre/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasProxHoraCierre/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                                {
                for $ReglasPreviaHoraCierre in $outvariablePreviaHoraCierre.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasPreviaHoraCierre/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasPreviaHoraCierre/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasPreviaHoraCierre/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasPreviaHoraCierre/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasPreviaHoraCierre/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasPreviaHoraCierre/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasPreviaHoraCierre/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasPreviaHoraCierre/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasPreviaHoraCierre/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasPreviaHoraCierre/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasPreviaHoraCierre/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasPreviaHoraCierre/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasPreviaHoraCierre/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasPreviaHoraCierre/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasPreviaHoraCierre/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasPreviaHoraCierre/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasPreviaHoraCierre/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasPreviaHoraCierre/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasPreviaHoraCierre/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasPreviaHoraCierre/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasPreviaHoraCierre/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasPreviaHoraCierre/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasPreviaHoraCierre/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasPreviaHoraCierre/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasPreviaHoraCierre/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasPreviaHoraCierre/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasPreviaHoraCierre/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasPreviaHoraCierre/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasPreviaHoraCierre/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasPreviaHoraCierre/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasPreviaHoraCierre/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasPreviaHoraCierre/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasPreviaHoraCierre/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasPreviaHoraCierre/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasPreviaHoraCierre/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasPreviaHoraCierre/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasPreviaHoraCierre/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasPreviaHoraCierre/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasPreviaHoraCierre/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasPreviaHoraCierre/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasPreviaHoraCierre/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                {
                for $ReglasPresentacionF1 in $outvariablePresentacionF1.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasPresentacionF1/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasPresentacionF1/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasPresentacionF1/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasPresentacionF1/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasPresentacionF1/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasPresentacionF1/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasPresentacionF1/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasPresentacionF1/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasPresentacionF1/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasPresentacionF1/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasPresentacionF1/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasPresentacionF1/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasPresentacionF1/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasPresentacionF1/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasPresentacionF1/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasPresentacionF1/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasPresentacionF1/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasPresentacionF1/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasPresentacionF1/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasPresentacionF1/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasPresentacionF1/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasPresentacionF1/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasPresentacionF1/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasPresentacionF1/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasPresentacionF1/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasPresentacionF1/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasPresentacionF1/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasPresentacionF1/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasPresentacionF1/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasPresentacionF1/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasPresentacionF1/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasPresentacionF1/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasPresentacionF1/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasPresentacionF1/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasPresentacionF1/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasPresentacionF1/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasPresentacionF1/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasPresentacionF1/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasPresentacionF1/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasPresentacionF1/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasPresentacionF1/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasPresentacionF1/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasPresentacionF1/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasPresentacionF1/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasPresentacionF1/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasPresentacionF1/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasPresentacionF1/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasPresentacionF1/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                
                 {
                for $ReglasComponenteInteres in $outvariableValidarComponenteInteres.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasComponenteInteres/reg1:Id/text()) > 0
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasComponenteInteres/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasComponenteInteres/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasComponenteInteres/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasComponenteInteres/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasComponenteInteres/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasComponenteInteres/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasComponenteInteres/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasComponenteInteres/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasComponenteInteres/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasComponenteInteres/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasComponenteInteres/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasComponenteInteres/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasComponenteInteres/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasComponenteInteres/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasComponenteInteres/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasComponenteInteres/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasComponenteInteres/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasComponenteInteres/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasComponenteInteres/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasComponenteInteres/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasComponenteInteres/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasComponenteInteres/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasComponenteInteres/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasComponenteInteres/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasComponenteInteres/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasComponenteInteres/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasComponenteInteres/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasComponenteInteres/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasComponenteInteres/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasComponenteInteres/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasComponenteInteres/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasComponenteInteres/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasComponenteInteres/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasComponenteInteres/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasComponenteInteres/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasComponenteInteres/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasComponenteInteres/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasComponenteInteres/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasComponenteInteres/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasComponenteInteres/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasComponenteInteres/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasComponenteInteres/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasComponenteInteres/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasComponenteInteres/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasComponenteInteres/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasComponenteInteres/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasComponenteInteres/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                {
                for $ReglasPlazoCapitalMinimoMaximoGracia at $pos in $outvariableValidarPlazoCapitalMinimoMaximoGracia.response/des:TareaReglas/reg:ReglasEvaluacion
                return 
                <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>
                                    {
                                      if($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:DescripcionCorta = 'NO_EVALUADA')
                                      then
                                       'CUMPLIDA'
                                      else
                                        fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:DescripcionCorta)
                                    }
                                    </cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasPlazoCapitalMinimoMaximoGracia/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                                  {
                for $ReglasCondiciones in $outvariableCondiciones.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasCondiciones/reg1:Id/text()) > 0
                return 
             <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasCondiciones/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasCondiciones/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasCondiciones/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasCondiciones/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasCondiciones/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasCondiciones/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasCondiciones/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasCondiciones/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasCondiciones/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasCondiciones/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasCondiciones/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasCondiciones/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasCondiciones/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasCondiciones/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasCondiciones/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasCondiciones/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasCondiciones/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasCondiciones/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasCondiciones/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasCondiciones/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasCondiciones/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasCondiciones/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasCondiciones/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasCondiciones/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasCondiciones/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasCondiciones/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasCondiciones/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasCondiciones/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasCondiciones/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasCondiciones/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasCondiciones/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasCondiciones/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasCondiciones/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasCondiciones/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasCondiciones/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasCondiciones/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasCondiciones/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasCondiciones/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasCondiciones/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasCondiciones/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasCondiciones/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasCondiciones/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasCondiciones/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasCondiciones/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasCondiciones/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasCondiciones/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasCondiciones/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }       
                 {
                for $ReglasLimiteDestino in $outvariableTotalContratoDesembolso.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasLimiteDestino/reg1:Id/text()) > 0
                return 
             <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasLimiteDestino/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasLimiteDestino/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasLimiteDestino/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasLimiteDestino/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasLimiteDestino/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasLimiteDestino/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasLimiteDestino/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLimiteDestino/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLimiteDestino/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLimiteDestino/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLimiteDestino/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLimiteDestino/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasLimiteDestino/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasLimiteDestino/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasLimiteDestino/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasLimiteDestino/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLimiteDestino/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLimiteDestino/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLimiteDestino/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLimiteDestino/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLimiteDestino/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasLimiteDestino/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasLimiteDestino/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasLimiteDestino/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasLimiteDestino/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasLimiteDestino/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasLimiteDestino/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasLimiteDestino/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasLimiteDestino/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasLimiteDestino/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasLimiteDestino/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasLimiteDestino/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasLimiteDestino/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasLimiteDestino/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasLimiteDestino/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasLimiteDestino/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasLimiteDestino/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasLimiteDestino/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasLimiteDestino/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasLimiteDestino/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasLimiteDestino/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasLimiteDestino/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasLimiteDestino/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasLimiteDestino/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasLimiteDestino/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasLimiteDestino/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasLimiteDestino/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                
                {
                for $ReglasValidacionTemprana in $outVariableValidacionTempranaFlexcube.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasValidacionTemprana/reg1:Id/text()) > 0
                return 
             <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasValidacionTemprana/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasValidacionTemprana/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasValidacionTemprana/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasValidacionTemprana/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasValidacionTemprana/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasValidacionTemprana/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasValidacionTemprana/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasValidacionTemprana/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasValidacionTemprana/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasValidacionTemprana/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasValidacionTemprana/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasValidacionTemprana/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasValidacionTemprana/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasValidacionTemprana/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasValidacionTemprana/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasValidacionTemprana/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasValidacionTemprana/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasValidacionTemprana/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasValidacionTemprana/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasValidacionTemprana/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasValidacionTemprana/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasValidacionTemprana/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasValidacionTemprana/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasValidacionTemprana/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasValidacionTemprana/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasValidacionTemprana/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasValidacionTemprana/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasValidacionTemprana/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasValidacionTemprana/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasValidacionTemprana/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasValidacionTemprana/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasValidacionTemprana/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasValidacionTemprana/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasValidacionTemprana/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasValidacionTemprana/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasValidacionTemprana/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasValidacionTemprana/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasValidacionTemprana/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasValidacionTemprana/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasValidacionTemprana/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasValidacionTemprana/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasValidacionTemprana/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasValidacionTemprana/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasValidacionTemprana/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasValidacionTemprana/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasValidacionTemprana/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasValidacionTemprana/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                {
                for $ReglasValidarMontoMaximoDesembolso in $outVariableValidarMontoMaximoDesembolso.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasValidarMontoMaximoDesembolso/reg1:Id/text()) > 0
                return 
             <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasValidarMontoMaximoDesembolso/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasValidarMontoMaximoDesembolso/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasValidarMontoMaximoDesembolso/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasValidarMontoMaximoDesembolso/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasValidarMontoMaximoDesembolso/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasValidarMontoMaximoDesembolso/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasValidarMontoMaximoDesembolso/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasValidarMontoMaximoDesembolso/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasValidarMontoMaximoDesembolso/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasValidarMontoMaximoDesembolso/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasValidarMontoMaximoDesembolso/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasValidarMontoMaximoDesembolso/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }
                {
                for $ReglasValidaFechaVencimiento in $outVariableValidarFechaVencimiento.response/des:TareaReglas/reg:ReglasEvaluacion
                where string-length($ReglasValidaFechaVencimiento/reg1:Id/text()) > 0
                return 
             <reg:ReglasEvaluacion>
                    <reg1:Id>{fn:data($ReglasValidaFechaVencimiento/reg1:Id)}</reg1:Id>
                    {
                        if ($ReglasValidaFechaVencimiento/reg1:Descripcion)
                        then <reg1:Descripcion>{fn:data($ReglasValidaFechaVencimiento/reg1:Descripcion)}</reg1:Descripcion>
                        else ()
                    }
                    {
                        if ($ReglasValidaFechaVencimiento/reg1:Transaccion)
                        then <reg1:Transaccion>{fn:data($ReglasValidaFechaVencimiento/reg1:Transaccion)}</reg1:Transaccion>
                        else ()
                    }
                    {
                        if ($ReglasValidaFechaVencimiento/reg1:Tipo)
                        then 
                            <reg1:Tipo>
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ReglasValidaFechaVencimiento/reg1:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasValidaFechaVencimiento/reg1:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasValidaFechaVencimiento/reg1:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasValidaFechaVencimiento/reg1:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasValidaFechaVencimiento/reg1:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Tipo>
                        else ()
                    }
                    {
                        if ($ReglasValidaFechaVencimiento/reg1:PosibleExceptuar)
                        then <reg1:PosibleExceptuar>{fn:data($ReglasValidaFechaVencimiento/reg1:PosibleExceptuar)}</reg1:PosibleExceptuar>
                        else ()
                    }
                    {
                        if ($ReglasValidaFechaVencimiento/reg1:Exceptuado)
                        then 
                            <reg1:Exceptuado>
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasValidaFechaVencimiento/reg1:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Exceptuado>
                        else ()
                    }
                    {
                        if ($ReglasValidaFechaVencimiento/reg1:UsuarioExceptua)
                        then <reg1:UsuarioExceptua>{fn:data($ReglasValidaFechaVencimiento/reg1:UsuarioExceptua)}</reg1:UsuarioExceptua>
                        else ()
                    }
                    {
                        if ($ReglasValidaFechaVencimiento/reg1:FechaExcepcion)
                        then <reg1:FechaExcepcion>{fn:data($ReglasValidaFechaVencimiento/reg1:FechaExcepcion)}</reg1:FechaExcepcion>
                        else ()
                    }
                    {
                        if ($ReglasValidaFechaVencimiento/reg1:Estado)
                        then 
                            <reg1:Estado>
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Estado/cat:Id)
                                    then <cat:Id>{fn:data($ReglasValidaFechaVencimiento/reg1:Estado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Estado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ReglasValidaFechaVencimiento/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Estado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ReglasValidaFechaVencimiento/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Estado/cat:estatus)
                                    then <cat:estatus>{fn:data($ReglasValidaFechaVencimiento/reg1:Estado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ReglasValidaFechaVencimiento/reg1:Estado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ReglasValidaFechaVencimiento/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </reg1:Estado>
                        else ()
                    }
                    {
                        if ($ReglasValidaFechaVencimiento/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasValidaFechaVencimiento/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasValidaFechaVencimiento/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasValidaFechaVencimiento/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        {
                            if ($ReglasValidaFechaVencimiento/reg:Mensaje/cat:Id)
                            then <cat:Id>{fn:data($ReglasValidaFechaVencimiento/reg:Mensaje/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ReglasValidaFechaVencimiento/reg:Mensaje/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ReglasValidaFechaVencimiento/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ReglasValidaFechaVencimiento/reg:Mensaje/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ReglasValidaFechaVencimiento/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ReglasValidaFechaVencimiento/reg:Mensaje/cat:estatus)
                            then <cat:estatus>{fn:data($ReglasValidaFechaVencimiento/reg:Mensaje/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ReglasValidaFechaVencimiento/reg:Mensaje/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ReglasValidaFechaVencimiento/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
                }

        </des:TareaReglas>
        <des:Resultado>
            <res:result>OK</res:result>
            <res:message>Validación Exitosa</res:message>
        </des:Resultado>
    </des:ValidarReglasDesembolsoResponse>
};

local:funcXq_responsevalidarreglasdesembolso($outputVariable.response, $outputVariableProgramacion.response, $outvariableLAFT.response, $outvariableSCR.response, $outputVariableMora.response, $outvariableLimites.response, $outvariableTotalLC.response, $outvariableLimiteGlobal.response, $outvariableProxHoraCierre.response, $outvariablePreviaHoraCierre.response, $outvariablePresentacionF1.response, $outvariableValidarComponenteInteres.response, $outvariableValidarPlazoCapitalMinimoMaximoGracia.response,$outvariableCondiciones.response,$outvariableTotalContratoDesembolso.response, $outVariableValidacionTempranaFlexcube.response, $outVariableValidarMontoMaximoDesembolso.response, $outVariableValidarFechaVencimiento.response)
