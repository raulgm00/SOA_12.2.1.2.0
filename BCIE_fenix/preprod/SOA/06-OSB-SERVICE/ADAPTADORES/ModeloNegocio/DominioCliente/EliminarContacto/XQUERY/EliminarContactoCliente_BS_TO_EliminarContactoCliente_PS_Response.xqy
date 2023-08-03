xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $EliminarContactoRequest as element() (:: schema-element(ns1:EliminarContactoRequest) ::) external;

declare function local:func($EliminarContactoRequest as element() (:: schema-element(ns1:EliminarContactoRequest) ::)) as element() (:: schema-element(ns1:EliminarContactoResponse) ::) {
    <ns1:EliminarContactoResponse>
        {
            if ($EliminarContactoRequest/ns1:idContacto)
            then <ns1:idContacto>{fn:data($EliminarContactoRequest/ns1:idContacto)}</ns1:idContacto>
            else ()
        }
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Contacto eliminado satisfactoriamente</res:message>
        </ns1:Resultado>
    </ns1:EliminarContactoResponse>
};

local:func($EliminarContactoRequest)
