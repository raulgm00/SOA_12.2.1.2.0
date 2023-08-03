xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $ObtenerLineasCredito as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare function local:func($ObtenerLineasCredito as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) {
    <ns1:ConsultarLineaCreditoByIdLineaCreditoResponse>
        <ns1:Operacion>
            <ope:contrato>
                { for $lineaCredito in $ObtenerLineasCredito/ns1:Operacion/ope:contrato/con:LineaCredito
                return
                <con:LineaCredito>
                    <lin:idLineaCredito>{fn:data($lineaCredito/lin:idLineaCredito)}</lin:idLineaCredito>
                </con:LineaCredito>
                }
            </ope:contrato>
        </ns1:Operacion>
    </ns1:ConsultarLineaCreditoByIdLineaCreditoResponse>
};

local:func($ObtenerLineasCredito)
