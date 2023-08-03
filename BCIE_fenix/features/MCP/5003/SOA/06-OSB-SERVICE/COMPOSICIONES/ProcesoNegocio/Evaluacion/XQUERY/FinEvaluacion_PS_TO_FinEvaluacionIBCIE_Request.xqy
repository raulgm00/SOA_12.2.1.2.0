xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/EvaluacionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA10/EvaluacionProcess.xsd" ::)

declare variable $FinEvaluacion as element() (:: schema-element(ns1:FinEvaluacion) ::) external;

declare function local:func($FinEvaluacion as element() (:: schema-element(ns1:FinEvaluacion) ::)) as element() (:: schema-element(ns1:FinEvaluacionIBCIE) ::) {
    <ns1:FinEvaluacionIBCIE>
    {$FinEvaluacion/*}
    </ns1:FinEvaluacionIBCIE>
};

local:func($FinEvaluacion)
