xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns4="http://www.bcie.org/ConsultarOperacionMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarDetalleVotacion/V1/Schema/ConsultarOperacionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns5="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDocumentoTareaDB";
(:: import schema at "../../../../ADAPTADORES/ModeloNegocio/DominioDocumento/ConsultarDocumentoTarea/XSD/ConsultarDocumentoTareaDB.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarOperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace sol="http://www.bcie.org/SolicitudAprobacionBO";

declare variable $ConsultaDocumentosResponse as element() (:: schema-element(ns1:ConsultaDocumentosResponse) ::) external;
declare variable $ConsultarDecisionAprobacionByIdAprobacionResponse as element() (:: schema-element(ns2:ConsultarDecisionAprobacionByIdAprobacionResponse) ::) external;
declare variable $ConsultarClienteResponse as element() (:: schema-element(ns3:ConsultarClienteResponse) ::) external;
declare variable $ConsultarSeguimientoCrediticioByIdClienteResponse as element() (:: schema-element(ns3:ConsultarSeguimientoCrediticioByIdClienteResponse) ::) external;
declare variable $ConsultarDocumentosTareaResponse as element() (:: schema-element(ns5:ConsultarDocumentoTareaDBOutputCollection) ::) external;
declare variable $ConsultarVotacionUsuarioResponse as element() (:: schema-element(ns2:ConsultarVotacionUsuarioResponse) ::) external;

declare function local:func($ConsultaDocumentosResponse as element() (:: schema-element(ns1:ConsultaDocumentosResponse) ::), 
                            $ConsultarDecisionAprobacionByIdAprobacionResponse as element() (:: schema-element(ns2:ConsultarDecisionAprobacionByIdAprobacionResponse) ::), 
                            $ConsultarClienteResponse as element() (:: schema-element(ns3:ConsultarClienteResponse) ::), 
                            $ConsultarSeguimientoCrediticioByIdClienteResponse as element() (:: schema-element(ns3:ConsultarSeguimientoCrediticioByIdClienteResponse) ::),
                            $ConsultarDocumentosTareaResponse as element() (:: schema-element(ns5:ConsultarDocumentoTareaDBOutputCollection) ::),
                            $ConsultarVotacionUsuarioResponse as element() (:: schema-element(ns2:ConsultarVotacionUsuarioResponse) ::)) 
                            as element() (:: schema-element(ns4:responseConsultarOperacionMessage) ::) {
    <ns4:responseConsultarOperacionMessage>
        <ns4:detalleOperacion>
            <con:cliente>
                <con:idCliente>{fn:data($ConsultarClienteResponse/ns3:Cliente/cli:idCliente)}</con:idCliente>
                <con:NombreCliente>{fn:data($ConsultarClienteResponse/ns3:Cliente/cli:razonSocial)}</con:NombreCliente>
                <con:idFacturador>{fn:data($ConsultarClienteResponse/ns3:Cliente/cli:idFacturador)}</con:idFacturador>
                <con:idOficina>{fn:data($ConsultarClienteResponse/ns3:Cliente/cli:oficina/cat:Id)}</con:idOficina>
                <con:NombreOficina>{fn:data($ConsultarClienteResponse/ns3:Cliente/cli:oficina/cat:Descripcion)}</con:NombreOficina>
                <con:idScrVigente>{fn:data($ConsultarClienteResponse/ns3:Cliente/cli:SCR/cat:Id)}</con:idScrVigente>
                <con:scrVigente>{fn:data($ConsultarClienteResponse/ns3:Cliente/cli:SCR/cat:DescripcionCorta)}</con:scrVigente>
                <con:idScrRecomendado>{fn:data($ConsultarSeguimientoCrediticioByIdClienteResponse/ns3:SeguimientoCrediticio/cli:scr/cat:Id)}</con:idScrRecomendado>
                <con:scrRecomendado>{fn:data($ConsultarSeguimientoCrediticioByIdClienteResponse/ns3:SeguimientoCrediticio/cli:scr/cat:DescripcionCorta)}</con:scrRecomendado>
                <con:idSector>{fn:data($ConsultarClienteResponse/ns3:Cliente/cli:sector/cat:Id)}</con:idSector>
                <con:nombreSector>{fn:data($ConsultarClienteResponse/ns3:Cliente/cli:sector/cat:DescripcionCorta)}</con:nombreSector>
                <con:Argumentado>{if ($ConsultarSeguimientoCrediticioByIdClienteResponse/ns3:SeguimientoCrediticio/cli:tipoInicio = 'ARGUMENTACION') then '1' else '0'}</con:Argumentado>
            </con:cliente>
            {
            for $i in $ConsultaDocumentosResponse/ns1:Documentos/doc:etapa/doc:tipoDocumento
            where  (count($ConsultarDocumentosTareaResponse/ns5:ConsultarDocumentoTareaDBOutput[ns5:ID_TCA_DOCUMENTO/text() = $i/doc:documento/doc:idTipoDocumento/text()]) > 0)
            return
            
                 let $mostrarUltimo := $ConsultarDocumentosTareaResponse/ns5:ConsultarDocumentoTareaDBOutput[ns5:ID_TCA_DOCUMENTO/text() = $i/doc:documento/doc:idTipoDocumento/text()]/ns5:MOSTRAR_ULTIMO/text()
                 let $fechaMaxima := max( for $registro in $ConsultaDocumentosResponse/ns1:Documentos/doc:etapa/doc:tipoDocumento/doc:documento[doc:idTipoDocumento/text() = $i/doc:documento/doc:idTipoDocumento/text()] return xs:dateTime($registro/doc:fechaRegistro))
            
                 for $documento in $i/doc:documento
                 where $ConsultarVotacionUsuarioResponse/ns2:ListaVotacionUsuario/sol:instanciaProcesoSeguimiento/text() = $documento/doc:idFlujoNegocio/text()
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
                          <con:fechaRegistro>{fn:data($documento/doc:fechaRegistro)}</con:fechaRegistro>
                          <con:fechaDocumento>{fn:data($documento/doc:fechaDocumento)}</con:fechaDocumento>
                      </con:listadoDocumentos>
                      else()
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
        </ns4:Result>
    </ns4:responseConsultarOperacionMessage>
};

local:func($ConsultaDocumentosResponse, $ConsultarDecisionAprobacionByIdAprobacionResponse, $ConsultarClienteResponse, $ConsultarSeguimientoCrediticioByIdClienteResponse,$ConsultarDocumentosTareaResponse, $ConsultarVotacionUsuarioResponse)
