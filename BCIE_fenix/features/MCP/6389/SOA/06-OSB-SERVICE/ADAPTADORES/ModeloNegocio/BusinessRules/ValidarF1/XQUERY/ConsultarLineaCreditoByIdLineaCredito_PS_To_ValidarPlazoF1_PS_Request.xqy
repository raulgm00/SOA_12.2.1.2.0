xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare function local:func($ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::), 
                            $ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) 
                            as element() (:: schema-element(ns1:ValidarPlazoF1Request) ::) {
    <ns1:ValidarPlazoF1Request>
        <ns1:LineaCredito>
            <lin:NumeroLineaCredito>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns2:Operacion/ope:contrato/con1:LineaCredito/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
            <lin:ContratoDesembolso>
                <des:Programa>
                    <her:LineaFinanciera>
                        {
                            if ($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </her:LineaFinanciera>
                </des:Programa>
            </lin:ContratoDesembolso>
        </ns1:LineaCredito>
    </ns1:ValidarPlazoF1Request>
};

local:func($ConsultarDesembolsosByIdResponse, $ConsultarLineaCreditoByIdLineaCreditoResponse)
