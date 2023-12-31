<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Cancelar/Payload/V1"
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
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA05/CancelarOperacion.xsd"/>
        <oracle-xsl-mapper:rootElement name="CancelarPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Cancelar/Payload/V1"/>
        <oracle-xsl-mapper:param name="cancelarDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA05/CancelarOperacion.xsd"/>
        <oracle-xsl-mapper:rootElement name="CancelarPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Cancelar/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [THU NOV 26 17:05:12 CST 2015].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <ns0:CancelarPayload>
      <ns0:Header>
        <header:Operacion>
          <ns1:CodigoOperacion>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Operacion/ns1:CodigoOperacion"/>
          </ns1:CodigoOperacion>
          <ns1:NombreOperacion>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Operacion/ns1:NombreOperacion"/>
          </ns1:NombreOperacion>
          <ns1:ResponsableOperacion>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion"/>
          </ns1:ResponsableOperacion>
          <ns1:CodigoCliente>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Operacion/ns1:CodigoCliente"/>
          </ns1:CodigoCliente>
          <ns1:NombreCliente>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Operacion/ns1:NombreCliente"/>
          </ns1:NombreCliente>
          <ns1:CodigoProducto>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Operacion/ns1:CodigoProducto"/>
          </ns1:CodigoProducto>
          <ns1:NombreProducto>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Operacion/ns1:NombreProducto"/>
          </ns1:NombreProducto>
          <ns1:MontoSolicitado>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Operacion/ns1:MontoSolicitado"/>
          </ns1:MontoSolicitado>
          <ns1:Pais>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Operacion/ns1:Pais"/>
          </ns1:Pais>
        </header:Operacion>
        <header:Tarea>
          <xsl:choose>
            <xsl:when test='/ns0:CancelarPayload/ns0:razon = "Suspensión"'>
              <ns2:CodigoTarea>83</ns2:CodigoTarea>
            </xsl:when>
            <xsl:otherwise>
              <ns2:CodigoTarea>86</ns2:CodigoTarea>
            </xsl:otherwise>
          </xsl:choose>
          <ns2:NombreTarea>
            <xsl:value-of select='concat ("Aprobar ", /ns0:CancelarPayload/ns0:razon, " de Operación" )'/>
          </ns2:NombreTarea>
          <ns2:CodigoRol>8</ns2:CodigoRol>
          <ns2:NombreRol>Gerente de País</ns2:NombreRol>
          <ns2:CodigoProceso>11</ns2:CodigoProceso>
          <ns2:NombreProceso>Cancelar Operación</ns2:NombreProceso>
        </header:Tarea>
        <header:Proceso>
          <ns4:IdProceso>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Proceso/ns4:IdProceso"/>
          </ns4:IdProceso>
          <ns4:CodigoProceso>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Proceso/ns4:CodigoProceso"/>
          </ns4:CodigoProceso>
          <ns4:NombreProceso>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Proceso/ns4:NombreProceso"/>
          </ns4:NombreProceso>
          <ns4:EsProcesoCore>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Proceso/ns4:EsProcesoCore"/>
          </ns4:EsProcesoCore>
          <ns4:RolIniciaProceso>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Proceso/ns4:RolIniciaProceso"/>
          </ns4:RolIniciaProceso>
          <ns4:UsuarioIniciaProceso>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Proceso/ns4:UsuarioIniciaProceso"/>
          </ns4:UsuarioIniciaProceso>
          <ns4:InstanciaProceso>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Proceso/ns4:InstanciaProceso"/>
          </ns4:InstanciaProceso>
          <ns4:IdFlujo>
            <xsl:value-of select="/ns0:CancelarPayload/ns0:Header/header:Proceso/ns4:IdFlujo"/>
          </ns4:IdFlujo>
        </header:Proceso>
        <xsl:for-each select="/ns0:CancelarPayload/ns0:Header/parameter:ParameterType">
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
        <ns0:solicitaGerente>
          <xsl:value-of select="/ns0:CancelarPayload/ns0:Configuration/ns0:solicitaGerente"/>
        </ns0:solicitaGerente>
      </ns0:Configuration>
      <ns0:solicitaCancelar>
        <xsl:value-of select="/ns0:CancelarPayload/ns0:solicitaCancelar"/>
      </ns0:solicitaCancelar>
      <ns0:razon>
        <xsl:value-of select="/ns0:CancelarPayload/ns0:razon"/>
      </ns0:razon>
      <ns0:observacion>
        <xsl:value-of select="/ns0:CancelarPayload/ns0:observacion"/>
      </ns0:observacion>
      <ns0:EquipoPayload>
        <ns0:GerentePais>
          <xsl:value-of select="/ns0:CancelarPayload/ns0:EquipoPayload/ns0:GerentePais"/>
        </ns0:GerentePais>
      </ns0:EquipoPayload>
      <xsl:for-each select="/ns0:CancelarPayload/ns0:Enmiendas">
        <ns0:Enmiendas>
          <ns0:idEnmienda>
            <xsl:value-of select="ns0:idEnmienda"/>
          </ns0:idEnmienda>
        </ns0:Enmiendas>
      </xsl:for-each>
      <xsl:for-each select="/ns0:CancelarPayload/ns0:Comisiones">
        <ns0:Comisiones>
          <ns0:idComision>
            <xsl:value-of select="ns0:idComision"/>
          </ns0:idComision>
        </ns0:Comisiones>
      </xsl:for-each>
      <xsl:for-each select="/ns0:CancelarPayload/ns0:Lotes">
        <ns0:Lotes>
          <ns0:idLote>
            <xsl:value-of select="ns0:idLote"/>
          </ns0:idLote>
        </ns0:Lotes>
      </xsl:for-each>
      <xsl:for-each select="/ns0:CancelarPayload/ns0:AgrupadorCondiciones">
        <ns0:AgrupadorCondiciones>
          <ns0:idAgrupador>
            <xsl:value-of select="ns0:idAgrupador"/>
          </ns0:idAgrupador>
        </ns0:AgrupadorCondiciones>
      </xsl:for-each>
      <xsl:for-each select="/ns0:CancelarPayload/ns0:SolicitudDesembolso">
        <ns0:SolicitudDesembolso>
          <ns0:idSolicitud>
            <xsl:value-of select="ns0:idSolicitud"/>
          </ns0:idSolicitud>
        </ns0:SolicitudDesembolso>
      </xsl:for-each>
      <xsl:for-each select="/ns0:CancelarPayload/ns0:ContratoDesembolso">
        <ns0:ContratoDesembolso>
          <ns0:idContrato>
            <xsl:value-of select="ns0:idContrato"/>
          </ns0:idContrato>
        </ns0:ContratoDesembolso>
      </xsl:for-each>
      <xsl:for-each select="/ns0:CancelarPayload/ns0:Adquisiciones">
        <ns0:Adquisiciones>
          <ns0:idAdquisicion>
            <xsl:value-of select="ns0:idAdquisicion"/>
          </ns0:idAdquisicion>
        </ns0:Adquisiciones>
      </xsl:for-each>
      <xsl:for-each select="/ns0:CancelarPayload/ns0:RegistraComision">
        <ns0:RegistraComision>
          <ns0:idInstanciaRegistraComision>
            <xsl:value-of select="ns0:idInstanciaRegistraComision"/>
          </ns0:idInstanciaRegistraComision>
        </ns0:RegistraComision>
      </xsl:for-each>
      <ns0:EquipoEjecucionPayload>
        <ns0:GerentePais>
          <xsl:value-of select="/ns0:CancelarPayload/ns0:EquipoEjecucionPayload/ns0:GerentePais"/>
        </ns0:GerentePais>
      </ns0:EquipoEjecucionPayload>
      <xsl:for-each select="/ns0:CancelarPayload/parameter:ParameterType">
        <parameter:ParameterType>
          <parameter:parameterName>
            <xsl:value-of select="parameter:parameterName"/>
          </parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="parameter:parameterValue"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </xsl:for-each>
    </ns0:CancelarPayload>
  </xsl:template>
</xsl:stylesheet>
