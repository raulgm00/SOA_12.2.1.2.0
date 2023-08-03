xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace eva="http://www.bcie.org/EvaluacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace eva1 = "http://www.bcie.org/EvaluacionBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $inputVariable.request as element() (:: schema-element(eva:DuplicarEvaluacionRequest) ::) external;
declare variable $outConsultarEvaluacion.response as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::) external;

declare function local:funcOutconsultarevaluacion_to_induplicarevaluacion($inputVariable.request as element() (:: schema-element(eva:DuplicarEvaluacionRequest) ::), 
                                                                           $outConsultarEvaluacion.response as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::)) 
                                                                           as element() (:: schema-element(eva:DuplicarEvaluacionEVALRequest) ::) {
                                                                           
  
    <eva:DuplicarEvaluacionEVALRequest>
      <eva:Evaluacion>
        <eva1:Id>{fn:data($outConsultarEvaluacion.response/eva:Evaluaciones/eva1:evaluacion[xs:string(eva1:idEvaluacionOrigen) = '' and eva1:instanciaProceso = $inputVariable.request/eva:Evaluacion/eva1:IdFlujo][1]/eva1:idEvaluacion)}</eva1:Id>
        <eva1:LoginUsuario>{fn:data($inputVariable.request/eva:Evaluacion/eva1:LoginUsuario)}</eva1:LoginUsuario>
      </eva:Evaluacion>  
    </eva:DuplicarEvaluacionEVALRequest>
};

local:funcOutconsultarevaluacion_to_induplicarevaluacion($inputVariable.request, $outConsultarEvaluacion.response)
