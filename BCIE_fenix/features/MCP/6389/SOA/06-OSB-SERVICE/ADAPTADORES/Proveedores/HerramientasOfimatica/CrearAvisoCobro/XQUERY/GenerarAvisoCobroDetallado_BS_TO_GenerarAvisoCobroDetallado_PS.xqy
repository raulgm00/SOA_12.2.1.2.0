xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://service.org.bcie.www/";
(:: import schema at "../XSD/GenerarAvisoCobroDetallado.xsd" ::)
declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace rep="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../XSD/Reporte.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";
declare variable $respuesta as xs:string external;
declare variable $generarAvisoDetResponse as element() (:: schema-element(ns1:generarAvisoDetResponse) ::) external;
declare variable $ReporteMessage as element() (:: schema-element(rep:Reporte) ::) external;

declare function local:func($ReporteMessage as element() (:: schema-element(rep:Reporte) ::)) as element() (:: schema-element(ns2:GeneraReporteAvisoCobroResponse) ::) {
    <ns2:GeneraReporteAvisoCobroResponse>
        <ns2:Resultado>
        <res:result>
        {
        if(fn:data($ReporteMessage/rep:Resultado))
        then "OK"
        else "ERROR"
        }
        </res:result>
        <res:message>
         {
        if(fn:data($ReporteMessage/rep:Resultado))
        then "reporte generado"
        else "No se puede generar el reporte"
        }
        </res:message>
        </ns2:Resultado>
       {
         for $Archivo in $ReporteMessage/rep:Archivo
         return 
         <ns2:Documento>
             <doc:idTipoDocumento>
             {
                if(fn:data($respuesta!="Mora"))
                then "1122"
                else "1123"
                }
             </doc:idTipoDocumento>
             <doc:nombreTipoDocumento>{fn:data($Archivo/rep:tipo)}</doc:nombreTipoDocumento>
             <doc:mime_type>{dvmtr:lookup('MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/DVM/TipoArchivo','mimeFormat',$Archivo/rep:tipo,'tipoArchivo','')}</doc:mime_type>
             <doc:content>{fn:data($Archivo/rep:content)}</doc:content>
         </ns2:Documento>
        }
    </ns2:GeneraReporteAvisoCobroResponse>
};

local:func($ReporteMessage)
