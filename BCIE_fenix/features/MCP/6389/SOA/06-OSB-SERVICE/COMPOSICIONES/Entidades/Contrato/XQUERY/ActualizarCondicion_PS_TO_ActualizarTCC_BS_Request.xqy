xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ActualizarTCCRequest as element() (:: schema-element(ns1:ActualizarTCCRequest) ::) external;

declare function local:func($ActualizarTCCRequest as element() (:: schema-element(ns1:ActualizarTCCRequest) ::)) as element() (:: schema-element(ns1:ActualizarTCCRequest) ::) {
    <ns1:ActualizarTCCRequest>
        {
            for $Tipo in $ActualizarTCCRequest/ns1:Tipo
            return 
            <ns1:Tipo>{fn:data($ActualizarTCCRequest/ns1:Tipo)}</ns1:Tipo>
        }
        {
            if ($ActualizarTCCRequest/ns1:idProceso)
            then <ns1:idProceso>{fn:data($ActualizarTCCRequest/ns1:idProceso)}</ns1:idProceso>
            else ()
        }
        {
            for $Condicion in $ActualizarTCCRequest/ns1:Condicion
            return 
            <ns1:Condicion>
                <con:idCondicion>{fn:data($Condicion/con:idCondicion)}</con:idCondicion>
                <con:operacion>{fn:data($Condicion/con:operacion)}</con:operacion>
                {
                    if ($Condicion/con:nombre)
                    then <con:nombre>{fn:data($Condicion/con:nombre)}</con:nombre>
                    else ()
                }
                {
                    if ($Condicion/con:descripcion)
                    then <con:descripcion>{fn:data($Condicion/con:descripcion)}</con:descripcion>
                    else ()
                }
                {
                    if ($Condicion/con:tipoCondicion)
                    then 
                        <con:tipoCondicion>
                            {
                                if ($Condicion/con:tipoCondicion/con:idCatCondicion)
                                then <con:idCatCondicion>{fn:data($Condicion/con:tipoCondicion/con:idCatCondicion)}</con:idCatCondicion>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:descripcion)
                                then <con:descripcion>{fn:data($Condicion/con:tipoCondicion/con:descripcion)}</con:descripcion>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:descripcionCorta)
                                then <con:descripcionCorta>{fn:data($Condicion/con:tipoCondicion/con:descripcionCorta)}</con:descripcionCorta>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:idTipoCondicion)
                                then <con:idTipoCondicion>{fn:data($Condicion/con:tipoCondicion/con:idTipoCondicion)}</con:idTipoCondicion>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:esEditableEnFormalizacion)
                                then <con:esEditableEnFormalizacion>{fn:data($Condicion/con:tipoCondicion/con:esEditableEnFormalizacion)}</con:esEditableEnFormalizacion>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:requiereValidarCOFI)
                                then <con:requiereValidarCOFI>{fn:data($Condicion/con:tipoCondicion/con:requiereValidarCOFI)}</con:requiereValidarCOFI>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:sePuedeDispensar)
                                then <con:sePuedeDispensar>{fn:data($Condicion/con:tipoCondicion/con:sePuedeDispensar)}</con:sePuedeDispensar>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:esPlantilla)
                                then <con:esPlantilla>{fn:data($Condicion/con:tipoCondicion/con:esPlantilla)}</con:esPlantilla>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:idCondicionPrecarga)
                                then <con:idCondicionPrecarga>{fn:data($Condicion/con:tipoCondicion/con:idCondicionPrecarga)}</con:idCondicionPrecarga>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:fechaRegistro)
                                then <con:fechaRegistro>{fn:data($Condicion/con:tipoCondicion/con:fechaRegistro)}</con:fechaRegistro>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:estado)
                                then <con:estado>{fn:data($Condicion/con:tipoCondicion/con:estado)}</con:estado>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoCondicion/con:codigoExterno)
                                then <con:codigoExterno>{fn:data($Condicion/con:tipoCondicion/con:codigoExterno)}</con:codigoExterno>
                                else ()
                            }
                        </con:tipoCondicion>
                    else ()
                }
                {
                    for $categoriaCondicion in $Condicion/con:categoriaCondicion
                    return 
                    <con:categoriaCondicion>
                        <con:id>{fn:data($categoriaCondicion/con:id)}</con:id>
                        <con:descripcion>{fn:data($categoriaCondicion/con:descripcion)}</con:descripcion>
                        {
                            if ($categoriaCondicion/con:descripcionCorta)
                            then <con:descripcionCorta>{fn:data($categoriaCondicion/con:descripcionCorta)}</con:descripcionCorta>
                            else ()
                        }
                        {
                            if ($categoriaCondicion/con:estatus)
                            then <con:estatus>{fn:data($categoriaCondicion/con:estatus)}</con:estatus>
                            else ()
                        }
                        {
                            if ($categoriaCondicion/con:codigoExterno)
                            then <con:codigoExterno>{fn:data($categoriaCondicion/con:codigoExterno)}</con:codigoExterno>
                            else ()
                        }
                        {
                            for $validadores in $categoriaCondicion/con:validadores
                            return 
                            <con:validadores>
                                {
                                    if ($validadores/cat:Id)
                                    then <cat:Id>{fn:data($validadores/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($validadores/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($validadores/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($validadores/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($validadores/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($validadores/cat:estatus)
                                    then <cat:estatus>{fn:data($validadores/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($validadores/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($validadores/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </con:validadores>
                        }
                    </con:categoriaCondicion>
                }
                {
                    if ($Condicion/con:controlCondicion)
                    then 
                        <con:controlCondicion>
                            {
                                if ($Condicion/con:controlCondicion/cat:Id)
                                then <cat:Id>{fn:data($Condicion/con:controlCondicion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Condicion/con:controlCondicion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Condicion/con:controlCondicion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Condicion/con:controlCondicion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Condicion/con:controlCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Condicion/con:controlCondicion/cat:estatus)
                                then <cat:estatus>{fn:data($Condicion/con:controlCondicion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Condicion/con:controlCondicion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Condicion/con:controlCondicion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </con:controlCondicion>
                    else ()
                }
                {
                    for $eventoCondicion in $Condicion/con:eventoCondicion
                    return 
                    <con:eventoCondicion>
                        {
                            if ($eventoCondicion/cat:Id)
                            then <cat:Id>{fn:data($eventoCondicion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($eventoCondicion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($eventoCondicion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($eventoCondicion/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($eventoCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($eventoCondicion/cat:estatus)
                            then <cat:estatus>{fn:data($eventoCondicion/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($eventoCondicion/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($eventoCondicion/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </con:eventoCondicion>
                }
                {
                    if ($Condicion/con:tipoFechaInicio)
                    then 
                        <con:tipoFechaInicio>
                            {
                                if ($Condicion/con:tipoFechaInicio/cat:Id)
                                then <cat:Id>{fn:data($Condicion/con:tipoFechaInicio/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoFechaInicio/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Condicion/con:tipoFechaInicio/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoFechaInicio/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Condicion/con:tipoFechaInicio/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoFechaInicio/cat:estatus)
                                then <cat:estatus>{fn:data($Condicion/con:tipoFechaInicio/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Condicion/con:tipoFechaInicio/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Condicion/con:tipoFechaInicio/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </con:tipoFechaInicio>
                    else ()
                }
                {
                    if ($Condicion/con:fechaInicio)
                    then <con:fechaInicio>{fn:data($Condicion/con:fechaInicio)}</con:fechaInicio>
                    else ()
                }
                {
                    if ($Condicion/con:plazo)
                    then <con:plazo>{fn:data($Condicion/con:plazo)}</con:plazo>
                    else ()
                }
                {
                    if ($Condicion/con:frecuenciaPlazo)
                    then 
                        <con:frecuenciaPlazo>
                            {
                                if ($Condicion/con:frecuenciaPlazo/cat:Id)
                                then <cat:Id>{fn:data($Condicion/con:frecuenciaPlazo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Condicion/con:frecuenciaPlazo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Condicion/con:frecuenciaPlazo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Condicion/con:frecuenciaPlazo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Condicion/con:frecuenciaPlazo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Condicion/con:frecuenciaPlazo/cat:estatus)
                                then <cat:estatus>{fn:data($Condicion/con:frecuenciaPlazo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Condicion/con:frecuenciaPlazo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Condicion/con:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </con:frecuenciaPlazo>
                    else ()
                }
                {
                    if ($Condicion/con:fechaFinal)
                    then <con:fechaFinal>{fn:data($Condicion/con:fechaFinal)}</con:fechaFinal>
                    else ()
                }
                {
                    if ($Condicion/con:estadoTCC)
                    then 
                        <con:estadoTCC>
                            {
                                if ($Condicion/con:estadoTCC/atr:id)
                                then <atr:id>{fn:data($Condicion/con:estadoTCC/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($Condicion/con:estadoTCC/atr:descripcion)
                                then <atr:descripcion>{fn:data($Condicion/con:estadoTCC/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($Condicion/con:estadoTCC/atr:descripcionCorta)
                                then <atr:descripcionCorta>{fn:data($Condicion/con:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                else ()
                            }
                            {
                                if ($Condicion/con:estadoTCC/atr:estatus)
                                then <atr:estatus>{fn:data($Condicion/con:estadoTCC/atr:estatus)}</atr:estatus>
                                else ()
                            }
                            {
                                if ($Condicion/con:estadoTCC/atr:codigoExterno)
                                then <atr:codigoExterno>{fn:data($Condicion/con:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                else ()
                            }
                            {
                                if ($Condicion/con:estadoTCC/atr:esSubEstado)
                                then <atr:esSubEstado>{fn:data($Condicion/con:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                else ()
                            }
                        </con:estadoTCC>
                    else ()
                }
                {
                    if ($Condicion/con:subEstadoTCC)
                    then 
                        <con:subEstadoTCC>
                            {
                                if ($Condicion/con:subEstadoTCC/atr:id)
                                then <atr:id>{fn:data($Condicion/con:subEstadoTCC/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($Condicion/con:subEstadoTCC/atr:descripcion)
                                then <atr:descripcion>{fn:data($Condicion/con:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($Condicion/con:subEstadoTCC/atr:descripcionCorta)
                                then <atr:descripcionCorta>{fn:data($Condicion/con:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                else ()
                            }
                            {
                                if ($Condicion/con:subEstadoTCC/atr:estatus)
                                then <atr:estatus>{fn:data($Condicion/con:subEstadoTCC/atr:estatus)}</atr:estatus>
                                else ()
                            }
                            {
                                if ($Condicion/con:subEstadoTCC/atr:codigoExterno)
                                then <atr:codigoExterno>{fn:data($Condicion/con:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                else ()
                            }
                            {
                                if ($Condicion/con:subEstadoTCC/atr:esSubEstado)
                                then <atr:esSubEstado>{fn:data($Condicion/con:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                else ()
                            }
                        </con:subEstadoTCC>
                    else ()
                }
                {
                    if ($Condicion/con:fechaRegistro)
                    then <con:fechaRegistro>{fn:data($Condicion/con:fechaRegistro)}</con:fechaRegistro>
                    else ()
                }
                {
                    if ($Condicion/con:estado)
                    then <con:estado>{fn:data($Condicion/con:estado)}</con:estado>
                    else ()
                }
                {
                    if ($Condicion/con:condicionEnmendada)
                    then <con:condicionEnmendada>{fn:data($Condicion/con:condicionEnmendada)}</con:condicionEnmendada>
                    else ()
                }
                {
                    if ($Condicion/con:fechaEnmienda)
                    then <con:fechaEnmienda>{fn:data($Condicion/con:fechaEnmienda)}</con:fechaEnmienda>
                    else ()
                }
                {
                    for $configAtributo in $Condicion/con:configAtributo
                    return 
                    <con:configAtributo>
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
                    </con:configAtributo>
                }
                {
                    if ($Condicion/con:evidencia)
                    then 
                        <con:evidencia>
                            {
                                for $Documento in $Condicion/con:evidencia/doc:Documento
                                return 
                                <doc:Documento>
                                    {
                                        if ($Documento/doc:idDocumento)
                                        then <doc:idDocumento>{fn:data($Documento/doc:idDocumento)}</doc:idDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idTipoDocumento)
                                        then <doc:idTipoDocumento>{fn:data($Documento/doc:idTipoDocumento)}</doc:idTipoDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idTipoDocumentoExterno)
                                        then <doc:idTipoDocumentoExterno>{fn:data($Documento/doc:idTipoDocumentoExterno)}</doc:idTipoDocumentoExterno>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:nombreTipoDocumento)
                                        then <doc:nombreTipoDocumento>{fn:data($Documento/doc:nombreTipoDocumento)}</doc:nombreTipoDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:etapa)
                                        then <doc:etapa>{fn:data($Documento/doc:etapa)}</doc:etapa>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:codigoDocumento)
                                        then <doc:codigoDocumento>{fn:data($Documento/doc:codigoDocumento)}</doc:codigoDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idExterno)
                                        then <doc:idExterno>{fn:data($Documento/doc:idExterno)}</doc:idExterno>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idDocAreaTipo)
                                        then <doc:idDocAreaTipo>{fn:data($Documento/doc:idDocAreaTipo)}</doc:idDocAreaTipo>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idOperacion)
                                        then <doc:idOperacion>{fn:data($Documento/doc:idOperacion)}</doc:idOperacion>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idProducto)
                                        then <doc:idProducto>{fn:data($Documento/doc:idProducto)}</doc:idProducto>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idPais)
                                        then <doc:idPais>{fn:data($Documento/doc:idPais)}</doc:idPais>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:nombre)
                                        then <doc:nombre>{fn:data($Documento/doc:nombre)}</doc:nombre>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:filename)
                                        then <doc:filename>{fn:data($Documento/doc:filename)}</doc:filename>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:mime_type)
                                        then <doc:mime_type>{fn:data($Documento/doc:mime_type)}</doc:mime_type>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:tamanio)
                                        then <doc:tamanio>{fn:data($Documento/doc:tamanio)}</doc:tamanio>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idAdjunto)
                                        then <doc:idAdjunto>{fn:data($Documento/doc:idAdjunto)}</doc:idAdjunto>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:esJustificacion)
                                        then <doc:esJustificacion>{fn:data($Documento/doc:esJustificacion)}</doc:esJustificacion>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:comentario)
                                        then <doc:comentario>{fn:data($Documento/doc:comentario)}</doc:comentario>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:fechaDocumento)
                                        then <doc:fechaDocumento>{fn:data($Documento/doc:fechaDocumento)}</doc:fechaDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:fechaRegistro)
                                        then <doc:fechaRegistro>{fn:data($Documento/doc:fechaRegistro)}</doc:fechaRegistro>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:status)
                                        then <doc:status>{fn:data($Documento/doc:status)}</doc:status>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:estatusTipoDoc)
                                        then <doc:estatusTipoDoc>{fn:data($Documento/doc:estatusTipoDoc)}</doc:estatusTipoDoc>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:codExtTipoDoc)
                                        then <doc:codExtTipoDoc>{fn:data($Documento/doc:codExtTipoDoc)}</doc:codExtTipoDoc>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:tarea)
                                        then <doc:tarea>{fn:data($Documento/doc:tarea)}</doc:tarea>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idtarea)
                                        then <doc:idtarea>{fn:data($Documento/doc:idtarea)}</doc:idtarea>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:content)
                                        then <doc:content>{fn:data($Documento/doc:content)}</doc:content>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:PuedeModificar)
                                        then <doc:PuedeModificar>{fn:data($Documento/doc:PuedeModificar)}</doc:PuedeModificar>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:PuedeBorrar)
                                        then <doc:PuedeBorrar>{fn:data($Documento/doc:PuedeBorrar)}</doc:PuedeBorrar>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:usuarioAgrega)
                                        then <doc:usuarioAgrega>{fn:data($Documento/doc:usuarioAgrega)}</doc:usuarioAgrega>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:usuarioUltimaActualizacion)
                                        then <doc:usuarioUltimaActualizacion>{fn:data($Documento/doc:usuarioUltimaActualizacion)}</doc:usuarioUltimaActualizacion>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:accionARealizar)
                                        then <doc:accionARealizar>{fn:data($Documento/doc:accionARealizar)}</doc:accionARealizar>
                                        else ()
                                    }
                                </doc:Documento>
                            }
                        </con:evidencia>
                    else ()
                }
                {
                    for $observaciones in $Condicion/con:observaciones
                    return 
                    <con:observaciones>
                        <con:id>{fn:data($observaciones/con:id)}</con:id>
                        {
                            if ($observaciones/con:observacion)
                            then <con:observacion>{fn:data($observaciones/con:observacion)}</con:observacion>
                            else ()
                        }
                        {
                            if ($observaciones/con:loginUsuario)
                            then <con:loginUsuario>{fn:data($observaciones/con:loginUsuario)}</con:loginUsuario>
                            else ()
                        }
                        {
                            if ($observaciones/con:nombreUsuario)
                            then <con:nombreUsuario>{fn:data($observaciones/con:nombreUsuario)}</con:nombreUsuario>
                            else ()
                        }
                        {
                            if ($observaciones/con:fechaRegistro)
                            then <con:fechaRegistro>{fn:data($observaciones/con:fechaRegistro)}</con:fechaRegistro>
                            else ()
                        }
                        {
                            if ($observaciones/con:estatus)
                            then <con:estatus>{fn:data($observaciones/con:estatus)}</con:estatus>
                            else ()
                        }
                        {
                            if ($observaciones/con:esPrincipal)
                            then <con:esPrincipal>{fn:data($observaciones/con:esPrincipal)}</con:esPrincipal>
                            else ()
                        }
                        {
                            if ($observaciones/con:tareaBPM)
                            then 
                                <con:tareaBPM>
                                    {
                                        if ($observaciones/con:tareaBPM/cat:Id)
                                        then <cat:Id>{fn:data($observaciones/con:tareaBPM/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($observaciones/con:tareaBPM/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($observaciones/con:tareaBPM/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:estatus)
                                        then <cat:estatus>{fn:data($observaciones/con:tareaBPM/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($observaciones/con:tareaBPM/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </con:tareaBPM>
                            else ()
                        }
                    </con:observaciones>
                }
                {
                    for $lineaCredito in $Condicion/con:lineaCredito
                    return 
                    <con:lineaCredito>
                        {
                            if ($lineaCredito/atr:id)
                            then <atr:id>{fn:data($lineaCredito/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($lineaCredito/atr:descripcion)
                            then <atr:descripcion>{fn:data($lineaCredito/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($lineaCredito/atr:estatus)
                            then <atr:estatus>{fn:data($lineaCredito/atr:estatus)}</atr:estatus>
                            else ()
                        }
                    </con:lineaCredito>
                }
                {
                    for $fuente in $Condicion/con:fuente
                    return 
                    <con:fuente>
                        {
                            if ($fuente/atr:id)
                            then <atr:id>{fn:data($fuente/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($fuente/atr:descripcion)
                            then <atr:descripcion>{fn:data($fuente/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($fuente/atr:estatus)
                            then <atr:estatus>{fn:data($fuente/atr:estatus)}</atr:estatus>
                            else ()
                        }
                    </con:fuente>
                }
                {
                    if ($Condicion/con:Accion)
                    then <con:Accion>{fn:data($Condicion/con:Accion)}</con:Accion>
                    else ()
                }
            </ns1:Condicion>
        }
    </ns1:ActualizarTCCRequest>
};

local:func($ActualizarTCCRequest)
