<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:tns="http://xmlns.oracle.com/bpmn/bpmnProcess/EnmiendasProcess"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Enmiendas/Payload/V1"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:notificacion="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:ns1="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:eqTr="http://www.bcie.org/EqipoTrabajo/V1" xmlns:ns3="http://www.bcie.org/CatalogoBO"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA04/EnmiendaPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="EnmiendaPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Enmiendas/Payload/V1"/>
        <oracle-xsl-mapper:param name="enmiendaDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA04/EnmiendasProcess.xsd"/>
        <oracle-xsl-mapper:rootElement name="FinEnmiendas"
                                       namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/EnmiendasProcess"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [FRI FEB 12 11:49:14 CST 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:FinEnmiendas>
      <tns:Header>
        <header:Operacion>
          <ns1:CodigoOperacion>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:CodigoOperacion"/>
          </ns1:CodigoOperacion>
          <ns1:NombreOperacion>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:NombreOperacion"/>
          </ns1:NombreOperacion>
          <ns1:ResponsableOperacion>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion"/>
          </ns1:ResponsableOperacion>
          <ns1:CodigoCliente>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:CodigoCliente"/>
          </ns1:CodigoCliente>
          <ns1:NombreCliente>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:NombreCliente"/>
          </ns1:NombreCliente>
          <ns1:CodigoProducto>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:CodigoProducto"/>
          </ns1:CodigoProducto>
          <ns1:NombreProducto>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:NombreProducto"/>
          </ns1:NombreProducto>
          <ns1:MontoSolicitado>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:MontoSolicitado"/>
          </ns1:MontoSolicitado>
          <ns1:Pais>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:Pais"/>
          </ns1:Pais>
        </header:Operacion>
        <header:Tarea>
          <ns2:CodigoTarea>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Tarea/ns2:CodigoTarea"/>
          </ns2:CodigoTarea>
          <ns2:NombreTarea>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Tarea/ns2:NombreTarea"/>
          </ns2:NombreTarea>
          <ns2:CodigoRol>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Tarea/ns2:CodigoRol"/>
          </ns2:CodigoRol>
          <ns2:NombreRol>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Tarea/ns2:NombreRol"/>
          </ns2:NombreRol>
          <ns2:CodigoProceso>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Tarea/ns2:CodigoProceso"/>
          </ns2:CodigoProceso>
          <ns2:NombreProceso>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Tarea/ns2:NombreProceso"/>
          </ns2:NombreProceso>
        </header:Tarea>
        <header:Proceso>
          <ns4:IdProceso>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Proceso/ns4:IdProceso"/>
          </ns4:IdProceso>
          <ns4:CodigoProceso>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Proceso/ns4:CodigoProceso"/>
          </ns4:CodigoProceso>
          <ns4:NombreProceso>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Proceso/ns4:NombreProceso"/>
          </ns4:NombreProceso>
          <ns4:EsProcesoCore>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Proceso/ns4:EsProcesoCore"/>
          </ns4:EsProcesoCore>
          <ns4:RolIniciaProceso>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Proceso/ns4:RolIniciaProceso"/>
          </ns4:RolIniciaProceso>
          <ns4:UsuarioIniciaProceso>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Proceso/ns4:UsuarioIniciaProceso"/>
          </ns4:UsuarioIniciaProceso>
          <ns4:InstanciaProceso>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Proceso/ns4:InstanciaProceso"/>
          </ns4:InstanciaProceso>
          <ns4:IdFlujo>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Proceso/ns4:IdFlujo"/>
          </ns4:IdFlujo>
        </header:Proceso>
        <parameter:ParameterType>
          <parameter:parameterName>PROCESO</parameter:parameterName>
          <parameter:parameterValue>PC04</parameter:parameterValue>
        </parameter:ParameterType>
        <parameter:ParameterType>
          <parameter:parameterName>IDENMIENDA</parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:idEnmienda"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </tns:Header>
      <tns:EquipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Responsable de Operación</eqTr:DescripcionRol>
          <eqTr:IdRol>1</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_REPRESENTANTES</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>10</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Gerente de País</eqTr:DescripcionRol>
          <eqTr:IdRol>8</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_GERENTE_PAIS</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>10</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:EquipoEjecucion/ns0:GerentedePais"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Abogado</eqTr:DescripcionRol>
          <eqTr:IdRol>11</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_ASJUR</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>10</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:EquipoEjecucion/ns0:Abogado"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Analista de Crédito</eqTr:DescripcionRol>
          <eqTr:IdRol>3</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_ANALISTA_AECRED</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>10</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:EquipoEjecucion/ns0:AnalistaCredito"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Analista COFI</eqTr:DescripcionRol>
          <eqTr:IdRol>7</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_ANALISTA_COFI</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>10</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:EnmiendaPayload/ns0:EquipoEjecucion/ns0:AnalistaCOFI"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
      </tns:EquipoTrabajo>
    </tns:FinEnmiendas>
  </xsl:template>
</xsl:stylesheet>
