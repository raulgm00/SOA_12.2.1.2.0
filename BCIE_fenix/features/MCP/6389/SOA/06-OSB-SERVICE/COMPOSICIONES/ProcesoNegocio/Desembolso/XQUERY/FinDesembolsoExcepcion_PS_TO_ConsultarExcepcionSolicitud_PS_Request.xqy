xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinDesembolsoExcepcion as element() (:: schema-element(ns1:FinDesembolsoExcepcion) ::) external;

declare function local:func($FinDesembolsoExcepcion as element() (:: schema-element(ns1:FinDesembolsoExcepcion) ::)) as element() (:: schema-element(ns2:ConsultarExcepcionSolicitudRequest) ::) {
    <ns2:ConsultarExcepcionSolicitudRequest>
        <ns2:idSolicitud>{fn:data($FinDesembolsoExcepcion/ns1:Header/ns3:ParameterType[ns3:parameterName = 'ID_SOLICITUD']/ns3:parameterValue)}</ns2:idSolicitud>
    </ns2:ConsultarExcepcionSolicitudRequest>
};

local:func($FinDesembolsoExcepcion)
