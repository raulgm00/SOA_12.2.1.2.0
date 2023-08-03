xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoResponse) ::) external;

declare function local:func($ConsultarLineaCreditoResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoResponse) ::)) as element() (:: schema-element(ns1:ConsultarLineaCreditoResponse) ::) {
    <ns1:ConsultarLineaCreditoResponse>
      {
      for $contrato in $ConsultarLineaCreditoResponse/ns1:clienteContrato
      return
        <ns1:clienteContrato>
            <con:idContrato>{fn:data($contrato/con:idContrato)}</con:idContrato>
            <con:idOperacion>{fn:data($contrato/con:idOperacion)}</con:idOperacion>
            <con:fechaFirma>{fn:data($contrato/con:fechaFirma)}</con:fechaFirma>
            <con:fechaVigencia>{fn:data($contrato/con:fechaVigencia)}</con:fechaVigencia>
            {
                if ($contrato/con:idTipoMonedaMontoEscriturado)
                then <con:idTipoMonedaMontoEscriturado>{fn:data($contrato/con:idTipoMonedaMontoEscriturado)}</con:idTipoMonedaMontoEscriturado>
                else ()
            }
            <con:MontoEscriturado>{fn:data($contrato/con:MontoEscriturado)}</con:MontoEscriturado>
            <con:cuentaCliente>
                {
                    for $cuentaCliente in $contrato/con:cuentaCliente/con:cuentaCliente
                    return 
                    <con:cuentaCliente>{fn:data($cuentaCliente)}</con:cuentaCliente>
                }
            </con:cuentaCliente>
            <con:instanciaProceso>{fn:data($contrato/con:instanciaProceso)}</con:instanciaProceso>
            <con:fechaValor>{fn:data($contrato/con:fechaValor)}</con:fechaValor>
            {
            for $linea in $contrato/con:LineaCredito
            return
            <con:LineaCredito>
                {
                    if ($linea/lin:idLineaCredito)
                    then <lin:idLineaCredito>{fn:data($linea/lin:idLineaCredito)}</lin:idLineaCredito>
                    else ()
                }
                <lin:idContrato>{fn:data($linea/lin:idContrato)}</lin:idContrato>
                <lin:NumeroLineaCredito>{fn:data($linea/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
                <lin:Descripcion>{fn:data($linea/lin:Descripcion)}</lin:Descripcion>
                {
                    if ($linea/lin:Flexcube)
                    then 
                        <lin:Flexcube>
                            {
                                if ($linea/lin:Flexcube/lin:id)
                                then <lin:id>{fn:data($linea/lin:Flexcube/lin:id)}</lin:id>
                                else ()
                            }
                            {
                                if ($linea/lin:Flexcube/lin:idProductoFlexcube)
                                then <lin:idProductoFlexcube>{fn:data($linea/lin:Flexcube/lin:idProductoFlexcube)}</lin:idProductoFlexcube>
                                else ()
                            }
                            {
                                if ($linea/lin:Flexcube/lin:idFlexcubePasivo)
                                then <lin:idFlexcubePasivo>{fn:data($linea/lin:Flexcube/lin:idFlexcubePasivo)}</lin:idFlexcubePasivo>
                                else ()
                            }
                        </lin:Flexcube>
                    else ()
                }
                <lin:Fondo>{fn:data($linea/lin:Fondo)}</lin:Fondo>
                {
                    if ($linea/lin:IdTipoMonedaMontoLinea)
                    then <lin:IdTipoMonedaMontoLinea>{fn:data($linea/lin:IdTipoMonedaMontoLinea)}</lin:IdTipoMonedaMontoLinea>
                    else ()
                }
                <lin:MontoLinea>{fn:data($linea/lin:MontoLinea)}</lin:MontoLinea>
                <lin:EsRevolvente>{fn:data($linea/lin:EsRevolvente)}</lin:EsRevolvente>
                <lin:TratamientoDiasFeriados>{fn:data($linea/lin:TratamientoDiasFeriados)}</lin:TratamientoDiasFeriados>
                <lin:FechaValor>{fn:data($linea/lin:FechaValor)}</lin:FechaValor>
                <lin:FechaVencimiento>{fn:data($linea/lin:FechaVencimiento)}</lin:FechaVencimiento>
                <lin:CodigoPantallaLimite>{fn:data($linea/lin:CodigoPantallaLimite)}</lin:CodigoPantallaLimite>
                <lin:CodigoSerialLimite>{fn:data($linea/lin:CodigoSerialLimite)}</lin:CodigoSerialLimite>
                <lin:CodigoAsignacion>{fn:data($linea/lin:CodigoAsignacion)}</lin:CodigoAsignacion>
                <lin:DescripcionAsignacion>{fn:data($linea/lin:DescripcionAsignacion)}</lin:DescripcionAsignacion>
                <lin:CondicionEspecial>{fn:data($linea/lin:CondicionEspecial)}</lin:CondicionEspecial>
                <lin:FechaRegistro>{fn:data($linea/lin:FechaRegistro)}</lin:FechaRegistro>
                <lin:FechaMaximaDesembolso>{fn:data($linea/lin:FechaMaximaDesembolso)}</lin:FechaMaximaDesembolso>
                <lin:Estado>{fn:data($linea/lin:Estado)}</lin:Estado>
                <lin:descCondEspecial>{fn:data($linea/lin:descCondEspecial)}</lin:descCondEspecial>
                <lin:frecuenciaGracia>{fn:data($linea/lin:frecuenciaGracia)}</lin:frecuenciaGracia>
                <lin:plazoGracia>{fn:data($linea/lin:plazoGracia)}</lin:plazoGracia>
                <lin:frecuenciaFinanciamiento>{fn:data($linea/lin:frecuenciaFinanciamiento)}</lin:frecuenciaFinanciamiento>
                <lin:plazoFinanciamiento>{fn:data($linea/lin:plazoFinanciamiento)}</lin:plazoFinanciamiento>
                <lin:recursosExternos>{fn:data($linea/lin:recursosExternos)}</lin:recursosExternos>
                <lin:tasaMinima>{fn:data($linea/lin:tasaMinima)}</lin:tasaMinima>
                <lin:tasaMaxima>{fn:data($linea/lin:tasaMaxima)}</lin:tasaMaxima>
                <lin:montoMinimo>{fn:data($linea/lin:montoMinimo)}</lin:montoMinimo>
                <lin:montoMaximo>{fn:data($linea/lin:montoMaximo)}</lin:montoMaximo>
                <lin:MoverEntreMeses>{fn:data($linea/lin:MoverEntreMeses)}</lin:MoverEntreMeses>
                {
                    for $Termino in $linea/lin:Termino
                    return 
                    <lin:Termino>
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
                        </ter:frecuenciaPlazo>
                        <ter:fechaVencimiento>{fn:data($Termino/ter:fechaVencimiento)}</ter:fechaVencimiento>
                        <ter:moneda>
                            {
                                if ($Termino/ter:moneda/cat:Id)
                                then <cat:Id>{fn:data($Termino/ter:moneda/cat:Id)}</cat:Id>
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
                        </ter:tipoTasa>
                        <ter:fecha>{fn:data($Termino/ter:fecha)}</ter:fecha>
                        <ter:frecuenciaRevision>{fn:data($Termino/ter:frecuenciaRevision)}</ter:frecuenciaRevision>
                        <ter:tipoFrecuenciaRevision>
                            {
                                if ($Termino/ter:tipoFrecuenciaRevision/cat:Id)
                                then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:Id)}</cat:Id>
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
                        </ter:tipoFrecuenciaPagoInteres>
                        <ter:fechaInicioPagoInteres>{fn:data($Termino/ter:fechaInicioPagoInteres)}</ter:fechaInicioPagoInteres>
                        <ter:frecuenciaAmortizacion>{fn:data($Termino/ter:frecuenciaAmortizacion)}</ter:frecuenciaAmortizacion>
                        <ter:tipoFrecuenciaAmortizacion>
                            {
                                if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)
                                then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)}</cat:Id>
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
                        </ter:estadoTCC>
                        <ter:fechaRegistro>{fn:data($Termino/ter:fechaRegistro)}</ter:fechaRegistro>
                        <ter:estado>{fn:data($Termino/ter:estado)}</ter:estado>
                        <ter:terminoEnmendado>{fn:data($Termino/ter:terminoEnmendado)}</ter:terminoEnmendado>
                        <ter:fechaEnmienda>{fn:data($Termino/ter:fechaEnmienda)}</ter:fechaEnmienda>
                     
                    </lin:Termino>
                }
                {
                    for $Comision in $linea/lin:Comision
                    return 
                    <lin:Comision>
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
                                            </com1:idTipoComision>
                                        else ()
                                    }
                                    <com1:esEditableEnFormalizacion>{fn:data($Comision/com1:tipoComision/com1:esEditableEnFormalizacion)}</com1:esEditableEnFormalizacion>
                                    <com1:requiereValidarCOFI>{fn:data($Comision/com1:tipoComision/com1:requiereValidarCOFI)}</com1:requiereValidarCOFI>
                                    <com1:sePuedeDispensar>{fn:data($Comision/com1:tipoComision/com1:sePuedeDispensar)}</com1:sePuedeDispensar>
                                    <com1:seRegistraEnFlexCube>{fn:data($Comision/com1:tipoComision/com1:seRegistraEnFlexCube)}</com1:seRegistraEnFlexCube>
                                    <com1:esPlantilla>{fn:data($Comision/com1:tipoComision/com1:esPlantilla)}</com1:esPlantilla>
                                    <com1:idComisionPrecarga>{fn:data($Comision/com1:tipoComision/com1:idComisionPrecarga)}</com1:idComisionPrecarga>

                                </com1:tipoComision>
                            else ()
                        }
                        {
                            if ($Comision/com1:montoComision)
                            then <com1:montoComision>{fn:data($Comision/com1:montoComision)}</com1:montoComision>
                            else ()
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
                                </com1:tipoFrecuencia>
                            else ()
                        }
                        {
                            if ($Comision/com1:frecuenciaPago)
                            then <com1:frecuenciaPago>{fn:data($Comision/com1:frecuenciaPago)}</com1:frecuenciaPago>
                            else ()
                        }
                        {
                            if ($Comision/com1:comisionCompartida)
                            then <com1:comisionCompartida>{fn:data($Comision/com1:comisionCompartida)}</com1:comisionCompartida>
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
                        </com1:estadoTCC>
                       
                        <com1:fechaRegistro>{fn:data($Comision/com1:fechaRegistro)}</com1:fechaRegistro>
                        <com1:estado>{fn:data($Comision/com1:estado)}</com1:estado>
                       
                    </lin:Comision>
                }
            </con:LineaCredito>
          }
          
        </ns1:clienteContrato>
        }
      <ns1:Resultado>
          <res:result>OK</res:result>
          {if (count($ConsultarLineaCreditoResponse/ns1:clienteContrato)=0)then
            <res:message>No existen registros</res:message>
          else
            <res:message>Consulta realizada existosamente</res:message>
          }
      </ns1:Resultado>
    </ns1:ConsultarLineaCreditoResponse>
};

local:func($ConsultarLineaCreditoResponse)