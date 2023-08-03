xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContactos";
(:: import schema at "../XSD/ConsultarContactos.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarContactosOutputCollection as element() (:: schema-element(ns1:ConsultarContactosOutputCollection) ::) external;

declare function local:func($ConsultarContactosOutputCollection as element() (:: schema-element(ns1:ConsultarContactosOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarContactosResponse) ::) {
    <ns2:ConsultarContactosResponse>
        <ns2:Contactos>
        {if(fn:count($ConsultarContactosOutputCollection/ns1:ConsultarContactosOutput[1]) > 0)then
        for $contactos in $ConsultarContactosOutputCollection/ns1:ConsultarContactosOutput
        return
            <cli:Contacto>
                <cli:idContacto>{fn:data($contactos/ns1:ID_CONTACTO)}</cli:idContacto>
                <cli:idCliente>{fn:data($contactos/ns1:ID_CLIENTE)}</cli:idCliente>
                <cli:nombre>{fn:data($contactos/ns1:NOMBRE)}</cli:nombre>
                <cli:telefono>{fn:data($contactos/ns1:TELEFONO)}</cli:telefono>
                <cli:correoElectronico>{fn:data($contactos/ns1:CORREO_ELECTRONICO)}</cli:correoElectronico>
                <cli:cargo>{fn:data($contactos/ns1:CARGO)}</cli:cargo>
                <cli:tipo>{if(string(fn:data($contactos/ns1:RECIBE_AVISO_COBRO))='') then 'Nota' else if (fn:data($contactos/ns1:RECIBE_AVISO_COBRO)= 1) then 'Facturacion' else 'Nota'}</cli:tipo>
                <cli:fechaRegistro>{fn:data($contactos/ns1:FECHA_REGISTRO)}</cli:fechaRegistro>
                <cli:idContactosClientes>{fn:data($contactos/ns1:ID_CONTACTOS_CLIENTES)}</cli:idContactosClientes>
                <cli:recibeAvisoCobro>{if(string(fn:data($contactos/ns1:RECIBE_AVISO_COBRO))='') then false() else if (fn:data($contactos/ns1:RECIBE_AVISO_COBRO)= 1) then true() else false()}</cli:recibeAvisoCobro>
            </cli:Contacto>
        else()
        }
        </ns2:Contactos>
   
    <ns2:Resultado>
        <res:result>OK</res:result>
        {if(fn:count($ConsultarContactosOutputCollection/ns1:ConsultarContactosOutput[1]) > 0)then
          <res:message>Consulta exitosa</res:message>
        else
          <res:message>Consulta sin resultados</res:message>
        }
    </ns2:Resultado>
    </ns2:ConsultarContactosResponse>
};

local:func($ConsultarContactosOutputCollection)
