xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)



declare variable $ConsultarFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarFLEXCUBERequest) ::) external;

declare function local:func($ConsultarFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarFLEXCUBERequest) ::))as element() (:: element(flex:consultarContrato) ::) {
    <flex:consultarContrato>
                <numeroContrato>{fn:data($ConsultarFLEXCUBERequest/ns1:codigoContrato)}</numeroContrato>
                <referenciaUsuario/>
                
    </flex:consultarContrato>

};

local:func($ConsultarFLEXCUBERequest)