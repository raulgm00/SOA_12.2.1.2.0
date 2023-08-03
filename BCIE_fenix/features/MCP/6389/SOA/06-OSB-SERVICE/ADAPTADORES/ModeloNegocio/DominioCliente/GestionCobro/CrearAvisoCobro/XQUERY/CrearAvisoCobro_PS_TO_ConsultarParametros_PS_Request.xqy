xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace functx = "http://www.functx.com";
declare variable $CrearAvisoCobroRequest as element() (:: schema-element(ns1:CrearAvisoCobroRequest) ::) external;

declare function local:func($CrearAvisoCobroRequest as element() (:: schema-element(ns1:CrearAvisoCobroRequest) ::)) as element() (:: schema-element(ns1:ConsultarParametrosAvisoCobroRequest) ::) {
    <ns1:ConsultarParametrosAvisoCobroRequest>
        <ns1:Sector>{fn:data($CrearAvisoCobroRequest/ns1:Sector)}</ns1:Sector>
        {
        let $dateTimeString := xs:string(fn:current-dateTime())
        return
        if($CrearAvisoCobroRequest/ns1:Fecha)then
        <ns1:Fecha>{fn:data($CrearAvisoCobroRequest/ns1:Fecha)}</ns1:Fecha>
        else
        <ns1:Fecha>{functx:add-months(fn:substring-before($dateTimeString,'T'),1)}</ns1:Fecha>
        }
    </ns1:ConsultarParametrosAvisoCobroRequest>
};
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
local:func($CrearAvisoCobroRequest)
