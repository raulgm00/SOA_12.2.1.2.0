xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $GenerarReportePrepagoResponse as element() (:: schema-element(ns2:GenerarReportePrepagoResponse) ::) external;

declare function local:func($GenerarReportePrepagoResponse as element() (:: schema-element(ns2:GenerarReportePrepagoResponse) ::)) as element() (:: schema-element(ns2:ReportePrepagoResponse) ::) {
    <ns2:ReportePrepagoResponse>
        <ns2:Documentos>
            {
                for $Documento in $GenerarReportePrepagoResponse/ns2:Documentos/doc:Documento
                return 
                <doc:Documento>
                    {
                        if ($Documento/doc:idDocumento)
                        then <doc:idDocumento>{fn:data($Documento/doc:idDocumento)}</doc:idDocumento>
                        else ()
                    }
                    {
                        if ($Documento/doc:idTipoDocumento)
                        then <doc:idTipoDocumento>{fn:data($Documento/doc:idTipoDocumento)}</doc:idTipoDocumento>
                        else ()
                    }
                    {
                        if ($Documento/doc:idTipoDocumentoExterno)
                        then <doc:idTipoDocumentoExterno>{fn:data($Documento/doc:idTipoDocumentoExterno)}</doc:idTipoDocumentoExterno>
                        else ()
                    }
                    {
                        if ($Documento/doc:nombreTipoDocumento)
                        then <doc:nombreTipoDocumento>{fn:data($Documento/doc:nombreTipoDocumento)}</doc:nombreTipoDocumento>
                        else ()
                    }
                    {
                        if ($Documento/doc:etapa)
                        then <doc:etapa>{fn:data($Documento/doc:etapa)}</doc:etapa>
                        else ()
                    }
                    {
                        if ($Documento/doc:codigoDocumento)
                        then <doc:codigoDocumento>{fn:data($Documento/doc:codigoDocumento)}</doc:codigoDocumento>
                        else ()
                    }
                    {
                        if ($Documento/doc:idExterno)
                        then <doc:idExterno>{fn:data($Documento/doc:idExterno)}</doc:idExterno>
                        else ()
                    }
                    {
                        if ($Documento/doc:idDocAreaTipo)
                        then <doc:idDocAreaTipo>{fn:data($Documento/doc:idDocAreaTipo)}</doc:idDocAreaTipo>
                        else ()
                    }
                    {
                        if ($Documento/doc:idOperacion)
                        then <doc:idOperacion>{fn:data($Documento/doc:idOperacion)}</doc:idOperacion>
                        else ()
                    }
                    {
                        if ($Documento/doc:idCliente)
                        then <doc:idCliente>{fn:data($Documento/doc:idCliente)}</doc:idCliente>
                        else ()
                    }
                    {
                        if ($Documento/doc:idProducto)
                        then <doc:idProducto>{fn:data($Documento/doc:idProducto)}</doc:idProducto>
                        else ()
                    }
                    {
                        if ($Documento/doc:idPais)
                        then <doc:idPais>{fn:data($Documento/doc:idPais)}</doc:idPais>
                        else ()
                    }
                    {
                        if ($Documento/doc:nombre)
                        then <doc:nombre>{fn:data($Documento/doc:nombre)}</doc:nombre>
                        else ()
                    }
                    {
                        if ($Documento/doc:filename)
                        then <doc:filename>{fn:data($Documento/doc:filename)}</doc:filename>
                        else ()
                    }
                    {
                        if ($Documento/doc:mime_type)
                        then <doc:mime_type>{fn:data($Documento/doc:mime_type)}</doc:mime_type>
                        else ()
                    }
                    {
                        if ($Documento/doc:tamanio)
                        then <doc:tamanio>{fn:data($Documento/doc:tamanio)}</doc:tamanio>
                        else ()
                    }
                    {
                        if ($Documento/doc:idAdjunto)
                        then <doc:idAdjunto>{fn:data($Documento/doc:idAdjunto)}</doc:idAdjunto>
                        else ()
                    }
                    {
                        if ($Documento/doc:esJustificacion)
                        then <doc:esJustificacion>{fn:data($Documento/doc:esJustificacion)}</doc:esJustificacion>
                        else ()
                    }
                    {
                        if ($Documento/doc:comentario)
                        then <doc:comentario>{fn:data($Documento/doc:comentario)}</doc:comentario>
                        else ()
                    }
                    {
                        if ($Documento/doc:fechaDocumento)
                        then <doc:fechaDocumento>{fn:data($Documento/doc:fechaDocumento)}</doc:fechaDocumento>
                        else ()
                    }
                    {
                        if ($Documento/doc:fechaRegistro)
                        then <doc:fechaRegistro>{fn:data($Documento/doc:fechaRegistro)}</doc:fechaRegistro>
                        else ()
                    }
                    {
                        if ($Documento/doc:status)
                        then <doc:status>{fn:data($Documento/doc:status)}</doc:status>
                        else ()
                    }
                    {
                        if ($Documento/doc:estatusTipoDoc)
                        then <doc:estatusTipoDoc>{fn:data($Documento/doc:estatusTipoDoc)}</doc:estatusTipoDoc>
                        else ()
                    }
                    {
                        if ($Documento/doc:codExtTipoDoc)
                        then <doc:codExtTipoDoc>{fn:data($Documento/doc:codExtTipoDoc)}</doc:codExtTipoDoc>
                        else ()
                    }
                    {
                        if ($Documento/doc:tarea)
                        then <doc:tarea>{fn:data($Documento/doc:tarea)}</doc:tarea>
                        else ()
                    }
                    {
                        if ($Documento/doc:idtarea)
                        then <doc:idtarea>{fn:data($Documento/doc:idtarea)}</doc:idtarea>
                        else ()
                    }
                    {
                        if ($Documento/doc:content)
                        then <doc:content>{fn:data($Documento/doc:content)}</doc:content>
                        else ()
                    }
                    {
                        if ($Documento/doc:PuedeModificar)
                        then <doc:PuedeModificar>{fn:data($Documento/doc:PuedeModificar)}</doc:PuedeModificar>
                        else ()
                    }
                    {
                        if ($Documento/doc:PuedeBorrar)
                        then <doc:PuedeBorrar>{fn:data($Documento/doc:PuedeBorrar)}</doc:PuedeBorrar>
                        else ()
                    }
                    {
                        if ($Documento/doc:usuarioAgrega)
                        then <doc:usuarioAgrega>{fn:data($Documento/doc:usuarioAgrega)}</doc:usuarioAgrega>
                        else ()
                    }
                    {
                        if ($Documento/doc:usuarioUltimaActualizacion)
                        then <doc:usuarioUltimaActualizacion>{fn:data($Documento/doc:usuarioUltimaActualizacion)}</doc:usuarioUltimaActualizacion>
                        else ()
                    }
                    {
                        if ($Documento/doc:accionARealizar)
                        then <doc:accionARealizar>{fn:data($Documento/doc:accionARealizar)}</doc:accionARealizar>
                        else ()
                    }
                    {
                        if ($Documento/doc:idFlujoNegocio)
                        then <doc:idFlujoNegocio>{fn:data($Documento/doc:idFlujoNegocio)}</doc:idFlujoNegocio>
                        else ()
                    }
                </doc:Documento>
            }
        </ns2:Documentos>
        <ns2:Resultado>
            <res:result>OK</res:result>
            {if(fn:count($GenerarReportePrepagoResponse/ns2:Documentos/doc:Documento) > 0)then
              <res:message>Documento(s) generado(s) exitosamente</res:message>
            else
              <res:message>Consulta sin resultados</res:message>
            }
        </ns2:Resultado>
    </ns2:ReportePrepagoResponse>
};

local:func($GenerarReportePrepagoResponse)
