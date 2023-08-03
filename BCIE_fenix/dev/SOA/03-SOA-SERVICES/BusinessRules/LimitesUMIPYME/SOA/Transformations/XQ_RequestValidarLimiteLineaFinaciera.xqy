xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

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
declare variable $outConsultarImporteTransito.response as element() (:: schema-element(lin:ConsultarImporteTransitoResponse) ::) external;
declare variable $outConsultarSaldoCartera.response as element() (:: schema-element(des:ConsultarSaldoCarteraResponse) ::) external;
declare variable $outConsultarContrato.response as element() (:: schema-element(lin:ConsultarFLEXCUBEResponse) ::) external;

declare function local:funcXq_requestvalidarlimitelineafinaciera($outConsultarLineaCreditoByIdLineaCredito.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::), 
                                                                 $outConsultaDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::), 
                                                                 $outConsultarImporteTransito.response as element() (:: schema-element(lin:ConsultarImporteTransitoResponse) ::), 
                                                                 $outConsultarSaldoCartera.response as element() (:: schema-element(des:ConsultarSaldoCarteraResponse) ::), 
                                                                 $outConsultarContrato.response as element() (:: schema-element(lin:ConsultarFLEXCUBEResponse) ::)) 
                                                                 as element() (:: schema-element(des:ValidarLimiteLineaFinacieraRequest) ::) {
    <des:ValidarLimiteLineaFinacieraRequest>
        <des:LineaCredito>
            <lin1:idLineaCredito></lin1:idLineaCredito>
            <lin1:idContrato></lin1:idContrato>
            <lin1:NumeroLineaCredito>{fn:data($outConsultarLineaCreditoByIdLineaCredito.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:MontoLinea>{fn:data($outConsultarContrato.response/lin:TipoContrato/lin1:LineaCredito/lin1:MontoLinea)}</lin1:MontoLinea>
            {
                for $MontoOutConsultarImporteTransito in $outConsultarImporteTransito.response/lin:LineaCredito/lin1:Monto
                return 
                <lin1:Monto>
                  {
                    $MontoOutConsultarImporteTransito/*
                  }
                </lin1:Monto>
            }
            {
                for $MontoOutConsultarSaldoCartera in $outConsultarSaldoCartera.response/des:Monto
                return 
                <lin1:Monto>
                  {
                    $MontoOutConsultarSaldoCartera/*
                  }
                </lin1:Monto>
            }
            <lin1:ContratoDesembolso>
                <des1:Programa>
                    <her:LineaFinanciera>
                        <cat:codigoExterno>{fn:data($outConsultaDesembolsoById.response/des:ContratoDesembolso/des1:Programa/her:LineaFinanciera/cat:codigoExterno)}</cat:codigoExterno>
                    </her:LineaFinanciera>
                </des1:Programa>
            </lin1:ContratoDesembolso>
        </des:LineaCredito>
    </des:ValidarLimiteLineaFinacieraRequest>
};

local:funcXq_requestvalidarlimitelineafinaciera($outConsultarLineaCreditoByIdLineaCredito.response, $outConsultaDesembolsoById.response, $outConsultarImporteTransito.response, $outConsultarSaldoCartera.response, $outConsultarContrato.response)
