xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreReglasDesembolso_DB";
(:: import schema at "../XSD/ConsultarReglasDesembolso_DB.xsd" ::)

declare variable $ConsultarTREReglasDesembolsoRequest as element() (:: schema-element(ns1:ConsultarReglasDesembolsoRequest) ::) external;

declare function local:func($ConsultarTREReglasDesembolsoRequest as element() (:: schema-element(ns1:ConsultarReglasDesembolsoRequest) ::)) as element() (:: schema-element(ns2:ConsultarTreReglasDesembolso_DBInput) ::) {
    <ns2:ConsultarTreReglasDesembolso_DBInput>
        <ns2:ID_DESEMBOLSO>{fn:data($ConsultarTREReglasDesembolsoRequest/ns1:idDesembolso)}</ns2:ID_DESEMBOLSO>
    </ns2:ConsultarTreReglasDesembolso_DBInput>
};

local:func($ConsultarTREReglasDesembolsoRequest)
