xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAprobacionById";
(:: import schema at "../XSD/ConsultarAprobacionById.xsd" ::)

declare variable $ConsultarAprobacionByIdRequest as element() (:: schema-element(ns1:ConsultarAprobacionByIdRequest) ::) external;

declare function local:func($ConsultarAprobacionByIdRequest as element() (:: schema-element(ns1:ConsultarAprobacionByIdRequest) ::)) as element() (:: schema-element(ns2:ConsultarAprobacionByIdInput) ::) {
    <ns2:ConsultarAprobacionByIdInput>
        <ns2:idAprobacion>{fn:data($ConsultarAprobacionByIdRequest/ns1:idAprobacion)}</ns2:idAprobacion>
    </ns2:ConsultarAprobacionByIdInput>
};

local:func($ConsultarAprobacionByIdRequest)
