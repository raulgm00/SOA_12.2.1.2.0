<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:ns3="http://www.bcie.org/ConfiguracionProcesosMO"
                xmlns:ns2="http://www.bcie.org/ConsultarRolesPorProcesoMO"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ns0="http://www.bcie.org/ClienteMO"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns3 ns2 ns0 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:ns21="http://www.bcie.org/ConfiguracionProcesosBO" xmlns:ns5="http://www.bcie.org/TerminoBO"
                xmlns:tns="http://xmlns.bcie.com/ClienteService" xmlns:ns6="http://www.bcie.org/OperacionBO"
                xmlns:ns9="http://www.bcie.org/CommonBO" xmlns:ns8="http://www.bcie.org/ResultBO"
                xmlns:ns18="http://www.bcie.com/ConsultarRolesPorProceso"
                xmlns:ns22="http://xmlns.bcie.com/ConfiguracionProcesosService/types"
                xmlns:ns23="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns24="http://www.bcie.org/MatrizTCCBO" xmlns:ns12="http://www.bcie.org/LineaCreditoBO"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                xmlns:ns25="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ns26="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns1="http://www.bcie.org/ProductoBO" xmlns:ns4="http://www.bcie.org/CondicionBO"
                xmlns:ns19="http://www.bcie.org/ConsultarRolesPorProcesoBO"
                xmlns:ns7="http://www.bcie.org/DeclaracionJuradaBO"
                xmlns:ns27="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:ns10="http://www.bcie.org/DocumentoBO" xmlns:ns11="http://www.bcie.org/ComisionBO"
                xmlns:ns20="http://xmlns.bcie.com/ConfiguracionProcesosService"
                xmlns:ns13="http://www.bcie.org/CatalogoBO" xmlns:ns14="http://www.bcie.org/ContratoBO"
                xmlns:ns15="http://www.bcie.org/ClienteBO" xmlns:ns16="http://www.bcie.org/AtributoBO"
                xmlns:ns29="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:ns28="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1" xmlns:ns17="http://www.bcie.org/ErrorBO"
                xmlns:ns30="http://www.bcie.org/DesembolsoBO">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Wsdl/ADAPTER/ConsultarClienteByIdSAD.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConsultarClienteResponse" namespace="http://www.bcie.org/ClienteMO"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Wsdl/ADAPTER/ConsultarClienteByIdSAD.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConsultarSeguimientoCrediticioByIdClienteResponse"
                                       namespace="http://www.bcie.org/ClienteMO"/>
        <oracle-xsl-mapper:param name="ConsultaSeguimientoCrediticioOut.response"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Wsdl/ConsultarRolesPorProceso.wsdl"/>
        <oracle-xsl-mapper:rootElement name="responseConsultarRolesPorProcesoMessage"
                                       namespace="http://www.bcie.org/ConsultarRolesPorProcesoMO"/>
        <oracle-xsl-mapper:param name="outConsultarRolesPorProcesos.response"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/WSDL/BPEL/ConfiguracionSeguimientoCrediticioSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="configurarSeguimientoCrediticioResponse"
                                       namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
        <oracle-xsl-mapper:param name="outputVariable.response"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/WSDL/BPEL/ConfiguracionSeguimientoCrediticioSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="configurarSeguimientoCrediticioResponse"
                                       namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [FRI AUG 12 10:20:35 CDT 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="ConsultaSeguimientoCrediticioOut.response"/>
  <xsl:param name="outConsultarRolesPorProcesos.response"/>
  <xsl:param name="outputVariable.response"/>
  <xsl:template match="/">
    <ns3:configurarSeguimientoCrediticioResponse>
      <ns3:configuracionSeguimientoCrediticio>
        <ns21:Header>
          <ns27:Cliente>
            <ns29:IdCliente>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:idCliente"/>
            </ns29:IdCliente>
            <ns29:IdFlexCube>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:idFacturador"/>
            </ns29:IdFlexCube>
            <ns29:RazonSocial>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:razonSocial"/>
            </ns29:RazonSocial>
            <ns29:Abreviatura>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:abreviatura"/>
            </ns29:Abreviatura>
            <ns29:IdSector>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:sector/ns13:Id"/>
            </ns29:IdSector>
            <ns29:Sector>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:sector/ns13:DescripcionCorta"/>
            </ns29:Sector>
            <ns29:IdPais>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:pais/ns13:Id"/>
            </ns29:IdPais>
            <ns29:Pais>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:pais/ns13:DescripcionCorta"/>
            </ns29:Pais>
            <ns29:IdOficina>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:oficina/ns13:Id"/>
            </ns29:IdOficina>
            <ns29:Oficina>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:oficina/ns13:DescripcionCorta"/>
            </ns29:Oficina>
            <ns29:ResponsableCliente>
              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns15:responsableCliente"/>
            </ns29:ResponsableCliente>
          </ns27:Cliente>
          <ns27:Proceso>
            <ns26:IdProceso>20</ns26:IdProceso>
            <ns26:IdFlujo>
              <xsl:value-of select="$outputVariable.response/ns3:configurarSeguimientoCrediticioResponse/ns3:configuracionSeguimientoCrediticio/ns21:Header/ns27:Proceso/ns26:IdFlujo"/>
            </ns26:IdFlujo>
          </ns27:Proceso>
        </ns21:Header>
        <ns21:RolesEquipoTrabajo>
          <xsl:for-each select="$outConsultarRolesPorProcesos.response/ns2:responseConsultarRolesPorProcesoMessage/ns2:ListadoRoles/ns19:listadoRoles">
            <ns21:Rol>
              <xsl:if test="ns13:Id">
                <ns13:Id>
                  <xsl:value-of select="ns13:Id"/>
                </ns13:Id>
              </xsl:if>
              <xsl:if test="ns13:Descripcion">
                <ns13:Descripcion>
                  <xsl:value-of select="ns13:Descripcion"/>
                </ns13:Descripcion>
              </xsl:if>
              <xsl:if test="ns13:DescripcionCorta">
                <ns13:DescripcionCorta>
                  <xsl:value-of select="ns13:DescripcionCorta"/>
                </ns13:DescripcionCorta>
              </xsl:if>
              <xsl:if test="ns13:estatus">
                <ns13:estatus>
                  <xsl:value-of select="ns13:estatus"/>
                </ns13:estatus>
              </xsl:if>
              <xsl:if test="ns13:codigoExterno">
                <ns13:codigoExterno>
                  <xsl:value-of select="ns13:codigoExterno"/>
                </ns13:codigoExterno>
              </xsl:if>
            </ns21:Rol>
          </xsl:for-each>
        </ns21:RolesEquipoTrabajo>
      </ns3:configuracionSeguimientoCrediticio>
      <ns3:Resultado>
        <ns8:result>OK</ns8:result>
        <ns8:message>Operación Exitosa</ns8:message>
      </ns3:Resultado>
    </ns3:configurarSeguimientoCrediticioResponse>
  </xsl:template>
</xsl:stylesheet>
