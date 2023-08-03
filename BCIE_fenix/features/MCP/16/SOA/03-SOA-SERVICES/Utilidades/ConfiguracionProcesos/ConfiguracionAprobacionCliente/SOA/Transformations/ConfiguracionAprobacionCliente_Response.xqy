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

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cli1 = "http://www.bcie.org/ClienteBO";

declare namespace con3 = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare variable $outConsultarClienteById.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::) external;
declare variable $outConsultarRolesProceso.response as element() (:: schema-element(con:responseConsultarRolesPorProcesoMessage) ::) external;
declare variable $outConsultarContactos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::) external;

declare function local:funcConfiguracionaprobacioncliente_response($outConsultarClienteById.response as element() (:: schema-element(cli:ConsultarClienteResponse) ::), 
                                                                   $outConsultarRolesProceso.response as element() (:: schema-element(con:responseConsultarRolesPorProcesoMessage) ::), 
                                                                   $outConsultarContactos.response as element() (:: schema-element(cli:ConsultarContactosResponse) ::)) 
                                                                   as element() (:: schema-element(con1:CofiguracionAprobacionClienteResponse) ::) {
    <con1:CofiguracionAprobacionClienteResponse>

        <con1:configuracionAprobacionCliente>
            <con2:Header>
             <ns1:Cliente>
                    <ns3:IdCliente>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:idCliente)}</ns3:IdCliente>
                    <ns3:IdFlexCube>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:idFacturador)}</ns3:IdFlexCube>
                    <ns3:RazonSocial>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:razonSocial)}</ns3:RazonSocial>
                    <ns3:Abreviatura>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:abreviatura)}</ns3:Abreviatura>
                    <ns3:IdSector>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:sector/cat:Id)}</ns3:IdSector>
                    <ns3:Sector>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:sector/cat:Descripcion)}</ns3:Sector>
                    <ns3:IdPais>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:pais/cat:Id)}</ns3:IdPais>
                    <ns3:Pais>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:pais/cat:Descripcion)}</ns3:Pais>
                    <ns3:IdOficina>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:oficina/cat:Id)}</ns3:IdOficina>
                    <ns3:Oficina>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:oficina/cat:Descripcion)}</ns3:Oficina>
                    <ns3:ResponsableCliente>{fn:data($outConsultarClienteById.response/cli:Cliente/cli1:responsableCliente)}</ns3:ResponsableCliente>
                </ns1:Cliente>
            </con2:Header>
            <con2:RolesEquipoTrabajo>
            {
                for $listadoRoles in $outConsultarRolesProceso.response/con:ListadoRoles/con3:listadoRoles
                return 
                <con2:Rol>
                    <cat:Id>{fn:data($listadoRoles/cat:Id)}</cat:Id>
                    <cat:Descripcion>{fn:data($listadoRoles/cat:Descripcion)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($listadoRoles/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    <cat:estatus>{fn:data($listadoRoles/cat:estatus)}</cat:estatus>
                    <cat:codigoExterno>{fn:data($listadoRoles/cat:codigoExterno)}</cat:codigoExterno>
                </con2:Rol>
                }
            </con2:RolesEquipoTrabajo>
        </con1:configuracionAprobacionCliente>
        <con1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion Exitosa</res:message>
        </con1:Resultado>
    </con1:CofiguracionAprobacionClienteResponse>
};

local:funcConfiguracionaprobacioncliente_response($outConsultarClienteById.response, $outConsultarRolesProceso.response, $outConsultarContactos.response)
