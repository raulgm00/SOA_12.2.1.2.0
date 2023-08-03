xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.com/Cuestionario/LeerExcel";
(:: import schema at "../XSD/LeerCuestionario.xsd" ::)
declare namespace ns1="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)

declare namespace cue = "http://xmlns.bcie.com/CuestionarioBO";

declare variable $LeerCuestionario as element() (:: schema-element(ns1:LeerCuestionarioRequest) ::) external;

declare function local:func($LeerCuestionario as element() (:: schema-element(ns1:LeerCuestionarioRequest) ::)) as element() (:: schema-element(ns2:LeerDatos) ::) {
    <ns2:LeerDatos>
        <archivo>{fn:data($LeerCuestionario/ns1:Archivo)}</archivo>
    </ns2:LeerDatos>
};

local:func($LeerCuestionario)
