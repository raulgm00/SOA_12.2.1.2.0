xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare variable $Inicial as xs:string external;

declare function local:func($Inicial as xs:string) as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) {
    <ns1:ConsultarLineaCreditoByIdLineaCreditoResponse>
    </ns1:ConsultarLineaCreditoByIdLineaCreditoResponse>
};

local:func($Inicial)
