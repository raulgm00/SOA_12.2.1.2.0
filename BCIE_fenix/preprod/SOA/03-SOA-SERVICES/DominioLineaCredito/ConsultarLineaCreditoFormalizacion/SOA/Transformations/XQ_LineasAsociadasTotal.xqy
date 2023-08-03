xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ope="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope1 = "http://www.bcie.org/OperacionBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $outConsultarOperacionesAsociadas.response as element() (:: schema-element(ope:ConsultarOperacionesAsociadasResponse) ::) external;

declare function local:funcXq_lineasasociadastotal($outConsultarOperacionesAsociadas.response as element() (:: schema-element(ope:ConsultarOperacionesAsociadasResponse) ::)) as element() (:: schema-element(ope:ConsultarOperacionesAsociadasResponse) ::) {
    <ope:ConsultarOperacionesAsociadasResponse>
        <ope:Operacion>
            <ope1:idOperacion></ope1:idOperacion>
            <ope1:contrato>
                { for $lineaCreditoAsociada in $outConsultarOperacionesAsociadas.response/ope:Operacion/ope1:contrato/con:LineaCredito
                return
                <con:LineaCredito>
                    <lin:idLineaCredito>{fn:data($lineaCreditoAsociada/lin:idLineaCredito)}</lin:idLineaCredito>
                    <lin:NumeroLineaCredito>{fn:data($lineaCreditoAsociada/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                </con:LineaCredito>
                }
            </ope1:contrato>
        </ope:Operacion>
    </ope:ConsultarOperacionesAsociadasResponse>
};

local:funcXq_lineasasociadastotal($outConsultarOperacionesAsociadas.response)
