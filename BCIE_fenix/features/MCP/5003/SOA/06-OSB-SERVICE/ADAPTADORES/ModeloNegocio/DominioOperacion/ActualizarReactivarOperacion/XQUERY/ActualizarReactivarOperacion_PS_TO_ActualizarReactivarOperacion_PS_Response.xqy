xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ActualizarReactivarOperacionRequest as element() (:: schema-element(ns1:ActualizarReactivarOperacionRequest) ::) external;

declare function local:func($ActualizarReactivarOperacionRequest as element() (:: schema-element(ns1:ActualizarReactivarOperacionRequest) ::)) as element() (:: schema-element(ns1:ActualizarReactivarOperacionResponse) ::) {
    <ns1:ActualizarReactivarOperacionResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>El registro se actualizó correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ActualizarReactivarOperacionResponse>
};

local:func($ActualizarReactivarOperacionRequest)
