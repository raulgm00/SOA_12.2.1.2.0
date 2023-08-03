xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ResponsableSolicitudMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarResponsableSolicitud/V1/Schema/ResponsableSolicitudMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarResponsableSolicitud_BD";
(:: import schema at "../XSD/ConsultarResponsableSolicitud_BD_table.xsd" ::)

declare variable $ResponsableSolicitudRequest as element() (:: schema-element(ns1:ResponsableSolicitudRequest) ::) external;

declare function local:func($ResponsableSolicitudRequest as element() (:: schema-element(ns1:ResponsableSolicitudRequest) ::)) as element() (:: schema-element(ns2:ConsultarResponsableSolicitud_BDSelect_idOperacionInputParameters) ::) {
    <ns2:ConsultarResponsableSolicitud_BDSelect_idOperacionInputParameters>
        <ns2:idOperacion>{fn:data($ResponsableSolicitudRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarResponsableSolicitud_BDSelect_idOperacionInputParameters>
};

local:func($ResponsableSolicitudRequest)