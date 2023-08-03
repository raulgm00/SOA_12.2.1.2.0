xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;

declare variable $counter as xs:int external;

declare function local:func($ConsultarLineaCreditoByIdLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoResponse) ::)) as element() (:: schema-element(ns2:ConsultarSaldoRequest) ::) {
    <ns2:ConsultarSaldoRequest>
        <ns2:idDesembolso>{fn:data($ConsultarLineaCreditoByIdLineaCreditoResponse/ns1:Operacion/ope:contrato/con:LineaCredito[1]/lin:ContratoDesembolso[$counter]/des:idDesembolso)}</ns2:idDesembolso>
    </ns2:ConsultarSaldoRequest>
};

local:func($ConsultarLineaCreditoByIdLineaCreditoResponse)
