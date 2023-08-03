xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearActualizarClienteProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarClienteProceso/V1/Schema/CrearActualizarClienteProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarClienteProcesoDB";
(:: import schema at "../XSD/CrearActualizarClienteProcesoDB_table.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearActualizarClienteProcesoBO";

declare variable $CrearActualizarClienteProcesoRequest as element() (:: schema-element(ns1:CrearActualizarClienteProcesoRequest) ::) external;
declare variable $idClienteProceso as xs:int external;

declare function local:func($CrearActualizarClienteProcesoRequest as element() (:: schema-element(ns1:CrearActualizarClienteProcesoRequest) ::), 
                            $idClienteProceso as xs:int) 
                            as element() (:: schema-element(ns2:TreClienteProcesoBpmCollection) ::) {
    <ns2:TreClienteProcesoBpmCollection>
        <ns2:TreClienteProcesoBpm>
            <ns2:id>{fn:data($idClienteProceso)}</ns2:id>
            <ns2:idCliente>{fn:data($CrearActualizarClienteProcesoRequest/ns1:ClienteProceso/cre:idCliente)}</ns2:idCliente>
            <ns2:idProceso>{fn:data($CrearActualizarClienteProcesoRequest/ns1:ClienteProceso/cre:idProceso)}</ns2:idProceso>
            <ns2:banEstatus>{
            if (fn:data($CrearActualizarClienteProcesoRequest/ns1:ClienteProceso/cre:estatus) = true())
            then (1)
            else (0)}</ns2:banEstatus>
        </ns2:TreClienteProcesoBpm>
    </ns2:TreClienteProcesoBpmCollection>
};

local:func($CrearActualizarClienteProcesoRequest, $idClienteProceso)
