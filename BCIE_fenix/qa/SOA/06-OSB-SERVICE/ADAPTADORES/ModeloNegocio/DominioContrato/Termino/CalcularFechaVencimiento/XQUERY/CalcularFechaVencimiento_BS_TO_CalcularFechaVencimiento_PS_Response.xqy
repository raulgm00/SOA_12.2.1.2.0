xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/FENIX/SP_CALCULA_FECHA_VENCIMIENTO/";
(:: import schema at "../XSD/CalcularFechaVencimiento_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CalcularFechaVencimientoResponse) ::) {
    <ns2:CalcularFechaVencimientoResponse>
        <ns2:Resultado>{
        if ($OutputParameters/ns1:RESUL=1) then 
        
            <res:result>OK</res:result>
            else
             <res:result>ERROR</res:result>
            }
            
            {
        if ($OutputParameters/ns1:RESUL=1) then 
        
            <res:message>Actualización realizada exitosamente</res:message>
            else 
             <res:message></res:message>
          }
        </ns2:Resultado>
    </ns2:CalcularFechaVencimientoResponse>
};

local:func($OutputParameters)
