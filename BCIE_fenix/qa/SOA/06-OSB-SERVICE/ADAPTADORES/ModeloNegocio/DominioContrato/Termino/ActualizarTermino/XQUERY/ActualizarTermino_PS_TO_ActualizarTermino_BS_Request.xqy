xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarTerminoDB";
(:: import schema at "../XSD/ActualizarTerminoDB_table.xsd" ::)

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $TerminoRequest as element() (:: schema-element(ns1:ActualizarTerminoRequest) ::) external;

declare function local:func($TerminoRequest as element() (:: schema-element(ns1:ActualizarTerminoRequest) ::)) as element() (:: schema-element(ns2:TerminoCollection) ::) {
    <ns2:TerminoCollection>    
        <ns2:Termino>
            <ns2:id>{fn:data($TerminoRequest/ns1:Termino/ter:idTermino)}</ns2:id>
            <ns2:idOperacion>{fn:data($TerminoRequest/ns1:Termino/ter:operacion)}</ns2:idOperacion>
            <ns2:nombre>{fn:data($TerminoRequest/ns1:Termino/ter:nombre)}</ns2:nombre>
            <ns2:descripcion>{fn:data($TerminoRequest/ns1:Termino/ter:descripcion)}</ns2:descripcion>
            {
                if ($TerminoRequest/ns1:Termino/ter:tipoTermino/ter:idCatTermino)
                then <ns2:idTcaTermino>{fn:data($TerminoRequest/ns1:Termino/ter:tipoTermino/ter:idCatTermino)}</ns2:idTcaTermino>
                else ()
            }
            {
                if ($TerminoRequest/ns1:Termino/ter:tipoFechaInicio/cat:Id)
                then <ns2:idTcaTipoFechaInicio>{fn:data($TerminoRequest/ns1:Termino/ter:tipoFechaInicio/cat:Id)}</ns2:idTcaTipoFechaInicio>
                else ()
            }
            <ns2:fechaInicio>{fn:data($TerminoRequest/ns1:Termino/ter:fechaInicio)}</ns2:fechaInicio>
            <ns2:plazo>{fn:data($TerminoRequest/ns1:Termino/ter:plazo)}</ns2:plazo>
            {
                if ($TerminoRequest/ns1:Termino/ter:frecuenciaPlazo/cat:Id)
                then <ns2:idTcaFrecuenciaPlazo>{fn:data($TerminoRequest/ns1:Termino/ter:frecuenciaPlazo/cat:Id)}</ns2:idTcaFrecuenciaPlazo>
                else ()
            }
            <ns2:fechaVencimiento>{fn:data($TerminoRequest/ns1:Termino/ter:fechaVencimiento)}</ns2:fechaVencimiento>
            {
                if ($TerminoRequest/ns1:Termino/ter:moneda/cat:Id)
                then <ns2:idTcaMoneda>{fn:data($TerminoRequest/ns1:Termino/ter:moneda/cat:Id)}</ns2:idTcaMoneda>
                else ()
            }
            <ns2:monto>{fn:data($TerminoRequest/ns1:Termino/ter:monto)}</ns2:monto>
            <ns2:tasa>{fn:data($TerminoRequest/ns1:Termino/ter:tasa)}</ns2:tasa>
            {
                if ($TerminoRequest/ns1:Termino/ter:tipoTasa/cat:Id)
                then <ns2:idTcaTipoTasa>{fn:data($TerminoRequest/ns1:Termino/ter:tipoTasa/cat:Id)}</ns2:idTcaTipoTasa>
                else ()
            }
            <ns2:fecha>{fn:data($TerminoRequest/ns1:Termino/ter:fecha)}</ns2:fecha>
            <ns2:frecuenciaRevision>{fn:data($TerminoRequest/ns1:Termino/ter:frecuenciaRevision)}</ns2:frecuenciaRevision>
            {
                if ($TerminoRequest/ns1:Termino/ter:tipoFrecuenciaRevision/cat:Id)
                then <ns2:idTcaFrecuenciaRevision>{fn:data($TerminoRequest/ns1:Termino/ter:tipoFrecuenciaRevision/cat:Id)}</ns2:idTcaFrecuenciaRevision>
                else ()
            }
            <ns2:fechaInicioRevision>{fn:data($TerminoRequest/ns1:Termino/ter:fechaInicioRevision)}</ns2:fechaInicioRevision>
            <ns2:frecuenciaPagoInteres>{fn:data($TerminoRequest/ns1:Termino/ter:frecuenciaPagoInteres)}</ns2:frecuenciaPagoInteres>
            {
                if ($TerminoRequest/ns1:Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)
                then <ns2:idTcaFrecuenciaPagoInteres>{fn:data($TerminoRequest/ns1:Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)}</ns2:idTcaFrecuenciaPagoInteres>
                else ()
            }
            <ns2:fechaInicioPagoInteres>{fn:data($TerminoRequest/ns1:Termino/ter:fechaInicioPagoInteres)}</ns2:fechaInicioPagoInteres>
            <ns2:frecuenciaAmortizacion>{fn:data($TerminoRequest/ns1:Termino/ter:frecuenciaAmortizacion)}</ns2:frecuenciaAmortizacion>
            {
                if ($TerminoRequest/ns1:Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)
                then <ns2:idTcaFrecuenciaAmortizacion>{fn:data($TerminoRequest/ns1:Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)}</ns2:idTcaFrecuenciaAmortizacion>
                else ()
            }
            <ns2:mora>{fn:data($TerminoRequest/ns1:Termino/ter:mora)}</ns2:mora>
            <ns2:porcentajeCobertura>{fn:data($TerminoRequest/ns1:Termino/ter:porcentajeCobertura)}</ns2:porcentajeCobertura>
            <ns2:seAplicanRecursosConcesion>{
              if (string($TerminoRequest/ns1:Termino/ter:seAplicanRecursosConcesion)='')
               then 0
              else (if (fn:data($TerminoRequest/ns1:Termino/ter:seAplicanRecursosConcesion) = true())
                then 1
                  else 0)
            }
            </ns2:seAplicanRecursosConcesion>
            <ns2:seAplicanRecursosExternos>{
            if (string($TerminoRequest/ns1:Termino/ter:seAplicanRecursosExternos) = '')
               then(0)
              else (if (fn:data($TerminoRequest/ns1:Termino/ter:seAplicanRecursosExternos) = true())
                then (1)
                  else (0))
            }
            </ns2:seAplicanRecursosExternos>
            <ns2:tipoContraparte>{fn:data($TerminoRequest/ns1:Termino/ter:tipoContraparte)}</ns2:tipoContraparte>
            <ns2:montoMinimoDesembolso>{ if (string ($TerminoRequest/ns1:Termino/ter:montoMinimoDesembolso)='NaN') then  () else
            fn:data($TerminoRequest/ns1:Termino/ter:montoMinimoDesembolso)}</ns2:montoMinimoDesembolso>
            <ns2:montoMaximoDesembolso>{ if (string($TerminoRequest/ns1:Termino/ter:montoMaximoDesembolso)='NaN') then () else
            fn:data($TerminoRequest/ns1:Termino/ter:montoMaximoDesembolso)}</ns2:montoMaximoDesembolso>
            <ns2:tasaMinimaDesembolso>{ if (string ($TerminoRequest/ns1:Termino/ter:tasaMinimaDesembolso)='NaN') then () else
            fn:data($TerminoRequest/ns1:Termino/ter:tasaMinimaDesembolso)}</ns2:tasaMinimaDesembolso>
            <ns2:tasaMaximaDesembolso>{ if (string ($TerminoRequest/ns1:Termino/ter:tasaMaximaDesembolso)='NaN') then () else
            fn:data($TerminoRequest/ns1:Termino/ter:tasaMaximaDesembolso)}</ns2:tasaMaximaDesembolso>
            {
                if ($TerminoRequest/ns1:Termino/ter:estadoTCC/atr:id)
                then <ns2:idTcaEstadoTcc>{fn:data($TerminoRequest/ns1:Termino/ter:estadoTCC/atr:id)}</ns2:idTcaEstadoTcc>
                else ()
            }
            {
                if ($TerminoRequest/ns1:Termino/ter:subEstadoTCC/atr:id and $TerminoRequest/ns1:Termino/ter:subEstadoTCC/atr:id != 0)
                then <ns2:idTcaSubEstadoTcc>{fn:data($TerminoRequest/ns1:Termino/ter:subEstadoTCC/atr:id)}</ns2:idTcaSubEstadoTcc>
                else ()
            }
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
            {
            if ($TerminoRequest/ns1:Termino/ter:estado)then(
            <ns2:banEstatus>{
              if (xs:string($TerminoRequest/ns1:Termino/ter:estado) = '')
                then(1)
                  else(
                    if (fn:data($TerminoRequest/ns1:Termino/ter:estado) = true())
                      then(1)
                        else(0))}</ns2:banEstatus>)
            else()
            }
            <ns2:fechaEnmienda>{fn:data($TerminoRequest/ns1:Termino/ter:fechaEnmienda)}</ns2:fechaEnmienda>
            <ns2:porcentajeModificacion>{fn:data($TerminoRequest/ns1:Termino/ter:porcentajeModificacion)}</ns2:porcentajeModificacion>
<ns2:idTcaTipoAvance>{fn:data($TerminoRequest/ns1:Termino/ter:idTcaTipoAvance)}</ns2:idTcaTipoAvance>
<ns2:idTcaTipoPorcentaje>{fn:data($TerminoRequest/ns1:Termino/ter:idTcaTipoPorcentaje)}</ns2:idTcaTipoPorcentaje>
<ns2:porcentaje>{fn:data($TerminoRequest/ns1:Termino/ter:porcentaje)}</ns2:porcentaje>
<ns2:porcentajeInicial>{fn:data($TerminoRequest/ns1:Termino/ter:porcentajeInicial)}</ns2:porcentajeInicial>
<ns2:porcentajeFinal>{fn:data($TerminoRequest/ns1:Termino/ter:porcentajeFinal)}</ns2:porcentajeFinal>
        </ns2:Termino>
    </ns2:TerminoCollection>
};

local:func($TerminoRequest)
