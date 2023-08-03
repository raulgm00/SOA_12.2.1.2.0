xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace mat="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd", 
                     "oramds:/apps/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::) external;
declare variable $outputVariable.response as element() (:: schema-element(mat:ActualizarTCCResponse) ::) external;

declare function local:funcComisiontransformation($inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::), 
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
            for $Comision in $inputVariable.request/mat:Comision
            let $contadorComision := $outputVariable.response/mat:Comision/com:idComision
            return 
            <mat:Comision>
                <com:idComision>{fn:data($Comision[$contadorComision]/com:idComision)}</com:idComision>
                <com:idOperacion>{fn:data($Comision[$contadorComision]/com:idOperacion)}</com:idOperacion>
                {
                    if ($Comision[$contadorComision]/com:nombre)
                    then <com:nombre>{fn:data($Comision[$contadorComision]/com:nombre)}</com:nombre>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:descripcion)
                    then <com:descripcion>{fn:data($Comision[$contadorComision]/com:descripcion)}</com:descripcion>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:tipoComision)
                    then 
                        <com:tipoComision>
                            {
                                if ($Comision[$contadorComision]/com:tipoComision/com:idCatComision)
                                then <com:idCatComision>{fn:data($Comision[$contadorComision]/com:tipoComision/com:idCatComision)}</com:idCatComision>
                                else ()
                            }
                            {
                                if ($Comision[$contadorComision]/com:tipoComision/com:idTipoComision)
                                then 
                                    <com:idTipoComision>
                                        {
                                            if ($Comision[$contadorComision]/com:tipoComision/com:idTipoComision/cat:Id)
                                            then <cat:Id>{fn:data($Comision[$contadorComision]/com:tipoComision/com:idTipoComision/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                    </com:idTipoComision>
                                else ()
                            }                            
                        </com:tipoComision>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:moneda)
                    then 
                        <com:moneda>
                            {
                                if ($Comision[$contadorComision]/com:moneda/cat:Id)
                                then <cat:Id>{fn:data($Comision[$contadorComision]/com:moneda/cat:Id)}</cat:Id>
                                else ()
                            }
                        </com:moneda>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:montoComision)
                    then <com:montoComision>{fn:data($Comision[$contadorComision]/com:montoComision)}</com:montoComision>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:montoBase)
                    then 
                        <com:montoBase>
                            {
                                if ($Comision[$contadorComision]/com:montoBase/com:idMontoBase)
                                then <com:idMontoBase>{fn:data($Comision[$contadorComision]/com:montoBase/com:idMontoBase)}</com:idMontoBase>
                                else ()
                            }
                            {
                                if ($Comision[$contadorComision]/com:montoBase/com:valorMontoBase)
                                then <com:valorMontoBase>{fn:data($Comision[$contadorComision]/com:montoBase/com:valorMontoBase)}</com:valorMontoBase>
                                else ()
                            }
                            {
                                if ($Comision[$contadorComision]/com:montoBase/com:porcentajeMontoBase)
                                then <com:porcentajeMontoBase>{fn:data($Comision[$contadorComision]/com:montoBase/com:porcentajeMontoBase)}</com:porcentajeMontoBase>
                                else ()
                            }
                        </com:montoBase>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:fechaValor)
                    then <com:fechaValor>{fn:data($Comision[$contadorComision]/com:fechaValor)}</com:fechaValor>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:fechaVencimiento)
                    then <com:fechaVencimiento>{fn:data($Comision[$contadorComision]/com:fechaVencimiento)}</com:fechaVencimiento>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:fechaInicioCapital)
                    then <com:fechaInicioCapital>{fn:data($Comision[$contadorComision]/com:fechaInicioCapital)}</com:fechaInicioCapital>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:fechaInicioComision)
                    then <com:fechaInicioComision>{fn:data($Comision[$contadorComision]/com:fechaInicioComision)}</com:fechaInicioComision>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:tipoFrecuencia)
                    then 
                        <com:tipoFrecuencia>
                            {
                                if ($Comision[$contadorComision]/com:tipoFrecuencia/cat:Id)
                                then <cat:Id>{fn:data($Comision[$contadorComision]/com:tipoFrecuencia/cat:Id)}</cat:Id>
                                else ()
                            }
                        </com:tipoFrecuencia>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:frecuenciaPago)
                    then <com:frecuenciaPago>{fn:data($Comision[$contadorComision]/com:frecuenciaPago)}</com:frecuenciaPago>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:fondo)
                    then <com:fondo>{fn:data($Comision[$contadorComision]/com:fondo)}</com:fondo>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:comisionCompartida)
                    then <com:comisionCompartida>{fn:data($Comision[$contadorComision]/com:comisionCompartida)}</com:comisionCompartida>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:codigoDesembolso)
                    then <com:codigoDesembolso>{fn:data($Comision[$contadorComision]/com:codigoDesembolso)}</com:codigoDesembolso>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:numeroTesoreria)
                    then <com:numeroTesoreria>{fn:data($Comision[$contadorComision]/com:numeroTesoreria)}</com:numeroTesoreria>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:codigoContrato)
                    then <com:codigoContrato>{fn:data($Comision[$contadorComision]/com:codigoContrato)}</com:codigoContrato>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:momentoCobro)
                    then 
                        <com:momentoCobro>
                            {
                                if ($Comision[$contadorComision]/com:momentoCobro/cat:Id)
                                then <cat:Id>{fn:data($Comision[$contadorComision]/com:momentoCobro/cat:Id)}</cat:Id>
                                else ()
                            }
                        </com:momentoCobro>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:tipoTasa)
                    then 
                        <com:tipoTasa>
                            {
                                if ($Comision[$contadorComision]/com:tipoTasa/cat:Id)
                                then <cat:Id>{fn:data($Comision[$contadorComision]/com:tipoTasa/cat:Id)}</cat:Id>
                                else ()
                            }
                        </com:tipoTasa>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:baseCalculo)
                    then 
                        <com:baseCalculo>
                            {
                                if ($Comision[$contadorComision]/com:baseCalculo/cat:Id)
                                then <cat:Id>{fn:data($Comision[$contadorComision]/com:baseCalculo/cat:Id)}</cat:Id>
                                else ()
                            }
                        </com:baseCalculo>
                    else ()
                }
                {
                    if ($Comision[$contadorComision]/com:responsableComision)
                    then <com:responsableComision>{fn:data($Comision[$contadorComision]/com:responsableComision)}</com:responsableComision>
                    else ()
                }                
                <com:estado>{fn:data($Comision[$contadorComision]/com:estado)}</com:estado>
                <com:comisionEnmendada>
                </com:comisionEnmendada>
                
                {
                    if ($Comision[$contadorComision]/com:Accion)
                    then <com:Accion>{fn:data($Comision[$contadorComision]/com:Accion)}</com:Accion>
                    else ()
                }
            </mat:Comision>
        }
    </mat:ActualizarTCCRequest>
};

local:funcComisiontransformation($inputVariable.request, $outputVariable.response)
