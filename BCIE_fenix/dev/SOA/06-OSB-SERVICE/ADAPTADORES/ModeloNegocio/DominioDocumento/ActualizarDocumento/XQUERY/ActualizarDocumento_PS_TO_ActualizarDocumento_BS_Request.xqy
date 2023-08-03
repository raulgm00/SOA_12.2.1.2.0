xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarDocumento";
(:: import schema at "../XSD/ActualizarDocumento_table.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ActualizarDocumentoRequest as element() (:: schema-element(ns1:ActualizarDocumentoRequest) ::) external;

declare function local:func($ActualizarDocumentoRequest as element() (:: schema-element(ns1:ActualizarDocumentoRequest) ::)) as element() (:: schema-element(ns2:DocumentoCollection) ::) {
    <ns2:DocumentoCollection>
        <ns2:Documento>
            <ns2:idDocumento>{fn:data($ActualizarDocumentoRequest/ns1:Documento/doc:idDocumento)}</ns2:idDocumento>
            <ns2:idTipoDocumento></ns2:idTipoDocumento>
            <ns2:comentario></ns2:comentario>
            <ns2:fechaRegistro></ns2:fechaRegistro>
            {
                if ($ActualizarDocumentoRequest/ns1:Documento/doc:estatusTipoDoc)
                then <ns2:banEstatus>{fn:data($ActualizarDocumentoRequest/ns1:Documento/doc:estatusTipoDoc)}</ns2:banEstatus>
                else ()
            }
        
            <ns2:esJustificacion></ns2:esJustificacion>
            <ns2:codigoDocumento></ns2:codigoDocumento>
            <ns2:fechaDocumento></ns2:fechaDocumento>
            <ns2:idTareaBpm></ns2:idTareaBpm>
            {
                if ($ActualizarDocumentoRequest/ns1:Documento/doc:accionARealizar)
                then <ns2:idTcaAccion>{fn:data($ActualizarDocumentoRequest/ns1:Documento/doc:accionARealizar)}</ns2:idTcaAccion>
                else ()
            }
            <ns2:loginUsuarioCrea></ns2:loginUsuarioCrea>
            <ns2:nombreUsuarioCrea></ns2:nombreUsuarioCrea>
            <ns2:loginUsuarioModifica></ns2:loginUsuarioModifica>
            <ns2:nombreUsuarioModifica></ns2:nombreUsuarioModifica>
            {
                if ($ActualizarDocumentoRequest/ns1:Documento/doc:idFlujoNegocio)
                then <ns2:numSerieDocumento>{fn:data($ActualizarDocumentoRequest/ns1:Documento/doc:idFlujoNegocio)}</ns2:numSerieDocumento>
                else ()
            }
        </ns2:Documento>
    </ns2:DocumentoCollection>
};

local:func($ActualizarDocumentoRequest)
