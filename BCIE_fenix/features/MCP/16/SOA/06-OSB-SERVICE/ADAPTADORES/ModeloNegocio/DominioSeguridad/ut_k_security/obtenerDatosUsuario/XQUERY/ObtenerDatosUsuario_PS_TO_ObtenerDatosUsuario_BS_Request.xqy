xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SeguridadMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioSeguridad/Schema/SeguridadMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/obtenerDatosUsuario";
(:: import schema at "../XSD/obtenerDatosUsuario_sp.xsd" ::)

declare variable $ObtenerDatosUsuario as element() (:: schema-element(ns1:ObtenerDatosUsuarioRequest) ::) external;

declare function local:func($ObtenerDatosUsuario as element() (:: schema-element(ns1:ObtenerDatosUsuarioRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PVNOMBREUSUARIO>{fn:data($ObtenerDatosUsuario/ns1:NombreUsuario)}</ns2:PVNOMBREUSUARIO>
    </ns2:InputParameters>
};

local:func($ObtenerDatosUsuario)