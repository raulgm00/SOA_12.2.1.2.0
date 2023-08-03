xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarProcesoBPMProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarProcesoBPMProceso/V1/Schema/ConsultarProcesoBPMProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/consultarCodExterno";
(:: import schema at "../XSD/consultarCodExterno_table.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarProcesoBPMProcesoBO";

declare variable $outconsultar as element() (:: schema-element(ns1:TcaProcesoBpmCollection) ::) external;

declare function local:func($outconsultar as element() (:: schema-element(ns1:TcaProcesoBpmCollection) ::)) as element() (:: schema-element(ns2:responseConsultarProcesoBPM) ::) {
    <ns2:responseConsultarProcesoBPM>
        <ns2:Etapa>
            <con:idExterno>{fn:data($outconsultar/ns1:TcaProcesoBpm/ns1:codExterno)}</con:idExterno></ns2:Etapa>
    </ns2:responseConsultarProcesoBPM>
};

local:func($outconsultar)
