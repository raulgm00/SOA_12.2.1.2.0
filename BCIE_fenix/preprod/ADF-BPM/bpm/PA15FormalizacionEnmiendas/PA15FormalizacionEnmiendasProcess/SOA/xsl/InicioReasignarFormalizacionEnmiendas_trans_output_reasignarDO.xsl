<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:ns0="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionEnmiendasProcess" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket" xmlns:BCIESH="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1" xmlns:eqTr="http://www.bcie.org/EqipoTrabajo/V1" xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1" xmlns:ns1="http://www.bcie.org/CatalogoBO" xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1" xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1" xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1" xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
   <oracle-xsl-mapper:schema>
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA15/FormalizacionEnmiendasProcess.xsd"/>
            <oracle-xsl-mapper:rootElement name="InicioReasignarFormalizacionEnmiendas" namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionEnmiendasProcess"/>
            <oracle-xsl-mapper:param name="inicioReasignarFormalizacionEnmiendas"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA15/FormalizacionEnmiendasPayload.xsd"/>
            <oracle-xsl-mapper:rootElement name="FormalizacionEnmiendasPayload" namespace="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
   </oracle-xsl-mapper:schema>
   <xsl:template match="/">
      <tns:FormalizacionEnmiendasPayload>
         <tns:Header>
            <BCIESH:Operacion>
               <ns2:ResponsableOperacion>
                  <xsl:value-of select="/ns0:InicioReasignarFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:ResponsableOperacion"/>
               </ns2:ResponsableOperacion>
            </BCIESH:Operacion>
         </tns:Header>
      </tns:FormalizacionEnmiendasPayload>
   </xsl:template>
</xsl:stylesheet>
