xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarRolUsuarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarRolUsuario/V1/Schema/ConsultarRolUsuarioMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolUsuario_DB";
(:: import schema at "../XSD/ConsultarRolUsuario_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConsultarRolUsuarioRequest as element() (:: schema-element(ns1:ConsultarRolUsuarioRequest) ::) external;

declare function local:func($ConsultarRolUsuarioRequest as element() (:: schema-element(ns1:ConsultarRolUsuarioRequest) ::)) as element() (:: schema-element(ns2:ConsultarRolUsuario_DBInput) ::) {
    <ns2:ConsultarRolUsuario_DBInput>
        <ns2:idRolUsuario>{fn:data($ConsultarRolUsuarioRequest/ns1:idRolUsuario/cat:Id)}</ns2:idRolUsuario>
    </ns2:ConsultarRolUsuario_DBInput>
};

local:func($ConsultarRolUsuarioRequest)
