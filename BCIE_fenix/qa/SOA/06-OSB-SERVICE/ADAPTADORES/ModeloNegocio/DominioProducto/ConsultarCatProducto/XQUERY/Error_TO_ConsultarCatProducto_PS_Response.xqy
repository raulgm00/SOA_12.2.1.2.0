xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/ProductoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:string external;
declare variable $Error as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::) external;

declare function local:ErrorResponse($message as xs:string, $Error as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::)) as element() (:: schema-element(ns1:ConsultarCatProductoResponse) ::) {
    <ns1:ConsultarCatProductoResponse>
        <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($message)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($Error/ns2:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($Error/ns2:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($Error/ns2:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
     
        </ns1:Resultado>
    </ns1:ConsultarCatProductoResponse>
};

local:ErrorResponse($message,$Error)
