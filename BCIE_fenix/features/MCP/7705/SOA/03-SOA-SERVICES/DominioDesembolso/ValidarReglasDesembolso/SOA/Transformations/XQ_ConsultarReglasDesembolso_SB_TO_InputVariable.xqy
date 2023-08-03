xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaTareaBO";

declare namespace reg1 = "http://www.bcie.org/ReglaBO";

declare variable $inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) external;
declare variable $outConsultarReglasDesembolso.response as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::) external;

declare function local:funcXq_consultarreglasdesembolso_sb_to_inputvariable($inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::), 
                                                                            $outConsultarReglasDesembolso.response as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::)) 
                                                                            as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) {
    <des:ValidarReglasDesembolsoRequest>
        <des:idDesembolso>{fn:data($inputVariable.request/des:idDesembolso)}</des:idDesembolso>
        <des:instanciaProceso>{fn:data($inputVariable.request/des:instanciaProceso)}</des:instanciaProceso>
        <des:TareaReglas>
            <reg:IdTarea>{fn:data($inputVariable.request/des:TareaReglas/reg:IdTarea)}</reg:IdTarea>
            {
                if ($inputVariable.request/des:TareaReglas/reg:Proceso)
                then <reg:Proceso>{fn:data($inputVariable.request/des:TareaReglas/reg:Proceso)}</reg:Proceso>
                else ()
            }
            {
                if ($inputVariable.request/des:TareaReglas/reg:Descripcion)
                then <reg:Descripcion>{fn:data($inputVariable.request/des:TareaReglas/reg:Descripcion)}</reg:Descripcion>
                else ()
            }
            {
                if ($inputVariable.request/des:TareaReglas/reg:Estatus)
                then <reg:Estatus>{fn:data($inputVariable.request/des:TareaReglas/reg:Estatus)}</reg:Estatus>
                else ()
            }
            {
                for $ReglasEvaluacion in $outConsultarReglasDesembolso.response/des:ReglasEvaluacion
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
                    <reg1:Estado>
                          <cat:Id>{fn:data($ReglasEvaluacion/reg1:Estado/cat:Id)}</cat:Id>
                          <cat:Descripcion>{fn:data($ReglasEvaluacion/reg1:Estado/cat:Descripcion)}</cat:Descripcion>
                          <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg1:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                          <cat:estatus>{fn:data($ReglasEvaluacion/reg1:Estado/cat:estatus)}</cat:estatus>
                          <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg1:Estado/cat:codigoExterno)}</cat:codigoExterno>
                    </reg1:Estado>
                    {
                        if ($ReglasEvaluacion/reg1:Estatus)
                        then <reg1:Estatus>{fn:data($ReglasEvaluacion/reg1:Estatus)}</reg1:Estatus>
                        else ()
                    }
                    <reg:EsAdvertencia>{fn:data($ReglasEvaluacion/reg:EsAdvertencia)}</reg:EsAdvertencia>
                    <reg:EsError>{fn:data($ReglasEvaluacion/reg:EsError)}</reg:EsError>
                    <reg:Mensaje>
                        <cat:Id>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:Id)}</cat:Id>
                        <cat:Descripcion>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:Descripcion)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        <cat:estatus>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:estatus)}</cat:estatus>
                        <cat:codigoExterno>{fn:data($ReglasEvaluacion/reg:Mensaje/cat:codigoExterno)}</cat:codigoExterno>
                    </reg:Mensaje>
                </reg:ReglasEvaluacion>
            }
        </des:TareaReglas>
    </des:ValidarReglasDesembolsoRequest>
};

local:funcXq_consultarreglasdesembolso_sb_to_inputvariable($inputVariable.request, $outConsultarReglasDesembolso.response)
