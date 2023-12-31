<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://www.bcie.org/ConfiguracionProcesosMO"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Condiciones/Payload/V1"
                exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 tns oraxsl xp20 xref mhdr oraext dvm socket"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns1="http://www.bcie.org/CatalogoBO"
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:ns6="http://www.bcie.org/TerminoBO" xmlns:confBO="http://www.bcie.org/ConfiguracionProcesosBO"
                xmlns:operBO="http://www.bcie.org/OperacionBO" xmlns:ns7="http://www.bcie.org/CommonBO"
                xmlns:resBO="http://www.bcie.org/ResultBO" xmlns:ns9="http://www.bcie.org/MatrizTCCBO"
                xmlns:ns8="http://www.bcie.org/LineaCreditoBO" xmlns:ns11="http://www.bcie.org/ProductoBO"
                xmlns:ns10="http://www.bcie.org/CondicionBO" xmlns:ns12="http://www.bcie.org/DeclaracionJuradaBO"
                xmlns:ns13="http://www.bcie.org/DocumentoBO" xmlns:ns14="http://www.bcie.org/ComisionBO"
                xmlns:ns15="http://www.bcie.org/DesembolsoBO" xmlns:ns16="http://www.bcie.org/ContratoBO"
                xmlns:cteBO="http://www.bcie.org/ClienteBO" xmlns:ns17="http://www.bcie.org/AtributoBO"
                xmlns:ns18="http://www.bcie.org/ErrorBO"
                xmlns:notificacion="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:ns19="http://www.bcie.org/ReglaBO" xmlns:ns20="http://www.bcie.org/HerramientaCEBO"
                xmlns:ns21="http://www.bcie.org/CrearBitacoraProcesoBO"
                xmlns:ns22="http://www.bcie.org/ImplementacionPctBO" xmlns:ns23="http://www.bcie.org/AdquisicionBO">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA03/CondicionPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="CondicionPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Condiciones/Payload/V1"/>
        <oracle-xsl-mapper:param name="condicionDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd"/>
        <oracle-xsl-mapper:rootElement name="configuracionCondicionesRequest"
                                       namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [WED SEP 07 17:20:36 CDT 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:configuracionCondicionesRequest>
      <tns:idOperacion>
        <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Operacion/ns2:CodigoOperacion"/>
      </tns:idOperacion>
      <tns:Agrupador>
        <xsl:value-of select="/ns0:CondicionPayload/ns0:agrupamiento"/>
      </tns:Agrupador>
      <tns:instanciaProceso>
        <xsl:value-of select="/ns0:CondicionPayload/ns0:Header/header:Proceso/ns3:InstanciaProceso"/>
      </tns:instanciaProceso>
    </tns:configuracionCondicionesRequest>
  </xsl:template>
</xsl:stylesheet>
