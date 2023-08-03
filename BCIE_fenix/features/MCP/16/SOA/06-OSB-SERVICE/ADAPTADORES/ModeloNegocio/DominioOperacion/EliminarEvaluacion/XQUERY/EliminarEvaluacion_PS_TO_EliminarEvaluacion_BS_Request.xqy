xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarEvaluacion";
(:: import schema at "../XSD/EliminarEvaluacion.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $EvaluacionRequest as element() (:: element(*, ns1:EliminarCuestionarioRequestType) ::) external;

declare function local:func($EvaluacionRequest as element() (:: element(*, ns1:EliminarCuestionarioRequestType) ::)) as element() (:: schema-element(ns2:EliminarEvaluacionInput) ::) {
    <ns2:EliminarEvaluacionInput>
        <ns2:idEvaluacion>{fn:data($EvaluacionRequest/ns1:idEvaluacion)}</ns2:idEvaluacion>
        <ns2:codigoExterno>{fn:data($EvaluacionRequest/ns1:Cuestionario/cat:codigoExterno)}</ns2:codigoExterno>
    </ns2:EliminarEvaluacionInput>
};

local:func($EvaluacionRequest)
