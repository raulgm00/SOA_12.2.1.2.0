xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare variable $inputVariable.request as element() (:: schema-element(lin:PropagarFuenteRequest) ::) external;
declare variable $outConsultarAsignacionARE.response as element() (:: schema-element(lin:ConsultarAsignacionAREResponse) ::) external;

declare function local:funcConsultarasignacionare_to_propagarfuenteinput($inputVariable.request as element() (:: schema-element(lin:PropagarFuenteRequest) ::), 
                                                                         $outConsultarAsignacionARE.response as element() (:: schema-element(lin:ConsultarAsignacionAREResponse) ::)) 
                                                                         as element() (:: schema-element(lin:PropagarFuenteRequest) ::) {
    <lin:PropagarFuenteRequest>
        <lin:LineaCredito>
            {
                if ($inputVariable.request/lin:LineaCredito/lin1:idLineaCredito)
                then <lin1:idLineaCredito>{fn:data($inputVariable.request/lin:LineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
                else ()
            }
            {
                if ($inputVariable.request/lin:LineaCredito/lin1:idContrato)
                then <lin1:idContrato>{fn:data($inputVariable.request/lin:LineaCredito/lin1:idContrato)}</lin1:idContrato>
                else ()
            }
            <lin1:NumeroLineaCredito>{fn:data($inputVariable.request/lin:LineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Descripcion>{fn:data($inputVariable.request/lin:LineaCredito/lin1:Descripcion)}</lin1:Descripcion>
            {
                if ($inputVariable.request/lin:LineaCredito/lin1:Flexcube)
                then 
                    <lin1:Flexcube>
                        {
                            if ($inputVariable.request/lin:LineaCredito/lin1:Flexcube/lin1:id)
                            then <lin1:id>{fn:data($inputVariable.request/lin:LineaCredito/lin1:Flexcube/lin1:id)}</lin1:id>
                            else ()
                        }
                        {
                            if ($inputVariable.request/lin:LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)
                            then <lin1:idProductoFlexcube>{fn:data($inputVariable.request/lin:LineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)}</lin1:idProductoFlexcube>
                            else ()
                        }
                        {
                            if ($inputVariable.request/lin:LineaCredito/lin1:Flexcube/lin1:idFlexcubePasivo)
                            then <lin1:idFlexcubePasivo>{fn:data($inputVariable.request/lin:LineaCredito/lin1:Flexcube/lin1:idFlexcubePasivo)}</lin1:idFlexcubePasivo>
                            else ()
                        }
                    </lin1:Flexcube>
                else ()
            }
            <lin1:Fondo>{fn:data($inputVariable.request/lin:LineaCredito/lin1:Fondo)}</lin1:Fondo>
            <lin1:MontoLinea>{fn:data($inputVariable.request/lin:LineaCredito/lin1:MontoLinea)}</lin1:MontoLinea>
            {
                if ($inputVariable.request/lin:LineaCredito/lin1:EsRevolvente)
                then <lin1:EsRevolvente>{fn:data($inputVariable.request/lin:LineaCredito/lin1:EsRevolvente)}</lin1:EsRevolvente>
                else ()
            }
            <lin1:TratamientoDiasFeriados>{fn:data($inputVariable.request/lin:LineaCredito/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
            <lin1:FechaValor>{fn:data($inputVariable.request/lin:LineaCredito/lin1:FechaValor)}</lin1:FechaValor>
            <lin1:FechaVencimiento>{fn:data($inputVariable.request/lin:LineaCredito/lin1:FechaVencimiento)}</lin1:FechaVencimiento>
            <lin1:CodigoPantallaLimite>{fn:data($inputVariable.request/lin:LineaCredito/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
            <lin1:CodigoSerialLimite>{fn:data($inputVariable.request/lin:LineaCredito/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
            <lin1:CodigoAsignacion>{fn:data($inputVariable.request/lin:LineaCredito/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
            <lin1:DescripcionAsignacion>{fn:data($inputVariable.request/lin:LineaCredito/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>
            {
                if ($inputVariable.request/lin:LineaCredito/lin1:CondicionEspecial)
                then <lin1:CondicionEspecial>{fn:data($inputVariable.request/lin:LineaCredito/lin1:CondicionEspecial)}</lin1:CondicionEspecial>
                else ()
            }
            <lin1:FechaRegistro>{fn:data($inputVariable.request/lin:LineaCredito/lin1:FechaRegistro)}</lin1:FechaRegistro>
            {
                if ($inputVariable.request/lin:LineaCredito/lin1:Estado)
                then <lin1:Estado>{fn:data($inputVariable.request/lin:LineaCredito/lin1:Estado)}</lin1:Estado>
                else ()
            }
            <lin1:descCondEspecial>{fn:data($inputVariable.request/lin:LineaCredito/lin1:descCondEspecial)}</lin1:descCondEspecial>
            <lin1:frecuenciaGracia>{fn:data($inputVariable.request/lin:LineaCredito/lin1:frecuenciaGracia)}</lin1:frecuenciaGracia>
            <lin1:plazoGracia>{fn:data($inputVariable.request/lin:LineaCredito/lin1:plazoGracia)}</lin1:plazoGracia>
            <lin1:frecuenciaFinanciamiento>{fn:data($inputVariable.request/lin:LineaCredito/lin1:frecuenciaFinanciamiento)}</lin1:frecuenciaFinanciamiento>
            <lin1:plazoFinanciamiento>{fn:data($inputVariable.request/lin:LineaCredito/lin1:plazoFinanciamiento)}</lin1:plazoFinanciamiento>
            <lin1:recursosExternos>{fn:data($inputVariable.request/lin:LineaCredito/lin1:recursosExternos)}</lin1:recursosExternos>
            <lin1:tasaMinima>{fn:data($inputVariable.request/lin:LineaCredito/lin1:tasaMinima)}</lin1:tasaMinima>
            <lin1:tasaMaxima>{fn:data($inputVariable.request/lin:LineaCredito/lin1:tasaMaxima)}</lin1:tasaMaxima>
            <lin1:montoMinimo>{fn:data($inputVariable.request/lin:LineaCredito/lin1:montoMinimo)}</lin1:montoMinimo>
            <lin1:montoMaximo>{fn:data($inputVariable.request/lin:LineaCredito/lin1:montoMaximo)}</lin1:montoMaximo>
            {
                for $Condicion in $inputVariable.request/lin:LineaCredito/lin1:Condicion
                return 
                <lin1:Condicion>
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
                </lin1:Condicion>
            }
            {
                for $Termino in $inputVariable.request/lin:LineaCredito/lin1:Termino
                return 
                <lin1:Termino>
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
                    {
                        if ($Termino/ter:Accion)
                        then <ter:Accion>{fn:data($Termino/ter:Accion)}</ter:Accion>
                        else ()
                    }
                    {
                        for $Contraparte in $Termino/ter:Contraparte
                        return 
                        <ter:Contraparte>
                            {
                                if ($Contraparte/atr:id)
                                then <atr:id>{fn:data($Contraparte/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($Contraparte/atr:descripcion)
                                then <atr:descripcion>{fn:data($Contraparte/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($Contraparte/atr:estatus)
                                then <atr:estatus>{fn:data($Contraparte/atr:estatus)}</atr:estatus>
                                else ()
                            }
                        </ter:Contraparte>
                    }
                </lin1:Termino>
            }
            {
                for $Comision in $inputVariable.request/lin:LineaCredito/lin1:Comision
                return 
                <lin1:Comision>
                    <com:idComision>{fn:data($Comision/com:idComision)}</com:idComision>
                    <com:idOperacion>{fn:data($Comision/com:idOperacion)}</com:idOperacion>
                    {
                        if ($Comision/com:nombre)
                        then <com:nombre>{fn:data($Comision/com:nombre)}</com:nombre>
                        else ()
                    }
                    {
                        if ($Comision/com:descripcion)
                        then <com:descripcion>{fn:data($Comision/com:descripcion)}</com:descripcion>
                        else ()
                    }
                    {
                        if ($Comision/com:tipoComision)
                        then 
                            <com:tipoComision>
                                {
                                    if ($Comision/com:tipoComision/com:idCatComision)
                                    then <com:idCatComision>{fn:data($Comision/com:tipoComision/com:idCatComision)}</com:idCatComision>
                                    else ()
                                }
                                <com:descripcion>{fn:data($Comision/com:tipoComision/com:descripcion)}</com:descripcion>
                                <com:descripcionCorta>{fn:data($Comision/com:tipoComision/com:descripcionCorta)}</com:descripcionCorta>
                                {
                                    if ($Comision/com:tipoComision/com:idTipoComision)
                                    then 
                                        <com:idTipoComision>
                                            {
                                                if ($Comision/com:tipoComision/com:idTipoComision/cat:Id)
                                                then <cat:Id>{fn:data($Comision/com:tipoComision/com:idTipoComision/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com:tipoComision/com:idTipoComision/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($Comision/com:tipoComision/com:idTipoComision/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com:tipoComision/com:idTipoComision/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($Comision/com:tipoComision/com:idTipoComision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com:tipoComision/com:idTipoComision/cat:estatus)
                                                then <cat:estatus>{fn:data($Comision/com:tipoComision/com:idTipoComision/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com:tipoComision/com:idTipoComision/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($Comision/com:tipoComision/com:idTipoComision/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com:idTipoComision>
                                    else ()
                                }
                                <com:esEditableEnFormalizacion>{fn:data($Comision/com:tipoComision/com:esEditableEnFormalizacion)}</com:esEditableEnFormalizacion>
                                <com:requiereValidarCOFI>{fn:data($Comision/com:tipoComision/com:requiereValidarCOFI)}</com:requiereValidarCOFI>
                                <com:sePuedeDispensar>{fn:data($Comision/com:tipoComision/com:sePuedeDispensar)}</com:sePuedeDispensar>
                                <com:seRegistraEnFlexCube>{fn:data($Comision/com:tipoComision/com:seRegistraEnFlexCube)}</com:seRegistraEnFlexCube>
                                <com:esPlantilla>{fn:data($Comision/com:tipoComision/com:esPlantilla)}</com:esPlantilla>
                                <com:idComisionPrecarga>{fn:data($Comision/com:tipoComision/com:idComisionPrecarga)}</com:idComisionPrecarga>
                                <com:fechaRegistro>{fn:data($Comision/com:tipoComision/com:fechaRegistro)}</com:fechaRegistro>
                                <com:estado>{fn:data($Comision/com:tipoComision/com:estado)}</com:estado>
                                <com:codigoExterno>{fn:data($Comision/com:tipoComision/com:codigoExterno)}</com:codigoExterno>
                            </com:tipoComision>
                        else ()
                    }
                    {
                        if ($Comision/com:moneda)
                        then 
                            <com:moneda>
                                {
                                    if ($Comision/com:moneda/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com:moneda/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com:moneda/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com:moneda/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com:moneda/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com:moneda/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com:moneda/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:moneda>
                        else ()
                    }
                    {
                        if ($Comision/com:montoComision)
                        then <com:montoComision>{fn:data($Comision/com:montoComision)}</com:montoComision>
                        else ()
                    }
                    {
                        if ($Comision/com:montoBase)
                        then 
                            <com:montoBase>
                                {
                                    if ($Comision/com:montoBase/com:idMontoBase)
                                    then <com:idMontoBase>{fn:data($Comision/com:montoBase/com:idMontoBase)}</com:idMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com:montoBase/com:valorMontoBase)
                                    then <com:valorMontoBase>{fn:data($Comision/com:montoBase/com:valorMontoBase)}</com:valorMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com:montoBase/com:porcentajeMontoBase)
                                    then <com:porcentajeMontoBase>{fn:data($Comision/com:montoBase/com:porcentajeMontoBase)}</com:porcentajeMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com:montoBase/com:descripcionMontoBase)
                                    then <com:descripcionMontoBase>{fn:data($Comision/com:montoBase/com:descripcionMontoBase)}</com:descripcionMontoBase>
                                    else ()
                                }
                            </com:montoBase>
                        else ()
                    }
                    {
                        if ($Comision/com:fechaValor)
                        then <com:fechaValor>{fn:data($Comision/com:fechaValor)}</com:fechaValor>
                        else ()
                    }
                    {
                        if ($Comision/com:fechaVencimiento)
                        then <com:fechaVencimiento>{fn:data($Comision/com:fechaVencimiento)}</com:fechaVencimiento>
                        else ()
                    }
                    {
                        if ($Comision/com:fechaInicioCapital)
                        then <com:fechaInicioCapital>{fn:data($Comision/com:fechaInicioCapital)}</com:fechaInicioCapital>
                        else ()
                    }
                    {
                        if ($Comision/com:fechaInicioComision)
                        then <com:fechaInicioComision>{fn:data($Comision/com:fechaInicioComision)}</com:fechaInicioComision>
                        else ()
                    }
                    {
                        if ($Comision/com:tipoFrecuencia)
                        then 
                            <com:tipoFrecuencia>
                                {
                                    if ($Comision/com:tipoFrecuencia/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com:tipoFrecuencia/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com:tipoFrecuencia/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com:tipoFrecuencia/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com:tipoFrecuencia/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com:tipoFrecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com:tipoFrecuencia/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com:tipoFrecuencia/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com:tipoFrecuencia/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com:tipoFrecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:tipoFrecuencia>
                        else ()
                    }
                    {
                        if ($Comision/com:frecuenciaPago)
                        then <com:frecuenciaPago>{fn:data($Comision/com:frecuenciaPago)}</com:frecuenciaPago>
                        else ()
                    }
                    {
                        if ($Comision/com:fondo)
                        then <com:fondo>{fn:data($Comision/com:fondo)}</com:fondo>
                        else ()
                    }
                    {
                        if ($Comision/com:comisionCompartida)
                        then <com:comisionCompartida>{fn:data($Comision/com:comisionCompartida)}</com:comisionCompartida>
                        else ()
                    }
                    {
                        if ($Comision/com:codigoDesembolso)
                        then <com:codigoDesembolso>{fn:data($Comision/com:codigoDesembolso)}</com:codigoDesembolso>
                        else ()
                    }
                    {
                        if ($Comision/com:numeroTesoreria)
                        then <com:numeroTesoreria>{fn:data($Comision/com:numeroTesoreria)}</com:numeroTesoreria>
                        else ()
                    }
                    {
                        if ($Comision/com:codigoContrato)
                        then <com:codigoContrato>{fn:data($Comision/com:codigoContrato)}</com:codigoContrato>
                        else ()
                    }
                    {
                        if ($Comision/com:momentoCobro)
                        then 
                            <com:momentoCobro>
                                {
                                    if ($Comision/com:momentoCobro/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com:momentoCobro/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com:momentoCobro/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com:momentoCobro/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com:momentoCobro/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com:momentoCobro/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com:momentoCobro/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com:momentoCobro/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com:momentoCobro/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com:momentoCobro/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:momentoCobro>
                        else ()
                    }
                    {
                        if ($Comision/com:tipoTasa)
                        then 
                            <com:tipoTasa>
                                {
                                    if ($Comision/com:tipoTasa/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com:tipoTasa/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com:tipoTasa/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com:tipoTasa/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com:tipoTasa/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com:tipoTasa/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com:tipoTasa/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:tipoTasa>
                        else ()
                    }
                    {
                        if ($Comision/com:baseCalculo)
                        then 
                            <com:baseCalculo>
                                {
                                    if ($Comision/com:baseCalculo/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com:baseCalculo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com:baseCalculo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com:baseCalculo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com:baseCalculo/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com:baseCalculo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com:baseCalculo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:baseCalculo>
                        else ()
                    }
                    {
                        if ($Comision/com:responsableComision)
                        then <com:responsableComision>{fn:data($Comision/com:responsableComision)}</com:responsableComision>
                        else ()
                    }
                    <com:estadoTCC>
                        {
                            if ($Comision/com:estadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision/com:estadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision/com:estadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision/com:estadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com:estadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision/com:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com:estadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision/com:estadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com:estadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision/com:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision/com:estadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision/com:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com:estadoTCC>
                    <com:subEstadoTCC>
                        {
                            if ($Comision/com:subEstadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision/com:subEstadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision/com:subEstadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision/com:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com:subEstadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision/com:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com:subEstadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision/com:subEstadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com:subEstadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision/com:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision/com:subEstadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision/com:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com:subEstadoTCC>
                    <com:fechaRegistro>{fn:data($Comision/com:fechaRegistro)}</com:fechaRegistro>
                    <com:estado>{fn:data($Comision/com:estado)}</com:estado>
                    <com:comisionEnmendada>{fn:data($Comision/com:comisionEnmendada)}</com:comisionEnmendada>
                    <com:fechaEnmienda>{fn:data($Comision/com:fechaEnmienda)}</com:fechaEnmienda>
                    {
                        if ($Comision/com:banSugerida)
                        then <com:banSugerida>{fn:data($Comision/com:banSugerida)}</com:banSugerida>
                        else ()
                    }
                    {
                        if ($Comision/com:numeroCuentaCliente)
                        then <com:numeroCuentaCliente>{fn:data($Comision/com:numeroCuentaCliente)}</com:numeroCuentaCliente>
                        else ()
                    }
                    {
                        if ($Comision/com:observaciones)
                        then <com:observaciones>{fn:data($Comision/com:observaciones)}</com:observaciones>
                        else ()
                    }
                    {
                        for $configAtributo in $Comision/com:configAtributo
                        return 
                        <com:configAtributo>
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
                        </com:configAtributo>
                    }
                    {
                        if ($Comision/com:Accion)
                        then <com:Accion>{fn:data($Comision/com:Accion)}</com:Accion>
                        else ()
                    }
                </lin1:Comision>
            }
            {
                for $Fuente in $inputVariable.request/lin:LineaCredito/lin1:Fuente
                let $numeroAsignacion := $outConsultarAsignacionARE.response/lin:LineaCredito/lin1:Fuente[lin1:codigoLineaPasiva = string($Fuente/lin1:idLineaPasiva)]/lin1:NumeroAsignacion/text()
                return 
                <lin1:Fuente>
                    <lin1:idFuente>{fn:data($Fuente/lin1:idFuente)}</lin1:idFuente>
                    <lin1:idLineaCredito>{fn:data($Fuente/lin1:idLineaCredito)}</lin1:idLineaCredito>
                    <lin1:idLineaPasiva>{fn:data($Fuente/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                    <lin1:codigoLineaPasiva>{fn:data($Fuente/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                    <lin1:Descripcion>{fn:data($Fuente/lin1:Descripcion)}</lin1:Descripcion>
                    <lin1:FechaObtenido>{fn:data($Fuente/lin1:FechaObtenido)}</lin1:FechaObtenido>
                    <lin1:MontoAsignado>{fn:data($Fuente/lin1:MontoAsignado)}</lin1:MontoAsignado>
                    <lin1:NumeroAsignacion>{fn:data($numeroAsignacion)}</lin1:NumeroAsignacion>
                    <lin1:Comentario>{fn:data($Fuente/lin1:Comentario)}</lin1:Comentario>
                    <lin1:idContrato>{fn:data($Fuente/lin1:idContrato)}</lin1:idContrato>
                    <lin1:FechaRegistro>{fn:data($Fuente/lin1:FechaRegistro)}</lin1:FechaRegistro>
                    <lin1:Estado>{fn:data($Fuente/lin1:Estado)}</lin1:Estado>
                </lin1:Fuente>
            }
        </lin:LineaCredito>
    </lin:PropagarFuenteRequest>
};

local:funcConsultarasignacionare_to_propagarfuenteinput($inputVariable.request, $outConsultarAsignacionARE.response)
