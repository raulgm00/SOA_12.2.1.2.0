xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/herramientaOfismatica";
(:: import schema at "../XSD/GenerarAvisoCobroConsolidado.xsd" ::)

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";



declare variable $GenerarAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::) external;
declare function local:func($GenerarAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::)) as element() (:: schema-element(ns2:aviso) ::) {
    <ns2:aviso>
        {
            for $avisoOperacion in $GenerarAvisoCobroRequest/ns1:AvisoCobro/ges:aviso[1]/ges:avisoOperacion[count(ges:informacionPago/ges:detallePago/ges:Mora)>0]
            let $aviso := $GenerarAvisoCobroRequest/ns1:AvisoCobro/ges:aviso[1]
            let $tipoReporte :=  "Mora" 
            let $encabezado :="Mora"
            return 
            <ns2:consolidado>
                <ns2:fechaEmision>{local:formatReportDate(fn:data($aviso/ges:emision/text()))}</ns2:fechaEmision>
                <ns2:pais>{fn:data($aviso/cli:pais/cat:Descripcion)}</ns2:pais>
                <ns2:cliente>{fn:concat($aviso/cli:idFacturador/text()," ", fn:data($aviso/cli:razonSocial))}</ns2:cliente>
                <ns2:operacion>{fn:concat($avisoOperacion/ope:idOperacion/text()," - ", fn:data($avisoOperacion/ope:nombre))}</ns2:operacion>
                <ns2:moneda>{fn:data($avisoOperacion/ges:Moneda/cat:Descripcion)}</ns2:moneda>
                 <ns2:tipoReporte>{fn:data($tipoReporte)}</ns2:tipoReporte>
                {
                      for $informacionPago in $avisoOperacion/ges:informacionPago
                    let $totalCapital := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)
                    let $totalInteres := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='INTERES_VENCIDO']/com:importe)
                    let $totalCc := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='COMISION_VENCIDA']/com:importe)
                    let $totalMoratorios := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='INTERES_MORATORIO']/com:importe)
                    let $totalOc := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='OTRA_COMISION_VENCIDA']/com:importe)
                    let $totales := $totalCapital + $totalInteres + $totalCc + $totalOc + $totalMoratorios
                    let $incluyeMora := (if($totalMoratorios>0) then "true" else "false")
                    let $comentario3:=$informacionPago/ges:detallePago[1]/ges:comentario3
                    return 
                    <ns2:resumenPago>
                        <ns2:fecha>{ local:formatReportDate($informacionPago/ges:fechaPago/text())}</ns2:fecha>
                        <ns2:encabezado>{fn:data($encabezado)}</ns2:encabezado>
                        <ns2:incluyeMoratorio>{fn:data($incluyeMora)}</ns2:incluyeMoratorio>
                        {
                            for $detallePago in $informacionPago/ges:detallePago
                            for $detalleMora in $detallePago/ges:Mora
                            let $capital := (if($detalleMora/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)then $detalleMora/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe else 0)
                            let $interes := (if($detalleMora/des:monto[com:tipo/cat:Descripcion='INTERES_VENCIDO']/com:importe)then $detalleMora/des:monto[com:tipo/cat:Descripcion='INTERES_VENCIDO']/com:importe else 0)
                            let $cc := (if($detalleMora/des:monto[com:tipo/cat:Descripcion='COMISION_VENCIDA']/com:importe)then $detalleMora/des:monto[com:tipo/cat:Descripcion='COMISION_VENCIDA']/com:importe else 0)
                            let $oc := (if($detalleMora/des:monto[com:tipo/cat:Descripcion='OTRA_COMISION_VENCIDA']/com:importe)then $detalleMora/des:monto[com:tipo/cat:Descripcion='OTRA_COMISION_VENCIDA']/com:importe else 0)
                            let $moratorio := (if($detalleMora/des:monto[com:tipo/cat:Descripcion='INTERES_MORATORIO']/com:importe)then $detalleMora/des:monto[com:tipo/cat:Descripcion='INTERES_MORATORIO']/com:importe else 0)
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
                        <ns2:fechaPago>Total</ns2:fechaPago>
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
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                for $detalleLinea in $avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea][1]   
                for $comentario1 in $detalleLinea/ges:detallePago[1]/ges:comentario1
                return -->
                <ns2:comentario1>
                    <!--<ns2:comentario>{fn:data($comentario1)}</ns2:comentario>-->
                    <ns2:comentario>{fn:data($avisoOperacion/ges:informacionPago[1]/ges:detallePago[1]/ges:comentario1)}</ns2:comentario>
                </ns2:comentario1>
                <!--}-->
                
                {
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                for $fondo in distinct-values($avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea]/ges:detallePago/lin:Fondo)
                let $detalleLinea := ($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo])[1]
                let $total := if(count($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo]/ges:Mora/des:monto[com:tipo/cat:Descripcion='TOTAL']/com:importe) > 0) then 
                                fn:sum($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo]/ges:Mora/des:monto[com:tipo/cat:Descripcion='TOTAL']/com:importe)
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
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                for $detalleLinea in $avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea][1]   
                for $comentario2 in $detalleLinea/ges:detallePago[1]/ges:comentario2
                return 
                <ns2:comentario2>
                    <ns2:comentario>{fn:data($comentario2)}</ns2:comentario>
                </ns2:comentario2>
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
declare function local:formatReportDate($fecha as xs:string)
as xs:string {
let $año := substring-before($fecha , '-')
let $añoResiduo := substring-after($fecha , '-')
let $mes := substring-before($añoResiduo , '-')
let $mesResiduo := substring-after($añoResiduo , '-')
let $dia := if(fn:string-length($mesResiduo)>3) then substring-before($mesResiduo , '-') else $mesResiduo
let $formatoFecha := ''
return concat($dia,local:monthFormat($mes),$año)
};
declare function local:monthFormat($mes as xs:string)
as xs:string {
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
};
local:func($GenerarAvisoCobroRequest)
