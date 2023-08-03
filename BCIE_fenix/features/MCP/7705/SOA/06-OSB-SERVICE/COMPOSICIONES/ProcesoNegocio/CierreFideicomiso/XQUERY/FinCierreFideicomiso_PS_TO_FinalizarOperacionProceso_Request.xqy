xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/FinalizarOperacionProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/FinalizarOperacionProceso/V1/Schema/FinalizarOperacionProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CierreFideicomisoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA14/CierreFideicomisoProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace fin = "http://www.bcie.org/FinalizarOperacionProcesoBO";

declare variable $FinCierreFideicomiso as element() (:: schema-element(ns1:FinCierreFideicomiso) ::) external;

declare function local:func($FinCierreFideicomiso as element() (:: schema-element(ns1:FinCierreFideicomiso) ::)) as element() (:: schema-element(ns2:requestFinalizarOperacionProcesoMessage) ::) {
    <ns2:requestFinalizarOperacionProcesoMessage>
        <ns2:BitacoraInput>
            <fin:idOperacion>{fn:data($FinCierreFideicomiso/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</fin:idOperacion>
            <fin:idProceso>28</fin:idProceso></ns2:BitacoraInput></ns2:requestFinalizarOperacionProcesoMessage>
};

local:func($FinCierreFideicomiso)
