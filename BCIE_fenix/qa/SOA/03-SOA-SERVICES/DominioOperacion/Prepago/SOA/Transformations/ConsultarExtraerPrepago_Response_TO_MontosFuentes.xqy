xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace pre="http://www.bcie.org/PrepagoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pre1 = "http://www.bcie.org/PrepagoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $outInvokeConsultarPrepago.ConsultarPrepagoResponse as element() (:: schema-element(pre:ConsultarPrepagoResponse) ::) external;
declare variable $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse as element() (:: schema-element(pre:ExtraerReportePrepagoResponse) ::) external;

declare function local:funcConsultarextraerprepago_response_to_montosfuentes($outInvokeConsultarPrepago.ConsultarPrepagoResponse as element() (:: schema-element(pre:ConsultarPrepagoResponse) ::), 
                                                                             $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse as element() (:: schema-element(pre:ExtraerReportePrepagoResponse) ::)) 
                                                                             as element() (:: schema-element(pre:ExtraerReportePrepagoResponse) ::) {
    <pre:ExtraerReportePrepagoResponse>
        <pre:Reporte>
        {
        for $monto in $outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Cargo[pre1:Rol/cat:Id = 21]
        return
            <pre1:Consolidado>
                <pre1:Monto>
                 <com:importe>{fn:data($monto/pre1:Monto[com:tipo/cat:Descripcion = 'OTROS_CARGOS']/com:importe)}</com:importe>
                    <com:moneda>
                        <cat:Descripcion>{if (fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Monto[com:tipo/cat:Descripcion = 'MONTO_PREPAGO']/com:moneda/cat:DescripcionCorta)= fn:data($monto/pre1:Monto[com:tipo/cat:Descripcion = 'OTROS_CARGOS']/com:moneda/cat:DescripcionCorta)) then 1 else 0}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($monto/pre1:Monto[com:tipo/cat:Descripcion = 'OTROS_CARGOS']/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
                <pre1:Observacion></pre1:Observacion>
            </pre1:Consolidado>
            }
            <pre1:Penalidad>
                <pre1:Monto>
                    <com:tipo>
                        <cat:Descripcion>MONTO_FUENTES</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{fn:sum
                (for $monto in $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad[pre1:ConFuentes = 'S']/pre1:Monto[com:tipo/cat:Descripcion ='PENALIDAD']/com:importe
                return $monto)}</com:importe>
                    <com:moneda>
                        <cat:DescripcionCorta>{fn:data($outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad[1]/pre1:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
                 <pre1:Monto>
                    <com:tipo>
                        <cat:Descripcion>MONTO_SIN_FUENTES</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{fn:sum
                (for $monto in $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad[pre1:ConFuentes = 'N']/pre1:Monto[com:tipo/cat:Descripcion ='PENALIDAD']/com:importe
                return $monto)}</com:importe>
                <com:moneda>
                        <cat:DescripcionCorta>{fn:data($outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad[1]/pre1:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
            </pre1:Penalidad>
        </pre:Reporte>
    </pre:ExtraerReportePrepagoResponse>
};

local:funcConsultarextraerprepago_response_to_montosfuentes($outInvokeConsultarPrepago.ConsultarPrepagoResponse, $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse)
