xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarContrapartesTermino_DB";
(:: import schema at "../XSD/EliminarContrapartesTermino_DB.xsd" ::)

declare variable $idTermino as element() (:: schema-element(ns1:EliminarTreContrapartesTerminoRequest) ::) external;

declare function local:func($idTermino as element() (:: schema-element(ns1:EliminarTreContrapartesTerminoRequest) ::)) as element() (:: schema-element(ns2:EliminarContrapartesTermino_DBInput) ::) {
    <ns2:EliminarContrapartesTermino_DBInput>
        <ns2:idTermino>{fn:data($idTermino/ns1:idTermino)}</ns2:idTermino>
    </ns2:EliminarContrapartesTermino_DBInput>
};

local:func($idTermino)
