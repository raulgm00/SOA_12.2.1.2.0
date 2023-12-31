<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/Cancelar/Payload/V1"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns0="http://xmlns.oracle.com/bpmn/bpmnProcess/CancelarOperacionProcess"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:tns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:eqTr="http://www.bcie.org/EqipoTrabajo/V1"
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns1="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:notificacion="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:ns4="http://www.bcie.org/CatalogoBO"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA05/CancelarOperacionProcess.xsd"/>
        <oracle-xsl-mapper:rootElement name="InicioCancelarOperacion"
                                       namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/CancelarOperacionProcess"/>
        <oracle-xsl-mapper:param name="inicioCancelarOperacion"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA05/CancelarOperacion.xsd"/>
        <oracle-xsl-mapper:rootElement name="CancelarPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Cancelar/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [WED FEB 03 19:13:59 CST 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:CancelarPayload>
      <tns:Header>
        <tns6:Operacion>
          <ns1:CodigoOperacion>
            <xsl:value-of select="/ns0:InicioCancelarOperacion/ns0:Header/tns6:Operacion/ns1:CodigoOperacion"/>
          </ns1:CodigoOperacion>
          <ns1:NombreOperacion>
            <xsl:value-of select="/ns0:InicioCancelarOperacion/ns0:Header/tns6:Operacion/ns1:NombreOperacion"/>
          </ns1:NombreOperacion>
          <ns1:ResponsableOperacion>
            <xsl:value-of select="/ns0:InicioCancelarOperacion/ns0:Header/tns6:Operacion/ns1:ResponsableOperacion"/>
          </ns1:ResponsableOperacion>
          <ns1:CodigoCliente>
            <xsl:value-of select="/ns0:InicioCancelarOperacion/ns0:Header/tns6:Operacion/ns1:CodigoCliente"/>
          </ns1:CodigoCliente>
          <ns1:NombreCliente>
            <xsl:value-of select="/ns0:InicioCancelarOperacion/ns0:Header/tns6:Operacion/ns1:NombreCliente"/>
          </ns1:NombreCliente>
          <ns1:CodigoProducto>
            <xsl:value-of select="/ns0:InicioCancelarOperacion/ns0:Header/tns6:Operacion/ns1:CodigoProducto"/>
          </ns1:CodigoProducto>
          <ns1:NombreProducto>
            <xsl:value-of select="/ns0:InicioCancelarOperacion/ns0:Header/tns6:Operacion/ns1:NombreProducto"/>
          </ns1:NombreProducto>
          <ns1:MontoSolicitado>
            <xsl:value-of select="/ns0:InicioCancelarOperacion/ns0:Header/tns6:Operacion/ns1:MontoSolicitado"/>
          </ns1:MontoSolicitado>
          <ns1:Pais>
            <xsl:value-of select="/ns0:InicioCancelarOperacion/ns0:Header/tns6:Operacion/ns1:Pais"/>
          </ns1:Pais>
        </tns6:Operacion>
        <tns6:Tarea>
          <ns3:CodigoTarea/>
          <ns3:NombreTarea/>
          <ns3:CodigoRol/>
          <ns3:NombreRol/>
          <ns3:CodigoProceso>11</ns3:CodigoProceso>
          <ns3:NombreProceso/>
        </tns6:Tarea>
        <tns6:Proceso>
          <ns5:IdProceso/>
          <ns5:CodigoProceso/>
          <ns5:NombreProceso/>
          <ns5:EsProcesoCore/>
          <ns5:RolIniciaProceso/>
          <ns5:UsuarioIniciaProceso/>
          <ns5:InstanciaProceso/>
          <ns5:IdFlujo/>
        </tns6:Proceso>
        <xsl:for-each select="/ns0:InicioCancelarOperacion/ns0:Header/ns2:ParameterType">
          <ns2:ParameterType>
            <ns2:parameterName>
              <xsl:value-of select="ns2:parameterName"/>
            </ns2:parameterName>
            <ns2:parameterValue>
              <xsl:value-of select="ns2:parameterValue"/>
            </ns2:parameterValue>
          </ns2:ParameterType>
        </xsl:for-each>
      </tns:Header>
      <tns:Configuration>
        <tns:solicitaGerente/>
      </tns:Configuration>
      <xsl:for-each select="/ns0:InicioCancelarOperacion/ns0:Header/ns2:ParameterType">
        <xsl:if test='ns2:parameterName = "TIPO_SOLICITUD"'>
          <xsl:choose>
            <xsl:when test="ns2:parameterValue = 0">
              <tns:solicitaCancelar>true</tns:solicitaCancelar>
            </xsl:when>
            <xsl:otherwise>
              <tns:solicitaCancelar>false</tns:solicitaCancelar>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:if>
      </xsl:for-each>
      <xsl:for-each select="/ns0:InicioCancelarOperacion/ns0:Header/ns2:ParameterType">
        <xsl:choose>
          <xsl:when test="ns2:parameterName = &quot;TIPO_INICIO&quot;">
            <xsl:choose>
              <xsl:when test="ns2:parameterValue = &quot;1&quot;">
                <tns:razon>Suspensión</tns:razon>
              </xsl:when>
            </xsl:choose>
          </xsl:when>
        </xsl:choose>
      </xsl:for-each>
      <tns:observacion/>
      <tns:EquipoPayload>
        <tns:GerentePais/>
      </tns:EquipoPayload>
    </tns:CancelarPayload>
  </xsl:template>
</xsl:stylesheet>
