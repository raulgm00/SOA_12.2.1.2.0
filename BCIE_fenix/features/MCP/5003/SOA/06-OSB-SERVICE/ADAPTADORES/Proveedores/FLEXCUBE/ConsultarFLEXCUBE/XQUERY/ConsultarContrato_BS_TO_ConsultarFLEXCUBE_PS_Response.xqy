xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarContrato";
(:: import schema at "../XSD/ConsultarContrato_sp.xsd" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $consultarContratoResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;
declare variable $nivelDetalle as xs:string external;

declare function local:func($consultarContratoResponse as element() (:: schema-element(ns2:OutputParameters) ::), 
                            $nivelDetalle as xs:string) 
                            as element() (:: schema-element(ns1:ConsultarFLEXCUBEResponse) ::) {
        <ns1:ConsultarFLEXCUBEResponse>
       
        <ns1:TipoContrato>
            {
                if ($nivelDetalle = 'LINEA' and $consultarContratoResponse/ns2:CODIGO_RESULTADO = 0) then 
                    <lin:LineaCredito>
                        <lin:idLineaCredito>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:NUMERO_LINEA_CREDITO)}</lin:idLineaCredito>
                        <lin:NumeroLineaCredito>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:NUMERO_LINEA_CREDITO)}</lin:NumeroLineaCredito>
                        <lin:Descripcion>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:DESCRIPCION_LINEA)}</lin:Descripcion>
                        <lin:Flexcube>
                            <lin:id>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:NUMERO_CONTRATO)}</lin:id>
                            <lin:idProductoFlexcube></lin:idProductoFlexcube>
                            <lin:idFlexcubePasivo></lin:idFlexcubePasivo>
                        </lin:Flexcube>
                        <lin:Fondo>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:FONDO)}</lin:Fondo>
                        <lin:MontoLinea>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:MONTO_APROBADO)}</lin:MontoLinea>
                        <lin:saldo>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:SALDO)}</lin:saldo>
                        <lin:FechaValor>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:FECHA_VALOR)}</lin:FechaValor>
                        <lin:FechaVencimiento>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:FECHA_VENCIMIENTO)}</lin:FechaVencimiento>
                        <lin:CodigoSerialLimite>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:SERIAL_LIMITE)}</lin:CodigoSerialLimite>
                        <lin:FechaMaximaDesembolso>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:FECHA_MAX_DESEMBOLSAR)}</lin:FechaMaximaDesembolso>
                        <lin:tasaMinima>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:TASA_MINIMA)}</lin:tasaMinima>
                        <lin:tasaMaxima>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:TASA_MAXIMA)}</lin:tasaMaxima>
                        <lin:Monto>
                            <com:tipo>
                                <cat:Id></cat:Id>
                                <cat:Descripcion></cat:Descripcion>
                                <cat:DescripcionCorta>DISPONIBLE</cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno></cat:codigoExterno>
                            </com:tipo>
                            { for $importe in $consultarContratoResponse/ns2:CONTRATO/ns2:LISTA_BALANCE/ns2:LISTA_BALANCE_ITEM
                                  return 
                                        if(fn:data($importe/ns2:COMPONENTE) = 'DISPONIBILIDAD') then <com:importe>{fn:data($importe/ns2:BALANCE)}</com:importe>
                                   else 
                                            ()
                           }
                            <com:moneda>
                                <cat:Id></cat:Id>
                                <cat:Descripcion></cat:Descripcion>
                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:MONEDA)}</cat:codigoExterno>
                            </com:moneda>
                         
                        </lin:Monto>
                    
                    
                    <lin:Fuente>
                            <lin:idContrato>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:NUMERO_CONTRATO)}</lin:idContrato>
                    </lin:Fuente>
                    <lin:HerramientaCE>
                        <her:ActividadEconomica>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:ACTIVIDAD_ECONOMICA)}</cat:codigoExterno>
                        </her:ActividadEconomica>
                        <her:EjeEstrategico>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:EJE_ESTRATEGICO)}</cat:codigoExterno>
                        </her:EjeEstrategico>
                        <her:AreaFocalizacion>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:AREA_FOCALIZACION)}</cat:codigoExterno>
                        </her:AreaFocalizacion>
                    </lin:HerramientaCE>
                           
               
                    </lin:LineaCredito>
                    
                    
                else if($nivelDetalle = 'COMISION'and $consultarContratoResponse/ns2:CODIGO_RESULTADO = 0)then
                    <lin:Comision>
                       <com:idComision></com:idComision>
             <com:idOperacion></com:idOperacion>
             <com:nombre></com:nombre>
        
             <com:descripcion></com:descripcion>
             <com:tipoComision>
                 <com:idCatComision></com:idCatComision>
                 <com:descripcion></com:descripcion>
                 <com:descripcionCorta></com:descripcionCorta>
                 <com:idTipoComision>
                     <cat:Id></cat:Id>
                     <cat:Descripcion></cat:Descripcion>
                     <cat:DescripcionCorta></cat:DescripcionCorta>
                     <cat:estatus></cat:estatus>
                     <cat:codigoExterno></cat:codigoExterno>
                 </com:idTipoComision>
                 <com:esEditableEnFormalizacion></com:esEditableEnFormalizacion>
                 <com:requiereValidarCOFI></com:requiereValidarCOFI>
                 <com:sePuedeDispensar></com:sePuedeDispensar>
                 <com:seRegistraEnFlexCube></com:seRegistraEnFlexCube>
                 <com:esPlantilla></com:esPlantilla>
                 <com:idComisionPrecarga></com:idComisionPrecarga>
                 <com:fechaRegistro></com:fechaRegistro>
                 <com:estado>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:ESTADO)}</com:estado>
                 <com:codigoExterno></com:codigoExterno>
             </com:tipoComision>
             { for $importe in $consultarContratoResponse/ns2:CONTRATO/ns2:LISTA_BALANCE/ns2:LISTA_BALANCE_ITEM
                                  return 
                                        if(fn:data($importe/ns2:COMPONENTE) = 'CC13TRAMIT') then <com:montoComision>{fn:data($importe/ns2:BALANCE)}</com:montoComision>
                                   else 
                                            ()
                           }
             <com:moneda>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:MONEDA)}</cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:moneda>
      
             <com:montoBase>
                 <com:idMontoBase></com:idMontoBase>
                 <com:valorMontoBase></com:valorMontoBase>
                 <com:porcentajeMontoBase></com:porcentajeMontoBase>
             </com:montoBase>
             <com:fechaValor>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:FECHA_VALOR)}</com:fechaValor>
             <com:fechaVencimiento></com:fechaVencimiento>
             <com:fechaInicioCapital></com:fechaInicioCapital>
             <com:fechaInicioComision></com:fechaInicioComision>
             <com:tipoFrecuencia>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta></cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:tipoFrecuencia>
             <com:frecuenciaPago></com:frecuenciaPago>
             <com:fondo>{fn:data($consultarContratoResponse/ns2:CONTRATO/ns2:FONDO)}</com:fondo>
             <com:comisionCompartida></com:comisionCompartida>
             <com:codigoDesembolso></com:codigoDesembolso>
             <com:numeroTesoreria></com:numeroTesoreria>
             <com:codigoContrato></com:codigoContrato>
             <com:momentoCobro>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta></cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:momentoCobro>
             <com:tipoTasa>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta></cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:tipoTasa>
             <com:baseCalculo>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta></cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:baseCalculo>
             <com:responsableComision></com:responsableComision>
             <com:estadoTCC>
                 <atr:id></atr:id>
                 <atr:descripcion></atr:descripcion>
                 <atr:descripcionCorta></atr:descripcionCorta>
                 <atr:estatus></atr:estatus>
                 <atr:codigoExterno></atr:codigoExterno>
                 <atr:esSubEstado></atr:esSubEstado>
             </com:estadoTCC>
             <com:subEstadoTCC>
                 <atr:id></atr:id>
                 <atr:descripcion></atr:descripcion>
                 <atr:descripcionCorta></atr:descripcionCorta>
                 <atr:estatus></atr:estatus>
                 <atr:codigoExterno></atr:codigoExterno>
                 <atr:esSubEstado></atr:esSubEstado>
             </com:subEstadoTCC>
             <com:fechaRegistro></com:fechaRegistro>
             <com:estado></com:estado>
             <com:comisionEnmendada></com:comisionEnmendada>
             <com:fechaEnmienda></com:fechaEnmienda>
             <com:banSugerida></com:banSugerida>
             <com:numeroCuentaCliente></com:numeroCuentaCliente>
             <com:observaciones></com:observaciones>
             <com:configAtributo>
                 <atr:id></atr:id>
                 <atr:columna></atr:columna>
                 <atr:ordenamiento></atr:ordenamiento>
                 <atr:puedeLeer></atr:puedeLeer>
                 <atr:puedeModificar></atr:puedeModificar>
                 <atr:etiqueta></atr:etiqueta>
             </com:configAtributo>
          </lin:Comision>
                else()
            }
        </ns1:TipoContrato>
        <ns1:Resultado>
        {
                if (fn:data($consultarContratoResponse/ns2:CODIGO_RESULTADO)= 0) then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
        }
         {
                if (fn:data($consultarContratoResponse/ns2:CODIGO_RESULTADO)= 0) then
            <res:message>Consulta realizada correctamente</res:message>
             else  <res:message>{fn:data($consultarContratoResponse/ns2:MENSAJE)}</res:message>
        }
        </ns1:Resultado>
    </ns1:ConsultarFLEXCUBEResponse>
};

local:func($consultarContratoResponse, $nivelDetalle)