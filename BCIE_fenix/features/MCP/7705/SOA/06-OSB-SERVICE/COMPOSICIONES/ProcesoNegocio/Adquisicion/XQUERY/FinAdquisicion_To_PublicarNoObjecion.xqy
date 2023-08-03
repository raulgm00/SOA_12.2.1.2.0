xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/AdquisicionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA09/AdquisicionProcess.xsd" ::)

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinAdquisicion as element() (:: schema-element(ns1:FinAdquisicion) ::) external;

declare function local:func($FinAdquisicion as element() (:: schema-element(ns1:FinAdquisicion) ::)) as element() (:: schema-element(ns2:InformeNoObjecionRequest) ::) {
    <ns2:InformeNoObjecionRequest>
        <ns2:requierePublicacion>{fn:data($FinAdquisicion/ns1:Header/ns3:ParameterType[ns3:parameterName ='SE_REQUIERE_PUBICACION']/ns3:parameterValue)}</ns2:requierePublicacion>
        <ns2:idAdquisicion>{fn:data($FinAdquisicion/ns1:Header/ns3:ParameterType[ns3:parameterName ='ID_ADQUISICION']/ns3:parameterValue)}</ns2:idAdquisicion>
        <ns2:idNoObjecion>{fn:data($FinAdquisicion/ns1:Header/ns3:ParameterType[ns3:parameterName ='ID_NO_OBJECION']/ns3:parameterValue)}</ns2:idNoObjecion>
    </ns2:InformeNoObjecionRequest>
};

local:func($FinAdquisicion)
