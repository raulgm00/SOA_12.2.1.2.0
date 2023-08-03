xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $consultarCumplimientoCondicionRequest as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionRequest) ::) external;

declare function local:func($consultarCumplimientoCondicionRequest as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionRequest) ::)) as element() (:: schema-element(ns1:CrearReporteCondicionRequest) ::) {
    <ns1:CrearReporteCondicionRequest>
        <ns1:idOperacion>{fn:data($consultarCumplimientoCondicionRequest/ns1:idOperacion)}</ns1:idOperacion>
        <ns1:idTipoControl>2</ns1:idTipoControl>
    </ns1:CrearReporteCondicionRequest>
};

local:func($consultarCumplimientoCondicionRequest)
