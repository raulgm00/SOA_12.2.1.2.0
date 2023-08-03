xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EquipoTrabajoClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajoCliente/V1/Schema/CrearEquipoTrabajoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/UCEProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA13/UCEProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://www.bcie.org/EqipoTrabajo/V1";

declare variable $FinUCE as element() (:: schema-element(ns1:FinUCE) ::) external;

declare function local:func($FinUCE as element() (:: schema-element(ns1:FinUCE) ::)) as element() (:: schema-element(ns2:CrearEquipoTrabajoClienteRequest) ::) {
    <ns2:CrearEquipoTrabajoClienteRequest>
        <ns2:idCliente>{fn:data($FinUCE/ns1:Header/ns3:Cliente/ns4:IdCliente)}</ns2:idCliente>
        <ns2:listadoEquipoTrabajo>
            {
                for $equipoTrabajo in $FinUCE/ns1:equipoTrabajo/ns5:equipoTrabajo
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
    </ns2:CrearEquipoTrabajoClienteRequest>
};

local:func($FinUCE)
