xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarContrato";
(:: import schema at "../../ConsultarFLEXCUBE/XSD/ConsultarContrato_sp.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ValidarRegistroFLEXCUBERequest as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBERequest) ::) external;

declare function local:func($ValidarRegistroFLEXCUBERequest as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            <ns2:NUMERO_CONTRATO>{fn:data($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:idContrato)}</ns2:NUMERO_CONTRATO>
        }
    </ns2:InputParameters>
};

local:func($ValidarRegistroFLEXCUBERequest)