xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/ReporteCondicion.xsd" ::)
declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $idOperacion as element() (:: schema-element(ns1:CrearReporteCondicionRequest) ::) external;

declare function local:func($idOperacion as element() (:: schema-element(ns1:CrearReporteCondicionRequest) ::)) as element() (:: schema-element(ns2:ReporteCondicion) ::) {
    <ns2:ReporteCondicion>
        <idOperacion>{fn:data($idOperacion/ns1:idOperacion)}</idOperacion>
        <idAgrupador>{fn:data($idOperacion/ns1:idAgrupador)}</idAgrupador>
    </ns2:ReporteCondicion>
};

local:func($idOperacion)