<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/ReasignacionRC/Payload/V1"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns0="http://xmlns.oracle.com/bpmn/bpmnProcess/ReasignacionRCProcess"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 tns oraxsl xp20 xref mhdr oraext dvm socket"
                xmlns:tns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns1="http://www.bcie.org/CatalogoBO"
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PU02/ReasignacionRCProcess.xsd"/>
        <oracle-xsl-mapper:rootElement name="InicioReasignacionRC"
                                       namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/ReasignacionRCProcess"/>
        <oracle-xsl-mapper:param name="inicioReasignacionRC"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PU02/ReasignacionRCPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="ReasignacionRCPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/ReasignacionRC/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [FRI JUN 30 17:43:30 CDT 2017].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:ReasignacionRCPayload>
      <tns:Header>
        <tns6:Cliente>
          <ns4:IdCliente>
            <xsl:value-of select="/ns0:InicioReasignacionRC/ns0:Header/tns6:Cliente/ns4:IdCliente"/>
          </ns4:IdCliente>
          <ns4:ResponsableCliente>
            <xsl:value-of select="/ns0:InicioReasignacionRC/ns0:Header/tns6:Cliente/ns4:ResponsableCliente"/>
          </ns4:ResponsableCliente>
        </tns6:Cliente>
        <tns6:Tarea>
          <ns6:CodigoTarea>
            <xsl:value-of select="/ns0:InicioReasignacionRC/ns0:Header/tns6:Tarea/ns6:CodigoTarea"/>
          </ns6:CodigoTarea>
          <ns6:NombreTarea>
            <xsl:value-of select="/ns0:InicioReasignacionRC/ns0:Header/tns6:Tarea/ns6:NombreTarea"/>
          </ns6:NombreTarea>
          <ns6:CodigoRol>
            <xsl:value-of select="/ns0:InicioReasignacionRC/ns0:Header/tns6:Tarea/ns6:CodigoRol"/>
          </ns6:CodigoRol>
          <ns6:NombreRol>
            <xsl:value-of select="/ns0:InicioReasignacionRC/ns0:Header/tns6:Tarea/ns6:NombreRol"/>
          </ns6:NombreRol>
          <ns6:CodigoProceso>34</ns6:CodigoProceso>
          <ns6:NombreProceso>
            <xsl:value-of select="/ns0:InicioReasignacionRC/ns0:Header/tns6:Tarea/ns6:NombreProceso"/>
          </ns6:NombreProceso>
        </tns6:Tarea>
        <tns6:Proceso>
          <ns3:IdProceso>34</ns3:IdProceso>
        </tns6:Proceso>
      </tns:Header>
      <xsl:for-each select="/ns0:InicioReasignacionRC/ns0:Header/ns5:ParameterType">
        <ns5:ParameterType>
          <ns5:parameterName>
            <xsl:value-of select="ns5:parameterName"/>
          </ns5:parameterName>
          <ns5:parameterValue>
            <xsl:value-of select="ns5:parameterValue"/>
          </ns5:parameterValue>
        </ns5:ParameterType>
      </xsl:for-each>
    </tns:ReasignacionRCPayload>
  </xsl:template>
</xsl:stylesheet>
