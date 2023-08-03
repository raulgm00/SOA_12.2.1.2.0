xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarMontoMaximoDesembolso_DB";
(:: import schema at "../XSD/ValidarMontoMaximoDesembolso_DB_sp.xsd" ::)

declare variable $ValidarMontoMaximoDesembolsoRequest as element() (:: schema-element(ns1:ValidarMontoMaximoDesembolsoRequest) ::) external;

declare function ns1:func($ValidarMontoMaximoDesembolsoRequest as element() (:: schema-element(ns1:ValidarMontoMaximoDesembolsoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_CODIGO_CLIENTE>{fn:data($ValidarMontoMaximoDesembolsoRequest/ns1:codigoCliente)}</ns2:P_CODIGO_CLIENTE>
    </ns2:InputParameters>
};

ns1:func($ValidarMontoMaximoDesembolsoRequest)