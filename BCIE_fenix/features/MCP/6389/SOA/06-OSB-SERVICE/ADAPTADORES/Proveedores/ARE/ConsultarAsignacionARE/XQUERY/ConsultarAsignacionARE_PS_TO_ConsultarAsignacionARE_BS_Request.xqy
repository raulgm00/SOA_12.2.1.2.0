xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace are="http://org/bcie/ws/middle/ARE/ARE.wsdl";

declare namespace ns2="http://org/bcie/ws/middle/ARE/ARE.wsdl/types/";
(:: import schema at "../../WSDL/ARE.wsdl" ::)
declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare variable $ConsultarAsignacionARERequest as element() (:: schema-element(ns1:ConsultarAsignacionARERequest) ::) external;

declare function local:func($ConsultarAsignacionARERequest as element() (:: schema-element(ns1:ConsultarAsignacionARERequest) ::)) as element() (:: element(are:consultarAsignacion) ::) {
    <are:consultarAsignacion>
        <ns2:codigoAsignacion>{fn:data($ConsultarAsignacionARERequest/ns1:codigoAsignacion)}</ns2:codigoAsignacion>
    </are:consultarAsignacion>
};

local:func($ConsultarAsignacionARERequest)
