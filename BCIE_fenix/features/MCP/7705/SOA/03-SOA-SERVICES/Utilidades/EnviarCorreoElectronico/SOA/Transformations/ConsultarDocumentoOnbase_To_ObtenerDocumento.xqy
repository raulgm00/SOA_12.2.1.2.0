xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace functx = "http://www.functx.com";

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare variable $outConsultarDocumentosOnBase.ConsultarDocumentosOnBaseResponse as element() (:: schema-element(doc:ConsultaDocumentosResponse) ::) external;
declare variable $inputVariable.payload as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) external;

declare function local:funcConsultardocumentoonbase_to_obtenerdocumento($outConsultarDocumentosOnBase.ConsultarDocumentosOnBaseResponse as element() (:: schema-element(doc:ConsultaDocumentosResponse) ::), 
                                                                        $inputVariable.payload as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::)) 
                                                                        as element() (:: schema-element(doc:ObtenerDocumentoOnBaseRequest) ::) {
    let $tipo := functx:if-empty($inputVariable.payload/cor:correoElectronico/cor1:attachment[1]/cor1:type, 1100)
    let $id := max(fn:data($outConsultarDocumentosOnBase.ConsultarDocumentosOnBaseResponse/doc:Documentos/doc1:etapa/doc1:tipoDocumento/doc1:documento[doc1:idTipoDocumento = $tipo]/doc1:idDocumento))
    return
    
    <doc:ObtenerDocumentoOnBaseRequest>
        <doc:idExterno>{fn:data($outConsultarDocumentosOnBase.ConsultarDocumentosOnBaseResponse/doc:Documentos/doc1:etapa/doc1:tipoDocumento/doc1:documento[doc1:idDocumento=$id]/doc1:idExterno)}</doc:idExterno>
        <doc:tipoArchivo>{fn:data($outConsultarDocumentosOnBase.ConsultarDocumentosOnBaseResponse/doc:Documentos/doc1:etapa/doc1:tipoDocumento/doc1:documento[doc1:idDocumento=$id]/doc1:mime_type)}</doc:tipoArchivo>
    </doc:ObtenerDocumentoOnBaseRequest>
};

declare function functx:if-empty
  ( $arg as item()? ,
    $value as item()* )  as item()* {

  if (string($arg) != '')
  then data($arg)
  else $value
 } ;

local:funcConsultardocumentoonbase_to_obtenerdocumento($outConsultarDocumentosOnBase.ConsultarDocumentosOnBaseResponse, $inputVariable.payload)
