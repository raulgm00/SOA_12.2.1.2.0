xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarClienteProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarClienteProceso/V1/Schema/ConsultarClienteProcesoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/CrearActualizarClienteProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarClienteProceso/V1/Schema/CrearActualizarClienteProcesoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarClienteProcesoBO";

declare namespace cre = "http://www.bcie.org/CrearActualizarClienteProcesoBO";

declare variable $CrearActualizarClienteProcesoRequest as element() (:: schema-element(ns1:CrearActualizarClienteProcesoRequest) ::) external;

declare function local:func($CrearActualizarClienteProcesoRequest as element() (:: schema-element(ns1:CrearActualizarClienteProcesoRequest) ::)) as element() (:: schema-element(ns2:ConsultarClienteProcesoRequest) ::) {
    <ns2:ConsultarClienteProcesoRequest>
        <ns2:ClienteProceso>
            <con:id></con:id>
            <con:idCliente>{fn:data($CrearActualizarClienteProcesoRequest/ns1:ClienteProceso/cre:idCliente)}</con:idCliente>
            <con:idProceso>{fn:data($CrearActualizarClienteProcesoRequest/ns1:ClienteProceso/cre:idProceso)}</con:idProceso>
            <con:estatus></con:estatus>
        </ns2:ClienteProceso>
    </ns2:ConsultarClienteProcesoRequest>
};

local:func($CrearActualizarClienteProcesoRequest)
