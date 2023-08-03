xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare variable $ConsultarDetalleTransaccionNRequest as element() (:: schema-element(ns1:ConsultarDetalleTransaccionNRequest) ::) external;
declare variable $counter as xs:int external;

declare function local:func($ConsultarDetalleTransaccionNRequest as element() (:: schema-element(ns1:ConsultarDetalleTransaccionNRequest) ::), $counter as xs:int) as element() (:: schema-element(ns1:ConsultarDetalleTransaccionRequest) ::) {
    <ns1:ConsultarDetalleTransaccionRequest>
        <ns1:idDesembolso>{fn:data($ConsultarDetalleTransaccionNRequest/ns1:idDesembolso[$counter])}</ns1:idDesembolso>
    </ns1:ConsultarDetalleTransaccionRequest>
};

local:func($ConsultarDetalleTransaccionNRequest,$counter)
