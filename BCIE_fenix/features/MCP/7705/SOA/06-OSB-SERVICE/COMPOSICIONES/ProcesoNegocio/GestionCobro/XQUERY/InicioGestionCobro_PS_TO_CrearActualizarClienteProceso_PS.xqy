xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearActualizarClienteProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarClienteProceso/V1/Schema/CrearActualizarClienteProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/GestionCobroProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC07/GestionCobroProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearActualizarClienteProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioGestionCobro as element() (:: schema-element(ns2:InicioGestionCobro) ::) external;

declare function local:func($InicioGestionCobro as element() (:: schema-element(ns2:InicioGestionCobro) ::)) as element() (:: schema-element(ns1:CrearActualizarClienteProcesoRequest) ::) {
    <ns1:CrearActualizarClienteProcesoRequest>
        <ns1:ClienteProceso>
            <cre:idCliente>{fn:data($InicioGestionCobro/ns2:Header/ns3:Cliente/ns4:IdCliente)}</cre:idCliente>
            <cre:idProceso>18</cre:idProceso>
            <cre:estatus>{fn:false()}</cre:estatus>
        </ns1:ClienteProceso>
    </ns1:CrearActualizarClienteProcesoRequest>
};

local:func($InicioGestionCobro)
