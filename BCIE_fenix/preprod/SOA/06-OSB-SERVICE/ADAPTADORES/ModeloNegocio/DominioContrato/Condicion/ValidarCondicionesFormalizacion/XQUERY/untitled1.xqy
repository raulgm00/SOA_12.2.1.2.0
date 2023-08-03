xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:string external;

declare function local:func($message as xs:string) as element() (:: schema-element(ns1:ValidarCondicionesFormalizacionResponse) ::) {
    <ns1:ValidarCondicionesFormalizacionResponse>
        <ns1:validaFechaFormalizacion>true</ns1:validaFechaFormalizacion>
        <ns1:validaCondiciones>true</ns1:validaCondiciones>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Validación realizada exitosamente</res:message>
            
        </ns1:Resultado>
    </ns1:ValidarCondicionesFormalizacionResponse>
};

local:func($message)
