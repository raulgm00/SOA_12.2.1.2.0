<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Evaluacion/Payload/V1"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 oraxsl xp20 xref mhdr oraext dvm socket"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:notificacion="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:ns1="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../Schemas/Evaluacion/V1/EvaluacionPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="EvaluacionPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Evaluacion/Payload/V1"/>
        <oracle-xsl-mapper:param name="EvaluacionDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../Schemas/Evaluacion/V1/EvaluacionPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="EvaluacionPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Evaluacion/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [WED SEP 23 11:52:27 CDT 2015].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <ns0:EvaluacionPayload>
      <ns0:Header>
        <header:Operacion>
          <ns1:CodigoOperacion>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:CodigoOperacion"/>
          </ns1:CodigoOperacion>
          <ns1:NombreOperacion>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:NombreOperacion"/>
          </ns1:NombreOperacion>
          <xsl:if test="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion/text()">
            <ns1:ResponsableOperacion>
              <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion"/>
            </ns1:ResponsableOperacion>
          </xsl:if>
          <ns1:CodigoCliente>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:CodigoCliente"/>
          </ns1:CodigoCliente>
          <ns1:NombreCliente>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:NombreCliente"/>
          </ns1:NombreCliente>
          <xsl:if test="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:CodigoProducto/text()">
            <ns1:CodigoProducto>
              <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:CodigoProducto"/>
            </ns1:CodigoProducto>
          </xsl:if>
          <ns1:NombreProducto>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:NombreProducto"/>
          </ns1:NombreProducto>
          <xsl:if test="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:MontoSolicitado/text()">
            <ns1:MontoSolicitado>
              <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:MontoSolicitado"/>
            </ns1:MontoSolicitado>
          </xsl:if>
          <ns1:Pais>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns1:Pais"/>
          </ns1:Pais>
        </header:Operacion>
        <xsl:for-each select="/ns0:EvaluacionPayload/ns0:Header/parameter:ParameterType">
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
        <ns0:requiereSIEMAS>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:requiereSIEMAS"/>
        </ns0:requiereSIEMAS>
        <ns0:requiereIBCIE>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:requiereIBCIE"/>
        </ns0:requiereIBCIE>
        <ns0:resultado>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:resultado"/>
        </ns0:resultado>
      </ns0:Configuration>
      <ns0:EquipoPayload>
        <ns0:AnalistaAED>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoPayload/ns0:AnalistaAED"/>
        </ns0:AnalistaAED>
        <ns0:Control>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoPayload/ns0:Control"/>
        </ns0:Control>
      </ns0:EquipoPayload>
      <ns0:EquipoEjecucionPayload>
        <ns0:AnalistaAED>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoEjecucionPayload/ns0:AnalistaAED"/>
        </ns0:AnalistaAED>
        <ns0:Control>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoEjecucionPayload/ns0:Control"/>
        </ns0:Control>
      </ns0:EquipoEjecucionPayload>
      <xsl:for-each select="/ns0:EvaluacionPayload/parameter:ParameterType">
        <parameter:ParameterType>
          <parameter:parameterName>
            <xsl:value-of select="parameter:parameterName"/>
          </parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="parameter:parameterValue"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </xsl:for-each>
    </ns0:EvaluacionPayload>
  </xsl:template>
</xsl:stylesheet>
