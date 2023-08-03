xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) external;
declare variable $outInvokeConsultarListaClientes.ConsultarListaClientesResponse as element() (:: schema-element(cli:ConsultarListaClientesResponse) ::) external;

declare function local:funcConsultarlistaclientes_response_to_avisocobro($outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::), 
                                                                          $outInvokeConsultarListaClientes.ConsultarListaClientesResponse as element() (:: schema-element(cli:ConsultarListaClientesResponse) ::)
                                                                          ) 
                                                                          as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) {
      <ges:ObtenerAvisoCobroResponse>
      
        <ges:AvisoCobro>
        {
        for $aviso in  $outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse/ges:AvisoCobro/ges1:aviso
        return
          for $cliente in $outInvokeConsultarListaClientes.ConsultarListaClientesResponse/cli:Cliente
          return
          
                if (fn:data($aviso/cli1:idFacturador) = fn:data($cliente/cli1:idFacturador))
                then
                <ges1:aviso>
                      <cli1:idCliente>{fn:data($cliente/cli1:idCliente)}</cli1:idCliente>
                      <cli1:idFacturador>{fn:data($aviso/cli1:idFacturador)}</cli1:idFacturador>
                      <cli1:razonSocial>{fn:data($aviso/cli1:razonSocial)}</cli1:razonSocial>
                      <cli1:abreviatura>{fn:data($cliente/cli1:abreviatura)}</cli1:abreviatura>
                      <cli1:pais>
                            <cat:Descripcion>{fn:data($aviso/cli1:pais/cat:Descripcion)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($aviso/cli1:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                      </cli1:pais>
                      <cli1:responsableCliente>{fn:data($cliente/cli1:responsableCliente)}</cli1:responsableCliente>
                      <ges1:idEjecucionLote>{fn:data($aviso/ges1:idEjecucionLote)}</ges1:idEjecucionLote>
                      <ges1:emision>{fn:data($aviso/ges1:emision)}</ges1:emision>
                      {
                      for $avisoOperacion in $aviso/ges1:avisoOperacion
                      return
                      $avisoOperacion
                      }
                  </ges1:aviso>
                  else()
                }
        </ges:AvisoCobro>
    </ges:ObtenerAvisoCobroResponse>
};

local:funcConsultarlistaclientes_response_to_avisocobro($outInvokeObtenerAvisoCobro.ObtenerAvisoCobroResponse, $outInvokeConsultarListaClientes.ConsultarListaClientesResponse)
