xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cli="http://www.bcie.org/ClienteMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace con1="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace con="http://www.bcie.org/ConsultarRolesPorProcesoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Schema/ConsultarRolesPorProcesoMO.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace con2 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare namespace con3 = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarByIdCliente_consultarByIdCliente_OutputVariable.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::) external;
declare variable $InvokeConsultarContactos_ConsultarContactos_OutputVariable.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::) external;
declare variable $consultarRolProProceso_ConsultarRolesPorProceso_OutputVariable.response as element() (:: schema-element(con:responseConsultarRolesPorProcesoMessage) ::) external;

declare function local:funcXq_response($consultarByIdCliente_consultarByIdCliente_OutputVariable.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::), 
                                       $InvokeConsultarContactos_ConsultarContactos_OutputVariable.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::), 
                                       $consultarRolProProceso_ConsultarRolesPorProceso_OutputVariable.response as element() (:: schema-element(con:responseConsultarRolesPorProcesoMessage) ::)) 
                                       as element() (:: schema-element(con1:configuracionSeguimientoCobroResponse) ::) {
    <con1:configuracionSeguimientoCobroResponse>
        <con1:configuracionSeguimientoCobro>
            <con2:Header>
                
                        <ns1:Cliente>
                            <ns3:IdCliente>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:idCliente)}</ns3:IdCliente>
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:idFacturador)
                                then <ns3:IdFlexCube>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:idFacturador)}</ns3:IdFlexCube>
                                else ()
                            }
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:razonSocial)
                                then <ns3:RazonSocial>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:razonSocial)}</ns3:RazonSocial>
                                else ()
                            }
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:abreviatura)
                                then <ns3:Abreviatura>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:abreviatura)}</ns3:Abreviatura>
                                else ()
                            }
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:sector/cat:Id)
                                then <ns3:IdSector>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:sector/cat:Id)}</ns3:IdSector>
                                else ()
                            }
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:sector/cat:Descripcion)
                                then <ns3:Sector>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:sector/cat:Descripcion)}</ns3:Sector>
                                else ()
                            }
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:pais/cat:Id)
                                then <ns3:IdPais>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:pais/cat:Id)}</ns3:IdPais>
                                else ()
                            }
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:pais/cat:Descripcion)
                                then <ns3:Pais>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:pais/cat:Descripcion)}</ns3:Pais>
                                else ()
                            }
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:oficina/cat:Id)
                                then <ns3:IdOficina>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:oficina/cat:Id)}</ns3:IdOficina>
                                else ()
                            }
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:oficina/cat:Descripcion)
                                then <ns3:Oficina>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:oficina/cat:Descripcion)}</ns3:Oficina>
                                else ()
                            }
                            {
                                if ($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:ejecutivo)
                                then <ns3:ResponsableCliente>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:ejecutivo)}</ns3:ResponsableCliente>
                                else ()
                            }
                        </ns1:Cliente>
                        {
                        if(fn:count($InvokeConsultarContactos_ConsultarContactos_OutputVariable.response/cli:Contactos/cli1:Contacto) > 0)then
                          for $contactos in $InvokeConsultarContactos_ConsultarContactos_OutputVariable.response/cli:Contactos/cli1:Contacto
                              let $correoElectronico := $contactos/cli1:correoElectronico
                              let $tipo := $contactos/cli1:tipo
                              let $envioAutomatico := $consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:requiereEnvioAutomatico
                              return        
                          <ns6:ParameterType>
                              <ns6:parameterName>ENVIO_AUTOMATICO</ns6:parameterName>
                                {if((matches($correoElectronico, '^[a-z0-9._-]+@[a-z0-9.-]+\.[a-z]{2,3}$') and $tipo = 'Facturacion' and $envioAutomatico = 1))
                                then
                                  <ns6:parameterValue>SI</ns6:parameterValue>
                                else
                                  <ns6:parameterValue>NO</ns6:parameterValue>
                                }
                          </ns6:ParameterType>
                          else
                            <ns6:ParameterType>
                              <ns6:parameterName>ENVIO_AUTOMATICO</ns6:parameterName>
                              <ns6:parameterValue>NO</ns6:parameterValue>
                            </ns6:ParameterType>
              }
              <ns6:ParameterType>
                <ns6:parameterName>CODIGO_PAIS</ns6:parameterName>
                <ns6:parameterValue>{fn:data($consultarByIdCliente_consultarByIdCliente_OutputVariable.response/cli:Cliente/cli1:pais/cat:codigoExterno)}</ns6:parameterValue>
              </ns6:ParameterType>
            </con2:Header>
            <con2:RolesEquipoTrabajo>
                {
                    for $listadoRoles in $consultarRolProProceso_ConsultarRolesPorProceso_OutputVariable.response/con:ListadoRoles/con3:listadoRoles
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
        </con1:configuracionSeguimientoCobro>
        <con1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion Exitosa</res:message>
        </con1:Resultado>
    </con1:configuracionSeguimientoCobroResponse>
};

local:funcXq_response($consultarByIdCliente_consultarByIdCliente_OutputVariable.response, $InvokeConsultarContactos_ConsultarContactos_OutputVariable.response, $consultarRolProProceso_ConsultarRolesPorProceso_OutputVariable.response)
