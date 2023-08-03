xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd"::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $outConsultarTransferenciasBancariasById.response as element() (:: schema-element(des:ConsultarTransferenciasBancariasByIdResponse) ::) external;
declare variable $outConsultarDetalleTransaccionById.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) external;

declare function local:funcXq_comparardetalletransaccion($outConsultarTransferenciasBancariasById.response as element() (:: schema-element(des:ConsultarTransferenciasBancariasByIdResponse) ::), 
                                                         $outConsultarDetalleTransaccionById.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::)) 
                                                         as element() (:: schema-element(des:ConsultarTransferenciasBancariasByIdResponse) ::) {
    <des:ConsultarTransferenciasBancariasByIdResponse>
        <des:ContratoDesembolso>
            <des1:idDesembolso>{fn:data($outConsultarTransferenciasBancariasById.response/des:ContratoDesembolso/des1:idDesembolso)}</des1:idDesembolso>
    {
        for $transferencia in $outConsultarTransferenciasBancariasById.response/des:ContratoDesembolso/des1:Transferencia
        let $agrupador := if($outConsultarDetalleTransaccionById.response/des:TransaccionDesembolso[des1:plataforma='MFC' and des1:operacion = 'registrarReserva']/des1:detalleTransaccion/des1:Parameters[com:name='transferencia']/com:value/text() = $transferencia/des1:idTransferencia/text())
                          then $outConsultarDetalleTransaccionById.response/des:TransaccionDesembolso[des1:plataforma='MFC' and des1:operacion = 'registrarReserva']/des1:detalleTransaccion/des1:Parameters[com:name='transferencia' and com:value/text() = $transferencia/des1:idTransferencia/text()]/com:agrupador/text()
                          else ''
        let $importe := if($agrupador != '')then if($outConsultarDetalleTransaccionById.response/des:TransaccionDesembolso[des1:plataforma='MFC' and des1:operacion = 'registrarReserva']/des1:detalleTransaccion/des1:Parameters[com:agrupador/text()= $agrupador][com:name='importe']/com:value/text() != $transferencia/des1:Monto/com:importe/text()) 
                                                  then $transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:importe/text() else '' 
                        else''
        let $moneda := if($agrupador != '') then if($outConsultarDetalleTransaccionById.response/des:TransaccionDesembolso[des1:plataforma='MFC' and des1:operacion = 'registrarReserva']/des1:detalleTransaccion/des1:Parameters[com:agrupador/text()= $agrupador][com:name='moneda']/com:value/text() != $transferencia/des1:Monto/com:moneda/cat:codigoExterno/text())
                                                  then $transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:moneda/cat:codigoExterno/text() else  ''
                        else ''
        let $numeroCuenta := if($agrupador != '') then if($outConsultarDetalleTransaccionById.response/des:TransaccionDesembolso[des1:plataforma='MFC' and des1:operacion = 'registrarReserva']/des1:detalleTransaccion/des1:Parameters[com:agrupador/text()= $agrupador][com:name='numeroCuenta']/com:value/text() != $transferencia/des1:numeroCuenta/text())
                                                      then $transferencia/des1:numeroCuenta/text() else ''
                             else ''
        let $fechaDisponibilidad:= if($agrupador != '') then if($outConsultarDetalleTransaccionById.response/des:TransaccionDesembolso[des1:plataforma='MFC' and des1:operacion = 'registrarReserva']/des1:detalleTransaccion/des1:Parameters[com:agrupador/text()= $agrupador][com:name='fechaDisponibilidadFondos']/com:value/text() != $outConsultarTransferenciasBancariasById.response/des:ContratoDesembolso/des1:fechaDisponibilidadFondos)
                                                      then $transferencia/des1:numeroCuenta/text() else ''
                             else ''
          return
          if ($agrupador = '' or $importe != '' or $moneda != '' or $numeroCuenta != '' or $fechaDisponibilidad != '') then
            <des1:Transferencia>
                <des1:idTransferencia>{fn:data($transferencia/des1:idTransferencia)}</des1:idTransferencia>
                <des1:Monto>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta>TRANSFERENCIA_BANCARIA</cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:tipo>
                    {
                        if ($importe)
                        then <com:importe>{fn:data($importe)}</com:importe>
                        else (<com:importe>{fn:data($transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:importe)}</com:importe>)
                    }
                    <com:moneda>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        {
                            if ($moneda)
                            then <cat:codigoExterno>{fn:data($moneda)}</cat:codigoExterno>
                            else (<cat:codigoExterno>{fn:data($transferencia/des1:Monto[com:tipo/cat:DescripcionCorta='TRANSFERENCIA_BANCARIA']/com:moneda/cat:codigoExterno)}</cat:codigoExterno>)
                        }
                    </com:moneda>
                </des1:Monto>
                {
                    if ($numeroCuenta)
                    then <des1:numeroCuenta>{fn:data($numeroCuenta)}</des1:numeroCuenta>
                    else (<des1:numeroCuenta>{fn:data($transferencia/des1:numeroCuenta)}</des1:numeroCuenta>)
                }
            </des1:Transferencia>
            else ()
            }
        </des:ContratoDesembolso>
    </des:ConsultarTransferenciasBancariasByIdResponse>
};

local:funcXq_comparardetalletransaccion($outConsultarTransferenciasBancariasById.response, $outConsultarDetalleTransaccionById.response)
