xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../../../CrearReporteZip/XSD/Reporte.xsd" ::)
declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $archivos as element() (:: schema-element(ns1:Reporte) ::) external;
declare variable $tipoDocumento as xs:string external;

declare function local:func($archivos as element() (:: schema-element(ns1:Reporte) ::), $tipoDocumento as xs:string) as element() (:: schema-element(ns2:GeneraReporteAvisoCobroV2Response) ::) {
    <ns2:GeneraReporteAvisoCobroV2Response>
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
         
             <doc:idTipoDocumento>
              {
                if(fn:data($tipoDocumento!="MORA"))
                then "1122"
                else "1123"
              }
             </doc:idTipoDocumento>
             <doc:nombreTipoDocumento>{fn:data($Archivo/ns1:tipo)}</doc:nombreTipoDocumento>
             <doc:mime_type>{dvmtr:lookup('MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/DVM/TipoArchivo','mimeFormat',$Archivo/ns1:tipo,'tipoArchivo','')}</doc:mime_type>
             <doc:content>{fn:data($Archivo/ns1:content)}</doc:content>
         </ns2:Documento>
        }
    </ns2:GeneraReporteAvisoCobroV2Response>
};

local:func($archivos,$tipoDocumento)
