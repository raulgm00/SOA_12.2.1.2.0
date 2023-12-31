<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Analisis/Payload/V1"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns1="http://www.bcie.org/OperacionMO"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 ns1 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:ns8="http://www.bcie.org/TerminoBO" xmlns:regBO="http://www.bcie.org/ReglaBO"
                xmlns:cuesBO="http://www.bcie.org/CuestionarioBO" xmlns:operBO="http://www.bcie.org/OperacionBO"
                xmlns:ns10="http://www.bcie.org/CommonBO" xmlns:resBO="http://www.bcie.org/ResultBO"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:notificacion="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:ns14="http://www.bcie.org/MatrizTCCBO" xmlns:linCreBO="http://www.bcie.org/LineaCreditoBO"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns7="http://www.bcie.org/CondicionBO" xmlns:prodBO="http://www.bcie.org/ProductoBO"
                xmlns:ns9="http://www.bcie.org/DeclaracionJuradaBO"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:ns11="http://www.bcie.org/DocumentoBO" xmlns:ns12="http://www.bcie.org/HerramientaCEBO"
                xmlns:ns13="http://www.bcie.org/ComisionBO" xmlns:ns2="http://www.bcie.org/CatalogoBO"
                xmlns:desBO="http://www.bcie.org/DesembolsoBO" xmlns:ns15="http://www.bcie.org/ContratoBO"
                xmlns:clieBO="http://www.bcie.org/ClienteBO" xmlns:AtributoBO="http://www.bcie.org/AtributoBO"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1" xmlns:ns16="http://www.bcie.org/ErrorBO">
    <oracle-xsl-mapper:schema>
        <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
        <oracle-xsl-mapper:mapSources>
            <oracle-xsl-mapper:source type="XSD">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC03/AnalisisPayload.xsd"/>
                <oracle-xsl-mapper:rootElement name="AnalisisPayload"
                                               namespace="http://xmlns.bcie.org/ObjetoProceso/Analisis/Payload/V1"/>
                <oracle-xsl-mapper:param name="analisisDO"/>
            </oracle-xsl-mapper:source>
            <oracle-xsl-mapper:source type="XSD">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd"/>
                <oracle-xsl-mapper:rootElement name="PropagarSCRyTIRResponse"
                                               namespace="http://www.bcie.org/OperacionMO"/>
                <oracle-xsl-mapper:param name="propagarSCRyTIRResponse"/>
            </oracle-xsl-mapper:source>
        </oracle-xsl-mapper:mapSources>
        <oracle-xsl-mapper:mapTargets>
            <oracle-xsl-mapper:target type="XSD">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC03/AnalisisPayload.xsd"/>
                <oracle-xsl-mapper:rootElement name="AnalisisPayload"
                                               namespace="http://xmlns.bcie.org/ObjetoProceso/Analisis/Payload/V1"/>
            </oracle-xsl-mapper:target>
        </oracle-xsl-mapper:mapTargets>
        <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [MON FEB 17 10:21:10 CST 2020].-->
    </oracle-xsl-mapper:schema>
    <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
    <xsl:param name="propagarSCRyTIRResponse"/>
    <xsl:template match="/">
        <ns0:AnalisisPayload>
            <ns0:Header>
                <header:Operacion>
                    <ns3:CodigoOperacion>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:CodigoOperacion"/>
                    </ns3:CodigoOperacion>
                    <ns3:NombreOperacion>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:NombreOperacion"/>
                    </ns3:NombreOperacion>
                    <xsl:if test="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:ResponsableOperacion/text()">
                        <ns3:ResponsableOperacion>
                            <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:ResponsableOperacion"/>
                        </ns3:ResponsableOperacion>
                    </xsl:if>
                    <ns3:CodigoCliente>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:CodigoCliente"/>
                    </ns3:CodigoCliente>
                    <ns3:NombreCliente>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:NombreCliente"/>
                    </ns3:NombreCliente>
                    <xsl:if test="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:CodigoProducto/text()">
                        <ns3:CodigoProducto>
                            <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:CodigoProducto"/>
                        </ns3:CodigoProducto>
                    </xsl:if>
                    <ns3:NombreProducto>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:NombreProducto"/>
                    </ns3:NombreProducto>
                    <xsl:if test="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:MontoSolicitado/text()">
                        <ns3:MontoSolicitado>
                            <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:MontoSolicitado"/>
                        </ns3:MontoSolicitado>
                    </xsl:if>
                    <ns3:Pais>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns3:Pais"/>
                    </ns3:Pais>
                </header:Operacion>
                <header:Tarea>
                    <ns2:CodigoTarea>217</ns2:CodigoTarea>
                    <ns2:NombreTarea>Error al propagar SCR</ns2:NombreTarea>
                    <ns2:CodigoRol>10</ns2:CodigoRol>
                    <ns2:NombreRol>Analista de Seguimiento Crediticio</ns2:NombreRol>
                    <ns2:CodigoProceso>3</ns2:CodigoProceso>
                    <ns2:NombreProceso>Análisis</ns2:NombreProceso>
                </header:Tarea>
                <header:Proceso>
                    <ns4:IdProceso>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Proceso/ns4:IdProceso"/>
                    </ns4:IdProceso>
                    <ns4:CodigoProceso>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Proceso/ns4:CodigoProceso"/>
                    </ns4:CodigoProceso>
                    <ns4:NombreProceso>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Proceso/ns4:NombreProceso"/>
                    </ns4:NombreProceso>
                    <ns4:EsProcesoCore>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Proceso/ns4:EsProcesoCore"/>
                    </ns4:EsProcesoCore>
                    <ns4:RolIniciaProceso>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Proceso/ns4:RolIniciaProceso"/>
                    </ns4:RolIniciaProceso>
                    <ns4:UsuarioIniciaProceso>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Proceso/ns4:UsuarioIniciaProceso"/>
                    </ns4:UsuarioIniciaProceso>
                    <ns4:InstanciaProceso>
                        <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Proceso/ns4:InstanciaProceso"/>
                    </ns4:InstanciaProceso>
                    <ns4:IdFlujo/>
                </header:Proceso>
                <xsl:for-each select="/ns0:AnalisisPayload/ns0:Header/parameter:ParameterType">
                    <parameter:ParameterType>
                        <parameter:parameterName>
                            <xsl:value-of select="parameter:parameterName"/>
                        </parameter:parameterName>
                        <parameter:parameterValue>
                            <xsl:value-of select="parameter:parameterValue"/>
                        </parameter:parameterValue>
                    </parameter:ParameterType>
                </xsl:for-each>
            </ns0:Header>
            <ns0:Configuration>
                <ns0:retornoAprobacion>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:Configuration/ns0:retornoAprobacion"/>
                </ns0:retornoAprobacion>
                <ns0:tieneRiesgoCredito>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:Configuration/ns0:tieneRiesgoCredito"/>
                </ns0:tieneRiesgoCredito>
                <ns0:requireOpinionJuridica>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:Configuration/ns0:requireOpinionJuridica"/>
                </ns0:requireOpinionJuridica>
                <ns0:requireSCR>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:Configuration/ns0:requireSCR"/>
                </ns0:requireSCR>
                <ns0:esInstitucionFinanciera>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:Configuration/ns0:esInstitucionFinanciera"/>
                </ns0:esInstitucionFinanciera>
                <ns0:responsableAreaTecnica>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:Configuration/ns0:responsableAreaTecnica"/>
                </ns0:responsableAreaTecnica>
            </ns0:Configuration>
            <ns0:EquipoPayload>
                <ns0:coordinadorSeguimiento>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoPayload/ns0:coordinadorSeguimiento"/>
                </ns0:coordinadorSeguimiento>
                <ns0:analistaSeguimiento>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoPayload/ns0:analistaSeguimiento"/>
                </ns0:analistaSeguimiento>
                <ns0:analistaCredito>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoPayload/ns0:analistaCredito"/>
                </ns0:analistaCredito>
                <ns0:abogado>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoPayload/ns0:abogado"/>
                </ns0:abogado>
                <ns0:ejecutivoAreaTecnica>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoPayload/ns0:ejecutivoAreaTecnica"/>
                </ns0:ejecutivoAreaTecnica>
                <ns0:jefeAreaTecnica>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoPayload/ns0:jefeAreaTecnica"/>
                </ns0:jefeAreaTecnica>
                <ns0:gerente>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoPayload/ns0:gerente"/>
                </ns0:gerente>
                <ns0:control>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoPayload/ns0:control"/>
                </ns0:control>
            </ns0:EquipoPayload>
            <ns0:ValorSCR>
                <xsl:value-of select="/ns0:AnalisisPayload/ns0:ValorSCR"/>
            </ns0:ValorSCR>
            <ns0:CodigoSCR>
                <xsl:value-of select="/ns0:AnalisisPayload/ns0:CodigoSCR"/>
            </ns0:CodigoSCR>
            <ns0:AprobacionSCR>
                <xsl:value-of select="/ns0:AnalisisPayload/ns0:AprobacionSCR"/>
            </ns0:AprobacionSCR>
            <parameter:ParameterType>
                <parameter:parameterName>Resultado</parameter:parameterName>
                <parameter:parameterValue>
                    <xsl:value-of select="$propagarSCRyTIRResponse/ns1:PropagarSCRyTIRResponse/ns1:Resultado/resBO:result"/>
                </parameter:parameterValue>
            </parameter:ParameterType>
            <parameter:ParameterType>
                <parameter:parameterName>Mensaje</parameter:parameterName>
                <parameter:parameterValue>
                    <xsl:value-of select="$propagarSCRyTIRResponse/ns1:PropagarSCRyTIRResponse/ns1:Resultado/resBO:message"/>
                </parameter:parameterValue>
            </parameter:ParameterType>
            <ns0:EquipoEjecucionPayload>
                <ns0:coordinadorSeguimiento>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoEjecucionPayload/ns0:coordinadorSeguimiento"/>
                </ns0:coordinadorSeguimiento>
                <ns0:analistaSeguimiento>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoEjecucionPayload/ns0:analistaSeguimiento"/>
                </ns0:analistaSeguimiento>
                <ns0:analistaCredito>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoEjecucionPayload/ns0:analistaCredito"/>
                </ns0:analistaCredito>
                <ns0:abogado>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoEjecucionPayload/ns0:abogado"/>
                </ns0:abogado>
                <ns0:ejecutivoAreaTecnica>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoEjecucionPayload/ns0:ejecutivoAreaTecnica"/>
                </ns0:ejecutivoAreaTecnica>
                <ns0:jefeAreaTecnica>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoEjecucionPayload/ns0:jefeAreaTecnica"/>
                </ns0:jefeAreaTecnica>
                <ns0:gerente>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoEjecucionPayload/ns0:gerente"/>
                </ns0:gerente>
                <ns0:control>
                    <xsl:value-of select="/ns0:AnalisisPayload/ns0:EquipoEjecucionPayload/ns0:control"/>
                </ns0:control>
            </ns0:EquipoEjecucionPayload>
        </ns0:AnalisisPayload>
    </xsl:template>
</xsl:stylesheet>
