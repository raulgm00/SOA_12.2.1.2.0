<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:tns="http://xmlns.oracle.com/bpmn/bpmnProcess/ElegibilidadProcess"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Elegibilidad/Payload/V1"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 tns oraxsl xp20 xref mhdr oraext dvm socket"
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
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC01/ElegibilidadPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="ElegibilidadPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Elegibilidad/Payload/V1"/>
        <oracle-xsl-mapper:param name="elegibilidadDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC01/ElegibilidadProcess.xsd"/>
        <oracle-xsl-mapper:rootElement name="FinElegibilidad"
                                       namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/ElegibilidadProcess"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [WED NOV 11 17:35:40 CST 2015].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:FinElegibilidad>
      <tns:Header>
        <header:Operacion>
          <ns1:CodigoOperacion>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:CodigoOperacion"/>
          </ns1:CodigoOperacion>
          <ns1:NombreOperacion>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:NombreOperacion"/>
          </ns1:NombreOperacion>
          <ns1:ResponsableOperacion>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion"/>
          </ns1:ResponsableOperacion>
          <ns1:CodigoCliente>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:CodigoCliente"/>
          </ns1:CodigoCliente>
          <ns1:NombreCliente>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:NombreCliente"/>
          </ns1:NombreCliente>
          <ns1:CodigoProducto>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:CodigoProducto"/>
          </ns1:CodigoProducto>
          <ns1:NombreProducto>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:NombreProducto"/>
          </ns1:NombreProducto>
          <ns1:MontoSolicitado>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:MontoSolicitado"/>
          </ns1:MontoSolicitado>
          <ns1:Pais>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:Pais"/>
          </ns1:Pais>
        </header:Operacion>
        <header:Proceso>
          <ns4:IdProceso>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Proceso/ns4:IdProceso"/>
          </ns4:IdProceso>
          <ns4:CodigoProceso>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Proceso/ns4:CodigoProceso"/>
          </ns4:CodigoProceso>
          <ns4:NombreProceso>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Proceso/ns4:NombreProceso"/>
          </ns4:NombreProceso>
          <ns4:EsProcesoCore>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Proceso/ns4:EsProcesoCore"/>
          </ns4:EsProcesoCore>
          <ns4:RolIniciaProceso>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Proceso/ns4:RolIniciaProceso"/>
          </ns4:RolIniciaProceso>
          <ns4:UsuarioIniciaProceso>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Proceso/ns4:UsuarioIniciaProceso"/>
          </ns4:UsuarioIniciaProceso>
          <ns4:InstanciaProceso>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Proceso/ns4:InstanciaProceso"/>
          </ns4:InstanciaProceso>
          <ns4:IdFlujo>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Proceso/ns4:IdFlujo"/>
          </ns4:IdFlujo>
        </header:Proceso>
        <parameter:ParameterType>
          <parameter:parameterName>PROCESO</parameter:parameterName>
          <parameter:parameterValue>PC02</parameter:parameterValue>
        </parameter:ParameterType>
      </tns:Header>
      <tns:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Abogado</eqTr:DescripcionRol>
          <eqTr:IdRol>11</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_ASJUR</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>1</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:EquipoEjecucion/ns0:abogado"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>GERIES</eqTr:DescripcionRol>
          <eqTr:IdRol>16</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_RIESGOS</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>1</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:EquipoEjecucion/ns0:geries"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Gerente de País</eqTr:DescripcionRol>
          <eqTr:IdRol>8</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>FENIX_GERENTE_PAIS</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>1</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:EquipoEjecucion/ns0:gerente"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
        <eqTr:equipoTrabajo>
          <eqTr:DescripcionRol>Responsable Operacion</eqTr:DescripcionRol>
          <eqTr:IdRol>1</eqTr:IdRol>
          <eqTr:DescripcionCortaRol>Responsable Operacion</eqTr:DescripcionCortaRol>
          <eqTr:idProceso>1</eqTr:idProceso>
          <eqTr:usuario>
            <xsl:value-of select="/ns0:ElegibilidadPayload/ns0:Header/header:Operacion/ns1:ResponsableOperacion"/>
          </eqTr:usuario>
        </eqTr:equipoTrabajo>
      </tns:equipoTrabajo>
    </tns:FinElegibilidad>
  </xsl:template>
</xsl:stylesheet>
