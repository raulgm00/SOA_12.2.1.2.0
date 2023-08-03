xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/SP_GENERAR_CUESTIONARIO_PROCESO";
(:: import schema at "../../XSD/GenerarCuestionarioSP_V2.xsd" ::)

declare variable $CrearCuestionario2Request as element() (:: schema-element(ns1:CrearCuestionario2Request) ::) external;

declare function local:func($CrearCuestionario2Request as element() (:: schema-element(ns1:CrearCuestionario2Request) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:NUM_OPERACION>{fn:data($CrearCuestionario2Request/ns1:idOperacion)}</ns2:NUM_OPERACION>
        <ns2:NUM_PROCESO_BPM>{fn:data($CrearCuestionario2Request/ns1:idProceso)}</ns2:NUM_PROCESO_BPM>
        <ns2:REGENERAR>{if (fn:data($CrearCuestionario2Request/ns1:regenerar)=true()) then 1
        else 0
        }</ns2:REGENERAR>
    </ns2:InputParameters>
};

local:func($CrearCuestionario2Request)
