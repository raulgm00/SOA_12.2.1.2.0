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

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare variable $MontosFuentes.ExtraerReportePrepagoResponse as element() (:: schema-element(pre:ExtraerReportePrepagoResponse) ::) external;
declare variable $outInvokeConsultarPrepago.ConsultarPrepagoResponse as element() (:: schema-element(pre:ConsultarPrepagoResponse) ::) external;

declare function local:funcMontosinfuentes_to_consultarprepago_($MontosFuentes.ExtraerReportePrepagoResponse as element() (:: schema-element(pre:ExtraerReportePrepagoResponse) ::), 
                                                                $outInvokeConsultarPrepago.ConsultarPrepagoResponse as element() (:: schema-element(pre:ConsultarPrepagoResponse) ::)) 
                                                                as element() (:: schema-element(pre:ConsultarPrepagoResponse) ::) {
    <pre:ConsultarPrepagoResponse>
         <pre:Prepago>
            <atr:id>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/atr:id)}</atr:id>
            <pre1:FechaSolicitud>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:FechaSolicitud)}</pre1:FechaSolicitud>
            <pre1:FechaPrepago>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:FechaPrepago)}</pre1:FechaPrepago>
             <pre1:Monto>
                    <com:tipo>
                        <cat:Descripcion>MONTO_FUENTES</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{data($MontosFuentes.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad/pre1:Monto[com:tipo/cat:Descripcion = 'MONTO_FUENTES']/com:importe)}</com:importe>
                     <com:moneda>
                        <cat:DescripcionCorta>{fn:data($MontosFuentes.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad/pre1:Monto[com:tipo/cat:Descripcion = 'MONTO_FUENTES']/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
                <pre1:Monto>
                    <com:tipo>
                        <cat:Descripcion>MONTO_SIN_FUENTES</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{data($MontosFuentes.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad/pre1:Monto[com:tipo/cat:Descripcion = 'MONTO_SIN_FUENTES']/com:importe)}</com:importe>
                     <com:moneda>
                        <cat:DescripcionCorta>{fn:data($MontosFuentes.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad/pre1:Monto[com:tipo/cat:Descripcion = 'MONTO_SIN_FUENTES']/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
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
                         {
                             if ($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:Id)
                             then <cat:Id>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:Id)}</cat:Id>
                             else ()
                         }
                         {
                             if ($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:Descripcion)
                             then <cat:Descripcion>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:Descripcion)}</cat:Descripcion>
                             else ()
                         }
                         {
                             if ($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:DescripcionCorta)
                             then <cat:DescripcionCorta>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                             else ()
                         }
                         {
                             if ($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:estatus)
                             then <cat:estatus>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:estatus)}</cat:estatus>
                             else ()
                         }
                         {
                             if ($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:codigoExterno)
                             then <cat:codigoExterno>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Resolucion/cat:codigoExterno)}</cat:codigoExterno>
                             else ()
                         }
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
           for $cargo in $outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago[1]/pre1:Cargo
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
                    <com:importe>{fn:data($monto/com:importe)}</com:importe>
                    <com:moneda>
                        <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
                }
                {
                if (fn:data($cargo/pre1:Rol/cat:Id)= 7) then
                <pre1:Monto>
                    <com:tipo>
                        <cat:Descripcion>MONTO_PENALIDAD</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{fn:data($MontosFuentes.ExtraerReportePrepagoResponse/pre:Reporte/pre1:Penalidad/pre1:Monto[com:tipo/cat:Descripcion = 'MONTO_SIN_FUENTES']/com:importe)}</com:importe>
                    <com:moneda>
                        <cat:DescripcionCorta>{fn:data($outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago/pre1:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    </com:moneda>
                </pre1:Monto>
                else ()
                }
                <pre1:Observacion></pre1:Observacion>
            </pre1:Cargo>
            }
             {
                 for $InfoBanco in $outInvokeConsultarPrepago.ConsultarPrepagoResponse/pre:Prepago[1]/pre1:InfoBanco
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
    </pre:ConsultarPrepagoResponse>
};

local:funcMontosinfuentes_to_consultarprepago_($MontosFuentes.ExtraerReportePrepagoResponse, $outInvokeConsultarPrepago.ConsultarPrepagoResponse)
