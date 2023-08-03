xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEquipoTrabajoReunion";
(:: import schema at "../XSD/ConsultarEquipoTrabajoReunion.xsd" ::)

declare variable $EquipoTrabajoRequest as element() (:: schema-element(ns1:ConsultarUsuarioReunionRequest) ::) external;

declare function local:func($EquipoTrabajoRequest as element() (:: schema-element(ns1:ConsultarUsuarioReunionRequest) ::)) as element() (:: schema-element(ns2:ConsultarEquipoTrabajoReunionInput) ::) {
    <ns2:ConsultarEquipoTrabajoReunionInput>
        <ns2:idOperacion>{fn:data($EquipoTrabajoRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idOperacion1>{fn:data($EquipoTrabajoRequest/ns1:idOperacion)}</ns2:idOperacion1>
    </ns2:ConsultarEquipoTrabajoReunionInput>
};

local:func($EquipoTrabajoRequest)
