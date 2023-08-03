xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoFormalizacionResponse) ::) external;
declare variable $tempConsultarContratoLineaCreditoAsociada.response as element() (:: schema-element(lin:ConsultarLineaCreditoResponse) ::) external;

declare function local:funcXq_response($outputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoFormalizacionResponse) ::), 
                                       $tempConsultarContratoLineaCreditoAsociada.response as element() (:: schema-element(lin:ConsultarLineaCreditoResponse) ::)) 
                                       as element() (:: schema-element(lin:ConsultarLineaCreditoFormalizacionResponse) ::) {
    <lin:ConsultarLineaCreditoFormalizacionResponse>
      {
      for $contrato in $outputVariable.response/lin:clienteContrato
      return
         <lin:clienteContrato>
            <con:idContrato>{fn:data($contrato/con:idContrato)}</con:idContrato>
            <con:idOperacion>{fn:data($contrato/con:idOperacion)}</con:idOperacion>
            <con:fechaFirma>{fn:data($contrato/con:fechaFirma)}</con:fechaFirma>
            <con:fechaVigencia>{fn:data($contrato/con:fechaVigencia)}</con:fechaVigencia>
             {
                 if ($contrato/con:idTipoMonedaMontoEscriturado)
                 then <con:idTipoMonedaMontoEscriturado>{fn:data($contrato/con:idTipoMonedaMontoEscriturado)}</con:idTipoMonedaMontoEscriturado>
                 else ()
             }
            <con:MontoEscriturado>{fn:data($contrato/con:MontoEscriturado)}</con:MontoEscriturado>
            <con:cuentaCliente>
                {
                    for $cuentaCliente in $contrato/con:cuentaCliente/con:cuentaCliente
                    return 
                    <con:cuentaCliente>{fn:data($cuentaCliente)}</con:cuentaCliente>
                }
            </con:cuentaCliente>
            <con:instanciaProceso>{fn:data($contrato/con:instanciaProceso)}</con:instanciaProceso>
             {
                 if ($contrato/con:fechaValor)
                 then <con:fechaValor>{fn:data($contrato/con:fechaValor)}</con:fechaValor>
                 else ()
             }
          {
          for $linea in $contrato/con:LineaCredito
          return
            <con:LineaCredito>
                <lin1:idLineaCredito>{fn:data($linea/lin1:idLineaCredito)}</lin1:idLineaCredito>
                <lin1:idContrato>{fn:data($linea/lin1:idContrato)}</lin1:idContrato>
                <lin1:NumeroLineaCredito>{fn:data($linea/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
                <lin1:Descripcion>{fn:data($linea/lin1:Descripcion)}</lin1:Descripcion>  
                <lin1:Flexcube>
                   <lin1:id>{fn:data($linea/lin1:Flexcube/lin1:id)}</lin1:id>
                   <lin1:idProductoFlexcube>{fn:data($linea/lin1:Flexcube/lin1:idProductoFlexcube)}</lin1:idProductoFlexcube>
                   <lin1:idFlexcubePasivo>{fn:data($linea/lin1:Flexcube/lin1:idFlexcubePasivo)}</lin1:idFlexcubePasivo>
                </lin1:Flexcube>
                <lin1:Fondo>{fn:data($linea/lin1:Fondo)}</lin1:Fondo>
                {
                    if ($linea/lin1:IdTipoMonedaMontoLinea)
                    then <lin1:IdTipoMonedaMontoLinea>{fn:data($linea/lin1:IdTipoMonedaMontoLinea)}</lin1:IdTipoMonedaMontoLinea>
                    else ()
                }
                <lin1:MontoLinea>{fn:data($linea/lin1:MontoLinea)}</lin1:MontoLinea>
                <lin1:saldo>{fn:data($linea/lin1:saldo)}</lin1:saldo>
                <lin1:EsRevolvente>{fn:data($linea/lin1:EsRevolvente)}</lin1:EsRevolvente>
                <lin1:TratamientoDiasFeriados>{fn:data($linea/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
                <lin1:FechaValor>{fn:data($linea/lin1:FechaValor)}</lin1:FechaValor>
                <lin1:FechaVencimiento>{fn:data($linea/lin1:FechaVencimiento)}</lin1:FechaVencimiento>
                <lin1:CodigoPantallaLimite>{fn:data($linea/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
                <lin1:CodigoSerialLimite>{fn:data($linea/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
                <lin1:CodigoAsignacion>{fn:data($linea/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
                <lin1:DescripcionAsignacion>{fn:data($linea/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>
                <lin1:CondicionEspecial>{fn:data($linea/lin1:CondicionEspecial)}</lin1:CondicionEspecial>
                <lin1:FechaRegistro>{fn:data($linea/lin1:FechaRegistro)}</lin1:FechaRegistro>
                <lin1:FechaMaximaDesembolso>{fn:data($linea/lin1:FechaMaximaDesembolso)}</lin1:FechaMaximaDesembolso>
                <lin1:Estado>{fn:data($linea/lin1:Estado)}</lin1:Estado>
                <lin1:descCondEspecial>{fn:data($linea/lin1:descCondEspecial)}</lin1:descCondEspecial>
                <lin1:frecuenciaGracia>{fn:data($linea/lin1:frecuenciaGracia)}</lin1:frecuenciaGracia>
                <lin1:plazoGracia>{fn:data($linea/lin1:plazoGracia)}</lin1:plazoGracia>
                <lin1:frecuenciaFinanciamiento>{fn:data($linea/lin1:frecuenciaFinanciamiento)}</lin1:frecuenciaFinanciamiento>
                <lin1:plazoFinanciamiento>{fn:data($linea/lin1:plazoFinanciamiento)}</lin1:plazoFinanciamiento>
                <lin1:recursosExternos>{fn:data($linea/lin1:recursosExternos)}</lin1:recursosExternos>
                {
                if (string(fn:data($linea/lin1:tasaMinima))!='')then
                <lin1:tasaMinima>{fn:data($linea/lin1:tasaMinima)}</lin1:tasaMinima>
                else
                ()
                }
                {
                if (string(fn:data($linea/lin1:tasaMaxima))!='')then
                <lin1:tasaMaxima>{fn:data($linea/lin1:tasaMaxima)}</lin1:tasaMaxima>
                  else
                ()
                }
                {
                if (string(fn:data($linea/lin1:montoMinimo))!='')then
                <lin1:montoMinimo>{fn:data($linea/lin1:montoMinimo)}</lin1:montoMinimo>
                else
                ()
                }
                 {
                if (string(fn:data($linea/lin1:montoMaximo))!='')then
                <lin1:montoMaximo>{fn:data($linea/lin1:montoMaximo)}</lin1:montoMaximo>
                else
                ()
                }
                
                {
                for $Monto in $linea/lin1:Monto
                return 
                <lin1:Monto>
                    {
                      $Monto/*
                    }
                </lin1:Monto>
              }
                    then <lin1:MoverEntreMeses>{fn:data($linea/lin1:MoverEntreMeses)}</lin1:MoverEntreMeses>

                {
                for $Condicion in $linea/lin1:Condicion
                return 
                <lin1:Condicion>
                    {
                      $Condicion/*
                    }
                </lin1:Condicion>
              }
              {
                for $Termino in $linea/lin1:Termino
                return 
                <lin1:Termino>
                    {
                      $Termino/*
                    }
                </lin1:Termino>
              }
              {
                for $Comision in $linea/lin1:Comision
                return 
                <lin1:Comision>
                    {
                      $Comision/*
                    }
                </lin1:Comision>
              }
              {
                for $Fuente in $linea/lin1:Fuente
                return 
                <lin1:Fuente>
                    {
                      $Fuente/*
                    }
                </lin1:Fuente>
              }
              {
                for $ContratoDesembolso in $linea/lin1:ContratoDesembolso
                return 
                <lin1:ContratoDesembolso>
                    {
                      $ContratoDesembolso/*
                    }
                </lin1:ContratoDesembolso>
              }
              {
                for $atributos in $linea/lin1:atributos
                return 
                <lin1:atributos>
                    {
                      $atributos/*
                    }
                </lin1:atributos>
            }
            <lin1:HerramientaCE>
                {
                  $linea/lin1:HerramientaCE/*
                }
            </lin1:HerramientaCE>
            </con:LineaCredito>
          }
        </lin:clienteContrato>
        }
        {
        for $lineaCreditoAsociada in $outputVariable.response/lin:LineaCreditoAsociada
        return
        if (string-length($lineaCreditoAsociada/lin1:idLineaCredito/text()) >0 ) then
        <lin:LineaCreditoAsociada>
            {
                if ($lineaCreditoAsociada/lin1:idLineaCredito)
                then <lin1:idLineaCredito>{fn:data($lineaCreditoAsociada/lin1:idLineaCredito)}</lin1:idLineaCredito>
                else ()
            }
            {
                if ($lineaCreditoAsociada/lin1:idContrato)
                then <lin1:idContrato>{fn:data($lineaCreditoAsociada/lin1:idContrato)}</lin1:idContrato>
                else ()
            }
            <lin1:idOperacion>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:idOperacion)}</lin1:idOperacion>
           
            
            
            <lin1:NumeroLineaCredito>{fn:data($lineaCreditoAsociada/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Descripcion>{fn:data($lineaCreditoAsociada/lin1:Descripcion)}</lin1:Descripcion>
            <lin1:Flexcube>
                        <lin1:id>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:Flexcube/lin1:id)}</lin1:id>
                        <lin1:idProductoFlexcube>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:Flexcube/lin1:idProductoFlexcube)}</lin1:idProductoFlexcube>
                        <lin1:idFlexcubePasivo>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:Flexcube/lin1:idFlexcubePasivo)}</lin1:idFlexcubePasivo>
            </lin1:Flexcube>
            <lin1:Fondo>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:Fondo)}</lin1:Fondo>
            {
                if ($lineaCreditoAsociada/lin1:IdTipoMonedaMontoLinea)
                then <lin1:IdTipoMonedaMontoLinea>{fn:data($lineaCreditoAsociada/lin1:IdTipoMonedaMontoLinea)}</lin1:IdTipoMonedaMontoLinea>
                else ()
            }
            {
                if ($lineaCreditoAsociada/lin1:MontoLinea)
                then <lin1:MontoLinea>{fn:data($lineaCreditoAsociada/lin1:MontoLinea)}</lin1:MontoLinea>
                else ()
            }
            <lin1:EsRevolvente>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:EsRevolvente)}</lin1:EsRevolvente>
            <lin1:TratamientoDiasFeriados>{fn:data($lineaCreditoAsociada/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
            <lin1:FechaValor>{fn:data($lineaCreditoAsociada/lin1:FechaValor)}</lin1:FechaValor>
            <lin1:FechaVencimiento>{fn:data($lineaCreditoAsociada/lin1:FechaVencimiento)}</lin1:FechaVencimiento>
            <lin1:CodigoPantallaLimite>{fn:data($lineaCreditoAsociada/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
            <lin1:CodigoSerialLimite>{fn:data($lineaCreditoAsociada/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
            <lin1:CodigoAsignacion>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
            <lin1:DescripcionAsignacion>{fn:data($lineaCreditoAsociada/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>
            <lin1:CondicionEspecial>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:CondicionEspecial)}</lin1:CondicionEspecial>
            <lin1:FechaRegistro>{fn:data($lineaCreditoAsociada/lin1:FechaRegistro)}</lin1:FechaRegistro>
            <lin1:FechaMaximaDesembolso>{fn:data($lineaCreditoAsociada/lin1:FechaMaximaDesembolso)}</lin1:FechaMaximaDesembolso>
            <lin1:Estado>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:Estado)}</lin1:Estado>
            <lin1:descCondEspecial>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:descCondEspecial)}</lin1:descCondEspecial>
            <lin1:frecuenciaGracia>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:frecuenciaGracia)}</lin1:frecuenciaGracia>
            <lin1:plazoGracia>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:plazoGracia)}</lin1:plazoGracia>
            <lin1:frecuenciaFinanciamiento>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:frecuenciaFinanciamiento)}</lin1:frecuenciaFinanciamiento>
            <lin1:plazoFinanciamiento>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:plazoFinanciamiento)}</lin1:plazoFinanciamiento>
            <lin1:recursosExternos>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:recursosExternos)}</lin1:recursosExternos>
            <lin1:tasaMinima>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:tasaMinima)}</lin1:tasaMinima>
            <lin1:tasaMaxima>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:tasaMaxima)}</lin1:tasaMaxima>
            <lin1:montoMinimo>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:montoMinimo)}</lin1:montoMinimo>
            <lin1:montoMaximo>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:montoMaximo)}</lin1:montoMaximo>
            {
                for $Monto in $lineaCreditoAsociada/lin1:Monto
                return 
                <lin1:Monto>
                    {
                      $Monto/*
                    }
                </lin1:Monto>
            }
            <lin1:MoverEntreMeses>{fn:data($tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito/lin1:MoverEntreMeses)}</lin1:MoverEntreMeses>

  
            {
                for $Condicion in $lineaCreditoAsociada/lin1:Condicion
                return 
                <lin1:Condicion>
                    {
                      $Condicion/*
                    }
                </lin1:Condicion>
            }
            {
                for $Termino in $tempConsultarContratoLineaCreditoAsociada.response/lin:clienteContrato/con:LineaCredito[lin1:idLineaCredito = $lineaCreditoAsociada/lin1:idLineaCredito]/lin1:Termino
                return 
                <lin1:Termino>
                    {
                      $Termino/*
                    }
                </lin1:Termino>
            }
            {
                for $Comision in $lineaCreditoAsociada/lin1:Comision
                return 
                <lin1:Comision>
                    {
                      $Comision/*
                    }
                </lin1:Comision>
            }
            {
                for $Fuente in $lineaCreditoAsociada/lin1:Fuente
                return 
                <lin1:Fuente>
                    {
                      $Fuente/*
                    }
                </lin1:Fuente>
            }
            {
                for $ContratoDesembolso in $lineaCreditoAsociada/lin1:ContratoDesembolso
                return 
                <lin1:ContratoDesembolso>
                    {
                      $ContratoDesembolso/*
                    }
                </lin1:ContratoDesembolso>
            }
            {
                for $atributos in $lineaCreditoAsociada/lin1:atributos
                return 
                <lin1:atributos>
                    {
                      $atributos/*
                    }
                </lin1:atributos>
            }
            <lin1:HerramientaCE>
                {
                  $lineaCreditoAsociada/lin1:HerramientaCE/*
                }
            </lin1:HerramientaCE>
        </lin:LineaCreditoAsociada>
        else ()
        }
        <lin:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </lin:Resultado>
    </lin:ConsultarLineaCreditoFormalizacionResponse>
};

local:funcXq_response($outputVariable.response, $tempConsultarContratoLineaCreditoAsociada.response)
