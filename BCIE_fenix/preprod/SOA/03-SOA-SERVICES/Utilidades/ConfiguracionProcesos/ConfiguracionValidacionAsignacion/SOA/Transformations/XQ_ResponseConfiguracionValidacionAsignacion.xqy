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
declare namespace prod="http://www.bcie.org/ProductoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace con2 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace con3 = "http://www.bcie.org/ContratoBO";

declare namespace ope1 = "http://www.bcie.org/OperacionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace con4 = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $inputVariable.request as element() (:: schema-element(con:ConfiguracionValidacionAsignacionRequest) ::) external;
declare variable $outConsultarRolesPorProceso.response as element() (:: schema-element(con1:responseConsultarRolesPorProcesoMessage) ::) external;
declare variable $outConsultarSolicitudDesembolso.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::) external;
declare variable $outConsultarOperacionById.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) external;
declare variable $OutConsultarProductoByIdOperacion_SB.response as element() (:: schema-element(prod:ConsultarProductoByIdOperacionResponse) ::) external;



declare function local:funcXq_responseconfiguracionvalidacionasignacion($inputVariable.request as element() (:: schema-element(con:ConfiguracionValidacionAsignacionRequest) ::), 
                                                                        $outConsultarRolesPorProceso.response as element() (:: schema-element(con1:responseConsultarRolesPorProcesoMessage) ::), 
                                                                        $outConsultarSolicitudDesembolso.response as element() (:: schema-element(des:ConsultarSolicitudDesembolsoResponse) ::), 
                                                                        $outConsultarOperacionById.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::),
                                                                        $OutConsultarProductoByIdOperacion_SB.response as element() (:: schema-element(prod:ConsultarProductoByIdOperacionResponse) ::)) 
                                                                        as element() (:: schema-element(con:ConfiguracionValidacionAsignacionResponse) ::) {
    <con:ConfiguracionValidacionAsignacionResponse>
        <con:ConfiguracionValidacionAsignacion>
            <con2:Header>
                        <ns1:Operacion>
                            <ns2:CodigoOperacion>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:idOperacion)}</ns2:CodigoOperacion>
                            <ns2:NombreOperacion>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:nombre)}</ns2:NombreOperacion>
                            <ns2:ResponsableOperacion>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:responsable)}</ns2:ResponsableOperacion>
                            <ns2:CodigoCliente>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:cliente/cli:idCliente)}</ns2:CodigoCliente>
                            <ns2:NombreCliente>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:cliente/cli:razonSocial)}</ns2:NombreCliente>
                            <ns2:CodigoProducto>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:producto/pro:idProducto)}</ns2:CodigoProducto>
                            <ns2:NombreProducto>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:producto/pro:descripcion)}</ns2:NombreProducto>
                            <ns2:MontoSolicitado></ns2:MontoSolicitado>
                            <ns2:Pais>{fn:data($outConsultarOperacionById.response/ope:Operacion/ope1:cliente/cli:pais/cat:DescripcionCorta)}</ns2:Pais>
                        </ns1:Operacion>
                <ns1:Proceso>
                    <ns5:IdProceso>17</ns5:IdProceso>
                    <ns5:CodigoProceso>PC06</ns5:CodigoProceso>
                    <ns5:NombreProceso></ns5:NombreProceso>
                    <ns5:EsProcesoCore></ns5:EsProcesoCore>
                    <ns5:RolIniciaProceso></ns5:RolIniciaProceso>
                    <ns5:UsuarioIniciaProceso></ns5:UsuarioIniciaProceso>
                    <ns5:InstanciaProceso>{fn:data($inputVariable.request/con:instanciaProceso)}</ns5:InstanciaProceso>
                    <ns5:IdFlujo></ns5:IdFlujo>
                </ns1:Proceso>
                  <ns6:ParameterType>
                    <ns6:parameterName>SECTOR_PRIVADO</ns6:parameterName>
                    <ns6:parameterValue>
                    {
                      if(fn:data($OutConsultarProductoByIdOperacion_SB.response/prod:Operacion/ope1:idSectorInstitucional/text() = '2'))
                        then('true')
                      else('false')
                    }
                    </ns6:parameterValue>
                </ns6:ParameterType>
                {
                if (fn:string($OutConsultarProductoByIdOperacion_SB.response/prod:Producto/pro:reglas/pro:requiereRAROC)='true' and (fn:string($OutConsultarProductoByIdOperacion_SB.response/prod:Operacion/ope1:idSectorInstitucional)!='2' and fn:string($OutConsultarProductoByIdOperacion_SB.response/prod:Operacion/ope1:tipoGarantia/cat:Id)='1') or fn:string($OutConsultarProductoByIdOperacion_SB.response/prod:Operacion/ope1:idSectorInstitucional)='2') then
                        <ns6:ParameterType>
                            <ns6:parameterName>REQUIERE_RAROC</ns6:parameterName>
                            <ns6:parameterValue>{true()}</ns6:parameterValue>
                        </ns6:ParameterType>
                else()}
                <ns6:ParameterType>
                    <ns6:parameterName>ID_SOLICITUD</ns6:parameterName>
                    <ns6:parameterValue>{fn:data($inputVariable.request/con:idSolicitud)}</ns6:parameterValue>
                </ns6:ParameterType> 
                
                
                {
                if(count($outConsultarSolicitudDesembolso.response/des:SolicitudDesembolso/des1:ContratoDesembolso[des1:condicionesFinancieras/des1:fondo/des1:Validador/cat:Id/text() = '21'])>0
                 or 
                (fn:string($outConsultarSolicitudDesembolso.response/des:Resultado/res:message)='true'))
                then
                <ns6:ParameterType>
                    <ns6:parameterName>VALIDADOR_DAECI</ns6:parameterName>
                    <ns6:parameterValue>{true()}</ns6:parameterValue>
                </ns6:ParameterType>
                else()}
               {
               if(count($outConsultarSolicitudDesembolso.response/des:SolicitudDesembolso/des1:ContratoDesembolso[des1:condicionesFinancieras/des1:fondo/des1:Validador/cat:Id/text() = '24'])>0)then
               <ns6:ParameterType>
                    <ns6:parameterName>VALIDADOR_FINAM</ns6:parameterName>
                    <ns6:parameterValue>{true()}</ns6:parameterValue>
                </ns6:ParameterType> 
                else()}
              {
               if(count($outConsultarSolicitudDesembolso.response/des:SolicitudDesembolso/des1:ContratoDesembolso[des1:condicionesFinancieras/des1:fondo/des1:Validador/cat:Id/text() = '4'])>0)then
               <ns6:ParameterType>
                    <ns6:parameterName>VALIDADOR_PCT</ns6:parameterName>
                    <ns6:parameterValue>{true()}</ns6:parameterValue>
                </ns6:ParameterType> 
                else()}
              {
               if(count($outConsultarSolicitudDesembolso.response/des:SolicitudDesembolso/des1:ContratoDesembolso[des1:condicionesFinancieras/des1:fondo/des1:Validador/cat:Id/text() = '64'])>0)then
               <ns6:ParameterType>
                    <ns6:parameterName>VALIDADOR_PREA</ns6:parameterName>
                    <ns6:parameterValue>{true()}</ns6:parameterValue>
                </ns6:ParameterType> 
                else()}
            </con2:Header>
            <con2:RolesEquipoTrabajo>
                {
                    for $listadoRoles in $outConsultarRolesPorProceso.response/con1:ListadoRoles/con4:listadoRoles
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
        </con:ConfiguracionValidacionAsignacion>
        <con:Resultado>
            <res:result>OK</res:result>
            <res:message></res:message>
        </con:Resultado>
    </con:ConfiguracionValidacionAsignacionResponse>
};

local:funcXq_responseconfiguracionvalidacionasignacion($inputVariable.request, $outConsultarRolesPorProceso.response, $outConsultarSolicitudDesembolso.response, $outConsultarOperacionById.response,$OutConsultarProductoByIdOperacion_SB.response)
