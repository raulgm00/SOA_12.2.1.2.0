xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarUsuarioReunionAprob";
(:: import schema at "../XSD/ActualizarUsuarioReunionAprob.xsd" ::)

declare variable $ActualizarUsuarioReunionRequest as element() (:: schema-element(ns1:ActualizarUsuarioReunionRequest) ::) external;

declare function local:func($ActualizarUsuarioReunionRequest as element() (:: schema-element(ns1:ActualizarUsuarioReunionRequest) ::)) as element() (:: schema-element(ns2:ActualizarUsuarioReunionAprobInput) ::) {
    <ns2:ActualizarUsuarioReunionAprobInput>
        <ns2:P_IdUsuario>{fn:data($ActualizarUsuarioReunionRequest/ns1:idUsuarioReunion)}</ns2:P_IdUsuario>
    </ns2:ActualizarUsuarioReunionAprobInput>
};

local:func($ActualizarUsuarioReunionRequest)
