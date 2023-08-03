xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarCuestionariodb";
(:: import schema at "../XSD/ConsultarCuestionariodb_sp.xsd" ::)

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarCuestionarioResponse as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($ConsultarCuestionarioResponse as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarCuestionarioResponse) ::) {
    <ns2:ConsultarCuestionarioResponse>   <ns2:Cuestionarios>          
  
        {
          let $consultacuestionario := $ConsultarCuestionarioResponse/ns1:P_RECORDSET/ns1:Row
        return
          for $consulta in $consultacuestionario
          return 
        <eva:cuestionario>     
            <eva:idCuestionario> {data($consulta/ns1:Column[@name='CODIGO_MODELO'])}</eva:idCuestionario>
            <eva:cuestionario> {data($consulta/ns1:Column[@name='TITULO'])}</eva:cuestionario>
        </eva:cuestionario>
        }
       
          
        
        </ns2:Cuestionarios>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada exitosamente</res:message>
            
        </ns2:Resultado>
    </ns2:ConsultarCuestionarioResponse>
};

local:func($ConsultarCuestionarioResponse)
