xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarFuente";
(:: import schema at "../../ConsultarFuente/XSD/ConsultarFuente.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCredito";
(:: import schema at "../XSD/ConsultarLineaCredito.xsd" ::)

declare variable $ConsultarLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarLineaCreditoOutputCollection) ::) external;

declare function local:func($ConsultarLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarLineaCreditoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarFuenteInput) ::) {
    <ns2:ConsultarFuenteInput>
        <ns2:P_ID_LC>{fn:data($ConsultarLineaCreditoOutputCollection/ns1:ConsultarLineaCreditoOutput/ns1:ID)}</ns2:P_ID_LC>
    </ns2:ConsultarFuenteInput>
};

local:func($ConsultarLineaCreditoOutputCollection)
