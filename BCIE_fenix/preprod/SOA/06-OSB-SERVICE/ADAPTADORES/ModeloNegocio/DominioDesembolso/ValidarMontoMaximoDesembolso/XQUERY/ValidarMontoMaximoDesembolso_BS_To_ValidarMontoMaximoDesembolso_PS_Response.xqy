xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarMontoMaximoDesembolso_DB";
(:: import schema at "../XSD/ValidarMontoMaximoDesembolso_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ValidarMontoMaximoDesembolsoResponse) ::) {
    <ns2:ValidarMontoMaximoDesembolsoResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Validacion satisfactoria</res:message>
        </ns2:Resultado>
        <ns2:Monto>{fn:data($OutputParameters/ns1:V_MONTO_USD)}</ns2:Monto>
    </ns2:ValidarMontoMaximoDesembolsoResponse>
};

local:func($OutputParameters)