xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarCommitment";
(:: import schema at "../XSD/ConsultarCommitment_sp.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ConsultarCommitmentRequest as element() (:: schema-element(ns1:ConsultarCommitmentRequest) ::) external;

declare function local:func($ConsultarCommitmentRequest as element() (:: schema-element(ns1:ConsultarCommitmentRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:NUMERO_LINEA_CREDITO>{fn:data($ConsultarCommitmentRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</ns2:NUMERO_LINEA_CREDITO>
        <ns2:PROGRAMA_OPERACION></ns2:PROGRAMA_OPERACION>
        <ns2:MONTO></ns2:MONTO>
    </ns2:InputParameters>
};

local:func($ConsultarCommitmentRequest)