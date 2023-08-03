xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $CrearReporteCondicionResponse as element() (:: schema-element(ns1:CrearReporteCondicionResponse) ::) external;

declare function local:func($CrearReporteCondicionResponse as element() (:: schema-element(ns1:CrearReporteCondicionResponse) ::)) as element() (:: schema-element(ns1:GenerarReporteCondicionValidadorResponse) ::) {
    <ns1:GenerarReporteCondicionValidadorResponse>
        {
            if ($CrearReporteCondicionResponse/ns1:Reporte)
            then <ns1:Reporte>{fn:data($CrearReporteCondicionResponse/ns1:Reporte)}</ns1:Reporte>
            else ()
        }
        <ns1:Resultado>
            <res:result>
            {
            if ($CrearReporteCondicionResponse/ns1:Reporte)
            then 'OK'
            else 'ERROR'
            }
            </res:result>
            <res:message></res:message>
        </ns1:Resultado>
    </ns1:GenerarReporteCondicionValidadorResponse>
};

local:func($CrearReporteCondicionResponse)
