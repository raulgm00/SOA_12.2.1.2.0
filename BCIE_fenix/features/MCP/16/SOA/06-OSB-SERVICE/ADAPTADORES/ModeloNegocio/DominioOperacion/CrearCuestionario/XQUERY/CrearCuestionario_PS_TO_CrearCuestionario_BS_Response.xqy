xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/SP_GENERA_CUESTIONARIO";
(:: import schema at "../XSD/GenerarCuestionarioSP.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace eva = "http://www.bcie.org/EvaluacionBO";

declare variable $CrearCuestionarioResponse as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($CrearCuestionarioResponse as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearCuestionarioResponse) ::) {
    <ns2:CrearCuestionarioResponse>                
            {
          let $crearcuestionario := $CrearCuestionarioResponse/ns1:RECORDSET/ns1:Row
        return
          for $crear in $crearcuestionario
          return 
            <ns2:PreguntaCuestionario>
                <eva:idPregunta>{fn:data($crear/ns1:ID)}</eva:idPregunta>
                <eva:Pregunta>{fn:data($crear/ns1:PREGUNTA)}</eva:Pregunta>
                <eva:Responsable>{fn:data($crear/ns1:RESPONSABLE)}</eva:Responsable>
            </ns2:PreguntaCuestionario>
            }
            <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Cuestionario creado exitosamente</res:message>
            
        </ns2:Resultado>
      
        
    </ns2:CrearCuestionarioResponse>
};

local:func($CrearCuestionarioResponse)