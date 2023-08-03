xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarClienteResponse as element() (:: schema-element(ns2:ConsultarClienteResponse) ::) external;

declare function local:func($ConsultarClienteResponse as element() (:: schema-element(ns2:ConsultarClienteResponse) ::)) as element() (:: schema-element(ns1:ConsultaDocumentosRequest) ::) {
    <ns1:ConsultaDocumentosRequest>
        <ns1:idCliente>{fn:data($ConsultarClienteResponse/ns2:Cliente/cli:idCliente)}</ns1:idCliente>
        <ns1:idTarea>66</ns1:idTarea>
    </ns1:ConsultaDocumentosRequest>
};

local:func($ConsultarClienteResponse)
