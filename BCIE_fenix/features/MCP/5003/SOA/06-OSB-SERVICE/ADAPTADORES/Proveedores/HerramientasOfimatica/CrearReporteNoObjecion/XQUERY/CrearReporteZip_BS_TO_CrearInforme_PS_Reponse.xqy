xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/herramientaOfismatica/Reporte";
(:: import schema at "../../CrearReporteZip/XSD/Reporte.xsd" ::)
 
declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare variable $generarReporteNoObjecionesResponse as element() (:: schema-element(ns1:Reporte) ::) external;

declare variable $CrearReporteNoObjecionRequest as element() (:: schema-element(ns2:CrearReporteNoObjecionRequest) ::) external;

declare function local:func($generarReporteNoObjecionesResponse as element() (:: schema-element(ns1:Reporte) ::),
$CrearReporteNoObjecionRequest as element() (:: schema-element(ns2:CrearReporteNoObjecionRequest) ::)) as element() (:: schema-element(ns2:CrearReporteNoObjecionResponse) ::) {
    <ns2:CrearReporteNoObjecionResponse>
     {
         for $Archivo in $generarReporteNoObjecionesResponse/ns1:Archivo
         return 
        <ns2:Documento>
            <doc:filename>{upper-case(fn:data($CrearReporteNoObjecionRequest/ns2:Adquisicion/adq:noObjecion/adq:tipoNoObjecion/cat:Descripcion))}</doc:filename>
             <doc:mime_type>{dvmtr:lookup('MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/DVM/TipoArchivo','mimeFormat',$Archivo/ns1:tipo,'tipoArchivo','')}</doc:mime_type>
             <doc:content>{fn:data($Archivo/ns1:content)}</doc:content>
        </ns2:Documento>
        }
        <ns2:Resultado>
            <res:result>
              {
              if(fn:data($generarReporteNoObjecionesResponse/ns1:Resultado))
                then "OK"
                else "ERROR"
              }
            </res:result>
            <res:message>
              {
              if(fn:data($generarReporteNoObjecionesResponse/ns1:Resultado))
                then "reporte generado"
                else "No se puede generar el reporte"
              }
            </res:message>
        </ns2:Resultado>
    </ns2:CrearReporteNoObjecionResponse>
};

local:func($generarReporteNoObjecionesResponse, $CrearReporteNoObjecionRequest)