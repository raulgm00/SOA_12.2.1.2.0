xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare variable $ConsultarSaldoLineaRequest as element() (:: schema-element(ns1:ConsultarSaldoLineaRequest) ::) external;

declare function local:func($ConsultarSaldoLineaRequest as element() (:: schema-element(ns1:ConsultarSaldoLineaRequest) ::)) as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoRequest) ::) {
    <ns1:ConsultarLineaCreditoByIdLineaCreditoRequest>
        <ns1:idLineaCredito>{fn:data($ConsultarSaldoLineaRequest/ns1:idLineaCredito)}</ns1:idLineaCredito>
    </ns1:ConsultarLineaCreditoByIdLineaCreditoRequest>
};

local:func($ConsultarSaldoLineaRequest)
