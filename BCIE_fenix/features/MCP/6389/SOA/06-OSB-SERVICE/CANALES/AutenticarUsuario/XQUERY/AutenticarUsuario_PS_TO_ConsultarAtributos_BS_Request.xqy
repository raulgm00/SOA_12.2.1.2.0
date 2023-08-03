xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/UsuarioMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace usu = "http://www.bcie.org/UsuarioBO";

declare variable $AutenticarUsuarioRequest as element() (:: schema-element(ns1:AutenticarUsuarioRequest) ::) external;

declare function local:func($AutenticarUsuarioRequest as element() (:: schema-element(ns1:AutenticarUsuarioRequest) ::)) as element() (:: schema-element(ns1:ConsultarAtributosUsuarioRequest) ::) {
    <ns1:ConsultarAtributosUsuarioRequest>
        <ns1:listaNombres>
            <usu:nombreUsuario>{fn:data($AutenticarUsuarioRequest/ns1:usuario/usu:usuario)}</usu:nombreUsuario>
        </ns1:listaNombres>
    </ns1:ConsultarAtributosUsuarioRequest>
};

local:func($AutenticarUsuarioRequest)
