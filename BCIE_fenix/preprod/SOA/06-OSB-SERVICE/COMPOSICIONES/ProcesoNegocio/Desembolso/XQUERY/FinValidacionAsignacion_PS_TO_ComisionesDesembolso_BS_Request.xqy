xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinValidacionAsignacion as element() (:: schema-element(ns1:FinValidacionAsignacion) ::) external;

declare function local:func($FinValidacionAsignacion as element() (:: schema-element(ns1:FinValidacionAsignacion) ::)) as element() (:: schema-element(ns2:ComisionesDesembolsoRequest) ::) {
    <ns2:ComisionesDesembolsoRequest>
        <ns2:idSolicitudDesembolso>{fn:data($FinValidacionAsignacion/ns1:Header/ns3:ParameterType[ns3:parameterName='ID_SOLICITUD']/ns3:parameterValue)}</ns2:idSolicitudDesembolso>
    </ns2:ComisionesDesembolsoRequest>
};

local:func($FinValidacionAsignacion)
