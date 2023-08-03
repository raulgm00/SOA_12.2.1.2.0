xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $outputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoFormalizacionResponse) ::) external;

declare function local:funcXq_distinctcontrato($outputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoFormalizacionResponse) ::)) as element() (:: schema-element(lin:ConsultarLineaCreditoFormalizacionResponse) ::) {
    <lin:ConsultarLineaCreditoFormalizacionResponse>
    {for $idContrato in distinct-values($outputVariable.response/lin:LineaCreditoAsociada/lin1:idContrato)
    let $contrato := $outputVariable.response/lin:LineaCreditoAsociada[lin1:idContrato = $idContrato] [1]
      return
        <lin:LineaCreditoAsociada>
            {
                if ($contrato/lin1:idContrato)
                then <lin1:idContrato>{fn:data($contrato/lin1:idContrato)}</lin1:idContrato>
                else ()
            }
          

  {
                if ($contrato/lin1:idOperacion)
                then <lin1:idOperacion>{fn:data($contrato/lin1:idOperacion)}</lin1:idOperacion>
                else ()
            }
        </lin:LineaCreditoAsociada>
        }
    </lin:ConsultarLineaCreditoFormalizacionResponse>
};

local:funcXq_distinctcontrato($outputVariable.response)
