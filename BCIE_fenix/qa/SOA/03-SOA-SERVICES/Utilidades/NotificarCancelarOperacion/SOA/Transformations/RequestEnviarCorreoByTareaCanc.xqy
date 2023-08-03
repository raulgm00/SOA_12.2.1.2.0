xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConsultarTareasActivasMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarTareasActivas/V1/Schema/ConsultarTareasActivasMO.xsd" ::)
declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace not="http://www.bcie.org/NotificarCancelarOperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/NotificarCancelarOperacion/V1/Schema/NotificarCancelarOperacionMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare namespace not1 = "http://www.bcie.org/NotificarCancelarOperacionBO";

declare variable $inputVariable.request as element() (:: schema-element(not:requestNotificarCancelarOperacion) ::) external;
declare variable $outConsultarTareasActivas.response as element() (:: schema-element(con:responseConsultarTareasActivas) ::) external;

declare function local:funcRequestenviarcorreobytareacanc1($inputVariable.request as element() (:: schema-element(not:requestNotificarCancelarOperacion) ::), 
                                                           $outConsultarTareasActivas.response as element() (:: schema-element(con:responseConsultarTareasActivas) ::)) 
                                                           as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) {
    <cor:EnviarCorreoElectronicoBPELRequest>
        <cor:correoElectronico>
            <cor1:to>
             {
             for $listaTareas in $outConsultarTareasActivas.response/con:ListaTareasActivas
             return
             if (fn:data($listaTareas/con:usuario != '')) then
                      <cor1:usuario>{ fn:data($listaTareas/con:usuario)}</cor1:usuario> 
             else ()
             }
            </cor1:to>
            <cor1:bcc>
                <cor1:usuario>{fn:data($outConsultarTareasActivas.response/con:ListaTareasActivas/con:usuario)}</cor1:usuario>
            </cor1:bcc>
            <cor1:cc>
                <cor1:usuario></cor1:usuario>
            </cor1:cc>
            <cor1:idOperacion>{fn:data($inputVariable.request/not:Operacion/not1:idOperacion)}</cor1:idOperacion>
            <cor1:idClienteFenix></cor1:idClienteFenix>
            <cor1:replyToAddress></cor1:replyToAddress>
            <cor1:subject></cor1:subject>
            <cor1:mensaje></cor1:mensaje>
            <cor1:id_plantilla>37</cor1:id_plantilla>
            <cor1:proceso></cor1:proceso>
            <cor1:evento></cor1:evento>
            <cor1:parametros>
                <cor1:tag>OPERACION</cor1:tag>
                <cor1:valor>{fn:data($inputVariable.request/not:Operacion/not1:idOperacion)}</cor1:valor>
            </cor1:parametros>
        </cor:correoElectronico>
    </cor:EnviarCorreoElectronicoBPELRequest>
};

local:funcRequestenviarcorreobytareacanc1($inputVariable.request, $outConsultarTareasActivas.response)
