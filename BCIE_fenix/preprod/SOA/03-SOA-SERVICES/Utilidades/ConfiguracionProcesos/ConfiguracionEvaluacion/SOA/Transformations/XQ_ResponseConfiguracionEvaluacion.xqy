xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace con1="http://www.bcie.org/ConsultarRolesPorProcesoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Schema/ConsultarRolesPorProcesoMO.xsd" ::)
declare namespace pro="http://www.bcie.org/ProductoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace con2 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace pro1 = "http://www.bcie.org/ProductoBO";

declare namespace con3 = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $inputVariable.request as element() (:: schema-element(con:configuracionEvaluacionRequest) ::) external;
declare variable $OutConsultarProducto.response as element() (:: schema-element(pro:ConsultarProductoByIdOperacionResponse) ::) external;
declare variable $outConsultarConsultarRolPorProceso.response as element() (:: schema-element(con1:responseConsultarRolesPorProcesoMessage) ::) external;

declare function local:funcXq_responseconfiguracionevaluacion($inputVariable.request as element() (:: schema-element(con:configuracionEvaluacionRequest) ::), 
                                                              $OutConsultarProducto.response as element() (:: schema-element(pro:ConsultarProductoByIdOperacionResponse) ::), 
                                                              $outConsultarConsultarRolPorProceso.response as element() (:: schema-element(con1:responseConsultarRolesPorProcesoMessage) ::)) 
                                                              as element() (:: schema-element(con:configuracionEvaluacionResponse) ::) {
    <con:configuracionEvaluacionResponse>
        <con:configuracionEvaluacion>
            <con2:Header>
                        <ns1:Operacion>
                            <ns2:CodigoOperacion>{fn:data($OutConsultarProducto.response/pro:Operacion/ope:idOperacion)}</ns2:CodigoOperacion>
                            <ns2:NombreOperacion>{fn:data($OutConsultarProducto.response/pro:Operacion/ope:nombre)}</ns2:NombreOperacion>
                            {
                                if ($OutConsultarProducto.response/pro:Operacion/ope:responsable)
                                then <ns2:ResponsableOperacion>{fn:data($OutConsultarProducto.response/pro:Operacion/ope:responsable)}</ns2:ResponsableOperacion>
                                else ()
                            }
                            <ns2:CodigoCliente>{fn:data($OutConsultarProducto.response/pro:Operacion/ope:cliente/cli:idCliente)}</ns2:CodigoCliente>
                            <ns2:NombreCliente>{fn:data($OutConsultarProducto.response/pro:Operacion/ope:cliente/cli:razonSocial)}</ns2:NombreCliente>
                            <ns2:CodigoProducto>{fn:data($OutConsultarProducto.response/pro:Producto/pro1:idProducto)}</ns2:CodigoProducto>
                            <ns2:NombreProducto>{fn:data($OutConsultarProducto.response/pro:Producto/pro1:descripcion)}</ns2:NombreProducto>
                            <ns2:MontoSolicitado></ns2:MontoSolicitado>
                            <ns2:Pais>{fn:data($OutConsultarProducto.response/pro:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta )}</ns2:Pais>
                        </ns1:Operacion>
                        <ns1:Proceso>
                            <ns5:IdProceso>6</ns5:IdProceso>
                            <ns5:CodigoProceso>PA10</ns5:CodigoProceso>
                            <ns5:NombreProceso></ns5:NombreProceso>
                            <ns5:EsProcesoCore></ns5:EsProcesoCore>
                            <ns5:RolIniciaProceso></ns5:RolIniciaProceso>
                            <ns5:UsuarioIniciaProceso></ns5:UsuarioIniciaProceso>
                            <ns5:InstanciaProceso>{fn:data($inputVariable.request/con:instanciaProceso)}</ns5:InstanciaProceso>
                            <ns5:IdFlujo>{fn:data($inputVariable.request/con:instanciaProceso)}</ns5:IdFlujo>
                        </ns1:Proceso> 
                    <ns6:ParameterType>
                        <ns6:parameterName>TIPO_EVALUACION</ns6:parameterName>
                        <ns6:parameterValue>{fn:data($inputVariable.request/con:idTipoEvaluacion)}</ns6:parameterValue>
                    </ns6:ParameterType>
            </con2:Header>
            <con2:RolesEquipoTrabajo>
                {
                    for $listadoRoles in $outConsultarConsultarRolPorProceso.response/con1:ListadoRoles/con3:listadoRoles
                    return 
                    <con2:Rol>
                        {
                            if ($listadoRoles/cat:Id)
                            then <cat:Id>{fn:data($listadoRoles/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($listadoRoles/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($listadoRoles/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($listadoRoles/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($listadoRoles/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($listadoRoles/cat:estatus)
                            then <cat:estatus>{fn:data($listadoRoles/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($listadoRoles/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($listadoRoles/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </con2:Rol>
                }
            </con2:RolesEquipoTrabajo>
        </con:configuracionEvaluacion>
        <con:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación Exitosa</res:message>
        </con:Resultado>
    </con:configuracionEvaluacionResponse>
};

local:funcXq_responseconfiguracionevaluacion($inputVariable.request, $OutConsultarProducto.response, $outConsultarConsultarRolPorProceso.response)
