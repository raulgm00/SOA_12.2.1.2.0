xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con1="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace con="http://www.bcie.org/ConsultarRolesPorProcesoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Schema/ConsultarRolesPorProcesoMO.xsd" ::)
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

declare namespace ope1 = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace con3 = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarOperacionById_ConsultarOperacionById_OutputVariable.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) external;
declare variable $consultarRolesPorProceso_ConsultarRolesPorProceso_OutputVariable.response as element() (:: schema-element(con:responseConsultarRolesPorProcesoMessage) ::) external;

declare function local:funcXq_consultaroperacionbyid_consultarrolesporproceso($consultarOperacionById_ConsultarOperacionById_OutputVariable.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::), 
                                                                              $consultarRolesPorProceso_ConsultarRolesPorProceso_OutputVariable.response as element() (:: schema-element(con:responseConsultarRolesPorProcesoMessage) ::)) 
                                                                              as element() (:: schema-element(con1:configuracionSupervisionResponse) ::) {
    <con1:configuracionSupervisionResponse>
        <con1:configuracionSupervision>
            <con2:Header>
                {
                    if ($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion) then 
                        <ns1:Operacion>
                            <ns2:CodigoOperacion>{fn:data($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:idOperacion)}</ns2:CodigoOperacion>
                            <ns2:NombreOperacion>{fn:data($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:nombre)}</ns2:NombreOperacion>
                            {
                                if ($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:responsable)
                                then <ns2:ResponsableOperacion>{fn:data($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:responsable)}</ns2:ResponsableOperacion>
                                else ()
                            }
                            <ns2:CodigoCliente>{fn:data($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:idCliente)}</ns2:CodigoCliente>
                            <ns2:NombreCliente>{fn:data($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:razonSocial)}</ns2:NombreCliente>
                            <ns2:CodigoProducto>{fn:data($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:producto/pro:idProducto)}</ns2:CodigoProducto>
                            <ns2:NombreProducto>{fn:data($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:producto/pro:descripcion)}</ns2:NombreProducto>
                            {
                              for $i in $consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:montoOperacion/ope1:montoOperacion
                                where $i/ope1:Descripcion = 'SOLICITADO'
                                return
                                  <ns2:MontoSolicitado>{fn:data($i/ope1:monto)}</ns2:MontoSolicitado>
                            }
                            <ns2:Pais>{fn:data($consultarOperacionById_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:pais/cat:Descripcion)}</ns2:Pais>
                        </ns1:Operacion>
                    else
                        <ns1:Cliente>
                            <ns3:IdCliente></ns3:IdCliente>
                            <ns3:IdFlexCube></ns3:IdFlexCube>
                            <ns3:RazonSocial></ns3:RazonSocial>
                            <ns3:Abreviatura></ns3:Abreviatura>
                            <ns3:IdSector></ns3:IdSector>
                            <ns3:Sector></ns3:Sector>
                            <ns3:IdPais></ns3:IdPais>
                            <ns3:Pais></ns3:Pais>
                            <ns3:IdOficina></ns3:IdOficina>
                            <ns3:Oficina></ns3:Oficina>
                            <ns3:ResponsableCliente></ns3:ResponsableCliente>
                        </ns1:Cliente>
                }
            </con2:Header>
            <con2:RolesEquipoTrabajo>
                {
                    for $listadoRoles in $consultarRolesPorProceso_ConsultarRolesPorProceso_OutputVariable.response/con:ListadoRoles/con3:listadoRoles
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
        </con1:configuracionSupervision>
        <con1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion Exitosa</res:message>
        </con1:Resultado>
    </con1:configuracionSupervisionResponse>
};

local:funcXq_consultaroperacionbyid_consultarrolesporproceso($consultarOperacionById_ConsultarOperacionById_OutputVariable.response, $consultarRolesPorProceso_ConsultarRolesPorProceso_OutputVariable.response)