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

declare variable $inputVariable.GenerarArchivosFenixRequest as element() (:: schema-element(ges:GenerarArchivosFenixRequest) ::) external;

declare function local:funcGenerararchivosfenix_request_to_generaravisocobromora_request($inputVariable.GenerarArchivosFenixRequest as element() (:: schema-element(ges:GenerarArchivosFenixRequest) ::)) as element() (:: schema-element(ges:GeneraReporteAvisoCobroRequest) ::) {
    <ges:GeneraReporteAvisoCobroRequest>
        <ges:AvisoCobro>
           <ges1:aviso>
                <cli:idCliente>{fn:data($inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/cli:idCliente)}</cli:idCliente>
               <cli:idFacturador>{fn:data($inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/cli:idFacturador)}</cli:idFacturador>
                <cli:razonSocial>{fn:data($inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/cli:razonSocial)}</cli:razonSocial>
                <cli:abreviatura>{fn:data($inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/cli:abreviatura)}</cli:abreviatura>
                <cli:pais>
                    <cat:Descripcion>{fn:data($inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/cli:pais/cat:Descripcion)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/cli:pais/cat:DescripcionCorta)}</cat:DescripcionCorta>
                </cli:pais>
               <cli:responsableCliente>{fn:data($inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/cli:responsableCliente)}</cli:responsableCliente>
                <ges1:idEjecucionLote>{fn:data($inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/ges1:idEjecucionLote)}</ges1:idEjecucionLote>
                <ges1:emision>{fn:data($inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/ges1:emision)}</ges1:emision>
                {
                for $avisoOperacion in $inputVariable.GenerarArchivosFenixRequest/ges:AvisoCobro/ges1:aviso/ges1:avisoOperacion
                return
                if (count($avisoOperacion/ges1:informacionPago/ges1:detallePago/ges1:Mora)> 0)then
                <ges1:avisoOperacion>
                    <ope:idOperacion>{fn:data($avisoOperacion/ope:idOperacion)}</ope:idOperacion>
                    <ope:nombre>{fn:data($avisoOperacion/ope:nombre)}</ope:nombre>
                    <ges1:Moneda>
                        <cat:Descripcion>{fn:data($avisoOperacion/ges1:Moneda/cat:Descripcion)}</cat:Descripcion>
                    </ges1:Moneda>
                    {
                    for $informacionPago in $avisoOperacion/ges1:informacionPago
                    return
                    <ges1:informacionPago>
                        <ges1:fechaPago>{fn:data($informacionPago/ges1:fechaPago)}</ges1:fechaPago>
                        {
                        for $detallePago in $informacionPago/ges1:detallePago
                        return
                        <ges1:detallePago>
                            <lin:NumeroLineaCredito>{fn:data($detallePago/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                            <lin:Fondo>{fn:data($detallePago/lin:Fondo)}</lin:Fondo>
                           
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
                                for $comentario3 in $informacionPago/ges1:detallePago/ges1:comentario3
                                return 
                                <ges1:comentario3>{fn:data($informacionPago/ges1:detallePago/ges1:comentario3)}</ges1:comentario3>
                            }
                        </ges1:detallePago>
                        }
                    </ges1:informacionPago>
                    }
                </ges1:avisoOperacion>
                else ()
                }
            </ges1:aviso>
        </ges:AvisoCobro>
    </ges:GeneraReporteAvisoCobroRequest>
};

local:funcGenerararchivosfenix_request_to_generaravisocobromora_request($inputVariable.GenerarArchivosFenixRequest)
