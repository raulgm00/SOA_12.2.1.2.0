xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/CancelarOperacionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA05/CancelarOperacionProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioCancelarOperacion as element() (:: schema-element(ns1:InicioCancelarOperacion) ::) external;

declare function local:func($InicioCancelarOperacion as element() (:: schema-element(ns1:InicioCancelarOperacion) ::)) as element() (:: schema-element(ns2:ConsultarSolicitudDesembolsoRequest) ::) {
    <ns2:ConsultarSolicitudDesembolsoRequest>
        <ns2:idOperacion>{fn:data($InicioCancelarOperacion/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</ns2:idOperacion>
    </ns2:ConsultarSolicitudDesembolsoRequest>
};

local:func($InicioCancelarOperacion)
