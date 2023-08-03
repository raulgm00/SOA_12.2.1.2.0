xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";



declare variable $AvisoCobro as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) external;

declare function local:funcAvisocobro_to_segregaroperacion_avisocobro($AvisoCobro as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::)) as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) {
    <ges:ObtenerAvisoCobroResponse>
        <ges:AvisoCobro>
          {for $idOperacion at $pos in fn:distinct-values($AvisoCobro/ges:AvisoCobro/ges1:aviso/ges1:avisoOperacion/ope:idOperacion)
          let $operacion := $AvisoCobro/ges:AvisoCobro/ges1:aviso/ges1:avisoOperacion[ope:idOperacion = $idOperacion][1]
          return
            <ges1:aviso>
                <cli:idCliente>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:idCliente)}</cli:idCliente>
                {
                    if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:idFacturador)
                    then <cli:idFacturador>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:idFacturador)}</cli:idFacturador>
                    else ()
                }
                {
                    if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:razonSocial)
                    then <cli:razonSocial>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:razonSocial)}</cli:razonSocial>
                    else ()
                }
                {
                    if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:abreviatura)
                    then <cli:abreviatura>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:abreviatura)}</cli:abreviatura>
                    else ()
                }
                <cli:pais>
                    <cat:Id></cat:Id>
                    {
                        if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:pais/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:pais/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:pais/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                </cli:pais>
                {
                    if ($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:responsableCliente)
                    then <cli:responsableCliente>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/cli:responsableCliente)}</cli:responsableCliente>
                    else ()
                }
                <ges1:idEjecucionLote>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/ges1:idEjecucionLote)}</ges1:idEjecucionLote>
                <ges1:emision>{fn:data($AvisoCobro/ges:AvisoCobro/ges1:aviso[1]/ges1:emision)}</ges1:emision>
                <ges1:avisoOperacion>
                    <ope:idOperacion>{fn:data($operacion[ope:idOperacion = $idOperacion]/ope:idOperacion)}</ope:idOperacion>
                    <ope:nombre>{fn:data($operacion[ope:idOperacion = $idOperacion]/ope:nombre)}</ope:nombre>
                    <ges1:Moneda>
                        <cat:Descripcion>{fn:data($operacion/ges1:Moneda/cat:Descripcion)}</cat:Descripcion>
                    </ges1:Moneda>
                    {for $infoPago in $operacion/ges1:informacionPago
                    return
                    <ges1:informacionPago>
                        <ges1:fechaPago>{fn:data($infoPago/ges1:fechaPago)}</ges1:fechaPago>
                        {for $detallePago in $infoPago/ges1:detallePago
                        return
                        <ges1:detallePago>
                            <lin:NumeroLineaCredito>{fn:data($detallePago/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                            <lin:Fondo>{fn:data($detallePago/lin:Fondo)}</lin:Fondo>
                               
                                {
                                  for $monto in $detallePago/ges1:ContratoDesembolso
                                  return
                                  <lin:Monto>
                                      <com:tipo>
                                          <cat:Descripcion>{fn:data($monto/des:monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                      </com:tipo>
                                      <com:importe>{fn:data($monto/des:monto/com:importe)}</com:importe>
                                  </lin:Monto>
                                  }
                            <ges1:tablasDetalle>
                                      <ges1:pagosAplicados>
                                          <com:importe>{fn:data($detallePago/ges1:tablasDetalle/ges1:pagosAplicados/com:importe)}</com:importe>
                                      </ges1:pagosAplicados>
                                      {
                                       for $detalleIntereses in $detallePago/ges1:tablasDetalle/ges1:detalleIntereses
                                       return
                                             $detalleIntereses
                                          }
                                          
                                            {
                                       for $detalleCC in $detallePago/ges1:tablasDetalle/ges1:detalleCC
                                       return
                                             $detalleCC
                                          }
                                          {
                                          for $detalleOC in $detallePago/ges1:tablasDetalle/ges1:detalleOC
                                           return
                                             $detalleOC
                                          } 
                                  </ges1:tablasDetalle>
                            {
                                      for $comentario1 in $detallePago/ges1:comentario1
                                      return 
                                      <ges1:comentario1>{fn:data($comentario1)}</ges1:comentario1>
                                  }
                            {
                                      for $Banco in $detallePago/ges1:Banco
                                      return 
                                      <ges1:Banco>
                                          <ges1:banco>{fn:data($Banco/ges1:banco)}</ges1:banco>
                                          <ges1:SWIFTBanco>{fn:data($Banco/ges1:SWIFTBanco)}</ges1:SWIFTBanco>
                                          <ges1:cuentaNo>{fn:data($Banco/ges1:cuentaNo)}</ges1:cuentaNo>
                                          <ges1:beneficiario>{fn:data($Banco/ges1:beneficiario)}</ges1:beneficiario>
                                          <ges1:Referencia>{fn:data($Banco/ges1:Referencia)}</ges1:Referencia>
                                      </ges1:Banco>
                            }
                            {
                                      for $comentario2 in $detallePago/ges1:comentario2
                                      return 
                                      <ges1:comentario2>{fn:data($comentario2)}</ges1:comentario2>
                                  }
                        </ges1:detallePago>
                        }
                    </ges1:informacionPago>
                    }
                </ges1:avisoOperacion>
            </ges1:aviso>
          }   
        </ges:AvisoCobro>
    </ges:ObtenerAvisoCobroResponse>
};

local:funcAvisocobro_to_segregaroperacion_avisocobro($AvisoCobro)
