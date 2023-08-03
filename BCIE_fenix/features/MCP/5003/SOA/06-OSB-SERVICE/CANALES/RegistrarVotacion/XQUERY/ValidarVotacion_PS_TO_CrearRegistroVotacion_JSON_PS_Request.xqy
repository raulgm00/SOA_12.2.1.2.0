xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearRegistroVotacionBO";
(:: import schema at "../XSD/CrearRegistroVotacion_JSON.xsd" ::)
declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare variable $ValidarVotacionResponse as element() (:: schema-element(ns1:ValidarVotacionResponse) ::) external;

declare function local:func($ValidarVotacionResponse as element() (:: schema-element(ns1:ValidarVotacionResponse) ::)) as element() (:: schema-element(ns2:CrearRegistroVotacionResponse) ::) {
    <ns2:CrearRegistroVotacionResponse>
        <ns2:Resultado>
            <ns2:result>{if (fn:data($ValidarVotacionResponse/ns1:esValido) =1) then 'OK' else 'ERROR'}</ns2:result>
            <ns2:message>{if (fn:data($ValidarVotacionResponse/ns1:mensajeValidacion/text()) !='') then fn:data($ValidarVotacionResponse/ns1:mensajeValidacion) else 'Error de sistema, no se pudo registrar la votación'}</ns2:message>
        </ns2:Resultado>
    </ns2:CrearRegistroVotacionResponse>
};

local:func($ValidarVotacionResponse)
