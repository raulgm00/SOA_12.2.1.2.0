xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/CommonBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $outputVariable.ConsultarLineaCreditoResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::) external;
declare variable $outInvoke_ConsultarCondicionByLineaCredito.ConsultarCondicionByIdLineaCreditoResponse as element() (:: schema-element(con:ConsultarCondicionByIdLineaCreditoResponse) ::) external;

declare function local:funcConsultarcondicion_to_consultarlineacredito2($outputVariable.ConsultarLineaCreditoResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::), 
                                                                        $outInvoke_ConsultarCondicionByLineaCredito.ConsultarCondicionByIdLineaCreditoResponse as element() (:: schema-element(con:ConsultarCondicionByIdLineaCreditoResponse) ::)) 
                                                                        as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::) {
    <lin:ConsultarLineaCreditoBPELResponse>
  <lin:LineaCredito>
            <lin1:idLineaCredito>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
            <lin1:idContrato>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:idContrato)}</lin1:idContrato>
            <lin1:NumeroLineaCredito>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Descripcion>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Descripcion)}</lin1:Descripcion>
            <lin1:Flexcube>
                    <lin1:id>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Flexcube/lin1:id)}</lin1:id>
                    <lin1:idProductoFlexcube>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)}</lin1:idProductoFlexcube>
                    <lin1:idFlexcubePasivo>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Flexcube/lin1:idFlexcubePasivo)}</lin1:idFlexcubePasivo>
            </lin1:Flexcube>
            <lin1:Fondo>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Fondo)}</lin1:Fondo>
			{
				if ($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:IdTipoMonedaMontoLinea)
				then <lin1:IdTipoMonedaMontoLinea>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:IdTipoMonedaMontoLinea)}</lin1:IdTipoMonedaMontoLinea>
				else ()
			}
            <lin1:MontoLinea>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:MontoLinea)}</lin1:MontoLinea>
            <lin1:EsRevolvente>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:EsRevolvente)}</lin1:EsRevolvente>
            <lin1:TratamientoDiasFeriados>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
            <lin1:FechaValor>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:FechaValor)}</lin1:FechaValor>
            <lin1:FechaVencimiento>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:FechaVencimiento)}</lin1:FechaVencimiento>
            <lin1:CodigoPantallaLimite>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
            <lin1:CodigoSerialLimite>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
            <lin1:CodigoAsignacion>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
            <lin1:DescripcionAsignacion>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>
            <lin1:CondicionEspecial>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:CondicionEspecial)}</lin1:CondicionEspecial>
            <lin1:FechaRegistro>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:FechaRegistro)}</lin1:FechaRegistro>
            <lin1:Estado>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Estado)}</lin1:Estado>
            <lin1:descCondEspecial>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:descCondEspecial)}</lin1:descCondEspecial>
            <lin1:frecuenciaGracia>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:frecuenciaGracia)}</lin1:frecuenciaGracia>
            <lin1:plazoGracia>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:plazoGracia)}</lin1:plazoGracia>
            <lin1:frecuenciaFinanciamiento>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:frecuenciaFinanciamiento)}</lin1:frecuenciaFinanciamiento>
            <lin1:plazoFinanciamiento>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:plazoFinanciamiento)}</lin1:plazoFinanciamiento>
            <lin1:recursosExternos>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:recursosExternos)}</lin1:recursosExternos>
            <lin1:tasaMinima>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:tasaMinima)}</lin1:tasaMinima>
            <lin1:tasaMaxima>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:tasaMaxima)}</lin1:tasaMaxima>
            <lin1:montoMinimo>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:montoMinimo)}</lin1:montoMinimo>
            <lin1:montoMaximo>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:montoMaximo)}</lin1:montoMaximo>
            {
                for $Condicion in $outInvoke_ConsultarCondicionByLineaCredito.ConsultarCondicionByIdLineaCreditoResponse/con:Condicion
                return 
                <lin1:Condicion>
                    <con1:idCondicion>{fn:data($Condicion/con1:idCondicion)}</con1:idCondicion>
                    <con1:operacion>{fn:data($Condicion/con1:operacion)}</con1:operacion>
                    <con1:nombre>{fn:data($Condicion/con1:nombre)}</con1:nombre>
                    <con1:descripcion>{fn:data($Condicion/con1:descripcion)}</con1:descripcion>
                    <con1:tipoCondicion>
                              <con1:idCatCondicion>{fn:data($Condicion/con1:tipoCondicion/con1:idCatCondicion)}</con1:idCatCondicion>
                              <con1:requiereValidarCOFI>{fn:data($Condicion/con1:tipoCondicion/con1:requiereValidarCOFI)}</con1:requiereValidarCOFI>
                    </con1:tipoCondicion>
                    <con1:controlCondicion>
                              <cat:Id>{fn:data($Condicion/con1:controlCondicion/cat:Id)}</cat:Id>
                    </con1:controlCondicion>
                    {
                        for $eventoCondicion in $Condicion/con1:eventoCondicion
                        return 
                        <con1:eventoCondicion>
                            <cat:Id>{fn:data($eventoCondicion/cat:Id)}</cat:Id>
                            <cat:Descripcion>{fn:data($eventoCondicion/cat:Descripcion)}</cat:Descripcion>
                        </con1:eventoCondicion>
                    }
                    <con1:tipoFechaInicio>
                             <cat:Id>{fn:data($Condicion/con1:tipoFechaInicio/cat:Id)}</cat:Id>
                    </con1:tipoFechaInicio>
                    <con1:fechaInicio>{fn:data($Condicion/con1:fechaInicio)}</con1:fechaInicio>
                    <con1:plazo>{fn:data($Condicion/con1:plazo)}</con1:plazo>
                    <con1:frecuenciaPlazo>
                               <cat:Id>{fn:data($Condicion/con1:frecuenciaPlazo/cat:Id)}</cat:Id>
                    </con1:frecuenciaPlazo>
                    <con1:fechaFinal>{fn:data($Condicion/con1:fechaFinal)}</con1:fechaFinal>
                    <con1:estadoTCC>
                               <atr:id>{fn:data($Condicion/con1:estadoTCC/atr:id)}</atr:id>
                               <atr:descripcion>{fn:data($Condicion/con1:estadoTCC/atr:descripcion)}</atr:descripcion>
                   </con1:estadoTCC>
                   <con1:subEstadoTCC>
                               <atr:id>{fn:data($Condicion/con1:subEstadoTCC/atr:id)}</atr:id>
                               <atr:descripcion>{fn:data($Condicion/con1:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                  </con1:subEstadoTCC>
                  <con1:fechaRegistro>{fn:data($Condicion/con1:fechaRegistro)}</con1:fechaRegistro>
                  <con1:estado>{fn:data($Condicion/con1:estado)}</con1:estado>
                  <con1:condicionEnmendada>{fn:data($Condicion/con1:condicionEnmendada)}</con1:condicionEnmendada>
                  <con1:fechaEnmienda>{fn:data($Condicion/con1:fechaEnmienda)}</con1:fechaEnmienda>
                </lin1:Condicion>
            } 
            {
                for $Termino in $outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Termino
                return 
                <lin1:Termino>
                    <ter:idTermino>{fn:data($Termino/ter:idTermino)}</ter:idTermino>
                    <ter:operacion>{fn:data($Termino/ter:operacion)}</ter:operacion>
                    <ter:nombre>{fn:data($Termino/ter:nombre)}</ter:nombre>
                    <ter:descripcion>{fn:data($Termino/ter:descripcion)}</ter:descripcion>
                    <ter:tipoTermino>
                        <ter:idCatTermino>{fn:data($Termino/ter:tipoTermino/ter:idCatTermino)}</ter:idCatTermino>
                    </ter:tipoTermino>
                    <ter:tipoFechaInicio>
                      <cat:Id>{fn:data($Termino/ter:tipoFechaInicio/cat:Id)}</cat:Id>
                    </ter:tipoFechaInicio>
                    <ter:fechaInicio>{fn:data($Termino/ter:fechaInicio)}</ter:fechaInicio>
                    <ter:plazo>{fn:data($Termino/ter:plazo)}</ter:plazo>
                    <ter:frecuenciaPlazo>
                        <cat:Id>{fn:data($Termino/ter:frecuenciaPlazo/cat:Id)}</cat:Id>
                    </ter:frecuenciaPlazo>
                    <ter:fechaVencimiento>{fn:data($Termino/ter:fechaVencimiento)}</ter:fechaVencimiento>
                    <ter:moneda>
                        <cat:Id>{fn:data($Termino/ter:moneda/cat:Id)}</cat:Id>
                    </ter:moneda>
                    <ter:monto>{fn:data($Termino/ter:monto)}</ter:monto>
                    <ter:tasa>{fn:data($Termino/ter:tasa)}</ter:tasa>
                    <ter:tipoTasa>
                        <cat:Id>{fn:data($Termino/ter:tipoTasa/cat:Id)}</cat:Id>
                    </ter:tipoTasa>
                    <ter:fecha>{fn:data($Termino/ter:fecha)}</ter:fecha>
                    <ter:frecuenciaRevision>{fn:data($Termino/ter:frecuenciaRevision)}</ter:frecuenciaRevision>
                    <ter:tipoFrecuenciaRevision>
                        <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:Id)}</cat:Id>
                    </ter:tipoFrecuenciaRevision>
                    <ter:fechaInicioRevision>{fn:data($Termino/ter:fechaInicioRevision)}</ter:fechaInicioRevision>
                    <ter:frecuenciaPagoInteres>{fn:data($Termino/ter:frecuenciaPagoInteres)}</ter:frecuenciaPagoInteres>
                    <ter:tipoFrecuenciaPagoInteres>
                        <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)}</cat:Id>
                    </ter:tipoFrecuenciaPagoInteres>
                    <ter:fechaInicioPagoInteres>{fn:data($Termino/ter:fechaInicioPagoInteres)}</ter:fechaInicioPagoInteres>
                    <ter:frecuenciaAmortizacion>{fn:data($Termino/ter:frecuenciaAmortizacion)}</ter:frecuenciaAmortizacion>
                    <ter:tipoFrecuenciaAmortizacion>
                        <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)}</cat:Id>
                    </ter:tipoFrecuenciaAmortizacion>
                    <ter:mora>{fn:data($Termino/ter:mora)}</ter:mora>
                    <ter:porcentajeCobertura>{fn:data($Termino/ter:porcentajeCobertura)}</ter:porcentajeCobertura>
                    <ter:seAplicanRecursosConcesion>{fn:data($Termino/ter:seAplicanRecursosConcesion)}</ter:seAplicanRecursosConcesion>
                    <ter:seAplicanRecursosExternos>{fn:data($Termino/ter:seAplicanRecursosExternos)}</ter:seAplicanRecursosExternos>
                    <ter:tipoContraparte>{fn:data($Termino/ter:tipoContraparte)}</ter:tipoContraparte>
                    <ter:montoMinimoDesembolso>{fn:data($Termino/ter:montoMinimoDesembolso)}</ter:montoMinimoDesembolso>
                    <ter:montoMaximoDesembolso>{fn:data($Termino/ter:montoMaximoDesembolso)}</ter:montoMaximoDesembolso>
                    <ter:tasaMinimaDesembolso>{fn:data($Termino/ter:tasaMinimaDesembolso)}</ter:tasaMinimaDesembolso>
                    <ter:tasaMaximaDesembolso>{fn:data($Termino/ter:tasaMaximaDesembolso)}</ter:tasaMaximaDesembolso>
                    <ter:estadoTCC>
                        <atr:id>{fn:data($Termino/ter:estadoTCC/atr:id)}</atr:id>
                    </ter:estadoTCC>
                    <ter:subEstadoTCC>
                        <atr:id>{fn:data($Termino/ter:subEstadoTCC/atr:id)}</atr:id>
                    </ter:subEstadoTCC>
                    <ter:fechaRegistro>{fn:data($Termino/ter:fechaRegistro)}</ter:fechaRegistro>
                    <ter:estado>{fn:data($Termino/ter:estado)}</ter:estado>
                    <ter:terminoEnmendado>{fn:data($Termino/ter:terminoEnmendado)}</ter:terminoEnmendado>
                    <ter:fechaEnmienda>{fn:data($Termino/ter:fechaEnmienda)}</ter:fechaEnmienda>
                </lin1:Termino>
            }
              {
                for $Comision in $outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Comision
                return 
                <lin1:Comision>
                    <com:idComision>{fn:data($Comision/com:idComision)}</com:idComision>
                    <com:idOperacion>{fn:data($Comision/com:idOperacion)}</com:idOperacion>
                    <com:nombre>{fn:data($Comision/com:nombre)}</com:nombre>
                    <com:descripcion>{fn:data($Comision/com:descripcion)}</com:descripcion>
                    <com:tipoComision>
                            <com:idCatComision>{fn:data($Comision/com:tipoComision/com:idCatComision)}</com:idCatComision>
                            <com:idTipoComision>
                                       <cat:Id>{fn:data($Comision/com:tipoComision/com:idTipoComision/cat:Id)}</cat:Id>
                            </com:idTipoComision>
                    </com:tipoComision>
                    <com:moneda>
                           <cat:Id>{fn:data($Comision/com:moneda/cat:Id)}</cat:Id>
                    </com:moneda>
                    <com:montoComision>{fn:data($Comision/com:montoComision)}</com:montoComision>
                    <com:montoBase>
                           <com:idMontoBase>{fn:data($Comision/com:montoBase/com:idMontoBase)}</com:idMontoBase>
                           <com:valorMontoBase>{fn:data($Comision/com:montoBase/com:valorMontoBase)}</com:valorMontoBase>
                           <com:porcentajeMontoBase>{fn:data($Comision/com:montoBase/com:porcentajeMontoBase)}</com:porcentajeMontoBase>
                    </com:montoBase>
                    <com:fechaValor>{fn:data($Comision/com:fechaValor)}</com:fechaValor>
                    <com:fechaVencimiento>{fn:data($Comision/com:fechaVencimiento)}</com:fechaVencimiento>
                    <com:fechaInicioCapital>{fn:data($Comision/com:fechaInicioCapital)}</com:fechaInicioCapital>
                    <com:fechaInicioComision>{fn:data($Comision/com:fechaInicioComision)}</com:fechaInicioComision>
                    <com:tipoFrecuencia>
                                <cat:Id>{fn:data($Comision/com:tipoFrecuencia/cat:Id)}</cat:Id>
                    </com:tipoFrecuencia>
                    <com:frecuenciaPago>{fn:data($Comision/com:frecuenciaPago)}</com:frecuenciaPago>
                    <com:fondo>{fn:data($Comision/com:fondo)}</com:fondo>
                    <com:comisionCompartida>{fn:data($Comision/com:comisionCompartida)}</com:comisionCompartida>
                    <com:codigoDesembolso>{fn:data($Comision/com:codigoDesembolso)}</com:codigoDesembolso>
                    <com:numeroTesoreria>{fn:data($Comision/com:numeroTesoreria)}</com:numeroTesoreria>
                    <com:codigoContrato>{fn:data($Comision/com:codigoContrato)}</com:codigoContrato>
                     <com:momentoCobro>
                              <cat:Id>{fn:data($Comision/com:momentoCobro/cat:Id)}</cat:Id>
                    </com:momentoCobro>
                    <com:tipoTasa>
                              <cat:Id>{fn:data($Comision/com:tipoTasa/cat:Id)}</cat:Id>
                    </com:tipoTasa>
                    <com:baseCalculo>
                               <cat:Id>{fn:data($Comision/com:baseCalculo/cat:Id)}</cat:Id>
                    </com:baseCalculo>
                    <com:responsableComision>{fn:data($Comision/com:responsableComision)}</com:responsableComision>
                    <com:estadoTCC>
                                <atr:id>{fn:data($Comision/com:estadoTCC/atr:id)}</atr:id>
                    </com:estadoTCC>
                    <com:subEstadoTCC>
                                <atr:id>{fn:data($Comision/com:subEstadoTCC/atr:id)}</atr:id>
                    </com:subEstadoTCC>
                    <com:fechaRegistro>{fn:data($Comision/com:fechaRegistro)}</com:fechaRegistro>
                    <com:estado>{fn:data($Comision/com:estado)}</com:estado>
                    <com:comisionEnmendada>{fn:data($Comision/com:comisionEnmendada)}</com:comisionEnmendada>
                    <com:fechaEnmienda>{fn:data($Comision/com:fechaEnmienda)}</com:fechaEnmienda>
                    <com:banSugerida>{fn:data($Comision/com:banSugerida)}</com:banSugerida>
                    <com:numeroCuentaCliente>{fn:data($Comision/com:numeroCuentaCliente)}</com:numeroCuentaCliente>
                    <com:observaciones>{fn:data($Comision/com:observaciones)}</com:observaciones>
                </lin1:Comision>
            }
            {
                for $Fuente in $outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Fuente
                return 
                <lin1:Fuente>
                    <lin1:idFuente>{fn:data($Fuente/lin1:idFuente)}</lin1:idFuente>
                    <lin1:idLineaCredito>{fn:data($Fuente/lin1:idLineaCredito)}</lin1:idLineaCredito>
                    <lin1:idLineaPasiva>{fn:data($Fuente/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                    <lin1:codigoLineaPasiva>{fn:data($Fuente/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                    <lin1:Descripcion>{fn:data($Fuente/lin1:Descripcion)}</lin1:Descripcion>
                    <lin1:FechaObtenido>{fn:data($Fuente/lin1:FechaObtenido)}</lin1:FechaObtenido>
                    <lin1:MontoAsignado>{fn:data($Fuente/lin1:MontoAsignado)}</lin1:MontoAsignado>
                    <lin1:NumeroAsignacion>{fn:data($Fuente/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
                    <lin1:Comentario>{fn:data($Fuente/lin1:Comentario)}</lin1:Comentario>
                    <lin1:idContrato>{fn:data($Fuente/lin1:idContrato)}</lin1:idContrato>
                    <lin1:FechaRegistro>{fn:data($Fuente/lin1:FechaRegistro)}</lin1:FechaRegistro>
                    <lin1:Estado>{fn:data($Fuente/lin1:Estado)}</lin1:Estado>
                </lin1:Fuente>
            }
            {
                for $atributos in $outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:atributos
                return 
                <lin1:atributos>
                    <com1:name>{fn:data($atributos/com1:name)}</com1:name>
                    <com1:value>{fn:data($atributos/com1:value)}</com1:value>
                </lin1:atributos>
            }
        </lin:LineaCredito>
    </lin:ConsultarLineaCreditoBPELResponse>
};

local:funcConsultarcondicion_to_consultarlineacredito2($outputVariable.ConsultarLineaCreditoResponse, $outInvoke_ConsultarCondicionByLineaCredito.ConsultarCondicionByIdLineaCreditoResponse)
