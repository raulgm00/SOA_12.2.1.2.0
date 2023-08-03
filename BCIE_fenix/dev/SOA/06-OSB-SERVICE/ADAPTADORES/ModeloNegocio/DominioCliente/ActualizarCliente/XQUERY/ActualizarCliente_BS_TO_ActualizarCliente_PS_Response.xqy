xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualiizarClientes";
(:: import schema at "../XSD/ActualiizarClientes_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $actualizarClienteResponse as element() (:: element(*, ns1:Clientes) ::) external;

declare function local:func($actualizarClienteResponse as element() (:: element(*, ns1:Clientes) ::)) as element() (:: schema-element(ns2:ActualizarClienteResponse) ::) {
    <ns2:ActualizarClienteResponse>
        <ns2:Cliente>
            <cli:idCliente>{fn:data($actualizarClienteResponse/ns1:idCliente)}</cli:idCliente>
            {
                if ($actualizarClienteResponse/ns1:idFlexcube)
                then <cli:idFacturador>{fn:data($actualizarClienteResponse/ns1:idFlexcube)}</cli:idFacturador>
                else ()
            }
            {
                if ($actualizarClienteResponse/ns1:razonSocial)
                then <cli:razonSocial>{fn:data($actualizarClienteResponse/ns1:razonSocial)}</cli:razonSocial>
                else ()
            }</ns2:Cliente>
        <ns2:Resultado>
            <res:result>Ok</res:result>
            <res:message>Actualización Realizada Exitosamente</res:message>
            
        </ns2:Resultado>
    </ns2:ActualizarClienteResponse>
};

local:func($actualizarClienteResponse)
