xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace con = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConfiguracionValidacionAsignacionResponse as element() (:: schema-element(ns1:ConfiguracionValidacionAsignacionResponse) ::) external;
declare variable $SolicitudDesembolso as element() (:: schema-element(ns2:ConsultarSolicitudDesembolsoResponse) ::) external;


declare function local:func($ConfiguracionValidacionAsignacionResponse as element() (:: schema-element(ns1:ConfiguracionValidacionAsignacionResponse) ::),
                             $SolicitudDesembolso as element() (:: schema-element(ns2:ConsultarSolicitudDesembolsoResponse) ::)) as element() (:: schema-element(ns2:VerificarValidacionAsignacionResponse) ::) {
  <ns2:VerificarValidacionAsignacionResponse>
        <ns2:SolicitudDesembolso>
          <des:ValidacionAsignacion>{
          if(fn:data($ConfiguracionValidacionAsignacionResponse/ns1:ConfiguracionValidacionAsignacion/con:Header/ns3:ParameterType/ns3:parameterName ='VALIDADOR_DAECI')or 
          fn:data($ConfiguracionValidacionAsignacionResponse/ns1:ConfiguracionValidacionAsignacion/con:Header/ns3:ParameterType/ns3:parameterName ='VALIDADOR_FINAM')or
          fn:data($ConfiguracionValidacionAsignacionResponse/ns1:ConfiguracionValidacionAsignacion/con:Header/ns3:ParameterType/ns3:parameterName ='VALIDADOR_PCT')or
          fn:data($ConfiguracionValidacionAsignacionResponse/ns1:ConfiguracionValidacionAsignacion/con:Header/ns3:ParameterType/ns3:parameterName ='VALIDADOR_PREA')or
          fn:string(fn:data($SolicitudDesembolso/ns2:SolicitudDesmebolso/des:ValidacionAsignacion))='true')
          then 
          fn:true()
          else false()
          }</des:ValidacionAsignacion>
        </ns2:SolicitudDesembolso>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta exitosa</res:message>
        </ns2:Resultado>
    </ns2:VerificarValidacionAsignacionResponse>
};

local:func($ConfiguracionValidacionAsignacionResponse,$SolicitudDesembolso)
