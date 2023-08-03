xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarEvaluacion";
(:: import schema at "../XSD/ConsultarEvaluacion_sp.xsd" ::)

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;
declare variable $ConsultarEvaluacionRequest as element() (:: schema-element(ns2:ConsultarEvaluacionRequest) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::), 
                            $ConsultarEvaluacionRequest as element() (:: schema-element(ns2:ConsultarEvaluacionRequest) ::)) 
                            as element() (:: schema-element(ns2:ConsultarEvaluacionFenixRequest) ::) {  
        <ns2:ConsultarEvaluacionFenixRequest>
          <ns2:idOperacion>{fn:data($OutputParameters/ns1:P_RECORDSET/ns1:Row[1]/ns1:Column[@name = 'CODIGO_NEGOCIO'])}</ns2:idOperacion>
        </ns2:ConsultarEvaluacionFenixRequest> 
};

local:func($OutputParameters, $ConsultarEvaluacionRequest)
