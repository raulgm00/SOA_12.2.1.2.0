xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $PropagarTransferenciaRecursosResponse as element() (:: schema-element(ns1:PropagarTransferenciaRecursosResponse) ::) external;

declare function local:func($PropagarTransferenciaRecursosResponse as element() (:: schema-element(ns1:PropagarTransferenciaRecursosResponse) ::)) as element() (:: schema-element(ns1:PropagarTransSinSalidaRecursosResponse) ::) {
    <ns1:PropagarTransSinSalidaRecursosResponse>
        <ns1:ContratoDesembolso>
            <des:idDesembolso></des:idDesembolso>
            <des:idFacturador></des:idFacturador>
            <des:producto>
                <pro:idProducto></pro:idProducto>
                <pro:descripcion></pro:descripcion>
                <pro:descripcionCorta></pro:descripcionCorta>
                <pro:fechaRegistro></pro:fechaRegistro>
                <pro:codExterno></pro:codExterno>
                <des:Componente>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                    <des:TipoComponente>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </des:TipoComponente>
                    <des:TipoTasa>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </des:TipoTasa>
                    <des:esPrincipal></des:esPrincipal>
                    <des:Plazo>
                        <com:Tipo>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno></cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor></com:Valor>
                    </des:Plazo>
                </des:Componente>
            </des:producto>
            <des:referencia></des:referencia>
            <des:monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe></com:importe>
                <com:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:moneda>
            </des:monto>
            <des:estado>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </des:estado>
            <des:programado></des:programado>
            <des:fechaEstimadaDesembolsar></des:fechaEstimadaDesembolsar>
            <des:fechaEfectiva></des:fechaEfectiva>
            <des:fechaAsignacion></des:fechaAsignacion>
            <des:fechaRegistro></des:fechaRegistro>
            <des:fechaVencimiento></des:fechaVencimiento>
            <des:estatus></des:estatus>
            <des:condicionesFinancieras>
                <des:tasa>
                    <des:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </des:tipo>
                            <des:fija>
                                <des:valor></des:valor>
                            </des:fija>
                            <des:variable>
                                    <des:tasaReferencia>
                                        <cat:Id></cat:Id>
                                        <cat:Descripcion></cat:Descripcion>
                                        <cat:DescripcionCorta></cat:DescripcionCorta>
                                        <cat:estatus></cat:estatus>
                                        <cat:codigoExterno></cat:codigoExterno>
                                        <des:valor></des:valor>
                                    </des:tasaReferencia>
                                    <des:spread>
                                        <des:valor></des:valor>
                                        <des:codigo></des:codigo>
                                        <des:revision>
                                            <des:FechaInicio></des:FechaInicio>
                                            <des:Frecuencia>
                                                <com:Tipo>
                                                    <cat:Id></cat:Id>
                                                    <cat:Descripcion></cat:Descripcion>
                                                    <cat:DescripcionCorta></cat:DescripcionCorta>
                                                    <cat:estatus></cat:estatus>
                                                    <cat:codigoExterno></cat:codigoExterno>
                                                </com:Tipo>
                                                <com:Valor></com:Valor>
                                            </des:Frecuencia>
                                        </des:revision>
                                    </des:spread>
                                    <des:revision>
                                        <des:FechaInicio></des:FechaInicio>
                                        <des:Frecuencia>
                                            <com:Tipo>
                                                <cat:Id></cat:Id>
                                                <cat:Descripcion></cat:Descripcion>
                                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                                <cat:estatus></cat:estatus>
                                                <cat:codigoExterno></cat:codigoExterno>
                                            </com:Tipo>
                                            <com:Valor></com:Valor>
                                        </des:Frecuencia>
                                    </des:revision>
                                    <des:limite>
                                        <com:maximo></com:maximo>
                                        <com:minimo></com:minimo>
                                    </des:limite>
                                </des:variable>
                                <des:especial>
                                    <des:valor></des:valor>
                                </des:especial>
                    <des:spreadMora>
                        <des:valor></des:valor>
                        <des:codigo></des:codigo>
                        <des:revision>
                            <des:FechaInicio></des:FechaInicio>
                            <des:Frecuencia>
                                <com:Tipo>
                                    <cat:Id></cat:Id>
                                    <cat:Descripcion></cat:Descripcion>
                                    <cat:DescripcionCorta></cat:DescripcionCorta>
                                    <cat:estatus></cat:estatus>
                                    <cat:codigoExterno></cat:codigoExterno>
                                </com:Tipo>
                                <com:Valor></com:Valor>
                            </des:Frecuencia>
                        </des:revision>
                    </des:spreadMora>
                </des:tasa>
                <des:fondo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                    <des:Validador>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </des:Validador>
                </des:fondo>
                <des:baseCalculo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </des:baseCalculo>
                <des:principal>
                    <des:FechaInicio></des:FechaInicio>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno></cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor></com:Valor>
                    </des:Frecuencia>
                    <des:fechaVencimiento></des:fechaVencimiento>
                </des:principal>
                <des:interes>
                    <des:FechaInicio></des:FechaInicio>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno></cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor></com:Valor>
                    </des:Frecuencia>
                </des:interes>
                <des:periodoGracia>
                    <des:FechaInicio></des:FechaInicio>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno></cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor></com:Valor>
                    </des:Frecuencia>
                </des:periodoGracia>
                <des:tratamientoDiasFeriados></des:tratamientoDiasFeriados>
                <des:tipoCalendario>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </des:tipoCalendario>
                <des:calendarioComplejo>
                    <des:TipoPlan>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </des:TipoPlan>
                    <des:Frecuencia>
                        <des:FechaInicio></des:FechaInicio>
                        <des:Frecuencia>
                            <com:Tipo>
                                <cat:Id></cat:Id>
                                <cat:Descripcion></cat:Descripcion>
                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno></cat:codigoExterno>
                            </com:Tipo>
                            <com:Valor></com:Valor>
                        </des:Frecuencia>
                    </des:Frecuencia>
                    <des:Monto>
                        <com:tipo>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno></cat:codigoExterno>
                        </com:tipo>
                        <com:importe></com:importe>
                        <com:moneda>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno></cat:codigoExterno>
                        </com:moneda>
                    </des:Monto>
                    <des:NumeroCuotas></des:NumeroCuotas>
                </des:calendarioComplejo>
            </des:condicionesFinancieras>
            <des:idLinea></des:idLinea>
            <des:recursosExternos></des:recursosExternos>
            <des:cuentaCliente></des:cuentaCliente>
            <des:usuario></des:usuario>
            <des:fechaDisponibilidadFondos></des:fechaDisponibilidadFondos>
            <des:origenTransferenciaCte></des:origenTransferenciaCte>
            <des:Programa>
                <her:LineaFinanciera>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </her:LineaFinanciera>
                <her:DestinoFinanciamiento>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </her:DestinoFinanciamiento>
                <her:ModalidadFinanciamiento>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </her:ModalidadFinanciamiento>
                <her:HerramientaCE>
                    <her:ActividadEconomica>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </her:ActividadEconomica>
                    <her:EjeEstrategico>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </her:EjeEstrategico>
                    <her:AreaFocalizacion>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </her:AreaFocalizacion>
                </her:HerramientaCE>
                <her:ClasificacionGeneral>
                    <her:SectorMercado>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </her:SectorMercado>
                    <her:SectorInstitucional>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </her:SectorInstitucional>
                </her:ClasificacionGeneral>
            </des:Programa>
            <des:EstimadoDesembolso>
                <des:Plazo></des:Plazo>
                <des:Frecuencia>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </des:Frecuencia>
            </des:EstimadoDesembolso>
            <des:Utilizacion>
                <lin:idFuente></lin:idFuente>
                <lin:idLineaCredito></lin:idLineaCredito>
                <lin:idLineaPasiva></lin:idLineaPasiva>
                <lin:codigoLineaPasiva></lin:codigoLineaPasiva>
                <lin:idFacturadorLineaPasiva></lin:idFacturadorLineaPasiva>
                <lin:idFondo></lin:idFondo>
                <lin:Descripcion></lin:Descripcion>
                <lin:FechaObtenido></lin:FechaObtenido>
                <lin:MontoAsignado></lin:MontoAsignado>
                <lin:montoDisponible></lin:montoDisponible>
                <lin:Monto>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:tipo>
                    <com:importe></com:importe>
                    <com:moneda>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:moneda>
                </lin:Monto>
                <lin:NumeroAsignacion></lin:NumeroAsignacion>
                <lin:Comentario></lin:Comentario>
                <lin:idContrato></lin:idContrato>
                <lin:FechaRegistro></lin:FechaRegistro>
                <lin:Estado></lin:Estado>
            </des:Utilizacion>
            <des:Cargo>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
                <des:Monto>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:tipo>
                    <com:importe></com:importe>
                    <com:moneda>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:moneda>
                </des:Monto>
                <des:FechaRegistro></des:FechaRegistro>
                <des:Status></des:Status>
                <des:SoloLectura></des:SoloLectura>
                <des:TCC>
                    <mat:id></mat:id>
                    <mat:tipo></mat:tipo>
                    <mat:estado></mat:estado>
                    <mat:subEstado></mat:subEstado>
                </des:TCC>
            </des:Cargo>
            <des:Comision>
                <com1:idComision></com1:idComision>
                <com1:idOperacion></com1:idOperacion>
                <com1:nombre></com1:nombre>
                <com1:descripcion></com1:descripcion>
                <com1:tipoComision>
                    <com1:idCatComision></com1:idCatComision>
                    <com1:descripcion></com1:descripcion>
                    <com1:descripcionCorta></com1:descripcionCorta>
                    <com1:idTipoComision>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com1:idTipoComision>
                    <com1:esEditableEnFormalizacion></com1:esEditableEnFormalizacion>
                    <com1:requiereValidarCOFI></com1:requiereValidarCOFI>
                    <com1:sePuedeDispensar></com1:sePuedeDispensar>
                    <com1:seRegistraEnFlexCube></com1:seRegistraEnFlexCube>
                    <com1:esPlantilla></com1:esPlantilla>
                    <com1:idComisionPrecarga></com1:idComisionPrecarga>
                    <com1:fechaRegistro></com1:fechaRegistro>
                    <com1:estado></com1:estado>
                    <com1:codigoExterno></com1:codigoExterno>
                </com1:tipoComision>
                <com1:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com1:moneda>
                <com1:montoComision></com1:montoComision>
                <com1:montoBase>
                    <com1:idMontoBase></com1:idMontoBase>
                    <com1:valorMontoBase></com1:valorMontoBase>
                    <com1:porcentajeMontoBase></com1:porcentajeMontoBase>
                    <com1:descripcionMontoBase></com1:descripcionMontoBase>
                </com1:montoBase>
                <com1:fechaValor></com1:fechaValor>
                <com1:fechaVencimiento></com1:fechaVencimiento>
                <com1:fechaInicioCapital></com1:fechaInicioCapital>
                <com1:fechaInicioComision></com1:fechaInicioComision>
                <com1:tipoFrecuencia>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com1:tipoFrecuencia>
                <com1:frecuenciaPago></com1:frecuenciaPago>
                <com1:fondo></com1:fondo>
                <com1:comisionCompartida></com1:comisionCompartida>
                <com1:codigoDesembolso></com1:codigoDesembolso>
                <com1:numeroTesoreria></com1:numeroTesoreria>
                <com1:codigoContrato></com1:codigoContrato>
                <com1:momentoCobro>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com1:momentoCobro>
                <com1:tipoTasa>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com1:tipoTasa>
                <com1:baseCalculo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com1:baseCalculo>
                <com1:responsableComision></com1:responsableComision>
                <com1:estadoTCC>
                    <atr:id></atr:id>
                    <atr:descripcion></atr:descripcion>
                    <atr:descripcionCorta></atr:descripcionCorta>
                    <atr:estatus></atr:estatus>
                    <atr:codigoExterno></atr:codigoExterno>
                    <atr:esSubEstado></atr:esSubEstado>
                </com1:estadoTCC>
                <com1:subEstadoTCC>
                    <atr:id></atr:id>
                    <atr:descripcion></atr:descripcion>
                    <atr:descripcionCorta></atr:descripcionCorta>
                    <atr:estatus></atr:estatus>
                    <atr:codigoExterno></atr:codigoExterno>
                    <atr:esSubEstado></atr:esSubEstado>
                </com1:subEstadoTCC>
                <com1:fechaRegistro></com1:fechaRegistro>
                <com1:estado></com1:estado>
                <com1:comisionEnmendada></com1:comisionEnmendada>
                <com1:fechaEnmienda></com1:fechaEnmienda>
                <com1:banSugerida></com1:banSugerida>
                <com1:numeroCuentaCliente></com1:numeroCuentaCliente>
                <com1:observaciones></com1:observaciones>
                <com1:configAtributo>
                    <atr:id></atr:id>
                    <atr:columna></atr:columna>
                    <atr:ordenamiento></atr:ordenamiento>
                    <atr:soloLectura></atr:soloLectura>
                    <atr:esObligatorio></atr:esObligatorio>
                    <atr:etiqueta></atr:etiqueta>
                    <atr:valor></atr:valor>
                    <atr:tipoValor></atr:tipoValor>
                    <atr:catalogo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </atr:catalogo>
                </com1:configAtributo>
                <com1:Accion></com1:Accion>
            </des:Comision>
            <des:Transferencia>
                <des:idTransferencia></des:idTransferencia>
                <des:idDesembolso></des:idDesembolso>
                <des:idFacturador></des:idFacturador>
                <des:Monto>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:tipo>
                    <com:importe></com:importe>
                    <com:moneda>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:moneda>
                </des:Monto>
                <des:FormaTransferencia>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </des:FormaTransferencia>
                <des:referenciaMensaje></des:referenciaMensaje>
                <des:esConsolidada></des:esConsolidada>
                <des:esConsolidacionPadre></des:esConsolidacionPadre>
                <des:idConsolidacionPadre></des:idConsolidacionPadre>
                <des:bhqTransferencia></des:bhqTransferencia>
                <des:NumeroReserva></des:NumeroReserva>
                <des:idBancoTransferencia></des:idBancoTransferencia>
                <des:numeroCuenta></des:numeroCuenta>
                <des:nombreCuenta></des:nombreCuenta>
                <des:nombreBanco></des:nombreBanco>
                <des:idOperacion></des:idOperacion>
                <des:tipoMensaje></des:tipoMensaje>
                <des:estado></des:estado>
                <des:fechaRegistro></des:fechaRegistro>
                <des:Beneficiario>
                    <des:tipoOpcion></des:tipoOpcion>
                    <des:numeroCta></des:numeroCta>
                    <des:identificador></des:identificador>
                    <des:beneficiario></des:beneficiario>
                    <des:direccion></des:direccion>
                </des:Beneficiario>
                <des:Banco>
                    <des:tipoOpcion></des:tipoOpcion>
                    <des:numeroCta></des:numeroCta>
                    <des:identificador></des:identificador>
                    <des:beneficiario></des:beneficiario>
                    <des:direccion></des:direccion>
                </des:Banco>
                <des:Intermediario>
                    <des:tipoOpcion></des:tipoOpcion>
                    <des:numeroCta></des:numeroCta>
                    <des:identificador></des:identificador>
                    <des:beneficiario></des:beneficiario>
                    <des:direccion></des:direccion>
                </des:Intermediario>
            </des:Transferencia>
            <des:TransferenciaFT05>
                <des:idTransferenciaFT05></des:idTransferenciaFT05>
                <des:idDesembolso></des:idDesembolso>
                <des:idFacturador></des:idFacturador>
                <des:FechaEfectiva></des:FechaEfectiva>
            </des:TransferenciaFT05>
            {
                for $TransferenciasRecurso in $PropagarTransferenciaRecursosResponse/ns1:TransferenciasRecurso
                return 
                <des:TransferenciaRecursos>
                    <des:idTransferencia>{fn:data($TransferenciasRecurso/des:idTransferencia)}</des:idTransferencia>
                    <des:idDesembolso>{fn:data($TransferenciasRecurso/des:idDesembolso)}</des:idDesembolso>
                    {
                        if ($TransferenciasRecurso/des:idFacturador)
                        then <des:idFacturador>{fn:data($TransferenciasRecurso/des:idFacturador)}</des:idFacturador>
                        else ()
                    }
                    <des:idLineaPasiva>{fn:data($TransferenciasRecurso/des:idLineaPasiva)}</des:idLineaPasiva>
                    <des:Monto>
                        <com:tipo>
                            {
                                if ($TransferenciasRecurso/des:Monto/com:tipo/cat:Id)
                                then <cat:Id>{fn:data($TransferenciasRecurso/des:Monto/com:tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($TransferenciasRecurso/des:Monto/com:tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($TransferenciasRecurso/des:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($TransferenciasRecurso/des:Monto/com:tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($TransferenciasRecurso/des:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($TransferenciasRecurso/des:Monto/com:tipo/cat:estatus)
                                then <cat:estatus>{fn:data($TransferenciasRecurso/des:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($TransferenciasRecurso/des:Monto/com:tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($TransferenciasRecurso/des:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </com:tipo>
                        {
                            if ($TransferenciasRecurso/des:Monto/com:importe)
                            then <com:importe>{fn:data($TransferenciasRecurso/des:Monto/com:importe)}</com:importe>
                            else ()
                        }
                        {
                            if ($TransferenciasRecurso/des:Monto/com:moneda)
                            then 
                                <com:moneda>
                                    {
                                        if ($TransferenciasRecurso/des:Monto/com:moneda/cat:Id)
                                        then <cat:Id>{fn:data($TransferenciasRecurso/des:Monto/com:moneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciasRecurso/des:Monto/com:moneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($TransferenciasRecurso/des:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciasRecurso/des:Monto/com:moneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($TransferenciasRecurso/des:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciasRecurso/des:Monto/com:moneda/cat:estatus)
                                        then <cat:estatus>{fn:data($TransferenciasRecurso/des:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($TransferenciasRecurso/des:Monto/com:moneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($TransferenciasRecurso/des:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:moneda>
                            else ()
                        }
                    </des:Monto>
                    <des:fecha>{fn:data($TransferenciasRecurso/des:fecha)}</des:fecha>
                    <des:fechaEfectiva>{fn:data($TransferenciasRecurso/des:fechaEfectiva)}</des:fechaEfectiva>
                    <des:FormaTransferencia>
                        {
                            if ($TransferenciasRecurso/des:FormaTransferencia/cat:Id)
                            then <cat:Id>{fn:data($TransferenciasRecurso/des:FormaTransferencia/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($TransferenciasRecurso/des:FormaTransferencia/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($TransferenciasRecurso/des:FormaTransferencia/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($TransferenciasRecurso/des:FormaTransferencia/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($TransferenciasRecurso/des:FormaTransferencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($TransferenciasRecurso/des:FormaTransferencia/cat:estatus)
                            then <cat:estatus>{fn:data($TransferenciasRecurso/des:FormaTransferencia/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($TransferenciasRecurso/des:FormaTransferencia/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($TransferenciasRecurso/des:FormaTransferencia/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des:FormaTransferencia>
                    {
                        if ($TransferenciasRecurso/des:idBanco)
                        then <des:idBanco>{fn:data($TransferenciasRecurso/des:idBanco)}</des:idBanco>
                        else ()
                    }
                    {
                        if ($TransferenciasRecurso/des:nombreBanco)
                        then <des:nombreBanco>{fn:data($TransferenciasRecurso/des:nombreBanco)}</des:nombreBanco>
                        else ()
                    }
                    {
                        if ($TransferenciasRecurso/des:numeroCuenta)
                        then <des:numeroCuenta>{fn:data($TransferenciasRecurso/des:numeroCuenta)}</des:numeroCuenta>
                        else ()
                    }
                    {
                        if ($TransferenciasRecurso/des:nombreCuenta)
                        then <des:nombreCuenta>{fn:data($TransferenciasRecurso/des:nombreCuenta)}</des:nombreCuenta>
                        else ()
                    }
                    {
                        if ($TransferenciasRecurso/des:estatus)
                        then <des:estatus>{fn:data($TransferenciasRecurso/des:estatus)}</des:estatus>
                        else ()
                    }
                    {
                        if ($TransferenciasRecurso/des:fechaRegistro)
                        then <des:fechaRegistro>{fn:data($TransferenciasRecurso/des:fechaRegistro)}</des:fechaRegistro>
                        else ()
                    }
                </des:TransferenciaRecursos>
            }
            <des:HerramientaCE>
                <her:ActividadEconomica>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </her:ActividadEconomica>
                <her:EjeEstrategico>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </her:EjeEstrategico>
                <her:AreaFocalizacion>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </her:AreaFocalizacion>
            </des:HerramientaCE>
        </ns1:ContratoDesembolso>
        <ns1:Resultado>
            <res:result>{fn:data($PropagarTransferenciaRecursosResponse/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($PropagarTransferenciaRecursosResponse/ns1:Resultado/res:message)}</res:message>
            {
                if ($PropagarTransferenciaRecursosResponse/ns1:Resultado/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($PropagarTransferenciaRecursosResponse/ns1:Resultado/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($PropagarTransferenciaRecursosResponse/ns1:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($PropagarTransferenciaRecursosResponse/ns1:Resultado/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </ns1:Resultado>
    </ns1:PropagarTransSinSalidaRecursosResponse>
};

local:func($PropagarTransferenciaRecursosResponse)
