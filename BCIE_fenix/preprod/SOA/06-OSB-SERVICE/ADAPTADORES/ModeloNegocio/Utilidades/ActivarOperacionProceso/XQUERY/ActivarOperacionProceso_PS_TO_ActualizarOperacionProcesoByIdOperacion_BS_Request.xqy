xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ActivarOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ActivarOperacionProceso/V1/Schema/ActivarOperacionProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarOperacionProcesoByIdOperacion_DB";
(:: import schema at "../XSD/ActualizarOperacionProcesoByIdOperacion_DB.xsd" ::)

declare namespace act = "http://www.bcie.org/ActivarOperacionProcesoBO";

declare variable $ActivarOperacionProcesoRequest as element() (:: schema-element(ns1:ActivarOperacionProcesoRequest) ::) external;

declare function local:func($ActivarOperacionProcesoRequest as element() (:: schema-element(ns1:ActivarOperacionProcesoRequest) ::)) as element() (:: schema-element(ns2:ActualizarOperacionProcesoByIdOperacion_DBInput) ::) {
    <ns2:ActualizarOperacionProcesoByIdOperacion_DBInput>
        <ns2:idOperacion>{fn:data($ActivarOperacionProcesoRequest/ns1:OperacionProceso/act:idOperacion)}</ns2:idOperacion>
    </ns2:ActualizarOperacionProcesoByIdOperacion_DBInput>
};

local:func($ActivarOperacionProcesoRequest)
