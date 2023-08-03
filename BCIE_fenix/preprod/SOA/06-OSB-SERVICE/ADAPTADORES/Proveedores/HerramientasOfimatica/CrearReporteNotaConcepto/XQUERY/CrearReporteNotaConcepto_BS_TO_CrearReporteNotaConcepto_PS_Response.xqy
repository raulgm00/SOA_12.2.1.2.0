xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../../CrearReporteZip/XSD/Reporte.xsd" ::)
declare namespace ns2="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cue = "http://xmlns.bcie.com/CuestionarioBO";

declare variable $reporteResponse as element() (:: schema-element(ns1:Reporte) ::) external;

declare function local:func($reporteResponse as element() (:: schema-element(ns1:Reporte) ::)) as element() (:: schema-element(ns2:CrearReporteElegibilidadV2Response) ::) {
    <ns2:CrearReporteElegibilidadV2Response>
        <ns2:Cuestionario>{fn:data($reporteResponse/ns1:Archivo[1]/ns1:content)}</ns2:Cuestionario>
        <ns2:Respuesta>
            <res:result>OK</res:result>
            <res:message>El documento no se ha guardado en OnBase</res:message>
        </ns2:Respuesta>
    </ns2:CrearReporteElegibilidadV2Response>
};

local:func($reporteResponse)
