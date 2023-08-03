xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSolicitudDesembolso_DB";
(:: import schema at "../XSD/ConsultarSolicitudDesembolso_DB.xsd" ::)

declare variable $ConsultarSolicitudDesembolso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarSolicitudDesembolso_DBOutputCollection) ::) external;

declare function local:func($ConsultarSolicitudDesembolso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarSolicitudDesembolso_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarDesembolsosByIdRequest) ::) {
    <ns2:ConsultarDesembolsosByIdRequest>
    {
    for $Solicitud in $ConsultarSolicitudDesembolso_DBOutputCollection/ns1:ConsultarSolicitudDesembolso_DBOutput
    return
        <ns2:idContratoDesembolso>{fn:data($Solicitud/ns1:ID_CONTRATO_DESEMBOLSO)}</ns2:idContratoDesembolso>
    }
    </ns2:ConsultarDesembolsosByIdRequest>
};

local:func($ConsultarSolicitudDesembolso_DBOutputCollection)