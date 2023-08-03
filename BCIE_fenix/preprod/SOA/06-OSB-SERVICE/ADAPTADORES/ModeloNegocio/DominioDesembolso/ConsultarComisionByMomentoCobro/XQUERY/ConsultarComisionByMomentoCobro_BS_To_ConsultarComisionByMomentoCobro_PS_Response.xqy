xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarComisionByMomentoCobro_DB";
(:: import schema at "../XSD/ConsultarComisionByMomentoCobro_DB_sp.xsd" ::)

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

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarComisionByMomentoCobroResponse) ::) {
    <ns2:ConsultarComisionByMomentoCobroResponse>
        <ns2:Desembolso>
            {
                for $Comision in $OutputParameters/ns1:RECORDSET/ns1:Row
                return 
                <des:Comision>
                    <com1:idComision>{fn:data($Comision/ns1:Column[@name='ID_COMISION'])}</com1:idComision>
                    <com1:idOperacion>{fn:data($Comision/ns1:Column[@name='ID_OPERACION'])}</com1:idOperacion>
                    <com1:nombre>{fn:data($Comision/ns1:Column[@name='NOMBRE'])}</com1:nombre>
                    <com1:descripcion>{fn:data($Comision/ns1:Column[@name='DESCRIPCION_COMISION'])}</com1:descripcion>
                    <com1:tipoComision>
                        <com1:idCatComision>{fn:data($Comision/ns1:Column[@name='ID_TCA_COMISION'])}</com1:idCatComision>
                    </com1:tipoComision>
                    <com1:moneda>
                        <cat:Id>{fn:data($Comision/ns1:Column[@name='ID_TCA_MONEDA'])}</cat:Id>
                    </com1:moneda>
                    <com1:montoComision>{fn:data($Comision/ns1:Column[@name='MONTO_COMISION'])}</com1:montoComision>
                    <com1:montoBase>
                        <com1:idMontoBase>{fn:data($Comision/ns1:Column[@name='ID_TCA_MONTO_BASE'])}</com1:idMontoBase>
                        <com1:valorMontoBase></com1:valorMontoBase>
                        <com1:porcentajeMontoBase></com1:porcentajeMontoBase>
                        <com1:descripcionMontoBase></com1:descripcionMontoBase>
                    </com1:montoBase>
                    <com1:fechaValor>{fn:data($Comision/ns1:Column[@name='FECHA_VALOR'])}</com1:fechaValor>
                    <com1:fechaVencimiento>{fn:data($Comision/ns1:Column[@name='FECHA_VENCIMIENTO'])}</com1:fechaVencimiento>
                    <com1:fechaInicioCapital>{fn:data($Comision/ns1:Column[@name='FECHA_INICIO_CAPITAL'])}</com1:fechaInicioCapital>
                    <com1:fechaInicioComision>{fn:data($Comision/ns1:Column[@name='FECHA_INICIO_COMISION'])}</com1:fechaInicioComision>
                    <com1:tipoFrecuencia>
                        <cat:Id>{fn:data($Comision/ns1:Column[@name='ID_TCA_TIPO_FRECUENCIA'])}</cat:Id>
                    </com1:tipoFrecuencia>
                    <com1:frecuenciaPago>{fn:data($Comision/ns1:Column[@name='FRECUENCIA_PAGO'])}</com1:frecuenciaPago>
                    <com1:fondo>{fn:data($Comision/ns1:Column[@name='ID_TCA_FONDO'])}</com1:fondo>
                    <com1:comisionCompartida>{fn:data($Comision/ns1:Column[@name='COMISION_COMPARTIDA'])}</com1:comisionCompartida>
                    <com1:codigoDesembolso>{fn:data($Comision/ns1:Column[@name='CODIGO_DESEMBOLSO'])}</com1:codigoDesembolso>
                    <com1:numeroTesoreria>{fn:data($Comision/ns1:Column[@name='NUMERO_TESORERIA'])}</com1:numeroTesoreria>
                    <com1:codigoContrato>{fn:data($Comision/ns1:Column[@name='CODIGO_CONTRATO'])}</com1:codigoContrato>
                    <com1:momentoCobro>
                        <cat:Id>{fn:data($Comision/ns1:Column[@name='ID_MOMENTO_COBRO'])}</cat:Id>
                        <cat:Descripcion>{fn:data($Comision/ns1:Column[@name='DESCRIPCION_MOMENTO_COBRO'])}</cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($Comision/ns1:Column[@name='DESCRIP_CORTA_MOMENTO_COBRO'])}</cat:DescripcionCorta>
                        <cat:estatus>{fn:data($Comision/ns1:Column[@name='BAN_ESTATUS_MOMENTO_COBRO'])}</cat:estatus>
                        <cat:codigoExterno>{fn:data($Comision/ns1:Column[@name='COD_EXTERNO_MOMENTO_COBRO'])}</cat:codigoExterno>
                    </com1:momentoCobro>
                    <com1:tipoTasa>
                        <cat:Id>{fn:data($Comision/ns1:Column[@name='ID_TCA_TIPO_TASA'])}</cat:Id>
                    </com1:tipoTasa>
                    <com1:baseCalculo>
                        <cat:Id>{fn:data($Comision/ns1:Column[@name='ID_TCA_BASE_CALCULO'])}</cat:Id>
                    </com1:baseCalculo>
                    <com1:responsableComision>{fn:data($Comision/ns1:Column[@name='RESPONSABLE_COMISION'])}</com1:responsableComision>
                    <com1:estadoTCC>
                        <atr:id>{fn:data($Comision/ns1:Column[@name='ID_TCA_ESTADO_TCC'])}</atr:id>
                        <atr:descripcion>{fn:data($Comision/ns1:Column[@name='DESCRIPCION_ESTADO'])}</atr:descripcion>
                        <atr:descripcionCorta>{fn:data($Comision/ns1:Column[@name='DESCRIPCION_CORTA_ESTADO'])}</atr:descripcionCorta>
                        <atr:estatus>{fn:data($Comision/ns1:Column[@name='BAN_ESTATUS_ESTADO'])}</atr:estatus>
                        <atr:codigoExterno>{fn:data($Comision/ns1:Column[@name='COD_EXTERNO_ESTADO'])}</atr:codigoExterno>
                        <atr:esSubEstado>{fn:data($Comision/ns1:Column[@name='ES_SUBESTADO_ESTADO'])}</atr:esSubEstado>
                    </com1:estadoTCC>
                    <com1:subEstadoTCC>
                        <atr:id>{fn:data($Comision/ns1:Column[@name='ID_TCA_SUB_ESTADO_TCC'])}</atr:id>
                    </com1:subEstadoTCC>
                    <com1:fechaRegistro>{fn:data($Comision/ns1:Column[@name='FECHA_REGISTRO_COMISION'])}</com1:fechaRegistro>
                    <com1:estado>{fn:data($Comision/ns1:Column[@name='BAN_ESTATUS_COMISION'])}</com1:estado>
                    <com1:comisionEnmendada>{fn:data($Comision/ns1:Column[@name='ID_COMISION_ENMENDADA'])}</com1:comisionEnmendada>
                    <com1:fechaEnmienda>{fn:data($Comision/ns1:Column[@name='FECHA_ENMIENDA'])}</com1:fechaEnmienda>
                    <com1:banSugerida>{fn:data($Comision/ns1:Column[@name='BAN_SUGERIDA'])}</com1:banSugerida>
                    <com1:numeroCuentaCliente></com1:numeroCuentaCliente>
                    <com1:observaciones></com1:observaciones>
                </des:Comision>
            }
        </ns2:Desembolso>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>
            {
            if (count($OutputParameters/ns1:RECORDSET/ns1:Row) > 0)
            then 'Consulta Exitosa'
            else 'No existen registros'
            }
            </res:message>
        </ns2:Resultado>
    </ns2:ConsultarComisionByMomentoCobroResponse>
};

local:func($OutputParameters)
