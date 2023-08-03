xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare namespace functx = "http://www.functx.com";

declare namespace cor1 = "http://www.bcie.org/CorreoBO";


declare variable $varInvokeWsConsultarDocumentosFenixRs.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::) external;
declare variable $inputVariable.payload as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) external;

declare function local:funcConsultardocumentos_to_enviarcorreorequest($varInvokeWsConsultarDocumentosFenixRs.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::), 
                                                                      $inputVariable.payload as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::)) 
                                                                      as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) {
                                                                      
    let $tipo := functx:if-empty($inputVariable.payload/cor:correoElectronico/cor1:attachment[1]/cor1:type, 1100)
    let $id := max(fn:data($varInvokeWsConsultarDocumentosFenixRs.response/doc:Documento[doc1:idTipoDocumento = $tipo]/doc1:idDocumento))
    return
    <cor:EnviarCorreoElectronicoBPELRequest>
        <cor:correoElectronico>
            <cor1:attachment>
                <cor1:name>{fn:data($varInvokeWsConsultarDocumentosFenixRs.response/doc:Documento[doc1:idDocumento=$id]/doc1:filename)}</cor1:name>
                <cor1:type>{fn:data($varInvokeWsConsultarDocumentosFenixRs.response/doc:Documento[doc1:idDocumento=$id]/doc1:mime_type)}</cor1:type>
                <cor1:content>{fn:data($varInvokeWsConsultarDocumentosFenixRs.response/doc:Documento[doc1:idDocumento=$id]/doc1:content)}</cor1:content>
            </cor1:attachment>
        </cor:correoElectronico>
    </cor:EnviarCorreoElectronicoBPELRequest>
};

declare function functx:if-empty
  ( $arg as item()? ,
    $value as item()* )  as item()* {

  if (string($arg) != '')
  then data($arg)
  else $value
 } ;
 

local:funcConsultardocumentos_to_enviarcorreorequest($varInvokeWsConsultarDocumentosFenixRs.response, $inputVariable.payload)
