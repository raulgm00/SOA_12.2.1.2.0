xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarHoraCierreMoneda";
(:: import schema at "../XSD/ConsultarHoraCierreMoneda.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace functx = "http://www.functx.com";

declare variable $resultRestaHoras := '';
declare variable $horas := '';
declare variable $minutos := '';
declare variable $segundos := '';
declare variable $ConsultarHoraCierreMonedaOutputCollection as element() (:: schema-element(ns1:ConsultarHoraCierreMonedaOutputCollection) ::) external;
declare variable $ValidarHoraCierreRequest as element() (:: schema-element(ns2:ValidarHoraCierreRequest) ::) external;
declare variable $mensaje := '';

declare function functx:timezone-from-duration
  ( $duration as xs:dayTimeDuration )  as xs:string {

   if (string($duration) = ('PT0S','-PT0S'))
   then 'Z'
   else if (matches(string($duration),'-PT[1-9]H'))
   then replace(string($duration),'PT([1-9])H','0$1:00')
   else if (matches(string($duration),'PT[1-9]H'))
   then replace(string($duration),'PT([1-9])H','+0$1:00')
   else if (matches(string($duration),'-PT1[0-4]H'))
   then replace(string($duration),'PT(1[0-4])H','$1:00')
   else if (matches(string($duration),'PT1[0-4]H'))
   then replace(string($duration),'PT(1[0-4])H','+$1:00')
   else  ''
 } ;

declare function local:func($ConsultarHoraCierreMonedaOutputCollection as element() (:: schema-element(ns1:ConsultarHoraCierreMonedaOutputCollection) ::), 
                            $ValidarHoraCierreRequest as element() (:: schema-element(ns2:ValidarHoraCierreRequest) ::)) 
                            as element() (:: schema-element(ns2:ValidarHoraCierreResponse) ::) {
    <ns2:ValidarHoraCierreResponse>
        <ns2:Regla>
            <reg:Transaccion>{fn:data($ValidarHoraCierreRequest/ns2:Regla/reg:Transaccion)}</reg:Transaccion>
            <reg:Estado>
              {if(fn:count($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput) > 0)then (:Valida si hay resultados en la consulta, si no, es NO_VALIDADA:)
                  if($ValidarHoraCierreRequest/ns2:Regla/reg:Transaccion = 'PROXIMA_HORA_CIERRE_MONEDA')then (:Valida si es PROXIMA_HORA_CIERRE_MONEDA:)
                    <cat:DescripcionCorta>{
                      let $resultRestaHoras := xs:time($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput/ns1:HORA_CIERRE) - xs:time('01:30:00')(:Resta de 1.5 hrs a la hora de cierre de la moneda:)
                      let $horas := fn:string(fn:hours-from-duration(xs:dayTimeDuration($resultRestaHoras)))
                      let $minutos := fn:string(fn:minutes-from-duration(xs:dayTimeDuration($resultRestaHoras)))
                      let $segundos := fn:string(fn:seconds-from-duration(xs:dayTimeDuration($resultRestaHoras)))
                      let $timeZone := fn:concat("",functx:timezone-from-duration(xs:dayTimeDuration($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput/ns1:UTC)))
                      return
                        if(fn:adjust-time-to-timezone(fn:current-time(),xs:dayTimeDuration($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput/ns1:UTC)) 
                          < (:Evaluacion de la hora local con la hora de cierre de la moneda:)
                          xs:time(fn:concat(
                            if(fn:string-length($horas) = 1)then fn:concat('0',$horas,':') else fn:concat($horas,':'),(:Si las horas son de un solo digito, se le concatena un 0:)
                            if(fn:string-length($minutos) = 1) then fn:concat('0',$minutos,':') else fn:concat($minutos,':'),(:Si los minutos son de un solo digito, se le concatena un 0:)
                            if(fn:string-length($segundos) = 1) then fn:concat('0',$segundos) else $segundos,(:Si los segundos son de un solo digito, se le concatena un 0:)
                            $timeZone (:Se añade la zona horaria de la hora de referencia, para evitar problemas con la validacion al ser diferentes:)
                          )))then 'CUMPLIDA'
                        else 'NO_CUMPLIDA'  
                    }</cat:DescripcionCorta>
                  else 
                    if($ValidarHoraCierreRequest/ns2:Regla/reg:Transaccion = 'PREVIA_HORA_CIERRE_MONEDA')then (:Valida si es PREVIA_HORA_CIERRE_MONEDA:)
                        if(fn:adjust-time-to-timezone(fn:current-time(),xs:dayTimeDuration($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput/ns1:UTC)) 
                        < xs:time(fn:concat($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput/ns1:HORA_CIERRE,
                                            functx:timezone-from-duration(xs:dayTimeDuration($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput/ns1:UTC))
                                            )
                                  )  
                        )then (:Valida que la hora actual del sistema con la zona horaria de la moneda, sea previa a la hora de cierre de banca de la moneda:)
                          <cat:DescripcionCorta>CUMPLIDA</cat:DescripcionCorta>
                        else
                          <cat:DescripcionCorta>NO_CUMPLIDA</cat:DescripcionCorta>
                    else
                      let $mensaje := fn:concat($mensaje,'Transaccion incorrecta')
                      return
                      <cat:DescripcionCorta>NO_EVALUADA</cat:DescripcionCorta>
               else
                let $mensaje := fn:concat($mensaje,'Consultar HoraCierre sin resultados')
                return
                <cat:DescripcionCorta>NO_EVALUADA</cat:DescripcionCorta>
              }
            </reg:Estado>
            <reg:DetalleRegla>
                <atr:id>{fn:data($ValidarHoraCierreRequest/ns2:Regla/reg:DetalleRegla/atr:id)}</atr:id>
            </reg:DetalleRegla>
        </ns2:Regla>
        <ns2:Resultado>
            <res:result>OK</res:result>
            {if(fn:count($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput) > 0 )then
              <res:message>{fn:concat('Operacion exitosa. Hora local: ',fn:string(fn:current-time()),
              ' Hora moneda: ',fn:string(fn:adjust-time-to-timezone(fn:current-time(),xs:dayTimeDuration($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput/ns1:UTC)))
              ,' Hora cierre moneda: ',fn:concat($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput/ns1:HORA_CIERRE,
                                            functx:timezone-from-duration(xs:dayTimeDuration($ConsultarHoraCierreMonedaOutputCollection/ns1:ConsultarHoraCierreMonedaOutput/ns1:UTC)))
              )}</res:message>
            else
              <res:message>{fn:concat('Operacion sin resultados. ',$mensaje)}</res:message>
            }  
        </ns2:Resultado>
    </ns2:ValidarHoraCierreResponse>
};

local:func($ConsultarHoraCierreMonedaOutputCollection, $ValidarHoraCierreRequest)