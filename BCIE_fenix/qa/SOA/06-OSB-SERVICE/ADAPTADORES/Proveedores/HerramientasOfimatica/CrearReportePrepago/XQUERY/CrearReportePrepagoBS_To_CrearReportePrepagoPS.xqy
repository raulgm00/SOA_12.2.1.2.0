xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://service.org.bcie.www/";
(:: import schema at "../XSD/ReportePrepago.xsd" ::)
declare namespace ns2="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace rep="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../XSD/Reporte.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace pre = "http://www.bcie.org/herramientaOfismatica/prepago";

declare namespace pre1 = "http://www.bcie.org/PrepagoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare variable $ReporteMessage as element() (:: schema-element(rep:Reporte) ::) external;
declare variable $GenerarReportePrepagoRequest as element() (:: schema-element(ns2:GenerarReportePrepagoRequest) ::) external;

declare function local:func($ReporteMessage as element() (:: schema-element(rep:Reporte) ::), $GenerarReportePrepagoRequest as element() (:: schema-element(ns2:GenerarReportePrepagoRequest) ::)) as element() (:: schema-element(ns2:GenerarReportePrepagoResponse) ::) {
    <ns2:GenerarReportePrepagoResponse>
    {
    let $fecha := concat(fn:day-from-dateTime(fn:current-dateTime()),"-",fn:month-from-dateTime(fn:current-dateTime()),"-",fn:year-from-dateTime(fn:current-dateTime()))
    let $filename := concat(fn:data($GenerarReportePrepagoRequest/ns2:Prepago/pre1:Cliente/cli:idFacturador),fn:data($GenerarReportePrepagoRequest/ns2:Prepago/pre1:Cliente/cli:razonSocial),"_",$GenerarReportePrepagoRequest/ns2:Prepago/pre1:Operacion/ope:idOperacion/text(),fn:data($GenerarReportePrepagoRequest/ns2:Prepago/pre1:Operacion/ope:nombre) ) 
    return
    <ns2:Documentos>
        {
         for $Archivo at $count in $ReporteMessage/rep:Archivo
         return 
         <doc:Documento>
             <doc:idTipoDocumento>
        {
        if($count = 1)
        then  1117
        else  1116
          }</doc:idTipoDocumento>
             <doc:filename>
             {
             if($count = 1)
              then  concat("AVISO INTERNO",$fecha,$filename)
              else  concat($fecha,$filename)
             }</doc:filename>
             <doc:mime_type>{dvmtr:lookup('MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/DVM/TipoArchivo','mimeFormat',$Archivo/rep:tipo,'tipoArchivo','')}</doc:mime_type>
             <doc:content>{fn:data($Archivo/rep:content)}</doc:content>
         </doc:Documento>
     }
    </ns2:Documentos>
    }
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
    </ns2:GenerarReportePrepagoResponse>
};

local:func($ReporteMessage,$GenerarReportePrepagoRequest)
