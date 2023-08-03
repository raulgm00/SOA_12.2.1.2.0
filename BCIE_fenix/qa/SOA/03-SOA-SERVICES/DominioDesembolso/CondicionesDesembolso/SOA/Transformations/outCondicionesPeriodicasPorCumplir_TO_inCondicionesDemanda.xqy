xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $outInvokeConsultarInformacionRegla.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(des:CondicionesDesembolsoRequest) ::) external;
declare variable $outInvokeCondicionesPeriodicasPorCumplir.response as element() (:: schema-element(con:CondicionesPeriodicasPorCumplirResponse) ::) external;

declare function local:funcOutcondicionesperiodicasporcumplir_to_incondicionesdemanda($outInvokeConsultarInformacionRegla.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::), 
                                                                                      $inputVariable.request as element() (:: schema-element(des:CondicionesDesembolsoRequest) ::),
                                                                                      $outInvokeCondicionesPeriodicasPorCumplir.response as element() (:: schema-element(con:CondicionesPeriodicasPorCumplirResponse) ::)) 
                                                                                      as element() (:: schema-element(con:CondicionesDemandaRequest) ::) {
    <con:CondicionesDemandaRequest>
        <con:CondicionesDemanda>
            <con1:Operacion>
                {
                    if ($outInvokeConsultarInformacionRegla.response/des:Operacion/ope:idOperacion)
                    then <ope:idOperacion>{fn:data($outInvokeConsultarInformacionRegla.response/des:Operacion/ope:idOperacion)}</ope:idOperacion>
                    else ()
                }
                {
                    if ($outInvokeConsultarInformacionRegla.response/des:Operacion/ope:responsable)
                    then <ope:responsable>{fn:data($outInvokeConsultarInformacionRegla.response/des:Operacion/ope:responsable)}</ope:responsable>
                    else ()
                }
                {
                    if ($outInvokeConsultarInformacionRegla.response/des:Operacion/ope:nombre)
                    then <ope:nombre>{fn:data($outInvokeConsultarInformacionRegla.response/des:Operacion/ope:nombre)}</ope:nombre>
                    else ()
                }
            </con1:Operacion>
            <con1:ContratoDesembolso>
                {
                    if ($inputVariable.request/des:idContratoDesembolso)
                    then <des1:idNumeroContrato>{fn:data($inputVariable.request/des:idContratoDesembolso)}</des1:idNumeroContrato>
                    else ()
                }
            </con1:ContratoDesembolso>
            {for $condicion in $outInvokeCondicionesPeriodicasPorCumplir.response/con:Condicion
            return
            <con1:Condicion>
                <con1:idCondicion>{fn:data($condicion/con1:idCondicion)}</con1:idCondicion>
            </con1:Condicion>
            }
        </con:CondicionesDemanda>
    </con:CondicionesDemandaRequest>
};

local:funcOutcondicionesperiodicasporcumplir_to_incondicionesdemanda($outInvokeConsultarInformacionRegla.response, $inputVariable.request, $outInvokeCondicionesPeriodicasPorCumplir.response)
