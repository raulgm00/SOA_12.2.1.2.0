xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

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

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $outConsultarCondicionesByOperacion.response as element() (:: schema-element(con:ConsultarCondicionByIdOperacionResponse) ::) external;

declare function local:funcConsultarcondicionbyoperacion_to_condicionescalendario($outConsultarCondicionesByOperacion.response as element() (:: schema-element(con:ConsultarCondicionByIdOperacionResponse) ::)) as element() (:: schema-element(con:ConsultarCondicionByIdOperacionResponse) ::) {
    <con:ConsultarCondicionByIdOperacionResponse>
        <con:ListaCondiciones>
        {
        for $condicion in $outConsultarCondicionesByOperacion.response/con:ListaCondiciones/con1:Condicion[con1:controlCondicion/cat:Id=2][con1:estadoTCC/atr:id!=26]
        return 
        if (string(fn:data($condicion/con1:observaciones/con1:fechaRegistro))!='')
        then
          if (fn:current-date()>=fn:data($condicion/con1:observaciones/con1:fechaRegistro))then
            <con1:Condicion>
                <con1:idCondicion>{fn:data($condicion/con1:idCondicion)}</con1:idCondicion>
            </con1:Condicion>
            else()
        else()
            }
        </con:ListaCondiciones>
    </con:ConsultarCondicionByIdOperacionResponse>
};

local:funcConsultarcondicionbyoperacion_to_condicionescalendario($outConsultarCondicionesByOperacion.response)
