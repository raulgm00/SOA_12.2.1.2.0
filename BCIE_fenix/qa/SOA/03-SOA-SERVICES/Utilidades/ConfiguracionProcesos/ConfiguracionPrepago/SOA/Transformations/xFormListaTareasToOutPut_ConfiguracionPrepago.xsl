<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:ns0="http://www.bcie.org/ConfiguracionProcesosMO"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns1="http://www.bcie.org/ProductoMO"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                exclude-result-prefixes="xsi oracle-xsl-mapper xsl xsd ns0 ns1 xp20 oraxsl mhdr oraext dvm xref socket"
                xmlns:ns2="http://www.bcie.org/ConfiguracionProcesosBO" xmlns:ns3="http://www.bcie.org/TerminoBO"
                xmlns:ns4="http://www.bcie.org/ReglaBO" xmlns:ns5="http://www.bcie.org/OperacionBO"
                xmlns:ns6="http://www.bcie.org/ResultBO" xmlns:ns7="http://www.bcie.org/CommonBO"
                xmlns:ns8="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1"
                xmlns:ns11="http://www.bcie.org/MatrizTCCBO" xmlns:ns10="http://www.bcie.org/LineaCreditoBO"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/"
                xmlns:ns9="http://www.bcie.org/CrearBitacoraProcesoBO"
                xmlns:ns12="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/"
                xmlns:ns13="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1"
                xmlns:ns15="http://www.bcie.org/ProductoBO" xmlns:ns14="http://www.bcie.org/CondicionBO"
                xmlns:ns16="http://www.bcie.org/DeclaracionJuradaBO"
                xmlns:ns17="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1"
                xmlns:ns18="http://www.bcie.org/ImplementacionPctBO" xmlns:ns19="http://www.bcie.org/DocumentoBO"
                xmlns:ns20="http://www.bcie.org/HerramientaCEBO" xmlns:ns21="http://www.bcie.org/ComisionBO"
                xmlns:tns="http://xmlns.bcie.com/ConfiguracionProcesosService"
                xmlns:ns31="http://xmlns.bcie.org/ProductoService" xmlns:ns22="http://www.bcie.org/CatalogoBO"
                xmlns:ns23="http://www.bcie.org/DesembolsoBO" xmlns:ns24="http://www.bcie.org/AdquisicionBO"
                xmlns:ns25="http://www.bcie.org/ContratoBO" xmlns:ns26="http://www.bcie.org/ClienteBO"
                xmlns:ns27="http://www.bcie.org/AtributoBO"
                xmlns:ns29="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1"
                xmlns:ns28="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1"
                xmlns:ns30="http://www.bcie.org/ErrorBO">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/WSDL/BPEL/ConfiguracionPrepagoSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConfiguracionPrepagoResponse"
                                       namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioProducto/Producto/V1/Wsdl/ADAPTER/ConsultarProductoByIdOperacionSAD.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ObtenerTareasProductoResponse" namespace="http://www.bcie.org/ProductoMO"/>
        <oracle-xsl-mapper:param name="obtenerTareasProducto_OutputVariable.response"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="WSDL">
        <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/WSDL/BPEL/ConfiguracionPrepagoSOR.wsdl"/>
        <oracle-xsl-mapper:rootElement name="ConfiguracionPrepagoResponse"
                                       namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.1.3.0.0(XSLT Build 140529.0700.0211) AT [FRI OCT 29 14:43:46 CDT 2021].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="obtenerTareasProducto_OutputVariable.response"/>
  <xsl:template match="/">
    <ns0:ConfiguracionPrepagoResponse>
      <ns0:configuracionPrepago>
        <ns2:Header>
          <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion">
            <ns17:Operacion>
              <ns12:CodigoOperacion>
                <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:CodigoOperacion"/>
              </ns12:CodigoOperacion>
              <ns12:NombreOperacion>
                <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:NombreOperacion"/>
              </ns12:NombreOperacion>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:ResponsableOperacion">
                <ns12:ResponsableOperacion>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:ResponsableOperacion"/>
                </ns12:ResponsableOperacion>
              </xsl:if>
              <ns12:CodigoCliente>
                <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:CodigoCliente"/>
              </ns12:CodigoCliente>
              <ns12:NombreCliente>
                <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:NombreCliente"/>
              </ns12:NombreCliente>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:CodigoProducto">
                <ns12:CodigoProducto>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:CodigoProducto"/>
                </ns12:CodigoProducto>
              </xsl:if>
              <ns12:NombreProducto>
                <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:NombreProducto"/>
              </ns12:NombreProducto>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:MontoSolicitado">
                <ns12:MontoSolicitado>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:MontoSolicitado"/>
                </ns12:MontoSolicitado>
              </xsl:if>
              <ns12:Pais>
                <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:Pais"/>
              </ns12:Pais>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:idSectorInstitucional">
                <ns12:idSectorInstitucional>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:idSectorInstitucional"/>
                </ns12:idSectorInstitucional>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:idEncargado">
                <ns12:idEncargado>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:idEncargado"/>
                </ns12:idEncargado>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:idRol">
                <ns12:idRol>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:idRol"/>
                </ns12:idRol>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:descripcionRol">
                <ns12:descripcionRol>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Operacion/ns12:descripcionRol"/>
                </ns12:descripcionRol>
              </xsl:if>
            </ns17:Operacion>
          </xsl:if>
          <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente">
            <ns17:Cliente>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdCliente">
                <ns28:IdCliente>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdCliente"/>
                </ns28:IdCliente>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdFlexCube">
                <ns28:IdFlexCube>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdFlexCube"/>
                </ns28:IdFlexCube>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:RazonSocial">
                <ns28:RazonSocial>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:RazonSocial"/>
                </ns28:RazonSocial>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:Abreviatura">
                <ns28:Abreviatura>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:Abreviatura"/>
                </ns28:Abreviatura>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdSector">
                <ns28:IdSector>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdSector"/>
                </ns28:IdSector>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:Sector">
                <ns28:Sector>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:Sector"/>
                </ns28:Sector>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdPais">
                <ns28:IdPais>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdPais"/>
                </ns28:IdPais>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:Pais">
                <ns28:Pais>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:Pais"/>
                </ns28:Pais>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdOficina">
                <ns28:IdOficina>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:IdOficina"/>
                </ns28:IdOficina>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:Oficina">
                <ns28:Oficina>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:Oficina"/>
                </ns28:Oficina>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:ResponsableCliente">
                <ns28:ResponsableCliente>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:ResponsableCliente"/>
                </ns28:ResponsableCliente>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:bicCode">
                <ns28:bicCode>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:bicCode"/>
                </ns28:bicCode>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:direccion">
                <ns28:direccion>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Cliente/ns28:direccion"/>
                </ns28:direccion>
              </xsl:if>
            </ns17:Cliente>
          </xsl:if>
          <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea">
            <ns17:Tarea>
              <ns29:CodigoTarea>
                <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:CodigoTarea"/>
              </ns29:CodigoTarea>
              <ns29:NombreTarea>
                <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:NombreTarea"/>
              </ns29:NombreTarea>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:CodigoRol">
                <ns29:CodigoRol>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:CodigoRol"/>
                </ns29:CodigoRol>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:NombreRol">
                <ns29:NombreRol>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:NombreRol"/>
                </ns29:NombreRol>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:CodigoProceso">
                <ns29:CodigoProceso>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:CodigoProceso"/>
                </ns29:CodigoProceso>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:NombreProceso">
                <ns29:NombreProceso>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Tarea/ns29:NombreProceso"/>
                </ns29:NombreProceso>
              </xsl:if>
            </ns17:Tarea>
          </xsl:if>
          <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso">
            <ns17:Proceso>
              <ns13:IdProceso>
                <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:IdProceso"/>
              </ns13:IdProceso>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:CodigoProceso">
                <ns13:CodigoProceso>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:CodigoProceso"/>
                </ns13:CodigoProceso>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:NombreProceso">
                <ns13:NombreProceso>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:NombreProceso"/>
                </ns13:NombreProceso>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:EsProcesoCore">
                <ns13:EsProcesoCore>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:EsProcesoCore"/>
                </ns13:EsProcesoCore>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:RolIniciaProceso">
                <ns13:RolIniciaProceso>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:RolIniciaProceso"/>
                </ns13:RolIniciaProceso>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:UsuarioIniciaProceso">
                <ns13:UsuarioIniciaProceso>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:UsuarioIniciaProceso"/>
                </ns13:UsuarioIniciaProceso>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:InstanciaProceso">
                <ns13:InstanciaProceso>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:InstanciaProceso"/>
                </ns13:InstanciaProceso>
              </xsl:if>
              <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:IdFlujo">
                <ns13:IdFlujo>
                  <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns17:Proceso/ns13:IdFlujo"/>
                </ns13:IdFlujo>
              </xsl:if>
            </ns17:Proceso>
          </xsl:if>
          <xsl:for-each select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:Header/ns8:ParameterType">
            <ns8:ParameterType>
              <ns8:parameterName>
                <xsl:value-of select="ns8:parameterName"/>
              </ns8:parameterName>
              <ns8:parameterValue>
                <xsl:value-of select="ns8:parameterValue"/>
              </ns8:parameterValue>
            </ns8:ParameterType>
          </xsl:for-each>
        </ns2:Header>
        <ns2:RolesEquipoTrabajo>
          <xsl:for-each select="/ns0:ConfiguracionPrepagoResponse/ns0:configuracionPrepago/ns2:RolesEquipoTrabajo/ns2:Rol">
            <ns2:Rol>
              <xsl:if test="ns22:Id">
                <ns22:Id>
                  <xsl:value-of select="ns22:Id"/>
                </ns22:Id>
              </xsl:if>
              <xsl:if test="ns22:Descripcion">
                <ns22:Descripcion>
                  <xsl:value-of select="ns22:Descripcion"/>
                </ns22:Descripcion>
              </xsl:if>
              <xsl:if test="ns22:DescripcionCorta">
                <ns22:DescripcionCorta>
                  <xsl:value-of select="ns22:DescripcionCorta"/>
                </ns22:DescripcionCorta>
              </xsl:if>
              <xsl:if test="ns22:estatus">
                <ns22:estatus>
                  <xsl:value-of select="ns22:estatus"/>
                </ns22:estatus>
              </xsl:if>
              <xsl:if test="ns22:codigoExterno">
                <ns22:codigoExterno>
                  <xsl:value-of select="ns22:codigoExterno"/>
                </ns22:codigoExterno>
              </xsl:if>
            </ns2:Rol>
          </xsl:for-each>
        </ns2:RolesEquipoTrabajo>
        <ns2:ListaTareasProducto>
          <xsl:for-each select="$obtenerTareasProducto_OutputVariable.response/ns1:ObtenerTareasProductoResponse/ns1:ListadoTareas/ns15:listadoTareas">
            <xsl:if test=".">
              <ns15:listadoTareas>
                <xsl:if test="ns22:idTarea">
                  <ns22:idTarea>
                    <xsl:value-of select="ns22:idTarea"/>
                  </ns22:idTarea>
                </xsl:if>
                <xsl:if test="ns22:descripcion">
                  <ns22:descripcion>
                    <xsl:value-of select="ns22:descripcion"/>
                  </ns22:descripcion>
                </xsl:if>
                <xsl:if test="ns22:descripcionCorta">
                  <ns22:descripcionCorta>
                    <xsl:value-of select="ns22:descripcionCorta"/>
                  </ns22:descripcionCorta>
                </xsl:if>
                <xsl:if test="ns22:idProcesoBpm">
                  <ns22:idProcesoBpm>
                    <xsl:value-of select="ns22:idProcesoBpm"/>
                  </ns22:idProcesoBpm>
                </xsl:if>
              </ns15:listadoTareas>
            </xsl:if>
          </xsl:for-each>
        </ns2:ListaTareasProducto>
      </ns0:configuracionPrepago>
      <ns0:Resultado>
        <ns6:result>
          <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:Resultado/ns6:result"/>
        </ns6:result>
        <ns6:message>
          <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:Resultado/ns6:message"/>
        </ns6:message>
        <xsl:if test="/ns0:ConfiguracionPrepagoResponse/ns0:Resultado/ns6:error">
          <ns6:error>
            <ns30:errorCode>
              <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:Resultado/ns6:error/ns30:errorCode"/>
            </ns30:errorCode>
            <ns30:errorDescription>
              <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:Resultado/ns6:error/ns30:errorDescription"/>
            </ns30:errorDescription>
            <ns30:errorType>
              <xsl:value-of select="/ns0:ConfiguracionPrepagoResponse/ns0:Resultado/ns6:error/ns30:errorType"/>
            </ns30:errorType>
          </ns6:error>
        </xsl:if>
      </ns0:Resultado>
    </ns0:ConfiguracionPrepagoResponse>
  </xsl:template>
</xsl:stylesheet>
