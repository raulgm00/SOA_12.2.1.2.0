xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContratoBySolicitud";
(:: import schema at "../XSD/ConsultarContratoBySolicitud.xsd" ::)

declare variable $ConsultarContratoBySolicitudRequet as element() (:: schema-element(ns1:ConsultarContratoBySolicitudRequest) ::) external;

declare function local:func($ConsultarContratoBySolicitudRequet as element() (:: schema-element(ns1:ConsultarContratoBySolicitudRequest) ::)) as element() (:: schema-element(ns2:ConsultarContratoBySolicitudInput) ::) {
    <ns2:ConsultarContratoBySolicitudInput>
        <ns2:P_ID_SOLICITUD>{fn:data($ConsultarContratoBySolicitudRequet/ns1:idSolicitud)}</ns2:P_ID_SOLICITUD>
    </ns2:ConsultarContratoBySolicitudInput>
};

local:func($ConsultarContratoBySolicitudRequet)

