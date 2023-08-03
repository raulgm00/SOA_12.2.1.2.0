xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CancelarOperacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA05/CancelarOperacionProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinCancelarOperacion as element() (:: schema-element(ns1:FinCancelarOperacion) ::) external;

declare function local:func($FinCancelarOperacion as element() (:: schema-element(ns1:FinCancelarOperacion) ::)) as element() (:: schema-element(ns2:consultarBitacoraProcesoRequest) ::) {
    <ns2:consultarBitacoraProcesoRequest>
        <ns2:idOperacion>{fn:data($FinCancelarOperacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns2:idOperacion>
        <ns2:idProceso></ns2:idProceso>
        <ns2:idTipo></ns2:idTipo>
        <ns2:Tipo></ns2:Tipo>
    </ns2:consultarBitacoraProcesoRequest>
};

local:func($FinCancelarOperacion)
