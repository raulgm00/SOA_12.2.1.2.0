xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDesAproByIdNivelAprobacion";
(:: import schema at "../XSD/ConsultarDesAproByIdNivelAprobacion.xsd" ::)

declare variable $ConsultarDecisionAprobacionRequest as element() (:: schema-element(ns1:ConsultarDecisionAprobacionByIdAprobacionRequest) ::) external;

declare function local:func($ConsultarDecisionAprobacionRequest as element() (:: schema-element(ns1:ConsultarDecisionAprobacionByIdAprobacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarDesAproByIdNivelAprobacionInput) ::) {
    <ns2:ConsultarDesAproByIdNivelAprobacionInput>
        <ns2:ID>{fn:data($ConsultarDecisionAprobacionRequest/ns1:idAprobacion)}</ns2:ID>
        
    </ns2:ConsultarDesAproByIdNivelAprobacionInput>
};

local:func($ConsultarDecisionAprobacionRequest)
