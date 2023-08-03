xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarProcesoBPMProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarProcesoBPMProceso/V1/Schema/ConsultarProcesoBPMProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/consultarCodExterno";
(:: import schema at "../XSD/consultarCodExterno_table.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarProcesoBPMProcesoBO";

declare variable $inConsultar as element() (:: schema-element(ns1:requestConsultarProcesoBPM) ::) external;

declare function local:func($inConsultar as element() (:: schema-element(ns1:requestConsultarProcesoBPM) ::)) as element() (:: schema-element(ns2:consultarCodExternoSelect_pIdInputParameters) ::) {
    <ns2:consultarCodExternoSelect_pIdInputParameters>
        <ns2:pId>{fn:data($inConsultar/ns1:Etapa/con:idEtapa)}</ns2:pId>
    </ns2:consultarCodExternoSelect_pIdInputParameters>
};

local:func($inConsultar)
