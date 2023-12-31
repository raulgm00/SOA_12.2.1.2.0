<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tns="http://xmlns.oracle.com/bpmn/bpmnProcess/ComisionesProcess"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Comisiones/Payload/V1"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:notificacion="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:ns1="http://www.bcie.org/CatalogoBO"
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:eqTr="http://www.bcie.org/EqipoTrabajo/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA02/ComisionPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="ComisionPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Comisiones/Payload/V1"/>
        <oracle-xsl-mapper:param name="comisionDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA02/ComisionesProcess.xsd"/>
        <oracle-xsl-mapper:rootElement name="FinComisiones"
                                       namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/ComisionesProcess"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [FRI MAY 19 13:13:28 CDT 2017].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:FinComisiones>
      <tns:Header>
        <header:Operacion>
          <ns2:CodigoOperacion>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:CodigoOperacion"/>
          </ns2:CodigoOperacion>
          <ns2:NombreOperacion>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:NombreOperacion"/>
          </ns2:NombreOperacion>
          <ns2:ResponsableOperacion>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:ResponsableOperacion"/>
          </ns2:ResponsableOperacion>
          <ns2:CodigoCliente>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:CodigoCliente"/>
          </ns2:CodigoCliente>
          <ns2:NombreCliente>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:NombreCliente"/>
          </ns2:NombreCliente>
          <ns2:CodigoProducto>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:CodigoProducto"/>
          </ns2:CodigoProducto>
          <ns2:NombreProducto>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:NombreProducto"/>
          </ns2:NombreProducto>
          <ns2:MontoSolicitado>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:MontoSolicitado"/>
          </ns2:MontoSolicitado>
          <ns2:Pais>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:Pais"/>
          </ns2:Pais>
        </header:Operacion>
        <xsl:for-each select="/ns0:ComisionPayload/ns0:Header/parameter:ParameterType">
          <parameter:ParameterType>
            <parameter:parameterName>
              <xsl:value-of select="parameter:parameterName"/>
            </parameter:parameterName>
            <parameter:parameterValue>
              <xsl:value-of select="parameter:parameterValue"/>
            </parameter:parameterValue>
          </parameter:ParameterType>
        </xsl:for-each>
      </tns:Header>
      <tns:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Analista COFI</eqTr:DescripcionRol>
          <eqTr:IdRol>7</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_ANALISTA_COFI</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>8</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Equipo/ns0:AnalistaCOFI"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Responsable de Operación</eqTr:DescripcionRol>
          <eqTr:IdRol>1</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_REPRESENTANTES</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>8</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:ComisionPayload/ns0:Header/header:Operacion/ns2:ResponsableOperacion"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
      </tns:equipoTrabajo>
    </tns:FinComisiones>
  </xsl:template>
</xsl:stylesheet>
