xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarExcepcionSolicitud";
(:: import schema at "../XSD/ConsultarExcepcionSolicitud_table.xsd" ::)

declare variable $ConsultarExcepcionSolicitudRequest as element() (:: schema-element(ns1:ConsultarExcepcionSolicitudRequest) ::) external;

declare function local:func($ConsultarExcepcionSolicitudRequest as element() (:: schema-element(ns1:ConsultarExcepcionSolicitudRequest) ::)) as element() (:: schema-element(ns2:ConsultarExcepcionSolicitudSelect_idSolicitudInputParameters) ::) {
    <ns2:ConsultarExcepcionSolicitudSelect_idSolicitudInputParameters>
        <ns2:idSolicitud>{fn:data($ConsultarExcepcionSolicitudRequest/ns1:idSolicitud)}</ns2:idSolicitud>
    </ns2:ConsultarExcepcionSolicitudSelect_idSolicitudInputParameters>
};

local:func($ConsultarExcepcionSolicitudRequest)
