xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare variable $ConsultarTransferenciasBancariasByIdRequest as element() (:: schema-element(ns1:ConsultarTransferenciasBancariasByIdRequest) ::) external;

declare function local:func($ConsultarTransferenciasBancariasByIdRequest as element() (:: schema-element(ns1:ConsultarTransferenciasBancariasByIdRequest) ::)) as element() (:: schema-element(ns1:ConsultarDesembolsosByIdRequest) ::) {
    <ns1:ConsultarDesembolsosByIdRequest>
        <ns1:idContratoDesembolso>{fn:data($ConsultarTransferenciasBancariasByIdRequest/ns1:idDesembolso)}</ns1:idContratoDesembolso>
    </ns1:ConsultarDesembolsosByIdRequest>
};

local:func($ConsultarTransferenciasBancariasByIdRequest)
