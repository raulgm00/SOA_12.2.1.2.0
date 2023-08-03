xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CalcularCuotas_DB";
(:: import schema at "../XSD/CalcularCuotas_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CalcularCuotasResponse) ::) {
    <ns2:CalcularCuotasResponse>
        <ns2:numeroCuotas>{fn:data($OutputParameters/ns1:VNCUOTAS)}</ns2:numeroCuotas>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:data($OutputParameters/ns1:PCMENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:CalcularCuotasResponse>
};

local:func($OutputParameters)
