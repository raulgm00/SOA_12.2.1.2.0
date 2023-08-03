xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EquipoTrabajoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajo/V1/Schema/CrearEquipoTrabajoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/PreparacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC02/PreparacionProcess.xsd" ::)

declare namespace ns3 = "http://www.bcie.org/EqipoTrabajo/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare variable $FinPreparacion as element() (:: schema-element(ns1:FinPreparacion) ::) external;

declare function local:func($FinPreparacion as element() (:: schema-element(ns1:FinPreparacion) ::)) as element() (:: schema-element(ns2:requestCrearEquipoTrabajoMessage) ::) {
    <ns2:requestCrearEquipoTrabajoMessage>
        <ns2:idOperacion>{fn:data($FinPreparacion/ns1:Header/ns4:Operacion/ns5:CodigoOperacion)}</ns2:idOperacion>
        <ns2:listadoEquipoTrabajo>
            {
                for $equipoTrabajo in $FinPreparacion/ns1:equipoTrabajo/ns3:equipoTrabajo
                where string($equipoTrabajo/ns3:usuario)!= ''
                return 
                <ns3:equipoTrabajo>
                    <ns3:DescripcionRol>{fn:data($equipoTrabajo/ns3:DescripcionRol)}</ns3:DescripcionRol>
                    <ns3:IdRol>{fn:data($equipoTrabajo/ns3:IdRol)}</ns3:IdRol>
                    <ns3:DescripcionCortaRol>{fn:data($equipoTrabajo/ns3:DescripcionCortaRol)}</ns3:DescripcionCortaRol>
                    <ns3:idProceso>{fn:data($equipoTrabajo/ns3:idProceso)}</ns3:idProceso>
                    <ns3:usuario>{fn:data($equipoTrabajo/ns3:usuario)}</ns3:usuario>
                </ns3:equipoTrabajo>
            }
        </ns2:listadoEquipoTrabajo>
    </ns2:requestCrearEquipoTrabajoMessage>
};

local:func($FinPreparacion)
