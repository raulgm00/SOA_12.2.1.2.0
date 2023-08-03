<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns2="http://www.bcie.org/OperacionMO"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ns0="http://www.bcie.org/DesembolsoMO"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns3="http://www.bcie.org/LineaCreditoMO"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns2 ns0 ns3 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:ns1="http://www.bcie.org/ReglaTareaBO" xmlns:ns6="http://www.bcie.org/TerminoBO"
                xmlns:ns7="http://www.bcie.org/ReglaBO" xmlns:ns8="http://www.bcie.org/OperacionBO"
                xmlns:ns11="http://www.bcie.org/ResultBO" xmlns:ns10="http://www.bcie.org/CommonBO"
                xmlns:tns="http://xmlns.oracle.com/OSB_BCIE/MDS/ValidarLimitesSOR"
                xmlns:ns24="http://www.bcie.org/AprobacionBO" xmlns:ns15="http://www.bcie.org/LineaCreditoBO"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                xmlns:ns23="http://www.bice.org/ActualizarLineaCreditoService"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:ns22="http://xmlns.bcie.com/OperacionService"
                xmlns:ns4="http://www.bcie.org/CondicionBO" xmlns:ns5="http://www.bcie.org/ProductoBO"
                xmlns:ns9="http://www.bcie.org/DeclaracionJuradaBO" xmlns:ns12="http://www.bcie.org/DocumentoBO"
                xmlns:ns13="http://www.bcie.org/HerramientaCEBO" xmlns:ns14="http://www.bcie.org/ComisionBO"
                xmlns:ns16="http://www.bcie.org/CatalogoBO" xmlns:ns17="http://www.bcie.org/DesembolsoBO"
                xmlns:ns18="http://www.bcie.org/ContratoBO" xmlns:ns19="http://www.bcie.org/ClienteBO"
                xmlns:ns20="http://www.bcie.org/AtributoBO" xmlns:ns21="http://www.bcie.org/ErrorBO"
                xmlns:ns25="http://www.bcie.org/MatrizTCCBO">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Wsdl/BPEL/ValidarLimitesSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConsultarDesembolsosByIdResponse"
                                       namespace="http://www.bcie.org/DesembolsoMO"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Wsdl/BPEL/ValidarLimitesSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConsultarTransitoGrupoPaisResponse"
                                       namespace="http://www.bcie.org/DesembolsoMO"/>
        <oracle-xsl-mapper:param name="ConsultarTransitoGrupoPais_OutputVariable.ConsultarTransitoGrupoPaisResponseMessage"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Wsdl/ADAPTER/ConsultarOperacionByIdSAD.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConsultarOperacionResponse" namespace="http://www.bcie.org/OperacionMO"/>
        <oracle-xsl-mapper:param name="ConsultarOperacionById_OutputVariable.response"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Wsdl/ADAPTER/ConsultarLineaCreditoByIdLineaCreditoSAD.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConsultarLineaCreditoByIdLineaCreditoResponse"
                                       namespace="http://www.bcie.org/LineaCreditoMO"/>
        <oracle-xsl-mapper:param name="consultarLineaCreditoByIdLineaCredito_OutputVariable.response"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Wsdl/BPEL/ValidarLimitesSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ValidarLimitesLIMRequest" namespace="http://www.bcie.org/DesembolsoMO"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [TUE OCT 11 12:37:09 CDT 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="ConsultarTransitoGrupoPais_OutputVariable.ConsultarTransitoGrupoPaisResponseMessage"/>
  <xsl:param name="ConsultarOperacionById_OutputVariable.response"/>
  <xsl:param name="consultarLineaCreditoByIdLineaCredito_OutputVariable.response"/>
  <xsl:template match="/">
    <ns0:ValidarLimitesLIMRequest>
      <ns0:Operacion>
        <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente">
          <ns8:cliente>
            <ns19:idCliente>
              <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:idCliente"/>
            </ns19:idCliente>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:idFacturador != ''">
              <ns19:idFacturador>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:idFacturador"/>
              </ns19:idFacturador>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:razonSocial">
              <ns19:razonSocial>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:razonSocial"/>
              </ns19:razonSocial>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:abreviatura">
              <ns19:abreviatura>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:abreviatura"/>
              </ns19:abreviatura>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona">
              <ns19:tipoPersona>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:Id">
                  <ns16:Id>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:Id"/>
                  </ns16:Id>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:Descripcion">
                  <ns16:Descripcion>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:Descripcion"/>
                  </ns16:Descripcion>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:DescripcionCorta">
                  <ns16:DescripcionCorta>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:DescripcionCorta"/>
                  </ns16:DescripcionCorta>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:estatus">
                  <ns16:estatus>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:estatus"/>
                  </ns16:estatus>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:codigoExterno">
                  <ns16:codigoExterno>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoPersona/ns16:codigoExterno"/>
                  </ns16:codigoExterno>
                </xsl:if>
              </ns19:tipoPersona>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente">
              <ns19:tipoCliente>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:Id">
                  <ns16:Id>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:Id"/>
                  </ns16:Id>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:Descripcion">
                  <ns16:Descripcion>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:Descripcion"/>
                  </ns16:Descripcion>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:DescripcionCorta">
                  <ns16:DescripcionCorta>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:DescripcionCorta"/>
                  </ns16:DescripcionCorta>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:estatus">
                  <ns16:estatus>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:estatus"/>
                  </ns16:estatus>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:codigoExterno">
                  <ns16:codigoExterno>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoCliente/ns16:codigoExterno"/>
                  </ns16:codigoExterno>
                </xsl:if>
              </ns19:tipoCliente>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector">
              <ns19:sector>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:Id">
                  <ns16:Id>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:Id"/>
                  </ns16:Id>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:Descripcion">
                  <ns16:Descripcion>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:Descripcion"/>
                  </ns16:Descripcion>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:DescripcionCorta">
                  <ns16:DescripcionCorta>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:DescripcionCorta"/>
                  </ns16:DescripcionCorta>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:estatus">
                  <ns16:estatus>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:estatus"/>
                  </ns16:estatus>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:codigoExterno">
                  <ns16:codigoExterno>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:sector/ns16:codigoExterno"/>
                  </ns16:codigoExterno>
                </xsl:if>
              </ns19:sector>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion">
              <ns19:tipoInstitucion>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:Id">
                  <ns16:Id>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:Id"/>
                  </ns16:Id>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:Descripcion">
                  <ns16:Descripcion>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:Descripcion"/>
                  </ns16:Descripcion>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:DescripcionCorta">
                  <ns16:DescripcionCorta>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:DescripcionCorta"/>
                  </ns16:DescripcionCorta>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:estatus">
                  <ns16:estatus>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:estatus"/>
                  </ns16:estatus>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:codigoExterno">
                  <ns16:codigoExterno>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoInstitucion/ns16:codigoExterno"/>
                  </ns16:codigoExterno>
                </xsl:if>
              </ns19:tipoInstitucion>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais">
              <ns19:pais>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:Id">
                  <ns16:Id>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:Id"/>
                  </ns16:Id>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:Descripcion">
                  <ns16:Descripcion>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:Descripcion"/>
                  </ns16:Descripcion>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:DescripcionCorta">
                  <ns16:DescripcionCorta>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:DescripcionCorta"/>
                  </ns16:DescripcionCorta>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:estatus">
                  <ns16:estatus>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:estatus"/>
                  </ns16:estatus>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:codigoExterno">
                  <ns16:codigoExterno>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:pais/ns16:codigoExterno"/>
                  </ns16:codigoExterno>
                </xsl:if>
              </ns19:pais>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico">
              <ns19:grupoEconomico>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:Id">
                  <ns16:Id>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:Id"/>
                  </ns16:Id>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:Descripcion">
                  <ns16:Descripcion>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:Descripcion"/>
                  </ns16:Descripcion>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:DescripcionCorta">
                  <ns16:DescripcionCorta>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:DescripcionCorta"/>
                  </ns16:DescripcionCorta>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:estatus">
                  <ns16:estatus>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:estatus"/>
                  </ns16:estatus>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:codigoExterno">
                  <ns16:codigoExterno>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEconomico/ns16:codigoExterno"/>
                  </ns16:codigoExterno>
                </xsl:if>
              </ns19:grupoEconomico>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoIdentificacion">
              <ns19:tipoIdentificacion>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:tipoIdentificacion"/>
              </ns19:tipoIdentificacion>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:numeroIdentificacion">
              <ns19:numeroIdentificacion>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:numeroIdentificacion"/>
              </ns19:numeroIdentificacion>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:direccion">
              <ns19:direccion>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:direccion"/>
              </ns19:direccion>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:telefono">
              <ns19:telefono>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:telefono"/>
              </ns19:telefono>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:fax">
              <ns19:fax>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:fax"/>
              </ns19:fax>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:email">
              <ns19:email>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:email"/>
              </ns19:email>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:ciudad">
              <ns19:ciudad>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:ciudad"/>
              </ns19:ciudad>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina">
              <ns19:oficina>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:Id">
                  <ns16:Id>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:Id"/>
                  </ns16:Id>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:Descripcion">
                  <ns16:Descripcion>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:Descripcion"/>
                  </ns16:Descripcion>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:DescripcionCorta">
                  <ns16:DescripcionCorta>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:DescripcionCorta"/>
                  </ns16:DescripcionCorta>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:estatus">
                  <ns16:estatus>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:estatus"/>
                  </ns16:estatus>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:codigoExterno">
                  <ns16:codigoExterno>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:oficina/ns16:codigoExterno"/>
                  </ns16:codigoExterno>
                </xsl:if>
              </ns19:oficina>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEmpresarial">
              <ns19:grupoEmpresarial>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:grupoEmpresarial"/>
              </ns19:grupoEmpresarial>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:fechaRegistro">
              <ns19:fechaRegistro>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:fechaRegistro"/>
              </ns19:fechaRegistro>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:fechaAprobacion">
              <ns19:fechaAprobacion>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:fechaAprobacion"/>
              </ns19:fechaAprobacion>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:ejecutivo">
              <ns19:ejecutivo>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:ejecutivo"/>
              </ns19:ejecutivo>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:responsableCliente">
              <ns19:responsableCliente>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:responsableCliente"/>
              </ns19:responsableCliente>
            </xsl:if>
            <xsl:for-each select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:Contactos">
              <ns19:Contactos>
                <xsl:for-each select="ns19:Contacto">
                  <ns19:Contacto>
                    <ns19:idContacto>
                      <xsl:value-of select="ns19:idContacto"/>
                    </ns19:idContacto>
                    <ns19:idCliente>
                      <xsl:value-of select="ns19:idCliente"/>
                    </ns19:idCliente>
                    <ns19:nombre>
                      <xsl:value-of select="ns19:nombre"/>
                    </ns19:nombre>
                    <ns19:telefono>
                      <xsl:value-of select="ns19:telefono"/>
                    </ns19:telefono>
                    <ns19:correoElectronico>
                      <xsl:value-of select="ns19:correoElectronico"/>
                    </ns19:correoElectronico>
                    <ns19:cargo>
                      <xsl:value-of select="ns19:cargo"/>
                    </ns19:cargo>
                    <ns19:tipo>
                      <xsl:value-of select="ns19:tipo"/>
                    </ns19:tipo>
                    <ns19:fechaRegistro>
                      <xsl:value-of select="ns19:fechaRegistro"/>
                    </ns19:fechaRegistro>
                    <ns19:idContactosClientes>
                      <xsl:value-of select="ns19:idContactosClientes"/>
                    </ns19:idContactosClientes>
                  </ns19:Contacto>
                </xsl:for-each>
              </ns19:Contactos>
            </xsl:for-each>
            <ns19:InformacionCorrecta>
              <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:InformacionCorrecta"/>
            </ns19:InformacionCorrecta>
            <ns19:ActualizacionPermitida>
              <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:ActualizacionPermitida"/>
            </ns19:ActualizacionPermitida>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:comentarioAprobacion">
              <ns19:comentarioAprobacion>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:comentarioAprobacion"/>
              </ns19:comentarioAprobacion>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:revisadoAprobacion">
              <ns19:revisadoAprobacion>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:revisadoAprobacion"/>
              </ns19:revisadoAprobacion>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:status">
              <ns19:status>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:status"/>
              </ns19:status>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:fechaBaja">
              <ns19:fechaBaja>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:fechaBaja"/>
              </ns19:fechaBaja>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:diaPago">
              <ns19:diaPago>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:diaPago"/>
              </ns19:diaPago>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC">
              <ns19:SRC>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:Id">
                  <ns16:Id>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:Id"/>
                  </ns16:Id>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:Descripcion">
                  <ns16:Descripcion>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:Descripcion"/>
                  </ns16:Descripcion>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:DescripcionCorta">
                  <ns16:DescripcionCorta>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:DescripcionCorta"/>
                  </ns16:DescripcionCorta>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:estatus">
                  <ns16:estatus>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:estatus"/>
                  </ns16:estatus>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:codigoExterno">
                  <ns16:codigoExterno>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:SRC/ns16:codigoExterno"/>
                  </ns16:codigoExterno>
                </xsl:if>
              </ns19:SRC>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva">
              <ns19:perspectiva>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:Id">
                  <ns16:Id>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:Id"/>
                  </ns16:Id>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:Descripcion">
                  <ns16:Descripcion>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:Descripcion"/>
                  </ns16:Descripcion>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:DescripcionCorta">
                  <ns16:DescripcionCorta>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:DescripcionCorta"/>
                  </ns16:DescripcionCorta>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:estatus">
                  <ns16:estatus>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:estatus"/>
                  </ns16:estatus>
                </xsl:if>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:codigoExterno">
                  <ns16:codigoExterno>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:perspectiva/ns16:codigoExterno"/>
                  </ns16:codigoExterno>
                </xsl:if>
              </ns19:perspectiva>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:enMora">
              <ns19:enMora>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:enMora"/>
              </ns19:enMora>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora">
              <ns19:mora>
                <ns19:dias>
                  <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:dias"/>
                </ns19:dias>
                <ns19:monto>
                  <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:monto"/>
                </ns19:monto>
                <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo">
                  <ns19:tipo>
                    <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:mora/ns19:tipo/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns19:tipo>
                </xsl:if>
              </ns19:mora>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:deteriorado">
              <ns19:deteriorado>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:deteriorado"/>
              </ns19:deteriorado>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:reserva">
              <ns19:reserva>
                <ns19:importeReserva>
                  <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:reserva/ns19:importeReserva"/>
                </ns19:importeReserva>
                <ns19:tipoReserva>
                  <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:reserva/ns19:tipoReserva"/>
                </ns19:tipoReserva>
              </ns19:reserva>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:requiereEnvioAutomatico">
              <ns19:requiereEnvioAutomatico>
                <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:requiereEnvioAutomatico"/>
              </ns19:requiereEnvioAutomatico>
            </xsl:if>
            <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR">
              <ns19:detalleSCR>
                <ns19:scrFuente>
                  <ns19:idCliente>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:idCliente"/>
                  </ns19:idCliente>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:idFacturador">
                    <ns19:idFacturador>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:idFacturador"/>
                    </ns19:idFacturador>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:scr">
                    <ns19:scr>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:scr"/>
                    </ns19:scr>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:estadoScr">
                    <ns19:estadoScr>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:estadoScr"/>
                    </ns19:estadoScr>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:observacion">
                    <ns19:observacion>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:observacion"/>
                    </ns19:observacion>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:fecha">
                    <ns19:fecha>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:fecha"/>
                    </ns19:fecha>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:usuarioModifico">
                    <ns19:usuarioModifico>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:usuarioModifico"/>
                    </ns19:usuarioModifico>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:usuarioValido">
                    <ns19:usuarioValido>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrFuente/ns19:usuarioValido"/>
                    </ns19:usuarioValido>
                  </xsl:if>
                </ns19:scrFuente>
                <ns19:scrReferencia>
                  <ns19:idCliente>
                    <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:idCliente"/>
                  </ns19:idCliente>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:idFacturador">
                    <ns19:idFacturador>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:idFacturador"/>
                    </ns19:idFacturador>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:scr">
                    <ns19:scr>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:scr"/>
                    </ns19:scr>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:estadoScr">
                    <ns19:estadoScr>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:estadoScr"/>
                    </ns19:estadoScr>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:observacion">
                    <ns19:observacion>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:observacion"/>
                    </ns19:observacion>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:fecha">
                    <ns19:fecha>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:fecha"/>
                    </ns19:fecha>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:usuarioModifico">
                    <ns19:usuarioModifico>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:usuarioModifico"/>
                    </ns19:usuarioModifico>
                  </xsl:if>
                  <xsl:if test="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:usuarioValido">
                    <ns19:usuarioValido>
                      <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:scrReferencia/ns19:usuarioValido"/>
                    </ns19:usuarioValido>
                  </xsl:if>
                </ns19:scrReferencia>
                <ns19:estatusComparacion>
                  <xsl:value-of select="$ConsultarOperacionById_OutputVariable.response/ns2:ConsultarOperacionResponse/ns2:Operacion/ns8:cliente/ns19:detalleSCR/ns19:estatusComparacion"/>
                </ns19:estatusComparacion>
              </ns19:detalleSCR>
            </xsl:if>
          </ns8:cliente>
        </xsl:if>
        <ns8:contrato>
          <ns18:instanciaProceso/>
          <xsl:for-each select="$consultarLineaCreditoByIdLineaCredito_OutputVariable.response/ns3:ConsultarLineaCreditoByIdLineaCreditoResponse/ns3:Operacion/ns8:contrato/ns18:LineaCredito">
            <ns18:LineaCredito>
              <xsl:if test="ns15:idLineaCredito">
                <ns15:idLineaCredito>
                  <xsl:value-of select="ns15:idLineaCredito"/>
                </ns15:idLineaCredito>
              </xsl:if>
              <xsl:for-each select="/ns0:ConsultarDesembolsosByIdResponse/ns0:ContratoDesembolso">
                <ns15:ContratoDesembolso>
                  <ns17:idDesembolso>
                    <xsl:value-of select="ns17:idDesembolso"/>
                  </ns17:idDesembolso>
                  <xsl:if test="ns17:idFacturador">
                    <ns17:idFacturador>
                      <xsl:value-of select="ns17:idFacturador"/>
                    </ns17:idFacturador>
                  </xsl:if>
                  <xsl:if test="ns17:producto">
                    <ns17:producto>
                      <ns5:idProducto>
                        <xsl:value-of select="ns17:producto/ns5:idProducto"/>
                      </ns5:idProducto>
                      <ns5:descripcion>
                        <xsl:value-of select="ns17:producto/ns5:descripcion"/>
                      </ns5:descripcion>
                      <ns5:descripcionCorta>
                        <xsl:value-of select="ns17:producto/ns5:descripcionCorta"/>
                      </ns5:descripcionCorta>
                      <ns5:fechaRegistro>
                        <xsl:value-of select="ns17:producto/ns5:fechaRegistro"/>
                      </ns5:fechaRegistro>
                      <ns5:codExterno>
                        <xsl:value-of select="ns17:producto/ns5:codExterno"/>
                      </ns5:codExterno>
                    </ns17:producto>
                  </xsl:if>
                  <xsl:if test="ns17:referencia">
                    <ns17:referencia>
                      <xsl:value-of select="ns17:referencia"/>
                    </ns17:referencia>
                  </xsl:if>
                  <xsl:for-each select="ns17:monto">
                    <ns17:monto>
                      <ns10:tipo>
                        <xsl:if test="ns10:tipo/ns16:Id">
                          <ns16:Id>
                            <xsl:value-of select="ns10:tipo/ns16:Id"/>
                          </ns16:Id>
                        </xsl:if>
                        <xsl:if test="ns10:tipo/ns16:Descripcion">
                          <ns16:Descripcion>
                            <xsl:value-of select="ns10:tipo/ns16:Descripcion"/>
                          </ns16:Descripcion>
                        </xsl:if>
                        <xsl:if test="ns10:tipo/ns16:DescripcionCorta">
                          <ns16:DescripcionCorta>
                            <xsl:value-of select="ns10:tipo/ns16:DescripcionCorta"/>
                          </ns16:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns10:tipo/ns16:estatus">
                          <ns16:estatus>
                            <xsl:value-of select="ns10:tipo/ns16:estatus"/>
                          </ns16:estatus>
                        </xsl:if>
                        <xsl:if test="ns10:tipo/ns16:codigoExterno">
                          <ns16:codigoExterno>
                            <xsl:value-of select="ns10:tipo/ns16:codigoExterno"/>
                          </ns16:codigoExterno>
                        </xsl:if>
                      </ns10:tipo>
                      <xsl:if test="ns10:importe">
                        <ns10:importe>
                          <xsl:value-of select="ns10:importe"/>
                        </ns10:importe>
                      </xsl:if>
                      <xsl:if test="ns10:moneda">
                        <ns10:moneda>
                          <xsl:if test="ns10:moneda/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns10:moneda/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns10:moneda/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns10:moneda/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns10:moneda/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns10:moneda/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns10:moneda/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns10:moneda/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns10:moneda/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns10:moneda/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns10:moneda>
                      </xsl:if>
                    </ns17:monto>
                  </xsl:for-each>
                  <ns17:estado>
                    <xsl:if test="ns17:estado/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns17:estado/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns17:estado/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns17:estado/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns17:estado/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns17:estado/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns17:estado/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns17:estado/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns17:estado/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns17:estado/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns17:estado>
                  <xsl:if test="ns17:programado">
                    <ns17:programado>
                      <xsl:value-of select="ns17:programado"/>
                    </ns17:programado>
                  </xsl:if>
                  <xsl:if test="ns17:fechaEstimadaDesembolsar">
                    <ns17:fechaEstimadaDesembolsar>
                      <xsl:value-of select="ns17:fechaEstimadaDesembolsar"/>
                    </ns17:fechaEstimadaDesembolsar>
                  </xsl:if>
                  <xsl:if test="ns17:fechaEfectiva">
                    <ns17:fechaEfectiva>
                      <xsl:value-of select="ns17:fechaEfectiva"/>
                    </ns17:fechaEfectiva>
                  </xsl:if>
                  <xsl:if test="ns17:fechaAsignacion">
                    <ns17:fechaAsignacion>
                      <xsl:value-of select="ns17:fechaAsignacion"/>
                    </ns17:fechaAsignacion>
                  </xsl:if>
                  <xsl:if test="ns17:fechaRegistro">
                    <ns17:fechaRegistro>
                      <xsl:value-of select="ns17:fechaRegistro"/>
                    </ns17:fechaRegistro>
                  </xsl:if>
                  <xsl:if test="ns17:estatus">
                    <ns17:estatus>
                      <xsl:value-of select="ns17:estatus"/>
                    </ns17:estatus>
                  </xsl:if>
                  <xsl:if test="ns17:condicionesFinancieras">
                    <ns17:condicionesFinancieras>
                      <ns17:tasa>
                        <ns17:tipo>
                          <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:tipo/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns17:tipo>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:fija">
                          <ns17:fija>
                            <ns17:valor>
                              <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:fija/ns17:valor"/>
                            </ns17:valor>
                          </ns17:fija>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:variable">
                          <ns17:variable>
                            <ns17:tasaReferencia>
                              <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:Id">
                                <ns16:Id>
                                  <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:Id"/>
                                </ns16:Id>
                              </xsl:if>
                              <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:Descripcion">
                                <ns16:Descripcion>
                                  <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:Descripcion"/>
                                </ns16:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:DescripcionCorta">
                                <ns16:DescripcionCorta>
                                  <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:DescripcionCorta"/>
                                </ns16:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:estatus">
                                <ns16:estatus>
                                  <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:estatus"/>
                                </ns16:estatus>
                              </xsl:if>
                              <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:codigoExterno">
                                <ns16:codigoExterno>
                                  <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns16:codigoExterno"/>
                                </ns16:codigoExterno>
                              </xsl:if>
                              <ns17:valor>
                                <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:tasaReferencia/ns17:valor"/>
                              </ns17:valor>
                            </ns17:tasaReferencia>
                            <ns17:spread>
                              <ns17:valor>
                                <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:variable/ns17:spread/ns17:valor"/>
                              </ns17:valor>
                            </ns17:spread>
                          </ns17:variable>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:tasa/ns17:especial">
                          <ns17:especial>
                            <ns17:valor>
                              <xsl:value-of select="ns17:condicionesFinancieras/ns17:tasa/ns17:especial/ns17:valor"/>
                            </ns17:valor>
                          </ns17:especial>
                        </xsl:if>
                      </ns17:tasa>
                      <ns17:fondo>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:fondo/ns16:Id">
                          <ns16:Id>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:fondo/ns16:Id"/>
                          </ns16:Id>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:fondo/ns16:Descripcion">
                          <ns16:Descripcion>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:fondo/ns16:Descripcion"/>
                          </ns16:Descripcion>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:fondo/ns16:DescripcionCorta">
                          <ns16:DescripcionCorta>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:fondo/ns16:DescripcionCorta"/>
                          </ns16:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:fondo/ns16:estatus">
                          <ns16:estatus>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:fondo/ns16:estatus"/>
                          </ns16:estatus>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:fondo/ns16:codigoExterno">
                          <ns16:codigoExterno>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:fondo/ns16:codigoExterno"/>
                          </ns16:codigoExterno>
                        </xsl:if>
                      </ns17:fondo>
                      <ns17:baseCalculo>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:Id">
                          <ns16:Id>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:Id"/>
                          </ns16:Id>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:Descripcion">
                          <ns16:Descripcion>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:Descripcion"/>
                          </ns16:Descripcion>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:DescripcionCorta">
                          <ns16:DescripcionCorta>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:DescripcionCorta"/>
                          </ns16:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:estatus">
                          <ns16:estatus>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:estatus"/>
                          </ns16:estatus>
                        </xsl:if>
                        <xsl:if test="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:codigoExterno">
                          <ns16:codigoExterno>
                            <xsl:value-of select="ns17:condicionesFinancieras/ns17:baseCalculo/ns16:codigoExterno"/>
                          </ns16:codigoExterno>
                        </xsl:if>
                      </ns17:baseCalculo>
                    </ns17:condicionesFinancieras>
                  </xsl:if>
                  <xsl:if test="ns17:idLinea">
                    <ns17:idLinea>
                      <xsl:value-of select="ns17:idLinea"/>
                    </ns17:idLinea>
                  </xsl:if>
                  <xsl:if test="ns17:Programa">
                    <ns17:Programa>
                      <ns13:LineaFinanciera>
                        <xsl:if test="ns17:Programa/ns13:LineaFinanciera/ns16:Id">
                          <ns16:Id>
                            <xsl:value-of select="ns17:Programa/ns13:LineaFinanciera/ns16:Id"/>
                          </ns16:Id>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:LineaFinanciera/ns16:Descripcion">
                          <ns16:Descripcion>
                            <xsl:value-of select="ns17:Programa/ns13:LineaFinanciera/ns16:Descripcion"/>
                          </ns16:Descripcion>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:LineaFinanciera/ns16:DescripcionCorta">
                          <ns16:DescripcionCorta>
                            <xsl:value-of select="ns17:Programa/ns13:LineaFinanciera/ns16:DescripcionCorta"/>
                          </ns16:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:LineaFinanciera/ns16:estatus">
                          <ns16:estatus>
                            <xsl:value-of select="ns17:Programa/ns13:LineaFinanciera/ns16:estatus"/>
                          </ns16:estatus>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:LineaFinanciera/ns16:codigoExterno">
                          <ns16:codigoExterno>
                            <xsl:value-of select="ns17:Programa/ns13:LineaFinanciera/ns16:codigoExterno"/>
                          </ns16:codigoExterno>
                        </xsl:if>
                      </ns13:LineaFinanciera>
                      <ns13:DestinoFinanciamiento>
                        <xsl:if test="ns17:Programa/ns13:DestinoFinanciamiento/ns16:Id">
                          <ns16:Id>
                            <xsl:value-of select="ns17:Programa/ns13:DestinoFinanciamiento/ns16:Id"/>
                          </ns16:Id>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:DestinoFinanciamiento/ns16:Descripcion">
                          <ns16:Descripcion>
                            <xsl:value-of select="ns17:Programa/ns13:DestinoFinanciamiento/ns16:Descripcion"/>
                          </ns16:Descripcion>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:DestinoFinanciamiento/ns16:DescripcionCorta">
                          <ns16:DescripcionCorta>
                            <xsl:value-of select="ns17:Programa/ns13:DestinoFinanciamiento/ns16:DescripcionCorta"/>
                          </ns16:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:DestinoFinanciamiento/ns16:estatus">
                          <ns16:estatus>
                            <xsl:value-of select="ns17:Programa/ns13:DestinoFinanciamiento/ns16:estatus"/>
                          </ns16:estatus>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:DestinoFinanciamiento/ns16:codigoExterno">
                          <ns16:codigoExterno>
                            <xsl:value-of select="ns17:Programa/ns13:DestinoFinanciamiento/ns16:codigoExterno"/>
                          </ns16:codigoExterno>
                        </xsl:if>
                      </ns13:DestinoFinanciamiento>
                      <ns13:ModalidadFinanciamiento>
                        <xsl:if test="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:Id">
                          <ns16:Id>
                            <xsl:value-of select="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:Id"/>
                          </ns16:Id>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:Descripcion">
                          <ns16:Descripcion>
                            <xsl:value-of select="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:Descripcion"/>
                          </ns16:Descripcion>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:DescripcionCorta">
                          <ns16:DescripcionCorta>
                            <xsl:value-of select="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:DescripcionCorta"/>
                          </ns16:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:estatus">
                          <ns16:estatus>
                            <xsl:value-of select="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:estatus"/>
                          </ns16:estatus>
                        </xsl:if>
                        <xsl:if test="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:codigoExterno">
                          <ns16:codigoExterno>
                            <xsl:value-of select="ns17:Programa/ns13:ModalidadFinanciamiento/ns16:codigoExterno"/>
                          </ns16:codigoExterno>
                        </xsl:if>
                      </ns13:ModalidadFinanciamiento>
                      <ns13:HerramientaCE>
                        <ns13:ActividadEconomica>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:ActividadEconomica/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns13:ActividadEconomica>
                        <ns13:EjeEstrategico>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:EjeEstrategico/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns13:EjeEstrategico>
                        <ns13:AreaFocalizacion>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns17:Programa/ns13:HerramientaCE/ns13:AreaFocalizacion/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns13:AreaFocalizacion>
                      </ns13:HerramientaCE>
                      <ns13:ClasificacionGeneral>
                        <ns13:SectorMercado>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorMercado/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns13:SectorMercado>
                        <ns13:SectorInstitucional>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns17:Programa/ns13:ClasificacionGeneral/ns13:SectorInstitucional/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns13:SectorInstitucional>
                      </ns13:ClasificacionGeneral>
                    </ns17:Programa>
                  </xsl:if>
                  <xsl:if test="ns17:Utilizacion">
                    <ns17:Utilizacion>
                      <ns15:idFuente>
                        <xsl:value-of select="ns17:Utilizacion/ns15:idFuente"/>
                      </ns15:idFuente>
                      <ns15:idLineaCredito>
                        <xsl:value-of select="ns17:Utilizacion/ns15:idLineaCredito"/>
                      </ns15:idLineaCredito>
                      <ns15:idLineaPasiva>
                        <xsl:value-of select="ns17:Utilizacion/ns15:idLineaPasiva"/>
                      </ns15:idLineaPasiva>
                      <ns15:codigoLineaPasiva>
                        <xsl:value-of select="ns17:Utilizacion/ns15:codigoLineaPasiva"/>
                      </ns15:codigoLineaPasiva>
                      <ns15:idFacturadorLineaPasiva>
                        <xsl:value-of select="ns17:Utilizacion/ns15:idFacturadorLineaPasiva"/>
                      </ns15:idFacturadorLineaPasiva>
                      <ns15:idFondo>
                        <xsl:value-of select="ns17:Utilizacion/ns15:idFondo"/>
                      </ns15:idFondo>
                      <ns15:Descripcion>
                        <xsl:value-of select="ns17:Utilizacion/ns15:Descripcion"/>
                      </ns15:Descripcion>
                      <ns15:FechaObtenido>
                        <xsl:value-of select="ns17:Utilizacion/ns15:FechaObtenido"/>
                      </ns15:FechaObtenido>
                      <ns15:MontoAsignado>
                        <xsl:value-of select="ns17:Utilizacion/ns15:MontoAsignado"/>
                      </ns15:MontoAsignado>
                      <ns15:montoDisponible>
                        <xsl:value-of select="ns17:Utilizacion/ns15:montoDisponible"/>
                      </ns15:montoDisponible>
                      <xsl:for-each select="ns17:Utilizacion/ns15:Monto">
                        <ns15:Monto>
                          <ns10:tipo>
                            <xsl:if test="ns10:tipo/ns16:Id">
                              <ns16:Id>
                                <xsl:value-of select="ns10:tipo/ns16:Id"/>
                              </ns16:Id>
                            </xsl:if>
                            <xsl:if test="ns10:tipo/ns16:Descripcion">
                              <ns16:Descripcion>
                                <xsl:value-of select="ns10:tipo/ns16:Descripcion"/>
                              </ns16:Descripcion>
                            </xsl:if>
                            <xsl:if test="ns10:tipo/ns16:DescripcionCorta">
                              <ns16:DescripcionCorta>
                                <xsl:value-of select="ns10:tipo/ns16:DescripcionCorta"/>
                              </ns16:DescripcionCorta>
                            </xsl:if>
                            <xsl:if test="ns10:tipo/ns16:estatus">
                              <ns16:estatus>
                                <xsl:value-of select="ns10:tipo/ns16:estatus"/>
                              </ns16:estatus>
                            </xsl:if>
                            <xsl:if test="ns10:tipo/ns16:codigoExterno">
                              <ns16:codigoExterno>
                                <xsl:value-of select="ns10:tipo/ns16:codigoExterno"/>
                              </ns16:codigoExterno>
                            </xsl:if>
                          </ns10:tipo>
                          <xsl:if test="ns10:importe">
                            <ns10:importe>
                              <xsl:value-of select="ns10:importe"/>
                            </ns10:importe>
                          </xsl:if>
                          <xsl:if test="ns10:moneda">
                            <ns10:moneda>
                              <xsl:if test="ns10:moneda/ns16:Id">
                                <ns16:Id>
                                  <xsl:value-of select="ns10:moneda/ns16:Id"/>
                                </ns16:Id>
                              </xsl:if>
                              <xsl:if test="ns10:moneda/ns16:Descripcion">
                                <ns16:Descripcion>
                                  <xsl:value-of select="ns10:moneda/ns16:Descripcion"/>
                                </ns16:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns10:moneda/ns16:DescripcionCorta">
                                <ns16:DescripcionCorta>
                                  <xsl:value-of select="ns10:moneda/ns16:DescripcionCorta"/>
                                </ns16:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns10:moneda/ns16:estatus">
                                <ns16:estatus>
                                  <xsl:value-of select="ns10:moneda/ns16:estatus"/>
                                </ns16:estatus>
                              </xsl:if>
                              <xsl:if test="ns10:moneda/ns16:codigoExterno">
                                <ns16:codigoExterno>
                                  <xsl:value-of select="ns10:moneda/ns16:codigoExterno"/>
                                </ns16:codigoExterno>
                              </xsl:if>
                            </ns10:moneda>
                          </xsl:if>
                        </ns15:Monto>
                      </xsl:for-each>
                      <ns15:NumeroAsignacion>
                        <xsl:value-of select="ns17:Utilizacion/ns15:NumeroAsignacion"/>
                      </ns15:NumeroAsignacion>
                      <ns15:Comentario>
                        <xsl:value-of select="ns17:Utilizacion/ns15:Comentario"/>
                      </ns15:Comentario>
                      <ns15:idContrato>
                        <xsl:value-of select="ns17:Utilizacion/ns15:idContrato"/>
                      </ns15:idContrato>
                      <ns15:FechaRegistro>
                        <xsl:value-of select="ns17:Utilizacion/ns15:FechaRegistro"/>
                      </ns15:FechaRegistro>
                      <ns15:Estado>
                        <xsl:value-of select="ns17:Utilizacion/ns15:Estado"/>
                      </ns15:Estado>
                    </ns17:Utilizacion>
                  </xsl:if>
                  <xsl:for-each select="ns17:Cargo">
                    <ns17:Cargo>
                      <xsl:if test="ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                      <ns17:Monto>
                        <ns10:tipo>
                          <xsl:if test="ns17:Monto/ns10:tipo/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns17:Monto/ns10:tipo/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns17:Monto/ns10:tipo/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns17:Monto/ns10:tipo/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns17:Monto/ns10:tipo/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns17:Monto/ns10:tipo/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns17:Monto/ns10:tipo/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns17:Monto/ns10:tipo/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns17:Monto/ns10:tipo/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns17:Monto/ns10:tipo/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns10:tipo>
                        <xsl:if test="ns17:Monto/ns10:importe">
                          <ns10:importe>
                            <xsl:value-of select="ns17:Monto/ns10:importe"/>
                          </ns10:importe>
                        </xsl:if>
                        <xsl:if test="ns17:Monto/ns10:moneda">
                          <ns10:moneda>
                            <xsl:if test="ns17:Monto/ns10:moneda/ns16:Id">
                              <ns16:Id>
                                <xsl:value-of select="ns17:Monto/ns10:moneda/ns16:Id"/>
                              </ns16:Id>
                            </xsl:if>
                            <xsl:if test="ns17:Monto/ns10:moneda/ns16:Descripcion">
                              <ns16:Descripcion>
                                <xsl:value-of select="ns17:Monto/ns10:moneda/ns16:Descripcion"/>
                              </ns16:Descripcion>
                            </xsl:if>
                            <xsl:if test="ns17:Monto/ns10:moneda/ns16:DescripcionCorta">
                              <ns16:DescripcionCorta>
                                <xsl:value-of select="ns17:Monto/ns10:moneda/ns16:DescripcionCorta"/>
                              </ns16:DescripcionCorta>
                            </xsl:if>
                            <xsl:if test="ns17:Monto/ns10:moneda/ns16:estatus">
                              <ns16:estatus>
                                <xsl:value-of select="ns17:Monto/ns10:moneda/ns16:estatus"/>
                              </ns16:estatus>
                            </xsl:if>
                            <xsl:if test="ns17:Monto/ns10:moneda/ns16:codigoExterno">
                              <ns16:codigoExterno>
                                <xsl:value-of select="ns17:Monto/ns10:moneda/ns16:codigoExterno"/>
                              </ns16:codigoExterno>
                            </xsl:if>
                          </ns10:moneda>
                        </xsl:if>
                      </ns17:Monto>
                      <ns17:FechaRegistro>
                        <xsl:value-of select="ns17:FechaRegistro"/>
                      </ns17:FechaRegistro>
                      <ns17:Status>
                        <xsl:value-of select="ns17:Status"/>
                      </ns17:Status>
                      <ns17:SoloLectura>
                        <xsl:value-of select="ns17:SoloLectura"/>
                      </ns17:SoloLectura>
                    </ns17:Cargo>
                  </xsl:for-each>
                  <xsl:for-each select="ns17:Comision">
                    <ns17:Comision>
                      <ns14:idComision>
                        <xsl:value-of select="ns14:idComision"/>
                      </ns14:idComision>
                      <ns14:idOperacion>
                        <xsl:value-of select="ns14:idOperacion"/>
                      </ns14:idOperacion>
                      <xsl:if test="ns14:nombre">
                        <ns14:nombre>
                          <xsl:value-of select="ns14:nombre"/>
                        </ns14:nombre>
                      </xsl:if>
                      <xsl:if test="ns14:descripcion">
                        <ns14:descripcion>
                          <xsl:value-of select="ns14:descripcion"/>
                        </ns14:descripcion>
                      </xsl:if>
                      <xsl:if test="ns14:tipoComision">
                        <ns14:tipoComision>
                          <xsl:if test="ns14:tipoComision/ns14:idCatComision">
                            <ns14:idCatComision>
                              <xsl:value-of select="ns14:tipoComision/ns14:idCatComision"/>
                            </ns14:idCatComision>
                          </xsl:if>
                          <ns14:descripcion>
                            <xsl:value-of select="ns14:tipoComision/ns14:descripcion"/>
                          </ns14:descripcion>
                          <ns14:descripcionCorta>
                            <xsl:value-of select="ns14:tipoComision/ns14:descripcionCorta"/>
                          </ns14:descripcionCorta>
                          <xsl:if test="ns14:tipoComision/ns14:idTipoComision">
                            <ns14:idTipoComision>
                              <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:Id">
                                <ns16:Id>
                                  <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:Id"/>
                                </ns16:Id>
                              </xsl:if>
                              <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:Descripcion">
                                <ns16:Descripcion>
                                  <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:Descripcion"/>
                                </ns16:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:DescripcionCorta">
                                <ns16:DescripcionCorta>
                                  <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:DescripcionCorta"/>
                                </ns16:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:estatus">
                                <ns16:estatus>
                                  <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:estatus"/>
                                </ns16:estatus>
                              </xsl:if>
                              <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:codigoExterno">
                                <ns16:codigoExterno>
                                  <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:codigoExterno"/>
                                </ns16:codigoExterno>
                              </xsl:if>
                            </ns14:idTipoComision>
                          </xsl:if>
                          <ns14:esEditableEnFormalizacion>
                            <xsl:value-of select="ns14:tipoComision/ns14:esEditableEnFormalizacion"/>
                          </ns14:esEditableEnFormalizacion>
                          <ns14:requiereValidarCOFI>
                            <xsl:value-of select="ns14:tipoComision/ns14:requiereValidarCOFI"/>
                          </ns14:requiereValidarCOFI>
                          <ns14:sePuedeDispensar>
                            <xsl:value-of select="ns14:tipoComision/ns14:sePuedeDispensar"/>
                          </ns14:sePuedeDispensar>
                          <ns14:seRegistraEnFlexCube>
                            <xsl:value-of select="ns14:tipoComision/ns14:seRegistraEnFlexCube"/>
                          </ns14:seRegistraEnFlexCube>
                          <ns14:esPlantilla>
                            <xsl:value-of select="ns14:tipoComision/ns14:esPlantilla"/>
                          </ns14:esPlantilla>
                          <ns14:idComisionPrecarga>
                            <xsl:value-of select="ns14:tipoComision/ns14:idComisionPrecarga"/>
                          </ns14:idComisionPrecarga>
                          <ns14:fechaRegistro>
                            <xsl:value-of select="ns14:tipoComision/ns14:fechaRegistro"/>
                          </ns14:fechaRegistro>
                          <ns14:estado>
                            <xsl:value-of select="ns14:tipoComision/ns14:estado"/>
                          </ns14:estado>
                          <ns14:codigoExterno>
                            <xsl:value-of select="ns14:tipoComision/ns14:codigoExterno"/>
                          </ns14:codigoExterno>
                        </ns14:tipoComision>
                      </xsl:if>
                      <xsl:if test="ns14:moneda">
                        <ns14:moneda>
                          <xsl:if test="ns14:moneda/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns14:moneda/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns14:moneda/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns14:moneda/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns14:moneda/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns14:moneda/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns14:moneda/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns14:moneda/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns14:moneda/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns14:moneda/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns14:moneda>
                      </xsl:if>
                      <xsl:if test="ns14:montoComision">
                        <ns14:montoComision>
                          <xsl:value-of select="ns14:montoComision"/>
                        </ns14:montoComision>
                      </xsl:if>
                      <xsl:if test="ns14:montoBase">
                        <ns14:montoBase>
                          <xsl:if test="ns14:montoBase/ns14:idMontoBase">
                            <ns14:idMontoBase>
                              <xsl:value-of select="ns14:montoBase/ns14:idMontoBase"/>
                            </ns14:idMontoBase>
                          </xsl:if>
                          <xsl:if test="ns14:montoBase/ns14:valorMontoBase">
                            <ns14:valorMontoBase>
                              <xsl:value-of select="ns14:montoBase/ns14:valorMontoBase"/>
                            </ns14:valorMontoBase>
                          </xsl:if>
                          <xsl:if test="ns14:montoBase/ns14:porcentajeMontoBase">
                            <ns14:porcentajeMontoBase>
                              <xsl:value-of select="ns14:montoBase/ns14:porcentajeMontoBase"/>
                            </ns14:porcentajeMontoBase>
                          </xsl:if>
                          <xsl:if test="ns14:montoBase/ns14:descripcionMontoBase">
                            <ns14:descripcionMontoBase>
                              <xsl:value-of select="ns14:montoBase/ns14:descripcionMontoBase"/>
                            </ns14:descripcionMontoBase>
                          </xsl:if>
                        </ns14:montoBase>
                      </xsl:if>
                      <xsl:if test="ns14:fechaValor">
                        <ns14:fechaValor>
                          <xsl:value-of select="ns14:fechaValor"/>
                        </ns14:fechaValor>
                      </xsl:if>
                      <xsl:if test="ns14:fechaVencimiento">
                        <ns14:fechaVencimiento>
                          <xsl:value-of select="ns14:fechaVencimiento"/>
                        </ns14:fechaVencimiento>
                      </xsl:if>
                      <xsl:if test="ns14:fechaInicioCapital">
                        <ns14:fechaInicioCapital>
                          <xsl:value-of select="ns14:fechaInicioCapital"/>
                        </ns14:fechaInicioCapital>
                      </xsl:if>
                      <xsl:if test="ns14:fechaInicioComision">
                        <ns14:fechaInicioComision>
                          <xsl:value-of select="ns14:fechaInicioComision"/>
                        </ns14:fechaInicioComision>
                      </xsl:if>
                      <xsl:if test="ns14:tipoFrecuencia">
                        <ns14:tipoFrecuencia>
                          <xsl:if test="ns14:tipoFrecuencia/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns14:tipoFrecuencia/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns14:tipoFrecuencia/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns14:tipoFrecuencia/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns14:tipoFrecuencia/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns14:tipoFrecuencia/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns14:tipoFrecuencia/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns14:tipoFrecuencia/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns14:tipoFrecuencia/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns14:tipoFrecuencia/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns14:tipoFrecuencia>
                      </xsl:if>
                      <xsl:if test="ns14:frecuenciaPago">
                        <ns14:frecuenciaPago>
                          <xsl:value-of select="ns14:frecuenciaPago"/>
                        </ns14:frecuenciaPago>
                      </xsl:if>
                      <xsl:if test="ns14:fondo">
                        <ns14:fondo>
                          <xsl:value-of select="ns14:fondo"/>
                        </ns14:fondo>
                      </xsl:if>
                      <xsl:if test="ns14:comisionCompartida">
                        <ns14:comisionCompartida>
                          <xsl:value-of select="ns14:comisionCompartida"/>
                        </ns14:comisionCompartida>
                      </xsl:if>
                      <xsl:if test="ns14:codigoDesembolso">
                        <ns14:codigoDesembolso>
                          <xsl:value-of select="ns14:codigoDesembolso"/>
                        </ns14:codigoDesembolso>
                      </xsl:if>
                      <xsl:if test="ns14:numeroTesoreria">
                        <ns14:numeroTesoreria>
                          <xsl:value-of select="ns14:numeroTesoreria"/>
                        </ns14:numeroTesoreria>
                      </xsl:if>
                      <xsl:if test="ns14:codigoContrato">
                        <ns14:codigoContrato>
                          <xsl:value-of select="ns14:codigoContrato"/>
                        </ns14:codigoContrato>
                      </xsl:if>
                      <xsl:if test="ns14:momentoCobro">
                        <ns14:momentoCobro>
                          <xsl:if test="ns14:momentoCobro/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns14:momentoCobro/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns14:momentoCobro/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns14:momentoCobro/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns14:momentoCobro/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns14:momentoCobro/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns14:momentoCobro/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns14:momentoCobro/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns14:momentoCobro/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns14:momentoCobro/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns14:momentoCobro>
                      </xsl:if>
                      <xsl:if test="ns14:tipoTasa">
                        <ns14:tipoTasa>
                          <xsl:if test="ns14:tipoTasa/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns14:tipoTasa/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns14:tipoTasa/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns14:tipoTasa/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns14:tipoTasa/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns14:tipoTasa/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns14:tipoTasa/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns14:tipoTasa/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns14:tipoTasa/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns14:tipoTasa/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns14:tipoTasa>
                      </xsl:if>
                      <xsl:if test="ns14:baseCalculo">
                        <ns14:baseCalculo>
                          <xsl:if test="ns14:baseCalculo/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns14:baseCalculo/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns14:baseCalculo/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns14:baseCalculo/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns14:baseCalculo/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns14:baseCalculo/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns14:baseCalculo/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns14:baseCalculo/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns14:baseCalculo/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns14:baseCalculo/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns14:baseCalculo>
                      </xsl:if>
                      <xsl:if test="ns14:responsableComision">
                        <ns14:responsableComision>
                          <xsl:value-of select="ns14:responsableComision"/>
                        </ns14:responsableComision>
                      </xsl:if>
                      <ns14:estadoTCC>
                        <xsl:if test="ns14:estadoTCC/ns20:id">
                          <ns20:id>
                            <xsl:value-of select="ns14:estadoTCC/ns20:id"/>
                          </ns20:id>
                        </xsl:if>
                        <xsl:if test="ns14:estadoTCC/ns20:descripcion">
                          <ns20:descripcion>
                            <xsl:value-of select="ns14:estadoTCC/ns20:descripcion"/>
                          </ns20:descripcion>
                        </xsl:if>
                        <xsl:if test="ns14:estadoTCC/ns20:descripcionCorta">
                          <ns20:descripcionCorta>
                            <xsl:value-of select="ns14:estadoTCC/ns20:descripcionCorta"/>
                          </ns20:descripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns14:estadoTCC/ns20:estatus">
                          <ns20:estatus>
                            <xsl:value-of select="ns14:estadoTCC/ns20:estatus"/>
                          </ns20:estatus>
                        </xsl:if>
                        <xsl:if test="ns14:estadoTCC/ns20:codigoExterno">
                          <ns20:codigoExterno>
                            <xsl:value-of select="ns14:estadoTCC/ns20:codigoExterno"/>
                          </ns20:codigoExterno>
                        </xsl:if>
                        <xsl:if test="ns14:estadoTCC/ns20:esSubEstado">
                          <ns20:esSubEstado>
                            <xsl:value-of select="ns14:estadoTCC/ns20:esSubEstado"/>
                          </ns20:esSubEstado>
                        </xsl:if>
                      </ns14:estadoTCC>
                      <ns14:subEstadoTCC>
                        <xsl:if test="ns14:subEstadoTCC/ns20:id">
                          <ns20:id>
                            <xsl:value-of select="ns14:subEstadoTCC/ns20:id"/>
                          </ns20:id>
                        </xsl:if>
                        <xsl:if test="ns14:subEstadoTCC/ns20:descripcion">
                          <ns20:descripcion>
                            <xsl:value-of select="ns14:subEstadoTCC/ns20:descripcion"/>
                          </ns20:descripcion>
                        </xsl:if>
                        <xsl:if test="ns14:subEstadoTCC/ns20:descripcionCorta">
                          <ns20:descripcionCorta>
                            <xsl:value-of select="ns14:subEstadoTCC/ns20:descripcionCorta"/>
                          </ns20:descripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns14:subEstadoTCC/ns20:estatus">
                          <ns20:estatus>
                            <xsl:value-of select="ns14:subEstadoTCC/ns20:estatus"/>
                          </ns20:estatus>
                        </xsl:if>
                        <xsl:if test="ns14:subEstadoTCC/ns20:codigoExterno">
                          <ns20:codigoExterno>
                            <xsl:value-of select="ns14:subEstadoTCC/ns20:codigoExterno"/>
                          </ns20:codigoExterno>
                        </xsl:if>
                        <xsl:if test="ns14:subEstadoTCC/ns20:esSubEstado">
                          <ns20:esSubEstado>
                            <xsl:value-of select="ns14:subEstadoTCC/ns20:esSubEstado"/>
                          </ns20:esSubEstado>
                        </xsl:if>
                      </ns14:subEstadoTCC>
                      <ns14:fechaRegistro>
                        <xsl:value-of select="ns14:fechaRegistro"/>
                      </ns14:fechaRegistro>
                      <ns14:estado>
                        <xsl:value-of select="ns14:estado"/>
                      </ns14:estado>
                      <ns14:comisionEnmendada>
                        <xsl:value-of select="ns14:comisionEnmendada"/>
                      </ns14:comisionEnmendada>
                      <ns14:fechaEnmienda>
                        <xsl:value-of select="ns14:fechaEnmienda"/>
                      </ns14:fechaEnmienda>
                      <xsl:if test="ns14:banSugerida">
                        <ns14:banSugerida>
                          <xsl:value-of select="ns14:banSugerida"/>
                        </ns14:banSugerida>
                      </xsl:if>
                      <xsl:if test="ns14:numeroCuentaCliente">
                        <ns14:numeroCuentaCliente>
                          <xsl:value-of select="ns14:numeroCuentaCliente"/>
                        </ns14:numeroCuentaCliente>
                      </xsl:if>
                      <xsl:if test="ns14:observaciones">
                        <ns14:observaciones>
                          <xsl:value-of select="ns14:observaciones"/>
                        </ns14:observaciones>
                      </xsl:if>
                      <xsl:for-each select="ns14:configAtributo">
                        <ns14:configAtributo>
                          <xsl:if test="ns20:id">
                            <ns20:id>
                              <xsl:value-of select="ns20:id"/>
                            </ns20:id>
                          </xsl:if>
                          <ns20:columna>
                            <xsl:value-of select="ns20:columna"/>
                          </ns20:columna>
                          <xsl:if test="ns20:ordenamiento">
                            <ns20:ordenamiento>
                              <xsl:value-of select="ns20:ordenamiento"/>
                            </ns20:ordenamiento>
                          </xsl:if>
                          <xsl:if test="ns20:soloLectura">
                            <ns20:soloLectura>
                              <xsl:value-of select="ns20:soloLectura"/>
                            </ns20:soloLectura>
                          </xsl:if>
                          <xsl:if test="ns20:esObligatorio">
                            <ns20:esObligatorio>
                              <xsl:value-of select="ns20:esObligatorio"/>
                            </ns20:esObligatorio>
                          </xsl:if>
                          <xsl:if test="ns20:etiqueta">
                            <ns20:etiqueta>
                              <xsl:value-of select="ns20:etiqueta"/>
                            </ns20:etiqueta>
                          </xsl:if>
                          <xsl:for-each select="ns20:valor">
                            <ns20:valor>
                              <xsl:value-of select="."/>
                            </ns20:valor>
                          </xsl:for-each>
                          <xsl:if test="ns20:tipoValor">
                            <ns20:tipoValor>
                              <xsl:value-of select="ns20:tipoValor"/>
                            </ns20:tipoValor>
                          </xsl:if>
                          <xsl:for-each select="ns20:catalogo">
                            <ns20:catalogo>
                              <xsl:if test="ns16:Id">
                                <ns16:Id>
                                  <xsl:value-of select="ns16:Id"/>
                                </ns16:Id>
                              </xsl:if>
                              <xsl:if test="ns16:Descripcion">
                                <ns16:Descripcion>
                                  <xsl:value-of select="ns16:Descripcion"/>
                                </ns16:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns16:DescripcionCorta">
                                <ns16:DescripcionCorta>
                                  <xsl:value-of select="ns16:DescripcionCorta"/>
                                </ns16:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns16:estatus">
                                <ns16:estatus>
                                  <xsl:value-of select="ns16:estatus"/>
                                </ns16:estatus>
                              </xsl:if>
                              <xsl:if test="ns16:codigoExterno">
                                <ns16:codigoExterno>
                                  <xsl:value-of select="ns16:codigoExterno"/>
                                </ns16:codigoExterno>
                              </xsl:if>
                            </ns20:catalogo>
                          </xsl:for-each>
                        </ns14:configAtributo>
                      </xsl:for-each>
                      <xsl:if test="ns14:Accion">
                        <ns14:Accion>
                          <xsl:value-of select="ns14:Accion"/>
                        </ns14:Accion>
                      </xsl:if>
                    </ns17:Comision>
                  </xsl:for-each>
                </ns15:ContratoDesembolso>
              </xsl:for-each>
              <xsl:if test="ns15:idContrato">
                <ns15:idContrato>
                  <xsl:value-of select="ns15:idContrato"/>
                </ns15:idContrato>
              </xsl:if>
              <ns15:NumeroLineaCredito>
                <xsl:value-of select="ns15:NumeroLineaCredito"/>
              </ns15:NumeroLineaCredito>
              <ns15:Descripcion>
                <xsl:value-of select="ns15:Descripcion"/>
              </ns15:Descripcion>
              <xsl:if test="ns15:Flexcube">
                <ns15:Flexcube>
                  <xsl:if test="ns15:Flexcube/ns15:id">
                    <ns15:id>
                      <xsl:value-of select="ns15:Flexcube/ns15:id"/>
                    </ns15:id>
                  </xsl:if>
                  <xsl:if test="ns15:Flexcube/ns15:idProductoFlexcube">
                    <ns15:idProductoFlexcube>
                      <xsl:value-of select="ns15:Flexcube/ns15:idProductoFlexcube"/>
                    </ns15:idProductoFlexcube>
                  </xsl:if>
                  <xsl:if test="ns15:Flexcube/ns15:idFlexcubePasivo">
                    <ns15:idFlexcubePasivo>
                      <xsl:value-of select="ns15:Flexcube/ns15:idFlexcubePasivo"/>
                    </ns15:idFlexcubePasivo>
                  </xsl:if>
                </ns15:Flexcube>
              </xsl:if>
              <ns15:Fondo>
                <xsl:value-of select="ns15:Fondo"/>
              </ns15:Fondo>
              <ns15:MontoLinea>
                <xsl:value-of select="ns15:MontoLinea"/>
              </ns15:MontoLinea>
              <xsl:if test="ns15:EsRevolvente">
                <ns15:EsRevolvente>
                  <xsl:value-of select="ns15:EsRevolvente"/>
                </ns15:EsRevolvente>
              </xsl:if>
              <ns15:TratamientoDiasFeriados>
                <xsl:value-of select="ns15:TratamientoDiasFeriados"/>
              </ns15:TratamientoDiasFeriados>
              <ns15:FechaValor>
                <xsl:value-of select="ns15:FechaValor"/>
              </ns15:FechaValor>
              <ns15:FechaVencimiento>
                <xsl:value-of select="ns15:FechaVencimiento"/>
              </ns15:FechaVencimiento>
              <ns15:CodigoPantallaLimite>
                <xsl:value-of select="ns15:CodigoPantallaLimite"/>
              </ns15:CodigoPantallaLimite>
              <ns15:CodigoSerialLimite>
                <xsl:value-of select="ns15:CodigoSerialLimite"/>
              </ns15:CodigoSerialLimite>
              <ns15:CodigoAsignacion>
                <xsl:value-of select="ns15:CodigoAsignacion"/>
              </ns15:CodigoAsignacion>
              <ns15:DescripcionAsignacion>
                <xsl:value-of select="ns15:DescripcionAsignacion"/>
              </ns15:DescripcionAsignacion>
              <xsl:if test="ns15:CondicionEspecial">
                <ns15:CondicionEspecial>
                  <xsl:value-of select="ns15:CondicionEspecial"/>
                </ns15:CondicionEspecial>
              </xsl:if>
              <ns15:FechaRegistro>
                <xsl:value-of select="ns15:FechaRegistro"/>
              </ns15:FechaRegistro>
              <ns15:FechaMaximaDesembolso>
                <xsl:value-of select="ns15:FechaMaximaDesembolso"/>
              </ns15:FechaMaximaDesembolso>
              <xsl:if test="ns15:Estado">
                <ns15:Estado>
                  <xsl:value-of select="ns15:Estado"/>
                </ns15:Estado>
              </xsl:if>
              <ns15:descCondEspecial>
                <xsl:value-of select="ns15:descCondEspecial"/>
              </ns15:descCondEspecial>
              <ns15:frecuenciaGracia>
                <xsl:value-of select="ns15:frecuenciaGracia"/>
              </ns15:frecuenciaGracia>
              <ns15:plazoGracia>
                <xsl:value-of select="ns15:plazoGracia"/>
              </ns15:plazoGracia>
              <ns15:frecuenciaFinanciamiento>
                <xsl:value-of select="ns15:frecuenciaFinanciamiento"/>
              </ns15:frecuenciaFinanciamiento>
              <ns15:plazoFinanciamiento>
                <xsl:value-of select="ns15:plazoFinanciamiento"/>
              </ns15:plazoFinanciamiento>
              <ns15:recursosExternos>
                <xsl:value-of select="ns15:recursosExternos"/>
              </ns15:recursosExternos>
              <ns15:tasaMinima>
                <xsl:if test="ns15:tasaMinima/@xsi:nil">
                  <xsl:attribute name="nil" namespace="http://www.w3.org/2001/XMLSchema-instance">
                    <xsl:value-of select="ns15:tasaMinima/@xsi:nil"/>
                  </xsl:attribute>
                </xsl:if>
                <xsl:value-of select="ns15:tasaMinima"/>
              </ns15:tasaMinima>
              <ns15:tasaMaxima>
                <xsl:if test="ns15:tasaMaxima/@xsi:nil">
                  <xsl:attribute name="nil" namespace="http://www.w3.org/2001/XMLSchema-instance">
                    <xsl:value-of select="ns15:tasaMaxima/@xsi:nil"/>
                  </xsl:attribute>
                </xsl:if>
                <xsl:value-of select="ns15:tasaMaxima"/>
              </ns15:tasaMaxima>
              <ns15:montoMinimo>
                <xsl:if test="ns15:montoMinimo/@xsi:nil">
                  <xsl:attribute name="nil" namespace="http://www.w3.org/2001/XMLSchema-instance">
                    <xsl:value-of select="ns15:montoMinimo/@xsi:nil"/>
                  </xsl:attribute>
                </xsl:if>
                <xsl:value-of select="ns15:montoMinimo"/>
              </ns15:montoMinimo>
              <ns15:montoMaximo>
                <xsl:if test="ns15:montoMaximo/@xsi:nil">
                  <xsl:attribute name="nil" namespace="http://www.w3.org/2001/XMLSchema-instance">
                    <xsl:value-of select="ns15:montoMaximo/@xsi:nil"/>
                  </xsl:attribute>
                </xsl:if>
                <xsl:value-of select="ns15:montoMaximo"/>
              </ns15:montoMaximo>
              <xsl:for-each select="ns15:Monto">
                <ns15:Monto>
                  <ns10:tipo>
                    <xsl:if test="ns10:tipo/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns10:tipo/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns10:tipo/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns10:tipo/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns10:tipo/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns10:tipo/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns10:tipo/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns10:tipo/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns10:tipo/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns10:tipo/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns10:tipo>
                  <xsl:if test="ns10:importe">
                    <ns10:importe>
                      <xsl:value-of select="ns10:importe"/>
                    </ns10:importe>
                  </xsl:if>
                  <xsl:if test="ns10:moneda">
                    <ns10:moneda>
                      <xsl:if test="ns10:moneda/ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns10:moneda/ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns10:moneda/ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns10:moneda/ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns10:moneda/ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns10:moneda/ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns10:moneda/ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns10:moneda/ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns10:moneda/ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns10:moneda/ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns10:moneda>
                  </xsl:if>
                </ns15:Monto>
              </xsl:for-each>
              <xsl:for-each select="ns15:Condicion">
                <ns15:Condicion>
                  <ns4:idCondicion>
                    <xsl:value-of select="ns4:idCondicion"/>
                  </ns4:idCondicion>
                  <ns4:operacion>
                    <xsl:value-of select="ns4:operacion"/>
                  </ns4:operacion>
                  <xsl:if test="ns4:nombre">
                    <ns4:nombre>
                      <xsl:value-of select="ns4:nombre"/>
                    </ns4:nombre>
                  </xsl:if>
                  <xsl:if test="ns4:descripcion">
                    <ns4:descripcion>
                      <xsl:value-of select="ns4:descripcion"/>
                    </ns4:descripcion>
                  </xsl:if>
                  <xsl:if test="ns4:tipoCondicion">
                    <ns4:tipoCondicion>
                      <xsl:if test="ns4:tipoCondicion/ns4:idCatCondicion">
                        <ns4:idCatCondicion>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:idCatCondicion"/>
                        </ns4:idCatCondicion>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:descripcion">
                        <ns4:descripcion>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:descripcion"/>
                        </ns4:descripcion>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:descripcionCorta">
                        <ns4:descripcionCorta>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:descripcionCorta"/>
                        </ns4:descripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:idTipoCondicion">
                        <ns4:idTipoCondicion>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:idTipoCondicion"/>
                        </ns4:idTipoCondicion>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:esEditableEnFormalizacion">
                        <ns4:esEditableEnFormalizacion>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:esEditableEnFormalizacion"/>
                        </ns4:esEditableEnFormalizacion>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:requiereValidarCOFI">
                        <ns4:requiereValidarCOFI>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:requiereValidarCOFI"/>
                        </ns4:requiereValidarCOFI>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:sePuedeDispensar">
                        <ns4:sePuedeDispensar>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:sePuedeDispensar"/>
                        </ns4:sePuedeDispensar>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:esPlantilla">
                        <ns4:esPlantilla>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:esPlantilla"/>
                        </ns4:esPlantilla>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:idCondicionPrecarga">
                        <ns4:idCondicionPrecarga>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:idCondicionPrecarga"/>
                        </ns4:idCondicionPrecarga>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:fechaRegistro">
                        <ns4:fechaRegistro>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:fechaRegistro"/>
                        </ns4:fechaRegistro>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:estado">
                        <ns4:estado>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:estado"/>
                        </ns4:estado>
                      </xsl:if>
                      <xsl:if test="ns4:tipoCondicion/ns4:codigoExterno">
                        <ns4:codigoExterno>
                          <xsl:value-of select="ns4:tipoCondicion/ns4:codigoExterno"/>
                        </ns4:codigoExterno>
                      </xsl:if>
                    </ns4:tipoCondicion>
                  </xsl:if>
                  <xsl:for-each select="ns4:categoriaCondicion">
                    <ns4:categoriaCondicion>
                      <ns4:id>
                        <xsl:value-of select="ns4:id"/>
                      </ns4:id>
                      <ns4:descripcion>
                        <xsl:value-of select="ns4:descripcion"/>
                      </ns4:descripcion>
                      <xsl:if test="ns4:descripcionCorta">
                        <ns4:descripcionCorta>
                          <xsl:value-of select="ns4:descripcionCorta"/>
                        </ns4:descripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns4:estatus">
                        <ns4:estatus>
                          <xsl:value-of select="ns4:estatus"/>
                        </ns4:estatus>
                      </xsl:if>
                      <xsl:if test="ns4:codigoExterno">
                        <ns4:codigoExterno>
                          <xsl:value-of select="ns4:codigoExterno"/>
                        </ns4:codigoExterno>
                      </xsl:if>
                      <xsl:for-each select="ns4:validadores">
                        <ns4:validadores>
                          <xsl:if test="ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns4:validadores>
                      </xsl:for-each>
                    </ns4:categoriaCondicion>
                  </xsl:for-each>
                  <xsl:if test="ns4:controlCondicion">
                    <ns4:controlCondicion>
                      <xsl:if test="ns4:controlCondicion/ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns4:controlCondicion/ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns4:controlCondicion/ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns4:controlCondicion/ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns4:controlCondicion/ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns4:controlCondicion/ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns4:controlCondicion/ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns4:controlCondicion/ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns4:controlCondicion/ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns4:controlCondicion/ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns4:controlCondicion>
                  </xsl:if>
                  <xsl:for-each select="ns4:eventoCondicion">
                    <ns4:eventoCondicion>
                      <xsl:if test="ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns4:eventoCondicion>
                  </xsl:for-each>
                  <xsl:if test="ns4:tipoFechaInicio">
                    <ns4:tipoFechaInicio>
                      <xsl:if test="ns4:tipoFechaInicio/ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns4:tipoFechaInicio/ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns4:tipoFechaInicio/ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns4:tipoFechaInicio/ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns4:tipoFechaInicio/ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns4:tipoFechaInicio/ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns4:tipoFechaInicio/ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns4:tipoFechaInicio/ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns4:tipoFechaInicio/ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns4:tipoFechaInicio/ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns4:tipoFechaInicio>
                  </xsl:if>
                  <xsl:if test="ns4:fechaInicio">
                    <ns4:fechaInicio>
                      <xsl:value-of select="ns4:fechaInicio"/>
                    </ns4:fechaInicio>
                  </xsl:if>
                  <xsl:if test="ns4:plazo">
                    <ns4:plazo>
                      <xsl:value-of select="ns4:plazo"/>
                    </ns4:plazo>
                  </xsl:if>
                  <xsl:if test="ns4:frecuenciaPlazo">
                    <ns4:frecuenciaPlazo>
                      <xsl:if test="ns4:frecuenciaPlazo/ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns4:frecuenciaPlazo/ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns4:frecuenciaPlazo/ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns4:frecuenciaPlazo/ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns4:frecuenciaPlazo/ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns4:frecuenciaPlazo/ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns4:frecuenciaPlazo/ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns4:frecuenciaPlazo/ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns4:frecuenciaPlazo/ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns4:frecuenciaPlazo/ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns4:frecuenciaPlazo>
                  </xsl:if>
                  <xsl:if test="ns4:fechaFinal">
                    <ns4:fechaFinal>
                      <xsl:value-of select="ns4:fechaFinal"/>
                    </ns4:fechaFinal>
                  </xsl:if>
                  <xsl:if test="ns4:estadoTCC">
                    <ns4:estadoTCC>
                      <xsl:if test="ns4:estadoTCC/ns20:id">
                        <ns20:id>
                          <xsl:value-of select="ns4:estadoTCC/ns20:id"/>
                        </ns20:id>
                      </xsl:if>
                      <xsl:if test="ns4:estadoTCC/ns20:descripcion">
                        <ns20:descripcion>
                          <xsl:value-of select="ns4:estadoTCC/ns20:descripcion"/>
                        </ns20:descripcion>
                      </xsl:if>
                      <xsl:if test="ns4:estadoTCC/ns20:descripcionCorta">
                        <ns20:descripcionCorta>
                          <xsl:value-of select="ns4:estadoTCC/ns20:descripcionCorta"/>
                        </ns20:descripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns4:estadoTCC/ns20:estatus">
                        <ns20:estatus>
                          <xsl:value-of select="ns4:estadoTCC/ns20:estatus"/>
                        </ns20:estatus>
                      </xsl:if>
                      <xsl:if test="ns4:estadoTCC/ns20:codigoExterno">
                        <ns20:codigoExterno>
                          <xsl:value-of select="ns4:estadoTCC/ns20:codigoExterno"/>
                        </ns20:codigoExterno>
                      </xsl:if>
                      <xsl:if test="ns4:estadoTCC/ns20:esSubEstado">
                        <ns20:esSubEstado>
                          <xsl:value-of select="ns4:estadoTCC/ns20:esSubEstado"/>
                        </ns20:esSubEstado>
                      </xsl:if>
                    </ns4:estadoTCC>
                  </xsl:if>
                  <xsl:if test="ns4:subEstadoTCC">
                    <ns4:subEstadoTCC>
                      <xsl:if test="ns4:subEstadoTCC/ns20:id">
                        <ns20:id>
                          <xsl:value-of select="ns4:subEstadoTCC/ns20:id"/>
                        </ns20:id>
                      </xsl:if>
                      <xsl:if test="ns4:subEstadoTCC/ns20:descripcion">
                        <ns20:descripcion>
                          <xsl:value-of select="ns4:subEstadoTCC/ns20:descripcion"/>
                        </ns20:descripcion>
                      </xsl:if>
                      <xsl:if test="ns4:subEstadoTCC/ns20:descripcionCorta">
                        <ns20:descripcionCorta>
                          <xsl:value-of select="ns4:subEstadoTCC/ns20:descripcionCorta"/>
                        </ns20:descripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns4:subEstadoTCC/ns20:estatus">
                        <ns20:estatus>
                          <xsl:value-of select="ns4:subEstadoTCC/ns20:estatus"/>
                        </ns20:estatus>
                      </xsl:if>
                      <xsl:if test="ns4:subEstadoTCC/ns20:codigoExterno">
                        <ns20:codigoExterno>
                          <xsl:value-of select="ns4:subEstadoTCC/ns20:codigoExterno"/>
                        </ns20:codigoExterno>
                      </xsl:if>
                      <xsl:if test="ns4:subEstadoTCC/ns20:esSubEstado">
                        <ns20:esSubEstado>
                          <xsl:value-of select="ns4:subEstadoTCC/ns20:esSubEstado"/>
                        </ns20:esSubEstado>
                      </xsl:if>
                    </ns4:subEstadoTCC>
                  </xsl:if>
                  <xsl:if test="ns4:fechaRegistro">
                    <ns4:fechaRegistro>
                      <xsl:value-of select="ns4:fechaRegistro"/>
                    </ns4:fechaRegistro>
                  </xsl:if>
                  <xsl:if test="ns4:estado">
                    <ns4:estado>
                      <xsl:value-of select="ns4:estado"/>
                    </ns4:estado>
                  </xsl:if>
                  <xsl:if test="ns4:condicionEnmendada">
                    <ns4:condicionEnmendada>
                      <xsl:value-of select="ns4:condicionEnmendada"/>
                    </ns4:condicionEnmendada>
                  </xsl:if>
                  <xsl:if test="ns4:fechaEnmienda">
                    <ns4:fechaEnmienda>
                      <xsl:value-of select="ns4:fechaEnmienda"/>
                    </ns4:fechaEnmienda>
                  </xsl:if>
                  <xsl:for-each select="ns4:configAtributo">
                    <ns4:configAtributo>
                      <xsl:if test="ns20:id">
                        <ns20:id>
                          <xsl:value-of select="ns20:id"/>
                        </ns20:id>
                      </xsl:if>
                      <ns20:columna>
                        <xsl:value-of select="ns20:columna"/>
                      </ns20:columna>
                      <xsl:if test="ns20:ordenamiento">
                        <ns20:ordenamiento>
                          <xsl:value-of select="ns20:ordenamiento"/>
                        </ns20:ordenamiento>
                      </xsl:if>
                      <xsl:if test="ns20:soloLectura">
                        <ns20:soloLectura>
                          <xsl:value-of select="ns20:soloLectura"/>
                        </ns20:soloLectura>
                      </xsl:if>
                      <xsl:if test="ns20:esObligatorio">
                        <ns20:esObligatorio>
                          <xsl:value-of select="ns20:esObligatorio"/>
                        </ns20:esObligatorio>
                      </xsl:if>
                      <xsl:if test="ns20:etiqueta">
                        <ns20:etiqueta>
                          <xsl:value-of select="ns20:etiqueta"/>
                        </ns20:etiqueta>
                      </xsl:if>
                      <xsl:for-each select="ns20:valor">
                        <ns20:valor>
                          <xsl:value-of select="."/>
                        </ns20:valor>
                      </xsl:for-each>
                      <xsl:if test="ns20:tipoValor">
                        <ns20:tipoValor>
                          <xsl:value-of select="ns20:tipoValor"/>
                        </ns20:tipoValor>
                      </xsl:if>
                      <xsl:for-each select="ns20:catalogo">
                        <ns20:catalogo>
                          <xsl:if test="ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns20:catalogo>
                      </xsl:for-each>
                    </ns4:configAtributo>
                  </xsl:for-each>
                  <xsl:if test="ns4:evidencia">
                    <ns4:evidencia>
                      <xsl:for-each select="ns4:evidencia/ns12:Documento">
                        <ns12:Documento>
                          <xsl:if test="ns12:idDocumento">
                            <ns12:idDocumento>
                              <xsl:value-of select="ns12:idDocumento"/>
                            </ns12:idDocumento>
                          </xsl:if>
                          <xsl:if test="ns12:idTipoDocumento">
                            <ns12:idTipoDocumento>
                              <xsl:value-of select="ns12:idTipoDocumento"/>
                            </ns12:idTipoDocumento>
                          </xsl:if>
                          <xsl:if test="ns12:idTipoDocumentoExterno">
                            <ns12:idTipoDocumentoExterno>
                              <xsl:value-of select="ns12:idTipoDocumentoExterno"/>
                            </ns12:idTipoDocumentoExterno>
                          </xsl:if>
                          <xsl:if test="ns12:nombreTipoDocumento">
                            <ns12:nombreTipoDocumento>
                              <xsl:value-of select="ns12:nombreTipoDocumento"/>
                            </ns12:nombreTipoDocumento>
                          </xsl:if>
                          <xsl:if test="ns12:etapa">
                            <ns12:etapa>
                              <xsl:value-of select="ns12:etapa"/>
                            </ns12:etapa>
                          </xsl:if>
                          <xsl:if test="ns12:codigoDocumento">
                            <ns12:codigoDocumento>
                              <xsl:value-of select="ns12:codigoDocumento"/>
                            </ns12:codigoDocumento>
                          </xsl:if>
                          <xsl:if test="ns12:idExterno">
                            <ns12:idExterno>
                              <xsl:value-of select="ns12:idExterno"/>
                            </ns12:idExterno>
                          </xsl:if>
                          <xsl:if test="ns12:idDocAreaTipo">
                            <ns12:idDocAreaTipo>
                              <xsl:value-of select="ns12:idDocAreaTipo"/>
                            </ns12:idDocAreaTipo>
                          </xsl:if>
                          <xsl:if test="ns12:idOperacion">
                            <ns12:idOperacion>
                              <xsl:value-of select="ns12:idOperacion"/>
                            </ns12:idOperacion>
                          </xsl:if>
                          <xsl:if test="ns12:idCliente">
                            <ns12:idCliente>
                              <xsl:value-of select="ns12:idCliente"/>
                            </ns12:idCliente>
                          </xsl:if>
                          <xsl:if test="ns12:idPais">
                            <ns12:idPais>
                              <xsl:value-of select="ns12:idPais"/>
                            </ns12:idPais>
                          </xsl:if>
                          <xsl:if test="ns12:nombre">
                            <ns12:nombre>
                              <xsl:value-of select="ns12:nombre"/>
                            </ns12:nombre>
                          </xsl:if>
                          <xsl:if test="ns12:filename">
                            <ns12:filename>
                              <xsl:value-of select="ns12:filename"/>
                            </ns12:filename>
                          </xsl:if>
                          <xsl:if test="ns12:mime_type">
                            <ns12:mime_type>
                              <xsl:value-of select="ns12:mime_type"/>
                            </ns12:mime_type>
                          </xsl:if>
                          <xsl:if test="ns12:tamanio">
                            <ns12:tamanio>
                              <xsl:value-of select="ns12:tamanio"/>
                            </ns12:tamanio>
                          </xsl:if>
                          <xsl:if test="ns12:idAdjunto">
                            <ns12:idAdjunto>
                              <xsl:value-of select="ns12:idAdjunto"/>
                            </ns12:idAdjunto>
                          </xsl:if>
                          <xsl:if test="ns12:esJustificacion">
                            <ns12:esJustificacion>
                              <xsl:value-of select="ns12:esJustificacion"/>
                            </ns12:esJustificacion>
                          </xsl:if>
                          <xsl:if test="ns12:comentario">
                            <ns12:comentario>
                              <xsl:value-of select="ns12:comentario"/>
                            </ns12:comentario>
                          </xsl:if>
                          <xsl:if test="ns12:fechaDocumento">
                            <ns12:fechaDocumento>
                              <xsl:value-of select="ns12:fechaDocumento"/>
                            </ns12:fechaDocumento>
                          </xsl:if>
                          <xsl:if test="ns12:fechaRegistro">
                            <ns12:fechaRegistro>
                              <xsl:value-of select="ns12:fechaRegistro"/>
                            </ns12:fechaRegistro>
                          </xsl:if>
                          <xsl:if test="ns12:status">
                            <ns12:status>
                              <xsl:value-of select="ns12:status"/>
                            </ns12:status>
                          </xsl:if>
                          <xsl:if test="ns12:estatusTipoDoc">
                            <ns12:estatusTipoDoc>
                              <xsl:value-of select="ns12:estatusTipoDoc"/>
                            </ns12:estatusTipoDoc>
                          </xsl:if>
                          <xsl:if test="ns12:codExtTipoDoc">
                            <ns12:codExtTipoDoc>
                              <xsl:value-of select="ns12:codExtTipoDoc"/>
                            </ns12:codExtTipoDoc>
                          </xsl:if>
                          <xsl:if test="ns12:tarea">
                            <ns12:tarea>
                              <xsl:value-of select="ns12:tarea"/>
                            </ns12:tarea>
                          </xsl:if>
                          <xsl:if test="ns12:idtarea">
                            <ns12:idtarea>
                              <xsl:value-of select="ns12:idtarea"/>
                            </ns12:idtarea>
                          </xsl:if>
                          <xsl:if test="ns12:content">
                            <ns12:content>
                              <xsl:value-of select="ns12:content"/>
                            </ns12:content>
                          </xsl:if>
                          <xsl:if test="ns12:PuedeModificar">
                            <ns12:PuedeModificar>
                              <xsl:value-of select="ns12:PuedeModificar"/>
                            </ns12:PuedeModificar>
                          </xsl:if>
                          <xsl:if test="ns12:PuedeBorrar">
                            <ns12:PuedeBorrar>
                              <xsl:value-of select="ns12:PuedeBorrar"/>
                            </ns12:PuedeBorrar>
                          </xsl:if>
                          <xsl:if test="ns12:usuarioAgrega">
                            <ns12:usuarioAgrega>
                              <xsl:value-of select="ns12:usuarioAgrega"/>
                            </ns12:usuarioAgrega>
                          </xsl:if>
                          <xsl:if test="ns12:usuarioUltimaActualizacion">
                            <ns12:usuarioUltimaActualizacion>
                              <xsl:value-of select="ns12:usuarioUltimaActualizacion"/>
                            </ns12:usuarioUltimaActualizacion>
                          </xsl:if>
                          <xsl:if test="ns12:accionARealizar">
                            <ns12:accionARealizar>
                              <xsl:value-of select="ns12:accionARealizar"/>
                            </ns12:accionARealizar>
                          </xsl:if>
                          <xsl:if test="ns12:idFlujoNegocio">
                            <ns12:idFlujoNegocio>
                              <xsl:value-of select="ns12:idFlujoNegocio"/>
                            </ns12:idFlujoNegocio>
                          </xsl:if>
                        </ns12:Documento>
                      </xsl:for-each>
                    </ns4:evidencia>
                  </xsl:if>
                  <xsl:for-each select="ns4:observaciones">
                    <ns4:observaciones>
                      <ns4:id>
                        <xsl:value-of select="ns4:id"/>
                      </ns4:id>
                      <xsl:if test="ns4:observacion">
                        <ns4:observacion>
                          <xsl:value-of select="ns4:observacion"/>
                        </ns4:observacion>
                      </xsl:if>
                      <xsl:if test="ns4:loginUsuario">
                        <ns4:loginUsuario>
                          <xsl:value-of select="ns4:loginUsuario"/>
                        </ns4:loginUsuario>
                      </xsl:if>
                      <xsl:if test="ns4:nombreUsuario">
                        <ns4:nombreUsuario>
                          <xsl:value-of select="ns4:nombreUsuario"/>
                        </ns4:nombreUsuario>
                      </xsl:if>
                      <xsl:if test="ns4:fechaRegistro">
                        <ns4:fechaRegistro>
                          <xsl:value-of select="ns4:fechaRegistro"/>
                        </ns4:fechaRegistro>
                      </xsl:if>
                      <xsl:if test="ns4:estatus">
                        <ns4:estatus>
                          <xsl:value-of select="ns4:estatus"/>
                        </ns4:estatus>
                      </xsl:if>
                      <xsl:if test="ns4:esPrincipal">
                        <ns4:esPrincipal>
                          <xsl:value-of select="ns4:esPrincipal"/>
                        </ns4:esPrincipal>
                      </xsl:if>
                      <xsl:if test="ns4:tareaBPM">
                        <ns4:tareaBPM>
                          <xsl:if test="ns4:tareaBPM/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns4:tareaBPM/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns4:tareaBPM/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns4:tareaBPM/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns4:tareaBPM/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns4:tareaBPM/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns4:tareaBPM/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns4:tareaBPM/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns4:tareaBPM/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns4:tareaBPM/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns4:tareaBPM>
                      </xsl:if>
                    </ns4:observaciones>
                  </xsl:for-each>
                  <xsl:for-each select="ns4:lineaCredito">
                    <ns4:lineaCredito>
                      <xsl:if test="ns20:id">
                        <ns20:id>
                          <xsl:value-of select="ns20:id"/>
                        </ns20:id>
                      </xsl:if>
                      <xsl:if test="ns20:descripcion">
                        <ns20:descripcion>
                          <xsl:value-of select="ns20:descripcion"/>
                        </ns20:descripcion>
                      </xsl:if>
                      <xsl:if test="ns20:estatus">
                        <ns20:estatus>
                          <xsl:value-of select="ns20:estatus"/>
                        </ns20:estatus>
                      </xsl:if>
                    </ns4:lineaCredito>
                  </xsl:for-each>
                  <xsl:for-each select="ns4:fuente">
                    <ns4:fuente>
                      <xsl:if test="ns20:id">
                        <ns20:id>
                          <xsl:value-of select="ns20:id"/>
                        </ns20:id>
                      </xsl:if>
                      <xsl:if test="ns20:descripcion">
                        <ns20:descripcion>
                          <xsl:value-of select="ns20:descripcion"/>
                        </ns20:descripcion>
                      </xsl:if>
                      <xsl:if test="ns20:estatus">
                        <ns20:estatus>
                          <xsl:value-of select="ns20:estatus"/>
                        </ns20:estatus>
                      </xsl:if>
                    </ns4:fuente>
                  </xsl:for-each>
                  <xsl:if test="ns4:Accion">
                    <ns4:Accion>
                      <xsl:value-of select="ns4:Accion"/>
                    </ns4:Accion>
                  </xsl:if>
                </ns15:Condicion>
              </xsl:for-each>
              <xsl:for-each select="ns15:Termino">
                <ns15:Termino>
                  <ns6:idTermino>
                    <xsl:value-of select="ns6:idTermino"/>
                  </ns6:idTermino>
                  <ns6:operacion>
                    <xsl:value-of select="ns6:operacion"/>
                  </ns6:operacion>
                  <ns6:nombre>
                    <xsl:value-of select="ns6:nombre"/>
                  </ns6:nombre>
                  <ns6:descripcion>
                    <xsl:value-of select="ns6:descripcion"/>
                  </ns6:descripcion>
                  <ns6:tipoTermino>
                    <xsl:if test="ns6:tipoTermino/ns6:idCatTermino">
                      <ns6:idCatTermino>
                        <xsl:value-of select="ns6:tipoTermino/ns6:idCatTermino"/>
                      </ns6:idCatTermino>
                    </xsl:if>
                    <ns6:descripcion>
                      <xsl:value-of select="ns6:tipoTermino/ns6:descripcion"/>
                    </ns6:descripcion>
                    <ns6:descripcionCorta>
                      <xsl:value-of select="ns6:tipoTermino/ns6:descripcionCorta"/>
                    </ns6:descripcionCorta>
                    <xsl:if test="ns6:tipoTermino/ns6:idTipoTermino">
                      <ns6:idTipoTermino>
                        <xsl:value-of select="ns6:tipoTermino/ns6:idTipoTermino"/>
                      </ns6:idTipoTermino>
                    </xsl:if>
                    <ns6:esEditableEnFormalizacion>
                      <xsl:value-of select="ns6:tipoTermino/ns6:esEditableEnFormalizacion"/>
                    </ns6:esEditableEnFormalizacion>
                    <ns6:requiereValidarCOFI>
                      <xsl:value-of select="ns6:tipoTermino/ns6:requiereValidarCOFI"/>
                    </ns6:requiereValidarCOFI>
                    <ns6:sePuedeDispensar>
                      <xsl:value-of select="ns6:tipoTermino/ns6:sePuedeDispensar"/>
                    </ns6:sePuedeDispensar>
                    <ns6:esPlantilla>
                      <xsl:value-of select="ns6:tipoTermino/ns6:esPlantilla"/>
                    </ns6:esPlantilla>
                    <ns6:requiereOGC>
                      <xsl:value-of select="ns6:tipoTermino/ns6:requiereOGC"/>
                    </ns6:requiereOGC>
                    <ns6:idTerminoPrecarga>
                      <xsl:value-of select="ns6:tipoTermino/ns6:idTerminoPrecarga"/>
                    </ns6:idTerminoPrecarga>
                    <ns6:fechaRegistro>
                      <xsl:value-of select="ns6:tipoTermino/ns6:fechaRegistro"/>
                    </ns6:fechaRegistro>
                    <ns6:estado>
                      <xsl:value-of select="ns6:tipoTermino/ns6:estado"/>
                    </ns6:estado>
                    <ns6:codigoExterno>
                      <xsl:value-of select="ns6:tipoTermino/ns6:codigoExterno"/>
                    </ns6:codigoExterno>
                  </ns6:tipoTermino>
                  <ns6:tipoFechaInicio>
                    <xsl:if test="ns6:tipoFechaInicio/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns6:tipoFechaInicio/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFechaInicio/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns6:tipoFechaInicio/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFechaInicio/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns6:tipoFechaInicio/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFechaInicio/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns6:tipoFechaInicio/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFechaInicio/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns6:tipoFechaInicio/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns6:tipoFechaInicio>
                  <ns6:fechaInicio>
                    <xsl:value-of select="ns6:fechaInicio"/>
                  </ns6:fechaInicio>
                  <ns6:plazo>
                    <xsl:value-of select="ns6:plazo"/>
                  </ns6:plazo>
                  <ns6:frecuenciaPlazo>
                    <xsl:if test="ns6:frecuenciaPlazo/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns6:frecuenciaPlazo/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns6:frecuenciaPlazo/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns6:frecuenciaPlazo/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns6:frecuenciaPlazo/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns6:frecuenciaPlazo/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns6:frecuenciaPlazo/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns6:frecuenciaPlazo/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns6:frecuenciaPlazo/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns6:frecuenciaPlazo/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns6:frecuenciaPlazo>
                  <ns6:fechaVencimiento>
                    <xsl:value-of select="ns6:fechaVencimiento"/>
                  </ns6:fechaVencimiento>
                  <ns6:moneda>
                    <xsl:if test="ns6:moneda/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns6:moneda/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns6:moneda/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns6:moneda/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns6:moneda/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns6:moneda/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns6:moneda/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns6:moneda/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns6:moneda/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns6:moneda/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns6:moneda>
                  <ns6:monto>
                    <xsl:value-of select="ns6:monto"/>
                  </ns6:monto>
                  <ns6:tasa>
                    <xsl:value-of select="ns6:tasa"/>
                  </ns6:tasa>
                  <ns6:tipoTasa>
                    <xsl:if test="ns6:tipoTasa/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns6:tipoTasa/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns6:tipoTasa/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns6:tipoTasa/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns6:tipoTasa/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns6:tipoTasa/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns6:tipoTasa/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns6:tipoTasa/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns6:tipoTasa/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns6:tipoTasa/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns6:tipoTasa>
                  <ns6:fecha>
                    <xsl:value-of select="ns6:fecha"/>
                  </ns6:fecha>
                  <ns6:frecuenciaRevision>
                    <xsl:value-of select="ns6:frecuenciaRevision"/>
                  </ns6:frecuenciaRevision>
                  <ns6:tipoFrecuenciaRevision>
                    <xsl:if test="ns6:tipoFrecuenciaRevision/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns6:tipoFrecuenciaRevision/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaRevision/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns6:tipoFrecuenciaRevision/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaRevision/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns6:tipoFrecuenciaRevision/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaRevision/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns6:tipoFrecuenciaRevision/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaRevision/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns6:tipoFrecuenciaRevision/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns6:tipoFrecuenciaRevision>
                  <ns6:fechaInicioRevision>
                    <xsl:value-of select="ns6:fechaInicioRevision"/>
                  </ns6:fechaInicioRevision>
                  <ns6:frecuenciaPagoInteres>
                    <xsl:value-of select="ns6:frecuenciaPagoInteres"/>
                  </ns6:frecuenciaPagoInteres>
                  <ns6:tipoFrecuenciaPagoInteres>
                    <xsl:if test="ns6:tipoFrecuenciaPagoInteres/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns6:tipoFrecuenciaPagoInteres/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaPagoInteres/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns6:tipoFrecuenciaPagoInteres/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaPagoInteres/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns6:tipoFrecuenciaPagoInteres/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaPagoInteres/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns6:tipoFrecuenciaPagoInteres/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaPagoInteres/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns6:tipoFrecuenciaPagoInteres/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns6:tipoFrecuenciaPagoInteres>
                  <ns6:fechaInicioPagoInteres>
                    <xsl:value-of select="ns6:fechaInicioPagoInteres"/>
                  </ns6:fechaInicioPagoInteres>
                  <ns6:frecuenciaAmortizacion>
                    <xsl:value-of select="ns6:frecuenciaAmortizacion"/>
                  </ns6:frecuenciaAmortizacion>
                  <ns6:tipoFrecuenciaAmortizacion>
                    <xsl:if test="ns6:tipoFrecuenciaAmortizacion/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns6:tipoFrecuenciaAmortizacion/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaAmortizacion/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns6:tipoFrecuenciaAmortizacion/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaAmortizacion/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns6:tipoFrecuenciaAmortizacion/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaAmortizacion/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns6:tipoFrecuenciaAmortizacion/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns6:tipoFrecuenciaAmortizacion/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns6:tipoFrecuenciaAmortizacion/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns6:tipoFrecuenciaAmortizacion>
                  <ns6:mora>
                    <xsl:value-of select="ns6:mora"/>
                  </ns6:mora>
                  <ns6:porcentajeCobertura>
                    <xsl:value-of select="ns6:porcentajeCobertura"/>
                  </ns6:porcentajeCobertura>
                  <ns6:seAplicanRecursosConcesion>
                    <xsl:value-of select="ns6:seAplicanRecursosConcesion"/>
                  </ns6:seAplicanRecursosConcesion>
                  <ns6:seAplicanRecursosExternos>
                    <xsl:value-of select="ns6:seAplicanRecursosExternos"/>
                  </ns6:seAplicanRecursosExternos>
                  <ns6:tipoContraparte>
                    <xsl:value-of select="ns6:tipoContraparte"/>
                  </ns6:tipoContraparte>
                  <ns6:montoMinimoDesembolso>
                    <xsl:value-of select="ns6:montoMinimoDesembolso"/>
                  </ns6:montoMinimoDesembolso>
                  <ns6:montoMaximoDesembolso>
                    <xsl:value-of select="ns6:montoMaximoDesembolso"/>
                  </ns6:montoMaximoDesembolso>
                  <ns6:tasaMinimaDesembolso>
                    <xsl:value-of select="ns6:tasaMinimaDesembolso"/>
                  </ns6:tasaMinimaDesembolso>
                  <ns6:tasaMaximaDesembolso>
                    <xsl:value-of select="ns6:tasaMaximaDesembolso"/>
                  </ns6:tasaMaximaDesembolso>
                  <ns6:estadoTCC>
                    <xsl:if test="ns6:estadoTCC/ns20:id">
                      <ns20:id>
                        <xsl:value-of select="ns6:estadoTCC/ns20:id"/>
                      </ns20:id>
                    </xsl:if>
                    <xsl:if test="ns6:estadoTCC/ns20:descripcion">
                      <ns20:descripcion>
                        <xsl:value-of select="ns6:estadoTCC/ns20:descripcion"/>
                      </ns20:descripcion>
                    </xsl:if>
                    <xsl:if test="ns6:estadoTCC/ns20:descripcionCorta">
                      <ns20:descripcionCorta>
                        <xsl:value-of select="ns6:estadoTCC/ns20:descripcionCorta"/>
                      </ns20:descripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns6:estadoTCC/ns20:estatus">
                      <ns20:estatus>
                        <xsl:value-of select="ns6:estadoTCC/ns20:estatus"/>
                      </ns20:estatus>
                    </xsl:if>
                    <xsl:if test="ns6:estadoTCC/ns20:codigoExterno">
                      <ns20:codigoExterno>
                        <xsl:value-of select="ns6:estadoTCC/ns20:codigoExterno"/>
                      </ns20:codigoExterno>
                    </xsl:if>
                    <xsl:if test="ns6:estadoTCC/ns20:esSubEstado">
                      <ns20:esSubEstado>
                        <xsl:value-of select="ns6:estadoTCC/ns20:esSubEstado"/>
                      </ns20:esSubEstado>
                    </xsl:if>
                  </ns6:estadoTCC>
                  <ns6:subEstadoTCC>
                    <xsl:if test="ns6:subEstadoTCC/ns20:id">
                      <ns20:id>
                        <xsl:value-of select="ns6:subEstadoTCC/ns20:id"/>
                      </ns20:id>
                    </xsl:if>
                    <xsl:if test="ns6:subEstadoTCC/ns20:descripcion">
                      <ns20:descripcion>
                        <xsl:value-of select="ns6:subEstadoTCC/ns20:descripcion"/>
                      </ns20:descripcion>
                    </xsl:if>
                    <xsl:if test="ns6:subEstadoTCC/ns20:descripcionCorta">
                      <ns20:descripcionCorta>
                        <xsl:value-of select="ns6:subEstadoTCC/ns20:descripcionCorta"/>
                      </ns20:descripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns6:subEstadoTCC/ns20:estatus">
                      <ns20:estatus>
                        <xsl:value-of select="ns6:subEstadoTCC/ns20:estatus"/>
                      </ns20:estatus>
                    </xsl:if>
                    <xsl:if test="ns6:subEstadoTCC/ns20:codigoExterno">
                      <ns20:codigoExterno>
                        <xsl:value-of select="ns6:subEstadoTCC/ns20:codigoExterno"/>
                      </ns20:codigoExterno>
                    </xsl:if>
                    <xsl:if test="ns6:subEstadoTCC/ns20:esSubEstado">
                      <ns20:esSubEstado>
                        <xsl:value-of select="ns6:subEstadoTCC/ns20:esSubEstado"/>
                      </ns20:esSubEstado>
                    </xsl:if>
                  </ns6:subEstadoTCC>
                  <ns6:fechaRegistro>
                    <xsl:value-of select="ns6:fechaRegistro"/>
                  </ns6:fechaRegistro>
                  <ns6:estado>
                    <xsl:value-of select="ns6:estado"/>
                  </ns6:estado>
                  <ns6:terminoEnmendado>
                    <xsl:value-of select="ns6:terminoEnmendado"/>
                  </ns6:terminoEnmendado>
                  <ns6:fechaEnmienda>
                    <xsl:value-of select="ns6:fechaEnmienda"/>
                  </ns6:fechaEnmienda>
                  <xsl:for-each select="ns6:configAtributo">
                    <ns6:configAtributo>
                      <xsl:if test="ns20:id">
                        <ns20:id>
                          <xsl:value-of select="ns20:id"/>
                        </ns20:id>
                      </xsl:if>
                      <ns20:columna>
                        <xsl:value-of select="ns20:columna"/>
                      </ns20:columna>
                      <xsl:if test="ns20:ordenamiento">
                        <ns20:ordenamiento>
                          <xsl:value-of select="ns20:ordenamiento"/>
                        </ns20:ordenamiento>
                      </xsl:if>
                      <xsl:if test="ns20:soloLectura">
                        <ns20:soloLectura>
                          <xsl:value-of select="ns20:soloLectura"/>
                        </ns20:soloLectura>
                      </xsl:if>
                      <xsl:if test="ns20:esObligatorio">
                        <ns20:esObligatorio>
                          <xsl:value-of select="ns20:esObligatorio"/>
                        </ns20:esObligatorio>
                      </xsl:if>
                      <xsl:if test="ns20:etiqueta">
                        <ns20:etiqueta>
                          <xsl:value-of select="ns20:etiqueta"/>
                        </ns20:etiqueta>
                      </xsl:if>
                      <xsl:for-each select="ns20:valor">
                        <ns20:valor>
                          <xsl:value-of select="."/>
                        </ns20:valor>
                      </xsl:for-each>
                      <xsl:if test="ns20:tipoValor">
                        <ns20:tipoValor>
                          <xsl:value-of select="ns20:tipoValor"/>
                        </ns20:tipoValor>
                      </xsl:if>
                      <xsl:for-each select="ns20:catalogo">
                        <ns20:catalogo>
                          <xsl:if test="ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns20:catalogo>
                      </xsl:for-each>
                    </ns6:configAtributo>
                  </xsl:for-each>
                  <xsl:if test="ns6:Accion">
                    <ns6:Accion>
                      <xsl:value-of select="ns6:Accion"/>
                    </ns6:Accion>
                  </xsl:if>
                  <xsl:for-each select="ns6:Contraparte">
                    <ns6:Contraparte>
                      <xsl:if test="ns20:id">
                        <ns20:id>
                          <xsl:value-of select="ns20:id"/>
                        </ns20:id>
                      </xsl:if>
                      <xsl:if test="ns20:descripcion">
                        <ns20:descripcion>
                          <xsl:value-of select="ns20:descripcion"/>
                        </ns20:descripcion>
                      </xsl:if>
                      <xsl:if test="ns20:estatus">
                        <ns20:estatus>
                          <xsl:value-of select="ns20:estatus"/>
                        </ns20:estatus>
                      </xsl:if>
                    </ns6:Contraparte>
                  </xsl:for-each>
                </ns15:Termino>
              </xsl:for-each>
              <xsl:for-each select="ns15:Comision">
                <ns15:Comision>
                  <ns14:idComision>
                    <xsl:value-of select="ns14:idComision"/>
                  </ns14:idComision>
                  <ns14:idOperacion>
                    <xsl:value-of select="ns14:idOperacion"/>
                  </ns14:idOperacion>
                  <xsl:if test="ns14:nombre">
                    <ns14:nombre>
                      <xsl:value-of select="ns14:nombre"/>
                    </ns14:nombre>
                  </xsl:if>
                  <xsl:if test="ns14:descripcion">
                    <ns14:descripcion>
                      <xsl:value-of select="ns14:descripcion"/>
                    </ns14:descripcion>
                  </xsl:if>
                  <xsl:if test="ns14:tipoComision">
                    <ns14:tipoComision>
                      <xsl:if test="ns14:tipoComision/ns14:idCatComision">
                        <ns14:idCatComision>
                          <xsl:value-of select="ns14:tipoComision/ns14:idCatComision"/>
                        </ns14:idCatComision>
                      </xsl:if>
                      <ns14:descripcion>
                        <xsl:value-of select="ns14:tipoComision/ns14:descripcion"/>
                      </ns14:descripcion>
                      <ns14:descripcionCorta>
                        <xsl:value-of select="ns14:tipoComision/ns14:descripcionCorta"/>
                      </ns14:descripcionCorta>
                      <xsl:if test="ns14:tipoComision/ns14:idTipoComision">
                        <ns14:idTipoComision>
                          <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns14:tipoComision/ns14:idTipoComision/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns14:tipoComision/ns14:idTipoComision/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns14:idTipoComision>
                      </xsl:if>
                      <ns14:esEditableEnFormalizacion>
                        <xsl:value-of select="ns14:tipoComision/ns14:esEditableEnFormalizacion"/>
                      </ns14:esEditableEnFormalizacion>
                      <ns14:requiereValidarCOFI>
                        <xsl:value-of select="ns14:tipoComision/ns14:requiereValidarCOFI"/>
                      </ns14:requiereValidarCOFI>
                      <ns14:sePuedeDispensar>
                        <xsl:value-of select="ns14:tipoComision/ns14:sePuedeDispensar"/>
                      </ns14:sePuedeDispensar>
                      <ns14:seRegistraEnFlexCube>
                        <xsl:value-of select="ns14:tipoComision/ns14:seRegistraEnFlexCube"/>
                      </ns14:seRegistraEnFlexCube>
                      <ns14:esPlantilla>
                        <xsl:value-of select="ns14:tipoComision/ns14:esPlantilla"/>
                      </ns14:esPlantilla>
                      <ns14:idComisionPrecarga>
                        <xsl:value-of select="ns14:tipoComision/ns14:idComisionPrecarga"/>
                      </ns14:idComisionPrecarga>
                      <ns14:fechaRegistro>
                        <xsl:value-of select="ns14:tipoComision/ns14:fechaRegistro"/>
                      </ns14:fechaRegistro>
                      <ns14:estado>
                        <xsl:value-of select="ns14:tipoComision/ns14:estado"/>
                      </ns14:estado>
                      <ns14:codigoExterno>
                        <xsl:value-of select="ns14:tipoComision/ns14:codigoExterno"/>
                      </ns14:codigoExterno>
                    </ns14:tipoComision>
                  </xsl:if>
                  <xsl:if test="ns14:moneda">
                    <ns14:moneda>
                      <xsl:if test="ns14:moneda/ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns14:moneda/ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns14:moneda/ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns14:moneda/ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns14:moneda/ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns14:moneda/ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns14:moneda/ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns14:moneda/ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns14:moneda/ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns14:moneda/ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns14:moneda>
                  </xsl:if>
                  <xsl:if test="ns14:montoComision">
                    <ns14:montoComision>
                      <xsl:value-of select="ns14:montoComision"/>
                    </ns14:montoComision>
                  </xsl:if>
                  <xsl:if test="ns14:montoBase">
                    <ns14:montoBase>
                      <xsl:if test="ns14:montoBase/ns14:idMontoBase">
                        <ns14:idMontoBase>
                          <xsl:value-of select="ns14:montoBase/ns14:idMontoBase"/>
                        </ns14:idMontoBase>
                      </xsl:if>
                      <xsl:if test="ns14:montoBase/ns14:valorMontoBase">
                        <ns14:valorMontoBase>
                          <xsl:value-of select="ns14:montoBase/ns14:valorMontoBase"/>
                        </ns14:valorMontoBase>
                      </xsl:if>
                      <xsl:if test="ns14:montoBase/ns14:porcentajeMontoBase">
                        <ns14:porcentajeMontoBase>
                          <xsl:value-of select="ns14:montoBase/ns14:porcentajeMontoBase"/>
                        </ns14:porcentajeMontoBase>
                      </xsl:if>
                      <xsl:if test="ns14:montoBase/ns14:descripcionMontoBase">
                        <ns14:descripcionMontoBase>
                          <xsl:value-of select="ns14:montoBase/ns14:descripcionMontoBase"/>
                        </ns14:descripcionMontoBase>
                      </xsl:if>
                    </ns14:montoBase>
                  </xsl:if>
                  <xsl:if test="ns14:fechaValor">
                    <ns14:fechaValor>
                      <xsl:value-of select="ns14:fechaValor"/>
                    </ns14:fechaValor>
                  </xsl:if>
                  <xsl:if test="ns14:fechaVencimiento">
                    <ns14:fechaVencimiento>
                      <xsl:value-of select="ns14:fechaVencimiento"/>
                    </ns14:fechaVencimiento>
                  </xsl:if>
                  <xsl:if test="ns14:fechaInicioCapital">
                    <ns14:fechaInicioCapital>
                      <xsl:value-of select="ns14:fechaInicioCapital"/>
                    </ns14:fechaInicioCapital>
                  </xsl:if>
                  <xsl:if test="ns14:fechaInicioComision">
                    <ns14:fechaInicioComision>
                      <xsl:value-of select="ns14:fechaInicioComision"/>
                    </ns14:fechaInicioComision>
                  </xsl:if>
                  <xsl:if test="ns14:tipoFrecuencia">
                    <ns14:tipoFrecuencia>
                      <xsl:if test="ns14:tipoFrecuencia/ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns14:tipoFrecuencia/ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns14:tipoFrecuencia/ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns14:tipoFrecuencia/ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns14:tipoFrecuencia/ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns14:tipoFrecuencia/ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns14:tipoFrecuencia/ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns14:tipoFrecuencia/ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns14:tipoFrecuencia/ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns14:tipoFrecuencia/ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns14:tipoFrecuencia>
                  </xsl:if>
                  <xsl:if test="ns14:frecuenciaPago">
                    <ns14:frecuenciaPago>
                      <xsl:value-of select="ns14:frecuenciaPago"/>
                    </ns14:frecuenciaPago>
                  </xsl:if>
                  <xsl:if test="ns14:fondo">
                    <ns14:fondo>
                      <xsl:value-of select="ns14:fondo"/>
                    </ns14:fondo>
                  </xsl:if>
                  <xsl:if test="ns14:comisionCompartida">
                    <ns14:comisionCompartida>
                      <xsl:value-of select="ns14:comisionCompartida"/>
                    </ns14:comisionCompartida>
                  </xsl:if>
                  <xsl:if test="ns14:codigoDesembolso">
                    <ns14:codigoDesembolso>
                      <xsl:value-of select="ns14:codigoDesembolso"/>
                    </ns14:codigoDesembolso>
                  </xsl:if>
                  <xsl:if test="ns14:numeroTesoreria">
                    <ns14:numeroTesoreria>
                      <xsl:value-of select="ns14:numeroTesoreria"/>
                    </ns14:numeroTesoreria>
                  </xsl:if>
                  <xsl:if test="ns14:codigoContrato">
                    <ns14:codigoContrato>
                      <xsl:value-of select="ns14:codigoContrato"/>
                    </ns14:codigoContrato>
                  </xsl:if>
                  <xsl:if test="ns14:momentoCobro">
                    <ns14:momentoCobro>
                      <xsl:if test="ns14:momentoCobro/ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns14:momentoCobro/ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns14:momentoCobro/ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns14:momentoCobro/ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns14:momentoCobro/ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns14:momentoCobro/ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns14:momentoCobro/ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns14:momentoCobro/ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns14:momentoCobro/ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns14:momentoCobro/ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns14:momentoCobro>
                  </xsl:if>
                  <xsl:if test="ns14:tipoTasa">
                    <ns14:tipoTasa>
                      <xsl:if test="ns14:tipoTasa/ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns14:tipoTasa/ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns14:tipoTasa/ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns14:tipoTasa/ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns14:tipoTasa/ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns14:tipoTasa/ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns14:tipoTasa/ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns14:tipoTasa/ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns14:tipoTasa/ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns14:tipoTasa/ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns14:tipoTasa>
                  </xsl:if>
                  <xsl:if test="ns14:baseCalculo">
                    <ns14:baseCalculo>
                      <xsl:if test="ns14:baseCalculo/ns16:Id">
                        <ns16:Id>
                          <xsl:value-of select="ns14:baseCalculo/ns16:Id"/>
                        </ns16:Id>
                      </xsl:if>
                      <xsl:if test="ns14:baseCalculo/ns16:Descripcion">
                        <ns16:Descripcion>
                          <xsl:value-of select="ns14:baseCalculo/ns16:Descripcion"/>
                        </ns16:Descripcion>
                      </xsl:if>
                      <xsl:if test="ns14:baseCalculo/ns16:DescripcionCorta">
                        <ns16:DescripcionCorta>
                          <xsl:value-of select="ns14:baseCalculo/ns16:DescripcionCorta"/>
                        </ns16:DescripcionCorta>
                      </xsl:if>
                      <xsl:if test="ns14:baseCalculo/ns16:estatus">
                        <ns16:estatus>
                          <xsl:value-of select="ns14:baseCalculo/ns16:estatus"/>
                        </ns16:estatus>
                      </xsl:if>
                      <xsl:if test="ns14:baseCalculo/ns16:codigoExterno">
                        <ns16:codigoExterno>
                          <xsl:value-of select="ns14:baseCalculo/ns16:codigoExterno"/>
                        </ns16:codigoExterno>
                      </xsl:if>
                    </ns14:baseCalculo>
                  </xsl:if>
                  <xsl:if test="ns14:responsableComision">
                    <ns14:responsableComision>
                      <xsl:value-of select="ns14:responsableComision"/>
                    </ns14:responsableComision>
                  </xsl:if>
                  <ns14:estadoTCC>
                    <xsl:if test="ns14:estadoTCC/ns20:id">
                      <ns20:id>
                        <xsl:value-of select="ns14:estadoTCC/ns20:id"/>
                      </ns20:id>
                    </xsl:if>
                    <xsl:if test="ns14:estadoTCC/ns20:descripcion">
                      <ns20:descripcion>
                        <xsl:value-of select="ns14:estadoTCC/ns20:descripcion"/>
                      </ns20:descripcion>
                    </xsl:if>
                    <xsl:if test="ns14:estadoTCC/ns20:descripcionCorta">
                      <ns20:descripcionCorta>
                        <xsl:value-of select="ns14:estadoTCC/ns20:descripcionCorta"/>
                      </ns20:descripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns14:estadoTCC/ns20:estatus">
                      <ns20:estatus>
                        <xsl:value-of select="ns14:estadoTCC/ns20:estatus"/>
                      </ns20:estatus>
                    </xsl:if>
                    <xsl:if test="ns14:estadoTCC/ns20:codigoExterno">
                      <ns20:codigoExterno>
                        <xsl:value-of select="ns14:estadoTCC/ns20:codigoExterno"/>
                      </ns20:codigoExterno>
                    </xsl:if>
                    <xsl:if test="ns14:estadoTCC/ns20:esSubEstado">
                      <ns20:esSubEstado>
                        <xsl:value-of select="ns14:estadoTCC/ns20:esSubEstado"/>
                      </ns20:esSubEstado>
                    </xsl:if>
                  </ns14:estadoTCC>
                  <ns14:subEstadoTCC>
                    <xsl:if test="ns14:subEstadoTCC/ns20:id">
                      <ns20:id>
                        <xsl:value-of select="ns14:subEstadoTCC/ns20:id"/>
                      </ns20:id>
                    </xsl:if>
                    <xsl:if test="ns14:subEstadoTCC/ns20:descripcion">
                      <ns20:descripcion>
                        <xsl:value-of select="ns14:subEstadoTCC/ns20:descripcion"/>
                      </ns20:descripcion>
                    </xsl:if>
                    <xsl:if test="ns14:subEstadoTCC/ns20:descripcionCorta">
                      <ns20:descripcionCorta>
                        <xsl:value-of select="ns14:subEstadoTCC/ns20:descripcionCorta"/>
                      </ns20:descripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns14:subEstadoTCC/ns20:estatus">
                      <ns20:estatus>
                        <xsl:value-of select="ns14:subEstadoTCC/ns20:estatus"/>
                      </ns20:estatus>
                    </xsl:if>
                    <xsl:if test="ns14:subEstadoTCC/ns20:codigoExterno">
                      <ns20:codigoExterno>
                        <xsl:value-of select="ns14:subEstadoTCC/ns20:codigoExterno"/>
                      </ns20:codigoExterno>
                    </xsl:if>
                    <xsl:if test="ns14:subEstadoTCC/ns20:esSubEstado">
                      <ns20:esSubEstado>
                        <xsl:value-of select="ns14:subEstadoTCC/ns20:esSubEstado"/>
                      </ns20:esSubEstado>
                    </xsl:if>
                  </ns14:subEstadoTCC>
                  <ns14:fechaRegistro>
                    <xsl:value-of select="ns14:fechaRegistro"/>
                  </ns14:fechaRegistro>
                  <ns14:estado>
                    <xsl:value-of select="ns14:estado"/>
                  </ns14:estado>
                  <ns14:comisionEnmendada>
                    <xsl:value-of select="ns14:comisionEnmendada"/>
                  </ns14:comisionEnmendada>
                  <ns14:fechaEnmienda>
                    <xsl:value-of select="ns14:fechaEnmienda"/>
                  </ns14:fechaEnmienda>
                  <xsl:if test="ns14:banSugerida">
                    <ns14:banSugerida>
                      <xsl:value-of select="ns14:banSugerida"/>
                    </ns14:banSugerida>
                  </xsl:if>
                  <xsl:if test="ns14:numeroCuentaCliente">
                    <ns14:numeroCuentaCliente>
                      <xsl:value-of select="ns14:numeroCuentaCliente"/>
                    </ns14:numeroCuentaCliente>
                  </xsl:if>
                  <xsl:if test="ns14:observaciones">
                    <ns14:observaciones>
                      <xsl:value-of select="ns14:observaciones"/>
                    </ns14:observaciones>
                  </xsl:if>
                  <xsl:for-each select="ns14:configAtributo">
                    <ns14:configAtributo>
                      <xsl:if test="ns20:id">
                        <ns20:id>
                          <xsl:value-of select="ns20:id"/>
                        </ns20:id>
                      </xsl:if>
                      <ns20:columna>
                        <xsl:value-of select="ns20:columna"/>
                      </ns20:columna>
                      <xsl:if test="ns20:ordenamiento">
                        <ns20:ordenamiento>
                          <xsl:value-of select="ns20:ordenamiento"/>
                        </ns20:ordenamiento>
                      </xsl:if>
                      <xsl:if test="ns20:soloLectura">
                        <ns20:soloLectura>
                          <xsl:value-of select="ns20:soloLectura"/>
                        </ns20:soloLectura>
                      </xsl:if>
                      <xsl:if test="ns20:esObligatorio">
                        <ns20:esObligatorio>
                          <xsl:value-of select="ns20:esObligatorio"/>
                        </ns20:esObligatorio>
                      </xsl:if>
                      <xsl:if test="ns20:etiqueta">
                        <ns20:etiqueta>
                          <xsl:value-of select="ns20:etiqueta"/>
                        </ns20:etiqueta>
                      </xsl:if>
                      <xsl:for-each select="ns20:valor">
                        <ns20:valor>
                          <xsl:value-of select="."/>
                        </ns20:valor>
                      </xsl:for-each>
                      <xsl:if test="ns20:tipoValor">
                        <ns20:tipoValor>
                          <xsl:value-of select="ns20:tipoValor"/>
                        </ns20:tipoValor>
                      </xsl:if>
                      <xsl:for-each select="ns20:catalogo">
                        <ns20:catalogo>
                          <xsl:if test="ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns20:catalogo>
                      </xsl:for-each>
                    </ns14:configAtributo>
                  </xsl:for-each>
                  <xsl:if test="ns14:Accion">
                    <ns14:Accion>
                      <xsl:value-of select="ns14:Accion"/>
                    </ns14:Accion>
                  </xsl:if>
                </ns15:Comision>
              </xsl:for-each>
              <xsl:for-each select="ns15:Fuente">
                <ns15:Fuente>
                  <ns15:idFuente>
                    <xsl:value-of select="ns15:idFuente"/>
                  </ns15:idFuente>
                  <ns15:idLineaCredito>
                    <xsl:value-of select="ns15:idLineaCredito"/>
                  </ns15:idLineaCredito>
                  <ns15:idLineaPasiva>
                    <xsl:value-of select="ns15:idLineaPasiva"/>
                  </ns15:idLineaPasiva>
                  <ns15:codigoLineaPasiva>
                    <xsl:value-of select="ns15:codigoLineaPasiva"/>
                  </ns15:codigoLineaPasiva>
                  <ns15:idFacturadorLineaPasiva>
                    <xsl:value-of select="ns15:idFacturadorLineaPasiva"/>
                  </ns15:idFacturadorLineaPasiva>
                  <ns15:idFondo>
                    <xsl:value-of select="ns15:idFondo"/>
                  </ns15:idFondo>
                  <ns15:Descripcion>
                    <xsl:value-of select="ns15:Descripcion"/>
                  </ns15:Descripcion>
                  <ns15:FechaObtenido>
                    <xsl:value-of select="ns15:FechaObtenido"/>
                  </ns15:FechaObtenido>
                  <ns15:MontoAsignado>
                    <xsl:value-of select="ns15:MontoAsignado"/>
                  </ns15:MontoAsignado>
                  <ns15:montoDisponible>
                    <xsl:value-of select="ns15:montoDisponible"/>
                  </ns15:montoDisponible>
                  <xsl:for-each select="ns15:Monto">
                    <ns15:Monto>
                      <ns10:tipo>
                        <xsl:if test="ns10:tipo/ns16:Id">
                          <ns16:Id>
                            <xsl:value-of select="ns10:tipo/ns16:Id"/>
                          </ns16:Id>
                        </xsl:if>
                        <xsl:if test="ns10:tipo/ns16:Descripcion">
                          <ns16:Descripcion>
                            <xsl:value-of select="ns10:tipo/ns16:Descripcion"/>
                          </ns16:Descripcion>
                        </xsl:if>
                        <xsl:if test="ns10:tipo/ns16:DescripcionCorta">
                          <ns16:DescripcionCorta>
                            <xsl:value-of select="ns10:tipo/ns16:DescripcionCorta"/>
                          </ns16:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns10:tipo/ns16:estatus">
                          <ns16:estatus>
                            <xsl:value-of select="ns10:tipo/ns16:estatus"/>
                          </ns16:estatus>
                        </xsl:if>
                        <xsl:if test="ns10:tipo/ns16:codigoExterno">
                          <ns16:codigoExterno>
                            <xsl:value-of select="ns10:tipo/ns16:codigoExterno"/>
                          </ns16:codigoExterno>
                        </xsl:if>
                      </ns10:tipo>
                      <xsl:if test="ns10:importe">
                        <ns10:importe>
                          <xsl:value-of select="ns10:importe"/>
                        </ns10:importe>
                      </xsl:if>
                      <xsl:if test="ns10:moneda">
                        <ns10:moneda>
                          <xsl:if test="ns10:moneda/ns16:Id">
                            <ns16:Id>
                              <xsl:value-of select="ns10:moneda/ns16:Id"/>
                            </ns16:Id>
                          </xsl:if>
                          <xsl:if test="ns10:moneda/ns16:Descripcion">
                            <ns16:Descripcion>
                              <xsl:value-of select="ns10:moneda/ns16:Descripcion"/>
                            </ns16:Descripcion>
                          </xsl:if>
                          <xsl:if test="ns10:moneda/ns16:DescripcionCorta">
                            <ns16:DescripcionCorta>
                              <xsl:value-of select="ns10:moneda/ns16:DescripcionCorta"/>
                            </ns16:DescripcionCorta>
                          </xsl:if>
                          <xsl:if test="ns10:moneda/ns16:estatus">
                            <ns16:estatus>
                              <xsl:value-of select="ns10:moneda/ns16:estatus"/>
                            </ns16:estatus>
                          </xsl:if>
                          <xsl:if test="ns10:moneda/ns16:codigoExterno">
                            <ns16:codigoExterno>
                              <xsl:value-of select="ns10:moneda/ns16:codigoExterno"/>
                            </ns16:codigoExterno>
                          </xsl:if>
                        </ns10:moneda>
                      </xsl:if>
                    </ns15:Monto>
                  </xsl:for-each>
                  <ns15:NumeroAsignacion>
                    <xsl:value-of select="ns15:NumeroAsignacion"/>
                  </ns15:NumeroAsignacion>
                  <ns15:Comentario>
                    <xsl:value-of select="ns15:Comentario"/>
                  </ns15:Comentario>
                  <ns15:idContrato>
                    <xsl:value-of select="ns15:idContrato"/>
                  </ns15:idContrato>
                  <ns15:FechaRegistro>
                    <xsl:value-of select="ns15:FechaRegistro"/>
                  </ns15:FechaRegistro>
                  <ns15:Estado>
                    <xsl:value-of select="ns15:Estado"/>
                  </ns15:Estado>
                </ns15:Fuente>
              </xsl:for-each>
              <xsl:for-each select="ns15:atributos">
                <ns15:atributos>
                  <ns10:name>
                    <xsl:value-of select="ns10:name"/>
                  </ns10:name>
                  <ns10:value>
                    <xsl:value-of select="ns10:value"/>
                  </ns10:value>
                </ns15:atributos>
              </xsl:for-each>
              <xsl:if test="ns15:HerramientaCE">
                <ns15:HerramientaCE>
                  <ns13:ActividadEconomica>
                    <xsl:if test="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:ActividadEconomica/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns13:ActividadEconomica>
                  <ns13:EjeEstrategico>
                    <xsl:if test="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:EjeEstrategico/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns13:EjeEstrategico>
                  <ns13:AreaFocalizacion>
                    <xsl:if test="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:Id">
                      <ns16:Id>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:Id"/>
                      </ns16:Id>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:Descripcion">
                      <ns16:Descripcion>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:Descripcion"/>
                      </ns16:Descripcion>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:DescripcionCorta">
                      <ns16:DescripcionCorta>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:DescripcionCorta"/>
                      </ns16:DescripcionCorta>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:estatus">
                      <ns16:estatus>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:estatus"/>
                      </ns16:estatus>
                    </xsl:if>
                    <xsl:if test="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:codigoExterno">
                      <ns16:codigoExterno>
                        <xsl:value-of select="ns15:HerramientaCE/ns13:AreaFocalizacion/ns16:codigoExterno"/>
                      </ns16:codigoExterno>
                    </xsl:if>
                  </ns13:AreaFocalizacion>
                </ns15:HerramientaCE>
              </xsl:if>
            </ns18:LineaCredito>
          </xsl:for-each>
        </ns8:contrato>
      </ns0:Operacion>
      <xsl:for-each select="$ConsultarTransitoGrupoPais_OutputVariable.ConsultarTransitoGrupoPaisResponseMessage/ns0:ConsultarTransitoGrupoPaisResponse/ns0:Montos">
        <ns0:Monto>
          <ns10:tipo>
            <xsl:if test="ns10:tipo/ns16:Id">
              <ns16:Id>
                <xsl:value-of select="ns10:tipo/ns16:Id"/>
              </ns16:Id>
            </xsl:if>
            <xsl:if test="ns10:tipo/ns16:Descripcion">
              <ns16:Descripcion>
                <xsl:value-of select="ns10:tipo/ns16:Descripcion"/>
              </ns16:Descripcion>
            </xsl:if>
            <xsl:if test="ns10:tipo/ns16:DescripcionCorta">
              <ns16:DescripcionCorta>
                <xsl:value-of select="ns10:tipo/ns16:DescripcionCorta"/>
              </ns16:DescripcionCorta>
            </xsl:if>
            <xsl:if test="ns10:tipo/ns16:estatus">
              <ns16:estatus>
                <xsl:value-of select="ns10:tipo/ns16:estatus"/>
              </ns16:estatus>
            </xsl:if>
            <xsl:if test="ns10:tipo/ns16:codigoExterno">
              <ns16:codigoExterno>
                <xsl:value-of select="ns10:tipo/ns16:codigoExterno"/>
              </ns16:codigoExterno>
            </xsl:if>
          </ns10:tipo>
          <xsl:if test="ns10:importe">
            <ns10:importe>
              <xsl:value-of select="ns10:importe"/>
            </ns10:importe>
          </xsl:if>
          <xsl:if test="ns10:moneda">
            <ns10:moneda>
              <xsl:if test="ns10:moneda/ns16:Id">
                <ns16:Id>
                  <xsl:value-of select="ns10:moneda/ns16:Id"/>
                </ns16:Id>
              </xsl:if>
              <xsl:if test="ns10:moneda/ns16:Descripcion">
                <ns16:Descripcion>
                  <xsl:value-of select="ns10:moneda/ns16:Descripcion"/>
                </ns16:Descripcion>
              </xsl:if>
              <xsl:if test="ns10:moneda/ns16:DescripcionCorta">
                <ns16:DescripcionCorta>
                  <xsl:value-of select="ns10:moneda/ns16:DescripcionCorta"/>
                </ns16:DescripcionCorta>
              </xsl:if>
              <xsl:if test="ns10:moneda/ns16:estatus">
                <ns16:estatus>
                  <xsl:value-of select="ns10:moneda/ns16:estatus"/>
                </ns16:estatus>
              </xsl:if>
              <xsl:if test="ns10:moneda/ns16:codigoExterno">
                <ns16:codigoExterno>
                  <xsl:value-of select="ns10:moneda/ns16:codigoExterno"/>
                </ns16:codigoExterno>
              </xsl:if>
            </ns10:moneda>
          </xsl:if>
        </ns0:Monto>
      </xsl:for-each>
    </ns0:ValidarLimitesLIMRequest>
  </xsl:template>
</xsl:stylesheet>
