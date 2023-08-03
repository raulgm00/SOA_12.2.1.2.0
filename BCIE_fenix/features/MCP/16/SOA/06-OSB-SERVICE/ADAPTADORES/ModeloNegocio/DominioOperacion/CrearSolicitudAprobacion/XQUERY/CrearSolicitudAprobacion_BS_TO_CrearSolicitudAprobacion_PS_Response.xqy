xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idSolicitud as xs:long external;

declare function local:func($idSolicitud as xs:long) as element() (:: schema-element(ns1:CrearSolicitudAprobacionResponse) ::) {
    <ns1:CrearSolicitudAprobacionResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:data($idSolicitud)}</res:message>
        </ns1:Resultado>
    </ns1:CrearSolicitudAprobacionResponse>
};

local:func($idSolicitud)
