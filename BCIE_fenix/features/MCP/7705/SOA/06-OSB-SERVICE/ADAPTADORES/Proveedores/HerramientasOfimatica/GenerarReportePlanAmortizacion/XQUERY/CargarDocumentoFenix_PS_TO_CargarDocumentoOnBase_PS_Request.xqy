xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $CargarDocumentosFenixResponse as element() (:: schema-element(ns1:CargarDocumentoFenixResponse) ::) external;

declare function local:func($CargarDocumentosFenixResponse as element() (:: schema-element(ns1:CargarDocumentoFenixResponse) ::)) as element() (:: schema-element(ns1:CargarDocumentoRequest) ::) {
    <ns1:CargarDocumentoRequest>
        <ns1:Documentos>
            <doc:Documento>
                <doc:idAdjunto>{fn:data($CargarDocumentosFenixResponse/ns1:Adjunto)}</doc:idAdjunto>
            </doc:Documento>
        </ns1:Documentos>
    </ns1:CargarDocumentoRequest>
};

local:func($CargarDocumentosFenixResponse)
