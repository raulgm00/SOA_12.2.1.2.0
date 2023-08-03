xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EquipoTrabajoClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajoCliente/V1/Schema/CrearEquipoTrabajoClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEquipoTrabajoByClienteProceso";
(:: import schema at "../../ConsultarEquipoTrabajoByClienteProceso/XSD/ConsultarEquipoTrabajoByClienteProceso.xsd" ::)

declare namespace ns3 = "http://www.bcie.org/EqipoTrabajo/V1";

declare variable $CrearEquipoTrabajoClienteRequest as element() (:: schema-element(ns1:CrearEquipoTrabajoClienteRequest) ::) external;

declare function local:func($CrearEquipoTrabajoClienteRequest as element() (:: schema-element(ns1:CrearEquipoTrabajoClienteRequest) ::)) as element() (:: schema-element(ns2:ConsultarEquipoTrabajoByClienteProcesoInput) ::) {
    <ns2:ConsultarEquipoTrabajoByClienteProcesoInput>
        <ns2:idCliente>{fn:data($CrearEquipoTrabajoClienteRequest/ns1:idCliente)}</ns2:idCliente>
        <ns2:idProceso>{fn:data($CrearEquipoTrabajoClienteRequest/ns1:listadoEquipoTrabajo/ns3:equipoTrabajo[1]/ns3:idProceso)}</ns2:idProceso>
    </ns2:ConsultarEquipoTrabajoByClienteProcesoInput>
};

local:func($CrearEquipoTrabajoClienteRequest)
