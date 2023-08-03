xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $inConsultar as element() (:: schema-element(ns1:InicioValidacionAsignacion) ::) external;

declare function local:func($inConsultar as element() (:: schema-element(ns1:InicioValidacionAsignacion) ::)) as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdOperacionRequest) ::) {
    <ns2:ConsultarLineaCreditoByIdOperacionRequest>
        <ns2:IdOperacion>{fn:data($inConsultar/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns2:IdOperacion>
    </ns2:ConsultarLineaCreditoByIdOperacionRequest>
};

local:func($inConsultar)
