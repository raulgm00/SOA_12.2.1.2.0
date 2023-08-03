xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearRegistroVotacionBO";
(:: import schema at "../XSD/CrearRegistroVotacion_JSON.xsd" ::)
declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $CrearRegistroVotacionResponse as element() (:: schema-element(ns1:CrearRegistroVotacionResponse) ::) external;

declare function local:func($CrearRegistroVotacionResponse as element() (:: schema-element(ns1:CrearRegistroVotacionResponse) ::)) as element() (:: schema-element(ns2:CrearRegistroVotacionResponse) ::) {
    <ns2:CrearRegistroVotacionResponse>
        <ns2:Resultado>
            <ns2:result>{xs:string($CrearRegistroVotacionResponse/ns1:Resultado/res:result)}</ns2:result>
            <ns2:message>{fn:data($CrearRegistroVotacionResponse/ns1:Resultado/res:message)}</ns2:message>
        </ns2:Resultado>
    </ns2:CrearRegistroVotacionResponse>
};

local:func($CrearRegistroVotacionResponse)
