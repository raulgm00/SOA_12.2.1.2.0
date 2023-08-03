xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace con1="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)

declare namespace con2 = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

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

declare variable $outInvokeConsultarCondicionByRol.response as element() (:: schema-element(con:ConsultarCondicionByRolResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(con1:configuracionCondicionesRequest) ::) external;

declare function local:funcConsultarcondicionbyrol_to_upserttransaccioncondicion_request($outInvokeConsultarCondicionByRol.response as element() (:: schema-element(con:ConsultarCondicionByRolResponse) ::), 
                                                                                         $inputVariable.request as element() (:: schema-element(con1:configuracionCondicionesRequest) ::)) 
                                                                                         as element() (:: schema-element(con:UpsertTransaccionCondicionRequest) ::) {
    <con:UpsertTransaccionCondicionRequest>
        <con:transaccionCondicion>
            <con2:Operacion>
                <ope:idOperacion>{fn:data($outInvokeConsultarCondicionByRol.response/con:Condicion[1]/con2:operacion)}</ope:idOperacion>
            </con2:Operacion>
            {for $condicion in $outInvokeConsultarCondicionByRol.response/con:Condicion
            return
            <con2:Condicion>
                <con2:idCondicion>{fn:data($condicion/con2:idCondicion)}</con2:idCondicion>
            </con2:Condicion>
            }
            <con2:EstadoTCC>23</con2:EstadoTCC>
            <con2:enProceso>{fn:true()}</con2:enProceso>
            {
                if ($inputVariable.request/con1:Agrupador)
                then <con2:agrupador>{fn:data($inputVariable.request/con1:Agrupador)}</con2:agrupador>
                else ()
            }
            <con2:subEstadoTCC>
                <cat:Id>29</cat:Id>
            </con2:subEstadoTCC>
        </con:transaccionCondicion>
    </con:UpsertTransaccionCondicionRequest>
};

local:funcConsultarcondicionbyrol_to_upserttransaccioncondicion_request($outInvokeConsultarCondicionByRol.response, $inputVariable.request)
