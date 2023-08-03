xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarContrato";
(:: import schema at "../../ConsultarFLEXCUBE/XSD/ConsultarContrato_sp.xsd" ::)

declare variable $consultarContratoComisionRequest as element() (:: schema-element(ns1:ConsultarContratoComisionFLEXCUBERequest) ::) external;

declare function local:func($consultarContratoComisionRequest as element() (:: schema-element(ns1:ConsultarContratoComisionFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:NUMERO_CONTRATO>{fn:normalize-space(fn:data($consultarContratoComisionRequest/ns1:codigoContrato))}</ns2:NUMERO_CONTRATO>
    </ns2:InputParameters>
};

local:func($consultarContratoComisionRequest)