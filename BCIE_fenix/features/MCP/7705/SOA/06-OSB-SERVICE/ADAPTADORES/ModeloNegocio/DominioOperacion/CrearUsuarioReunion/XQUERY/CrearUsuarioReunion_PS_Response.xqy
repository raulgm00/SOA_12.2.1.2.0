xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idUsuario as xs:long external;

declare function local:func($idUsuario as xs:long) as element() (:: schema-element(ns1:CrearUsuarioReunionResponse) ::) {
    <ns1:CrearUsuarioReunionResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:data($idUsuario)}</res:message>
        </ns1:Resultado>
    </ns1:CrearUsuarioReunionResponse>
};

local:func($idUsuario)
