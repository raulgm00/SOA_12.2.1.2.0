xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FLEXCUBE14/ClienteMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Flexcube14/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/consultarCodigoCliente_DB";
(:: import schema at "../XSD/consultarCodigoCliente_DB.xsd" ::)

declare variable $ConsultarCodigoClienteRequest as element() (:: schema-element(ns1:ConsultarCodigoClienteRequest) ::) external;

declare function local:func($ConsultarCodigoClienteRequest as element() (:: schema-element(ns1:ConsultarCodigoClienteRequest) ::)) as element() (:: schema-element(ns2:consultarCodigoCliente_DBInput) ::) {
    <ns2:consultarCodigoCliente_DBInput>
        <ns2:ABREVIATURA>{fn:data($ConsultarCodigoClienteRequest/ns1:Cliente/ns1:Abreviatura)}</ns2:ABREVIATURA>
    </ns2:consultarCodigoCliente_DBInput>
};

local:func($ConsultarCodigoClienteRequest)
