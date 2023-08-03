xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare variable $ExceptuarSolicitudRequest as element() (:: schema-element(ns1:ExceptuarSolicitudRequest) ::) external;

declare function local:func($ExceptuarSolicitudRequest as element() (:: schema-element(ns1:ExceptuarSolicitudRequest) ::)) as element() (:: schema-element(ns1:ConsultarExcepcionSolicitudRequest) ::) {
    <ns1:ConsultarExcepcionSolicitudRequest>
        <ns1:idSolicitud>{fn:data($ExceptuarSolicitudRequest/ns1:idSolicitud)}</ns1:idSolicitud>
    </ns1:ConsultarExcepcionSolicitudRequest>
};

local:func($ExceptuarSolicitudRequest)
