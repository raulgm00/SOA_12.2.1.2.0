xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolesCondicion";
(:: import schema at "../../../../Utilidades/ConsultarRolesCondicion/XSD/ConsultarRolesCondicion.xsd" ::)

declare namespace ns11="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $outConsultarTransaccionCondicion as element() (:: schema-element(ns11:ConsultarTransaccionCondicionResponse) ::) external;

declare variable $ConsultarRolesCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarRolesCondicionOutputCollection) ::) external;
declare variable $CrearReporteCondicionResponse as element() (:: schema-element(ns11:CrearReporteCondicionResponse) ::) external;

declare function local:func($ConsultarRolesCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarRolesCondicionOutputCollection) ::), 
                            $CrearReporteCondicionResponse as element() (:: schema-element(ns11:CrearReporteCondicionResponse) ::), 
                            $outConsultarTransaccionCondicion as element() (:: schema-element(ns11:ConsultarTransaccionCondicionResponse) ::))
                            as element() (:: schema-element(ns2:enviarCorreoElectronicoRequest) ::) {
    <ns2:enviarCorreoElectronicoRequest>
    <ns2:CorreoElectronico>
     <cor:to>
        {
            for $ConsultarRolesCondicionOutput in $ConsultarRolesCondicionOutputCollection/ns1:ConsultarRolesCondicionOutput
                return <cor:usuario>{fn:data($ConsultarRolesCondicionOutput/ns1:USUARIO)}</cor:usuario>
        }
        </cor:to>
          {
            if(count($ConsultarRolesCondicionOutputCollection/ns1:ConsultarRolesCondicionOutput) > 0)
                then <cor:idOperacion>{fn:data($ConsultarRolesCondicionOutputCollection/ns1:ConsultarRolesCondicionOutput[1]/ns1:ID_OPERACION)}</cor:idOperacion>
            else()
         }
        <cor:id_plantilla>36</cor:id_plantilla>
           
        <cor:attachment>
              {
              if(fn:string($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso)='' and 
                  fn:string($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso)!='') then
              
                  <cor:name>{concat("ReporteCondicion_Sol_",fn:data($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso),'_',local:formatFecha(string(fn:day-from-date(fn:current-date()))),'-',local:formatFecha(string(fn:month-from-date(fn:current-date()))),'-',local:formatFecha(string(fn:year-from-date(fn:current-date()))),".pdf")}</cor:name>
                  
              else if (fn:string($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso)!='' and
                        fn:string($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso)='')  then
                        
                  <cor:name>{concat("ReporteCondicion_Con_",fn:data($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso),"_",local:formatFecha(string(fn:day-from-date(fn:current-date()))),'-',local:formatFecha(string(fn:month-from-date(fn:current-date()))),'-',local:formatFecha(string(fn:year-from-date(fn:current-date()))),".pdf")}</cor:name>
              
              else if (fn:string($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso)!='' and
                        fn:string($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso)!='')  then
              
                  <cor:name>{concat("ReporteCondicion_Sol_",fn:data($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:Solicitud/des:idDesembolso),"_Con_",fn:data($outConsultarTransaccionCondicion/ns11:TransaccionCondicion[1]/con:ContratoDesembolso/des:idDesembolso),"_",local:formatFecha(string(fn:day-from-date(fn:current-date()))),'-',local:formatFecha(string(fn:month-from-date(fn:current-date()))),'-',local:formatFecha(string(fn:year-from-date(fn:current-date()))),".pdf")}</cor:name>
              
              else  
              
                  <cor:name>{concat("ReporteCondicion_",local:formatFecha(string(fn:day-from-date(fn:current-date()))),'-',local:formatFecha(string(fn:month-from-date(fn:current-date()))),'-',local:formatFecha(string(fn:year-from-date(fn:current-date()))),".pdf")}</cor:name>
              }
              
              <cor:type>application/pdf</cor:type>
              <cor:content>{fn:data($CrearReporteCondicionResponse/ns11:Reporte)}</cor:content>
        </cor:attachment>  
    </ns2:CorreoElectronico>
    
    </ns2:enviarCorreoElectronicoRequest>
};

declare function local:formatFecha ($valor as xs:string) as xs:string {
  
  if (fn:string-length($valor)= 1) then 
  concat(0,$valor)
  else if(fn:string-length($valor)= 4)then
  substring($valor ,3,4)
  else
  $valor
};

local:func($ConsultarRolesCondicionOutputCollection, $CrearReporteCondicionResponse,$outConsultarTransaccionCondicion)
