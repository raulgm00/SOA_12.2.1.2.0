xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDeclaracionJuradaByIdDeclaracion";
(:: import schema at "../XSD/ConsultarDeclaracionJuradaByIdDeclaracion.xsd" ::)

declare variable $consultarDeclaracionJuradaByIdDeclaracion as element() (:: schema-element(ns1:ConsultarDeclaracionJuradaByIdDeclaracionRequest) ::) external;

declare function local:func($consultarDeclaracionJuradaByIdDeclaracion as element() (:: schema-element(ns1:ConsultarDeclaracionJuradaByIdDeclaracionRequest) ::)) as element() (:: schema-element(ns2:ConsultarDeclaracionJuradaByIdDeclaracionInput) ::) {
    <ns2:ConsultarDeclaracionJuradaByIdDeclaracionInput>
        <ns2:idDeclaracion>{fn:data($consultarDeclaracionJuradaByIdDeclaracion/ns1:idDeclaracion)}</ns2:idDeclaracion>
    </ns2:ConsultarDeclaracionJuradaByIdDeclaracionInput>
};

local:func($consultarDeclaracionJuradaByIdDeclaracion)
