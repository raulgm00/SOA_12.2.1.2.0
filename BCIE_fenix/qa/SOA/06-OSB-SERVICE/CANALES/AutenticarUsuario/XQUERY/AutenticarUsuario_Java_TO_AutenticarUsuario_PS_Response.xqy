xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AutenticarUsuario";
(:: import schema at "../XSD/AutenticarUsuarioResponse_JSON.xsd" ::)

declare variable $responseJava as xs:boolean external;

declare function local:func($responseJava as xs:boolean) as element() (:: schema-element(ns1:AutenticarUsuario) ::) {
    <ns1:AutenticarUsuario>
        <ns1:respuesta>{fn:data($responseJava)}</ns1:respuesta>
        <ns1:Resultado>
            <ns1:result>OK</ns1:result>
        </ns1:Resultado>
    </ns1:AutenticarUsuario>
};

local:func($responseJava)
