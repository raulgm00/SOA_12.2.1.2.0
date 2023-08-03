xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare variable $ConsultarLineasPasivasResponse as element() (:: schema-element(ns1:ConsultarLineasPasivasResponse) ::) external;

declare function local:func($ConsultarLineasPasivasResponse as element() (:: schema-element(ns1:ConsultarLineasPasivasResponse) ::)) as element() (:: schema-element(ns1:ConsultarLineasPasivasResponse) ::) {
    <ns1:ConsultarLineasPasivasResponse>
    </ns1:ConsultarLineasPasivasResponse>
};

local:func($ConsultarLineasPasivasResponse)
