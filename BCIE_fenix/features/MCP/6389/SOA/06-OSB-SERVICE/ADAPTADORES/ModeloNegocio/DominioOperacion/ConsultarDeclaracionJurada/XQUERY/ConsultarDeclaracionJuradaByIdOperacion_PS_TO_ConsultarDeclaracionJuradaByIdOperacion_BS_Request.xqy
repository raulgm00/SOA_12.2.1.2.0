xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDeclaracionByIdOperacion";
(:: import schema at "../XSD/ConsultarDeclaracionJuradaByIdOperacion.xsd" ::)

declare variable $idOperacionRequest as element() (:: schema-element(ns1:ConsultarDeclaracionJuradaRequest) ::) external;

declare function local:func($idOperacionRequest as element() (:: schema-element(ns1:ConsultarDeclaracionJuradaRequest) ::)) as element() (:: schema-element(ns2:ConsultarDeclaracionByIdOperacionInput) ::) {
    <ns2:ConsultarDeclaracionByIdOperacionInput>
        <ns2:idOperacion>{fn:data($idOperacionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarDeclaracionByIdOperacionInput>
};

local:func($idOperacionRequest)
