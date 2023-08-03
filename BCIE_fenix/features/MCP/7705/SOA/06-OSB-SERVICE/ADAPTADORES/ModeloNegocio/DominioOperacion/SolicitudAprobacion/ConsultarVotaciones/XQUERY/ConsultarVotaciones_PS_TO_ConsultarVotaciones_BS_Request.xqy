xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarVotacion";
(:: import schema at "../XSD/ConsultarVotaciones_sp.xsd" ::)

declare variable $ConsultarVotacionesRequest as element() (:: schema-element(ns1:ConsultarVotacionesRequest) ::) external;

declare function local:func($ConsultarVotacionesRequest as element() (:: schema-element(ns1:ConsultarVotacionesRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_USUARIO>{fn:data($ConsultarVotacionesRequest/ns1:Usuario)}</ns2:P_USUARIO>
        <ns2:P_HISTORIAL>{if (fn:data($ConsultarVotacionesRequest/ns1:historial/text())= 'true') then 1 else 0}</ns2:P_HISTORIAL>
        <ns2:P_APROBACION_CLIENTE>{if (fn:data($ConsultarVotacionesRequest/ns1:aprobacionCliente/text())= 'true') then 1 else 0}</ns2:P_APROBACION_CLIENTE>
    </ns2:InputParameters>
};

local:func($ConsultarVotacionesRequest)
