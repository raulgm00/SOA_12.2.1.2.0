<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:ns0="http://www.bcie.org/ConfiguracionProcesosMO"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns1="http://xmlns.bcie.org/ObjetoProceso/SupervisionAmbientalSocial/Payload/V1"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 ns1 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:confBO="http://www.bcie.org/ConfiguracionProcesosBO" xmlns:ns2="http://www.bcie.org/TerminoBO"
                xmlns:ns3="http://www.bcie.org/ReglaBO" xmlns:operBO="http://www.bcie.org/OperacionBO"
                xmlns:resBO="http://www.bcie.org/ResultBO" xmlns:cmnBO="http://www.bcie.org/CommonBO"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns5="http://www.bcie.org/CrearBitacoraProcesoBO" xmlns:ns6="http://www.bcie.org/LineaCreditoBO"
                xmlns:ns7="http://www.bcie.org/MatrizTCCBO"
                xmlns:ns8="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns9="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns11="http://www.bcie.org/ProductoBO" xmlns:ns10="http://www.bcie.org/CondicionBO"
                xmlns:ns12="http://www.bcie.org/DeclaracionJuradaBO"
                xmlns:ns13="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:ns14="http://www.bcie.org/ImplementacionPctBO" xmlns:ns15="http://www.bcie.org/DocumentoBO"
                xmlns:ns16="http://www.bcie.org/HerramientaCEBO" xmlns:ns17="http://www.bcie.org/ComisionBO"
                xmlns:ns18="http://www.bcie.org/CatalogoBO" xmlns:desBO="http://www.bcie.org/DesembolsoBO"
                xmlns:ns19="http://www.bcie.org/AdquisicionBO" xmlns:ns20="http://www.bcie.org/ContratoBO"
                xmlns:cteBO="http://www.bcie.org/ClienteBO" xmlns:ns21="http://www.bcie.org/AtributoBO"
                xmlns:ns23="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:ns22="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns24="http://www.bcie.org/ErrorBO">
    <oracle-xsl-mapper:schema>
        <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
        <oracle-xsl-mapper:mapSources>
            <oracle-xsl-mapper:source type="XSD">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd"/>
                <oracle-xsl-mapper:rootElement name="configuracionSupervisionAmbientalSocialResponse"
                                               namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
                <oracle-xsl-mapper:param name="configuracionSupervisionAmbientalSocialResponse"/>
            </oracle-xsl-mapper:source>
            <oracle-xsl-mapper:source type="XSD">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA18/SupervisionAmbientalSocialPayload.xsd"/>
                <oracle-xsl-mapper:rootElement name="SupervisionAmbientalSocialPayload"
                                               namespace="http://xmlns.bcie.org/ObjetoProceso/SupervisionAmbientalSocial/Payload/V1"/>
                <oracle-xsl-mapper:param name="supervisionDO"/>
            </oracle-xsl-mapper:source>
        </oracle-xsl-mapper:mapSources>
        <oracle-xsl-mapper:mapTargets>
            <oracle-xsl-mapper:target type="XSD">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA18/SupervisionAmbientalSocialPayload.xsd"/>
                <oracle-xsl-mapper:rootElement name="SupervisionAmbientalSocialPayload"
                                               namespace="http://xmlns.bcie.org/ObjetoProceso/SupervisionAmbientalSocial/Payload/V1"/>
            </oracle-xsl-mapper:target>
        </oracle-xsl-mapper:mapTargets>
        <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [TUE OCT 26 21:04:23 COT 2021].-->
    </oracle-xsl-mapper:schema>
    <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
    <xsl:param name="supervisionDO"/>
    <xsl:template match="/">
        <ns1:SupervisionAmbientalSocialPayload>
            <ns1:Header>
                <ns13:Operacion>
                    <ns8:CodigoOperacion>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Operacion/ns8:CodigoOperacion"/>
                    </ns8:CodigoOperacion>
                    <ns8:NombreOperacion>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Operacion/ns8:NombreOperacion"/>
                    </ns8:NombreOperacion>
                    <ns8:ResponsableOperacion>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Operacion/ns8:ResponsableOperacion"/>
                    </ns8:ResponsableOperacion>
                    <ns8:CodigoCliente>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Operacion/ns8:CodigoCliente"/>
                    </ns8:CodigoCliente>
                    <ns8:NombreCliente>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Operacion/ns8:NombreCliente"/>
                    </ns8:NombreCliente>
                    <ns8:CodigoProducto>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Operacion/ns8:CodigoProducto"/>
                    </ns8:CodigoProducto>
                    <ns8:NombreProducto>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Operacion/ns8:NombreProducto"/>
                    </ns8:NombreProducto>
                    <ns8:MontoSolicitado>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Operacion/ns8:MontoSolicitado"/>
                    </ns8:MontoSolicitado>
                    <ns8:Pais>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Operacion/ns8:Pais"/>
                    </ns8:Pais>
                </ns13:Operacion>
                <ns13:Tarea>
                    <ns22:CodigoTarea>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Tarea/ns22:CodigoTarea"/>
                    </ns22:CodigoTarea>
                    <ns22:NombreTarea>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Tarea/ns22:NombreTarea"/>
                    </ns22:NombreTarea>
                    <ns22:CodigoRol>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Tarea/ns22:CodigoRol"/>
                    </ns22:CodigoRol>
                    <ns22:NombreRol>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Tarea/ns22:NombreRol"/>
                    </ns22:NombreRol>
                    <ns22:CodigoProceso>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Tarea/ns22:CodigoProceso"/>
                    </ns22:CodigoProceso>
                    <ns22:NombreProceso>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Tarea/ns22:NombreProceso"/>
                    </ns22:NombreProceso>
                </ns13:Tarea>
                <ns13:Proceso>
                    <ns9:IdProceso>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Proceso/ns9:IdProceso"/>
                    </ns9:IdProceso>
                    <ns9:CodigoProceso>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Proceso/ns9:CodigoProceso"/>
                    </ns9:CodigoProceso>
                    <ns9:NombreProceso>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Proceso/ns9:NombreProceso"/>
                    </ns9:NombreProceso>
                    <ns9:EsProcesoCore>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Proceso/ns9:EsProcesoCore"/>
                    </ns9:EsProcesoCore>
                    <ns9:RolIniciaProceso>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Proceso/ns9:RolIniciaProceso"/>
                    </ns9:RolIniciaProceso>
                    <ns9:UsuarioIniciaProceso>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Proceso/ns9:UsuarioIniciaProceso"/>
                    </ns9:UsuarioIniciaProceso>
                    <ns9:InstanciaProceso>
                        <xsl:value-of select="$supervisionDO/ns1:SupervisionAmbientalSocialPayload/ns1:Header/ns13:Proceso/ns9:InstanciaProceso"/>
                    </ns9:InstanciaProceso>
                    <ns9:IdFlujo>
                        <xsl:value-of select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns13:Proceso/ns9:IdFlujo"/>
                    </ns9:IdFlujo>
                </ns13:Proceso>
                <xsl:for-each select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns4:ParameterType">
                    <ns4:ParameterType>
                        <ns4:parameterName>
                            <xsl:value-of select="ns4:parameterName"/>
                        </ns4:parameterName>
                        <ns4:parameterValue>
                            <xsl:value-of select="ns4:parameterValue"/>
                        </ns4:parameterValue>
                    </ns4:ParameterType>
                </xsl:for-each>
            </ns1:Header>
            <ns1:ConfigGateways/>
            <ns1:avanceFisico/>
            <ns1:avanceFinanciero/>
            <ns1:SCT/>
            <ns1:requiereAjustes/>
            <xsl:for-each select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:RolesEquipoTrabajo/confBO:Rol">
                <ns1:Equipo>
                    <xsl:if test="ns18:Id = 63">
                        <ns1:analistaSupervision>
                            <xsl:value-of select="ns18:DescripcionCorta"/>
                        </ns1:analistaSupervision>
                    </xsl:if>
                </ns1:Equipo>
            </xsl:for-each>
            <ns1:EquipoEjecucion>
                <ns1:analistaSupervision/>
            </ns1:EquipoEjecucion>
            <xsl:for-each select="/ns0:configuracionSupervisionAmbientalSocialResponse/ns0:configuracionSupervision/confBO:Header/ns4:ParameterType">
                <ns4:ParameterType>
                    <ns4:parameterName>
                        <xsl:value-of select="ns4:parameterName"/>
                    </ns4:parameterName>
                    <ns4:parameterValue>
                        <xsl:value-of select="ns4:parameterValue"/>
                    </ns4:parameterValue>
                </ns4:ParameterType>
            </xsl:for-each>
        </ns1:SupervisionAmbientalSocialPayload>
    </xsl:template>
</xsl:stylesheet>
