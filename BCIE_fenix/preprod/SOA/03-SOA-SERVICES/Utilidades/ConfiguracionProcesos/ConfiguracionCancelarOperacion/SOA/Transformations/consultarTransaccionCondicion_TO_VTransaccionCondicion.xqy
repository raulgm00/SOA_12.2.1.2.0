xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace con1="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)

declare namespace con3 = "http://www.bcie.org/ContratoBO";

declare namespace con2 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace con4 = "http://www.bcie.org/CondicionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarTransaccionCondicion_SB.response as element() (:: schema-element(con:ConsultarTransaccionCondicionResponse) ::) external;

declare function local:funcConsultartransaccioncondicion_to_vtransaccioncondicion($outConsultarTransaccionCondicion_SB.response as element() (:: schema-element(con:ConsultarTransaccionCondicionResponse) ::)) as element() (:: schema-element(con1:configuracionCancelarOperacionResponse) ::) {
    <con1:configuracionCancelarOperacionResponse>
        <con1:configuracionCancelarOperacion>
            <con2:Condiciones>
                <con4:TransaccionCondicion>
                    {
                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:IdTransaccion)
                        then <con4:IdTransaccion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:IdTransaccion)}</con4:IdTransaccion>
                        else ()
                    }
                    {
                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Operacion)
                        then 
                            <con4:Operacion>
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Operacion/ope:idOperacion)
                                    then <ope:idOperacion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Operacion/ope:idOperacion)}</ope:idOperacion>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Operacion/ope:responsable)
                                    then <ope:responsable>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Operacion/ope:responsable)}</ope:responsable>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Operacion/ope:oficina)
                                    then <ope:oficina>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Operacion/ope:oficina)}</ope:oficina>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Operacion/ope:nombre)
                                    then <ope:nombre>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Operacion/ope:nombre)}</ope:nombre>
                                    else ()
                                }
                            </con4:Operacion>
                        else ()
                    }
                    {
                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud)
                        then 
                            <con4:Solicitud>
                                <des:idDesembolso>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:idDesembolso)}</des:idDesembolso>
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:idFacturador)
                                    then <des:idFacturador>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:idFacturador)}</des:idFacturador>
                                    else ()
                                }
                                <des:fechaCreacion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:fechaCreacion)}</des:fechaCreacion>
                                <des:fechaSolicitud>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:fechaSolicitud)}</des:fechaSolicitud>
                                <des:monto>
                                    <com:tipo>
                                        {
                                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:Id)
                                            then <cat:Id>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:tipo>
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:importe)
                                        then <com:importe>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:importe)}</com:importe>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda)
                                        then 
                                            <com:moneda>
                                                {
                                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:Id)
                                                    then <cat:Id>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:estatus)
                                                    then <cat:estatus>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </com:moneda>
                                        else ()
                                    }
                                </des:monto>
                                <des:tipoMoneda>
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:Id)
                                        then <cat:Id>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:estatus)
                                        then <cat:estatus>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:tipoMoneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des:tipoMoneda>
                                <des:estado>
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:Id)
                                        then <cat:Id>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:estatus)
                                        then <cat:estatus>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estado/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des:estado>
                                <des:estatus>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:estatus)}</des:estatus>
                                <des:instanciaProceso>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:instanciaProceso)}</des:instanciaProceso>
                                <des:ValidacionAsignacion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:ValidacionAsignacion)}</des:ValidacionAsignacion>
                                {
                                    for $Excepcion in $outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Solicitud/des:Excepcion
                                    return 
                                    <des:Excepcion>
                                        <reg:Id>{fn:data($Excepcion/reg:Id)}</reg:Id>
                                        {
                                            if ($Excepcion/reg:Descripcion)
                                            then <reg:Descripcion>{fn:data($Excepcion/reg:Descripcion)}</reg:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Transaccion)
                                            then <reg:Transaccion>{fn:data($Excepcion/reg:Transaccion)}</reg:Transaccion>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Tipo)
                                            then 
                                                <reg:Tipo>
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:Id)
                                                        then <cat:Id>{fn:data($Excepcion/reg:Tipo/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Excepcion/reg:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Excepcion/reg:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:estatus)
                                                        then <cat:estatus>{fn:data($Excepcion/reg:Tipo/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Excepcion/reg:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </reg:Tipo>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:PosibleExceptuar)
                                            then <reg:PosibleExceptuar>{fn:data($Excepcion/reg:PosibleExceptuar)}</reg:PosibleExceptuar>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Exceptuado)
                                            then 
                                                <reg:Exceptuado>
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:Id)
                                                        then <cat:Id>{fn:data($Excepcion/reg:Exceptuado/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Excepcion/reg:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Excepcion/reg:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:estatus)
                                                        then <cat:estatus>{fn:data($Excepcion/reg:Exceptuado/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Excepcion/reg:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </reg:Exceptuado>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:UsuarioExceptua)
                                            then <reg:UsuarioExceptua>{fn:data($Excepcion/reg:UsuarioExceptua)}</reg:UsuarioExceptua>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:FechaExcepcion)
                                            then <reg:FechaExcepcion>{fn:data($Excepcion/reg:FechaExcepcion)}</reg:FechaExcepcion>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Estado)
                                            then 
                                                <reg:Estado>
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:Id)
                                                        then <cat:Id>{fn:data($Excepcion/reg:Estado/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Excepcion/reg:Estado/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Excepcion/reg:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:estatus)
                                                        then <cat:estatus>{fn:data($Excepcion/reg:Estado/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Excepcion/reg:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </reg:Estado>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Estatus)
                                            then <reg:Estatus>{fn:data($Excepcion/reg:Estatus)}</reg:Estatus>
                                            else ()
                                        }
                                        {
                                            for $DetalleRegla in $Excepcion/reg:DetalleRegla
                                            return 
                                            <reg:DetalleRegla>
                                                {
                                                    if ($DetalleRegla/atr:id)
                                                    then <atr:id>{fn:data($DetalleRegla/atr:id)}</atr:id>
                                                    else ()
                                                }
                                                {
                                                    if ($DetalleRegla/atr:descripcion)
                                                    then <atr:descripcion>{fn:data($DetalleRegla/atr:descripcion)}</atr:descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($DetalleRegla/atr:estatus)
                                                    then <atr:estatus>{fn:data($DetalleRegla/atr:estatus)}</atr:estatus>
                                                    else ()
                                                }
                                            </reg:DetalleRegla>
                                        }
                                        <des:instanciaProceso>{fn:data($Excepcion/des:instanciaProceso)}</des:instanciaProceso>
                                        <des:enProcesoExcepcion>{fn:data($Excepcion/des:enProcesoExcepcion)}</des:enProcesoExcepcion>
                                    </des:Excepcion>
                                }
                            </con4:Solicitud>
                        else ()
                    }
                    <con4:ContratoDesembolso>{
                        $outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:ContratoDesembolso/*
                        }
                    </con4:ContratoDesembolso>
                    {
                    for $condicion in $outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:Condicion 
                    return 
                      <con4:Condicion>
                      {
                        $condicion/*
                      }
                      </con4:Condicion> 
                    }
                    <con4:EstadoTCC>
                        {
                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:Id)
                            then <cat:Id>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:estatus)
                            then <cat:estatus>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EstadoTCC/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </con4:EstadoTCC>
                    <con4:estatus>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:estatus)}</con4:estatus>
                    <con4:enProceso>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:enProceso)}</con4:enProceso>
                    {
                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:agrupador)
                        then <con4:agrupador>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:agrupador)}</con4:agrupador>
                        else ()
                    }
                    {
                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion)
                        then 
                            <con4:EventoCondicion>
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:EventoCondicion/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </con4:EventoCondicion>
                        else ()
                    }
                    {
                        if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC)
                        then 
                            <con4:subEstadoTCC>
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:Id)
                                    then <cat:Id>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:estatus)
                                    then <cat:estatus>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($outConsultarTransaccionCondicion_SB.response/con:TransaccionCondicion/con4:subEstadoTCC/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </con4:subEstadoTCC>
                        else ()
                    }
                </con4:TransaccionCondicion>
            </con2:Condiciones>
        </con1:configuracionCancelarOperacion>
        <con1:Resultado>
            <res:result>{fn:data($outConsultarTransaccionCondicion_SB.response/con:Resultado/res:result)}</res:result>
            <res:message>{fn:data($outConsultarTransaccionCondicion_SB.response/con:Resultado/res:message)}</res:message>
            {
                if ($outConsultarTransaccionCondicion_SB.response/con:Resultado/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($outConsultarTransaccionCondicion_SB.response/con:Resultado/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($outConsultarTransaccionCondicion_SB.response/con:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($outConsultarTransaccionCondicion_SB.response/con:Resultado/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </con1:Resultado>
    </con1:configuracionCancelarOperacionResponse>
};

local:funcConsultartransaccioncondicion_to_vtransaccioncondicion($outConsultarTransaccionCondicion_SB.response)
