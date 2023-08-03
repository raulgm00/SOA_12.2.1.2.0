xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $codigoResultado as element() external;
declare variable $tipoResultado as element() external;
declare variable $mensajeError as element() external;

declare function local:func($codigoResultado as element(), 
                            $tipoResultado as element(), 
                            $mensajeError as element()) 
                            as element() (:: schema-element(ns1:CrearClienteResponse) ::) {
    <ns1:CrearClienteResponse>
        <ns1:Resultado>
            <res:result></res:result>
            <res:message></res:message>
            <res:error>
                <err:errorCode>{fn:data($codigoResultado)}</err:errorCode>
                <err:errorDescription>{fn:data($mensajeError)}</err:errorDescription>
                <err:errorType>{fn:data($tipoResultado)}</err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearClienteResponse>
};

local:func($codigoResultado, $tipoResultado, $mensajeError)
