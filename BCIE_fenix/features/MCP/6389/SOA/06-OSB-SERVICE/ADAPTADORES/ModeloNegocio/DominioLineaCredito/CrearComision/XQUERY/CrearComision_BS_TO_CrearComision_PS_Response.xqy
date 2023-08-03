xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idComision as xs:long external;

declare function local:func($idComision as xs:long) as element() (:: schema-element(ns1:CrearComisionResponse) ::) {
    <ns1:CrearComisionResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:data($idComision)}</res:message>
        </ns1:Resultado>
    </ns1:CrearComisionResponse>
};

local:func($idComision)
