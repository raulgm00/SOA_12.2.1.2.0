xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComisionByIdLineaCredito";
(:: import schema at "../XSD/ConsultarComisionByIdLineaCredito.xsd" ::)

declare variable $ConsultarComisionByIdLineaCreditoRequest as element() (:: schema-element(ns2:ConsultarComisionByIdLineaCreditoRequest) ::) external;

declare function local:func($ConsultarComisionByIdLineaCreditoRequest as element() (:: schema-element(ns2:ConsultarComisionByIdLineaCreditoRequest) ::)) as element() (:: schema-element(ns1:ConsultarComisionByIdLineaCreditoInput) ::) {
    <ns1:ConsultarComisionByIdLineaCreditoInput>
        <ns1:IDLINEACREDITO>{fn:data($ConsultarComisionByIdLineaCreditoRequest/ns2:idLineaCredito)}</ns1:IDLINEACREDITO>
    </ns1:ConsultarComisionByIdLineaCreditoInput>
};

local:func($ConsultarComisionByIdLineaCreditoRequest)
