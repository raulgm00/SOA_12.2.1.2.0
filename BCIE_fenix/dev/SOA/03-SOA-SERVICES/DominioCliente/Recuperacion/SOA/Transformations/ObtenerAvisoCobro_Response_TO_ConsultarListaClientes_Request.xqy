xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare variable $outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) external;

declare function local:funcObteneravisocobro_response_to_consultarlistaclientes_request($outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::)) as element() (:: schema-element(cli:ConsultarListaClientesRequest) ::) {
  <cli:ConsultarListaClientesRequest>
      {
      for $idFacturador in fn:distinct-values( $outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse/ges:AvisoCobro/ges1:aviso/cli1:idFacturador)
      return
        <cli:IdFacturador>{fn:data($idFacturador)}</cli:IdFacturador>
      }
    </cli:ConsultarListaClientesRequest>
};

local:funcObteneravisocobro_response_to_consultarlistaclientes_request($outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse)
