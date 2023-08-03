xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)


declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerAvisoCobro";
(:: import schema at "../XSD/ObtenerAvisoCobro_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;
declare variable $ObtenerAvisoCobroRequest as element() (:: schema-element(ns3:ObtenerAvisoCobroRequest) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::),
                            $ObtenerAvisoCobroRequest as element() (:: schema-element(ns3:ObtenerAvisoCobroRequest) ::)) 
                            as element() (:: schema-element(ns3:ObtenerAvisoCobroResponse) ::) {
    <ns3:ObtenerAvisoCobroResponse>

        <ns3:AvisoCobro>

            { 
            for $Clientes in distinct-values($OutputParameters/ns1:XML_AVISO/AVISO/LISTA_DOCUMENTOS/DOCUMENTO/CODIGO_CLIENTE)
            let $AvisosCliente:= $OutputParameters/ns1:XML_AVISO/AVISO/LISTA_DOCUMENTOS/DOCUMENTO[CODIGO_CLIENTE = $Clientes]
            return
            <ges:aviso>
                <cli:idFacturador>{fn:data($AvisosCliente[1]/CODIGO_CLIENTE)}</cli:idFacturador>
                <cli:razonSocial>{fn:data($AvisosCliente[1]/NOMBRE_CLIENTE)}</cli:razonSocial>
                <cli:pais>
                    <cat:Descripcion>{fn:data($AvisosCliente[1]/NOMBRE_PAIS)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($AvisosCliente[1]/CODIGO_PAIS)}</cat:DescripcionCorta>
                </cli:pais>
                <ges:idEjecucionLote>{fn:data($ObtenerAvisoCobroRequest/ns3:idContrato)}</ges:idEjecucionLote>
                <ges:emision>{fn:current-date()}</ges:emision>
                
                {
                for $aviso in $AvisosCliente
                return
                if ($aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "SALDO_NO_VENCIDO"]) then
                
                for $moneda in distinct-values($aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "SALDO_NO_VENCIDO"]/MONEDA)
                return
                <ges:avisoOperacion>
                    <ges:Moneda>
                        <cat:Descripcion>{fn:data($moneda)}</cat:Descripcion>
                    </ges:Moneda>

                    {
                    for $fecha in distinct-values($aviso/LISTA_DETALLES/DETALLE[MONEDA=$moneda]/LISTA_CONTRATOS/CONTRATO/FECHA_VENCIMIENTO)
                    return
                    <ges:informacionPago>
                        <ges:fechaPago>{fn:data($fecha)}</ges:fechaPago>
                        

                        {
                        for $detalle in $aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "SALDO_NO_VENCIDO"]
                        let $linea := if ($detalle/MONEDA = $moneda and $detalle/LISTA_CONTRATOS/CONTRATO/FECHA_VENCIMIENTO = $fecha)
                        then $detalle/LINEA
                        else()
                        return
                        
                        for $fondo in $detalle/LISTA_CONTRATOS/CONTRATO[FECHA_VENCIMIENTO=$fecha]/CODIGO_FONDO
                        where $detalle/LINEA = $linea 
                        return
                        
                        if ($linea != '') then
                        
                       
                        <ges:detallePago>
                            <lin:NumeroLineaCredito>{fn:data($linea)}</lin:NumeroLineaCredito>
                            <lin:Flexcube>
                                <lin:id>{fn:data($detalle/LINEA_CONTRATO)}</lin:id>
                            </lin:Flexcube>
                             <lin:Fondo>{fn:data($fondo)}</lin:Fondo>
                         {

                          for $listaSaldo in $detalle/LISTA_CONTRATOS/CONTRATO[FECHA_VENCIMIENTO=$fecha]
                          let $saldo := $listaSaldo[CODIGO_FONDO=$fondo]/LISTA_SALDOS/SALDO
                          where $detalle/LINEA = $linea 
                          return
                          
                                                      
                            for $monto in $saldo/*
                            return
                            <lin:Monto>

                                <com:tipo>
                                    <cat:Descripcion>{fn:name($monto)}</cat:Descripcion>
                                </com:tipo>
                                <com:importe>{fn:data($monto)}</com:importe>
                            
                            </lin:Monto>

                            }
                            
                            <ges:tablasDetalle>
                            
                             {
                 
                                for $detalleP in $aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "DETALLE_CALCULO"]
                                let $totalPago := if ($detalleP/MONEDA = $moneda and $detalleP/LINEA= $linea)
                                then $detalleP/LISTA_CONTRATOS/CONTRATO[TIPO_INTERES="Intereses Ordinarios"]
                                else()
                                return
                                
                                  for $pago in $totalPago
                                  let $fechaDetalle := distinct-values($pago/LISTA_INTERESES/INTERES/FECHA_VENCIMIENTO)
                                  let $fondoPago := $pago/CODIGO_FONDO
                                  return
                                  
                                  
                                    if ( $fechaDetalle = $fecha and $fondo = $fondoPago)
                                    then
                                      <ges:pagosAplicados>
                                           <com:importe>{fn:data($pago/LISTA_INTERESES/TOTAL_INTERES_PAGO)}</com:importe>
                                      </ges:pagosAplicados>
                                      
                                    
                                    else ()
                                    
                                    }
                                    {
                   
                                    for $detalleD in $aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "DETALLE_CALCULO"]
                                    let $contratoDetalle := if ($detalleD/MONEDA = $moneda and $detalleD/LINEA= $linea)
                                    then $detalleD/LISTA_CONTRATOS/CONTRATO[TIPO_INTERES="Intereses Ordinarios"]
                                    else()
                                    return
                                  
                                     for $intereses in $contratoDetalle[CODIGO_FONDO=$fondo]/LISTA_INTERESES/INTERES[FECHA_VENCIMIENTO = $fecha]
                                      return
                                      
                                      <ges:detalleIntereses>
                                      {
                                       
                                        for $interes in $intereses/*
                                        return    
                                    
                                          if ( fn:name($interes)="SALDO" or fn:name($interes)="MONTO_INTERES") then
                                         <des:monto>
                                                <com:tipo>
                                                    <cat:Descripcion>{fn:name($interes)}</cat:Descripcion>
                                                </com:tipo>
                                                <com:importe>{fn:data($interes)}</com:importe> 
                                         </des:monto>
                                        else()
                                        
                                        }
                                     
                                      <des:condicionesFinancieras>
                                          <des:tasa>
                                              <des:fija>
                                                    <des:valor>{fn:data($intereses/TASA)}</des:valor>
                                               </des:fija>
                                          </des:tasa>
                                          <des:baseCalculo>
                                              <cat:DescripcionCorta>{fn:data($intereses/BASE)}</cat:DescripcionCorta>
                                          </des:baseCalculo>
                                      </des:condicionesFinancieras>
                                      <ges:periodo>
                                          <com:inicio>{fn:data($intereses/FECHA_DESDE)}</com:inicio>
                                          <com:fin>{fn:data($intereses/FECHA_HASTA)}</com:fin>
                                          <com:dias>{fn:data($intereses/DIAS)}</com:dias>
                                      </ges:periodo>
                                  </ges:detalleIntereses>
                         
                                 
                                }
                                
                            </ges:tablasDetalle>
                  
                           
                             <ges:tablasDetalle>
                             {
                              for $detalleP in $aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "DETALLE_CALCULO"]
                                let $totalPago := if ($detalleP/MONEDA = $moneda and $detalleP/LINEA= $linea)
                                then $detalleP/LISTA_CONTRATOS/CONTRATO[TIPO_INTERES="Comision de Compromiso"]
                                else()
                                return
                                
                                  for $pago in $totalPago
                                  let $fechaDetalle := distinct-values($pago/LISTA_INTERESES/INTERES/FECHA_VENCIMIENTO)
                                  let $fondoPago := $pago/CODIGO_FONDO
                                  return
                                  
                                  
                                    if ( $fechaDetalle = $fecha and $fondo = $fondoPago)
                                    then
                                      <ges:pagosAplicados>
                                           <com:importe>{fn:data($pago/LISTA_INTERESES/TOTAL_INTERES_PAGO)}</com:importe>
                                      </ges:pagosAplicados>
                                    else ()
                                    }
                                    
                                    {
                                    for $detalleD in $aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "DETALLE_CALCULO"]
                                    let $contratoDetalle := if ($detalleD/MONEDA = $moneda and $detalleD/LINEA= $linea)
                                    then $detalleD/LISTA_CONTRATOS/CONTRATO[TIPO_INTERES="Comision de Compromiso"]
                                    else()
                                    return
                                  
                                     for $intereses in $contratoDetalle[CODIGO_FONDO=$fondo]/LISTA_INTERESES/INTERES[FECHA_VENCIMIENTO = $fecha]
                                      return
                                      
                                      <ges:detalleCC>
                                      {
                                       
                                        for $interes in $intereses/*
                                        return    
                                    
                                          if ( fn:name($interes)="SALDO" or fn:name($interes)="MONTO_INTERES") then
                                         <des:monto>
                                                <com:tipo>
                                                    <cat:Descripcion>{fn:name($interes)}</cat:Descripcion>
                                                </com:tipo>
                                                <com:importe>{fn:data($interes)}</com:importe> 
                                                </des:monto>
                                        else()
                                        
                                        }
                                     
                                      <des:condicionesFinancieras>
                                          <des:tasa>
                                              <des:fija>
                                                    <des:valor>{fn:data($intereses/TASA)}</des:valor>
                                               </des:fija>
                                          </des:tasa>
                                          <des:baseCalculo>
                                              <cat:DescripcionCorta>{fn:data($intereses/BASE)}</cat:DescripcionCorta>
                                          </des:baseCalculo>
                                      </des:condicionesFinancieras>
                                      <ges:periodo>
                                          <com:inicio>{fn:data($intereses/FECHA_DESDE)}</com:inicio>
                                          <com:fin>{fn:data($intereses/FECHA_HASTA)}</com:fin>
                                          <com:dias>{fn:data($intereses/DIAS)}</com:dias>
                                      </ges:periodo>
                                  </ges:detalleCC>
                           }
                                
                            </ges:tablasDetalle>
                  
                            
                           <ges:tablasDetalle>
                             {
                              for $detalleP in $aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "DETALLE_CALCULO"]
                                let $totalPago := if ($detalleP/MONEDA = $moneda and $detalleP/LINEA= $linea)
                                then $detalleP/LISTA_CONTRATOS/CONTRATO[TIPO_INTERES="Otras Comisiones"]
                                else()
                                return
                                
                                  for $pago in $totalPago
                                  let $fechaDetalle := distinct-values($pago/LISTA_INTERESES/INTERES/FECHA_VENCIMIENTO)
                                  let $fondoPago := $pago/CODIGO_FONDO
                                  return
                                  
                                  
                                    if ( $fechaDetalle = $fecha and $fondo = $fondoPago)
                                    then
                                      <ges:pagosAplicados>
                                           <com:importe>{fn:data($pago/LISTA_INTERESES/TOTAL_INTERES_PAGO)}</com:importe>
                                      </ges:pagosAplicados>
                                    else ()
                                    }
                                    
                                    {
                                    for $detalleD in $aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "DETALLE_CALCULO"]
                                    let $contratoDetalle := if ($detalleD/MONEDA = $moneda and $detalleD/LINEA= $linea)
                                    then $detalleD/LISTA_CONTRATOS/CONTRATO[TIPO_INTERES="Otras Comisiones"]
                                    else()
                                    return
                                  
                                     for $intereses in $contratoDetalle[CODIGO_FONDO=$fondo]/LISTA_INTERESES/INTERES[FECHA_VENCIMIENTO = $fecha]
                                      return
                                      
                                      <ges:detalleOC>
                                        <com:tipo>
                                          <cat:Descripcion>{fn:name($intereses/DIAS)}</cat:Descripcion>
                                       </com:tipo>
                                      <com:importe>{fn:data($intereses/MONTO_INTERES)}</com:importe>
                                      <ges:vencimiento>{fn:data($intereses/FECHA_VENCIMIENTO)}</ges:vencimiento>
                                 </ges:detalleOC>
                           }
                                
                            </ges:tablasDetalle>
                  
                            
                            
                            <ges:comentario1>El pago deberá realizarse de acuerdo con las siguientes instrucciones de pago:</ges:comentario1>
                            {
                            for $banco in $detalle/LISTA_CUENTAS/CUENTA[CODIGO_FONDO = $fondo]
                            return
                            <ges:Banco>
                                <ges:banco>{fn:data($banco/BANCO)}</ges:banco>
                                <ges:SWIFTBanco>{fn:data($banco/CODIGO_SWIFT)}</ges:SWIFTBanco>
                                <ges:cuentaNo>{fn:data($banco/NUMERO_CUENTA)}</ges:cuentaNo>
                                <ges:beneficiario>{fn:data($banco/NOMBRE_CUENTA)}</ges:beneficiario>
                                <ges:SWIFTBeneficiario></ges:SWIFTBeneficiario>
                                <ges:Referencia>{fn:data($banco/REFERENCIA)}</ges:Referencia>
                            </ges:Banco>
                            }
                            {
                            for $nota in $detalle/LISTA_NOTAS/NOTA/TEXTO
                            return
                            <ges:comentario2>{fn:data($nota)}</ges:comentario2>
                            }
                            {
                            for $detalleNota in $aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "DETALLE_CALCULO"]
                              let $contratoDetalle := if ($detalleNota/MONEDA = $moneda and $detalleNota/LINEA= $linea)
                              then $detalleNota/LISTA_CONTRATOS/CONTRATO[TIPO_INTERES="Intereses Ordinarios"]
                              else()
                              return
                            
                               if ($contratoDetalle[CODIGO_FONDO=$fondo]/LISTA_INTERESES/INTERES[FECHA_VENCIMIENTO = $fecha]) then
                                 if (fn:data($AvisosCliente[1]/REVISION_TASA = 'S'))then
                                  <ges:comentario3>{fn:data($detalleNota/LISTA_NOTAS[@CLASE = 'NOTA_DETALLE_HEADER']/NOTA/TEXTO)}</ges:comentario3>
                                 else 
                                 
                                  <ges:comentario3></ges:comentario3>
                               else
                               ()
                             }
                                
                        </ges:detallePago>
                        
                         else ()
                        }
                    </ges:informacionPago>
                    }
                </ges:avisoOperacion>
                else()
                }
                
                
                {
                for $aviso in $AvisosCliente
                return
                if ($aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "SALDO_VENCIDO"]) then

                for $moneda in distinct-values($aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "SALDO_VENCIDO"]/MONEDA)
                return
                <ges:avisoOperacion>
                    <ges:Moneda>
                        <cat:Descripcion>{fn:data($moneda)}</cat:Descripcion>
                    </ges:Moneda>

                    {
                    for $fecha in distinct-values($aviso/LISTA_DETALLES/DETALLE[MONEDA=$moneda]/LISTA_CONTRATOS/CONTRATO/LISTA_VENCIMIENTOS/VENCIMIENTO/FECHA_VENCIMIENTO)
                    return
                    <ges:informacionPago>
                        <ges:fechaPago>{fn:data($fecha)}</ges:fechaPago>
                        

                        {
                        for $detalle in $aviso/LISTA_DETALLES/DETALLE[@TIPO_DETALLE = "SALDO_VENCIDO"]
                        let $linea := if ($detalle/MONEDA = $moneda and $detalle/LISTA_CONTRATOS/CONTRATO/LISTA_VENCIMIENTOS/VENCIMIENTO/FECHA_VENCIMIENTO = $fecha)
                        then $detalle/LINEA
                        else()
                        return
                        
                        for $fondo in $detalle/LISTA_CONTRATOS/CONTRATO/CODIGO_FONDO
                        where $detalle/LINEA = $linea 
                        and $detalle/LISTA_CONTRATOS/CONTRATO/LISTA_VENCIMIENTOS/VENCIMIENTO/FECHA_VENCIMIENTO = $fecha
                        return
                        
                        if ($linea != '') then
                        
                       
                        <ges:detallePago>
                            <lin:NumeroLineaCredito>{fn:data($linea)}</lin:NumeroLineaCredito>
                            <lin:Flexcube>
                                <lin:id>{fn:data($detalle/LINEA_CONTRATO)}</lin:id>
                            </lin:Flexcube>
                             <lin:Fondo>{fn:data($fondo)}</lin:Fondo>
                         {

                          for $listaSaldo in $detalle/LISTA_CONTRATOS/CONTRATO[CODIGO_FONDO=$fondo]/LISTA_VENCIMIENTOS/VENCIMIENTO[FECHA_VENCIMIENTO=$fecha]
                          where $detalle/LINEA = $linea
                          return
                          
                        
                           <ges:Mora>
                                  {
                                  for $monto in $listaSaldo/*
                                  return
                                <des:monto>
                                    <com:tipo>
                                        <cat:Descripcion>{fn:name($monto)}</cat:Descripcion>
                                    </com:tipo>
                                    <com:importe>{fn:data($monto)}</com:importe>
                                </des:monto> 
                                }
                            </ges:Mora>
                           
                            }
                            
                            <ges:comentario1>El pago deberá realizarse de acuerdo con las siguientes instrucciones de pago:</ges:comentario1>
                            {
                            for $banco in $detalle/LISTA_CUENTAS/CUENTA
                            return
                            <ges:Banco>
                                <ges:banco>{fn:data($banco/BANCO)}</ges:banco>
                                <ges:SWIFTBanco>{fn:data($banco/CODIGO_SWIFT)}</ges:SWIFTBanco>
                                <ges:cuentaNo>{fn:data($banco/NUMERO_CUENTA)}</ges:cuentaNo>
                                <ges:beneficiario>{fn:data($banco/NOMBRE_CUENTA)}</ges:beneficiario>
                                <ges:SWIFTBeneficiario></ges:SWIFTBeneficiario>
                                <ges:Referencia>{fn:data($banco/REFERENCIA)}</ges:Referencia>
                            </ges:Banco>
                            }
                            {
                            for $nota in $detalle/LISTA_NOTAS[@CLASE="NOTA_DETALLE_CONTENT"]/NOTA/TEXTO
                            return
                            <ges:comentario2>{fn:data($nota)}</ges:comentario2>
                            }
                        </ges:detallePago>
                        
                         else ()
                        }
                    </ges:informacionPago>
                    }
                </ges:avisoOperacion>
                else()
                }
                
                
            </ges:aviso>
            }
        </ns3:AvisoCobro>
        <ns3:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion Exitosa</res:message>
        </ns3:Resultado>
        
    </ns3:ObtenerAvisoCobroResponse>
};

local:func($OutputParameters, $ObtenerAvisoCobroRequest)

