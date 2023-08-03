xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare variable $ValidarF1Request as element() (:: schema-element(ns1:ValidarF1Request) ::) external;

declare function local:func($ValidarF1Request as element() (:: schema-element(ns1:ValidarF1Request) ::)) as element() (:: schema-element(ns1:ConsultarDesembolsosByIdRequest) ::) {
    <ns1:ConsultarDesembolsosByIdRequest>
        <ns1:idContratoDesembolso>{fn:data($ValidarF1Request/ns1:idDesembolso)}</ns1:idContratoDesembolso>
    </ns1:ConsultarDesembolsosByIdRequest>
};

local:func($ValidarF1Request)
