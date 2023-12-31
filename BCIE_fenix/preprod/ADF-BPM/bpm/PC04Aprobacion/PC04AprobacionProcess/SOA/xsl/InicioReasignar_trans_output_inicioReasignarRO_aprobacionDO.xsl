<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:ns1="http://xmlns.bcie.org/ObjetoProceso/Aprobacion/Payload/V1"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Reasignacion/Payload/V1"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns1 ns0 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1" xmlns:ns2="http://www.example.org"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:notificacion="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:ns3="http://www.bcie.org/CatalogoBO"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns7="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
    <oracle-xsl-mapper:schema>
        <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
        <oracle-xsl-mapper:mapSources>
            <oracle-xsl-mapper:source type="XSD">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PU01/ReasignacionPayload.xsd"/>
                <oracle-xsl-mapper:rootElement name="ReasignacionPayload"
                                               namespace="http://xmlns.bcie.org/ObjetoProceso/Reasignacion/Payload/V1"/>
                <oracle-xsl-mapper:param name="payload"/>
            </oracle-xsl-mapper:source>
            <oracle-xsl-mapper:source type="XSD">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC04/AprobacionPayload.xsd"/>
                <oracle-xsl-mapper:rootElement name="AprobacionPayload"
                                               namespace="http://xmlns.bcie.org/ObjetoProceso/Aprobacion/Payload/V1"/>
                <oracle-xsl-mapper:param name="aprobacionDO"/>
            </oracle-xsl-mapper:source>
        </oracle-xsl-mapper:mapSources>
        <oracle-xsl-mapper:mapTargets>
            <oracle-xsl-mapper:target type="XSD">
                <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC04/AprobacionPayload.xsd"/>
                <oracle-xsl-mapper:rootElement name="AprobacionPayload"
                                               namespace="http://xmlns.bcie.org/ObjetoProceso/Aprobacion/Payload/V1"/>
            </oracle-xsl-mapper:target>
        </oracle-xsl-mapper:mapTargets>
        <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [FRI NOV 10 13:33:34 CST 2017].-->
    </oracle-xsl-mapper:schema>
    <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
    <xsl:param name="aprobacionDO"/>
    <xsl:template match="/">
        <ns1:AprobacionPayload>
            <ns1:Header>
                <header:Operacion>
                    <ns4:CodigoOperacion>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:CodigoOperacion"/>
                    </ns4:CodigoOperacion>
                    <ns4:NombreOperacion>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:NombreOperacion"/>
                    </ns4:NombreOperacion>
                    <ns4:ResponsableOperacion>
                        <xsl:value-of select="/ns0:ReasignacionPayload/ns0:Header/header:Operacion/ns4:ResponsableOperacion"/>
                    </ns4:ResponsableOperacion>
                    <ns4:CodigoCliente>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:CodigoCliente"/>
                    </ns4:CodigoCliente>
                    <ns4:NombreCliente>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:NombreCliente"/>
                    </ns4:NombreCliente>
                    <ns4:CodigoProducto>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:CodigoProducto"/>
                    </ns4:CodigoProducto>
                    <ns4:NombreProducto>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:NombreProducto"/>
                    </ns4:NombreProducto>
                    <ns4:MontoSolicitado>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:MontoSolicitado"/>
                    </ns4:MontoSolicitado>
                    <ns4:Pais>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:Pais"/>
                    </ns4:Pais>
                    <ns4:idSectorInstitucional>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:idSectorInstitucional"/>
                    </ns4:idSectorInstitucional>
                    <ns4:idEncargado>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:idEncargado"/>
                    </ns4:idEncargado>
                    <ns4:idRol>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:idRol"/>
                    </ns4:idRol>
                    <ns4:descripcionRol>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Operacion/ns4:descripcionRol"/>
                    </ns4:descripcionRol>
                </header:Operacion>
                <header:Cliente>
                    <ns6:IdCliente>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:IdCliente"/>
                    </ns6:IdCliente>
                    <ns6:IdFlexCube>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:IdFlexCube"/>
                    </ns6:IdFlexCube>
                    <ns6:RazonSocial>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:RazonSocial"/>
                    </ns6:RazonSocial>
                    <ns6:Abreviatura>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:Abreviatura"/>
                    </ns6:Abreviatura>
                    <ns6:IdSector>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:IdSector"/>
                    </ns6:IdSector>
                    <ns6:Sector>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:Sector"/>
                    </ns6:Sector>
                    <ns6:IdPais>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:IdPais"/>
                    </ns6:IdPais>
                    <ns6:Pais>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:Pais"/>
                    </ns6:Pais>
                    <ns6:IdOficina>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:IdOficina"/>
                    </ns6:IdOficina>
                    <ns6:Oficina>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:Oficina"/>
                    </ns6:Oficina>
                    <ns6:ResponsableCliente>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:ResponsableCliente"/>
                    </ns6:ResponsableCliente>
                    <ns6:bicCode>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:bicCode"/>
                    </ns6:bicCode>
                    <ns6:direccion>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Cliente/ns6:direccion"/>
                    </ns6:direccion>
                </header:Cliente>
                <header:Tarea>
                    <ns7:CodigoTarea>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Tarea/ns7:CodigoTarea"/>
                    </ns7:CodigoTarea>
                    <ns7:NombreTarea>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Tarea/ns7:NombreTarea"/>
                    </ns7:NombreTarea>
                    <ns7:CodigoRol>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Tarea/ns7:CodigoRol"/>
                    </ns7:CodigoRol>
                    <ns7:NombreRol>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Tarea/ns7:NombreRol"/>
                    </ns7:NombreRol>
                    <ns7:CodigoProceso>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Tarea/ns7:CodigoProceso"/>
                    </ns7:CodigoProceso>
                    <ns7:NombreProceso>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Tarea/ns7:NombreProceso"/>
                    </ns7:NombreProceso>
                </header:Tarea>
                <header:Proceso>
                    <ns5:IdProceso>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Proceso/ns5:IdProceso"/>
                    </ns5:IdProceso>
                    <ns5:CodigoProceso>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Proceso/ns5:CodigoProceso"/>
                    </ns5:CodigoProceso>
                    <ns5:NombreProceso>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Proceso/ns5:NombreProceso"/>
                    </ns5:NombreProceso>
                    <ns5:EsProcesoCore>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Proceso/ns5:EsProcesoCore"/>
                    </ns5:EsProcesoCore>
                    <ns5:RolIniciaProceso>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Proceso/ns5:RolIniciaProceso"/>
                    </ns5:RolIniciaProceso>
                    <ns5:UsuarioIniciaProceso>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Proceso/ns5:UsuarioIniciaProceso"/>
                    </ns5:UsuarioIniciaProceso>
                    <ns5:InstanciaProceso>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Proceso/ns5:InstanciaProceso"/>
                    </ns5:InstanciaProceso>
                    <ns5:IdFlujo>
                        <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/header:Proceso/ns5:IdFlujo"/>
                    </ns5:IdFlujo>
                </header:Proceso>
                <xsl:for-each select="$aprobacionDO/ns1:AprobacionPayload/ns1:Header/parameter:ParameterType">
                    <parameter:ParameterType>
                        <parameter:parameterName>
                            <xsl:value-of select="parameter:parameterName"/>
                        </parameter:parameterName>
                        <parameter:parameterValue>
                            <xsl:value-of select="parameter:parameterValue"/>
                        </parameter:parameterValue>
                    </parameter:ParameterType>
                </xsl:for-each>
            </ns1:Header>
            <ns1:Configuration>
                <ns1:requiereAED>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:requiereAED"/>
                </ns1:requiereAED>
                <ns1:requiereASJUR>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:requiereASJUR"/>
                </ns1:requiereASJUR>
                <ns1:continuaProceso>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:continuaProceso"/>
                </ns1:continuaProceso>
                <ns1:enmiendaAprobada>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:enmiendaAprobada"/>
                </ns1:enmiendaAprobada>
                <ns1:retorno>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:retorno"/>
                </ns1:retorno>
                <ns1:requiereFormalizacion>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:requiereFormalizacion"/>
                </ns1:requiereFormalizacion>
                <ns1:reunionPresencial>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:reunionPresencial"/>
                </ns1:reunionPresencial>
                <ns1:idNivelAprobacion>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:idNivelAprobacion"/>
                </ns1:idNivelAprobacion>
                <ns1:accion>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:accion"/>
                </ns1:accion>
                <ns1:idAprobador>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:Configuration/ns1:idAprobador"/>
                </ns1:idAprobador>
            </ns1:Configuration>
            <ns1:EquipoPayload>
                <ns1:SecretarioCC>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoPayload/ns1:SecretarioCC"/>
                </ns1:SecretarioCC>
                <ns1:Abogado>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoPayload/ns1:Abogado"/>
                </ns1:Abogado>
                <ns1:SecretarioGeneral>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoPayload/ns1:SecretarioGeneral"/>
                </ns1:SecretarioGeneral>
                <ns1:AsistentePresidencia>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoPayload/ns1:AsistentePresidencia"/>
                </ns1:AsistentePresidencia>
                <ns1:AnalistaCredito>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoPayload/ns1:AnalistaCredito"/>
                </ns1:AnalistaCredito>
                <ns1:AnalistaAED>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoPayload/ns1:AnalistaAED"/>
                </ns1:AnalistaAED>
            </ns1:EquipoPayload>
            <ns1:EquipoEjecucionPayload>
                <ns1:SecretarioCC>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoEjecucionPayload/ns1:SecretarioCC"/>
                </ns1:SecretarioCC>
                <ns1:Abogado>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoEjecucionPayload/ns1:Abogado"/>
                </ns1:Abogado>
                <ns1:SecretarioGeneral>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoEjecucionPayload/ns1:SecretarioGeneral"/>
                </ns1:SecretarioGeneral>
                <ns1:AsistentePresidencia>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoEjecucionPayload/ns1:AsistentePresidencia"/>
                </ns1:AsistentePresidencia>
                <ns1:AnalistaCredito>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoEjecucionPayload/ns1:AnalistaCredito"/>
                </ns1:AnalistaCredito>
                <ns1:AnalistaAED>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoEjecucionPayload/ns1:AnalistaAED"/>
                </ns1:AnalistaAED>
            </ns1:EquipoEjecucionPayload>
            <ns1:EquipoSolicitudPayload>
                <ns1:ResponsableOperacion>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoSolicitudPayload/ns1:ResponsableOperacion"/>
                </ns1:ResponsableOperacion>
                <ns1:AnalistaCredito>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoSolicitudPayload/ns1:AnalistaCredito"/>
                </ns1:AnalistaCredito>
                <ns1:AnalistaSeguimiento>
                    <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:EquipoSolicitudPayload/ns1:AnalistaSeguimiento"/>
                </ns1:AnalistaSeguimiento>
            </ns1:EquipoSolicitudPayload>
            <xsl:for-each select="$aprobacionDO/ns1:AprobacionPayload/parameter:ParameterType">
                <parameter:ParameterType>
                    <parameter:parameterName>
                        <xsl:value-of select="parameter:parameterName"/>
                    </parameter:parameterName>
                    <parameter:parameterValue>
                        <xsl:value-of select="parameter:parameterValue"/>
                    </parameter:parameterValue>
                </parameter:ParameterType>
            </xsl:for-each>
            <ns1:operacion>
                <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:operacion"/>
            </ns1:operacion>
            <ns1:IdTipoAprobacion>
                <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:IdTipoAprobacion"/>
            </ns1:IdTipoAprobacion>
            <ns1:IdRequerimiento>
                <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:IdRequerimiento"/>
            </ns1:IdRequerimiento>
            <ns1:IdSolicitud>
                <xsl:value-of select="$aprobacionDO/ns1:AprobacionPayload/ns1:IdSolicitud"/>
            </ns1:IdSolicitud>
        </ns1:AprobacionPayload>
    </xsl:template>
</xsl:stylesheet>
