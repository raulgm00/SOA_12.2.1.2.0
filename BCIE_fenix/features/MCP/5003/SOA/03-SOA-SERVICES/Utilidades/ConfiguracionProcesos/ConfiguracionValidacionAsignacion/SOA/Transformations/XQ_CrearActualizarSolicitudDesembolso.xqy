xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd"::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace con1 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare variable $inputVariable.request as element() (:: schema-element(con:ConfiguracionValidacionAsignacionRequest) ::) external;
declare variable $solicitudDesembolso.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::) external;
declare variable $outputVariable.response as element() (:: schema-element(con:ConfiguracionValidacionAsignacionResponse) ::) external;

declare function local:funcXq_crearactualizarsolicituddesembolso($inputVariable.request as element() (:: schema-element(con:ConfiguracionValidacionAsignacionRequest) ::), 
                                                                 $solicitudDesembolso.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::),
                                                                 $outputVariable.response as element() (:: schema-element(con:ConfiguracionValidacionAsignacionResponse) ::))
                                                                 as element() (:: schema-element(des:CrearActualizarSolicitudDesembolsoRequest) ::){
    <des:CrearActualizarSolicitudDesembolsoRequest>
        <des:SolicitudDesembolso>
            <des1:idDesembolso>{fn:data($inputVariable.request/con:idSolicitud)}</des1:idDesembolso>
           {
           if(fn:string( fn:data($inputVariable.request/con:instanciaProceso))!='0')
           then
            <des1:estado>
                <cat:Id>6</cat:Id>
            </des1:estado>
           else()}
            <des1:ValidacionAsignacion>{
            if($solicitudDesembolso.response/des:SolicitudDesmebolso/des1:ValidacionAsignacion = true() or 
            $outputVariable.response/con:ConfiguracionValidacionAsignacion/con1:Header/ns1:ParameterType[ns1:parameterName='VALIDADOR_DAECI']/ns1:parameterValue = 'true' or
            $outputVariable.response/con:ConfiguracionValidacionAsignacion/con1:Header/ns1:ParameterType[ns1:parameterName='VALIDADOR_FINAM']/ns1:parameterValue = 'true' or
            $outputVariable.response/con:ConfiguracionValidacionAsignacion/con1:Header/ns1:ParameterType[ns1:parameterName='VALIDADOR_PCT']/ns1:parameterValue = 'true' or
            $outputVariable.response/con:ConfiguracionValidacionAsignacion/con1:Header/ns1:ParameterType[ns1:parameterName='VALIDADOR_PREA']/ns1:parameterValue = 'true')
            then true()
            else false()}</des1:ValidacionAsignacion>
        </des:SolicitudDesembolso>
    </des:CrearActualizarSolicitudDesembolsoRequest>
};

local:funcXq_crearactualizarsolicituddesembolso($inputVariable.request, $solicitudDesembolso.response,$outputVariable.response)
