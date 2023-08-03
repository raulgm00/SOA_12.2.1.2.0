xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace conBO=  "http://www.bcie.org/CondicionBO";
declare namespace desBO="http://www.bcie.org/DesembolsoBO";
declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outValidarCondicionesDesembolso.response as element() (:: schema-element(con:ValidarCondicionesDesembolsoResponse) ::) external;
declare variable $outConsultarCondicionesByOperacion.response as element() (:: schema-element(con:ConsultarCondicionByIdOperacionResponse) ::) external;
declare variable $outConsultarCumplimientoCondiciones.response as element() (:: schema-element(des:ConsultarCumplimientoCondicionesResponse) ::) external;
declare variable $consultarSolicitudDesembolso_Output.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(des:ValidarCondicionesDesembolsoRequest) ::) external;


declare function local:funcXq_validaregla($outValidarCondicionesDesembolso.response as element() (:: schema-element(con:ValidarCondicionesDesembolsoResponse) ::), 
                                          $outConsultarCondicionesByOperacion.response as element() (:: schema-element(con:ConsultarCondicionByIdOperacionResponse) ::), 
                                          $outConsultarCumplimientoCondiciones.response as element() (:: schema-element(des:ConsultarCumplimientoCondicionesResponse) ::), 
                                          $consultarSolicitudDesembolso_Output.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::),
                                          $inputVariable.request as element() (:: schema-element(des:ValidarCondicionesDesembolsoRequest) ::)) 
                                          as element() (:: schema-element(des:ValidarCondicionesDesembolsoResponse) ::) {
          
      let $condiciones1cumplimiento := $outConsultarCondicionesByOperacion.response/con:ListaCondiciones/conBO:Condicion[conBO:controlCondicion/cat:Id = 1 and
                                                                                    (conBO:eventoCondicion/cat:Id = 1 or conBO:eventoCondicion/cat:Id = 2) and
                                                                                     (conBO:estadoTCC/atr:id = 15 or conBO:estadoTCC/atr:id = 14) and conBO:banEstatus = 1]
      let $condiconesculaquierdesembolso := $outConsultarCondicionesByOperacion.response/con:ListaCondiciones/conBO:Condicion[conBO:controlCondicion/cat:Id = 1 and
                                                                                    (conBO:eventoCondicion/cat:Id = 3) and
                                                                                    (conBO:estadoTCC/atr:id = 15 or conBO:estadoTCC/atr:id = 14) and conBO:banEstatus = 1]
      let $band := true()                                                                               
      let $respuesta1cumplimiento := if(count($condiciones1cumplimiento) > 0) then 
                                        let $band:= $band and (
                                          count($outConsultarCumplimientoCondiciones.response/des:Condicion[conBO:idCondicion = $condiciones1cumplimiento/conBO:idCondicion]/conBO:Cumplimientos[conBO:EstadoTCC/cat:Id = 26]) > 0
                                        )
                                        return $band
                                      else true()
      let $cumplimientoSolicitud := if(count($condiconesculaquierdesembolso[conBO:tipoDesembolso/cat:Id = 1]) > 0 ) then
                                        let $band := $band and (
                                          count($outConsultarCumplimientoCondiciones.response/des:Condicion[conBO:idCondicion = $condiconesculaquierdesembolso[conBO:tipoDesembolso/cat:Id = 1]
                                          /conBO:idCondicion]/conBO:Cumplimientos[conBO:Solicitud/desBO:idDesembolso = $consultarSolicitudDesembolso_Output.response/des:SolicitudDesembolso/desBO:idDesembolso and
                                                                                  conBO:EstadoTCC/cat:Id = 26]) > 0
                                        )
                                        return $band
                                      else true()
      let $cumplimientoDesembolsoCondicion := 
                              for $condicion in $condiconesculaquierdesembolso[conBO:tipoDesembolso/cat:Id != 1]
                                  let $band := (
                                    count($outConsultarCumplimientoCondiciones.response/des:Condicion[conBO:idCondicion = $condicion
                                    /conBO:idCondicion]/conBO:Cumplimientos[conBO:ContratoDesembolso/desBO:idDesembolso = $inputVariable.request/des:idDesembolso and
                                                                            conBO:EstadoTCC/cat:Id = 26]) > 0
                                  )
                                  return <condicion><id>{data($condicion/conBO:idCondicion)}</id><estado>{$band}</estado></condicion>
      let $regla2 := $respuesta1cumplimiento and  $cumplimientoSolicitud and  count($cumplimientoDesembolsoCondicion[estado = false()]) = 0  
      let $regla := ($outValidarCondicionesDesembolso.response/con:ValidarCondicionDesembolso) = 0                             
                                
        return                               
    <des:ValidarCondicionesDesembolsoResponse>
        <des:Regla>
            <reg:Id></reg:Id>
            <reg:Estado>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>
                {if($regla = true()) then
                  "CUMPLIDA"
                else
                  "NO_CUMPLIDA"
                }
                </cat:DescripcionCorta>
            </reg:Estado>
        </des:Regla>
        
        <des:Resultado>
            <res:result>OK</res:result>
            <res:message>
            {if($regla = true()) then
                  "Todas las condiciones para este desembolso estan cumplidas"
                else
                  "Faltan condiciones por cumplir"
                }
            </res:message>
        </des:Resultado>
    </des:ValidarCondicionesDesembolsoResponse>
    
};

local:funcXq_validaregla($outValidarCondicionesDesembolso.response,$outConsultarCondicionesByOperacion.response, $outConsultarCumplimientoCondiciones.response, $consultarSolicitudDesembolso_Output.response, $inputVariable.request)
