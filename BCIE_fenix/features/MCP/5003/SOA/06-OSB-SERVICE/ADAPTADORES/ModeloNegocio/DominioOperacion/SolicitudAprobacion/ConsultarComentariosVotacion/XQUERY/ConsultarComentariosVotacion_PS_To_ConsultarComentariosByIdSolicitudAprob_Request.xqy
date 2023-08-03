xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComentariosDB";
(:: import schema at "../XSD/ConsultarComentariosByIdSolicitudAproDB.xsd" ::)

declare variable $ConsultarComentariosVotacionRequest as element() (:: schema-element(ns1:ConsultarComentariosVotacionRequest) ::) external;

declare function local:func($ConsultarComentariosVotacionRequest as element() (:: schema-element(ns1:ConsultarComentariosVotacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarComentariosDBInput) ::) {
    <ns2:ConsultarComentariosDBInput>
        <ns2:idSolicitudAprobacion>{fn:data($ConsultarComentariosVotacionRequest/ns1:idSolicitudAprobacion)}</ns2:idSolicitudAprobacion>
    </ns2:ConsultarComentariosDBInput>
};

local:func($ConsultarComentariosVotacionRequest)
