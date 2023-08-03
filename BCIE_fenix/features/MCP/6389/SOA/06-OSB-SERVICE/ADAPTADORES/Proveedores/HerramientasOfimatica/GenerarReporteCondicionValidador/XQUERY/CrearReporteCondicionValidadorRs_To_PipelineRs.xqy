xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://service.org.bcie.www/";
(:: import schema at "../XSD/XMLSchema_558377673.xsd" ::)
declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ReporteValidacionCondicionResponse as element() (:: schema-element(ns1:ReporteValidacionCondicionResponse) ::) external;

declare function tns:func($ReporteValidacionCondicionResponse as element() (:: schema-element(ns1:ReporteValidacionCondicionResponse) ::)) as element() (:: schema-element(ns2:GenerarReporteCondicionValidadorResponse) ::) {
    <ns2:GenerarReporteCondicionValidadorResponse>
        {
            if ($ReporteValidacionCondicionResponse/return)
            then <ns2:Reporte>{fn:data($ReporteValidacionCondicionResponse/return)}</ns2:Reporte>
            else ()
        }
        
        {
            if ($ReporteValidacionCondicionResponse/return)
            then
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Reporte generado exitosamente</res:message> 
        </ns2:Resultado>
        else 
         <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>No se genero el reporte</res:message>
        </ns2:Resultado>
        }
    </ns2:GenerarReporteCondicionValidadorResponse>
};

tns:func($ReporteValidacionCondicionResponse)
