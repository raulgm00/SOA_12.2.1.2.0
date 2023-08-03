xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarTreTermino_DB";
(:: import schema at "../XSD/EliminarTreTermino_DB.xsd" ::)

declare variable $idTermino as element() (:: schema-element(ns1:EliminarTreTerminoRequest) ::) external;

declare function local:func($idTermino as element() (:: schema-element(ns1:EliminarTreTerminoRequest) ::)) as element() (:: schema-element(ns2:EliminarTreTermino_DBInput) ::) {
    <ns2:EliminarTreTermino_DBInput>
        <ns2:ID_TERMINO>{fn:data($idTermino/ns1:Termino)}</ns2:ID_TERMINO>
    </ns2:EliminarTreTermino_DBInput>
};

local:func($idTermino)
