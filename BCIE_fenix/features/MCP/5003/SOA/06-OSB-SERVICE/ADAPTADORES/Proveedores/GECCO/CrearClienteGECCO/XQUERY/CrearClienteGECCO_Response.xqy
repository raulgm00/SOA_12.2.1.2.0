xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:CrearClienteResponse) ::) {
    <ns1:CrearClienteResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>La inserción se ha realizado correctamente</res:message>
        </ns1:Resultado>
    </ns1:CrearClienteResponse>
};

local:func()
