xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarDesembolsoByIdV2";
(:: import schema at "../XSD/ConsultarDesembolsoByIdV2_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBOV2";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarDesembolsoByIdV2OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($ConsultarDesembolsoByIdV2OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarDesembolsosByIdV2Response) ::) {
    <ns2:ConsultarDesembolsosByIdV2Response>
      {
        for $desembolso in $ConsultarDesembolsoByIdV2OutputParameters/ns1:P_DESEMBOLSOS/DESEMBOLSOS/DESEMBOLSO
        return
         <ns2:ContratoDesembolso>
            <des:idDesembolso>{fn:data($desembolso/ID_DESEMBOLSO )}</des:idDesembolso>
            <des:idFacturador>{fn:data($desembolso/ID_FACTURADOR )}</des:idFacturador>
            <des:producto>
                <pro:codExterno>{fn:data($desembolso/PRODUCTO/COD_EXTERNO )}</pro:codExterno>
            </des:producto>
            <des:referencia>{fn:data($desembolso/REFERENCIA )}</des:referencia>
            {
            for $monto in $desembolso/MONTO
            return
              <des:monto>
                  <com:tipo>
                      <cat:DescripcionCorta>{fn:data($monto/TIPO/DESCRIPCION_CORTA )}</cat:DescripcionCorta>
                  </com:tipo>
                  <com:importe>{fn:data($monto/IMPORTE )}</com:importe>
                  <com:moneda>
                      <cat:Id>{fn:data($monto/MONEDA/ID )}</cat:Id>
                      <cat:codigoExterno>{fn:data($monto/MONEDA/CODIGO_EXTERNO )}</cat:codigoExterno>
                  </com:moneda>
              </des:monto>
            }
            <des:estado>
                <cat:Id>{fn:data($desembolso/ESTADO/ID )}</cat:Id>
                <cat:Descripcion>{fn:data($desembolso/ESTADO/DESCRIPCION)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($desembolso/ESTADO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                <cat:estatus>{fn:data($desembolso/ESTADO/ESTATUS)}</cat:estatus>
                <cat:codigoExterno>{fn:data($desembolso/ESTADO/CODIGO_EXTERNO)}</cat:codigoExterno>
            </des:estado>
            <des:programado>{fn:data($desembolso/PROGRAMADO)}</des:programado>
            <des:fechaEstimadaDesembolsar>{fn:data($desembolso/FECHA_ESTIMADA_DESEMBOLSAR)}</des:fechaEstimadaDesembolsar>
            <des:fechaEfectiva>{fn:data($desembolso/FECHA_EFECTIVA)}</des:fechaEfectiva>
            <des:fechaVencimiento>{fn:data($desembolso/FECHA_VENCIMIENTO)}</des:fechaVencimiento>
            <des:condicionesFinancieras>
                <des:idCondicionFinanciera>{fn:data($desembolso/CONDICIONES_FINANCIERAS/ID_CONDICION_FINANCIERA)}</des:idCondicionFinanciera>
                <des:fondo>
                    <cat:Id>{fn:data($desembolso/CONDICIONES_FINANCIERAS/FONDO/ID)}</cat:Id>
                </des:fondo>
                <des:baseCalculo>
                    <cat:Id>{fn:data($desembolso/CONDICIONES_FINANCIERAS/BASE_CALCULO/ID)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/CONDICIONES_FINANCIERAS/BASE_CALCULO/DESCRIPCION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/CONDICIONES_FINANCIERAS/BASE_CALCULO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($desembolso/CONDICIONES_FINANCIERAS/BASE_CALCULO/CODIGO_EXTERNO)}</cat:codigoExterno>
                </des:baseCalculo>
                <des:periodoGracia>
                    <des:Frecuencia>
                        <com:Tipo>
                            <cat:Id>{fn:data($desembolso/CONDICIONES_FINANCIERAS/PERIODO_GRACIA/FRECUENCIA/TIPO/ID)}</cat:Id>
                            <cat:Descripcion>{fn:data($desembolso/CONDICIONES_FINANCIERAS/PERIODO_GRACIA/FRECUENCIA/TIPO/DESCRIPCION)}</cat:Descripcion>
                            <cat:DescripcionCorta>{fn:data($desembolso/CONDICIONES_FINANCIERAS/PERIODO_GRACIA/FRECUENCIA/TIPO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                            <cat:estatus>{fn:data($desembolso/CONDICIONES_FINANCIERAS/PERIODO_GRACIA/FRECUENCIA/TIPO/ESTATUS)}</cat:estatus>
                            <cat:codigoExterno>{fn:data($desembolso/CONDICIONES_FINANCIERAS/PERIODO_GRACIA/FRECUENCIA/TIPO/CODIGO_EXTERNO)}</cat:codigoExterno>
                        </com:Tipo>
                        <com:Valor>{fn:data($desembolso/CONDICIONES_FINANCIERAS/PERIODO_GRACIA/FRECUENCIA/VALOR)}</com:Valor>
                    </des:Frecuencia>
                </des:periodoGracia>
                <des:tratamientoDiasFeriados>{fn:data($desembolso/CONDICIONES_FINANCIERAS/TRATAMIENTO_DIAS_FERIADOS)}</des:tratamientoDiasFeriados>
                <des:tipoCalendario>
                    <cat:Id>{fn:data($desembolso/CONDICIONES_FINANCIERAS/TIPO_CALENDARIO/ID)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/CONDICIONES_FINANCIERAS/TIPO_CALENDARIO/DESCRIPCION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/CONDICIONES_FINANCIERAS/TIPO_CALENDARIO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($desembolso/CONDICIONES_FINANCIERAS/TIPO_CALENDARIO/CODIGO_EXTERNO)}</cat:codigoExterno>
                </des:tipoCalendario>
                <des:moverEntreMeses>
                {
                if($desembolso/CONDICIONES_FINANCIERAS/MOVER_ENTRE_MESES/text()=1)
                then true()
                else false()
                }
                </des:moverEntreMeses>
                {
                for $componente in $desembolso/CONDICIONES_FINANCIERAS/COMPONENTES/COMPONENTE
                return
                <des:Componente>
                    <cat:Id>{fn:data($componente/ID)}</cat:Id>
                    <des:TipoComponente>
                        <cat:Id>{fn:data($componente/TIPO_COMPONENTE/ID)}</cat:Id>
                        <cat:Descripcion>{fn:data($componente/TIPO_COMPONENTE/DESCRIPCION)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($componente/TIPO_COMPONENTE/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                        <cat:codigoExterno>{fn:data($componente/TIPO_COMPONENTE/CODIGO_EXTERNO)}</cat:codigoExterno>
                    </des:TipoComponente>
                    <des:TipoTasa>
                        <cat:Id>{fn:data($componente/TIPO_TASA/ID)}</cat:Id>
                        <cat:Descripcion>{fn:data($componente/TIPO_TASA/DESCRIPCION)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($componente/TIPO_TASA/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                        <cat:codigoExterno>{fn:data($componente/TIPO_TASA/CODIGO_EXTERNO)}</cat:codigoExterno>
                    </des:TipoTasa>
                    <des:BaseCalculo>
                        <cat:Id>{fn:data($componente/BASE_CALCULO/ID)}</cat:Id>
                        <cat:Descripcion>{fn:data($componente/BASE_CALCULO/DESCRIPCION)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($componente/BASE_CALCULO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                        <cat:codigoExterno>{fn:data($componente/BASE_CALCULO/CODIGO_EXTERNO)}</cat:codigoExterno>
                    </des:BaseCalculo>
                    <des:esDependiente>{fn:data($componente/ES_DEPENDIENTE)}</des:esDependiente>
                    <des:esFactor>{fn:data($componente/ES_FACTOR)}</des:esFactor>
                    <des:CodigoTasaReferencia>{fn:data($componente/CODIGO_TASA_REFERENCIA)}</des:CodigoTasaReferencia>
                    <des:FechaEfectivaTasaReferencia>{fn:data($componente/FECHA_EFECTIVA_TASA_REFERENCIA)}</des:FechaEfectivaTasaReferencia>
                    <des:ValorTasaReferencia>{fn:data($componente/VALOR_TASA_REFERENCIA)}</des:ValorTasaReferencia>
                    <des:Tasa>{fn:data($componente/TASA)}</des:Tasa>
                    <des:SpreadTasa>{fn:data($componente/SPREAD_TASA)}</des:SpreadTasa>
                    <des:MontoDescuento>{fn:data($componente/MONTO_DESCUENTO)}</des:MontoDescuento>
                    <des:DiasSpotTasa>{fn:data($componente/DIAS_SPOT_TASA)}</des:DiasSpotTasa>
                    <des:TipoRedondeo>
                        <cat:Id>{fn:data($componente/TIPO_REDONDEO/ID)}</cat:Id>
                        <cat:Descripcion>{fn:data($componente/TIPO_REDONDEO/DESCRIPCION)}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($componente/TIPO_REDONDEO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                        <cat:codigoExterno>{fn:data($componente/TIPO_REDONDEO/CODIGO_EXTERNO)}</cat:codigoExterno>
                    </des:TipoRedondeo>
                    <des:CantidadDecimales>{fn:data($componente/CANTIDAD_DECIMALES)}</des:CantidadDecimales>
                    <des:LimiteTasaMinima>{fn:data($componente/LIMITE_TASA_MINIMA)}</des:LimiteTasaMinima>
                    <des:LimiteTasaMaxima>{fn:data($componente/LIMITE_TASA_MAXIMA)}</des:LimiteTasaMaxima>
                    {
                    for $calendario in $componente/CALENDARIOS/CALENDARIO
                    return
                    <des:calendario>
                        <cat:Id>{fn:data($calendario/ID)}</cat:Id>
                        <des:TipoPlan>
                            <cat:codigoExterno>{fn:data($calendario/TIPO_PLAN/CODIGO_EXTERNO)}</cat:codigoExterno>
                        </des:TipoPlan>
                        <des:Frecuencia>
                            <des:FechaInicio>{fn:data($calendario/FRECUENCIA/FECHA_INICIO)}</des:FechaInicio>
                            <des:Frecuencia>
                                <com:Tipo>
                                    <cat:Id>{fn:data($calendario/FRECUENCIA/FRECUENCIA/TIPO/ID)}</cat:Id>
                                    <cat:Descripcion>{fn:data($calendario/FRECUENCIA/FRECUENCIA/TIPO/DESCRIPCION)}</cat:Descripcion>
                                    <cat:DescripcionCorta>{fn:data($calendario/FRECUENCIA/FRECUENCIA/TIPO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                                    <cat:codigoExterno>{fn:data($calendario/FRECUENCIA/FRECUENCIA/TIPO/CODIGO_EXTERNO)}</cat:codigoExterno>
                                </com:Tipo>
                                <com:Valor>{fn:data($calendario/FRECUENCIA/FRECUENCIA/VALOR)}</com:Valor>
                            </des:Frecuencia>
                        </des:Frecuencia>
                        <des:Monto>
                            <com:importe>{fn:data($calendario/MONTO)}</com:importe>
                        </des:Monto>
                        <des:NumeroCuotas>{fn:data($calendario/NUMERO_CUOTAS)}</des:NumeroCuotas>
                    </des:calendario>
                    }
                </des:Componente>
                }
                {
                for $calendario in $desembolso/CONDICIONES_FINANCIERAS/CALENDARIOS/CALENDARIO
                return
                <des:calendario>
                    <cat:Id>{fn:data($calendario/ID)}</cat:Id>
                    <des:TipoPlan>
                        <cat:codigoExterno>{fn:data($calendario/TIPO_PLAN/CODIGO_EXTERNO)}</cat:codigoExterno>
                    </des:TipoPlan>
                    <des:Frecuencia>
                        <des:FechaInicio>{fn:data($calendario/FRECUENCIA/FECHA_INICIO)}</des:FechaInicio>
                        <des:Frecuencia>
                            <com:Tipo>
                                <cat:Id>{fn:data($calendario/FRECUENCIA/FRECUENCIA/TIPO/ID)}</cat:Id>
                                <cat:Descripcion>{fn:data($calendario/FRECUENCIA/FRECUENCIA/TIPO/DESCRIPCION)}</cat:Descripcion>
                                <cat:DescripcionCorta>{fn:data($calendario/FRECUENCIA/FRECUENCIA/TIPO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                                <cat:codigoExterno>{fn:data($calendario/FRECUENCIA/FRECUENCIA/TIPO/CODIGO_EXTERNO)}</cat:codigoExterno>
                            </com:Tipo>
                            <com:Valor>{fn:data($calendario/FRECUENCIA/FRECUENCIA/VALOR)}</com:Valor>
                        </des:Frecuencia>
                    </des:Frecuencia>
                    <des:Monto>
                        <com:importe>{fn:data($calendario/MONTO)}</com:importe>
                    </des:Monto>
                    <des:NumeroCuotas>{fn:data($calendario/NUMERO_CUOTAS)}</des:NumeroCuotas>
                </des:calendario>
                }
            </des:condicionesFinancieras>
            <des:idLinea>{fn:data($desembolso/ID_LINEA)}</des:idLinea>
            <des:recursosExternos>{fn:data($desembolso/RECURSOS_EXTERNOS)}</des:recursosExternos>
            <des:cuentaCliente>{fn:data($desembolso/CUENTA_CLIENTE)}</des:cuentaCliente>
            <des:usuario>{fn:data($desembolso/USUARIO)}</des:usuario>
            <des:fechaDisponibilidadFondos>{fn:data($desembolso/FECHA_DISPONIBILIDAD_FONDOS)}</des:fechaDisponibilidadFondos>
            <des:origenTransferenciaCte>{fn:data($desembolso/ORIGEN_TRANSFERENCIA_CLIENTE)}</des:origenTransferenciaCte>
            <des:Programa>
                <her:LineaFinanciera>
                    <cat:codigoExterno>{fn:data($desembolso/PROGRAMA/LINEA_FINANCIERA/CODIGO_EXTERNO)}</cat:codigoExterno>
                </her:LineaFinanciera>
                <her:DestinoFinanciamiento>
                    <cat:codigoExterno>{fn:data($desembolso/PROGRAMA/DESTINO_FINANCIAMIENTO/CODIGO_EXTERNO)}</cat:codigoExterno>
                </her:DestinoFinanciamiento>
            </des:Programa>
            <des:EstimadoDesembolso>
                <des:Plazo>{fn:data($desembolso/ESTIMADO_DESEMBOLSO/PLAZO)}</des:Plazo>
                <des:Frecuencia>
                    <cat:Id>{fn:data($desembolso/ESTIMADO_DESEMBOLSO/FRECUENCIA/ID)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/ESTIMADO_DESEMBOLSO/FRECUENCIA/DESCRIPCION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/ESTIMADO_DESEMBOLSO/FRECUENCIA/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($desembolso/ESTIMADO_DESEMBOLSO/FRECUENCIA/CODIGO_EXTERNO)}</cat:codigoExterno>
                </des:Frecuencia>
            </des:EstimadoDesembolso>
            <des:fechaInicioProceso>{fn:data($desembolso/FECHA_INICIO_PROCESO)}</des:fechaInicioProceso>
            <des:fechaEstimadaDisponibilidad>{fn:data($desembolso/FECHA_ESTIMADA_DISPONIBILIDAD)}</des:fechaEstimadaDisponibilidad>
            {
            for $utilizacion in $desembolso/UTILIZACIONES/UTILIZACION
            return
            <des:Utilizacion>
                <lin:idFuente>{fn:data($utilizacion/ID_FUENTE)}</lin:idFuente>
                <lin:idLineaPasiva>{fn:data($utilizacion/ID_LINEA_PASIVA)}</lin:idLineaPasiva>
                <lin:MontoAsignado>{fn:data($utilizacion/MONTO_ASIGNADO)}</lin:MontoAsignado>
                {
                for $monto in $utilizacion/MONTO
                return
                <lin:Monto>
                    <com:tipo>
                        <cat:DescripcionCorta>{fn:data($monto/TIPO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($monto/IMPORTE)}</com:importe>
                </lin:Monto>
                }
                <lin:NumeroAsignacion>{fn:data($utilizacion/NUMERO_ASIGNACION)}</lin:NumeroAsignacion>
                <lin:esExterna>{if(fn:string($utilizacion/ES_EXTERNA)='1')then 'true'else('false')}</lin:esExterna>
            </des:Utilizacion>
            }
            {
            for $cargo in $desembolso/CARGOS/CARGO
            return
            <des:Cargo>
                <cat:Id>{fn:data($cargo/ID)}</cat:Id>
                <cat:Descripcion>{fn:data($cargo/DESCRIPCION)}</cat:Descripcion>
                <cat:codigoExterno>{fn:data($cargo/CODIGO_EXTERNO)}</cat:codigoExterno>
                <des:Monto>
                    <com:tipo>
                        <cat:DescripcionCorta>{fn:data($cargo/MONTO/TIPO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    </com:tipo>
                    <com:importe>{fn:data($cargo/MONTO/IMPORTE)}</com:importe>
                </des:Monto>
                <des:FechaRegistro>{fn:data($cargo/FECHA_REGISTRO)}</des:FechaRegistro>
            </des:Cargo>
            }
            <des:TransferenciaFT05>
                <des:idTransferenciaFT05>{fn:data($desembolso/TRANSFERENCIA_FT05/ID_TRANSFERENCIA_FT05)}</des:idTransferenciaFT05>
                <des:idFacturador>{fn:data($desembolso/TRANSFERENCIA_FT05/ID_FACTURADOR)}</des:idFacturador>
                <des:FechaEfectiva>{fn:data($desembolso/TRANSFERENCIA_FT05/FECHA_EFECTIVA)}</des:FechaEfectiva>
            </des:TransferenciaFT05>
            <des:HerramientaCE>
                <her:ActividadEconomica>
                    <cat:Id>{fn:data($desembolso/HERRAMIENTA_CE/ACTIVIDAD_ECONOMICA/ID)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/HERRAMIENTA_CE/ACTIVIDAD_ECONOMICA/DESCRIPCION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/HERRAMIENTA_CE/ACTIVIDAD_ECONOMICA/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($desembolso/HERRAMIENTA_CE/ACTIVIDAD_ECONOMICA/CODIGO_EXTERNO)}</cat:codigoExterno>
                </her:ActividadEconomica>
                <her:EjeEstrategico>
                    <cat:Id>{fn:data($desembolso/HERRAMIENTA_CE/EJE_ESTRATEGICO/ID)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/HERRAMIENTA_CE/EJE_ESTRATEGICO/DESCRIPCION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/HERRAMIENTA_CE/EJE_ESTRATEGICO/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($desembolso/HERRAMIENTA_CE/EJE_ESTRATEGICO/CODIGO_EXTERNO)}</cat:codigoExterno>
                </her:EjeEstrategico>
                <her:AreaFocalizacion>
                    <cat:Id>{fn:data($desembolso/HERRAMIENTA_CE/AREA_FOCALIZACION/ID)}</cat:Id>
                    <cat:Descripcion>{fn:data($desembolso/HERRAMIENTA_CE/AREA_FOCALIZACION/DESCRIPCION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($desembolso/HERRAMIENTA_CE/AREA_FOCALIZACION/DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($desembolso/HERRAMIENTA_CE/AREA_FOCALIZACION/CODIGO_EXTERNO)}</cat:codigoExterno>
                </her:AreaFocalizacion>
            </des:HerramientaCE>
            <des:modalidadFinan>
                <cat:Id>{fn:data($desembolso/MODALIDAD_FINAN/ID)}</cat:Id>
            </des:modalidadFinan>
        </ns2:ContratoDesembolso>
      }
        <ns2:Resultado>
            <res:result>OK</res:result>
            {
            if(count($ConsultarDesembolsoByIdV2OutputParameters/ns1:P_DESEMBOLSOS/DESEMBOLSOS/DESEMBOLSO)>0) then
              <res:message>Operación realizada correctamente</res:message>
            else
              <res:message>Consulta sin resultados</res:message>
            }
        </ns2:Resultado>
    </ns2:ConsultarDesembolsosByIdV2Response>
};

local:func($ConsultarDesembolsoByIdV2OutputParameters)
