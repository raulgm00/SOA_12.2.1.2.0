<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:ns1="http://www.bcie.org/ConsultarRolesPorProcesoMO"
                xmlns:ns0="http://www.bcie.org/ConfiguracionProcesosMO"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns1 ns0 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:ns2="http://www.bcie.org/TerminoBO" xmlns:ns3="http://www.bcie.org/ConfiguracionProcesosBO"
                xmlns:ns4="http://www.bcie.org/ReglaBO" xmlns:ns5="http://www.bcie.org/OperacionBO"
                xmlns:ns6="http://www.bcie.org/ResultBO" xmlns:ns7="http://www.bcie.org/CommonBO"
                xmlns:ns28="http://www.bcie.com/ConsultarRolesPorProceso"
                xmlns:ns8="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns10="http://www.bcie.org/MatrizTCCBO" xmlns:ns9="http://www.bcie.org/LineaCreditoBO"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                xmlns:ns11="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ns12="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns14="http://www.bcie.org/CondicionBO" xmlns:ns13="http://www.bcie.org/ProductoBO"
                xmlns:ns29="http://www.bcie.org/ConsultarRolesPorProcesoBO"
                xmlns:ns15="http://www.bcie.org/DeclaracionJuradaBO"
                xmlns:ns16="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:ns17="http://www.bcie.org/DocumentoBO" xmlns:ns18="http://www.bcie.org/HerramientaCEBO"
                xmlns:ns19="http://www.bcie.org/ComisionBO"
                xmlns:tns="http://xmlns.bcie.com/ConfiguracionProcesosService"
                xmlns:ns20="http://www.bcie.org/CatalogoBO" xmlns:ns21="http://www.bcie.org/DesembolsoBO"
                xmlns:ns22="http://www.bcie.org/ContratoBO" xmlns:ns23="http://www.bcie.org/ClienteBO"
                xmlns:ns24="http://www.bcie.org/AtributoBO"
                xmlns:ns26="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns25="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:ns27="http://www.bcie.org/ErrorBO" xmlns:ns30="http://www.bcie.org/CrearBitacoraProcesoBO"
                xmlns:ns31="http://www.bcie.org/ImplementacionPctBO" xmlns:ns32="http://www.bcie.org/AdquisicionBO">
    <oracle-xsl-mapper:schema>
        <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
        <oracle-xsl-mapper:mapSources>
            <oracle-xsl-mapper:source type="WSDL">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/WSDL/BPEL/ConfiguracionCondicionesSOR.wsdl"/>
                <oracle-xsl-mapper:rootElement name="configuracionCondicionesResponse"
                                               namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
            </oracle-xsl-mapper:source>
            <oracle-xsl-mapper:source type="WSDL">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Wsdl/ConsultarRolesPorProceso.wsdl"/>
                <oracle-xsl-mapper:rootElement name="responseConsultarRolesPorProcesoMessage"
                                               namespace="http://www.bcie.org/ConsultarRolesPorProcesoMO"/>
                <oracle-xsl-mapper:param name="OutRol.response"/>
            </oracle-xsl-mapper:source>
        </oracle-xsl-mapper:mapSources>
        <oracle-xsl-mapper:mapTargets>
            <oracle-xsl-mapper:target type="WSDL">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/WSDL/BPEL/ConfiguracionCondicionesSOR.wsdl"/>
                <oracle-xsl-mapper:rootElement name="configuracionCondicionesResponse"
                                               namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
            </oracle-xsl-mapper:target>
        </oracle-xsl-mapper:mapTargets>
        <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [THU NOV 03 17:41:31 CST 2016].-->
    </oracle-xsl-mapper:schema>
    <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
    <xsl:param name="OutRol.response"/>
    <xsl:template match="/">
        <ns0:configuracionCondicionesResponse>
            <ns0:configuracionCondiciones>
                <ns3:operacion/>
                <ns3:producto/>
                <ns3:RolesEquipoTrabajo>
                    <xsl:for-each select="$OutRol.response/ns1:responseConsultarRolesPorProcesoMessage/ns1:ListadoRoles/ns29:listadoRoles">
                        <ns3:Rol>
                            <xsl:if test="ns20:Id">
                                <ns20:Id>
                                    <xsl:value-of select="ns20:Id"/>
                                </ns20:Id>
                            </xsl:if>
                            <xsl:if test="ns20:Descripcion">
                                <ns20:Descripcion>
                                    <xsl:value-of select="ns20:Descripcion"/>
                                </ns20:Descripcion>
                            </xsl:if>
                            <xsl:if test="ns20:DescripcionCorta">
                                <ns20:DescripcionCorta>
                                    <xsl:value-of select="ns20:DescripcionCorta"/>
                                </ns20:DescripcionCorta>
                            </xsl:if>
                            <xsl:if test="ns20:estatus">
                                <ns20:estatus>
                                    <xsl:value-of select="ns20:estatus"/>
                                </ns20:estatus>
                            </xsl:if>
                            <xsl:if test="ns20:codigoExterno">
                                <ns20:codigoExterno>
                                    <xsl:value-of select="ns20:codigoExterno"/>
                                </ns20:codigoExterno>
                            </xsl:if>
                        </ns3:Rol>
                    </xsl:for-each>
                </ns3:RolesEquipoTrabajo>
                <ns3:requiereValidarCondiciones>
                    <xsl:value-of select="/ns0:configuracionCondicionesResponse/ns0:configuracionCondiciones/ns3:requiereValidarCondiciones"/>
                </ns3:requiereValidarCondiciones>
                <xsl:for-each select="/ns0:configuracionCondicionesResponse/ns0:configuracionCondiciones/ns8:ParameterType">
                    <ns8:ParameterType>
                        <ns8:parameterName>
                            <xsl:value-of select="ns8:parameterName"/>
                        </ns8:parameterName>
                        <ns8:parameterValue>
                            <xsl:value-of select="ns8:parameterValue"/>
                        </ns8:parameterValue>
                    </ns8:ParameterType>
                </xsl:for-each>
            </ns0:configuracionCondiciones>
        </ns0:configuracionCondicionesResponse>
    </xsl:template>
</xsl:stylesheet>
