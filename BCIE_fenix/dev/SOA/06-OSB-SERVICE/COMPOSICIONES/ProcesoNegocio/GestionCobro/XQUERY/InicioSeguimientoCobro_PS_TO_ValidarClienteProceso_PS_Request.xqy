xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ValidarClienteProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarClienteProceso/V1/Schema/ValidarClienteProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/GestionCobroProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC07/GestionCobroProcess.xsd" ::)

declare namespace val = "http://www.bcie.org/ValidarClienteProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioSeguimientoCobro as element() (:: schema-element(ns1:InicioSeguimientoCobro) ::) external;

declare function local:func($InicioSeguimientoCobro as element() (:: schema-element(ns1:InicioSeguimientoCobro) ::)) as element() (:: schema-element(ns2:ValidarClienteProcesoRequest) ::) {
    <ns2:ValidarClienteProcesoRequest>
        <ns2:ClienteProceso>
            <val:idCliente>{fn:data($InicioSeguimientoCobro/ns1:Header/ns3:Cliente/ns4:IdCliente)}</val:idCliente>
            <val:idProceso>32</val:idProceso>
        </ns2:ClienteProceso>
    </ns2:ValidarClienteProcesoRequest>
};

local:func($InicioSeguimientoCobro)
