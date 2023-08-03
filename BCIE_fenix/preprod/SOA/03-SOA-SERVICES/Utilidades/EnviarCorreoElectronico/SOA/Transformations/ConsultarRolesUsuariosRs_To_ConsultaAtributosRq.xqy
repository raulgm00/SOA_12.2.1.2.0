xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace usu="http://www.bcie.org/UsuarioMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace usu1 = "http://www.bcie.org/UsuarioBO";

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare variable $inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) external;
declare variable $varInvokeObtenerRolesUsuariosByPlantillaRs.response as element() (:: schema-element(cor:ObtenerRolesUsuariosByPlantillaResponse) ::) external;
declare variable $InvokeConsultarAtributos_TO_consultarAtributosUsuario_OutputVariable.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::) external;

declare function local:funcConsultarrolesusuariosrs_to_consultaatributosrq($inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::), 
                                                                           $varInvokeObtenerRolesUsuariosByPlantillaRs.response as element() (:: schema-element(cor:ObtenerRolesUsuariosByPlantillaResponse) ::),
                                                                           $InvokeConsultarAtributos_TO_consultarAtributosUsuario_OutputVariable.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::)) 
                                                                           as element() (:: schema-element(usu:ConsultarAtributosUsuarioRequest) ::) {
    <usu:ConsultarAtributosUsuarioRequest>
        <usu:listaNombres>
        
             {
                for $usuario in $inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:to/cor1:usuario
                return 
                <usu1:nombreUsuario>{fn:data($usuario)}</usu1:nombreUsuario>
            }
            {
                for $usuario1 in $varInvokeObtenerRolesUsuariosByPlantillaRs.response/cor:listaUsuariosTo/cor1:usuario
                return 
                <usu1:nombreUsuario>{fn:data($usuario1)}</usu1:nombreUsuario>
            }
            {
                for $usuario2 in $InvokeConsultarAtributos_TO_consultarAtributosUsuario_OutputVariable.response/usu:listaUsuarios/usu1:usuario/usu1:manager
                return 
                <usu1:nombreUsuario>{fn:data($usuario2)}</usu1:nombreUsuario>
            }
        </usu:listaNombres>
    </usu:ConsultarAtributosUsuarioRequest>
};

local:funcConsultarrolesusuariosrs_to_consultaatributosrq($inputVariable.EnviarCorreoElectronicoRequest, $varInvokeObtenerRolesUsuariosByPlantillaRs.response, $InvokeConsultarAtributos_TO_consultarAtributosUsuario_OutputVariable.response)
