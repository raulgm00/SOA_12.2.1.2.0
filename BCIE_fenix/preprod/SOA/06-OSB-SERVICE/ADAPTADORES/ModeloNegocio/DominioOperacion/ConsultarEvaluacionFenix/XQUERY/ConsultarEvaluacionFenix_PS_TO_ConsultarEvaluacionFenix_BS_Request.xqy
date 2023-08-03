xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEvaluacionFenix";
(:: import schema at "../XSD/ConsultarEvaluacionFenix.xsd" ::)

declare variable $ConsultarEvaluacionFenixRequest as element() (:: schema-element(ns1:ConsultarEvaluacionFenixRequest) ::) external;

declare function local:func($ConsultarEvaluacionFenixRequest as element() (:: schema-element(ns1:ConsultarEvaluacionFenixRequest) ::)) as element() (:: schema-element(ns2:ConsultarEvaluacionFenixInput) ::) {
    <ns2:ConsultarEvaluacionFenixInput>
        <ns2:idOperacion>{fn:data($ConsultarEvaluacionFenixRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idTipoEvaluacion>{fn:data($ConsultarEvaluacionFenixRequest/ns1:idTipoEvaluacion)}</ns2:idTipoEvaluacion>
    </ns2:ConsultarEvaluacionFenixInput>
};

local:func($ConsultarEvaluacionFenixRequest)
