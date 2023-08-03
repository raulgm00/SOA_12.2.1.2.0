xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EquipoTrabajoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajo/V1/Schema/CrearEquipoTrabajoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarEquipoTrabajoByOperacionProceso";
(:: import schema at "../../ConsultarEquipoTrabajoByOperacionProceso/XSD/ConsultarEquipoTrabajoByOperacionProceso_table.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarEquipoTrabajo";
(:: import schema at "../XSD/CrearActualizarEquipoTrabajo_table.xsd" ::)

declare namespace ns4 = "http://www.bcie.org/EqipoTrabajo/V1";

declare variable $requestCrearEquipoTrabajoMessage as element() (:: schema-element(ns1:requestCrearEquipoTrabajoMessage) ::) external;
declare variable $index as xs:int external;
declare variable $ConsultaEquipoTrabajoCollection as element() (:: schema-element(ns2:EquipoTrabajoCollection) ::) external;

declare function local:func($requestCrearEquipoTrabajoMessage as element() (:: schema-element(ns1:requestCrearEquipoTrabajoMessage) ::), 
                            $index as xs:int, 
                            $ConsultaEquipoTrabajoCollection as element() (:: schema-element(ns2:EquipoTrabajoCollection) ::)) 
                            as element() (:: schema-element(ns3:EquipoTrabajoCollection) ::) {
<ns3:EquipoTrabajoCollection>
        <ns3:EquipoTrabajo>
          {
          for $EquipoTrabajoConsulta in $ConsultaEquipoTrabajoCollection/ns2:EquipoTrabajo
          where $requestCrearEquipoTrabajoMessage/ns1:listadoEquipoTrabajo/ns4:equipoTrabajo[$index]/ns4:IdRol = $EquipoTrabajoConsulta/ns2:idTcaRolBpm
          return
              <ns3:id>{fn:data($EquipoTrabajoConsulta/ns2:id)}</ns3:id>
          }
              <ns3:idOperacion>{fn:data($requestCrearEquipoTrabajoMessage/ns1:idOperacion)}</ns3:idOperacion>
              <ns3:idTcaRolBpm>{fn:data($requestCrearEquipoTrabajoMessage/ns1:listadoEquipoTrabajo/ns4:equipoTrabajo[$index]/ns4:IdRol)}</ns3:idTcaRolBpm>
              <ns3:idTcaProcesoBpm>{fn:data($requestCrearEquipoTrabajoMessage/ns1:listadoEquipoTrabajo/ns4:equipoTrabajo[$index]/ns4:idProceso)}</ns3:idTcaProcesoBpm>
            <ns3:usuario>{fn:data($requestCrearEquipoTrabajoMessage/ns1:listadoEquipoTrabajo/ns4:equipoTrabajo[$index]/ns4:usuario)}</ns3:usuario>
        </ns3:EquipoTrabajo> 
</ns3:EquipoTrabajoCollection>
 



};

local:func($requestCrearEquipoTrabajoMessage, $index, $ConsultaEquipoTrabajoCollection)
