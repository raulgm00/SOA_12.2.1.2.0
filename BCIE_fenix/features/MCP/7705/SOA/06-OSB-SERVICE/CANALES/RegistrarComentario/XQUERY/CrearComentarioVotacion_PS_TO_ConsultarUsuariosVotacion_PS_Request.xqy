xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://www.bice.org/CrearComentarioVotacion";
(:: import schema at "../XSD/CrearComentarioVotacion_JSON.xsd" ::)

declare variable $request as element() (:: schema-element(ns2:CrearComentarioVotacionRequest) ::) external;

declare function local:func($request as element() (:: schema-element(ns2:CrearComentarioVotacionRequest) ::)) as element() (:: schema-element(ns1:ConsultarUsuarioReunionRequest) ::) {
    <ns1:ConsultarUsuarioReunionRequest>
        <ns1:idUsuarioReunion>{fn:data($request/ns2:idUsuario)}</ns1:idUsuarioReunion>
    </ns1:ConsultarUsuarioReunionRequest>
};

local:func($request)
