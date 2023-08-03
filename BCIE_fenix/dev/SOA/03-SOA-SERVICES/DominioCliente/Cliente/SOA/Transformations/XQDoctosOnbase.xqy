xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare variable $DoctosPDFFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::) external;

declare function local:funcXqdoctosonbase($DoctosPDFFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::)) as element() (:: schema-element(doc:ConsultaDocumentosResponse) ::) {
    <doc:ConsultaDocumentosResponse>
        <doc:Documentos>
            <doc1:etapa>
                <doc1:tipoDocumento>
                    <doc1:nombreTipoDocumento></doc1:nombreTipoDocumento>
                    {
                        for $Documento in $DoctosPDFFenix.response/doc:Documento
                        where (fn:string-length($Documento/doc1:content/text()) = 0)
                        return 
                        <doc1:documento>
                            {
                              $Documento/*
                            }
                        </doc1:documento>
                    }
                </doc1:tipoDocumento>
            </doc1:etapa>
        </doc:Documentos>
        <doc:Resultado></doc:Resultado>
    </doc:ConsultaDocumentosResponse>
};

local:funcXqdoctosonbase($DoctosPDFFenix.response)
