xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";


declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ConsultarCommitmentRequest as element() (:: schema-element(ns1:ConsultarCommitmentRequest) ::) external;

declare function local:func($ConsultarCommitmentRequest as element() (:: schema-element(ns1:ConsultarCommitmentRequest) ::)) as element() (:: element(flex:consultarCommitment) ::) {
    <flex:consultarCommitment>
    <numeroLineaCredito>{fn:data($ConsultarCommitmentRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</numeroLineaCredito>
    <programaOperacion></programaOperacion>
    <monto></monto>
    </flex:consultarCommitment>
};

local:func($ConsultarCommitmentRequest)
