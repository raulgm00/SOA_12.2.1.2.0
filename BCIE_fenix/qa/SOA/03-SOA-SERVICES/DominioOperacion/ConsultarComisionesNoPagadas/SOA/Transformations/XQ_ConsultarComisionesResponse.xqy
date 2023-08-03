xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace com="http://www.bcie.org/ComisionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarComision.response as element() (:: schema-element(com:ConsultarComisionResponse) ::) external;

declare function local:funcXq_consultarcomisionesresponse($outConsultarComision.response as element() (:: schema-element(com:ConsultarComisionResponse) ::)) as element() (:: schema-element(des:ConsultarComisionesNoCobradasBPELResponse) ::) {
    <des:ConsultarComisionesNoCobradasBPELResponse>
        {
            for $Comision in $outConsultarComision.response/com:Comision
            return 
            <des:Comision>
                {
                    $Comision/*
                }
            </des:Comision>
        }
        <des:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Realizada Correctamente</res:message>
        </des:Resultado>
    </des:ConsultarComisionesNoCobradasBPELResponse>
};

local:funcXq_consultarcomisionesresponse($outConsultarComision.response)
