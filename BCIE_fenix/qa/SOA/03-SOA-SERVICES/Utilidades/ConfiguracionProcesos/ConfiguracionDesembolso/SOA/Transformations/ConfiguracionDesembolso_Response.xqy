xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con1="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace con="http://www.bcie.org/ConsultarRolesPorProcesoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Schema/ConsultarRolesPorProcesoMO.xsd" ::)
declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace pro="http://www.bcie.org/ProductoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)
declare namespace ter1="http://www.bcie.org/TerminoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace con2 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace con3 = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace pro1 = "http://www.bcie.org/ProductoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace con4 = "http://www.bcie.org/ContratoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare variable $outInvokeConsultarInformacionReglaById.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::) external;
declare variable $outInvokeConsultarRolesPorProceso.response as element() (:: schema-element(con:responseConsultarRolesPorProcesoMessage) ::) external;
declare variable $outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse as element() (:: schema-element(des:ConsultarDesembolsosConfiguracionByIdResponse) ::) external;
declare variable $outConsultarProducto.response as element() (:: schema-element(pro:ConsultarProductoByIdOperacionResponse) ::) external;
declare variable $outConsultarTermino.response as element() (:: schema-element(ter1:ConsultarTerminoResponse) ::) external;

declare function local:funcConfiguraciondesembolso_response($outInvokeConsultarInformacionReglaById.response as element() (:: schema-element(des:ConsultarInformacionReglasByIdResponse) ::), 
                                                            $outInvokeConsultarRolesPorProceso.response as element() (:: schema-element(con:responseConsultarRolesPorProcesoMessage) ::), 
                                                            $outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse as element() (:: schema-element(des:ConsultarDesembolsosConfiguracionByIdResponse) ::), 
                                                            $outConsultarProducto.response as element() (:: schema-element(pro:ConsultarProductoByIdOperacionResponse) ::), 
                                                            $outConsultarTermino.response as element() (:: schema-element(ter1:ConsultarTerminoResponse) ::)) 
                                                            as element() (:: schema-element(con1:configuracionDesembolsoResponse) ::) {
    let $MontoFuentesDesembolso:= count($outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse/des:ContratoDesembolso/des1:Utilizacion[lin:esExterna=fn:true()]/lin:Monto[string(com:importe)!='' and com:importe>0][com:tipo/cat:DescripcionCorta ='DESEMBOLSO'])
      return
     <con1:configuracionDesembolsoResponse>
        <con1:configuracionDesembolso>
            <con2:Header>
                {if (true()) then 
                        <ns1:Operacion>
                            <ns2:CodigoOperacion>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:idOperacion)}</ns2:CodigoOperacion>
                            <ns2:NombreOperacion>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:nombre)}</ns2:NombreOperacion>
                            {
                                if ($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:responsable)
                                then <ns2:ResponsableOperacion>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:responsable)}</ns2:ResponsableOperacion>
                                else ()
                            }
                            <ns2:CodigoCliente>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Cliente/cli:idCliente)}</ns2:CodigoCliente>
                            <ns2:NombreCliente>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Cliente/cli:razonSocial)}</ns2:NombreCliente>
                            <ns2:CodigoProducto>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:producto/pro1:idProducto)}</ns2:CodigoProducto>
                            <ns2:NombreProducto>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:producto/pro1:descripcion)}</ns2:NombreProducto>
                            <ns2:MontoSolicitado></ns2:MontoSolicitado>
                            <ns2:Pais>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Cliente/cli:pais/cat:Descripcion)}</ns2:Pais>
                           
                            <ns2:idSectorInstitucional>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:idSectorInstitucional)}</ns2:idSectorInstitucional>
                            <ns2:idEncargado>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:idEncargado)}</ns2:idEncargado>
                            <ns2:idRol>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:idRol)}</ns2:idRol>
                            <ns2:descripcionRol>{fn:data($outInvokeConsultarInformacionReglaById.response/des:Operacion/ope:descripcionRol)}</ns2:descripcionRol>

                     
                    
                            
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
               <ns6:ParameterType>
                    <ns6:parameterName>FONDOS_PRESUPUESTARIOS</ns6:parameterName>
                    {if (xs:string($outConsultarTermino.response/ter1:Termino/ter:requiereFondoPresupuestario) = 'true')then
                      <ns6:parameterValue>{fn:true()}</ns6:parameterValue>
                    else  
                      <ns6:parameterValue>{fn:false()}</ns6:parameterValue>
                    }
                </ns6:ParameterType>
                <ns6:ParameterType>
                    <ns6:parameterName>FUENTES_EXTERNAS</ns6:parameterName>
                    {if (xs:string($outConsultarTermino.response/ter1:Termino/ter:requiereFondoPresupuestario) = 'true')
                      then
                      <ns6:parameterValue>{fn:false()}</ns6:parameterValue>
                      else
                          if ($MontoFuentesDesembolso > 0)
                          then
                             if (fn:string($outInvokeConsultarInformacionReglaById.response/des:Desembolso/des1:recursosExternos) = '1') then
                              <ns6:parameterValue>{fn:false()}</ns6:parameterValue>
                            else
                              <ns6:parameterValue>{fn:true()}</ns6:parameterValue>
                          else
                            if (fn:string($outInvokeConsultarInformacionReglaById.response/des:Desembolso/des1:recursosExternos) = '1') then
                            <ns6:parameterValue>{fn:true()}</ns6:parameterValue>
                            else 
                            <ns6:parameterValue>{fn:false()}</ns6:parameterValue>
                    }
                </ns6:ParameterType>
                <ns6:ParameterType>
                    <ns6:parameterName>ES_IFI</ns6:parameterName>
                      <ns6:parameterValue>{fn:data($outConsultarProducto.response/pro:Producto/pro1:reglas/pro1:participaFINAM)}</ns6:parameterValue>
                </ns6:ParameterType>
            </con2:Header>
            <con2:RolesEquipoTrabajo>
                {
                    for $listadoRoles in $outInvokeConsultarRolesPorProceso.response/con:ListadoRoles/con3:listadoRoles
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
        </con1:configuracionDesembolso>
    </con1:configuracionDesembolsoResponse>
};

local:funcConfiguraciondesembolso_response($outInvokeConsultarInformacionReglaById.response, $outInvokeConsultarRolesPorProceso.response, $outInvokeConsultarDesembolsoById.ConsultarDesembolsosConfiguracionByIdResponse, $outConsultarProducto.response, $outConsultarTermino.response)
