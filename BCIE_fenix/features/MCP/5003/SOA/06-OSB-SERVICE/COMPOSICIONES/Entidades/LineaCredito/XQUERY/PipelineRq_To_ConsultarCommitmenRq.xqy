xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $ConsultarLineaCreditoBPELResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoBPELResponse) ::) external;

declare function tns:func($ConsultarLineaCreditoBPELResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoBPELResponse) ::)) as element() (:: schema-element(ns1:ConsultarCommitmentRequest) ::) {
    <ns1:ConsultarCommitmentRequest>
        <ns1:LineaCredito>
            <lin:NumeroLineaCredito>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
        </ns1:LineaCredito>
    </ns1:ConsultarCommitmentRequest>
};

tns:func($ConsultarLineaCreditoBPELResponse)
