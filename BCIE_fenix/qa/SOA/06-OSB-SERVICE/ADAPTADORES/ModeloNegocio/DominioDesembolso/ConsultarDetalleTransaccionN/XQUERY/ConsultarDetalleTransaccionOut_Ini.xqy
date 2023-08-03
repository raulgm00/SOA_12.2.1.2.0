xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $string as xs:string external;

declare function local:func($string as xs:string) as element() (:: schema-element(ns1:ConsultarDetalleTransaccionNResponse) ::) {
    <ns1:ConsultarDetalleTransaccionNResponse>
    </ns1:ConsultarDetalleTransaccionNResponse>
};

local:func($string)
