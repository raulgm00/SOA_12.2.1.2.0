xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComision";
(:: import schema at "../XSD/ConsultarComision.xsd" ::)

declare variable $requestConsultarComision as element() (:: schema-element(ns1:ConsultarComisionByIdLineaCreditoRequest) ::) external;

declare function local:func($requestConsultarComision as element() (:: schema-element(ns1:ConsultarComisionByIdLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:ConsultarComisionInput) ::) {
    <ns2:ConsultarComisionInput>
        <ns2:P_LINE_CREDITO>{fn:data($requestConsultarComision/ns1:idLineaCredito)}</ns2:P_LINE_CREDITO>
        <ns2:P_OPERACION>{fn:data($requestConsultarComision/ns1:idOperacion)}</ns2:P_OPERACION>
    </ns2:ConsultarComisionInput>
};

local:func($requestConsultarComision)
