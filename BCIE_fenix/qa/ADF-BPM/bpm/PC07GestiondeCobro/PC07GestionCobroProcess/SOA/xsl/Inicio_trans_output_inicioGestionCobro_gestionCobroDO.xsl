<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:ns0="http://xmlns.oracle.com/bpmn/bpmnProcess/GestionCobroProcess"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/GestionCobro/Payload/V1"
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
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC07/GestionCobroProcess.xsd"/>
        <oracle-xsl-mapper:rootElement name="InicioGestionCobro"
                                       namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/GestionCobroProcess"/>
        <oracle-xsl-mapper:param name="inicioGestionCobro"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PC07/GestionCobroPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="GestionCobroPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/GestionCobro/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [SAT OCT 15 15:06:00 CDT 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:GestionCobroPayload>
      <tns:Header>
        <tns6:Cliente>
          <ns4:IdCliente>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:IdCliente"/>
          </ns4:IdCliente>
          <ns4:IdFlexCube>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:IdFlexCube"/>
          </ns4:IdFlexCube>
          <ns4:RazonSocial>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:RazonSocial"/>
          </ns4:RazonSocial>
          <ns4:Abreviatura>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:Abreviatura"/>
          </ns4:Abreviatura>
          <ns4:IdSector>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:IdSector"/>
          </ns4:IdSector>
          <ns4:Sector>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:Sector"/>
          </ns4:Sector>
          <ns4:IdPais>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:IdPais"/>
          </ns4:IdPais>
          <ns4:Pais>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:Pais"/>
          </ns4:Pais>
          <ns4:IdOficina>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:IdOficina"/>
          </ns4:IdOficina>
          <ns4:Oficina>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:Oficina"/>
          </ns4:Oficina>
          <ns4:ResponsableCliente>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Cliente/ns4:ResponsableCliente"/>
          </ns4:ResponsableCliente>
        </tns6:Cliente>
        <tns6:Tarea>
          <ns5:CodigoTarea>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Tarea/ns5:CodigoTarea"/>
          </ns5:CodigoTarea>
          <ns5:NombreTarea>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Tarea/ns5:NombreTarea"/>
          </ns5:NombreTarea>
          <ns5:CodigoRol>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Tarea/ns5:CodigoRol"/>
          </ns5:CodigoRol>
          <ns5:NombreRol>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Tarea/ns5:NombreRol"/>
          </ns5:NombreRol>
          <ns5:CodigoProceso>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Tarea/ns5:CodigoProceso"/>
          </ns5:CodigoProceso>
          <ns5:NombreProceso>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Tarea/ns5:NombreProceso"/>
          </ns5:NombreProceso>
        </tns6:Tarea>
        <tns6:Proceso>
          <ns3:IdProceso>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Proceso/ns3:IdProceso"/>
          </ns3:IdProceso>
          <ns3:CodigoProceso>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Proceso/ns3:CodigoProceso"/>
          </ns3:CodigoProceso>
          <ns3:NombreProceso>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Proceso/ns3:NombreProceso"/>
          </ns3:NombreProceso>
          <ns3:EsProcesoCore>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Proceso/ns3:EsProcesoCore"/>
          </ns3:EsProcesoCore>
          <ns3:RolIniciaProceso>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Proceso/ns3:RolIniciaProceso"/>
          </ns3:RolIniciaProceso>
          <ns3:UsuarioIniciaProceso>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Proceso/ns3:UsuarioIniciaProceso"/>
          </ns3:UsuarioIniciaProceso>
          <ns3:InstanciaProceso>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Proceso/ns3:InstanciaProceso"/>
          </ns3:InstanciaProceso>
          <ns3:IdFlujo>
            <xsl:value-of select="/ns0:InicioGestionCobro/ns0:Header/tns6:Proceso/ns3:IdFlujo"/>
          </ns3:IdFlujo>
        </tns6:Proceso>
        <xsl:for-each select="/ns0:InicioGestionCobro/ns0:Header/param:ParameterType">
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
      <tns:Configuration/>
      <xsl:for-each select="/ns0:InicioGestionCobro/ns0:Header/param:ParameterType">
        <xsl:if test='param:parameterName = "TIPO_INICIO"'>
          <tns:tipoInicio>
            <xsl:value-of select="param:parameterValue"/>
          </tns:tipoInicio>
        </xsl:if>
      </xsl:for-each>
      <tns:envioManual/>
      <xsl:for-each select="/ns0:InicioGestionCobro/ns0:Header/param:ParameterType"/>
      <xsl:if test='/ns0:InicioGestionCobro/ns0:Header/param:ParameterType/param:parameterName = "TIPO_INICIO"'>
        <xsl:choose>
          <xsl:when test='/ns0:InicioGestionCobro/ns0:Header/param:ParameterType/param:parameterValue = "MANUAL"'>
            <tns:mes>
              <xsl:value-of select="xp20:month-from-dateTime (xp20:current-dateTime ( ) )"/>
            </tns:mes>
          </xsl:when>
          <xsl:otherwise>
            <tns:mes>
              <xsl:value-of select="xp20:month-from-dateTime (xp20:current-dateTime ( ) )"/>
            </tns:mes>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:if>
      <tns:codigoPais/>
      <tns:monto/>
      <tns:Equipo>
        <tns:analistaCOFI/>
        <tns:analistaCOPRES/>
      </tns:Equipo>
      <param:ParameterType>
        <param:parameterName/>
        <param:parameterValue/>
      </param:ParameterType>
      <tns:EquipoEjecucion>
        <tns:analistaCOFI/>
        <tns:analistaCOPRES/>
      </tns:EquipoEjecucion>
    </tns:GestionCobroPayload>
  </xsl:template>
</xsl:stylesheet>
