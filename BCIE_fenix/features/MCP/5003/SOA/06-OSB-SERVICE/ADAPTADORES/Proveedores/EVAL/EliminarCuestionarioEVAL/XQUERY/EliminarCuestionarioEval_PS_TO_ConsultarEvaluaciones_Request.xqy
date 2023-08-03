xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarEvaluacion";
(:: import schema at "../../../../ModeloNegocio/DominioOperacion/ConsultarEvaluacion/XSD/ConsultarEvaluacion_sp.xsd" ::)

declare variable $EliminarCuestionarioRequest as element() (:: schema-element(ns1:EliminarCuestionarioRequest) ::) external;

declare function local:func($EliminarCuestionarioRequest as element() (:: schema-element(ns1:EliminarCuestionarioRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        {
            if ($EliminarCuestionarioRequest/ns1:Operacion)
            then <ns2:PNCODIGONEGOCIO>{fn:data($EliminarCuestionarioRequest/ns1:Operacion)}</ns2:PNCODIGONEGOCIO>
            else ()
        }
        {
            if ($EliminarCuestionarioRequest/ns1:idEvaluacion)
            then <ns2:PNCODIGOEVALUACION>{fn:data($EliminarCuestionarioRequest/ns1:idEvaluacion)}</ns2:PNCODIGOEVALUACION>
            else ()
        }
        <ns2:PNVERSIONEVALUACION></ns2:PNVERSIONEVALUACION>
        <ns2:PCCODIGOGRUPO></ns2:PCCODIGOGRUPO>
      
    </ns2:InputParameters>
};

local:func($EliminarCuestionarioRequest)
