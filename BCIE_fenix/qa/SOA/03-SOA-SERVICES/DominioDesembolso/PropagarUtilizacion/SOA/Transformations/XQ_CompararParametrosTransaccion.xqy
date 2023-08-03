xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd", 
                     "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace functx = "http://www.functx.com";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $outConsultarDetalleTransaccion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) external;
declare variable $exist as xs:boolean external;

declare function local:funcXq_validarparametrostransaccion($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::), 
                                                           $outConsultarDetalleTransaccion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::)) 
                                                           as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) {
    <des:ConsultarDesembolsosByIdResponse>
    {
    if (count($outConsultarDetalleTransaccion.response/des:TransaccionDesembolso)>0)
    then    

    <des:ContratoDesembolso>
            {
              for $utilizacion in $outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:Utilizacion 
                  let $numeroAsignacion := if ($outConsultarDetalleTransaccion.response/des:TransaccionDesembolso[des1:plataforma='ARE' and des1:operacion = 'registrarUtilizacion']/des1:detalleTransaccion/des1:Parameters[com:name='numeroAsignacion']/com:value/text() = $utilizacion/lin:NumeroAsignacion)
                                            then $outConsultarDetalleTransaccion.response/des:TransaccionDesembolso[des1:plataforma='ARE' and des1:operacion = 'registrarUtilizacion']/des1:detalleTransaccion/des1:Parameters[com:name='numeroAsignacion' and com:value/text() = $utilizacion/lin:NumeroAsignacion]/com:value/text()
                                            else ''
                  let $importe := if ($outConsultarDetalleTransaccion.response/des:TransaccionDesembolso[des1:plataforma='ARE' and des1:operacion = 'registrarUtilizacion']/des1:detalleTransaccion/des1:Parameters[com:agrupador/text() = $numeroAsignacion][com:name='importe']/com:value/text()!= $utilizacion/lin:MontoAsignado/text())
                                  then $utilizacion/lin:MontoAsignado/text() else ''
                  return
            if ($numeroAsignacion = '' or $importe != '')then
            <des1:Utilizacion>
                <lin:MontoAsignado>{$importe}</lin:MontoAsignado>   
                <lin:NumeroAsignacion>{fn:data($utilizacion/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
            </des1:Utilizacion>
            else()
            }
        </des:ContratoDesembolso>
    else()
    }
    </des:ConsultarDesembolsosByIdResponse>
};

local:funcXq_validarparametrostransaccion($outConsultarDesembolsoById.response, $outConsultarDetalleTransaccion.response)

