xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarDocumentosOnBase";
(:: import schema at "../XSD/ConsultarDocumentosOnBase_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ListaDocumentos as element() (:: schema-element(ns1:OutputParameters) ::) external;
declare variable $ConsultarDocumentosRequest as element() (:: schema-element(ns2:ConsultaDocumentosRequest) ::) external;

declare function local:func($ListaDocumentos as element() (:: schema-element(ns1:OutputParameters) ::),$ConsultarDocumentosRequest as element() (:: schema-element(ns2:ConsultaDocumentosRequest) ::)) as element() (:: schema-element(ns2:ConsultaDocumentosResponse) ::) {
     <ns2:ConsultaDocumentosResponse>
     
      <ns2:Documentos>
    
      {
        let $etapas := distinct-values($ListaDocumentos/ns1:F_CONSULTA_ON_BASE/ns1:F_CONSULTA_ON_BASE_ITEM/ns1:ETAPA)
       
        return
          for $etapa in $etapas
          return 
          <doc:etapa>
            <doc:nombreEtapa>{data($etapa)}</doc:nombreEtapa>
            {
              let $documentosPorEtapa := $ListaDocumentos/ns1:F_CONSULTA_ON_BASE/ns1:F_CONSULTA_ON_BASE_ITEM[ns1:ETAPA = data($etapa)]
              let $tiposDocumento := distinct-values($documentosPorEtapa/ns1:NOMBRE_TIPO_DOCUMENTO)
              for $tipoDocumento in $tiposDocumento
              return
                 <doc:tipoDocumento>
                    <doc:nombreTipoDocumento>{data($tipoDocumento)}</doc:nombreTipoDocumento>
                    {
                      for $documento in $documentosPorEtapa
                      where data($documento/ns1:NOMBRE_TIPO_DOCUMENTO) = data($tipoDocumento)
                      return
                        <doc:documento>
                            <doc:idDocumento>{data($documento/ns1:ID_DOCUMENTO)}</doc:idDocumento>
                            <doc:idTipoDocumento>{data($documento/ns1:ID_TIPO_DOCUMENTO)}</doc:idTipoDocumento>
                            <doc:nombreTipoDocumento>{data($documento/ns1:NOMBRE_TIPO_DOCUMENTO)}</doc:nombreTipoDocumento>
                            <doc:etapa>{data($etapa)}</doc:etapa>
                            <doc:codigoDocumento>{data($documento/ns1:CODIGO_DOCUMENTO)}</doc:codigoDocumento>
                            <doc:idExterno>{data($documento/ns1:ITEMNUM)}</doc:idExterno>
                             <doc:idOperacion>{data($documento/ns1:CODIGO_INTERVENCION)}</doc:idOperacion>
                             <doc:idProducto>{data($documento/ns1:ID_PRODUCTO)}</doc:idProducto>
                             <doc:idPais>{data($documento/ns1:ID_PAIS)}</doc:idPais>
                             <doc:nombre>{data($documento/ns1:NOMBRE)}</doc:nombre>
                             <doc:filename>{data($documento/ns1:FILENAME)}</doc:filename>
                             <doc:mime_type>{data($documento/ns1:MIME_TYPE)}</doc:mime_type>
                            <doc:tamanio>{data($documento/ns1:TAMANIO)}</doc:tamanio>
                            <doc:idAdjunto>{data($documento/ns1:ID_ADJUNTO)}</doc:idAdjunto>
                            <doc:esJustificacion>{data($documento/ns1:ES_JUSTIFICACION)}</doc:esJustificacion>
                   
                            <doc:comentario>{data($documento/ns1:COMENTARIO)}</doc:comentario>
                            <doc:fechaDocumento>{data($documento/ns1:FECHA_DOCUMENTO)}</doc:fechaDocumento>
                            <doc:fechaRegistro>{data($documento/ns1:FECHA_REGISTRO)}</doc:fechaRegistro>
                            <doc:tarea>{data($documento/ns1:TAREA)}</doc:tarea>
                            <doc:idtarea>{data($documento/ns1:ID_TAREA)}</doc:idtarea>
                            <doc:PuedeModificar> { data($documento/ns1:PUEDE_MODIFICAR)  }     </doc:PuedeModificar>
                            <doc:PuedeBorrar>{ data($documento/ns1:PUEDE_BORRAR)}</doc:PuedeBorrar>
                             <doc:usuarioAgrega>{fn:data($documento/ns1:NOMBRE_USUARIO_CREA)}</doc:usuarioAgrega>
                              <doc:usuarioUltimaActualizacion>{fn:data($documento/ns1:NOMBRE_USUARIO_MODIFICA)}</doc:usuarioUltimaActualizacion>
                              <doc:idFlujoNegocio>{string($documento/ns1:IDFLUJONEGOCIO)}</doc:idFlujoNegocio>
                               
                   
                        </doc:documento>
                    }
                </doc:tipoDocumento>
            }
          </doc:etapa>
      }
      </ns2:Documentos>
      

 
      
      
      {if (count($ListaDocumentos/ns1:F_CONSULTA_ON_BASE/ns1:F_CONSULTA_ON_BASE_ITEM)>0)then 

      <ns2:Resultado>
          <res:result>OK</res:result>
          <res:message>Consulta de documentos realizada exitosamente</res:message>
          <res:error>
              <err:errorCode></err:errorCode>
              <err:errorDescription></err:errorDescription>
              <err:errorType></err:errorType>
          </res:error>
      </ns2:Resultado>
      else 
      
       <ns2:Resultado>
          <res:result>OK</res:result>
          <res:message>Consulta sin Resultado</res:message>
          <res:error>
              <err:errorCode></err:errorCode>
              <err:errorDescription></err:errorDescription>
              <err:errorType></err:errorType>
          </res:error>
      </ns2:Resultado>
      }
    </ns2:ConsultaDocumentosResponse>
    
};

local:func($ListaDocumentos, $ConsultarDocumentosRequest)