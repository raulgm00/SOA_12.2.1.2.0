xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarDocumentosFenix";
(:: import schema at "../XSD/ConsultarDocumentosFenix_db.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarDocumentosFenixResponse) ::) {
    <ns2:ConsultarDocumentosFenixResponse>
      {for $documento in $OutputParameters/ns1:RECORDSET/ns1:Row
        return
        <ns2:Documento>
            <doc:idDocumento>{fn:data($documento/ns1:Column[@name='ID_DOCUMENTO'])}</doc:idDocumento>
            <doc:idTipoDocumento>{fn:data($documento/ns1:Column[@name='ID_TIPO_DOCUMENTO'])}</doc:idTipoDocumento>
            <doc:codigoDocumento>{fn:data($documento/ns1:Column[@name='CODIGO_DOCUMENTO'])}</doc:codigoDocumento>
            <doc:idExterno>{fn:data($documento/ns1:Column[@name='ID_ONBASE'])}</doc:idExterno>
            <doc:idOperacion>{fn:data($documento/ns1:Column[@name='ID_OPERACION'])}</doc:idOperacion>
            <doc:idCliente>{fn:data($documento/ns1:Column[@name='ID_CLIENTE'])}</doc:idCliente>
            <doc:filename>{fn:data($documento/ns1:Column[@name='FILENAME'])}</doc:filename>
            <doc:mime_type>{fn:data($documento/ns1:Column[@name='MIME_TYPE'])}</doc:mime_type>
            <doc:idAdjunto>{fn:data($documento/ns1:Column[@name='ID_ADJUNTO'])}</doc:idAdjunto>
            <doc:esJustificacion>{fn:data($documento/ns1:Column[@name='ES_JUSTIFICACION'])}</doc:esJustificacion>
            <doc:comentario>{fn:data($documento/ns1:Column[@name='COMENTARIO'])}</doc:comentario>
            <doc:fechaDocumento>{fn:data($documento/ns1:Column[@name='FECHA_DOCUMENTO'])}</doc:fechaDocumento>
            <doc:fechaRegistro>{fn:data($documento/ns1:Column[@name='FECHA_REGISTRO'])}</doc:fechaRegistro>
            <doc:status>{fn:data($documento/ns1:Column[@name='BAN_ESTATUS'])}</doc:status>
            <doc:idtarea>{fn:data($documento/ns1:Column[@name='ID_TAREA_BPM'])}</doc:idtarea>
            <doc:content>{if(fn:count($documento/ns1:Column[@name='CONTENT']) > 0)then
            fn:data($documento/ns1:Column[@name='CONTENT'])
            else()}</doc:content>
            <doc:usuarioAgrega>{fn:data($documento/ns1:Column[@name='NOMBRE_USUARIO_CREA'])}</doc:usuarioAgrega>
            <doc:usuarioUltimaActualizacion>{fn:data($documento/ns1:Column[@name='NOMBRE_USUARIO_MODIFICA'])}</doc:usuarioUltimaActualizacion>
            <doc:idFlujoNegocio>{fn:data($documento/ns1:Column[@name='NUM_SERIE_DOCUMENTO'])}</doc:idFlujoNegocio>
        </ns2:Documento>
     }
      <ns2:Resultado>
          <res:result>OK</res:result>
          {if(fn:count($OutputParameters/ns1:RECORDSET/ns1:Row) > 0)then
            <res:message>Consulta Exitosa</res:message>
          else
            <res:message>Consulta sin resultados</res:message>
          }
      </ns2:Resultado>   
    </ns2:ConsultarDocumentosFenixResponse>
};

local:func($OutputParameters)
