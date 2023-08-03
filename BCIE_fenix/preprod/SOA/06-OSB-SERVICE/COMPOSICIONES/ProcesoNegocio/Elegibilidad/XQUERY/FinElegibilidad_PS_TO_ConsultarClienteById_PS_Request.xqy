xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ElegibilidadProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC01/ElegibilidadProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinElegibilidad as element() (:: schema-element(ns1:FinElegibilidad) ::) external;

declare function local:func($FinElegibilidad as element() (:: schema-element(ns1:FinElegibilidad) ::)) as element() (:: schema-element(ns2:ConsultarClienteByIdClienteRequest) ::) {
    <ns2:ConsultarClienteByIdClienteRequest>
        <ns2:idCliente>{fn:data($FinElegibilidad/ns1:Header/ns3:Operacion/ns4:CodigoCliente)}</ns2:idCliente>
        <ns2:idOperacion> </ns2:idOperacion>
    </ns2:ConsultarClienteByIdClienteRequest>
};

local:func($FinElegibilidad)
