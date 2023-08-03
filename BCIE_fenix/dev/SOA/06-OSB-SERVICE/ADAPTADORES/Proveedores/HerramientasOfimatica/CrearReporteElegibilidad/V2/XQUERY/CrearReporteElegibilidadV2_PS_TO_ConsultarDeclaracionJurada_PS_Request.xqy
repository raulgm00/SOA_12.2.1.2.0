xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace cues="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)

declare namespace cues1="http://xmlns.bcie.com/CuestionarioBO";

declare variable $reporteElegibilidad as element() (:: schema-element(cues:CrearReporteElegibilidadV2Request) ::) external;

declare function local:func($reporteElegibilidad as element() (:: schema-element(cues:CrearReporteElegibilidadV2Request) ::)) as element() (:: schema-element(ns1:ConsultarDeclaracionJuradaRequest) ::) {
    <ns1:ConsultarDeclaracionJuradaRequest>
        <ns1:idOperacion>{fn:data($reporteElegibilidad/cues:idOperacion)}</ns1:idOperacion>
    </ns1:ConsultarDeclaracionJuradaRequest>
};

local:func($reporteElegibilidad)
