xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTCCConfiguracion";
(:: import schema at "../XSD/ConsultarTCCConfiguracion.xsd" ::)

declare variable $ConsultarTCCConfiguracionRequest as element() (:: schema-element(ns2:ConsultarTCCConfiguracionRequest) ::) external;

declare function local:func($ConsultarTCCConfiguracionRequest as element() (:: schema-element(ns2:ConsultarTCCConfiguracionRequest) ::)) as element() (:: schema-element(ns1:ConsultarTCCConfiguracionInput) ::) {
    <ns1:ConsultarTCCConfiguracionInput>
        <ns1:ID_TCA_PROCESO_BPM>{fn:data($ConsultarTCCConfiguracionRequest/ns2:idTcaProcesoBPM)}</ns1:ID_TCA_PROCESO_BPM>
        <ns1:ID_OPERACION>{fn:data($ConsultarTCCConfiguracionRequest/ns2:idOperacion)}</ns1:ID_OPERACION>
    </ns1:ConsultarTCCConfiguracionInput>
};

local:func($ConsultarTCCConfiguracionRequest)
