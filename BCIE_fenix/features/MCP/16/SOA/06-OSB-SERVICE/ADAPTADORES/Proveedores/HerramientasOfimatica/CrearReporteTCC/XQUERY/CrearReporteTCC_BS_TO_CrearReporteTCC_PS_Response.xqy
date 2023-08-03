xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://service.org.bcie.www/";
(:: import schema at "../XSD/CrearReporteTCC.xsd" ::)
declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $Reporte as element() (:: schema-element(ns1:crearReporteTCCResponse) ::) external;

declare function local:func($Reporte as element() (:: schema-element(ns1:crearReporteTCCResponse) ::)) as element() (:: schema-element(ns2:CrearReporteTCCResposne) ::) {
    <ns2:CrearReporteTCCResposne>
        {
            if ($Reporte/return)
            then <ns2:Reporte>{fn:data($Reporte/return)}</ns2:Reporte>
            else ()
        }        
        {
        if(fn:string-length(xs:string(fn:data($Reporte/return))) > 0)
            then(
                <ns2:Resultado>
                    <res:result>OK</res:result>
                    <res:message>El reporte se ha creado correctamente</res:message>
                </ns2:Resultado>
                )
            else(
                <ns2:Resultado>
                    <res:result>ERROR</res:result>
                    <res:message>El reporte no se ha podido crear</res:message>
                </ns2:Resultado>            
            )
        }        
    </ns2:CrearReporteTCCResposne>
};

local:func($Reporte)
