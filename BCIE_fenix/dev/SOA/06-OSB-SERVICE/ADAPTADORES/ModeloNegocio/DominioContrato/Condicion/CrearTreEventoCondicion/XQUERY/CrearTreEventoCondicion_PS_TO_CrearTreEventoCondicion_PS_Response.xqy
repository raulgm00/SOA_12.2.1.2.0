xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $CrearTreEventoCondicionRequest as element() (:: schema-element(ns1:CrearTreEventoCondicionRequest) ::) external;

declare function local:func($CrearTreEventoCondicionRequest as element() (:: schema-element(ns1:CrearTreEventoCondicionRequest) ::)) as element() (:: schema-element(ns1:CrearTreEventoCondicionResponse) ::) {
    <ns1:CrearTreEventoCondicionResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>La inserción se ha realizado correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearTreEventoCondicionResponse>
};

local:func($CrearTreEventoCondicionRequest)
