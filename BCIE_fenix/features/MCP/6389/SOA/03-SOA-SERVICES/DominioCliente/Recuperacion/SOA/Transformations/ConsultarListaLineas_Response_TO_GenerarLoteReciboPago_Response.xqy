xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare variable $outInvokeGenerarLoteReciboPago.GenerarLoteReciboPagoResponse as element() (:: schema-element(ges:GenerarLoteReciboPagoResponse) ::) external;
declare variable $outInvokeConsultarListaLineas.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::) external;

declare function local:funcConsultarlistalineas_response_to_generarloterecibopago_response($outInvokeGenerarLoteReciboPago.GenerarLoteReciboPagoResponse as element() (:: schema-element(ges:GenerarLoteReciboPagoResponse) ::), 
                                                                                           $outInvokeConsultarListaLineas.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::)) 
                                                                                           as element() (:: schema-element(ges:GenerarLoteReciboPagoResponse) ::) {
    <ges:GenerarLoteReciboPagoResponse>
        <ges:ReciboPago>
        {
        for $recibo in $outInvokeGenerarLoteReciboPago.GenerarLoteReciboPagoResponse/ges:ReciboPago/ges1:recibo
        return
            <ges1:recibo>
                <ges1:Recibo>{fn:data($recibo/ges1:Recibo)}</ges1:Recibo>
                <ges1:FechaEjecucion>{fn:data($recibo/ges1:FechaEjecucion)}</ges1:FechaEjecucion>
                <ges1:FechaEfectiva>{fn:data($recibo/ges1:FechaEfectiva)}</ges1:FechaEfectiva>
                {
                let $linea := $outInvokeConsultarListaLineas.response/lin:LineaCredito[lin:Operacion/ope:contrato/con:LineaCredito/lin1:NumeroLineaCredito = $recibo/ges1:parameter[com:name= 'NUMERO_LINEA']/com:value and 
                                                                                      lin:Operacion/ope:producto/pro:idProducto !=26 and
                                                                                      lin:Operacion/ope:contrato/con:LineaCredito/lin1:Flexcube/lin1:id != '' ][1]
                let $cliente := $linea/lin:Operacion[1]/ope:cliente
            
                return
                <ges1:Cliente>
                    <cli:idCliente>{fn:data($cliente/cli:idCliente)}</cli:idCliente>
                    <cli:idFacturador>{fn:data($cliente/cli:idFacturador)}</cli:idFacturador>
                    <cli:razonSocial>{fn:data($cliente/cli:razonSocial)}</cli:razonSocial>
                    <cli:responsableCliente>{fn:data($cliente/cli:responsableCliente)}</cli:responsableCliente>
                </ges1:Cliente>
                }
              { 
              for $desembolso in $recibo/ges1:Desembolsos
              return
                <ges1:Desembolsos>
                   
                   <des:idFacturador>{fn:data($desembolso/des:idFacturador)}</des:idFacturador>
                  
                    <des:referencia>{fn:data($desembolso/des:referencia)}</des:referencia>
                   {
                   for $monto in $desembolso/des:monto
                   return
                    <des:monto>
                        <com:importe>{fn:data($monto/com:importe)}</com:importe>
                        <com:moneda>
                            <cat:Descripcion>{fn:data($monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            <cat:codigoExterno>{fn:data($monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                        </com:moneda>
                    </des:monto>
                    }
                </ges1:Desembolsos>
                }
            </ges1:recibo>
            }
        </ges:ReciboPago>
    </ges:GenerarLoteReciboPagoResponse>
};

local:funcConsultarlistalineas_response_to_generarloterecibopago_response($outInvokeGenerarLoteReciboPago.GenerarLoteReciboPagoResponse, $outInvokeConsultarListaLineas.response)
