xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarAdjunto";
(:: import schema at "../XSD/ConsultarAdjunto_sp.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarAdjuntoByIdAdjuntoResponse) ::) {
    <ns2:ConsultarAdjuntoByIdAdjuntoResponse>
    { if (fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_DOCUMENTO'])) then
        <ns2:Documento>
            <doc:idDocumento>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_DOCUMENTO'])}</doc:idDocumento>
            <doc:idTipoDocumentoExterno>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_TIPO_ON_BASE'])}</doc:idTipoDocumentoExterno>
            <doc:nombreTipoDocumento>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='TIPO_DOCUMENTO'])}</doc:nombreTipoDocumento>
            <doc:etapa>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='PROCESO_BPM'])}</doc:etapa>
            <doc:codigoDocumento>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='CODIGO_DOCUMENTO'])}</doc:codigoDocumento>
        
            <doc:idExterno>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_ONBASE'])}</doc:idExterno>
          
            <doc:idOperacion>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_OPERACION'])}</doc:idOperacion>
  
            <doc:filename>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='FILENAME'])}</doc:filename>
            <doc:mime_type>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='MIME_TYPE'])}</doc:mime_type>
                   <doc:idAdjunto>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_ADJUNTO'])}</doc:idAdjunto>
           
            <doc:comentario>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='COMENTARIO'])}</doc:comentario>
            <doc:fechaRegistro>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='FECHA_REGISTRO'])}</doc:fechaRegistro>
            <doc:idtarea>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_TAREA'])}</doc:idtarea>
         
            <doc:content>{replace(fn:normalize-space($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='CONTENIDO']/text()), '\s', '') }</doc:content>
         
            <doc:accionARealizar>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ACCION'])}</doc:accionARealizar>
        </ns2:Documento>
        else ()
        }
        {
          if (fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_DOCUMENTO'])) then
        <ns2:Cliente>
            <cli:idCliente>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_CLIENTE'])}</cli:idCliente>
            <cli:idFacturador>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_FLEXCUBE'])}</cli:idFacturador>
            <cli:razonSocial>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='RAZON_SOCIAL'])}</cli:razonSocial>
            
            <cli:pais>
                <cat:DescripcionCorta>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='PAIS_DESC'])}</cat:DescripcionCorta>
                <cat:codigoExterno>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='PAIS'])}</cat:codigoExterno>
            </cli:pais>
            
        </ns2:Cliente>
        else ()
        }
        { if (fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_DOCUMENTO'])) then
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_OPERACION'])}</ope:idOperacion>

            <ope:nombre>{fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='NOMBRE'])}</ope:nombre>
          
        </ns2:Operacion>
        else()
        }
        { if (fn:data($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_DOCUMENTO'])) then
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
           
        </ns2:Resultado>
        else 
         <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>La consulta no devolvió resultados</res:message>
            <res:error>
                <err:errorCode>-1</err:errorCode>
                <err:errorDescription>Consulta vacía</err:errorDescription>

            </res:error>
        </ns2:Resultado>
        }
        
    </ns2:ConsultarAdjuntoByIdAdjuntoResponse>
};

local:func($OutputParameters)