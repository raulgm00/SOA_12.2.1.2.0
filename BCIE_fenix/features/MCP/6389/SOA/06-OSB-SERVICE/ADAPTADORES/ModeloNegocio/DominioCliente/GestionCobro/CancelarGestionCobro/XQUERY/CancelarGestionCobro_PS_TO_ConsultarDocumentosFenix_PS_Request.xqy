xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare variable $CancelarGestionCobroRequest as element() (:: schema-element(ns1:CancelarGestionCobroRequest) ::) external;

declare function local:func($CancelarGestionCobroRequest as element() (:: schema-element(ns1:CancelarGestionCobroRequest) ::)) as element() (:: schema-element(ns2:ConsultarDocumentosFenixRequest) ::) {
    <ns2:ConsultarDocumentosFenixRequest>
        <ns2:idCliente>{fn:data($CancelarGestionCobroRequest/ns1:idCliente)}</ns2:idCliente>
        <ns2:idOperacion></ns2:idOperacion>
        <ns2:Content>{fn:false()}</ns2:Content>
    </ns2:ConsultarDocumentosFenixRequest>
};

local:func($CancelarGestionCobroRequest)
