xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTransferenciasBancariasById_DB";
(:: import schema at "../XSD/ConsultarTransferenciasBancariasById_DB.xsd" ::)

declare variable $ConsultarTransferenciasBancariasByIdRequest as element() (:: schema-element(ns1:ConsultarTransferenciasBancariasByIdRequest) ::) external;

declare function local:func($ConsultarTransferenciasBancariasByIdRequest as element() (:: schema-element(ns1:ConsultarTransferenciasBancariasByIdRequest) ::)) as element() (:: schema-element(ns2:ConsultarTransferenciasBancariasById_DBInput) ::) {
    <ns2:ConsultarTransferenciasBancariasById_DBInput>
        <ns2:idDesembolso>{fn:data($ConsultarTransferenciasBancariasByIdRequest/ns1:idDesembolso)}</ns2:idDesembolso>
    </ns2:ConsultarTransferenciasBancariasById_DBInput>
};

local:func($ConsultarTransferenciasBancariasByIdRequest)
