xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd"::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage as element() (:: schema-element(lin:AplicarAmpliacionRequest) ::) external;
declare variable $ConsultarListaLineasCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::) external;
declare variable $CountLinea as xs:int external;

declare function local:funcXq_consultarlistalineasout_to_propagardesembolsosin($AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage as element() (:: schema-element(lin:AplicarAmpliacionRequest) ::),$ConsultarListaLineasCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::),$CountLinea as xs:int) as element() (:: schema-element(des:PropagarDesembolsosRequest) ::) {
    <des:PropagarDesembolsosRequest>
        {
        for $idDesembolsosNuevos in $ConsultarListaLineasCredito_OutputVariable.response/lin:LineaCredito[$CountLinea]/lin:Operacion/ope:contrato/con:LineaCredito/lin1:ContratoDesembolso
        let $ContratoNuevo := distinct-values($AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage/lin:SolicitudDesembolso/des1:ContratoDesembolso[des1:idDesembolso = $idDesembolsosNuevos/des1:idDesembolso]/des1:idDesembolso)
        return
        if(string-length(string($ContratoNuevo))>0)
         then
        <des:idDesembolso>{fn:data($idDesembolsosNuevos/des1:idDesembolso)}</des:idDesembolso>
        else()
        }
    </des:PropagarDesembolsosRequest>
};

local:funcXq_consultarlistalineasout_to_propagardesembolsosin($AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage,$ConsultarListaLineasCredito_OutputVariable.response,$CountLinea)
