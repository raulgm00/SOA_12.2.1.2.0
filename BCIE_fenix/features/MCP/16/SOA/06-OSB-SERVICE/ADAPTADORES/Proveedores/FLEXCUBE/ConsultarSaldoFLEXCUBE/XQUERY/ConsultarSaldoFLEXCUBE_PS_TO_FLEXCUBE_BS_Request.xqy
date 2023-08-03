xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ConsultarSaldoFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarSaldoFLEXCUBERequest) ::) external;


declare function local:func($ConsultarSaldoFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarSaldoFLEXCUBERequest) ::))as element() (:: element(flex:consultarSaldo) ::) {
    <flex:consultarSaldo>
         <numeroContrato>{fn:data($ConsultarSaldoFLEXCUBERequest/ns1:LineaCredito/lin:Flexcube/lin:id)}</numeroContrato>
    </flex:consultarSaldo>
};

local:func($ConsultarSaldoFLEXCUBERequest)