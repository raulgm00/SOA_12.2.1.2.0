xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/GenerarAvisoCobroDetallado.xsd" ::)
declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace linMO="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace functx = "http://www.functx.com";
declare namespace avi = "http://www.bcie.org/herramientaOfismatica/AvisoDetallado";
declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

  declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $GeneraReporteAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::) external;
declare variable $consultarFondo as element() (:: schema-element(linMO:ConsultarFondoResponse) ::) external;

declare function local:func($GeneraReporteAvisoCobroRequest as element() (:: schema-element(ns1:GeneraReporteAvisoCobroRequest) ::),
                            $consultarFondo as element() (:: schema-element(linMO:ConsultarFondoResponse) ::)) as element() (:: schema-element(ns2:generarAvisoDet) ::) {
    <ns2:generarAvisoDet>
        <avisoDet>
         {
            for $avisoOperacion in $GeneraReporteAvisoCobroRequest/ns1:AvisoCobro/ges:aviso[1]/ges:avisoOperacion
            let $aviso := $GeneraReporteAvisoCobroRequest/ns1:AvisoCobro/ges:aviso[1]
            let $tipoReporte := (if(count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:Mora)>0) then "Mora" 
            else if 
            (sum((count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:tablasDetalle/ges:detalleOC),count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:tablasDetalle/ges:detalleCC),count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:tablasDetalle/ges:detalleIntereses)))<=0)then "CARATULA" else if(count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:ContratoDesembolso)>0) then "No Mora" else "")
            let $encabezado := (if(count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:Mora)>0) then "Mora" else if (count($avisoOperacion/ges:informacionPago/ges:detallePago/ges:ContratoDesembolso)>0) then "Pago" else "")
            return 
            <avi:resumen>
                <avi:fechaEmision>{local:formatReportDate(fn:data($aviso/ges:emision/text()),'1')}</avi:fechaEmision>
                <avi:pais>{fn:data($aviso/cli:pais/cat:Descripcion)}</avi:pais>
                <avi:cliente>{fn:concat($aviso/cli:idFacturador/text()," ", fn:data($aviso/cli:razonSocial))}</avi:cliente>
                <avi:operacion>{fn:concat($avisoOperacion/ope:idOperacion/text()," - ", fn:data($avisoOperacion/ope:nombre))}</avi:operacion>
                <avi:moneda>{fn:data($avisoOperacion/ges:Moneda/cat:Descripcion)}</avi:moneda>
                <avi:tipoReporte>{fn:data($tipoReporte)}</avi:tipoReporte>
                <avi:Comentario1>
                 <avi:comentario>{fn:data($avisoOperacion/ges:informacionPago[1]/ges:detallePago[1]/ges:comentario1)}</avi:comentario>
                    <!--avi:comentario>{fn:data($comentario1)}</avi:comentario-->
                </avi:Comentario1>
                 {
                    for $informacionPago in $avisoOperacion/ges:informacionPago
                    let $totalCapital := fn:sum($informacionPago/ges:detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)
                    let $totalInteres := fn:sum($informacionPago/ges:detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='INTERESES']/com:importe)
                    let $totalCc := fn:sum($informacionPago/ges:detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_COMPROMISO']/com:importe)
                    let $totalOc := fn:sum($informacionPago/ges:detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_OTROS']/com:importe)
                    let $totalMoratorios := fn:sum($informacionPago/ges:detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='MORATORIOS']/com:importe)
                    let $totales := $totalCapital + $totalInteres + $totalCc + $totalOc
                    let $incluyeMora := (if($totalMoratorios>0) then "true" else "false")
                    let $comentario3:=$informacionPago/ges:detallePago[1]/ges:comentario3
                    return 
                    <avi:resumenPago>
                        <avi:fecha>{local:formatReportDate($informacionPago/ges:fechaPago/text(),'1')}</avi:fecha>
                        <avi:encabezado>{fn:data($encabezado)}</avi:encabezado>
                        <avi:incluyeMoratorio>{fn:data($incluyeMora)}</avi:incluyeMoratorio>
                        {
                           for $detallePago in $informacionPago/ges:detallePago
                            let $subCapital :=  fn:sum($detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)
                            let $subInteres :=  fn:sum($detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='INTERESES']/com:importe)
                            let $subCc :=  fn:sum($detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_COMPROMISO']/com:importe)
                            let $subOc :=  fn:sum($detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_OTROS']/com:importe)
                            let $subMoratorio :=  fn:sum($detallePago/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='MORATORIOS']/com:importe)
                            let $titulo := (if(count($informacionPago/ges:detallePago)>1) then concat("Subtotal ", fn:data($detallePago/lin:NumeroLineaCredito)) else concat("Total a pagar ",local:formatReportDate($informacionPago/ges:fechaPago/text(),'1')))
                            let $subTotal := $subCapital + $subInteres + $subCc + $subOc + $subMoratorio
                            return 
                        <avi:saldos>
                            <avi:linea>{fn:data($detallePago/lin:NumeroLineaCredito)}</avi:linea>
                            <avi:lstLinea>
                            {
                            for $detalleDesembolso in $detallePago/ges:ContratoDesembolso
                            let $capital := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='MONTO_PRINCIPAL']/com:importe else 0)
                            let $interes := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='INTERESES']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='INTERESES']/com:importe else 0)
                            let $cc := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_COMPROMISO']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_COMPROMISO']/com:importe else 0)
                            let $oc := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_OTROS']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='COMISION_OTROS']/com:importe else 0)
                            let $moratorio := (if($detalleDesembolso/des:monto[com:tipo/cat:Descripcion='MORATORIOS']/com:importe)then $detalleDesembolso/des:monto[com:tipo/cat:Descripcion='MORATORIOS']/com:importe else 0)
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
                  
                    <avi:fechaPago>{local:formatReportDate($informacionPago/ges:fechaPago/text(),'1')}</avi:fechaPago>
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
                  <avi:Comentario1>
                for $comentario1 in $detalleLinea/ges:detallePago[1]/ges:comentario1
                  </avi:Comentario1>
                return -->
              
                <!--}-->
                
                {
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                for $fondo in distinct-values($avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea]/ges:detallePago/lin:Fondo)
                let $detalleLinea := ($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo])[1]
                let $total := if(count($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo]/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='TOTAL']/com:importe) > 0) then 
                                sum($avisoOperacion/ges:informacionPago/ges:detallePago[lin:NumeroLineaCredito = $linea and lin:Fondo = $fondo]/ges:ContratoDesembolso/des:monto[com:tipo/cat:Descripcion='TOTAL']/com:importe) 
                              else 
                                0
                for $datosBancos in $detalleLinea/ges:Banco
                return 
                <avi:lstBancos>
                    <avi:instruccion>{fn:concat("Instrucciones para el Fondo ", data($fondo), " del monto ", local:formatNumber($total))}</avi:instruccion>
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
                {
                for $linea in distinct-values($avisoOperacion/ges:informacionPago/ges:detallePago/lin:NumeroLineaCredito)
                  return
                <avi:detalleCalculo>
                    <avi:linea>{fn:data($linea)}</avi:linea>
                     {
                    for $infoPago in  $avisoOperacion/ges:informacionPago[ges:detallePago/lin:NumeroLineaCredito = $linea]
                     for $detallesCalulo in $infoPago/ges:detallePago[lin:NumeroLineaCredito = $linea]
                     for $detalles in $detallesCalulo/ges:tablasDetalle
                     let $tipoTabla := (if(count($detalles/ges:detalleIntereses)>0) then "CI" else if (count($detalles/ges:detalleCC)>0) then "CC" else if(count($detalles/ges:detalleOC)>0) then "OC" else "")
                     let $fechaPago := $infoPago/ges:fechaPago
                     let $fondo := fn:data(concat($detallesCalulo/lin:Fondo/text(), 
                                        " - ",
                                        fn:normalize-space($consultarFondo/linMO:fondo[cat:Id = $detallesCalulo/lin:Fondo/text()]/cat:Descripcion/text())))
                     return
                     if($tipoTabla eq "CI")
                    then 
                    <avi:tablaDetalle>
                        <avi:tipoTabla>{fn:data($tipoTabla)}</avi:tipoTabla>
                        <avi:fondo>{$fondo}</avi:fondo>
                        <avi:blTotales>{(if(count($detalles/ges:detalleIntereses)>1) then"false" else "true")}</avi:blTotales>
                         {
                              for $desembolsoInteres in distinct-values($detalles/ges:detalleIntereses/des:referencia)
                              for $detalleIntereses in $detalles/ges:detalleIntereses[des:referencia = $desembolsoInteres][1]					   
                               let $pagosAplicados := (if($detalleIntereses/ges:pagosAplicados/com:importe and $detalleIntereses/ges:pagosAplicados/com:importe/text() !='')then $detalleIntereses/ges:pagosAplicados/com:importe else 0)
                               let $titulo := concat("Subtotal ", fn:data($detalleIntereses/des:referencia))
                               let $SubtotalValor := fn:sum($detalles/ges:detalleIntereses[des:referencia = $desembolsoInteres]/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe)
                              let $SubtotalCI := $SubtotalValor - $pagosAplicados
                              return 
                        <avi:lstDesembolso>
                            <avi:desembolso>{fn:data($detalleIntereses/des:referencia)}</avi:desembolso>
			{for $detalleBase in $detalles/ges:detalleIntereses[des:referencia = $desembolsoInteres]
                               let $saldo := (if($detalleBase/des:monto[com:tipo/cat:Descripcion='SALDO']/com:importe)then $detalleBase/des:monto[com:tipo/cat:Descripcion='SALDO']/com:importe else 0)
                               let $importe := (if($detalleBase/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe)then $detalleBase/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe else 0)  
                               return
                            <avi:base>
                                <avi:base>{fn:data($detalleBase/des:condicionesFinancieras/des:baseCalculo/cat:DescripcionCorta)}</avi:base>
                                <avi:tasa>{local:formatoTasa($detalleBase/des:condicionesFinancieras/des:tasa/des:fija/des:valor)}</avi:tasa>
                                <avi:saldo>{local:formatNumber($saldo)}</avi:saldo>
                                <avi:desdeFecha>{local:formatReportDate($detalleBase/ges:periodo/com:inicio/text(),'2')}</avi:desdeFecha>
                                <avi:hastaFecha>{local:formatReportDate($detalleBase/ges:periodo/com:fin/text(),'2')}</avi:hastaFecha>
                                <avi:dias>{fn:data($detalleBase/ges:periodo/com:dias)}</avi:dias>
                                <avi:importe>{local:formatNumber($importe)}</avi:importe>
                            </avi:base>
                            }
							<avi:subTotales>
                                <avi:mensaje>{$titulo}</avi:mensaje>
                                <avi:valor>{local:formatNumber($SubtotalCI)}</avi:valor>
                            </avi:subTotales>
                           <avi:pagosAplicados>{if(xs:double(local:formatNumber($pagosAplicados))>0)then local:formatNumber($pagosAplicados) else "N"}</avi:pagosAplicados>
                        </avi:lstDesembolso>  
                        }
                        <avi:fechaPago>{concat("Total ",local:formatReportDate($fechaPago/text(),'1'))}</avi:fechaPago>
                        <avi:totalIntereses>{local:formatNumber(fn:sum($detalles/ges:detalleIntereses/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe))}</avi:totalIntereses>
                         </avi:tablaDetalle>
                        
                        else if($tipoTabla eq "CC")                        
                         then
                         let $totalTitulo := concat("Total a pagar ",local:formatReportDate($infoPago/ges:fechaPago/text(),'1'))
                         let $pagosAplicados := (if($detalles/ges:detalleCC[1]/ges:pagosAplicados/com:importe and $detalles/ges:detalleCC[1]/ges:pagosAplicados/com:importe/text() != '')then $detalles/ges:detalleCC[1]/ges:pagosAplicados/com:importe else 0)
                         let $totalValor := fn:sum($detalles/ges:detalleCC/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe)
                         let $totalCC := $totalValor - $pagosAplicados
                         return
                        <avi:tablaDetalle>
                        <avi:tipoTabla>{fn:data($tipoTabla)}</avi:tipoTabla>
                        <avi:fondo>{$fondo}</avi:fondo>
                        <avi:blTotales></avi:blTotales>
                        <avi:lstDesembolso>
                            <avi:desembolso></avi:desembolso>
                             {
                              for $detalleCC in $detalles/ges:detalleCC
                             let $saldo := (if($detalleCC/des:monto[com:tipo/cat:Descripcion='SALDO']/com:importe)then $detalleCC/des:monto[com:tipo/cat:Descripcion='SALDO']/com:importe else 0)
                             let $importe := (if($detalleCC/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe)then $detalleCC/des:monto[com:tipo/cat:Descripcion='MONTO_INTERES']/com:importe else 0)
                              return 
                            <avi:base>
                                <avi:base>{fn:data($detalleCC/des:condicionesFinancieras/des:baseCalculo/cat:DescripcionCorta)}</avi:base>
                                <avi:tasa>{local:formatoTasa($detalleCC/des:condicionesFinancieras/des:tasa/des:fija/des:valor)}</avi:tasa>
                                <avi:saldo>{local:formatNumber($saldo)}</avi:saldo>
                                <avi:desdeFecha>{local:formatReportDate($detalleCC/ges:periodo/com:inicio/text(),'2')}</avi:desdeFecha>
                                <avi:hastaFecha>{local:formatReportDate($detalleCC/ges:periodo/com:fin/text(),'2')}</avi:hastaFecha>
                                <avi:dias>{fn:data($detalleCC/ges:periodo/com:dias)}</avi:dias>
                                <avi:importe>{local:formatNumber($importe)}</avi:importe>
                            </avi:base>                 
                            }
                            <avi:subTotales>
                                <avi:mensaje>{$totalTitulo}</avi:mensaje>
                                <avi:valor>{local:formatNumber($totalCC)}</avi:valor>
                            </avi:subTotales>
                            <avi:pagosAplicados>{if(xs:double(local:formatNumber($pagosAplicados))>0)then local:formatNumber($pagosAplicados) else "N"}</avi:pagosAplicados>
                        </avi:lstDesembolso>
                         </avi:tablaDetalle>
                       else if($tipoTabla eq "OC")
                         then     
                         let $totalTitulo := concat("Total a pagar ",local:formatReportDate($infoPago/ges:fechaPago/text(),'1'))
                         return
                        <avi:tablaDetalle>
                        <avi:tipoTabla>{fn:data($tipoTabla)}</avi:tipoTabla>
                        <avi:fondo>{$fondo}</avi:fondo>
                        <avi:blTotales></avi:blTotales>
                         {
                               for $detallesOC in $detalles/ges:detalleOC
                             let $importe := (if($detallesOC/com:importe)then $detallesOC/com:importe else 0)
                              return 
                        <avi:lstDesembolso>
                            <avi:desembolso></avi:desembolso>
                            <avi:lsBase>
                                  <avi:comision>{fn:data($detallesOC/com:tipo/cat:DescripcionCorta)}</avi:comision>
                                  <avi:hastaFecha>{local:formatReportDate($detallesOC/ges:vencimiento/text(),'2')}</avi:hastaFecha>
                                  <avi:importe>{local:formatNumber($importe)}</avi:importe>
                              </avi:lsBase>
                            <avi:subTotales>
                                <avi:mensaje>{$totalTitulo}</avi:mensaje>
                                <avi:valor>{local:formatNumber(sum($detalles/ges:detalleOC/com:importe))}</avi:valor>
                            </avi:subTotales>
                            <avi:pagosAplicados></avi:pagosAplicados>
                        </avi:lstDesembolso>                        
                        }
                    </avi:tablaDetalle>
                     else()
                         }
                </avi:detalleCalculo>
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

   string-join((for $i in 1 to $count return $stringToRepeat),
                        '')
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
local:func($GeneraReporteAvisoCobroRequest, $consultarFondo)
