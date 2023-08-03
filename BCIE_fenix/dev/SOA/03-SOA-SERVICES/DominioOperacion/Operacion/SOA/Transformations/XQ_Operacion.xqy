xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace dec="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ope="http://www.bcie.org/OperacionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/CommonBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope1 = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) external;

declare function local:funcXq_operaciondeclaracion($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) ) 
                                                   as element() (:: schema-element(ope:ConsultarOperacionResponse) ::) {
    <ope:ConsultarOperacionResponse>
        <ope:Operacion>
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:idOperacion)
                then <ope1:idOperacion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:idOperacion)}</ope1:idOperacion>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:responsable)
                then <ope1:responsable>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:responsable)}</ope1:responsable>
                else ()
            }
            <ope1:oficina></ope1:oficina>
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:nombre)
                then <ope1:nombre>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:nombre)}</ope1:nombre>
                else ()
            }
            <ope1:cliente>
                <cli:idCliente>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:idCliente)}</cli:idCliente>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:idFacturador)
                    then <cli:idFacturador>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:idFacturador)}</cli:idFacturador>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:razonSocial)
                    then <cli:razonSocial>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:razonSocial)}</cli:razonSocial>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:abreviatura)
                    then <cli:abreviatura>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:abreviatura)}</cli:abreviatura>
                    else ()
                }
                <cli:tipoPersona>
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:tipoPersona/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:tipoPersona/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:tipoPersona/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:tipoPersona/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </cli:tipoPersona>
                <cli:sector>
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:sector/cat:Id)
                        then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:sector/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:sector/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:sector/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:sector/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:sector/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </cli:sector>
                <cli:tipoInstitucion>
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:tipoInstitucion/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:tipoInstitucion/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                </cli:tipoInstitucion>
                <cli:pais>
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:pais/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:pais/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:pais/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:pais/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </cli:pais>
                <cli:grupoEconomico>
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:grupoEconomico/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:grupoEconomico/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                </cli:grupoEconomico>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:tipoIdentificacion)
                    then <cli:tipoIdentificacion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:tipoIdentificacion)}</cli:tipoIdentificacion>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:numeroIdentificacion)
                    then <cli:numeroIdentificacion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:numeroIdentificacion)}</cli:numeroIdentificacion>
                    else ()
                }
                <cli:oficina>
                    <cat:Id></cat:Id>
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:oficina/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:oficina/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:oficina/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:oficina/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </cli:oficina>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:ejecutivo)
                    then <cli:ejecutivo>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:ejecutivo)}</cli:ejecutivo>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR)
                    then 
                        <cli:SCR>
                            {
                                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:Id)
                                then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:estatus)
                                then <cat:estatus>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cliente/cli:SCR/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </cli:SCR>
                    else ()
                }
            </ope1:cliente>
            <ope1:producto>
                <pro:idProducto>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:producto/pro:idProducto)}</pro:idProducto>
                <pro:descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:producto/pro:descripcion)}</pro:descripcion>
            </ope1:producto>
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:descripcion)
                then <ope1:descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:descripcion)}</ope1:descripcion>
                else ()
            }
            <ope1:actividadEconomica>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomica/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomica/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomica/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomica/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomica/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope1:actividadEconomica>
            <ope1:actividadEconomicaAsociada>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomicaAsociada/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomicaAsociada/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomicaAsociada/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomicaAsociada/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomicaAsociada/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope1:actividadEconomicaAsociada>
            <ope1:areaFocalizacion>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:areaFocalizacion/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:areaFocalizacion/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:areaFocalizacion/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:areaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
            </ope1:areaFocalizacion>
            <ope1:ejeEstrategico>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejeEstrategico/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejeEstrategico/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejeEstrategico/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejeEstrategico/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejeEstrategico/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope1:ejeEstrategico>
            <ope1:iniciativaEstrategica>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:iniciativaEstrategica/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:iniciativaEstrategica/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:iniciativaEstrategica/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:iniciativaEstrategica/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
            </ope1:iniciativaEstrategica>
            <ope1:cantidadPaises>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cantidadPaises/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cantidadPaises/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cantidadPaises/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:cantidadPaises/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
            </ope1:cantidadPaises>
            <ope1:sectorMercado>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorMercado/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorMercado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorMercado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorMercado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
            </ope1:sectorMercado>
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:programadoPOA)
                then <ope1:programadoPOA>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:programadoPOA)}</ope1:programadoPOA>
                else ()
            }
            <ope1:ejercicioPOA>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejercicioPOA/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejercicioPOA/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejercicioPOA/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ejercicioPOA/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
            </ope1:ejercicioPOA>
            <ope1:tipoGarantia>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:tipoGarantia/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:tipoGarantia/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:tipoGarantia/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:tipoGarantia/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
            </ope1:tipoGarantia>
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:componenteConcesionalidad)
                then <ope1:componenteConcesionalidad>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:componenteConcesionalidad)}</ope1:componenteConcesionalidad>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estructurador)
                then <ope1:estructurador>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estructurador)}</ope1:estructurador>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:beneficiario)
                then <ope1:beneficiario>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:beneficiario)}</ope1:beneficiario>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:unidadEjecutora)
                then <ope1:unidadEjecutora>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:unidadEjecutora)}</ope1:unidadEjecutora>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:programa)
                then <ope1:programa>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:programa)}</ope1:programa>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:etapa)
                then <ope1:etapa>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:etapa)}</ope1:etapa>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:status)
                then <ope1:status>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:status)}</ope1:status>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:fechaBaja)
                then <ope1:fechaBaja>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:fechaBaja)}</ope1:fechaBaja>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:calificacionRiesgo)
                then <ope1:calificacionRiesgo>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:calificacionRiesgo)}</ope1:calificacionRiesgo>
                else ()
            }
            <ope1:perspectiva>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:perspectiva/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:perspectiva/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:perspectiva/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:perspectiva/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
            </ope1:perspectiva>
            <ope1:estado>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:estatus)
                    then <cat:estatus>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:estado/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope1:estado>
            <ope1:SRC>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:SRC/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:SRC/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:SRC/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:SRC/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:SRC/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:SRC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                <cat:estatus></cat:estatus>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:SRC/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:SRC/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope1:SRC>
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos)
                then 
                    <ope1:scrClienteDesembolsos>
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:Id)
                            then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:estatus)
                            then <cat:estatus>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:scrClienteDesembolsos/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope1:scrClienteDesembolsos>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:RAROC)
                then <ope1:RAROC>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:RAROC)}</ope1:RAROC>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:TIR)
                then <ope1:TIR>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:TIR)}</ope1:TIR>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:TIREstatus)
                then <ope1:TIREstatus>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:TIREstatus)}</ope1:TIREstatus>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:requiereRecursosExternos)
                then <ope1:requiereRecursosExternos>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:requiereRecursosExternos)}</ope1:requiereRecursosExternos>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:enProcesoLaft)
                then <ope1:enProcesoLaft>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:enProcesoLaft)}</ope1:enProcesoLaft>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:aplicaLaft)
                then <ope1:aplicaLaft>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:aplicaLaft)}</ope1:aplicaLaft>
                else ()
            }
            <ope1:montoOperacion>
                {
                    for $montoOperacion in $InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:montoOperacion/ope1:montoOperacion
                    return 
                    <ope1:montoOperacion>
                        {
                            if ($montoOperacion/ope1:id)
                            then <ope1:id>{fn:data($montoOperacion/ope1:id)}</ope1:id>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope1:idOperacion)
                            then <ope1:idOperacion>{fn:data($montoOperacion/ope1:idOperacion)}</ope1:idOperacion>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope1:IdTcaTipoMonto)
                            then <ope1:IdTcaTipoMonto>{fn:data($montoOperacion/ope1:IdTcaTipoMonto)}</ope1:IdTcaTipoMonto>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope1:monto)
                            then <ope1:monto>{fn:data($montoOperacion/ope1:monto)}</ope1:monto>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope1:Descripcion)
                            then <ope1:Descripcion>{fn:data($montoOperacion/ope1:Descripcion)}</ope1:Descripcion>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope1:fechaRegistro)
                            then <ope1:fechaRegistro>{fn:data($montoOperacion/ope1:fechaRegistro)}</ope1:fechaRegistro>
                            else ()
                        }
                        {
                            if ($montoOperacion/ope1:estado)
                            then <ope1:estado>{fn:data($montoOperacion/ope1:estado)}</ope1:estado>
                            else ()
                        }
                    </ope1:montoOperacion>
                }
            </ope1:montoOperacion>
             {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:esMultisectorial)
                then <ope1:esMultisectorial>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:esMultisectorial)}</ope1:esMultisectorial>
                else ()
            }
             <ope1:clasificacionEstrategicaMultisectorial>
            {
                for $clasificacionEstrategicaMultisectorial in $InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:clasificacionEstrategicaMultisectorial/ope1:componenteClasificacionEstrategica
                return 
                <ope1:componenteClasificacionEstrategica>
                    {
                        if ($clasificacionEstrategicaMultisectorial/ope1:nombreComponente)
                        then <ope1:nombreComponente>{fn:data($clasificacionEstrategicaMultisectorial/ope1:nombreComponente)}</ope1:nombreComponente>
                        else ()
                    }
                    {
                        if ($clasificacionEstrategicaMultisectorial/ope1:descripcion)
                        then <ope1:descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:descripcion)}</ope1:descripcion>
                        else ()
                    }
                    {
                        if ($clasificacionEstrategicaMultisectorial/ope1:porcentaje)
                        then <ope1:porcentaje>{fn:data($clasificacionEstrategicaMultisectorial/ope1:porcentaje)}</ope1:porcentaje>
                        else ()
                    }
                    {
                        if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica)
                        then 
                            <ope1:actividadEconomica>
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:Id)
                                    then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope1:actividadEconomica>
                        else ()
                    }
                    {
                        if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada)
                        then 
                            <ope1:actividadEconomicaAsociada>
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:Id)
                                    then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($clasificacionEstrategicaMultisectorial/ope1:actividadEconomicaAsociada/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </ope1:actividadEconomicaAsociada>
                        else ()
                    }
                    {
                        if ($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion)
                        then 
                            <ope1:areaFocalizacion>
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion/cat:Id)
                                    then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:areaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                            </ope1:areaFocalizacion>
                        else ()
                    }
                    {
                        if ($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico)
                        then 
                            <ope1:ejeEstrategico>
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:Id)
                                    then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:ejeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                            </ope1:ejeEstrategico>
                        else ()
                    }
                    {
                        if ($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica)
                        then 
                            <ope1:iniciativaEstrategica>
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica/cat:Id)
                                    then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:iniciativaEstrategica/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                            </ope1:iniciativaEstrategica>
                        else ()
                    }
                    {
                        if ($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises)
                        then 
                            <ope1:cantidadPaises>
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises/cat:Id)
                                    then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:cantidadPaises/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                            </ope1:cantidadPaises>
                        else ()
                    }
                    <ope1:sectorIbcie>
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:Id)
                            then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:sectorIbcie/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                    </ope1:sectorIbcie>
                    <ope1:subSectorIbcie>
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:Id)
                            then <cat:Id>{fn:data($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($clasificacionEstrategicaMultisectorial/ope1:subSectorIbcie/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                    </ope1:subSectorIbcie>
                </ope1:componenteClasificacionEstrategica>
            }
            </ope1:clasificacionEstrategicaMultisectorial>
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional)
                then 
                    <ope1:sectorInstitucional>
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:Id)
                            then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:estatus)
                            then <cat:estatus>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:sectorInstitucional/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope1:sectorInstitucional>
                else ()
            }
           
            
            <ope1:TipoMoneda>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:TipoMoneda/cat:Id)
                    then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:TipoMoneda/cat:Id)}</cat:Id>
                    else ()
                }
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                {
                    if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:TipoMoneda/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:TipoMoneda/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </ope1:TipoMoneda>
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado)
                then 
                    <ope1:encargado>
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:Id)
                            then <cat:Id>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:estatus)
                            then <cat:estatus>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:encargado/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ope1:encargado>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:idRol)
                then <ope1:idRol>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:idRol)}</ope1:idRol>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:descripcionRol)
                then <ope1:descripcionRol>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:descripcionRol)}</ope1:descripcionRol>
                else ()
            }
            {
                if ($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ubicacionProyecto)
                then <ope1:ubicacionProyecto>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:ubicacionProyecto)}</ope1:ubicacionProyecto>
                else ()
            }
            {                
                if (xs:string($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion[1]/ope1:idCatPais)!='')
                then <ope1:idCatPais>{fn:data($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response/ope:Operacion/ope1:idCatPais)}</ope1:idCatPais>
                else ()
            }
         
        </ope:Operacion>
        {if(fn:count($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response[1]) > 0)then
          <ope:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
          </ope:Resultado>
        else
          <ope:Resultado>
            <res:result>EEROR</res:result>
            <res:message>Consulta sin resultados</res:message>
          </ope:Resultado>
        }
    </ope:ConsultarOperacionResponse>
};

local:funcXq_operaciondeclaracion($InvokeConsultarOperacion_ConsultarOperacionById_OutputVariable.response)
