xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace mat="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::) external;
declare variable $outputVariable.response as element() (:: schema-element(mat:ActualizarTCCResponse) ::) external;

declare function local:funcTerminotransformation($inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::), 
                                                 $outputVariable.response as element() (:: schema-element(mat:ActualizarTCCResponse) ::)) 
                                                 as element() (:: schema-element(mat:ActualizarTCCRequest) ::) {
    <mat:ActualizarTCCRequest>
        {
            if ($inputVariable.request/mat:Tipo)
            then <mat:Tipo>{fn:data($inputVariable.request/mat:Tipo)}</mat:Tipo>
            else ()
        }
        {
            if ($inputVariable.request/mat:idProceso)
            then <mat:idProceso>{fn:data($inputVariable.request/mat:idProceso)}</mat:idProceso>
            else ()
        }
        {
            for $Termino in $inputVariable.request/mat:Termino
            let $TerminoCounter := $outputVariable.response/mat:Termino/ter:idTermino
            return 
            <mat:Termino>
                <ter:idTermino>{fn:data($Termino[$TerminoCounter]/ter:idTermino)}</ter:idTermino>                
                <ter:operacion>{fn:data($Termino[$TerminoCounter]/ter:operacion)}</ter:operacion>
                <ter:nombre>{fn:data($Termino[$TerminoCounter]/ter:nombre)}</ter:nombre>
                <ter:descripcion>{fn:data($Termino[$TerminoCounter]/ter:descripcion)}</ter:descripcion>
                <ter:tipoTermino>
                    {
                        if ($Termino[$TerminoCounter]/ter:tipoTermino/ter:idCatTermino)
                        then <ter:idCatTermino>{fn:data($Termino[$TerminoCounter]/ter:tipoTermino/ter:idCatTermino)}</ter:idCatTermino>
                        else ()
                    }                    
                    {
                        if ($Termino[$TerminoCounter]/ter:tipoTermino/ter:idTipoTermino)
                        then <ter:idTipoTermino>{fn:data($Termino[$TerminoCounter]/ter:tipoTermino/ter:idTipoTermino)}</ter:idTipoTermino>
                        else ()
                    }
                </ter:tipoTermino>
                <ter:tipoFechaInicio>
                    {
                        if ($Termino[$TerminoCounter]/ter:tipoFechaInicio/cat:Id)
                        then <cat:Id>{fn:data($Termino[$TerminoCounter]/ter:tipoFechaInicio/cat:Id)}</cat:Id>
                        else ()
                    }
                </ter:tipoFechaInicio>
                <ter:fechaInicio>{fn:data($Termino[$TerminoCounter]/ter:fechaInicio)}</ter:fechaInicio>
                <ter:plazo>{fn:data($Termino[$TerminoCounter]/ter:plazo)}</ter:plazo>
                <ter:frecuenciaPlazo>
                    {
                        if ($Termino[$TerminoCounter]/ter:frecuenciaPlazo/cat:Id)
                        then <cat:Id>{fn:data($Termino[$TerminoCounter]/ter:frecuenciaPlazo/cat:Id)}</cat:Id>
                        else ()
                    }
                </ter:frecuenciaPlazo>
                <ter:fechaVencimiento>{fn:data($Termino[$TerminoCounter]/ter:fechaVencimiento)}</ter:fechaVencimiento>
                <ter:moneda>
                    {
                        if ($Termino[$TerminoCounter]/ter:moneda/cat:Id)
                        then <cat:Id>{fn:data($Termino[$TerminoCounter]/ter:moneda/cat:Id)}</cat:Id>
                        else ()
                    }
                </ter:moneda>
                <ter:monto>{fn:data($Termino[$TerminoCounter]/ter:monto)}</ter:monto>
                <ter:tasa>{fn:data($Termino[$TerminoCounter]/ter:tasa)}</ter:tasa>
                <ter:tipoTasa>
                    {
                        if ($Termino[$TerminoCounter]/ter:tipoTasa/cat:Id)
                        then <cat:Id>{fn:data($Termino[$TerminoCounter]/ter:tipoTasa/cat:Id)}</cat:Id>
                        else ()
                    }
                </ter:tipoTasa>
                <ter:fecha>{fn:data($Termino[$TerminoCounter]/ter:fecha)}</ter:fecha>
                <ter:frecuenciaRevision>{fn:data($Termino[$TerminoCounter]/ter:frecuenciaRevision)}</ter:frecuenciaRevision>
                <ter:tipoFrecuenciaRevision>
                    {
                        if ($Termino[$TerminoCounter]/ter:tipoFrecuenciaRevision/cat:Id)
                        then <cat:Id>{fn:data($Termino[$TerminoCounter]/ter:tipoFrecuenciaRevision/cat:Id)}</cat:Id>
                        else ()
                    }
                </ter:tipoFrecuenciaRevision>
                <ter:fechaInicioRevision>{fn:data($Termino[$TerminoCounter]/ter:fechaInicioRevision)}</ter:fechaInicioRevision>
                <ter:frecuenciaPagoInteres>{fn:data($Termino[$TerminoCounter]/ter:frecuenciaPagoInteres)}</ter:frecuenciaPagoInteres>
                <ter:tipoFrecuenciaPagoInteres>
                    {
                        if ($Termino[$TerminoCounter]/ter:tipoFrecuenciaPagoInteres/cat:Id)
                        then <cat:Id>{fn:data($Termino[$TerminoCounter]/ter:tipoFrecuenciaPagoInteres/cat:Id)}</cat:Id>
                        else ()
                    }
                </ter:tipoFrecuenciaPagoInteres>
                <ter:fechaInicioPagoInteres>{fn:data($Termino[$TerminoCounter]/ter:fechaInicioPagoInteres)}</ter:fechaInicioPagoInteres>
                <ter:frecuenciaAmortizacion>{fn:data($Termino[$TerminoCounter]/ter:frecuenciaAmortizacion)}</ter:frecuenciaAmortizacion>
                <ter:tipoFrecuenciaAmortizacion>
                    {
                        if ($Termino[$TerminoCounter]/ter:tipoFrecuenciaAmortizacion/cat:Id)
                        then <cat:Id>{fn:data($Termino[$TerminoCounter]/ter:tipoFrecuenciaAmortizacion/cat:Id)}</cat:Id>
                        else ()
                    }
                </ter:tipoFrecuenciaAmortizacion>
                <ter:mora>{fn:data($Termino[$TerminoCounter]/ter:mora)}</ter:mora>
                <ter:porcentajeCobertura>{fn:data($Termino[$TerminoCounter]/ter:porcentajeCobertura)}</ter:porcentajeCobertura>
                <ter:seAplicanRecursosConcesion>{fn:data($Termino[$TerminoCounter]/ter:seAplicanRecursosConcesion)}</ter:seAplicanRecursosConcesion>
                <ter:seAplicanRecursosExternos>{fn:data($Termino[$TerminoCounter]/ter:seAplicanRecursosExternos)}</ter:seAplicanRecursosExternos>
                <ter:tipoContraparte>{fn:data($Termino[$TerminoCounter]/ter:tipoContraparte)}</ter:tipoContraparte>
                <ter:montoMinimoDesembolso>{fn:data($Termino[$TerminoCounter]/ter:montoMinimoDesembolso)}</ter:montoMinimoDesembolso>
                <ter:montoMaximoDesembolso>{fn:data($Termino[$TerminoCounter]/ter:montoMaximoDesembolso)}</ter:montoMaximoDesembolso>
                <ter:tasaMinimaDesembolso>{fn:data($Termino[$TerminoCounter]/ter:tasaMinimaDesembolso)}</ter:tasaMinimaDesembolso>
                <ter:tasaMaximaDesembolso>{fn:data($Termino[$TerminoCounter]/ter:tasaMaximaDesembolso)}</ter:tasaMaximaDesembolso>                
                <ter:fechaRegistro>{fn:data($Termino[$TerminoCounter]/ter:fechaRegistro)}</ter:fechaRegistro>
                <ter:estado>{fn:data($Termino[$TerminoCounter]/ter:estado)}</ter:estado>
                <ter:terminoEnmendado>{fn:data($Termino[$TerminoCounter]/ter:terminoEnmendado)}</ter:terminoEnmendado>
                <ter:fechaEnmienda>{fn:data($Termino[$TerminoCounter]/ter:fechaEnmienda)}</ter:fechaEnmienda>               
                {
                    if ($Termino[$TerminoCounter]/ter:Accion)
                    then <ter:Accion>{fn:data($Termino[$TerminoCounter]/ter:Accion)}</ter:Accion>
                    else ()
                }
                {
                    for $Contraparte in $Termino[$TerminoCounter]/ter:Contraparte
                    return 
                    <ter:Contraparte>
                        {
                            if ($Contraparte/atr:id)
                            then <atr:id>{fn:data($Contraparte/atr:id)}</atr:id>
                            else ()
                        }
                    </ter:Contraparte>
                }
            </mat:Termino>
        }
    </mat:ActualizarTCCRequest>
};

local:funcTerminotransformation($inputVariable.request, $outputVariable.response)
