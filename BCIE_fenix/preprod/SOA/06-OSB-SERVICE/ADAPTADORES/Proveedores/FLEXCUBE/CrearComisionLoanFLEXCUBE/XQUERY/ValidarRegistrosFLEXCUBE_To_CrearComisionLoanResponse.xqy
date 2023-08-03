xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarRegistroFLEXCUBEResponse as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::) external;

declare function local:func($ValidarRegistroFLEXCUBEResponse as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::)) as element() (:: schema-element(ns1:CrearPrestamoFLEXCUBEResponse) ::) {
    <ns1:CrearPrestamoFLEXCUBEResponse>
        <ns1:codigoContrato>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:LineaCredito/lin:idContrato)}</ns1:codigoContrato>
        <ns1:Resultado>
            <res:result>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:Resultado/res:message)}</res:message>
            {
                if ($ValidarRegistroFLEXCUBEResponse/ns1:Resultado/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:Resultado/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:Resultado/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </ns1:Resultado>
    </ns1:CrearPrestamoFLEXCUBEResponse>
};

local:func($ValidarRegistroFLEXCUBEResponse)
