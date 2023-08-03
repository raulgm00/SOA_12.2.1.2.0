xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace usu="http://www.bcie.org/UsuarioMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare namespace usu1 = "http://www.bcie.org/UsuarioBO";

declare variable $CorreosTo as element() (:: schema-element(cor:TipoString) ::) external;
declare variable $outInvoke_ConsultarAtributosUsuarioTO.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::) external;

declare function local:funcConsultaratributosrs_to_correostorq($CorreosTo as element() (:: schema-element(cor:TipoString) ::), 
                                                               $outInvoke_ConsultarAtributosUsuarioTO.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::)) 
                                                               as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) {
    <cor:EnviarCorreoElectronicoBPELRequest>
        <cor:correoElectronico>
            <cor1:to>
            {
                    for $usuario in distinct-values($outInvoke_ConsultarAtributosUsuarioTO.response/usu:listaUsuarios/usu1:usuario/usu1:mail)
                    return 
                    if($usuario !='')
                    then
                <cor1:usuario>{fn:data($usuario)}</cor1:usuario>
                else()
              }
              {
                if($CorreosTo != '')
                then <cor1:usuario>{fn:data($CorreosTo)}</cor1:usuario>
                else()
                }
            </cor1:to>
        </cor:correoElectronico>
    </cor:EnviarCorreoElectronicoBPELRequest>
};

local:funcConsultaratributosrs_to_correostorq($CorreosTo, $outInvoke_ConsultarAtributosUsuarioTO.response)
