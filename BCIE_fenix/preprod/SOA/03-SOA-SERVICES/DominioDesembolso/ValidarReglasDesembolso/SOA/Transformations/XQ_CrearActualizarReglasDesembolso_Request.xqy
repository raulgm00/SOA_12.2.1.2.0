xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare variable $inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::) external;
declare variable $consultarReglasDesembolsoAux.response as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::) external;

declare function local:funcXq_crearactualizarreglasdesembolso_request($inputVariable.request as element() (:: schema-element(des:ValidarReglasDesembolsoRequest) ::), 
                                                                      $consultarReglasDesembolsoAux.response as element() (:: schema-element(des:ConsultarReglasDesembolsoResponse) ::)) 
                                                                      as element() (:: schema-element(des:CrearActualizarReglasDesembolsoRequest) ::) {
    <des:CrearActualizarReglasDesembolsoRequest>
        <des:idDesembolso>{fn:data($inputVariable.request/des:idDesembolso)}</des:idDesembolso>
        {
        for $reglaDesembolso in $consultarReglasDesembolsoAux.response/des:ReglasEvaluacion
        return
        <des:ReglasDesembolso>
            <reg:Id>{fn:data($reglaDesembolso/reg:Id)}</reg:Id>
            {
                if ($reglaDesembolso/reg:Descripcion)
                then <reg:Descripcion>{fn:data($reglaDesembolso/reg:Descripcion)}</reg:Descripcion>
                else ()
            }
            {
                if ($reglaDesembolso/reg:Transaccion)
                then <reg:Transaccion>{fn:data($reglaDesembolso/reg:Transaccion)}</reg:Transaccion>
                else ()
            }
            {
                if ($reglaDesembolso/reg:Tipo)
                then 
                    <reg:Tipo>
                        {
                            if ($reglaDesembolso/reg:Tipo/cat:Id)
                            then <cat:Id>{fn:data($reglaDesembolso/reg:Tipo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($reglaDesembolso/reg:Tipo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($reglaDesembolso/reg:Tipo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($reglaDesembolso/reg:Tipo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($reglaDesembolso/reg:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($reglaDesembolso/reg:Tipo/cat:estatus)
                            then <cat:estatus>{fn:data($reglaDesembolso/reg:Tipo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($reglaDesembolso/reg:Tipo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($reglaDesembolso/reg:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Tipo>
                else ()
            }
            <reg:PosibleExceptuar></reg:PosibleExceptuar>
                    <reg:Exceptuado>
                        {
                            if ($reglaDesembolso/reg:Exceptuado/cat:Id)
                            then <cat:Id>{fn:data($reglaDesembolso/reg:Exceptuado/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($reglaDesembolso/reg:Exceptuado/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($reglaDesembolso/reg:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($reglaDesembolso/reg:Exceptuado/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($reglaDesembolso/reg:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        <cat:estatus>{false()}</cat:estatus>
                        {
                            if ($reglaDesembolso/reg:Exceptuado/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($reglaDesembolso/reg:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Exceptuado>
            <reg:Estado>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </reg:Estado>
            <reg:Estatus></reg:Estatus>
        </des:ReglasDesembolso>
        }
    </des:CrearActualizarReglasDesembolsoRequest>
};

local:funcXq_crearactualizarreglasdesembolso_request($inputVariable.request, $consultarReglasDesembolsoAux.response)
