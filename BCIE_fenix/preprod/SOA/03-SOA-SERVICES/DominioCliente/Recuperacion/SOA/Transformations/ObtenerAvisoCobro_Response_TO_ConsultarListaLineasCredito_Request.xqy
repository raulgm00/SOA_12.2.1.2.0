xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) external;

declare function local:funcObteneravisocobro_response_to_consultarlistalineascredito_request($outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::)) as element() (:: schema-element(lin:ConsultarListaLineasCreditoRequest) ::) {
    <lin:ConsultarListaLineasCreditoRequest>
    {
    for $numerloLinea in fn:distinct-values($outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse/ges:AvisoCobro/ges1:aviso/ges1:avisoOperacion/ges1:informacionPago/ges1:detallePago/lin1:NumeroLineaCredito)
    return
      <lin:NumeroLinea>{fn:data($numerloLinea)}</lin:NumeroLinea>
      }
    </lin:ConsultarListaLineasCreditoRequest>
};

local:funcObteneravisocobro_response_to_consultarlistalineascredito_request($outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse)
