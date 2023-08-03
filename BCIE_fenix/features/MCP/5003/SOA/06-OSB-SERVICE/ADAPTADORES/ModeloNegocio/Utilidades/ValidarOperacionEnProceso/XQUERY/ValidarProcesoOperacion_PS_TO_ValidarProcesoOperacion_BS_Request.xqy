xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ValidarOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarOperacionProceso/V1/Schema/ValidarOperacionProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarOperacionProceso_DB";
(:: import schema at "../XSD/ValidarOperacionProceso_DB.xsd" ::)

declare namespace val = "http://www.bcie.org/ValidarOperacionProcesoBO";

declare variable $ValdiarRequest as element() (:: schema-element(ns1:requestValidarOperacionProceso) ::) external;

declare function local:func($ValdiarRequest as element() (:: schema-element(ns1:requestValidarOperacionProceso) ::)) as element() (:: schema-element(ns2:ValidarOperacionProceso_DBInput) ::) {
    <ns2:ValidarOperacionProceso_DBInput>
        <ns2:idOperacion>{fn:data($ValdiarRequest/ns1:ValidarProcesoOperacionInput/val:idOperacion)}</ns2:idOperacion>
        <ns2:idProceso>{fn:data($ValdiarRequest/ns1:ValidarProcesoOperacionInput/val:idProceso)}</ns2:idProceso>
    </ns2:ValidarOperacionProceso_DBInput>
};

local:func($ValdiarRequest)
