xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinValidacionAsignacion as element() (:: schema-element(ns1:FinValidacionAsignacion) ::) external;

declare function local:func($FinValidacionAsignacion as element() (:: schema-element(ns1:FinValidacionAsignacion) ::)) as element() (:: schema-element(ns2:ConsultarContratoBySolicitudRequest) ::) {
    <ns2:ConsultarContratoBySolicitudRequest>
        <ns2:idSolicitud>{fn:data($FinValidacionAsignacion/ns1:Header/ns3:ParameterType[ns3:parameterName='ID_SOLICITUD']/ns3:parameterValue)}</ns2:idSolicitud>
    </ns2:ConsultarContratoBySolicitudRequest>
};

local:func($FinValidacionAsignacion)

