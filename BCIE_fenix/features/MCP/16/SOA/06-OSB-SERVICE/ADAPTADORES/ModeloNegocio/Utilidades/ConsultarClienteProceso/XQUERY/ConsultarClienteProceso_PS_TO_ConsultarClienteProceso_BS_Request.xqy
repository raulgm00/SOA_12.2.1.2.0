xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarClienteProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarClienteProceso/V1/Schema/ConsultarClienteProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarClienteProcesoDB";
(:: import schema at "../XSD/ConsultarClienteProcesoDB_table.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarClienteProcesoBO";

declare variable $ConsultarClienteProcesoRequest as element() (:: schema-element(ns1:ConsultarClienteProcesoRequest) ::) external;

declare function local:func($ConsultarClienteProcesoRequest as element() (:: schema-element(ns1:ConsultarClienteProcesoRequest) ::)) as element() (:: schema-element(ns2:ConsultarClienteProcesoDBSelect_idCliente_idProcesoInputParameters) ::) {
    <ns2:ConsultarClienteProcesoDBSelect_idCliente_idProcesoInputParameters>
        <ns2:idCliente>{fn:data($ConsultarClienteProcesoRequest/ns1:ClienteProceso/con:idCliente)}</ns2:idCliente>
        <ns2:idProceso>{fn:data($ConsultarClienteProcesoRequest/ns1:ClienteProceso/con:idProceso)}</ns2:idProceso>
    </ns2:ConsultarClienteProcesoDBSelect_idCliente_idProcesoInputParameters>
};

local:func($ConsultarClienteProcesoRequest)
