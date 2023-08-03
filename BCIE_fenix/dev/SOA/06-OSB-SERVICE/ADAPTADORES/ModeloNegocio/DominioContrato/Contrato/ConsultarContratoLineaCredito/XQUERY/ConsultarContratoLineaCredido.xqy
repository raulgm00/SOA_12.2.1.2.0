xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)


declare variable $ConsultarLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoResponse) ::) external;

declare function local:func($ConsultarLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoResponse) ::)) as element() (:: schema-element(ns1:ConsultarLineaCreditoResponse) ::) {

    <ns1:ConsultarLineaCreditoResponse>
    </ns1:ConsultarLineaCreditoResponse>
};

local:func($ConsultarLineaCreditoResponse)
