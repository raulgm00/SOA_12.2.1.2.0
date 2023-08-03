xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarLineaCreditoByIdContrato";
(:: import schema at "../XSD/EliminarLineaCreditoByIdContrato.xsd" ::)

declare variable $EliminarLineaCreditoByIdContratoRequest as element() (:: schema-element(ns1:EliminarLineaCreditoByIdContratoRequest) ::) external;

declare function local:func($EliminarLineaCreditoByIdContratoRequest as element() (:: schema-element(ns1:EliminarLineaCreditoByIdContratoRequest) ::)) as element() (:: schema-element(ns2:EliminarLineaCreditoByIdContratoInput) ::) {
    <ns2:EliminarLineaCreditoByIdContratoInput>
        <ns2:idContrato>{fn:data($EliminarLineaCreditoByIdContratoRequest/ns1:idContrato)}</ns2:idContrato>
    </ns2:EliminarLineaCreditoByIdContratoInput>
};

local:func($EliminarLineaCreditoByIdContratoRequest)
