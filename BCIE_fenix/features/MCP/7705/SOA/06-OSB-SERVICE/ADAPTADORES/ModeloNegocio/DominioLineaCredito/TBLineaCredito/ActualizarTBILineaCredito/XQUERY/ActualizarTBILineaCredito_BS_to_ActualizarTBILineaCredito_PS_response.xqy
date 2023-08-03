xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarTBILineaCredito";
(:: import schema at "../../../TBLineaCredito/ActualizarTBILineaCredito/XSD/TBILineaCredito.xsd" ::)

declare variable $requestBS as element()* (:: schema-element(ns1:TbiLineaCreditoCollection)* ::) external;

declare function local:func($requestBS as element()* (:: schema-element(ns1:TbiLineaCreditoCollection)* ::)) as element() (:: schema-element(ns2:TBILineaCreditoActualizarResponse) ::) {
    <ns2:TBILineaCreditoActualizarResponse/>
};

local:func($requestBS)
