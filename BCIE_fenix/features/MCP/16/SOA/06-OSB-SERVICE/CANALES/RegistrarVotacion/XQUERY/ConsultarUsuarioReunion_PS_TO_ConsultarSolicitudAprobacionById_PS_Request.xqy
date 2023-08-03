xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare variable $ConsultarUsuarioReunionResponse as element() (:: schema-element(ns1:ConsultarUsuarioReunionResponse) ::) external;

declare function local:func($ConsultarUsuarioReunionResponse as element() (:: schema-element(ns1:ConsultarUsuarioReunionResponse) ::)) as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionByIdRequest) ::) {
    <ns1:ConsultarSolicitudAprobacionByIdRequest>
        <ns1:idSolicitudAprobacion>{fn:data($ConsultarUsuarioReunionResponse/ns1:listadoUsuariosReunion[1]/sol:idSolicitudAprobacion)}</ns1:idSolicitudAprobacion>
    </ns1:ConsultarSolicitudAprobacionByIdRequest>
};

local:func($ConsultarUsuarioReunionResponse)
