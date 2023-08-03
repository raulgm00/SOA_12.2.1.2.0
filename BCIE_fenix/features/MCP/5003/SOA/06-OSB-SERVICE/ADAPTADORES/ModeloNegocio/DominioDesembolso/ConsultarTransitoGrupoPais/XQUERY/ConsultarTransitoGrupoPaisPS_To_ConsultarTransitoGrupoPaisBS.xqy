xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarTransitoGrupoPais";
(:: import schema at "../XSD/ConsultarTransitoGrupoPais_sp.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarTransitoGrupoPaisRequest as element() (:: schema-element(ns1:ConsultarTransitoGrupoPaisRequest) ::) external;

declare function local:func($ConsultarTransitoGrupoPaisRequest as element() (:: schema-element(ns1:ConsultarTransitoGrupoPaisRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_CLIENTE>{fn:data($ConsultarTransitoGrupoPaisRequest/ns1:idCliente/cli:idCliente)}</ns2:P_ID_CLIENTE>
    </ns2:InputParameters>
};

local:func($ConsultarTransitoGrupoPaisRequest)
