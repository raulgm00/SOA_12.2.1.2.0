xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizaContactosDB";
(:: import schema at "../XSD/ActualizarContacto_table.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $contactos as element() (:: element(*, ns1:ContactosCollection) ::) external;

declare function local:func($contactos as element() (:: element(*, ns1:ContactosCollection) ::)) as element() (:: schema-element(ns2:ActualizarContactoResponse) ::) {
    <ns2:ActualizarContactoResponse>
    { for $contacto in $contactos/ns1:Contactos
            return 
        <ns2:Contacto>
            <cli:idContacto>{fn:data($contacto/ns1:idContacto)}</cli:idContacto>
            <cli:nombre>{fn:data($contacto/ns1:nombre)}</cli:nombre>
            <cli:telefono>{fn:data($contacto/ns1:telefono)}</cli:telefono>
            <cli:correoElectronico>{fn:data($contacto/ns1:correoElectronico)}</cli:correoElectronico>
            <cli:cargo>{fn:data($contacto/ns1:cargo)}</cli:cargo></ns2:Contacto>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Contacto Actualizado</res:message>
        </ns2:Resultado></ns2:ActualizarContactoResponse>
};

local:func($contactos)