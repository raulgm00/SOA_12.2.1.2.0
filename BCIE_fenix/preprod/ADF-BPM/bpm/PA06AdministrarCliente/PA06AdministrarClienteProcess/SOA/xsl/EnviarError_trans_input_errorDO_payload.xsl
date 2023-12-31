<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tns="http://xmlns.bcie.org/ObjetoProceso/AdminClientesError/Payload/V1"
                xmlns:ns0="http://xmlns.bcie.org/ObjetoProceso/Admincliente/Payload/V1"
                exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 tns oraxsl xp20 xref mhdr oraext dvm socket"
                xmlns:parameter="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns1="http://xmlns.bcie.org/ObjetoProceso/Comun/Notificacion/V1"
                xmlns:header="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns2="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:ns3="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA06/AdministarCliente.xsd"/>
        <oracle-xsl-mapper:rootElement name="AdminClienteErrorPayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Admincliente/Payload/V1"/>
        <oracle-xsl-mapper:param name="errorDO"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA06/AdministarCliente.xsd"/>
        <oracle-xsl-mapper:rootElement name="AdminClientePayload"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/Admincliente/Payload/V1"/>
        <oracle-xsl-mapper:param name="administrarClienteDO"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/BPM/Schema/PA06/ClientesErrorPayload.xsd"/>
        <oracle-xsl-mapper:rootElement name="AdminClientesErrorPayloadType"
                                       namespace="http://xmlns.bcie.org/ObjetoProceso/AdminClientesError/Payload/V1"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.1(XSLT Build 150816.0404) AT [WED FEB 17 12:19:50 CST 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="administrarClienteDO"/>
  <xsl:template match="/">
    <tns:AdminClientesErrorPayloadType>
      <tns:Header>
        <header:CodigoProceso>12</header:CodigoProceso>
      </tns:Header>
      <tns:ClientePayload>
        <tns:idCliente>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idCliente"/>
        </tns:idCliente>
        <tns:idFlexcube>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idFlexcube"/>
        </tns:idFlexcube>
        <tns:razonSocial>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:razonSocial"/>
        </tns:razonSocial>
        
<tns:bicCode>
               <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:bicCode"/>
            </tns:bicCode>
            <tns:direccion>
               <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:direccion"/>
            </tns:direccion>
            <tns:codigoClienteFLEXCUBE>
               <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:codigoClienteFLEXCUBE"/>
            </tns:codigoClienteFLEXCUBE>
            
<tns:idTipoPersona>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idTipoPersona"/>
        </tns:idTipoPersona>
        <tns:idSector>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idSector"/>
        </tns:idSector>
        <tns:idPais>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idPais"/>
        </tns:idPais>
        <tns:diaPago>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:diaPago"/>
        </tns:diaPago>
        <tns:idOficina>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idOficina"/>
        </tns:idOficina>
        <tns:abreviatura>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:abreviatura"/>
        </tns:abreviatura>
        <tns:idTipoCliente>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idTipoCliente"/>
        </tns:idTipoCliente>
        <tns:idTipoInstitucion>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idTipoInstitucion"/>
        </tns:idTipoInstitucion>
        <tns:idGrupoEconomico>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idGrupoEconomico"/>
        </tns:idGrupoEconomico>
        <tns:idNumeroIdentificacion>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:idNumeroIdentificacion"/>
        </tns:idNumeroIdentificacion>
        <tns:numeroIdentificacion>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:numeroIdentificacion"/>
        </tns:numeroIdentificacion>
        <tns:responsableCliente>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:responsableCliente"/>
        </tns:responsableCliente>
        <tns:fechaRegistro>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:fechaRegistro"/>
        </tns:fechaRegistro>
        <tns:fechaAprobacion>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:fechaAprobacion"/>
        </tns:fechaAprobacion>
        <tns:autorizoCliente>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:autorizoCliente"/>
        </tns:autorizoCliente>
        <tns:estatus>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:estatus"/>
        </tns:estatus>
        <tns:fechaBaja>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:ClientePayload/ns0:fechaBaja"/>
        </tns:fechaBaja>
      </tns:ClientePayload>
      <tns:EquipoPayload>
        <tns:AnalistaCOFI>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:Equipo/ns0:AnalistaCOFI"/>
        </tns:AnalistaCOFI>
      </tns:EquipoPayload>
      <tns:EquipoEjecucion>
        <tns:AnalistaCOFI>
          <xsl:value-of select="$administrarClienteDO/ns0:AdminClientePayload/ns0:EquipoEjecucion/ns0:AnalistaCOFI"/>
        </tns:AnalistaCOFI>
      </tns:EquipoEjecucion>
      <xsl:for-each select="$administrarClienteDO/ns0:AdminClientePayload/parameter:ParameterType">
        <parameter:ParameterType>
          <parameter:parameterName>
            <xsl:value-of select="parameter:parameterName"/>
          </parameter:parameterName>
          <parameter:parameterValue>
            <xsl:value-of select="parameter:parameterValue"/>
          </parameter:parameterValue>
        </parameter:ParameterType>
      </xsl:for-each>
      <tns:ErrorMsg>
        <xsl:value-of select="/ns0:AdminClienteErrorPayload/ns0:ErrorMsg"/>
      </tns:ErrorMsg>
      <tns:ErrorNamespace>
        <xsl:value-of select="/ns0:AdminClienteErrorPayload/ns0:ErrorNamespace"/>
      </tns:ErrorNamespace>
      <tns:ErrorFault>
        <xsl:value-of select="/ns0:AdminClienteErrorPayload/ns0:ErrorFault"/>
      </tns:ErrorFault>
      <tns:Accion>
        <xsl:value-of select="/ns0:AdminClienteErrorPayload/ns0:Accion"/>
      </tns:Accion>
    </tns:AdminClientesErrorPayloadType>
  </xsl:template>
</xsl:stylesheet>
