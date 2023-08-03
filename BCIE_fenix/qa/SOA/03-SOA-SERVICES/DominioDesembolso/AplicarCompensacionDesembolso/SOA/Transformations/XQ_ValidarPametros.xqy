xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $inputVariable.request as element() (:: schema-element(des:AplicarCompensacionDesembolsoRequest) ::) external;
declare variable $outConsultarDetalleTransaccion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) external;

declare function local:funcXq_validarpametros($inputVariable.request as element() (:: schema-element(des:AplicarCompensacionDesembolsoRequest) ::), 
                                              $outConsultarDetalleTransaccion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::)) 
                                              as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) {
    <des:ConsultarDetalleTransaccionResponse>
        {
            for $TransaccionDesembolso in $outConsultarDetalleTransaccion.response/des:TransaccionDesembolso
            for $inDetalles in $inputVariable.request/des:DetalleTransaccion
            for $inParametros in $inDetalles/des1:Parameters
            for $detalleTransaccion in $TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters
            return 
                    if ($inParametros/com:name = $detalleTransaccion/com:name and $inParametros/com:value = $detalleTransaccion/com:value) 
                    then 
            <des:TransaccionDesembolso>
                <des1:id>{fn:data($TransaccionDesembolso/des1:id)}</des1:id>
                <des1:idDesembolso>{fn:data($TransaccionDesembolso/des1:idDesembolso)}</des1:idDesembolso>
                <des1:plataforma>{fn:data($TransaccionDesembolso/des1:plataforma)}</des1:plataforma>
                <des1:operacion>{fn:data($TransaccionDesembolso/des1:operacion)}</des1:operacion>

                    <des1:fechaRegistro>{fn:data($TransaccionDesembolso/des1:fechaRegistro)}</des1:fechaRegistro>
                    
                <des1:estatus>{fn:data($TransaccionDesembolso/des1:estatus)}</des1:estatus>
                <des1:detalleTransaccion>
                    {
                        for $Parameters in $TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters
                        return 
                        <des1:Parameters>
                            <com:name>{fn:data($Parameters/com:name)}</com:name>
                            <com:value>{fn:data($Parameters/com:value)}</com:value>
                            <com:id>{fn:data($Parameters/com:id)}</com:id>
                            <com:agrupador>{fn:data($Parameters/com:agrupador)}</com:agrupador>
                        </des1:Parameters>
                    }
                </des1:detalleTransaccion>
            </des:TransaccionDesembolso>
            else()
        }
    </des:ConsultarDetalleTransaccionResponse>
};

local:funcXq_validarpametros($inputVariable.request, $outConsultarDetalleTransaccion.response)
