xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/EVAL.wsdl";

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $duplicarEvaluacionResponse as element() (:: element(m:duplicarEvaluacionResponse) ::) external;

declare function local:func($duplicarEvaluacionResponse as element(m:duplicarEvaluacionResponse) (:: element(m:duplicarEvaluacionResponse) ::)) as element() (:: schema-element(ns1:DuplicarEvaluacionEVALResponse) ::) {
    <ns1:DuplicarEvaluacionEVALResponse>
        <ns1:codigoEvaluacion>{fn:data($duplicarEvaluacionResponse/nuevaEvaluacion_out)}</ns1:codigoEvaluacion>
        <ns1:codigoResultadoOut>{fn:data($duplicarEvaluacionResponse/codigoResultado_out)}</ns1:codigoResultadoOut>
        <ns1:tipoResultadoOut>{fn:data($duplicarEvaluacionResponse/tipoResultado_out)}</ns1:tipoResultadoOut>
        <ns1:mensajeOut>{fn:data($duplicarEvaluacionResponse/mensaje_out)}</ns1:mensajeOut>
        <ns1:Resultado>
            <res:result>{
            if(fn:string-length(fn:data($duplicarEvaluacionResponse/mensaje_out))=0) then ("OK")
            else("ERROR")
            }
            </res:result>
            <res:message>{
            if (fn:string-length(fn:data($duplicarEvaluacionResponse/mensaje_out))=0) then ("Operación exitosa")
            else (fn:data($duplicarEvaluacionResponse/mensaje_out))
            }</res:message>
        </ns1:Resultado>
    </ns1:DuplicarEvaluacionEVALResponse>
};

local:func($duplicarEvaluacionResponse)
