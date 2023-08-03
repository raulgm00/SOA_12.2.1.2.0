xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CancelarOperacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA05/CancelarOperacionProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $FinCancelarOperacion as element() (:: schema-element(ns1:FinCancelarOperacion) ::) external;

declare function local:func($FinCancelarOperacion as element() (:: schema-element(ns1:FinCancelarOperacion) ::)) as element() (:: schema-element(ns2:ActualizarSolicitudAprobacionByIdOperacionRequest) ::) {
    <ns2:ActualizarSolicitudAprobacionByIdOperacionRequest>
        <ns2:idOperacion>{fn:data($FinCancelarOperacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns2:idOperacion>
    </ns2:ActualizarSolicitudAprobacionByIdOperacionRequest>
};

local:func($FinCancelarOperacion)
