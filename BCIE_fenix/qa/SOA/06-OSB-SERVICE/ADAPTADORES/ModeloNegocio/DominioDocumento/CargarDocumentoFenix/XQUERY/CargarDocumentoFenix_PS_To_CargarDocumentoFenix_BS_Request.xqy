xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CargarDocumentoFenix";
(:: import schema at "../XSD/CargarDocumentoFenix_sp.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $CargarDocumentoFenixRequest as element() (:: schema-element(ns1:CargarDocumentoFenixRequest) ::) external;

declare function local:func($CargarDocumentoFenixRequest as element() (:: schema-element(ns1:CargarDocumentoFenixRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idTipoDocumento)
            then <ns2:P_ID_TIPO_DOCUMENTO>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idTipoDocumento)}</ns2:P_ID_TIPO_DOCUMENTO>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:comentario)
            then <ns2:P_COMENTARIO>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:comentario)}</ns2:P_COMENTARIO>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro)
            then <ns2:P_FECHA_REGISTRO>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:fechaRegistro)}</ns2:P_FECHA_REGISTRO>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:status)
            then <ns2:P_BAN_ESTATUS>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:status)}</ns2:P_BAN_ESTATUS>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:esJustificacion)
            then <ns2:P_ES_JUSTIFICACION>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:esJustificacion)}</ns2:P_ES_JUSTIFICACION>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:codigoDocumento)
            then <ns2:P_CODIGO_DOCUMENTO>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:codigoDocumento)}</ns2:P_CODIGO_DOCUMENTO>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:fechaDocumento)
            then <ns2:P_FECHA_DOCUMENTO>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:fechaDocumento)}</ns2:P_FECHA_DOCUMENTO>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idtarea)
            then <ns2:P_ID_TAREA_BPM>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idtarea)}</ns2:P_ID_TAREA_BPM>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idExterno)
            then <ns2:P_ID_TCA_ACCION>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idExterno)}</ns2:P_ID_TCA_ACCION>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:usuarioAgrega)
            then <ns2:P_LOGIN_USUARIO_CREA>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:usuarioAgrega)}</ns2:P_LOGIN_USUARIO_CREA>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:usuarioAgrega)
            then <ns2:P_NOMBRE_USUARIO_CREA>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:usuarioAgrega)}</ns2:P_NOMBRE_USUARIO_CREA>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:usuarioUltimaActualizacion)
            then <ns2:P_LOGIN_USUARIO_MODIFICA>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:usuarioUltimaActualizacion)}</ns2:P_LOGIN_USUARIO_MODIFICA>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:usuarioUltimaActualizacion)
            then <ns2:P_NOMBRE_USUARIO_MODIFICA>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:usuarioUltimaActualizacion)}</ns2:P_NOMBRE_USUARIO_MODIFICA>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idFlujoNegocio)
            then <ns2:P_NUM_SERIE_DOCUMENTO>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idFlujoNegocio)}</ns2:P_NUM_SERIE_DOCUMENTO>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idOperacion)
            then <ns2:P_OPERACION>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idOperacion)}</ns2:P_OPERACION>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idCliente)
            then <ns2:P_CLIENTE>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idCliente)}</ns2:P_CLIENTE>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:filename)
            then <ns2:P_FILENAME>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:filename)}</ns2:P_FILENAME>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:mime_type)
            then <ns2:P_MIME_TYPE>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:mime_type)}</ns2:P_MIME_TYPE>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:content)
            then <ns2:P_CONTENT>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:content)}</ns2:P_CONTENT>
            else ()
        }
        {
            if ($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idTipoDocumentoExterno)
            then <ns2:P_ID_ONBASE>{fn:data($CargarDocumentoFenixRequest/ns1:Documentos/doc:Documento/doc:idTipoDocumentoExterno)}</ns2:P_ID_ONBASE>
            else ()
        }
      
    </ns2:InputParameters>
};

local:func($CargarDocumentoFenixRequest)