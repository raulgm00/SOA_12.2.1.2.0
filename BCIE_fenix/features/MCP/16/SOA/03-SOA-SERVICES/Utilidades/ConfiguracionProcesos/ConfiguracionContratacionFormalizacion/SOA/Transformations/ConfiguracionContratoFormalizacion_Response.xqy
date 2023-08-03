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

declare namespace pro1 = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace con3 = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare variable $InvokeConsultarProductoByOperacionout.response as element() (:: schema-element(pro:ConsultarProductoByIdOperacionResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(con:ConfiguracionContratacionFormalizacionRequest) ::) external;
declare variable $outInvokeConsultarRolPorProceso.response as element() (:: schema-element(con1:responseConsultarRolesPorProcesoMessage) ::) external;

declare function local:funcConfiguracioncontratoformalizacion_response($InvokeConsultarProductoByOperacionout.response as element() (:: schema-element(pro:ConsultarProductoByIdOperacionResponse) ::), 
                                                                       $inputVariable.request as element() (:: schema-element(con:ConfiguracionContratacionFormalizacionRequest) ::), 
                                                                       $outInvokeConsultarRolPorProceso.response as element() (:: schema-element(con1:responseConsultarRolesPorProcesoMessage) ::)) 
                                                                       as element() (:: schema-element(con:ConfiguracionContratacionFormalizacionResponse) ::) {
    <con:ConfiguracionContratacionFormalizacionResponse>
        <con:configuracionContratoFormalizacion>
            <con2:Header>
                {
                    if (fn:true()) then 
                        <ns1:Operacion>
                            <ns2:CodigoOperacion>{fn:data($InvokeConsultarProductoByOperacionout.response/pro:Operacion/ope:idOperacion)}</ns2:CodigoOperacion>
                            <ns2:NombreOperacion>{fn:data($InvokeConsultarProductoByOperacionout.response/pro:Operacion/ope:nombre)}</ns2:NombreOperacion>
                            {
                                if ($InvokeConsultarProductoByOperacionout.response/pro:Operacion/ope:responsable)
                                then <ns2:ResponsableOperacion>{fn:data($InvokeConsultarProductoByOperacionout.response/pro:Operacion/ope:responsable)}</ns2:ResponsableOperacion>
                                else ()
                            }
                            <ns2:CodigoCliente>{fn:data($InvokeConsultarProductoByOperacionout.response/pro:Operacion/ope:cliente/cli:idCliente)}</ns2:CodigoCliente>
                            <ns2:NombreCliente>{fn:data($InvokeConsultarProductoByOperacionout.response/pro:Operacion/ope:cliente/cli:razonSocial)}</ns2:NombreCliente>
                            <ns2:CodigoProducto>{fn:data($InvokeConsultarProductoByOperacionout.response/pro:Producto/pro1:idProducto)}</ns2:CodigoProducto>
                            <ns2:NombreProducto>{fn:data($InvokeConsultarProductoByOperacionout.response/pro:Producto/pro1:descripcion)}</ns2:NombreProducto>
                            <ns2:MontoSolicitado></ns2:MontoSolicitado>
                            <ns2:Pais>{fn:data($InvokeConsultarProductoByOperacionout.response/pro:Operacion/ope:cliente/cli:pais/cat:DescripcionCorta)}</ns2:Pais>
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
                <ns1:Proceso>
                    <ns5:IdFlujo>{fn:data($inputVariable.request/con:intanciaProceso)}</ns5:IdFlujo>
                </ns1:Proceso>
            </con2:Header>
            <con2:producto>
                <pro1:idProducto></pro1:idProducto>
                <pro1:descripcion></pro1:descripcion>
                <pro1:descripcionCorta></pro1:descripcionCorta>
                <pro1:fechaRegistro></pro1:fechaRegistro>
                <pro1:codExterno></pro1:codExterno>
                <pro1:idInstrumentoFinanciero></pro1:idInstrumentoFinanciero>
                <pro1:idTipoOperacion></pro1:idTipoOperacion>
                <pro1:reglas>
                    <pro1:banStatus></pro1:banStatus>
                    <pro1:requiereElegibilidad></pro1:requiereElegibilidad>
                    <pro1:requiereLaft></pro1:requiereLaft>
                    <pro1:OFICrealizaAnalisisLAFT></pro1:OFICrealizaAnalisisLAFT>
                    <pro1:requiereEvaluacionExAnte></pro1:requiereEvaluacionExAnte>
                    <pro1:requiereFormularProgramas></pro1:requiereFormularProgramas>
                    <pro1:elaboraAnalizarAdquisiciones></pro1:elaboraAnalizarAdquisiciones>
                    <pro1:elaboraHojaTerminosPCT></pro1:elaboraHojaTerminosPCT>
                    <pro1:elaboraHojaTerminosSEPRI></pro1:elaboraHojaTerminosSEPRI>
                    <pro1:analizarModelo></pro1:analizarModelo>
                    <pro1:realizarPreanalisis></pro1:realizarPreanalisis>
                    <pro1:participaSupervision></pro1:participaSupervision>
                    <pro1:participaSeguimiento></pro1:participaSeguimiento>
                    <pro1:participaFINAM></pro1:participaFINAM>
                    <pro1:SRC></pro1:SRC>
                    <pro1:OpinionJuridica></pro1:OpinionJuridica>
                    <pro1:firmaContrato></pro1:firmaContrato>
                    <pro1:condicionesPreviasFormalizar></pro1:condicionesPreviasFormalizar>
                    <pro1:condicionesPreviasDesembolso></pro1:condicionesPreviasDesembolso>
                    <pro1:programacionDesembolsos></pro1:programacionDesembolsos>
                    <pro1:reglaLAFT></pro1:reglaLAFT>
                    <pro1:adquisiciones2></pro1:adquisiciones2>
                    <pro1:supervicionTecnica></pro1:supervicionTecnica>
                    <pro1:seguimientoCrediticio></pro1:seguimientoCrediticio>
                    <pro1:enmiendas></pro1:enmiendas>
                    <pro1:recuperacion></pro1:recuperacion>
                    <pro1:cierreOperativo></pro1:cierreOperativo>
                    <pro1:rendicionCuentas></pro1:rendicionCuentas>
                    <pro1:evaluacionMedioTermino></pro1:evaluacionMedioTermino>
                    <pro1:evaluacionExPost></pro1:evaluacionExPost>
                    <pro1:requiereAdquisiciones></pro1:requiereAdquisiciones>
                    <pro1:requiereRAROC></pro1:requiereRAROC>
                    <pro1:requiereIBICIE></pro1:requiereIBICIE>
                    <pro1:requiereSIEMAS></pro1:requiereSIEMAS>
                    <pro1:requiereGERIESElegibilidad></pro1:requiereGERIESElegibilidad>
                    <pro1:requiereOpinionTecnica></pro1:requiereOpinionTecnica>
                    <pro1:requiereAsjurAnalisis></pro1:requiereAsjurAnalisis>
                    <pro1:requiereAsjurElegibilidad></pro1:requiereAsjurElegibilidad>
                    <pro1:requierePreparacion></pro1:requierePreparacion>
                    <pro1:tieneRiesgoCredito></pro1:tieneRiesgoCredito>
                    <pro1:responsableAnalisis></pro1:responsableAnalisis>
                    <pro1:nombreResponsableAnalisis></pro1:nombreResponsableAnalisis>
                    <pro1:requiereFirmacontrato></pro1:requiereFirmacontrato>
                    <pro1:requiereRegistroLinea></pro1:requiereRegistroLinea>
                    <pro1:requiereCore></pro1:requiereCore>
                </pro1:reglas>
                <pro1:instrumentoFinanciero>
                    <pro1:descripcion></pro1:descripcion>
                    <pro1:descripcionCorta></pro1:descripcionCorta>
                    <pro1:estado></pro1:estado>
                    <pro1:fechaRegistro></pro1:fechaRegistro>
                    <pro1:codigoExterno></pro1:codigoExterno>
                </pro1:instrumentoFinanciero>
                <pro1:estatus></pro1:estatus>
                <pro1:Requiere_Cierre>
                    <pro1:Requiere_Cierre></pro1:Requiere_Cierre>
                    <pro1:Tipo_Cierre></pro1:Tipo_Cierre>
                </pro1:Requiere_Cierre>
            </con2:producto>
            <con2:RolesEquipoTrabajo>
                {
                    for $listadoRoles in $outInvokeConsultarRolPorProceso.response/con1:ListadoRoles/con3:listadoRoles
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
        </con:configuracionContratoFormalizacion>
        <con:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion exitosa</res:message>
        </con:Resultado>
    </con:ConfiguracionContratacionFormalizacionResponse>
};

local:funcConfiguracioncontratoformalizacion_response($InvokeConsultarProductoByOperacionout.response, $inputVariable.request, $outInvokeConsultarRolPorProceso.response)
