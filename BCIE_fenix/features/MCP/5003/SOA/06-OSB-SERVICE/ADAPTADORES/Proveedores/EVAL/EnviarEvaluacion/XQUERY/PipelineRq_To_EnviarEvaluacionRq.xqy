xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace eval="http://org/bcie/ws/middle/EVAL.wsdl";

declare namespace ns2="http://org/bcie/ws/middle/EVAL.wsdl/types/";
(:: import schema at "../../WSDL/EVAL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare variable $EnviarEvaluacionRequest as element() (:: schema-element(ns1:EnviarEvaluacionRequest) ::) external;

declare function tns:func($EnviarEvaluacionRequest as element() (:: schema-element(ns1:EnviarEvaluacionRequest) ::)) as element() (:: element(eval:enviarEvaluacion) ::) {
    <eval:enviarEvaluacion>
      <codigoevaluacion>{fn:data($EnviarEvaluacionRequest/ns1:IdEvaluacion)}</codigoevaluacion>
      <versionevaluacion>{fn:data($EnviarEvaluacionRequest/ns1:Version)}</versionevaluacion>
      <codigomodelo>{fn:data($EnviarEvaluacionRequest/ns1:IdCuestionario)}</codigomodelo>
      
      {
      if(fn:data($EnviarEvaluacionRequest/ns1:VersionModelo)) then
      <versionmodelo>{fn:data($EnviarEvaluacionRequest/ns1:VersionModelo)}</versionmodelo>
      else 
      <versionmodelo></versionmodelo>
      }
      <codigousuario>{fn:data($EnviarEvaluacionRequest/ns1:LoginUsuario)}</codigousuario>
    </eval:enviarEvaluacion>
};

tns:func($EnviarEvaluacionRequest)
