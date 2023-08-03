xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd"::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage as element() (:: schema-element(lin:AplicarAmpliacionRequest) ::) external;
declare variable $ConsultarListaLineasCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::) external;
declare variable $CountLinea as xs:int external;

declare function local:funcXq_excluordesembolsosnuevos_to_enviarcobroin($AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage as element() (:: schema-element(lin:AplicarAmpliacionRequest) ::), 
                                                                        $ConsultarListaLineasCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::),$CountLinea as xs:int) 
                                                                        as element() (:: schema-element(des:EnvioCobroBPELRequest) ::) {
    <des:EnvioCobroBPELRequest>
     {
        for $idDesembolsosViejos in $ConsultarListaLineasCredito_OutputVariable.response/lin:LineaCredito[$CountLinea]/lin:Operacion/ope:contrato/con:LineaCredito/lin1:ContratoDesembolso
        let $ContratoNuevo := distinct-values($AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage/lin:SolicitudDesembolso/des1:ContratoDesembolso[des1:idDesembolso = $idDesembolsosViejos/des1:idDesembolso]/des1:idDesembolso)
        return
        if(string-length(string($ContratoNuevo))<=0 and ($idDesembolsosViejos/des1:estado/cat:Id = 21))
         then
        <des:Desembolsos>
            <des1:idDesembolso>{fn:data($idDesembolsosViejos/des1:idDesembolso)}</des1:idDesembolso>
            <des1:estado>
                <cat:Id>24</cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>Creado</cat:DescripcionCorta>
                <cat:estatus>1</cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </des1:estado>
            <des1:fechaEfectiva>{fn:data($idDesembolsosViejos/des1:fechaEfectiva)}</des1:fechaEfectiva>
            <des1:fechaRegistro>{fn:data($ConsultarListaLineasCredito_OutputVariable.response/lin:LineaCredito[$CountLinea]/lin:Operacion/ope:contrato/con:LineaCredito/lin1:FechaRegistro)}</des1:fechaRegistro>
        </des:Desembolsos>
        else()
        }
    </des:EnvioCobroBPELRequest>
};

local:funcXq_excluordesembolsosnuevos_to_enviarcobroin($AplicarAmpliacion_InputVariable.AplicarAmpliacionRequestMessage, $ConsultarListaLineasCredito_OutputVariable.response,$CountLinea)
