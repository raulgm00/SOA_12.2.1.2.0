xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../../CrearReporteZip/XSD/Reporte.xsd" ::)
declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $archivos as element() (:: schema-element(ns1:Reporte) ::) external;
declare variable $GeneraReportePlanAmortizacionRequest as element() (:: schema-element(ns2:GeneraReportePlanAmortizacionRequest) ::) external;

declare function local:func($archivos as element() (:: schema-element(ns1:Reporte) ::), $GeneraReportePlanAmortizacionRequest as element() (:: schema-element(ns2:GeneraReportePlanAmortizacionRequest) ::)) as element() (:: schema-element(ns2:GeneraReportePlanAmortizacionResponse) ::) {
    <ns2:GeneraReportePlanAmortizacionResponse>
        <ns2:Resultado>
            <res:result>
            {
              if(fn:data($archivos/ns1:Resultado))
              then "OK"
             else "ERROR"
            }
            </res:result>
            <res:message>
            {
             if(fn:data($archivos/ns1:Mensaje))
             then "reporte generado"
             else "No se puede generar el reporte"
            }
            </res:message>
        </ns2:Resultado>
        {
         for $Archivo in $archivos/ns1:Archivo
         return 
         <ns2:Documento>
             <doc:idTipoDocumento>1207</doc:idTipoDocumento>
             <doc:nombreTipoDocumento>{fn:data($Archivo/ns1:tipo)}</doc:nombreTipoDocumento>
             <doc:idExterno>1</doc:idExterno>
             {
                 if ($GeneraReportePlanAmortizacionRequest/ns2:idOperacion)
                 then <doc:idOperacion>{fn:data($GeneraReportePlanAmortizacionRequest/ns2:idOperacion)}</doc:idOperacion>
                 else ()
             }
             <doc:nombre>{fn:concat('Plan de Amortizacion Desembolso - ',fn:data($GeneraReportePlanAmortizacionRequest/ns2:idDesembolso))}</doc:nombre>
             <doc:filename>{fn:concat('Plan de Amortizacion Desembolso - ',fn:data($GeneraReportePlanAmortizacionRequest/ns2:idDesembolso),'.',$Archivo/ns1:tipo)}</doc:filename>
             <doc:mime_type>{dvmtr:lookup('MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/DVM/TipoArchivo','mimeFormat',$Archivo/ns1:tipo,'tipoArchivo','')}</doc:mime_type>
             <doc:esJustificacion>0</doc:esJustificacion>
             <doc:comentario>Plan de Amortizacion</doc:comentario>
             <doc:fechaDocumento>{fn:current-date()}</doc:fechaDocumento>
             <doc:fechaRegistro>{fn:current-dateTime()}</doc:fechaRegistro>
             <doc:status>1</doc:status>
             <doc:idtarea>157</doc:idtarea>
             <doc:content>{fn:data($Archivo/ns1:content)}</doc:content>
             <doc:usuarioAgrega>fenix</doc:usuarioAgrega>
         </ns2:Documento>
        }
    </ns2:GeneraReportePlanAmortizacionResponse>
};

local:func($archivos, $GeneraReportePlanAmortizacionRequest)
