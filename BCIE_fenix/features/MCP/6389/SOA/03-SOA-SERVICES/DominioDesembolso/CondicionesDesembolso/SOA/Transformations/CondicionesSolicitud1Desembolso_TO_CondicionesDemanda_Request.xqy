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

declare variable $outConsultarCondicionesSolicitud1erD.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::) external;
declare variable $outInvokeConsultarCondicionByEvento.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::) external;
declare variable $outInvokeConsultarCondicionByEventoCualquier.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::) external;
declare variable $outInvokeConsultarInformacionRegla.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::) external;
declare variable $outConsultarSolicitudDesembolso.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::) external;


declare function local:funcCondicionessolicitud1desembolso_to_condicionesdemanda_request($outConsultarCondicionesSolicitud1erD.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::), 
                                                                                         $outInvokeConsultarCondicionByEvento.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::), 
                                                                                         $outInvokeConsultarCondicionByEventoCualquier.response as element() (:: schema-element(con:ConsultarCondicionByIdEventoResponse) ::),
                                                                                         $outInvokeConsultarInformacionRegla.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::), 
                                                                                         $outConsultarSolicitudDesembolso.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::)) 
                                                                                         as element() (:: schema-element(con:CondicionesDemandaRequest) ::) {
    <con:CondicionesDemandaRequest>
        <con:CondicionesDemanda>
            <con1:Operacion>
                <ope:idOperacion>{fn:data($outInvokeConsultarInformacionRegla.response/des:Operacion/ope:idOperacion)}</ope:idOperacion>
                <ope:responsable>{fn:data($outInvokeConsultarInformacionRegla.response/des:Operacion/ope:responsable)}</ope:responsable>
                <ope:nombre>{fn:data($outInvokeConsultarInformacionRegla.response/des:Operacion/ope:nombre)}</ope:nombre>
            </con1:Operacion>
            <con1:Solicitud>
                <des1:idDesembolso>{fn:data($outConsultarSolicitudDesembolso.response/des:SolicitudDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            </con1:Solicitud>
            {
            for $condicionSolicitud in $outConsultarCondicionesSolicitud1erD.response/con:Condicion
            return
              <con1:Condicion>
                  <con1:idCondicion>{fn:data($condicionSolicitud/con1:idCondicion)}</con1:idCondicion>
              </con1:Condicion>
            }
            
            {
            for $condicion1erD in $outInvokeConsultarCondicionByEvento.response/con:Condicion
            return
              <con1:Condicion>
                  <con1:idCondicion>{fn:data($condicion1erD/con1:idCondicion)}</con1:idCondicion>
              </con1:Condicion>
            }
            
            {
            for $condicionCualquierD in $outInvokeConsultarCondicionByEventoCualquier.response/con:Condicion
            return
              <con1:Condicion>
                  <con1:idCondicion>{fn:data($condicionCualquierD/con1:idCondicion)}</con1:idCondicion>
              </con1:Condicion>
            }
        
            <con1:EventoCondicion>
                <cat:Id>3</cat:Id>
            </con1:EventoCondicion>
        </con:CondicionesDemanda>
    </con:CondicionesDemandaRequest>
};

local:funcCondicionessolicitud1desembolso_to_condicionesdemanda_request($outConsultarCondicionesSolicitud1erD.response, $outInvokeConsultarCondicionByEvento.response, $outInvokeConsultarCondicionByEventoCualquier.response, $outInvokeConsultarInformacionRegla.response, $outConsultarSolicitudDesembolso.response)
