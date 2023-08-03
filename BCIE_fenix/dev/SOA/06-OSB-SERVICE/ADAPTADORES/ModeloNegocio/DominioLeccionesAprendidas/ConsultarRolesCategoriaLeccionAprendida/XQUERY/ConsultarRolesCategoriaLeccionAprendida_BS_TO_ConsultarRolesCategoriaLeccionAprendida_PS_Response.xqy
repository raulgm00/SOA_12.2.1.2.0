xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LeccionAprendidaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLeccionesAprendidas/LeccionAprendida/V1/Schema/LeccionAprendidaMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolesCategoriaLeccion";
(:: import schema at "../XSD/ConsultarRolesCategoriaLeccion.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace lec = "http://www.bcie.org/LeccionAprendidaBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ResponseConsultarRolesCategoria as element() (:: schema-element(ns1:ConsultarRolesCategoriaLeccionOutputCollection) ::) external;
declare variable $ConsultarRolesCategoriaLeccionAprendidaRequest as element() (:: schema-element(ns2:ConsultarRolesCategoriaLeccionAprendidaRequest) ::) external;

declare function local:func($ResponseConsultarRolesCategoria as element() (:: schema-element(ns1:ConsultarRolesCategoriaLeccionOutputCollection) ::), 
                            $ConsultarRolesCategoriaLeccionAprendidaRequest as element() (:: schema-element(ns2:ConsultarRolesCategoriaLeccionAprendidaRequest) ::)) 
                            as element() (:: schema-element(ns2:ConsultarRolesCategoriaLeccionAprendidaResponse) ::) {
    <ns2:ConsultarRolesCategoriaLeccionAprendidaResponse>
        <ns2:LeccionAprendida>
            <lec:idLeccionAprendida>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:idLeccionAprendida)}</lec:idLeccionAprendida>
            <lec:descripcion>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:descripcion)}</lec:descripcion>
            <lec:recomendacion>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:recomendacion)}</lec:recomendacion>
            <lec:nivelIncidencia>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:nivelIncidencia)}</lec:nivelIncidencia>
            <lec:usuario>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:usuario)}</lec:usuario>
            <lec:estado>
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:Id)
                    then <cat:Id>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:estatus)
                    then <cat:estatus>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:estado/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </lec:estado>
            <lec:producto>
                <pro:idProducto>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:producto/pro:idProducto)}</pro:idProducto>
                <pro:descripcion>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:producto/pro:descripcion)}</pro:descripcion>
                <pro:descripcionCorta>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                <pro:fechaRegistro>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                <pro:codExterno>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:producto/pro:codExterno)}</pro:codExterno>
            </lec:producto>
            <lec:operacion>
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:operacion/ope:idOperacion)
                    then <ope:idOperacion>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:operacion/ope:idOperacion)}</ope:idOperacion>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:operacion/ope:responsable)
                    then <ope:responsable>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:operacion/ope:responsable)}</ope:responsable>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:operacion/ope:oficina)
                    then <ope:oficina>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:operacion/ope:oficina)}</ope:oficina>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:operacion/ope:nombre)
                    then <ope:nombre>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:operacion/ope:nombre)}</ope:nombre>
                    else ()
                }
            </lec:operacion>
            <lec:proceso>
                <ns3:IdProceso>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:IdProceso)}</ns3:IdProceso>
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:CodigoProceso)
                    then <ns3:CodigoProceso>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:CodigoProceso)}</ns3:CodigoProceso>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:NombreProceso)
                    then <ns3:NombreProceso>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:NombreProceso)}</ns3:NombreProceso>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:EsProcesoCore)
                    then <ns3:EsProcesoCore>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:EsProcesoCore)}</ns3:EsProcesoCore>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:RolIniciaProceso)
                    then <ns3:RolIniciaProceso>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:RolIniciaProceso)}</ns3:RolIniciaProceso>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:UsuarioIniciaProceso)
                    then <ns3:UsuarioIniciaProceso>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:UsuarioIniciaProceso)}</ns3:UsuarioIniciaProceso>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:InstanciaProceso)
                    then <ns3:InstanciaProceso>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:InstanciaProceso)}</ns3:InstanciaProceso>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:IdFlujo)
                    then <ns3:IdFlujo>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:proceso/ns3:IdFlujo)}</ns3:IdFlujo>
                    else ()
                }
            </lec:proceso>
            <lec:tarea>
                <ns4:CodigoTarea>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:CodigoTarea)}</ns4:CodigoTarea>
                <ns4:NombreTarea>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:NombreTarea)}</ns4:NombreTarea>
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:CodigoRol)
                    then <ns4:CodigoRol>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:CodigoRol)}</ns4:CodigoRol>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:NombreRol)
                    then <ns4:NombreRol>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:NombreRol)}</ns4:NombreRol>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:CodigoProceso)
                    then <ns4:CodigoProceso>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:CodigoProceso)}</ns4:CodigoProceso>
                    else ()
                }
                {
                    if ($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:NombreProceso)
                    then <ns4:NombreProceso>{fn:data($ConsultarRolesCategoriaLeccionAprendidaRequest/ns2:LeccionAprendida/lec:tarea/ns4:NombreProceso)}</ns4:NombreProceso>
                    else ()
                }
            </lec:tarea>
            <lec:categorias>
                {
                    for $categoria in $ResponseConsultarRolesCategoria/ns1:ConsultarRolesCategoriaLeccionOutput
                    order by $categoria/ns1:ID_TCA_CATEGORIA_LECCION
                    return 
                    <lec:categoria>
                        {
                            <cat:Id>{fn:data($categoria/ns1:ID_TCA_CATEGORIA_LECCION)}</cat:Id>
                        }
                        {
                            <cat:Descripcion>{fn:data($categoria/ns1:DESCRIPCION_CATEGORIA)}</cat:Descripcion>
                        }
                        {
                            <cat:DescripcionCorta>{fn:data($categoria/ns1:DESCRIPCION_CATEGORIA)}</cat:DescripcionCorta>
                        }
                        <lec:rol>
                            {
                                <cat:Id>{fn:data($categoria/ns1:ID_TCA_ROL_BPM)}</cat:Id>
                            }
                            {
                                <cat:Descripcion>{fn:data($categoria/ns1:DESCRIPCION_ROL)}</cat:Descripcion>
                            }
                            {
                                <cat:DescripcionCorta>{fn:data($categoria/ns1:DESCRIPCION_CORTA_ROL)}</cat:DescripcionCorta>
                            }
                        </lec:rol>
                        <lec:usuario></lec:usuario>
                        <lec:respuesta></lec:respuesta>
                    </lec:categoria>
                }
            </lec:categorias>
        </ns2:LeccionAprendida>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Roles por categoria obtenidos exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarRolesCategoriaLeccionAprendidaResponse>
};

local:func($ResponseConsultarRolesCategoria, $ConsultarRolesCategoriaLeccionAprendidaRequest)
