<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/ComisionesError/Payload/V1"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:ns1="http://xmlns.bcie.org/ObjetoProceso/Comisiones/Payload/V1"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 ns1 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:notificacion="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:ns2="http://www.bcie.org/CatalogoBO"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA02/ComisionesErrorPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="ComisionesErrorPayloadType"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/ComisionesError/Payload/V1"/>
        <oracle-xsl-mapper:param name="errorDO"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA02/ComisionPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="ComisionPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Comisiones/Payload/V1"/>
        <oracle-xsl-mapper:param name="comisionDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA02/ComisionesErrorPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="ComisionesErrorPayloadType"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/ComisionesError/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [FRI MAY 19 14:09:40 CDT 2017].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="comisionDO"/>
  <xsl:template match="/">
    <ns0:ComisionesErrorPayloadType>
      <ns0:Header>
        <header:Operacion>
          <ns3:CodigoOperacion>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Operacion/ns3:CodigoOperacion"/>
          </ns3:CodigoOperacion>
          <ns3:NombreOperacion>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Operacion/ns3:NombreOperacion"/>
          </ns3:NombreOperacion>
          <ns3:ResponsableOperacion>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Operacion/ns3:ResponsableOperacion"/>
          </ns3:ResponsableOperacion>
          <ns3:CodigoCliente>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Operacion/ns3:CodigoCliente"/>
          </ns3:CodigoCliente>
          <ns3:NombreCliente>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Operacion/ns3:NombreCliente"/>
          </ns3:NombreCliente>
          <ns3:CodigoProducto>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Operacion/ns3:CodigoProducto"/>
          </ns3:CodigoProducto>
          <ns3:NombreProducto>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Operacion/ns3:NombreProducto"/>
          </ns3:NombreProducto>
          <ns3:MontoSolicitado>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Operacion/ns3:MontoSolicitado"/>
          </ns3:MontoSolicitado>
          <ns3:Pais>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Operacion/ns3:Pais"/>
          </ns3:Pais>
        </header:Operacion>
        <header:Tarea>
          <ns6:CodigoTarea>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Tarea/ns6:CodigoTarea"/>
          </ns6:CodigoTarea>
          <ns6:NombreTarea>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Tarea/ns6:NombreTarea"/>
          </ns6:NombreTarea>
          <ns6:CodigoRol>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Tarea/ns6:CodigoRol"/>
          </ns6:CodigoRol>
          <ns6:NombreRol>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Tarea/ns6:NombreRol"/>
          </ns6:NombreRol>
          <ns6:CodigoProceso>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Tarea/ns6:CodigoProceso"/>
          </ns6:CodigoProceso>
          <ns6:NombreProceso>
            <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Header/header:Tarea/ns6:NombreProceso"/>
          </ns6:NombreProceso>
        </header:Tarea>
        <xsl:for-each select="$comisionDO/ns1:ComisionPayload/ns1:Header/parameter:ParameterType">
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
        <ns0:idComision>
          <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:idComision"/>
        </ns0:idComision>
        <ns0:esDispensa>
          <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Configuration/ns1:esDispensa"/>
        </ns0:esDispensa>
      </ns0:Configuration>
      <ns0:EquipoPayload>
        <ns0:AnalistaCOFI>
          <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:Equipo/ns1:AnalistaCOFI"/>
        </ns0:AnalistaCOFI>
      </ns0:EquipoPayload>
      <ns0:EquipoEjecucion>
        <ns0:AnalistaCOFI>
          <xsl:value-of select="$comisionDO/ns1:ComisionPayload/ns1:EquipoEjecucion/ns1:AnalistaCOFI"/>
        </ns0:AnalistaCOFI>
      </ns0:EquipoEjecucion>
      <xsl:for-each select="$comisionDO/ns1:ComisionPayload/parameter:ParameterType">
        <parameter:ParameterType>
          <parameter:parameterName>
            <xsl:value-of select="parameter:parameterName"/>
          </parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="parameter:parameterValue"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </xsl:for-each>
      <ns0:ErrorMsg>
        <xsl:value-of select="/ns0:ComisionesErrorPayloadType/ns0:ErrorMsg"/>
      </ns0:ErrorMsg>
      <ns0:ErrorNamespace>
        <xsl:value-of select="/ns0:ComisionesErrorPayloadType/ns0:ErrorNamespace"/>
      </ns0:ErrorNamespace>
      <ns0:ErrorFault>
        <xsl:value-of select="/ns0:ComisionesErrorPayloadType/ns0:ErrorFault"/>
      </ns0:ErrorFault>
      <ns0:Accion>
        <xsl:value-of select="/ns0:ComisionesErrorPayloadType/ns0:Accion"/>
      </ns0:Accion>
    </ns0:ComisionesErrorPayloadType>
  </xsl:template>
</xsl:stylesheet>
