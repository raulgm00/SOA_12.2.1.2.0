xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

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

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $ConsultarSaldoLineaResponse as element() (:: schema-element(ns1:ConsultarSaldoLineaResponse) ::) external;

declare function local:func($ConsultarSaldoLineaResponse as element() (:: schema-element(ns1:ConsultarSaldoLineaResponse) ::)) as element() (:: schema-element(ns2:ConsultarSaldoContratoResponse) ::) {
    <ns2:ConsultarSaldoContratoResponse>
        <ns2:Contrato>
            <con:LineaCredito>
                {$ConsultarSaldoLineaResponse/ns1:LineaCredito/*}
            </con:LineaCredito>
        </ns2:Contrato>
    </ns2:ConsultarSaldoContratoResponse>
};

local:func($ConsultarSaldoLineaResponse)
