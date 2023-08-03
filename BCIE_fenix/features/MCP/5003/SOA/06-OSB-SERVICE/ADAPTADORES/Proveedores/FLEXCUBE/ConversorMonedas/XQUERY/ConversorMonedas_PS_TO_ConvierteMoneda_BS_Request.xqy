xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConvierteMoneda";
(:: import schema at "../XSD/ConvierteMoneda_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConversorMonedasFLEXCUBErequest as element() (:: schema-element(ns1:ConversorMonedasRequest) ::) external;

declare function local:func($ConversorMonedasFLEXCUBErequest as element() (:: schema-element(ns1:ConversorMonedasRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            <ns2:MONEDA_DE>{fn:data($ConversorMonedasFLEXCUBErequest/ns1:ConvierteMonedas/con:monedaDe/cat:codigoExterno)}</ns2:MONEDA_DE>
        }
        {
            <ns2:MONEDA_A>{fn:data($ConversorMonedasFLEXCUBErequest/ns1:ConvierteMonedas/con:monedaA/cat:codigoExterno)}</ns2:MONEDA_A>
        }
        <ns2:MONTO>{fn:data($ConversorMonedasFLEXCUBErequest/ns1:ConvierteMonedas/con:monto)}</ns2:MONTO>
    </ns2:InputParameters>
};

local:func($ConversorMonedasFLEXCUBErequest)