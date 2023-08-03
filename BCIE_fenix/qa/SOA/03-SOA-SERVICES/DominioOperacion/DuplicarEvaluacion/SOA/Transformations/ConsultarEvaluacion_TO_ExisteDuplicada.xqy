xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace eva="http://www.bcie.org/EvaluacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace eva1 = "http://www.bcie.org/EvaluacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarEvaluacionFenix.response as element() (:: schema-element(eva:ConsultarEvaluacionFenixResponse) ::) external;
declare variable $outConsultarEvaluacion.response as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(eva:DuplicarEvaluacionRequest) ::) external;

declare function local:funcConsultarevaluacion_to_existeduplicada($outConsultarEvaluacionFenix.response as element() (:: schema-element(eva:ConsultarEvaluacionFenixResponse) ::), 
                                                                   $outConsultarEvaluacion.response as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::), 
                                                                   $inputVariable.request as element() (:: schema-element(eva:DuplicarEvaluacionRequest) ::)) 
                                                                   as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::) {
   let $existeDuplicada:= count($outConsultarEvaluacionFenix.response/eva:Evaluaciones/eva1:evaluacion[string(eva1:idEvaluacionOrigen) != ''][eva1:instanciaProceso = $inputVariable.request/eva:Evaluacion/eva1:IdFlujo])
   let $idDuplicado:= $outConsultarEvaluacion.response/eva:Evaluaciones/eva1:evaluacion[eva1:instanciaProceso = $inputVariable.request/eva:Evaluacion/eva1:IdFlujo][string(eva1:idEvaluacionOrigen) != ''][1]/eva1:idEvaluacion
   let $idOriginal:= $outConsultarEvaluacion.response/eva:Evaluaciones/eva1:evaluacion[eva1:instanciaProceso = $inputVariable.request/eva:Evaluacion/eva1:IdFlujo][string(eva1:idEvaluacionOrigen) = ''][1]/eva1:idEvaluacion
   return
    <eva:ConsultarEvaluacionResponse>
        <eva:Evaluaciones>
            <eva1:evaluacion>
                <eva1:catEvaluacion>
                    <cat:Id>{$existeDuplicada}</cat:Id>
                    <cat:Descripcion>EXISTE_DUPLICADA</cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </eva1:catEvaluacion>
            </eva1:evaluacion>
             <eva1:evaluacion>
                <eva1:catEvaluacion>
                    <cat:Id>{$idDuplicado}</cat:Id>
                    <cat:Descripcion>ID_DUPLICADO</cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </eva1:catEvaluacion>
            </eva1:evaluacion>
             <eva1:evaluacion>
                <eva1:catEvaluacion>
                    <cat:Id>{$idOriginal}</cat:Id>
                    <cat:Descripcion>ID_ORIGINAL</cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </eva1:catEvaluacion>
            </eva1:evaluacion>
        </eva:Evaluaciones>
    </eva:ConsultarEvaluacionResponse>
};

local:funcConsultarevaluacion_to_existeduplicada($outConsultarEvaluacionFenix.response, $outConsultarEvaluacion.response, $inputVariable.request)
