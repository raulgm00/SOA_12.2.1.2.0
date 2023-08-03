xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare variable $idTermino as element() (:: schema-element(ns1:EliminarTerminoRequest) ::) external;

declare function local:func($idTermino as element() (:: schema-element(ns1:EliminarTerminoRequest) ::)) as element() (:: schema-element(ns1:EliminarTreTerminoRequest) ::) {
    <ns1:EliminarTreTerminoRequest>
        {
            if ($idTermino/ns1:idTermino)
            then <ns1:Termino>{fn:data($idTermino/ns1:idTermino)}</ns1:Termino>
            else ()
        }
    </ns1:EliminarTreTerminoRequest>
};

local:func($idTermino)
