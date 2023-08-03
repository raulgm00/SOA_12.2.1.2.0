xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:EliminarCuestionarioResponse) ::) {
    <ns1:EliminarCuestionarioResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>La eliminación se realizó correctamente</res:message>
        </ns1:Resultado>
    </ns1:EliminarCuestionarioResponse>
};

local:func()
