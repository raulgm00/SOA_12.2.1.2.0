xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarDesembolsoConfiguracion.ConsultarDesembolsosConfiguracionByIdResponse as element() (:: schema-element(des:ConsultarDesembolsosConfiguracionByIdResponse) ::) external;

declare variable $outPropagarUtilizacion.response as element() (:: schema-element(des:PropagarUtilizacionResponse) ::) external;

declare function local:funcXq_responsepropagardesembolso($outConsultarDesembolsoConfiguracion.ConsultarDesembolsosConfiguracionByIdResponse as element() (:: schema-element(des:ConsultarDesembolsosConfiguracionByIdResponse) ::),$outPropagarUtilizacion.response as element() (:: schema-element(des:PropagarUtilizacionResponse) ::)) as element() (:: schema-element(des:PropagarContratoDesembolsoResponse) ::) {
    <des:PropagarContratoDesembolsoResponse>
        <des:ContratoDesembolso>
        {$outConsultarDesembolsoConfiguracion.ConsultarDesembolsosConfiguracionByIdResponse/des:ContratoDesembolso/*}
        </des:ContratoDesembolso>
        <des:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if(fn:data($outConsultarDesembolsoConfiguracion.ConsultarDesembolsosConfiguracionByIdResponse/des:Resultado/res:message)='AJUSTE_TASA')then
            fn:concat($outPropagarUtilizacion.response/des:Resultado/res:message, " y ajuste de tasa realizado")
            else fn:data($outPropagarUtilizacion.response/des:Resultado/res:message)}</res:message>
        </des:Resultado>
    </des:PropagarContratoDesembolsoResponse>
};

local:funcXq_responsepropagardesembolso($outConsultarDesembolsoConfiguracion.ConsultarDesembolsosConfiguracionByIdResponse,$outPropagarUtilizacion.response)
