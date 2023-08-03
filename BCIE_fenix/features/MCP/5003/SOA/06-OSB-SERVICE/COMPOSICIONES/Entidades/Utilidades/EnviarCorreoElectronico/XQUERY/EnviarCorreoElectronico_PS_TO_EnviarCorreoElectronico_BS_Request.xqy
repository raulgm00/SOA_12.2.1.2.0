xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare variable $enviarCorreoElectronicoRequest as element() (:: schema-element(ns1:enviarCorreoElectronicoRequest) ::) external;

declare function local:func($enviarCorreoElectronicoRequest as element() (:: schema-element(ns1:enviarCorreoElectronicoRequest) ::)) as element() (:: schema-element(ns1:EnviarCorreoElectronicoBPELRequest) ::) {
    <ns1:EnviarCorreoElectronicoBPELRequest>
        <ns1:correoElectronico>
            <cor:to>
                {
                    for $usuarioTo in $enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:to/cor:usuario
                    return 
                    <cor:usuario>{fn:data($usuarioTo)}</cor:usuario>
                }
            </cor:to>
            <cor:bcc>
                {
                    for $usuarioBcc in $enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:bcc/cor:usuario
                    return 
                    <cor:usuario>{fn:data($usuarioBcc)}</cor:usuario>
                }
            </cor:bcc>
            <cor:cc>
                {
                    for $usuarioCc in $enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:cc/cor:usuario
                    return 
                    <cor:usuario>{fn:data($usuarioCc)}</cor:usuario>
                }
            </cor:cc>
           <cor:idOperacion>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:idOperacion)}</cor:idOperacion>
           <cor:idClienteFenix>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:idClienteFenix)}</cor:idClienteFenix>
           <cor:replyToAddress>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:replyToAddress)}</cor:replyToAddress>
           <cor:subject>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:subject)}</cor:subject>
           <cor:mensaje>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:mensaje)}</cor:mensaje>
           <cor:id_plantilla>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:id_plantilla)}</cor:id_plantilla>
            {
                if ($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:DescripcionTCAPlantilla)
                then <cor:DescripcionTCAPlantilla>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:DescripcionTCAPlantilla)}</cor:DescripcionTCAPlantilla>
                else ()
            }
            {
                if ($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:IdFlujo)
                then <cor:IdFlujo>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:IdFlujo)}</cor:IdFlujo>
                else ()
            }
            {
                if ($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:IdSupervicion)
                then <cor:IdSupervicion>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:IdSupervicion)}</cor:IdSupervicion>
                else ()
            }
           <cor:proceso>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:proceso)}</cor:proceso>
           <cor:evento>{fn:data($enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:evento)}</cor:evento>
           {
           for $parametro in $enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:parametros
           return
            <cor:parametros>
                <cor:tag>{fn:data($parametro/cor:tag)}</cor:tag>
                <cor:valor>{fn:data($parametro/cor:valor)}</cor:valor>
            </cor:parametros>
            }
            {
                for $attachment in $enviarCorreoElectronicoRequest/ns1:CorreoElectronico/cor:attachment
                return 
                <cor:attachment>
                    <cor:name>{fn:data($attachment/cor:name)}</cor:name>
                    <cor:type>{fn:data($attachment/cor:type)}</cor:type>
                    <cor:content>{fn:data($attachment/cor:content)}</cor:content>
                </cor:attachment>
            }
            
        </ns1:correoElectronico>
    </ns1:EnviarCorreoElectronicoBPELRequest>
};

local:func($enviarCorreoElectronicoRequest)
