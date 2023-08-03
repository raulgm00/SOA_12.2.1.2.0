xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace pre="http://www.bcie.org/PrepagoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pre1 = "http://www.bcie.org/PrepagoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare variable $outInvokeConsultarPrepago.ConsultarPrepagoResponse as element() (:: schema-element(pre:ConsultarPrepagoResponse) ::) external;
declare variable $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse as element() (:: schema-element(pre:ExtraerReportePrepagoResponse) ::) external;
declare variable $Prepago.ExtraerReportePrepagoResponse as element() (:: schema-element(pre:ExtraerReportePrepagoResponse) ::) external;

declare function local:funcConsultarprepago_extraerreporte_to_informeprepago($outInvokeConsultarPrepago.ConsultarPrepagoResponse as element() (:: schema-element(pre:ConsultarPrepagoResponse) ::), 
                                                                             $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse as element() (:: schema-element(pre:ExtraerReportePrepagoResponse) ::),
                                                                             $Prepago.ExtraerReportePrepagoResponse as element() (:: schema-element(pre:ExtraerReportePrepagoResponse) ::)) 
                                                                             as element() (:: schema-element(pre:InformePrepagoResponse) ::) {
    <pre:InformePrepagoResponse>
        <pre:Prepago>
            <atr:id>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/atr:id)}</atr:id>
            <pre1:FechaSolicitud>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:FechaSolicitud)}</pre1:FechaSolicitud>
            <pre1:FechaPrepago>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:FechaPrepago)}</pre1:FechaPrepago>
            {
            for $monto in $outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Monto
            return
            <pre1:Monto>
                <com:tipo>
                    <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                </com:tipo>
                <com:importe>{fn:data($monto/com:importe)}</com:importe>
                <com:moneda>
                    <cat:Descripcion>{fn:data($monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                </com:moneda>
            </pre1:Monto>
            }
            <pre1:Tipo>
                <cat:Descripcion>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Tipo/cat:Descripcion)}</cat:Descripcion>
            </pre1:Tipo>
            {
                if ($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion)
                then 
                        <pre1:Resolucion>
                          <cat:Id>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:Id)}</cat:Id>
                          <cat:Descripcion>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:Descripcion)}</cat:Descripcion>
                          <cat:DescripcionCorta>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                          <cat:estatus>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:estatus)}</cat:estatus>
                          <cat:codigoExterno>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:codigoExterno)}</cat:codigoExterno>
                        </pre1:Resolucion>
                else ()
            }
            <pre1:Operacion>
                <ope:idOperacion>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Operacion/ope:idOperacion)}</ope:idOperacion>
                <ope:nombre>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Operacion/ope:nombre)}</ope:nombre>
            </pre1:Operacion>
            <pre1:Cliente>
                <cli:idFacturador>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Cliente/cli:idFacturador)}</cli:idFacturador>
                <cli:razonSocial>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Cliente/cli:razonSocial)}</cli:razonSocial>
                <cli:pais>
                    <cat:Descripcion>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                </cli:pais>
            </pre1:Cliente>
          {
           for $cargo in $outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Cargo
           return
            <pre1:Cargo>
                <pre1:Rol>
                    <cat:Id>{fn:data($cargo/pre1:Rol/cat:Id)}</cat:Id>
                    <cat:Descripcion>{fn:data($cargo/pre1:Rol/cat:Descripcion)}</cat:Descripcion>
                </pre1:Rol>
                {
                for $monto in $cargo/pre1:Monto
                return
                <pre1:Monto>
                    <com:tipo>
                        <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                    </com:tipo>
                   <com:importe>
					   {if(fn:string($monto/com:tipo/cat:Descripcion)='MONTO_PENALIDAD') 
                          then  
                               fn:sum (for $monto in $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad/pre1:Monto[com:tipo/cat:Descripcion ='PENALIDAD']/com:importe
                               return $monto)  
                          else 
                              fn:data($monto/com:importe)}
                    </com:importe>
                    <com:moneda>
                        <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
                }
            </pre1:Cargo>
            }
            {
                for $InfoBanco in $outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:InfoBanco
                return 
                <pre1:InfoBanco>
                    <ges:banco>{fn:data($InfoBanco/ges:banco)}</ges:banco>
                    <ges:SWIFTBanco>{fn:data($InfoBanco/ges:SWIFTBanco)}</ges:SWIFTBanco>
                    <ges:cuentaNo>{fn:data($InfoBanco/ges:cuentaNo)}</ges:cuentaNo>
                    <ges:beneficiario>{fn:data($InfoBanco/ges:beneficiario)}</ges:beneficiario>
                    <ges:SWIFTBeneficiario>{fn:data($InfoBanco/ges:SWIFTBeneficiario)}</ges:SWIFTBeneficiario>
                    <ges:Referencia>{fn:data($InfoBanco/ges:Referencia)}</ges:Referencia>
                </pre1:InfoBanco>
            }
        </pre:Prepago>
        
        <pre:Reporte>
        {
        for $consolidado in $Prepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Consolidado
        return
            <pre1:Consolidado>
            {
            for $monto in $consolidado/pre1:Monto
            return
                <pre1:Monto>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($monto/com:importe)}</com:importe>
                    <com:moneda>
                        <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
              }
            </pre1:Consolidado>
            }
          {
          for $observacion in $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Observaciones
          return
            <pre1:Observaciones>
                <pre1:Rol>
                    <cat:DescripcionCorta>{fn:data($observacion/pre1:Rol/cat:DescripcionCorta)}</cat:DescripcionCorta>
                </pre1:Rol>
                <pre1:Observacion>{if(fn:string(fn:substring-after(fn:substring-after(fn:data($observacion/pre1:Rol/cat:DescripcionCorta),'_'),'_'))!='')then fn:concat(fn:substring-after(fn:substring-after(fn:data($observacion/pre1:Rol/cat:DescripcionCorta),'_'),'_'),'-',fn:data($observacion/pre1:Observacion))else (fn:data($observacion/pre1:Observacion))}</pre1:Observacion>
            </pre1:Observaciones>
            }
            {
            for $penalidad in $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad
            return
            <pre1:Penalidad>
                <des:idFacturador>{fn:data($penalidad/des:idFacturador)}</des:idFacturador>
                {
                for  $monto in $penalidad/pre1:Monto
                return
                <pre1:Monto>
                    <com:tipo>
                        <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{fn:data($monto/com:importe)}</com:importe>
                    <com:moneda>
                         <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
                }
                <pre1:Resolucion>{fn:data($penalidad/pre1:Resolucion)}</pre1:Resolucion>
                <pre1:Inicio>{fn:data($penalidad/pre1:Inicio)}</pre1:Inicio>
                <pre1:Fin>{fn:data($penalidad/pre1:Fin)}</pre1:Fin>
                <pre1:Plazo>{fn:data($penalidad/pre1:Plazo)}</pre1:Plazo>
                <pre1:Tasa>{fn:data($penalidad/pre1:Tasa)}</pre1:Tasa>
                <pre1:ProximoPago>{fn:data($penalidad/pre1:ProximoPago)}</pre1:ProximoPago>
                <pre1:ConFuentes>{fn:data($penalidad/pre1:ConFuentes)}</pre1:ConFuentes>
                <pre1:Spread>{fn:data($penalidad/pre1:Spread)}</pre1:Spread>
                <pre1:FraccionLibor>{fn:data($penalidad/pre1:FraccionLibor)}</pre1:FraccionLibor>
            </pre1:Penalidad>
            }
            {
            for $condicion in $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:CondicionesEspeciales
            return
            <pre1:CondicionesEspeciales>
                <pre1:Linea>
                    <lin:NumeroLineaCredito>{fn:data($condicion/pre1:Linea/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                    <lin:Descripcion>{fn:data($condicion/pre1:Linea/lin:Descripcion)}</lin:Descripcion>
                </pre1:Linea>
            </pre1:CondicionesEspeciales>
            }
            {
            for $cobertura in $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Coberturas
            return
            <pre1:Coberturas>
                    <des:idFacturador>{fn:data($cobertura/des:idFacturador)}</des:idFacturador>
                <pre1:Linea>
                    <lin:NumeroLineaCredito>{fn:data($cobertura/pre1:Linea/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                </pre1:Linea>
                <pre1:Contraparte>
                    <cat:Descripcion>{fn:data($cobertura/pre1:Contraparte/cat:Descripcion)}</cat:Descripcion>
                    <cat:codigoExterno>{fn:data($cobertura/pre1:Contraparte/cat:codigoExterno)}</cat:codigoExterno>
                </pre1:Contraparte>
                <pre1:FechaEfectiva>{fn:data($cobertura/pre1:FechaEfectiva)}</pre1:FechaEfectiva>
                <pre1:FechaVencimiento>{fn:data($cobertura/pre1:FechaVencimiento)}</pre1:FechaVencimiento>
            </pre1:Coberturas>
            }
            {
            for $venta in $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:VentaCartera
            return
            <pre1:VentaCartera>
                    <des:idFacturador>{fn:data($venta/des:idFacturador)}</des:idFacturador>
                <pre1:Linea>
                    <lin:NumeroLineaCredito>{fn:data($venta/pre1:Linea/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                    <lin:Fondo>{fn:data($venta/pre1:Linea/lin:Fondo)}</lin:Fondo>
                </pre1:Linea>
            </pre1:VentaCartera>
            }
            {
            for $fuente in $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse/pre:Reporte/pre1:FuentesExternas
            return
            <pre1:FuentesExternas>
                    <des:idFacturador>{fn:data($fuente/des:idFacturador)}</des:idFacturador>
                <pre1:Linea>
                    <lin:NumeroLineaCredito>{fn:data($fuente/pre1:Linea/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                </pre1:Linea>
                <pre1:Fuente>
                    <lin:codigoLineaPasiva>{fn:data($fuente/pre1:Fuente/lin:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                    <lin:Descripcion>{fn:data($fuente/pre1:Fuente/lin:Descripcion)}</lin:Descripcion>
                    <lin:Monto>
                        <com:importe>{fn:data($fuente/pre1:Fuente/lin:Monto/com:importe)}</com:importe>
                    </lin:Monto>
                </pre1:Fuente>
            </pre1:FuentesExternas>
            }
        </pre:Reporte>
    </pre:InformePrepagoResponse>
};

local:funcConsultarprepago_extraerreporte_to_informeprepago($outInvokeConsultarPrepago.ConsultarPrepagoResponse, $outInvokeExtraerReportePrepago.ExtraerReportePrepagoResponse, $Prepago.ExtraerReportePrepagoResponse)
