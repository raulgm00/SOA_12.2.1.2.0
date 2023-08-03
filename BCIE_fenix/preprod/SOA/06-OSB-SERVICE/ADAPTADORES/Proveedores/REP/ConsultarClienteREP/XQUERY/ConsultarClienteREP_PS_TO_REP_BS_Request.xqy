xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace rep="http://org/bcie/ws/middle/REP.wsdl";

declare namespace ns2="http://org/bcie/ws/middle/REP.wsdl";
(:: import schema at "../../XSD/REP_CUSTOM.xsd" ::)
declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarClienteREPRequest as element() (:: schema-element(ns1:ConsultarClienteREPRequest) ::) external;

declare function local:func($ConsultarClienteREPRequest as element() (:: schema-element(ns1:ConsultarClienteREPRequest) ::)) as element() (:: element(rep:consultarReserva) ::) {
     <rep:consultarReserva>
         <codigoCliente>{fn:data($ConsultarClienteREPRequest/cli:idFacturador)}</codigoCliente>
         <fondos/>
      </rep:consultarReserva>

};

local:func($ConsultarClienteREPRequest)
