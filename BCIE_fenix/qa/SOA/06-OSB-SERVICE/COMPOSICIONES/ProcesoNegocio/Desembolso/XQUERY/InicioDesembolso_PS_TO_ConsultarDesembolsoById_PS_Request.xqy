xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioDesembolso as element() (:: schema-element(ns1:InicioDesembolso) ::) external;

declare function local:func($InicioDesembolso as element() (:: schema-element(ns1:InicioDesembolso) ::)) as element() (:: schema-element(ns2:ConsultarDesembolsosByIdRequest) ::) {
    <ns2:ConsultarDesembolsosByIdRequest>
        <ns2:idContratoDesembolso>{fn:data($InicioDesembolso/ns1:Header/ns3:ParameterType[ns3:parameterName = 'ID_DESEMBOLSO']/ns3:parameterValue)}</ns2:idContratoDesembolso>
    </ns2:ConsultarDesembolsosByIdRequest>
};

local:func($InicioDesembolso)
