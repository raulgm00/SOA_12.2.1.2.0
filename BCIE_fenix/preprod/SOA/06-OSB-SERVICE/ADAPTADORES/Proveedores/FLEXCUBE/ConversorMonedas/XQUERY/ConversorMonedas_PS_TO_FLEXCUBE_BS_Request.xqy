xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace ns2="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
declare namespace ns1="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConversorMonedasRequest as element() (:: schema-element(ns1:ConversorMonedasRequest) ::) external;


declare function local:func($ConversorMonedasFLEXCUBErequest as element() (:: schema-element(ns1:ConversorMonedasRequest) ::)) {
    <flex:convierteMoneda>
         <monedaDe>{fn:data($ConversorMonedasFLEXCUBErequest/ns1:ConvierteMonedas/con:monedaDe/cat:codigoExterno)}</monedaDe>
         <monedaA>{fn:data($ConversorMonedasFLEXCUBErequest/ns1:ConvierteMonedas/con:monedaA/cat:codigoExterno)}</monedaA>
         <monto>{fn:data($ConversorMonedasFLEXCUBErequest/ns1:ConvierteMonedas/con:monto)}</monto>
      </flex:convierteMoneda>

};

local:func($ConversorMonedasRequest)
