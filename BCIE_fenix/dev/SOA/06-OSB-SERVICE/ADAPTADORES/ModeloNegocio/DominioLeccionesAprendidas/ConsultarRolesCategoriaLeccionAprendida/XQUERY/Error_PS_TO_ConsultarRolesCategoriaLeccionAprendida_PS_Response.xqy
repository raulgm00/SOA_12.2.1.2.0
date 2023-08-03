xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LeccionAprendidaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLeccionesAprendidas/LeccionAprendida/V1/Schema/LeccionAprendidaMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace lec = "http://www.bcie.org/LeccionAprendidaBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $inConsultarRolesCategoriaLeccionAprendida as element() (:: schema-element(ns1:ConsultarRolesCategoriaLeccionAprendidaRequest) ::) external;
declare variable $responseMapeoErrorMessage as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::) external;
declare variable $mensaje as xs:string external;

declare function local:func($inConsultarRolesCategoriaLeccionAprendida as element() (:: schema-element(ns1:ConsultarRolesCategoriaLeccionAprendidaRequest) ::), 
                            $responseMapeoErrorMessage as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::), 
                            $mensaje as xs:string) 
                            as element() (:: schema-element(ns1:ConsultarRolesCategoriaLeccionAprendidaResponse) ::) {
    <ns1:ConsultarRolesCategoriaLeccionAprendidaResponse>
        <ns1:LeccionAprendida>
            <lec:idLeccionAprendida>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:idLeccionAprendida)}</lec:idLeccionAprendida>
            <lec:descripcion>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:descripcion)}</lec:descripcion>
            <lec:recomendacion>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:recomendacion)}</lec:recomendacion>
            <lec:nivelIncidencia>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:nivelIncidencia)}</lec:nivelIncidencia>
            <lec:usuario>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:usuario)}</lec:usuario>
            <lec:estado>
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:Id)
                    then <cat:Id>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:estatus)
                    then <cat:estatus>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:estado/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </lec:estado>
            <lec:producto>
                <pro:idProducto>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:producto/pro:idProducto)}</pro:idProducto>
                <pro:descripcion>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:producto/pro:descripcion)}</pro:descripcion>
                <pro:descripcionCorta>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                <pro:fechaRegistro>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                <pro:codExterno>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:producto/pro:codExterno)}</pro:codExterno>
            </lec:producto>
            <lec:operacion>
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:operacion/ope:idOperacion)
                    then <ope:idOperacion>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:operacion/ope:idOperacion)}</ope:idOperacion>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:operacion/ope:responsable)
                    then <ope:responsable>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:operacion/ope:responsable)}</ope:responsable>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:operacion/ope:oficina)
                    then <ope:oficina>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:operacion/ope:oficina)}</ope:oficina>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:operacion/ope:nombre)
                    then <ope:nombre>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:operacion/ope:nombre)}</ope:nombre>
                    else ()
                }
            </lec:operacion>
            <lec:proceso>
                <ns3:IdProceso>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:IdProceso)}</ns3:IdProceso>
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:CodigoProceso)
                    then <ns3:CodigoProceso>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:CodigoProceso)}</ns3:CodigoProceso>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:NombreProceso)
                    then <ns3:NombreProceso>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:NombreProceso)}</ns3:NombreProceso>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:EsProcesoCore)
                    then <ns3:EsProcesoCore>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:EsProcesoCore)}</ns3:EsProcesoCore>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:RolIniciaProceso)
                    then <ns3:RolIniciaProceso>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:RolIniciaProceso)}</ns3:RolIniciaProceso>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:UsuarioIniciaProceso)
                    then <ns3:UsuarioIniciaProceso>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:UsuarioIniciaProceso)}</ns3:UsuarioIniciaProceso>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:InstanciaProceso)
                    then <ns3:InstanciaProceso>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:InstanciaProceso)}</ns3:InstanciaProceso>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:IdFlujo)
                    then <ns3:IdFlujo>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:proceso/ns3:IdFlujo)}</ns3:IdFlujo>
                    else ()
                }
            </lec:proceso>
            <lec:tarea>
                <ns4:CodigoTarea>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:CodigoTarea)}</ns4:CodigoTarea>
                <ns4:NombreTarea>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:NombreTarea)}</ns4:NombreTarea>
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:CodigoRol)
                    then <ns4:CodigoRol>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:CodigoRol)}</ns4:CodigoRol>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:NombreRol)
                    then <ns4:NombreRol>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:NombreRol)}</ns4:NombreRol>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:CodigoProceso)
                    then <ns4:CodigoProceso>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:CodigoProceso)}</ns4:CodigoProceso>
                    else ()
                }
                {
                    if ($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:NombreProceso)
                    then <ns4:NombreProceso>{fn:data($inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:tarea/ns4:NombreProceso)}</ns4:NombreProceso>
                    else ()
                }
            </lec:tarea>
            <lec:categorias>
                {
                    for $categoria in $inConsultarRolesCategoriaLeccionAprendida/ns1:LeccionAprendida/lec:categorias/lec:categoria
                    return 
                    <lec:categoria>
                        {
                            if ($categoria/cat:Id)
                            then <cat:Id>{fn:data($categoria/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($categoria/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($categoria/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($categoria/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($categoria/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($categoria/cat:estatus)
                            then <cat:estatus>{fn:data($categoria/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($categoria/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($categoria/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                        <lec:rol>
                            {
                                if ($categoria/lec:rol/cat:Id)
                                then <cat:Id>{fn:data($categoria/lec:rol/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($categoria/lec:rol/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($categoria/lec:rol/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($categoria/lec:rol/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($categoria/lec:rol/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($categoria/lec:rol/cat:estatus)
                                then <cat:estatus>{fn:data($categoria/lec:rol/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($categoria/lec:rol/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($categoria/lec:rol/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </lec:rol>
                        <lec:usuario>{fn:data($categoria/lec:usuario)}</lec:usuario>
                        <lec:respuesta>{fn:data($categoria/lec:respuesta)}</lec:respuesta>
                    </lec:categoria>
                }
            </lec:categorias>
        </ns1:LeccionAprendida>
        <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ConsultarRolesCategoriaLeccionAprendidaResponse>
};

local:func($inConsultarRolesCategoriaLeccionAprendida, $responseMapeoErrorMessage, $mensaje)
