xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/UsuarioMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)
declare namespace usu = "http://www.bcie.org/UsuarioBO";

declare variable $user as xs:string external;

declare function local:func($user as xs:string) as element() (:: schema-element(ns1:ConsultarAtributosUsuarioRequest) ::) {
    <ns1:ConsultarAtributosUsuarioRequest>
        <ns1:listaNombres>
            <usu:nombreUsuario>{fn:data($user)}</usu:nombreUsuario>
        </ns1:listaNombres>
    </ns1:ConsultarAtributosUsuarioRequest>
};

local:func($user)
