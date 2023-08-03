xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearContacto";
(:: import schema at "../XSD/CrearContacto_table.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $CrearContactoRequest as element() (:: schema-element(ns1:InsertarContactoRequest) ::) external;

declare function local:func($CrearContactoRequest as element() (:: schema-element(ns1:InsertarContactoRequest) ::)) as element() (:: schema-element(ns2:ContactosCollection) ::) {
    <ns2:ContactosCollection>
        <ns2:Contactos>
            <ns2:idContacto> </ns2:idContacto>
            <ns2:nombre>{fn:data($CrearContactoRequest/ns1:Contacto/cli:nombre)}</ns2:nombre>
            <ns2:correoElectronico>{fn:data($CrearContactoRequest/ns1:Contacto/cli:correoElectronico)}</ns2:correoElectronico>
            <ns2:cargo>{fn:data($CrearContactoRequest/ns1:Contacto/cli:cargo)}</ns2:cargo>
            <ns2:telefono>{fn:data($CrearContactoRequest/ns1:Contacto/cli:telefono)}</ns2:telefono>
            {
if (fn:string(fn:data($CrearContactoRequest/ns1:Contacto/cli:fechaRegistro))= '') then
<ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
                else
                    <ns2:fechaRegistro>{fn:data($CrearContactoRequest/ns1:Contacto/cli:fechaRegistro)}</ns2:fechaRegistro>
            }
           
            </ns2:Contactos>
    </ns2:ContactosCollection>
};

local:func($CrearContactoRequest)
