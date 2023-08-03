xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $UpsertTransaccionCondicionResponse as element() (:: schema-element(ns1:UpsertTransaccionCondicionResponse) ::) external;

declare function local:func($UpsertTransaccionCondicionResponse as element() (:: schema-element(ns1:UpsertTransaccionCondicionResponse) ::)) as element() (:: schema-element(ns1:CondicionesDemandaResponse) ::) {
    <ns1:CondicionesDemandaResponse>
        <ns1:Agrupador>{fn:data($UpsertTransaccionCondicionResponse/ns1:idAgrupador)}</ns1:Agrupador>
        <ns1:Resultado>
            <res:result>{fn:data($UpsertTransaccionCondicionResponse/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($UpsertTransaccionCondicionResponse/ns1:Resultado/res:message)}</res:message>
        </ns1:Resultado>
    </ns1:CondicionesDemandaResponse>
};

local:func($UpsertTransaccionCondicionResponse)
