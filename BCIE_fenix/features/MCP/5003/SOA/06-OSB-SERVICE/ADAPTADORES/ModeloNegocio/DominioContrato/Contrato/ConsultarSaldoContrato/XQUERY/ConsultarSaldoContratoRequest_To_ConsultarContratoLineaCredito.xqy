xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare variable $ConsultarSaldoContratoRequest as element() (:: schema-element(ns1:ConsultarSaldoContratoRequest) ::) external;

declare function local:func($ConsultarSaldoContratoRequest as element() (:: schema-element(ns1:ConsultarSaldoContratoRequest) ::)) as element() (:: schema-element(ns2:ConsultarLineaCreditoRequest) ::) {
    <ns2:ConsultarLineaCreditoRequest>
        <ns2:IdContrato>{fn:data($ConsultarSaldoContratoRequest/ns1:idContrato)}</ns2:IdContrato>
    </ns2:ConsultarLineaCreditoRequest>
};

local:func($ConsultarSaldoContratoRequest)
