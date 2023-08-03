xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace not="http://www.bcie.org/NotificarCancelarOperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/NotificarCancelarOperacion/V1/Schema/NotificarCancelarOperacionMO.xsd" ::)
declare namespace ope="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace usu="http://www.bcie.org/UsuarioMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare namespace ope1 = "http://www.bcie.org/OperacionBO";

declare namespace usu1 = "http://www.bcie.org/UsuarioBO";

declare namespace not1 = "http://www.bcie.org/NotificarCancelarOperacionBO";

declare variable $inputVariable.request as element() (:: schema-element(not:requestNotificarCancelarOperacion) ::) external;
declare variable $outConsultarOperacion.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) external;
declare variable $outConsultaAtributosUsuario.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::) external;

declare function local:funcRequestenviarcorreooperacionsusp1($inputVariable.request as element() (:: schema-element(not:requestNotificarCancelarOperacion) ::), 
                                                             $outConsultarOperacion.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::), 
                                                             $outConsultaAtributosUsuario.response as element() (:: schema-element(usu:ConsultarAtributosUsuarioResponse) ::)) 
                                                             as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) {
    <cor:EnviarCorreoElectronicoBPELRequest>
        <cor:correoElectronico>
            <cor1:to>
                <cor1:usuario>{fn:data($outConsultarOperacion.response/ope:Operacion/ope1:responsable)}</cor1:usuario>
                <cor1:usuario>{fn:data($outConsultaAtributosUsuario.response/usu:listaUsuarios/usu1:usuario[1]/usu1:manager)}</cor1:usuario>
            </cor1:to>
            <cor1:bcc>
                <cor1:usuario></cor1:usuario>
            </cor1:bcc>
            <cor1:cc>
                <cor1:usuario></cor1:usuario>
            </cor1:cc>
            <cor1:idOperacion>{fn:data($inputVariable.request/not:Operacion/not1:idOperacion)}</cor1:idOperacion>
            <cor1:idClienteFenix></cor1:idClienteFenix>
            <cor1:replyToAddress></cor1:replyToAddress>
            <cor1:subject></cor1:subject>
            <cor1:mensaje></cor1:mensaje>
            <cor1:id_plantilla>23</cor1:id_plantilla>
            <cor1:proceso></cor1:proceso>
            <cor1:evento></cor1:evento>
            <cor1:parametros>
                <cor1:tag>OPERACION</cor1:tag>
                <cor1:valor>{fn:data($inputVariable.request/not:Operacion/not1:idOperacion)}</cor1:valor>
            </cor1:parametros>
        </cor:correoElectronico>
    </cor:EnviarCorreoElectronicoBPELRequest>
};

local:funcRequestenviarcorreooperacionsusp1($inputVariable.request, $outConsultarOperacion.response, $outConsultaAtributosUsuario.response)
