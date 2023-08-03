xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ValidarRegistroFLEXCUBERequest as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBERequest) ::) external;

declare function local:func($ValidarRegistroFLEXCUBERequest as element() (:: schema-element(ns1:ValidarRegistroFLEXCUBERequest) ::)) as element() (:: element(flex:consultarContrato) ::) {
 <flex:consultarContrato>
                <numeroContrato>{fn:data($ValidarRegistroFLEXCUBERequest/ns1:LineaCredito/lin:idContrato)}</numeroContrato>
                <referenciaUsuario/>     
    </flex:consultarContrato>
};

local:func($ValidarRegistroFLEXCUBERequest)
