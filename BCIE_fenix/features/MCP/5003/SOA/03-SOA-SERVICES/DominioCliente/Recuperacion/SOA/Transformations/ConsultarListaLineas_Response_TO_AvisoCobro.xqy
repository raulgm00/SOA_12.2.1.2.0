xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $AvisoCobro as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) external;
declare variable $outInvokeConsultarListaLineas.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::) external;

declare function local:funcConsultarlistalineas_response_to_avisocobro($AvisoCobro as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::), 
                                            $outInvokeConsultarListaLineas.response as element() (:: schema-element(lin:ConsultarListaLineasCreditoResponse) ::)) 
                                            as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) {
    <ges:ObtenerAvisoCobroResponse>
         <ges:AvisoCobro>
             {
            for $aviso in  $AvisoCobro/ges:AvisoCobro/ges1:aviso
            return
            <ges1:aviso>
                <cli:idCliente>{fn:data($aviso/cli:idCliente)}</cli:idCliente>
                <cli:idFacturador>{fn:data($aviso/cli:idFacturador)}</cli:idFacturador>
                <cli:razonSocial>{fn:data($aviso/cli:razonSocial)}</cli:razonSocial>
                <cli:abreviatura>{fn:data($aviso/cli:abreviatura)}</cli:abreviatura>
                <cli:pais>
                      <cat:Descripcion>{fn:data($aviso/cli:pais/cat:Descripcion)}</cat:Descripcion>
                      <cat:DescripcionCorta>{fn:data($aviso/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                </cli:pais>
                <cli:responsableCliente>{fn:data($aviso/cli:responsableCliente)}</cli:responsableCliente>
                <ges1:idEjecucionLote>{fn:data($aviso/ges1:idEjecucionLote)}</ges1:idEjecucionLote>
                <ges1:emision>{fn:data($aviso/ges1:emision)}</ges1:emision>
              
                {
                 for $idOperacion in distinct-values($outInvokeConsultarListaLineas.response/lin:LineaCredito/lin:Operacion/ope:idOperacion)
                    for $moneda in  distinct-values($aviso/ges1:avisoOperacion/ges1:Moneda/cat:Descripcion)
                    for $avisoOperacion in $aviso/ges1:avisoOperacion
                    let $lineaCredito := $outInvokeConsultarListaLineas.response/lin:LineaCredito[lin:Operacion/ope:idOperacion = $idOperacion and lin:Operacion/ope:contrato/con:LineaCredito/lin1:Flexcube/lin1:id = $avisoOperacion/ges1:informacionPago/ges1:detallePago/lin1:Flexcube/lin1:id]
                    where $avisoOperacion/ges1:Moneda/cat:Descripcion = $moneda
                    return
                    
                    if (count($avisoOperacion/ges1:informacionPago/ges1:detallePago[lin1:NumeroLineaCredito= $lineaCredito/lin:Operacion/ope:contrato/con:LineaCredito/lin1:NumeroLineaCredito])>0) then 

                    <ges1:avisoOperacion>
                      <ope:idOperacion>{fn:data($lineaCredito[1]/lin:Operacion/ope:idOperacion)}</ope:idOperacion>
                      <ope:nombre>{fn:data($lineaCredito[1]/lin:Operacion/ope:nombre)}</ope:nombre>
                      <ges1:Moneda>
                        <cat:Descripcion>{fn:data($moneda)}</cat:Descripcion>
                      </ges1:Moneda>
                      {
                      for $informacionPago in $avisoOperacion/ges1:informacionPago
                      return
                       if (count($informacionPago/ges1:detallePago[lin1:NumeroLineaCredito= $lineaCredito/lin:Operacion/ope:contrato/con:LineaCredito/lin1:NumeroLineaCredito])>0) then 
                      <ges1:informacionPago>
                        <ges1:fechaPago>{fn:data($informacionPago/ges1:fechaPago)}</ges1:fechaPago>
                        {
                        for $detallePago in $informacionPago/ges1:detallePago
                        return
                        if (count($detallePago[lin1:NumeroLineaCredito= $lineaCredito/lin:Operacion/ope:contrato/con:LineaCredito/lin1:NumeroLineaCredito])>0) then 
                        <ges1:detallePago>
                            <lin1:NumeroLineaCredito>{fn:data($detallePago/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
                            <lin1:Fondo>{fn:data($detallePago/lin1:Fondo)}</lin1:Fondo>
                             {
                            for $monto in $detallePago/lin1:Monto
                            return
                            <lin1:Monto>
                                <com:tipo>
                                    <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                </com:tipo>
                                <com:importe>{fn:data($monto/com:importe)}</com:importe>
                            </lin1:Monto>
                            }
                            
                                 {
                                 for $detallado in $detallePago/ges1:ContratoDesembolso
                                 return
                                  <ges1:ContratoDesembolso>
                                      <des:referencia>{fn:data($detallado/des:referencia)}</des:referencia>
                                      {
                                      for $monto in  $detallado/des:monto
                                      return
                                        <des:monto>
                                              <com:tipo>
                                                  <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                              </com:tipo>
                                              <com:importe>{fn:data($monto/com:importe)}</com:importe>
                                        </des:monto>
                                        }
                                        
                                  </ges1:ContratoDesembolso>
                                  }
                                  
                                  {
                                  for $mora in $detallePago/ges1:Mora
                                  return
                                  <ges1:Mora>
                                      <des:referencia>{fn:data($mora/des:referencia)}</des:referencia>
                                       
                                       { 
                                       for $monto in  $mora/des:monto
                                      return
                                        <des:monto>
                                              <com:tipo>
                                                  <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                              </com:tipo>
                                              <com:importe>{fn:data($monto/com:importe)}</com:importe>
                                        </des:monto>
                                      }
                                  </ges1:Mora>
                                  }
                                  {
                                  if (count($detallePago/ges1:tablasDetalle/ges1:detalleIntereses)>0) then 
                                  <ges1:tablasDetalle>
                                      <ges1:pagosAplicados>
                                          <com:importe>{fn:data($detallePago/ges1:tablasDetalle/ges1:pagosAplicados/com:importe)}</com:importe>
                                      </ges1:pagosAplicados>
                                      {
                                       for $detalleIntereses in $detallePago/ges1:tablasDetalle/ges1:detalleIntereses
                                       return
                                       $detalleIntereses
                                      }
                                  </ges1:tablasDetalle>
                                      else()
                                    }
                                    {
                                    if (count($detallePago/ges1:tablasDetalle/ges1:detalleCC)>0) then 
                                    <ges1:tablasDetalle>
                                            {
                                       for $detalleCC in $detallePago/ges1:tablasDetalle/ges1:detalleCC
                                       return
                                            $detalleCC
                                          }
                                    </ges1:tablasDetalle>
                                         else()
                                        }
                                        {
                                        if (count($detallePago/ges1:tablasDetalle/ges1:detalleOC)>0) then 
                                        <ges1:tablasDetalle>
                                         {
                                          for $detalleOC in $detallePago/ges1:tablasDetalle/ges1:detalleOC
                                           return
                                             $detalleOC
                                          }
                                          
                                  </ges1:tablasDetalle>
                                   else()
                                  }
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
                            {
                                for $comentario3 in $avisoOperacion/ges1:informacionPago/ges1:detallePago/ges1:comentario3
                                return 
                                <ges1:comentario3>{fn:data($avisoOperacion/ges1:informacionPago/ges1:detallePago/ges1:comentario3)}</ges1:comentario3>
                            }
                              </ges1:detallePago>
                        else()
                        }
                      </ges1:informacionPago>
                      else()
                      }
                     </ges1:avisoOperacion>
                      else ()
                }
                
            </ges1:aviso>
            }
        </ges:AvisoCobro>
    </ges:ObtenerAvisoCobroResponse>
};

local:funcConsultarlistalineas_response_to_avisocobro($AvisoCobro, $outInvokeConsultarListaLineas.response)
