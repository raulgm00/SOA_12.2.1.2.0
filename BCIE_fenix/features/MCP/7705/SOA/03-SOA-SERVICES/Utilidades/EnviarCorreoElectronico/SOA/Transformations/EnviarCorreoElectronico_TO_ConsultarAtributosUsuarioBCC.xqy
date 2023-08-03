xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace usu="http://www.bcie.org/UsuarioMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace usu1 = "http://www.bcie.org/UsuarioBO";

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare variable $inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) external;

declare function local:funcEnviarcorreoelectronico_to_consultaratributosusuariobcc($inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::)) as element() (:: schema-element(usu:ConsultarAtributosUsuarioRequest) ::) {
    <usu:ConsultarAtributosUsuarioRequest>
        <usu:listaNombres>
            {
                for $usuario in $inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:bcc/cor1:usuario
                return 
                <usu1:nombreUsuario>{fn:data($usuario)}</usu1:nombreUsuario>
            }
        </usu:listaNombres>
    </usu:ConsultarAtributosUsuarioRequest>
};

local:funcEnviarcorreoelectronico_to_consultaratributosusuariobcc($inputVariable.EnviarCorreoElectronicoRequest)
