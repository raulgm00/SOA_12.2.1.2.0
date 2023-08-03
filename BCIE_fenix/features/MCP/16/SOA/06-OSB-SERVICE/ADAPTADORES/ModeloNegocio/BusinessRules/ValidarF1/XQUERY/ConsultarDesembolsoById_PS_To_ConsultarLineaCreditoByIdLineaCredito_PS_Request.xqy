xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:func($ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns1:ConsultarDesembolsosByIdResponse) ::)) as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoRequest) ::) {
    <ns2:ConsultarLineaCreditoByIdLineaCreditoRequest>
        {
            if ($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:idLinea)
            then <ns2:idLineaCredito>{fn:data($ConsultarDesembolsosByIdResponse/ns1:ContratoDesembolso/des:idLinea)}</ns2:idLineaCredito>
            else ()
        }
    </ns2:ConsultarLineaCreditoByIdLineaCreditoRequest>
};

local:func($ConsultarDesembolsosByIdResponse)
