xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $inputVariable.request as element() (:: schema-element(des:AplicarCompensacionDesembolsoRequest) ::) external;
declare variable $outConsultarDetalleTransaccion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) external;

declare function local:funcXq_validardetallecompensacion($inputVariable.request as element() (:: schema-element(des:AplicarCompensacionDesembolsoRequest) ::), 
                                                         $outConsultarDetalleTransaccion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::)) 
                                                         as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) {
    <des:ConsultarDetalleTransaccionResponse>
      {
      for $transaccionDesembolso in$outConsultarDetalleTransaccion.response/des:TransaccionDesembolso
        for $plataforma in  $inputVariable.request/des:plataforma
      where $transaccionDesembolso/des1:plataforma = $plataforma 
      return
        <des:TransaccionDesembolso>
            <des1:id>{fn:data($transaccionDesembolso/des1:id)}</des1:id>
            <des1:idDesembolso>{fn:data($transaccionDesembolso/des1:idDesembolso)}</des1:idDesembolso>
            <des1:plataforma>{fn:data($transaccionDesembolso/des1:plataforma)}</des1:plataforma>
            <des1:operacion>{fn:data($transaccionDesembolso/des1:operacion)}</des1:operacion>
            <des1:estatus>{fn:data($transaccionDesembolso/des1:estatus)}</des1:estatus>
            <des1:detalleTransaccion>
                {
                    for $Parameters in $transaccionDesembolso/des1:detalleTransaccion/des1:Parameters
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
        }
        <des:Resultado>
            <res:result>{fn:data($outConsultarDetalleTransaccion.response/des:Resultado/res:result)}</res:result>
            <res:message>{fn:data($outConsultarDetalleTransaccion.response/des:Resultado/res:message)}</res:message>
            {
                if ($outConsultarDetalleTransaccion.response/des:Resultado/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($outConsultarDetalleTransaccion.response/des:Resultado/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($outConsultarDetalleTransaccion.response/des:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($outConsultarDetalleTransaccion.response/des:Resultado/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </des:Resultado>
    </des:ConsultarDetalleTransaccionResponse>
};

local:funcXq_validardetallecompensacion($inputVariable.request, $outConsultarDetalleTransaccion.response)
