xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace conMO="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace docMO="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $NotificarValidacionCondicionRequest as element() (:: schema-element(conMO:NotificarValidacionCondicionRequest) ::) external;
declare variable $CrearReporteCondicionResponse as element() (:: schema-element(conMO:CrearReporteCondicionResponse) ::) external;
declare variable $outConsultarTransaccionCondicion as element() (:: schema-element(conMO:ConsultarTransaccionCondicionResponse) ::) external;

declare function local:func($NotificarValidacionCondicionRequest as element() (:: schema-element(conMO:NotificarValidacionCondicionRequest) ::), 
                            $CrearReporteCondicionResponse as element() (:: schema-element(conMO:CrearReporteCondicionResponse) ::),
                            $outConsultarTransaccionCondicion as element() (:: schema-element(conMO:ConsultarTransaccionCondicionResponse) ::)) 
                            as element() (:: schema-element(docMO:CargarDocumentoFenixRequest) ::) {
    <docMO:CargarDocumentoFenixRequest>
        <docMO:Documentos>
            <doc:Documento>
              {
              if(fn:string($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:EventoCondicion) = '')
                then
                <doc:idTipoDocumento>1181</doc:idTipoDocumento>
              else if(fn:string($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:EventoCondicion) = '2' or fn:string($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:EventoCondicion) = '3')
                then
                <doc:idTipoDocumento>1182</doc:idTipoDocumento>
              else
                (<doc:idTipoDocumento>1068</doc:idTipoDocumento>)
              }
                <doc:idExterno>1</doc:idExterno>
                <doc:idOperacion>{$NotificarValidacionCondicionRequest/conMO:idOperacion/text()}</doc:idOperacion>
                {
                if(fn:string($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso)='' and 
                  fn:string($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso)!='') then
                  
                    <doc:filename>{fn:concat('ReporteCondiciones_Sol_',fn:data($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso),'_',fn:substring( local:formatDate( fn:substring(fn:string(fn:current-date()),1,10)),1,8))}</doc:filename>
                
                else if (fn:string($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso)!='' and
                        fn:string($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso)='')  then
                        
                    <doc:filename>{fn:concat('ReporteCondiciones_Con_',fn:data($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso),'_',fn:substring( local:formatDate( fn:substring(fn:string(fn:current-date()),1,10)),1,8))}</doc:filename>
                
                else if (fn:string($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso)!='' and
                        fn:string($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso)!='')  then
                  
                    <doc:filename>{fn:concat('ReporteCondiciones_Sol',fn:data($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso),'_Con_',fn:data($outConsultarTransaccionCondicion/conMO:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso),'_',fn:substring( local:formatDate( fn:substring(fn:string(fn:current-date()),1,10)),1,8))}</doc:filename>
                
                else
                    
                    <doc:filename>{fn:concat('ReporteCondiciones_',fn:substring( local:formatDate( fn:substring(fn:string(fn:current-date()),1,10)),1,8))}</doc:filename>
                }
                <doc:mime_type>PDF</doc:mime_type>
                <doc:esJustificacion>0</doc:esJustificacion>
                <doc:comentario>Reporte condiciones</doc:comentario>
                <doc:fechaDocumento>{fn:current-date()}</doc:fechaDocumento>
                <doc:fechaRegistro>{fn:current-dateTime()}</doc:fechaRegistro>
                <doc:status>1</doc:status>
                <doc:idtarea>69</doc:idtarea>
                {
                if($CrearReporteCondicionResponse/conMO:Reporte) then 
                <doc:content>{data($CrearReporteCondicionResponse/conMO:Reporte)}</doc:content>
                else ()
                }
                <doc:usuarioAgrega>fenix</doc:usuarioAgrega>
            </doc:Documento>
        </docMO:Documentos>
    </docMO:CargarDocumentoFenixRequest>
};

declare function local:formatDate($date as xs:string)
as xs:string {
  if( fn:contains(fn:substring($date,6,8),'10/') or fn:contains(fn:substring($date,6,8),'10-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-10-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,8),'11/') or fn:contains(fn:substring($date,6,8),'11-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-11-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,8),'12/') or fn:contains(fn:substring($date,6,8),'12-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-12-',fn:substring(fn:string($date),3,4))
else if( fn:contains(fn:substring($date,6,7),'1/') or fn:contains(fn:substring($date,6,7),'1-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-01-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,7),'2/') or fn:contains(fn:substring($date,6,7),'2-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-02-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,7),'3/') or fn:contains(fn:substring($date,6,7),'3-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-03-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,7),'4/') or fn:contains(fn:substring($date,6,7),'4-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-04-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,7),'5/') or fn:contains(fn:substring($date,6,7),'5-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-05-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,7),'6/') or fn:contains(fn:substring($date,6,7),'6-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-06-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,7),'7/') or fn:contains(fn:substring($date,6,7),'7-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-07-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,7),'8/') or fn:contains(fn:substring($date,6,7),'8-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-08-',fn:substring(fn:string($date),3,4))
 else if( fn:contains(fn:substring($date,6,7),'9/') or fn:contains(fn:substring($date,6,7),'9-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'-09-',fn:substring(fn:string($date),3,4))
 else 
 ''     
};
local:func($NotificarValidacionCondicionRequest, $CrearReporteCondicionResponse,$outConsultarTransaccionCondicion)