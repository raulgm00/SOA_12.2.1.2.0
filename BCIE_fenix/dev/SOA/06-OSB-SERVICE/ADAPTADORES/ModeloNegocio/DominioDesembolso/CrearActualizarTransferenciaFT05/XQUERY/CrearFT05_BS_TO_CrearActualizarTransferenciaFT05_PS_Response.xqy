xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearFT05_DB";
(:: import schema at "../XSD/CrearFT05_DB_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearActualizarTransferenciaFT05Response) ::) {
    <ns2:CrearActualizarTransferenciaFT05Response>
        <ns2:TransferenciaFT05>
            <des:idTransferenciaFT05>{fn:data($OutputParameters/ns1:P_ID_TRANSFERENCIA_FT05)}</des:idTransferenciaFT05>
            <des:idDesembolso></des:idDesembolso>
            <des:idFacturador></des:idFacturador>
            <des:FechaEfectiva></des:FechaEfectiva>
        </ns2:TransferenciaFT05>
        <ns2:Resultado>
            <res:result>{
            if(fn:data($OutputParameters/ns1:P_CODIGO_RES)= 0)
            then 'OK'
            else 'ERROR'}</res:result>
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:CrearActualizarTransferenciaFT05Response>
};

local:func($OutputParameters)
