xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare variable $ValidarHoraCierreRequest as element() (:: schema-element(ns1:ValidarHoraCierreRequest) ::) external;

declare function local:func($ValidarHoraCierreRequest as element() (:: schema-element(ns1:ValidarHoraCierreRequest) ::)) as element() (:: schema-element(ns1:ConsultarDesembolsosByIdRequest) ::) {
    <ns1:ConsultarDesembolsosByIdRequest>
        <ns1:idContratoDesembolso>{fn:data($ValidarHoraCierreRequest/ns1:Regla/reg:DetalleRegla/atr:id)}</ns1:idContratoDesembolso>
    </ns1:ConsultarDesembolsosByIdRequest>
};

local:func($ValidarHoraCierreRequest)
