xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $codigoEvaluacion as xs:string external;
declare variable $codigoResultado as xs:string external;
declare variable $tipoResultado as xs:string external;
declare variable $mensaje as xs:string external;

declare function local:func($codigoEvaluacion as xs:string, 
                            $codigoResultado as xs:string, 
                            $tipoResultado as xs:string, 
                            $mensaje as xs:string) 
                            as element() (:: schema-element(ns1:CrearEvaluacionResponse) ::) {
    <ns1:CrearEvaluacionResponse>
        <ns1:Evaluaciones>
            <eva:evaluacion>
                <eva:idEvaluacion>{fn:data($codigoEvaluacion)}</eva:idEvaluacion>
            </eva:evaluacion>
        </ns1:Evaluaciones>
        <ns1:Resultado>
       {if($codigoResultado='1')
           then <res:result>ERROR</res:result>
           else <res:result>OK</res:result>
            }
            <res:message>{fn:concat($tipoResultado,' - ' , $mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($codigoResultado)}</err:errorCode>
                <err:errorDescription>{fn:data($mensaje)}</err:errorDescription>
                <err:errorType>{fn:data($tipoResultado)}</err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearEvaluacionResponse>
};

local:func($codigoEvaluacion, $codigoResultado, $tipoResultado, $mensaje)
