xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://www.bice.org/CrearComentarioVotacion";
(:: import schema at "../XSD/CrearComentarioVotacion_JSON.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $CrearComentarioVotacionResponse as element() (:: schema-element(ns1:CrearComentarioVotacionResponse) ::) external;

declare function local:func($CrearComentarioVotacionResponse as element() (:: schema-element(ns1:CrearComentarioVotacionResponse) ::)) as element() (:: schema-element(ns2:CrearComentarioVotacionResponse) ::) {
    <ns2:CrearComentarioVotacionResponse>
        <ns2:Resultado>
            <ns2:result>{fn:data($CrearComentarioVotacionResponse/ns1:Resultado/res:result)}</ns2:result>
            <ns2:message>{fn:data($CrearComentarioVotacionResponse/ns1:Resultado/res:message)}</ns2:message>
        </ns2:Resultado>
    </ns2:CrearComentarioVotacionResponse>
};

local:func($CrearComentarioVotacionResponse)
