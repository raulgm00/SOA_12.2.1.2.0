xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarTreLineaCreditoTCC_DB";
(:: import schema at "../XSD/EliminarTreLineaCreditoTCC_DB.xsd" ::)

declare variable $EliminarTreLineaCreditoTCCRequest as element() (:: schema-element(ns1:EliminarTreLineaCreditoTCCRequest) ::) external;

declare function local:func($EliminarTreLineaCreditoTCCRequest as element() (:: schema-element(ns1:EliminarTreLineaCreditoTCCRequest) ::)) as element() (:: schema-element(ns2:EliminarTreLineaCreditoTCC_DBInput) ::) {
    <ns2:EliminarTreLineaCreditoTCC_DBInput>
        <ns2:ID_LINEA_CREDITO>{fn:data($EliminarTreLineaCreditoTCCRequest/ns1:idLineaCredito)}</ns2:ID_LINEA_CREDITO>
    </ns2:EliminarTreLineaCreditoTCC_DBInput>
};

local:func($EliminarTreLineaCreditoTCCRequest)
