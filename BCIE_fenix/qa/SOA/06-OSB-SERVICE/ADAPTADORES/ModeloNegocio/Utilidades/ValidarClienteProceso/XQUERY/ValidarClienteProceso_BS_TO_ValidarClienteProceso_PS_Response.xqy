xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ValidarClienteProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarClienteProceso/V1/Schema/ValidarClienteProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarClienteProceso";
(:: import schema at "../XSD/ValidarClienteProceso.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarClienteProcesoOutputCollection as element() (:: schema-element(ns1:ValidarClienteProcesoOutputCollection) ::) external;

declare function local:func($ValidarClienteProcesoOutputCollection as element() (:: schema-element(ns1:ValidarClienteProcesoOutputCollection) ::)) as element() (:: schema-element(ns2:ValidarClienteProcesoResponse) ::) {
    <ns2:ValidarClienteProcesoResponse>
        <ns2:enProceso>{fn:data($ValidarClienteProcesoOutputCollection/ns1:ValidarClienteProcesoOutput/ns1:enProceso)}</ns2:enProceso>
        <ns2:Resultado>
            <res:result>OK</res:result>
            {if(fn:count($ValidarClienteProcesoOutputCollection/ns1:ValidarClienteProcesoOutput) > 0)then
              <res:message>Consulta exitosa</res:message>
            else
              <res:message>Consulta sin resultados</res:message>
            }
        </ns2:Resultado>
    </ns2:ValidarClienteProcesoResponse>
};

local:func($ValidarClienteProcesoOutputCollection)
