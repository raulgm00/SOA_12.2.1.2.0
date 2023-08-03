xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDesembolsosConfiguracionById";
(:: import schema at "../XSD/ConsultarDesembolsosConfiguracionById.xsd" ::)

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

declare variable $ConsultarDesembolsosConfiguracionById as element() (:: schema-element(ns1:ConsultarDesembolsosConfiguracionByIdOutputCollection) ::) external;

declare function local:func($ConsultarDesembolsosConfiguracionById as element() (:: schema-element(ns1:ConsultarDesembolsosConfiguracionByIdOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarDesembolsosConfiguracionByIdResponse) ::) {
    <ns2:ConsultarDesembolsosConfiguracionByIdResponse>
    {               
        for $idDesembolso in distinct-values($ConsultarDesembolsosConfiguracionById/ns1:ConsultarDesembolsosConfiguracionByIdOutput/ns1:ID_DESEMBOLSO)
        let $desembolso := $ConsultarDesembolsosConfiguracionById/ns1:ConsultarDesembolsosConfiguracionByIdOutput[ns1:ID_DESEMBOLSO = $idDesembolso ][1]
        return
        <ns2:ContratoDesembolso>
        <des:idDesembolso>{fn:data($desembolso/ns1:ID_DESEMBOLSO)}</des:idDesembolso>
        <des:idFacturador>{fn:data($desembolso/ns1:CONTRATO_FLEXCUBE)}</des:idFacturador>
        <des:producto>
            <pro:codExterno>{fn:data($desembolso/ns1:ID_PRODUCTO_FLEXCUBE)}</pro:codExterno>
        </des:producto>
        <des:referencia>{fn:data($desembolso/ns1:REFERENCIA)}</des:referencia>
            <des:monto>
                <com:tipo>
                    <cat:DescripcionCorta>DESEMBOLSO</cat:DescripcionCorta>
                </com:tipo>
                <com:importe>{fn:data($desembolso/ns1:MONTO_DESEMBOLSAR)}</com:importe>
                <com:moneda>
                    <cat:Id>{fn:data($desembolso/ns1:ID_TCA_MONEDA)}</cat:Id>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_MONEDA)}</cat:codigoExterno>
                </com:moneda>
            </des:monto>
            <des:monto>
                <com:tipo>
                    <cat:DescripcionCorta>DESEMBOLSO_USD</cat:DescripcionCorta>
                </com:tipo>
                <com:importe>{fn:data($desembolso/ns1:MONTO_DESEMBOLSAR_USD)}</com:importe>
                <com:moneda>
                    <cat:codigoExterno>USD</cat:codigoExterno>
                </com:moneda>
            </des:monto>
            <des:estado>
                <cat:Id>{fn:data($desembolso/ns1:ID_TCA_ESTADO)}</cat:Id>
                <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_ESTADO)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($desembolso/ns1:DESCRIPCION_CORTA_ESTADO)}</cat:DescripcionCorta>
                <cat:estatus>{fn:data($desembolso/ns1:BAN_ESTATUS_ESTADO)}</cat:estatus>
                <cat:codigoExterno>{fn:data($desembolso/ns1:CODIGO_EXTERNO_ESTADO)}</cat:codigoExterno>
            </des:estado>
            <des:programado>{fn:data($desembolso/ns1:PROGRAMADO)}</des:programado>
            <des:fechaEstimadaDesembolsar>{fn:data($desembolso/ns1:FECHA_ESTIMADA_DESEMBOLSAR)}</des:fechaEstimadaDesembolsar>
            <des:fechaEfectiva>{fn:data($desembolso/ns1:FECHA_EFECTIVA_DESEMBOLSO)}</des:fechaEfectiva>
            <des:fechaVencimiento>{fn:data($desembolso/ns1:FECHA_VENCIMIENTO)}</des:fechaVencimiento>
            <des:condicionesFinancieras>
                <des:idCondicionFinanciera>{fn:data($desembolso/ns1:ID_CONDICION_FINANCIERA[1])}</des:idCondicionFinanciera>
                <des:tasa>
                    <des:tipo>
                        <cat:Id>{fn:data($desembolso/ns1:ID_TIPO_TASA_DESEMBOLSO)}</cat:Id>
                        <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_TASA_DESEMBOLSO)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($desembolso/ns1:DESC_CORTA_TASA_DESEMBOLSO)}</cat:DescripcionCorta>
                        <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_TASA_DESEMBOLSO)}</cat:codigoExterno>
                    </des:tipo>
                    <des:spreadMora>
                        <des:valor>{fn:data($desembolso/ns1:SPREAD_MORA)}</des:valor>
                    </des:spreadMora>
                      {
                        if (fn:data($desembolso/ns1:COD_EXTERNO_TASA_DESEMBOLSO = 'X')) then 
                            <des:fija>
                                <des:valor>{fn:data($desembolso/ns1:TASA_FIJA)}</des:valor>
                            </des:fija>
                        else()
                        }
                        {
                        if ((fn:data($desembolso/ns1:COD_EXTERNO_TASA_DESEMBOLSO) = 'F') or (fn:data($desembolso/ns1:COD_EXTERNO_TASA_DESEMBOLSO)='X')) then 
                                <des:variable>
                                    <des:tasaReferencia>
                                        <cat:Id>{fn:data($desembolso/ns1:CODIGO_TASA_REFERENCIA)}</cat:Id>
                                    </des:tasaReferencia>
                                    <des:spread>
                                        <des:codigo>{fn:data($desembolso/ns1:CODIGO_TASA_REFERENCIA_SPREAD)}</des:codigo>
                                        <des:revision>
                                            <des:FechaInicio>{fn:data($desembolso/ns1:FECHA_PROXIMA_REVISION_SPREAD)}</des:FechaInicio>
                                            <des:Frecuencia>
                                                <com:Tipo>
                                                    <cat:Id>{fn:data($desembolso/ns1:ID_TCA_FRECUENCIA_REV_SPREAD)}</cat:Id>
                                                    <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_FRE_REV_SPREAD)}</cat:Descripcion>
                                                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:DES_CORTA_FRE_REV_SPREAD)}</cat:DescripcionCorta>
                                                    <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_FRE_REV_SPREAD)}</cat:codigoExterno>
                                                </com:Tipo>
                                                <com:Valor>{fn:data($desembolso/ns1:FRECUENCIA_REVISION_SPREAD)}</com:Valor>
                                            </des:Frecuencia>
                                        </des:revision>
                                    </des:spread>
                                    <des:revision>
                                        <des:FechaInicio>{fn:data($desembolso/ns1:FECHA_PROXIMA_REVISION_TASA)}</des:FechaInicio>
                                        <des:Frecuencia>
                                            <com:Tipo>
                                                <cat:Id>{fn:data($desembolso/ns1:ID_TCA_FRECUENCIA_REV_TASA)}</cat:Id>
                                                <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_FREC_REV_TASA)}</cat:Descripcion>
                                                <cat:DescripcionCorta>{fn:data($desembolso/ns1:DES_CORTA_FREC_REV_TASA)}</cat:DescripcionCorta>
                                                <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_FREC_REV_TASA)}</cat:codigoExterno>
                                            </com:Tipo>
                                            <com:Valor>{fn:data($desembolso/ns1:FRECUENCIA_REVISION_TASA)}</com:Valor>
                                        </des:Frecuencia>
                                    </des:revision>
                                    <des:limite>
                                        <com:maximo>{fn:data($desembolso/ns1:TASA_MAXIMA)}</com:maximo>
                                        <com:minimo>{fn:data($desembolso/ns1:TASA_MINIMA)}</com:minimo>
                                    </des:limite>
                                </des:variable>
                            else()
                            }
                        {
                            if (fn:data($desembolso/ns1:COD_EXTERNO_TASA_DESEMBOLSO = 'S')) then 
                                <des:especial>
                                    <des:valor>{fn:data($desembolso/ns1:MONTO_ESPECIAL)}</des:valor>
                                </des:especial>
                                else()
                            }
                        
                </des:tasa>
                <des:fondo>
                    <cat:Id>{fn:data($desembolso/ns1:FONDO)}</cat:Id>
                </des:fondo>
                <des:baseCalculo>
                    <cat:Id>{fn:data($desembolso/ns1:ID_TCA_BASE_CALCULO)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_BASE_CALCULO)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:DESCRIPCION_CORTA_BASE_CALCULO)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_BASE_CALCULO)}</cat:codigoExterno>
                </des:baseCalculo>
                <des:principal>
                    <des:FechaInicio>{fn:data($desembolso/ns1:FECHA_PRIMER_PAGO_CAPITAL)}</des:FechaInicio>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id>{fn:data($desembolso/ns1:ID_TCA_FRECUENCIA_PAGO_CAPITAL)}</cat:Id>
                            <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_F_PAGO_CAPITAL)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($desembolso/ns1:DES_CORTA_F_PAGO_CAPITAL)}</cat:DescripcionCorta>
                            <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_F_PAGO_CAPITAL)}</cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor>{fn:data($desembolso/ns1:FRECUENCIA_PAGO_CAPITAL)}</com:Valor>
                    </des:Frecuencia>
                    <des:fechaVencimiento></des:fechaVencimiento>
                </des:principal>
                <des:interes>
                    <des:FechaInicio>{fn:data($desembolso/ns1:FECHA_PROXIMO_PAGO_INTERES)}</des:FechaInicio>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id>{fn:data($desembolso/ns1:ID_TCA_FRECUENCIA_PAGO_INTERES)}</cat:Id>
                            <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_FREC_PAGO_INTERES)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($desembolso/ns1:DES_CORTA_FREC_PAGO_INTERES)}</cat:DescripcionCorta>
                            <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_FREC_PAGO_INTERES)}</cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor>{fn:data($desembolso/ns1:FRECUENCIA_PAGO_INTERES)}</com:Valor>
                    </des:Frecuencia>
                </des:interes>
                <des:periodoGracia>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id>{fn:data($desembolso/ns1:ID_TCA_FRECUENCIA_PERIODO_GRA)}</cat:Id>
                            <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_PERIODO_GRA)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($desembolso/ns1:DESCRIPCION_CORTA_PERIODO_GRA)}</cat:DescripcionCorta>
                            <cat:estatus>{fn:data($desembolso/ns1:ID_TCA_ESPECIFICACION_FECHA)}</cat:estatus>
                            <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_PERIODO_GRA)}</cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor>{fn:data($desembolso/ns1:FRECUENCIA_PERIODO_GRACIA)}</com:Valor>
                    </des:Frecuencia>
                </des:periodoGracia>
                <des:tratamientoDiasFeriados>{fn:data($desembolso/ns1:TRATAMIENTO_DIAS_FERIADOS)}</des:tratamientoDiasFeriados>
                <des:tipoCalendario>
                    <cat:Id>{fn:data($desembolso/ns1:ID_TCA_TIPO_CALENDARIO)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_CALENDARIO)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:DESCRIPCION_CORTA_CALENDARIO)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_CALENDARIO)}</cat:codigoExterno>
                </des:tipoCalendario>
                <des:moverEntreMeses>
                {
                if($desembolso/ns1:SE_PUEDE_MOVER_ENTRE_MESES/text()=1)
                then true()
                else false()
                }
                </des:moverEntreMeses>
            </des:condicionesFinancieras>
            <des:idLinea>{fn:data($desembolso/ns1:ID_LINEA_CREDITO)}</des:idLinea>
            <des:recursosExternos>{fn:data($desembolso/ns1:RECURSOS_EXTERNOS)}</des:recursosExternos>
            <des:cuentaCliente>{fn:data($desembolso/ns1:CUENTA_CLIENTE)}</des:cuentaCliente>
            <des:usuario>{fn:data($desembolso/ns1:USUARIO)}</des:usuario>
            <des:fechaDisponibilidadFondos>{fn:data($desembolso/ns1:FECHA_DISPONIBILIDAD_FONDOS)}</des:fechaDisponibilidadFondos>
            <des:origenTransferenciaCte>{fn:data($desembolso/ns1:ORIGEN_TRANFERENCIA_CLIENTE)}</des:origenTransferenciaCte>
            <des:Programa>
                <her:LineaFinanciera>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:PROGRAMA_OPERACION)}</cat:codigoExterno>
                </her:LineaFinanciera>
                <her:DestinoFinanciamiento>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:DESTINO_FINANCIAMIENTO)}</cat:codigoExterno>
                </her:DestinoFinanciamiento>
            </des:Programa>
            <des:EstimadoDesembolso>
                <des:Plazo>{fn:data($desembolso/ns1:FRECUENCIA_PLAZO)}</des:Plazo>
                <des:Frecuencia>
                    <cat:Id>{fn:data($desembolso/ns1:ID_TCA_FRECUENCIA_PLAZO)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:DESCRIPCION_FRECUENCIA_PLAZO)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:DESCRIPCION_CORTA_FREC_PLAZO)}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_FREC_PLAZO)}</cat:codigoExterno>
                </des:Frecuencia>
            </des:EstimadoDesembolso>
            <des:fechaInicioProceso>{fn:data($desembolso/ns1:FECHA_INICIO_PROCESO_DESEM)}</des:fechaInicioProceso>
            <des:fechaEstimadaDisponibilidad>{fn:data($desembolso/ns1:FECHA_ESTIMADA_DISP_RECURSOS)}</des:fechaEstimadaDisponibilidad>
            {
              if((string-length($desembolso/ns1:FUENTE)) != 0)
              then(
             for $idUtilizacion in distinct-values($ConsultarDesembolsosConfiguracionById/ns1:ConsultarDesembolsosConfiguracionByIdOutput/ns1:FUENTE)
             let $utilizacion := $ConsultarDesembolsosConfiguracionById/ns1:ConsultarDesembolsosConfiguracionByIdOutput[ns1:FUENTE = $idUtilizacion ][1]
             where $utilizacion/ns1:ID_DESEMBOLSO = $idDesembolso
             return
            <des:Utilizacion>
                <lin:idFuente>{fn:data($utilizacion/ns1:FUENTE)}</lin:idFuente>
                <lin:idLineaPasiva>{fn:data($utilizacion/ns1:ID_LINEA_PASIVA)}</lin:idLineaPasiva>
                <lin:MontoAsignado>{fn:data($utilizacion/ns1:MONTO_ASIGNADO_FUENTE)}</lin:MontoAsignado>
                <lin:Monto>
                    <com:tipo>
                        <cat:DescripcionCorta>DESEMBOLSO</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($utilizacion/ns1:MONTO_DESEMBOLSAR_FUENTE)}</com:importe>
                </lin:Monto>
                <lin:Monto>
                    <com:tipo>
                        <cat:DescripcionCorta>DISPONIBLE</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($utilizacion/ns1:MONTO_DISPONIBLE_FUENTE)}</com:importe>
                </lin:Monto>
                    <lin:Monto>
                    <com:tipo>
                        <cat:DescripcionCorta>RESERVA_TOTAL</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($utilizacion/ns1:RESERVA_TOTAL_FUENTE)}</com:importe>
                </lin:Monto>
                <lin:NumeroAsignacion>{fn:data($utilizacion/ns1:NUMERO_ASIGNACION)}</lin:NumeroAsignacion>
                <lin:esExterna>{if(fn:string($utilizacion/ns1:ES_EXTERNO)='1')then 'true'else('false')}</lin:esExterna>
            </des:Utilizacion>
            )
            else()
          }
                    {
          if(string-length($desembolso/ns1:CARGO_DESEMBOLSO) != 0) then (
           for $idCargo in distinct-values($ConsultarDesembolsosConfiguracionById/ns1:ConsultarDesembolsosConfiguracionByIdOutput/ns1:CARGO_DESEMBOLSO)
           let $cargo := $ConsultarDesembolsosConfiguracionById/ns1:ConsultarDesembolsosConfiguracionByIdOutput[ns1:CARGO_DESEMBOLSO = $idCargo ][1]
           where $cargo/ns1:ID_DESEMBOLSO = $idDesembolso
           return
          <des:Cargo>
                <cat:Id>{fn:data($cargo/ns1:CARGO_DESEMBOLSO)}</cat:Id>
                <cat:Descripcion>{fn:data($cargo/ns1:CARGO_DESEMBOLSO)}</cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno>{fn:data($cargo/ns1:COMPONENTE)}</cat:codigoExterno>
                <des:Monto>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta>CARGO_DESEMBOLSO</cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:tipo>
                    <com:importe>{fn:data($cargo/ns1:MONTO_CARGO)}</com:importe>
                </des:Monto>
                <des:FechaRegistro>{fn:data($cargo/ns1:FECHA_REGISTRO_CARGO)}</des:FechaRegistro>
            </des:Cargo>)
            else()}
            <des:TransferenciaFT05>
                <des:idTransferenciaFT05>{fn:data($desembolso/ns1:ID_TRANSFERENCIA_FT05)}</des:idTransferenciaFT05>
                <des:idDesembolso></des:idDesembolso>
                <des:idFacturador>{fn:data($desembolso/ns1:BHQ_TRANSFERENCIA_FT05)}</des:idFacturador>
                <des:FechaEfectiva>{fn:data($desembolso/ns1:FECHA_EFECTIVA_FT05)}</des:FechaEfectiva>
            </des:TransferenciaFT05>
            <des:HerramientaCE>
                <her:ActividadEconomica>
                    <cat:Id>{fn:data($desembolso/ns1:ID_CAT_ACTIVIDAD_ECONOMICA)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:DES_ACTIVIDAD_ECONOMICA)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:DES_CORTA_ACTIVIDAD_ECONOMICA)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXT_ACTIVIDAD_ECONOMICA)}</cat:codigoExterno>
                </her:ActividadEconomica>
                <her:EjeEstrategico>
                    <cat:Id>{fn:data($desembolso/ns1:ID_CAT_EJE_ESTRATEGICO)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:DES_EJE_ESTRATEGICO)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:DES_CORTA_EJE_ESTRATEGICO)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_EJE_ESTRATEGICO)}</cat:codigoExterno>
                </her:EjeEstrategico>
                <her:AreaFocalizacion>
                    <cat:Id>{fn:data($desembolso/ns1:ID_CAT_AREA_FOCALIZACION)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:DES_AREA_FOCALIZACION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:DES_CORTA_AREA_FOCALIZACION)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:COD_EXTERNO_AREA_FOCALIZACION)}</cat:codigoExterno>
                </her:AreaFocalizacion>
            </des:HerramientaCE>
          
            <des:modalidadFinan>
                <cat:Id>{fn:data($desembolso/ns1:ID_TCA_MODALIDAD_FINAN )}</cat:Id>
            </des:modalidadFinan>
         
        </ns2:ContratoDesembolso>
        }
    <ns2:Resultado>
        <res:result>OK</res:result>
        {if($ConsultarDesembolsosConfiguracionById/ns1:ConsultarDesembolsosConfiguracionByIdOutput/ns1:ID_DESEMBOLSO)then
          <res:message>Operación realizada correctamente</res:message>
        else
          <res:message>Consulta sin resultados</res:message>
        }   
    </ns2:Resultado>
    </ns2:ConsultarDesembolsosConfiguracionByIdResponse>
};

local:func($ConsultarDesembolsosConfiguracionById)