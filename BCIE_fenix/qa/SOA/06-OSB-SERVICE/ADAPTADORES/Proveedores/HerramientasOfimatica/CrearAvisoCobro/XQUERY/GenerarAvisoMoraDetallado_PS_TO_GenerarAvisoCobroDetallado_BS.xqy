xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/GenerarAvisoCobroDetallado.xsd" ::)
declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace avi = "http://www.bcie.org/herramientaOfismatica/AvisoDetallado";
declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $GeneraReporteAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::) external;

declare function local:func($GeneraReporteAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::)) as element() (:: schema-element(ns2:generarAvisoDet) ::) {
    <ns2:generarAvisoDet>
        <avisoDet>
         {
            for $avisoOperacion in $GeneraReporteAvisoCobroRequest/ns1:AvisoCobro/ges:aviso[1]/ges:avisoOperacion[count(ges:informacionPago/ges:detallePago/ges:Mora)>0]
            let $aviso := $GeneraReporteAvisoCobroRequest/ns1:AvisoCobro/ges:aviso[1]
            let $tipoReporte := (if(count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:Mora)>0) then "Mora" else if (count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:ContratoDesembolso)>0) then "No Mora" else "")
            let $encabezado := (if(count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:Mora)>0) then "Mora" else if (count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:ContratoDesembolso)>0) then "Pago" else "")
            return 
            <avi:resumen>
                <avi:fechaEmision>{local:formatReportDate(fn:data($aviso/ges:emision/text()))}</avi:fechaEmision>
                <avi:pais>{fn:data($aviso/cli:pais/cat:Descripcion)}</avi:pais>
                <avi:cliente>{fn:concat($aviso/cli:idFacturador/text()," ", fn:data($aviso/cli:razonSocial))}</avi:cliente>
                <avi:operacion>{fn:concat($avisoOperacion/ope:idOperacion/text()," - ", fn:data($avisoOperacion/ope:nombre))}</avi:operacion>
                <avi:moneda>{fn:data($avisoOperacion/ges:Moneda/cat:Descripcion)}</avi:moneda>
                <avi:tipoReporte>{fn:data($tipoReporte)}</avi:tipoReporte>
                 {
                      for $informacionPago in $avisoOperacion/ges:informacionPago
                    let $totalCapital := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)
                    let $totalInteres := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='INTERES_VENCIDO']/com:importe)
                    let $totalCc := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='COMISION_VENCIDA']/com:importe)
                    let $totalOc := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='OTRA_COMISION_VENCIDA']/com:importe)
                    let $totalMoratorios := fn:sum($informacionPago/ges:detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='INTERES_MORATORIO']/com:importe)
                    let $totales := $totalCapital + $totalInteres + $totalCc + $totalOc
                    let $incluyeMora := (if($totalMoratorios>0) then "true" else "false")
                    let $comentario3:=$informacionPago/ges:detallePago[1]/ges:comentario3
                    return 
                    <avi:resumenPago>
                        <avi:fecha>{local:formatReportDate($informacionPago/ges:fechaPago/text())}</avi:fecha>
                        <avi:encabezado>{fn:data($encabezado)}</avi:encabezado>
                        <avi:incluyeMoratorio>{fn:data($incluyeMora)}</avi:incluyeMoratorio>
                        {
                            for $detallePago in $informacionPago/ges:detallePago
                            let $subCapital := fn:sum($detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)
                            let $subInteres := fn:sum($detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='INTERES_VENCIDO']/com:importe)
                            let $subCc := fn:sum($detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='COMISION_VENCIDA']/com:importe)
                            let $subOc := fn:sum($detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='OTRA_COMISION_VENCIDA']/com:importe)
                            let $subMoratorio := fn:sum($detallePago/ges:Mora/des:monto[com:tipo/cat:Descripcion='INTERES_MORATORIO']/com:importe)
                            let $titulo := (if(count($informacionPago/ges:detallePago)>1) then concat("Subtotal ", fn:data($detallePago/lin:NumeroLineaCredito)) else "Total")
                            let $subTotal := $subCapital + $subInteres + $subCc + $subOc + $subMoratorio
                            return 
                        <avi:saldos>
                            <avi:linea>{fn:data($detallePago/lin:NumeroLineaCredito)}</avi:linea>
                            <avi:lstLinea>
                            {
                            for $detalleDesembolso in $detallePago/ges:Mora
                            let $capital := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe else 0)
                            let $interes := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='INTERES_VENCIDO']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='INTERES_VENCIDO']/com:importe else 0)
                            let $cc := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_VENCIDA']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_VENCIDA']/com:importe  else 0)
                            let $oc := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='OTRA_COMISION_VENCIDA']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='OTRA_COMISION_VENCIDA']/com:importe else 0)
                            let $moratorio := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='INTERES_MORATORIO']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='INTERES_MORATORIO']/com:importe else 0)
                            let $totalDetalle := $capital + $interes + $cc + $oc + $moratorio
                            return
                                <avi:lstFondo>
                                    <avi:desembolso>{fn:data($detalleDesembolso/des:referencia)}</avi:desembolso>
                                    <avi:capital>{local:formatNumber($capital)}</avi:capital>
                                    <avi:intereses>{local:formatNumber($interes)}</avi:intereses>
                                    <avi:interesesMoratorios>{local:formatNumber($moratorio)}</avi:interesesMoratorios>
                                    <avi:comisionCompromiso>{local:formatNumber($cc)}</avi:comisionCompromiso>
                                    <avi:otrasComisiones>{local:formatNumber($oc)}</avi:otrasComisiones>
                                    <avi:totalSaldo>{local:formatNumber($totalDetalle)}</avi:totalSaldo>
                                </avi:lstFondo>
                                }
                                <avi:fondo>{fn:data($detallePago/lin:Fondo)}</avi:fondo>
                            </avi:lstLinea>
                            <avi:lstSubtotales>
                                <avi:fechaPago>{$titulo}</avi:fechaPago>
                                <avi:totalCapital>{local:formatNumber($subCapital)}</avi:totalCapital>
                                <avi:totalIntereses>{local:formatNumber($subInteres)}</avi:totalIntereses>
                                <avi:totalInteresesMoratorios>{local:formatNumber($subMoratorio)}</avi:totalInteresesMoratorios>
                                <avi:totalComisionCompromiso>{local:formatNumber($subCc)}</avi:totalComisionCompromiso>
                                <avi:totalOtrasComisiones>{local:formatNumber($subOc)}</avi:totalOtrasComisiones>
                                <avi:total>{local:formatNumber($subTotal)}</avi:total>
                            </avi:lstSubtotales>
                        </avi:saldos>
                    }
                    <avi:fechaPago>{fn:data($informacionPago/ges:fechaPago)}</avi:fechaPago>
                    <avi:Comentario3>
                      <avi:comentario>{if(fn:string($comentario3)!='') then fn:string($comentario3) else() }</avi:comentario>
                    </avi:Comentario3>
                    <avi:capitalVencido>{local:formatNumber($totalCapital)}</avi:capitalVencido>
                    <avi:interesesVencidos>{local:formatNumber($totalInteres)}</avi:interesesVencidos>
                    <avi:interesesMoratorios>{local:formatNumber($totalMoratorios)}</avi:interesesMoratorios>
                    <avi:comisionCompromisoVencida>{local:formatNumber($totalCc)}</avi:comisionCompromisoVencida>
                    <avi:otrasComisionesVencidas>{local:formatNumber($totalOc)}</avi:otrasComisionesVencidas>
                    <avi:totalVencido>{local:formatNumber($totales)}</avi:totalVencido>
                  
                </avi:resumenPago>
                }
                <!--{
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                for $detalleLinea in $avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea][1]   
                for $comentario1 in $detalleLinea/ges:detallePago[1]/ges:comentario1
                where $detalleLinea/ges:detallePago[1]/ges:Mora[1]/des:referencia eq $detalleLinea/ges:detallePago[1]/ges:Mora[1]/des:referencia
                return -->
                <avi:Comentario1>
                    <!--<avi:comentario>{fn:data($comentario1)}</avi:comentario>-->
                    <avi:comentario>{fn:data($avisoOperacion/ges:informacionPago[1]/ges:detallePago[1]/ges:comentario1)}</avi:comentario>
                </avi:Comentario1>
                <!--}-->
                {
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                for $fondo in distinct-values($avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea]/ges:detallePago/lin:Fondo)
                let $detalleLinea := ($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo])[1]
                let $fondo := $detalleLinea/lin:Fondo
                let $total := if(count($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo]/ges:Mora/des:monto[com:tipo/cat:Descripcion='TOTAL']/com:importe) > 0) then 
                                sum($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo]/ges:Mora/des:monto[com:tipo/cat:Descripcion='TOTAL']/com:importe) 
                              else 
                                0
                for $datosBancos in $detalleLinea/ges:Banco
                return 
                <avi:lstBancos>
                    <avi:banco>{fn:data($datosBancos/ges:banco)}</avi:banco>
                    <avi:codigoSwiftBanco>{fn:data($datosBancos/ges:SWIFTBanco)}</avi:codigoSwiftBanco>
                    <avi:CuentaNo>{fn:data($datosBancos/ges:cuentaNo)}</avi:CuentaNo>
                    <avi:Beneficiario>{fn:data($datosBancos/ges:beneficiario)}</avi:Beneficiario>
                    <avi:codigoSwiftBeneficiario>{fn:data("BCIEHNTE")}</avi:codigoSwiftBeneficiario>
                    <avi:Referencia>{fn:data($datosBancos/ges:Referencia)}</avi:Referencia>
                </avi:lstBancos>
                  }
                {
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                for $detalleLinea in $avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea][1]   
                for $comentario2 in $detalleLinea/ges:detallePago[1]/ges:comentario2
                return 
                <avi:Comentario2>
                    <avi:comentario>{fn:data($comentario2)}</avi:comentario>
                </avi:Comentario2>
                }
            </avi:resumen>
             }
        </avisoDet>
    </ns2:generarAvisoDet>
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
local:func($GeneraReporteAvisoCobroRequest)
