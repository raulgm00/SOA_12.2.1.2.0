xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/SP_GENERAR_CUESTIONARIO_PROCESO";
(:: import schema at "../../XSD/GenerarCuestionarioSP_V2.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearCuestionario2Response) ::) {
    <ns2:CrearCuestionario2Response>
        <ns2:respuesta>{fn:data($OutputParameters/ns1:RESPUESTA)}</ns2:respuesta>
        <ns2:Resultado>
             <res:result>OK</res:result>
            <res:message>Cuestionario creado exitosamente</res:message>
        </ns2:Resultado>
    </ns2:CrearCuestionario2Response>
};

local:func($OutputParameters)
