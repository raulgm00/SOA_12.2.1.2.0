<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/LAFT/Payload/V1"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/LAFTError/Payload/V1"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket"
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
        <oracle-xsl-mapper:rootElement name="LAFTErrorPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/LAFT/Payload/V1"/>
        <oracle-xsl-mapper:param name="errorDO"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA01/LAFTPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="LAFTPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/LAFT/Payload/V1"/>
        <oracle-xsl-mapper:param name="laftDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA01/LAFTErrorPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="LAFTErrorPayloadType"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/LAFTError/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [TUE MAR 01 12:17:09 CST 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="laftDO"/>
  <xsl:template match="/">
    <tns:LAFTErrorPayloadType>
      <tns:Header>
        <header:Operacion>
          <ns1:CodigoOperacion>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:CodigoOperacion"/>
          </ns1:CodigoOperacion>
          <ns1:NombreOperacion>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:NombreOperacion"/>
          </ns1:NombreOperacion>
          <ns1:ResponsableOperacion>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion"/>
          </ns1:ResponsableOperacion>
          <ns1:CodigoCliente>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:CodigoCliente"/>
          </ns1:CodigoCliente>
          <ns1:NombreCliente>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:NombreCliente"/>
          </ns1:NombreCliente>
          <ns1:CodigoProducto>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:CodigoProducto"/>
          </ns1:CodigoProducto>
          <ns1:NombreProducto>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:NombreProducto"/>
          </ns1:NombreProducto>
          <ns1:MontoSolicitado>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:MontoSolicitado"/>
          </ns1:MontoSolicitado>
          <ns1:Pais>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Operacion/ns1:Pais"/>
          </ns1:Pais>
        </header:Operacion>
        <header:Tarea>
          <ns2:CodigoTarea>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Tarea/ns2:CodigoTarea"/>
          </ns2:CodigoTarea>
          <ns2:NombreTarea>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Tarea/ns2:NombreTarea"/>
          </ns2:NombreTarea>
          <ns2:CodigoRol>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Tarea/ns2:CodigoRol"/>
          </ns2:CodigoRol>
          <ns2:NombreRol>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Tarea/ns2:NombreRol"/>
          </ns2:NombreRol>
          <ns2:CodigoProceso>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Tarea/ns2:CodigoProceso"/>
          </ns2:CodigoProceso>
          <ns2:NombreProceso>
            <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Header/header:Tarea/ns2:NombreProceso"/>
          </ns2:NombreProceso>
        </header:Tarea>
        <xsl:for-each select="$laftDO/ns0:LAFTPayload/ns0:Header/parameter:ParameterType">
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
        <tns:responsableRO>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Configuration/ns0:responsableRO"/>
        </tns:responsableRO>
        <tns:accionLAFT>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Configuration/ns0:accionLAFT"/>
        </tns:accionLAFT>
        <tns:existeRetorno>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Configuration/ns0:existeRetorno"/>
        </tns:existeRetorno>
        <tns:solicitoAnalista>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Configuration/ns0:solicitoAnalista"/>
        </tns:solicitoAnalista>
        <tns:responsableOFIC>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Configuration/ns0:responsableOFIC"/>
        </tns:responsableOFIC>
        <tns:requiereAsociacion>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Configuration/ns0:requiereAsociacion"/>
        </tns:requiereAsociacion>
        <tns:requiereExcepcion>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:Configuration/ns0:requiereExcepcion"/>
        </tns:requiereExcepcion>
      </tns:Configuration>
      <tns:EquipoPayload>
        <tns:EjecutorOFIC>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:EquipoPayload/ns0:EjecutorOFIC"/>
        </tns:EjecutorOFIC>
        <tns:GerentePais>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:EquipoPayload/ns0:Gerente"/>
        </tns:GerentePais>
      </tns:EquipoPayload>
      <tns:EquipoEjecucion>
        <tns:EjecutorOFIC>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:EquipoEjecucionPayload/ns0:EjecutorOFIC"/>
        </tns:EjecutorOFIC>
        <tns:GerentePais>
          <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:EquipoEjecucionPayload/ns0:Gerente"/>
        </tns:GerentePais>
      </tns:EquipoEjecucion>
      <tns:SaveTaskPayload>
        <tns:esNuevaLaft>
          <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:SaveTaskPayload/ns0:esNuevaLaft"/>
        </tns:esNuevaLaft>
        <tns:esElevarLaft>
          <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:SaveTaskPayload/ns0:esElevarLaft"/>
        </tns:esElevarLaft>
        <tns:esActualizacionLaft>
          <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:SaveTaskPayload/ns0:esActualizacionLaft"/>
        </tns:esActualizacionLaft>
        <tns:esExcepcionLaft>
          <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:SaveTaskPayload/ns0:esExcepcionLaft"/>
        </tns:esExcepcionLaft>
        <tns:esAsociacionLaft>
          <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:SaveTaskPayload/ns0:esAsociacionLaft"/>
        </tns:esAsociacionLaft>
        <tns:esPanelOficSinDjVisible>
          <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:SaveTaskPayload/ns0:esPanelOficSinDjVisible"/>
        </tns:esPanelOficSinDjVisible>
        <tns:tipoSolicitudSinDj>
          <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:SaveTaskPayload/ns0:tipoSolicitudSinDj"/>
        </tns:tipoSolicitudSinDj>
        <tns:tipoSolicitudAsoc>
          <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:SaveTaskPayload/ns0:tipoSolicitudAsoc"/>
        </tns:tipoSolicitudAsoc>
        <tns:tipoSeguimiento>
          <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:SaveTaskPayload/ns0:tipoSeguimiento"/>
        </tns:tipoSeguimiento>
      </tns:SaveTaskPayload>
      <tns:porSeguimiento>
        <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:porSeguimiento"/>
      </tns:porSeguimiento>
      <tns:porVencimiento>
        <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:porVencimiento"/>
      </tns:porVencimiento>
      <tns:porNoticia>
        <xsl:value-of select="$laftDO/ns0:LAFTPayload/ns0:porNoticia"/>
      </tns:porNoticia>
      <xsl:for-each select="$laftDO/ns0:LAFTPayload/parameter:ParameterType">
        <parameter:ParameterType>
          <parameter:parameterName>
            <xsl:value-of select="parameter:parameterName"/>
          </parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="parameter:parameterValue"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </xsl:for-each>
      <tns:ErrorMsg>
        <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:ErrorMsg"/>
      </tns:ErrorMsg>
      <tns:ErrorNamespace>
        <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:ErrorNamespace"/>
      </tns:ErrorNamespace>
      <tns:ErrorFault>
        <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:ErrorFault"/>
      </tns:ErrorFault>
      <tns:Accion>
        <xsl:value-of select="/ns0:LAFTErrorPayload/ns0:Accion"/>
      </tns:Accion>
    </tns:LAFTErrorPayloadType>
  </xsl:template>
</xsl:stylesheet>
