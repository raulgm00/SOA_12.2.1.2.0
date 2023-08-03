xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $generarReporteCondicionValidadorRequest as element() (:: schema-element(ns1:GenerarReporteCondicionValidadorRequest) ::) external;

declare function local:func($generarReporteCondicionValidadorRequest as element() (:: schema-element(ns1:GenerarReporteCondicionValidadorRequest) ::)) as element() (:: schema-element(ns1:CrearReporteCondicionRequest) ::) {
    <ns1:CrearReporteCondicionRequest>
        <ns1:idOperacion>{fn:data($generarReporteCondicionValidadorRequest/ns1:idOperacion)}</ns1:idOperacion>
    </ns1:CrearReporteCondicionRequest>
};

local:func($generarReporteCondicionValidadorRequest)
