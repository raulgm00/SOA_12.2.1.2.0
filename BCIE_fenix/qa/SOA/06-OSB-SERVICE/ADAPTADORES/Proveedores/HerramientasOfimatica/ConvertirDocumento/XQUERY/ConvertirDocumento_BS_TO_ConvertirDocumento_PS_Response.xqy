xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/ConvertirDocumento.xsd" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $generarAvisoDetalladoResponse as element() (:: schema-element(ns2:generarAvisoDetalladoResponse) ::) external;
declare variable $ConvertirDocumentoRequest as element() (:: schema-element(ns1:ConvertirDocumentoRequest) ::) external;

declare function local:func($generarAvisoDetalladoResponse as element() (:: schema-element(ns2:generarAvisoDetalladoResponse) ::), 
                            $ConvertirDocumentoRequest as element() (:: schema-element(ns1:ConvertirDocumentoRequest) ::)) 
                            as element() (:: schema-element(ns1:ConvertirDocumentoResponse) ::) {
    <ns1:ConvertirDocumentoResponse>
        <ns1:Documento>
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idDocumento)
                then <doc:idDocumento>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idDocumento)}</doc:idDocumento>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idTipoDocumento)
                then <doc:idTipoDocumento>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idTipoDocumento)}</doc:idTipoDocumento>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idTipoDocumentoExterno)
                then <doc:idTipoDocumentoExterno>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idTipoDocumentoExterno)}</doc:idTipoDocumentoExterno>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:nombreTipoDocumento)
                then <doc:nombreTipoDocumento>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:nombreTipoDocumento)}</doc:nombreTipoDocumento>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:etapa)
                then <doc:etapa>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:etapa)}</doc:etapa>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:codigoDocumento)
                then <doc:codigoDocumento>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:codigoDocumento)}</doc:codigoDocumento>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idExterno)
                then <doc:idExterno>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idExterno)}</doc:idExterno>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idDocAreaTipo)
                then <doc:idDocAreaTipo>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idDocAreaTipo)}</doc:idDocAreaTipo>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idOperacion)
                then <doc:idOperacion>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idOperacion)}</doc:idOperacion>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idCliente)
                then <doc:idCliente>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idCliente)}</doc:idCliente>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idProducto)
                then <doc:idProducto>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idProducto)}</doc:idProducto>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idPais)
                then <doc:idPais>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idPais)}</doc:idPais>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:nombre)
                then <doc:nombre>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:nombre)}</doc:nombre>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:filename)
                then <doc:filename>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:filename)}</doc:filename>
                else ()
            }
            <doc:mime_type>
            {
            dvmtr:lookup('MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/DVM/TipoArchivo','mimeFormat',$ConvertirDocumentoRequest/ns1:TipoDocumento/doc:nombreTipoDocumento,'tipoArchivo','')
            }
            </doc:mime_type>
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:tamanio)
                then <doc:tamanio>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:tamanio)}</doc:tamanio>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idAdjunto)
                then <doc:idAdjunto>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idAdjunto)}</doc:idAdjunto>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:esJustificacion)
                then <doc:esJustificacion>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:esJustificacion)}</doc:esJustificacion>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:comentario)
                then <doc:comentario>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:comentario)}</doc:comentario>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:fechaDocumento)
                then <doc:fechaDocumento>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:fechaDocumento)}</doc:fechaDocumento>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:fechaRegistro)
                then <doc:fechaRegistro>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:fechaRegistro)}</doc:fechaRegistro>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:status)
                then <doc:status>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:status)}</doc:status>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:estatusTipoDoc)
                then <doc:estatusTipoDoc>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:estatusTipoDoc)}</doc:estatusTipoDoc>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:codExtTipoDoc)
                then <doc:codExtTipoDoc>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:codExtTipoDoc)}</doc:codExtTipoDoc>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:tarea)
                then <doc:tarea>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:tarea)}</doc:tarea>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idtarea)
                then <doc:idtarea>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idtarea)}</doc:idtarea>
                else ()
            }
            {
                if ($generarAvisoDetalladoResponse/return)
                then <doc:content>{fn:data($generarAvisoDetalladoResponse/return)}</doc:content>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:PuedeModificar)
                then <doc:PuedeModificar>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:PuedeModificar)}</doc:PuedeModificar>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:PuedeBorrar)
                then <doc:PuedeBorrar>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:PuedeBorrar)}</doc:PuedeBorrar>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:usuarioAgrega)
                then <doc:usuarioAgrega>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:usuarioAgrega)}</doc:usuarioAgrega>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:usuarioUltimaActualizacion)
                then <doc:usuarioUltimaActualizacion>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:usuarioUltimaActualizacion)}</doc:usuarioUltimaActualizacion>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:accionARealizar)
                then <doc:accionARealizar>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:accionARealizar)}</doc:accionARealizar>
                else ()
            }
            {
                if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idFlujoNegocio)
                then <doc:idFlujoNegocio>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:idFlujoNegocio)}</doc:idFlujoNegocio>
                else ()
            }
        </ns1:Documento>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Documento Convertido</res:message>
        </ns1:Resultado>
    </ns1:ConvertirDocumentoResponse>
};

local:func($generarAvisoDetalladoResponse, $ConvertirDocumentoRequest)
