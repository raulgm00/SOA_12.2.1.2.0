xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/AprobacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC04/AprobacionProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinAprobacionCliente as element() (:: schema-element(ns2:FinAprobacionCliente) ::) external;

declare function local:func($FinAprobacionCliente as element() (:: schema-element(ns2:FinAprobacionCliente) ::)) as element() (:: schema-element(ns1:PropagarAnalisisRiesgoRequest) ::) {
    <ns1:PropagarAnalisisRiesgoRequest>
        <ns1:Cliente>
            <cli:idCliente>{fn:data($FinAprobacionCliente/ns2:Header/ns3:Cliente/ns4:IdCliente)}</cli:idCliente>
            <cli:idFacturador>{fn:data($FinAprobacionCliente/ns2:Header/ns3:Cliente/ns4:IdFlexCube)}</cli:idFacturador>
        </ns1:Cliente>
    </ns1:PropagarAnalisisRiesgoRequest>
};

local:func($FinAprobacionCliente)
