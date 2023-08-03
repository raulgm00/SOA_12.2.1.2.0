xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultaraprobacionByIdOperacion_DB";
(:: import schema at "../XSD/ConsultaraprobacionByIdOperacion_DB.xsd" ::)

declare variable $ConsultarAprobacionByIdOperacionRequest as element() (:: schema-element(ns1:ConsultarAprobacionByIdOperacionRequest) ::) external;

declare function local:func($ConsultarAprobacionByIdOperacionRequest as element() (:: schema-element(ns1:ConsultarAprobacionByIdOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultaraprobacionByIdOperacion_DBInput) ::) {
    <ns2:ConsultaraprobacionByIdOperacion_DBInput>
        <ns2:idOperacion>{fn:data($ConsultarAprobacionByIdOperacionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultaraprobacionByIdOperacion_DBInput>
};

local:func($ConsultarAprobacionByIdOperacionRequest)
