xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace functx = "http://www.functx.com"; 

declare variable $gestionCobro as element() (:: schema-element(ges:GestionCobroV2Request) ::) external;

(: FUNCION PARA OBTENER EL PRIMER DIA DE CUALQUIER MES :)
declare function functx:first-day-of-month( $date as xs:anyAtomicType? )  as xs:date? {
  if(fn:month-from-date($date) < 10)then
    xs:date(fn:concat(fn:year-from-date($date),"-0",fn:month-from-date($date),"-","01"))
  else
    xs:date(fn:concat(fn:year-from-date($date),"-",fn:month-from-date($date),"-","01"))
 } ;
 
 (: FUNCION PARA OBTENER EL ÚLTIMO DIA DE CUALQUIER MES :)
 declare function functx:last-day-of-month( $date as xs:anyAtomicType? )  as xs:date? {
  if(fn:month-from-date($date) < 10)then
   xs:date(fn:concat(year-from-date(xs:date($date)),"-0",month-from-date(xs:date($date)),"-",functx:days-in-month($date)))
  else
  xs:date(fn:concat(year-from-date(xs:date($date)),"-",month-from-date(xs:date($date)),"-",functx:days-in-month($date)))
 } ;
 
 (:FUNCION PARA SABER SI ES AÑO BICIESTO, Y QUE ULTIMO DIA CORRESPONDE DEPENDIENDO EL MES:)
 declare function functx:days-in-month($date as xs:anyAtomicType?)  as xs:integer? {
   if (month-from-date(xs:date($date)) = 2 and functx:is-leap-year($date))
   then 29
   else
   (31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    [month-from-date(xs:date($date))]
 } ;
 
 (:FUNCION PARA DETERMINAR SI ES AÑO BICIESTO :)
 declare function functx:is-leap-year
  ( $date as xs:anyAtomicType? )  as xs:boolean {

    for $year in xs:integer(substring(string($date),1,4))
    return ($year mod 4 = 0 and $year mod 100 != 0) or $year mod 400 = 0
 } ;
 
 declare function functx:add-months
  ( $date as xs:anyAtomicType? ,
    $months as xs:integer )  as xs:date? {

   xs:date($date) + functx:yearMonthDuration(0,$months)
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

declare function local:funcGestioncobrov2_to_crearavisocobrov2($gestionCobro as element() (:: schema-element(ges:GestionCobroV2Request) ::)) as element() (:: schema-element(ges:CrearAvisoCobroV2Request) ::) {
    <ges:CrearAvisoCobroV2Request>
        {
            let $dateTimeString := xs:string(fn:current-dateTime())
            return
            if($gestionCobro/ges:Fecha)then
                <ges:fechaInicio>{functx:first-day-of-month(fn:data($gestionCobro/ges:Fecha))}</ges:fechaInicio>
            else
                <ges:fechaInicio>{functx:first-day-of-month(functx:add-months(fn:substring-before($dateTimeString,'T'),1))}</ges:fechaInicio>
        }
        {
            let $dateTimeString := xs:string(fn:current-dateTime())
            return
            if($gestionCobro/ges:Fecha)then
                <ges:fechaFin>{functx:last-day-of-month(fn:data($gestionCobro/ges:Fecha))}</ges:fechaFin>
            else
                <ges:fechaFin>{functx:last-day-of-month(functx:add-months(fn:substring-before($dateTimeString,'T'),1))}</ges:fechaFin>
        }
        <ges:sectorInstitucional>
        {if(fn:lower-case($gestionCobro/ges:Sector) = 'privado')then 2 else (1)}
        </ges:sectorInstitucional>
    </ges:CrearAvisoCobroV2Request>
};

local:funcGestioncobrov2_to_crearavisocobrov2($gestionCobro)
