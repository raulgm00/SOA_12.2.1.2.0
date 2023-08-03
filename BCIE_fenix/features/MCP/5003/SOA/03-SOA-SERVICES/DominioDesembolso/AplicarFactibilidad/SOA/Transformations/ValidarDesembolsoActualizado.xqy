xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace desMO="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace desBO="http://www.bcie.org/DesembolsoBO";
declare namespace comBO="http://www.bcie.org/CommonBO";
declare namespace operBO="http://www.bcie.org/OperacionBO";
declare namespace catBO="http://www.bcie.org/CatalogoBO";

declare variable $AplicarFactibilidad as element() (:: schema-element(desMO:AplicarFactibilidadRequest) ::) external;
declare variable $ConsultarDetalleTransaccion as element() (:: schema-element(desMO:ConsultarDetalleTransaccionResponse) ::) external;

declare function local:ValidaContratoActualizado($AplicarFactibilidad as element() (:: schema-element(desMO:AplicarFactibilidadRequest) ::), 
                                                 $ConsultarDetalleTransaccion as element() (:: schema-element(desMO:ConsultarDetalleTransaccionResponse) ::)) {
   let $transaccionDesembolso := $ConsultarDetalleTransaccion/desMO:TransaccionDesembolso[desBO:plataforma = 'FLEXCUBE' and desBO:operacion = 'ActualizarPrestamo'][1]
   let $tipoTasa := $transaccionDesembolso/desBO:detalleTransaccion/desBO:Parameters[comBO:name = 'TipoTasa']/comBO:value = string($AplicarFactibilidad/desMO:Factibilidad/desBO:tipo/catBO:Id)
   let $TasaRef := if($AplicarFactibilidad/desMO:Factibilidad/desBO:variable/desBO:tasaReferencia/catBO:codigoExterno = '') then '0' else $AplicarFactibilidad/desMO:Factibilidad/desBO:variable/desBO:tasaReferencia/catBO:codigoExterno
   let $tasa := 
      if($AplicarFactibilidad/desMO:Factibilidad/desBO:tipo/catBO:codigoExterno = 'F') then
        if($transaccionDesembolso/desBO:detalleTransaccion/desBO:Parameters[comBO:name = 'CodTasaRef']/comBO:value = $TasaRef and
           $transaccionDesembolso/desBO:detalleTransaccion/desBO:Parameters[comBO:name = 'ValorTasa']/comBO:value  = string($AplicarFactibilidad/desMO:Factibilidad/desBO:variable/desBO:tasaReferencia/desBO:valor) and
           $transaccionDesembolso/desBO:detalleTransaccion/desBO:Parameters[comBO:name = 'Spreed']/comBO:value     = string($AplicarFactibilidad/desMO:Factibilidad/desBO:variable/desBO:spread/desBO:valor)
        ) then 
          true()
        else false()
      else if ($AplicarFactibilidad/desMO:Factibilidad/desBO:tipo/catBO:codigoExterno = 'X') then
        if($transaccionDesembolso/desBO:detalleTransaccion/desBO:Parameters[comBO:name = 'ValorTasa']/comBO:value = string($AplicarFactibilidad/desMO:Factibilidad/desBO:fija/desBO:valor)) then
          true()
        else false()
      else if ($AplicarFactibilidad/desMO:Factibilidad/desBO:tipo/catBO:codigoExterno = 'S') then
        if($transaccionDesembolso/desBO:detalleTransaccion/desBO:Parameters[comBO:name = 'ValorTasa']/comBO:value = string($AplicarFactibilidad/desMO:Factibilidad/desBO:especial/desBO:valor)) then
          true()
        else false()
      else false()
   let $fecha := $transaccionDesembolso/desBO:detalleTransaccion/desBO:Parameters[comBO:name = 'FechaEstudios']/comBO:value = $AplicarFactibilidad/desMO:Factibilidad/operBO:fechaFinalizacionEstudios/text()
   return
     $tipoTasa and $tasa and $fecha
};

local:ValidaContratoActualizado($AplicarFactibilidad, $ConsultarDetalleTransaccion)
