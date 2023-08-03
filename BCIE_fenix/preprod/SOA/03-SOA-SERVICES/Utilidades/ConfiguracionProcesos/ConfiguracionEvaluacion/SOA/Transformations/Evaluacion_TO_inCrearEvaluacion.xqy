xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace eva="http://www.bcie.org/EvaluacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace eva1 = "http://www.bcie.org/EvaluacionBO";

declare variable $inputVariable.request as element() (:: schema-element(con:configuracionEvaluacionRequest) ::) external;
declare variable $Evaluacion.response as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::) external;

declare function local:funcEvaluacion_to_increarevaluacion($inputVariable.request as element() (:: schema-element(con:configuracionEvaluacionRequest) ::), 
                                                           $Evaluacion.response as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::)) 
                                                           as element() (:: schema-element(eva:CrearEvaluacionRequest) ::) {
    <eva:CrearEvaluacionRequest>
        <eva:Evaluaciones>
            <eva1:evaluacion>
                <eva1:id></eva1:id>
                <eva1:idOperacion>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:idOperacion)}</eva1:idOperacion>
                <eva1:idEvaluacion></eva1:idEvaluacion>
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:tituloEvaluacion)
                    then <eva1:tituloEvaluacion>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:tituloEvaluacion)}</eva1:tituloEvaluacion>
                    else ()
                }
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:idSectorIBCIE)
                    then <eva1:idSectorIBCIE>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:idSectorIBCIE)}</eva1:idSectorIBCIE>
                    else ()
                }
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:SectorIBCIE)
                    then <eva1:SectorIBCIE>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:SectorIBCIE)}</eva1:SectorIBCIE>
                    else ()
                }
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:idSubSectorIBCIE)
                    then <eva1:idSubSectorIBCIE>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:idSubSectorIBCIE)}</eva1:idSubSectorIBCIE>
                    else ()
                }
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:subSectorIBCIE)
                    then <eva1:subSectorIBCIE>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:subSectorIBCIE)}</eva1:subSectorIBCIE>
                    else ()
                }
                <eva1:cuestionarios>
                    {
                        for $cuestionario in $Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:cuestionarios/eva1:cuestionario
                        return 
                        <eva1:cuestionario>
                            {
                                if ($cuestionario/eva1:idCuestionario)
                                then <eva1:idCuestionario>{fn:data($cuestionario/eva1:idCuestionario)}</eva1:idCuestionario>
                                else ()
                            }
                            {
                                if ($cuestionario/eva1:cuestionario)
                                then <eva1:cuestionario>{fn:data($cuestionario/eva1:cuestionario)}</eva1:cuestionario>
                                else ()
                            }
                            {
                                if ($cuestionario/eva1:porcentaje)
                                then <eva1:porcentaje>{fn:data($cuestionario/eva1:porcentaje)}</eva1:porcentaje>
                                else ()
                            }
                            {
                                if ($cuestionario/eva1:porcentajeObligatorio)
                                then <eva1:porcentajeObligatorio>{fn:data($cuestionario/eva1:porcentajeObligatorio)}</eva1:porcentajeObligatorio>
                                else ()
                            }
                            {
                                if ($cuestionario/eva1:codigoExterno)
                                then <eva1:codigoExterno>{fn:data($cuestionario/eva1:codigoExterno)}</eva1:codigoExterno>
                                else ()
                            }
                            {
                                if ($cuestionario/eva1:estado)
                                then <eva1:estado>{fn:data($cuestionario/eva1:estado)}</eva1:estado>
                                else ()
                            }
                            {
                                if ($cuestionario/eva1:descripcionEstado)
                                then <eva1:descripcionEstado>{fn:data($cuestionario/eva1:descripcionEstado)}</eva1:descripcionEstado>
                                else ()
                            }
                        </eva1:cuestionario>
                    }
                </eva1:cuestionarios>
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:enAnalisis)
                    then <eva1:enAnalisis>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:enAnalisis)}</eva1:enAnalisis>
                    else ()
                }
                <eva1:fechaRegistro></eva1:fechaRegistro>
                <eva1:banEstatus></eva1:banEstatus>
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:tipoEvaluacion)
                    then <eva1:tipoEvaluacion>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:tipoEvaluacion)}</eva1:tipoEvaluacion>
                    else ()
                }
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:descripcionTipoEvaluacion)
                    then <eva1:descripcionTipoEvaluacion>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:descripcionTipoEvaluacion)}</eva1:descripcionTipoEvaluacion>
                    else ()
                }
                <eva1:version></eva1:version>
                <eva1:codigoUsuario>{fn:data($inputVariable.request/con:loginUsuario)}</eva1:codigoUsuario>
                <eva1:nombreUsuario>{fn:data($inputVariable.request/con:loginUsuario)}</eva1:nombreUsuario>
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:Porcentaje)
                    then <eva1:Porcentaje>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:Porcentaje)}</eva1:Porcentaje>
                    else ()
                }
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:PorcentajeObligatorio)
                    then <eva1:PorcentajeObligatorio>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:PorcentajeObligatorio)}</eva1:PorcentajeObligatorio>
                    else ()
                }
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:codigoExterno)
                    then <eva1:codigoExterno>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:codigoExterno)}</eva1:codigoExterno>
                    else ()
                }
                <eva1:catEvaluacion>
                    <cat:Id>4</cat:Id>
                </eva1:catEvaluacion>
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:idEvaluacionOrigen)
                    then <eva1:idEvaluacionOrigen>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:idEvaluacionOrigen)}</eva1:idEvaluacionOrigen>
                    else ()
                }
                {
                    if ($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:calificacionIBCIE)
                    then <eva1:calificacionIBCIE>{fn:data($Evaluacion.response/eva:Evaluaciones/eva1:evaluacion[1]/eva1:calificacionIBCIE)}</eva1:calificacionIBCIE>
                    else ()
                }
                <eva1:instanciaProceso>{fn:data($inputVariable.request/con:instanciaProceso)}</eva1:instanciaProceso>
            </eva1:evaluacion>
        </eva:Evaluaciones>
    </eva:CrearEvaluacionRequest>
};

local:funcEvaluacion_to_increarevaluacion($inputVariable.request, $Evaluacion.response)
