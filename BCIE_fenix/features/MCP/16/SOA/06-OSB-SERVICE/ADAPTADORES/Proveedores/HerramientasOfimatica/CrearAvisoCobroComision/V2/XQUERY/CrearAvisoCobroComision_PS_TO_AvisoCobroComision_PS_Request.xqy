xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/AvisoCobroComisionBO";
(:: import schema at "../XSD/AvisoCobroComisionBO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCobroComisionById";
(:: import schema at "../XSD/ConsultarCobroComisionById.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarConfiguracionCobroComision";
(:: import schema at "../XSD/ConsultarConfiguracionCobroComision.xsd" ::)

declare variable $CobroComision as element() (:: schema-element(ns1:ConsultarCobroComisionByIdOutputCollection) ::) external;
declare variable $Configuracion as element() (:: schema-element(ns2:ConsultarConfiguracionCobroComisionOutputCollection) ::) external;

declare function local:func($CobroComision as element() (:: schema-element(ns1:ConsultarCobroComisionByIdOutputCollection) ::), 
                            $Configuracion as element() (:: schema-element(ns2:ConsultarConfiguracionCobroComisionOutputCollection) ::)) 
                            as element() (:: schema-element(ns3:AvisoCobroComision) ::) {
    
    <ns3:AvisoCobroComision>
        <ns3:NombreOperacion>{fn:data($CobroComision/ns1:ConsultarCobroComisionByIdOutput/ns1:NOMBRE_OPERACION)}</ns3:NombreOperacion>
        <ns3:NombreComision>{fn:data($CobroComision/ns1:ConsultarCobroComisionByIdOutput/ns1:NOMBRE_COMISION)}</ns3:NombreComision>
        <ns3:MontoPagar>{local:formatNumber(fn:data($CobroComision/ns1:ConsultarCobroComisionByIdOutput/ns1:MONTO_PAGAR))}</ns3:MontoPagar>
        {
            let $fechaVencimiento := fn:data($CobroComision/ns1:ConsultarCobroComisionByIdOutput/ns1:FECHA_VENCIMIENTO)
            let $hora24 := fn:hours-from-dateTime($fechaVencimiento)
            let $hora := if ($hora24 > 12 or $hora24 = 0) then fn:abs($hora24 - 12) else $hora24
            let $hora := fn-bea:pad-left(xs:string($hora),2,"0")
            let $minutos := fn-bea:pad-left(xs:string(fn:minutes-from-dateTime($fechaVencimiento)),2,"0")
            let $denominacion := if ($hora24 < 12 ) then "AM" else "PM"
            return <ns3:FechaVencimiento>{fn:concat(fn:day-from-dateTime($fechaVencimiento),
                                                   "/",
                                                   fn:month-from-dateTime($fechaVencimiento),
                                                   "/",
                                                   fn:substring(string(fn:year-from-dateTime($fechaVencimiento)),3),
                                                   " ",
                                                   $hora,
                                                   ":",
                                                   $minutos,
                                                   " ",
                                                   $denominacion
                                          )}</ns3:FechaVencimiento>
        }
        <ns3:NombreCliente>{fn:data($CobroComision/ns1:ConsultarCobroComisionByIdOutput/ns1:NOMBRE_CLIENTE)}</ns3:NombreCliente>
        <ns3:IdCliente>{fn:data($CobroComision/ns1:ConsultarCobroComisionByIdOutput/ns1:ID_CLIENTE)}</ns3:IdCliente>
        <ns3:ConfiguracionCollection>
        {
            for $valor in $Configuracion/ns2:ConsultarConfiguracionCobroComisionOutput
            return
            <ns3:Configuracion>
                <ns3:Llave>{fn:data($valor/ns2:LLAVE)}</ns3:Llave>
                <ns3:Valor>{fn:data($valor/ns2:VALOR)}</ns3:Valor>
            </ns3:Configuracion>
        }
        </ns3:ConfiguracionCollection>
    </ns3:AvisoCobroComision>
};

declare function local:formatNumber($number as xs:double)
as xs:string {
if($number = 0)then '0.00'
else
   fn-bea:format-number(xs:double($number),"###,###.00")
};
declare function local:formatReportDate($fecha as xs:string, $tipoFecha as xs:string)
as xs:string {
let $año := substring-before($fecha , '-')
let $añoResiduo := substring-after($fecha , '-')
let $mes := substring-before($añoResiduo , '-')
let $mesResiduo := substring-after($añoResiduo , '-')
let $dia := if(fn:string-length($mesResiduo)>3) then substring-before($mesResiduo , '-') else $mesResiduo
let $formatoFecha := ''

return if($tipoFecha='1')then concat($dia,local:monthFormat($mes,$tipoFecha),$año) else concat($dia,'-',local:monthFormat($mes,$tipoFecha),'-',substring($año,3,2))
};
declare function local:monthFormat($mes as xs:string, $tipoFecha as xs:string)
as xs:string {
if ($tipoFecha = '1')then
if($mes='01')then
' de enero, '
else if($mes='02' )then
' de febrero,'
else if($mes='03' )then
' de marzo,'
else if($mes='04' )then
' de abril,'
else if($mes='05' )then
' de mayo,'
else if($mes='06' )then
' de junio,'
else if($mes='07' )then
' de julio,'
else if($mes='08' )then
' de agosto,'
else if($mes='09' )then
' de septiembre,'
else if($mes='10' )then
' de octubre,'
else if($mes='11' )then
' de noviembre,'
else if($mes='12' )then
' de diciembre, '
else()
else
if($mes='01')then
'ene'
else if($mes='02' )then
'feb'
else if($mes='03' )then
'mar'
else if($mes='04' )then
'abr'
else if($mes='05' )then
'may'
else if($mes='06' )then
'jun'
else if($mes='07' )then
'jul'
else if($mes='08' )then
'ago'
else if($mes='09' )then
'sep'
else if($mes='10' )then
'oct'
else if($mes='11' )then
'nov'
else if($mes='12' )then
'dic'
else()
};

local:func($CobroComision, $Configuracion)
