xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::) {
    <ns1:ConsultarOperacionResponse>
        <ns1:Resultado>
            <res:error>
                <err:errorCode>ERROR</err:errorCode>
                <err:errorDescription>No existe la operación en la base de datos</err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ConsultarOperacionResponse>
};

local:func()