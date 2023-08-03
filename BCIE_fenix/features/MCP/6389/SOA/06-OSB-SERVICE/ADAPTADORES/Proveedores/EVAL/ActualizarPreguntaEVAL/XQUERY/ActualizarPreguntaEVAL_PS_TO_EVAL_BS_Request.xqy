xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace eval="http://org/bcie/ws/middle/EVAL.wsdl";

declare namespace ns2="http://org/bcie/ws/middle/EVAL.wsdl/types/";
(:: import schema at "../../WSDL/EVAL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare variable $ActualizarPreguntaEVALRequest as element() (:: schema-element(ns1:ActualizarPreguntaEVALRequest) ::) external;

declare function local:func($ActualizarPreguntaEVALRequest as element() (:: schema-element(ns1:ActualizarPreguntaEVALRequest) ::)) as element() (:: element(eval:actualizarPregunta) ::) {
    <eval:actualizarPregunta>
      <idoperacion>{fn:data($ActualizarPreguntaEVALRequest/ns1:idOperacion)}</idoperacion>
      <scr>{fn:data($ActualizarPreguntaEVALRequest/ns1:scr)}</scr>
      <tir>{fn:data($ActualizarPreguntaEVALRequest/ns1:tir)}</tir>
      <raroc>{fn:data($ActualizarPreguntaEVALRequest/ns1:raroc)}</raroc>
      <fecha>{fn:current-date()}</fecha>
      <codigousuario>{fn:data($ActualizarPreguntaEVALRequest/ns1:codigoUsuario)}</codigousuario>
    </eval:actualizarPregunta>
};

local:func($ActualizarPreguntaEVALRequest)
