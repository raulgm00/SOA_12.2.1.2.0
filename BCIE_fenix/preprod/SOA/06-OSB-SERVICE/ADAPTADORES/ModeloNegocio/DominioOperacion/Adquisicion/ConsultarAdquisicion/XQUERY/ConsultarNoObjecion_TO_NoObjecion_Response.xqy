xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare variable $noObjecionResponse as element() (:: schema-element(ns1:ConsultarNoObjecionResponse) ::) external;

declare function local:func($noObjecionResponse as element() (:: schema-element(ns1:ConsultarNoObjecionResponse) ::)) as element() (:: schema-element(ns1:ConsultarAdquisicionResponse) ::) {
    <ns1:ConsultarAdquisicionResponse>
        <ns1:Adquisicion>
            {
                for $NoObjecion in $noObjecionResponse/ns1:NoObjecion
                return 
                <adq:noObjecion>
                    {
                        if ($NoObjecion/adq:idNoObjecion)
                        then <adq:idNoObjecion>{fn:data($NoObjecion/adq:idNoObjecion)}</adq:idNoObjecion>
                        else ()
                    }
                    <adq:idAdquisicion>{fn:data($NoObjecion/adq:idAdquisicion)}</adq:idAdquisicion>
                    <adq:tipoNoObjecion>
                        {
                            if ($NoObjecion/adq:tipoNoObjecion/cat:Id)
                            then <cat:Id>{fn:data($NoObjecion/adq:tipoNoObjecion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($NoObjecion/adq:tipoNoObjecion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($NoObjecion/adq:tipoNoObjecion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($NoObjecion/adq:tipoNoObjecion/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($NoObjecion/adq:tipoNoObjecion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($NoObjecion/adq:tipoNoObjecion/cat:estatus)
                            then <cat:estatus>{fn:data($NoObjecion/adq:tipoNoObjecion/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($NoObjecion/adq:tipoNoObjecion/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($NoObjecion/adq:tipoNoObjecion/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                        {
                            for $NoObjecionRelacion in $NoObjecion/adq:tipoNoObjecion/adq:NoObjecionRelacion
                            return 
                            <adq:NoObjecionRelacion>{fn:data($NoObjecion/adq:tipoNoObjecion/adq:NoObjecionRelacion)}</adq:NoObjecionRelacion>
                        }
                    </adq:tipoNoObjecion>
                    {
                        if ($NoObjecion/adq:fechaPublicacion)
                        then <adq:fechaPublicacion>{fn:data($NoObjecion/adq:fechaPublicacion)}</adq:fechaPublicacion>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:fechaInicioDoctoBase)
                        then <adq:fechaInicioDoctoBase>{fn:data($NoObjecion/adq:fechaInicioDoctoBase)}</adq:fechaInicioDoctoBase>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:fechaFinDoctoBase)
                        then <adq:fechaFinDoctoBase>{fn:data($NoObjecion/adq:fechaFinDoctoBase)}</adq:fechaFinDoctoBase>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:fechaRecepcionPropuesta)
                        then <adq:fechaRecepcionPropuesta>{fn:data($NoObjecion/adq:fechaRecepcionPropuesta)}</adq:fechaRecepcionPropuesta>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:lugarObtenerDoctoBase)
                        then <adq:lugarObtenerDoctoBase>{fn:data($NoObjecion/adq:lugarObtenerDoctoBase)}</adq:lugarObtenerDoctoBase>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:correoInfoAdicional)
                        then <adq:correoInfoAdicional>{fn:data($NoObjecion/adq:correoInfoAdicional)}</adq:correoInfoAdicional>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:direccionPresentacionPropuesta)
                        then <adq:direccionPresentacionPropuesta>{fn:data($NoObjecion/adq:direccionPresentacionPropuesta)}</adq:direccionPresentacionPropuesta>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:resultadoProceso)
                        then 
                            <adq:resultadoProceso>
                                {
                                    if ($NoObjecion/adq:resultadoProceso/cat:Id)
                                    then <cat:Id>{fn:data($NoObjecion/adq:resultadoProceso/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($NoObjecion/adq:resultadoProceso/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($NoObjecion/adq:resultadoProceso/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($NoObjecion/adq:resultadoProceso/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($NoObjecion/adq:resultadoProceso/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($NoObjecion/adq:resultadoProceso/cat:estatus)
                                    then <cat:estatus>{fn:data($NoObjecion/adq:resultadoProceso/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($NoObjecion/adq:resultadoProceso/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($NoObjecion/adq:resultadoProceso/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </adq:resultadoProceso>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:revisadoPublicacion)
                        then <adq:revisadoPublicacion>{fn:data($NoObjecion/adq:revisadoPublicacion)}</adq:revisadoPublicacion>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:publicado)
                        then <adq:publicado>{fn:data($NoObjecion/adq:publicado)}</adq:publicado>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:numeroCorrespondencia)
                        then <adq:numeroCorrespondencia>{fn:data($NoObjecion/adq:numeroCorrespondencia)}</adq:numeroCorrespondencia>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:otorgoNoObjecion)
                        then <adq:otorgoNoObjecion>{fn:data($NoObjecion/adq:otorgoNoObjecion)}</adq:otorgoNoObjecion>
                        else ()
                    }
                    {
                        if ($NoObjecion/adq:fechaFirmaDocto)
                        then <adq:fechaFirmaDocto>{fn:data($NoObjecion/adq:fechaFirmaDocto)}</adq:fechaFirmaDocto>
                        else ()
                    }
                    <adq:estado>{fn:data($NoObjecion/adq:estado)}</adq:estado>
                    <adq:fechaRegistro>{fn:data($NoObjecion/adq:fechaRegistro)}</adq:fechaRegistro>
                    {
                        if ($NoObjecion/adq:enProcesoBpm)
                        then <adq:enProcesoBpm>{fn:data($NoObjecion/adq:enProcesoBpm)}</adq:enProcesoBpm>
                        else ()
                    }
                    {
                        for $concursante in $NoObjecion/adq:concursante
                        return 
                        <adq:concursante>
                            <adq:idConcursante>{fn:data($concursante/adq:idConcursante)}</adq:idConcursante>
                            <adq:idNoObjecion>{fn:data($concursante/adq:idNoObjecion)}</adq:idNoObjecion>
                            <adq:tipoPerfil>
                                {
                                    if ($concursante/adq:tipoPerfil/cat:Id)
                                    then <cat:Id>{fn:data($concursante/adq:tipoPerfil/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($concursante/adq:tipoPerfil/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($concursante/adq:tipoPerfil/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($concursante/adq:tipoPerfil/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($concursante/adq:tipoPerfil/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($concursante/adq:tipoPerfil/cat:estatus)
                                    then <cat:estatus>{fn:data($concursante/adq:tipoPerfil/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($concursante/adq:tipoPerfil/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($concursante/adq:tipoPerfil/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </adq:tipoPerfil>
                            <adq:nombre>{fn:data($concursante/adq:nombre)}</adq:nombre>
                            <adq:nacionalidad>
                                {
                                    if ($concursante/adq:nacionalidad/cat:Id)
                                    then <cat:Id>{fn:data($concursante/adq:nacionalidad/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($concursante/adq:nacionalidad/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($concursante/adq:nacionalidad/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($concursante/adq:nacionalidad/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($concursante/adq:nacionalidad/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($concursante/adq:nacionalidad/cat:estatus)
                                    then <cat:estatus>{fn:data($concursante/adq:nacionalidad/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($concursante/adq:nacionalidad/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($concursante/adq:nacionalidad/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </adq:nacionalidad>
                            {
                                if ($concursante/adq:monto)
                                then 
                                    <adq:monto>
                                        <com:tipo>
                                            {
                                                if ($concursante/adq:monto/com:tipo/cat:Id)
                                                then <cat:Id>{fn:data($concursante/adq:monto/com:tipo/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($concursante/adq:monto/com:tipo/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($concursante/adq:monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($concursante/adq:monto/com:tipo/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($concursante/adq:monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($concursante/adq:monto/com:tipo/cat:estatus)
                                                then <cat:estatus>{fn:data($concursante/adq:monto/com:tipo/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($concursante/adq:monto/com:tipo/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($concursante/adq:monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com:tipo>
                                        {
                                            if ($concursante/adq:monto/com:importe)
                                            then <com:importe>{fn:data($concursante/adq:monto/com:importe)}</com:importe>
                                            else ()
                                        }
                                        {
                                            if ($concursante/adq:monto/com:moneda)
                                            then 
                                                <com:moneda>
                                                    {
                                                        if ($concursante/adq:monto/com:moneda/cat:Id)
                                                        then <cat:Id>{fn:data($concursante/adq:monto/com:moneda/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($concursante/adq:monto/com:moneda/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($concursante/adq:monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($concursante/adq:monto/com:moneda/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($concursante/adq:monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($concursante/adq:monto/com:moneda/cat:estatus)
                                                        then <cat:estatus>{fn:data($concursante/adq:monto/com:moneda/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($concursante/adq:monto/com:moneda/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($concursante/adq:monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </com:moneda>
                                            else ()
                                        }
                                    </adq:monto>
                                else ()
                            }
                            {
                                if ($concursante/adq:instanciaAprobacion)
                                then 
                                    <adq:instanciaAprobacion>
                                        {
                                            if ($concursante/adq:instanciaAprobacion/cat:Id)
                                            then <cat:Id>{fn:data($concursante/adq:instanciaAprobacion/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($concursante/adq:instanciaAprobacion/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($concursante/adq:instanciaAprobacion/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($concursante/adq:instanciaAprobacion/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($concursante/adq:instanciaAprobacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($concursante/adq:instanciaAprobacion/cat:estatus)
                                            then <cat:estatus>{fn:data($concursante/adq:instanciaAprobacion/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($concursante/adq:instanciaAprobacion/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($concursante/adq:instanciaAprobacion/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </adq:instanciaAprobacion>
                                else ()
                            }
                            <adq:fechaRegistro>{fn:data($concursante/adq:fechaRegistro)}</adq:fechaRegistro>
                        </adq:concursante>
                    }
                </adq:noObjecion>
            }
        </ns1:Adquisicion>
    </ns1:ConsultarAdquisicionResponse>
};

local:func($noObjecionResponse)