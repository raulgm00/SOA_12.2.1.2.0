<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/LAFT/Payload/V1"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
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
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA01/LAFTPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="LAFTPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/LAFT/Payload/V1"/>
        <oracle-xsl-mapper:param name="laftDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA01/LAFTPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="LAFTPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/LAFT/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [FRI SEP 18 10:38:18 CDT 2015].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <ns0:LAFTPayload>
      <ns0:Header>
        <header:Operacion>
          <ns1:CodigoOperacion>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:CodigoOperacion"/>
          </ns1:CodigoOperacion>
          <ns1:NombreOperacion>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:NombreOperacion"/>
          </ns1:NombreOperacion>
          <ns1:ResponsableOperacion>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion"/>
          </ns1:ResponsableOperacion>
          <ns1:CodigoCliente>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:CodigoCliente"/>
          </ns1:CodigoCliente>
          <ns1:NombreCliente>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:NombreCliente"/>
          </ns1:NombreCliente>
          <ns1:CodigoProducto>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:CodigoProducto"/>
          </ns1:CodigoProducto>
          <ns1:NombreProducto>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:NombreProducto"/>
          </ns1:NombreProducto>
          <ns1:MontoSolicitado>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:MontoSolicitado"/>
          </ns1:MontoSolicitado>
          <ns1:Pais>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:Pais"/>
          </ns1:Pais>
        </header:Operacion>
        <header:Tarea>
          <ns2:CodigoTarea>40</ns2:CodigoTarea>
          <ns2:NombreTarea>Recopilar Informacion</ns2:NombreTarea>
          <ns2:CodigoRol>1</ns2:CodigoRol>
          <ns2:NombreRol>Responsable de Operacion</ns2:NombreRol>
          <ns2:CodigoProceso>7</ns2:CodigoProceso>
          <ns2:NombreProceso>Lavado de Activos</ns2:NombreProceso>
        </header:Tarea>
        <header:Proceso>
          <ns4:IdProceso>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Proceso/ns4:IdProceso"/>
          </ns4:IdProceso>
          <ns4:CodigoProceso>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Proceso/ns4:CodigoProceso"/>
          </ns4:CodigoProceso>
          <ns4:NombreProceso>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Proceso/ns4:NombreProceso"/>
          </ns4:NombreProceso>
          <ns4:EsProcesoCore>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Proceso/ns4:EsProcesoCore"/>
          </ns4:EsProcesoCore>
          <ns4:RolIniciaProceso>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Proceso/ns4:RolIniciaProceso"/>
          </ns4:RolIniciaProceso>
          <ns4:UsuarioIniciaProceso>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Proceso/ns4:UsuarioIniciaProceso"/>
          </ns4:UsuarioIniciaProceso>
          <ns4:InstanciaProceso>
            <xsl:value-of select="/ns0:LAFTPayload/ns0:Header/header:Proceso/ns4:InstanciaProceso"/>
          </ns4:InstanciaProceso>
          <ns4:IdFlujo/>
        </header:Proceso>
        <xsl:for-each select="/ns0:LAFTPayload/ns0:Header/parameter:ParameterType">
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
        <ns0:responsableRO>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:Configuration/ns0:responsableRO"/>
        </ns0:responsableRO>
        <ns0:accionLAFT>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:Configuration/ns0:accionLAFT"/>
        </ns0:accionLAFT>
        <ns0:existeRetorno>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:Configuration/ns0:existeRetorno"/>
        </ns0:existeRetorno>
        <ns0:solicitoAnalista>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:Configuration/ns0:solicitoAnalista"/>
        </ns0:solicitoAnalista>
        <ns0:responsableOFIC>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:Configuration/ns0:responsableOFIC"/>
        </ns0:responsableOFIC>
        <ns0:requiereAsociacion>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:Configuration/ns0:requiereAsociacion"/>
        </ns0:requiereAsociacion>
        <ns0:requiereExcepcion>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:Configuration/ns0:requiereExcepcion"/>
        </ns0:requiereExcepcion>
      </ns0:Configuration>
      <ns0:EquipoPayload>
        <ns0:EjecutorOFIC>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:EquipoPayload/ns0:EjecutorOFIC"/>
        </ns0:EjecutorOFIC>
        <ns0:Gerente>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:EquipoPayload/ns0:Gerente"/>
        </ns0:Gerente>
      </ns0:EquipoPayload>
      <ns0:EquipoEjecucionPayload>
        <ns0:EjecutorOFIC>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:EquipoEjecucionPayload/ns0:EjecutorOFIC"/>
        </ns0:EjecutorOFIC>
        <ns0:Gerente>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:EquipoEjecucionPayload/ns0:Gerente"/>
        </ns0:Gerente>
      </ns0:EquipoEjecucionPayload>
      <ns0:SaveTaskPayload>
        <ns0:esNuevaLaft>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:SaveTaskPayload/ns0:esNuevaLaft"/>
        </ns0:esNuevaLaft>
        <ns0:esElevarLaft>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:SaveTaskPayload/ns0:esElevarLaft"/>
        </ns0:esElevarLaft>
        <ns0:esActualizacionLaft>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:SaveTaskPayload/ns0:esActualizacionLaft"/>
        </ns0:esActualizacionLaft>
        <ns0:esExcepcionLaft>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:SaveTaskPayload/ns0:esExcepcionLaft"/>
        </ns0:esExcepcionLaft>
        <ns0:esAsociacionLaft>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:SaveTaskPayload/ns0:esAsociacionLaft"/>
        </ns0:esAsociacionLaft>
        <ns0:esPanelOficSinDjVisible>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:SaveTaskPayload/ns0:esPanelOficSinDjVisible"/>
        </ns0:esPanelOficSinDjVisible>
        <ns0:tipoSolicitudSinDj>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:SaveTaskPayload/ns0:tipoSolicitudSinDj"/>
        </ns0:tipoSolicitudSinDj>
        <ns0:tipoSolicitudAsoc>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:SaveTaskPayload/ns0:tipoSolicitudAsoc"/>
        </ns0:tipoSolicitudAsoc>
        <ns0:tipoSeguimiento>
          <xsl:value-of select="/ns0:LAFTPayload/ns0:SaveTaskPayload/ns0:tipoSeguimiento"/>
        </ns0:tipoSeguimiento>
      </ns0:SaveTaskPayload>
      <xsl:for-each select="/ns0:LAFTPayload/parameter:ParameterType">
        <parameter:ParameterType>
          <parameter:parameterName>
            <xsl:value-of select="parameter:parameterName"/>
          </parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="parameter:parameterValue"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </xsl:for-each>
      <xsl:choose>
        <xsl:when test='/ns0:LAFTPayload/ns0:porSeguimiento = "true"'>
          <ns0:porSeguimiento>true</ns0:porSeguimiento>
        </xsl:when>
        <xsl:otherwise>
          <ns0:porSeguimiento>false</ns0:porSeguimiento>
        </xsl:otherwise>
      </xsl:choose>
      <ns0:porVencimiento>
        <xsl:value-of select="/ns0:LAFTPayload/ns0:porVencimiento"/>
      </ns0:porVencimiento>
      <ns0:porNoticia>
        <xsl:value-of select="/ns0:LAFTPayload/ns0:porNoticia"/>
      </ns0:porNoticia>
    </ns0:LAFTPayload>
  </xsl:template>
</xsl:stylesheet>
