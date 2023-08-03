xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)
declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace ns2="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";
(:: import schema at "../../WSDL/FLEXCUBE_WSDL.wsdl" ::)
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

declare variable $nivelDetalle as xs:string external;


declare variable $consultarContratoResponse as element(m:consultarContratoResponse)(:: element(ns2:contrato_out) ::) external;

declare function local:func($nivelDetalle as xs:string, 
                            $consultarContratoResponse as element(m:consultarContratoResponse) (:: element(ns2:contrato_out) ::)) 
                            as element() (:: schema-element(ns1:ConsultarFLEXCUBEResponse) ::) {
    <ns1:ConsultarFLEXCUBEResponse>
       
        <ns1:TipoContrato>
            {
                if ($nivelDetalle = 'LINEA' and $consultarContratoResponse/codigoResultado_out = 0) then 
                    <lin:LineaCredito>
                        <lin:idLineaCredito>{fn:data($consultarContratoResponse/contrato_out/ns2:numeroLineaCredito)}</lin:idLineaCredito>
                        <lin:NumeroLineaCredito>{fn:data($consultarContratoResponse/contrato_out/ns2:numeroLineaCredito)}</lin:NumeroLineaCredito>
                        <lin:Descripcion>{fn:data($consultarContratoResponse/contrato_out/ns2:descripcionLinea)}</lin:Descripcion>
                        <lin:Flexcube>
                            <lin:id>{fn:data($consultarContratoResponse/contrato_out/ns2:numeroContrato)}</lin:id>
                            <lin:idProductoFlexcube></lin:idProductoFlexcube>
                            <lin:idFlexcubePasivo></lin:idFlexcubePasivo>
                        </lin:Flexcube>
                        <lin:Fondo>{fn:data($consultarContratoResponse/contrato_out/ns2:fondo)}</lin:Fondo>
                        <lin:MontoLinea>{fn:data($consultarContratoResponse/contrato_out/ns2:montoAprobado)}</lin:MontoLinea>
                        <lin:saldo>{fn:data($consultarContratoResponse/contrato_out/ns2:saldo)}</lin:saldo>
                        <lin:FechaValor>{fn:data($consultarContratoResponse/contrato_out/ns2:fechaValor)}</lin:FechaValor>
                        <lin:FechaVencimiento>{fn:data($consultarContratoResponse/contrato_out/ns2:fechaVencimiento)}</lin:FechaVencimiento>
                        <lin:CodigoSerialLimite>{fn:data($consultarContratoResponse/contrato_out/ns2:serialLimite)}</lin:CodigoSerialLimite>
                        <lin:FechaMaximaDesembolso>{fn:data($consultarContratoResponse/contrato_out/ns2:fechaMaxDesembolsar)}</lin:FechaMaximaDesembolso>
                        <lin:tasaMinima>{fn:data($consultarContratoResponse/contrato_out/ns2:tasaMinima)}</lin:tasaMinima>
                        <lin:tasaMaxima>{fn:data($consultarContratoResponse/contrato_out/ns2:tasaMaxima)}</lin:tasaMaxima>
                        <lin:Monto>
                            <com:tipo>
                                <cat:Id></cat:Id>
                                <cat:Descripcion></cat:Descripcion>
                                <cat:DescripcionCorta>DISPONIBLE</cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno></cat:codigoExterno>
                            </com:tipo>
                            { for $importe in $consultarContratoResponse/contrato_out/ns2:listaBalance/ns2:array
                                  return 
                                        if(fn:data($importe/ns2:componente) = 'DISPONIBILIDAD') then <com:importe>{fn:data($importe/ns2:balance)}</com:importe>
                                   else 
                                            ()
                           }
                            <com:moneda>
                                <cat:Id></cat:Id>
                                <cat:Descripcion></cat:Descripcion>
                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno>{fn:data($consultarContratoResponse/contrato_out/ns2:moneda)}</cat:codigoExterno>
                            </com:moneda>
                         
                        </lin:Monto>
              
                       
                    
                    
                    <lin:Fuente>
                            <lin:idContrato>{fn:data($consultarContratoResponse/contrato_out/ns2:numeroContrato)}</lin:idContrato>
                    </lin:Fuente>
                    <lin:HerramientaCE>
                        <her:ActividadEconomica>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno>{fn:data($consultarContratoResponse/contrato_out/ns2:actividadEconomica)}</cat:codigoExterno>
                        </her:ActividadEconomica>
                        <her:EjeEstrategico>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno>{fn:data($consultarContratoResponse/contrato_out/ns2:ejeEstrategico)}</cat:codigoExterno>
                        </her:EjeEstrategico>
                        <her:AreaFocalizacion>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno>{fn:data($consultarContratoResponse/contrato_out/ns2:areaFocalizacion)}</cat:codigoExterno>
                        </her:AreaFocalizacion>
                    </lin:HerramientaCE>
                           
               
                    </lin:LineaCredito>
                    
                    
                else if($nivelDetalle = 'COMISION'and $consultarContratoResponse/codigoResultado_out = 0)then
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
                 <com:estado>{fn:data($consultarContratoResponse/contrato_out/ns2:estado)}</com:estado>
                 <com:codigoExterno></com:codigoExterno>
             </com:tipoComision>
             { for $importe in $consultarContratoResponse/contrato_out/ns2:listaBalance/ns2:array
                                  return 
                                        if(fn:data($importe/ns2:componente) = 'CC13TRAMIT') then <com:montoComision>{fn:data($importe/ns2:balance)}</com:montoComision>
                                   else 
                                            ()
                           }
             <com:moneda>
                 <cat:Id></cat:Id>
                 <cat:Descripcion></cat:Descripcion>
                 <cat:DescripcionCorta>{fn:data($consultarContratoResponse/contrato_out/ns2:moneda)}</cat:DescripcionCorta>
                 <cat:estatus></cat:estatus>
                 <cat:codigoExterno></cat:codigoExterno>
             </com:moneda>
      
             <com:montoBase>
                 <com:idMontoBase></com:idMontoBase>
                 <com:valorMontoBase></com:valorMontoBase>
                 <com:porcentajeMontoBase></com:porcentajeMontoBase>
             </com:montoBase>
             <com:fechaValor>{fn:data($consultarContratoResponse/contrato_out/ns2:fechaValor)}</com:fechaValor>
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
             <com:fondo>{fn:data($consultarContratoResponse/contrato_out/ns2:fondo)}</com:fondo>
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
                if (fn:data($consultarContratoResponse/codigoResultado_out)= 0) then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
        }
         {
                if (fn:data($consultarContratoResponse/codigoResultado_out)= 0) then
            <res:message>Consulta realizada correctamente</res:message>
             else  <res:message>{fn:data($consultarContratoResponse/mensaje_out)}</res:message>
        }
        </ns1:Resultado>
    </ns1:ConsultarFLEXCUBEResponse>
    
};

local:func($nivelDetalle, $consultarContratoResponse)