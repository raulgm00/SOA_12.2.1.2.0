<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:ns0="http://www.bcie.org/ConfiguracionProcesosMO"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:ns1="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 ns1 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:confBO="http://www.bcie.org/ConfiguracionProcesosBO" xmlns:ns2="http://www.bcie.org/TerminoBO"
                xmlns:ns3="http://www.bcie.org/ReglaBO" xmlns:operBO="http://www.bcie.org/OperacionBO"
                xmlns:resBO="http://www.bcie.org/ResultBO" xmlns:cmnBO="http://www.bcie.org/CommonBO"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns7="http://www.bcie.org/MatrizTCCBO" xmlns:ns5="http://www.bcie.org/CrearBitacoraProcesoBO"
                xmlns:ns6="http://www.bcie.org/LineaCreditoBO"
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
                xmlns:ns23="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns22="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:ns24="http://www.bcie.org/ErrorBO">
  <oracle-xsl-mapper:schema>
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd"/>
        <oracle-xsl-mapper:rootElement name="ConfiguracionFormalizacionEnmiendasResponse"
                                       namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
        <oracle-xsl-mapper:param name="configuracionFormalizacionEnmiendasResponse"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA15/FormalizacionEnmiendasPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="FormalizacionEnmiendasPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1"/>
        <oracle-xsl-mapper:param name="formalizacionEnmiendasDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA15/FormalizacionEnmiendasPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="FormalizacionEnmiendasPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
  </oracle-xsl-mapper:schema>
  <xsl:param name="formalizacionEnmiendasDO"/>
  <xsl:template match="/">
    <ns1:FormalizacionEnmiendasPayload>
      <ns1:Header>
        <ns13:Operacion>
          <ns8:CodigoOperacion>
            <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:operacion/operBO:idOperacion"/>
          </ns8:CodigoOperacion>
          <ns8:NombreOperacion>
            <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:operacion/operBO:nombre"/>
          </ns8:NombreOperacion>
          <ns8:ResponsableOperacion>
            <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:operacion/operBO:responsable"/>
          </ns8:ResponsableOperacion>
          <ns8:CodigoCliente>
            <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:operacion/operBO:cliente/cteBO:idCliente"/>
          </ns8:CodigoCliente>
          <ns8:NombreCliente>
            <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:operacion/operBO:cliente/cteBO:razonSocial"/>
          </ns8:NombreCliente>
          <ns8:CodigoProducto>
            <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:producto/ns11:idProducto"/>
          </ns8:CodigoProducto>
          <ns8:NombreProducto>
            <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:producto/ns11:descripcion"/>
          </ns8:NombreProducto>
          <xsl:for-each select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:operacion/operBO:montoOperacion/operBO:montoOperacion">
            <xsl:if test="operBO:id = 2">
              <ns8:MontoSolicitado>
                <xsl:value-of select="operBO:monto"/>
              </ns8:MontoSolicitado>
            </xsl:if>
          </xsl:for-each>
          <ns8:Pais>
            <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:operacion/operBO:cliente/cteBO:pais/ns18:Descripcion"/>
          </ns8:Pais>
        </ns13:Operacion>
        <ns13:Proceso>
          <ns9:IdProceso>
            <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:Header/ns13:Proceso/ns9:IdProceso"/>
          </ns9:IdProceso>
          <ns9:CodigoProceso>
            <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:Header/ns13:Proceso/ns9:CodigoProceso"/>
          </ns9:CodigoProceso>
          <ns9:NombreProceso>
            <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:Header/ns13:Proceso/ns9:NombreProceso"/>
          </ns9:NombreProceso>
          <ns9:EsProcesoCore>
            <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:Header/ns13:Proceso/ns9:EsProcesoCore"/>
          </ns9:EsProcesoCore>
          <ns9:RolIniciaProceso>
            <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:Header/ns13:Proceso/ns9:RolIniciaProceso"/>
          </ns9:RolIniciaProceso>
          <ns9:UsuarioIniciaProceso>
            <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:Header/ns13:Proceso/ns9:UsuarioIniciaProceso"/>
          </ns9:UsuarioIniciaProceso>
          <ns9:InstanciaProceso>
            <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:Header/ns13:Proceso/ns9:InstanciaProceso"/>
          </ns9:InstanciaProceso>
          <ns9:IdFlujo>
            <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:Header/ns13:Proceso/ns9:IdFlujo"/>
          </ns9:IdFlujo>
        </ns13:Proceso>
        <xsl:for-each select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:Header/ns4:ParameterType">
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
      <ns1:ConfigGateways>
        <ns1:requiereDocumentacionContractual>
          <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:producto/ns11:reglas/ns11:requiereDocumentacionContractual"/>
        </ns1:requiereDocumentacionContractual>
        <ns1:requiereCOF>
          <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:producto/ns11:reglas/ns11:requiereCOF"/>
        </ns1:requiereCOF>
        <ns1:esDesobligacion>
          <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:producto/ns11:reglas/ns11:esDesobligacion"/>
        </ns1:esDesobligacion>
        <ns1:esCambioPlazo>
          <xsl:value-of select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:producto/ns11:reglas/ns11:esCambioPlazo"/>
        </ns1:esCambioPlazo>
      </ns1:ConfigGateways>
      <xsl:for-each select="/ns0:ConfiguracionFormalizacionEnmiendasResponse/ns0:configuracionFormalizacionEnmiendas/confBO:RolesEquipoTrabajo/confBO:Rol">
        <ns1:Equipo>
          <xsl:if test="ns18:Id = 11">
            <ns1:abogado>
              <xsl:value-of select="ns18:DescripcionCorta"/>
            </ns1:abogado>
          </xsl:if>
          <xsl:if test="ns18:Id = 7">
            <ns1:analistaCOFI>
              <xsl:value-of select="ns18:DescripcionCorta"/>
            </ns1:analistaCOFI>
          </xsl:if>
          <xsl:if test="ns18:Id = 22">
            <ns1:CustodioCOPRES>
              <xsl:value-of select="ns18:DescripcionCorta"/>
            </ns1:CustodioCOPRES>
          </xsl:if>
        </ns1:Equipo>
      </xsl:for-each>
      <xsl:for-each select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns4:ParameterType">
        <ns4:ParameterType>
          <ns4:parameterName>
            <xsl:value-of select="ns4:parameterName"/>
          </ns4:parameterName>
          <ns4:parameterValue>
            <xsl:value-of select="ns4:parameterValue"/>
          </ns4:parameterValue>
        </ns4:ParameterType>
      </xsl:for-each>
      <ns1:solicitudContratacion>
        <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:solicitudContratacion"/>
      </ns1:solicitudContratacion>
      <ns1:idEnmienda>
        <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:idEnmienda"/>
      </ns1:idEnmienda>
      <ns1:idContrato>
        <xsl:value-of select="$formalizacionEnmiendasDO/ns1:FormalizacionEnmiendasPayload/ns1:idContrato"/>
      </ns1:idContrato>
    </ns1:FormalizacionEnmiendasPayload>
  </xsl:template>
</xsl:stylesheet>
