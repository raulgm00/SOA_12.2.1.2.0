xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/EVAL.wsdl";

declare namespace ns1="http://org/bcie/ws/middle/EVAL.wsdl/types/";
(:: import schema at "../../WSDL/EVAL.wsdl" ::)
declare namespace ns2="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $actualizarPreguntaResponse as element() (:: element(m:actualizarPreguntaResponse) ::) external;

declare function local:func($actualizarPreguntaResponse as element(m:actualizarPreguntaResponse) (:: element(m:actualizarPreguntaResponse) ::)) as element() (:: schema-element(ns2:ActualizarPreguntaEVALResponse) ::) {
    <ns2:ActualizarPreguntaEVALResponse>
        <ns2:codigoResultadoOut>{fn:data($actualizarPreguntaResponse/codigoNegocio)}</ns2:codigoResultadoOut>
        <ns2:tipoResultadoOut>{fn:data($actualizarPreguntaResponse/tiporesultado_out)}</ns2:tipoResultadoOut>
        <ns2:mensajeOut>{fn:data($actualizarPreguntaResponse/mensaje_out)}</ns2:mensajeOut>
        <ns2:Resultado>
            <res:result>{
            if(fn:string-length(fn:data($actualizarPreguntaResponse/mensaje_out))=0) then ("OK")
            else("ERROR")
            }
            </res:result>
            <res:message>{
            if (fn:string-length(fn:data($actualizarPreguntaResponse/mensaje_out))=0) then ("Operación exitosa")
            else (fn:data($actualizarPreguntaResponse/mensaje_out))
            }</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ActualizarPreguntaEVALResponse>
};

local:func($actualizarPreguntaResponse)
