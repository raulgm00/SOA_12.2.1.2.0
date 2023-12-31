<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Analisis/Payload/V1"
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
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns3="http://www.bcie.org/CatalogoBO"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC03/AnalisisPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="AnalisisPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Analisis/Payload/V1"/>
        <oracle-xsl-mapper:param name="analisisDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC03/AnalisisPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="AnalisisPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Analisis/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [THU SEP 24 11:06:19 CDT 2015].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <ns0:AnalisisPayload>
      <ns0:Header>
        <header:Operacion>
          <ns1:CodigoOperacion>
            <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:CodigoOperacion"/>
          </ns1:CodigoOperacion>
          <ns1:NombreOperacion>
            <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:NombreOperacion"/>
          </ns1:NombreOperacion>
          <xsl:if test="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion/text()">
            <ns1:ResponsableOperacion>
              <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion"/>
            </ns1:ResponsableOperacion>
          </xsl:if>
          <ns1:CodigoCliente>
            <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:CodigoCliente"/>
          </ns1:CodigoCliente>
          <ns1:NombreCliente>
            <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:NombreCliente"/>
          </ns1:NombreCliente>
          <xsl:if test="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:CodigoProducto/text()">
            <ns1:CodigoProducto>
              <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:CodigoProducto"/>
            </ns1:CodigoProducto>
          </xsl:if>
          <ns1:NombreProducto>
            <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:NombreProducto"/>
          </ns1:NombreProducto>
          <xsl:if test="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:MontoSolicitado/text()">
            <ns1:MontoSolicitado>
              <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:MontoSolicitado"/>
            </ns1:MontoSolicitado>
          </xsl:if>
          <ns1:Pais>
            <xsl:value-of select="/ns0:AnalisisPayload/ns0:Header/header:Operacion/ns1:Pais"/>
          </ns1:Pais>
        </header:Operacion>
        <header:Tarea>
          <ns2:CodigoTarea>25</ns2:CodigoTarea>
          <ns2:NombreTarea>Modificar Opinión Jurídica</ns2:NombreTarea>
          <ns2:CodigoRol>1</ns2:CodigoRol>
          <ns2:NombreRol>ASJUR</ns2:NombreRol>
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
      <xsl:for-each select="/ns0:AnalisisPayload/parameter:ParameterType">
        <parameter:ParameterType>
          <parameter:parameterName>
            <xsl:value-of select="parameter:parameterName"/>
          </parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="parameter:parameterValue"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </xsl:for-each>
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
