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

declare variable $outputVariable.response as element() (:: schema-element(des:ValidarProgramacionResponse) ::) external;

declare function local:funcXq_agregarmontoevaluado($outputVariable.response as element() (:: schema-element(des:ValidarProgramacionResponse) ::)) as element() (:: schema-element(des:ValidarProgramacionResponse) ::) {
    <des:ValidarProgramacionResponse>
        <des:ContratoDesembolso>
            <des1:idDesembolso>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            {for $monto in $outputVariable.response/des:ContratoDesembolso/des1:monto
            return
            <des1:monto>
                {
                  $monto/*
                }
            </des1:monto>
            }
            <des1:monto>
                <com:tipo>
                  <cat:DescripcionCorta>EVALUADO_PROGRAMACION</cat:DescripcionCorta>
                </com:tipo>
                <com:importe>
                {
                  (xs:decimal(fn:data($outputVariable.response/des:ContratoDesembolso/des1:monto[com:tipo/cat:DescripcionCorta = 'PROGRAMADO']/com:importe)) - (xs:decimal(fn:data($outputVariable.response/des:ContratoDesembolso/des1:monto[com:tipo/cat:DescripcionCorta = 'EJECUTADO']/com:importe)) + xs:decimal(fn:data($outputVariable.response/des:ContratoDesembolso/des1:monto[com:tipo/cat:DescripcionCorta = 'TRANSITO']/com:importe))))
                }
                </com:importe>
            </des1:monto>
            {if ($outputVariable.response/des:ContratoDesembolso/des1:estado) then
            <des1:estado>
                {$outputVariable.response/des:ContratoDesembolso/des1:estado/*}
            </des1:estado>
            else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:programado)
                then <des1:programado>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:programado)}</des1:programado>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:fechaEstimadaDesembolsar)
                then <des1:fechaEstimadaDesembolsar>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:fechaEstimadaDesembolsar)}</des1:fechaEstimadaDesembolsar>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:fechaEfectiva)
                then <des1:fechaEfectiva>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:fechaEfectiva)}</des1:fechaEfectiva>
                else ()
            }
            {
                if ($outputVariable.response/des:ContratoDesembolso/des1:idLinea)
                then <des1:idLinea>{fn:data($outputVariable.response/des:ContratoDesembolso/des1:idLinea)}</des1:idLinea>
                else ()
            }
        </des:ContratoDesembolso>
    </des:ValidarProgramacionResponse>
};

local:funcXq_agregarmontoevaluado($outputVariable.response)
