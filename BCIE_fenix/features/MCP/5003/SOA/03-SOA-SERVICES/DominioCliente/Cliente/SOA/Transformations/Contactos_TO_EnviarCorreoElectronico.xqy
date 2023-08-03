  xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outInvokeConsultarContactos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::) external;
declare variable $ListaCorreos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(cli:EnviarDocumentoClienteRequest) ::) external;

declare function local:funcContactos_to_enviarcorreoelectronico($outInvokeConsultarContactos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::), 
                                                                 $ListaCorreos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::), 
                                                                 $inputVariable.request as element() (:: schema-element(cli:EnviarDocumentoClienteRequest) ::)) 
                                                                 as element() (:: schema-element(cli:ConsultarContactosResponse) ::) {
                                                                
    let $contactos:= $outInvokeConsultarContactos.response/cli:Contactos/cli1:Contacto[cli1:recibeAvisoCobro = true()]
    return
    <cli:ConsultarContactosResponse>
    {
    if ($inputVariable.request/cli:idProceso = 18 or $inputVariable.request/cli:idProceso = 32)
    then
        <cli:Contactos>
        {
        for $contacto in $contactos
        return
            <cli1:Contacto>
                <cli1:correoElectronico>{fn:data($contacto/cli1:correoElectronico)}</cli1:correoElectronico>
                </cli1:Contacto>
 
        }
           <cli1:Contacto>
                <cli1:correoElectronico>{fn:data($ListaCorreos.response/cli:Contactos/cli1:Contacto/cli1:correoElectronico)}</cli1:correoElectronico>
            </cli1:Contacto>
        
        </cli:Contactos>
    
    else
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
        }
    </cli:ConsultarContactosResponse>
};

local:funcContactos_to_enviarcorreoelectronico($outInvokeConsultarContactos.response, $ListaCorreos.response, $inputVariable.request)
