xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/UpsertTransferencia_DB";
(:: import schema at "../XSD/UpsertTransferencia_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:UpsertTransferenciasResponse) ::) {
    <ns2:UpsertTransferenciasResponse>
        <ns2:Resultado>
            <res:result>{
            if(string($OutputParameters/ns1:P_TIPO_RES) = '0')then 'OK'
            else 'ERROR'
            }</res:result>
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($OutputParameters/ns1:P_CODIGO_RES)}</err:errorCode>
                <err:errorDescription>{fn:data($OutputParameters/ns1:P_TIPO_RES)}</err:errorDescription>
                <err:errorType>{fn:data($OutputParameters/ns1:P_MENSAJE)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:UpsertTransferenciasResponse>
};

local:func($OutputParameters)
