xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare variable $outInvokeConsultarContactos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::) external;
declare variable $ListaCorreos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::) external;

declare function local:funcContactosprocesos_to_enviarcorreoelectronico($outInvokeConsultarContactos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::), 
                                                                        $ListaCorreos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::)) 
                                                                        as element() (:: schema-element(cli:ConsultarContactosResponse) ::) {
    <cli:ConsultarContactosResponse>
        <cli:Contactos>
        {
        for $contacto in $outInvokeConsultarContactos.response/cli:Contactos/cli1:Contacto
        return
            <cli1:Contacto>
                <cli1:correoElectronico>{fn:data($contacto/cli1:correoElectronico)}</cli1:correoElectronico>
            </cli1:Contacto>
        }
        
         <cli1:Contacto>
                <cli1:correoElectronico>{fn:data($ListaCorreos.response/cli:Contactos/cli1:Contacto/cli1:correoElectronico)}</cli1:correoElectronico>
            </cli1:Contacto>
        </cli:Contactos>
        
    </cli:ConsultarContactosResponse>
};

local:funcContactosprocesos_to_enviarcorreoelectronico($outInvokeConsultarContactos.response, $ListaCorreos.response)
