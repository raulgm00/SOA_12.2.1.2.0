xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $codigoResultado as xs:string external;
declare variable $tipoResultado as xs:string external;
declare variable $mensaje as xs:string external;

declare function local:func($codigoResultado as xs:string, 
                            $tipoResultado as xs:string, 
                            $mensaje as xs:string) 
                            as element() (:: schema-element(ns1:ActualizarClienteFLEXCUBEResponse) ::) {
    <ns1:ActualizarClienteFLEXCUBEResponse>
        <ns1:Resultado>
           {
                if (fn:data($codigoResultado)= '0') then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
             }
            <res:message>{fn:data($mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($codigoResultado)}</err:errorCode>
                <err:errorDescription>{fn:data($tipoResultado)}</err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ActualizarClienteFLEXCUBEResponse>
};

local:func($codigoResultado, $tipoResultado, $mensaje)
