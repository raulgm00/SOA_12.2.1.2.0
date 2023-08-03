xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EquipoTrabajoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajo/V1/Schema/CrearEquipoTrabajoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarEquipoTrabajoByOperacionProceso";
(:: import schema at "../../ConsultarEquipoTrabajoByOperacionProceso/XSD/ConsultarEquipoTrabajoByOperacionProceso_table.xsd" ::)

declare namespace ns3 = "http://www.bcie.org/EqipoTrabajo/V1";

declare variable $requestCrearEquipoTrabajoMessage as element() (:: schema-element(ns1:requestCrearEquipoTrabajoMessage) ::) external;


declare function local:func($requestCrearEquipoTrabajoMessage as element() (:: schema-element(ns1:requestCrearEquipoTrabajoMessage) ::)) 
                            as element() (:: schema-element(ns2:ConsultarEquipoTrabajoByOperacionProcesoSelect_idOperacion_idProcesoInputParameters) ::) {
    <ns2:ConsultarEquipoTrabajoByOpecionRolProcesoInput>
        <ns2:idOperacion>{fn:data($requestCrearEquipoTrabajoMessage/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idProceso>{fn:data($requestCrearEquipoTrabajoMessage/ns1:listadoEquipoTrabajo/ns3:equipoTrabajo[1]/ns3:idProceso)}</ns2:idProceso>
    </ns2:ConsultarEquipoTrabajoByOpecionRolProcesoInput>
};

local:func($requestCrearEquipoTrabajoMessage)
