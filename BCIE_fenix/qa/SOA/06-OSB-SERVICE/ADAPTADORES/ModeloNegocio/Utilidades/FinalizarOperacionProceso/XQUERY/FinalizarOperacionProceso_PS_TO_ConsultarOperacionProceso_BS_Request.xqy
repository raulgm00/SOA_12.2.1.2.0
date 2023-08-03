xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FinalizarOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/FinalizarOperacionProceso/V1/Schema/FinalizarOperacionProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionProceso_DB";
(:: import schema at "../../CrearOperacionProceso/XSD/ConsultarOperacionProceso_DB.xsd" ::)

declare namespace fin = "http://www.bcie.org/FinalizarOperacionProcesoBO";

declare variable $requestFinalizarOperacionProcesoMessage as element() (:: schema-element(ns1:requestFinalizarOperacionProcesoMessage) ::) external;

declare function local:func($requestFinalizarOperacionProcesoMessage as element() (:: schema-element(ns1:requestFinalizarOperacionProcesoMessage) ::)) as element() (:: schema-element(ns2:ConsultarOperacionProceso_DBInput) ::) {
    <ns2:ConsultarOperacionProceso_DBInput>
        <ns2:idOperacion>{fn:data($requestFinalizarOperacionProcesoMessage/ns1:BitacoraInput/fin:idOperacion)}</ns2:idOperacion>
        <ns2:idProceso>{fn:data($requestFinalizarOperacionProcesoMessage/ns1:BitacoraInput/fin:idProceso)}</ns2:idProceso>
    </ns2:ConsultarOperacionProceso_DBInput>
};

local:func($requestFinalizarOperacionProcesoMessage)
