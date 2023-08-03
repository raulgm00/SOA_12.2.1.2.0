xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCreditoByIdOperacion_DB";
(:: import schema at "../XSD/ConsultarLineaCreditoByIdOperacion_DB.xsd" ::)

declare variable $ConsultarLineaCreditoByIdOperacionRequest as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdOperacionRequest) ::) external;

declare function local:func($ConsultarLineaCreditoByIdOperacionRequest as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdOperacion_DBInput) ::) {
    <ns2:ConsultarLineaCreditoByIdOperacion_DBInput>
        <ns2:IDOPERACION>{fn:data($ConsultarLineaCreditoByIdOperacionRequest/ns1:IdOperacion)}</ns2:IDOPERACION>
    </ns2:ConsultarLineaCreditoByIdOperacion_DBInput>
};

local:func($ConsultarLineaCreditoByIdOperacionRequest)
