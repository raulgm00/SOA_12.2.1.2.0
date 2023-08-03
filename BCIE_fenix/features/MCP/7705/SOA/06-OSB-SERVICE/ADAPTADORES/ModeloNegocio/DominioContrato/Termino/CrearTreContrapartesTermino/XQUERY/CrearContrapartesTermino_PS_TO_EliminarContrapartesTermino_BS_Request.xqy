xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarContrapartesTermino_DB";
(:: import schema at "../../EliminarTreContrapartesTermino/XSD/EliminarContrapartesTermino_DB.xsd" ::)

declare variable $idTermino as element()external;

declare function local:func($idTermino as element()) {
    <ns2:EliminarContrapartesTermino_DBInput>
        <ns2:idTermino>{fn:data($idTermino)}</ns2:idTermino>
    </ns2:EliminarContrapartesTermino_DBInput>
};

local:func($idTermino)