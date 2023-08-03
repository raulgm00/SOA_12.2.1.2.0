xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace con1="http://www.bcie.org/ConsultarRolesPorProcesoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Schema/ConsultarRolesPorProcesoMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ope="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

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

declare namespace ope1 = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace con3 = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare variable $inputVariable.request as element() (:: schema-element(con:ConfiguracionDesembolsoExcepcionRequest) ::) external;
declare variable $outConsultarOperacionById.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) external;
declare variable $outConsultarRolesPorProceso.response as element() (:: schema-element(con1:responseConsultarRolesPorProcesoMessage) ::) external;
declare variable $outConsultarExcepcionSolicitud.response as element() (:: schema-element(des:ConsultarExcepcionSolicitudResponse) ::) external;

declare function local:funcXq_responseconfiguraciondesembolsoexcepcion($inputVariable.request as element() (:: schema-element(con:ConfiguracionDesembolsoExcepcionRequest) ::), 
                                                                       $outConsultarOperacionById.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::), 
                                                                       $outConsultarRolesPorProceso.response as element() (:: schema-element(con1:responseConsultarRolesPorProcesoMessage) ::), 
                                                                       $outConsultarExcepcionSolicitud.response as element() (:: schema-element(des:ConsultarExcepcionSolicitudResponse) ::)) 
                                                                       as element() (:: schema-element(con:ConfiguracionDesembolsoExcepcionResponse) ::) {
    <con:ConfiguracionDesembolsoExcepcionResponse>
        <con:ConfiguracionDesembolsoExcepcion>
            <con2:Header>
                        <ns1:Operacion>
                            <ns2:CodigoOperacion>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:idOperacion)}</ns2:CodigoOperacion>
                            <ns2:NombreOperacion>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:nombre)}</ns2:NombreOperacion>
                            <ns2:ResponsableOperacion>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:responsable)}</ns2:ResponsableOperacion>
                            <ns2:NombreCliente>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:cliente/cli:razonSocial)}</ns2:NombreCliente>
                            <ns2:CodigoProducto>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:producto/pro:idProducto)}</ns2:CodigoProducto>
                            <ns2:NombreProducto>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:producto/pro:descripcion)}</ns2:NombreProducto>
                            <ns2:MontoSolicitado></ns2:MontoSolicitado>
                            <ns2:Pais>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:cliente/cli:pais/cat:Descripcion)}</ns2:Pais>
                        </ns1:Operacion>
                <ns1:Tarea>
                    <ns4:CodigoTarea></ns4:CodigoTarea>
                    <ns4:NombreTarea></ns4:NombreTarea>
                    <ns4:CodigoRol></ns4:CodigoRol>
                    <ns4:NombreRol></ns4:NombreRol>
                    <ns4:CodigoProceso></ns4:CodigoProceso>
                    <ns4:NombreProceso></ns4:NombreProceso>
                </ns1:Tarea>
                <ns1:Proceso>
                    <ns5:IdProceso></ns5:IdProceso>
                    <ns5:CodigoProceso></ns5:CodigoProceso>
                    <ns5:NombreProceso></ns5:NombreProceso>
                    <ns5:EsProcesoCore></ns5:EsProcesoCore>
                    <ns5:RolIniciaProceso></ns5:RolIniciaProceso>
                    <ns5:UsuarioIniciaProceso></ns5:UsuarioIniciaProceso>
                    <ns5:InstanciaProceso></ns5:InstanciaProceso>
                    <ns5:IdFlujo>{fn:data($inputVariable.request/con:instanciaProceso)}</ns5:IdFlujo>
                </ns1:Proceso>
                <ns6:ParameterType>
                    <ns6:parameterName>ID_SOLICITUD</ns6:parameterName>
                    <ns6:parameterValue>{fn:data($inputVariable.request/con:idSolicitud)}</ns6:parameterValue>
                </ns6:ParameterType>
            </con2:Header>
            <con2:RolesEquipoTrabajo>
                {
                    for $listadoRoles in $outConsultarRolesPorProceso.response/con1:ListadoRoles/con3:listadoRoles
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
        </con:ConfiguracionDesembolsoExcepcion>
        <con:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion Exitosa</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </con:Resultado>
    </con:ConfiguracionDesembolsoExcepcionResponse>
};

local:funcXq_responseconfiguraciondesembolsoexcepcion($inputVariable.request, $outConsultarOperacionById.response, $outConsultarRolesPorProceso.response, $outConsultarExcepcionSolicitud.response)
