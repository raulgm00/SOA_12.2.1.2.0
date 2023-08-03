xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

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

declare function local:funcXq_condiciones1cumplimiento($outConsultarCondicionesByOperacion.response as element() (:: schema-element(con:ConsultarCondicionByIdOperacionResponse) ::)) as element() (:: schema-element(des:ConsultarCumplimientoCondicionesRequest) ::) {
    <des:ConsultarCumplimientoCondicionesRequest>
      {
        for $condicion in $outConsultarCondicionesByOperacion.response/con:ListaCondiciones/con1:Condicion[
                                                     con1:controlCondicion/cat:Id = 1 and       (:Condiciones de tipo evento:)
                                                     (:Los eventos donce se tiene que validar 1 sales el cumplimiento
                                                      1 - formalizacion
                                                      2 - primer desembolso
                                                      3 - cualquier desembolso :)
                                                    (con1:eventoCondicion/cat:Id = 1 or con1:eventoCondicion/cat:Id = 2 or con1:eventoCondicion/cat:Id = 3)
                                                    (:Solo los interesan las que aplican aqui se agregan el resto de los estados:)
                                                    and (con1:estadoTCC/atr:id = 15 or con1:estadoTCC/atr:id = 14) and con1:banEstatus = 1]
        return
        <des:Condicion>
            <con1:idCondicion>{fn:data($condicion/con1:idCondicion)}</con1:idCondicion>
        </des:Condicion>
      }
    </des:ConsultarCumplimientoCondicionesRequest>
};

local:funcXq_condiciones1cumplimiento($outConsultarCondicionesByOperacion.response)
