xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearRegistroVotacionBO";
(:: import schema at "../XSD/CrearRegistroVotacion_JSON.xsd" ::)
declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare variable $CrearRegistroVotacionRequest as element() (:: schema-element(ns1:CrearRegistroVotacionRequest) ::) external;

declare function local:func($CrearRegistroVotacionRequest as element() (:: schema-element(ns1:CrearRegistroVotacionRequest) ::)) as element() (:: schema-element(ns2:ValidarVotacionRequest) ::) {
    <ns2:ValidarVotacionRequest>
        <ns2:idUsuarioReunion>{fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:idUsuario)}</ns2:idUsuarioReunion>
        <ns2:loginUsuario>{fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:loginUsuario)}</ns2:loginUsuario>
    </ns2:ValidarVotacionRequest>
};

local:func($CrearRegistroVotacionRequest)
