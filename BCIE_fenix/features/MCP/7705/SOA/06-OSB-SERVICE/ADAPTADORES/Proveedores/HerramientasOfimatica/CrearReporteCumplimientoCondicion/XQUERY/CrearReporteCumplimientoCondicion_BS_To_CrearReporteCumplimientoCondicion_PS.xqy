xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../XSD/Reporte.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $Reporte as element() (:: schema-element(ns1:Reporte) ::) external;

declare function local:func($Reporte as element() (:: schema-element(ns1:Reporte) ::)) as element() (:: schema-element(ns2:CrearReporteCumplimientoCondicionResponse) ::) {
    <ns2:CrearReporteCumplimientoCondicionResponse>
        {
             for $Archivo in $Reporte/ns1:Archivo
             return 
             <ns2:Documento>
                 <doc:idTipoDocumento>1121</doc:idTipoDocumento>
                 <doc:nombreTipoDocumento>{fn:data($Archivo/ns1:tipo)}</doc:nombreTipoDocumento>
                 <doc:mime_type>{dvmtr:lookup('MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/DVM/TipoArchivo','mimeFormat',$Archivo/ns1:tipo,'tipoArchivo','')}</doc:mime_type>
                 <doc:content>{fn:data($Archivo/ns1:content)}</doc:content>
             </ns2:Documento>
         }
        <ns2:Resultado>
            <res:result>
            {
              if(fn:data($Reporte/ns1:Resultado))
              then "OK"
              else "ERROR"
            }
            </res:result>
            <res:message>
            {
              if(fn:data($Reporte/ns1:Resultado))
              then "Reporte generado"
              else "No se puede generar el reporte"
            }
            </res:message>
        </ns2:Resultado>
    </ns2:CrearReporteCumplimientoCondicionResponse>
};

local:func($Reporte)
