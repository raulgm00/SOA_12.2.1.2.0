xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarRegistroFLEXCUBEResponse as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::) external;

declare function local:func($ValidarRegistroFLEXCUBEResponse as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::)) as element() (:: schema-element(ns2:CrearTransferenciaFLEXCUBEResponse) ::) {
    <ns2:CrearTransferenciaFLEXCUBEResponse>
        <ns2:ContratoDesembolso>
           <des:Transferencia>
                <des:idTransferencia></des:idTransferencia>
                <des:idDesembolso></des:idDesembolso>
                <des:idFacturador>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:LineaCredito/lin:idContrato)}</des:idFacturador>
            </des:Transferencia>
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
    </ns2:CrearTransferenciaFLEXCUBEResponse>
};

local:func($ValidarRegistroFLEXCUBEResponse)