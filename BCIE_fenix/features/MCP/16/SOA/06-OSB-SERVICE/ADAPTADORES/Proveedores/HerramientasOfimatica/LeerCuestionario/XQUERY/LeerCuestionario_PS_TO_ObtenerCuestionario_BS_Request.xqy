xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ObtenerCuestionario";
(:: import schema at "../XSD/ObtenerCuestionario.xsd" ::)

declare variable $CuestionarioRequest as element() (:: schema-element(ns1:ObtenerCuestionarioRequest) ::) external;

declare function local:func($CuestionarioRequest as element() (:: schema-element(ns1:ObtenerCuestionarioRequest) ::)) as element() (:: schema-element(ns2:ObtenerCuestionarioInput) ::) {
    <ns2:ObtenerCuestionarioInput>
        <ns2:idOperacion>{fn:data($CuestionarioRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idResponsable>{fn:data($CuestionarioRequest/ns1:idResponsable)}</ns2:idResponsable>
    </ns2:ObtenerCuestionarioInput>
};

local:func($CuestionarioRequest)