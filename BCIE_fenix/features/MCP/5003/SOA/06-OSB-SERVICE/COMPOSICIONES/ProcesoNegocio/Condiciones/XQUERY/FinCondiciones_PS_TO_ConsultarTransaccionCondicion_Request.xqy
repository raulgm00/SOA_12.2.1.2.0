xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CondicionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA03/CondicionesProcess.xsd" ::)

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $inConsultarTransaccionCondicion as element() (:: schema-element(ns1:FinCondiciones) ::) external;

declare function local:func($inConsultarTransaccionCondicion as element() (:: schema-element(ns1:FinCondiciones) ::)) as element() (:: schema-element(ns2:ConsultarTransaccionCondicionRequest) ::) {
    <ns2:ConsultarTransaccionCondicionRequest>
        <ns2:Agrupador>{fn:data($inConsultarTransaccionCondicion/ns1:Header/ns3:ParameterType[ns3:parameterName = 'AGRUPADOR']/ns3:parameterValue)}</ns2:Agrupador>
    </ns2:ConsultarTransaccionCondicionRequest>
};

local:func($inConsultarTransaccionCondicion)