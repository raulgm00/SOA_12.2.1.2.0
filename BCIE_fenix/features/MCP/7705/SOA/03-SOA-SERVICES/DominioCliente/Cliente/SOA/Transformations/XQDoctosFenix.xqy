xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare variable $DoctosPDFFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::) external;

declare function local:funcXqdoctosfenix($DoctosPDFFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::)) as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::) {
    <doc:ConsultarDocumentosFenixResponse>
        {
            for $Documento in $DoctosPDFFenix.response/doc:Documento
            where (fn:string-length($Documento/doc1:content/text()) != 0)
            return 
            <doc:Documento>
                {
                  $Documento/*
                }
            </doc:Documento>
        }
    </doc:ConsultarDocumentosFenixResponse>
};

local:funcXqdoctosfenix($DoctosPDFFenix.response)
