xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/UsuarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace usu = "http://www.bcie.org/UsuarioBO";

declare variable $usuario as xs:string external;

declare function tns:func($usuario as xs:string) as element() (:: schema-element(ns1:ConsultarAtributosUsuarioRequest) ::) {
    <ns1:ConsultarAtributosUsuarioRequest>
        <ns1:listaNombres>
            <usu:nombreUsuario>{fn:data($usuario)}</usu:nombreUsuario>
        </ns1:listaNombres>
    </ns1:ConsultarAtributosUsuarioRequest>
};

tns:func($usuario)
