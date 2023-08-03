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

declare variable $outInvokeConsultarDocumentosOnBase.response as element() (:: schema-element(doc:ConsultaDocumentosResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(cli:EnviarDocumentoClienteRequest) ::) external;

declare function local:funcConsultardocumentoonbase_to_obtenerdocumentoonbase2($outInvokeConsultarDocumentosOnBase.response as element() (:: schema-element(doc:ConsultaDocumentosResponse) ::), 
                                                                  $inputVariable.request as element() (:: schema-element(cli:EnviarDocumentoClienteRequest) ::)) 
                                                                  as element() (:: schema-element(doc:ConsultaDocumentosResponse) ::) {
    <doc:ConsultaDocumentosResponse>
        <doc:Documentos>
                <doc1:etapa>
                        <doc1:tipoDocumento>
                          {
                          if (fn:data($inputVariable.request/cli:parametros[com:name='TIPO_INICIO']/com:value) = 'MANUAL') 
                          then
                          for $documentoPDF in $outInvokeConsultarDocumentosOnBase.response/doc:Documentos/doc1:etapa/doc1:tipoDocumento/doc1:documento[doc1:mime_type = 'application/pdf']
                            return 
                            if (fn:data($documentoPDF/doc1:idTipoDocumento) = 1121 or fn:data($documentoPDF/doc1:idTipoDocumento) = 1122 or fn:data($documentoPDF/doc1:idTipoDocumento) = 1123) then 
                            <doc1:documento>
                              <doc1:idDocumento>{fn:data($documentoPDF/doc1:idDocumento)}</doc1:idDocumento>
                               <doc1:idTipoDocumento>{fn:data($documentoPDF/doc1:idTipoDocumento)}</doc1:idTipoDocumento>
                               <doc1:idTipoDocumentoExterno>{fn:data($documentoPDF/doc1:idTipoDocumentoExterno)}</doc1:idTipoDocumentoExterno>
                               <doc1:nombreTipoDocumento>{fn:data($documentoPDF/doc1:nombreTipoDocumento)}</doc1:nombreTipoDocumento>
                               <doc1:etapa>{fn:data($documentoPDF/doc1:etapa)}</doc1:etapa>
                               <doc1:codigoDocumento>{fn:data($documentoPDF/doc1:codigoDocumento)}</doc1:codigoDocumento>
                               <doc1:idExterno>{fn:data($documentoPDF/doc1:idExterno)}</doc1:idExterno>
                               <doc1:idDocAreaTipo>{fn:data($documentoPDF/doc1:idDocAreaTipo)}</doc1:idDocAreaTipo>
                               <doc1:idOperacion>{fn:data($documentoPDF/doc1:idOperacion)}</doc1:idOperacion>
                               <doc1:idCliente>{fn:data($documentoPDF/doc1:idCliente)}</doc1:idCliente>
                               <doc1:idProducto>{fn:data($documentoPDF/doc1:idProducto)}</doc1:idProducto>
                               <doc1:idPais>{fn:data($documentoPDF/doc1:idPais)}</doc1:idPais>
                               <doc1:nombre>{fn:data($documentoPDF/doc1:nombre)}</doc1:nombre>
                               <doc1:filename>{fn:data($documentoPDF/doc1:filename)}</doc1:filename>
                               <doc1:mime_type>{fn:data($documentoPDF/doc1:mime_type)}</doc1:mime_type>
                               <doc1:tamanio>{fn:data($documentoPDF/doc1:tamanio)}</doc1:tamanio>
                               <doc1:idAdjunto>{fn:data($documentoPDF/doc1:idAdjunto)}</doc1:idAdjunto>
                               <doc1:esJustificacion>{fn:data($documentoPDF/doc1:esJustificacion)}</doc1:esJustificacion>
                               <doc1:comentario>{fn:data($documentoPDF/doc1:comentario)}</doc1:comentario>
                               <doc1:fechaDocumento>{fn:data($documentoPDF/doc1:fechaDocumento)}</doc1:fechaDocumento>
                               <doc1:fechaRegistro>{fn:data($documentoPDF/doc1:fechaRegistro)}</doc1:fechaRegistro>
                               <doc1:status>{fn:data($documentoPDF/doc1:status)}</doc1:status>
                               <doc1:estatusTipoDoc>{fn:data($documentoPDF/doc1:estatusTipoDoc)}</doc1:estatusTipoDoc>
                               <doc1:codExtTipoDoc>{fn:data($documentoPDF/doc1:codExtTipoDoc)}</doc1:codExtTipoDoc>
                               <doc1:tarea>{fn:data($documentoPDF/doc1:tarea)}</doc1:tarea>
                               <doc1:idtarea>{fn:data($documentoPDF/doc1:idtarea)}</doc1:idtarea>
                               <doc1:content>{fn:data($documentoPDF/doc1:content)}</doc1:content>
                               <doc1:PuedeModificar>{fn:data($documentoPDF/doc1:PuedeModificar)}</doc1:PuedeModificar>
                               <doc1:PuedeBorrar>{fn:data($documentoPDF/doc1:PuedeBorrar)}</doc1:PuedeBorrar>
                               <doc1:usuarioAgrega>{fn:data($documentoPDF/doc1:usuarioAgrega)}</doc1:usuarioAgrega>
                               <doc1:usuarioUltimaActualizacion>{fn:data($documentoPDF/doc1:usuarioUltimaActualizacion)}</doc1:usuarioUltimaActualizacion>
                               <doc1:accionARealizar>{fn:data($documentoPDF/doc1:accionARealizar)}</doc1:accionARealizar>
                               <doc1:idFlujoNegocio>{fn:data($documentoPDF/doc1:idFlujoNegocio)}</doc1:idFlujoNegocio>
                            </doc1:documento>
                            else()
                          else
                          for $documentoPDF in $outInvokeConsultarDocumentosOnBase.response/doc:Documentos/doc1:etapa/doc1:tipoDocumento/doc1:documento[doc1:mime_type = 'application/pdf'][string(doc1:idAdjunto) = fn:data($inputVariable.request/cli:parametros[com:name='ID_ADJUNTO_PDF']/com:value)]
                            return 
                            if (fn:data($documentoPDF/doc1:idTipoDocumento) = 1121 or fn:data($documentoPDF/doc1:idTipoDocumento) = 1122 or fn:data($documentoPDF/doc1:idTipoDocumento) = 1123) then 
                            <doc1:documento>
                              <doc1:idDocumento>{fn:data($documentoPDF/doc1:idDocumento)}</doc1:idDocumento>
                               <doc1:idTipoDocumento>{fn:data($documentoPDF/doc1:idTipoDocumento)}</doc1:idTipoDocumento>
                               <doc1:idTipoDocumentoExterno>{fn:data($documentoPDF/doc1:idTipoDocumentoExterno)}</doc1:idTipoDocumentoExterno>
                               <doc1:nombreTipoDocumento>{fn:data($documentoPDF/doc1:nombreTipoDocumento)}</doc1:nombreTipoDocumento>
                               <doc1:etapa>{fn:data($documentoPDF/doc1:etapa)}</doc1:etapa>
                               <doc1:codigoDocumento>{fn:data($documentoPDF/doc1:codigoDocumento)}</doc1:codigoDocumento>
                               <doc1:idExterno>{fn:data($documentoPDF/doc1:idExterno)}</doc1:idExterno>
                               <doc1:idDocAreaTipo>{fn:data($documentoPDF/doc1:idDocAreaTipo)}</doc1:idDocAreaTipo>
                               <doc1:idOperacion>{fn:data($documentoPDF/doc1:idOperacion)}</doc1:idOperacion>
                               <doc1:idCliente>{fn:data($documentoPDF/doc1:idCliente)}</doc1:idCliente>
                               <doc1:idProducto>{fn:data($documentoPDF/doc1:idProducto)}</doc1:idProducto>
                               <doc1:idPais>{fn:data($documentoPDF/doc1:idPais)}</doc1:idPais>
                               <doc1:nombre>{fn:data($documentoPDF/doc1:nombre)}</doc1:nombre>
                               <doc1:filename>{fn:data($documentoPDF/doc1:filename)}</doc1:filename>
                               <doc1:mime_type>{fn:data($documentoPDF/doc1:mime_type)}</doc1:mime_type>
                               <doc1:tamanio>{fn:data($documentoPDF/doc1:tamanio)}</doc1:tamanio>
                               <doc1:idAdjunto>{fn:data($documentoPDF/doc1:idAdjunto)}</doc1:idAdjunto>
                               <doc1:esJustificacion>{fn:data($documentoPDF/doc1:esJustificacion)}</doc1:esJustificacion>
                               <doc1:comentario>{fn:data($documentoPDF/doc1:comentario)}</doc1:comentario>
                               <doc1:fechaDocumento>{fn:data($documentoPDF/doc1:fechaDocumento)}</doc1:fechaDocumento>
                               <doc1:fechaRegistro>{fn:data($documentoPDF/doc1:fechaRegistro)}</doc1:fechaRegistro>
                               <doc1:status>{fn:data($documentoPDF/doc1:status)}</doc1:status>
                               <doc1:estatusTipoDoc>{fn:data($documentoPDF/doc1:estatusTipoDoc)}</doc1:estatusTipoDoc>
                               <doc1:codExtTipoDoc>{fn:data($documentoPDF/doc1:codExtTipoDoc)}</doc1:codExtTipoDoc>
                               <doc1:tarea>{fn:data($documentoPDF/doc1:tarea)}</doc1:tarea>
                               <doc1:idtarea>{fn:data($documentoPDF/doc1:idtarea)}</doc1:idtarea>
                               <doc1:content>{fn:data($documentoPDF/doc1:content)}</doc1:content>
                               <doc1:PuedeModificar>{fn:data($documentoPDF/doc1:PuedeModificar)}</doc1:PuedeModificar>
                               <doc1:PuedeBorrar>{fn:data($documentoPDF/doc1:PuedeBorrar)}</doc1:PuedeBorrar>
                               <doc1:usuarioAgrega>{fn:data($documentoPDF/doc1:usuarioAgrega)}</doc1:usuarioAgrega>
                               <doc1:usuarioUltimaActualizacion>{fn:data($documentoPDF/doc1:usuarioUltimaActualizacion)}</doc1:usuarioUltimaActualizacion>
                               <doc1:accionARealizar>{fn:data($documentoPDF/doc1:accionARealizar)}</doc1:accionARealizar>
                               <doc1:idFlujoNegocio>{fn:data($documentoPDF/doc1:idFlujoNegocio)}</doc1:idFlujoNegocio>
                            </doc1:documento>
                            else()
                        }
                        </doc1:tipoDocumento>
                    
                </doc1:etapa>
            
        </doc:Documentos>
    </doc:ConsultaDocumentosResponse>
};

local:funcConsultardocumentoonbase_to_obtenerdocumentoonbase2($outInvokeConsultarDocumentosOnBase.response,  $inputVariable.request)
