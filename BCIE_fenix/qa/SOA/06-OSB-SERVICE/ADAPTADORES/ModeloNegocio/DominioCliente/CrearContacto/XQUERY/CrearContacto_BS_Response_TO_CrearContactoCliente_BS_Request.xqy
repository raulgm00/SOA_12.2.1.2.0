xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertaContactosClienteService";
(:: import schema at "../../InsertarContactoCliente/XSD/InsertaContactosClienteService_table.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearContacto";
(:: import schema at "../XSD/CrearContacto_table.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $CrearContactoRequest as element() (:: schema-element(ns1:InsertarContactoRequest) ::) external;
declare variable $ContactosCollection as element() (:: schema-element(ns2:ContactosCollection) ::) external;

declare function local:func($CrearContactoRequest as element() (:: schema-element(ns1:InsertarContactoRequest) ::), 
                            $ContactosCollection as element() (:: schema-element(ns2:ContactosCollection) ::)) 
                            as element() (:: schema-element(ns3:ContactosClienteCollection) ::) {
    <ns3:ContactosClienteCollection>
        <ns3:ContactosCliente>
            <ns3:idContactosClientes> </ns3:idContactosClientes>
            <ns3:idCliente>{fn:data($CrearContactoRequest/ns1:Contacto/cli:idCliente)}</ns3:idCliente>
            <ns3:idContacto>{fn:data($ContactosCollection/ns2:Contactos/ns2:idContacto)}</ns3:idContacto>
            {
                if (fn:string(fn:data($CrearContactoRequest/ns1:Contacto/cli:fechaRegistro))= '') then
                    <ns3:fechaRegistro>{fn:current-dateTime()}</ns3:fechaRegistro>
                  
                else
               <ns3:fechaRegistro>{fn:data($CrearContactoRequest/ns1:Contacto/cli:fechaRegistro)}</ns3:fechaRegistro>
            }
        </ns3:ContactosCliente>
    </ns3:ContactosClienteCollection>
};

local:func($CrearContactoRequest, $ContactosCollection)
