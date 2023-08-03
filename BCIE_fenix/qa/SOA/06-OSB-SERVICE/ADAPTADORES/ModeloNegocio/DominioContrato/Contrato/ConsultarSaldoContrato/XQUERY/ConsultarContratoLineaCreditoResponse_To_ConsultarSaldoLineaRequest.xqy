xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ConsultarLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoResponse) ::) external;
declare variable $counter as xs:integer external;

declare function local:func($ConsultarLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoResponse) ::), 
                            $counter as xs:integer) 
                            as element() (:: schema-element(ns1:ConsultarSaldoLineaRequest) ::) {
    <ns1:ConsultarSaldoLineaRequest>
        <ns1:idLineaCredito>{fn:data($ConsultarLineaCreditoResponse/ns1:clienteContrato/con:LineaCredito[lin:Flexcube/lin:id/text()!=''][$counter]/lin:idLineaCredito)}</ns1:idLineaCredito>
    </ns1:ConsultarSaldoLineaRequest>
};

local:func($ConsultarLineaCreditoResponse, $counter)
