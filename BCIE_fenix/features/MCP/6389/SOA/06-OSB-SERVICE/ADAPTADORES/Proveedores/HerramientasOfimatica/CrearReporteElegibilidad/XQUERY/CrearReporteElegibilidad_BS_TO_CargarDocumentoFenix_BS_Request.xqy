xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CargarDocumentoFenix";
(:: import schema at "../../../../ModeloNegocio/DominioDocumento/CargarDocumentoFenix/XSD/CargarDocumentoFenix_sp.xsd" ::)

declare namespace cue = "http://xmlns.bcie.com/CuestionarioBO";

declare variable $inCrearReporte as element() (:: schema-element(ns1:CrearReporteElegibilidadRequest) ::) external;
declare variable $return as xs:base64Binary external;

declare function local:func($inCrearReporte as element() (:: schema-element(ns1:CrearReporteElegibilidadRequest) ::), 
                            $return as xs:base64Binary) 
                            as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_TIPO_DOCUMENTO>1075</ns2:P_ID_TIPO_DOCUMENTO>
        <ns2:P_COMENTARIO>Reporte Elegibilidad</ns2:P_COMENTARIO>
        <ns2:P_FECHA_REGISTRO>{fn:current-date()}</ns2:P_FECHA_REGISTRO>
        <ns2:P_BAN_ESTATUS>1</ns2:P_BAN_ESTATUS>
        <ns2:P_ES_JUSTIFICACION>0</ns2:P_ES_JUSTIFICACION>
        <ns2:P_FECHA_DOCUMENTO>{fn:current-date()}</ns2:P_FECHA_DOCUMENTO>
        <ns2:P_ID_TAREA_BPM>6</ns2:P_ID_TAREA_BPM>
        <ns2:P_ID_TCA_ACCION>1</ns2:P_ID_TCA_ACCION>
        <ns2:P_NOMBRE_USUARIO_CREA>fenix</ns2:P_NOMBRE_USUARIO_CREA>
        <ns2:P_OPERACION>{fn:data($inCrearReporte/ns1:idOperacion)}</ns2:P_OPERACION>
        <ns2:P_FILENAME>{fn:data($inCrearReporte/ns1:NombreArchivo/cue:Archivo)}</ns2:P_FILENAME>
        <ns2:P_MIME_TYPE>PDF</ns2:P_MIME_TYPE>
        <ns2:P_CONTENT>{fn:data($return)}</ns2:P_CONTENT>
    </ns2:InputParameters>
};

local:func($inCrearReporte, $return)
