xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $idCliente as xs:string external;


declare function local:func($idCliente as xs:string) as element() (:: schema-element(ns1:CrearClienteResponse) ::) {
    <ns1:CrearClienteResponse>
        <ns1:Cliente>
            <cli:idCliente>{fn:data($idCliente)}</cli:idCliente>            
        </ns1:Cliente>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:concat('La inserción se ha realizado correctamente, cliente ID:', xs:string($idCliente))}</res:message>
        </ns1:Resultado>
    </ns1:CrearClienteResponse>
};

local:func($idCliente)
