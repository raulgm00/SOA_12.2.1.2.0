xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ReasignacionRCProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PU02/ReasignacionRCProcess.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $request as element() (:: schema-element(ns1:FinReasignacionCliente) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:FinReasignacionCliente) ::)) as element() (:: schema-element(ns2:ActualizarClienteRequest) ::) {
    <ns2:ActualizarClienteRequest>
        <ns2:Cliente>
            <cli:idCliente>{fn:data($request/ns1:Header/ns3:Cliente/ns4:IdCliente)}</cli:idCliente>
            {
                if ($request/ns1:Header/ns3:Cliente/ns4:ResponsableCliente)
                then
                <cli:ejecutivo>{fn:data($request/ns1:Header/ns3:Cliente/ns4:ResponsableCliente)}</cli:ejecutivo>
                else ()
            }
        </ns2:Cliente>
    </ns2:ActualizarClienteRequest>
};

local:func($request)
