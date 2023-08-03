xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCreditoByIdLineaCredito";
(:: import schema at "../XSD/ConsultarLineaCreditoByIdLineaCredito.xsd" ::)

declare variable $ConsultarLineaCredito as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoRequest) ::) external;

declare function local:func($ConsultarLineaCredito as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoInput) ::) {
    <ns2:ConsultarLineaCreditoByIdLineaCreditoInput>
        <ns2:idLineaCredito>{fn:data($ConsultarLineaCredito/ns1:idLineaCredito)}</ns2:idLineaCredito>
        <ns2:numeroLinea>{fn:data($ConsultarLineaCredito/ns1:numeroLinea)}</ns2:numeroLinea>
    </ns2:ConsultarLineaCreditoByIdLineaCreditoInput>
};

local:func($ConsultarLineaCredito)
