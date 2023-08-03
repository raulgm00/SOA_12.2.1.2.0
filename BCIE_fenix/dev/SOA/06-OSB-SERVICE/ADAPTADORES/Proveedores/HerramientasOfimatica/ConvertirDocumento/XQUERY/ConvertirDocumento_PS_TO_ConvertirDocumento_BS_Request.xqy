xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/ConvertirDocumento.xsd" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ConvertirDocumentoRequest as element() (:: schema-element(ns1:ConvertirDocumentoRequest) ::) external;

declare function local:func($ConvertirDocumentoRequest as element() (:: schema-element(ns1:ConvertirDocumentoRequest) ::)) as element() (:: schema-element(ns2:generarAvisoDetallado) ::) {
    <ns2:generarAvisoDetallado>
        <tipoOrigen>
        {(:Formato en el que viene el documento --- ejemplo "application/vnd.openxmlformats-officedocument.wordprocessingml.document" return "docx":)
          dvmtr:lookup('MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/DVM/TipoArchivo','tipoArchivo',$ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:mime_type,'mimeFormat','')
        }
        </tipoOrigen>
        <tipoDestino>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:nombreTipoDocumento)}</tipoDestino>
        {
            if ($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:content)
            then <base64>{fn:data($ConvertirDocumentoRequest/ns1:TipoDocumento/doc:documento/doc:content)}</base64>
            else ()
        }
    </ns2:generarAvisoDetallado>
};

local:func($ConvertirDocumentoRequest)
