xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ValidarOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarOperacionProceso/V1/Schema/ValidarOperacionProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ValidarOperacionProceso_DB";
(:: import schema at "../XSD/ValidarOperacionProceso_DB.xsd" ::)

declare variable $ValidarResponse as element() (:: schema-element(ns1:ValidarOperacionProceso_DBOutputCollection) ::) external;

declare function local:func($ValidarResponse as element() (:: schema-element(ns1:ValidarOperacionProceso_DBOutputCollection) ::)) as element() (:: schema-element(ns2:responseValidarOperacionProceso) ::) {
    <ns2:responseValidarOperacionProceso>
        <ns2:EnProceso>{fn:data($ValidarResponse/ns1:ValidarOperacionProceso_DBOutput/ns1:enProceso)}</ns2:EnProceso>
    </ns2:responseValidarOperacionProceso>
};

local:func($ValidarResponse)
