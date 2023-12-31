<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:ns0="http://xmlns.oracle.com/bpel/workflow/taskService"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tns="http://www.bcie.org/CrearBitacoraMO"
                exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 tns oraxsl xp20 xref mhdr oraext dvm socket"
                xmlns:ns1="http://xmlns.oracle.com/bpel/workflow/taskError"
                xmlns:command="http://xmlns.oracle.com/bpel/workflow/taskService/command"
                xmlns:routingslip="http://xmlns.oracle.com/bpel/workflow/routingSlip"
                xmlns:tsc="http://xmlns.oracle.com/bpel/workflow/common/tsc"
                xmlns:wfcommon="http://xmlns.oracle.com/bpel/workflow/common"
                xmlns:ns4="http://xmlns.oracle.com/bpel/workflow/TaskEvidenceService"
                xmlns:ns2="http://xmlns.oracle.com/bpel/workflow/taskQuery"
                xmlns:ns3="http://xmlns.oracle.com/bpel/workflow/taskQueryService"
                xmlns:task="http://xmlns.oracle.com/bpel/workflow/task" xmlns:ns7="http://www.bcie.org/ResultBO"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:ns5="http://www.bcie.org/CrearBitacora"
                xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:ns9="http://www.bcie.org/CrearBitacoraBO"
                xmlns:ns6="http://www.bcie.org/CatalogoBO" xmlns:ns8="http://www.bcie.org/ErrorBO">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="oramds:/soa/shared/workflow/TaskService.xsd"/>
        <oracle-xsl-mapper:rootElement name="taskAssignedMessage"
                                       namespace="http://xmlns.oracle.com/bpel/workflow/taskService"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/CrearBitacora/V1/Wsdl/CrearBitacora.wsdl"/>
        <oracle-xsl-mapper:rootElement name="CrearBitacoraRequest" namespace="http://www.bcie.org/CrearBitacoraMO"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [WED JUL 20 15:59:47 CDT 2016].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:CrearBitacoraRequest>
      <tns:BitacoraInput>
        <xsl:choose>
          <xsl:when test='/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Operacion/*:CodigoOperacion !=""'>
            <ns9:idNegocio>
              <ns6:Id>
                <xsl:value-of select="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Operacion/*:CodigoOperacion"/>
              </ns6:Id>
              <ns6:Descripcion>
                <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Operacion/*:NombreOperacion, 1, 105 )"/>
              </ns6:Descripcion>
              <ns6:codigoExterno>OPERACION</ns6:codigoExterno>
            </ns9:idNegocio>
          </xsl:when>
          <xsl:when test='/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:OperacionPayload/*:idOperacion !=""'>
            <ns9:idNegocio>
              <ns6:Id>
                <xsl:value-of select="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:OperacionPayload/*:idOperacion"/>
              </ns6:Id>
              <ns6:Descripcion>
                <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:OperacionPayload/*:nombre, 1, 105 )"/>
              </ns6:Descripcion>
              <ns6:codigoExterno>OPERACION</ns6:codigoExterno>
            </ns9:idNegocio>
          </xsl:when>
          <xsl:otherwise>
            <ns9:idNegocio>
              <ns6:Id>
                <xsl:value-of select="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Cliente/*:IdCliente"/>
              </ns6:Id>
              <ns6:Descripcion>
                <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Cliente/*:RazonSocial, 1, 105 )"/>
              </ns6:Descripcion>
              <ns6:codigoExterno>CLIENTE</ns6:codigoExterno>
            </ns9:idNegocio>
          </xsl:otherwise>
        </xsl:choose>
        <ns9:idProceso>
          <xsl:choose>
            <xsl:when test="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Tarea/*:CodigoProceso">
              <xsl:value-of select="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Tarea/*:CodigoProceso"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:CodigoProceso"/>
            </xsl:otherwise>
          </xsl:choose>
        </ns9:idProceso>
        <ns9:nombreProceso>
          <xsl:choose>
            <xsl:when test="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Tarea/*:NombreProceso">
              <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Tarea/*:NombreProceso, 1, 255 )"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:NombreProceso, 1, 255 )"/>
            </xsl:otherwise>
          </xsl:choose>
        </ns9:nombreProceso>
        <ns9:instanciaProceso>
          <xsl:value-of select="substring(/ns0:taskAssignedMessage/task:task/task:processInfo/task:instanceId, 1, 255)"/>
        </ns9:instanciaProceso>
        <ns9:esProceso>false</ns9:esProceso>
        <ns9:idTarea>
          <xsl:choose>
            <xsl:when test="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Tarea/*:CodigoTarea">
              <xsl:value-of select="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Tarea/*:CodigoTarea"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:CodigoTarea"/>
            </xsl:otherwise>
          </xsl:choose>
        </ns9:idTarea>
        <ns9:nombreTarea>
          <xsl:choose>
            <xsl:when test="/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Tarea/*:NombreTarea">
              <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:Tarea/*:NombreTarea, 1, 255 )"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:payload/*[1]/*:Header/*:NombreTarea, 1, 255 )"/>
            </xsl:otherwise>
          </xsl:choose>
        </ns9:nombreTarea>
        <ns9:instanciaTarea>
          <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:systemAttributes/task:activityId, 1, 255 )"/>
        </ns9:instanciaTarea>
        <ns9:usuario>
          <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:systemAttributes/task:assignees/task:id, 1, 255 )"/>
        </ns9:usuario>
        <ns9:nombreUsuario>
          <xsl:value-of select="substring (/ns0:taskAssignedMessage/task:task/task:systemAttributes/task:assignees/task:displayName, 1, 255 )"/>
        </ns9:nombreUsuario>
        <ns9:esFinActividad>false</ns9:esFinActividad>
        <ns9:fechaRegistro>
          <xsl:value-of select="xp20:current-dateTime( )"/>
        </ns9:fechaRegistro>
        <ns9:finalizado>false</ns9:finalizado>
      </tns:BitacoraInput>
    </tns:CrearBitacoraRequest>
  </xsl:template>
</xsl:stylesheet>
