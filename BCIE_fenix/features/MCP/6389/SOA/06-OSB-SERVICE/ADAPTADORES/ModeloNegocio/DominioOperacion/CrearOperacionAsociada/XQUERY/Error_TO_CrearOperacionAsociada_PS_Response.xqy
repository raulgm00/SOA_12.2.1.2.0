xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $mensajeError as xs:string external;
declare variable $codigoError as xs:string external;
declare variable $descripcionError as xs:string external;

declare function local:func($mensajeError as xs:string, 
                            $codigoError as xs:string,
                            $descripcionError as xs:string) as element() (:: schema-element(ns1:CrearOperacionAsociadaResponse) ::) {
    <ns1:CrearOperacionAsociadaResponse>
        <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($mensajeError)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($codigoError)}</err:errorCode>
                <err:errorDescription>{fn:data($descripcionError)}</err:errorDescription>
                <err:errorType>TECNICO</err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearOperacionAsociadaResponse>
};

local:func($mensajeError, $codigoError, $descripcionError)
