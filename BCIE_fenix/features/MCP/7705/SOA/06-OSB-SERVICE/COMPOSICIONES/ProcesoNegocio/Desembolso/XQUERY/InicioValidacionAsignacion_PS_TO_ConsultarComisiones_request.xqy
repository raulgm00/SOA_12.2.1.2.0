xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $inComisiones as element() (:: schema-element(ns2:InicioValidacionAsignacion) ::) external;

declare function local:func($inComisiones as element() (:: schema-element(ns2:InicioValidacionAsignacion) ::)) as element() (:: schema-element(ns1:ComisionesDesembolsoRequest) ::) {
    <ns1:ComisionesDesembolsoRequest>
        <ns1:idSolicitudDesembolso>{fn:data($inComisiones/ns2:Header/ns3:ParameterType[ns3:parameterName='ID_SOLICITUD']/ns3:parameterValue)}</ns1:idSolicitudDesembolso>
    </ns1:ComisionesDesembolsoRequest>
};

local:func($inComisiones)
