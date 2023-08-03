xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FinalizarOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/FinalizarOperacionProceso/V1/Schema/FinalizarOperacionProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/PrepagoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA12/PrepagoProcess.xsd" ::)

declare namespace fin = "http://www.bcie.org/FinalizarOperacionProcesoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinPrepago as element() (:: schema-element(ns2:FinPrepago) ::) external;

declare function local:func($FinPrepago as element() (:: schema-element(ns2:FinPrepago) ::)) as element() (:: schema-element(ns1:requestFinalizarOperacionProcesoMessage) ::) {
    <ns1:requestFinalizarOperacionProcesoMessage>
        <ns1:BitacoraInput>
            <fin:idOperacion>{fn:data($FinPrepago/ns2:Header/ns3:Operacion/ns4:CodigoOperacion)}</fin:idOperacion>
            <fin:idProceso>26</fin:idProceso>
        </ns1:BitacoraInput>
    </ns1:requestFinalizarOperacionProcesoMessage>
};

local:func($FinPrepago)
