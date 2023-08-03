xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTcaTermino";
(:: import schema at "../XSD/ConsultarTcaTermino.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $ConsultarTcaTerminoOutputCollection as element() (:: schema-element(ns1:ConsultarTcaTerminoOutputCollection) ::) external;

declare function local:func($ConsultarTcaTerminoOutputCollection as element() (:: schema-element(ns1:ConsultarTcaTerminoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTerminobyIdLineaCreditoResponse) ::) {
    <ns2:ConsultarTerminobyIdLineaCreditoResponse>
    {
    for $termino in $ConsultarTcaTerminoOutputCollection/ns1:ConsultarTcaTerminoOutput
    return
      <ns2:termino>
            <ter:idTermino>{fn:data($termino/ns1:ID)}</ter:idTermino>
            <ter:operacion>{fn:data($termino/ns1:ID_OPERACION)}</ter:operacion>
            <ter:nombre>{fn:data($termino/ns1:NOMBRE)}</ter:nombre>
            <ter:descripcion>{fn:data($termino/ns1:DESCRIPCION)}</ter:descripcion>
            <ter:tipoTermino>
                <ter:idCatTermino>{fn:data($termino/ns1:ID_TCA_TERMINO)}</ter:idCatTermino>
            </ter:tipoTermino>
            <ter:tipoFechaInicio>
                <cat:Id>{fn:data($termino/ns1:ID_TCA_TIPO_FECHA_INICIO)}</cat:Id>
            </ter:tipoFechaInicio>
            <ter:fechaInicio>{fn:data($termino/ns1:FECHA_INICIO)}</ter:fechaInicio>
            <ter:plazo>{fn:data($termino/ns1:PLAZO)}</ter:plazo>
            <ter:frecuenciaPlazo>
                <cat:Id>{fn:data($termino/ns1:ID_TCA_FRECUENCIA_PLAZO)}</cat:Id>
            </ter:frecuenciaPlazo>
            <ter:fechaVencimiento>{fn:data($termino/ns1:FECHA_VENCIMIENTO)}</ter:fechaVencimiento>
            <ter:moneda>
                <cat:Id>{fn:data($termino/ns1:ID_TCA_MONEDA)}</cat:Id>
            </ter:moneda>
            <ter:monto>{fn:data($termino/ns1:MONTO)}</ter:monto>
            <ter:tasa>{fn:data($termino/ns1:TASA)}</ter:tasa>
            <ter:tipoTasa>
                <cat:Id>{fn:data($termino/ns1:ID_TCA_TIPO_TASA)}</cat:Id>
            </ter:tipoTasa>
            <ter:fecha>{fn:data($termino/ns1:FECHA)}</ter:fecha>
            <ter:frecuenciaRevision>{fn:data($termino/ns1:FRECUENCIA_REVISION)}</ter:frecuenciaRevision>
            <ter:tipoFrecuenciaRevision>
                <cat:Id>{fn:data($termino/ns1:ID_TCA_FRECUENCIA_REVISION)}</cat:Id>
            </ter:tipoFrecuenciaRevision>
            <ter:fechaInicioRevision>{fn:data($termino/ns1:FECHA_INICIO_REVISION)}</ter:fechaInicioRevision>
            <ter:frecuenciaPagoInteres>{fn:data($termino/ns1:FRECUENCIA_PAGO_INTERES)}</ter:frecuenciaPagoInteres>
            <ter:tipoFrecuenciaPagoInteres>
                <cat:Id>{fn:data($termino/ns1:ID_TCA_FRECUENCIA_PAGO_INTERES)}</cat:Id>
            </ter:tipoFrecuenciaPagoInteres>
            <ter:fechaInicioPagoInteres>{fn:data($termino/ns1:FECHA_INICIO_PAGO_INTERES)}</ter:fechaInicioPagoInteres>
            <ter:frecuenciaAmortizacion>{fn:data($termino/ns1:FRECUENCIA_AMORTIZACION)}</ter:frecuenciaAmortizacion>
            <ter:tipoFrecuenciaAmortizacion>
                <cat:Id>{fn:data($termino/ns1:ID_TCA_FRECUENCIA_AMORTIZACION)}</cat:Id>
            </ter:tipoFrecuenciaAmortizacion>
            <ter:mora>{fn:data($termino/ns1:MORA)}</ter:mora>
            <ter:porcentajeCobertura>{fn:data($termino/ns1:PORCENTAJE_COBERTURA)}</ter:porcentajeCobertura>
            <ter:seAplicanRecursosConcesion>
            {
              if(xs:string($termino/ns1:SE_APLICAN_RECURSOS_CONCESION)!='') then
                (
                  if(fn:data($termino/ns1:SE_APLICAN_RECURSOS_CONCESION)=1) then
                    true()
                  else
                    false()
                )
              else
                false()
            }
            </ter:seAplicanRecursosConcesion>
            <ter:seAplicanRecursosExternos>            
            {
              if(xs:string($termino/ns1:SE_APLICAN_RECURSOS_EXTERNOS)!='') then
                (
                  if(fn:data($termino/ns1:SE_APLICAN_RECURSOS_EXTERNOS)=1) then
                    true()
                  else
                    false()
                )
              else
                false()
             }
            </ter:seAplicanRecursosExternos>
            <ter:tipoContraparte>{fn:data($termino/ns1:TIPO_CONTRAPARTE)}</ter:tipoContraparte>
            <ter:montoMinimoDesembolso>{fn:data($termino/ns1:MONTO_MINIMO_DESEMBOLSO)}</ter:montoMinimoDesembolso>
            <ter:montoMaximoDesembolso>{fn:data($termino/ns1:MONTO_MAXIMO_DESEMBOLSO)}</ter:montoMaximoDesembolso>
            <ter:tasaMinimaDesembolso>{fn:data($termino/ns1:TASA_MINIMA_DESEMBOLSO)}</ter:tasaMinimaDesembolso>
            <ter:tasaMaximaDesembolso>{fn:data($termino/ns1:TASA_MAXIMA_DESEMBOLSO)}</ter:tasaMaximaDesembolso>
            <ter:estadoTCC>
                <atr:id>{fn:data($termino/ns1:ID_TCA_ESTADO_TCC)}</atr:id>
            </ter:estadoTCC>
            <ter:subEstadoTCC>
                <atr:id>{fn:data($termino/ns1:ID_TCA_SUB_ESTADO_TCC)}</atr:id>
            </ter:subEstadoTCC>
            <ter:fechaRegistro>{fn:data($termino/ns1:FECHA_REGISTRO)}</ter:fechaRegistro>
            <ter:estado>
             {
              if(xs:string($termino/ns1:TER_BAN_ESTATUS)!='') then
                (
                  if(fn:data($termino/ns1:TER_BAN_ESTATUS)=1) then
                    true()
                  else
                    false()
                )
              else
                false()
             }
            </ter:estado>
            <ter:terminoEnmendado>{fn:data($termino/ns1:ID_TERMINO_ENMENDADO)}</ter:terminoEnmendado>
            <ter:fechaEnmienda>{fn:data($termino/ns1:FECHA_ENMIENDA)}</ter:fechaEnmienda>
        </ns2:termino>
        }
    </ns2:ConsultarTerminobyIdLineaCreditoResponse>
};

local:func($ConsultarTcaTerminoOutputCollection)
