xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearContacto";
(:: import schema at "../XSD/CrearContacto_table.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $CrearContactoResponse as element() (:: schema-element(ns1:ContactosCollection) ::) external;

declare function local:func($CrearContactoResponse as element() (:: schema-element(ns1:ContactosCollection) ::)) as element() (:: schema-element(ns2:InsertarContactoResponse) ::) {
    <ns2:CrearContactoResponse>
        <ns2:Contacto>
            <cli:idContacto>{fn:data($CrearContactoResponse/ns1:Contactos/ns1:idContacto)}</cli:idContacto></ns2:Contacto>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La inserción se ha realizado correctamente</res:message>
            
        </ns2:Resultado>
    </ns2:CrearContactoResponse>
};

local:func($CrearContactoResponse)
