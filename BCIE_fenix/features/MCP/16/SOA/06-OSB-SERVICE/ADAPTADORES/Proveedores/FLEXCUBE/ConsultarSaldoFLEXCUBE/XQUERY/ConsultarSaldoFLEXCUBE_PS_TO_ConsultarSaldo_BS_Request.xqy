xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarSaldo";
(:: import schema at "../XSD/ConsultarSaldo_sp.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ConsultarSaldoFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarSaldoFLEXCUBERequest) ::) external;

declare function local:func($ConsultarSaldoFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarSaldoFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            <ns2:NUMERO_CONTRATO>{fn:data($ConsultarSaldoFLEXCUBERequest/ns1:LineaCredito/lin:Flexcube/lin:id)}</ns2:NUMERO_CONTRATO>
        }
    </ns2:InputParameters>
};

local:func($ConsultarSaldoFLEXCUBERequest)