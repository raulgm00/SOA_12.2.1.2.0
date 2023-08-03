xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

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
            for $Comision in $ActualizarTCCRequest/ns1:Comision
            return 
            <ns1:Comision>
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
            </ns1:Comision>
        }
    </ns1:ActualizarTCCRequest>
};

local:func($ActualizarTCCRequest)
