xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare variable $ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare function local:func($ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) as element() (:: schema-element(ns1:ConsultarListaLineasCreditoResponse) ::) {
    <ns1:ConsultarListaLineasCreditoResponse/>
};

local:func($ConsultarLineaCreditoByIdLineaCreditoResponse)
