xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTipoEvaluacion";
(:: import schema at "../XSD/ConsultarTipoEvaluacion.xsd" ::)

declare variable $ConsultarTipoEvaluacionRequest as element() (:: schema-element(ns1:ConsultarTipoEvaluacionRequest) ::) external;

declare function local:func($ConsultarTipoEvaluacionRequest as element() (:: schema-element(ns1:ConsultarTipoEvaluacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarTipoEvaluacionInput) ::) {
    <ns2:ConsultarTipoEvaluacionInput>
        <ns2:idTipoEvaluacion>{fn:data($ConsultarTipoEvaluacionRequest/ns1:idTipoEvaluacion)}</ns2:idTipoEvaluacion>
        <ns2:codExterno>{fn:data($ConsultarTipoEvaluacionRequest/ns1:codExterno)}</ns2:codExterno>
    </ns2:ConsultarTipoEvaluacionInput>
};

local:func($ConsultarTipoEvaluacionRequest)
