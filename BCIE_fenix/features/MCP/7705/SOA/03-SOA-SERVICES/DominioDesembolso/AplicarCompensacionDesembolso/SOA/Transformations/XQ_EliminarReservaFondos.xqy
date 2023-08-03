xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $outConsultarTransferenciasById.response as element() (:: schema-element(des:ConsultarTransferenciasBancariasByIdResponse) ::) external;
declare variable $detalleCompensacion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) external;

declare function local:funcXq_eliminarreservafondos($outConsultarTransferenciasById.response as element() (:: schema-element(des:ConsultarTransferenciasBancariasByIdResponse) ::), 
                                                    $detalleCompensacion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::)) 
                                                    as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) {
<des:ConsultarDetalleTransaccionResponse>
    {
    for $transferencia in $outConsultarTransferenciasById.response/des:ContratoDesembolso/des1:Transferencia
    let $agrupador := if ($detalleCompensacion.response/des:TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters[com:name='transferencia']/com:value/text() = $transferencia/des1:idTransferencia/text())
                          then $detalleCompensacion.response/des:TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters[com:name='transferencia' and com:value/text() = $transferencia/des1:idTransferencia/text()]/com:agrupador/text()
                          else ''
    let $importe := if($agrupador != '')then if($detalleCompensacion.response/des:TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters[com:agrupador/text()= $agrupador][com:name='importe']/com:value/text() != $transferencia/des1:Monto/com:importe/text()) 
                                                  then $transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:importe/text() else '' 
                        else''
    let $moneda := if($agrupador != '') then if($detalleCompensacion.response/des:TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters[com:agrupador/text()= $agrupador][com:name='moneda']/com:value/text() != $transferencia/des1:Monto/com:moneda/cat:codigoExterno/text())
                                                  then $transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:moneda/cat:codigoExterno/text() else  ''
                        else ''
    let $numeroCuenta := if($agrupador != '') then if($detalleCompensacion.response/des:TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters[com:agrupador/text()= $agrupador][com:name='numeroCuenta']/com:value/text() != $transferencia/des1:numeroCuenta/text())
                                                      then $transferencia/des1:numeroCuenta/text() else ''
                             else ''
        
          return
        if ($importe != '' or $moneda != '' or $numeroCuenta != '')then  
        <des:TransaccionDesembolso>
            <des1:id>{fn:data($detalleCompensacion.response/des:TransaccionDesembolso/des1:id)}</des1:id>
            <des1:estatus>{false()}</des1:estatus>
            <des1:detalleTransaccion>
                <des1:Parameters>
                    <com:name>numeroReserva</com:name>
                    <com:value>{fn:data($detalleCompensacion.response/des:TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters[com:name='numeroReserva']/com:value)}</com:value>
                    <com:id></com:id>
                    <com:agrupador></com:agrupador>
                </des1:Parameters>
            </des1:detalleTransaccion>
        </des:TransaccionDesembolso>
     else()   
     }
    </des:ConsultarDetalleTransaccionResponse>
};


local:funcXq_eliminarreservafondos($outConsultarTransferenciasById.response, $detalleCompensacion.response)
