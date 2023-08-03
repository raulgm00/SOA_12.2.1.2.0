xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $outputVariable.response as element() (:: schema-element(des:ValidarProgramacionResponse) ::) external;
declare variable $InvokeConsultaProgramacionPYD_Out.response as element() (:: schema-element(lin:ConsultarProgramacionPYDResponse) ::) external;

declare function local:funcXq_validaimportesnulos($outputVariable.response as element() (:: schema-element(des:ValidarProgramacionResponse) ::), 
                                                  $InvokeConsultaProgramacionPYD_Out.response as element() (:: schema-element(lin:ConsultarProgramacionPYDResponse) ::)) 
                                                  as element() (:: schema-element(des:ValidarProgramacionResponse) ::) {
    <des:ValidarProgramacionResponse>
        <des:ContratoDesembolso>
            <des1:idDesembolso>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            {for $monto in $outputVariable.response/des:ContratoDesembolso/des1:monto
            return
            <des1:monto>
                {
                    if ($monto/com:tipo)
                    then 
                        <com:tipo>
                          {$monto/com:tipo/*}
                        </com:tipo>
                    else ()
                }
                <com:importe>
                {if (string-length($monto/com:importe/text()) = 0) 
                then 0
                else data($monto/com:importe)
                }
                </com:importe>
                {
                    if ($monto/com:moneda)
                    then 
                        <com:moneda>
                          {$monto/com:moneda/*}
                        </com:moneda>
                    else ()
                }
            </des1:monto>
            }
            {for $montoProgrmacionPYD in $InvokeConsultaProgramacionPYD_Out.response/lin:LineaCredito/lin1:Monto
            return
            <des1:monto>
                {
                    if ($montoProgrmacionPYD/com:tipo)
                    then 
                        <com:tipo>
                          {$montoProgrmacionPYD/com:tipo/*}
                        </com:tipo>
                    else ()
                }
                <com:importe>
                {if (string-length($montoProgrmacionPYD/com:importe/text()) = 0) 
                then 0
                else data($montoProgrmacionPYD/com:importe)
                }
                </com:importe>
                {
                    if ($montoProgrmacionPYD/com:moneda)
                    then 
                        <com:moneda>
                          {$montoProgrmacionPYD/com:moneda/*}
                        </com:moneda>
                    else ()
                }
            </des1:monto>
            }
            {if ($outputVariable.response/des:ContratoDesembolso/des1:estado) then
            <des1:estado>
                {$outputVariable.response/des:ContratoDesembolso/des1:estado/*}
            </des1:estado>
            else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:programado)
                then <des1:programado>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:programado)}</des1:programado>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:fechaEstimadaDesembolsar)
                then <des1:fechaEstimadaDesembolsar>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:fechaEstimadaDesembolsar)}</des1:fechaEstimadaDesembolsar>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:fechaEfectiva)
                then <des1:fechaEfectiva>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:fechaEfectiva)}</des1:fechaEfectiva>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:idLinea)
                then <des1:idLinea>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:idLinea)}</des1:idLinea>
                else ()
            }
        </des:ContratoDesembolso>
    </des:ValidarProgramacionResponse>
};

local:funcXq_validaimportesnulos($outputVariable.response, $InvokeConsultaProgramacionPYD_Out.response)
