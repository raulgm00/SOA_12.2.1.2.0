xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarEvaluacion";
(:: import schema at "../XSD/ConsultarEvaluacion_sp.xsd" ::)

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare variable $ConsultarEvaluacionRequest as element() (:: schema-element(ns1:ConsultarEvaluacionRequest) ::) external;

declare function local:func($ConsultarEvaluacionRequest as element() (:: schema-element(ns1:ConsultarEvaluacionRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PNCODIGONEGOCIO>{fn:data($ConsultarEvaluacionRequest/ns1:Evaluacion/eva:idOperacion)}</ns2:PNCODIGONEGOCIO>
        {
            if ($ConsultarEvaluacionRequest/ns1:Evaluacion/eva:idEvaluacion)
            then <ns2:PNCODIGOEVALUACION>{fn:data($ConsultarEvaluacionRequest/ns1:Evaluacion/eva:idEvaluacion)}</ns2:PNCODIGOEVALUACION>
            else ()
        }
        {
            if ($ConsultarEvaluacionRequest/ns1:Evaluacion/eva:version)
            then <ns2:PNVERSIONEVALUACION>{fn:data($ConsultarEvaluacionRequest/ns1:Evaluacion/eva:version)}</ns2:PNVERSIONEVALUACION>
            else ()
        }
        <ns2:PCCODIGOGRUPO>EJECUTIVO</ns2:PCCODIGOGRUPO>
        
    </ns2:InputParameters>
};

local:func($ConsultarEvaluacionRequest)
