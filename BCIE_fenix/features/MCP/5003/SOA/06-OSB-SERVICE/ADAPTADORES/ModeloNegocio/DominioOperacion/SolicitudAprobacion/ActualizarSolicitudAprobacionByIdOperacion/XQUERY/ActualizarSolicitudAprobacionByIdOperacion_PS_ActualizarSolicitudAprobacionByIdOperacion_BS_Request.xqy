xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarStatusSolicitudAprobacionByOperacion_DB";
(:: import schema at "../XSD/ActualizarStatusSolicitudAprobacionByOperacion_DB.xsd" ::)

declare variable $ActualizarSolicitudAprobacionByIdOperacionRequest as element() (:: schema-element(ns1:ActualizarSolicitudAprobacionByIdOperacionRequest) ::) external;

declare function local:func($ActualizarSolicitudAprobacionByIdOperacionRequest as element() (:: schema-element(ns1:ActualizarSolicitudAprobacionByIdOperacionRequest) ::)) as element() (:: schema-element(ns2:ActualizarStatusSolicitudAprobacionByOperacion_DBInput) ::) {
    <ns2:ActualizarStatusSolicitudAprobacionByOperacion_DBInput>
        <ns2:idOperacion>{fn:data($ActualizarSolicitudAprobacionByIdOperacionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ActualizarStatusSolicitudAprobacionByOperacion_DBInput>
};

local:func($ActualizarSolicitudAprobacionByIdOperacionRequest)
