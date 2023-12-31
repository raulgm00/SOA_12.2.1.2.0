<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Evaluaciones/Payload/V1"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:ns1="http://xmlns.oracle.com/bpel/workflow/task"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 ns1 oraxsl xp20 xref mhdr oraext dvm socket"
                xmlns:ns7="http://xmlns.oracle.com/bpel/workflow/common"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:evidence="http://xmlns.oracle.com/bpel/workflow/TaskEvidenceService"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:jaxb="http://java.sun.com/xml/ns/jaxb" xmlns:ns2="http://www.bcie.org/CatalogoBO"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA10/EvaluacionPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="EvaluacionPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Evaluaciones/Payload/V1"/>
        <oracle-xsl-mapper:param name="evaluacionPayload"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/soa/shared/workflow/WorkflowTask.xsd"/>
        <oracle-xsl-mapper:rootElement name="task" namespace="http://xmlns.oracle.com/bpel/workflow/task"/>
        <oracle-xsl-mapper:param name="execData"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA10/EvaluacionPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="EvaluacionPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Evaluaciones/Payload/V1"/>
        <oracle-xsl-mapper:param name="evaluacionDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA10/EvaluacionPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="EvaluacionPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Evaluaciones/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [WED NOV 16 16:22:25 CST 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="execData"/>
  <xsl:param name="evaluacionDO"/>
  <xsl:template match="/">
    <ns0:EvaluacionPayload>
      <ns0:Header>
        <header:Operacion>
          <ns3:CodigoOperacion>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns3:CodigoOperacion"/>
          </ns3:CodigoOperacion>
          <ns3:NombreOperacion>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns3:NombreOperacion"/>
          </ns3:NombreOperacion>
          <ns3:ResponsableOperacion>
            <xsl:value-of select="$evaluacionDO/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns3:ResponsableOperacion"/>
          </ns3:ResponsableOperacion>
          <ns3:CodigoCliente>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns3:CodigoCliente"/>
          </ns3:CodigoCliente>
          <ns3:NombreCliente>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns3:NombreCliente"/>
          </ns3:NombreCliente>
          <ns3:CodigoProducto>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns3:CodigoProducto"/>
          </ns3:CodigoProducto>
          <ns3:NombreProducto>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns3:NombreProducto"/>
          </ns3:NombreProducto>
          <ns3:MontoSolicitado>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns3:MontoSolicitado"/>
          </ns3:MontoSolicitado>
          <ns3:Pais>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Operacion/ns3:Pais"/>
          </ns3:Pais>
        </header:Operacion>
        <header:Tarea>
          <ns6:CodigoTarea>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Tarea/ns6:CodigoTarea"/>
          </ns6:CodigoTarea>
          <ns6:NombreTarea>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Tarea/ns6:NombreTarea"/>
          </ns6:NombreTarea>
          <ns6:CodigoRol>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Tarea/ns6:CodigoRol"/>
          </ns6:CodigoRol>
          <ns6:NombreRol>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Tarea/ns6:NombreRol"/>
          </ns6:NombreRol>
          <ns6:CodigoProceso>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Tarea/ns6:CodigoProceso"/>
          </ns6:CodigoProceso>
          <ns6:NombreProceso>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Tarea/ns6:NombreProceso"/>
          </ns6:NombreProceso>
        </header:Tarea>
        <header:Proceso>
          <ns4:IdProceso>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Proceso/ns4:IdProceso"/>
          </ns4:IdProceso>
          <ns4:CodigoProceso>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Proceso/ns4:CodigoProceso"/>
          </ns4:CodigoProceso>
          <ns4:NombreProceso>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Proceso/ns4:NombreProceso"/>
          </ns4:NombreProceso>
          <ns4:EsProcesoCore>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Proceso/ns4:EsProcesoCore"/>
          </ns4:EsProcesoCore>
          <ns4:RolIniciaProceso>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Proceso/ns4:RolIniciaProceso"/>
          </ns4:RolIniciaProceso>
          <ns4:UsuarioIniciaProceso>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Proceso/ns4:UsuarioIniciaProceso"/>
          </ns4:UsuarioIniciaProceso>
          <ns4:InstanciaProceso>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Proceso/ns4:InstanciaProceso"/>
          </ns4:InstanciaProceso>
          <ns4:IdFlujo>
            <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Header/header:Proceso/ns4:IdFlujo"/>
          </ns4:IdFlujo>
        </header:Proceso>
        <xsl:for-each select="/ns0:EvaluacionPayload/ns0:Header/parameter:ParameterType">
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
      <ns0:Configuration>
        <ns0:requiereSIEMAS>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:requiereSIEMAS"/>
        </ns0:requiereSIEMAS>
        <ns0:requiereIBCIE>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:requiereIBCIE"/>
        </ns0:requiereIBCIE>
        <ns0:requiereModificarSIEMAS>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:requiereModificarSIEMAS"/>
        </ns0:requiereModificarSIEMAS>
        <ns0:requiereModificarIBCIE>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:requiereModificarIBCIE"/>
        </ns0:requiereModificarIBCIE>
        <ns0:retorno>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:retorno"/>
        </ns0:retorno>
        <ns0:resultado>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:resultado"/>
        </ns0:resultado>
        <ns0:tipoEvaluacion>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:tipoEvaluacion"/>
        </ns0:tipoEvaluacion>
        <ns0:eventoEvaluacion>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:Configuration/ns0:eventoEvaluacion"/>
        </ns0:eventoEvaluacion>
      </ns0:Configuration>
      <ns0:EquipoPayload>
        <ns0:AnalistaAED>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoPayload/ns0:AnalistaAED"/>
        </ns0:AnalistaAED>
        <ns0:AnalistaSupervision>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoPayload/ns0:AnalistaSupervision"/>
        </ns0:AnalistaSupervision>
        <ns0:ValidadorIBCIE>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoPayload/ns0:ValidadorIBCIE"/>
        </ns0:ValidadorIBCIE>
        <ns0:AnalistaODE>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoPayload/ns0:AnalistaODE"/>
        </ns0:AnalistaODE>
        <ns0:JefeODE>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoPayload/ns0:JefeODE"/>
        </ns0:JefeODE>
        <ns0:ValidadorSIEMAS>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoPayload/ns0:ValidadorSIEMAS"/>
        </ns0:ValidadorSIEMAS>
      </ns0:EquipoPayload>
      <ns0:EquipoEjecucionPayload>
        <ns0:AnalistaAED>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoEjecucionPayload/ns0:AnalistaAED"/>
        </ns0:AnalistaAED>
        <ns0:AnalistaSupervision>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoEjecucionPayload/ns0:AnalistaSupervision"/>
        </ns0:AnalistaSupervision>
        <ns0:ValidadorIBCIE>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoEjecucionPayload/ns0:ValidadorIBCIE"/>
        </ns0:ValidadorIBCIE>
        <ns0:AnalistaODE>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoEjecucionPayload/ns0:AnalistaODE"/>
        </ns0:AnalistaODE>
        <ns0:JefeODE>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoEjecucionPayload/ns0:JefeODE"/>
        </ns0:JefeODE>
        <ns0:ValidadorSIEMAS>
          <xsl:value-of select="/ns0:EvaluacionPayload/ns0:EquipoEjecucionPayload/ns0:ValidadorSIEMAS"/>
        </ns0:ValidadorSIEMAS>
      </ns0:EquipoEjecucionPayload>
      <xsl:for-each select="/ns0:EvaluacionPayload/parameter:ParameterType">
        <parameter:ParameterType>
          <parameter:parameterName>
            <xsl:value-of select="parameter:parameterName"/>
          </parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="parameter:parameterValue"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </xsl:for-each>
    </ns0:EvaluacionPayload>
  </xsl:template>
</xsl:stylesheet>
