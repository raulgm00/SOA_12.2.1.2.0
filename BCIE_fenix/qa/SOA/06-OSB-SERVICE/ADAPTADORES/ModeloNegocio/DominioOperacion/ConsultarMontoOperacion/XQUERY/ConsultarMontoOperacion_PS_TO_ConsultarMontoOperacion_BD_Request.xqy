xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarMontoOperacion_db";
(:: import schema at "../XSD/ConsultarMontoOperacion_db.xsd" ::)

declare variable $RequestMontoOperacion as element() (:: schema-element(ns1:ConsultarMontoOperacionRequest) ::) external;

declare function local:func($RequestMontoOperacion as element() (:: schema-element(ns1:ConsultarMontoOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarMontoOperacion_dbInput) ::) {
    <ns2:ConsultarMontoOperacion_dbInput>
        <ns2:P_ID_OPERACION>{fn:data($RequestMontoOperacion/ns1:idOperacion)}</ns2:P_ID_OPERACION>
        <ns2:P_ID_TCA_TIPO_MONTO>{fn:data($RequestMontoOperacion/ns1:IdTcaTipoMonto)}</ns2:P_ID_TCA_TIPO_MONTO>
    </ns2:ConsultarMontoOperacion_dbInput>
};

local:func($RequestMontoOperacion)