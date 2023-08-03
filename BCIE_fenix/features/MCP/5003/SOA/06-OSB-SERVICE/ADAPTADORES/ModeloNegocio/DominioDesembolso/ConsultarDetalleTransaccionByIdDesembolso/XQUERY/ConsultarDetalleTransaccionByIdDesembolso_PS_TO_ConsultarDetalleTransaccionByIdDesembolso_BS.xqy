xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDetalleTransaccionDesembolso_db";
(:: import schema at "../XSD/ConsultarDetalleTransaccionByIdDesembolso.xsd" ::)

declare variable $ConsultarDetalleTransaccionRequest as element() (:: schema-element(ns1:ConsultarDetalleTransaccionRequest) ::) external;

declare function local:func($ConsultarDetalleTransaccionRequest as element() (:: schema-element(ns1:ConsultarDetalleTransaccionRequest) ::)) as element() (:: schema-element(ns2:ConsultarDetalleTransaccionDesembolso_dbInput) ::) {
    <ns2:ConsultarDetalleTransaccionDesembolso_dbInput>
        <ns2:idContratoDesembolso>{fn:data($ConsultarDetalleTransaccionRequest/ns1:idDesembolso)}</ns2:idContratoDesembolso>
    </ns2:ConsultarDetalleTransaccionDesembolso_dbInput>
};

local:func($ConsultarDetalleTransaccionRequest)
