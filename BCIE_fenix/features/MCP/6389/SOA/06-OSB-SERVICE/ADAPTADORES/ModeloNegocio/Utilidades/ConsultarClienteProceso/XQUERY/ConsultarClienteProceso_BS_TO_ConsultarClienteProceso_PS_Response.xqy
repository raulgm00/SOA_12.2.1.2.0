xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarClienteProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarClienteProceso/V1/Schema/ConsultarClienteProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarClienteProcesoDB";
(:: import schema at "../XSD/ConsultarClienteProcesoDB_table.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarClienteProcesoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $TreClienteProcesoBpmCollection as element() (:: schema-element(ns1:TreClienteProcesoBpmCollection) ::) external;

declare function local:func($TreClienteProcesoBpmCollection as element() (:: schema-element(ns1:TreClienteProcesoBpmCollection) ::)) as element() (:: schema-element(ns2:ConsultarClienteProcesoResponse) ::) {
    <ns2:ConsultarClienteProcesoResponse>
    {
    for $clienteProceso in $TreClienteProcesoBpmCollection/ns1:TreClienteProcesoBpm
    return
        <ns2:ClienteProceso>
            <con:id>{fn:data($clienteProceso/ns1:ID)}</con:id>
            <con:idCliente>{fn:data($clienteProceso/ns1:ID_CLIENTE)}</con:idCliente>
            <con:idProceso>{fn:data($clienteProceso/ns1:ID_PROCESO)}</con:idProceso>
            <con:estatus>{fn:data($clienteProceso/ns1:BAN_ESTATUS)}</con:estatus>
        </ns2:ClienteProceso>
   }
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>{
        if (count($TreClienteProcesoBpmCollection/ns1:TreClienteProcesoBpm)> 0)then
            ("Consulta Exitosa")
            else
            ("No existen registros")
            }
          </res:message>  
        <res:error>
            <err:errorCode></err:errorCode>
            <err:errorDescription></err:errorDescription>
            <err:errorType></err:errorType>
        </res:error>
    </ns2:Resultado>
    </ns2:ConsultarClienteProcesoResponse>
};

local:func($TreClienteProcesoBpmCollection)
