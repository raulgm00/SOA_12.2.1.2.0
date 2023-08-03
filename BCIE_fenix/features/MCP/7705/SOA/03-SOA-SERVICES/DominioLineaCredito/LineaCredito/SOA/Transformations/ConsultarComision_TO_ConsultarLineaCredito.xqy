xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace com="http://www.bcie.org/ComisionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace com2 = "http://www.bcie.org/CommonBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $outputVariable.ConsultarLineaCreditoResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::) external;
declare variable $outInvoke_ConsultarComision.response as element() (:: schema-element(com:ConsultarComisionByIdLineaCreditoResponse) ::) external;

declare function local:funcConsultarcomision_to_consultarlineacredito($outputVariable.ConsultarLineaCreditoResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::), 
                                                                      $outInvoke_ConsultarComision.response as element() (:: schema-element(com:ConsultarComisionByIdLineaCreditoResponse) ::)) 
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
                for $Comision in $outInvoke_ConsultarComision.response/com:Comision
                return 
                <lin1:Comision>
                    <com1:idComision>{fn:data($Comision/com1:idComision)}</com1:idComision>
                    <com1:idOperacion>{fn:data($Comision/com1:idOperacion)}</com1:idOperacion>
                    <com1:nombre>{fn:data($Comision/com1:nombre)}</com1:nombre>
                    <com1:descripcion>{fn:data($Comision/com1:descripcion)}</com1:descripcion>
                    <com1:tipoComision>
                            <com1:idCatComision>{fn:data($Comision/com1:tipoComision/com1:idCatComision)}</com1:idCatComision>
                            <com1:idTipoComision>
                                       <cat:Id>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)}</cat:Id>
                            </com1:idTipoComision>
                    </com1:tipoComision>
                    <com1:moneda>
                           <cat:Id>{fn:data($Comision/com1:moneda/cat:Id)}</cat:Id>
                    </com1:moneda>
                    <com1:montoComision>{fn:data($Comision/com1:montoComision)}</com1:montoComision>
                    <com1:montoBase>
                           <com1:idMontoBase>{fn:data($Comision/com1:montoBase/com1:idMontoBase)}</com1:idMontoBase>
                           <com1:valorMontoBase>{fn:data($Comision/com1:montoBase/com1:valorMontoBase)}</com1:valorMontoBase>
                           <com1:porcentajeMontoBase>{fn:data($Comision/com1:montoBase/com1:porcentajeMontoBase)}</com1:porcentajeMontoBase>
                    </com1:montoBase>
                    <com1:fechaValor>{fn:data($Comision/com1:fechaValor)}</com1:fechaValor>
                    <com1:fechaVencimiento>{fn:data($Comision/com1:fechaVencimiento)}</com1:fechaVencimiento>
                    <com1:fechaInicioCapital>{fn:data($Comision/com1:fechaInicioCapital)}</com1:fechaInicioCapital>
                    <com1:fechaInicioComision>{fn:data($Comision/com1:fechaInicioComision)}</com1:fechaInicioComision>
                    <com1:tipoFrecuencia>
                                <cat:Id>{fn:data($Comision/com1:tipoFrecuencia/cat:Id)}</cat:Id>
                    </com1:tipoFrecuencia>
                    <com1:frecuenciaPago>{fn:data($Comision/com1:frecuenciaPago)}</com1:frecuenciaPago>
                    <com1:fondo>{fn:data($Comision/com1:fondo)}</com1:fondo>
                    <com1:comisionCompartida>{fn:data($Comision/com1:comisionCompartida)}</com1:comisionCompartida>
                    <com1:codigoDesembolso>{fn:data($Comision/com1:codigoDesembolso)}</com1:codigoDesembolso>
                    <com1:numeroTesoreria>{fn:data($Comision/com1:numeroTesoreria)}</com1:numeroTesoreria>
                    <com1:codigoContrato>{fn:data($Comision/com1:codigoContrato)}</com1:codigoContrato>
                     <com1:momentoCobro>
                              <cat:Id>{fn:data($Comision/com1:momentoCobro/cat:Id)}</cat:Id>
                    </com1:momentoCobro>
                    <com1:tipoTasa>
                              <cat:Id>{fn:data($Comision/com1:tipoTasa/cat:Id)}</cat:Id>
                    </com1:tipoTasa>
                    <com1:baseCalculo>
                               <cat:Id>{fn:data($Comision/com1:baseCalculo/cat:Id)}</cat:Id>
                    </com1:baseCalculo>
                    <com1:responsableComision>{fn:data($Comision/com1:responsableComision)}</com1:responsableComision>
                    <com1:estadoTCC>
                                <atr:id>{fn:data($Comision/com1:estadoTCC/atr:id)}</atr:id>
                    </com1:estadoTCC>
                    <com1:subEstadoTCC>
                                <atr:id>{fn:data($Comision/com1:subEstadoTCC/atr:id)}</atr:id>
                    </com1:subEstadoTCC>
                    <com1:fechaRegistro>{fn:data($Comision/com1:fechaRegistro)}</com1:fechaRegistro>
                    <com1:estado>{fn:data($Comision/com1:estado)}</com1:estado>
                    <com1:comisionEnmendada>{fn:data($Comision/com1:comisionEnmendada)}</com1:comisionEnmendada>
                    <com1:fechaEnmienda>{fn:data($Comision/com1:fechaEnmienda)}</com1:fechaEnmienda>
                    <com1:banSugerida>{fn:data($Comision/com1:banSugerida)}</com1:banSugerida>
                    <com1:numeroCuentaCliente>{fn:data($Comision/com1:numeroCuentaCliente)}</com1:numeroCuentaCliente>
                    <com1:observaciones>{fn:data($Comision/com1:observaciones)}</com1:observaciones>
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
        </lin:LineaCredito>
    </lin:ConsultarLineaCreditoBPELResponse>
};

local:funcConsultarcomision_to_consultarlineacredito($outputVariable.ConsultarLineaCreditoResponse, $outInvoke_ConsultarComision.response)
