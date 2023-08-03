xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $codigoResultado as xs:string external;
declare variable $tipoResultado as xs:string external;
declare variable $mensajeOut as xs:string external;

declare function local:func($codigoResultado as xs:string, 
                            $tipoResultado as xs:string, 
                            $mensajeOut as xs:string) 
                            as element() (:: schema-element(ns1:EliminarCuestionarioResponse) ::) {
    <ns1:EliminarCuestionarioResponse>
     {  if ($codigoResultado='1') then
                (  <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:concat($tipoResultado,' - ' ,$mensajeOut)}</res:message>
        </ns1:Resultado>)
                else
                (  <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:concat($tipoResultado,' - ', $mensajeOut)}</res:message>
        </ns1:Resultado>)}
         
    </ns1:EliminarCuestionarioResponse>
};

local:func($codigoResultado, $tipoResultado, $mensajeOut)
