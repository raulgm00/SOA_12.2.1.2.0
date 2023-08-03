xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/ReportePrepago.xsd" ::)
declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)

declare namespace pre = "http://www.bcie.org/herramientaOfismatica/prepago";

declare namespace pre1 = "http://www.bcie.org/PrepagoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare variable $GenerarReportePrepagoRequest as element() (:: schema-element(ns1:GenerarReportePrepagoRequest) ::) external;

declare function local:func($GenerarReportePrepagoRequest as element() (:: schema-element(ns1:GenerarReportePrepagoRequest) ::)) as element() (:: schema-element(ns2:generarReportePrepago) ::) {
    <ns2:generarReportePrepago>
        <prepago>
            <pre:consolidado>
                <pre:tipoReporte></pre:tipoReporte>
                <pre:fechaEmision>{local:formatDateLong( fn:string( concat( fn:year-from-dateTime(fn:current-dateTime()),"-",fn:month-from-dateTime(fn:current-dateTime()),"--",fn:day-from-dateTime(fn:current-dateTime()))))}</pre:fechaEmision>
                <pre:Pais>{fn:data($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Cliente/cli:pais/cat:Descripcion)}</pre:Pais>
                <pre:Cliente>{concat(fn:data($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Cliente/cli:idFacturador)," ",fn:data($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Cliente/cli:razonSocial) )}</pre:Cliente>
                <pre:Operacion>{fn:concat($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Operacion/ope:idOperacion/text()," ", fn:data($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Operacion/ope:nombre))}</pre:Operacion>
                <pre:Moneda>{fn:data($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Monto[com:tipo/cat:Descripcion = "MONTO_PREPAGO"]/com:moneda/cat:DescripcionCorta)}</pre:Moneda>
                <pre:FechaPrePago>{local:formatDateLong( substring-before($GenerarReportePrepagoRequest/ns1:Prepago/pre1:FechaPrepago/text(),'T'))}</pre:FechaPrePago>
                <pre:DatosPrePago>
                    <pre:fechaSolicitud>{local:formatDateLong( substring-before($GenerarReportePrepagoRequest/ns1:Prepago/pre1:FechaSolicitud/text(),'T'))}</pre:fechaSolicitud>
                    <pre:fechaPrePago>{local:formatDateLong( substring-before($GenerarReportePrepagoRequest/ns1:Prepago/pre1:FechaPrepago/text(),'T'))}</pre:fechaPrePago>
                    <pre:tipoPrePago>{fn:data($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Tipo/cat:Descripcion)}</pre:tipoPrePago>
                    <pre:montoPrePagar>{local:formatNumber($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Monto[com:tipo/cat:Descripcion = "MONTO_PREPAGO"]/com:importe)}</pre:montoPrePagar>
                </pre:DatosPrePago>
                <pre:ConsolidadoPago>
                    {
                        for $Consolidado in $GenerarReportePrepagoRequest/ns1:Reporte/pre1:Consolidado
                        let $concepto := if(count($Consolidado/pre1:Monto/com:moneda[cat:DescripcionCorta = "USD"])>1) then fn:data($Consolidado/pre1:Monto[com:moneda/cat:DescripcionCorta = "USD"][2]/com:tipo/cat:Descripcion) else fn:data($Consolidado/pre1:Monto[com:moneda/cat:DescripcionCorta != "USD"][1]/com:tipo/cat:Descripcion)
                        let $montoDolares := if(count($Consolidado/pre1:Monto/com:moneda[cat:DescripcionCorta = "USD"])>1) then local:formatNumber($Consolidado/pre1:Monto[com:moneda/cat:DescripcionCorta = "USD"][2]/com:importe) else local:formatNumber($Consolidado/pre1:Monto[com:moneda/cat:DescripcionCorta = "USD"]/com:importe)
                        let $montosMoneda := if(count($Consolidado/pre1:Monto/com:moneda[cat:DescripcionCorta = "USD"])>1) then local:formatNumber(0) else local:formatNumber($Consolidado/pre1:Monto[com:moneda/cat:DescripcionCorta != "USD"][1]/com:importe)
                        return
                         
                        if(fn:string( $concepto)!='Total a pagar en dolares' and fn:string( $concepto)!='Total a pagar moneda local' and fn:string( $concepto)!= 'null')then
                        
                        <pre:detallesConsolidadoPago>
                        {
                        if(fn:string( $concepto)!='Total a pagar en dolares' and fn:string( $concepto)!='Total a pagar moneda local' and fn:string( $concepto)!= 'null')then
                          <pre:concepto>{$concepto}</pre:concepto>
                        else()
                         }
                         {
                        if(fn:string( $concepto)!='Total a pagar en dolares' and fn:string( $concepto)!='Total a pagar moneda local'  and fn:string( $concepto)!= 'null')then
                            <pre:montosMoneda>{$montosMoneda}</pre:montosMoneda>
                         else()
                         }
                         {
                        if(fn:string( $concepto)!='Total a pagar en dolares' and fn:string( $concepto)!='Total a pagar moneda local' and fn:string( $concepto)!= 'null')then
                          <pre:montoDolares>{$montoDolares}</pre:montoDolares>
                         else()
                         } 
                        </pre:detallesConsolidadoPago>
                        else
                        ()
                   }
                      <pre:totalMonedaLocal>{if(fn:string(($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Consolidado/pre1:Monto[com:tipo/cat:Descripcion ="Total a pagar moneda local"]/com:importe))!='' and fn:string($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Consolidado/pre1:Monto[com:tipo/cat:Descripcion ="Total a pagar moneda local"]/com:moneda/cat:DescripcionCorta) != "USD")then local:formatNumber( fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Consolidado/pre1:Monto[com:tipo/cat:Descripcion ="Total a pagar moneda local"]/com:importe))else (local:formatNumber(0))}</pre:totalMonedaLocal>
                    <pre:totalDolares>    {if(fn:string( fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Consolidado/pre1:Monto[com:tipo/cat:DescripcionCorta ="TOTAL_USD"]/com:importe))!='')then local:formatNumber( fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Consolidado/pre1:Monto[com:tipo/cat:DescripcionCorta ="TOTAL_USD"]/com:importe))else()}</pre:totalDolares>
                </pre:ConsolidadoPago>
                <pre:Comentario1>
                    <pre:comentario>Una vez que el BCIE reciba la confirmación oficial y aceptación de los términos del prepago, el pago deberá realizarse de acuerdo con las siguientes instrucciones de pago:</pre:comentario>
                </pre:Comentario1>
                {
                    for $InfoBanco in $GenerarReportePrepagoRequest/ns1:Prepago/pre1:InfoBanco
                    return 
                    <pre:lstBancos>
                        <pre:banco>{fn:data($InfoBanco/ges:banco)}</pre:banco>
                        <pre:codigoSwiftBanco>{fn:data($InfoBanco/ges:SWIFTBanco)}</pre:codigoSwiftBanco>
                        <pre:CuentaNo>{fn:data($InfoBanco/ges:cuentaNo)}</pre:CuentaNo>
                        <pre:Beneficiario>{fn:data($InfoBanco/ges:beneficiario)}</pre:Beneficiario>
                        <pre:codigoSwiftBeneficiario>{fn:data($InfoBanco/ges:SWIFTBeneficiario)}</pre:codigoSwiftBeneficiario>
                        <pre:Referencia>{fn:data($InfoBanco/ges:Referencia)}</pre:Referencia></pre:lstBancos>
                }
                <pre:Comentario2>
                    <pre:comentario>Favor incluir en la referencia de la transferencia el número de cliente y el número de préstamo que desea pagar.</pre:comentario>
                </pre:Comentario2>
                <pre:detalleCalculo>
                    <pre:tipoReporte></pre:tipoReporte>
                    {
                        if (fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Penalidad/pre1:Spread)!= 0) then
                            <pre:columnaPenalidad>Spread</pre:columnaPenalidad>
                        else 
                            <pre:columnaPenalidad>Fraccion Libor</pre:columnaPenalidad>
                    }
                    <pre:tipoDetallePenalidad>{fn:data($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Resolucion/cat:DescripcionCorta)}</pre:tipoDetallePenalidad>
                    {
                        for $Cargo in $GenerarReportePrepagoRequest/ns1:Prepago/pre1:Cargo
                        return 
                        <pre:tablaCargosPenalidades>
                            <pre:areaTecnica>{fn:data($Cargo/pre1:Rol/cat:Descripcion)}</pre:areaTecnica>
                            <pre:penalidad>{
                            if($Cargo/pre1:Monto[com:tipo/cat:Descripcion='MONTO_PENALIDAD']/com:importe/text()!='')
                            then local:formatNumber($Cargo/pre1:Monto[com:tipo/cat:Descripcion='MONTO_PENALIDAD']/com:importe)
                            else "0"
                            }</pre:penalidad>
                            <pre:monedaP>{
                            if($Cargo/pre1:Monto[com:tipo/cat:Descripcion='MONTO_PENALIDAD']/com:moneda/cat:DescripcionCorta/text()!='')
                            then fn:data($Cargo/pre1:Monto[com:tipo/cat:Descripcion='MONTO_PENALIDAD']/com:moneda/cat:DescripcionCorta)
                            else "-"
                            }</pre:monedaP>
                            <pre:cargosTramite>{
                            if($Cargo/pre1:Monto[com:tipo/cat:Descripcion='CARGOS_TRAMITE']/com:importe/text()!='')
                            then local:formatNumber($Cargo/pre1:Monto[com:tipo/cat:Descripcion='CARGOS_TRAMITE']/com:importe)
                            else "0"
                            }</pre:cargosTramite>
                            <pre:monedaCT>
                            {
                            if($Cargo/pre1:Monto[com:tipo/cat:Descripcion='CARGOS_TRAMITE']/com:moneda/cat:DescripcionCorta/text()!='')
                            then fn:data($Cargo/pre1:Monto[com:tipo/cat:Descripcion='CARGOS_TRAMITE']/com:moneda/cat:DescripcionCorta)
                            else "-"
                            }
                            </pre:monedaCT>
                            <pre:otrosCargos>{
                             if($Cargo/pre1:Monto[com:tipo/cat:Descripcion='OTROS_CARGOS']/com:importe/text()!='')
                            then local:formatNumber($Cargo/pre1:Monto[com:tipo/cat:Descripcion='OTROS_CARGOS']/com:importe)
                            else "0"
                            }</pre:otrosCargos>
                            <pre:monedaOC>{
                            if($Cargo/pre1:Monto[com:tipo/cat:Descripcion='OTROS_CARGOS']/com:moneda/cat:DescripcionCorta/text()!='')
                            then fn:data($Cargo/pre1:Monto[com:tipo/cat:Descripcion='OTROS_CARGOS']/com:moneda/cat:DescripcionCorta)
                            else "-"
                            }</pre:monedaOC></pre:tablaCargosPenalidades>
                    }
                    <pre:columnaPenalidad>Spread</pre:columnaPenalidad>

                    {
                        if(fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones/pre1:Rol[cat:DescripcionCorta ='FENIX_ANALISTA_COPRES']))then
                        <pre:Observaciones>
                            <pre:observacion>{fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones[pre1:Rol/cat:DescripcionCorta ='FENIX_ANALISTA_COPRES']/pre1:Observacion)}</pre:observacion>
                        </pre:Observaciones>
                        else()
                    }
                    {
                        if(fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones/pre1:Rol[cat:DescripcionCorta ='FENIX_EJECUTIVO_DAECI']))then
                        <pre:Observaciones>
                            <pre:observacion>{fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones[pre1:Rol/cat:DescripcionCorta ='FENIX_EJECUTIVO_DAECI']/pre1:Observacion)}</pre:observacion>
                        </pre:Observaciones>
                        else()
                    }
                    {
                        if(fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones/pre1:Rol[cat:DescripcionCorta ='FENIX_EJECUTIVO_MDC']))then
                        <pre:Observaciones>
                            <pre:observacion>{fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones[pre1:Rol/cat:DescripcionCorta ='FENIX_EJECUTIVO_MDC']/pre1:Observacion)}</pre:observacion>
                        </pre:Observaciones>
                        else()
                    }
                    {
                        if(fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones/pre1:Rol[cat:DescripcionCorta ='FENIX_EJECUTIVO_SEPRI']))then
                        <pre:Observaciones>
                            <pre:observacion>{fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones[pre1:Rol/cat:DescripcionCorta ='FENIX_EJECUTIVO_SEPRI']/pre1:Observacion)}</pre:observacion>
                        </pre:Observaciones>
                        else()
                    }
                    {
                        if(fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones/pre1:Rol[cat:DescripcionCorta ='FENIX_ANALISTA_COFI']))then
                        <pre:Observaciones>
                            <pre:observacion>{fn:data($GenerarReportePrepagoRequest/ns1:Reporte/pre1:Observaciones[pre1:Rol/cat:DescripcionCorta ='FENIX_ANALISTA_COFI']/pre1:Observacion)}</pre:observacion>
                        </pre:Observaciones>
                        else()
                    }
                    {
                        for $Penalidad in $GenerarReportePrepagoRequest/ns1:Reporte/pre1:Penalidad
                        return 
                        <pre:tablaDetallePenalidad>
                            <pre:tipoDetallePenalidad>{fn:data($GenerarReportePrepagoRequest/ns1:Prepago/pre1:Resolucion/cat:DescripcionCorta)}</pre:tipoDetallePenalidad>
                            <pre:contratoDesembolso>{fn:data($Penalidad/des:idFacturador)}</pre:contratoDesembolso>
                            <pre:moneda>{fn:data($Penalidad/pre1:Monto/com:moneda/cat:DescripcionCorta)}</pre:moneda>
                            <pre:resolucion>{fn:data($Penalidad/pre1:Resolucion)}</pre:resolucion>
                            <pre:fechaInicio>{local:formatDate( substring-before($Penalidad/pre1:Inicio/text(),'T'))}</pre:fechaInicio>
                            <pre:fechaFin>{local:formatDate( substring-before($Penalidad/pre1:Fin/text(),'T'))}</pre:fechaFin>
                            <pre:plazoTranscurrido>{fn:data($Penalidad/pre1:Plazo)}</pre:plazoTranscurrido>
                            {
                            if (fn:data($Penalidad/pre1:Spread)!= 0) then
                                    <pre:spred>{fn:data($Penalidad/pre1:Spread)}</pre:spred>
                                else
                                    <pre:spred>{fn:data($Penalidad/pre1:FraccionLibor)}</pre:spred>
                            }
                            <pre:tasaPrepago>{fn:data($Penalidad/pre1:Tasa)}</pre:tasaPrepago>
                            <pre:montoPrepago>{local:formatNumber($Penalidad/pre1:Monto[com:tipo/cat:Descripcion = "MONTO_PREPAGO"]/com:importe)}</pre:montoPrepago>
                            <pre:penalidad>{local:formatNumber($Penalidad/pre1:Monto[com:tipo/cat:Descripcion = "PENALIDAD"]/com:importe)}</pre:penalidad>
                            <pre:intereses>{local:formatNumber($Penalidad/pre1:Monto[com:tipo/cat:Descripcion = "INTERESES"]/com:importe)}</pre:intereses>
                            <pre:proximoPago>{local:formatDate( substring-before($Penalidad/pre1:ProximoPago/text(),'T'))}</pre:proximoPago>
                            </pre:tablaDetallePenalidad>
                    }
                    {
                        for $CondicionesEspeciales in $GenerarReportePrepagoRequest/ns1:Reporte/pre1:CondicionesEspeciales
                        return 
                        <pre:tablaCondicionesEspeciales>
                            <pre:lineaCredito>{fn:data($CondicionesEspeciales/pre1:Linea/lin:NumeroLineaCredito)}</pre:lineaCredito>
                            <pre:descripcion>{fn:data($CondicionesEspeciales/pre1:Linea/lin:Descripcion)}</pre:descripcion>
                        </pre:tablaCondicionesEspeciales>
                    }
                    {
                        for $Coberturas in $GenerarReportePrepagoRequest/ns1:Reporte/pre1:Coberturas
                        return 
                        <pre:tablaCoberturas>
                            <pre:lineaCredito>{fn:data($Coberturas/pre1:Linea/lin:NumeroLineaCredito)}</pre:lineaCredito>
                            <pre:contratoDesembolso>{fn:data($Coberturas/des:idFacturador)}</pre:contratoDesembolso>
                            <pre:codigoCobertura>{fn:data($Coberturas/pre1:Contraparte/cat:codigoExterno)}</pre:codigoCobertura>
                            <pre:contraParte>{fn:data($Coberturas/pre1:Contraparte/cat:Descripcion)}</pre:contraParte>
                            <pre:fechaEfectiva>{local:formatDate( substring-before($Coberturas/pre1:FechaEfectiva/text(),'T'))}</pre:fechaEfectiva>
                            <pre:fechaVencimiento>{local:formatDate( substring-before($Coberturas/pre1:FechaVencimiento/text(),'T'))}</pre:fechaVencimiento></pre:tablaCoberturas>
                    }
                    {
                        for $VentaCartera in $GenerarReportePrepagoRequest/ns1:Reporte/pre1:VentaCartera
                        return 
                        <pre:tablaVentaCartera>
                            <pre:lineaCredito>{fn:data($VentaCartera/pre1:Linea/lin:NumeroLineaCredito)}</pre:lineaCredito>
                            <pre:contratoDesembolso>{fn:data($VentaCartera/des:idFacturador)}</pre:contratoDesembolso>
                            <pre:fondo>{fn:data($VentaCartera/pre1:Linea/lin:Fondo)}</pre:fondo>
                        </pre:tablaVentaCartera>
                    }
                    {
                        for $FuentesExternas in $GenerarReportePrepagoRequest/ns1:Reporte/pre1:FuentesExternas
                        return 
                        <pre:tablaFuentesExternas>
                            <pre:lineaCredito>{fn:data($FuentesExternas/pre1:Linea/lin:NumeroLineaCredito)}</pre:lineaCredito>
                            <pre:contratoDesembolso>{fn:data($FuentesExternas/des:idFacturador)}</pre:contratoDesembolso>
                            <pre:fuente>{fn:data($FuentesExternas/pre1:Fuente/lin:Descripcion)}</pre:fuente>
                            <pre:lineaPasiva>{fn:data($FuentesExternas/pre1:Fuente/lin:codigoLineaPasiva)}</pre:lineaPasiva>
                            <pre:monto>{local:formatNumber($FuentesExternas/pre1:Fuente/lin:Monto/com:importe)}</pre:monto>
                        </pre:tablaFuentesExternas>
                    }
                </pre:detalleCalculo>
            </pre:consolidado>
        </prepago>
    </ns2:generarReportePrepago>
};
declare function local:formatNumber($number as xs:double)
as xs:string {
if($number = 0)then '0.00'
else
   fn-bea:format-number(xs:double($number),"###,###.00")
};

declare function local:formatDate($date as xs:string)
as xs:string {
  if( fn:contains(fn:substring($date,6,8),'10/') or fn:contains(fn:substring($date,6,8),'10-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Oct/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,8),'11/') or fn:contains(fn:substring($date,6,8),'11-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Nov/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,8),'12/') or fn:contains(fn:substring($date,6,8),'12-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Dic/',fn:substring(fn:string($date),1,4))
else if( fn:contains(fn:substring($date,6,7),'1/') or fn:contains(fn:substring($date,6,7),'1-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Ene/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'2/') or fn:contains(fn:substring($date,6,7),'2-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Feb/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'3/') or fn:contains(fn:substring($date,6,7),'3-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Mar/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'4/') or fn:contains(fn:substring($date,6,7),'4-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Abr/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'5/') or fn:contains(fn:substring($date,6,7),'5-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/May/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'6/') or fn:contains(fn:substring($date,6,7),'6-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Jun/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'7/') or fn:contains(fn:substring($date,6,7),'7-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Jul/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'8/') or fn:contains(fn:substring($date,6,7),'8-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Ago/',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'9/') or fn:contains(fn:substring($date,6,7),'9-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),'/Sep/',fn:substring(fn:string($date),1,4))
 else 
 ''     
};

declare function local:formatDateLong($date as xs:string)
as xs:string {
 if( fn:contains(fn:substring($date,6,8),'10/') or fn:contains(fn:substring($date,6,8),'10-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Octubre, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,8),'11/') or fn:contains(fn:substring($date,6,8),'11-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Noviembre, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,8),'12/') or fn:contains(fn:substring($date,6,8),'12-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Diciembre, ',fn:substring(fn:string($date),1,4))
else if( fn:contains(fn:substring($date,6,7),'1/') or fn:contains(fn:substring($date,6,7),'1-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Enero, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'2/') or fn:contains(fn:substring($date,6,7),'2-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Febrero, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'3/') or fn:contains(fn:substring($date,6,7),'3-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Marzo, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'4/') or fn:contains(fn:substring($date,6,7),'4-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Abril, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'5/') or fn:contains(fn:substring($date,6,7),'5-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Mayo, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'6/') or fn:contains(fn:substring($date,6,7),'6-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Junio, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'7/') or fn:contains(fn:substring($date,6,7),'7-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Julio, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'8/') or fn:contains(fn:substring($date,6,7),'8-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Agosto, ',fn:substring(fn:string($date),1,4))
 else if( fn:contains(fn:substring($date,6,7),'9/') or fn:contains(fn:substring($date,6,7),'9-'))then
    fn:concat ( fn:substring(fn:string($date),9,10),' de Septiembre, ',fn:substring(fn:string($date),1,4))
 
 else 
 ''     
};


local:func($GenerarReportePrepagoRequest)