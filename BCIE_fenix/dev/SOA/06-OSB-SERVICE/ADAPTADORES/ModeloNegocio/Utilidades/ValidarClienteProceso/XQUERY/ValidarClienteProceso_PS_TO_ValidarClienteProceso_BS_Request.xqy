xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ValidarClienteProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarClienteProceso/V1/Schema/ValidarClienteProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarClienteProceso";
(:: import schema at "../XSD/ValidarClienteProceso.xsd" ::)

declare namespace val = "http://www.bcie.org/ValidarClienteProcesoBO";

declare variable $ValidarClienteProcesoRequest as element() (:: schema-element(ns1:ValidarClienteProcesoRequest) ::) external;

declare function local:func($ValidarClienteProcesoRequest as element() (:: schema-element(ns1:ValidarClienteProcesoRequest) ::)) as element() (:: schema-element(ns2:ValidarClienteProcesoInput) ::) {
    <ns2:ValidarClienteProcesoInput>
        <ns2:idCliente>{fn:data($ValidarClienteProcesoRequest/ns1:ClienteProceso/val:idCliente)}</ns2:idCliente>
        <ns2:idProceso>{fn:data($ValidarClienteProcesoRequest/ns1:ClienteProceso/val:idProceso)}</ns2:idProceso>
    </ns2:ValidarClienteProcesoInput>
};

local:func($ValidarClienteProcesoRequest)
