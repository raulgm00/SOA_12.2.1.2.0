xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarCliente";
(:: import schema at "../XSD/ConsultarCliente_sp.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarClienteFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBERequest) ::) external;

declare function local:func($ConsultarClienteFLEXCUBERequest as element() (:: schema-element(ns1:ConsultarClienteFLEXCUBERequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
            <ns2:CODIGO_CLIENTE>{fn:data($ConsultarClienteFLEXCUBERequest/cli:idFacturador)}</ns2:CODIGO_CLIENTE>
    </ns2:InputParameters>
};

local:func($ConsultarClienteFLEXCUBERequest)