xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/FinalizarOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/FinalizarOperacionProceso/V1/Schema/FinalizarOperacionProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC05/FormalizacionProcess.xsd" ::)

declare namespace fin = "http://www.bcie.org/FinalizarOperacionProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinContratacionFormalizacion as element() (:: schema-element(ns1:FinContratacionFormalizacion) ::) external;

declare function local:func($FinContratacionFormalizacion as element() (:: schema-element(ns1:FinContratacionFormalizacion) ::)) as element() (:: schema-element(ns2:requestFinalizarOperacionProcesoMessage) ::) {
    <ns2:requestFinalizarOperacionProcesoMessage>
        <ns2:BitacoraInput>
            <fin:idOperacion>{fn:data($FinContratacionFormalizacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</fin:idOperacion>
            <fin:idProceso>5</fin:idProceso>
        </ns2:BitacoraInput>
    </ns2:requestFinalizarOperacionProcesoMessage>
};

local:func($FinContratacionFormalizacion)
