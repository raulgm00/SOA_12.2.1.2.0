xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace eval="http://org/bcie/ws/middle/EVAL.wsdl"; 

declare namespace ns2="http://org/bcie/ws/middle/EVAL.wsdl/types/";
(:: import schema at "../../WSDL/EVAL.wsdl" ::)
declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare variable $DuplicarEvaluacionEVALRequest as element() (:: schema-element(ns1:DuplicarEvaluacionEVALRequest) ::) external;

declare function local:func($DuplicarEvaluacionEVALRequest as element() (:: schema-element(ns1:DuplicarEvaluacionEVALRequest) ::)) as element() (:: element(eval:duplicarEvaluacion) ::) {
    <eval:duplicarEvaluacion>
         <codigoEvaluacion>{fn:data($DuplicarEvaluacionEVALRequest/ns1:Evaluacion/eva:Id)}</codigoEvaluacion>
         <codigoUsuario>{fn:data($DuplicarEvaluacionEVALRequest/ns1:Evaluacion/eva:LoginUsuario)}</codigoUsuario>
         <tipo>{fn:data($DuplicarEvaluacionEVALRequest/ns1:Evaluacion/eva:TipoEvaluacion/cat:Id)}</tipo>
      </eval:duplicarEvaluacion>
};

local:func($DuplicarEvaluacionEVALRequest)
