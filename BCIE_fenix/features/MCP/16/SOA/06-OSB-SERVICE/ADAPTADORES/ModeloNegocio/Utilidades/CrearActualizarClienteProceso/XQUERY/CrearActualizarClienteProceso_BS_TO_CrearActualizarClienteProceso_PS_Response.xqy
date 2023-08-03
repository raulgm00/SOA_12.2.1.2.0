xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearActualizarClienteProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarClienteProceso/V1/Schema/CrearActualizarClienteProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarClienteProcesoDB";
(:: import schema at "../XSD/CrearActualizarClienteProcesoDB_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $TreClienteProcesoBpmCollection as element() (:: schema-element(ns1:TreClienteProcesoBpmCollection) ::) external;
declare variable $idClienteProceso as xs:int external;

declare function local:func($TreClienteProcesoBpmCollection as element() (:: schema-element(ns1:TreClienteProcesoBpmCollection) ::), 
                            $idClienteProceso as xs:int) 
                            as element() (:: schema-element(ns2:CrearActualizarClienteProcesoResponse) ::) {
    <ns2:CrearActualizarClienteProcesoResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:concat("Id: ",xs:string($idClienteProceso)," insertado o actualizado correctamente")}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:CrearActualizarClienteProcesoResponse>
};

local:func($TreClienteProcesoBpmCollection, $idClienteProceso)
