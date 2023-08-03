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

declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:func($ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::)) as element() (:: schema-element(ns2:ConsultarSaldoFLEXCUBERequest) ::) {
    <ns2:ConsultarSaldoFLEXCUBERequest>
        <ns2:LineaCredito>
            <lin:Flexcube>
                <lin:id>{string(fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:idFacturador))}</lin:id>
            </lin:Flexcube>
        </ns2:LineaCredito>
    </ns2:ConsultarSaldoFLEXCUBERequest>
};

local:func($ConsultarDesembolsosByIdResponse)
