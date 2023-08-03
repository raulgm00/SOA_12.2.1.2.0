xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $PropagarFuenteLCRequest as element() (:: schema-element(ns1:PropagarFuenteLCRequest) ::) external;
declare variable $indfuente as xs:int external;
declare function local:func($PropagarFuenteLCRequest as element() (:: schema-element(ns1:PropagarFuenteLCRequest) ::),$countfuente as xs:int) as element() (:: schema-element(ns1:ConsultarLineaCreditoFuenteRequest) ::) {
    <ns1:ConsultarLineaCreditoFuenteRequest>
        <ns1:idLineaCredito>{fn:data($PropagarFuenteLCRequest/ns1:LineasCredito[$indfuente])}</ns1:idLineaCredito>
        </ns1:ConsultarLineaCreditoFuenteRequest>
};

local:func($PropagarFuenteLCRequest,$indfuente)
