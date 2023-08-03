xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace obt="http://xmlns.oracle.com/ObtenerDisponibilidadRecursos/ObtenerDisponibilidadRecursos_DecisionService_1";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/BusinessRules/ObtenerDisponibilidadRecursos/V1/Wsdl/ObtenerDisponibilidadRecursos_DecisionService.wsdl" ::)

declare namespace functx = "http://www.functx.com";

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace www = "http://www.bcie.org";

declare variable $outInvokeConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $outObtenerDisponibilidadRecursos.payload as element() (:: schema-element(obt:callFunctionStatelessDecision) ::) external;

declare function local:funcXqcrearactualizardesembolsorequest($outInvokeConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::), 
                                                              $outObtenerDisponibilidadRecursos.payload as element() (:: schema-element(obt:callFunctionStatelessDecision) ::)) 
                                                              as element() (:: schema-element(des:CrearActualizarDesembolsosRequest) ::) {
    <des:CrearActualizarDesembolsosRequest>
        <des:ContratoDesembolso>
            <des1:idDesembolso>{fn:data($outInvokeConsultarDesembolsoById.response/des:ContratoDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            <des1:fechaEstimadaDisponibilidad>{xs:date(fn:substring(xs:string(functx:add-period(fn:data($outInvokeConsultarDesembolsoById.response/des:ContratoDesembolso/des1:fechaInicioProceso),xs:integer(fn:data($outObtenerDisponibilidadRecursos.payload/obt:resultList/www:ObtenerDisponibilidadRecursosResponse/www:dias)),fn:data('D'))),0,11))}</des1:fechaEstimadaDisponibilidad>
        </des:ContratoDesembolso>
    </des:CrearActualizarDesembolsosRequest>
};
declare function functx:add-period
  ( $date as xs:anyAtomicType? ,
    $value as xs:integer? ,
    $value-tipe as xs:string? )  as xs:dateTime? {
    if ($value-tipe = 'Y')
    then functx:add-months(xs:dateTime($date),xs:integer(12 * $value))
    else 
      if ($value-tipe = 'M')
      then functx:add-months(xs:dateTime($date),$value)
      else functx:add-day(xs:dateTime($date),$value)
 } ;
 
declare function functx:add-months
  ( $date as xs:anyAtomicType? ,
    $months as xs:integer )  as xs:dateTime? {
   xs:dateTime($date) + functx:yearMonthDuration(0,$months)
 } ;
 
 declare function functx:yearMonthDuration
  ( $years as xs:decimal? ,
    $months as xs:integer? )  as xs:yearMonthDuration {

    (xs:yearMonthDuration('P1M') * functx:if-empty($months,0)) +
    (xs:yearMonthDuration('P1Y') * functx:if-empty($years,0))
 } ;
 declare function functx:if-empty
  ( $arg as item()? ,
    $value as item()* )  as item()* {

  if (string($arg) != '')
  then data($arg)
  else $value
 } ;
 declare function functx:add-day
 ( $date as xs:anyAtomicType? ,
  $num-of-days as xs:integer) as xs:dateTime? {
  xs:dateTime($date) + xs:dayTimeDuration(string(concat('P', $num-of-days, 'D')))
 };
 
local:funcXqcrearactualizardesembolsorequest($outInvokeConsultarDesembolsoById.response, $outObtenerDisponibilidadRecursos.payload)
