xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns4="http://www.bcie.org/ConsultarOperacionMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarDetalleVotacion/V1/Schema/ConsultarOperacionMO.xsd" ::)
declare namespace ns3="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace ns5="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDocumentoTareaDB";
(:: import schema at "../../../../ADAPTADORES/ModeloNegocio/DominioDocumento/ConsultarDocumentoTarea/XSD/ConsultarDocumentoTareaDB.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarOperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ConsultarOperacionResponse as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::) external;
declare variable $ConsultarDecisionAprobacionByIdAprobacionResponse as element() (:: schema-element(ns2:ConsultarDecisionAprobacionByIdAprobacionResponse) ::) external;
declare variable $ConsultaDocumentosResponse as element() (:: schema-element(ns3:ConsultaDocumentosResponse) ::) external;
declare variable $ConsultarDocumentosFenixResponse as element() (:: schema-element(ns3:ConsultarDocumentosFenixResponse) ::) external;
declare variable $ConsultarDocumentosTareaResponse as element() (:: schema-element(ns5:ConsultarDocumentoTareaDBOutputCollection) ::) external;


declare function local:func($ConsultarOperacionResponse as element() (:: schema-element(ns1:ConsultarOperacionResponse) ::), 
                            $ConsultarDecisionAprobacionByIdAprobacionResponse as element() (:: schema-element(ns2:ConsultarDecisionAprobacionByIdAprobacionResponse) ::), 
                            $ConsultaDocumentosResponse as element() (:: schema-element(ns3:ConsultaDocumentosResponse) ::),
                            $ConsultarDocumentosFenixResponse as element() (:: schema-element(ns3:ConsultarDocumentosFenixResponse) ::),
                            $ConsultarDocumentosTareaResponse as element() (:: schema-element(ns5:ConsultarDocumentoTareaDBOutputCollection) ::))
                            as element() (:: schema-element(ns4:responseConsultarOperacionMessage) ::) {
    <ns4:responseConsultarOperacionMessage>
        <ns4:detalleOperacion>
            <con:operacion>
                <con:idOperacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:idOperacion)}</con:idOperacion>
                <con:nombreOperacion>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:nombre)}</con:nombreOperacion>
                <con:idProducto>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:idProducto)}</con:idProducto>
                <con:producto>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:producto/pro:descripcion)}</con:producto>
                <con:idCliente>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:idCliente)}</con:idCliente>
                <con:nombreCliente>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:razonSocial)}</con:nombreCliente>
                <con:idPais>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:Id)}</con:idPais>
                <con:pais>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:pais/cat:Descripcion)}</con:pais>
                <con:idSector>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:Id)}</con:idSector>
                <con:sector>{fn:data($ConsultarOperacionResponse/ns1:Operacion/ope:cliente/cli:sector/cat:Descripcion)}</con:sector>
            </con:operacion>
            {
            for $i in $ConsultaDocumentosResponse/ns3:Documentos/doc:etapa/doc:tipoDocumento
             where count($ConsultarDocumentosTareaResponse/ns5:ConsultarDocumentoTareaDBOutput[ns5:ID_TCA_DOCUMENTO/text() = $i/doc:documento/doc:idTipoDocumento/text()]) > 0
            return
                
                let $mostrarUltimo := $ConsultarDocumentosTareaResponse/ns5:ConsultarDocumentoTareaDBOutput[ns5:ID_TCA_DOCUMENTO/text() = $i/doc:documento/doc:idTipoDocumento/text()]/ns5:MOSTRAR_ULTIMO/text()
                let $fechaMaxima := max( for $registro in $ConsultaDocumentosResponse/ns3:Documentos/doc:etapa/doc:tipoDocumento/doc:documento[doc:idTipoDocumento/text() = $i/doc:documento/doc:idTipoDocumento/text()] return xs:dateTime($registro/doc:fechaRegistro))
            
                for $documento in $i/doc:documento
                where ($documento/doc:mime_type = 'application/pdf' or $documento/doc:mime_type = 'application/vnd.openxmlformats-officedocument.wordprocessingml.document' 
                        or $documento/doc:mime_type = 'application/msword')
                
                return 
                  if($mostrarUltimo != 1 or $documento/doc:fechaRegistro/text() = $fechaMaxima)
                  then 
                    <con:listadoDocumentos>
                      <con:idDocumento>{fn:data($documento/doc:idExterno)}</con:idDocumento>
                      <con:idAdjunto>{fn:data($documento/doc:idAdjunto)}</con:idAdjunto>
                      <con:nombreDocumento>{fn:data($documento/doc:nombreTipoDocumento)}</con:nombreDocumento>
                     {
                      if(fn:string($documento/doc:esJustificacion)='1')
                        then
                             <con:esJustificacion>{fn:string('true')}</con:esJustificacion>
                        
                      else if (fn:string($documento/doc:esJustificacion)='0')
                        then
                       <con:esJustificacion>{fn:string('false')}</con:esJustificacion>
                      else
                      ()
                     }
                     {
                      if(fn:string($documento/doc:esJustificacion)='1')
                        then
                             <con:comentario>{fn:data($documento/doc:comentario)}</con:comentario>
                        else
                        ()
                     }
                      <con:mimeType>{fn:data($documento/doc:mime_type)}</con:mimeType>
                      <con:fechaRegistro>{fn:data($documento/doc:fechaDocumento)}</con:fechaRegistro>
                      <con:fechaDocumento>{fn:data($documento/doc:fechaRegistro)}</con:fechaDocumento>
                  </con:listadoDocumentos>
                else ()
                
            }
           
           
           {
            for $id in $ConsultarDocumentosFenixResponse/ns3:Documento
             
             let $mostrarUltimo := $ConsultarDocumentosTareaResponse/ns5:ConsultarDocumentoTareaDBOutput[ns5:ID_TCA_DOCUMENTO/text() = $id/doc:idTipoDocumento/text()]/ns5:MOSTRAR_ULTIMO/text()
             let $fechaMaxima := max( for $registro in $ConsultarDocumentosFenixResponse/ns3:Documento[doc:idTipoDocumento/text() = $id/doc:idTipoDocumento/text()] return xs:dateTime($registro/doc:fechaRegistro))
             
             where (count($ConsultarDocumentosTareaResponse/ns5:ConsultarDocumentoTareaDBOutput[ns5:ID_TCA_DOCUMENTO/text() = $id/doc:idTipoDocumento/text()]) > 0 )
              and ($id/doc:mime_type = 'application/pdf' or $id/doc:mime_type = 'application/vnd.openxmlformats-officedocument.wordprocessingml.document' 
                        or $id/doc:mime_type = 'application/msword')
              
            return
            if($mostrarUltimo != 1 or $id/doc:fechaRegistro/text() = $fechaMaxima)
            then
           <con:listadoDocumentos>
                    <con:idDocumento>{fn:data($id/doc:idExterno)}</con:idDocumento>
                    <con:idAdjunto>{fn:data($id/doc:idAdjunto)}</con:idAdjunto>
                    <con:nombreDocumento>{fn:data($id/doc:filename)}</con:nombreDocumento>
                   {
                    if(fn:string($id/doc:esJustificacion)='1')
                      then
                           <con:esJustificacion>{fn:string('true')}</con:esJustificacion>
                      
                    else if (fn:string($id/doc:esJustificacion)='0')
                      then
                     <con:esJustificacion>{fn:string('false')}</con:esJustificacion>
                    else
                    ()
                   }
                   {
                    if(fn:string($id/doc:esJustificacion)='1')
                      then
                           <con:comentario>{fn:data($id/doc:comentario)}</con:comentario>
                      else
                      ()
                   } 
                   <con:mimeType>{fn:data($id/doc:mime_type)}</con:mimeType>
                   <con:fechaRegistro>{fn:data($id/doc:fechaDocumento)}</con:fechaRegistro>
                   <con:fechaDocumento>{fn:data($id/doc:fechaRegistro)}</con:fechaDocumento>
                </con:listadoDocumentos>
                else ()
           }
            
            {
            for $j in $ConsultarDecisionAprobacionByIdAprobacionResponse/ns2:CatalogoAprobacion
            return
            <con:listadoCatalogos>
             <cat:Id>{fn:data($j/cat:Id)}</cat:Id>
             <cat:Descripcion>{fn:data($j/cat:Descripcion)}</cat:Descripcion>
             <cat:DescripcionCorta>{fn:data($j/cat:DescripcionCorta)}</cat:DescripcionCorta>
             <cat:estatus>{fn:data($j/cat:estatus)}</cat:estatus>
             <cat:codigoExterno>{fn:data($j/cat:codigoExterno)}</cat:codigoExterno>
            </con:listadoCatalogos>
            }
        </ns4:detalleOperacion>
        <ns4:Result>
            <res:result>OK</res:result>
            <res:message>Consulta Existosa</res:message>
            <res:error>
                <err:errorCode>{fn:data($ConsultarDocumentosFenixResponse/ns3:Documento/doc:idDocumento)}</err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns4:Result>
    </ns4:responseConsultarOperacionMessage>
};

local:func($ConsultarOperacionResponse, $ConsultarDecisionAprobacionByIdAprobacionResponse, $ConsultaDocumentosResponse,$ConsultarDocumentosFenixResponse,$ConsultarDocumentosTareaResponse)
