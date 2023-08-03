xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace functx = "http://www.functx.com";

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare variable $outInvokeGenerarAvisoCobroMoraV2.GenerarReporteAvisoCobroV2Response as element() (:: schema-element(ges:GeneraReporteAvisoCobroV2Response) ::) external;
declare variable $inputVariable.GenerarArchivosFenixV2Request as element() (:: schema-element(ges:GenerarArchivosFenixV2Request) ::) external;
declare variable $ListaBase64.request as element() (:: schema-element(doc:CargarDocumentoFenixRequest) ::) external;

declare function functx:month-abbrev-en
  ( $date as xs:anyAtomicType? )  as xs:string? {

   ('ENE', 'FEB', 'MAR', 'ABR', 'MAY', 'JUN',
    'JUL', 'AGO', 'SEP', 'OCT', 'NOV', 'DIC')
    [month-from-date(xs:date($date))]
 } ;

declare function local:funcGeneraravisocobromorav2_to_listabase64($outInvokeGenerarAvisoCobroMoraV2.GenerarReporteAvisoCobroV2Response as element() (:: schema-element(ges:GeneraReporteAvisoCobroV2Response) ::), 
                                                                  $inputVariable.GenerarArchivosFenixV2Request as element() (:: schema-element(ges:GenerarArchivosFenixV2Request) ::), 
                                                                  $ListaBase64.request as element() (:: schema-element(doc:CargarDocumentoFenixRequest) ::)) 
                                                                  as element() (:: schema-element(doc:CargarDocumentoFenixRequest) ::) {
    <doc:CargarDocumentoFenixRequest>
        <doc:Documentos>
        {
            for $docto in $ListaBase64.request/doc:Documentos/doc1:Documento
            return
                <doc1:Documento>
                    <doc1:idTipoDocumento>{fn:data($docto/doc1:idTipoDocumento)}</doc1:idTipoDocumento>
                    <doc1:filename>{fn:data($docto/doc1:filename)}</doc1:filename>
                    <doc1:mime_type>{fn:data($docto/doc1:mime_type)}</doc1:mime_type>
                    <doc1:content>{fn:data($docto/doc1:content)}</doc1:content>
                </doc1:Documento>
        }
        {
            for $doctoMora in $outInvokeGenerarAvisoCobroMoraV2.GenerarReporteAvisoCobroV2Response/ges:Documento
            where $doctoMora/doc1:nombreTipoDocumento eq $doctoMora/doc1:nombreTipoDocumento
            return
                <doc1:Documento>
                    <doc1:idTipoDocumento>{fn:data($doctoMora/doc1:idTipoDocumento)}</doc1:idTipoDocumento>
                     {
                        let $fechaInicio := $inputVariable.GenerarArchivosFenixV2Request/ges:AvisoCobro/ges1:aviso/ges1:xml/CLIENTES/PARAMETROS/FECHA_CIERRE_INICIO 
                        let $fechaFin := $inputVariable.GenerarArchivosFenixV2Request/ges:AvisoCobro/ges1:aviso/ges1:xml/CLIENTES/PARAMETROS/FECHA_CIERRE_FIN
                        let $anio := fn:year-from-date($fechaFin)
                        let $mesInicio := functx:month-abbrev-en($fechaInicio)
                        let $mesFin := functx:month-abbrev-en($fechaFin)
                        let $operacionSeleccionada := $inputVariable.GenerarArchivosFenixV2Request/ges:AvisoCobro/ges1:aviso/ges1:xml/CLIENTES/PARAMETROS/OPERACION_SELECCIONADA
                        let $textoMes := if ($mesInicio != $mesFin) then ( concat($mesInicio,'-',$mesFin,'-',$anio) ) else (concat($mesFin,'-',$anio))
                        let $textoMes := if ($operacionSeleccionada) then ( concat($operacionSeleccionada,'-',$textoMes) ) else ($textoMes)
                        
                        return <doc1:filename>{fn:data(concat('SALDOS EN MORA-',$inputVariable.GenerarArchivosFenixV2Request/ges:Cliente/cli:abreviatura,'-', $textoMes ,'.',fn:data($doctoMora/doc1:nombreTipoDocumento)))}</doc1:filename>
                    }
                    <doc1:mime_type>{fn:data($doctoMora/doc1:mime_type)}</doc1:mime_type>
                    <doc1:content>{fn:data($doctoMora/doc1:content)}</doc1:content>
                </doc1:Documento>
        }   
        </doc:Documentos>
    </doc:CargarDocumentoFenixRequest>
};

local:funcGeneraravisocobromorav2_to_listabase64($outInvokeGenerarAvisoCobroMoraV2.GenerarReporteAvisoCobroV2Response, $inputVariable.GenerarArchivosFenixV2Request, $ListaBase64.request)
