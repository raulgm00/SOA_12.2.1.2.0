xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarVotosEmitidos_BD";
(:: import schema at "../XSD/ConsultarVotosEmitidos_BD.xsd" ::)

declare variable $ConsultarVotosRequest as element() (:: schema-element(ns1:ConsultarVotosRequest) ::) external;

declare function local:func($ConsultarVotosRequest as element() (:: schema-element(ns1:ConsultarVotosRequest) ::)) as element() (:: schema-element(ns2:ConsultarVotosEmitidos_BDInput) ::) {
    <ns2:ConsultarVotosEmitidos_BDInput>
        <ns2:idSolicitud>{fn:data($ConsultarVotosRequest/ns1:idSolicitudAprobacion)}</ns2:idSolicitud>
    </ns2:ConsultarVotosEmitidos_BDInput>
};

local:func($ConsultarVotosRequest)
