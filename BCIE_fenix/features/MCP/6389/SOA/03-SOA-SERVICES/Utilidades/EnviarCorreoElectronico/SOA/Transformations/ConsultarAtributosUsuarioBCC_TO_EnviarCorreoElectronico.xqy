xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace usu="http://www.bcie.org/UsuarioMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare namespace usu1 = "http://www.bcie.org/UsuarioBO";

declare variable $outInvoke_ConsultarAtributosUsuarioCC.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::) external;

declare function local:funcConsultaratributosusuariobcc_to_enviarcorreoelectronico($outInvoke_ConsultarAtributosUsuarioCC.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::)) as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) {
    <cor:EnviarCorreoElectronicoBPELRequest>
        <cor:correoElectronico>
            <cor1:bcc>
            {
            for $mail in distinct-values($outInvoke_ConsultarAtributosUsuarioCC.response/usu:listaUsuarios/usu1:usuario/usu1:mail)
            return
              if ($mail!= "")
              then
                <cor1:usuario>{fn:data($mail)}</cor1:usuario>
              else ()
            }
            </cor1:bcc>
        </cor:correoElectronico>
    </cor:EnviarCorreoElectronicoBPELRequest>
};

local:funcConsultaratributosusuariobcc_to_enviarcorreoelectronico($outInvoke_ConsultarAtributosUsuarioCC.response)
