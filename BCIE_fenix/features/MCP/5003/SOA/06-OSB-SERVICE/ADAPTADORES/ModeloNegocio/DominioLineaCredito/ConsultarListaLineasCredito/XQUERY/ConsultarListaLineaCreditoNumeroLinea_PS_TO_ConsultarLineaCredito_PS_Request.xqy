xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare variable $ConsultarListaLineasCreditoRequest as element() (:: schema-element(ns1:ConsultarListaLineasCreditoRequest) ::) external;
declare variable $index as xs:int external;

declare function local:func($ConsultarListaLineasCreditoRequest as element() (:: schema-element(ns1:ConsultarListaLineasCreditoRequest) ::),
                            $index) as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoRequest) ::) {
    <ns1:ConsultarLineaCreditoByIdLineaCreditoRequest>
        <ns1:numeroLinea>{fn:data($ConsultarListaLineasCreditoRequest/ns1:NumeroLinea[$index])}</ns1:numeroLinea>
    </ns1:ConsultarLineaCreditoByIdLineaCreditoRequest>
};

local:func($ConsultarListaLineasCreditoRequest,$index)
