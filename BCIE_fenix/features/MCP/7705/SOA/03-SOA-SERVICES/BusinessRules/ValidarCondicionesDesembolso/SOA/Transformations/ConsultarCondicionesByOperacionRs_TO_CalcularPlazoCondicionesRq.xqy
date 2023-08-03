xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarCondicionByIdOperacionResponse as element() (:: schema-element(ns2:ConsultarCondicionByIdOperacionResponse) ::) external;

declare function ns1:func($ConsultarCondicionByIdOperacionResponse as element() (:: schema-element(ns2:ConsultarCondicionByIdOperacionResponse) ::)) as element() (:: schema-element(ns2:CalcularPlazoCondicionesRequest) ::) {
    <ns2:CalcularPlazoCondicionesRequest>
        <ns2:ListaCondiciones>
        
        {
        for $condicion in $ConsultarCondicionByIdOperacionResponse/ns2:ListaCondiciones/con:Condicion[con:controlCondicion/cat:Id=2][con:tipoFechaInicio/cat:Id =1 or con:tipoFechaInicio/cat:Id =2 or con:tipoFechaInicio/cat:Id =3 or con:tipoFechaInicio/cat:Id =7]
        
        return
            <con:Condicion>
                <con:idCondicion>{fn:data($condicion/con:idCondicion)}</con:idCondicion>
                <con:operacion>{fn:data($condicion/con:operacion)}</con:operacion>
                <con:tipoFechaInicio>
                    {
                        if ($condicion/con:tipoFechaInicio/cat:Id)
                        then <cat:Id>{fn:data($condicion/con:tipoFechaInicio/cat:Id)}</cat:Id>
                        else ()
                    }
                </con:tipoFechaInicio>
                {
                    if ($condicion/con:fechaVigencia)
                    then <con:fechaVigencia>{fn:data($condicion/con:fechaVigencia)}</con:fechaVigencia>
                    else ()
                }
            </con:Condicion>
            }
        </ns2:ListaCondiciones>
    </ns2:CalcularPlazoCondicionesRequest>
};

ns1:func($ConsultarCondicionByIdOperacionResponse)
