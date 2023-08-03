xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $outInvokeGenerarLoteReciboPago.GenerarLoteReciboPagoResponse as element() (:: schema-element(ges:GenerarLoteReciboPagoResponse) ::) external;

declare function local:funcGenerarloterecibopago_response_to_consultarlistalineas_request($outInvokeGenerarLoteReciboPago.GenerarLoteReciboPagoResponse as element() (:: schema-element(ges:GenerarLoteReciboPagoResponse) ::)) as element() (:: schema-element(lin:ConsultarListaLineasCreditoRequest) ::) {
    <lin:ConsultarListaLineasCreditoRequest>
    {
    for $num_linea in distinct-values($outInvokeGenerarLoteReciboPago.GenerarLoteReciboPagoResponse/ges:ReciboPago/ges1:recibo/ges1:parameter[com:name= 'NUMERO_LINEA']/com:value)
    return
    <lin:NumeroLinea>{fn:data($num_linea)}</lin:NumeroLinea>
    }
    </lin:ConsultarListaLineasCreditoRequest>
};

local:funcGenerarloterecibopago_response_to_consultarlistalineas_request($outInvokeGenerarLoteReciboPago.GenerarLoteReciboPagoResponse)
