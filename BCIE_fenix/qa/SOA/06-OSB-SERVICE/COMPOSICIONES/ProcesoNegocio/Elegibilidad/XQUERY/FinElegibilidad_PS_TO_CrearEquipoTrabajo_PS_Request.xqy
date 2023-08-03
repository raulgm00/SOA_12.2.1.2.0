xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EquipoTrabajoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajo/V1/Schema/CrearEquipoTrabajoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ElegibilidadProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC01/ElegibilidadProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://www.bcie.org/EqipoTrabajo/V1";

declare variable $FinElegibilidad as element() (:: schema-element(ns1:FinElegibilidad) ::) external;

declare function local:func($FinElegibilidad as element() (:: schema-element(ns1:FinElegibilidad) ::)) as element() (:: schema-element(ns2:requestCrearEquipoTrabajoMessage) ::) {
    <ns2:requestCrearEquipoTrabajoMessage>
        <ns2:idOperacion>{fn:data($FinElegibilidad/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns2:idOperacion>
        <ns2:listadoEquipoTrabajo>
            {
                for $equipoTrabajo in $FinElegibilidad/ns1:equipoTrabajo/ns5:equipoTrabajo
                where string($equipoTrabajo/ns5:usuario)!= ''
                return 
                <ns5:equipoTrabajo>
                    <ns5:DescripcionRol>{fn:data($equipoTrabajo/ns5:DescripcionRol)}</ns5:DescripcionRol>
                    <ns5:IdRol>{fn:data($equipoTrabajo/ns5:IdRol)}</ns5:IdRol>
                    <ns5:DescripcionCortaRol>{fn:data($equipoTrabajo/ns5:DescripcionCortaRol)}</ns5:DescripcionCortaRol>
                    <ns5:idProceso>{fn:data($equipoTrabajo/ns5:idProceso)}</ns5:idProceso>
                    <ns5:usuario>{fn:data($equipoTrabajo/ns5:usuario)}</ns5:usuario>
                </ns5:equipoTrabajo>
            }
        </ns2:listadoEquipoTrabajo>
    </ns2:requestCrearEquipoTrabajoMessage>
};

local:func($FinElegibilidad)
