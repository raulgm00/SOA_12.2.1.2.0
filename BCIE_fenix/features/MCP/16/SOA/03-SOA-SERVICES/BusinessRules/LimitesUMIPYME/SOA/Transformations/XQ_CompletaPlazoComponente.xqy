xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $outConsultarPlazoComponente.response as element() (:: schema-element(des:ConsultarPlazoComponenteResponse) ::) external;

declare function local:funcXq_completaplazocomponente($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::), 
                                                      $outConsultarPlazoComponente.response as element() (:: schema-element(des:ConsultarPlazoComponenteResponse) ::)) 
                                                      as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) {
    <des:ConsultarDesembolsosByIdResponse>
        <des:ContratoDesembolso>
            <des1:producto>
                {
                    for $Componente in $outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:producto/des1:Componente
                    where $Componente/cat:codigoExterno = $outConsultarPlazoComponente.response/des:Componente/cat:codigoExterno
                    return 
                    <des1:Componente>
                        {
                            if ($Componente/cat:Id)
                            then <cat:Id>{fn:data($Componente/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Componente/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Componente/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Componente/cat:DescripcionCorta)
                            then 
                                <cat:DescripcionCorta>{fn:data($Componente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Componente/cat:estatus)
                            then <cat:estatus>{fn:data($Componente/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Componente/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Componente/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                        <des1:TipoComponente>
                            {
                                if ($Componente/des1:TipoComponente)
                                then $Componente/des1:TipoComponente/*
                                else ()
                            }
                        </des1:TipoComponente>
                        <des1:TipoTasa>
                            {
                                if ($Componente/des1:TipoTasa)
                                then $Componente/des1:TipoTasa/*
                                else ()
                            }
                        </des1:TipoTasa>
                        <des1:esPrincipal>{fn:data($Componente/des1:esPrincipal)}</des1:esPrincipal>
                        <des1:Plazo>
                             {
                                if ($outConsultarPlazoComponente.response/des:Componente[cat:codigoExterno = $Componente/cat:codigoExterno]/des1:Plazo)
                                then $outConsultarPlazoComponente.response/des:Componente[cat:codigoExterno = $Componente/cat:codigoExterno]/des1:Plazo/*
                                else ()
                            }
                        </des1:Plazo>
                    </des1:Componente>
                }
            </des1:producto>
        </des:ContratoDesembolso>
    </des:ConsultarDesembolsosByIdResponse>
};

local:funcXq_completaplazocomponente($outConsultarDesembolsoById.response, $outConsultarPlazoComponente.response)
