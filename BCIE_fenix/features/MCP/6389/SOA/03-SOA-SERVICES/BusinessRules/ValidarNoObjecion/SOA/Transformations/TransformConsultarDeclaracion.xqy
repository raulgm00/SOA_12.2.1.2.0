xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace dec="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)

declare namespace dec1 = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarDeclaracion.response as element() (:: schema-element(dec:ConsultarDeclaracionJuradaResponse) ::) external;

declare function local:funcTransformconsultardeclaracion($outConsultarDeclaracion.response as element() (:: schema-element(dec:ConsultarDeclaracionJuradaResponse) ::)) as element() (:: schema-element(dec:ConsultarDeclaracionJuradaResponse) ::) {
    <dec:ConsultarDeclaracionJuradaResponse>
        {
            for $DeclaracionJurada in $outConsultarDeclaracion.response/dec:DeclaracionJurada
            where $DeclaracionJurada/dec1:estadoDeclaracion/dec1:codigoEstadoDeclaracion != 1
            return 
            <dec:DeclaracionJurada>
                {
                    if ($DeclaracionJurada/dec1:idDeclaracion)
                    then <dec1:idDeclaracion>{fn:data($DeclaracionJurada/dec1:idDeclaracion)}</dec1:idDeclaracion>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:codigoExternoDeclaracion)
                    then <dec1:codigoExternoDeclaracion>{fn:data($DeclaracionJurada/dec1:codigoExternoDeclaracion)}</dec1:codigoExternoDeclaracion>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:titulo)
                    then <dec1:titulo>{fn:data($DeclaracionJurada/dec1:titulo)}</dec1:titulo>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:estadoDeclaracion)
                    then 
                        <dec1:estadoDeclaracion>
                            {
                                if ($DeclaracionJurada/dec1:estadoDeclaracion/dec1:codigoEstadoDeclaracion)
                                then <dec1:codigoEstadoDeclaracion>{fn:data($DeclaracionJurada/dec1:estadoDeclaracion/dec1:codigoEstadoDeclaracion)}</dec1:codigoEstadoDeclaracion>
                                else ()
                            }
                            {
                                if ($DeclaracionJurada/dec1:estadoDeclaracion/dec1:nombreEstadoDeclaracion)
                                then <dec1:nombreEstadoDeclaracion>{fn:data($DeclaracionJurada/dec1:estadoDeclaracion/dec1:nombreEstadoDeclaracion)}</dec1:nombreEstadoDeclaracion>
                                else ()
                            }
                            {
                                if ($DeclaracionJurada/dec1:estadoDeclaracion/dec1:EstadoNoObjecion)
                                then <dec1:EstadoNoObjecion>{fn:data($DeclaracionJurada/dec1:estadoDeclaracion/dec1:EstadoNoObjecion)}</dec1:EstadoNoObjecion>
                                else ()
                            }
                            {
                                if ($DeclaracionJurada/dec1:estadoDeclaracion/dec1:nombreEstadoNoObjecion)
                                then <dec1:nombreEstadoNoObjecion>{fn:data($DeclaracionJurada/dec1:estadoDeclaracion/dec1:nombreEstadoNoObjecion)}</dec1:nombreEstadoNoObjecion>
                                else ()
                            }
                        </dec1:estadoDeclaracion>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:estadoBusqueda)
                    then 
                        <dec1:estadoBusqueda>
                            {
                                if ($DeclaracionJurada/dec1:estadoBusqueda/dec1:codigoEstadoBusqueda)
                                then <dec1:codigoEstadoBusqueda>{fn:data($DeclaracionJurada/dec1:estadoBusqueda/dec1:codigoEstadoBusqueda)}</dec1:codigoEstadoBusqueda>
                                else ()
                            }
                            {
                                if ($DeclaracionJurada/dec1:estadoBusqueda/dec1:nombreEstadoBusqueda)
                                then <dec1:nombreEstadoBusqueda>{fn:data($DeclaracionJurada/dec1:estadoBusqueda/dec1:nombreEstadoBusqueda)}</dec1:nombreEstadoBusqueda>
                                else ()
                            }
                        </dec1:estadoBusqueda>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:calificacionDeRiesgo)
                    then 
                        <dec1:calificacionDeRiesgo>
                            {
                                if ($DeclaracionJurada/dec1:calificacionDeRiesgo/dec1:codigoCalificacionDeRiesgo)
                                then <dec1:codigoCalificacionDeRiesgo>{fn:data($DeclaracionJurada/dec1:calificacionDeRiesgo/dec1:codigoCalificacionDeRiesgo)}</dec1:codigoCalificacionDeRiesgo>
                                else ()
                            }
                            {
                                if ($DeclaracionJurada/dec1:calificacionDeRiesgo/dec1:nombreCalificacionDeRiesgo)
                                then <dec1:nombreCalificacionDeRiesgo>{fn:data($DeclaracionJurada/dec1:calificacionDeRiesgo/dec1:nombreCalificacionDeRiesgo)}</dec1:nombreCalificacionDeRiesgo>
                                else ()
                            }
                        </dec1:calificacionDeRiesgo>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:fechaRegistro)
                    then <dec1:fechaRegistro>{fn:data($DeclaracionJurada/dec1:fechaRegistro)}</dec1:fechaRegistro>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:fechaFirmaDeclaracion)
                    then <dec1:fechaFirmaDeclaracion>{fn:data($DeclaracionJurada/dec1:fechaFirmaDeclaracion)}</dec1:fechaFirmaDeclaracion>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:fechaVencimiento)
                    then <dec1:fechaVencimiento>{fn:data($DeclaracionJurada/dec1:fechaVencimiento)}</dec1:fechaVencimiento>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:comentarioDeclaracion)
                    then <dec1:comentarioDeclaracion>{fn:data($DeclaracionJurada/dec1:comentarioDeclaracion)}</dec1:comentarioDeclaracion>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:comentarioBusqueda)
                    then <dec1:comentarioBusqueda>{fn:data($DeclaracionJurada/dec1:comentarioBusqueda)}</dec1:comentarioBusqueda>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:elevarAOtraInstancia)
                    then <dec1:elevarAOtraInstancia>{fn:data($DeclaracionJurada/dec1:elevarAOtraInstancia)}</dec1:elevarAOtraInstancia>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:tipoOrigen)
                    then <dec1:tipoOrigen>{fn:data($DeclaracionJurada/dec1:tipoOrigen)}</dec1:tipoOrigen>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:tipoSeguimiento)
                    then <dec1:tipoSeguimiento>{fn:data($DeclaracionJurada/dec1:tipoSeguimiento)}</dec1:tipoSeguimiento>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:instanciaProceso)
                    then <dec1:instanciaProceso>{fn:data($DeclaracionJurada/dec1:instanciaProceso)}</dec1:instanciaProceso>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:esSoloLectura)
                    then <dec1:esSoloLectura>{fn:data($DeclaracionJurada/dec1:esSoloLectura)}</dec1:esSoloLectura>
                    else ()
                }
                {
                    if ($DeclaracionJurada/dec1:status)
                    then <dec1:status>{fn:data($DeclaracionJurada/dec1:status)}</dec1:status>
                    else ()
                }
            </dec:DeclaracionJurada>
        }
        {
            if ($outConsultarDeclaracion.response/dec:Resultado)
            then 
                <dec:Resultado>
                    <res:result>{fn:data($outConsultarDeclaracion.response/dec:Resultado/res:result)}</res:result>
                    <res:message>{fn:data($outConsultarDeclaracion.response/dec:Resultado/res:message)}</res:message>
                    {
                        if ($outConsultarDeclaracion.response/dec:Resultado/res:error)
                        then 
                            <res:error>
                                <err:errorCode>{fn:data($outConsultarDeclaracion.response/dec:Resultado/res:error/err:errorCode)}</err:errorCode>
                                <err:errorDescription>{fn:data($outConsultarDeclaracion.response/dec:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                                <err:errorType>{fn:data($outConsultarDeclaracion.response/dec:Resultado/res:error/err:errorType)}</err:errorType>
                            </res:error>
                        else ()
                    }
                </dec:Resultado>
            else ()
        }
    </dec:ConsultarDeclaracionJuradaResponse>
};

local:funcTransformconsultardeclaracion($outConsultarDeclaracion.response)
