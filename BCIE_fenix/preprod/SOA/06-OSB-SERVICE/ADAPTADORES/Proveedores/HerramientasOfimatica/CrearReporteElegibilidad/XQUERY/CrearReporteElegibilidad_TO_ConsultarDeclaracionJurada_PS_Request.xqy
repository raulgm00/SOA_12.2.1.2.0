xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns1="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)

declare variable $idOperacion as element() (:: schema-element(ns1:CrearReporteElegibilidadRequest) ::) external;

declare function local:func($idOperacion as element() (:: schema-element(ns1:CrearReporteElegibilidadRequest) ::)) as element() (:: schema-element(ns2:ConsultarDeclaracionJuradaRequest) ::) {
    <ns2:ConsultarDeclaracionJuradaRequest>
        <ns2:idOperacion>{fn:data($idOperacion/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarDeclaracionJuradaRequest>
};

local:func($idOperacion)
