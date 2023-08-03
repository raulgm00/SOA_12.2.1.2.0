xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarMiembroReunionDB";
(:: import schema at "../XSD/ConsultarMiembroReunionDB.xsd" ::)

declare variable $ConsultarMiembroReunionRequest as element() (:: schema-element(ns1:ConsultarMiembroReunionRequest) ::) external;

declare function local:func($ConsultarMiembroReunionRequest as element() (:: schema-element(ns1:ConsultarMiembroReunionRequest) ::)) as element() (:: schema-element(ns2:ConsultarMiembroReunionDBInput) ::) {
    <ns2:ConsultarMiembroReunionDBInput>
        <ns2:idNivelAprobacion>{fn:data($ConsultarMiembroReunionRequest/ns1:nivelAprobacion)}</ns2:idNivelAprobacion>
        <ns2:soloNotificacion>{if(fn:string($ConsultarMiembroReunionRequest/ns1:soloNotificacion)='true') then 1 else 0}</ns2:soloNotificacion>
    </ns2:ConsultarMiembroReunionDBInput>
};

local:func($ConsultarMiembroReunionRequest)
