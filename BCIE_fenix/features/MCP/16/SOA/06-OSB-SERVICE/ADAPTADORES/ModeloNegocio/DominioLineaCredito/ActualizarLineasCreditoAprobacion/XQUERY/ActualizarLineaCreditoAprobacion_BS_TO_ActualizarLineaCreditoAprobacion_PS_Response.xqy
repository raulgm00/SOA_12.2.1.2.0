xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ActualizarLineasCreditoAprobacion";
(:: import schema at "../XSD/ActualizarLineasCreditoAprobacion_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ActualizarLineaCreditoAprobacionResponse) ::) {
    <ns2:ActualizarLineaCreditoAprobacionResponse>
        <ns2:Resultado>
            <res:result>{fn:data($OutputParameters/ns1:P_RESULTADO)}</res:result>
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:ActualizarLineaCreditoAprobacionResponse>
};

local:func($OutputParameters)
