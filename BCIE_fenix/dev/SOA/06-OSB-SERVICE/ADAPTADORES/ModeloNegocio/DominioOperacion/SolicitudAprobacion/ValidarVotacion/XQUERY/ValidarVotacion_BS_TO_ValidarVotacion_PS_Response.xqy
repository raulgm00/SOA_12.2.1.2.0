xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarVotacion";
(:: import schema at "../XSD/ValidarVotacion_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ValidarVotacionResponse) ::) {
    <ns2:ValidarVotacionResponse>
        <ns2:esValido>{if (fn:data($OutputParameters/ns1:P_RESULTADO)=1) then fn:data($OutputParameters/ns1:P_RESULTADO) else 0}</ns2:esValido>
        <ns2:mensajeValidacion>{if (fn:data($OutputParameters/ns1:P_RESULTADO)<=1) then fn:data(fn:data($OutputParameters/ns1:P_MENSAJE)) else()}</ns2:mensajeValidacion>
       {
        if (fn:data($OutputParameters/ns1:P_RESULTADO)<=1) then
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns2:Resultado>
        else
       <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:error>
                <err:errorCode>{fn:data($OutputParameters/ns1:P_RESULTADO)}</err:errorCode>
                <err:errorDescription>{fn:data($OutputParameters/ns1:P_MENSAJE)}</err:errorDescription>
            </res:error>
        </ns2:Resultado>
        }
    </ns2:ValidarVotacionResponse>
};

local:func($OutputParameters)
