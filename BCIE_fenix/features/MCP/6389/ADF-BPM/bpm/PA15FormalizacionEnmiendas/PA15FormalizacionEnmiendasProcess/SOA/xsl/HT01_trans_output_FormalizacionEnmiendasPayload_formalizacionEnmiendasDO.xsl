<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:ns1="http://xmlns.oracle.com/bpel/workflow/task" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 ns1 oraxsl xp20 xref mhdr oraext dvm socket" xmlns:ns7="http://xmlns.oracle.com/bpel/workflow/common" xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1" xmlns:evidence="http://xmlns.oracle.com/bpel/workflow/TaskEvidenceService" xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1" xmlns:jaxb="http://java.sun.com/xml/ns/jaxb" xmlns:ns2="http://www.bcie.org/CatalogoBO" xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1" xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1" xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1" xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
   <oracle-xsl-mapper:schema>
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA15/FormalizacionEnmiendasPayload.xsd"/>
            <oracle-xsl-mapper:rootElement name="FormalizacionEnmiendasPayload" namespace="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1"/>
            <oracle-xsl-mapper:param name="formalizacionEnmiendasPayload"/>
         </oracle-xsl-mapper:source>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="oramds:/soa/shared/workflow/WorkflowTask.xsd"/>
            <oracle-xsl-mapper:rootElement name="task" namespace="http://xmlns.oracle.com/bpel/workflow/task"/>
            <oracle-xsl-mapper:param name="execData"/>
         </oracle-xsl-mapper:source>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA15/FormalizacionEnmiendasPayload.xsd"/>
            <oracle-xsl-mapper:rootElement name="FormalizacionEnmiendasPayload" namespace="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1"/>
            <oracle-xsl-mapper:param name="formalizacionEnmiendasDO"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA15/FormalizacionEnmiendasPayload.xsd"/>
            <oracle-xsl-mapper:rootElement name="FormalizacionEnmiendasPayload" namespace="http://xmlns.bcie.org/ObjetoProceso/FormalizacionEnmiendas/Payload/V1"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
   </oracle-xsl-mapper:schema>
   <xsl:param name="execData"/>
   <xsl:param name="formalizacionEnmiendasDO"/>
   <xsl:template match="/">
      <ns0:FormalizacionEnmiendasPayload>
         <ns0:Header>
            <header:Operacion>
               <ns3:CodigoOperacion>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Operacion/ns3:CodigoOperacion"/>
               </ns3:CodigoOperacion>
               <ns3:NombreOperacion>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Operacion/ns3:NombreOperacion"/>
               </ns3:NombreOperacion>
               <ns3:ResponsableOperacion>
                  <xsl:value-of select="$formalizacionEnmiendasDO/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Operacion/ns3:ResponsableOperacion"/>
               </ns3:ResponsableOperacion>
               <ns3:CodigoCliente>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Operacion/ns3:CodigoCliente"/>
               </ns3:CodigoCliente>
               <ns3:NombreCliente>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Operacion/ns3:NombreCliente"/>
               </ns3:NombreCliente>
               <ns3:CodigoProducto>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Operacion/ns3:CodigoProducto"/>
               </ns3:CodigoProducto>
               <ns3:NombreProducto>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Operacion/ns3:NombreProducto"/>
               </ns3:NombreProducto>
               <ns3:MontoSolicitado>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Operacion/ns3:MontoSolicitado"/>
               </ns3:MontoSolicitado>
               <ns3:Pais>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Operacion/ns3:Pais"/>
               </ns3:Pais>
            </header:Operacion>
            <header:Tarea>
               <ns6:CodigoTarea>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Tarea/ns6:CodigoTarea"/>
               </ns6:CodigoTarea>
               <ns6:NombreTarea>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Tarea/ns6:NombreTarea"/>
               </ns6:NombreTarea>
               <ns6:CodigoRol>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Tarea/ns6:CodigoRol"/>
               </ns6:CodigoRol>
               <ns6:NombreRol>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Tarea/ns6:NombreRol"/>
               </ns6:NombreRol>
               <ns6:CodigoProceso>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Tarea/ns6:CodigoProceso"/>
               </ns6:CodigoProceso>
               <ns6:NombreProceso>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Tarea/ns6:NombreProceso"/>
               </ns6:NombreProceso>
            </header:Tarea>
            <header:Proceso>
               <ns4:IdProceso>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Proceso/ns4:IdProceso"/>
               </ns4:IdProceso>
               <ns4:CodigoProceso>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Proceso/ns4:CodigoProceso"/>
               </ns4:CodigoProceso>
               <ns4:NombreProceso>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Proceso/ns4:NombreProceso"/>
               </ns4:NombreProceso>
               <ns4:EsProcesoCore>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Proceso/ns4:EsProcesoCore"/>
               </ns4:EsProcesoCore>
               <ns4:RolIniciaProceso>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Proceso/ns4:RolIniciaProceso"/>
               </ns4:RolIniciaProceso>
               <ns4:UsuarioIniciaProceso>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Proceso/ns4:UsuarioIniciaProceso"/>
               </ns4:UsuarioIniciaProceso>
               <ns4:InstanciaProceso>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Proceso/ns4:InstanciaProceso"/>
               </ns4:InstanciaProceso>
               <ns4:IdFlujo>
                  <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/header:Proceso/ns4:IdFlujo"/>
               </ns4:IdFlujo>
            </header:Proceso>
            <xsl:for-each select="/ns0:FormalizacionEnmiendasPayload/ns0:Header/parameter:ParameterType">
               <parameter:ParameterType>
                  <parameter:parameterName>
                     <xsl:value-of select="parameter:parameterName"/>
                  </parameter:parameterName>
                  <parameter:parameterValue>
                     <xsl:value-of select="parameter:parameterValue"/>
                  </parameter:parameterValue>
               </parameter:ParameterType>
            </xsl:for-each>
         </ns0:Header>
         <ns0:ConfigGateways>
            <ns0:requiereDocumentacionContractual>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:ConfigGateways/ns0:requiereDocumentacionContractual"/>
            </ns0:requiereDocumentacionContractual>
            <ns0:requiereCOF>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:ConfigGateways/ns0:requiereCOF"/>
            </ns0:requiereCOF>
            <ns0:esDesobligacion>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:ConfigGateways/ns0:esDesobligacion"/>
            </ns0:esDesobligacion>
            <ns0:esCambioPlazo>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:ConfigGateways/ns0:esCambioPlazo"/>
            </ns0:esCambioPlazo>
         </ns0:ConfigGateways>
         <ns0:Equipo>
            <ns0:abogado>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Equipo/ns0:abogado"/>
            </ns0:abogado>
            <ns0:analistaCOFI>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Equipo/ns0:analistaCOFI"/>
            </ns0:analistaCOFI>
            <ns0:CustodioCOPRES>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:Equipo/ns0:CustodioCOPRES"/>
            </ns0:CustodioCOPRES>
         </ns0:Equipo>
         <ns0:EquipoEjecucion>
            <ns0:abogado>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:EquipoEjecucion/ns0:abogado"/>
            </ns0:abogado>
            <ns0:analistaCOFI>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:EquipoEjecucion/ns0:analistaCOFI"/>
            </ns0:analistaCOFI>
            <ns0:CustodioCOPRES>
               <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:EquipoEjecucion/ns0:CustodioCOPRES"/>
            </ns0:CustodioCOPRES>
         </ns0:EquipoEjecucion>
         <xsl:for-each select="/ns0:FormalizacionEnmiendasPayload/parameter:ParameterType">
            <parameter:ParameterType>
               <parameter:parameterName>
                  <xsl:value-of select="parameter:parameterName"/>
               </parameter:parameterName>
               <parameter:parameterValue>
                  <xsl:value-of select="parameter:parameterValue"/>
               </parameter:parameterValue>
            </parameter:ParameterType>
         </xsl:for-each>
         <ns0:solicitudContratacion>
            <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:solicitudContratacion"/>
         </ns0:solicitudContratacion>
         <ns0:idEnmienda>
            <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:idEnmienda"/>
         </ns0:idEnmienda>
         <ns0:idContrato>
            <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:idContrato"/>
         </ns0:idContrato>
         <ns0:OrigenFormalizacionEnmiendas>
            <xsl:value-of select="/ns0:FormalizacionEnmiendasPayload/ns0:OrigenFormalizacionEnmiendas"/>
         </ns0:OrigenFormalizacionEnmiendas>
      </ns0:FormalizacionEnmiendasPayload>
   </xsl:template>
</xsl:stylesheet>
