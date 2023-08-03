xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare variable $inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) external;

declare function local:funcEnviarcorreoelectronico_to_construircorreoelectronico($inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::)) as element() (:: schema-element(cor:ConstruirCorreoElectronicoRequest) ::) {
    <cor:ConstruirCorreoElectronicoRequest>

        <cor:CorreoElectronico>
            <cor1:to>
                    <cor1:usuario>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:to/cor1:usuario[1])}</cor1:usuario>
            </cor1:to>
            <cor1:idOperacion>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:idOperacion)}</cor1:idOperacion>
            <cor1:idClienteFenix>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:idClienteFenix)}</cor1:idClienteFenix>
            <cor1:subject>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:subject)}</cor1:subject>
            <cor1:mensaje>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:mensaje)}</cor1:mensaje>
            <cor1:id_plantilla>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:id_plantilla)}</cor1:id_plantilla>
            {
                for $parametro in $inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:parametros
                return 
                <cor1:parametros>
                    <cor1:tag>{fn:data($parametro/cor1:tag)}</cor1:tag>
                    <cor1:valor>{fn:data($parametro/cor1:valor)}</cor1:valor>
                </cor1:parametros>
            }
        </cor:CorreoElectronico>
    </cor:ConstruirCorreoElectronicoRequest>
    
    
};



local:funcEnviarcorreoelectronico_to_construircorreoelectronico($inputVariable.EnviarCorreoElectronicoRequest)
