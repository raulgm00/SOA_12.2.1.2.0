xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $ConsultarOperacionResponse as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::) external;

declare function local:func($ConsultarOperacionResponse as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::)) as element() (:: schema-element(ns2:ConsultaDocumentosRequest) ::) {
    <ns2:ConsultaDocumentosRequest>
        {
            if ($ConsultarOperacionResponse/ns1:Operacion/ope:idOperacion)
            then <ns2:idOperacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:idOperacion)}</ns2:idOperacion>
            else ()
        }
    </ns2:ConsultaDocumentosRequest>
};

local:func($ConsultarOperacionResponse)
