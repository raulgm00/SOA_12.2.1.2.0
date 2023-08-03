xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearEvaluacionDB";
(:: import schema at "../XSD/CrearEvaluacionDB_table.xsd" ::)

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $EvaluacionCollection as element() (:: schema-element(ns1:EvaluacionCollection) ::) external;

declare function local:func($EvaluacionCollection as element() (:: schema-element(ns1:EvaluacionCollection) ::)) as element() (:: schema-element(ns2:CrearEvaluacionResponse) ::) {
    <ns2:CrearEvaluacionResponse>
        <ns2:Evaluaciones>
            { for $eval in $EvaluacionCollection/ns1:Evaluacion
            return 
            <eva:evaluacion>
                <eva:id>{fn:data($eval/ns1:id)}</eva:id>
               
            </eva:evaluacion>
            }
        </ns2:Evaluaciones>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Evaluación Creada</res:message>
          
        </ns2:Resultado>
    </ns2:CrearEvaluacionResponse>
};

local:func($EvaluacionCollection)
