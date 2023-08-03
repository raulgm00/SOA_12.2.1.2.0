xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ConsultarDocumentosFenixResponse as element() (:: schema-element(ns1:ConsultarDocumentosFenixResponse) ::) external;

declare function local:func($ConsultarDocumentosFenixResponse as element() (:: schema-element(ns1:ConsultarDocumentosFenixResponse) ::)) as element() (:: schema-element(ns1:EliminarDocumentoFenixRequest) ::) {
    <ns1:EliminarDocumentoFenixRequest>
      {for $docs in $ConsultarDocumentosFenixResponse/ns1:Documento
      where $docs/doc:idTipoDocumento = 1122 or $docs/doc:idTipoDocumento = 1123
      return
        <ns1:idAdjunto>{fn:data($docs/doc:idAdjunto)}</ns1:idAdjunto>
      }  
    </ns1:EliminarDocumentoFenixRequest>
};

local:func($ConsultarDocumentosFenixResponse)
