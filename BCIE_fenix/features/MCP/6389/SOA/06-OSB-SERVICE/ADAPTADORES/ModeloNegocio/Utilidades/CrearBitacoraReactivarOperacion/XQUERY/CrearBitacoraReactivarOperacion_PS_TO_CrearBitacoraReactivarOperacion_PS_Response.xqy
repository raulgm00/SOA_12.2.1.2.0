xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraReactivarOperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraReactivarOperacion/V1/Schema/CrearBitacoraReactivarOperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $requestCrearBitacoraReactivarOperacionMessage as element() (:: schema-element(ns1:requestCrearBitacoraReactivarOperacionMessage) ::) external;

declare function local:func($requestCrearBitacoraReactivarOperacionMessage as element() (:: schema-element(ns1:requestCrearBitacoraReactivarOperacionMessage) ::)) as element() (:: schema-element(ns1:responseCrearBitacoraReactivarOperacionMessage) ::) {
    <ns1:responseCrearBitacoraReactivarOperacionMessage>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Los registros se insertaron correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:responseCrearBitacoraReactivarOperacionMessage>
};

local:func($requestCrearBitacoraReactivarOperacionMessage)
