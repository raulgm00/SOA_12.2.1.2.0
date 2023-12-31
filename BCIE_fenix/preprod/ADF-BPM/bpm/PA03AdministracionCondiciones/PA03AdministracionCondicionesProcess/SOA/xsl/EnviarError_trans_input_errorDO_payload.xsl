<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/CondicionesError/Payload/V1"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Condiciones/Payload/V1"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:notificacion="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:ns1="http://www.bcie.org/CatalogoBO"
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA03/CondicionPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="CondicionPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Condiciones/Payload/V1"/>
        <oracle-xsl-mapper:param name="errorDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA03/CondicionesErrorPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="CondicionesErrorPayloadType"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/CondicionesError/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [TUE SEP 13 12:56:55 CDT 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:CondicionesErrorPayloadType>
      <tns:Header>
        <header:Operacion>
          <ns2:CodigoOperacion>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:CodigoOperacion"/>
          </ns2:CodigoOperacion>
          <ns2:NombreOperacion>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:NombreOperacion"/>
          </ns2:NombreOperacion>
          <ns2:ResponsableOperacion>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:ResponsableOperacion"/>
          </ns2:ResponsableOperacion>
          <ns2:CodigoCliente>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:CodigoCliente"/>
          </ns2:CodigoCliente>
          <ns2:NombreCliente>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:NombreCliente"/>
          </ns2:NombreCliente>
          <ns2:CodigoProducto>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:CodigoProducto"/>
          </ns2:CodigoProducto>
          <ns2:NombreProducto>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:NombreProducto"/>
          </ns2:NombreProducto>
          <ns2:MontoSolicitado>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:MontoSolicitado"/>
          </ns2:MontoSolicitado>
          <ns2:Pais>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:Pais"/>
          </ns2:Pais>
        </header:Operacion>
        <header:Tarea>
          <ns5:CodigoTarea>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Tarea/ns5:CodigoTarea"/>
          </ns5:CodigoTarea>
          <ns5:NombreTarea>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Tarea/ns5:NombreTarea"/>
          </ns5:NombreTarea>
          <ns5:CodigoRol>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Tarea/ns5:CodigoRol"/>
          </ns5:CodigoRol>
          <ns5:NombreRol>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Tarea/ns5:NombreRol"/>
          </ns5:NombreRol>
          <ns5:CodigoProceso>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Tarea/ns5:CodigoProceso"/>
          </ns5:CodigoProceso>
          <ns5:NombreProceso>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Tarea/ns5:NombreProceso"/>
          </ns5:NombreProceso>
        </header:Tarea>
        <header:Proceso>
          <ns3:IdProceso>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Proceso/ns3:IdProceso"/>
          </ns3:IdProceso>
          <ns3:CodigoProceso>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Proceso/ns3:CodigoProceso"/>
          </ns3:CodigoProceso>
          <ns3:NombreProceso>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Proceso/ns3:NombreProceso"/>
          </ns3:NombreProceso>
          <ns3:EsProcesoCore>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Proceso/ns3:EsProcesoCore"/>
          </ns3:EsProcesoCore>
          <ns3:RolIniciaProceso>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Proceso/ns3:RolIniciaProceso"/>
          </ns3:RolIniciaProceso>
          <ns3:UsuarioIniciaProceso>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Proceso/ns3:UsuarioIniciaProceso"/>
          </ns3:UsuarioIniciaProceso>
          <ns3:InstanciaProceso>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Proceso/ns3:InstanciaProceso"/>
          </ns3:InstanciaProceso>
          <ns3:IdFlujo>
            <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Proceso/ns3:IdFlujo"/>
          </ns3:IdFlujo>
        </header:Proceso>
        <xsl:for-each select="/ns0:CondicionPayload/ns0:Header/parameter:ParameterType">
          <parameter:ParameterType>
            <parameter:parameterName>
              <xsl:value-of select="parameter:parameterName"/>
            </parameter:parameterName>
            <parameter:parameterValue>
              <xsl:value-of select="parameter:parameterValue"/>
            </parameter:parameterValue>
          </parameter:ParameterType>
        </xsl:for-each>
      </tns:Header>
      <tns:Configuration>
        <tns:requiereASJUR>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:Configuration/ns0:requiereASJUR"/>
        </tns:requiereASJUR>
        <tns:requiereSEPRI>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:Configuration/ns0:requiereSEPRI"/>
        </tns:requiereSEPRI>
        <tns:requiereAED>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:Configuration/ns0:requiereAED"/>
        </tns:requiereAED>
        <tns:requierePCT>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:Configuration/ns0:requierePCT"/>
        </tns:requierePCT>
        <tns:requiereSupervision>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:Configuration/ns0:requiereSupervision"/>
        </tns:requiereSupervision>
        <tns:requiereCOFI>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:Configuration/ns0:requiereCOFI"/>
        </tns:requiereCOFI>
        <tns:requiereValidar>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:Configuration/ns0:requiereValidar"/>
        </tns:requiereValidar>
      </tns:Configuration>
      <tns:EquipoPayload>
        <tns:Abogado>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoPayload/ns0:Abogado"/>
        </tns:Abogado>
        <tns:EjecutivoSEPRI>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoPayload/ns0:EjecutivoSEPRI"/>
        </tns:EjecutivoSEPRI>
        <tns:AnalistaSupervision>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoPayload/ns0:AnalistaSupervision"/>
        </tns:AnalistaSupervision>
        <tns:AnalistaAED>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoPayload/ns0:AnalistaAED"/>
        </tns:AnalistaAED>
        <tns:EjecutivoPCT>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoPayload/ns0:EjecutivoPCT"/>
        </tns:EjecutivoPCT>
        <tns:AnalistaCOFI>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoPayload/ns0:AnalistaCOFI"/>
        </tns:AnalistaCOFI>
      </tns:EquipoPayload>
      <tns:EquipoEjecucion>
        <tns:Abogado>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoEjecucionPayload/ns0:Abogado"/>
        </tns:Abogado>
        <tns:EjecutivoSEPRI>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoEjecucionPayload/ns0:EjecutivoSEPRI"/>
        </tns:EjecutivoSEPRI>
        <tns:AnalistaSupervision>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoEjecucionPayload/ns0:AnalistaSupervision"/>
        </tns:AnalistaSupervision>
        <tns:AnalistaAED>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoEjecucionPayload/ns0:AnalistaAED"/>
        </tns:AnalistaAED>
        <tns:EjecutivoPCT>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoEjecucionPayload/ns0:EjecutivoPCT"/>
        </tns:EjecutivoPCT>
        <tns:AnalistaCOFI>
          <xsl:value-of select="/ns0:CondicionPayload/ns0:EquipoEjecucionPayload/ns0:AnalistaCOFI"/>
        </tns:AnalistaCOFI>
      </tns:EquipoEjecucion>
      <xsl:for-each select="/ns0:CondicionPayload/parameter:ParameterType">
        <parameter:ParameterType>
          <parameter:parameterName>
            <xsl:value-of select="parameter:parameterName"/>
          </parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="parameter:parameterValue"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </xsl:for-each>
    </tns:CondicionesErrorPayloadType>
  </xsl:template>
</xsl:stylesheet>
