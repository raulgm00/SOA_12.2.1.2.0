xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

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

declare variable $outInvokeConsultarCondicionByEvento.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::) external;
declare variable $outInvokeConsultarCumplimientoCondiciones.response as element() (:: schema-element(des:ConsultarCumplimientoCondicionesResponse) ::) external;

declare function local:funcConsultarcumplimientocondiciones_to_consultarcondicionesbyevento($outInvokeConsultarCondicionByEvento.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::), 
                                                                                            $outInvokeConsultarCumplimientoCondiciones.response as element() (:: schema-element(des:ConsultarCumplimientoCondicionesResponse) ::)) 
                                                                                            as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::) {
    let $CondicionesCumplidas:= $outInvokeConsultarCumplimientoCondiciones.response/des:Condicion[count(con1:Cumplimientos)>0]
    let $CondicionesNoCumplidas:= $outInvokeConsultarCondicionByEvento.response/con:Condicion[string(con1:idCondicion) != string($CondicionesCumplidas/con1:idCondicion)]
    return
    <con:ConsultarCondicionByIdEventoResponse>
    {
    for $Condicion1erD in $CondicionesNoCumplidas
    return
      <con:Condicion>  
        <con1:idCondicion>{fn:data($Condicion1erD/con1:idCondicion)}</con1:idCondicion>    
      </con:Condicion>
    }
    </con:ConsultarCondicionByIdEventoResponse>
};

local:funcConsultarcumplimientocondiciones_to_consultarcondicionesbyevento($outInvokeConsultarCondicionByEvento.response, $outInvokeConsultarCumplimientoCondiciones.response)
