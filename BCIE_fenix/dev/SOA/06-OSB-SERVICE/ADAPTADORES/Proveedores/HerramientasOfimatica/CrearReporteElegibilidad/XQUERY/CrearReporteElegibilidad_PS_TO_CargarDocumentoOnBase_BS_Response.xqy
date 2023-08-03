xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://service.org.bcie.www/";
(:: import schema at "../XSD/ReporteElegibilidad.xsd" ::)
declare namespace ns2="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ReporteResponse as element() (:: schema-element(ns1:ReporteResponse) ::) external;

declare function local:func($ReporteResponse as element() (:: schema-element(ns1:ReporteResponse) ::)) as element() (:: schema-element(ns2:CrearReporteElegibilidadResponse) ::) {
    <ns2:CrearReporteElegibilidadResponse>
        <ns2:Cuestionario>{fn:data($ReporteResponse/return)}</ns2:Cuestionario>
        <ns2:Respuesta>
            <res:result>OK</res:result>
            <res:message>El reporte se guardo en OnBase</res:message>
        </ns2:Respuesta>
    </ns2:CrearReporteElegibilidadResponse>
};

local:func($ReporteResponse)
