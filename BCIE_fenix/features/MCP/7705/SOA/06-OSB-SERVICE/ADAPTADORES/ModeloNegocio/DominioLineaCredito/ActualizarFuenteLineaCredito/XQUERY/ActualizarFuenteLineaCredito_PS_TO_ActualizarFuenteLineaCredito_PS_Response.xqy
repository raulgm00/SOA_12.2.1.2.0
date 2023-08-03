xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ActualizarFuenteLCRequest as element() (:: schema-element(ns1:ActualizarFuenteLCRequest) ::) external;

declare function local:func($ActualizarFuenteLCRequest as element() (:: schema-element(ns1:ActualizarFuenteLCRequest) ::)) as element() (:: schema-element(ns1:ActualizarFuenteLCResponse) ::) {
    <ns1:ActualizarFuenteLCResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Los datos fueron actualizados correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ActualizarFuenteLCResponse>
};

local:func($ActualizarFuenteLCRequest)
