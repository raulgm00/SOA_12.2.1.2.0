xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $NotificarValidacionCondicionRequest as element() (:: schema-element(ns1:NotificarValidacionCondicionRequest) ::) external;

declare function local:func($NotificarValidacionCondicionRequest as element() (:: schema-element(ns1:NotificarValidacionCondicionRequest) ::)) as element() (:: schema-element(ns1:CrearReporteCondicionRequest) ::) {
    <ns1:CrearReporteCondicionRequest>
        <ns1:idOperacion>{fn:data($NotificarValidacionCondicionRequest/ns1:idOperacion)}</ns1:idOperacion>
        <ns1:idAgrupador>{fn:data($NotificarValidacionCondicionRequest/ns1:idAgrupador)}</ns1:idAgrupador>
    </ns1:CrearReporteCondicionRequest>
};

local:func($NotificarValidacionCondicionRequest)
