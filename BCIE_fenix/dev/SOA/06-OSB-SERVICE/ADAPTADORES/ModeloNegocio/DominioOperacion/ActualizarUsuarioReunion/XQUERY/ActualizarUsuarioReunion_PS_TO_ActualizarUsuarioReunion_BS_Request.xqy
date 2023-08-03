xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarUsuarioReunion";
(:: import schema at "../XSD/ActualizarUsuarioReunion.xsd" ::)

declare variable $ActualizarUsuarioReunionRequest as element() (:: schema-element(ns1:ActualizarUsuarioReunionRequest) ::) external;

declare function local:func($ActualizarUsuarioReunionRequest as element() (:: schema-element(ns1:ActualizarUsuarioReunionRequest) ::)) as element() (:: schema-element(ns2:ActualizarUsuarioReunionInput) ::) {
    <ns2:ActualizarUsuarioReunionInput>
        {
            if ($ActualizarUsuarioReunionRequest/ns1:marcadoComoLeido=true()) then
                <ns2:marcado>1</ns2:marcado>
            else
                <ns2:marcado>0</ns2:marcado>
        }
        <ns2:idSolicitudAprobacion>{fn:data($ActualizarUsuarioReunionRequest/ns1:idSolicitudAprobacion)}</ns2:idSolicitudAprobacion>
        <ns2:loginUsuario>{fn:data($ActualizarUsuarioReunionRequest/ns1:loginUsuario)}</ns2:loginUsuario>
    </ns2:ActualizarUsuarioReunionInput>
};

local:func($ActualizarUsuarioReunionRequest)
