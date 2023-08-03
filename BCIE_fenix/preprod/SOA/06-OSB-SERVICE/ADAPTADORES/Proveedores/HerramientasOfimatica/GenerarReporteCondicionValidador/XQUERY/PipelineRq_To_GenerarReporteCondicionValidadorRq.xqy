xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://service.org.bcie.www/";
(:: import schema at "../XSD/XMLSchema_558377673.xsd" ::)
declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $GenerarReporteCondicionValidadorRequest as element() (:: schema-element(ns2:GenerarReporteCondicionValidadorRequest) ::) external;

declare function tns:func($GenerarReporteCondicionValidadorRequest as element() (:: schema-element(ns2:GenerarReporteCondicionValidadorRequest) ::)) as element() (:: schema-element(ns1:ReporteValidacionCondicion) ::) {
    <ns1:ReporteValidacionCondicion>
        <idOperacion>{fn:data($GenerarReporteCondicionValidadorRequest/ns2:idOperacion)}</idOperacion>
    </ns1:ReporteValidacionCondicion>
};

tns:func($GenerarReporteCondicionValidadorRequest)
