xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.banesco.com/eopt/AlertEmailAdv_V1.0";
(:: import schema at "../../../../Commons/xsd/EOpt/Utility/AlertEmailAdv/AlertEmailAdv_V1.0.xsd" ::)

declare namespace ale = "http://xmlns.banesco.com/eo/Alert_V1.0";
declare namespace functx = "http://www.functx.com";

declare variable $AlertEmailAdvRq as element() (:: schema-element(ns1:AlertEmailAdvRq) ::) external;

declare function functx:if-absent
  ( $arg as item()* ,
    $value as item()* )  as item()* {
	
    if (exists($arg))
    then $arg
    else $value
 } ;

declare function functx:replace-multi
  ( $arg as xs:string? ,
    $changeFrom as xs:string* ,
    $changeTo as xs:string* )  as xs:string? {
	
   if (count($changeFrom) > 0)
   then functx:replace-multi(
          replace($arg, $changeFrom[1],
                     functx:if-absent($changeTo[1],'')),
          $changeFrom[position() > 1],
          $changeTo[position() > 1])
   else $arg
 } ;


declare function local:func($AlertEmailAdvRq as element() (:: schema-element(ns1:AlertEmailAdvRq) ::)) as xs:string {


    let $mensaje := dvmtr:lookup('Commons/dvm/EmailTemplate', 'CODE', $AlertEmailAdvRq/ns1:Alert/ale:MessageContent/ale:TemplateIdent/text(), 'VALUE', '')
      return

    let $changeFrom := 
      for $var1 in $AlertEmailAdvRq/ns1:Alert/ale:MessageContent/ale:TemplateParameters/ale:Parameter
      return fn:concat('"<', $var1/ale:Key/text(), '>"')

    let $changeTo := 
      for $var1 in $AlertEmailAdvRq/ns1:Alert/ale:MessageContent/ale:TemplateParameters/ale:Parameter
      return $var1/ale:Value/text()

   return functx:replace-multi($mensaje , $changeFrom, $changeTo )


};

local:func($AlertEmailAdvRq)