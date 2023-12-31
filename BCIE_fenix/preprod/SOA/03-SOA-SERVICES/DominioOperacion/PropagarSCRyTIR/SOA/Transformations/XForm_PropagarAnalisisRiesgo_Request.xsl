<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://www.bcie.org/OperacionMO"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:tns="http://www.bcie.org/ClienteMO"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns1="http://www.bcie.org/CrearBitacoraProcesoMO"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 ns1 tns xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:ns3="http://xmlns.bcie.com/OperacionService/types" xmlns:ns6="http://www.bcie.org/TerminoBO"
                xmlns:ns7="http://www.bcie.org/ReglaBO" xmlns:ns8="http://www.bcie.org/OperacionBO"
                xmlns:ns11="http://www.bcie.org/CommonBO" xmlns:ns10="http://www.bcie.org/ResultBO"
                xmlns:ns24="http://www.bcie.org/CrearBitacoraProcesoBO" xmlns:ns16="http://www.bcie.org/LineaCreditoBO"
                xmlns:ns15="http://www.bcie.org/MatrizTCCBO" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:ns25="http://www.bcie.org/ConsultarBitacoraProcesoSCR/types"
                xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:ns2="http://xmlns.bcie.com/OperacionService"
                xmlns:ns4="http://www.bcie.org/ProductoBO" xmlns:ns5="http://www.bcie.org/CondicionBO"
                xmlns:ns9="http://www.bcie.org/DeclaracionJuradaBO" xmlns:ns12="http://www.bcie.org/DocumentoBO"
                xmlns:ns13="http://www.bcie.org/HerramientaCEBO" xmlns:ns14="http://www.bcie.org/ComisionBO"
                xmlns:ns23="http://www.bcie.org/ConsultarBitacoraProcesoSCR" xmlns:ns17="http://www.bcie.org/CatalogoBO"
                xmlns:ns18="http://www.bcie.org/DesembolsoBO" xmlns:ns19="http://www.bcie.org/ContratoBO"
                xmlns:ns20="http://www.bcie.org/ClienteBO" xmlns:ns21="http://www.bcie.org/AtributoBO"
                xmlns:ns22="http://www.bcie.org/ErrorBO" xmlns:ns26="http://xmlns.bcie.com/ClienteService"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Wsdl/BPEL/PropagarSCRyTIRSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConsultarOperacionResponse" namespace="http://www.bcie.org/OperacionMO"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Wsdl/ConsultarBitacoraProcesoSCR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="consultarBitacoraProcesoSCRResponse"
                                       namespace="http://www.bcie.org/CrearBitacoraProcesoMO"/>
        <oracle-xsl-mapper:param name="outConsultarBitacoraProcesoSCR.response"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Wsdl/Cliente.wsdl"/>
        <oracle-xsl-mapper:rootElement name="PropagarAnalisisRiesgoRequest" namespace="http://www.bcie.org/ClienteMO"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [WED DEC 14 12:58:15 CST 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="outConsultarBitacoraProcesoSCR.response"/>
  <xsl:template match="/">
    <tns:PropagarAnalisisRiesgoRequest>
      <tns:Cliente>
        <ns20:idCliente>
          <xsl:value-of select="/ns0:ConsultarOperacionResponse/ns0:Operacion/ns8:cliente/ns20:idCliente"/>
        </ns20:idCliente>
        <ns20:idFacturador>
          <xsl:value-of select="/ns0:ConsultarOperacionResponse/ns0:Operacion/ns8:cliente/ns20:idFacturador"/>
        </ns20:idFacturador>
        <ns20:SCR>
          <ns17:Id>
            <xsl:value-of select="/ns0:ConsultarOperacionResponse/ns0:Operacion/ns8:SRC/ns17:Id"/>
          </ns17:Id>
          <ns17:DescripcionCorta>
            <xsl:value-of select="/ns0:ConsultarOperacionResponse/ns0:Operacion/ns8:SRC/ns17:DescripcionCorta"/>
          </ns17:DescripcionCorta>
        </ns20:SCR>
        <ns20:perspectiva>
          <ns17:Id>
            <xsl:value-of select="/ns0:ConsultarOperacionResponse/ns0:Operacion/ns8:perspectiva/ns17:Id"/>
          </ns17:Id>
          <ns17:DescripcionCorta>
            <xsl:value-of select="/ns0:ConsultarOperacionResponse/ns0:Operacion/ns8:perspectiva/ns17:DescripcionCorta"/>
          </ns17:DescripcionCorta>
        </ns20:perspectiva>
      </tns:Cliente>
      <tns:observacion></tns:observacion>
      <tns:usuarioModifico>
        <xsl:value-of select="$outConsultarBitacoraProcesoSCR.response/ns1:consultarBitacoraProcesoSCRResponse/ns1:Bitacora[ns24:idTarea=20 or ns24:idTarea=21][1]/ns24:usuario"/>
      </tns:usuarioModifico>
      <tns:usuarioValido>
        <xsl:value-of select="$outConsultarBitacoraProcesoSCR.response/ns1:consultarBitacoraProcesoSCRResponse/ns1:Bitacora[ns24:idTarea=22][1]/ns24:usuario"/>
      </tns:usuarioValido>
    </tns:PropagarAnalisisRiesgoRequest>
  </xsl:template>
</xsl:stylesheet>
