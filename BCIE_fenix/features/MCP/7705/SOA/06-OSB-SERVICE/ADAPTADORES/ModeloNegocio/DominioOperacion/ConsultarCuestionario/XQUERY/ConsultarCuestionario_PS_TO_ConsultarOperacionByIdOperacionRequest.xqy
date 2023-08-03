xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionByIdOperacion";
(:: import schema at "../../ConsultarOperacionByIDOperacion/XSD/ConsultarOperacionByIdOperacion.xsd" ::)

declare variable $consultarCuestionarioRequest as element() (:: schema-element(ns1:ConsultarCuestionarioRequest) ::) external;

declare function local:func($consultarCuestionarioRequest as element() (:: schema-element(ns1:ConsultarCuestionarioRequest) ::)) as element() (:: schema-element(ns2:ConsultarOperacionByIdOperacionInput) ::) {
    <ns2:ConsultarOperacionByIdOperacionInput>
        <ns2:idOperacion>{fn:data($consultarCuestionarioRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarOperacionByIdOperacionInput>
};

local:func($consultarCuestionarioRequest)
