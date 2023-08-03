xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ValidarClienteEnProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarClienteEnProceso/V1/Schema/ValidarClienteEnProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarClienteEnProcesoByIdCliente";
(:: import schema at "../XSD/ConsultarClienteEnProcesoByIdCliente.xsd" ::)

declare namespace val = "http://www.bcie.org/ValidarClienteEnProcesoBO";

declare variable $ValidarClienteEnProcesoRequest as element() (:: schema-element(ns1:ValidarClienteEnProcesoRequest) ::) external;

declare function local:func($ValidarClienteEnProcesoRequest as element() (:: schema-element(ns1:ValidarClienteEnProcesoRequest) ::)) as element() (:: schema-element(ns2:ConsultarClienteEnProcesoByIdClienteInput) ::) {
    <ns2:ConsultarClienteEnProcesoByIdClienteInput>
        <ns2:idCliente>{fn:data($ValidarClienteEnProcesoRequest/ns1:ClienteEnProceso/val:idCliente)}</ns2:idCliente>
        <ns2:idCliente2>{fn:data($ValidarClienteEnProcesoRequest/ns1:ClienteEnProceso/val:idCliente)}</ns2:idCliente2>
    </ns2:ConsultarClienteEnProcesoByIdClienteInput>
};

local:func($ValidarClienteEnProcesoRequest)
