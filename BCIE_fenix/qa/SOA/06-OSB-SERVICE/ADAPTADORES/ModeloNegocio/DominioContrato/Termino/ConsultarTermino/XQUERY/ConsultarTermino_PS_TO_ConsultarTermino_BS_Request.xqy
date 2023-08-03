xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace ns11="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTermino";
(:: import schema at "../XSD/ConsultarTermino.xsd" ::)

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $idTermino as element() (:: schema-element(ns1:ConsultarTerminoRequest) ::) external;

declare function local:func($idTermino as element() (:: schema-element(ns1:ConsultarTerminoRequest) ::)) 
as element() (:: schema-element(ns11:ConsultarTerminoInput) ::) {
<ns11:ConsultarTerminoInput>
    <ns11:idTermino>{fn:data($idTermino/ns1:idTermino)}</ns11:idTermino>
    <ns11:idOperacion>{fn:data($idTermino/ns1:idOperacion)}</ns11:idOperacion>
    <ns11:idTcaTermino>{fn:data($idTermino/ns1:tipoTermino/ter:idCatTermino)}</ns11:idTcaTermino>
</ns11:ConsultarTerminoInput>
};

local:func($idTermino)