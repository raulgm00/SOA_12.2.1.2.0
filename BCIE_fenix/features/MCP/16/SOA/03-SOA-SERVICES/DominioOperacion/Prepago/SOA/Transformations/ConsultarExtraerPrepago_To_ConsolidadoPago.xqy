xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PrepagoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)


declare namespace ns2="http://www.bcie.org/PrepagoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pre1 = "http://www.bcie.org/PrepagoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $outInvokeConsultarPrepago.ConsultarPrepagoResponse as element() (:: schema-element(ns1:ConsultarPrepagoResponse) ::) external;

declare function local:funcTransformation_2($outInvokeConsultarPrepago.ConsultarPrepagoResponse as element() (:: schema-element(ns1:ConsultarPrepagoResponse) ::)) as element() (:: schema-element(ns1:ExtraerReportePrepagoResponse) ::) {
 let $MonedaLocal := $outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_PREPAGO"]/com:moneda/cat:DescripcionCorta
    let $MontoPrepagoLocal:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_PREPAGO"]/com:importe)
    let $MontoPrepagoDolar:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_PREPAGO" and com:moneda/cat:DescripcionCorta = "USD"]/com:importe)
    let $InteresesLocal:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_INTERESES"]/com:importe)
    let $InteresesDolar:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_INTERESES" and com:moneda/cat:DescripcionCorta = "USD"]/com:importe)
    let $PenalidadLocal:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Cargo/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_PENALIDAD" and com:moneda/cat:DescripcionCorta = $MonedaLocal]/com:importe)
    let $PenalidadDolar:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Cargo/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_PENALIDAD" and com:moneda/cat:DescripcionCorta = "USD"]/com:importe)
    let $CargoTramiteLocal:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Cargo/ns2:Monto[com:tipo/cat:Descripcion = "CARGOS_TRAMITE" and com:moneda/cat:DescripcionCorta = $MonedaLocal]/com:importe)
    let $CargoTramiteDolar:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Cargo/ns2:Monto[com:tipo/cat:Descripcion = "CARGOS_TRAMITE" and com:moneda/cat:DescripcionCorta = "USD"]/com:importe)
    let $OtrosCargosLocal:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Cargo/ns2:Monto[com:tipo/cat:Descripcion = "OTROS_CARGOS" and com:moneda/cat:DescripcionCorta = $MonedaLocal]/com:importe)
    let $OtrosCargosDolar:= fn:sum($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Cargo/ns2:Monto[com:tipo/cat:Descripcion = "OTROS_CARGOS" and com:moneda/cat:DescripcionCorta = "USD"]/com:importe)
    let $TotalPagarLocal:= $MontoPrepagoLocal + $InteresesLocal + $PenalidadLocal + $CargoTramiteLocal + $OtrosCargosLocal
    let $TotalPagarDolar:= $MontoPrepagoDolar + $InteresesDolar + $PenalidadDolar + $CargoTramiteDolar + $OtrosCargosDolar
    return
    <ns1:ExtraerReportePrepagoResponse>
        <ns1:Reporte>
         
             <ns2:Consolidado>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Monto Prepago(principal)</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_PREPAGO</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($MontoPrepagoLocal)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>{fn:data($MonedaLocal)}</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Monto Prepago(principal)</cat:Descripcion>
                </com:tipo>
                  <com:importe>{fn:data($MontoPrepagoDolar)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>USD</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Observacion></ns2:Observacion>
        </ns2:Consolidado>
        <ns2:Consolidado>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Intereses</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_INTERES</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($InteresesLocal)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>{fn:data($MonedaLocal)}</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Intereses</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_INTERES</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($InteresesDolar)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>USD</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
       
            <ns2:Observacion></ns2:Observacion>
        </ns2:Consolidado>
        <ns2:Consolidado>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Penalidad</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_PENALIDAD</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($PenalidadLocal)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>{fn:data($MonedaLocal)}</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Penalidad</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_PENALIDAD</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($PenalidadDolar)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>USD</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
             <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Monto con fuente</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_FUENTES</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_FUENTES"]/com:importe)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_FUENTES"]/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Monto sin fuente</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_SIN_FUENTES</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_SIN_FUENTES"]/com:importe)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>{data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/ns1:Prepago/ns2:Monto[com:tipo/cat:Descripcion = "MONTO_SIN_FUENTES"]/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Observacion></ns2:Observacion>
        </ns2:Consolidado>
        <ns2:Consolidado>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Cargo por tramite</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_TRAMITE</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($CargoTramiteLocal)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>{fn:data($MonedaLocal)}</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Cargo por tramite</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_TRAMITE</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($CargoTramiteDolar)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>USD</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Observacion></ns2:Observacion>
        </ns2:Consolidado>
        <ns2:Consolidado>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Otros Cargos</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_OTROS_CARGOS</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($OtrosCargosLocal)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>{fn:data($MonedaLocal)}</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Otros Cargos</cat:Descripcion>
                    <cat:DescripcionCorta>MONTO_OTROS_CARGOS</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($OtrosCargosDolar)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>USD</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Observacion></ns2:Observacion>
        </ns2:Consolidado>
        
        <ns2:Consolidado>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Total a pagar moneda local</cat:Descripcion>
                </com:tipo>
                  <com:importe>{fn:data($TotalPagarLocal)}</com:importe>
                  <cat:DescripcionCorta>TOTAL_LOCAL</cat:DescripcionCorta>
                <com:moneda>
                    <cat:DescripcionCorta>{fn:data($MonedaLocal)}</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Monto>
                <com:tipo>
                    <cat:Descripcion>Total a pagar en dolares</cat:Descripcion>
                     <cat:DescripcionCorta>TOTAL_USD</cat:DescripcionCorta>
                </com:tipo>
                  <com:importe>{fn:data($TotalPagarDolar)}</com:importe>
                <com:moneda>
                    <cat:DescripcionCorta>USD</cat:DescripcionCorta>
                </com:moneda>
            </ns2:Monto>
            <ns2:Observacion></ns2:Observacion>
        </ns2:Consolidado>
        
        
        </ns1:Reporte>
    </ns1:ExtraerReportePrepagoResponse>
};

local:funcTransformation_2($outInvokeConsultarPrepago.ConsultarPrepagoResponse)
