xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace des1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

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

declare variable $outInvokeConsultarCondicionByEventoCualquier.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(des1:CondicionesDesembolsoRequest) ::) external;

declare function local:funcXq_condicionesbyevento_to_condicionesdemanda_request($outInvokeConsultarCondicionByEventoCualquier.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::),
                                                                                $inputVariable.request as element() (:: schema-element(des1:CondicionesDesembolsoRequest) ::)) 
                                                                                 as element() (:: schema-element(con:CondicionesDemandaRequest) ::) {


    <con:CondicionesDemandaRequest>
        <con:CondicionesDemanda>
            <con1:ContratoDesembolso>
                {
                    if ($inputVariable.request/des1:idContratoDesembolso)
                    then <des:idNumeroContrato>{fn:data($inputVariable.request/des1:idContratoDesembolso)}</des:idNumeroContrato>
                    else ()
                }
            </con1:ContratoDesembolso>
            {
            for $condicionByEvento in $outInvokeConsultarCondicionByEventoCualquier.response/con:Condicion
            return
            <con1:Condicion>
                <con1:idCondicion>{fn:data($condicionByEvento/con1:idCondicion)}</con1:idCondicion>         
            </con1:Condicion>
            }
            
            <con1:EventoCondicion>
              <cat:Id>3</cat:Id>
            </con1:EventoCondicion>
        </con:CondicionesDemanda>
    </con:CondicionesDemandaRequest>
};

local:funcXq_condicionesbyevento_to_condicionesdemanda_request($outInvokeConsultarCondicionByEventoCualquier.response, $inputVariable.request)
