xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace ns11="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTermino";
(:: import schema at "../XSD/ConsultarTermino.xsd" ::)

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";


declare variable $ConsultarTerminoOutputCollection as element() (:: schema-element(ns11:ConsultarTerminoOutputCollection) ::) external;

declare function local:func(
$ConsultarTerminoOutputCollection as element() (:: schema-element(ns11:ConsultarTerminoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTerminoResponse) ::) {
    <ns2:ConsultarTerminoResponse>
     { for $termino in $ConsultarTerminoOutputCollection/ns11:ConsultarTerminoOutput
            return 
      <ns2:Termino>
          <ter:idTermino>{fn:data($termino/ns11:ID)}</ter:idTermino>
          <ter:operacion>{fn:data($termino/ns11:ID_OPERACION)}</ter:operacion>
          <ter:nombre>{fn:data($termino/ns11:NOMBRE)}</ter:nombre>
          <ter:descripcion>{fn:data($termino/ns11:DESCRIPCION)}</ter:descripcion>
          <ter:tipoTermino>
              <ter:idCatTermino>{fn:data($termino/ns11:ID_TCA_TERMINO)}</ter:idCatTermino>
              <ter:descripcionCorta>{fn:data($termino/ns11:DESCRIPCION_CORTA_TCA)}</ter:descripcionCorta>
              <ter:requiereValidarCOFI>{     if      (string($termino/ns11:REQUIERE_VALIDAR_COFI)='') then false()
              
              else  if (      fn:data($termino/ns11:REQUIERE_VALIDAR_COFI)=1) then true() else
            false ()
              }</ter:requiereValidarCOFI>
             
          </ter:tipoTermino>
          <ter:tipoFechaInicio>
              <cat:Id>{fn:data($termino/ns11:ID_TCA_TIPO_FECHA_INICIO)}</cat:Id>
             
          </ter:tipoFechaInicio>
          <ter:fechaInicio>{fn:data($termino/ns11:FECHA_INICIO)}</ter:fechaInicio>
          <ter:plazo>{fn:data($termino/ns11:PLAZO)}</ter:plazo>
          <ter:frecuenciaPlazo>
              <cat:Id>{fn:data($termino/ns11:ID_TCA_FRECUENCIA_PLAZO)}</cat:Id>

          </ter:frecuenciaPlazo>
          <ter:fechaVencimiento>{fn:data($termino/ns11:FECHA_VENCIMIENTO)}</ter:fechaVencimiento>
          <ter:moneda>
              <cat:Id>{fn:data($termino/ns11:ID_TCA_MONEDA)}</cat:Id>
              
          </ter:moneda>
          <ter:monto>{fn:data($termino/ns11:MONTO)}</ter:monto>
          <ter:tasa>{fn:data($termino/ns11:TASA)}</ter:tasa>
          <ter:tipoTasa>
              <cat:Id>{fn:data($termino/ns11:ID_TCA_TIPO_TASA)}</cat:Id>

          </ter:tipoTasa>
          <ter:fecha>{fn:data($termino/ns11:FECHA)}</ter:fecha>
          <ter:frecuenciaRevision>{fn:data($termino/ns11:FRECUENCIA_REVISION)}</ter:frecuenciaRevision>
          <ter:tipoFrecuenciaRevision>
              <cat:Id>{fn:data($termino/ns11:ID_TCA_FRECUENCIA_REVISION)}</cat:Id>
            
          </ter:tipoFrecuenciaRevision>
          <ter:fechaInicioRevision>{fn:data($termino/ns11:FECHA_INICIO_REVISION)}</ter:fechaInicioRevision>
          <ter:frecuenciaPagoInteres>{fn:data($termino/ns11:FRECUENCIA_PAGO_INTERES)}</ter:frecuenciaPagoInteres>
          <ter:tipoFrecuenciaPagoInteres>
              <cat:Id>{fn:data($termino/ns11:ID_TCA_FRECUENCIA_PAGO_INTERES)}</cat:Id>
             
          </ter:tipoFrecuenciaPagoInteres>
          <ter:fechaInicioPagoInteres>{fn:data($termino/ns11:FECHA_INICIO_PAGO_INTERES)}</ter:fechaInicioPagoInteres>
          <ter:frecuenciaAmortizacion>{fn:data($termino/ns11:FRECUENCIA_AMORTIZACION)}</ter:frecuenciaAmortizacion>
          <ter:tipoFrecuenciaAmortizacion>
              <cat:Id>{fn:data($termino/ns11:ID_TCA_FRECUENCIA_AMORTIZACION)}</cat:Id>
       
          </ter:tipoFrecuenciaAmortizacion>
          <ter:mora>{fn:data($termino/ns11:MORA)}</ter:mora>
          <ter:porcentajeCobertura>{fn:data($termino/ns11:PORCENTAJE_COBERTURA)}</ter:porcentajeCobertura>
          <ter:seAplicanRecursosConcesion>{ if(         string($termino/ns11:SE_APLICAN_RECURSOS_CONCESION)='') then false()
          else
          if ( fn:data($termino/ns11:SE_APLICAN_RECURSOS_CONCESION)=1 )then true()
        else false()
          
          }</ter:seAplicanRecursosConcesion>
          <ter:seAplicanRecursosExternos>{
          if(     string($termino/ns11:SE_APLICAN_RECURSOS_EXTERNOS)='') then false()
          else if(
          fn:data($termino/ns11:SE_APLICAN_RECURSOS_EXTERNOS)=1) then true() else
          false()
          
          }</ter:seAplicanRecursosExternos>
          <ter:tipoContraparte>{fn:data($termino/ns11:TIPO_CONTRAPARTE)}</ter:tipoContraparte>
          <ter:montoMinimoDesembolso>{fn:data($termino/ns11:MONTO_MINIMO_DESEMBOLSO)}</ter:montoMinimoDesembolso>
          <ter:montoMaximoDesembolso>{fn:data($termino/ns11:MONTO_MAXIMO_DESEMBOLSO)}</ter:montoMaximoDesembolso>
          <ter:tasaMinimaDesembolso>{fn:data($termino/ns11:TASA_MINIMA_DESEMBOLSO)}</ter:tasaMinimaDesembolso>
          <ter:tasaMaximaDesembolso>{fn:data($termino/ns11:TASA_MAXIMA_DESEMBOLSO)}</ter:tasaMaximaDesembolso>
          <ter:estadoTCC>
              <atr:id>{
              fn:data($termino/ns11:ID_TCA_ESTADO_TCC)}</atr:id>
              
          </ter:estadoTCC>
          <ter:subEstadoTCC>
              <atr:id>{fn:data($termino/ns11:ID_TCA_SUB_ESTADO_TCC)}</atr:id>
         
          </ter:subEstadoTCC>
          <ter:fechaRegistro>{fn:data($termino/ns11:FECHA_REGISTRO)}</ter:fechaRegistro>
          <ter:estado>{ if(   string($termino/ns11:BAN_ESTATUS)='') then false ()
          else if (
          fn:data($termino/ns11:BAN_ESTATUS)=1) then true() else false()
          
          }</ter:estado>
          <ter:terminoEnmendado>{fn:data($termino/ns11:ID_TERMINO_ENMENDADO)}</ter:terminoEnmendado>
          <ter:fechaEnmienda>{fn:data($termino/ns11:FECHA_ENMIENDA)}</ter:fechaEnmienda>
          <ter:porcentajeModificacion>{fn:data($termino/ns11:PORCENTAJE_MODIFICACION)}</ter:porcentajeModificacion>
          <ter:idTcaTipoAvance>{fn:data($termino/ns11:ID_TCA_TIPO_AVANCE)}</ter:idTcaTipoAvance>
<ter:idTcaTipoPorcentaje>{fn:data($termino/ns11:ID_TCA_TIPO_PORCENTAJE)}</ter:idTcaTipoPorcentaje>
<ter:porcentaje>{fn:data($termino/ns11:PORCENTAJE)}</ter:porcentaje>
<ter:porcentajeInicial>{fn:data($termino/ns11:PORCENTAJE_INICIAL)}</ter:porcentajeInicial>
<ter:porcentajeFinal>{fn:data($termino/ns11:PORCENTAJE_FINAL)}</ter:porcentajeFinal>
          <ter:requiereFondoPresupuestario>
            {if (xs:string($termino/ns11:REQUIERE_FONDO_PRESUPUESTARIO) = '1')
              then true()
              else false()
            }
          </ter:requiereFondoPresupuestario>
          
         
       
      </ns2:Termino>
      }
      <ns2:Resultado>
          <res:result>OK</res:result>
          <res:message>La consulta se ha realizado correctamente</res:message>
      </ns2:Resultado>
    </ns2:ConsultarTerminoResponse>
};

local:func( $ConsultarTerminoOutputCollection)
