xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)


declare function local:func() as element() (:: schema-element(ns1:ConsultarMiembroReunionRequest) ::) {
    <ns1:ConsultarMiembroReunionRequest>
        <ns1:nivelAprobacion></ns1:nivelAprobacion>
        <ns1:soloNotificacion>false</ns1:soloNotificacion>
    </ns1:ConsultarMiembroReunionRequest>
};

local:func()
