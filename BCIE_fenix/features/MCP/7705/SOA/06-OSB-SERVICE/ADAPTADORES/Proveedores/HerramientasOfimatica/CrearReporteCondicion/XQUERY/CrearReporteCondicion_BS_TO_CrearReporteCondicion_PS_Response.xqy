xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../../CrearReporteZip/XSD/Reporte.xsd" ::)
declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $ReporteCondicionResponse  as element() (:: schema-element(ns1:Reporte) ::) external;

declare function local:func($ReporteCondicionResponse  as element() (:: schema-element(ns1:Reporte) ::)) as element() (:: schema-element(ns2:CrearReporteCondicionResponse) ::) {
    <ns2:CrearReporteCondicionResponse>
        <ns2:Reporte>{fn:data($ReporteCondicionResponse/ns1:Archivo/ns1:content)}</ns2:Reporte>
    </ns2:CrearReporteCondicionResponse>
};

local:func($ReporteCondicionResponse)