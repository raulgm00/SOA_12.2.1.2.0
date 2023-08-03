xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/EvaluacionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA10/EvaluacionProcess.xsd" ::)

declare variable $InicioEvaluacion as element() (:: schema-element(ns1:InicioEvaluacion) ::) external;

declare function local:func($InicioEvaluacion as element() (:: schema-element(ns1:InicioEvaluacion) ::)) as element() (:: schema-element(ns1:InicioEvaluacionSIEMAS) ::) {
    <ns1:InicioEvaluacionSIEMAS>
      {$InicioEvaluacion/*}
    </ns1:InicioEvaluacionSIEMAS>
};

local:func($InicioEvaluacion)
