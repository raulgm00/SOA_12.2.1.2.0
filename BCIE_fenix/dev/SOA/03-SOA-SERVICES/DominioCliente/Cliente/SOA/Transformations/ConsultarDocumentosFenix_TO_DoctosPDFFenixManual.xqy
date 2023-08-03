xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $outInvokeConsultarDocumentosFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(cli:EnviarDocumentoClienteRequest) ::) external;

declare function local:funcConsultardocumentosfenix_to_enviarcorreoelectronico($outInvokeConsultarDocumentosFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::), 
                                                                       $inputVariable.request as element() (:: schema-element(cli:EnviarDocumentoClienteRequest) ::)) 
                                                                       as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::) {
<doc:ConsultarDocumentosFenixResponse>
    {
    for $documento in $outInvokeConsultarDocumentosFenix.response/doc:Documento[doc1:mime_type ='application/pdf']
    return
    if (fn:data($documento/doc1:idTipoDocumento) = 1121 or fn:data($documento/doc1:idTipoDocumento) = 1122 or fn:data($documento/doc1:idTipoDocumento) = 1123) then 
        <doc:Documento>
            <doc1:idDocumento>{fn:data($documento/doc1:idDocumento)}</doc1:idDocumento>
            <doc1:idTipoDocumento>{fn:data($documento/doc1:idTipoDocumento)}</doc1:idTipoDocumento>
            <doc1:idTipoDocumentoExterno>{fn:data($documento/doc1:idTipoDocumentoExterno)}</doc1:idTipoDocumentoExterno>
            <doc1:nombreTipoDocumento>{fn:data($documento/doc1:nombreTipoDocumento)}</doc1:nombreTipoDocumento>
            <doc1:etapa>{fn:data($documento/doc1:etapa)}</doc1:etapa>
            <doc1:codigoDocumento>{fn:data($documento/doc1:codigoDocumento)}</doc1:codigoDocumento>
            <doc1:idExterno>{fn:data($documento/doc1:idExterno)}</doc1:idExterno>
            <doc1:idDocAreaTipo>{fn:data($documento/doc1:idDocAreaTipo)}</doc1:idDocAreaTipo>
            <doc1:idOperacion>{fn:data($documento/doc1:idOperacion)}</doc1:idOperacion>
            <doc1:idCliente>{fn:data($documento/doc1:idCliente)}</doc1:idCliente>
            <doc1:idProducto>{fn:data($documento/doc1:idProducto)}</doc1:idProducto>
            <doc1:idPais>{fn:data($documento/doc1:idPais)}</doc1:idPais>
            <doc1:nombre>{fn:data($documento/doc1:nombre)}</doc1:nombre>
            <doc1:filename>{fn:data($documento/doc1:filename)}</doc1:filename>
            <doc1:mime_type>{fn:data($documento/doc1:mime_type)}</doc1:mime_type>
            <doc1:tamanio>{fn:data($documento/doc1:tamanio)}</doc1:tamanio>
            <doc1:idAdjunto>{fn:data($documento/doc1:idAdjunto)}</doc1:idAdjunto>
            <doc1:esJustificacion>{fn:data($documento/doc1:esJustificacion)}</doc1:esJustificacion>
            <doc1:comentario>{fn:data($documento/doc1:comentario)}</doc1:comentario>
            <doc1:fechaDocumento>{fn:data($documento/doc1:fechaDocumento)}</doc1:fechaDocumento>
            <doc1:fechaRegistro>{fn:data($documento/doc1:fechaRegistro)}</doc1:fechaRegistro>
            <doc1:status>{fn:data($documento/doc1:status)}</doc1:status>
            <doc1:estatusTipoDoc>{fn:data($documento/doc1:estatusTipoDoc)}</doc1:estatusTipoDoc>
            <doc1:codExtTipoDoc>{fn:data($documento/doc1:codExtTipoDoc)}</doc1:codExtTipoDoc>
            <doc1:tarea>{fn:data($documento/doc1:tarea)}</doc1:tarea>
            <doc1:idtarea>{fn:data($documento/doc1:idtarea)}</doc1:idtarea>
            <doc1:content>{fn:data($documento/doc1:content)}</doc1:content>
            <doc1:PuedeModificar>{fn:data($documento/doc1:PuedeModificar)}</doc1:PuedeModificar>
            <doc1:PuedeBorrar>{fn:data($documento/doc1:PuedeBorrar)}</doc1:PuedeBorrar>
            <doc1:usuarioAgrega>{fn:data($documento/doc1:usuarioAgrega)}</doc1:usuarioAgrega>
            <doc1:usuarioUltimaActualizacion>{fn:data($documento/doc1:usuarioUltimaActualizacion)}</doc1:usuarioUltimaActualizacion>
            <doc1:accionARealizar>{fn:data($documento/doc1:accionARealizar)}</doc1:accionARealizar>
            <doc1:idFlujoNegocio>{fn:data($documento/doc1:idFlujoNegocio)}</doc1:idFlujoNegocio>
        </doc:Documento>
        else()
        }
    </doc:ConsultarDocumentosFenixResponse>
};

local:funcConsultardocumentosfenix_to_enviarcorreoelectronico($outInvokeConsultarDocumentosFenix.response, $inputVariable.request)
