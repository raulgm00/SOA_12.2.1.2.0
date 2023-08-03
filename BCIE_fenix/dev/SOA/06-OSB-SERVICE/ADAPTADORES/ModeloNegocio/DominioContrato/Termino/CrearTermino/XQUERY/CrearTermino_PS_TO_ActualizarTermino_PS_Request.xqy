xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns11="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTerminoByIdOperacion";
(:: import schema at "../XSD/ConsultarTerminoByIdOperacion.xsd" ::)
declare namespace ns1="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $ConsultarTerminoByIdOperacionOutputCollection as element() (:: schema-element(ns11:ConsultarTerminoByIdOperacionOutputCollection) ::) external;

declare variable $CrearTerminoRequest as element() (:: schema-element(ns1:CrearTerminoRequest) ::) external;

declare function local:func($CrearTerminoRequest as element() (:: schema-element(ns1:CrearTerminoRequest) ::),
$ConsultarTerminoByIdOperacionOutputCollection as element() (:: schema-element(ns11:ConsultarTerminoByIdOperacionOutputCollection) ::)
) as element() (:: schema-element(ns1:ActualizarTerminoRequest) ::) {
    <ns1:ActualizarTerminoRequest>
        <ns1:Termino>
            <ter:idTermino>{ if(
            fn:data($CrearTerminoRequest/ns1:esUnico)=true()) then 
            fn:data($ConsultarTerminoByIdOperacionOutputCollection/ns11:ConsultarTerminoByIdOperacionOutput/ns11:ID)
            else 
            fn:data($CrearTerminoRequest/ns1:Termino/ter:idTermino)
            }</ter:idTermino>
            <ter:operacion>{fn:data($CrearTerminoRequest/ns1:Termino/ter:operacion)}</ter:operacion>
            <ter:nombre>{fn:data($CrearTerminoRequest/ns1:Termino/ter:nombre)}</ter:nombre>
            <ter:descripcion>{fn:data($CrearTerminoRequest/ns1:Termino/ter:descripcion)}</ter:descripcion>
            <ter:tipoTermino>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:tipoTermino/ter:idCatTermino)
                    then <ter:idCatTermino>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoTermino/ter:idCatTermino)}</ter:idCatTermino>
                    else ()
                }
                <ter:descripcion>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoTermino/ter:descripcion)}</ter:descripcion>
                <ter:descripcionCorta>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoTermino/ter:descripcionCorta)}</ter:descripcionCorta>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:tipoTermino/ter:idTipoTermino)
                    then <ter:idTipoTermino>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoTermino/ter:idTipoTermino)}</ter:idTipoTermino>
                    else ()
                }
            </ter:tipoTermino>
            <ter:tipoFechaInicio>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:tipoFechaInicio/cat:Id)
                    then <cat:Id>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoFechaInicio/cat:Id)}</cat:Id>
                    else ()
                }
            </ter:tipoFechaInicio>
            <ter:fechaInicio>{fn:data($CrearTerminoRequest/ns1:Termino/ter:fechaInicio)}</ter:fechaInicio>
            <ter:plazo>{fn:data($CrearTerminoRequest/ns1:Termino/ter:plazo)}</ter:plazo>
            <ter:frecuenciaPlazo>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:frecuenciaPlazo/cat:Id)
                    then <cat:Id>{fn:data($CrearTerminoRequest/ns1:Termino/ter:frecuenciaPlazo/cat:Id)}</cat:Id>
                    else ()
                }    
            </ter:frecuenciaPlazo>
            <ter:fechaVencimiento>{fn:data($CrearTerminoRequest/ns1:Termino/ter:fechaVencimiento)}</ter:fechaVencimiento>
            <ter:moneda>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:moneda/cat:Id)
                    then <cat:Id>{fn:data($CrearTerminoRequest/ns1:Termino/ter:moneda/cat:Id)}</cat:Id>
                    else ()
                }
            </ter:moneda>
            <ter:monto>{fn:data($CrearTerminoRequest/ns1:Termino/ter:monto)}</ter:monto>
            <ter:tasa>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tasa)}</ter:tasa>
            <ter:tipoTasa>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:tipoTasa/cat:Id)
                    then <cat:Id>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoTasa/cat:Id)}</cat:Id>
                    else ()
                }
            </ter:tipoTasa>
            <ter:fecha>{fn:data($CrearTerminoRequest/ns1:Termino/ter:fecha)}</ter:fecha>
            <ter:frecuenciaRevision>{fn:data($CrearTerminoRequest/ns1:Termino/ter:frecuenciaRevision)}</ter:frecuenciaRevision>
            <ter:tipoFrecuenciaRevision>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:tipoFrecuenciaRevision/cat:Id)
                    then <cat:Id>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoFrecuenciaRevision/cat:Id)}</cat:Id>
                    else ()
                }
            </ter:tipoFrecuenciaRevision>
            <ter:fechaInicioRevision>{fn:data($CrearTerminoRequest/ns1:Termino/ter:fechaInicioRevision)}</ter:fechaInicioRevision>
            <ter:frecuenciaPagoInteres>{fn:data($CrearTerminoRequest/ns1:Termino/ter:frecuenciaPagoInteres)}</ter:frecuenciaPagoInteres>
            <ter:tipoFrecuenciaPagoInteres>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)
                    then <cat:Id>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)}</cat:Id>
                    else ()
                }
            </ter:tipoFrecuenciaPagoInteres>
            <ter:fechaInicioPagoInteres>{fn:data($CrearTerminoRequest/ns1:Termino/ter:fechaInicioPagoInteres)}</ter:fechaInicioPagoInteres>
            <ter:frecuenciaAmortizacion>{fn:data($CrearTerminoRequest/ns1:Termino/ter:frecuenciaAmortizacion)}</ter:frecuenciaAmortizacion>
            <ter:tipoFrecuenciaAmortizacion>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)
                    then <cat:Id>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)}</cat:Id>
                    else ()
                }
            </ter:tipoFrecuenciaAmortizacion>
            <ter:mora>{fn:data($CrearTerminoRequest/ns1:Termino/ter:mora)}</ter:mora>
            <ter:porcentajeCobertura>{fn:data($CrearTerminoRequest/ns1:Termino/ter:porcentajeCobertura)}</ter:porcentajeCobertura>
            <ter:seAplicanRecursosConcesion>{fn:data($CrearTerminoRequest/ns1:Termino/ter:seAplicanRecursosConcesion)}</ter:seAplicanRecursosConcesion>
            <ter:seAplicanRecursosExternos>{fn:data($CrearTerminoRequest/ns1:Termino/ter:seAplicanRecursosExternos)}</ter:seAplicanRecursosExternos>
            <ter:tipoContraparte>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tipoContraparte)}</ter:tipoContraparte>
            <ter:montoMinimoDesembolso>{fn:data($CrearTerminoRequest/ns1:Termino/ter:montoMinimoDesembolso)}</ter:montoMinimoDesembolso>
            <ter:montoMaximoDesembolso>{fn:data($CrearTerminoRequest/ns1:Termino/ter:montoMaximoDesembolso)}</ter:montoMaximoDesembolso>
            <ter:tasaMinimaDesembolso>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tasaMinimaDesembolso)}</ter:tasaMinimaDesembolso>
            <ter:tasaMaximaDesembolso>{fn:data($CrearTerminoRequest/ns1:Termino/ter:tasaMaximaDesembolso)}</ter:tasaMaximaDesembolso>
            <ter:estadoTCC>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:estadoTCC/atr:id)
                    then <atr:id>{fn:data($CrearTerminoRequest/ns1:Termino/ter:estadoTCC/atr:id)}</atr:id>
                    else ()
                }
            </ter:estadoTCC>
            <ter:subEstadoTCC>
                {
                    if ($CrearTerminoRequest/ns1:Termino/ter:subEstadoTCC/atr:id)
                    then <atr:id>{fn:data($CrearTerminoRequest/ns1:Termino/ter:subEstadoTCC/atr:id)}</atr:id>
                    else ()
                }
            </ter:subEstadoTCC>
            <ter:fechaRegistro>{fn:current-dateTime()}</ter:fechaRegistro>
            <ter:estado>{
              if (xs:string($CrearTerminoRequest/ns1:Termino/ter:estado) = '')
                then(1)
                  else(
                    if (fn:data($CrearTerminoRequest/ns1:Termino/ter:estado) = true())
                      then(1)
                        else(0))
            }</ter:estado>
            <ter:terminoEnmendado>{fn:data($CrearTerminoRequest/ns1:Termino/ter:terminoEnmendado)}</ter:terminoEnmendado>
            <ter:fechaEnmienda>{fn:data($CrearTerminoRequest/ns1:Termino/ter:fechaEnmienda)}</ter:fechaEnmienda>
            <ter:porcentajeModificacion>{fn:data($CrearTerminoRequest/ns1:Termino/ter:porcentajeModificacion)}</ter:porcentajeModificacion>
            <ter:idTcaTipoAvance>{fn:data($CrearTerminoRequest/ns1:Termino/ter:idTcaTipoAvance)}</ter:idTcaTipoAvance>
            <ter:idTcaTipoPorcentaje>{fn:data($CrearTerminoRequest/ns1:Termino/ter:idTcaTipoPorcentaje)}</ter:idTcaTipoPorcentaje>
            <ter:porcentaje>{fn:data($CrearTerminoRequest/ns1:Termino/ter:porcentaje)}</ter:porcentaje>
            <ter:porcentajeInicial>{fn:data($CrearTerminoRequest/ns1:Termino/ter:porcentajeInicial)}</ter:porcentajeInicial>
            <ter:porcentajeFinal>{fn:data($CrearTerminoRequest/ns1:Termino/ter:porcentajeFinal)}</ter:porcentajeFinal>
            
           
        </ns1:Termino>
    </ns1:ActualizarTerminoRequest>
};

local:func($CrearTerminoRequest, $ConsultarTerminoByIdOperacionOutputCollection)
