xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarOperacionEnProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarOperacionEnProceso/V1/Schema/ConsultarOperacionEnProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreOperacionProcesoBPM";
(:: import schema at "../XSD/ConsultarTreOperacionProcesoBPM.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarOperacionEnProcesoBO";

declare variable $ConsultarOperacionEnProcesoRequest as element() (:: schema-element(ns1:ConsultarOperacionEnProcesoRequest) ::) external;

declare function local:func($ConsultarOperacionEnProcesoRequest as element() (:: schema-element(ns1:ConsultarOperacionEnProcesoRequest) ::)) as element() (:: schema-element(ns2:ConsultarTreOperacionProcesoBPMInput) ::) {
    <ns2:ConsultarTreOperacionProcesoBPMSelect_idOperacion_idProcesoInputParameters>
        <ns2:idOperacion>{fn:data($ConsultarOperacionEnProcesoRequest/ns1:OperacionEnProceso/con:idOperacion)}</ns2:idOperacion>
        <ns2:idProceso>{fn:data($ConsultarOperacionEnProcesoRequest/ns1:OperacionEnProceso/con:idProceso)}</ns2:idProceso>
    </ns2:ConsultarTreOperacionProcesoBPMSelect_idOperacion_idProcesoInputParameters>
};

local:func($ConsultarOperacionEnProcesoRequest)
