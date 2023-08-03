xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearOperacionProceso/V1/Schema/CrearOperacionProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionProceso_DB";
(:: import schema at "../XSD/ConsultarOperacionProceso_DB.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearOperacionProcesoBO";

declare variable $requestCrearOperacionProcesoMessage as element() (:: schema-element(ns1:requestCrearOperacionProcesoMessage) ::) external;

declare function local:func($requestCrearOperacionProcesoMessage as element() (:: schema-element(ns1:requestCrearOperacionProcesoMessage) ::)) as element() (:: schema-element(ns2:ConsultarOperacionProceso_DBInput) ::) {
    <ns2:ConsultarOperacionProceso_DBInput>
        <ns2:idOperacion>{fn:data($requestCrearOperacionProcesoMessage/ns1:OperacionProcesoInput/cre:idOperacion)}</ns2:idOperacion>
        <ns2:idProceso>{fn:data($requestCrearOperacionProcesoMessage/ns1:OperacionProcesoInput/cre:idProceso)}</ns2:idProceso>
    </ns2:ConsultarOperacionProceso_DBInput>
};

local:func($requestCrearOperacionProcesoMessage)
