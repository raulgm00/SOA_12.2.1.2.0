xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ValidarRegistroFLEXCUBEResponse as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::) external;

declare function local:func($ValidarRegistroFLEXCUBEResponse as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBEResponse) ::)) as element() (:: schema-element(ns1:CrearCommitmentFLEXCUBEResponse) ::) {
    <ns1:CrearCommitmentFLEXCUBEResponse>
        {
            if ($ValidarRegistroFLEXCUBEResponse/ns1:LineaCredito/lin:idContrato)
            then <ns1:codigoContrato>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:LineaCredito/lin:idContrato)}</ns1:codigoContrato>
            else ()
        }
        <ns1:plantillaLimite>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:LineaCredito/lin:CodigoPantallaLimite)}</ns1:plantillaLimite>
        <ns1:serialLimite>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:LineaCredito/lin:CodigoSerialLimite)}</ns1:serialLimite>
        <ns1:Resultado>
            <res:result>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($ValidarRegistroFLEXCUBEResponse/ns1:Resultado/res:message)}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearCommitmentFLEXCUBEResponse>
};

local:func($ValidarRegistroFLEXCUBEResponse)
