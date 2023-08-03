xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace com="http://www.bcie.org/ComisionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace com2 = "http://www.bcie.org/CommonBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $outConsultarComision.response as element() (:: schema-element(com:ConsultarComisionResponse) ::) external;

declare function local:funcConsultarcomisiones($outConsultarComision.response as element() (:: schema-element(com:ConsultarComisionResponse) ::)) as element() (:: schema-element(com:ConsultarComisionResponse) ::) {
    <com:ConsultarComisionResponse>
    {
    for $comisiones in $outConsultarComision.response[com:Operacion/ope:montoOperacion/ope:montoOperacion/ope:monto>0]/com:Comision[com1:codigoContrato!=''][com1:estadoTCC/atr:id!= 5][com1:estadoTCC/atr:id!= 6]
    return
        
        for $Comision in $comisiones
        return 
        
        <com:Comision>
            <com1:idComision>{fn:data($Comision/com1:idComision)}</com1:idComision>
            <com1:idOperacion>{fn:data($Comision/com1:idOperacion)}</com1:idOperacion>
            {
                if ($Comision/com1:nombre)
                then <com1:nombre>{fn:data($Comision/com1:nombre)}</com1:nombre>
                else ()
            }
            {
                if ($Comision/com1:descripcion)
                then <com1:descripcion>{fn:data($Comision/com1:descripcion)}</com1:descripcion>
                else ()
            }
            {
                if ($Comision/com1:tipoComision)
                then 
                    <com1:tipoComision>
                        {
                            if ($Comision/com1:tipoComision/com1:idCatComision)
                            then <com1:idCatComision>{fn:data($Comision/com1:tipoComision/com1:idCatComision)}</com1:idCatComision>
                            else ()
                        }
                        <com1:descripcion>{fn:data($Comision/com1:tipoComision/com1:descripcion)}</com1:descripcion>
                        <com1:descripcionCorta>{fn:data($Comision/com1:tipoComision/com1:descripcionCorta)}</com1:descripcionCorta>
                        {
                            if ($Comision/com1:tipoComision/com1:idTipoComision)
                            then 
                                <com1:idTipoComision>
                                    {
                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)
                                        then <cat:Id>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)
                                        then <cat:estatus>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:idTipoComision>
                            else ()
                        }
                        <com1:esEditableEnFormalizacion>{fn:data($Comision/com1:tipoComision/com1:esEditableEnFormalizacion)}</com1:esEditableEnFormalizacion>
                        <com1:requiereValidarCOFI>{fn:data($Comision/com1:tipoComision/com1:requiereValidarCOFI)}</com1:requiereValidarCOFI>
                        <com1:sePuedeDispensar>{fn:data($Comision/com1:tipoComision/com1:sePuedeDispensar)}</com1:sePuedeDispensar>
                        <com1:seRegistraEnFlexCube>{fn:data($Comision/com1:tipoComision/com1:seRegistraEnFlexCube)}</com1:seRegistraEnFlexCube>
                        <com1:esPlantilla>{fn:data($Comision/com1:tipoComision/com1:esPlantilla)}</com1:esPlantilla>
                        <com1:idComisionPrecarga>{fn:data($Comision/com1:tipoComision/com1:idComisionPrecarga)}</com1:idComisionPrecarga>
                        <com1:fechaRegistro>{fn:data($Comision/com1:tipoComision/com1:fechaRegistro)}</com1:fechaRegistro>
                        <com1:estado>{fn:data($Comision/com1:tipoComision/com1:estado)}</com1:estado>
                        <com1:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:codigoExterno)}</com1:codigoExterno>
                    </com1:tipoComision>
                else ()
            }
            {
                if ($Comision/com1:moneda)
                then 
                    <com1:moneda>
                        {
                            if ($Comision/com1:moneda/cat:Id)
                            then <cat:Id>{fn:data($Comision/com1:moneda/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Comision/com1:moneda/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Comision/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:moneda/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Comision/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com1:moneda/cat:estatus)
                            then <cat:estatus>{fn:data($Comision/com1:moneda/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com1:moneda/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Comision/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </com1:moneda>
                else ()
            }
            {
                if (string(fn:data($Comision/com1:montoComision)) != '')
                then <com1:montoComision>{fn:data($Comision/com1:montoComision)}</com1:montoComision>
                else <com1:montoComision>0</com1:montoComision>
            }
            {
                if ($Comision/com1:montoBase)
                then 
                    <com1:montoBase>
                        {
                            if ($Comision/com1:montoBase/com1:idMontoBase)
                            then <com1:idMontoBase>{fn:data($Comision/com1:montoBase/com1:idMontoBase)}</com1:idMontoBase>
                            else ()
                        }
                        {
                            if ($Comision/com1:montoBase/com1:valorMontoBase)
                            then <com1:valorMontoBase>{fn:data($Comision/com1:montoBase/com1:valorMontoBase)}</com1:valorMontoBase>
                            else ()
                        }
                        {
                            if ($Comision/com1:montoBase/com1:porcentajeMontoBase)
                            then <com1:porcentajeMontoBase>{fn:data($Comision/com1:montoBase/com1:porcentajeMontoBase)}</com1:porcentajeMontoBase>
                            else ()
                        }
                        {
                            if ($Comision/com1:montoBase/com1:descripcionMontoBase)
                            then <com1:descripcionMontoBase>{fn:data($Comision/com1:montoBase/com1:descripcionMontoBase)}</com1:descripcionMontoBase>
                            else ()
                        }
                    </com1:montoBase>
                else ()
            }
            {
                if ($Comision/com1:fechaValor)
                then <com1:fechaValor>{fn:data($Comision/com1:fechaValor)}</com1:fechaValor>
                else ()
            }
            {
                if ($Comision/com1:fechaVencimiento)
                then <com1:fechaVencimiento>{fn:data($Comision/com1:fechaVencimiento)}</com1:fechaVencimiento>
                else ()
            }
            {
                if ($Comision/com1:fechaInicioCapital)
                then <com1:fechaInicioCapital>{fn:data($Comision/com1:fechaInicioCapital)}</com1:fechaInicioCapital>
                else ()
            }
            {
                if ($Comision/com1:fechaInicioComision)
                then <com1:fechaInicioComision>{fn:data($Comision/com1:fechaInicioComision)}</com1:fechaInicioComision>
                else ()
            }
            {
                if ($Comision/com1:tipoFrecuencia)
                then 
                    <com1:tipoFrecuencia>
                        {
                            if ($Comision/com1:tipoFrecuencia/cat:Id)
                            then <cat:Id>{fn:data($Comision/com1:tipoFrecuencia/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoFrecuencia/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Comision/com1:tipoFrecuencia/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoFrecuencia/cat:estatus)
                            then <cat:estatus>{fn:data($Comision/com1:tipoFrecuencia/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoFrecuencia/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Comision/com1:tipoFrecuencia/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </com1:tipoFrecuencia>
                else ()
            }
            {
                if ($Comision/com1:frecuenciaPago)
                then <com1:frecuenciaPago>{fn:data($Comision/com1:frecuenciaPago)}</com1:frecuenciaPago>
                else ()
            }
            {
                if ($Comision/com1:fondo)
                then <com1:fondo>{fn:data($Comision/com1:fondo)}</com1:fondo>
                else ()
            }
            {
                if ($Comision/com1:comisionCompartida)
                then <com1:comisionCompartida>{fn:data($Comision/com1:comisionCompartida)}</com1:comisionCompartida>
                else ()
            }
            {
                if ($Comision/com1:codigoDesembolso)
                then <com1:codigoDesembolso>{fn:data($Comision/com1:codigoDesembolso)}</com1:codigoDesembolso>
                else ()
            }
            {
                if ($Comision/com1:numeroTesoreria)
                then <com1:numeroTesoreria>{fn:data($Comision/com1:numeroTesoreria)}</com1:numeroTesoreria>
                else ()
            }
            {
                if ($Comision/com1:codigoContrato)
                then <com1:codigoContrato>{fn:data($Comision/com1:codigoContrato)}</com1:codigoContrato>
                else ()
            }
            {
                if ($Comision/com1:momentoCobro)
                then 
                    <com1:momentoCobro>
                        {
                            if ($Comision/com1:momentoCobro/cat:Id)
                            then <cat:Id>{fn:data($Comision/com1:momentoCobro/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Comision/com1:momentoCobro/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Comision/com1:momentoCobro/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:momentoCobro/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Comision/com1:momentoCobro/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com1:momentoCobro/cat:estatus)
                            then <cat:estatus>{fn:data($Comision/com1:momentoCobro/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com1:momentoCobro/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Comision/com1:momentoCobro/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </com1:momentoCobro>
                else ()
            }
            {
                if ($Comision/com1:tipoTasa)
                then 
                    <com1:tipoTasa>
                        {
                            if ($Comision/com1:tipoTasa/cat:Id)
                            then <cat:Id>{fn:data($Comision/com1:tipoTasa/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoTasa/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Comision/com1:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoTasa/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoTasa/cat:estatus)
                            then <cat:estatus>{fn:data($Comision/com1:tipoTasa/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoTasa/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Comision/com1:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </com1:tipoTasa>
                else ()
            }
            {
                if ($Comision/com1:baseCalculo)
                then 
                    <com1:baseCalculo>
                        {
                            if ($Comision/com1:baseCalculo/cat:Id)
                            then <cat:Id>{fn:data($Comision/com1:baseCalculo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Comision/com1:baseCalculo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Comision/com1:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:baseCalculo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Comision/com1:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com1:baseCalculo/cat:estatus)
                            then <cat:estatus>{fn:data($Comision/com1:baseCalculo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com1:baseCalculo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Comision/com1:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </com1:baseCalculo>
                else ()
            }
            {
                if ($Comision/com1:responsableComision)
                then <com1:responsableComision>{fn:data($Comision/com1:responsableComision)}</com1:responsableComision>
                else ()
            }
            <com1:estadoTCC>
                {
                    if ($Comision/com1:estadoTCC/atr:id)
                    then <atr:id>{fn:data($Comision/com1:estadoTCC/atr:id)}</atr:id>
                    else ()
                }
                {
                    if ($Comision/com1:estadoTCC/atr:descripcion)
                    then <atr:descripcion>{fn:data($Comision/com1:estadoTCC/atr:descripcion)}</atr:descripcion>
                    else ()
                }
                {
                    if ($Comision/com1:estadoTCC/atr:descripcionCorta)
                    then <atr:descripcionCorta>{fn:data($Comision/com1:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                    else ()
                }
                {
                    if ($Comision/com1:estadoTCC/atr:estatus)
                    then <atr:estatus>{fn:data($Comision/com1:estadoTCC/atr:estatus)}</atr:estatus>
                    else ()
                }
                {
                    if ($Comision/com1:estadoTCC/atr:codigoExterno)
                    then <atr:codigoExterno>{fn:data($Comision/com1:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                    else ()
                }
                {
                    if ($Comision/com1:estadoTCC/atr:esSubEstado)
                    then <atr:esSubEstado>{fn:data($Comision/com1:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                    else ()
                }
            </com1:estadoTCC>
            <com1:subEstadoTCC>
                {
                    if ($Comision/com1:subEstadoTCC/atr:id)
                    then <atr:id>{fn:data($Comision/com1:subEstadoTCC/atr:id)}</atr:id>
                    else ()
                }
                {
                    if ($Comision/com1:subEstadoTCC/atr:descripcion)
                    then <atr:descripcion>{fn:data($Comision/com1:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                    else ()
                }
                {
                    if ($Comision/com1:subEstadoTCC/atr:descripcionCorta)
                    then <atr:descripcionCorta>{fn:data($Comision/com1:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                    else ()
                }
                {
                    if ($Comision/com1:subEstadoTCC/atr:estatus)
                    then <atr:estatus>{fn:data($Comision/com1:subEstadoTCC/atr:estatus)}</atr:estatus>
                    else ()
                }
                {
                    if ($Comision/com1:subEstadoTCC/atr:codigoExterno)
                    then <atr:codigoExterno>{fn:data($Comision/com1:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                    else ()
                }
                {
                    if ($Comision/com1:subEstadoTCC/atr:esSubEstado)
                    then <atr:esSubEstado>{fn:data($Comision/com1:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                    else ()
                }
            </com1:subEstadoTCC>
            <com1:fechaRegistro>{fn:data($Comision/com1:fechaRegistro)}</com1:fechaRegistro>
            <com1:estado>{fn:data($Comision/com1:estado)}</com1:estado>
            <com1:comisionEnmendada>{fn:data($Comision/com1:comisionEnmendada)}</com1:comisionEnmendada>
            <com1:fechaEnmienda>{fn:data($Comision/com1:fechaEnmienda)}</com1:fechaEnmienda>
            {
                if ($Comision/com1:banSugerida)
                then <com1:banSugerida>{fn:data($Comision/com1:banSugerida)}</com1:banSugerida>
                else ()
            }
            {
                if ($Comision/com1:numeroCuentaCliente)
                then <com1:numeroCuentaCliente>{fn:data($Comision/com1:numeroCuentaCliente)}</com1:numeroCuentaCliente>
                else ()
            }
            {
                if ($Comision/com1:observaciones)
                then <com1:observaciones>{fn:data($Comision/com1:observaciones)}</com1:observaciones>
                else ()
            }
            {
                for $configAtributo in $Comision/com1:configAtributo
                return 
                <com1:configAtributo>
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
                </com1:configAtributo>
            }
            {
                if ($Comision/com1:Accion)
                then <com1:Accion>{fn:data($Comision/com1:Accion)}</com1:Accion>
                else ()
            }
        </com:Comision>
        }
   
    </com:ConsultarComisionResponse>
};

local:funcConsultarcomisiones($outConsultarComision.response)
