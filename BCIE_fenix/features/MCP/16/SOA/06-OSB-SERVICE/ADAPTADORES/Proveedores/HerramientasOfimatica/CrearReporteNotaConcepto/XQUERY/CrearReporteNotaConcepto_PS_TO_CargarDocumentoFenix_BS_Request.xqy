xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../../CrearReporteZip/XSD/Reporte.xsd" ::)
declare namespace ns1="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CargarDocumentoFenix";
(:: import schema at "../../../../ModeloNegocio/DominioDocumento/CargarDocumentoFenix/XSD/CargarDocumentoFenix_sp.xsd" ::)

declare namespace cue = "http://xmlns.bcie.com/CuestionarioBO";

declare variable $inCrearReporte as element() (:: schema-element(ns1:CrearReporteElegibilidadV2Request) ::) external;
declare variable $reporteGenerado as element() (:: schema-element(ns2:Reporte) ::) external;

declare function local:func($inCrearReporte as element() (:: schema-element(ns1:CrearReporteElegibilidadV2Request) ::), 
                            $reporteGenerado as element() (:: schema-element(ns2:Reporte) ::)) 
                            as element() (:: schema-element(ns3:InputParameters) ::) {
    <ns3:InputParameters>
        <ns3:P_ID_TIPO_DOCUMENTO>1075</ns3:P_ID_TIPO_DOCUMENTO>
        <ns3:P_COMENTARIO>Reporte Elegibilidad</ns3:P_COMENTARIO>
        <ns3:P_FECHA_REGISTRO>{fn:current-date()}</ns3:P_FECHA_REGISTRO>
        <ns3:P_BAN_ESTATUS>1</ns3:P_BAN_ESTATUS>
        <ns3:P_ES_JUSTIFICACION>0</ns3:P_ES_JUSTIFICACION>
        <ns3:P_FECHA_DOCUMENTO>{fn:current-date()}</ns3:P_FECHA_DOCUMENTO>
        <ns3:P_ID_TAREA_BPM>6</ns3:P_ID_TAREA_BPM>
        <ns3:P_ID_TCA_ACCION>1</ns3:P_ID_TCA_ACCION>
        <ns3:P_NOMBRE_USUARIO_CREA>fenix</ns3:P_NOMBRE_USUARIO_CREA>
        <ns3:P_OPERACION>{fn:data($inCrearReporte/ns1:idOperacion)}</ns3:P_OPERACION>
        <ns3:P_FILENAME>{fn:data($inCrearReporte/ns1:NombreArchivo/cue:Archivo)}</ns3:P_FILENAME>
        <ns3:P_MIME_TYPE>PDF</ns3:P_MIME_TYPE>
        <ns3:P_CONTENT>{fn:data($reporteGenerado/ns2:Archivo[1]/ns2:content)}</ns3:P_CONTENT>
    </ns3:InputParameters>
};

local:func($inCrearReporte, $reporteGenerado)
