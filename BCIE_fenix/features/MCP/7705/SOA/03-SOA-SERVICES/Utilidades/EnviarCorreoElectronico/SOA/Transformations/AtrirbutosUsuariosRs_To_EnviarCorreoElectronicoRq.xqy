xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace usu="http://www.bcie.org/UsuarioMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare namespace usu1 = "http://www.bcie.org/UsuarioBO";

declare variable $vaIinvokeConsultarAtributosUsuarioErrorRs.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::) external;

declare function local:funcAtrirbutosusuariosrs_to_enviarcorreoelectronicorq($vaIinvokeConsultarAtributosUsuarioErrorRs.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::)) as element() (:: schema-element(cor:enviarCorreoElectronicoRequest) ::) {
    <cor:enviarCorreoElectronicoRequest>
        <cor:CorreoElectronico>
            <cor1:to>
             {
                    for $usuario in distinct-values($vaIinvokeConsultarAtributosUsuarioErrorRs.response/usu:listaUsuarios/usu1:usuario/usu1:mail)
                    return 
                    if($usuario !='')
                    then
                <cor1:usuario>{fn:data($usuario)}</cor1:usuario>
                else()
              }
         </cor1:to> 
        </cor:CorreoElectronico>
    </cor:enviarCorreoElectronicoRequest>
};

local:funcAtrirbutosusuariosrs_to_enviarcorreoelectronicorq($vaIinvokeConsultarAtributosUsuarioErrorRs.response)
