xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Aprobacion/V1/Schema/AprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAprobacion_LineasCredito";
(:: import schema at "../XSD/ConsultarAprobacion_LineasCredito.xsd" ::)

declare variable $ConsultarAprobacionRequest as element() (:: schema-element(ns1:ConsultarAprobacionRequest) ::) external;

declare function local:func($ConsultarAprobacionRequest as element() (:: schema-element(ns1:ConsultarAprobacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarAprobacion_LineasCreditoInput) ::) {
    <ns2:ConsultarAprobacion_LineasCreditoInput>
        <ns2:idSolicitudAprobacion>{fn:data($ConsultarAprobacionRequest/ns1:idSolicitudAprobacion)}</ns2:idSolicitudAprobacion>
        <ns2:idOperacion>{fn:data($ConsultarAprobacionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarAprobacion_LineasCreditoInput>
};

local:func($ConsultarAprobacionRequest)
