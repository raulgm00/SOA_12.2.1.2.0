xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarEquipoTrabajoOperacionClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarEquipoTrabajoOperacionCliente/V1/Schema/ConsultarEquipoTrabajoOperacionClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarEquipoTrabajoByOperacionProceso";
(:: import schema at "../../ConsultarEquipoTrabajoByOperacionProceso/XSD/ConsultarEquipoTrabajoByOperacionProceso_table.xsd" ::)

declare variable $ConsultarEquipoTrabajoOperacionClienteRequest as element() (:: schema-element(ns1:ConsultarEquipoTrabajoOperacionClienteRequest) ::) external;

declare function local:func($ConsultarEquipoTrabajoOperacionClienteRequest as element() (:: schema-element(ns1:ConsultarEquipoTrabajoOperacionClienteRequest) ::)) as element() (:: schema-element(ns2:ConsultarEquipoTrabajoByOperacionProcesoSelect_idOperacion_idProcesoInputParameters) ::) {
    <ns2:ConsultarEquipoTrabajoByOperacionProcesoSelect_idOperacion_idProcesoInputParameters>
        <ns2:idOperacion>{fn:data($ConsultarEquipoTrabajoOperacionClienteRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idProceso>{fn:data($ConsultarEquipoTrabajoOperacionClienteRequest/ns1:idProceso)}</ns2:idProceso>
    </ns2:ConsultarEquipoTrabajoByOperacionProcesoSelect_idOperacion_idProcesoInputParameters>
};

local:func($ConsultarEquipoTrabajoOperacionClienteRequest)
