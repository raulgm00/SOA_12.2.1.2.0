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
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/Desembolso/Payload/V1"
                xmlns:ns0="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 tns xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:tns6="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:eqTr="http://www.bcie.org/EqipoTrabajo/V1"
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
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC06/DesembolsoProcess.xsd"/>
        <oracle-xsl-mapper:rootElement name="InicioDesembolsoExcepcion"
                                       namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/DesembolsoProcess"/>
        <oracle-xsl-mapper:param name="inicioDesembolsoExcepcion"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC06/DesembolsoPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="DesembolsoPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Desembolso/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [TUE OCT 25 11:41:21 CDT 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:DesembolsoPayload>
      <tns:Header>
        <tns6:Operacion>
          <ns2:CodigoOperacion>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Operacion/ns2:CodigoOperacion"/>
          </ns2:CodigoOperacion>
          <ns2:NombreOperacion>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Operacion/ns2:NombreOperacion"/>
          </ns2:NombreOperacion>
          <ns2:ResponsableOperacion>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Operacion/ns2:ResponsableOperacion"/>
          </ns2:ResponsableOperacion>
          <ns2:CodigoCliente>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Operacion/ns2:CodigoCliente"/>
          </ns2:CodigoCliente>
          <ns2:NombreCliente>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Operacion/ns2:NombreCliente"/>
          </ns2:NombreCliente>
          <ns2:CodigoProducto>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Operacion/ns2:CodigoProducto"/>
          </ns2:CodigoProducto>
          <ns2:NombreProducto>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Operacion/ns2:NombreProducto"/>
          </ns2:NombreProducto>
          <ns2:MontoSolicitado>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Operacion/ns2:MontoSolicitado"/>
          </ns2:MontoSolicitado>
          <ns2:Pais>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Operacion/ns2:Pais"/>
          </ns2:Pais>
        </tns6:Operacion>
        <tns6:Tarea>
          <ns6:CodigoTarea>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Tarea/ns6:CodigoTarea"/>
          </ns6:CodigoTarea>
          <ns6:NombreTarea>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Tarea/ns6:NombreTarea"/>
          </ns6:NombreTarea>
          <ns6:CodigoRol>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Tarea/ns6:CodigoRol"/>
          </ns6:CodigoRol>
          <ns6:NombreRol>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Tarea/ns6:NombreRol"/>
          </ns6:NombreRol>
          <ns6:CodigoProceso>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Tarea/ns6:CodigoProceso"/>
          </ns6:CodigoProceso>
          <ns6:NombreProceso>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Tarea/ns6:NombreProceso"/>
          </ns6:NombreProceso>
        </tns6:Tarea>
        <tns6:Proceso>
          <ns3:IdProceso>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Proceso/ns3:IdProceso"/>
          </ns3:IdProceso>
          <ns3:CodigoProceso>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Proceso/ns3:CodigoProceso"/>
          </ns3:CodigoProceso>
          <ns3:NombreProceso>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Proceso/ns3:NombreProceso"/>
          </ns3:NombreProceso>
          <ns3:EsProcesoCore>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Proceso/ns3:EsProcesoCore"/>
          </ns3:EsProcesoCore>
          <ns3:RolIniciaProceso>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Proceso/ns3:RolIniciaProceso"/>
          </ns3:RolIniciaProceso>
          <ns3:UsuarioIniciaProceso>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Proceso/ns3:UsuarioIniciaProceso"/>
          </ns3:UsuarioIniciaProceso>
          <ns3:InstanciaProceso>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Proceso/ns3:InstanciaProceso"/>
          </ns3:InstanciaProceso>
          <ns3:IdFlujo>
            <xsl:value-of select="/ns0:InicioDesembolsoExcepcion/ns0:Header/tns6:Proceso/ns3:IdFlujo"/>
          </ns3:IdFlujo>
        </tns6:Proceso>
        <xsl:for-each select="/ns0:InicioDesembolsoExcepcion/ns0:Header/ns5:ParameterType">
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
        <tns:requiereCOPRES/>
        <tns:requiereDAECI/>
        <tns:requiereFINAM/>
        <tns:requiereCOFI/>
        <tns:requierePCT/>
        <tns:requierePREA/>
        <tns:requiereMasInfoCOPRES/>
        <tns:requiereMasInfoDAECI/>
        <tns:requiereMasInfoFINAM/>
        <tns:requiereMasInfoCOFI/>
        <tns:requiereMasInfoPCT/>
        <tns:requiereMasInfoPREA/>
        <tns:cuentaBCIE/>
        <tns:consolidado/>
        <tns:requiereBaja/>
      </tns:ConfigGateways>
      <tns:idDesembolso/>
      <xsl:for-each select="/ns0:InicioDesembolsoExcepcion/ns0:Header/ns5:ParameterType">
        <xsl:if test='ns5:parameterName = "ID_SOLICITUD"'>
          <tns:idSolicitud>
            <xsl:value-of select="ns5:parameterValue"/>
          </tns:idSolicitud>
        </xsl:if>
      </xsl:for-each>
      <tns:estadoDesembolso/>
      <tns:desestimado/>
      <tns:motivoExcepcion/>
      <tns:fuentesExternas/>
      <tns:fondosPresupuestarios/>
      <tns:esIFI/>
      <tns:Equipo>
        <tns:analistaCOPRES/>
        <tns:analistaDAECI/>
        <tns:ejecutivoFINAM/>
        <tns:analistaCOFI/>
        <tns:gerentePais/>
        <tns:ejecutivoInversiones/>
        <tns:ejecutivoPCT/>
        <tns:analistaPREA/>
      </tns:Equipo>
      <tns:EquipoEjecucion>
        <tns:analistaCOPRES/>
        <tns:analistaDAECI/>
        <tns:ejecutivoFINAM/>
        <tns:analistaCOFI/>
        <tns:gerentePais/>
        <tns:ejecutivoInversiones/>
        <tns:ejecutivoPCT/>
        <tns:analistaPREA/>
      </tns:EquipoEjecucion>
      <ns5:ParameterType>
        <ns5:parameterName/>
        <ns5:parameterValue/>
      </ns5:ParameterType>
    </tns:DesembolsoPayload>
  </xsl:template>
</xsl:stylesheet>
