xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $codigoCliente as element() external;
declare variable $codigoResultado as element() external;
declare variable $tipoResultado as element() external;
declare variable $mensaje as element() external;

declare function local:func($codigoCliente as element(), 
                            $codigoResultado as element(), 
                            $tipoResultado as element(), 
                            $mensaje as element()) 
                            as element() (:: schema-element(ns1:CrearClienteFLEXCUBEResponse) ::) {
    <ns1:CrearClienteFLEXCUBEResponse>
        <ns1:Cliente>
            <cli:idFacturador>{fn:data($codigoCliente)}</cli:idFacturador>
         
        </ns1:Cliente>
        <ns1:Resultado>
          {
                if (fn:data($codigoResultado)= '0') then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
             }
            <res:message>{fn:data($mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($codigoResultado)}</err:errorCode>
                <err:errorDescription>{fn:data($mensaje)}</err:errorDescription>
                <err:errorType>{fn:data($tipoResultado)}</err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearClienteFLEXCUBEResponse>
};

local:func($codigoCliente, $codigoResultado, $tipoResultado, $mensaje)
