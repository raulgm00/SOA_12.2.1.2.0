xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";
declare namespace des = "http://www.bcie.org/DesembolsoBO";
declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ValidarRegistroFLEXCUBEResponse as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::) external;
declare variable $referencia as xs:string external;
declare function local:func($ValidarRegistroFLEXCUBEResponse as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::),$referencia as xs:string) as element() (:: schema-element(ns2:CrearDesembolsoComplejoFLEXCUBEResponse) ::) {
    <ns2:CrearDesembolsoComplejoFLEXCUBEResponse>
         <ns2:ContratoDesembolso>
             <des:idDesembolso></des:idDesembolso>
             <des:idFacturador>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:LineaCredito/lin:idContrato)}</des:idFacturador>
             <des:referencia>{$referencia}</des:referencia>
        </ns2:ContratoDesembolso>
        <ns2:Resultado>
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
        </ns2:Resultado>
    </ns2:CrearDesembolsoComplejoFLEXCUBEResponse>
};

local:func($ValidarRegistroFLEXCUBEResponse,$referencia)
