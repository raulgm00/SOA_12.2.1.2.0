xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarClienteDuplicado";
(:: import schema at "../XSD/ValidarClienteDuplicado_sp.xsd" ::)

declare variable $ValidarClienteDuplicadoRequest as element() (:: schema-element(ns1:ValidarClienteDuplicadoRequest) ::) external;

declare function local:func($ValidarClienteDuplicadoRequest as element() (:: schema-element(ns1:ValidarClienteDuplicadoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ABREVIATURA>{fn:data($ValidarClienteDuplicadoRequest/ns1:Abreviatura)}</ns2:P_ABREVIATURA>
        <ns2:P_RAZON_SOCIAL>{fn:data($ValidarClienteDuplicadoRequest/ns1:RazonSocial)}</ns2:P_RAZON_SOCIAL>
    </ns2:InputParameters>
};

local:func($ValidarClienteDuplicadoRequest)
