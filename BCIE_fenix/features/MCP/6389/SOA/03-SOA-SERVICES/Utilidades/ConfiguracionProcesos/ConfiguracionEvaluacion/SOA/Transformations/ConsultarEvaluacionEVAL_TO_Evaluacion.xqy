xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace eva="http://www.bcie.org/EvaluacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace eva1 = "http://www.bcie.org/EvaluacionBO";

declare namespace functx = "http://www.functx.com";

declare variable $outConsultarEvaluacionEVAL.response as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::) external;

declare function local:funcConsultarevaluacioneval_to_evaluacion($outConsultarEvaluacionEVAL.response as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::)) as element() (:: schema-element(eva:ConsultarEvaluacionResponse) ::) {
    <eva:ConsultarEvaluacionResponse>
        <eva:Evaluaciones>
            {
                for $evaluacion in $outConsultarEvaluacionEVAL.response/eva:Evaluaciones/eva1:evaluacion
                order by $evaluacion/eva1:fechaRegistro descending
                return 
                <eva1:evaluacion>
                    <eva1:id>{fn:data($evaluacion/eva1:id)}</eva1:id>
                    <eva1:idOperacion>{fn:data($evaluacion/eva1:idOperacion)}</eva1:idOperacion>
                    {
                        if ($evaluacion/eva1:idEvaluacion)
                        then <eva1:idEvaluacion>{fn:data($evaluacion/eva1:idEvaluacion)}</eva1:idEvaluacion>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:tituloEvaluacion)
                        then <eva1:tituloEvaluacion>{fn:data($evaluacion/eva1:tituloEvaluacion)}</eva1:tituloEvaluacion>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:idSectorIBCIE)
                        then <eva1:idSectorIBCIE>{fn:data($evaluacion/eva1:idSectorIBCIE)}</eva1:idSectorIBCIE>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:SectorIBCIE)
                        then <eva1:SectorIBCIE>{fn:data($evaluacion/eva1:SectorIBCIE)}</eva1:SectorIBCIE>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:idSubSectorIBCIE)
                        then <eva1:idSubSectorIBCIE>{fn:data($evaluacion/eva1:idSubSectorIBCIE)}</eva1:idSubSectorIBCIE>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:subSectorIBCIE)
                        then <eva1:subSectorIBCIE>{fn:data($evaluacion/eva1:subSectorIBCIE)}</eva1:subSectorIBCIE>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:cuestionarios)
                        then 
                            <eva1:cuestionarios>
                                {
                                    for $cuestionario in $evaluacion/eva1:cuestionarios/eva1:cuestionario
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
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:enAnalisis)
                        then <eva1:enAnalisis>{fn:data($evaluacion/eva1:enAnalisis)}</eva1:enAnalisis>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:fechaRegistro)
                        then <eva1:fechaRegistro>{fn:data($evaluacion/eva1:fechaRegistro)}</eva1:fechaRegistro>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:banEstatus)
                        then <eva1:banEstatus>{fn:data($evaluacion/eva1:banEstatus)}</eva1:banEstatus>
                        else ()
                    }
                    
                        
                        <eva1:tipoEvaluacion>4</eva1:tipoEvaluacion>
                          
                    
                    {
                        if ($evaluacion/eva1:descripcionTipoEvaluacion)
                        then <eva1:descripcionTipoEvaluacion>{fn:data($evaluacion/eva1:descripcionTipoEvaluacion)}</eva1:descripcionTipoEvaluacion>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:version)
                        then <eva1:version>{fn:data($evaluacion/eva1:version)}</eva1:version>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:codigoUsuario)
                        then <eva1:codigoUsuario>{fn:data($evaluacion/eva1:codigoUsuario)}</eva1:codigoUsuario>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:nombreUsuario)
                        then <eva1:nombreUsuario>{fn:data($evaluacion/eva1:nombreUsuario)}</eva1:nombreUsuario>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:Porcentaje)
                        then <eva1:Porcentaje>{fn:data($evaluacion/eva1:Porcentaje)}</eva1:Porcentaje>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:PorcentajeObligatorio)
                        then <eva1:PorcentajeObligatorio>{fn:data($evaluacion/eva1:PorcentajeObligatorio)}</eva1:PorcentajeObligatorio>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:codigoExterno)
                        then <eva1:codigoExterno>{fn:data($evaluacion/eva1:codigoExterno)}</eva1:codigoExterno>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:catEvaluacion)
                        then 
                            <eva1:catEvaluacion>
                                {
                                    if ($evaluacion/eva1:catEvaluacion/cat:Id)
                                    then <cat:Id>{fn:data($evaluacion/eva1:catEvaluacion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($evaluacion/eva1:catEvaluacion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($evaluacion/eva1:catEvaluacion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($evaluacion/eva1:catEvaluacion/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($evaluacion/eva1:catEvaluacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($evaluacion/eva1:catEvaluacion/cat:estatus)
                                    then <cat:estatus>{fn:data($evaluacion/eva1:catEvaluacion/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($evaluacion/eva1:catEvaluacion/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($evaluacion/eva1:catEvaluacion/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </eva1:catEvaluacion>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:idEvaluacionOrigen)
                        then <eva1:idEvaluacionOrigen>{fn:data($evaluacion/eva1:idEvaluacionOrigen)}</eva1:idEvaluacionOrigen>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:calificacionIBCIE)
                        then <eva1:calificacionIBCIE>{fn:data($evaluacion/eva1:calificacionIBCIE)}</eva1:calificacionIBCIE>
                        else ()
                    }
                    {
                        if ($evaluacion/eva1:instanciaProceso)
                        then <eva1:instanciaProceso>{fn:data($evaluacion/eva1:instanciaProceso)}</eva1:instanciaProceso>
                        else ()
                    }
                </eva1:evaluacion>
            }
        </eva:Evaluaciones>
    </eva:ConsultarEvaluacionResponse>
};

declare function functx:max-determine-type
  ( $seq as xs:anyAtomicType* )  as xs:anyAtomicType? {

   if (every $value in $seq satisfies ($value castable as xs:double))
   then max(for $value in $seq return xs:double($value))
   else max(for $value in $seq return xs:string($value))
 } ;

local:funcConsultarevaluacioneval_to_evaluacion($outConsultarEvaluacionEVAL.response)
