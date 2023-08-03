xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/EvaluacionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA10/EvaluacionProcess.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioEvaluacion as element() (:: schema-element(ns1:InicioEvaluacion) ::) external;

declare function local:func($InicioEvaluacion as element() (:: schema-element(ns1:InicioEvaluacion) ::)) as element() (:: schema-element(ns1:InicioEvaluacionIBCIE) ::) {
    <ns1:InicioEvaluacionIBCIE>
          {$InicioEvaluacion/*}
    </ns1:InicioEvaluacionIBCIE>
};

local:func($InicioEvaluacion)
