xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearOperacionProceso/V1/Schema/CrearOperacionProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/EvaluacionesProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA10/EvaluacionProcess.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearOperacionProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioEvaluacionIBCIE as element() (:: schema-element(ns1:InicioEvaluacionIBCIE) ::) external;

declare function local:func($InicioEvaluacionIBCIE as element() (:: schema-element(ns1:InicioEvaluacionIBCIE) ::)) as element() (:: schema-element(ns2:requestCrearOperacionProcesoMessage) ::) {
    <ns2:requestCrearOperacionProcesoMessage>
        <ns2:OperacionProcesoInput>
            <cre:idOperacion>{fn:data($InicioEvaluacionIBCIE/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</cre:idOperacion>
            <cre:idProceso>6</cre:idProceso>
        </ns2:OperacionProcesoInput>
    </ns2:requestCrearOperacionProcesoMessage>
};

local:func($InicioEvaluacionIBCIE)
