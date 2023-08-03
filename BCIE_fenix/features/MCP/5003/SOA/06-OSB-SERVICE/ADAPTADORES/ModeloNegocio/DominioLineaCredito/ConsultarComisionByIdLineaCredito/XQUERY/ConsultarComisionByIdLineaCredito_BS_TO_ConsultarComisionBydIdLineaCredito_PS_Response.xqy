xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComisionByIdLineaCredito";
(:: import schema at "../XSD/ConsultarComisionByIdLineaCredito.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $ConsultarComisionByIdLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarComisionByIdLineaCreditoOutputCollection) ::) external;

declare function local:func($ConsultarComisionByIdLineaCreditoOutputCollection as element() (:: schema-element(ns1:ConsultarComisionByIdLineaCreditoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarComisionByIdLineaCreditoResponse) ::) {
    <ns2:ConsultarComisionByIdLineaCreditoResponse>
     {
    for $comision in $ConsultarComisionByIdLineaCreditoOutputCollection/ns1:ConsultarComisionByIdLineaCreditoOutput
    return
        <ns2:Comision>
            <com:idComision>{fn:data($comision/ns1:ID)}</com:idComision>
            <com:idOperacion>{fn:data($comision/ns1:ID_OPERACION)}</com:idOperacion>
            <com:nombre>{fn:data($comision/ns1:TIPO_COMISION)}</com:nombre>
            <com:descripcion>{fn:data($comision/ns1:DESCRIPCION)}</com:descripcion>
            <com:tipoComision>
                <com:idCatComision>{fn:data($comision/ns1:ID_TCA_COMISION)}</com:idCatComision>
                <com:idTipoComision>
                    <cat:Descripcion>{fn:data($comision/ns1:TIPO_COMISION)}</cat:Descripcion>
                </com:idTipoComision>
            </com:tipoComision>
            <com:moneda>
                <cat:Id>{fn:data($comision/ns1:ID_TCA_MONEDA)}</cat:Id>
            </com:moneda>
            <com:montoComision>{fn:data($comision/ns1:MONTO)}</com:montoComision>
            <com:montoBase>
                <com:idMontoBase>{fn:data($comision/ns1:ID_TCA_MONTO_BASE)}</com:idMontoBase>
                <com:valorMontoBase>{fn:data($comision/ns1:MONTO_BASE)}</com:valorMontoBase>
                <com:porcentajeMontoBase>{fn:data($comision/ns1:VALOR_COMISION)}</com:porcentajeMontoBase>
            </com:montoBase>
            <com:fechaValor>{fn:data($comision/ns1:FECHA_VALOR)}</com:fechaValor>
            <com:fechaVencimiento>{fn:data($comision/ns1:FECHA_VENCIMIENTO)}</com:fechaVencimiento>
            <com:fechaInicioCapital>{fn:data($comision/ns1:FECHA_INICIO)}</com:fechaInicioCapital>
            <com:fechaInicioComision>{fn:data($comision/ns1:FECHA_INICIO_COMISION)}</com:fechaInicioComision>
            <com:tipoFrecuencia>
                <cat:Id>{fn:data($comision/ns1:ID_TCA_TIPO_FRECUENCIA)}</cat:Id>
                <cat:codigoExterno>{fn:data($comision/ns1:TIPO_FRECUENCIA)}</cat:codigoExterno>
            </com:tipoFrecuencia>
            <com:frecuenciaPago>{fn:data($comision/ns1:FRECUENCIA)}</com:frecuenciaPago>
            <com:comisionCompartida>
            {
            if (string($comision/ns1:COMISION_COMPARTIDA)!='' ) then 
              if ($comision/ns1:COMISION_COMPARTIDA= 1)then 
                true()
              else
                false()
            else
              false() 
            }
            </com:comisionCompartida>
            <com:numeroTesoreria>{fn:data($comision/ns1:NUMERO_TESORERIA)}</com:numeroTesoreria>
            <com:codigoContrato>{fn:data($comision/ns1:CODIGO_CONTRATO)}</com:codigoContrato>
            <com:momentoCobro>
                <cat:Id>{fn:data($comision/ns1:ID_TCA_MOMENTO_COBRO)}</cat:Id>
            </com:momentoCobro>
            <com:tipoTasa>
                <cat:Id>{fn:data($comision/ns1:TIPO_TASA)}</cat:Id>
            </com:tipoTasa>
            <com:baseCalculo>
                <cat:Id>{fn:data($comision/ns1:BASE_CALCULO)}</cat:Id>
                <cat:codigoExterno>{fn:data($comision/ns1:BASE_COD_EXTERNO)}</cat:codigoExterno>
            </com:baseCalculo>
            <com:responsableComision>{fn:data($comision/ns1:RESPONSABLE_COMISION)}</com:responsableComision>
            <com:estadoTCC>
                <atr:id>{fn:data($comision/ns1:ID_TCA_ESTADO_TCC)}</atr:id>
            </com:estadoTCC>
            <com:subEstadoTCC>
                <atr:id>{fn:data($comision/ns1:ID_TCA_SUB_ESTADO_TCC)}</atr:id>
            </com:subEstadoTCC>
            <com:fechaRegistro>{fn:data($comision/ns1:FECHA_REGISTRO)}</com:fechaRegistro>
            <com:estado>
            {
            if (string($comision/ns1:BAN_ESTATUS)!='' ) then 
              if ($comision/ns1:BAN_ESTATUS= 1)then 
                true()
              else
                false()
            else
              false() 
            }
            </com:estado>
            <com:comisionEnmendada>{fn:data($comision/ns1:ID_COMISION_ENMENDADA)}</com:comisionEnmendada>
            <com:fechaEnmienda>{fn:data($comision/ns1:FECHA_ENMIENDA)}</com:fechaEnmienda>
            <com:banSugerida>{fn:data($comision/ns1:BAN_SUGERIDA)}</com:banSugerida>
        </ns2:Comision>
        }
     {
         if (count($ConsultarComisionByIdLineaCreditoOutputCollection/ns1:ConsultarComisionByIdLineaCreditoOutput) > 0) then
             <ns2:Resultado>
                 <res:result>OK</res:result>
                 <res:message>Consulta exitosa</res:message>
             </ns2:Resultado>
         else
             <ns2:Resultado>
                 <res:result>OK</res:result>
                 <res:message>No existen registros</res:message> 
             </ns2:Resultado>
     }
    </ns2:ConsultarComisionByIdLineaCreditoResponse>
};

local:func($ConsultarComisionByIdLineaCreditoOutputCollection)
