xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarHoraCierreMoneda";
(:: import schema at "../XSD/ConsultarHoraCierreMoneda.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:func($ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::)) as element() (:: schema-element(ns2:ConsultarHoraCierreMonedaInput) ::) {
    <ns2:ConsultarHoraCierreMonedaInput>
        <ns2:idMoneda>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:monto[1]/com:moneda/cat:Id)}</ns2:idMoneda>
    </ns2:ConsultarHoraCierreMonedaInput>
};

local:func($ConsultarDesembolsosByIdResponse)
