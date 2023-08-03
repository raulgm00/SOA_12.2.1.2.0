xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ValidarEnProcesoExcepcionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarEnProcesoExcepcion/V1/Schema/ValidarEnProcesoExcepcionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd" ::)

declare namespace val = "http://www.bcie.org/ValidarEnProcesoExcepcionBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $InicioDesembolsoExcepcion as element() (:: schema-element(ns1:InicioDesembolsoExcepcion) ::) external;

declare function local:func($InicioDesembolsoExcepcion as element() (:: schema-element(ns1:InicioDesembolsoExcepcion) ::)) as element() (:: schema-element(ns2:ValidarEnProcesoExcepcionRequest) ::) {
    <ns2:ValidarEnProcesoExcepcionRequest>
        <ns2:ProcesoExcepcion>
            <val:idSolicitud>{fn:data($InicioDesembolsoExcepcion/ns1:Header/ns3:ParameterType[ns3:parameterName='ID_SOLICITUD']/ns3:parameterValue)}</val:idSolicitud>
        </ns2:ProcesoExcepcion>
    </ns2:ValidarEnProcesoExcepcionRequest>
};

local:func($InicioDesembolsoExcepcion)
