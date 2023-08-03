xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $outInvoke_ConsultarRolByPlantilla.ConsultarRolByPlantillaResponse as element() (:: schema-element(cor:ConsultaRolByPlantillaResponse) ::) external;
declare variable $inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) external;

declare function local:funcConsultarrolbyplantilla_to_enviarcorreoelectronicocc($outInvoke_ConsultarRolByPlantilla.ConsultarRolByPlantillaResponse as element() (:: schema-element(cor:ConsultaRolByPlantillaResponse) ::), 
                                                                                $inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::)) 
                                                                                as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) {
    <cor:EnviarCorreoElectronicoBPELRequest>
        <cor:correoElectronico>
            <cor1:to>
                {
                    for $to in $inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:to/cor1:usuario
                    return 
                    <cor1:usuario>{fn:data($to)}</cor1:usuario>
                }
            </cor1:to>
            <cor1:bcc>
                {
                    for $bcc in $inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:bcc/cor1:usuario
                    return 
                    <cor1:usuario>{fn:data($bcc)}</cor1:usuario>
                }
            </cor1:bcc>
            <cor1:cc>
             {
                    for $cc in $inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:cc/cor1:usuario
                    return 
                    <cor1:usuario>{fn:data($cc)}</cor1:usuario>
            }
            {
            for $rol in $outInvoke_ConsultarRolByPlantilla.ConsultarRolByPlantillaResponse/cor:ListaRoles/cor1:Roles
            return
                <cor1:usuario>{fn:data($rol/cat:DescripcionCorta)}</cor1:usuario>     
            }
            </cor1:cc>
              <cor1:idOperacion>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:idOperacion)}</cor1:idOperacion>
              <cor1:idClienteFenix>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:idClienteFenix)}</cor1:idClienteFenix>
              <cor1:replyToAddress>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:replyToAddress)}</cor1:replyToAddress>
              <cor1:subject>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:subject)}</cor1:subject>
              <cor1:mensaje>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:mensaje)}</cor1:mensaje>
              <cor1:id_plantilla>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:id_plantilla)}</cor1:id_plantilla>
              <cor1:proceso>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:proceso)}</cor1:proceso>
             <cor1:evento>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:evento)}</cor1:evento>
            {
                for $parametros in $inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:parametros
                return 
                <cor1:parametros>
                   <cor1:tag>{fn:data($parametros/cor1:tag)}</cor1:tag>
                   <cor1:valor>{fn:data($parametros/cor1:valor)}</cor1:valor>
                </cor1:parametros>
            }
            <cor1:attachment>
                <cor1:name>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:attachment/cor1:name)}</cor1:name>
                <cor1:type>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:attachment/cor1:type)}</cor1:type>
                <cor1:content>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:attachment/cor1:content)}</cor1:content>
            </cor1:attachment>
        </cor:correoElectronico>
    </cor:EnviarCorreoElectronicoBPELRequest>
};

local:funcConsultarrolbyplantilla_to_enviarcorreoelectronicocc($outInvoke_ConsultarRolByPlantilla.ConsultarRolByPlantillaResponse, $inputVariable.EnviarCorreoElectronicoRequest)
