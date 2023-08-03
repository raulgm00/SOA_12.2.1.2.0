xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $CrearReporteCondicionResponse as element() (:: schema-element(ns1:CrearReporteCondicionResponse) ::) external;

declare function local:func($CrearReporteCondicionResponse as element() (:: schema-element(ns1:CrearReporteCondicionResponse) ::)) as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionResponse) ::) {
    <ns1:ConsultarCumplimientoCondicionResponse>
        <ns1:Documento>
            <doc:idTipoDocumento>1121</doc:idTipoDocumento>
            <doc:nombreTipoDocumento>pdf</doc:nombreTipoDocumento>
            <doc:mime_type>application/pdf</doc:mime_type>
            {
                if ($CrearReporteCondicionResponse/ns1:Reporte)
                then <doc:content>{fn:data($CrearReporteCondicionResponse/ns1:Reporte)}</doc:content>
                else ()
            }
        </ns1:Documento>
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
    </ns1:ConsultarCumplimientoCondicionResponse>
};

local:func($CrearReporteCondicionResponse)
