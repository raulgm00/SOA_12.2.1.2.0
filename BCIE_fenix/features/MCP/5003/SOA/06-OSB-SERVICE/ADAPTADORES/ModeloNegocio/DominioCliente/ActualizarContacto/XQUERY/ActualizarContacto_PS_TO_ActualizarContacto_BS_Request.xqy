xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizaContactosDB";
(:: import schema at "../XSD/ActualizarContacto_table.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ActualizarContactoRequest as element() (:: schema-element(ns1:ActualizarContactoRequest) ::) external;

declare function local:func($ActualizarContactoRequest as element() (:: schema-element(ns1:ActualizarContactoRequest) ::)) as element() (:: schema-element(ns2:ContactosCollection) ::) {
    <ns2:ContactosCollection>
      { for $contacto in $ActualizarContactoRequest/ns1:Contacto
            return 
        <ns2:Contactos>
            <ns2:idContacto>{fn:data($contacto/cli:idContacto)}</ns2:idContacto>
            <ns2:nombre>{fn:data($contacto/cli:nombre)}</ns2:nombre>
            <ns2:correoElectronico>{fn:data($contacto/cli:correoElectronico)}</ns2:correoElectronico>
            <ns2:cargo>{fn:data($contacto/cli:cargo)}</ns2:cargo>
            <ns2:telefono>{fn:data($contacto/cli:telefono)}</ns2:telefono>
            </ns2:Contactos>
            }
    </ns2:ContactosCollection>
};

local:func($ActualizarContactoRequest)