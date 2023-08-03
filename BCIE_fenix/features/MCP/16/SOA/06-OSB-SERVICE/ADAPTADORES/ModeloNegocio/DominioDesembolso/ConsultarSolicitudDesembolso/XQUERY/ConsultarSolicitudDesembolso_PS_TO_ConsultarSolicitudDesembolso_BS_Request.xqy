xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSolicitudDesembolso_DB";
(:: import schema at "../XSD/ConsultarSolicitudDesembolso_DB.xsd" ::)

declare variable $ConsultarSolicitudDesembolsoRequest as element() (:: schema-element(ns1:ConsultarSolicitudDesembolsoRequest) ::) external;

declare function local:func($ConsultarSolicitudDesembolsoRequest as element() (:: schema-element(ns1:ConsultarSolicitudDesembolsoRequest) ::)) as element() (:: schema-element(ns2:ConsultarSolicitudDesembolso_DBInput) ::) {
    <ns2:ConsultarSolicitudDesembolso_DBInput>
        <ns2:id_solicitud>{fn:data($ConsultarSolicitudDesembolsoRequest/ns1:idSolicitud)}</ns2:id_solicitud>
        <ns2:id_contrato>{fn:data($ConsultarSolicitudDesembolsoRequest/ns1:idContratoDesembolso)}</ns2:id_contrato>
        <ns2:idOperacion>{fn:data($ConsultarSolicitudDesembolsoRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarSolicitudDesembolso_DBInput>
};

local:func($ConsultarSolicitudDesembolsoRequest)