xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarMontoOperacion_db";
(:: import schema at "../../ConsultarMontoOperacion/XSD/ConsultarMontoOperacion_db.xsd" ::)

declare variable $RequestConsultarMontoOperacion as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::) external;

declare function local:func($RequestConsultarMontoOperacion as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarMontoOperacion_dbInput) ::) {
    <ns2:ConsultarMontoOperacion_dbInput>
        <ns2:P_ID_OPERACION>{fn:data($RequestConsultarMontoOperacion/ns1:idOperacion)}</ns2:P_ID_OPERACION>
    </ns2:ConsultarMontoOperacion_dbInput>
};

local:func($RequestConsultarMontoOperacion)