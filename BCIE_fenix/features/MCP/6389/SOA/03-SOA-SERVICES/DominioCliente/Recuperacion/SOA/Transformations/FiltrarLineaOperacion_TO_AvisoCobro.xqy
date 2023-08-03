xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ges1 = "http://www.bcie.org/GestionCobroBO";

declare variable $inputVariable.GenerarAvisoCobroDemandaRequest as element() (:: schema-element(ges:GenerarAvisoCobroDemandaRequest) ::) external;
declare variable $AvisoCobro as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) external;

declare function local:funcFiltrarlineaoperacion_to_avisocobro($inputVariable.GenerarAvisoCobroDemandaRequest as element() (:: schema-element(ges:GenerarAvisoCobroDemandaRequest) ::), 
                                                               $AvisoCobro as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::)) 
                                                               as element() (:: schema-element(ges:ObtenerAvisoCobroResponse) ::) {
    <ges:ObtenerAvisoCobroResponse>
        <ges:AvisoCobro>
            
          {
          if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:operacion/ope:idOperacion/text() != '') then 
          for $aviso in $AvisoCobro/ges:AvisoCobro/ges1:aviso[ges1:avisoOperacion/ope:idOperacion = $inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:operacion/ope:idOperacion]
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
                {if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:operacion)then
                  for $avisoOperacion in $aviso/ges1:avisoOperacion[ope:idOperacion = $inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:operacion/ope:idOperacion]
                  return
                  if(count($avisoOperacion/ges1:informacionPago/ges1:detallePago[lin:NumeroLineaCredito = $inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:lineaCredito/lin:NumeroLineaCredito])>0)then
                  <ges1:avisoOperacion>
                    <ope:idOperacion>{fn:data($avisoOperacion/ope:idOperacion)}</ope:idOperacion>
                    <ope:responsable></ope:responsable>
                    <ope:oficina></ope:oficina>
                    <ope:nombre>{fn:data($avisoOperacion/ope:nombre)}</ope:nombre>
                    <ges1:Moneda>
                        <cat:Descripcion>{fn:data($avisoOperacion/ges1:Moneda/cat:Descripcion)}</cat:Descripcion>
                    </ges1:Moneda>
                    {for $informacionPago in $avisoOperacion/ges1:informacionPago
                    return
                    <ges1:informacionPago>
                        <ges1:fechaPago>{fn:data($informacionPago/ges1:fechaPago)}</ges1:fechaPago>
                        {
                        if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:lineaCredito) then
                          for $detallePago in $informacionPago/ges1:detallePago[lin:NumeroLineaCredito = $inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:lineaCredito/lin:NumeroLineaCredito]
                          return
                          <ges1:detallePago>
                            {$detallePago/*}
                          </ges1:detallePago>
                        else
                          for $detallePago in $informacionPago/ges1:detallePago
                          return
                          <ges1:detallePago>
                            {$detallePago/*}
                          </ges1:detallePago>
                        }
                    </ges1:informacionPago>
                    }
                  </ges1:avisoOperacion>
                  else()
                else 
                  for $avisoOperacion in $aviso/ges1:avisoOperacion
                  return
                  if(count($avisoOperacion/ges1:informacionPago/ges1:detallePago[lin:NumeroLineaCredito = $inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:lineaCredito/lin:NumeroLineaCredito])>0)then
                  <ges1:avisoOperacion>
                    <ope:idOperacion>{fn:data($avisoOperacion/ope:idOperacion)}</ope:idOperacion>
                    <ope:responsable></ope:responsable>
                    <ope:oficina></ope:oficina>
                    <ope:nombre>{fn:data($avisoOperacion/ope:nombre)}</ope:nombre>
                    <ges1:Moneda>
                        <cat:Descripcion>{fn:data($avisoOperacion/ges1:Moneda/cat:Descripcion)}</cat:Descripcion>
                    </ges1:Moneda>
                    {for $informacionPago in $avisoOperacion/ges1:informacionPago
                    return
                    <ges1:informacionPago>
                        <ges1:fechaPago>{fn:data($informacionPago/ges1:fechaPago)}</ges1:fechaPago>
                        {
                        if($inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:lineaCredito) then
                          for $detallePago in $informacionPago/ges1:detallePago[lin:NumeroLineaCredito = $inputVariable.GenerarAvisoCobroDemandaRequest/ges:parametrosAvisoCobro/ges1:lineaCredito/lin:NumeroLineaCredito]
                          return
                          <ges1:detallePago>
                            {$detallePago/*}
                          </ges1:detallePago>
                        else
                          for $detallePago in $informacionPago/ges1:detallePago
                          return
                          <ges1:detallePago>
                            {$detallePago/*}
                          </ges1:detallePago>
                        }
                    </ges1:informacionPago>
                    }
                  </ges1:avisoOperacion>
                  else()
                }
            </ges1:aviso>
            else
            for $aviso in $AvisoCobro/ges:AvisoCobro/ges1:aviso
            return 
             <ges1:aviso>{$aviso/*}</ges1:aviso>
          }  
        </ges:AvisoCobro>
    </ges:ObtenerAvisoCobroResponse>
};

local:funcFiltrarlineaoperacion_to_avisocobro($inputVariable.GenerarAvisoCobroDemandaRequest, $AvisoCobro)
