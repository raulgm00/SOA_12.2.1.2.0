<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:ns2="http://www.bcie.org/ConsultarRolesPorProcesoMO"
                xmlns:ns0="http://www.bcie.org/ConfiguracionProcesosMO"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:ns1="http://www.bcie.org/ProductoMO" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns2 ns0 ns1 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:ns3="http://www.bcie.org/TerminoBO" xmlns:ns4="http://www.bcie.org/ConfiguracionProcesosBO"
                xmlns:ns5="http://www.bcie.org/OperacionBO" xmlns:ns6="http://www.bcie.org/ResultBO"
                xmlns:ns7="http://www.bcie.org/CommonBO" xmlns:ns27="http://www.bcie.com/ConsultarRolesPorProceso"
                xmlns:ns8="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns10="http://www.bcie.org/MatrizTCCBO" xmlns:ns9="http://www.bcie.org/LineaCreditoBO"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                xmlns:ns11="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ns12="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns14="http://www.bcie.org/CondicionBO" xmlns:ns13="http://www.bcie.org/ProductoBO"
                xmlns:ns28="http://www.bcie.org/ConsultarRolesPorProcesoBO"
                xmlns:ns15="http://www.bcie.org/DeclaracionJuradaBO"
                xmlns:ns16="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:ns17="http://www.bcie.org/DocumentoBO" xmlns:ns18="http://www.bcie.org/ComisionBO"
                xmlns:tns="http://xmlns.bcie.com/ConfiguracionProcesosService"
                xmlns:ns26="http://xmlns.bcie.org/ProductoService" xmlns:ns19="http://www.bcie.org/CatalogoBO"
                xmlns:ns20="http://www.bcie.org/ContratoBO" xmlns:ns21="http://www.bcie.org/ClienteBO"
                xmlns:ns22="http://www.bcie.org/AtributoBO"
                xmlns:ns24="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:ns23="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns25="http://www.bcie.org/ErrorBO">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/WSDL/BPEL/ConfiguracionCierreFideicomisoSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="configuracionCierreFideicomisoResponse"
                                       namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioProducto/Producto/V1/Wsdl/ADAPTER/ConsultarProductoByIdOperacionSAD.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConsultarProductoByIdOperacionResponse"
                                       namespace="http://www.bcie.org/ProductoMO"/>
        <oracle-xsl-mapper:param name="ConsultarProductoOut.response"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Wsdl/ConsultarRolesPorProceso.wsdl"/>
        <oracle-xsl-mapper:rootElement name="responseConsultarRolesPorProcesoMessage"
                                       namespace="http://www.bcie.org/ConsultarRolesPorProcesoMO"/>
        <oracle-xsl-mapper:param name="ConsultaRolesByIdOperacionOut.response"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/WSDL/BPEL/ConfiguracionCierreFideicomisoSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="configuracionCierreFideicomisoResponse"
                                       namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [THU AUG 11 11:23:36 CDT 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="ConsultarProductoOut.response"/>
  <xsl:param name="ConsultaRolesByIdOperacionOut.response"/>
  <xsl:template match="/">
    <ns0:configuracionCierreFideicomisoResponse>
      <ns0:ConfiguracionCierreFideicomiso>
        <ns4:Header>
          <ns16:Operacion>
            <ns11:CodigoOperacion>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Operacion/ns11:CodigoOperacion"/>
            </ns11:CodigoOperacion>
            <ns11:NombreOperacion>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Operacion/ns11:NombreOperacion"/>
            </ns11:NombreOperacion>
            <ns11:ResponsableOperacion>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Operacion/ns11:ResponsableOperacion"/>
            </ns11:ResponsableOperacion>
            <ns11:CodigoCliente>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Operacion/ns11:CodigoCliente"/>
            </ns11:CodigoCliente>
            <ns11:NombreCliente>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Operacion/ns11:NombreCliente"/>
            </ns11:NombreCliente>
            <ns11:CodigoProducto>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Operacion/ns11:CodigoProducto"/>
            </ns11:CodigoProducto>
            <ns11:NombreProducto>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Operacion/ns11:NombreProducto"/>
            </ns11:NombreProducto>
            <ns11:MontoSolicitado>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Operacion/ns11:MontoSolicitado"/>
            </ns11:MontoSolicitado>
            <ns11:Pais>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Operacion/ns11:Pais"/>
            </ns11:Pais>
          </ns16:Operacion>
          <ns16:Proceso>
            <ns12:IdProceso>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Proceso/ns12:IdProceso"/>
            </ns12:IdProceso>
            <ns12:IdFlujo>
              <xsl:value-of select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns16:Proceso/ns12:IdFlujo"/>
            </ns12:IdFlujo>
          </ns16:Proceso>
          <xsl:for-each select="/ns0:configuracionCierreFideicomisoResponse/ns0:ConfiguracionCierreFideicomiso/ns4:Header/ns8:ParameterType">
            <ns8:ParameterType>
              <ns8:parameterName>
                <xsl:value-of select="ns8:parameterName"/>
              </ns8:parameterName>
              <ns8:parameterValue>
                <xsl:value-of select="ns8:parameterValue"/>
              </ns8:parameterValue>
            </ns8:ParameterType>
          </xsl:for-each>
        </ns4:Header>
        <ns4:RolesEquipoTrabajo>
          <xsl:for-each select="$ConsultaRolesByIdOperacionOut.response/ns2:responseConsultarRolesPorProcesoMessage/ns2:ListadoRoles/ns28:listadoRoles">
            <ns4:Rol>
              <xsl:if test="ns19:Id">
                <ns19:Id>
                  <xsl:value-of select="ns19:Id"/>
                </ns19:Id>
              </xsl:if>
              <xsl:if test="ns19:Descripcion">
                <ns19:Descripcion>
                  <xsl:value-of select="ns19:Descripcion"/>
                </ns19:Descripcion>
              </xsl:if>
              <xsl:if test="ns19:DescripcionCorta">
                <ns19:DescripcionCorta>
                  <xsl:value-of select="ns19:DescripcionCorta"/>
                </ns19:DescripcionCorta>
              </xsl:if>
              <xsl:if test="ns19:estatus">
                <ns19:estatus>
                  <xsl:value-of select="ns19:estatus"/>
                </ns19:estatus>
              </xsl:if>
              <xsl:if test="ns19:codigoExterno">
                <ns19:codigoExterno>
                  <xsl:value-of select="ns19:codigoExterno"/>
                </ns19:codigoExterno>
              </xsl:if>
            </ns4:Rol>
          </xsl:for-each>
        </ns4:RolesEquipoTrabajo>
      </ns0:ConfiguracionCierreFideicomiso>
      <ns0:Resultado>
        <ns6:result>
          <xsl:value-of select="$ConsultarProductoOut.response/ns1:ConsultarProductoByIdOperacionResponse/ns1:Resultado/ns6:result"/>
        </ns6:result>
        <ns6:message>
          <xsl:value-of select="$ConsultarProductoOut.response/ns1:ConsultarProductoByIdOperacionResponse/ns1:Resultado/ns6:message"/>
        </ns6:message>
      </ns0:Resultado>
    </ns0:configuracionCierreFideicomisoResponse>
  </xsl:template>
</xsl:stylesheet>
