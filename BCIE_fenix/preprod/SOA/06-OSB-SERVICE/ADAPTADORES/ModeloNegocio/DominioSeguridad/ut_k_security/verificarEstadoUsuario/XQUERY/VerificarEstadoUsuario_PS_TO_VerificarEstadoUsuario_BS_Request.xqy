xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SeguridadMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioSeguridad/Schema/SeguridadMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/verificarEstadoUsuario";
(:: import schema at "../XSD/verificarEstadoUsuario_sp.xsd" ::)

declare variable $VerificarEstadoUsuarioRequest as element() (:: schema-element(ns1:VerificarEstadoUsuarioRequest) ::) external;

declare function local:func($VerificarEstadoUsuarioRequest as element() (:: schema-element(ns1:VerificarEstadoUsuarioRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PVNOMBREUSUARIO>{fn:data($VerificarEstadoUsuarioRequest/ns1:NombreUsuario)}</ns2:PVNOMBREUSUARIO>
    </ns2:InputParameters>
};

local:func($VerificarEstadoUsuarioRequest)