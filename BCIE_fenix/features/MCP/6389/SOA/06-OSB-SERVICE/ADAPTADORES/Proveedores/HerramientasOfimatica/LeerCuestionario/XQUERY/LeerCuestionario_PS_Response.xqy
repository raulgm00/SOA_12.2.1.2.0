xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:LeerCuestionarioResponse) ::) {
    <ns1:LeerCuestionarioResponse>
        <ns1:Respuesta>
            <res:result>OK</res:result>
            <res:message>La inserción se ha realizado correctamente</res:message>
        </ns1:Respuesta>
    </ns1:LeerCuestionarioResponse>
};

local:func()
