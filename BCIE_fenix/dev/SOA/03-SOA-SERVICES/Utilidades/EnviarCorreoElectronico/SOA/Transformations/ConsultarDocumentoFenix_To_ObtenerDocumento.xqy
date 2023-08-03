xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace functx = "http://www.functx.com";

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare variable $ConsultarDocumentosFenixResponse as element() (:: schema-element(ns2:ConsultarDocumentosFenixResponse) ::) external;
declare variable $inputVariable.payload as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) external;

declare function local:funcConsultardocumentofenix_to_obtenerdocumento($ConsultarDocumentosFenixResponse as element() (:: schema-element(ns2:ConsultarDocumentosFenixResponse) ::), 
                                                                       $inputVariable.payload as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::)) 
                                                                       as element() (:: schema-element(ns2:ObtenerDocumentoOnBaseRequest) ::) {
    
    let $tipo := functx:if-empty($inputVariable.payload/cor:correoElectronico/cor1:attachment[1]/cor1:type, 1100)
    let $id := max(fn:data($ConsultarDocumentosFenixResponse/ns2:Documento[doc:idTipoDocumento = $tipo]/doc:idDocumento))
    return
    
    <ns2:ObtenerDocumentoOnBaseRequest>
        <ns2:idExterno>{fn:data($ConsultarDocumentosFenixResponse/ns2:Documento[doc:idDocumento=$id]/doc:idExterno)}</ns2:idExterno>
        <ns2:tipoArchivo>{fn:data($ConsultarDocumentosFenixResponse/ns2:Documento[doc:idDocumento=$id]/doc:mime_type)}</ns2:tipoArchivo>
    </ns2:ObtenerDocumentoOnBaseRequest>
};

declare function functx:if-empty
  ( $arg as item()? ,
    $value as item()* )  as item()* {

  if (string($arg) != '')
  then data($arg)
  else $value
 } ;
 

local:funcConsultardocumentofenix_to_obtenerdocumento($ConsultarDocumentosFenixResponse, $inputVariable.payload)
