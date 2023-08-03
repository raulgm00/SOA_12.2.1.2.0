xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/SP_GENERA_CUESTIONARIO";
(:: import schema at "../XSD/GenerarCuestionarioSP.xsd" ::)

declare variable $CrearCuestionarioRequest as element() (:: schema-element(ns1:CrearCuestionarioRequest) ::) external;

declare function local:func($CrearCuestionarioRequest as element() (:: schema-element(ns1:CrearCuestionarioRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:NUM_OPERACION>{fn:data($CrearCuestionarioRequest/ns1:idOperacion)}</ns2:NUM_OPERACION>
        <ns2:NUM_PRODUCTO>{fn:data($CrearCuestionarioRequest/ns1:idProducto)}</ns2:NUM_PRODUCTO>
        <ns2:REGENERAR>{if (fn:data($CrearCuestionarioRequest/ns1:regenerar)=true()) then 1
        else 0
        }</ns2:REGENERAR>
    </ns2:InputParameters>
};

local:func($CrearCuestionarioRequest)