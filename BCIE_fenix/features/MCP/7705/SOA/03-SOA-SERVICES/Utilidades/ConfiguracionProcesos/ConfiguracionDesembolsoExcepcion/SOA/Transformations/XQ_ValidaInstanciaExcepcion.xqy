xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $outConsultarExcepcionSolicitud.response as element() (:: schema-element(des:ConsultarExcepcionSolicitudResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(con:ConfiguracionDesembolsoExcepcionRequest) ::) external;

declare function local:funcXq_validainstanciaexcepcion($outConsultarExcepcionSolicitud.response as element() (:: schema-element(des:ConsultarExcepcionSolicitudResponse) ::), 
                                                       $inputVariable.request as element() (:: schema-element(con:ConfiguracionDesembolsoExcepcionRequest) ::)) 
                                                       as element() (:: schema-element(des:CrearActualizarExcepcionSolicitudRequest) ::) {
    
    
    <des:CrearActualizarExcepcionSolicitudRequest>
    {
    for $solicitudExcepcion in $outConsultarExcepcionSolicitud.response/des:SolicitudDesembolso[des1:Excepcion/reg:DetalleRegla/atr:id = xs:long(1) or des1:Excepcion/reg:DetalleRegla/atr:id = xs:long(2) or des1:Excepcion/reg:DetalleRegla/atr:id = xs:long(6)]
    where string($solicitudExcepcion/des1:Excepcion/des1:instanciaProceso) = ''
    return
        <des:Excepcion>
            <reg:Id>{fn:data($solicitudExcepcion/des1:Excepcion/reg:Id)}</reg:Id>
            <des1:instanciaProceso>{fn:data($inputVariable.request/con:instanciaProceso)}</des1:instanciaProceso>
            <des1:enProcesoExcepcion>{true()}</des1:enProcesoExcepcion>
        </des:Excepcion>
        }
    </des:CrearActualizarExcepcionSolicitudRequest>
};

local:funcXq_validainstanciaexcepcion($outConsultarExcepcionSolicitud.response, $inputVariable.request)
