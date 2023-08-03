xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $ConsultarComisionesAsignadasRequest as element() (:: schema-element(ns1:ConsultarComisionesAsignadasRequest) ::) external;

declare function local:func($ConsultarComisionesAsignadasRequest as element() (:: schema-element(ns1:ConsultarComisionesAsignadasRequest) ::)) as element() (:: schema-element(ns1:ConsultarInformacionReglasByIdRequest) ::) {
    <ns1:ConsultarInformacionReglasByIdRequest>
        <ns1:idDesembolso>{fn:data($ConsultarComisionesAsignadasRequest/ns1:Desembolso/des:idDesembolso)}</ns1:idDesembolso>
    </ns1:ConsultarInformacionReglasByIdRequest>
};

local:func($ConsultarComisionesAsignadasRequest)
