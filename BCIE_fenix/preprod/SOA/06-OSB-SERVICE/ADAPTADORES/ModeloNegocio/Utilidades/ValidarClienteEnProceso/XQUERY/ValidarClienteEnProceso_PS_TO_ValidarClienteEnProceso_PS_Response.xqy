xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ValidarClienteEnProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarClienteEnProceso/V1/Schema/ValidarClienteEnProcesoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $enProceso as xs:boolean external;

declare function local:func($enProceso as xs:boolean) as element() (:: schema-element(ns1:ValidarClienteEnProcesoResponse) ::) {
    <ns1:ValidarClienteEnProcesoResponse>
        <ns1:EnProceso>{fn:data($enProceso)}</ns1:EnProceso>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Validación Exitosa</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ValidarClienteEnProcesoResponse>
};

local:func($enProceso)
