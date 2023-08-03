xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ope="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope1 = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $AplicarAmpliacion_OutputVariable.AplicarAmpliacionResponseMessage as element() (:: schema-element(lin:AplicarAmpliacionResponse) ::) external;

declare function local:funcXq_response($AplicarAmpliacion_OutputVariable.AplicarAmpliacionResponseMessage as element() (:: schema-element(lin:AplicarAmpliacionResponse) ::)) as element() (:: schema-element(ope:AplicarEnvioCobroResponse) ::) {
    <ope:AplicarEnvioCobroResponse>
        <ope:LineaCredito>
        {$AplicarAmpliacion_OutputVariable.AplicarAmpliacionResponseMessage/lin:LineaCredito/*}
        </ope:LineaCredito>
        <ope:Resultado>
            <res:result>OK</res:result>
            <res:message>{if (string($AplicarAmpliacion_OutputVariable.AplicarAmpliacionResponseMessage/lin:LineaCredito/lin1:idLineaCredito) != '') then 'Operación exitosa'
            else 'Sin información de la línea'}</res:message>
        </ope:Resultado>
    </ope:AplicarEnvioCobroResponse>
};

local:funcXq_response($AplicarAmpliacion_OutputVariable.AplicarAmpliacionResponseMessage)
