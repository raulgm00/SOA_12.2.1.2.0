xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearActualizarClienteProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarClienteProceso/V1/Schema/CrearActualizarClienteProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/GestionCobroProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC07/GestionCobroProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearActualizarClienteProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioSeguimientoCobro as element() (:: schema-element(ns1:InicioSeguimientoCobro) ::) external;

declare function local:func($InicioSeguimientoCobro as element() (:: schema-element(ns1:InicioSeguimientoCobro) ::)) as element() (:: schema-element(ns2:CrearActualizarClienteProcesoRequest) ::) {
    <ns2:CrearActualizarClienteProcesoRequest>
        <ns2:ClienteProceso>
            <cre:idCliente>{fn:data($InicioSeguimientoCobro/ns1:Header/ns3:Cliente/ns4:IdCliente)}</cre:idCliente>
            <cre:idProceso>32</cre:idProceso>
            <cre:estatus>{fn:true()}</cre:estatus>
        </ns2:ClienteProceso>
    </ns2:CrearActualizarClienteProcesoRequest>
};

local:func($InicioSeguimientoCobro)
