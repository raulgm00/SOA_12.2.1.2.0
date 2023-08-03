xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EquipoTrabajoClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajoCliente/V1/Schema/CrearEquipoTrabajoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEquipoTrabajoByClienteProceso";
(:: import schema at "../../ConsultarEquipoTrabajoByClienteProceso/XSD/ConsultarEquipoTrabajoByClienteProceso.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearEquipoTrabajoCliente";
(:: import schema at "../XSD/CrearEquipoTrabajoCliente_table.xsd" ::)

declare namespace ns4 = "http://www.bcie.org/EqipoTrabajo/V1";

declare variable $ConsultarEquipoTrabajoByClienteProcesoOutputCollection as element() (:: schema-element(ns1:ConsultarEquipoTrabajoByClienteProcesoOutputCollection) ::) external;
declare variable $CrearEquipoTrabajoClienteRequest as element() (:: schema-element(ns2:CrearEquipoTrabajoClienteRequest) ::) external;
declare variable $index as xs:int external;

declare function local:func($ConsultarEquipoTrabajoByClienteProcesoOutputCollection as element() (:: schema-element(ns1:ConsultarEquipoTrabajoByClienteProcesoOutputCollection) ::), 
                            $CrearEquipoTrabajoClienteRequest as element() (:: schema-element(ns2:CrearEquipoTrabajoClienteRequest) ::),
                            $index as xs:int) 
                            as element() (:: schema-element(ns3:EquipoTrabajoCollection) ::) {
    <ns3:EquipoTrabajoCollection>
        <ns3:EquipoTrabajo>
          {for $equipoTrabajo in $ConsultarEquipoTrabajoByClienteProcesoOutputCollection/ns1:ConsultarEquipoTrabajoByClienteProcesoOutput
          where $CrearEquipoTrabajoClienteRequest/ns2:listadoEquipoTrabajo/ns4:equipoTrabajo[$index]/ns4:IdRol = $equipoTrabajo/ns1:ID_TCA_ROL_BPM
          return
            <ns3:id>{fn:data($equipoTrabajo/ns1:ID)}</ns3:id>
          }
            <ns3:idTcaRolBpm>{fn:data($CrearEquipoTrabajoClienteRequest/ns2:listadoEquipoTrabajo/ns4:equipoTrabajo[$index]/ns4:IdRol)}</ns3:idTcaRolBpm>
            <ns3:idTcaProcesoBpm>{fn:data($CrearEquipoTrabajoClienteRequest/ns2:listadoEquipoTrabajo/ns4:equipoTrabajo[$index]/ns4:idProceso)}</ns3:idTcaProcesoBpm>
            <ns3:usuario>{fn:data($CrearEquipoTrabajoClienteRequest/ns2:listadoEquipoTrabajo/ns4:equipoTrabajo[$index]/ns4:usuario)}</ns3:usuario>
            <ns3:idCliente>{fn:data($CrearEquipoTrabajoClienteRequest/ns2:idCliente)}</ns3:idCliente>
        </ns3:EquipoTrabajo>
    </ns3:EquipoTrabajoCollection>
};

local:func($ConsultarEquipoTrabajoByClienteProcesoOutputCollection, $CrearEquipoTrabajoClienteRequest,$index)
