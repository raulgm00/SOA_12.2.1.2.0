xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ter="http://www.bcie.org/TerminoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/CommonBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter1 = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $outputVariable.ConsultarLineaCreditoResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::) external;
declare variable $outInvoke_ConsultarTermino.response as element() (:: schema-element(ter:ConsultarTerminobyIdLineaCreditoResponse) ::) external;

declare function local:funcConsultartermino_to_consultarlineacredito3($outputVariable.ConsultarLineaCreditoResponse as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::), 
                                                                      $outInvoke_ConsultarTermino.response as element() (:: schema-element(ter:ConsultarTerminobyIdLineaCreditoResponse) ::)) 
                                                                      as element() (:: schema-element(lin:ConsultarLineaCreditoBPELResponse) ::) {
    <lin:ConsultarLineaCreditoBPELResponse>
        <lin:LineaCredito>
            <lin1:idLineaCredito>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
            <lin1:idContrato>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:idContrato)}</lin1:idContrato>
            <lin1:NumeroLineaCredito>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Descripcion>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Descripcion)}</lin1:Descripcion>
            <lin1:Flexcube>
                        <lin1:id>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Flexcube/lin1:id)}</lin1:id>
                {
                    if ($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)
                    then <lin1:idProductoFlexcube>{fn:data($outputVariable.ConsultarLineaCreditoResponse/lin:LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)}</lin1:idProductoFlexcube>
                    else ()
                }
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
            for $termino in $outInvoke_ConsultarTermino.response/ter:termino
            return
            <lin1:Termino>
                <ter1:idTermino>{fn:data($termino/ter1:idTermino)}</ter1:idTermino>
                <ter1:operacion>{fn:data($termino/ter1:operacion)}</ter1:operacion>
                <ter1:nombre>{fn:data($termino/ter1:nombre)}</ter1:nombre>
                <ter1:descripcion>{fn:data($termino/ter1:descripcion)}</ter1:descripcion>
                <ter1:tipoTermino>
                    <ter1:idCatTermino>{fn:data($termino/ter1:tipoTermino/ter1:idCatTermino)}</ter1:idCatTermino>
                    <ter1:descripcion>{fn:data($termino/ter1:tipoTermino/ter1:descripcion)}</ter1:descripcion>
                    <ter1:descripcionCorta>{fn:data($termino/ter1:tipoTermino/ter1:descripcionCorta)}</ter1:descripcionCorta>
                    <ter1:idTipoTermino>{fn:data($termino/ter1:tipoTermino/ter1:idTipoTermino)}</ter1:idTipoTermino>
                    <ter1:esEditableEnFormalizacion>{fn:data($termino/ter1:tipoTermino/ter1:esEditableEnFormalizacion)}</ter1:esEditableEnFormalizacion>
                    <ter1:requiereValidarCOFI>{fn:data($termino/ter1:tipoTermino/ter1:requiereValidarCOFI)}</ter1:requiereValidarCOFI>
                    <ter1:sePuedeDispensar>{fn:data($termino/ter1:tipoTermino/ter1:sePuedeDispensar)}</ter1:sePuedeDispensar>
                    <ter1:esPlantilla>{fn:data($termino/ter1:tipoTermino/ter1:esPlantilla)}</ter1:esPlantilla>
                    <ter1:requiereOGC>{fn:data($termino/ter1:tipoTermino/ter1:requiereOGC)}</ter1:requiereOGC>
                    <ter1:idTerminoPrecarga>{fn:data($termino/ter1:tipoTermino/ter1:idTerminoPrecarga)}</ter1:idTerminoPrecarga>
                    <ter1:fechaRegistro>{fn:data($termino/ter1:tipoTermino/ter1:fechaRegistro)}</ter1:fechaRegistro>
                    <ter1:estado>{fn:data($termino/ter1:tipoTermino/ter1:estado)}</ter1:estado>
                    <ter1:codigoExterno>{fn:data($termino/ter1:tipoTermino/ter1:codigoExterno)}</ter1:codigoExterno>
                </ter1:tipoTermino>
                <ter1:tipoFechaInicio>
                     <cat:Id>{fn:data($termino/ter1:tipoFechaInicio/cat:Id)}</cat:Id>
                </ter1:tipoFechaInicio>
                <ter1:fechaInicio>{fn:data($termino/ter1:fechaInicio)}</ter1:fechaInicio>
                <ter1:plazo>{fn:data($termino/ter1:plazo)}</ter1:plazo>
                <ter1:frecuenciaPlazo>
                     <cat:Id>{fn:data($termino/ter1:frecuenciaPlazo/cat:Id)}</cat:Id>
                </ter1:frecuenciaPlazo>
                <ter1:fechaVencimiento>{fn:data($termino/ter1:fechaVencimiento)}</ter1:fechaVencimiento>
                <ter1:moneda>
                    <cat:Id>{fn:data($termino/ter1:moneda/cat:Id)}</cat:Id>
                </ter1:moneda>
                <ter1:monto>{fn:data($termino/ter1:monto)}</ter1:monto>
                <ter1:tasa>{fn:data($termino/ter1:tasa)}</ter1:tasa>
                <ter1:tipoTasa>
                    <cat:Id>{fn:data($termino/ter1:tipoTasa/cat:Id)}</cat:Id>
                </ter1:tipoTasa>
                <ter1:fecha>{fn:data($termino/ter1:fecha)}</ter1:fecha>
                <ter1:frecuenciaRevision>{fn:data($termino/ter1:frecuenciaRevision)}</ter1:frecuenciaRevision>
                <ter1:tipoFrecuenciaRevision>
                    <cat:Id>{fn:data($termino/ter1:tipoFrecuenciaRevision/cat:Id)}</cat:Id>
                </ter1:tipoFrecuenciaRevision>
                <ter1:fechaInicioRevision>{fn:data($termino/ter1:fechaInicioRevision)}</ter1:fechaInicioRevision>
                <ter1:frecuenciaPagoInteres>{fn:data($termino/ter1:frecuenciaPagoInteres)}</ter1:frecuenciaPagoInteres>
                <ter1:tipoFrecuenciaPagoInteres>
                   <cat:Id>{fn:data($termino/ter1:tipoFrecuenciaPagoInteres/cat:Id)}</cat:Id>
                </ter1:tipoFrecuenciaPagoInteres>
                <ter1:fechaInicioPagoInteres>{fn:data($termino/ter1:fechaInicioPagoInteres)}</ter1:fechaInicioPagoInteres>
                <ter1:frecuenciaAmortizacion>{fn:data($termino/ter1:frecuenciaAmortizacion)}</ter1:frecuenciaAmortizacion>
                <ter1:tipoFrecuenciaAmortizacion>
                    <cat:Id>{fn:data($termino/ter1:tipoFrecuenciaAmortizacion/cat:Id)}</cat:Id>
                </ter1:tipoFrecuenciaAmortizacion>
                <ter1:mora>{fn:data($termino/ter1:mora)}</ter1:mora>
                <ter1:porcentajeCobertura>{fn:data($termino/ter1:porcentajeCobertura)}</ter1:porcentajeCobertura>
                <ter1:seAplicanRecursosConcesion>{fn:data($termino/ter1:seAplicanRecursosConcesion)}</ter1:seAplicanRecursosConcesion>
                <ter1:seAplicanRecursosExternos>{fn:data($termino/ter1:seAplicanRecursosExternos)}</ter1:seAplicanRecursosExternos>
                <ter1:tipoContraparte>{fn:data($termino/ter1:tipoContraparte)}</ter1:tipoContraparte>
                <ter1:montoMinimoDesembolso>{fn:data($termino/ter1:montoMinimoDesembolso)}</ter1:montoMinimoDesembolso>
                <ter1:montoMaximoDesembolso>{fn:data($termino/ter1:montoMaximoDesembolso)}</ter1:montoMaximoDesembolso>
                <ter1:tasaMinimaDesembolso>{fn:data($termino/ter1:tasaMinimaDesembolso)}</ter1:tasaMinimaDesembolso>
                <ter1:tasaMaximaDesembolso>{fn:data($termino/ter1:tasaMaximaDesembolso)}</ter1:tasaMaximaDesembolso>
                <ter1:estadoTCC>
                    <atr:id>{fn:data($termino/ter1:estadoTCC/atr:id)}</atr:id>
                </ter1:estadoTCC>
                <ter1:subEstadoTCC>
                    <atr:id>{fn:data($termino/ter1:subEstadoTCC/atr:id)}</atr:id>
                </ter1:subEstadoTCC>
                <ter1:fechaRegistro>{fn:data($termino/ter1:fechaRegistro)}</ter1:fechaRegistro>
                <ter1:estado>{fn:data($termino/ter1:estado)}</ter1:estado>
                <ter1:terminoEnmendado>{fn:data($termino/ter1:terminoEnmendado)}</ter1:terminoEnmendado>
                <ter1:fechaEnmienda>{fn:data($termino/ter1:fechaEnmienda)}</ter1:fechaEnmienda>
            </lin1:Termino>
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

local:funcConsultartermino_to_consultarlineacredito3($outputVariable.ConsultarLineaCreditoResponse, $outInvoke_ConsultarTermino.response)
