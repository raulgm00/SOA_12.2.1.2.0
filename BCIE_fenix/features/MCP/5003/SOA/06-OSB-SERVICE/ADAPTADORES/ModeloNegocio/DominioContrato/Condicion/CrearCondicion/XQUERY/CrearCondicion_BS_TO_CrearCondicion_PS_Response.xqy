xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idCondicion as xs:long external;

declare function local:func($idCondicion as xs:long) as element() (:: schema-element(ns1:CrearCondicionResponse) ::) {
    <ns1:CrearCondicionResponse>
        <ns1:codigoContrato>{fn:data($idCondicion)}</ns1:codigoContrato>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:concat('La inserción se realizó correctamente, Id. Condición: ', fn:data($idCondicion))}</res:message>
        </ns1:Resultado>
    </ns1:CrearCondicionResponse>
};

local:func($idCondicion)
