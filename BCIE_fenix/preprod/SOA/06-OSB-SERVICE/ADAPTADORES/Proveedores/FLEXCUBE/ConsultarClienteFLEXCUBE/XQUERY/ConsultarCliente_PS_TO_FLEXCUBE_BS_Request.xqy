xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace ns2="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarClienteFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBERequest) ::) external;

declare function local:func($ConsultarClienteFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBERequest) ::)) as element() (:: element(flex:consultarCliente) ::) {
   <flex:consultarCliente>
         <codigoCliente>{fn:data($ConsultarClienteFLEXCUBERequest/cli:idFacturador)}</codigoCliente>
   </flex:consultarCliente>
};

local:func($ConsultarClienteFLEXCUBERequest)
