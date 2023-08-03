xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $OutputParameters as element() (:: schema-element(ns2:CargarDocumentoFenixResponse) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns2:CargarDocumentoFenixResponse) ::)) as element() (:: schema-element(ns2:CargarDocumentoRequest) ::) {
    <ns2:CargarDocumentoRequest>
        <ns2:Documentos>
            <doc:Documento>
             
               <doc:idAdjunto>{fn:data($OutputParameters/ns2:Adjunto)}</doc:idAdjunto>
                
            </doc:Documento>
        </ns2:Documentos>
    </ns2:CargarDocumentoRequest>
};

local:func($OutputParameters)
