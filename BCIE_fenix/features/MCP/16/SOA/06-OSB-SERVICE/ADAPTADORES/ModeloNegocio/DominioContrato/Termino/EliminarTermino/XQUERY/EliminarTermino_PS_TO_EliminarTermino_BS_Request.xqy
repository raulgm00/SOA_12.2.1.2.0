xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarTermino";
(:: import schema at "../XSD/EliminarTermino.xsd" ::)

declare variable $idTermino as element() (:: schema-element(ns1:EliminarTerminoRequest) ::) external;

declare function local:func($idTermino as element() (:: schema-element(ns1:EliminarTerminoRequest) ::)) as element() (:: schema-element(ns2:EliminarTerminoInput) ::) {
    <ns2:EliminarTerminoInput>
        <ns2:ID>{fn:data($idTermino/ns1:idTermino)}</ns2:ID>
    </ns2:EliminarTerminoInput>
};

local:func($idTermino)
