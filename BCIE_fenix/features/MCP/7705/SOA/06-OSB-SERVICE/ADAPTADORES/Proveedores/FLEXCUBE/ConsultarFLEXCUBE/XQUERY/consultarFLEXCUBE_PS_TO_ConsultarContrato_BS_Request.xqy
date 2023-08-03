xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarContrato";
(:: import schema at "../XSD/ConsultarContrato_sp.xsd" ::)

declare variable $ConsultarFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarFLEXCUBERequest) ::) external;

declare function local:func($ConsultarFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:NUMERO_CONTRATO>{fn:data($ConsultarFLEXCUBERequest/ns1:codigoContrato)}</ns2:NUMERO_CONTRATO>
        <ns2:REFERENCIA_USUARIO/>
    </ns2:InputParameters>
};

local:func($ConsultarFLEXCUBERequest)