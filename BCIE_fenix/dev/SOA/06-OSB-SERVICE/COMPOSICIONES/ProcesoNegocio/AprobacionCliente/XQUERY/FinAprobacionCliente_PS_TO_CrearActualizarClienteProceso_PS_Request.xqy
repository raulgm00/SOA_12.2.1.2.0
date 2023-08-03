xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearActualizarClienteProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarClienteProceso/V1/Schema/CrearActualizarClienteProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/AprobacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC04/AprobacionProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearActualizarClienteProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinAprobacionCliente as element() (:: schema-element(ns1:FinAprobacionCliente) ::) external;

declare function local:func($FinAprobacionCliente as element() (:: schema-element(ns1:FinAprobacionCliente) ::)) as element() (:: schema-element(ns2:CrearActualizarClienteProcesoRequest) ::) {
    <ns2:CrearActualizarClienteProcesoRequest>
        <ns2:ClienteProceso>
            <cre:idCliente>{fn:data($FinAprobacionCliente/ns1:Header/ns3:Cliente/ns4:IdCliente)}</cre:idCliente>
            <cre:idProceso>4</cre:idProceso>
            <cre:estatus>{fn:false()}</cre:estatus>
        </ns2:ClienteProceso>
    </ns2:CrearActualizarClienteProcesoRequest>
};

local:func($FinAprobacionCliente)
