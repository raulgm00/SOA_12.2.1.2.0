xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idTermino as xs:long external;

declare function local:func($idTermino as xs:long) as element() (:: schema-element(ns1:CrearTerminoResponse) ::) {
    <ns1:CrearTerminoResponse>
        <ns1:codigoContrato>{fn:data($idTermino)}</ns1:codigoContrato>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:data($idTermino)}</res:message>
        </ns1:Resultado>
    </ns1:CrearTerminoResponse>
};

local:func($idTermino)
