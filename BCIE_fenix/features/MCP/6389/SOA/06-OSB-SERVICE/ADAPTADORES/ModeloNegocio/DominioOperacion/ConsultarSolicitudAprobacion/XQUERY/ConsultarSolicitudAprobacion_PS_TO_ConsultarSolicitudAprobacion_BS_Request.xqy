xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSolicitudAprobacion";
(:: import schema at "../XSD/ConsultarSolicitudAprobacion.xsd" ::)

declare variable $ConsultarSolicitudAprobacionRequest as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionRequest) ::) external;

declare function local:func($ConsultarSolicitudAprobacionRequest as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarSolicitudAprobacionInput) ::) {
    <ns2:ConsultarSolicitudAprobacionInput>
        <ns2:ID_OPERACION>{fn:data($ConsultarSolicitudAprobacionRequest/ns1:idOperacion)}</ns2:ID_OPERACION>
    </ns2:ConsultarSolicitudAprobacionInput>
};

local:func($ConsultarSolicitudAprobacionRequest)
