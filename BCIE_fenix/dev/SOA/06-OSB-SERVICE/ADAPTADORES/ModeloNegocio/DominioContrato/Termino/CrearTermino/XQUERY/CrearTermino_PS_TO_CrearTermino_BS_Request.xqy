xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearTerminoDB";
(:: import schema at "../XSD/CrearTerminoDB_table.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $CrearTermino as element() (:: schema-element(ns1:CrearTerminoRequest) ::) external;

declare function local:func($CrearTermino as element() (:: schema-element(ns1:CrearTerminoRequest) ::)) as element() (:: schema-element(ns2:TerminoCollection) ::) {
    <ns2:TerminoCollection>
        <ns2:Termino>
            <ns2:idOperacion>{fn:data($CrearTermino/ns1:Termino/ter:operacion)}</ns2:idOperacion>

            <ns2:nombre>{fn:data($CrearTermino/ns1:Termino/ter:nombre)}</ns2:nombre>
            <ns2:descripcion>{fn:data($CrearTermino/ns1:Termino/ter:descripcion)}</ns2:descripcion>
            {
                if ($CrearTermino/ns1:Termino/ter:tipoTermino/ter:idCatTermino)
                then <ns2:idTcaTermino>{fn:data($CrearTermino/ns1:Termino/ter:tipoTermino/ter:idCatTermino)}</ns2:idTcaTermino>
                else ()
            }

            {
                if ($CrearTermino/ns1:Termino/ter:tipoFechaInicio/cat:Id)
                then <ns2:idTcaTipoFechaInicio>{fn:data($CrearTermino/ns1:Termino/ter:tipoFechaInicio/cat:Id)}</ns2:idTcaTipoFechaInicio>
                else ()
            }
            <ns2:fechaInicio>{fn:data($CrearTermino/ns1:Termino/ter:fechaInicio)}</ns2:fechaInicio>
            <ns2:plazo>{fn:data($CrearTermino/ns1:Termino/ter:plazo)}</ns2:plazo>
            {
                if ($CrearTermino/ns1:Termino/ter:frecuenciaPlazo/cat:Id)
                then <ns2:idTcaFrecuenciaPlazo>{fn:data($CrearTermino/ns1:Termino/ter:frecuenciaPlazo/cat:Id)}</ns2:idTcaFrecuenciaPlazo>
                else ()
            }
            <ns2:fechaVencimiento>{fn:data($CrearTermino/ns1:Termino/ter:fechaVencimiento)}</ns2:fechaVencimiento>
            {
                if ($CrearTermino/ns1:Termino/ter:moneda/cat:Id)
                then <ns2:idTcaMoneda>{fn:data($CrearTermino/ns1:Termino/ter:moneda/cat:Id)}</ns2:idTcaMoneda>
                else ()
            }
            <ns2:monto>{fn:data($CrearTermino/ns1:Termino/ter:monto)}</ns2:monto>
            <ns2:tasa>{fn:data($CrearTermino/ns1:Termino/ter:tasa)}</ns2:tasa>
            {
                if ($CrearTermino/ns1:Termino/ter:tipoTasa/cat:Id)
                then <ns2:idTcaTipoTasa>{fn:data($CrearTermino/ns1:Termino/ter:tipoTasa/cat:Id)}</ns2:idTcaTipoTasa>
                else ()
            }
            <ns2:fecha>{fn:data($CrearTermino/ns1:Termino/ter:fecha)}</ns2:fecha>
            <ns2:frecuenciaRevision>{fn:data($CrearTermino/ns1:Termino/ter:frecuenciaRevision)}</ns2:frecuenciaRevision>
            {
                if ($CrearTermino/ns1:Termino/ter:tipoFrecuenciaRevision/cat:Id)
                then <ns2:idTcaFrecuenciaRevision>{fn:data($CrearTermino/ns1:Termino/ter:tipoFrecuenciaRevision/cat:Id)}</ns2:idTcaFrecuenciaRevision>
                else ()
            }
            <ns2:fechaInicioRevision>{fn:data($CrearTermino/ns1:Termino/ter:fechaInicioRevision)}</ns2:fechaInicioRevision>
            <ns2:frecuenciaPagoInteres>{fn:data($CrearTermino/ns1:Termino/ter:frecuenciaPagoInteres)}</ns2:frecuenciaPagoInteres>
            {
                if ($CrearTermino/ns1:Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)
                then <ns2:idTcaFrecuenciaPagoInteres>{fn:data($CrearTermino/ns1:Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)}</ns2:idTcaFrecuenciaPagoInteres>
                else ()
            }
            <ns2:fechaInicioPagoInteres>{fn:data($CrearTermino/ns1:Termino/ter:fechaInicioPagoInteres)}</ns2:fechaInicioPagoInteres>
            <ns2:frecuenciaAmortizacion>{fn:data($CrearTermino/ns1:Termino/ter:frecuenciaAmortizacion)}</ns2:frecuenciaAmortizacion>
            {
                if ($CrearTermino/ns1:Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)
                then <ns2:idTcaFrecuenciaAmortizacion>{fn:data($CrearTermino/ns1:Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)}</ns2:idTcaFrecuenciaAmortizacion>
                else ()
            }
            <ns2:mora>{fn:data($CrearTermino/ns1:Termino/ter:mora)}</ns2:mora>
            <ns2:porcentajeCobertura>{fn:data($CrearTermino/ns1:Termino/ter:porcentajeCobertura)}</ns2:porcentajeCobertura>
            <ns2:seAplicanRecursosConcesion>{
            if(string($CrearTermino/ns1:Termino/ter:seAplicanRecursosConcesion)='')
              then 0
                else(if( string($CrearTermino/ns1:Termino/ter:seAplicanRecursosConcesion) = 'true')
                then 1
                  else 0)
                }
            </ns2:seAplicanRecursosConcesion>
            <ns2:seAplicanRecursosExternos>{
            if(xs:string($CrearTermino/ns1:Termino/ter:seAplicanRecursosExternos)='')
              then 0
                else(if ( string($CrearTermino/ns1:Termino/ter:seAplicanRecursosExternos) = 'true')
                    then (1)
                      else (0))
            }</ns2:seAplicanRecursosExternos>
            <ns2:tipoContraparte>{fn:data($CrearTermino/ns1:Termino/ter:tipoContraparte)}</ns2:tipoContraparte>
            <ns2:montoMinimoDesembolso>{fn:data($CrearTermino/ns1:Termino/ter:montoMinimoDesembolso)}</ns2:montoMinimoDesembolso>
            <ns2:montoMaximoDesembolso>{fn:data($CrearTermino/ns1:Termino/ter:montoMaximoDesembolso)}</ns2:montoMaximoDesembolso>
            <ns2:tasaMinimaDesembolso>{fn:data($CrearTermino/ns1:Termino/ter:tasaMinimaDesembolso)}</ns2:tasaMinimaDesembolso>
            <ns2:tasaMaximaDesembolso>{fn:data($CrearTermino/ns1:Termino/ter:tasaMaximaDesembolso)}</ns2:tasaMaximaDesembolso>
            {
                if ($CrearTermino/ns1:Termino/ter:estadoTCC/atr:id)
                then <ns2:idTcaEstadoTcc>{fn:data($CrearTermino/ns1:Termino/ter:estadoTCC/atr:id)}</ns2:idTcaEstadoTcc>
                else ()
            }
            {
                if ($CrearTermino/ns1:Termino/ter:subEstadoTCC/atr:id)
                then <ns2:idTcaSubEstadoTcc>{fn:data($CrearTermino/ns1:Termino/ter:subEstadoTCC/atr:id)}</ns2:idTcaSubEstadoTcc>
                else ()
            }
            <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
            <ns2:banEstatus>1</ns2:banEstatus>
            <ns2:fechaEnmienda>{fn:data($CrearTermino/ns1:Termino/ter:fechaEnmienda)}</ns2:fechaEnmienda>
            <ns2:porcentajeModificacion>{fn:data($CrearTermino/ns1:Termino/ter:porcentajeModificacion)}</ns2:porcentajeModificacion>
            <ns2:idTcaTipoAvance>{fn:data($CrearTermino/ns1:Termino/ter:idTcaTipoAvance)}</ns2:idTcaTipoAvance>
            <ns2:idTcaTipoPorcentaje>{fn:data($CrearTermino/ns1:Termino/ter:idTcaTipoPorcentaje)}</ns2:idTcaTipoPorcentaje>
            <ns2:porcentaje>{fn:data($CrearTermino/ns1:Termino/ter:porcentaje)}</ns2:porcentaje>
            <ns2:porcentajeInicial>{fn:data($CrearTermino/ns1:Termino/ter:porcentajeInicial)}</ns2:porcentajeInicial>
            <ns2:porcentajeFinal>{fn:data($CrearTermino/ns1:Termino/ter:porcentajeFinal)}</ns2:porcentajeFinal>
            {
                if ($CrearTermino/ns1:Termino/ter:loginUsuario and $CrearTermino/ns1:Termino/ter:loginUsuario !='')
                then <ns2:loginUsuario>{fn:data($CrearTermino/ns1:Termino/ter:loginUsuario)}</ns2:loginUsuario>
                else 
                <ns2:loginUsuario>FENIX</ns2:loginUsuario>
            }
            {
                if ($CrearTermino/ns1:Termino/ter:nombreUsuario and $CrearTermino/ns1:Termino/ter:nombreUsuario != '')
                then <ns2:nombreUsuario>{fn:data($CrearTermino/ns1:Termino/ter:nombreUsuario)}</ns2:nombreUsuario>
                else 
                <ns2:nombreUsuario>FENIX</ns2:nombreUsuario>
            }
                <ns2:fechaUltimoCambio>{fn:current-dateTime()}</ns2:fechaUltimoCambio>
           
      </ns2:Termino>
    </ns2:TerminoCollection>
};

local:func($CrearTermino)
