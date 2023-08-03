xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd"::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $outConsultarLineaCreditoByIdLineaCredito.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;
declare variable $outConsultaDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;

declare function local:funcXq_consultarlineacredito_and_consultardesembolso_to_consultarimportetransito($outConsultarLineaCreditoByIdLineaCredito.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::), 
                                                                                                        $outConsultaDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::)) 
                                                                                                        as element() (:: schema-element(lin:ConsultarImporteTransitoRequest) ::) {
    <lin:ConsultarImporteTransitoRequest>
        <lin:LineaCredito>
            <lin1:idLineaCredito>{fn:data($outConsultarLineaCreditoByIdLineaCredito.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
            <lin1:idContrato></lin1:idContrato>
            <lin1:NumeroLineaCredito>
            {
              if(fn:data($outConsultaDesembolsoById.response/des:ContratoDesembolso/des1:idLinea = $outConsultarLineaCreditoByIdLineaCredito.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:idLineaCredito))
              then $outConsultaDesembolsoById.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:codigoExterno
              else ()
            }
            </lin1:NumeroLineaCredito>
        </lin:LineaCredito>
    </lin:ConsultarImporteTransitoRequest>
};

local:funcXq_consultarlineacredito_and_consultardesembolso_to_consultarimportetransito($outConsultarLineaCreditoByIdLineaCredito.response, $outConsultaDesembolsoById.response)
