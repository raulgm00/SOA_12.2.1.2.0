xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComision";
(:: import schema at "../XSD/ConsultarComision.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $responseconsultarcomision as element() (:: schema-element(ns1:ConsultarComisionOutputCollection) ::) external;

declare function local:func($responseconsultarcomision as element() (:: schema-element(ns1:ConsultarComisionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarComisionByIdLineaCreditoResponse) ::) {
    <ns2:ConsultarComisionByIdLineaCreditoResponse>
        {
            for $ConsultarComisionOutput in $responseconsultarcomision/ns1:ConsultarComisionOutput
            return 
            <ns2:Comision>
                <com:idComision>{fn:data($ConsultarComisionOutput/ns1:ID)}</com:idComision>
                <com:idOperacion>{fn:data($ConsultarComisionOutput/ns1:ID_OPERACION)}</com:idOperacion>
                <com:descripcion>{fn:data($ConsultarComisionOutput/ns1:DESCRIPCION)}</com:descripcion>
                <com:tipoComision>
                    <com:idCatComision>{fn:data($ConsultarComisionOutput/ns1:TC_ID)}</com:idCatComision>
                    <com:descripcion>{fn:data($ConsultarComisionOutput/ns1:TC_DESCRIPCION)}</com:descripcion>
                    <com:descripcionCorta>{fn:data($ConsultarComisionOutput/ns1:DESCRIPCION_CORTA)}</com:descripcionCorta>
                    <com:idTipoComision>
                        <cat:Id>{fn:data($ConsultarComisionOutput/ns1:ID_TCA_TIPO_COMISION)}</cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:idTipoComision>
                    <com:esEditableEnFormalizacion>{if(fn:data($ConsultarComisionOutput/ns1:ES_EDITABLE_EN_FORMALIZACION)=1) then true() else false()}</com:esEditableEnFormalizacion>
                    <com:requiereValidarCOFI>{if(fn:data($ConsultarComisionOutput/ns1:REQUIERE_VALIDAR_COFI)=1) then true() else false()}</com:requiereValidarCOFI>
                    <com:sePuedeDispensar>{if(fn:data($ConsultarComisionOutput/ns1:SE_PUEDE_DISPENSAR)=1) then true() else false()}</com:sePuedeDispensar>
                    <com:seRegistraEnFlexCube>{if(fn:data($ConsultarComisionOutput/ns1:SE_REGISTRA_FLEXCUBE)=1) then true() else false()}</com:seRegistraEnFlexCube>
                    <com:esPlantilla>{if(fn:data($ConsultarComisionOutput/ns1:ES_PLANTILLA)=1) then true() else false()}</com:esPlantilla>
                    <com:idComisionPrecarga>{fn:data($ConsultarComisionOutput/ns1:ID_COMISION_PRECARGA)}</com:idComisionPrecarga>
                    <com:fechaRegistro>{fn:data($ConsultarComisionOutput/ns1:TC_FECHA_REGISTRO)}</com:fechaRegistro>
                    <com:estado>{if(fn:data($ConsultarComisionOutput/ns1:TC_BAN_ESTATUS)=1) then true() else false()}</com:estado>
                    <com:codigoExterno>{fn:data($ConsultarComisionOutput/ns1:COD_EXTERNO)}</com:codigoExterno>
                </com:tipoComision>
                <com:moneda>
                    <cat:Id>{fn:data($ConsultarComisionOutput/ns1:ID_TCA_MONEDA)}</cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:moneda>
                <com:montoComision>{fn:data($ConsultarComisionOutput/ns1:MONTO_COMISION)}</com:montoComision>
                <com:montoBase>
                    <com:idMontoBase>{fn:data($ConsultarComisionOutput/ns1:ID_TCA_MONTO_BASE)}</com:idMontoBase>
                    <com:valorMontoBase>{fn:data($ConsultarComisionOutput/ns1:MONTO_BASE)}</com:valorMontoBase>
                    <com:porcentajeMontoBase>{fn:data($ConsultarComisionOutput/ns1:PORCENTAJE_SOBRE_MONTO_BASE)}</com:porcentajeMontoBase>
                </com:montoBase>
                <com:fechaValor>{fn:data($ConsultarComisionOutput/ns1:FECHA_VALOR)}</com:fechaValor>
                <com:fechaVencimiento>{fn:data($ConsultarComisionOutput/ns1:FECHA_VENCIMIENTO)}</com:fechaVencimiento>
                <com:fechaInicioCapital>{fn:data($ConsultarComisionOutput/ns1:FECHA_INICIO_CAPITAL)}</com:fechaInicioCapital>
                <com:fechaInicioComision>{fn:data($ConsultarComisionOutput/ns1:FECHA_INICIO_COMISION)}</com:fechaInicioComision>
                <com:tipoFrecuencia>
                    <cat:Id>{fn:data($ConsultarComisionOutput/ns1:ID_TCA_TIPO_FRECUENCIA)}</cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipoFrecuencia>
                <com:frecuenciaPago>{fn:data($ConsultarComisionOutput/ns1:FRECUENCIA_PAGO)}</com:frecuenciaPago>
                <com:fondo>{fn:data($ConsultarComisionOutput/ns1:ID_TCA_FONDO)}</com:fondo>
                <com:comisionCompartida>{fn:data($ConsultarComisionOutput/ns1:COMISION_COMPARTIDA)}</com:comisionCompartida>
                <com:codigoDesembolso>{fn:data($ConsultarComisionOutput/ns1:CODIGO_DESEMBOLSO)}</com:codigoDesembolso>
                <com:numeroTesoreria>{fn:data($ConsultarComisionOutput/ns1:NUMERO_TESORERIA)}</com:numeroTesoreria>
                <com:codigoContrato>{fn:data($ConsultarComisionOutput/ns1:CODIGO_CONTRATO)}</com:codigoContrato>
            </ns2:Comision>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada existosamente</res:message>
           
        </ns2:Resultado></ns2:ConsultarComisionByIdLineaCreditoResponse>
};

local:func($responseconsultarcomision)
