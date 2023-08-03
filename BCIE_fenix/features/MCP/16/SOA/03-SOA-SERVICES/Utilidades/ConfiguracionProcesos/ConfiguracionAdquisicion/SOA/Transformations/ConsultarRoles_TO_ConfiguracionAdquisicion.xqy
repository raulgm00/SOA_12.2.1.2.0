xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con1="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace pro="http://www.bcie.org/ProductoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioProducto/Producto/V1/Schema/ProductoMO.xsd" ::)
declare namespace adq="http://www.bcie.org/AdquisicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace con2 = "http://www.bcie.org/ConfiguracionProcesosBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace con3 = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace pro1 = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace adq1 = "http://www.bcie.org/AdquisicionBO";

declare variable $outInvokeConsultarParticipantes.response as element() (:: schema-element(adq:ConsultarParticipantesByIdNoObjecionResponse) ::) external;
declare variable $outInvokeConsultarProducto.response as element() (:: schema-element(pro:ConsultarProductoByIdOperacionResponse) ::) external;
declare variable $outInvokeConsutarAdquisicion.response as element() (:: schema-element(adq:ConsultarAdquisicionResponse) ::) external;


declare function local:funcConsultarroles_to_configuracionadquisicion($outInvokeConsultarParticipantes.response as element() (:: schema-element(adq:ConsultarParticipantesByIdNoObjecionResponse) ::), 
                                                                       $outInvokeConsultarProducto.response as element() (:: schema-element(pro:ConsultarProductoByIdOperacionResponse) ::),
                                                                       $outInvokeConsutarAdquisicion.response as element() (:: schema-element(adq:ConsultarAdquisicionResponse) ::)) 
                                                                       as element() (:: schema-element(con1:ConfiguracionAdquisicionResponse) ::) {
    <con1:ConfiguracionAdquisicionResponse>
        <con1:configuracionAdquisicion>
            <con2:Header> 
                <ns1:Operacion>
                    <ns2:CodigoOperacion>{fn:data($outInvokeConsultarProducto.response/pro:Operacion/ope:idOperacion)}</ns2:CodigoOperacion>
                    <ns2:NombreOperacion>{fn:data($outInvokeConsultarProducto.response/pro:Operacion/ope:nombre)}</ns2:NombreOperacion>
                    <ns2:ResponsableOperacion>{fn:data($outInvokeConsultarProducto.response/pro:Operacion/ope:responsable)}</ns2:ResponsableOperacion>
                    <ns2:CodigoCliente>{fn:data($outInvokeConsultarProducto.response/pro:Operacion/ope:cliente/cli:idCliente)}</ns2:CodigoCliente>
                    <ns2:NombreCliente>{fn:data($outInvokeConsultarProducto.response/pro:Operacion/ope:cliente/cli:razonSocial)}</ns2:NombreCliente>
                    <ns2:CodigoProducto>{fn:data($outInvokeConsultarProducto.response/pro:Producto/pro1:idProducto)}</ns2:CodigoProducto>
                    <ns2:NombreProducto>{fn:data($outInvokeConsultarProducto.response/pro:Producto/pro1:descripcion)}</ns2:NombreProducto>
                    <ns2:MontoSolicitado></ns2:MontoSolicitado>
                    <ns2:Pais>{fn:data($outInvokeConsultarProducto.response/pro:Operacion/ope:cliente/cli:pais/cat:Descripcion)}</ns2:Pais>
                </ns1:Operacion>                
                <ns6:ParameterType>
                  <ns6:parameterName>NUMERO_ADQUISICION</ns6:parameterName>
                  <ns6:parameterValue>{fn:data($outInvokeConsutarAdquisicion.response/adq:Adquisicion/adq1:numero)}</ns6:parameterValue>
                </ns6:ParameterType>
                 <ns6:ParameterType>
                      <ns6:parameterName>TIPO_NO_OBJECION</ns6:parameterName>
                      <ns6:parameterValue>{fn:data($outInvokeConsutarAdquisicion.response/adq:Adquisicion/adq1:noObjecion/adq1:tipoNoObjecion/cat:Descripcion)}</ns6:parameterValue>
                </ns6:ParameterType>
                 <ns6:ParameterType>
                      <ns6:parameterName>ADQUISICION_PREVIA</ns6:parameterName>
                      <ns6:parameterValue>{if (fn:data($outInvokeConsutarAdquisicion.response/adq:Adquisicion/adq1:adquisicionPrevia)= true()) then 1 else 0}</ns6:parameterValue>
                </ns6:ParameterType>
                 <ns6:ParameterType>
                      <ns6:parameterName>SE_REQUIERE_PUBICACION</ns6:parameterName>
                      <ns6:parameterValue>{fn:data($outInvokeConsutarAdquisicion.response/adq:Adquisicion/adq1:noObjecion/adq1:tipoNoObjecion/cat:codigoExterno)}</ns6:parameterValue>
                </ns6:ParameterType>
            </con2:Header>
            <con2:RolesEquipoTrabajo>
            {
            for $rol in $outInvokeConsultarParticipantes.response/adq:rol
            return
                <con2:Rol>
                    <cat:Id>{fn:data($rol/cat:Id)}</cat:Id>
                    <cat:Descripcion>{fn:data($rol/cat:Descripcion)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($rol/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    <cat:estatus>{fn:data($rol/cat:estatus)}</cat:estatus>
                    <cat:codigoExterno>{fn:data($rol/cat:codigoExterno)}</cat:codigoExterno>
                </con2:Rol>
            }
            </con2:RolesEquipoTrabajo>

        </con1:configuracionAdquisicion>
        <con1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación Exitosa</res:message>
        </con1:Resultado>
        
    </con1:ConfiguracionAdquisicionResponse>
};

local:funcConsultarroles_to_configuracionadquisicion($outInvokeConsultarParticipantes.response, $outInvokeConsultarProducto.response,  $outInvokeConsutarAdquisicion.response)
