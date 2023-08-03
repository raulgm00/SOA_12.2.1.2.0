xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ActualizarTerminoTCCResponse as element() (:: schema-element(ns1:ActualizarTCCResponse) ::) external;

declare function local:func($ActualizarTerminoTCCResponse as element() (:: schema-element(ns1:ActualizarTCCResponse) ::)) as element() (:: schema-element(ns1:ActualizarTCCResponse) ::) {
    <ns1:ActualizarTCCResponse>
        {
            for $Termino in $ActualizarTerminoTCCResponse/ns1:Termino
            return 
            <ns1:Termino>
                <ter:idTermino>{fn:data($Termino/ter:idTermino)}</ter:idTermino>
                <ter:operacion>{fn:data($Termino/ter:operacion)}</ter:operacion>
                <ter:nombre>{fn:data($Termino/ter:nombre)}</ter:nombre>
                <ter:descripcion>{fn:data($Termino/ter:descripcion)}</ter:descripcion>
                <ter:tipoTermino>
                    {
                        if ($Termino/ter:tipoTermino/ter:idCatTermino)
                        then <ter:idCatTermino>{fn:data($Termino/ter:tipoTermino/ter:idCatTermino)}</ter:idCatTermino>
                        else ()
                    }
                    <ter:descripcion>{fn:data($Termino/ter:tipoTermino/ter:descripcion)}</ter:descripcion>
                    <ter:descripcionCorta>{fn:data($Termino/ter:tipoTermino/ter:descripcionCorta)}</ter:descripcionCorta>
                    {
                        if ($Termino/ter:tipoTermino/ter:idTipoTermino)
                        then <ter:idTipoTermino>{fn:data($Termino/ter:tipoTermino/ter:idTipoTermino)}</ter:idTipoTermino>
                        else ()
                    }
                    <ter:esEditableEnFormalizacion>{fn:data($Termino/ter:tipoTermino/ter:esEditableEnFormalizacion)}</ter:esEditableEnFormalizacion>
                    <ter:requiereValidarCOFI>{fn:data($Termino/ter:tipoTermino/ter:requiereValidarCOFI)}</ter:requiereValidarCOFI>
                    <ter:sePuedeDispensar>{fn:data($Termino/ter:tipoTermino/ter:sePuedeDispensar)}</ter:sePuedeDispensar>
                    <ter:esPlantilla>{fn:data($Termino/ter:tipoTermino/ter:esPlantilla)}</ter:esPlantilla>
                    <ter:requiereOGC>{fn:data($Termino/ter:tipoTermino/ter:requiereOGC)}</ter:requiereOGC>
                    <ter:idTerminoPrecarga>{fn:data($Termino/ter:tipoTermino/ter:idTerminoPrecarga)}</ter:idTerminoPrecarga>
                    <ter:fechaRegistro>{fn:data($Termino/ter:tipoTermino/ter:fechaRegistro)}</ter:fechaRegistro>
                    <ter:estado>{fn:data($Termino/ter:tipoTermino/ter:estado)}</ter:estado>
                    <ter:codigoExterno>{fn:data($Termino/ter:tipoTermino/ter:codigoExterno)}</ter:codigoExterno>
                </ter:tipoTermino>
                <ter:tipoFechaInicio>
                    {
                        if ($Termino/ter:tipoFechaInicio/cat:Id)
                        then <cat:Id>{fn:data($Termino/ter:tipoFechaInicio/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFechaInicio/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($Termino/ter:tipoFechaInicio/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFechaInicio/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFechaInicio/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFechaInicio/cat:estatus)
                        then <cat:estatus>{fn:data($Termino/ter:tipoFechaInicio/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFechaInicio/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($Termino/ter:tipoFechaInicio/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </ter:tipoFechaInicio>
                <ter:fechaInicio>{fn:data($Termino/ter:fechaInicio)}</ter:fechaInicio>
                <ter:plazo>{fn:data($Termino/ter:plazo)}</ter:plazo>
                <ter:frecuenciaPlazo>
                    {
                        if ($Termino/ter:frecuenciaPlazo/cat:Id)
                        then <cat:Id>{fn:data($Termino/ter:frecuenciaPlazo/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($Termino/ter:frecuenciaPlazo/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($Termino/ter:frecuenciaPlazo/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($Termino/ter:frecuenciaPlazo/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($Termino/ter:frecuenciaPlazo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($Termino/ter:frecuenciaPlazo/cat:estatus)
                        then <cat:estatus>{fn:data($Termino/ter:frecuenciaPlazo/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($Termino/ter:frecuenciaPlazo/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($Termino/ter:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </ter:frecuenciaPlazo>
                <ter:fechaVencimiento>{fn:data($Termino/ter:fechaVencimiento)}</ter:fechaVencimiento>
                <ter:moneda>
                    {
                        if ($Termino/ter:moneda/cat:Id)
                        then <cat:Id>{fn:data($Termino/ter:moneda/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($Termino/ter:moneda/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($Termino/ter:moneda/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($Termino/ter:moneda/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($Termino/ter:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($Termino/ter:moneda/cat:estatus)
                        then <cat:estatus>{fn:data($Termino/ter:moneda/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($Termino/ter:moneda/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($Termino/ter:moneda/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </ter:moneda>
                <ter:monto>{fn:data($Termino/ter:monto)}</ter:monto>
                <ter:tasa>{fn:data($Termino/ter:tasa)}</ter:tasa>
                <ter:tipoTasa>
                    {
                        if ($Termino/ter:tipoTasa/cat:Id)
                        then <cat:Id>{fn:data($Termino/ter:tipoTasa/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoTasa/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($Termino/ter:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoTasa/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoTasa/cat:estatus)
                        then <cat:estatus>{fn:data($Termino/ter:tipoTasa/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoTasa/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($Termino/ter:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </ter:tipoTasa>
                <ter:fecha>{fn:data($Termino/ter:fecha)}</ter:fecha>
                <ter:frecuenciaRevision>{fn:data($Termino/ter:frecuenciaRevision)}</ter:frecuenciaRevision>
                <ter:tipoFrecuenciaRevision>
                    {
                        if ($Termino/ter:tipoFrecuenciaRevision/cat:Id)
                        then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaRevision/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaRevision/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaRevision/cat:estatus)
                        then <cat:estatus>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaRevision/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </ter:tipoFrecuenciaRevision>
                <ter:fechaInicioRevision>{fn:data($Termino/ter:fechaInicioRevision)}</ter:fechaInicioRevision>
                <ter:frecuenciaPagoInteres>{fn:data($Termino/ter:frecuenciaPagoInteres)}</ter:frecuenciaPagoInteres>
                <ter:tipoFrecuenciaPagoInteres>
                    {
                        if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)
                        then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:estatus)
                        then <cat:estatus>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </ter:tipoFrecuenciaPagoInteres>
                <ter:fechaInicioPagoInteres>{fn:data($Termino/ter:fechaInicioPagoInteres)}</ter:fechaInicioPagoInteres>
                <ter:frecuenciaAmortizacion>{fn:data($Termino/ter:frecuenciaAmortizacion)}</ter:frecuenciaAmortizacion>
                <ter:tipoFrecuenciaAmortizacion>
                    {
                        if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)
                        then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:estatus)
                        then <cat:estatus>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
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
                    {
                        if ($Termino/ter:estadoTCC/atr:id)
                        then <atr:id>{fn:data($Termino/ter:estadoTCC/atr:id)}</atr:id>
                        else ()
                    }
                    {
                        if ($Termino/ter:estadoTCC/atr:descripcion)
                        then <atr:descripcion>{fn:data($Termino/ter:estadoTCC/atr:descripcion)}</atr:descripcion>
                        else ()
                    }
                    {
                        if ($Termino/ter:estadoTCC/atr:descripcionCorta)
                        then <atr:descripcionCorta>{fn:data($Termino/ter:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                        else ()
                    }
                    {
                        if ($Termino/ter:estadoTCC/atr:estatus)
                        then <atr:estatus>{fn:data($Termino/ter:estadoTCC/atr:estatus)}</atr:estatus>
                        else ()
                    }
                    {
                        if ($Termino/ter:estadoTCC/atr:codigoExterno)
                        then <atr:codigoExterno>{fn:data($Termino/ter:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                        else ()
                    }
                    {
                        if ($Termino/ter:estadoTCC/atr:esSubEstado)
                        then <atr:esSubEstado>{fn:data($Termino/ter:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                        else ()
                    }
                </ter:estadoTCC>
                <ter:subEstadoTCC>
                    {
                        if ($Termino/ter:subEstadoTCC/atr:id)
                        then <atr:id>{fn:data($Termino/ter:subEstadoTCC/atr:id)}</atr:id>
                        else ()
                    }
                    {
                        if ($Termino/ter:subEstadoTCC/atr:descripcion)
                        then <atr:descripcion>{fn:data($Termino/ter:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                        else ()
                    }
                    {
                        if ($Termino/ter:subEstadoTCC/atr:descripcionCorta)
                        then <atr:descripcionCorta>{fn:data($Termino/ter:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                        else ()
                    }
                    {
                        if ($Termino/ter:subEstadoTCC/atr:estatus)
                        then <atr:estatus>{fn:data($Termino/ter:subEstadoTCC/atr:estatus)}</atr:estatus>
                        else ()
                    }
                    {
                        if ($Termino/ter:subEstadoTCC/atr:codigoExterno)
                        then <atr:codigoExterno>{fn:data($Termino/ter:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                        else ()
                    }
                    {
                        if ($Termino/ter:subEstadoTCC/atr:esSubEstado)
                        then <atr:esSubEstado>{fn:data($Termino/ter:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                        else ()
                    }
                </ter:subEstadoTCC>
                <ter:fechaRegistro>{fn:data($Termino/ter:fechaRegistro)}</ter:fechaRegistro>
                <ter:estado>{fn:data($Termino/ter:estado)}</ter:estado>
                <ter:terminoEnmendado>{fn:data($Termino/ter:terminoEnmendado)}</ter:terminoEnmendado>
                <ter:fechaEnmienda>{fn:data($Termino/ter:fechaEnmienda)}</ter:fechaEnmienda>
                {
                    for $configAtributo in $Termino/ter:configAtributo
                    return 
                    <ter:configAtributo>
                        {
                            if ($configAtributo/atr:id)
                            then <atr:id>{fn:data($configAtributo/atr:id)}</atr:id>
                            else ()
                        }
                        <atr:columna>{fn:data($configAtributo/atr:columna)}</atr:columna>
                        {
                            if ($configAtributo/atr:ordenamiento)
                            then <atr:ordenamiento>{fn:data($configAtributo/atr:ordenamiento)}</atr:ordenamiento>
                            else ()
                        }
                        {
                            if ($configAtributo/atr:soloLectura)
                            then <atr:soloLectura>{fn:data($configAtributo/atr:soloLectura)}</atr:soloLectura>
                            else ()
                        }
                        {
                            if ($configAtributo/atr:esObligatorio)
                            then <atr:esObligatorio>{fn:data($configAtributo/atr:esObligatorio)}</atr:esObligatorio>
                            else ()
                        }
                        {
                            if ($configAtributo/atr:etiqueta)
                            then <atr:etiqueta>{fn:data($configAtributo/atr:etiqueta)}</atr:etiqueta>
                            else ()
                        }
                        {
                            for $valor in $configAtributo/atr:valor
                            return 
                            <atr:valor>{fn:data($configAtributo/atr:valor)}</atr:valor>
                        }
                        {
                            if ($configAtributo/atr:tipoValor)
                            then <atr:tipoValor>{fn:data($configAtributo/atr:tipoValor)}</atr:tipoValor>
                            else ()
                        }
                        {
                            for $catalogo in $configAtributo/atr:catalogo
                            return 
                            <atr:catalogo>
                                {
                                    if ($catalogo/cat:Id)
                                    then <cat:Id>{fn:data($catalogo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($catalogo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($catalogo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($catalogo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($catalogo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($catalogo/cat:estatus)
                                    then <cat:estatus>{fn:data($catalogo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($catalogo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($catalogo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </atr:catalogo>
                        }
                    </ter:configAtributo>
                }
            </ns1:Termino>
        }
        {
            for $Resultado in $ActualizarTerminoTCCResponse/ns1:Resultado
            return 
            <ns1:Resultado>
                <res:result>{fn:data($Resultado/res:result)}</res:result>
                <res:message>{fn:data($Resultado/res:message)}</res:message>
                {
                    if ($Resultado/res:error)
                    then 
                        <res:error>
                            <err:errorCode>{fn:data($Resultado/res:error/err:errorCode)}</err:errorCode>
                            <err:errorDescription>{fn:data($Resultado/res:error/err:errorDescription)}</err:errorDescription>
                            <err:errorType>{fn:data($Resultado/res:error/err:errorType)}</err:errorType>
                        </res:error>
                    else ()
                }
            </ns1:Resultado>
        }
    </ns1:ActualizarTCCResponse>
};

local:func($ActualizarTerminoTCCResponse)
