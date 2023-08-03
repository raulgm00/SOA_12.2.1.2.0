<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://www.bcie.org/LineaCreditoMO"
                exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 oraxsl xp20 xref mhdr oraext dvm socket"
                xmlns:ns3="http://www.bcie.org/TerminoBO" xmlns:ns4="http://www.bcie.org/ReglaBO"
                xmlns:ns5="http://www.bcie.org/OperacionBO" xmlns:ns7="http://www.bcie.org/CommonBO"
                xmlns:ns8="http://www.bcie.org/ResultBO" xmlns:ns12="http://www.bcie.org/AprobacionBO"
                xmlns:ns14="http://www.bcie.org/LineaCreditoBO" xmlns:ns13="http://www.bcie.org/MatrizTCCBO"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ns1="http://www.bcie.org/CondicionBO" xmlns:ns2="http://www.bcie.org/ProductoBO"
                xmlns:ns6="http://www.bcie.org/DeclaracionJuradaBO" xmlns:ns9="http://www.bcie.org/DocumentoBO"
                xmlns:ns10="http://www.bcie.org/HerramientaCEBO" xmlns:ns11="http://www.bcie.org/ComisionBO"
                xmlns:ns15="http://www.bcie.org/CatalogoBO" xmlns:ns16="http://www.bcie.org/DesembolsoBO"
                xmlns:ns17="http://www.bcie.org/ContratoBO" xmlns:ns18="http://www.bcie.org/ClienteBO"
                xmlns:tns="http://xmlns.oracle.com/OSB_BCIE/MDS/AplicarAmpliacionSOR"
                xmlns:ns19="http://www.bcie.org/AtributoBO" xmlns:ns20="http://www.bcie.org/ErrorBO">
  <oracle-xsl-mapper:schema>
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Wsdl/BPEL/AplicarAmpliacionSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="AplicarAmpliacionRequest" namespace="http://www.bcie.org/LineaCreditoMO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Wsdl/BPEL/AplicarAmpliacionSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConsultarListaLineasCreditoRequest"
                                       namespace="http://www.bcie.org/LineaCreditoMO"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
  </oracle-xsl-mapper:schema>
  <xsl:template match="/">
    <ns0:ConsultarListaLineasCreditoRequest>
      <xsl:for-each select="/ns0:AplicarAmpliacionRequest/ns0:LineaCredito">
        <ns0:idLineaCredito>
          <xsl:value-of select="ns14:idLineaCredito"/>
        </ns0:idLineaCredito>
      </xsl:for-each>
    </ns0:ConsultarListaLineasCreditoRequest>
  </xsl:template>
</xsl:stylesheet>
