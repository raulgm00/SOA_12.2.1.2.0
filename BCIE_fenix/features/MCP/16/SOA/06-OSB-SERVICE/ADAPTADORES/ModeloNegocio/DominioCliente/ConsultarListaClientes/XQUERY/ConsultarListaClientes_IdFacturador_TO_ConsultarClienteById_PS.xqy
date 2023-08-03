xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarListaClientesRequest as element() (:: schema-element(ns1:ConsultarListaClientesRequest) ::) external;
declare variable $Index as xs:int external;

declare function local:func($ConsultarListaClientesRequest as element() (:: schema-element(ns1:ConsultarListaClientesRequest) ::), $Index as xs:int) as element() (:: schema-element(ns1:ConsultarClienteByIdClienteRequest) ::) {
    <ns1:ConsultarClienteByIdClienteRequest>
        <ns1:idFacturador>{fn:data($ConsultarListaClientesRequest/ns1:IdFacturador[$Index])}</ns1:idFacturador>
        
    </ns1:ConsultarClienteByIdClienteRequest>
};

local:func($ConsultarListaClientesRequest, $Index)
