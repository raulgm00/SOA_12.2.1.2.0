xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/herramientaOfismatica";
(:: import schema at "../XSD/GenerarAvisoCobroConsolidado.xsd" ::)
declare namespace linMO="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace functx = "http://www.functx.com";
declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

  declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";


declare variable $GenerarAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::) external;
declare variable $consultarFondo as element() (:: schema-element(linMO:ConsultarFondoResponse) ::) external;

declare function local:func($GenerarAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::),
                            $consultarFondo as element() (:: schema-element(linMO:ConsultarFondoResponse) ::)) as element() (:: schema-element(ns2:aviso) ::) {
    <ns2:aviso>
        {
            for $avisoOperacion in $GenerarAvisoCobroRequest/ns1:AvisoCobro/ges:aviso[1]/ges:avisoOperacion
            let $aviso := $GenerarAvisoCobroRequest/ns1:AvisoCobro/ges:aviso[1]
            let $tipoReporte := (if(count($avisoOperacion/ges:informacionPago[1]/ges:detallePago/ges:Mora)>0) then "Mora" 
            else if 
            (sum((count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:tablasDetalle/ges:detalleOC),count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:tablasDetalle/ges:detalleCC),count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:tablasDetalle/ges:detalleIntereses)))<=0)then "CARATULA"
            else if (count($avisoOperacion/ges:informacionPago[1]/ges:detallePago/ges:ContratoDesembolso)>0) then "No Mora" else "DETALLE")
            let $encabezado := (if(count($avisoOperacion/ges:informacionPago[1]/ges:detallePago/ges:Mora)>0) then "Mora" else if (count($avisoOperacion/ges:informacionPago[1]/ges:detallePago/ges:ContratoDesembolso)>0) then "Pago" else "")
            return 
            <ns2:consolidado>
                <ns2:fechaEmision>{local:formatReportDate(fn:data($aviso/ges:emision/text()),'1')}</ns2:fechaEmision>
                <ns2:pais>{fn:data($aviso/cli:pais/cat:Descripcion)}</ns2:pais>
                <ns2:cliente>{fn:concat($aviso/cli:idFacturador/text()," ", fn:data($aviso/cli:razonSocial))}</ns2:cliente>
                <ns2:operacion>{fn:concat($avisoOperacion/ope:idOperacion/text()," - ", fn:data($avisoOperacion/ope:nombre))}</ns2:operacion>
                <ns2:moneda>{fn:data($avisoOperacion/ges:Moneda/cat:Descripcion)}</ns2:moneda>
                 <ns2:tipoReporte>{fn:data($tipoReporte)}</ns2:tipoReporte>
                {
                      for $informacionPago in $avisoOperacion/ges:informacionPago
                    let $totalCapital := fn:sum($informacionPago/ges:detallePago/lin:Monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)
                    let $totalInteres := fn:sum($informacionPago/ges:detallePago/lin:Monto[com:tipo/cat:Descripcion='INTERESES']/com:importe)
                    let $totalCc := fn:sum($informacionPago/ges:detallePago/lin:Monto[com:tipo/cat:Descripcion='COMISION_COMPROMISO']/com:importe)
                    let $totalMoratorios := fn:sum($informacionPago/ges:detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='MORATORIOS']/com:importe)
                    let $totalOc := fn:sum($informacionPago/ges:detallePago/lin:Monto[com:tipo/cat:Descripcion='COMISION_OTROS']/com:importe)
                    let $totales := $totalCapital + $totalInteres + $totalCc + $totalOc + $totalMoratorios
                    let $incluyeMora := (if($totalMoratorios>0) then "true" else "false")
                    let $comentario3:=$informacionPago/ges:detallePago[1]/ges:comentario3[1]
                    return 
                    <ns2:resumenPago>
                        <ns2:fecha>{local:formatReportDate($informacionPago/ges:fechaPago/text(),'1')}</ns2:fecha>
                        <ns2:encabezado>{fn:data($encabezado)}</ns2:encabezado>
                        <ns2:incluyeMoratorio>{fn:data($incluyeMora)}</ns2:incluyeMoratorio>
                        {
                            for $detallePago in $informacionPago/ges:detallePago
                            
                            let $capital := (if($detallePago/lin:Monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)then sum($detallePago/lin:Monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe) else 0)
                            let $interes := (if($detallePago/lin:Monto[com:tipo/cat:Descripcion='INTERESES']/com:importe)then sum($detallePago/lin:Monto[com:tipo/cat:Descripcion='INTERESES']/com:importe) else 0)
                            let $cc := (if($detallePago/lin:Monto[com:tipo/cat:Descripcion='COMISION_COMPROMISO']/com:importe)then sum($detallePago/lin:Monto[com:tipo/cat:Descripcion='COMISION_COMPROMISO']/com:importe) else 0)
                            let $oc := (if($detallePago/lin:Monto[com:tipo/cat:Descripcion='COMISION_OTROS']/com:importe)then sum($detallePago/lin:Monto[com:tipo/cat:Descripcion='COMISION_OTROS']/com:importe) else 0)
                            let $moratorio := (if($detallePago/lin:Monto[com:tipo/cat:Descripcion='MORATORIOS']/com:importe)then sum($detallePago/lin:Monto[com:tipo/cat:Descripcion='MORATORIOS']/com:importe) else 0)
                            let $totalDetalle := $capital + $interes + $cc + $oc + $moratorio
                            return 
                            <ns2:saldos>
                                <ns2:linea>{fn:data($detallePago/lin:NumeroLineaCredito)}</ns2:linea>
                                <ns2:fondo>{fn:data($detallePago/lin:Fondo)}</ns2:fondo>
                                <ns2:capital>{local:formatNumber($capital)}</ns2:capital>
                                <ns2:Intereses>{local:formatNumber($interes)}</ns2:Intereses>
                                <ns2:interesesMoratorios>{local:formatNumber($moratorio)}</ns2:interesesMoratorios>
                                <ns2:comisionCompromiso>{local:formatNumber($cc)}</ns2:comisionCompromiso>
                                <ns2:otrasComisiones>{local:formatNumber($oc)}</ns2:otrasComisiones>
                                <ns2:totalPagar>{local:formatNumber($totalDetalle)}</ns2:totalPagar>
                            </ns2:saldos>
                        }
                        <ns2:fechaPago>{fn:data($informacionPago/ges:fechaPago)}</ns2:fechaPago>
                        <ns2:Comentario3>
                          <ns2:comentario>{if(fn:string($comentario3)!='') then fn:string($comentario3) else() }</ns2:comentario>
                        </ns2:Comentario3>
                        <ns2:totalCapital>{local:formatNumber($totalCapital)}</ns2:totalCapital>
                        <ns2:totalIntereses>{local:formatNumber($totalInteres)}</ns2:totalIntereses>
                        <ns2:interesesMoratorios>{local:formatNumber($totalMoratorios)}</ns2:interesesMoratorios>
                        <ns2:totalComisionCompromiso>{local:formatNumber($totalCc)}</ns2:totalComisionCompromiso>
                        <ns2:totalOtrasComisiones>{local:formatNumber($totalOc)}</ns2:totalOtrasComisiones>
                        <ns2:total>{local:formatNumber($totales)}</ns2:total>
                    </ns2:resumenPago>
                }
                <!--{
                for $linea in distinct-values($avisoOperacion/ges:informacionPago[1]/ges:detallePago/lin:NumeroLineaCredito)
                for $detalleLinea in $avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea][1]   
                for $comentario1 in $detalleLinea/ges:detallePago[1]/ges:comentario1
                return -->
                
                <ns2:comentario1>
                    <!--<ns2:comentario>{fn:data($comentario1)}</ns2:comentario>-->
                    <ns2:comentario>{fn:data($avisoOperacion/ges:informacionPago[1]/ges:detallePago[1]/ges:comentario1)}</ns2:comentario>-->
                </ns2:comentario1>
                <!--}-->
                
                {
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                for $fondo in distinct-values($avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea]/ges:detallePago/lin:Fondo)
                let $detalleLinea := ($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo])[1]
                let $total := if(count($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo]/lin:Monto[com:tipo/cat:Descripcion='TOTAL']/com:importe) > 0) then 
                                sum($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo]/lin:Monto[com:tipo/cat:Descripcion='TOTAL']/com:importe )
                              else 
                                0
                for $datosBancos in $detalleLinea/ges:Banco
                return 
                <ns2:lstBancos>
                    <ns2:instruccion>{fn:concat("Instrucciones para el Fondo ", data($fondo), " del monto ", local:formatNumber($total))}</ns2:instruccion>
                    <ns2:banco>{fn:data($datosBancos/ges:banco)}</ns2:banco>
                    <ns2:codigoSwiftBanco>{fn:data($datosBancos/ges:SWIFTBanco)}</ns2:codigoSwiftBanco>
                    <ns2:CuentaNo>{fn:data($datosBancos/ges:cuentaNo)}</ns2:CuentaNo>
                    <ns2:Beneficiario>{fn:data($datosBancos/ges:beneficiario)}</ns2:Beneficiario>
                    <ns2:codigoSwiftBeneficiario>{fn:data("BCIEHNTE")}</ns2:codigoSwiftBeneficiario>
                    <ns2:Referencia>{fn:data($datosBancos/ges:Referencia)}</ns2:Referencia>
                </ns2:lstBancos>               
                }
                {
                for $linea in distinct-values($avisoOperacion/ges:informacionPago[1]/ges:detallePago/lin:NumeroLineaCredito)
                for $detalleLinea in $avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea][1]   
                for $comentario2 in $detalleLinea/ges:detallePago[1]/ges:comentario2
                return 
                <ns2:comentario2>
                    <ns2:comentario>{fn:data($comentario2)}</ns2:comentario>
                </ns2:comentario2>
                }
                {
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                  return
                 <ns2:detalleCalculo>
                     <ns2:linea>{fn:data($linea)}</ns2:linea>
                     {
                    for $infoPago in  $avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea]
                     for $detallesCalulo in $infoPago/ges:detallePago[lin:NumeroLineaCredito = $linea]
                     for $detalles in $detallesCalulo/ges:tablasDetalle
                     let $tipoTabla := (if(count($detalles/ges:detalleIntereses)>0) then "CI" else if (count($detalles/ges:detalleCC)>0) then "CC" else if(count($detalles/ges:detalleOC)>0) then "OC" else "")
                     let $fechaPago := $infoPago/ges:fechaPago
                      return
                      <ns2:tablaDetalle>
                         <ns2:tipoTabla>{fn:data($tipoTabla)}</ns2:tipoTabla>
                         {
                          if($tipoTabla != '') then
                            <ns2:fondo>{fn:data(concat($detallesCalulo/lin:Fondo/text(), 
                                        " - ",
                                        fn:normalize-space($consultarFondo/linMO:fondo[cat:Id = $detallesCalulo/lin:Fondo/text()]/cat:Descripcion/text())))}
                            </ns2:fondo>
                          else
                            ()
                         }
                         {
                         if($tipoTabla eq "CI")
                         then 
                              for $detalleIntereses in $detalles/ges:detalleIntereses
                              let $saldo := (if($detalleIntereses/des:monto[com:tipo/cat:Descripcion='SALDO']/com:importe)then $detalleIntereses/des:monto[com:tipo/cat:Descripcion='SALDO']/com:importe else 0)
                             let $importe := (if($detalleIntereses/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe)then $detalleIntereses/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe else 0)
                              return 
                              <ns2:lsBase>
                                <ns2:base>{fn:data($detalleIntereses/des:condicionesFinancieras/des:baseCalculo/cat:DescripcionCorta)}</ns2:base>
                                  <ns2:tasa>{local:formatoTasa($detalleIntereses/des:condicionesFinancieras/des:tasa/des:fija/des:valor)}</ns2:tasa>
                                  <ns2:saldo>{local:formatNumber($saldo)}</ns2:saldo>
                                  <ns2:desdeFecha>{local:formatReportDate($detalleIntereses/ges:periodo/com:inicio/text(),'2')}</ns2:desdeFecha>
                                  <ns2:hastaFecha>{local:formatReportDate($detalleIntereses/ges:periodo/com:fin/text(),'2')}</ns2:hastaFecha>
                                  <ns2:dias>{fn:data($detalleIntereses/ges:periodo/com:dias)}</ns2:dias>
                                  <ns2:importe>{local:formatNumber($importe)}</ns2:importe>
                              </ns2:lsBase>
                         else if($tipoTabla eq "CC")
                         then
                            for $detalleCC in $detalles/ges:detalleCC
                             let $saldo := (if($detalleCC/des:monto[com:tipo/cat:Descripcion='SALDO']/com:importe)then $detalleCC/des:monto[com:tipo/cat:Descripcion='SALDO']/com:importe else 0)
                             let $importe := (if($detalleCC/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe)then $detalleCC/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe else 0)
                              return 
                              <ns2:lsBase>
                                <ns2:base>{fn:data($detalleCC/des:condicionesFinancieras/des:baseCalculo/cat:DescripcionCorta)}</ns2:base>
                                  <ns2:tasa>{local:formatoTasa($detalleCC/des:condicionesFinancieras/des:tasa/des:fija/des:valor/text())}</ns2:tasa>
                                  <ns2:saldo>{local:formatNumber($saldo)}</ns2:saldo>
                                  <ns2:desdeFecha>{local:formatReportDate($detalleCC/ges:periodo/com:inicio/text(),'2')}</ns2:desdeFecha>
                                  <ns2:hastaFecha>{local:formatReportDate($detalleCC/ges:periodo/com:fin/text(),'2')}</ns2:hastaFecha>
                                  <ns2:dias>{fn:data($detalleCC/ges:periodo/com:dias)}</ns2:dias>
                                  <ns2:importe>{local:formatNumber($importe)}</ns2:importe>
                              </ns2:lsBase>
                          else if($tipoTabla eq "OC")
                         then
                              for $detallesOC in $detalles/ges:detalleOC
                             let $importe := (if($detallesOC/com:importe)then $detallesOC/com:importe else 0)

                              return 
                              <ns2:lsBase>
                                  <ns2:comision>{fn:data($detallesOC/com:tipo/cat:DescripcionCorta)}</ns2:comision>
                                   <ns2:hastaFecha>{lower-case($detallesOC/ges:vencimiento/text())}</ns2:hastaFecha>
                                  <ns2:importe>{local:formatNumber($importe)}</ns2:importe>
                              </ns2:lsBase>
                          else()
                         }
                        {
                         if($tipoTabla eq "CI")
                         then 
                          <ns2:lsTotal>
                            <ns2:mensaje>{concat("Total ",local:formatReportDate($fechaPago/text(),'1'))}</ns2:mensaje>
                             <ns2:valor>{local:formatNumber(fn:sum($detalles/ges:detalleIntereses/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe))}</ns2:valor>
                         </ns2:lsTotal>
                         else if($tipoTabla eq "CC")
                         then
                           <ns2:lsTotal>
                            <ns2:mensaje>{concat("Total ",local:formatReportDate($fechaPago/text(),'1'))}</ns2:mensaje>
                             <ns2:valor>{local:formatNumber(fn:sum($detalles/ges:detalleCC/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe))}</ns2:valor>
                           </ns2:lsTotal>
                          else if($tipoTabla eq "OC")
                         then
                          <ns2:lsTotal>
                            <ns2:mensaje>{concat("Total ",local:formatReportDate($fechaPago/text(),'1'))}</ns2:mensaje>
                             <ns2:valor>{local:formatNumber(fn:sum($detalles/ges:detalleOC/com:importe))}</ns2:valor>
                         </ns2:lsTotal>
                          else()
                         }
                         
                     </ns2:tablaDetalle>
                     }
                     
                 </ns2:detalleCalculo>
                }
                
            </ns2:consolidado>
        }
    </ns2:aviso>
};
declare function local:formatNumber($number as xs:double)
as xs:string {
if($number = 0)then '0.00'
else
   fn-bea:format-number(xs:double($number),"###,###.00")
};
declare function local:formatoTasa($number as xs:double)
as xs:string {
if($number = 0)then '0.000000'
else
   
   let $roundNumber := round-half-to-even($number, 5)
   let $tasa := fn-bea:format-number(xs:double($roundNumber),"###,###.#####")
   return
   if(contains($tasa,'.'))then
   concat(local:AddDecimal($tasa),"%")
   else
   concat(local:AddDecimal(concat($tasa,'.')),"%")
};
declare function local:AddDecimal($number as xs:string)
as xs:string {
let $decimales := concat($number,functx:repeat-string("0",(5-fn:string-length(substring-after($number , '.')))))
return
$decimales
};
declare function functx:repeat-string
  ( $stringToRepeat as xs:string? ,
    $count as xs:integer )  as xs:string {
   string-join((for $i in 1 to $count return $stringToRepeat),'')
 } ;
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
local:func($GenerarAvisoCobroRequest, $consultarFondo)
