<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:ns0="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionEnmiendasProcess" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket" xmlns:BCIESH="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1" xmlns:eqTr="http://www.bcie.org/EqipoTrabajo/V1" xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1" xmlns:ns1="http://www.bcie.org/CatalogoBO" xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1" xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1" xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1" xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
   <oracle-xsl-mapper:schema>
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA15/FormalizacionEnmiendasProcess.xsd"/>
            <oracle-xsl-mapper:rootElement name="InicioFormalizacionEnmiendas" namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionEnmiendasProcess"/>
            <oracle-xsl-mapper:param name="inicioFormalizacionEnmiendas"/>
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
               <ns2:CodigoOperacion>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:CodigoOperacion"/>
               </ns2:CodigoOperacion>
               <ns2:NombreOperacion>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:NombreOperacion"/>
               </ns2:NombreOperacion>
               <ns2:ResponsableOperacion>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:ResponsableOperacion"/>
               </ns2:ResponsableOperacion>
               <ns2:CodigoCliente>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:CodigoCliente"/>
               </ns2:CodigoCliente>
               <ns2:NombreCliente>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:NombreCliente"/>
               </ns2:NombreCliente>
               <ns2:CodigoProducto>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:CodigoProducto"/>
               </ns2:CodigoProducto>
               <ns2:NombreProducto>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:NombreProducto"/>
               </ns2:NombreProducto>
               <ns2:MontoSolicitado>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:MontoSolicitado"/>
               </ns2:MontoSolicitado>
               <ns2:Pais>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Operacion/ns2:Pais"/>
               </ns2:Pais>
            </BCIESH:Operacion>
            <BCIESH:Tarea>
               <ns6:CodigoProceso>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Tarea/ns6:CodigoProceso"/>
               </ns6:CodigoProceso>
            </BCIESH:Tarea>
            <BCIESH:Proceso>
               <ns3:IdProceso>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Proceso/ns3:IdProceso"/>
               </ns3:IdProceso>
               <ns3:CodigoProceso>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Proceso/ns3:CodigoProceso"/>
               </ns3:CodigoProceso>
               <ns3:NombreProceso>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Proceso/ns3:NombreProceso"/>
               </ns3:NombreProceso>
               <ns3:EsProcesoCore>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Proceso/ns3:EsProcesoCore"/>
               </ns3:EsProcesoCore>
               <ns3:RolIniciaProceso>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Proceso/ns3:RolIniciaProceso"/>
               </ns3:RolIniciaProceso>
               <ns3:UsuarioIniciaProceso>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Proceso/ns3:UsuarioIniciaProceso"/>
               </ns3:UsuarioIniciaProceso>
               <ns3:InstanciaProceso>
                  <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/BCIESH:Proceso/ns3:InstanciaProceso"/>
               </ns3:InstanciaProceso>
            </BCIESH:Proceso>
            <xsl:for-each select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/ns5:ParameterType">
               <ns5:ParameterType>
                  <ns5:parameterName>
                     <xsl:value-of select="ns5:parameterName"/>
                  </ns5:parameterName>
                  <ns5:parameterValue>
                     <xsl:value-of select="ns5:parameterValue"/>
                  </ns5:parameterValue>
               </ns5:ParameterType>
            </xsl:for-each>
         </tns:Header>
         <tns:ConfigGateways>
            <tns:requiereDocumentacionContractual>
               <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:ConfigGateways/ns0:requiereDocumentacionContractual"/>
            </tns:requiereDocumentacionContractual>
            <tns:requiereCOF>
               <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:ConfigGateways/ns0:requiereCOF"/>
            </tns:requiereCOF>
            <tns:esDesobligacion>
               <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:ConfigGateways/ns0:esDesobligacion"/>
            </tns:esDesobligacion>
            <tns:esCambioPlazo>
               <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:ConfigGateways/ns0:esCambioPlazo"/>
            </tns:esCambioPlazo>
         </tns:ConfigGateways>
         <xsl:if test="string-length (/ns0:InicioFormalizacionEnmiendas/ns0:idEnmienda ) > 0">
            <tns:idEnmienda>
               <xsl:value-of select="/ns0:InicioFormalizacionEnmiendas/ns0:idEnmienda"/>
            </tns:idEnmienda>
         </xsl:if>
         <xsl:for-each select="/ns0:InicioFormalizacionEnmiendas/ns0:Header/ns5:ParameterType">
            <xsl:if test="ns5:parameterName = &quot;IDENMIENDA&quot;">
               <tns:idEnmienda>
                  <xsl:value-of select="ns5:parameterValue"/>
               </tns:idEnmienda>
            </xsl:if>
         </xsl:for-each>
         <tns:OrigenFormalizacionEnmiendas/>
      </tns:FormalizacionEnmiendasPayload>
   </xsl:template>
</xsl:stylesheet>
