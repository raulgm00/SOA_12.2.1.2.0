xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarProcesoAprobacionFinalizado";
(:: import schema at "../XSD/ValidarProcesoAprobacionFinalizado.xsd" ::)

declare namespace apr = "http://www.bcie.org/AprobacionBO";

declare variable $ValidarProcesoAprobacionFinalizadoOutputCollection as element() (:: schema-element(ns1:ValidarProcesoAprobacionFinalizadoOutputCollection) ::) external;

declare function local:func($ValidarProcesoAprobacionFinalizadoOutputCollection as element() (:: schema-element(ns1:ValidarProcesoAprobacionFinalizadoOutputCollection) ::)) as element() (:: schema-element(ns2:ValidarProcesoAprobacionFinalizadoResponse) ::) {
    <ns2:ValidarProcesoAprobacionFinalizadoResponse>
        <ns2:Aprobacion>
            <apr:idAprobacion>{fn:data($ValidarProcesoAprobacionFinalizadoOutputCollection/ns1:ValidarProcesoAprobacionFinalizadoOutput/ns1:ID)}</apr:idAprobacion>
            <apr:idReunionAprobacion>{fn:data($ValidarProcesoAprobacionFinalizadoOutputCollection/ns1:ValidarProcesoAprobacionFinalizadoOutput/ns1:ID_DECISION_REUNION)}</apr:idReunionAprobacion></ns2:Aprobacion>
    </ns2:ValidarProcesoAprobacionFinalizadoResponse>
};

local:func($ValidarProcesoAprobacionFinalizadoOutputCollection)
