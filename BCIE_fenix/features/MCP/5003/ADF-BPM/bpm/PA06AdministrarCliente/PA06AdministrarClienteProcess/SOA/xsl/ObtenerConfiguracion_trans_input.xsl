<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:tns="http://www.bcie.org/ConfiguracionProcesosMO"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Admincliente/Payload/V1"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:confBO="http://www.bcie.org/ConfiguracionProcesosBO" xmlns:ns1="http://www.bcie.org/TerminoBO"
                xmlns:ns2="http://www.bcie.org/ReglaBO" xmlns:operBO="http://www.bcie.org/OperacionBO"
                xmlns:resBO="http://www.bcie.org/ResultBO" xmlns:cmnBO="http://www.bcie.org/CommonBO"
                xmlns:ns5="http://www.bcie.org/MatrizTCCBO" xmlns:ns3="http://www.bcie.org/CrearBitacoraProcesoBO"
                xmlns:ns4="http://www.bcie.org/LineaCreditoBO"
                xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns7="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns9="http://www.bcie.org/ProductoBO" xmlns:ns8="http://www.bcie.org/CondicionBO"
                xmlns:ns10="http://www.bcie.org/DeclaracionJuradaBO"
                xmlns:ns11="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:ns12="http://www.bcie.org/ImplementacionPctBO" xmlns:ns13="http://www.bcie.org/DocumentoBO"
                xmlns:ns14="http://www.bcie.org/HerramientaCEBO" xmlns:ns15="http://www.bcie.org/ComisionBO"
                xmlns:ns16="http://www.bcie.org/CatalogoBO" xmlns:desBO="http://www.bcie.org/DesembolsoBO"
                xmlns:ns17="http://www.bcie.org/AdquisicionBO" xmlns:ns18="http://www.bcie.org/ContratoBO"
                xmlns:cteBO="http://www.bcie.org/ClienteBO" xmlns:ns19="http://www.bcie.org/AtributoBO"
                xmlns:ns20="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:ns21="http://www.bcie.org/ErrorBO" xml:id="id_1" oraxsl:ignorexmlids="true">
  <oracle-xsl-mapper:schema xml:id="id_2">
    <oracle-xsl-mapper:mapSources xml:id="id_3">
      <oracle-xsl-mapper:source type="XSD" xml:id="id_4">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA06/AdministarCliente.xsd"
                                  xml:id="id_5"/>
        <oracle-xsl-mapper:rootElement name="AdminClientePayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Admincliente/Payload/V1"
                                       xml:id="id_6"/>
        <oracle-xsl-mapper:param name="administrarClienteDO" xml:id="id_7"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets xml:id="id_8">
      <oracle-xsl-mapper:target type="XSD" xml:id="id_9">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd"
                                  xml:id="id_10"/>
        <oracle-xsl-mapper:rootElement name="configuracionAdministracionClienteRequest"
                                       namespace="http://www.bcie.org/ConfiguracionProcesosMO" xml:id="id_11"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
  </oracle-xsl-mapper:schema>
  <xsl:template match="/" xml:id="id_12">
    <tns:configuracionAdministracionClienteRequest xml:id="id_13">
      <tns:idOperacion xml:id="id_14">
        <xsl:value-of select="/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idCliente" xml:id="id_15"/>
      </tns:idOperacion>
    </tns:configuracionAdministracionClienteRequest>
  </xsl:template>
</xsl:stylesheet>
