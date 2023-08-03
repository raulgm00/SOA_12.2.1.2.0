xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://org/bcie/ws/middle/EVAL.wsdl/types/";
(:: import schema at "../../../EVAL/WSDL/EVAL.wsdl" ::)
declare namespace ns2="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare variable $CrearEvaluacionRequest as element() (:: schema-element(ns2:CrearEvaluacionRequest) ::) external;

declare function local:func($CrearEvaluacionRequest as element() (:: schema-element(ns2:CrearEvaluacionRequest) ::)) as element() (:: element(*, ns1:decimalArray) ::) {
    
   
    
    <ns1:decimalArray>
     { for $eval in $CrearEvaluacionRequest/ns2:Evaluaciones/eva:evaluacion[1]/eva:cuestionarios/eva:cuestionario
            return 
        <ns1:BigDecimal>{fn:data($eval/eva:codigoExterno)}</ns1:BigDecimal>
        }
    </ns1:decimalArray>
};

local:func($CrearEvaluacionRequest)
