<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/ImplementacionPCT/Payload/V1"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:ns0="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:tns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:eqTr="http://www.bcie.org/EqipoTrabajo/V1"
                xmlns:param="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns1="http://www.bcie.org/CatalogoBO"
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns5="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns4="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd"/>
        <oracle-xsl-mapper:rootElement name="InicioEnviarGasto"
                                       namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess"/>
        <oracle-xsl-mapper:param name="inicioEnviarGasto"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA11/ImpPCTPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="ImplementacionPCT"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/ImplementacionPCT/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [TUE AUG 15 00:51:01 CDT 2017].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:ImplementacionPCT>
      <tns:Header>
        <tns6:Operacion>
          <ns2:CodigoOperacion>
            <xsl:value-of select="/ns0:InicioEnviarGasto/ns0:Header/tns6:Operacion/ns2:CodigoOperacion"/>
          </ns2:CodigoOperacion>
          <ns2:NombreOperacion>
            <xsl:value-of select="/ns0:InicioEnviarGasto/ns0:Header/tns6:Operacion/ns2:NombreOperacion"/>
          </ns2:NombreOperacion>
          <ns2:ResponsableOperacion>
            <xsl:value-of select="/ns0:InicioEnviarGasto/ns0:Header/tns6:Operacion/ns2:ResponsableOperacion"/>
          </ns2:ResponsableOperacion>
          <ns2:CodigoCliente>
            <xsl:value-of select="/ns0:InicioEnviarGasto/ns0:Header/tns6:Operacion/ns2:CodigoCliente"/>
          </ns2:CodigoCliente>
          <ns2:NombreCliente>
            <xsl:value-of select="/ns0:InicioEnviarGasto/ns0:Header/tns6:Operacion/ns2:NombreCliente"/>
          </ns2:NombreCliente>
          <ns2:CodigoProducto>
            <xsl:value-of select="/ns0:InicioEnviarGasto/ns0:Header/tns6:Operacion/ns2:CodigoProducto"/>
          </ns2:CodigoProducto>
          <ns2:NombreProducto>
            <xsl:value-of select="/ns0:InicioEnviarGasto/ns0:Header/tns6:Operacion/ns2:NombreProducto"/>
          </ns2:NombreProducto>
          <ns2:MontoSolicitado>
            <xsl:value-of select="/ns0:InicioEnviarGasto/ns0:Header/tns6:Operacion/ns2:MontoSolicitado"/>
          </ns2:MontoSolicitado>
          <ns2:Pais>
            <xsl:value-of select="/ns0:InicioEnviarGasto/ns0:Header/tns6:Operacion/ns2:Pais"/>
          </ns2:Pais>
        </tns6:Operacion>
        <tns6:Tarea>
          <ns5:CodigoTarea/>
          <ns5:NombreTarea/>
          <ns5:CodigoRol/>
          <ns5:NombreRol/>
          <ns5:CodigoProceso/>
          <ns5:NombreProceso/>
        </tns6:Tarea>
        <tns6:Proceso>
          <ns3:IdProceso/>
          <ns3:CodigoProceso/>
          <ns3:NombreProceso/>
          <ns3:EsProcesoCore/>
          <ns3:RolIniciaProceso/>
          <ns3:UsuarioIniciaProceso/>
          <ns3:InstanciaProceso/>
          <ns3:IdFlujo/>
        </tns6:Proceso>
        <xsl:for-each select="/ns0:InicioEnviarGasto/ns0:Header/param:ParameterType">
          <param:ParameterType>
            <param:parameterName>
              <xsl:value-of select="param:parameterName"/>
            </param:parameterName>
            <param:parameterValue>
              <xsl:value-of select="param:parameterValue"/>
            </param:parameterValue>
          </param:ParameterType>
        </xsl:for-each>
      </tns:Header>
      <tns:ConfigGateways>
        <tns:requiereFinCurso/>
        <tns:gestionaCliente/>
        <tns:tipoConcurso/>
        <tns:ordenInicio/>
        <tns:tipoImplementacion/>
      </tns:ConfigGateways>
      <tns:Equipo>
        <tns:OficialAdquisiciones/>
        <tns:EjecutivoPCT/>
        <tns:AnalistaCOFI/>
        <tns:AnalistaCOPRES/>
      </tns:Equipo>
      <tns:EquipoEjecucion>
        <tns:OficialAdquisiciones/>
        <tns:EjecutivoPCT/>
        <tns:AnalistaCOFI/>
        <tns:AnalistaCOPRES/>
      </tns:EquipoEjecucion>
      <tns:IdImplementacion/>
      <tns:IdLote>0</tns:IdLote>
      <xsl:for-each select="/ns0:InicioEnviarGasto/ns0:Header/param:ParameterType">
        <param:ParameterType>
          <param:parameterName>
            <xsl:value-of select="param:parameterName"/>
          </param:parameterName>
          <param:parameterValue>
            <xsl:value-of select="param:parameterValue"/>
          </param:parameterValue>
        </param:ParameterType>
      </xsl:for-each>
    </tns:ImplementacionPCT>
  </xsl:template>
</xsl:stylesheet>
