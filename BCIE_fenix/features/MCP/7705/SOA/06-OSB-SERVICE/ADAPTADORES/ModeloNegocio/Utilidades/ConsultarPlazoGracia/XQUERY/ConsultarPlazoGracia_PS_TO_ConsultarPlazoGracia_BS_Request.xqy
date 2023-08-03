xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarPlazoGracia";
(:: import schema at "../XSD/ConsultarPlazoGracia.xsd" ::)

declare variable $ConsultarTreTerminoRequest as element() (:: schema-element(ns1:ConsultarTreTerminoRequest) ::) external;

declare function local:func($ConsultarTreTerminoRequest as element() (:: schema-element(ns1:ConsultarTreTerminoRequest) ::)) as element() (:: schema-element(ns2:ConsultarPlazoGraciaInput) ::) {
    <ns2:ConsultarPlazoGraciaInput>
        <ns2:idOperacion>{fn:data($ConsultarTreTerminoRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:descripcionCorta>{fn:data($ConsultarTreTerminoRequest/ns1:descripcionCorta)}</ns2:descripcionCorta>
    </ns2:ConsultarPlazoGraciaInput>
};

local:func($ConsultarTreTerminoRequest)
