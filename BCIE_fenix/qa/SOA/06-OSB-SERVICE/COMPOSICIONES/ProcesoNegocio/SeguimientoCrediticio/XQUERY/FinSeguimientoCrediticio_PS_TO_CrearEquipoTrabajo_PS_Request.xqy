xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EquipoTrabajoClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearEquipoTrabajoCliente/V1/Schema/CrearEquipoTrabajoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/SegCrediticioProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA07/SegCrediticioProcess.xsd" ::)

declare namespace ns3 = "http://www.bcie.org/EqipoTrabajo/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare variable $FinSeguimientoCrediticio as element() (:: schema-element(ns1:FinSeguimientoCrediticio) ::) external;

declare function local:func($FinSeguimientoCrediticio as element() (:: schema-element(ns1:FinSeguimientoCrediticio) ::)) as element() (:: schema-element(ns2:CrearEquipoTrabajoClienteRequest) ::) {
    <ns2:CrearEquipoTrabajoClienteRequest>
        <ns2:idCliente>{fn:data($FinSeguimientoCrediticio/ns1:Header/ns4:Cliente/ns5:IdCliente)}</ns2:idCliente>
        <ns2:listadoEquipoTrabajo>
            {
                for $equipoTrabajo in $FinSeguimientoCrediticio/ns1:equipoTrabajo/ns3:equipoTrabajo
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
    </ns2:CrearEquipoTrabajoClienteRequest>
};

local:func($FinSeguimientoCrediticio)
