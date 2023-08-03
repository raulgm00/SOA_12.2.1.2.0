xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/GenerarReciboPago.xsd" ::)
declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace rec = "http://www.bcie.org/herramientaOfismatica/ReciboPago";

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $CrearReciboPagoRequest as element() (:: schema-element(ns1:CrearReciboPagoRequest) ::) external;

declare function local:func($CrearReciboPagoRequest as element() (:: schema-element(ns1:CrearReciboPagoRequest) ::)) as element() (:: schema-element(ns2:generarReciboPago) ::) {
    <ns2:generarReciboPago>
        <ReciboPago>
            <rec:Recibo>
                <rec:fechaProceso>{local:formatReportDate(substring($CrearReciboPagoRequest/ns1:ReciboPago/ges:FechaEjecucion/text(),1,10))}</rec:fechaProceso>
                <rec:numRecibo>{fn:data($CrearReciboPagoRequest/ns1:ReciboPago/ges:Recibo)}</rec:numRecibo>
                <rec:cliente>{fn:concat($CrearReciboPagoRequest/ns1:ReciboPago/ges:Cliente/cli:idFacturador/text()," ",$CrearReciboPagoRequest/ns1:ReciboPago/ges:Cliente/cli:razonSocial/text())}</rec:cliente>
                <rec:fechaEfectiva>{local:formatReportDate(substring($CrearReciboPagoRequest/ns1:ReciboPago/ges:FechaEfectiva/text(),1,10))}</rec:fechaEfectiva>
                {
                for $tablasDetalle in distinct-values($CrearReciboPagoRequest/ns1:ReciboPago/ges:Desembolsos/des:monto/com:moneda[cat:DescripcionCorta = 'PAGADA']/cat:codigoExterno)
                let $totalPagada := fn:sum($CrearReciboPagoRequest/ns1:ReciboPago/ges:Desembolsos/des:monto[com:moneda/cat:DescripcionCorta = 'PAGADA' and com:moneda/cat:codigoExterno = $tablasDetalle]/com:importe)
                    return 
                      if ( fn:string($tablasDetalle)='USD' )
                      then  
                          <rec:tablaDetalleUSD>
                           {
                            for $Desembolsos in $CrearReciboPagoRequest/ns1:ReciboPago/ges:Desembolsos[des:monto/com:moneda/cat:codigoExterno = $tablasDetalle]
                            return
                            <rec:detalles>
                              <rec:prestamo>{fn:data($Desembolsos/des:idFacturador)}</rec:prestamo>
                              <rec:desembolso>{fn:data($Desembolsos/des:referencia)}</rec:desembolso>
                              <rec:detalle>{fn:data($Desembolsos/des:monto/com:moneda[cat:DescripcionCorta = 'PAGADA']/cat:Descripcion)}</rec:detalle>
                              <rec:monedaAdeudo>{fn:data($Desembolsos/des:monto/com:moneda[cat:DescripcionCorta = 'ADEUDADA']/cat:codigoExterno)}</rec:monedaAdeudo>
                              <rec:cantidadAdeudada>{local:formatNumber($Desembolsos/des:monto[com:moneda/cat:DescripcionCorta = 'ADEUDADA']/com:importe)}</rec:cantidadAdeudada>
                              <rec:monedaPagada>{fn:data($Desembolsos/des:monto/com:moneda[cat:DescripcionCorta = 'PAGADA']/cat:codigoExterno)}</rec:monedaPagada>
                              <rec:cantidadPagada>{local:formatNumber($Desembolsos/des:monto[com:moneda/cat:DescripcionCorta = 'PAGADA']/com:importe)}</rec:cantidadPagada>
                            </rec:detalles>
                           }
                           <rec:totalPagada>{local:formatNumber($totalPagada)}</rec:totalPagada>
                          </rec:tablaDetalleUSD>
                      else 
                          <rec:tablaDetalle>
                            {
                            for $Desembolsos in $CrearReciboPagoRequest/ns1:ReciboPago/ges:Desembolsos[des:monto/com:moneda/cat:codigoExterno = $tablasDetalle]
                            return
                            <rec:detalles>
                              <rec:prestamo>{fn:data($Desembolsos/des:idFacturador)}</rec:prestamo>
                              <rec:desembolso>{fn:data($Desembolsos/des:referencia)}</rec:desembolso>
                              <rec:detalle>{fn:data($Desembolsos/des:monto/com:moneda[cat:DescripcionCorta = 'PAGADA']/cat:Descripcion)}</rec:detalle>
                              <rec:monedaAdeudo>{fn:data($Desembolsos/des:monto/com:moneda[cat:DescripcionCorta = 'ADEUDADA']/cat:codigoExterno)}</rec:monedaAdeudo>
                              <rec:cantidadAdeudada>{local:formatNumber($Desembolsos/des:monto[com:moneda/cat:DescripcionCorta = 'ADEUDADA']/com:importe)}</rec:cantidadAdeudada>
                              <rec:monedaPagada>{fn:data($Desembolsos/des:monto/com:moneda[cat:DescripcionCorta = 'PAGADA']/cat:codigoExterno)}</rec:monedaPagada>
                              <rec:cantidadPagada>{local:formatNumber($Desembolsos/des:monto[com:moneda/cat:DescripcionCorta = 'PAGADA']/com:importe)}</rec:cantidadPagada>
                            </rec:detalles>
                           }
                           <rec:totalPagada>{local:formatNumber($totalPagada)}</rec:totalPagada>
                          </rec:tablaDetalle>
                }
            </rec:Recibo>
        </ReciboPago>
    </ns2:generarReciboPago>
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
return concat($dia,"/",$mes,"/",$año)
};
local:func($CrearReciboPagoRequest)
