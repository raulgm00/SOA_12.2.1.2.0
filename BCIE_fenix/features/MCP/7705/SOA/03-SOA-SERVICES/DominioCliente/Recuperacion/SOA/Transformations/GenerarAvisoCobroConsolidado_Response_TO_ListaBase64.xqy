xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare namespace functx = "http://www.functx.com";

declare variable $outInvokeGenerarAviso.GenerarAvisoCobroResponse as element() (:: schema-element(ges:GeneraReporteAvisoCobroResponse) ::) external;
declare variable $inputVariable.GenerarArchivosFenixRequest as element() (:: schema-element(ges:GenerarArchivosFenixRequest) ::) external;

declare function functx:month-abbrev-en
  ( $date as xs:anyAtomicType? )  as xs:string? {

   ('ENE', 'FEB', 'MAR', 'ABR', 'MAY', 'JUN',
    'JUL', 'AGO', 'SEP', 'OCT', 'NOV', 'DIC')
    [month-from-date(xs:date($date))]
 } ;

declare function local:funcGeneraravisocobro_response_to_listabase642($outInvokeGenerarAviso.GenerarAvisoCobroResponse as element() (:: schema-element(ges:GeneraReporteAvisoCobroResponse) ::), 
                                                                      $inputVariable.GenerarArchivosFenixRequest as element() (:: schema-element(ges:GenerarArchivosFenixRequest) ::)) 
                                                                      as element() (:: schema-element(doc:CargarDocumentoFenixRequest) ::) {
    <doc:CargarDocumentoFenixRequest>
         <doc:Documentos>

          { 
          for $documento in $outInvokeGenerarAviso.GenerarAvisoCobroResponse/ges:Documento
          return
              <doc1:Documento>
                <doc1:idTipoDocumento>{fn:data($documento/doc1:idTipoDocumento)}</doc1:idTipoDocumento>
                <doc1:filename>{fn:data(concat('AVISO DE COBRO-',$inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/cli:abreviatura,'-', functx:month-abbrev-en(fn:current-date()),'-',year-from-date(fn:current-date()),'.',fn:data($documento/doc1:nombreTipoDocumento)))}</doc1:filename>
                <doc1:mime_type>{fn:data($documento/doc1:mime_type)}</doc1:mime_type>
                <doc1:content>{fn:data($documento/doc1:content)}</doc1:content>
              </doc1:Documento>
            }
        </doc:Documentos>
    </doc:CargarDocumentoFenixRequest>
};

local:funcGeneraravisocobro_response_to_listabase642($outInvokeGenerarAviso.GenerarAvisoCobroResponse, $inputVariable.GenerarArchivosFenixRequest)
