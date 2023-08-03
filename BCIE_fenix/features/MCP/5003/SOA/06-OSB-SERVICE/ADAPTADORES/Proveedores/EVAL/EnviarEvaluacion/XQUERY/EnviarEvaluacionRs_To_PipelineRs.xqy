xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/EVAL.wsdl";

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $enviarEvaluacionResponse as element() (:: element(m:enviarEvaluacionResponse) ::) external;
declare function tns:func($enviarEvaluacionResponse as element() (:: element(m:enviarEvaluacionResponse) ::)) as element() (:: schema-element(ns1:EnviarEvaluacionResponse) ::) {
    <ns1:EnviarEvaluacionResponse>
        <ns1:EnviarEvaluacion>
            <ns1:CorreosEnviados>{fn:data($enviarEvaluacionResponse/correosenviados_out)}</ns1:CorreosEnviados>
        </ns1:EnviarEvaluacion>
         <ns1:Resultado>
            <res:result>{
            if(fn:string-length(fn:data($enviarEvaluacionResponse/mensaje_out))=0) then ("OK")
            else("ERROR")
            }
            </res:result>
            <res:message>{
            if (fn:string-length(fn:data($enviarEvaluacionResponse/mensaje_out))=0) then ("Operación exitosa")
            else (fn:data($enviarEvaluacionResponse/mensaje_out))
            }</res:message>
        </ns1:Resultado>
    </ns1:EnviarEvaluacionResponse>
};

tns:func($enviarEvaluacionResponse)
