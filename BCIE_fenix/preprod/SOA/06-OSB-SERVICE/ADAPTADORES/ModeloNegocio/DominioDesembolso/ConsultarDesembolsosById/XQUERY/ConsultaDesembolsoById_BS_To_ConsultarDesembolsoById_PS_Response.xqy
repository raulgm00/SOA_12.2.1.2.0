xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultaDesembolsoById";
(:: import schema at "../XSD/ConsultaDesembolsoById_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarDesembolsosByIdResponse) ::) {
    <ns2:ConsultarDesembolsosByIdResponse>
    {               
        for $idDesembolso in distinct-values($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_DESEMBOLSO'])
        let $desembolso := $OutputParameters/ns1:RECORDSET/ns1:Row[ns1:Column[@name='ID_DESEMBOLSO'] = $idDesembolso ][1]
        return
        <ns2:ContratoDesembolso>
        <des:idDesembolso>{fn:data($desembolso/ns1:Column[@name='ID_DESEMBOLSO'] )}</des:idDesembolso>
        <des:idFacturador>{fn:data($desembolso/ns1:Column[@name='CONTRATO_FLEXCUBE'])}</des:idFacturador>
        <des:producto>
            <pro:codExterno>{fn:data($desembolso/ns1:Column[@name='ID_PRODUCTO_FLEXCUBE'])}</pro:codExterno>
            {
              if((string-length($desembolso/ns1:Column[@name='ID_PRODUCTO_FLEXCUBE'])) != 0)
              then(
             for $idComponente in distinct-values($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='CODIGO_PROD_FLEX'])
             let $componente := $OutputParameters/ns1:RECORDSET/ns1:Row[ns1:Column[@name='CODIGO_PROD_FLEX'] = $idComponente ][1]
             where $componente/ns1:Column[@name='ID_DESEMBOLSO'] = $idDesembolso
             return
             <des:Componente>
                <cat:Descripcion>{fn:data($componente/ns1:Column[@name='DESCRIPCION_PROD_FLEX'])}</cat:Descripcion>
                <cat:codigoExterno>{fn:data($componente/ns1:Column[@name='CODIGO_PROD_FLEX'])}</cat:codigoExterno>
                <des:TipoComponente>
                    {if (fn:data($componente/ns1:Column[@name='PRINCIPAL_PROD_FLEX'] = 'Y') and string-length($componente/ns1:Column[@name='CODIGO_COMPONENTE_PROD_FLEX']) = 0)
                    then
                    <cat:codigoExterno>INTERES</cat:codigoExterno>
                    else
                    <cat:codigoExterno>{fn:data($componente/ns1:Column[@name='CODIGO_COMPONENTE_PROD_FLEX'])}</cat:codigoExterno>
                    }
                </des:TipoComponente>
                <des:TipoTasa>
                    <cat:codigoExterno>{fn:data($componente/ns1:Column[@name='TIPO_TASA_PROD_FLEX'])}</cat:codigoExterno>
                </des:TipoTasa>
                {if (fn:data($componente/ns1:Column[@name='PRINCIPAL_PROD_FLEX'] = 'Y'))
                then
                <des:esPrincipal>{true()}</des:esPrincipal>
                else
                <des:esPrincipal>{false()}</des:esPrincipal>
                }
            </des:Componente>
            )
            else()
          }
        </des:producto>
        <des:referencia>{fn:data($desembolso/ns1:Column[@name='REFERENCIA'])}</des:referencia>
            <des:monto>
                <com:tipo>
                    <cat:DescripcionCorta>DESEMBOLSO</cat:DescripcionCorta>
                </com:tipo>
                <com:importe>{fn:data($desembolso/ns1:Column[@name='MONTO_DESEMBOLSAR'])}</com:importe>
                <com:moneda>
                    <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_MONEDA'])}</cat:Id>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_MONEDA'])}</cat:codigoExterno>
                </com:moneda>
            </des:monto>
            <des:monto>
                <com:tipo>
                    <cat:DescripcionCorta>DESEMBOLSO_USD</cat:DescripcionCorta>
                </com:tipo>
                <com:importe>{fn:data($desembolso/ns1:Column[@name='MONTO_DESEMBOLSAR_USD'])}</com:importe>
                <com:moneda>
                    <cat:codigoExterno>USD</cat:codigoExterno>
                </com:moneda>
            </des:monto>
            <des:estado>
                <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_ESTADO'])}</cat:Id>
                <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_ESTADO'])}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_CORTA_ESTADO'])}</cat:DescripcionCorta>
                <cat:estatus>{fn:data($desembolso/ns1:Column[@name='BAN_ESTATUS_ESTADO'])}</cat:estatus>
                <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='CODIGO_EXTERNO_ESTADO'])}</cat:codigoExterno>
            </des:estado>
            <des:programado>{fn:data($desembolso/ns1:Column[@name='PROGRAMADO'])}</des:programado>
            <des:fechaEstimadaDesembolsar>{fn:data($desembolso/ns1:Column[@name='FECHA_ESTIMADA_DESEMBOLSAR'])}</des:fechaEstimadaDesembolsar>
            <des:fechaEfectiva>{fn:data($desembolso/ns1:Column[@name='FECHA_EFECTIVA_DESEMBOLSO'])}</des:fechaEfectiva>
            <des:fechaVencimiento>{fn:data($desembolso/ns1:Column[@name='FECHA_VENCIMIENTO'])}</des:fechaVencimiento>
            <des:condicionesFinancieras>
                <des:idCondicionFinanciera>{fn:data($desembolso/ns1:Column[@name='ID_CONDICION_FINANCIERA'][1])}</des:idCondicionFinanciera>
                <des:tasa>
                    <des:tipo>
                        <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TIPO_TASA_DESEMBOLSO'])}</cat:Id>
                        <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_TASA_DESEMBOLSO'])}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DESC_CORTA_TASA_DESEMBOLSO'])}</cat:DescripcionCorta>
                        <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_TASA_DESEMBOLSO'])}</cat:codigoExterno>
                    </des:tipo>
                    <des:spreadMora>
                        <des:valor>{fn:data($desembolso/ns1:Column[@name='SPREAD_MORA'])}</des:valor>
                    </des:spreadMora>
                      {
                        if (fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_TASA_DESEMBOLSO'] = 'X')) then 
                            <des:fija>
                                <des:valor>{fn:data($desembolso/ns1:Column[@name='TASA_FIJA'])}</des:valor>
                            </des:fija>
                        else()
                        }
                        {
                        if ((fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_TASA_DESEMBOLSO']) = 'F') or (fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_TASA_DESEMBOLSO'])='X')) then 
                                <des:variable>
                                    <des:tasaReferencia>
                                        <cat:Id>{fn:data($desembolso/ns1:Column[@name='CODIGO_TASA_REFERENCIA'])}</cat:Id>
                                        <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_TASA_REFERENCIA'])}</cat:Descripcion>
                                        <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DESC_CORTA_TASA_REFERENCIA'])}</cat:DescripcionCorta>
                                        <des:valor>{fn:data($desembolso/ns1:Column[@name='VALOR_TASA_REFERENCIA'])}</des:valor>
                                    </des:tasaReferencia>
                                    <des:spread>
                                        <des:valor>{fn:data($desembolso/ns1:Column[@name='SPREAD_TASA'])}</des:valor>
                                        <des:codigo>{fn:data($desembolso/ns1:Column[@name='CODIGO_TASA_REFERENCIA_SPREAD'])}</des:codigo>
                                        <des:revision>
                                            <des:FechaInicio>{fn:data($desembolso/ns1:Column[@name='FECHA_PROXIMA_REVISION_SPREAD'])}</des:FechaInicio>
                                            <des:Frecuencia>
                                                <com:Tipo>
                                                    <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_FRECUENCIA_REV_SPREAD'])}</cat:Id>
                                                    <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_FRE_REV_SPREAD'])}</cat:Descripcion>
                                                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DES_CORTA_FRE_REV_SPREAD'])}</cat:DescripcionCorta>
                                                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_FRE_REV_SPREAD'])}</cat:codigoExterno>
                                                </com:Tipo>
                                                <com:Valor>{fn:data($desembolso/ns1:Column[@name='FRECUENCIA_REVISION_SPREAD'])}</com:Valor>
                                            </des:Frecuencia>
                                        </des:revision>
                                    </des:spread>
                                    <des:revision>
                                        <des:FechaInicio>{fn:data($desembolso/ns1:Column[@name='FECHA_PROXIMA_REVISION_TASA'])}</des:FechaInicio>
                                        <des:Frecuencia>
                                            <com:Tipo>
                                                <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_FRECUENCIA_REV_TASA'])}</cat:Id>
                                                <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_FREC_REV_TASA'])}</cat:Descripcion>
                                                <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DES_CORTA_FREC_REV_TASA'])}</cat:DescripcionCorta>
                                                <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_FREC_REV_TASA'])}</cat:codigoExterno>
                                            </com:Tipo>
                                            <com:Valor>{fn:data($desembolso/ns1:Column[@name='FRECUENCIA_REVISION_TASA'])}</com:Valor>
                                        </des:Frecuencia>
                                    </des:revision>
                                    <des:limite>
                                        <com:maximo>{fn:data($desembolso/ns1:Column[@name='TASA_MAXIMA'])}</com:maximo>
                                        <com:minimo>{fn:data($desembolso/ns1:Column[@name='TASA_MINIMA'])}</com:minimo>
                                    </des:limite>
                                </des:variable>
                            else()
                            }
                        {
                            if (fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_TASA_DESEMBOLSO'] = 'S')) then 
                                <des:especial>
                                    <des:valor>{fn:data($desembolso/ns1:Column[@name='MONTO_ESPECIAL'])}</des:valor>
                                </des:especial>
                                else()
                            }
                        
                </des:tasa>
                <des:fondo>
                    <cat:Id>{fn:data($desembolso/ns1:Column[@name='FONDO'])}</cat:Id>
                </des:fondo>
                <des:baseCalculo>
                    <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_BASE_CALCULO'])}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_BASE_CALCULO'])}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_CORTA_BASE_CALCULO'])}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_BASE_CALCULO'])}</cat:codigoExterno>
                </des:baseCalculo>
                <des:principal>
                    <des:FechaInicio>{fn:data($desembolso/ns1:Column[@name='FECHA_PRIMER_PAGO_CAPITAL'])}</des:FechaInicio>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_FRECUENCIA_PAGO_CAPITAL'])}</cat:Id>
                            <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_F_PAGO_CAPITAL'])}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DES_CORTA_F_PAGO_CAPITAL'])}</cat:DescripcionCorta>
                            <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_F_PAGO_CAPITAL'])}</cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor>{fn:data($desembolso/ns1:Column[@name='FRECUENCIA_PAGO_CAPITAL'])}</com:Valor>
                    </des:Frecuencia>
                    <des:fechaVencimiento></des:fechaVencimiento>
                </des:principal>
                <des:interes>
                    <des:FechaInicio>{fn:data($desembolso/ns1:Column[@name='FECHA_PROXIMO_PAGO_INTERES'])}</des:FechaInicio>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_FRECUENCIA_PAGO_INTERES'])}</cat:Id>
                            <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_FREC_PAGO_INTERES'])}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DES_CORTA_FREC_PAGO_INTERES'])}</cat:DescripcionCorta>
                            <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_FREC_PAGO_INTERES'])}</cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor>{fn:data($desembolso/ns1:Column[@name='FRECUENCIA_PAGO_INTERES'])}</com:Valor>
                    </des:Frecuencia>
                </des:interes>
                <des:periodoGracia>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_FRECUENCIA_PERIODO_GRA'])}</cat:Id>
                            <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_PERIODO_GRA'])}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_CORTA_PERIODO_GRA'])}</cat:DescripcionCorta>
                            <cat:estatus>{fn:data($desembolso/ns1:Column[@name='ID_TCA_ESPECIFICACION_FECHA'])}</cat:estatus>
                            <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_PERIODO_GRA'])}</cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor>{fn:data($desembolso/ns1:Column[@name='FRECUENCIA_PERIODO_GRACIA'])}</com:Valor>
                    </des:Frecuencia>
                </des:periodoGracia>
                <des:tratamientoDiasFeriados>{fn:data($desembolso/ns1:Column[@name='TRATAMIENTO_DIAS_FERIADOS'])}</des:tratamientoDiasFeriados>
                <des:tipoCalendario>
                    <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_TIPO_CALENDARIO'])}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_CALENDARIO'])}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_CORTA_CALENDARIO'])}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_CALENDARIO'])}</cat:codigoExterno>
                </des:tipoCalendario>
                <des:moverEntreMeses>
                {
                if($desembolso/ns1:Column[@name='SE_PUEDE_MOVER_ENTRE_MESES']/text()=1)
                then true()
                else false()
                }
                </des:moverEntreMeses>
              {
              for $calendarioComplejo in $OutputParameters/ns1:RECORDSET2/ns1:Row
              where $calendarioComplejo/ns1:Column[@name='ID_CONTRATO_DESEMBOLSO'] = $idDesembolso
              return
                <des:calendarioComplejo>
                    <des:TipoPlan>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </des:TipoPlan>
                    <des:Frecuencia>
                        <des:FechaInicio>{fn:data($calendarioComplejo/ns1:Column[@name='FECHA_PAGO_CALENDARIO'])}</des:FechaInicio>
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
                            <cat:DescripcionCorta>{fn:data($calendarioComplejo/ns1:Column[@name='TIPO_PAGO_CALENDARIO'])}</cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno></cat:codigoExterno>
                        </com:tipo>
                        <com:importe>{fn:data($calendarioComplejo/ns1:Column[@name='MONTO_PAGO_CALENDARIO'])}</com:importe>
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
              }
            </des:condicionesFinancieras>
            <des:idLinea>{fn:data($desembolso/ns1:Column[@name='ID_LINEA_CREDITO'])}</des:idLinea>
            <des:recursosExternos>{fn:data($desembolso/ns1:Column[@name='RECURSOS_EXTERNOS'])}</des:recursosExternos>
            <des:cuentaCliente>{fn:data($desembolso/ns1:Column[@name='CUENTA_CLIENTE'])}</des:cuentaCliente>
            <des:usuario>{fn:data($desembolso/ns1:Column[@name='USUARIO'])}</des:usuario>
            <des:fechaDisponibilidadFondos>{fn:data($desembolso/ns1:Column[@name='FECHA_DISPONIBILIDAD_FONDOS'])}</des:fechaDisponibilidadFondos>
            <des:origenTransferenciaCte>{fn:data($desembolso/ns1:Column[@name='ORIGEN_TRANFERENCIA_CLIENTE'])}</des:origenTransferenciaCte>
            <des:Programa>
                <her:LineaFinanciera>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='PROGRAMA_OPERACION'])}</cat:codigoExterno>
                </her:LineaFinanciera>
                <her:DestinoFinanciamiento>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='DESTINO_FINANCIAMIENTO'])}</cat:codigoExterno>
                </her:DestinoFinanciamiento>
            </des:Programa>
            <des:EstimadoDesembolso>
                <des:Plazo>{fn:data($desembolso/ns1:Column[@name='FRECUENCIA_PLAZO'])}</des:Plazo>
                <des:Frecuencia>
                    <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_FRECUENCIA_PLAZO'])}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_FRECUENCIA_PLAZO'])}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DESCRIPCION_CORTA_FREC_PLAZO'])}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_FREC_PLAZO'])}</cat:codigoExterno>
                </des:Frecuencia>
            </des:EstimadoDesembolso>
            <des:fechaInicioProceso>{fn:data($desembolso/ns1:Column[@name='FECHA_INICIO_PROCESO_DESEM'])}</des:fechaInicioProceso>
            <des:fechaEstimadaDisponibilidad>{fn:data($desembolso/ns1:Column[@name='FECHA_ESTIMADA_DISP_RECURSOS'])}</des:fechaEstimadaDisponibilidad>
            {
              if((string-length($desembolso/ns1:Column[@name='FUENTE'])) != 0)
              then(
             for $idUtilizacion in distinct-values($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='FUENTE'])
             let $utilizacion := $OutputParameters/ns1:RECORDSET/ns1:Row[ns1:Column[@name='FUENTE'] = $idUtilizacion ][1]
             where $utilizacion/ns1:Column[@name='ID_DESEMBOLSO'] = $idDesembolso
             return
            <des:Utilizacion>
                <lin:idFuente>{fn:data($utilizacion/ns1:Column[@name='FUENTE'])}</lin:idFuente>
                <lin:idLineaPasiva>{fn:data($utilizacion/ns1:Column[@name='ID_LINEA_PASIVA'])}</lin:idLineaPasiva>
                <lin:MontoAsignado>{fn:data($utilizacion/ns1:Column[@name='MONTO_ASIGNADO_FUENTE'])}</lin:MontoAsignado>
                <lin:Monto>
                    <com:tipo>
                        <cat:DescripcionCorta>DESEMBOLSO</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($utilizacion/ns1:Column[@name='MONTO_DESEMBOLSAR_FUENTE'])}</com:importe>
                </lin:Monto>
                <lin:Monto>
                    <com:tipo>
                        <cat:DescripcionCorta>DISPONIBLE</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($utilizacion/ns1:Column[@name='MONTO_DISPONIBLE_FUENTE'])}</com:importe>
                </lin:Monto>
                    <lin:Monto>
                    <com:tipo>
                        <cat:DescripcionCorta>RESERVA_TOTAL</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($utilizacion/ns1:Column[@name='RESERVA_TOTAL_FUENTE'])}</com:importe>
                </lin:Monto>
                <lin:NumeroAsignacion>{fn:data($utilizacion/ns1:Column[@name='NUMERO_ASIGNACION'])}</lin:NumeroAsignacion>
                <lin:esExterna>{if(fn:string($utilizacion/ns1:Column[@name='ES_EXTERNO'])='1')then 'true'else('false')}</lin:esExterna>
            </des:Utilizacion>
            )
            else()
          }
                    {
          if(string-length($desembolso/ns1:Column[@name='CARGO_DESEMBOLSO']) != 0) then (
           for $idCargo in distinct-values($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='CARGO_DESEMBOLSO'])
           let $cargo := $OutputParameters/ns1:RECORDSET/ns1:Row[ns1:Column[@name='CARGO_DESEMBOLSO'] = $idCargo ][1]
           where $cargo/ns1:Column[@name='ID_DESEMBOLSO'] = $idDesembolso
           return
          <des:Cargo>
                <cat:Id>{fn:data($cargo/ns1:Column[@name='CARGO_DESEMBOLSO'])}</cat:Id>
                <cat:Descripcion>{fn:data($cargo/ns1:Column[@name='CARGO_DESEMBOLSO'])}</cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno>{fn:data($cargo/ns1:Column[@name='COMPONENTE'])}</cat:codigoExterno>
                <des:Monto>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta>CARGO_DESEMBOLSO</cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:tipo>
                    <com:importe>{fn:data($cargo/ns1:Column[@name='MONTO_CARGO'])}</com:importe>
                </des:Monto>
                <des:FechaRegistro>{fn:data($cargo/ns1:Column[@name='FECHA_REGISTRO_CARGO'])}</des:FechaRegistro>
            </des:Cargo>)
            else()}
            <des:TransferenciaFT05>
                <des:idTransferenciaFT05>{fn:data($desembolso/ns1:Column[@name='ID_TRANSFERENCIA_FT05'])}</des:idTransferenciaFT05>
                <des:idDesembolso></des:idDesembolso>
                <des:idFacturador>{fn:data($desembolso/ns1:Column[@name='BHQ_TRANSFERENCIA_FT05'])}</des:idFacturador>
                <des:FechaEfectiva>{fn:data($desembolso/ns1:Column[@name='FECHA_EFECTIVA_FT05'])}</des:FechaEfectiva>
            </des:TransferenciaFT05>
            <des:HerramientaCE>
                <her:ActividadEconomica>
                    <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_CAT_ACTIVIDAD_ECONOMICA'])}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DES_ACTIVIDAD_ECONOMICA'])}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DES_CORTA_ACTIVIDAD_ECONOMICA'])}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXT_ACTIVIDAD_ECONOMICA'])}</cat:codigoExterno>
                </her:ActividadEconomica>
                <her:EjeEstrategico>
                    <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_CAT_EJE_ESTRATEGICO'])}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DES_EJE_ESTRATEGICO'])}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DES_CORTA_EJE_ESTRATEGICO'])}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_EJE_ESTRATEGICO'])}</cat:codigoExterno>
                </her:EjeEstrategico>
                <her:AreaFocalizacion>
                    <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_CAT_AREA_FOCALIZACION'])}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ns1:Column[@name='DES_AREA_FOCALIZACION'])}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ns1:Column[@name='DES_CORTA_AREA_FOCALIZACION'])}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($desembolso/ns1:Column[@name='COD_EXTERNO_AREA_FOCALIZACION'])}</cat:codigoExterno>
                </her:AreaFocalizacion>
            </des:HerramientaCE>
          
            <des:modalidadFinan>
                <cat:Id>{fn:data($desembolso/ns1:Column[@name='ID_TCA_MODALIDAD_FINAN'] )}</cat:Id>
            </des:modalidadFinan>
         
        </ns2:ContratoDesembolso>
        }
    <ns2:Resultado>
        <res:result>OK</res:result>
        {if($OutputParameters/ns1:RECORDSET/ns1:Row/ns1:Column[@name='ID_DESEMBOLSO'])then
          <res:message>Operación realizada correctamente</res:message>
        else
          <res:message>Consulta sin resultados</res:message>
        }   
    </ns2:Resultado>
    </ns2:ConsultarDesembolsosByIdResponse>
};

local:func($OutputParameters)