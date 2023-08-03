xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ObtenerRolesCuestionario";
(:: import schema at "../XSD/ObtenerRolesCuestionario.xsd" ::)

declare variable $ObtenerRolesCuestionarioRequest as element() (:: schema-element(ns1:ObtenerRolesCuestionarioRequest) ::) external;

declare function local:func($ObtenerRolesCuestionarioRequest as element() (:: schema-element(ns1:ObtenerRolesCuestionarioRequest) ::)) as element() (:: schema-element(ns2:ObtenerRolesCuestionarioInput) ::) {
    <ns2:ObtenerRolesCuestionarioInput>
        <ns2:idOperacion>{fn:data($ObtenerRolesCuestionarioRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ObtenerRolesCuestionarioInput>
};

local:func($ObtenerRolesCuestionarioRequest)
