<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:tns="http://www.bcie.org/ConfiguracionProcesosMO" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:ns0="http://www.bcie.org/AdquisicionMO" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 tns oraxsl xp20 xref mhdr oraext dvm socket" xmlns:ns2="http://www.bcie.org/ConfiguracionProcesosBO" xmlns:ns3="http://www.bcie.org/TerminoBO" xmlns:bpws="http://docs.oasis-open.org/wsbpel/2.0/process/executable" xmlns:ns4="http://www.bcie.org/ReglaBO" xmlns:ns5="http://www.bcie.org/OperacionBO" xmlns:ns6="http://www.bcie.org/ResultBO" xmlns:ns7="http://www.bcie.org/CommonBO" xmlns:ns8="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1" xmlns:ns11="http://www.bcie.org/MatrizTCCBO" xmlns:ns10="http://www.bcie.org/LineaCreditoBO" xmlns:ns9="http://www.bcie.org/CrearBitacoraProcesoBO" xmlns:ns12="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:ns13="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1" xmlns:ns15="http://www.bcie.org/ProductoBO" xmlns:ns14="http://www.bcie.org/CondicionBO" xmlns:ns16="http://www.bcie.org/DeclaracionJuradaBO" xmlns:ns17="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1" xmlns:ns18="http://www.bcie.org/ImplementacionPctBO" xmlns:ns19="http://www.bcie.org/DocumentoBO" xmlns:ns20="http://www.bcie.org/HerramientaCEBO" xmlns:plnk="http://docs.oasis-open.org/wsbpel/2.0/plnktype" xmlns:ns21="http://www.bcie.org/ComisionBO" xmlns:ns1="http://xmlns.bcie.com/ConfiguracionProcesosService" xmlns:ns22="http://www.bcie.org/CatalogoBO" xmlns:ns23="http://www.bcie.org/DesembolsoBO" xmlns:ns24="http://www.bcie.org/AdquisicionBO" xmlns:ns25="http://www.bcie.org/ContratoBO" xmlns:ns26="http://www.bcie.org/ClienteBO" xmlns:ns27="http://www.bcie.org/AtributoBO" xmlns:ns29="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1" xmlns:ns28="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1" xmlns:ns30="http://www.bcie.org/ErrorBO" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/">
   <oracle-xsl-mapper:schema>
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="../../WSDLs/ConfiguracionReasignarOperacionSORWrapper.wsdl"/>
            <oracle-xsl-mapper:rootElement name="ConsultarAdquisicionResponse" namespace="http://www.bcie.org/AdquisicionMO"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="WSDL">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/WSDL/BPEL/ConfiguracionReasignarOperacionSOR.wsdl"/>
            <oracle-xsl-mapper:rootElement name="configuracionReasignarOperacionResponse" namespace="http://www.bcie.org/ConfiguracionProcesosMO"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
   </oracle-xsl-mapper:schema>
   <xsl:template match="/">
      <tns:configuracionReasignarOperacionResponse>
         <tns:configuracionReasignarOperacion>
            <ns2:Adquisiciones>
               <xsl:for-each select="/ns0:ConsultarAdquisicionResponse/ns0:Adquisicion">
                  <ns24:Adquisicion>
                     <ns24:idAdquisicion>
                        <xsl:value-of select="ns24:idAdquisicion"/>
                     </ns24:idAdquisicion>
                     <ns24:operacion>
                        <xsl:if test="ns24:operacion/ns5:idOperacion">
                           <ns5:idOperacion>
                              <xsl:value-of select="ns24:operacion/ns5:idOperacion"/>
                           </ns5:idOperacion>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:responsable">
                           <ns5:responsable>
                              <xsl:value-of select="ns24:operacion/ns5:responsable"/>
                           </ns5:responsable>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:oficina">
                           <ns5:oficina>
                              <xsl:value-of select="ns24:operacion/ns5:oficina"/>
                           </ns5:oficina>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:nombre">
                           <ns5:nombre>
                              <xsl:value-of select="ns24:operacion/ns5:nombre"/>
                           </ns5:nombre>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:cliente">
                           <ns5:cliente>
                              <ns26:idCliente>
                                 <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:idCliente"/>
                              </ns26:idCliente>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:idFacturador">
                                 <ns26:idFacturador>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:idFacturador"/>
                                 </ns26:idFacturador>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:razonSocial">
                                 <ns26:razonSocial>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:razonSocial"/>
                                 </ns26:razonSocial>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:abreviatura">
                                 <ns26:abreviatura>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:abreviatura"/>
                                 </ns26:abreviatura>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoPersona">
                                 <ns26:tipoPersona>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoPersona/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns26:tipoPersona>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoCliente">
                                 <ns26:tipoCliente>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoCliente/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns26:tipoCliente>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:sector">
                                 <ns26:sector>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:sector/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:sector/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:sector/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:sector/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:sector/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:sector/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:sector/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:sector/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:sector/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:sector/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns26:sector>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoInstitucion">
                                 <ns26:tipoInstitucion>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoInstitucion/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns26:tipoInstitucion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:pais">
                                 <ns26:pais>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:pais/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:pais/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:pais/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:pais/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:pais/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:pais/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:pais/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:pais/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:pais/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:pais/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns26:pais>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:grupoEconomico">
                                 <ns26:grupoEconomico>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:grupoEconomico/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns26:grupoEconomico>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:tipoIdentificacion">
                                 <ns26:tipoIdentificacion>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:tipoIdentificacion"/>
                                 </ns26:tipoIdentificacion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:numeroIdentificacion">
                                 <ns26:numeroIdentificacion>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:numeroIdentificacion"/>
                                 </ns26:numeroIdentificacion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:direccion">
                                 <ns26:direccion>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:direccion"/>
                                 </ns26:direccion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:telefono">
                                 <ns26:telefono>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:telefono"/>
                                 </ns26:telefono>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:fax">
                                 <ns26:fax>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:fax"/>
                                 </ns26:fax>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:email">
                                 <ns26:email>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:email"/>
                                 </ns26:email>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:ciudad">
                                 <ns26:ciudad>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:ciudad"/>
                                 </ns26:ciudad>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:oficina">
                                 <ns26:oficina>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:oficina/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:oficina/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:oficina/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:oficina/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:oficina/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:oficina/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:oficina/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:oficina/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:oficina/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:oficina/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns26:oficina>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:grupoEmpresarial">
                                 <ns26:grupoEmpresarial>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:grupoEmpresarial"/>
                                 </ns26:grupoEmpresarial>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:fechaRegistro">
                                 <ns26:fechaRegistro>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:fechaRegistro"/>
                                 </ns26:fechaRegistro>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:fechaAprobacion">
                                 <ns26:fechaAprobacion>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:fechaAprobacion"/>
                                 </ns26:fechaAprobacion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:ejecutivo">
                                 <ns26:ejecutivo>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:ejecutivo"/>
                                 </ns26:ejecutivo>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:responsableCliente">
                                 <ns26:responsableCliente>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:responsableCliente"/>
                                 </ns26:responsableCliente>
                              </xsl:if>
                              <xsl:for-each select="ns24:operacion/ns5:cliente/ns26:Contactos">
                                 <ns26:Contactos>
                                    <xsl:for-each select="ns26:Contacto">
                                       <ns26:Contacto>
                                          <ns26:idContacto>
                                             <xsl:value-of select="ns26:idContacto"/>
                                          </ns26:idContacto>
                                          <ns26:idCliente>
                                             <xsl:value-of select="ns26:idCliente"/>
                                          </ns26:idCliente>
                                          <ns26:nombre>
                                             <xsl:value-of select="ns26:nombre"/>
                                          </ns26:nombre>
                                          <ns26:telefono>
                                             <xsl:value-of select="ns26:telefono"/>
                                          </ns26:telefono>
                                          <ns26:correoElectronico>
                                             <xsl:value-of select="ns26:correoElectronico"/>
                                          </ns26:correoElectronico>
                                          <ns26:cargo>
                                             <xsl:value-of select="ns26:cargo"/>
                                          </ns26:cargo>
                                          <ns26:tipo>
                                             <xsl:value-of select="ns26:tipo"/>
                                          </ns26:tipo>
                                          <ns26:fechaRegistro>
                                             <xsl:value-of select="ns26:fechaRegistro"/>
                                          </ns26:fechaRegistro>
                                          <ns26:idContactosClientes>
                                             <xsl:value-of select="ns26:idContactosClientes"/>
                                          </ns26:idContactosClientes>
                                          <xsl:if test="ns26:recibeAvisoCobro">
                                             <ns26:recibeAvisoCobro>
                                                <xsl:value-of select="ns26:recibeAvisoCobro"/>
                                             </ns26:recibeAvisoCobro>
                                          </xsl:if>
                                       </ns26:Contacto>
                                    </xsl:for-each>
                                 </ns26:Contactos>
                              </xsl:for-each>
                              <ns26:InformacionCorrecta>
                                 <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:InformacionCorrecta"/>
                              </ns26:InformacionCorrecta>
                              <ns26:ActualizacionPermitida>
                                 <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:ActualizacionPermitida"/>
                              </ns26:ActualizacionPermitida>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:comentarioAprobacion">
                                 <ns26:comentarioAprobacion>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:comentarioAprobacion"/>
                                 </ns26:comentarioAprobacion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:revisadoAprobacion">
                                 <ns26:revisadoAprobacion>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:revisadoAprobacion"/>
                                 </ns26:revisadoAprobacion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:status">
                                 <ns26:status>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:status"/>
                                 </ns26:status>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:fechaBaja">
                                 <ns26:fechaBaja>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:fechaBaja"/>
                                 </ns26:fechaBaja>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:diaPago">
                                 <ns26:diaPago>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:diaPago"/>
                                 </ns26:diaPago>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:SCR">
                                 <ns26:SCR>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:SCR/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:SCR/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:SCR/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:SCR/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:SCR/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:SCR/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:SCR/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:SCR/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:SCR/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:SCR/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns26:SCR>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:perspectiva">
                                 <ns26:perspectiva>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:perspectiva/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns26:perspectiva>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:enMora">
                                 <ns26:enMora>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:enMora"/>
                                 </ns26:enMora>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:mora">
                                 <ns26:mora>
                                    <ns26:dias>
                                       <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:mora/ns26:dias"/>
                                    </ns26:dias>
                                    <ns26:monto>
                                       <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:mora/ns26:monto"/>
                                    </ns26:monto>
                                    <xsl:if test="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo">
                                       <ns26:tipo>
                                          <xsl:if test="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:Id">
                                             <ns22:Id>
                                                <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:Id"/>
                                             </ns22:Id>
                                          </xsl:if>
                                          <xsl:if test="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:Descripcion">
                                             <ns22:Descripcion>
                                                <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:Descripcion"/>
                                             </ns22:Descripcion>
                                          </xsl:if>
                                          <xsl:if test="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:DescripcionCorta">
                                             <ns22:DescripcionCorta>
                                                <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:DescripcionCorta"/>
                                             </ns22:DescripcionCorta>
                                          </xsl:if>
                                          <xsl:if test="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:estatus">
                                             <ns22:estatus>
                                                <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:estatus"/>
                                             </ns22:estatus>
                                          </xsl:if>
                                          <xsl:if test="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:codigoExterno">
                                             <ns22:codigoExterno>
                                                <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:mora/ns26:tipo/ns22:codigoExterno"/>
                                             </ns22:codigoExterno>
                                          </xsl:if>
                                       </ns26:tipo>
                                    </xsl:if>
                                 </ns26:mora>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:deteriorado">
                                 <ns26:deteriorado>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:deteriorado"/>
                                 </ns26:deteriorado>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:reserva">
                                 <ns26:reserva>
                                    <ns26:importeReserva>
                                       <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:reserva/ns26:importeReserva"/>
                                    </ns26:importeReserva>
                                    <ns26:tipoReserva>
                                       <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:reserva/ns26:tipoReserva"/>
                                    </ns26:tipoReserva>
                                 </ns26:reserva>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:requiereEnvioAutomatico">
                                 <ns26:requiereEnvioAutomatico>
                                    <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:requiereEnvioAutomatico"/>
                                 </ns26:requiereEnvioAutomatico>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR">
                                 <ns26:detalleSCR>
                                    <ns26:scrFuente>
                                       <ns26:idCliente>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:idCliente"/>
                                       </ns26:idCliente>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:idFacturador">
                                          <ns26:idFacturador>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:idFacturador"/>
                                          </ns26:idFacturador>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:scr">
                                          <ns26:scr>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:scr"/>
                                          </ns26:scr>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:estadoScr">
                                          <ns26:estadoScr>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:estadoScr"/>
                                          </ns26:estadoScr>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:observacion">
                                          <ns26:observacion>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:observacion"/>
                                          </ns26:observacion>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:fecha">
                                          <ns26:fecha>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:fecha"/>
                                          </ns26:fecha>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:usuarioModifico">
                                          <ns26:usuarioModifico>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:usuarioModifico"/>
                                          </ns26:usuarioModifico>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:usuarioValido">
                                          <ns26:usuarioValido>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrFuente/ns26:usuarioValido"/>
                                          </ns26:usuarioValido>
                                       </xsl:if>
                                    </ns26:scrFuente>
                                    <ns26:scrReferencia>
                                       <ns26:idCliente>
                                          <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:idCliente"/>
                                       </ns26:idCliente>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:idFacturador">
                                          <ns26:idFacturador>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:idFacturador"/>
                                          </ns26:idFacturador>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:scr">
                                          <ns26:scr>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:scr"/>
                                          </ns26:scr>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:estadoScr">
                                          <ns26:estadoScr>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:estadoScr"/>
                                          </ns26:estadoScr>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:observacion">
                                          <ns26:observacion>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:observacion"/>
                                          </ns26:observacion>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:fecha">
                                          <ns26:fecha>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:fecha"/>
                                          </ns26:fecha>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:usuarioModifico">
                                          <ns26:usuarioModifico>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:usuarioModifico"/>
                                          </ns26:usuarioModifico>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:usuarioValido">
                                          <ns26:usuarioValido>
                                             <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:scrReferencia/ns26:usuarioValido"/>
                                          </ns26:usuarioValido>
                                       </xsl:if>
                                    </ns26:scrReferencia>
                                    <ns26:estatusComparacion>
                                       <xsl:value-of select="ns24:operacion/ns5:cliente/ns26:detalleSCR/ns26:estatusComparacion"/>
                                    </ns26:estatusComparacion>
                                 </ns26:detalleSCR>
                              </xsl:if>
                           </ns5:cliente>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:producto">
                           <ns5:producto>
                              <ns15:idProducto>
                                 <xsl:value-of select="ns24:operacion/ns5:producto/ns15:idProducto"/>
                              </ns15:idProducto>
                              <ns15:descripcion>
                                 <xsl:value-of select="ns24:operacion/ns5:producto/ns15:descripcion"/>
                              </ns15:descripcion>
                              <ns15:descripcionCorta>
                                 <xsl:value-of select="ns24:operacion/ns5:producto/ns15:descripcionCorta"/>
                              </ns15:descripcionCorta>
                              <ns15:fechaRegistro>
                                 <xsl:value-of select="ns24:operacion/ns5:producto/ns15:fechaRegistro"/>
                              </ns15:fechaRegistro>
                              <ns15:codExterno>
                                 <xsl:value-of select="ns24:operacion/ns5:producto/ns15:codExterno"/>
                              </ns15:codExterno>
                              <ns15:idInstrumentoFinanciero>
                                 <xsl:value-of select="ns24:operacion/ns5:producto/ns15:idInstrumentoFinanciero"/>
                              </ns15:idInstrumentoFinanciero>
                              <ns15:idTipoOperacion>
                                 <xsl:value-of select="ns24:operacion/ns5:producto/ns15:idTipoOperacion"/>
                              </ns15:idTipoOperacion>
                              <ns15:reglas>
                                 <ns15:banStatus>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:banStatus"/>
                                 </ns15:banStatus>
                                 <ns15:requiereElegibilidad>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereElegibilidad"/>
                                 </ns15:requiereElegibilidad>
                                 <ns15:requiereLaft>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereLaft"/>
                                 </ns15:requiereLaft>
                                 <ns15:OFICrealizaAnalisisLAFT>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:OFICrealizaAnalisisLAFT"/>
                                 </ns15:OFICrealizaAnalisisLAFT>
                                 <ns15:requiereEvaluacionExAnte>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereEvaluacionExAnte"/>
                                 </ns15:requiereEvaluacionExAnte>
                                 <ns15:requiereFormularProgramas>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereFormularProgramas"/>
                                 </ns15:requiereFormularProgramas>
                                 <ns15:elaboraAnalizarAdquisiciones>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:elaboraAnalizarAdquisiciones"/>
                                 </ns15:elaboraAnalizarAdquisiciones>
                                 <ns15:elaboraHojaTerminosPCT>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:elaboraHojaTerminosPCT"/>
                                 </ns15:elaboraHojaTerminosPCT>
                                 <ns15:elaboraHojaTerminosSEPRI>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:elaboraHojaTerminosSEPRI"/>
                                 </ns15:elaboraHojaTerminosSEPRI>
                                 <ns15:analizarModelo>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:analizarModelo"/>
                                 </ns15:analizarModelo>
                                 <ns15:realizarPreanalisis>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:realizarPreanalisis"/>
                                 </ns15:realizarPreanalisis>
                                 <ns15:participaSupervision>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:participaSupervision"/>
                                 </ns15:participaSupervision>
                                 <ns15:participaSeguimiento>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:participaSeguimiento"/>
                                 </ns15:participaSeguimiento>
                                 <ns15:participaFINAM>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:participaFINAM"/>
                                 </ns15:participaFINAM>
                                 <ns15:SRC>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:SRC"/>
                                 </ns15:SRC>
                                 <ns15:OpinionJuridica>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:OpinionJuridica"/>
                                 </ns15:OpinionJuridica>
                                 <ns15:firmaContrato>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:firmaContrato"/>
                                 </ns15:firmaContrato>
                                 <ns15:condicionesPreviasFormalizar>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:condicionesPreviasFormalizar"/>
                                 </ns15:condicionesPreviasFormalizar>
                                 <ns15:condicionesPreviasDesembolso>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:condicionesPreviasDesembolso"/>
                                 </ns15:condicionesPreviasDesembolso>
                                 <ns15:programacionDesembolsos>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:programacionDesembolsos"/>
                                 </ns15:programacionDesembolsos>
                                 <ns15:reglaLAFT>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:reglaLAFT"/>
                                 </ns15:reglaLAFT>
                                 <ns15:adquisiciones2>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:adquisiciones2"/>
                                 </ns15:adquisiciones2>
                                 <ns15:supervicionTecnica>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:supervicionTecnica"/>
                                 </ns15:supervicionTecnica>
                                 <ns15:seguimientoCrediticio>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:seguimientoCrediticio"/>
                                 </ns15:seguimientoCrediticio>
                                 <ns15:enmiendas>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:enmiendas"/>
                                 </ns15:enmiendas>
                                 <ns15:recuperacion>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:recuperacion"/>
                                 </ns15:recuperacion>
                                 <ns15:cierreOperativo>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:cierreOperativo"/>
                                 </ns15:cierreOperativo>
                                 <ns15:rendicionCuentas>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:rendicionCuentas"/>
                                 </ns15:rendicionCuentas>
                                 <ns15:evaluacionMedioTermino>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:evaluacionMedioTermino"/>
                                 </ns15:evaluacionMedioTermino>
                                 <ns15:evaluacionExPost>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:evaluacionExPost"/>
                                 </ns15:evaluacionExPost>
                                 <ns15:requiereAdquisiciones>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereAdquisiciones"/>
                                 </ns15:requiereAdquisiciones>
                                 <ns15:requiereRAROC>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereRAROC"/>
                                 </ns15:requiereRAROC>
                                 <ns15:requiereIBICIE>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereIBICIE"/>
                                 </ns15:requiereIBICIE>
                                 <ns15:requiereSIEMAS>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereSIEMAS"/>
                                 </ns15:requiereSIEMAS>
                                 <ns15:requiereGERIESElegibilidad>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereGERIESElegibilidad"/>
                                 </ns15:requiereGERIESElegibilidad>
                                 <ns15:requiereOpinionTecnica>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereOpinionTecnica"/>
                                 </ns15:requiereOpinionTecnica>
                                 <ns15:requiereAsjurAnalisis>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereAsjurAnalisis"/>
                                 </ns15:requiereAsjurAnalisis>
                                 <ns15:requiereAsjurElegibilidad>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereAsjurElegibilidad"/>
                                 </ns15:requiereAsjurElegibilidad>
                                 <ns15:requierePreparacion>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requierePreparacion"/>
                                 </ns15:requierePreparacion>
                                 <ns15:tieneRiesgoCredito>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:tieneRiesgoCredito"/>
                                 </ns15:tieneRiesgoCredito>
                                 <ns15:responsableAnalisis>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:responsableAnalisis"/>
                                 </ns15:responsableAnalisis>
                                 <ns15:nombreResponsableAnalisis>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:nombreResponsableAnalisis"/>
                                 </ns15:nombreResponsableAnalisis>
                                 <ns15:requiereFirmacontrato>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereFirmacontrato"/>
                                 </ns15:requiereFirmacontrato>
                                 <ns15:requiereRegistroLinea>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereRegistroLinea"/>
                                 </ns15:requiereRegistroLinea>
                                 <ns15:requiereCore>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:reglas/ns15:requiereCore"/>
                                 </ns15:requiereCore>
                              </ns15:reglas>
                              <ns15:instrumentoFinanciero>
                                 <ns15:descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:instrumentoFinanciero/ns15:descripcion"/>
                                 </ns15:descripcion>
                                 <ns15:descripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:instrumentoFinanciero/ns15:descripcionCorta"/>
                                 </ns15:descripcionCorta>
                                 <ns15:estado>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:instrumentoFinanciero/ns15:estado"/>
                                 </ns15:estado>
                                 <ns15:fechaRegistro>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:instrumentoFinanciero/ns15:fechaRegistro"/>
                                 </ns15:fechaRegistro>
                                 <ns15:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:instrumentoFinanciero/ns15:codigoExterno"/>
                                 </ns15:codigoExterno>
                              </ns15:instrumentoFinanciero>
                              <ns15:estatus>
                                 <xsl:value-of select="ns24:operacion/ns5:producto/ns15:estatus"/>
                              </ns15:estatus>
                              <ns15:Requiere_Cierre>
                                 <ns15:Requiere_Cierre>
                                    <xsl:value-of select="ns24:operacion/ns5:producto/ns15:Requiere_Cierre/ns15:Requiere_Cierre"/>
                                 </ns15:Requiere_Cierre>
                                 <xsl:if test="ns24:operacion/ns5:producto/ns15:Requiere_Cierre/ns15:Tipo_Cierre">
                                    <ns15:Tipo_Cierre>
                                       <xsl:value-of select="ns24:operacion/ns5:producto/ns15:Requiere_Cierre/ns15:Tipo_Cierre"/>
                                    </ns15:Tipo_Cierre>
                                 </xsl:if>
                              </ns15:Requiere_Cierre>
                           </ns5:producto>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:descripcion">
                           <ns5:descripcion>
                              <xsl:value-of select="ns24:operacion/ns5:descripcion"/>
                           </ns5:descripcion>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:actividadEconomica">
                           <ns5:actividadEconomica>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomica/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomica/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomica/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomica/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomica/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomica/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomica/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomica/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomica/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomica/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:actividadEconomica>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:actividadEconomicaAsociada">
                           <ns5:actividadEconomicaAsociada>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:actividadEconomicaAsociada/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:actividadEconomicaAsociada>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:objetivosEstrategicos">
                           <ns5:objetivosEstrategicos>
                              <xsl:value-of select="ns24:operacion/ns5:objetivosEstrategicos"/>
                           </ns5:objetivosEstrategicos>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:areaFocalizacion">
                           <ns5:areaFocalizacion>
                              <xsl:if test="ns24:operacion/ns5:areaFocalizacion/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:areaFocalizacion/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:areaFocalizacion/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:areaFocalizacion/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:areaFocalizacion/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:areaFocalizacion/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:areaFocalizacion/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:areaFocalizacion/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:areaFocalizacion/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:areaFocalizacion/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:areaFocalizacion>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:ejeEstrategico">
                           <ns5:ejeEstrategico>
                              <xsl:if test="ns24:operacion/ns5:ejeEstrategico/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:ejeEstrategico/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:ejeEstrategico/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:ejeEstrategico/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:ejeEstrategico/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:ejeEstrategico/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:ejeEstrategico/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:ejeEstrategico/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:ejeEstrategico/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:ejeEstrategico/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:ejeEstrategico>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:iniciativaEstrategica">
                           <ns5:iniciativaEstrategica>
                              <xsl:if test="ns24:operacion/ns5:iniciativaEstrategica/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:iniciativaEstrategica/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:iniciativaEstrategica/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:iniciativaEstrategica/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:iniciativaEstrategica/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:iniciativaEstrategica/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:iniciativaEstrategica/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:iniciativaEstrategica/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:iniciativaEstrategica/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:iniciativaEstrategica/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:iniciativaEstrategica>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:cantidadPaises">
                           <ns5:cantidadPaises>
                              <xsl:if test="ns24:operacion/ns5:cantidadPaises/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:cantidadPaises/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cantidadPaises/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:cantidadPaises/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cantidadPaises/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:cantidadPaises/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cantidadPaises/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:cantidadPaises/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:cantidadPaises/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:cantidadPaises/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:cantidadPaises>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:sectorMercado">
                           <ns5:sectorMercado>
                              <xsl:if test="ns24:operacion/ns5:sectorMercado/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:sectorMercado/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:sectorMercado/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:sectorMercado/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:sectorMercado/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:sectorMercado/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:sectorMercado/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:sectorMercado/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:sectorMercado/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:sectorMercado/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:sectorMercado>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:fechaInicio">
                           <ns5:fechaInicio>
                              <xsl:value-of select="ns24:operacion/ns5:fechaInicio"/>
                           </ns5:fechaInicio>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:programadoPOA">
                           <ns5:programadoPOA>
                              <xsl:value-of select="ns24:operacion/ns5:programadoPOA"/>
                           </ns5:programadoPOA>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:ejercicioPOA">
                           <ns5:ejercicioPOA>
                              <xsl:if test="ns24:operacion/ns5:ejercicioPOA/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:ejercicioPOA/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:ejercicioPOA/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:ejercicioPOA/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:ejercicioPOA/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:ejercicioPOA/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:ejercicioPOA/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:ejercicioPOA/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:ejercicioPOA/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:ejercicioPOA/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:ejercicioPOA>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:tipoGarantia">
                           <ns5:tipoGarantia>
                              <xsl:if test="ns24:operacion/ns5:tipoGarantia/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:tipoGarantia/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:tipoGarantia/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:tipoGarantia/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:tipoGarantia/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:tipoGarantia/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:tipoGarantia/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:tipoGarantia/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:tipoGarantia/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:tipoGarantia/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:tipoGarantia>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:componenteConcesionalidad">
                           <ns5:componenteConcesionalidad>
                              <xsl:value-of select="ns24:operacion/ns5:componenteConcesionalidad"/>
                           </ns5:componenteConcesionalidad>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:estructurador">
                           <ns5:estructurador>
                              <xsl:value-of select="ns24:operacion/ns5:estructurador"/>
                           </ns5:estructurador>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:beneficiario">
                           <ns5:beneficiario>
                              <xsl:value-of select="ns24:operacion/ns5:beneficiario"/>
                           </ns5:beneficiario>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:unidadEjecutora">
                           <ns5:unidadEjecutora>
                              <xsl:value-of select="ns24:operacion/ns5:unidadEjecutora"/>
                           </ns5:unidadEjecutora>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:programa">
                           <ns5:programa>
                              <xsl:value-of select="ns24:operacion/ns5:programa"/>
                           </ns5:programa>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:asociadas">
                           <ns5:asociadas>
                              <xsl:for-each select="ns24:operacion/ns5:asociadas/ns5:Operacion">
                                 <ns5:Operacion>
                                    <xsl:value-of select="."/>
                                 </ns5:Operacion>
                              </xsl:for-each>
                           </ns5:asociadas>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:declaracionJurada">
                           <ns5:declaracionJurada>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:idDeclaracion">
                                 <ns16:idDeclaracion>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:idDeclaracion"/>
                                 </ns16:idDeclaracion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:codigoExternoDeclaracion">
                                 <ns16:codigoExternoDeclaracion>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:codigoExternoDeclaracion"/>
                                 </ns16:codigoExternoDeclaracion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:titulo">
                                 <ns16:titulo>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:titulo"/>
                                 </ns16:titulo>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:estadoDeclaracion">
                                 <ns16:estadoDeclaracion>
                                    <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:estadoDeclaracion/ns16:codigoEstadoDeclaracion">
                                       <ns16:codigoEstadoDeclaracion>
                                          <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:estadoDeclaracion/ns16:codigoEstadoDeclaracion"/>
                                       </ns16:codigoEstadoDeclaracion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:estadoDeclaracion/ns16:nombreEstadoDeclaracion">
                                       <ns16:nombreEstadoDeclaracion>
                                          <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:estadoDeclaracion/ns16:nombreEstadoDeclaracion"/>
                                       </ns16:nombreEstadoDeclaracion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:estadoDeclaracion/ns16:EstadoNoObjecion">
                                       <ns16:EstadoNoObjecion>
                                          <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:estadoDeclaracion/ns16:EstadoNoObjecion"/>
                                       </ns16:EstadoNoObjecion>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:estadoDeclaracion/ns16:nombreEstadoNoObjecion">
                                       <ns16:nombreEstadoNoObjecion>
                                          <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:estadoDeclaracion/ns16:nombreEstadoNoObjecion"/>
                                       </ns16:nombreEstadoNoObjecion>
                                    </xsl:if>
                                 </ns16:estadoDeclaracion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:estadoBusqueda">
                                 <ns16:estadoBusqueda>
                                    <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:estadoBusqueda/ns16:codigoEstadoBusqueda">
                                       <ns16:codigoEstadoBusqueda>
                                          <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:estadoBusqueda/ns16:codigoEstadoBusqueda"/>
                                       </ns16:codigoEstadoBusqueda>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:estadoBusqueda/ns16:nombreEstadoBusqueda">
                                       <ns16:nombreEstadoBusqueda>
                                          <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:estadoBusqueda/ns16:nombreEstadoBusqueda"/>
                                       </ns16:nombreEstadoBusqueda>
                                    </xsl:if>
                                 </ns16:estadoBusqueda>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:calificacionDeRiesgo">
                                 <ns16:calificacionDeRiesgo>
                                    <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:calificacionDeRiesgo/ns16:codigoCalificacionDeRiesgo">
                                       <ns16:codigoCalificacionDeRiesgo>
                                          <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:calificacionDeRiesgo/ns16:codigoCalificacionDeRiesgo"/>
                                       </ns16:codigoCalificacionDeRiesgo>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:calificacionDeRiesgo/ns16:nombreCalificacionDeRiesgo">
                                       <ns16:nombreCalificacionDeRiesgo>
                                          <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:calificacionDeRiesgo/ns16:nombreCalificacionDeRiesgo"/>
                                       </ns16:nombreCalificacionDeRiesgo>
                                    </xsl:if>
                                 </ns16:calificacionDeRiesgo>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:fechaRegistro">
                                 <ns16:fechaRegistro>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:fechaRegistro"/>
                                 </ns16:fechaRegistro>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:fechaFirmaDeclaracion">
                                 <ns16:fechaFirmaDeclaracion>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:fechaFirmaDeclaracion"/>
                                 </ns16:fechaFirmaDeclaracion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:fechaVencimiento">
                                 <ns16:fechaVencimiento>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:fechaVencimiento"/>
                                 </ns16:fechaVencimiento>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:comentarioDeclaracion">
                                 <ns16:comentarioDeclaracion>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:comentarioDeclaracion"/>
                                 </ns16:comentarioDeclaracion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:comentarioBusqueda">
                                 <ns16:comentarioBusqueda>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:comentarioBusqueda"/>
                                 </ns16:comentarioBusqueda>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:elevarAOtraInstancia">
                                 <ns16:elevarAOtraInstancia>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:elevarAOtraInstancia"/>
                                 </ns16:elevarAOtraInstancia>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:tipoOrigen">
                                 <ns16:tipoOrigen>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:tipoOrigen"/>
                                 </ns16:tipoOrigen>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:tipoSeguimiento">
                                 <ns16:tipoSeguimiento>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:tipoSeguimiento"/>
                                 </ns16:tipoSeguimiento>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:instanciaProceso">
                                 <ns16:instanciaProceso>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:instanciaProceso"/>
                                 </ns16:instanciaProceso>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:esSoloLectura">
                                 <ns16:esSoloLectura>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:esSoloLectura"/>
                                 </ns16:esSoloLectura>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:declaracionJurada/ns16:status">
                                 <ns16:status>
                                    <xsl:value-of select="ns24:operacion/ns5:declaracionJurada/ns16:status"/>
                                 </ns16:status>
                              </xsl:if>
                           </ns5:declaracionJurada>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:masInformacionRiesgo">
                           <ns5:masInformacionRiesgo>
                              <xsl:value-of select="ns24:operacion/ns5:masInformacionRiesgo"/>
                           </ns5:masInformacionRiesgo>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:informacionAdicionalRiesgo">
                           <ns5:informacionAdicionalRiesgo>
                              <xsl:value-of select="ns24:operacion/ns5:informacionAdicionalRiesgo"/>
                           </ns5:informacionAdicionalRiesgo>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:masInformacionJuridico">
                           <ns5:masInformacionJuridico>
                              <xsl:value-of select="ns24:operacion/ns5:masInformacionJuridico"/>
                           </ns5:masInformacionJuridico>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:informacionAdicionalJuridico">
                           <ns5:informacionAdicionalJuridico>
                              <xsl:value-of select="ns24:operacion/ns5:informacionAdicionalJuridico"/>
                           </ns5:informacionAdicionalJuridico>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:contrapartesProhibidas">
                           <ns5:contrapartesProhibidas>
                              <xsl:value-of select="ns24:operacion/ns5:contrapartesProhibidas"/>
                           </ns5:contrapartesProhibidas>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:comentarioContrapartes">
                           <ns5:comentarioContrapartes>
                              <xsl:value-of select="ns24:operacion/ns5:comentarioContrapartes"/>
                           </ns5:comentarioContrapartes>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:limitesAnalisis">
                           <ns5:limitesAnalisis>
                              <xsl:value-of select="ns24:operacion/ns5:limitesAnalisis"/>
                           </ns5:limitesAnalisis>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:limitesDeterminar">
                           <ns5:limitesDeterminar>
                              <xsl:value-of select="ns24:operacion/ns5:limitesDeterminar"/>
                           </ns5:limitesDeterminar>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:etapa">
                           <ns5:etapa>
                              <xsl:value-of select="ns24:operacion/ns5:etapa"/>
                           </ns5:etapa>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:status">
                           <ns5:status>
                              <xsl:value-of select="ns24:operacion/ns5:status"/>
                           </ns5:status>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:fechaBaja">
                           <ns5:fechaBaja>
                              <xsl:value-of select="ns24:operacion/ns5:fechaBaja"/>
                           </ns5:fechaBaja>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:comentarioLaft">
                           <ns5:comentarioLaft>
                              <xsl:value-of select="ns24:operacion/ns5:comentarioLaft"/>
                           </ns5:comentarioLaft>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:cumpleLaft">
                           <ns5:cumpleLaft>
                              <xsl:value-of select="ns24:operacion/ns5:cumpleLaft"/>
                           </ns5:cumpleLaft>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:calificacionRiesgo">
                           <ns5:calificacionRiesgo>
                              <xsl:value-of select="ns24:operacion/ns5:calificacionRiesgo"/>
                           </ns5:calificacionRiesgo>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:perspectiva">
                           <ns5:perspectiva>
                              <xsl:if test="ns24:operacion/ns5:perspectiva/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:perspectiva/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:perspectiva/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:perspectiva/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:perspectiva/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:perspectiva/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:perspectiva/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:perspectiva/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:perspectiva/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:perspectiva/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:perspectiva>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:estado">
                           <ns5:estado>
                              <xsl:if test="ns24:operacion/ns5:estado/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:estado/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:estado/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:estado/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:estado/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:estado/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:estado/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:estado/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:estado/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:estado/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:estado>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:lineaCredito">
                           <ns5:lineaCredito>
                              <xsl:value-of select="ns24:operacion/ns5:lineaCredito"/>
                           </ns5:lineaCredito>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:SRC">
                           <ns5:SRC>
                              <xsl:if test="ns24:operacion/ns5:SRC/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:SRC/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:SRC/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:SRC/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:SRC/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:SRC/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:SRC/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:SRC/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:SRC/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:SRC/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:SRC>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:scrClienteDesembolsos">
                           <ns5:scrClienteDesembolsos>
                              <xsl:if test="ns24:operacion/ns5:scrClienteDesembolsos/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:operacion/ns5:scrClienteDesembolsos/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:scrClienteDesembolsos/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:operacion/ns5:scrClienteDesembolsos/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:scrClienteDesembolsos/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:operacion/ns5:scrClienteDesembolsos/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:scrClienteDesembolsos/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:operacion/ns5:scrClienteDesembolsos/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:scrClienteDesembolsos/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:operacion/ns5:scrClienteDesembolsos/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns5:scrClienteDesembolsos>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:RAROC">
                           <ns5:RAROC>
                              <xsl:value-of select="ns24:operacion/ns5:RAROC"/>
                           </ns5:RAROC>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:TIR">
                           <ns5:TIR>
                              <xsl:value-of select="ns24:operacion/ns5:TIR"/>
                           </ns5:TIR>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:TIREstatus">
                           <ns5:TIREstatus>
                              <xsl:value-of select="ns24:operacion/ns5:TIREstatus"/>
                           </ns5:TIREstatus>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:requiereRecursosExternos">
                           <ns5:requiereRecursosExternos>
                              <xsl:value-of select="ns24:operacion/ns5:requiereRecursosExternos"/>
                           </ns5:requiereRecursosExternos>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:enProcesoLaft">
                           <ns5:enProcesoLaft>
                              <xsl:value-of select="ns24:operacion/ns5:enProcesoLaft"/>
                           </ns5:enProcesoLaft>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:aplicaLaft">
                           <ns5:aplicaLaft>
                              <xsl:value-of select="ns24:operacion/ns5:aplicaLaft"/>
                           </ns5:aplicaLaft>
                        </xsl:if>
                        <ns5:montoOperacion>
                           <xsl:for-each select="ns24:operacion/ns5:montoOperacion/ns5:montoOperacion">
                              <ns5:montoOperacion>
                                 <xsl:if test="ns5:id">
                                    <ns5:id>
                                       <xsl:value-of select="ns5:id"/>
                                    </ns5:id>
                                 </xsl:if>
                                 <xsl:if test="ns5:idOperacion">
                                    <ns5:idOperacion>
                                       <xsl:value-of select="ns5:idOperacion"/>
                                    </ns5:idOperacion>
                                 </xsl:if>
                                 <xsl:if test="ns5:IdTcaTipoMonto">
                                    <ns5:IdTcaTipoMonto>
                                       <xsl:value-of select="ns5:IdTcaTipoMonto"/>
                                    </ns5:IdTcaTipoMonto>
                                 </xsl:if>
                                 <xsl:if test="ns5:monto">
                                    <ns5:monto>
                                       <xsl:value-of select="ns5:monto"/>
                                    </ns5:monto>
                                 </xsl:if>
                                 <xsl:if test="ns5:Descripcion">
                                    <ns5:Descripcion>
                                       <xsl:value-of select="ns5:Descripcion"/>
                                    </ns5:Descripcion>
                                 </xsl:if>
                                 <xsl:if test="ns5:fechaRegistro">
                                    <ns5:fechaRegistro>
                                       <xsl:value-of select="ns5:fechaRegistro"/>
                                    </ns5:fechaRegistro>
                                 </xsl:if>
                                 <xsl:if test="ns5:estado">
                                    <ns5:estado>
                                       <xsl:value-of select="ns5:estado"/>
                                    </ns5:estado>
                                 </xsl:if>
                              </ns5:montoOperacion>
                           </xsl:for-each>
                        </ns5:montoOperacion>
                        <xsl:if test="ns24:operacion/ns5:justificacionCancela">
                           <ns5:justificacionCancela>
                              <xsl:value-of select="ns24:operacion/ns5:justificacionCancela"/>
                           </ns5:justificacionCancela>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:observacionCancela">
                           <ns5:observacionCancela>
                              <xsl:value-of select="ns24:operacion/ns5:observacionCancela"/>
                           </ns5:observacionCancela>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:justificacionEnvio">
                           <ns5:justificacionEnvio>
                              <xsl:value-of select="ns24:operacion/ns5:justificacionEnvio"/>
                           </ns5:justificacionEnvio>
                        </xsl:if>
                        <xsl:if test="ns24:operacion/ns5:Factibilidad">
                           <ns5:Factibilidad>
                              <ns23:tipo>
                                 <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:Id">
                                    <ns22:Id>
                                       <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:Id"/>
                                    </ns22:Id>
                                 </xsl:if>
                                 <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:Descripcion">
                                    <ns22:Descripcion>
                                       <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:Descripcion"/>
                                    </ns22:Descripcion>
                                 </xsl:if>
                                 <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:DescripcionCorta">
                                    <ns22:DescripcionCorta>
                                       <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:DescripcionCorta"/>
                                    </ns22:DescripcionCorta>
                                 </xsl:if>
                                 <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:estatus">
                                    <ns22:estatus>
                                       <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:estatus"/>
                                    </ns22:estatus>
                                 </xsl:if>
                                 <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:codigoExterno">
                                    <ns22:codigoExterno>
                                       <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:tipo/ns22:codigoExterno"/>
                                    </ns22:codigoExterno>
                                 </xsl:if>
                              </ns23:tipo>
                              <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:fija">
                                 <ns23:fija>
                                    <ns23:valor>
                                       <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:fija/ns23:valor"/>
                                    </ns23:valor>
                                 </ns23:fija>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable">
                                 <ns23:variable>
                                    <ns23:tasaReferencia>
                                       <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:Id">
                                          <ns22:Id>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:Id"/>
                                          </ns22:Id>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:Descripcion">
                                          <ns22:Descripcion>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:Descripcion"/>
                                          </ns22:Descripcion>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:DescripcionCorta">
                                          <ns22:DescripcionCorta>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:DescripcionCorta"/>
                                          </ns22:DescripcionCorta>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:estatus">
                                          <ns22:estatus>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:estatus"/>
                                          </ns22:estatus>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:codigoExterno">
                                          <ns22:codigoExterno>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns22:codigoExterno"/>
                                          </ns22:codigoExterno>
                                       </xsl:if>
                                       <ns23:valor>
                                          <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:tasaReferencia/ns23:valor"/>
                                       </ns23:valor>
                                    </ns23:tasaReferencia>
                                    <ns23:spread>
                                       <ns23:valor>
                                          <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:valor"/>
                                       </ns23:valor>
                                       <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:codigo">
                                          <ns23:codigo>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:codigo"/>
                                          </ns23:codigo>
                                       </xsl:if>
                                       <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision">
                                          <ns23:revision>
                                             <ns23:FechaInicio>
                                                <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:FechaInicio"/>
                                             </ns23:FechaInicio>
                                             <ns23:Frecuencia>
                                                <ns7:Tipo>
                                                   <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns7:Tipo>
                                                <ns7:Valor>
                                                   <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Valor"/>
                                                </ns7:Valor>
                                             </ns23:Frecuencia>
                                          </ns23:revision>
                                       </xsl:if>
                                    </ns23:spread>
                                    <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision">
                                       <ns23:revision>
                                          <ns23:FechaInicio>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:FechaInicio"/>
                                          </ns23:FechaInicio>
                                          <ns23:Frecuencia>
                                             <ns7:Tipo>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns7:Tipo>
                                             <ns7:Valor>
                                                <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Valor"/>
                                             </ns7:Valor>
                                          </ns23:Frecuencia>
                                       </ns23:revision>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:limite">
                                       <ns23:limite>
                                          <ns7:maximo>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:limite/ns7:maximo"/>
                                          </ns7:maximo>
                                          <ns7:minimo>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:variable/ns23:limite/ns7:minimo"/>
                                          </ns7:minimo>
                                       </ns23:limite>
                                    </xsl:if>
                                 </ns23:variable>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:especial">
                                 <ns23:especial>
                                    <ns23:valor>
                                       <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:especial/ns23:valor"/>
                                    </ns23:valor>
                                 </ns23:especial>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:spreadMora">
                                 <ns23:spreadMora>
                                    <ns23:valor>
                                       <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:valor"/>
                                    </ns23:valor>
                                    <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:codigo">
                                       <ns23:codigo>
                                          <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:codigo"/>
                                       </ns23:codigo>
                                    </xsl:if>
                                    <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision">
                                       <ns23:revision>
                                          <ns23:FechaInicio>
                                             <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:FechaInicio"/>
                                          </ns23:FechaInicio>
                                          <ns23:Frecuencia>
                                             <ns7:Tipo>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns7:Tipo>
                                             <ns7:Valor>
                                                <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Valor"/>
                                             </ns7:Valor>
                                          </ns23:Frecuencia>
                                       </ns23:revision>
                                    </xsl:if>
                                 </ns23:spreadMora>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:Factibilidad/ns5:esFactible">
                                 <ns5:esFactible>
                                    <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns5:esFactible"/>
                                 </ns5:esFactible>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:Factibilidad/ns5:fechaFinalizacionEstudios">
                                 <ns5:fechaFinalizacionEstudios>
                                    <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns5:fechaFinalizacionEstudios"/>
                                 </ns5:fechaFinalizacionEstudios>
                              </xsl:if>
                              <xsl:if test="ns24:operacion/ns5:Factibilidad/ns5:fechaCalculoInteres">
                                 <ns5:fechaCalculoInteres>
                                    <xsl:value-of select="ns24:operacion/ns5:Factibilidad/ns5:fechaCalculoInteres"/>
                                 </ns5:fechaCalculoInteres>
                              </xsl:if>
                           </ns5:Factibilidad>
                        </xsl:if>
                        <xsl:for-each select="ns24:operacion/ns5:contrato">
                           <ns5:contrato>
                              <ns25:idContrato>
                                 <xsl:value-of select="ns25:idContrato"/>
                              </ns25:idContrato>
                              <ns25:idOperacion>
                                 <xsl:value-of select="ns25:idOperacion"/>
                              </ns25:idOperacion>
                              <ns25:fechaFirma>
                                 <xsl:value-of select="ns25:fechaFirma"/>
                              </ns25:fechaFirma>
                              <ns25:fechaVigencia>
                                 <xsl:value-of select="ns25:fechaVigencia"/>
                              </ns25:fechaVigencia>
                              <ns25:MontoEscriturado>
                                 <xsl:value-of select="ns25:MontoEscriturado"/>
                              </ns25:MontoEscriturado>
                              <ns25:diaPago>
                                 <xsl:value-of select="ns25:diaPago"/>
                              </ns25:diaPago>
                              <ns25:cuentaCliente>
                                 <xsl:for-each select="ns25:cuentaCliente/ns25:cuentaCliente">
                                    <ns25:cuentaCliente>
                                       <xsl:value-of select="."/>
                                    </ns25:cuentaCliente>
                                 </xsl:for-each>
                              </ns25:cuentaCliente>
                              <ns25:instanciaProceso>
                                 <xsl:value-of select="ns25:instanciaProceso"/>
                              </ns25:instanciaProceso>
                              <xsl:for-each select="ns25:LineaCredito">
                                 <ns25:LineaCredito>
                                    <xsl:if test="ns10:idLineaCredito">
                                       <ns10:idLineaCredito>
                                          <xsl:value-of select="ns10:idLineaCredito"/>
                                       </ns10:idLineaCredito>
                                    </xsl:if>
                                    <xsl:if test="ns10:idContrato">
                                       <ns10:idContrato>
                                          <xsl:value-of select="ns10:idContrato"/>
                                       </ns10:idContrato>
                                    </xsl:if>
                                    <xsl:if test="ns10:idOperacion">
                                       <ns10:idOperacion>
                                          <xsl:value-of select="ns10:idOperacion"/>
                                       </ns10:idOperacion>
                                    </xsl:if>
                                    <ns10:NumeroLineaCredito>
                                       <xsl:value-of select="ns10:NumeroLineaCredito"/>
                                    </ns10:NumeroLineaCredito>
                                    <ns10:Descripcion>
                                       <xsl:value-of select="ns10:Descripcion"/>
                                    </ns10:Descripcion>
                                    <xsl:if test="ns10:Flexcube">
                                       <ns10:Flexcube>
                                          <xsl:if test="ns10:Flexcube/ns10:id">
                                             <ns10:id>
                                                <xsl:value-of select="ns10:Flexcube/ns10:id"/>
                                             </ns10:id>
                                          </xsl:if>
                                          <xsl:if test="ns10:Flexcube/ns10:idProductoFlexcube">
                                             <ns10:idProductoFlexcube>
                                                <xsl:value-of select="ns10:Flexcube/ns10:idProductoFlexcube"/>
                                             </ns10:idProductoFlexcube>
                                          </xsl:if>
                                          <xsl:if test="ns10:Flexcube/ns10:idFlexcubePasivo">
                                             <ns10:idFlexcubePasivo>
                                                <xsl:value-of select="ns10:Flexcube/ns10:idFlexcubePasivo"/>
                                             </ns10:idFlexcubePasivo>
                                          </xsl:if>
                                       </ns10:Flexcube>
                                    </xsl:if>
                                    <xsl:if test="ns10:Fondo">
                                       <ns10:Fondo>
                                          <xsl:value-of select="ns10:Fondo"/>
                                       </ns10:Fondo>
                                    </xsl:if>
                                    <xsl:if test="ns10:MontoLinea">
                                       <ns10:MontoLinea>
                                          <xsl:value-of select="ns10:MontoLinea"/>
                                       </ns10:MontoLinea>
                                    </xsl:if>
                                    <xsl:if test="ns10:EsRevolvente">
                                       <ns10:EsRevolvente>
                                          <xsl:value-of select="ns10:EsRevolvente"/>
                                       </ns10:EsRevolvente>
                                    </xsl:if>
                                    <ns10:TratamientoDiasFeriados>
                                       <xsl:value-of select="ns10:TratamientoDiasFeriados"/>
                                    </ns10:TratamientoDiasFeriados>
                                    <ns10:FechaValor>
                                       <xsl:value-of select="ns10:FechaValor"/>
                                    </ns10:FechaValor>
                                    <ns10:FechaVencimiento>
                                       <xsl:value-of select="ns10:FechaVencimiento"/>
                                    </ns10:FechaVencimiento>
                                    <ns10:CodigoPantallaLimite>
                                       <xsl:value-of select="ns10:CodigoPantallaLimite"/>
                                    </ns10:CodigoPantallaLimite>
                                    <ns10:CodigoSerialLimite>
                                       <xsl:value-of select="ns10:CodigoSerialLimite"/>
                                    </ns10:CodigoSerialLimite>
                                    <xsl:if test="ns10:CodigoAsignacion">
                                       <ns10:CodigoAsignacion>
                                          <xsl:value-of select="ns10:CodigoAsignacion"/>
                                       </ns10:CodigoAsignacion>
                                    </xsl:if>
                                    <ns10:DescripcionAsignacion>
                                       <xsl:value-of select="ns10:DescripcionAsignacion"/>
                                    </ns10:DescripcionAsignacion>
                                    <xsl:if test="ns10:CondicionEspecial">
                                       <ns10:CondicionEspecial>
                                          <xsl:value-of select="ns10:CondicionEspecial"/>
                                       </ns10:CondicionEspecial>
                                    </xsl:if>
                                    <ns10:FechaRegistro>
                                       <xsl:value-of select="ns10:FechaRegistro"/>
                                    </ns10:FechaRegistro>
                                    <ns10:FechaMaximaDesembolso>
                                       <xsl:value-of select="ns10:FechaMaximaDesembolso"/>
                                    </ns10:FechaMaximaDesembolso>
                                    <xsl:if test="ns10:Estado">
                                       <ns10:Estado>
                                          <xsl:value-of select="ns10:Estado"/>
                                       </ns10:Estado>
                                    </xsl:if>
                                    <ns10:descCondEspecial>
                                       <xsl:value-of select="ns10:descCondEspecial"/>
                                    </ns10:descCondEspecial>
                                    <ns10:frecuenciaGracia>
                                       <xsl:value-of select="ns10:frecuenciaGracia"/>
                                    </ns10:frecuenciaGracia>
                                    <ns10:plazoGracia>
                                       <xsl:value-of select="ns10:plazoGracia"/>
                                    </ns10:plazoGracia>
                                    <ns10:frecuenciaFinanciamiento>
                                       <xsl:value-of select="ns10:frecuenciaFinanciamiento"/>
                                    </ns10:frecuenciaFinanciamiento>
                                    <ns10:plazoFinanciamiento>
                                       <xsl:value-of select="ns10:plazoFinanciamiento"/>
                                    </ns10:plazoFinanciamiento>
                                    <ns10:recursosExternos>
                                       <xsl:value-of select="ns10:recursosExternos"/>
                                    </ns10:recursosExternos>
                                    <xsl:if test="ns10:tasaMinima">
                                       <ns10:tasaMinima>
                                          <xsl:if test="ns10:tasaMinima/@xsi:nil">
                                             <xsl:attribute name="nil" namespace="http://www.w3.org/2001/XMLSchema-instance">
                                                <xsl:value-of select="ns10:tasaMinima/@xsi:nil"/>
                                             </xsl:attribute>
                                          </xsl:if>
                                          <xsl:value-of select="ns10:tasaMinima"/>
                                       </ns10:tasaMinima>
                                    </xsl:if>
                                    <xsl:if test="ns10:tasaMaxima">
                                       <ns10:tasaMaxima>
                                          <xsl:if test="ns10:tasaMaxima/@xsi:nil">
                                             <xsl:attribute name="nil" namespace="http://www.w3.org/2001/XMLSchema-instance">
                                                <xsl:value-of select="ns10:tasaMaxima/@xsi:nil"/>
                                             </xsl:attribute>
                                          </xsl:if>
                                          <xsl:value-of select="ns10:tasaMaxima"/>
                                       </ns10:tasaMaxima>
                                    </xsl:if>
                                    <xsl:if test="ns10:montoMinimo">
                                       <ns10:montoMinimo>
                                          <xsl:if test="ns10:montoMinimo/@xsi:nil">
                                             <xsl:attribute name="nil" namespace="http://www.w3.org/2001/XMLSchema-instance">
                                                <xsl:value-of select="ns10:montoMinimo/@xsi:nil"/>
                                             </xsl:attribute>
                                          </xsl:if>
                                          <xsl:value-of select="ns10:montoMinimo"/>
                                       </ns10:montoMinimo>
                                    </xsl:if>
                                    <xsl:if test="ns10:montoMaximo">
                                       <ns10:montoMaximo>
                                          <xsl:if test="ns10:montoMaximo/@xsi:nil">
                                             <xsl:attribute name="nil" namespace="http://www.w3.org/2001/XMLSchema-instance">
                                                <xsl:value-of select="ns10:montoMaximo/@xsi:nil"/>
                                             </xsl:attribute>
                                          </xsl:if>
                                          <xsl:value-of select="ns10:montoMaximo"/>
                                       </ns10:montoMaximo>
                                    </xsl:if>
                                    <xsl:for-each select="ns10:Monto">
                                       <ns10:Monto>
                                          <ns7:tipo>
                                             <xsl:if test="ns7:tipo/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns7:tipo/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns7:tipo/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns7:tipo/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns7:tipo/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns7:tipo/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns7:tipo/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns7:tipo/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns7:tipo/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns7:tipo/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns7:tipo>
                                          <xsl:if test="ns7:importe">
                                             <ns7:importe>
                                                <xsl:value-of select="ns7:importe"/>
                                             </ns7:importe>
                                          </xsl:if>
                                          <xsl:if test="ns7:moneda">
                                             <ns7:moneda>
                                                <xsl:if test="ns7:moneda/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns7:moneda/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns7:moneda/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns7:moneda/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns7:moneda/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns7:moneda/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns7:moneda/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns7:moneda/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns7:moneda/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns7:moneda/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns7:moneda>
                                          </xsl:if>
                                       </ns10:Monto>
                                    </xsl:for-each>
                                    <xsl:if test="ns10:EstadoMonto">
                                       <ns10:EstadoMonto>
                                          <xsl:value-of select="ns10:EstadoMonto"/>
                                       </ns10:EstadoMonto>
                                    </xsl:if>
                                    <xsl:for-each select="ns10:Condicion">
                                       <ns10:Condicion>
                                          <ns14:idCondicion>
                                             <xsl:value-of select="ns14:idCondicion"/>
                                          </ns14:idCondicion>
                                          <ns14:operacion>
                                             <xsl:value-of select="ns14:operacion"/>
                                          </ns14:operacion>
                                          <xsl:if test="ns14:nombre">
                                             <ns14:nombre>
                                                <xsl:value-of select="ns14:nombre"/>
                                             </ns14:nombre>
                                          </xsl:if>
                                          <xsl:if test="ns14:descripcion">
                                             <ns14:descripcion>
                                                <xsl:value-of select="ns14:descripcion"/>
                                             </ns14:descripcion>
                                          </xsl:if>
                                          <xsl:if test="ns14:tipoCondicion">
                                             <ns14:tipoCondicion>
                                                <xsl:if test="ns14:tipoCondicion/ns14:idCatCondicion">
                                                   <ns14:idCatCondicion>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:idCatCondicion"/>
                                                   </ns14:idCatCondicion>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:descripcion">
                                                   <ns14:descripcion>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:descripcion"/>
                                                   </ns14:descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:descripcionCorta">
                                                   <ns14:descripcionCorta>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:descripcionCorta"/>
                                                   </ns14:descripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:idTipoCondicion">
                                                   <ns14:idTipoCondicion>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:idTipoCondicion"/>
                                                   </ns14:idTipoCondicion>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:esEditableEnFormalizacion">
                                                   <ns14:esEditableEnFormalizacion>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:esEditableEnFormalizacion"/>
                                                   </ns14:esEditableEnFormalizacion>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:requiereValidarCOFI">
                                                   <ns14:requiereValidarCOFI>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:requiereValidarCOFI"/>
                                                   </ns14:requiereValidarCOFI>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:sePuedeDispensar">
                                                   <ns14:sePuedeDispensar>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:sePuedeDispensar"/>
                                                   </ns14:sePuedeDispensar>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:esPlantilla">
                                                   <ns14:esPlantilla>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:esPlantilla"/>
                                                   </ns14:esPlantilla>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:idCondicionPrecarga">
                                                   <ns14:idCondicionPrecarga>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:idCondicionPrecarga"/>
                                                   </ns14:idCondicionPrecarga>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:fechaRegistro">
                                                   <ns14:fechaRegistro>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:fechaRegistro"/>
                                                   </ns14:fechaRegistro>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:estado">
                                                   <ns14:estado>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:estado"/>
                                                   </ns14:estado>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:codigoExterno">
                                                   <ns14:codigoExterno>
                                                      <xsl:value-of select="ns14:tipoCondicion/ns14:codigoExterno"/>
                                                   </ns14:codigoExterno>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoCondicion/ns14:tipoCondicion">
                                                   <ns14:tipoCondicion>
                                                      <xsl:if test="ns14:tipoCondicion/ns14:tipoCondicion/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns14:tipoCondicion/ns14:tipoCondicion/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:tipoCondicion/ns14:tipoCondicion/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns14:tipoCondicion/ns14:tipoCondicion/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:tipoCondicion/ns14:tipoCondicion/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns14:tipoCondicion/ns14:tipoCondicion/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:tipoCondicion/ns14:tipoCondicion/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns14:tipoCondicion/ns14:tipoCondicion/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:tipoCondicion/ns14:tipoCondicion/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns14:tipoCondicion/ns14:tipoCondicion/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns14:tipoCondicion>
                                                </xsl:if>
                                             </ns14:tipoCondicion>
                                          </xsl:if>
                                          <xsl:for-each select="ns14:categoriaCondicion">
                                             <ns14:categoriaCondicion>
                                                <ns14:id>
                                                   <xsl:value-of select="ns14:id"/>
                                                </ns14:id>
                                                <ns14:descripcion>
                                                   <xsl:value-of select="ns14:descripcion"/>
                                                </ns14:descripcion>
                                                <xsl:if test="ns14:descripcionCorta">
                                                   <ns14:descripcionCorta>
                                                      <xsl:value-of select="ns14:descripcionCorta"/>
                                                   </ns14:descripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns14:estatus">
                                                   <ns14:estatus>
                                                      <xsl:value-of select="ns14:estatus"/>
                                                   </ns14:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns14:codigoExterno">
                                                   <ns14:codigoExterno>
                                                      <xsl:value-of select="ns14:codigoExterno"/>
                                                   </ns14:codigoExterno>
                                                </xsl:if>
                                                <xsl:for-each select="ns14:validadores">
                                                   <ns14:validadores>
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
                                                   </ns14:validadores>
                                                </xsl:for-each>
                                             </ns14:categoriaCondicion>
                                          </xsl:for-each>
                                          <xsl:if test="ns14:controlCondicion">
                                             <ns14:controlCondicion>
                                                <xsl:if test="ns14:controlCondicion/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns14:controlCondicion/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns14:controlCondicion/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns14:controlCondicion/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns14:controlCondicion/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns14:controlCondicion/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns14:controlCondicion/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns14:controlCondicion/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns14:controlCondicion/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns14:controlCondicion/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns14:controlCondicion>
                                          </xsl:if>
                                          <xsl:for-each select="ns14:eventoCondicion">
                                             <ns14:eventoCondicion>
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
                                             </ns14:eventoCondicion>
                                          </xsl:for-each>
                                          <xsl:if test="ns14:tipoFechaInicio">
                                             <ns14:tipoFechaInicio>
                                                <xsl:if test="ns14:tipoFechaInicio/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns14:tipoFechaInicio/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoFechaInicio/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns14:tipoFechaInicio/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoFechaInicio/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns14:tipoFechaInicio/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoFechaInicio/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns14:tipoFechaInicio/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoFechaInicio/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns14:tipoFechaInicio/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns14:tipoFechaInicio>
                                          </xsl:if>
                                          <xsl:if test="ns14:fechaInicio">
                                             <ns14:fechaInicio>
                                                <xsl:value-of select="ns14:fechaInicio"/>
                                             </ns14:fechaInicio>
                                          </xsl:if>
                                          <xsl:if test="ns14:plazo">
                                             <ns14:plazo>
                                                <xsl:value-of select="ns14:plazo"/>
                                             </ns14:plazo>
                                          </xsl:if>
                                          <xsl:if test="ns14:frecuenciaPlazo">
                                             <ns14:frecuenciaPlazo>
                                                <xsl:if test="ns14:frecuenciaPlazo/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns14:frecuenciaPlazo/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns14:frecuenciaPlazo/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns14:frecuenciaPlazo/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns14:frecuenciaPlazo/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns14:frecuenciaPlazo/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns14:frecuenciaPlazo/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns14:frecuenciaPlazo/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns14:frecuenciaPlazo/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns14:frecuenciaPlazo/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns14:frecuenciaPlazo>
                                          </xsl:if>
                                          <xsl:if test="ns14:fechaFinal">
                                             <ns14:fechaFinal>
                                                <xsl:value-of select="ns14:fechaFinal"/>
                                             </ns14:fechaFinal>
                                          </xsl:if>
                                          <xsl:if test="ns14:estadoTCC">
                                             <ns14:estadoTCC>
                                                <xsl:if test="ns14:estadoTCC/ns27:id">
                                                   <ns27:id>
                                                      <xsl:value-of select="ns14:estadoTCC/ns27:id"/>
                                                   </ns27:id>
                                                </xsl:if>
                                                <xsl:if test="ns14:estadoTCC/ns27:descripcion">
                                                   <ns27:descripcion>
                                                      <xsl:value-of select="ns14:estadoTCC/ns27:descripcion"/>
                                                   </ns27:descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns14:estadoTCC/ns27:descripcionCorta">
                                                   <ns27:descripcionCorta>
                                                      <xsl:value-of select="ns14:estadoTCC/ns27:descripcionCorta"/>
                                                   </ns27:descripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns14:estadoTCC/ns27:estatus">
                                                   <ns27:estatus>
                                                      <xsl:value-of select="ns14:estadoTCC/ns27:estatus"/>
                                                   </ns27:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns14:estadoTCC/ns27:codigoExterno">
                                                   <ns27:codigoExterno>
                                                      <xsl:value-of select="ns14:estadoTCC/ns27:codigoExterno"/>
                                                   </ns27:codigoExterno>
                                                </xsl:if>
                                                <xsl:if test="ns14:estadoTCC/ns27:esSubEstado">
                                                   <ns27:esSubEstado>
                                                      <xsl:value-of select="ns14:estadoTCC/ns27:esSubEstado"/>
                                                   </ns27:esSubEstado>
                                                </xsl:if>
                                             </ns14:estadoTCC>
                                          </xsl:if>
                                          <xsl:if test="ns14:subEstadoTCC">
                                             <ns14:subEstadoTCC>
                                                <xsl:if test="ns14:subEstadoTCC/ns27:id">
                                                   <ns27:id>
                                                      <xsl:value-of select="ns14:subEstadoTCC/ns27:id"/>
                                                   </ns27:id>
                                                </xsl:if>
                                                <xsl:if test="ns14:subEstadoTCC/ns27:descripcion">
                                                   <ns27:descripcion>
                                                      <xsl:value-of select="ns14:subEstadoTCC/ns27:descripcion"/>
                                                   </ns27:descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns14:subEstadoTCC/ns27:descripcionCorta">
                                                   <ns27:descripcionCorta>
                                                      <xsl:value-of select="ns14:subEstadoTCC/ns27:descripcionCorta"/>
                                                   </ns27:descripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns14:subEstadoTCC/ns27:estatus">
                                                   <ns27:estatus>
                                                      <xsl:value-of select="ns14:subEstadoTCC/ns27:estatus"/>
                                                   </ns27:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns14:subEstadoTCC/ns27:codigoExterno">
                                                   <ns27:codigoExterno>
                                                      <xsl:value-of select="ns14:subEstadoTCC/ns27:codigoExterno"/>
                                                   </ns27:codigoExterno>
                                                </xsl:if>
                                                <xsl:if test="ns14:subEstadoTCC/ns27:esSubEstado">
                                                   <ns27:esSubEstado>
                                                      <xsl:value-of select="ns14:subEstadoTCC/ns27:esSubEstado"/>
                                                   </ns27:esSubEstado>
                                                </xsl:if>
                                             </ns14:subEstadoTCC>
                                          </xsl:if>
                                          <xsl:if test="ns14:fechaRegistro">
                                             <ns14:fechaRegistro>
                                                <xsl:value-of select="ns14:fechaRegistro"/>
                                             </ns14:fechaRegistro>
                                          </xsl:if>
                                          <xsl:if test="ns14:estado">
                                             <ns14:estado>
                                                <xsl:value-of select="ns14:estado"/>
                                             </ns14:estado>
                                          </xsl:if>
                                          <xsl:if test="ns14:condicionEnmendada">
                                             <ns14:condicionEnmendada>
                                                <xsl:value-of select="ns14:condicionEnmendada"/>
                                             </ns14:condicionEnmendada>
                                          </xsl:if>
                                          <xsl:if test="ns14:fechaEnmienda">
                                             <ns14:fechaEnmienda>
                                                <xsl:value-of select="ns14:fechaEnmienda"/>
                                             </ns14:fechaEnmienda>
                                          </xsl:if>
                                          <xsl:for-each select="ns14:configAtributo">
                                             <ns14:configAtributo>
                                                <xsl:if test="ns27:id">
                                                   <ns27:id>
                                                      <xsl:value-of select="ns27:id"/>
                                                   </ns27:id>
                                                </xsl:if>
                                                <ns27:columna>
                                                   <xsl:value-of select="ns27:columna"/>
                                                </ns27:columna>
                                                <xsl:if test="ns27:ordenamiento">
                                                   <ns27:ordenamiento>
                                                      <xsl:value-of select="ns27:ordenamiento"/>
                                                   </ns27:ordenamiento>
                                                </xsl:if>
                                                <xsl:if test="ns27:soloLectura">
                                                   <ns27:soloLectura>
                                                      <xsl:value-of select="ns27:soloLectura"/>
                                                   </ns27:soloLectura>
                                                </xsl:if>
                                                <xsl:if test="ns27:esObligatorio">
                                                   <ns27:esObligatorio>
                                                      <xsl:value-of select="ns27:esObligatorio"/>
                                                   </ns27:esObligatorio>
                                                </xsl:if>
                                                <xsl:if test="ns27:etiqueta">
                                                   <ns27:etiqueta>
                                                      <xsl:value-of select="ns27:etiqueta"/>
                                                   </ns27:etiqueta>
                                                </xsl:if>
                                                <xsl:for-each select="ns27:valor">
                                                   <ns27:valor>
                                                      <xsl:value-of select="."/>
                                                   </ns27:valor>
                                                </xsl:for-each>
                                                <xsl:if test="ns27:tipoValor">
                                                   <ns27:tipoValor>
                                                      <xsl:value-of select="ns27:tipoValor"/>
                                                   </ns27:tipoValor>
                                                </xsl:if>
                                                <xsl:for-each select="ns27:catalogo">
                                                   <ns27:catalogo>
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
                                                   </ns27:catalogo>
                                                </xsl:for-each>
                                             </ns14:configAtributo>
                                          </xsl:for-each>
                                          <xsl:if test="ns14:evidencia">
                                             <ns14:evidencia>
                                                <xsl:for-each select="ns14:evidencia/ns19:Documento">
                                                   <ns19:Documento>
                                                      <xsl:if test="ns19:idDocumento">
                                                         <ns19:idDocumento>
                                                            <xsl:value-of select="ns19:idDocumento"/>
                                                         </ns19:idDocumento>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idTipoDocumento">
                                                         <ns19:idTipoDocumento>
                                                            <xsl:value-of select="ns19:idTipoDocumento"/>
                                                         </ns19:idTipoDocumento>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idTipoDocumentoExterno">
                                                         <ns19:idTipoDocumentoExterno>
                                                            <xsl:value-of select="ns19:idTipoDocumentoExterno"/>
                                                         </ns19:idTipoDocumentoExterno>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:nombreTipoDocumento">
                                                         <ns19:nombreTipoDocumento>
                                                            <xsl:value-of select="ns19:nombreTipoDocumento"/>
                                                         </ns19:nombreTipoDocumento>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:etapa">
                                                         <ns19:etapa>
                                                            <xsl:value-of select="ns19:etapa"/>
                                                         </ns19:etapa>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:codigoDocumento">
                                                         <ns19:codigoDocumento>
                                                            <xsl:value-of select="ns19:codigoDocumento"/>
                                                         </ns19:codigoDocumento>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idExterno">
                                                         <ns19:idExterno>
                                                            <xsl:value-of select="ns19:idExterno"/>
                                                         </ns19:idExterno>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idDocAreaTipo">
                                                         <ns19:idDocAreaTipo>
                                                            <xsl:value-of select="ns19:idDocAreaTipo"/>
                                                         </ns19:idDocAreaTipo>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idOperacion">
                                                         <ns19:idOperacion>
                                                            <xsl:value-of select="ns19:idOperacion"/>
                                                         </ns19:idOperacion>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idCliente">
                                                         <ns19:idCliente>
                                                            <xsl:value-of select="ns19:idCliente"/>
                                                         </ns19:idCliente>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idPais">
                                                         <ns19:idPais>
                                                            <xsl:value-of select="ns19:idPais"/>
                                                         </ns19:idPais>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:nombre">
                                                         <ns19:nombre>
                                                            <xsl:value-of select="ns19:nombre"/>
                                                         </ns19:nombre>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:filename">
                                                         <ns19:filename>
                                                            <xsl:value-of select="ns19:filename"/>
                                                         </ns19:filename>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:mime_type">
                                                         <ns19:mime_type>
                                                            <xsl:value-of select="ns19:mime_type"/>
                                                         </ns19:mime_type>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:tamanio">
                                                         <ns19:tamanio>
                                                            <xsl:value-of select="ns19:tamanio"/>
                                                         </ns19:tamanio>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idAdjunto">
                                                         <ns19:idAdjunto>
                                                            <xsl:value-of select="ns19:idAdjunto"/>
                                                         </ns19:idAdjunto>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:esJustificacion">
                                                         <ns19:esJustificacion>
                                                            <xsl:value-of select="ns19:esJustificacion"/>
                                                         </ns19:esJustificacion>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:comentario">
                                                         <ns19:comentario>
                                                            <xsl:value-of select="ns19:comentario"/>
                                                         </ns19:comentario>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:fechaDocumento">
                                                         <ns19:fechaDocumento>
                                                            <xsl:value-of select="ns19:fechaDocumento"/>
                                                         </ns19:fechaDocumento>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:fechaRegistro">
                                                         <ns19:fechaRegistro>
                                                            <xsl:value-of select="ns19:fechaRegistro"/>
                                                         </ns19:fechaRegistro>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:status">
                                                         <ns19:status>
                                                            <xsl:value-of select="ns19:status"/>
                                                         </ns19:status>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:estatusTipoDoc">
                                                         <ns19:estatusTipoDoc>
                                                            <xsl:value-of select="ns19:estatusTipoDoc"/>
                                                         </ns19:estatusTipoDoc>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:codExtTipoDoc">
                                                         <ns19:codExtTipoDoc>
                                                            <xsl:value-of select="ns19:codExtTipoDoc"/>
                                                         </ns19:codExtTipoDoc>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:tarea">
                                                         <ns19:tarea>
                                                            <xsl:value-of select="ns19:tarea"/>
                                                         </ns19:tarea>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idtarea">
                                                         <ns19:idtarea>
                                                            <xsl:value-of select="ns19:idtarea"/>
                                                         </ns19:idtarea>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:content">
                                                         <ns19:content>
                                                            <xsl:value-of select="ns19:content"/>
                                                         </ns19:content>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:PuedeModificar">
                                                         <ns19:PuedeModificar>
                                                            <xsl:value-of select="ns19:PuedeModificar"/>
                                                         </ns19:PuedeModificar>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:PuedeBorrar">
                                                         <ns19:PuedeBorrar>
                                                            <xsl:value-of select="ns19:PuedeBorrar"/>
                                                         </ns19:PuedeBorrar>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:usuarioAgrega">
                                                         <ns19:usuarioAgrega>
                                                            <xsl:value-of select="ns19:usuarioAgrega"/>
                                                         </ns19:usuarioAgrega>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:usuarioUltimaActualizacion">
                                                         <ns19:usuarioUltimaActualizacion>
                                                            <xsl:value-of select="ns19:usuarioUltimaActualizacion"/>
                                                         </ns19:usuarioUltimaActualizacion>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:accionARealizar">
                                                         <ns19:accionARealizar>
                                                            <xsl:value-of select="ns19:accionARealizar"/>
                                                         </ns19:accionARealizar>
                                                      </xsl:if>
                                                      <xsl:if test="ns19:idFlujoNegocio">
                                                         <ns19:idFlujoNegocio>
                                                            <xsl:value-of select="ns19:idFlujoNegocio"/>
                                                         </ns19:idFlujoNegocio>
                                                      </xsl:if>
                                                   </ns19:Documento>
                                                </xsl:for-each>
                                             </ns14:evidencia>
                                          </xsl:if>
                                          <xsl:for-each select="ns14:observaciones">
                                             <ns14:observaciones>
                                                <ns14:id>
                                                   <xsl:value-of select="ns14:id"/>
                                                </ns14:id>
                                                <xsl:if test="ns14:observacion">
                                                   <ns14:observacion>
                                                      <xsl:value-of select="ns14:observacion"/>
                                                   </ns14:observacion>
                                                </xsl:if>
                                                <xsl:if test="ns14:loginUsuario">
                                                   <ns14:loginUsuario>
                                                      <xsl:value-of select="ns14:loginUsuario"/>
                                                   </ns14:loginUsuario>
                                                </xsl:if>
                                                <xsl:if test="ns14:nombreUsuario">
                                                   <ns14:nombreUsuario>
                                                      <xsl:value-of select="ns14:nombreUsuario"/>
                                                   </ns14:nombreUsuario>
                                                </xsl:if>
                                                <xsl:if test="ns14:fechaRegistro">
                                                   <ns14:fechaRegistro>
                                                      <xsl:value-of select="ns14:fechaRegistro"/>
                                                   </ns14:fechaRegistro>
                                                </xsl:if>
                                                <xsl:if test="ns14:estatus">
                                                   <ns14:estatus>
                                                      <xsl:value-of select="ns14:estatus"/>
                                                   </ns14:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns14:esPrincipal">
                                                   <ns14:esPrincipal>
                                                      <xsl:value-of select="ns14:esPrincipal"/>
                                                   </ns14:esPrincipal>
                                                </xsl:if>
                                                <xsl:if test="ns14:tareaBPM">
                                                   <ns14:tareaBPM>
                                                      <xsl:if test="ns14:tareaBPM/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns14:tareaBPM/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:tareaBPM/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns14:tareaBPM/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:tareaBPM/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns14:tareaBPM/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:tareaBPM/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns14:tareaBPM/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:tareaBPM/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns14:tareaBPM/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns14:tareaBPM>
                                                </xsl:if>
                                             </ns14:observaciones>
                                          </xsl:for-each>
                                          <xsl:for-each select="ns14:lineaCredito">
                                             <ns14:lineaCredito>
                                                <xsl:if test="ns27:id">
                                                   <ns27:id>
                                                      <xsl:value-of select="ns27:id"/>
                                                   </ns27:id>
                                                </xsl:if>
                                                <xsl:if test="ns27:descripcion">
                                                   <ns27:descripcion>
                                                      <xsl:value-of select="ns27:descripcion"/>
                                                   </ns27:descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns27:estatus">
                                                   <ns27:estatus>
                                                      <xsl:value-of select="ns27:estatus"/>
                                                   </ns27:estatus>
                                                </xsl:if>
                                             </ns14:lineaCredito>
                                          </xsl:for-each>
                                          <xsl:for-each select="ns14:fuente">
                                             <ns14:fuente>
                                                <xsl:if test="ns27:id">
                                                   <ns27:id>
                                                      <xsl:value-of select="ns27:id"/>
                                                   </ns27:id>
                                                </xsl:if>
                                                <xsl:if test="ns27:descripcion">
                                                   <ns27:descripcion>
                                                      <xsl:value-of select="ns27:descripcion"/>
                                                   </ns27:descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns27:estatus">
                                                   <ns27:estatus>
                                                      <xsl:value-of select="ns27:estatus"/>
                                                   </ns27:estatus>
                                                </xsl:if>
                                             </ns14:fuente>
                                          </xsl:for-each>
                                          <xsl:if test="ns14:Accion">
                                             <ns14:Accion>
                                                <xsl:value-of select="ns14:Accion"/>
                                             </ns14:Accion>
                                          </xsl:if>
                                          <xsl:if test="ns14:fechaVigencia">
                                             <ns14:fechaVigencia>
                                                <xsl:value-of select="ns14:fechaVigencia"/>
                                             </ns14:fechaVigencia>
                                          </xsl:if>
                                          <xsl:for-each select="ns14:Cumplimientos">
                                             <ns14:Cumplimientos>
                                                <xsl:if test="ns14:Id">
                                                   <ns14:Id>
                                                      <xsl:value-of select="ns14:Id"/>
                                                   </ns14:Id>
                                                </xsl:if>
                                                <xsl:if test="ns14:Operacion">
                                                   <ns14:Operacion>
                                                      <xsl:if test="ns14:Operacion/ns5:idOperacion">
                                                         <ns5:idOperacion>
                                                            <xsl:value-of select="ns14:Operacion/ns5:idOperacion"/>
                                                         </ns5:idOperacion>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:Operacion/ns5:responsable">
                                                         <ns5:responsable>
                                                            <xsl:value-of select="ns14:Operacion/ns5:responsable"/>
                                                         </ns5:responsable>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:Operacion/ns5:oficina">
                                                         <ns5:oficina>
                                                            <xsl:value-of select="ns14:Operacion/ns5:oficina"/>
                                                         </ns5:oficina>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:Operacion/ns5:nombre">
                                                         <ns5:nombre>
                                                            <xsl:value-of select="ns14:Operacion/ns5:nombre"/>
                                                         </ns5:nombre>
                                                      </xsl:if>
                                                   </ns14:Operacion>
                                                </xsl:if>
                                                <xsl:if test="ns14:Solicitud">
                                                   <ns14:Solicitud>
                                                      <ns23:idDesembolso>
                                                         <xsl:value-of select="ns14:Solicitud/ns23:idDesembolso"/>
                                                      </ns23:idDesembolso>
                                                      <xsl:if test="ns14:Solicitud/ns23:idFacturador">
                                                         <ns23:idFacturador>
                                                            <xsl:value-of select="ns14:Solicitud/ns23:idFacturador"/>
                                                         </ns23:idFacturador>
                                                      </xsl:if>
                                                      <ns23:fechaCreacion>
                                                         <xsl:value-of select="ns14:Solicitud/ns23:fechaCreacion"/>
                                                      </ns23:fechaCreacion>
                                                      <ns23:fechaSolicitud>
                                                         <xsl:value-of select="ns14:Solicitud/ns23:fechaSolicitud"/>
                                                      </ns23:fechaSolicitud>
                                                      <ns23:monto>
                                                         <ns7:tipo>
                                                            <xsl:if test="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:Id">
                                                               <ns22:Id>
                                                                  <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:Id"/>
                                                               </ns22:Id>
                                                            </xsl:if>
                                                            <xsl:if test="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:Descripcion">
                                                               <ns22:Descripcion>
                                                                  <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:Descripcion"/>
                                                               </ns22:Descripcion>
                                                            </xsl:if>
                                                            <xsl:if test="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:DescripcionCorta">
                                                               <ns22:DescripcionCorta>
                                                                  <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:DescripcionCorta"/>
                                                               </ns22:DescripcionCorta>
                                                            </xsl:if>
                                                            <xsl:if test="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:estatus">
                                                               <ns22:estatus>
                                                                  <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:estatus"/>
                                                               </ns22:estatus>
                                                            </xsl:if>
                                                            <xsl:if test="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:codigoExterno">
                                                               <ns22:codigoExterno>
                                                                  <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:tipo/ns22:codigoExterno"/>
                                                               </ns22:codigoExterno>
                                                            </xsl:if>
                                                         </ns7:tipo>
                                                         <xsl:if test="ns14:Solicitud/ns23:monto/ns7:importe">
                                                            <ns7:importe>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:importe"/>
                                                            </ns7:importe>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:Solicitud/ns23:monto/ns7:moneda">
                                                            <ns7:moneda>
                                                               <xsl:if test="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:Solicitud/ns23:monto/ns7:moneda/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns7:moneda>
                                                         </xsl:if>
                                                      </ns23:monto>
                                                      <ns23:tipoMoneda>
                                                         <xsl:if test="ns14:Solicitud/ns23:tipoMoneda/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:tipoMoneda/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:Solicitud/ns23:tipoMoneda/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:tipoMoneda/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:Solicitud/ns23:tipoMoneda/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:tipoMoneda/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:Solicitud/ns23:tipoMoneda/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:tipoMoneda/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:Solicitud/ns23:tipoMoneda/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:tipoMoneda/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns23:tipoMoneda>
                                                      <ns23:estado>
                                                         <xsl:if test="ns14:Solicitud/ns23:estado/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:estado/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:Solicitud/ns23:estado/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:estado/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:Solicitud/ns23:estado/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:estado/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:Solicitud/ns23:estado/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:estado/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:Solicitud/ns23:estado/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns14:Solicitud/ns23:estado/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns23:estado>
                                                      <ns23:estatus>
                                                         <xsl:value-of select="ns14:Solicitud/ns23:estatus"/>
                                                      </ns23:estatus>
                                                      <ns23:instanciaProceso>
                                                         <xsl:value-of select="ns14:Solicitud/ns23:instanciaProceso"/>
                                                      </ns23:instanciaProceso>
                                                      <ns23:ValidacionAsignacion>
                                                         <xsl:value-of select="ns14:Solicitud/ns23:ValidacionAsignacion"/>
                                                      </ns23:ValidacionAsignacion>
                                                      <xsl:for-each select="ns14:Solicitud/ns23:Excepcion">
                                                         <ns23:Excepcion>
                                                            <ns4:Id>
                                                               <xsl:value-of select="ns4:Id"/>
                                                            </ns4:Id>
                                                            <xsl:if test="ns4:Descripcion">
                                                               <ns4:Descripcion>
                                                                  <xsl:value-of select="ns4:Descripcion"/>
                                                               </ns4:Descripcion>
                                                            </xsl:if>
                                                            <xsl:if test="ns4:Transaccion">
                                                               <ns4:Transaccion>
                                                                  <xsl:value-of select="ns4:Transaccion"/>
                                                               </ns4:Transaccion>
                                                            </xsl:if>
                                                            <xsl:if test="ns4:Tipo">
                                                               <ns4:Tipo>
                                                                  <xsl:if test="ns4:Tipo/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns4:Tipo/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Tipo/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns4:Tipo/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Tipo/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns4:Tipo/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Tipo/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns4:Tipo/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Tipo/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns4:Tipo/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns4:Tipo>
                                                            </xsl:if>
                                                            <xsl:if test="ns4:PosibleExceptuar">
                                                               <ns4:PosibleExceptuar>
                                                                  <xsl:value-of select="ns4:PosibleExceptuar"/>
                                                               </ns4:PosibleExceptuar>
                                                            </xsl:if>
                                                            <xsl:if test="ns4:Exceptuado">
                                                               <ns4:Exceptuado>
                                                                  <xsl:if test="ns4:Exceptuado/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns4:Exceptuado/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Exceptuado/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns4:Exceptuado/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Exceptuado/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns4:Exceptuado/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Exceptuado/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns4:Exceptuado/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Exceptuado/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns4:Exceptuado/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns4:Exceptuado>
                                                            </xsl:if>
                                                            <xsl:if test="ns4:UsuarioExceptua">
                                                               <ns4:UsuarioExceptua>
                                                                  <xsl:value-of select="ns4:UsuarioExceptua"/>
                                                               </ns4:UsuarioExceptua>
                                                            </xsl:if>
                                                            <xsl:if test="ns4:FechaExcepcion">
                                                               <ns4:FechaExcepcion>
                                                                  <xsl:value-of select="ns4:FechaExcepcion"/>
                                                               </ns4:FechaExcepcion>
                                                            </xsl:if>
                                                            <xsl:if test="ns4:Estado">
                                                               <ns4:Estado>
                                                                  <xsl:if test="ns4:Estado/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns4:Estado/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Estado/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns4:Estado/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Estado/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns4:Estado/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Estado/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns4:Estado/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns4:Estado/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns4:Estado/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns4:Estado>
                                                            </xsl:if>
                                                            <xsl:if test="ns4:Estatus">
                                                               <ns4:Estatus>
                                                                  <xsl:value-of select="ns4:Estatus"/>
                                                               </ns4:Estatus>
                                                            </xsl:if>
                                                            <xsl:for-each select="ns4:DetalleRegla">
                                                               <ns4:DetalleRegla>
                                                                  <xsl:if test="ns27:id">
                                                                     <ns27:id>
                                                                        <xsl:value-of select="ns27:id"/>
                                                                     </ns27:id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns27:descripcion">
                                                                     <ns27:descripcion>
                                                                        <xsl:value-of select="ns27:descripcion"/>
                                                                     </ns27:descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns27:estatus">
                                                                     <ns27:estatus>
                                                                        <xsl:value-of select="ns27:estatus"/>
                                                                     </ns27:estatus>
                                                                  </xsl:if>
                                                               </ns4:DetalleRegla>
                                                            </xsl:for-each>
                                                            <ns23:instanciaProceso>
                                                               <xsl:value-of select="ns23:instanciaProceso"/>
                                                            </ns23:instanciaProceso>
                                                            <ns23:enProcesoExcepcion>
                                                               <xsl:value-of select="ns23:enProcesoExcepcion"/>
                                                            </ns23:enProcesoExcepcion>
                                                         </ns23:Excepcion>
                                                      </xsl:for-each>
                                                   </ns14:Solicitud>
                                                </xsl:if>
                                                <xsl:if test="ns14:ContratoDesembolso">
                                                   <ns14:ContratoDesembolso>
                                                      <ns23:idDesembolso>
                                                         <xsl:value-of select="ns14:ContratoDesembolso/ns23:idDesembolso"/>
                                                      </ns23:idDesembolso>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:idFacturador">
                                                         <ns23:idFacturador>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:idFacturador"/>
                                                         </ns23:idFacturador>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:producto">
                                                         <ns23:producto>
                                                            <ns15:idProducto>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:producto/ns15:idProducto"/>
                                                            </ns15:idProducto>
                                                            <ns15:descripcion>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:producto/ns15:descripcion"/>
                                                            </ns15:descripcion>
                                                            <ns15:descripcionCorta>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:producto/ns15:descripcionCorta"/>
                                                            </ns15:descripcionCorta>
                                                            <ns15:fechaRegistro>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:producto/ns15:fechaRegistro"/>
                                                            </ns15:fechaRegistro>
                                                            <ns15:codExterno>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:producto/ns15:codExterno"/>
                                                            </ns15:codExterno>
                                                            <xsl:for-each select="ns14:ContratoDesembolso/ns23:producto/ns23:Componente">
                                                               <ns23:Componente>
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
                                                                  <ns23:TipoComponente>
                                                                     <xsl:if test="ns23:TipoComponente/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns23:TipoComponente/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoComponente/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns23:TipoComponente/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoComponente/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns23:TipoComponente/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoComponente/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns23:TipoComponente/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoComponente/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns23:TipoComponente/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns23:TipoComponente>
                                                                  <ns23:TipoTasa>
                                                                     <xsl:if test="ns23:TipoTasa/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns23:TipoTasa/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoTasa/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns23:TipoTasa/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoTasa/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns23:TipoTasa/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoTasa/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns23:TipoTasa/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoTasa/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns23:TipoTasa/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns23:TipoTasa>
                                                                  <ns23:esPrincipal>
                                                                     <xsl:value-of select="ns23:esPrincipal"/>
                                                                  </ns23:esPrincipal>
                                                                  <xsl:if test="ns23:Plazo">
                                                                     <ns23:Plazo>
                                                                        <ns7:Tipo>
                                                                           <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:Id">
                                                                              <ns22:Id>
                                                                                 <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:Id"/>
                                                                              </ns22:Id>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:Descripcion">
                                                                              <ns22:Descripcion>
                                                                                 <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:Descripcion"/>
                                                                              </ns22:Descripcion>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:DescripcionCorta">
                                                                              <ns22:DescripcionCorta>
                                                                                 <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                              </ns22:DescripcionCorta>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:estatus">
                                                                              <ns22:estatus>
                                                                                 <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:estatus"/>
                                                                              </ns22:estatus>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:codigoExterno">
                                                                              <ns22:codigoExterno>
                                                                                 <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:codigoExterno"/>
                                                                              </ns22:codigoExterno>
                                                                           </xsl:if>
                                                                        </ns7:Tipo>
                                                                        <ns7:Valor>
                                                                           <xsl:value-of select="ns23:Plazo/ns7:Valor"/>
                                                                        </ns7:Valor>
                                                                     </ns23:Plazo>
                                                                  </xsl:if>
                                                               </ns23:Componente>
                                                            </xsl:for-each>
                                                         </ns23:producto>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:referencia">
                                                         <ns23:referencia>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:referencia"/>
                                                         </ns23:referencia>
                                                      </xsl:if>
                                                      <xsl:for-each select="ns14:ContratoDesembolso/ns23:monto">
                                                         <ns23:monto>
                                                            <ns7:tipo>
                                                               <xsl:if test="ns7:tipo/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns7:tipo/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns7:tipo/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns7:tipo/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns7:tipo/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns7:tipo/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns7:tipo/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns7:tipo/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns7:tipo/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns7:tipo/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns7:tipo>
                                                            <xsl:if test="ns7:importe">
                                                               <ns7:importe>
                                                                  <xsl:value-of select="ns7:importe"/>
                                                               </ns7:importe>
                                                            </xsl:if>
                                                            <xsl:if test="ns7:moneda">
                                                               <ns7:moneda>
                                                                  <xsl:if test="ns7:moneda/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns7:moneda/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns7:moneda/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns7:moneda/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns7:moneda/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns7:moneda/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns7:moneda/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns7:moneda/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns7:moneda/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns7:moneda/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns7:moneda>
                                                            </xsl:if>
                                                         </ns23:monto>
                                                      </xsl:for-each>
                                                      <ns23:estado>
                                                         <xsl:if test="ns14:ContratoDesembolso/ns23:estado/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:estado/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:ContratoDesembolso/ns23:estado/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:estado/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:ContratoDesembolso/ns23:estado/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:estado/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:ContratoDesembolso/ns23:estado/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:estado/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns14:ContratoDesembolso/ns23:estado/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:estado/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns23:estado>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:programado">
                                                         <ns23:programado>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:programado"/>
                                                         </ns23:programado>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:fechaEstimadaDesembolsar">
                                                         <ns23:fechaEstimadaDesembolsar>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:fechaEstimadaDesembolsar"/>
                                                         </ns23:fechaEstimadaDesembolsar>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:fechaEfectiva">
                                                         <ns23:fechaEfectiva>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:fechaEfectiva"/>
                                                         </ns23:fechaEfectiva>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:fechaAsignacion">
                                                         <ns23:fechaAsignacion>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:fechaAsignacion"/>
                                                         </ns23:fechaAsignacion>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:fechaRegistro">
                                                         <ns23:fechaRegistro>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:fechaRegistro"/>
                                                         </ns23:fechaRegistro>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:fechaVencimiento">
                                                         <ns23:fechaVencimiento>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:fechaVencimiento"/>
                                                         </ns23:fechaVencimiento>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:estatus">
                                                         <ns23:estatus>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:estatus"/>
                                                         </ns23:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras">
                                                         <ns23:condicionesFinancieras>
                                                            <ns23:idCondicionFinanciera>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:idCondicionFinanciera"/>
                                                            </ns23:idCondicionFinanciera>
                                                            <ns23:tasa>
                                                               <ns23:tipo>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns23:tipo>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:fija">
                                                                  <ns23:fija>
                                                                     <ns23:valor>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:fija/ns23:valor"/>
                                                                     </ns23:valor>
                                                                  </ns23:fija>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable">
                                                                  <ns23:variable>
                                                                     <ns23:tasaReferencia>
                                                                        <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:Id">
                                                                           <ns22:Id>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:Id"/>
                                                                           </ns22:Id>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:Descripcion">
                                                                           <ns22:Descripcion>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:Descripcion"/>
                                                                           </ns22:Descripcion>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:DescripcionCorta">
                                                                           <ns22:DescripcionCorta>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:DescripcionCorta"/>
                                                                           </ns22:DescripcionCorta>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:estatus">
                                                                           <ns22:estatus>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:estatus"/>
                                                                           </ns22:estatus>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:codigoExterno">
                                                                           <ns22:codigoExterno>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:codigoExterno"/>
                                                                           </ns22:codigoExterno>
                                                                        </xsl:if>
                                                                        <ns23:valor>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns23:valor"/>
                                                                        </ns23:valor>
                                                                     </ns23:tasaReferencia>
                                                                     <ns23:spread>
                                                                        <ns23:valor>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:valor"/>
                                                                        </ns23:valor>
                                                                        <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:codigo">
                                                                           <ns23:codigo>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:codigo"/>
                                                                           </ns23:codigo>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision">
                                                                           <ns23:revision>
                                                                              <ns23:FechaInicio>
                                                                                 <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:FechaInicio"/>
                                                                              </ns23:FechaInicio>
                                                                              <ns23:Frecuencia>
                                                                                 <ns7:Tipo>
                                                                                    <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                                                       <ns22:Id>
                                                                                          <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                                                       </ns22:Id>
                                                                                    </xsl:if>
                                                                                    <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                                                       <ns22:Descripcion>
                                                                                          <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                                                       </ns22:Descripcion>
                                                                                    </xsl:if>
                                                                                    <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                                                       <ns22:DescripcionCorta>
                                                                                          <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                                       </ns22:DescripcionCorta>
                                                                                    </xsl:if>
                                                                                    <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                                                       <ns22:estatus>
                                                                                          <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                                                       </ns22:estatus>
                                                                                    </xsl:if>
                                                                                    <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                                                       <ns22:codigoExterno>
                                                                                          <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                                                       </ns22:codigoExterno>
                                                                                    </xsl:if>
                                                                                 </ns7:Tipo>
                                                                              </ns23:Frecuencia>
                                                                           </ns23:revision>
                                                                        </xsl:if>
                                                                     </ns23:spread>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision">
                                                                        <ns23:revision>
                                                                           <ns23:FechaInicio>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:FechaInicio"/>
                                                                           </ns23:FechaInicio>
                                                                           <ns23:Frecuencia>
                                                                              <ns7:Valor>
                                                                                 <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Valor"/>
                                                                              </ns7:Valor>
                                                                           </ns23:Frecuencia>
                                                                        </ns23:revision>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:limite">
                                                                        <ns23:limite>
                                                                           <ns7:maximo>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:limite/ns7:maximo"/>
                                                                           </ns7:maximo>
                                                                           <ns7:minimo>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:limite/ns7:minimo"/>
                                                                           </ns7:minimo>
                                                                        </ns23:limite>
                                                                     </xsl:if>
                                                                  </ns23:variable>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:especial">
                                                                  <ns23:especial>
                                                                     <ns23:valor>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:especial/ns23:valor"/>
                                                                     </ns23:valor>
                                                                  </ns23:especial>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora">
                                                                  <ns23:spreadMora>
                                                                     <ns23:valor>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:valor"/>
                                                                     </ns23:valor>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:codigo">
                                                                        <ns23:codigo>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:codigo"/>
                                                                        </ns23:codigo>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision">
                                                                        <ns23:revision>
                                                                           <ns23:FechaInicio>
                                                                              <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:FechaInicio"/>
                                                                           </ns23:FechaInicio>
                                                                           <ns23:Frecuencia>
                                                                              <ns7:Valor>
                                                                                 <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Valor"/>
                                                                              </ns7:Valor>
                                                                           </ns23:Frecuencia>
                                                                        </ns23:revision>
                                                                     </xsl:if>
                                                                  </ns23:spreadMora>
                                                               </xsl:if>
                                                            </ns23:tasa>
                                                            <ns23:fondo>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                               <xsl:for-each select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:fondo/ns23:Validador">
                                                                  <ns23:Validador>
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
                                                                  </ns23:Validador>
                                                               </xsl:for-each>
                                                            </ns23:fondo>
                                                            <ns23:baseCalculo>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:baseCalculo/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns23:baseCalculo>
                                                            <ns23:principal>
                                                               <ns23:FechaInicio>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:FechaInicio"/>
                                                               </ns23:FechaInicio>
                                                               <ns23:Frecuencia>
                                                                  <ns7:Tipo>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns7:Tipo>
                                                                  <ns7:Valor>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Valor"/>
                                                                  </ns7:Valor>
                                                               </ns23:Frecuencia>
                                                               <ns23:fechaVencimiento>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:principal/ns23:fechaVencimiento"/>
                                                               </ns23:fechaVencimiento>
                                                            </ns23:principal>
                                                            <ns23:interes>
                                                               <ns23:FechaInicio>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:FechaInicio"/>
                                                               </ns23:FechaInicio>
                                                               <ns23:Frecuencia>
                                                                  <ns7:Tipo>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns7:Tipo>
                                                                  <ns7:Valor>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Valor"/>
                                                                  </ns7:Valor>
                                                               </ns23:Frecuencia>
                                                            </ns23:interes>
                                                            <ns23:periodoGracia>
                                                               <ns23:FechaInicio>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:FechaInicio"/>
                                                               </ns23:FechaInicio>
                                                               <ns23:Frecuencia>
                                                                  <ns7:Tipo>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns7:Tipo>
                                                                  <ns7:Valor>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Valor"/>
                                                                  </ns7:Valor>
                                                               </ns23:Frecuencia>
                                                            </ns23:periodoGracia>
                                                            <ns23:tratamientoDiasFeriados>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tratamientoDiasFeriados"/>
                                                            </ns23:tratamientoDiasFeriados>
                                                            <ns23:tipoCalendario>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns23:tipoCalendario>
                                                            <ns23:moverEntreMeses>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:moverEntreMeses"/>
                                                            </ns23:moverEntreMeses>
                                                            <xsl:for-each select="ns14:ContratoDesembolso/ns23:condicionesFinancieras/ns23:calendarioComplejo">
                                                               <ns23:calendarioComplejo>
                                                                  <ns23:TipoPlan>
                                                                     <xsl:if test="ns23:TipoPlan/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns23:TipoPlan/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoPlan/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns23:TipoPlan/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoPlan/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns23:TipoPlan/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoPlan/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns23:TipoPlan/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:TipoPlan/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns23:TipoPlan/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns23:TipoPlan>
                                                                  <ns23:Frecuencia>
                                                                     <ns23:FechaInicio>
                                                                        <xsl:value-of select="ns23:Frecuencia/ns23:FechaInicio"/>
                                                                     </ns23:FechaInicio>
                                                                     <ns23:Frecuencia>
                                                                        <ns7:Tipo>
                                                                           <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                                              <ns22:Id>
                                                                                 <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                                              </ns22:Id>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                                              <ns22:Descripcion>
                                                                                 <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                                              </ns22:Descripcion>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                                              <ns22:DescripcionCorta>
                                                                                 <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                              </ns22:DescripcionCorta>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                                              <ns22:estatus>
                                                                                 <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                                              </ns22:estatus>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                                              <ns22:codigoExterno>
                                                                                 <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                                              </ns22:codigoExterno>
                                                                           </xsl:if>
                                                                        </ns7:Tipo>
                                                                        <ns7:Valor>
                                                                           <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Valor"/>
                                                                        </ns7:Valor>
                                                                     </ns23:Frecuencia>
                                                                  </ns23:Frecuencia>
                                                                  <ns23:Monto>
                                                                     <ns7:tipo>
                                                                        <xsl:if test="ns23:Monto/ns7:tipo/ns22:Id">
                                                                           <ns22:Id>
                                                                              <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Id"/>
                                                                           </ns22:Id>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns23:Monto/ns7:tipo/ns22:Descripcion">
                                                                           <ns22:Descripcion>
                                                                              <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Descripcion"/>
                                                                           </ns22:Descripcion>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns23:Monto/ns7:tipo/ns22:DescripcionCorta">
                                                                           <ns22:DescripcionCorta>
                                                                              <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:DescripcionCorta"/>
                                                                           </ns22:DescripcionCorta>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns23:Monto/ns7:tipo/ns22:estatus">
                                                                           <ns22:estatus>
                                                                              <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:estatus"/>
                                                                           </ns22:estatus>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns23:Monto/ns7:tipo/ns22:codigoExterno">
                                                                           <ns22:codigoExterno>
                                                                              <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:codigoExterno"/>
                                                                           </ns22:codigoExterno>
                                                                        </xsl:if>
                                                                     </ns7:tipo>
                                                                     <xsl:if test="ns23:Monto/ns7:importe">
                                                                        <ns7:importe>
                                                                           <xsl:value-of select="ns23:Monto/ns7:importe"/>
                                                                        </ns7:importe>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda">
                                                                        <ns7:moneda>
                                                                           <xsl:if test="ns23:Monto/ns7:moneda/ns22:Id">
                                                                              <ns22:Id>
                                                                                 <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Id"/>
                                                                              </ns22:Id>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Monto/ns7:moneda/ns22:Descripcion">
                                                                              <ns22:Descripcion>
                                                                                 <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Descripcion"/>
                                                                              </ns22:Descripcion>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Monto/ns7:moneda/ns22:DescripcionCorta">
                                                                              <ns22:DescripcionCorta>
                                                                                 <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                                              </ns22:DescripcionCorta>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Monto/ns7:moneda/ns22:estatus">
                                                                              <ns22:estatus>
                                                                                 <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:estatus"/>
                                                                              </ns22:estatus>
                                                                           </xsl:if>
                                                                           <xsl:if test="ns23:Monto/ns7:moneda/ns22:codigoExterno">
                                                                              <ns22:codigoExterno>
                                                                                 <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:codigoExterno"/>
                                                                              </ns22:codigoExterno>
                                                                           </xsl:if>
                                                                        </ns7:moneda>
                                                                     </xsl:if>
                                                                  </ns23:Monto>
                                                                  <ns23:NumeroCuotas>
                                                                     <xsl:value-of select="ns23:NumeroCuotas"/>
                                                                  </ns23:NumeroCuotas>
                                                               </ns23:calendarioComplejo>
                                                            </xsl:for-each>
                                                         </ns23:condicionesFinancieras>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:idLinea">
                                                         <ns23:idLinea>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:idLinea"/>
                                                         </ns23:idLinea>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:recursosExternos">
                                                         <ns23:recursosExternos>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:recursosExternos"/>
                                                         </ns23:recursosExternos>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:cuentaCliente">
                                                         <ns23:cuentaCliente>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:cuentaCliente"/>
                                                         </ns23:cuentaCliente>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:usuario">
                                                         <ns23:usuario>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:usuario"/>
                                                         </ns23:usuario>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:fechaDisponibilidadFondos">
                                                         <ns23:fechaDisponibilidadFondos>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:fechaDisponibilidadFondos"/>
                                                         </ns23:fechaDisponibilidadFondos>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:origenTransferenciaCte">
                                                         <ns23:origenTransferenciaCte>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:origenTransferenciaCte"/>
                                                         </ns23:origenTransferenciaCte>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:Programa">
                                                         <ns23:Programa>
                                                            <ns20:LineaFinanciera>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:LineaFinanciera/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns20:LineaFinanciera>
                                                            <ns20:DestinoFinanciamiento>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:DestinoFinanciamiento/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns20:DestinoFinanciamiento>
                                                            <ns20:ModalidadFinanciamiento>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ModalidadFinanciamiento/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns20:ModalidadFinanciamiento>
                                                            <ns20:HerramientaCE>
                                                               <ns20:ActividadEconomica>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns20:ActividadEconomica>
                                                               <ns20:EjeEstrategico>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns20:EjeEstrategico>
                                                               <ns20:AreaFocalizacion>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns20:AreaFocalizacion>
                                                            </ns20:HerramientaCE>
                                                            <ns20:ClasificacionGeneral>
                                                               <ns20:SectorMercado>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns20:SectorMercado>
                                                               <ns20:SectorInstitucional>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns14:ContratoDesembolso/ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns20:SectorInstitucional>
                                                            </ns20:ClasificacionGeneral>
                                                         </ns23:Programa>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:EstimadoDesembolso">
                                                         <ns23:EstimadoDesembolso>
                                                            <ns23:Plazo>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Plazo"/>
                                                            </ns23:Plazo>
                                                            <ns23:Frecuencia>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns23:Frecuencia>
                                                         </ns23:EstimadoDesembolso>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:fechaInicioProceso">
                                                         <ns23:fechaInicioProceso>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:fechaInicioProceso"/>
                                                         </ns23:fechaInicioProceso>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:fechaEstimadaDisponibilidad">
                                                         <ns23:fechaEstimadaDisponibilidad>
                                                            <xsl:value-of select="ns14:ContratoDesembolso/ns23:fechaEstimadaDisponibilidad"/>
                                                         </ns23:fechaEstimadaDisponibilidad>
                                                      </xsl:if>
                                                      <xsl:for-each select="ns14:ContratoDesembolso/ns23:Utilizacion">
                                                         <ns23:Utilizacion>
                                                            <ns10:idFuente>
                                                               <xsl:value-of select="ns10:idFuente"/>
                                                            </ns10:idFuente>
                                                            <ns10:idLineaCredito>
                                                               <xsl:value-of select="ns10:idLineaCredito"/>
                                                            </ns10:idLineaCredito>
                                                            <ns10:idLineaPasiva>
                                                               <xsl:value-of select="ns10:idLineaPasiva"/>
                                                            </ns10:idLineaPasiva>
                                                            <ns10:codigoLineaPasiva>
                                                               <xsl:value-of select="ns10:codigoLineaPasiva"/>
                                                            </ns10:codigoLineaPasiva>
                                                            <ns10:idFacturadorLineaPasiva>
                                                               <xsl:value-of select="ns10:idFacturadorLineaPasiva"/>
                                                            </ns10:idFacturadorLineaPasiva>
                                                            <ns10:idFondo>
                                                               <xsl:value-of select="ns10:idFondo"/>
                                                            </ns10:idFondo>
                                                            <ns10:Descripcion>
                                                               <xsl:value-of select="ns10:Descripcion"/>
                                                            </ns10:Descripcion>
                                                            <ns10:FechaObtenido>
                                                               <xsl:value-of select="ns10:FechaObtenido"/>
                                                            </ns10:FechaObtenido>
                                                            <ns10:MontoAsignado>
                                                               <xsl:value-of select="ns10:MontoAsignado"/>
                                                            </ns10:MontoAsignado>
                                                            <ns10:montoDisponible>
                                                               <xsl:value-of select="ns10:montoDisponible"/>
                                                            </ns10:montoDisponible>
                                                            <xsl:for-each select="ns10:Monto">
                                                               <ns10:Monto>
                                                                  <ns7:tipo>
                                                                     <xsl:if test="ns7:tipo/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns7:tipo/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns7:tipo/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns7:tipo/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns7:tipo/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns7:tipo/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns7:tipo/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns7:tipo/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns7:tipo/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns7:tipo/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns7:tipo>
                                                                  <xsl:if test="ns7:importe">
                                                                     <ns7:importe>
                                                                        <xsl:value-of select="ns7:importe"/>
                                                                     </ns7:importe>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns7:moneda">
                                                                     <ns7:moneda>
                                                                        <xsl:if test="ns7:moneda/ns22:Id">
                                                                           <ns22:Id>
                                                                              <xsl:value-of select="ns7:moneda/ns22:Id"/>
                                                                           </ns22:Id>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns7:moneda/ns22:Descripcion">
                                                                           <ns22:Descripcion>
                                                                              <xsl:value-of select="ns7:moneda/ns22:Descripcion"/>
                                                                           </ns22:Descripcion>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns7:moneda/ns22:DescripcionCorta">
                                                                           <ns22:DescripcionCorta>
                                                                              <xsl:value-of select="ns7:moneda/ns22:DescripcionCorta"/>
                                                                           </ns22:DescripcionCorta>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns7:moneda/ns22:estatus">
                                                                           <ns22:estatus>
                                                                              <xsl:value-of select="ns7:moneda/ns22:estatus"/>
                                                                           </ns22:estatus>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns7:moneda/ns22:codigoExterno">
                                                                           <ns22:codigoExterno>
                                                                              <xsl:value-of select="ns7:moneda/ns22:codigoExterno"/>
                                                                           </ns22:codigoExterno>
                                                                        </xsl:if>
                                                                     </ns7:moneda>
                                                                  </xsl:if>
                                                               </ns10:Monto>
                                                            </xsl:for-each>
                                                            <ns10:NumeroAsignacion>
                                                               <xsl:value-of select="ns10:NumeroAsignacion"/>
                                                            </ns10:NumeroAsignacion>
                                                            <ns10:Comentario>
                                                               <xsl:value-of select="ns10:Comentario"/>
                                                            </ns10:Comentario>
                                                            <ns10:idContrato>
                                                               <xsl:value-of select="ns10:idContrato"/>
                                                            </ns10:idContrato>
                                                            <ns10:FechaRegistro>
                                                               <xsl:value-of select="ns10:FechaRegistro"/>
                                                            </ns10:FechaRegistro>
                                                            <ns10:Estado>
                                                               <xsl:value-of select="ns10:Estado"/>
                                                            </ns10:Estado>
                                                            <ns10:esExterna>
                                                               <xsl:value-of select="ns10:esExterna"/>
                                                            </ns10:esExterna>
                                                         </ns23:Utilizacion>
                                                      </xsl:for-each>
                                                      <xsl:for-each select="ns14:ContratoDesembolso/ns23:Cargo">
                                                         <ns23:Cargo>
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
                                                            <ns23:Monto>
                                                               <ns7:tipo>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns7:tipo>
                                                               <xsl:if test="ns23:Monto/ns7:importe">
                                                                  <ns7:importe>
                                                                     <xsl:value-of select="ns23:Monto/ns7:importe"/>
                                                                  </ns7:importe>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Monto/ns7:moneda">
                                                                  <ns7:moneda>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns7:moneda>
                                                               </xsl:if>
                                                            </ns23:Monto>
                                                            <ns23:FechaRegistro>
                                                               <xsl:value-of select="ns23:FechaRegistro"/>
                                                            </ns23:FechaRegistro>
                                                            <xsl:if test="ns23:Status">
                                                               <ns23:Status>
                                                                  <xsl:value-of select="ns23:Status"/>
                                                               </ns23:Status>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:SoloLectura">
                                                               <ns23:SoloLectura>
                                                                  <xsl:value-of select="ns23:SoloLectura"/>
                                                               </ns23:SoloLectura>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:TCC">
                                                               <ns23:TCC>
                                                                  <ns11:id>
                                                                     <xsl:value-of select="ns23:TCC/ns11:id"/>
                                                                  </ns11:id>
                                                                  <ns11:tipo>
                                                                     <xsl:value-of select="ns23:TCC/ns11:tipo"/>
                                                                  </ns11:tipo>
                                                                  <ns11:estado>
                                                                     <xsl:value-of select="ns23:TCC/ns11:estado"/>
                                                                  </ns11:estado>
                                                                  <ns11:subEstado>
                                                                     <xsl:value-of select="ns23:TCC/ns11:subEstado"/>
                                                                  </ns11:subEstado>
                                                               </ns23:TCC>
                                                            </xsl:if>
                                                         </ns23:Cargo>
                                                      </xsl:for-each>
                                                      <xsl:for-each select="ns14:ContratoDesembolso/ns23:Comision">
                                                         <ns23:Comision>
                                                            <ns21:idComision>
                                                               <xsl:value-of select="ns21:idComision"/>
                                                            </ns21:idComision>
                                                            <ns21:idOperacion>
                                                               <xsl:value-of select="ns21:idOperacion"/>
                                                            </ns21:idOperacion>
                                                            <xsl:if test="ns21:nombre">
                                                               <ns21:nombre>
                                                                  <xsl:value-of select="ns21:nombre"/>
                                                               </ns21:nombre>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:descripcion">
                                                               <ns21:descripcion>
                                                                  <xsl:value-of select="ns21:descripcion"/>
                                                               </ns21:descripcion>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:tipoComision">
                                                               <ns21:tipoComision>
                                                                  <xsl:if test="ns21:tipoComision/ns21:idCatComision">
                                                                     <ns21:idCatComision>
                                                                        <xsl:value-of select="ns21:tipoComision/ns21:idCatComision"/>
                                                                     </ns21:idCatComision>
                                                                  </xsl:if>
                                                                  <ns21:descripcion>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:descripcion"/>
                                                                  </ns21:descripcion>
                                                                  <ns21:descripcionCorta>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:descripcionCorta"/>
                                                                  </ns21:descripcionCorta>
                                                                  <xsl:if test="ns21:tipoComision/ns21:idTipoComision">
                                                                     <ns21:idTipoComision>
                                                                        <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:Id">
                                                                           <ns22:Id>
                                                                              <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:Id"/>
                                                                           </ns22:Id>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:Descripcion">
                                                                           <ns22:Descripcion>
                                                                              <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:Descripcion"/>
                                                                           </ns22:Descripcion>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:DescripcionCorta">
                                                                           <ns22:DescripcionCorta>
                                                                              <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:DescripcionCorta"/>
                                                                           </ns22:DescripcionCorta>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:estatus">
                                                                           <ns22:estatus>
                                                                              <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:estatus"/>
                                                                           </ns22:estatus>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:codigoExterno">
                                                                           <ns22:codigoExterno>
                                                                              <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:codigoExterno"/>
                                                                           </ns22:codigoExterno>
                                                                        </xsl:if>
                                                                     </ns21:idTipoComision>
                                                                  </xsl:if>
                                                                  <ns21:esEditableEnFormalizacion>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:esEditableEnFormalizacion"/>
                                                                  </ns21:esEditableEnFormalizacion>
                                                                  <ns21:requiereValidarCOFI>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:requiereValidarCOFI"/>
                                                                  </ns21:requiereValidarCOFI>
                                                                  <ns21:sePuedeDispensar>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:sePuedeDispensar"/>
                                                                  </ns21:sePuedeDispensar>
                                                                  <ns21:seRegistraEnFlexCube>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:seRegistraEnFlexCube"/>
                                                                  </ns21:seRegistraEnFlexCube>
                                                                  <ns21:esPlantilla>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:esPlantilla"/>
                                                                  </ns21:esPlantilla>
                                                                  <ns21:idComisionPrecarga>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:idComisionPrecarga"/>
                                                                  </ns21:idComisionPrecarga>
                                                                  <ns21:fechaRegistro>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:fechaRegistro"/>
                                                                  </ns21:fechaRegistro>
                                                                  <ns21:estado>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:estado"/>
                                                                  </ns21:estado>
                                                                  <ns21:codigoExterno>
                                                                     <xsl:value-of select="ns21:tipoComision/ns21:codigoExterno"/>
                                                                  </ns21:codigoExterno>
                                                               </ns21:tipoComision>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:moneda">
                                                               <ns21:moneda>
                                                                  <xsl:if test="ns21:moneda/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns21:moneda/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:moneda/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns21:moneda/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:moneda/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns21:moneda/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:moneda/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns21:moneda/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:moneda/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns21:moneda/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns21:moneda>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:montoComision">
                                                               <ns21:montoComision>
                                                                  <xsl:value-of select="ns21:montoComision"/>
                                                               </ns21:montoComision>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:montoBase">
                                                               <ns21:montoBase>
                                                                  <xsl:if test="ns21:montoBase/ns21:idMontoBase">
                                                                     <ns21:idMontoBase>
                                                                        <xsl:value-of select="ns21:montoBase/ns21:idMontoBase"/>
                                                                     </ns21:idMontoBase>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:montoBase/ns21:valorMontoBase">
                                                                     <ns21:valorMontoBase>
                                                                        <xsl:value-of select="ns21:montoBase/ns21:valorMontoBase"/>
                                                                     </ns21:valorMontoBase>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:montoBase/ns21:porcentajeMontoBase">
                                                                     <ns21:porcentajeMontoBase>
                                                                        <xsl:value-of select="ns21:montoBase/ns21:porcentajeMontoBase"/>
                                                                     </ns21:porcentajeMontoBase>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:montoBase/ns21:descripcionMontoBase">
                                                                     <ns21:descripcionMontoBase>
                                                                        <xsl:value-of select="ns21:montoBase/ns21:descripcionMontoBase"/>
                                                                     </ns21:descripcionMontoBase>
                                                                  </xsl:if>
                                                               </ns21:montoBase>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:fechaValor">
                                                               <ns21:fechaValor>
                                                                  <xsl:value-of select="ns21:fechaValor"/>
                                                               </ns21:fechaValor>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:fechaVencimiento">
                                                               <ns21:fechaVencimiento>
                                                                  <xsl:value-of select="ns21:fechaVencimiento"/>
                                                               </ns21:fechaVencimiento>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:fechaInicioCapital">
                                                               <ns21:fechaInicioCapital>
                                                                  <xsl:value-of select="ns21:fechaInicioCapital"/>
                                                               </ns21:fechaInicioCapital>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:fechaInicioComision">
                                                               <ns21:fechaInicioComision>
                                                                  <xsl:value-of select="ns21:fechaInicioComision"/>
                                                               </ns21:fechaInicioComision>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:tipoFrecuencia">
                                                               <ns21:tipoFrecuencia>
                                                                  <xsl:if test="ns21:tipoFrecuencia/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns21:tipoFrecuencia/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:tipoFrecuencia/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns21:tipoFrecuencia/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:tipoFrecuencia/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns21:tipoFrecuencia/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:tipoFrecuencia/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns21:tipoFrecuencia/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:tipoFrecuencia/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns21:tipoFrecuencia/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns21:tipoFrecuencia>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:frecuenciaPago">
                                                               <ns21:frecuenciaPago>
                                                                  <xsl:value-of select="ns21:frecuenciaPago"/>
                                                               </ns21:frecuenciaPago>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:fondo">
                                                               <ns21:fondo>
                                                                  <xsl:value-of select="ns21:fondo"/>
                                                               </ns21:fondo>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:comisionCompartida">
                                                               <ns21:comisionCompartida>
                                                                  <xsl:value-of select="ns21:comisionCompartida"/>
                                                               </ns21:comisionCompartida>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:codigoDesembolso">
                                                               <ns21:codigoDesembolso>
                                                                  <xsl:value-of select="ns21:codigoDesembolso"/>
                                                               </ns21:codigoDesembolso>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:numeroTesoreria">
                                                               <ns21:numeroTesoreria>
                                                                  <xsl:value-of select="ns21:numeroTesoreria"/>
                                                               </ns21:numeroTesoreria>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:codigoContrato">
                                                               <ns21:codigoContrato>
                                                                  <xsl:value-of select="ns21:codigoContrato"/>
                                                               </ns21:codigoContrato>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:momentoCobro">
                                                               <ns21:momentoCobro>
                                                                  <xsl:if test="ns21:momentoCobro/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns21:momentoCobro/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:momentoCobro/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns21:momentoCobro/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:momentoCobro/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns21:momentoCobro/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:momentoCobro/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns21:momentoCobro/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:momentoCobro/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns21:momentoCobro/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns21:momentoCobro>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:tipoTasa">
                                                               <ns21:tipoTasa>
                                                                  <xsl:if test="ns21:tipoTasa/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns21:tipoTasa/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:tipoTasa/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns21:tipoTasa/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:tipoTasa/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns21:tipoTasa/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:tipoTasa/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns21:tipoTasa/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:tipoTasa/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns21:tipoTasa/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns21:tipoTasa>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:baseCalculo">
                                                               <ns21:baseCalculo>
                                                                  <xsl:if test="ns21:baseCalculo/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns21:baseCalculo/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:baseCalculo/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns21:baseCalculo/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:baseCalculo/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns21:baseCalculo/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:baseCalculo/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns21:baseCalculo/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns21:baseCalculo/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns21:baseCalculo/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns21:baseCalculo>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:responsableComision">
                                                               <ns21:responsableComision>
                                                                  <xsl:value-of select="ns21:responsableComision"/>
                                                               </ns21:responsableComision>
                                                            </xsl:if>
                                                            <ns21:estadoTCC>
                                                               <xsl:if test="ns21:estadoTCC/ns27:id">
                                                                  <ns27:id>
                                                                     <xsl:value-of select="ns21:estadoTCC/ns27:id"/>
                                                                  </ns27:id>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:estadoTCC/ns27:descripcion">
                                                                  <ns27:descripcion>
                                                                     <xsl:value-of select="ns21:estadoTCC/ns27:descripcion"/>
                                                                  </ns27:descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:estadoTCC/ns27:descripcionCorta">
                                                                  <ns27:descripcionCorta>
                                                                     <xsl:value-of select="ns21:estadoTCC/ns27:descripcionCorta"/>
                                                                  </ns27:descripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:estadoTCC/ns27:estatus">
                                                                  <ns27:estatus>
                                                                     <xsl:value-of select="ns21:estadoTCC/ns27:estatus"/>
                                                                  </ns27:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:estadoTCC/ns27:codigoExterno">
                                                                  <ns27:codigoExterno>
                                                                     <xsl:value-of select="ns21:estadoTCC/ns27:codigoExterno"/>
                                                                  </ns27:codigoExterno>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:estadoTCC/ns27:esSubEstado">
                                                                  <ns27:esSubEstado>
                                                                     <xsl:value-of select="ns21:estadoTCC/ns27:esSubEstado"/>
                                                                  </ns27:esSubEstado>
                                                               </xsl:if>
                                                            </ns21:estadoTCC>
                                                            <ns21:subEstadoTCC>
                                                               <xsl:if test="ns21:subEstadoTCC/ns27:id">
                                                                  <ns27:id>
                                                                     <xsl:value-of select="ns21:subEstadoTCC/ns27:id"/>
                                                                  </ns27:id>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:subEstadoTCC/ns27:descripcion">
                                                                  <ns27:descripcion>
                                                                     <xsl:value-of select="ns21:subEstadoTCC/ns27:descripcion"/>
                                                                  </ns27:descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:subEstadoTCC/ns27:descripcionCorta">
                                                                  <ns27:descripcionCorta>
                                                                     <xsl:value-of select="ns21:subEstadoTCC/ns27:descripcionCorta"/>
                                                                  </ns27:descripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:subEstadoTCC/ns27:estatus">
                                                                  <ns27:estatus>
                                                                     <xsl:value-of select="ns21:subEstadoTCC/ns27:estatus"/>
                                                                  </ns27:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:subEstadoTCC/ns27:codigoExterno">
                                                                  <ns27:codigoExterno>
                                                                     <xsl:value-of select="ns21:subEstadoTCC/ns27:codigoExterno"/>
                                                                  </ns27:codigoExterno>
                                                               </xsl:if>
                                                               <xsl:if test="ns21:subEstadoTCC/ns27:esSubEstado">
                                                                  <ns27:esSubEstado>
                                                                     <xsl:value-of select="ns21:subEstadoTCC/ns27:esSubEstado"/>
                                                                  </ns27:esSubEstado>
                                                               </xsl:if>
                                                            </ns21:subEstadoTCC>
                                                            <ns21:fechaRegistro>
                                                               <xsl:value-of select="ns21:fechaRegistro"/>
                                                            </ns21:fechaRegistro>
                                                            <ns21:estado>
                                                               <xsl:value-of select="ns21:estado"/>
                                                            </ns21:estado>
                                                            <ns21:comisionEnmendada>
                                                               <xsl:value-of select="ns21:comisionEnmendada"/>
                                                            </ns21:comisionEnmendada>
                                                            <ns21:fechaEnmienda>
                                                               <xsl:value-of select="ns21:fechaEnmienda"/>
                                                            </ns21:fechaEnmienda>
                                                            <xsl:if test="ns21:banSugerida">
                                                               <ns21:banSugerida>
                                                                  <xsl:value-of select="ns21:banSugerida"/>
                                                               </ns21:banSugerida>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:numeroCuentaCliente">
                                                               <ns21:numeroCuentaCliente>
                                                                  <xsl:value-of select="ns21:numeroCuentaCliente"/>
                                                               </ns21:numeroCuentaCliente>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:observaciones">
                                                               <ns21:observaciones>
                                                                  <xsl:value-of select="ns21:observaciones"/>
                                                               </ns21:observaciones>
                                                            </xsl:if>
                                                            <xsl:for-each select="ns21:configAtributo">
                                                               <ns21:configAtributo>
                                                                  <xsl:if test="ns27:id">
                                                                     <ns27:id>
                                                                        <xsl:value-of select="ns27:id"/>
                                                                     </ns27:id>
                                                                  </xsl:if>
                                                                  <ns27:columna>
                                                                     <xsl:value-of select="ns27:columna"/>
                                                                  </ns27:columna>
                                                                  <xsl:if test="ns27:ordenamiento">
                                                                     <ns27:ordenamiento>
                                                                        <xsl:value-of select="ns27:ordenamiento"/>
                                                                     </ns27:ordenamiento>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns27:soloLectura">
                                                                     <ns27:soloLectura>
                                                                        <xsl:value-of select="ns27:soloLectura"/>
                                                                     </ns27:soloLectura>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns27:esObligatorio">
                                                                     <ns27:esObligatorio>
                                                                        <xsl:value-of select="ns27:esObligatorio"/>
                                                                     </ns27:esObligatorio>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns27:etiqueta">
                                                                     <ns27:etiqueta>
                                                                        <xsl:value-of select="ns27:etiqueta"/>
                                                                     </ns27:etiqueta>
                                                                  </xsl:if>
                                                                  <xsl:for-each select="ns27:valor">
                                                                     <ns27:valor>
                                                                        <xsl:value-of select="."/>
                                                                     </ns27:valor>
                                                                  </xsl:for-each>
                                                                  <xsl:if test="ns27:tipoValor">
                                                                     <ns27:tipoValor>
                                                                        <xsl:value-of select="ns27:tipoValor"/>
                                                                     </ns27:tipoValor>
                                                                  </xsl:if>
                                                                  <xsl:for-each select="ns27:catalogo">
                                                                     <ns27:catalogo>
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
                                                                     </ns27:catalogo>
                                                                  </xsl:for-each>
                                                               </ns21:configAtributo>
                                                            </xsl:for-each>
                                                            <xsl:if test="ns21:Accion">
                                                               <ns21:Accion>
                                                                  <xsl:value-of select="ns21:Accion"/>
                                                               </ns21:Accion>
                                                            </xsl:if>
                                                         </ns23:Comision>
                                                      </xsl:for-each>
                                                      <xsl:for-each select="ns14:ContratoDesembolso/ns23:Transferencia">
                                                         <ns23:Transferencia>
                                                            <ns23:idTransferencia>
                                                               <xsl:value-of select="ns23:idTransferencia"/>
                                                            </ns23:idTransferencia>
                                                            <ns23:idDesembolso>
                                                               <xsl:value-of select="ns23:idDesembolso"/>
                                                            </ns23:idDesembolso>
                                                            <xsl:if test="ns23:idFacturador">
                                                               <ns23:idFacturador>
                                                                  <xsl:value-of select="ns23:idFacturador"/>
                                                               </ns23:idFacturador>
                                                            </xsl:if>
                                                            <ns23:Monto>
                                                               <ns7:tipo>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns7:tipo>
                                                               <xsl:if test="ns23:Monto/ns7:importe">
                                                                  <ns7:importe>
                                                                     <xsl:value-of select="ns23:Monto/ns7:importe"/>
                                                                  </ns7:importe>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Monto/ns7:moneda">
                                                                  <ns7:moneda>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns7:moneda>
                                                               </xsl:if>
                                                            </ns23:Monto>
                                                            <ns23:FormaTransferencia>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns23:FormaTransferencia>
                                                            <xsl:if test="ns23:referenciaMensaje">
                                                               <ns23:referenciaMensaje>
                                                                  <xsl:value-of select="ns23:referenciaMensaje"/>
                                                               </ns23:referenciaMensaje>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:esConsolidada">
                                                               <ns23:esConsolidada>
                                                                  <xsl:value-of select="ns23:esConsolidada"/>
                                                               </ns23:esConsolidada>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:esConsolidacionPadre">
                                                               <ns23:esConsolidacionPadre>
                                                                  <xsl:value-of select="ns23:esConsolidacionPadre"/>
                                                               </ns23:esConsolidacionPadre>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:idConsolidacionPadre">
                                                               <ns23:idConsolidacionPadre>
                                                                  <xsl:value-of select="ns23:idConsolidacionPadre"/>
                                                               </ns23:idConsolidacionPadre>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:bhqTransferencia">
                                                               <ns23:bhqTransferencia>
                                                                  <xsl:value-of select="ns23:bhqTransferencia"/>
                                                               </ns23:bhqTransferencia>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:NumeroReserva">
                                                               <ns23:NumeroReserva>
                                                                  <xsl:value-of select="ns23:NumeroReserva"/>
                                                               </ns23:NumeroReserva>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:idBancoTransferencia">
                                                               <ns23:idBancoTransferencia>
                                                                  <xsl:value-of select="ns23:idBancoTransferencia"/>
                                                               </ns23:idBancoTransferencia>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:numeroCuenta">
                                                               <ns23:numeroCuenta>
                                                                  <xsl:value-of select="ns23:numeroCuenta"/>
                                                               </ns23:numeroCuenta>
                                                            </xsl:if>
                                                            <ns23:nombreCuenta>
                                                               <xsl:value-of select="ns23:nombreCuenta"/>
                                                            </ns23:nombreCuenta>
                                                            <xsl:if test="ns23:nombreBanco">
                                                               <ns23:nombreBanco>
                                                                  <xsl:value-of select="ns23:nombreBanco"/>
                                                               </ns23:nombreBanco>
                                                            </xsl:if>
                                                            <ns23:idOperacion>
                                                               <xsl:value-of select="ns23:idOperacion"/>
                                                            </ns23:idOperacion>
                                                            <ns23:tipoMensaje>
                                                               <xsl:value-of select="ns23:tipoMensaje"/>
                                                            </ns23:tipoMensaje>
                                                            <xsl:if test="ns23:estado">
                                                               <ns23:estado>
                                                                  <xsl:value-of select="ns23:estado"/>
                                                               </ns23:estado>
                                                            </xsl:if>
                                                            <ns23:fechaRegistro>
                                                               <xsl:value-of select="ns23:fechaRegistro"/>
                                                            </ns23:fechaRegistro>
                                                            <xsl:if test="ns23:Beneficiario">
                                                               <ns23:Beneficiario>
                                                                  <xsl:if test="ns23:Beneficiario/ns23:tipoOpcion">
                                                                     <ns23:tipoOpcion>
                                                                        <xsl:value-of select="ns23:Beneficiario/ns23:tipoOpcion"/>
                                                                     </ns23:tipoOpcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Beneficiario/ns23:numeroCta">
                                                                     <ns23:numeroCta>
                                                                        <xsl:value-of select="ns23:Beneficiario/ns23:numeroCta"/>
                                                                     </ns23:numeroCta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Beneficiario/ns23:identificador">
                                                                     <ns23:identificador>
                                                                        <xsl:value-of select="ns23:Beneficiario/ns23:identificador"/>
                                                                     </ns23:identificador>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Beneficiario/ns23:beneficiario">
                                                                     <ns23:beneficiario>
                                                                        <xsl:value-of select="ns23:Beneficiario/ns23:beneficiario"/>
                                                                     </ns23:beneficiario>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Beneficiario/ns23:direccion">
                                                                     <ns23:direccion>
                                                                        <xsl:value-of select="ns23:Beneficiario/ns23:direccion"/>
                                                                     </ns23:direccion>
                                                                  </xsl:if>
                                                               </ns23:Beneficiario>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:Banco">
                                                               <ns23:Banco>
                                                                  <xsl:if test="ns23:Banco/ns23:tipoOpcion">
                                                                     <ns23:tipoOpcion>
                                                                        <xsl:value-of select="ns23:Banco/ns23:tipoOpcion"/>
                                                                     </ns23:tipoOpcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Banco/ns23:numeroCta">
                                                                     <ns23:numeroCta>
                                                                        <xsl:value-of select="ns23:Banco/ns23:numeroCta"/>
                                                                     </ns23:numeroCta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Banco/ns23:identificador">
                                                                     <ns23:identificador>
                                                                        <xsl:value-of select="ns23:Banco/ns23:identificador"/>
                                                                     </ns23:identificador>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Banco/ns23:beneficiario">
                                                                     <ns23:beneficiario>
                                                                        <xsl:value-of select="ns23:Banco/ns23:beneficiario"/>
                                                                     </ns23:beneficiario>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Banco/ns23:direccion">
                                                                     <ns23:direccion>
                                                                        <xsl:value-of select="ns23:Banco/ns23:direccion"/>
                                                                     </ns23:direccion>
                                                                  </xsl:if>
                                                               </ns23:Banco>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:Intermediario">
                                                               <ns23:Intermediario>
                                                                  <xsl:if test="ns23:Intermediario/ns23:tipoOpcion">
                                                                     <ns23:tipoOpcion>
                                                                        <xsl:value-of select="ns23:Intermediario/ns23:tipoOpcion"/>
                                                                     </ns23:tipoOpcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Intermediario/ns23:numeroCta">
                                                                     <ns23:numeroCta>
                                                                        <xsl:value-of select="ns23:Intermediario/ns23:numeroCta"/>
                                                                     </ns23:numeroCta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Intermediario/ns23:identificador">
                                                                     <ns23:identificador>
                                                                        <xsl:value-of select="ns23:Intermediario/ns23:identificador"/>
                                                                     </ns23:identificador>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Intermediario/ns23:beneficiario">
                                                                     <ns23:beneficiario>
                                                                        <xsl:value-of select="ns23:Intermediario/ns23:beneficiario"/>
                                                                     </ns23:beneficiario>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Intermediario/ns23:direccion">
                                                                     <ns23:direccion>
                                                                        <xsl:value-of select="ns23:Intermediario/ns23:direccion"/>
                                                                     </ns23:direccion>
                                                                  </xsl:if>
                                                               </ns23:Intermediario>
                                                            </xsl:if>
                                                         </ns23:Transferencia>
                                                      </xsl:for-each>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:TransferenciaFT05">
                                                         <ns23:TransferenciaFT05>
                                                            <ns23:idTransferenciaFT05>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:TransferenciaFT05/ns23:idTransferenciaFT05"/>
                                                            </ns23:idTransferenciaFT05>
                                                            <ns23:idDesembolso>
                                                               <xsl:value-of select="ns14:ContratoDesembolso/ns23:TransferenciaFT05/ns23:idDesembolso"/>
                                                            </ns23:idDesembolso>
                                                            <xsl:if test="ns14:ContratoDesembolso/ns23:TransferenciaFT05/ns23:idFacturador">
                                                               <ns23:idFacturador>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:TransferenciaFT05/ns23:idFacturador"/>
                                                               </ns23:idFacturador>
                                                            </xsl:if>
                                                            <xsl:if test="ns14:ContratoDesembolso/ns23:TransferenciaFT05/ns23:FechaEfectiva">
                                                               <ns23:FechaEfectiva>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:TransferenciaFT05/ns23:FechaEfectiva"/>
                                                               </ns23:FechaEfectiva>
                                                            </xsl:if>
                                                         </ns23:TransferenciaFT05>
                                                      </xsl:if>
                                                      <xsl:for-each select="ns14:ContratoDesembolso/ns23:TransferenciaRecursos">
                                                         <ns23:TransferenciaRecursos>
                                                            <ns23:idTransferencia>
                                                               <xsl:value-of select="ns23:idTransferencia"/>
                                                            </ns23:idTransferencia>
                                                            <ns23:idDesembolso>
                                                               <xsl:value-of select="ns23:idDesembolso"/>
                                                            </ns23:idDesembolso>
                                                            <xsl:if test="ns23:idFacturador">
                                                               <ns23:idFacturador>
                                                                  <xsl:value-of select="ns23:idFacturador"/>
                                                               </ns23:idFacturador>
                                                            </xsl:if>
                                                            <ns23:idLineaPasiva>
                                                               <xsl:value-of select="ns23:idLineaPasiva"/>
                                                            </ns23:idLineaPasiva>
                                                            <ns23:Monto>
                                                               <ns7:tipo>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:Id">
                                                                     <ns22:Id>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Id"/>
                                                                     </ns22:Id>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:Descripcion">
                                                                     <ns22:Descripcion>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Descripcion"/>
                                                                     </ns22:Descripcion>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:DescripcionCorta">
                                                                     <ns22:DescripcionCorta>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:DescripcionCorta"/>
                                                                     </ns22:DescripcionCorta>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:estatus">
                                                                     <ns22:estatus>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:estatus"/>
                                                                     </ns22:estatus>
                                                                  </xsl:if>
                                                                  <xsl:if test="ns23:Monto/ns7:tipo/ns22:codigoExterno">
                                                                     <ns22:codigoExterno>
                                                                        <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:codigoExterno"/>
                                                                     </ns22:codigoExterno>
                                                                  </xsl:if>
                                                               </ns7:tipo>
                                                               <xsl:if test="ns23:Monto/ns7:importe">
                                                                  <ns7:importe>
                                                                     <xsl:value-of select="ns23:Monto/ns7:importe"/>
                                                                  </ns7:importe>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Monto/ns7:moneda">
                                                                  <ns7:moneda>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:Monto/ns7:moneda/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns7:moneda>
                                                               </xsl:if>
                                                            </ns23:Monto>
                                                            <ns23:fecha>
                                                               <xsl:value-of select="ns23:fecha"/>
                                                            </ns23:fecha>
                                                            <ns23:fechaEfectiva>
                                                               <xsl:value-of select="ns23:fechaEfectiva"/>
                                                            </ns23:fechaEfectiva>
                                                            <ns23:FormaTransferencia>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:FormaTransferencia/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns23:FormaTransferencia/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns23:FormaTransferencia>
                                                            <xsl:if test="ns23:idBanco">
                                                               <ns23:idBanco>
                                                                  <xsl:value-of select="ns23:idBanco"/>
                                                               </ns23:idBanco>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:nombreBanco">
                                                               <ns23:nombreBanco>
                                                                  <xsl:value-of select="ns23:nombreBanco"/>
                                                               </ns23:nombreBanco>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:numeroCuenta">
                                                               <ns23:numeroCuenta>
                                                                  <xsl:value-of select="ns23:numeroCuenta"/>
                                                               </ns23:numeroCuenta>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:nombreCuenta">
                                                               <ns23:nombreCuenta>
                                                                  <xsl:value-of select="ns23:nombreCuenta"/>
                                                               </ns23:nombreCuenta>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:estatus">
                                                               <ns23:estatus>
                                                                  <xsl:value-of select="ns23:estatus"/>
                                                               </ns23:estatus>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:fechaRegistro">
                                                               <ns23:fechaRegistro>
                                                                  <xsl:value-of select="ns23:fechaRegistro"/>
                                                               </ns23:fechaRegistro>
                                                            </xsl:if>
                                                         </ns23:TransferenciaRecursos>
                                                      </xsl:for-each>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE">
                                                         <ns23:HerramientaCE>
                                                            <ns20:ActividadEconomica>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns20:ActividadEconomica>
                                                            <ns20:EjeEstrategico>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns20:EjeEstrategico>
                                                            <ns20:AreaFocalizacion>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns14:ContratoDesembolso/ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns20:AreaFocalizacion>
                                                         </ns23:HerramientaCE>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:ContratoDesembolso/ns23:modalidadFinan">
                                                         <ns23:modalidadFinan>
                                                            <xsl:if test="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:Id">
                                                               <ns22:Id>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:Id"/>
                                                               </ns22:Id>
                                                            </xsl:if>
                                                            <xsl:if test="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:Descripcion">
                                                               <ns22:Descripcion>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:Descripcion"/>
                                                               </ns22:Descripcion>
                                                            </xsl:if>
                                                            <xsl:if test="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:DescripcionCorta">
                                                               <ns22:DescripcionCorta>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:DescripcionCorta"/>
                                                               </ns22:DescripcionCorta>
                                                            </xsl:if>
                                                            <xsl:if test="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:estatus">
                                                               <ns22:estatus>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:estatus"/>
                                                               </ns22:estatus>
                                                            </xsl:if>
                                                            <xsl:if test="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:codigoExterno">
                                                               <ns22:codigoExterno>
                                                                  <xsl:value-of select="ns14:ContratoDesembolso/ns23:modalidadFinan/ns22:codigoExterno"/>
                                                               </ns22:codigoExterno>
                                                            </xsl:if>
                                                         </ns23:modalidadFinan>
                                                      </xsl:if>
                                                   </ns14:ContratoDesembolso>
                                                </xsl:if>
                                                <ns14:EstadoTCC>
                                                   <xsl:if test="ns14:EstadoTCC/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns14:EstadoTCC/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns14:EstadoTCC/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns14:EstadoTCC/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns14:EstadoTCC/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns14:EstadoTCC/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns14:EstadoTCC/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns14:EstadoTCC/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns14:EstadoTCC/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns14:EstadoTCC/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns14:EstadoTCC>
                                                <xsl:if test="ns14:EventoCondicion">
                                                   <ns14:EventoCondicion>
                                                      <xsl:if test="ns14:EventoCondicion/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns14:EventoCondicion/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:EventoCondicion/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns14:EventoCondicion/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:EventoCondicion/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns14:EventoCondicion/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:EventoCondicion/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns14:EventoCondicion/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:EventoCondicion/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns14:EventoCondicion/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns14:EventoCondicion>
                                                </xsl:if>
                                                <xsl:if test="ns14:agrupador">
                                                   <ns14:agrupador>
                                                      <xsl:value-of select="ns14:agrupador"/>
                                                   </ns14:agrupador>
                                                </xsl:if>
                                                <ns14:estatus>
                                                   <xsl:value-of select="ns14:estatus"/>
                                                </ns14:estatus>
                                                <xsl:if test="ns14:subEstadoTCC">
                                                   <ns14:subEstadoTCC>
                                                      <xsl:if test="ns14:subEstadoTCC/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns14:subEstadoTCC/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:subEstadoTCC/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns14:subEstadoTCC/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:subEstadoTCC/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns14:subEstadoTCC/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:subEstadoTCC/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns14:subEstadoTCC/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns14:subEstadoTCC/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns14:subEstadoTCC/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns14:subEstadoTCC>
                                                </xsl:if>
                                                <ns14:enProceso>
                                                   <xsl:value-of select="ns14:enProceso"/>
                                                </ns14:enProceso>
                                             </ns14:Cumplimientos>
                                          </xsl:for-each>
                                          <xsl:if test="ns14:tipoDesembolso">
                                             <ns14:tipoDesembolso>
                                                <xsl:if test="ns14:tipoDesembolso/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns14:tipoDesembolso/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoDesembolso/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns14:tipoDesembolso/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoDesembolso/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns14:tipoDesembolso/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoDesembolso/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns14:tipoDesembolso/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns14:tipoDesembolso/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns14:tipoDesembolso/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns14:tipoDesembolso>
                                          </xsl:if>
                                       </ns10:Condicion>
                                    </xsl:for-each>
                                    <xsl:for-each select="ns10:Termino">
                                       <ns10:Termino>
                                          <ns3:idTermino>
                                             <xsl:value-of select="ns3:idTermino"/>
                                          </ns3:idTermino>
                                          <ns3:operacion>
                                             <xsl:value-of select="ns3:operacion"/>
                                          </ns3:operacion>
                                          <ns3:nombre>
                                             <xsl:value-of select="ns3:nombre"/>
                                          </ns3:nombre>
                                          <ns3:descripcion>
                                             <xsl:value-of select="ns3:descripcion"/>
                                          </ns3:descripcion>
                                          <ns3:tipoTermino>
                                             <xsl:if test="ns3:tipoTermino/ns3:idCatTermino">
                                                <ns3:idCatTermino>
                                                   <xsl:value-of select="ns3:tipoTermino/ns3:idCatTermino"/>
                                                </ns3:idCatTermino>
                                             </xsl:if>
                                             <ns3:descripcion>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:descripcion"/>
                                             </ns3:descripcion>
                                             <ns3:descripcionCorta>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:descripcionCorta"/>
                                             </ns3:descripcionCorta>
                                             <xsl:if test="ns3:tipoTermino/ns3:idTipoTermino">
                                                <ns3:idTipoTermino>
                                                   <xsl:value-of select="ns3:tipoTermino/ns3:idTipoTermino"/>
                                                </ns3:idTipoTermino>
                                             </xsl:if>
                                             <ns3:esEditableEnFormalizacion>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:esEditableEnFormalizacion"/>
                                             </ns3:esEditableEnFormalizacion>
                                             <ns3:requiereValidarCOFI>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:requiereValidarCOFI"/>
                                             </ns3:requiereValidarCOFI>
                                             <ns3:sePuedeDispensar>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:sePuedeDispensar"/>
                                             </ns3:sePuedeDispensar>
                                             <ns3:esPlantilla>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:esPlantilla"/>
                                             </ns3:esPlantilla>
                                             <ns3:requiereOGC>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:requiereOGC"/>
                                             </ns3:requiereOGC>
                                             <ns3:idTerminoPrecarga>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:idTerminoPrecarga"/>
                                             </ns3:idTerminoPrecarga>
                                             <ns3:fechaRegistro>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:fechaRegistro"/>
                                             </ns3:fechaRegistro>
                                             <ns3:estado>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:estado"/>
                                             </ns3:estado>
                                             <ns3:codigoExterno>
                                                <xsl:value-of select="ns3:tipoTermino/ns3:codigoExterno"/>
                                             </ns3:codigoExterno>
                                          </ns3:tipoTermino>
                                          <ns3:tipoFechaInicio>
                                             <xsl:if test="ns3:tipoFechaInicio/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns3:tipoFechaInicio/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFechaInicio/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns3:tipoFechaInicio/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFechaInicio/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns3:tipoFechaInicio/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFechaInicio/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns3:tipoFechaInicio/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFechaInicio/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns3:tipoFechaInicio/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns3:tipoFechaInicio>
                                          <ns3:fechaInicio>
                                             <xsl:value-of select="ns3:fechaInicio"/>
                                          </ns3:fechaInicio>
                                          <ns3:plazo>
                                             <xsl:value-of select="ns3:plazo"/>
                                          </ns3:plazo>
                                          <ns3:frecuenciaPlazo>
                                             <xsl:if test="ns3:frecuenciaPlazo/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns3:frecuenciaPlazo/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns3:frecuenciaPlazo/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns3:frecuenciaPlazo/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns3:frecuenciaPlazo/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns3:frecuenciaPlazo/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns3:frecuenciaPlazo/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns3:frecuenciaPlazo/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns3:frecuenciaPlazo/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns3:frecuenciaPlazo/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns3:frecuenciaPlazo>
                                          <ns3:fechaVencimiento>
                                             <xsl:value-of select="ns3:fechaVencimiento"/>
                                          </ns3:fechaVencimiento>
                                          <ns3:moneda>
                                             <xsl:if test="ns3:moneda/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns3:moneda/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns3:moneda/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns3:moneda/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns3:moneda/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns3:moneda/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns3:moneda/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns3:moneda/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns3:moneda/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns3:moneda/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns3:moneda>
                                          <ns3:monto>
                                             <xsl:value-of select="ns3:monto"/>
                                          </ns3:monto>
                                          <ns3:tasa>
                                             <xsl:value-of select="ns3:tasa"/>
                                          </ns3:tasa>
                                          <ns3:tipoTasa>
                                             <xsl:if test="ns3:tipoTasa/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns3:tipoTasa/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoTasa/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns3:tipoTasa/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoTasa/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns3:tipoTasa/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoTasa/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns3:tipoTasa/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoTasa/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns3:tipoTasa/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns3:tipoTasa>
                                          <ns3:fecha>
                                             <xsl:value-of select="ns3:fecha"/>
                                          </ns3:fecha>
                                          <ns3:frecuenciaRevision>
                                             <xsl:value-of select="ns3:frecuenciaRevision"/>
                                          </ns3:frecuenciaRevision>
                                          <ns3:tipoFrecuenciaRevision>
                                             <xsl:if test="ns3:tipoFrecuenciaRevision/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaRevision/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaRevision/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaRevision/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaRevision/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaRevision/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaRevision/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaRevision/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaRevision/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaRevision/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns3:tipoFrecuenciaRevision>
                                          <ns3:fechaInicioRevision>
                                             <xsl:value-of select="ns3:fechaInicioRevision"/>
                                          </ns3:fechaInicioRevision>
                                          <ns3:frecuenciaPagoInteres>
                                             <xsl:value-of select="ns3:frecuenciaPagoInteres"/>
                                          </ns3:frecuenciaPagoInteres>
                                          <ns3:tipoFrecuenciaPagoInteres>
                                             <xsl:if test="ns3:tipoFrecuenciaPagoInteres/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaPagoInteres/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaPagoInteres/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaPagoInteres/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaPagoInteres/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaPagoInteres/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaPagoInteres/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaPagoInteres/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaPagoInteres/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaPagoInteres/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns3:tipoFrecuenciaPagoInteres>
                                          <ns3:fechaInicioPagoInteres>
                                             <xsl:value-of select="ns3:fechaInicioPagoInteres"/>
                                          </ns3:fechaInicioPagoInteres>
                                          <ns3:frecuenciaAmortizacion>
                                             <xsl:value-of select="ns3:frecuenciaAmortizacion"/>
                                          </ns3:frecuenciaAmortizacion>
                                          <ns3:tipoFrecuenciaAmortizacion>
                                             <xsl:if test="ns3:tipoFrecuenciaAmortizacion/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaAmortizacion/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaAmortizacion/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaAmortizacion/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaAmortizacion/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaAmortizacion/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaAmortizacion/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaAmortizacion/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns3:tipoFrecuenciaAmortizacion/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns3:tipoFrecuenciaAmortizacion/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns3:tipoFrecuenciaAmortizacion>
                                          <ns3:mora>
                                             <xsl:value-of select="ns3:mora"/>
                                          </ns3:mora>
                                          <ns3:porcentajeCobertura>
                                             <xsl:value-of select="ns3:porcentajeCobertura"/>
                                          </ns3:porcentajeCobertura>
                                          <ns3:seAplicanRecursosConcesion>
                                             <xsl:value-of select="ns3:seAplicanRecursosConcesion"/>
                                          </ns3:seAplicanRecursosConcesion>
                                          <ns3:seAplicanRecursosExternos>
                                             <xsl:value-of select="ns3:seAplicanRecursosExternos"/>
                                          </ns3:seAplicanRecursosExternos>
                                          <ns3:tipoContraparte>
                                             <xsl:value-of select="ns3:tipoContraparte"/>
                                          </ns3:tipoContraparte>
                                          <ns3:montoMinimoDesembolso>
                                             <xsl:value-of select="ns3:montoMinimoDesembolso"/>
                                          </ns3:montoMinimoDesembolso>
                                          <ns3:montoMaximoDesembolso>
                                             <xsl:value-of select="ns3:montoMaximoDesembolso"/>
                                          </ns3:montoMaximoDesembolso>
                                          <ns3:tasaMinimaDesembolso>
                                             <xsl:value-of select="ns3:tasaMinimaDesembolso"/>
                                          </ns3:tasaMinimaDesembolso>
                                          <ns3:tasaMaximaDesembolso>
                                             <xsl:value-of select="ns3:tasaMaximaDesembolso"/>
                                          </ns3:tasaMaximaDesembolso>
                                          <ns3:estadoTCC>
                                             <xsl:if test="ns3:estadoTCC/ns27:id">
                                                <ns27:id>
                                                   <xsl:value-of select="ns3:estadoTCC/ns27:id"/>
                                                </ns27:id>
                                             </xsl:if>
                                             <xsl:if test="ns3:estadoTCC/ns27:descripcion">
                                                <ns27:descripcion>
                                                   <xsl:value-of select="ns3:estadoTCC/ns27:descripcion"/>
                                                </ns27:descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns3:estadoTCC/ns27:descripcionCorta">
                                                <ns27:descripcionCorta>
                                                   <xsl:value-of select="ns3:estadoTCC/ns27:descripcionCorta"/>
                                                </ns27:descripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns3:estadoTCC/ns27:estatus">
                                                <ns27:estatus>
                                                   <xsl:value-of select="ns3:estadoTCC/ns27:estatus"/>
                                                </ns27:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns3:estadoTCC/ns27:codigoExterno">
                                                <ns27:codigoExterno>
                                                   <xsl:value-of select="ns3:estadoTCC/ns27:codigoExterno"/>
                                                </ns27:codigoExterno>
                                             </xsl:if>
                                             <xsl:if test="ns3:estadoTCC/ns27:esSubEstado">
                                                <ns27:esSubEstado>
                                                   <xsl:value-of select="ns3:estadoTCC/ns27:esSubEstado"/>
                                                </ns27:esSubEstado>
                                             </xsl:if>
                                          </ns3:estadoTCC>
                                          <ns3:subEstadoTCC>
                                             <xsl:if test="ns3:subEstadoTCC/ns27:id">
                                                <ns27:id>
                                                   <xsl:value-of select="ns3:subEstadoTCC/ns27:id"/>
                                                </ns27:id>
                                             </xsl:if>
                                             <xsl:if test="ns3:subEstadoTCC/ns27:descripcion">
                                                <ns27:descripcion>
                                                   <xsl:value-of select="ns3:subEstadoTCC/ns27:descripcion"/>
                                                </ns27:descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns3:subEstadoTCC/ns27:descripcionCorta">
                                                <ns27:descripcionCorta>
                                                   <xsl:value-of select="ns3:subEstadoTCC/ns27:descripcionCorta"/>
                                                </ns27:descripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns3:subEstadoTCC/ns27:estatus">
                                                <ns27:estatus>
                                                   <xsl:value-of select="ns3:subEstadoTCC/ns27:estatus"/>
                                                </ns27:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns3:subEstadoTCC/ns27:codigoExterno">
                                                <ns27:codigoExterno>
                                                   <xsl:value-of select="ns3:subEstadoTCC/ns27:codigoExterno"/>
                                                </ns27:codigoExterno>
                                             </xsl:if>
                                             <xsl:if test="ns3:subEstadoTCC/ns27:esSubEstado">
                                                <ns27:esSubEstado>
                                                   <xsl:value-of select="ns3:subEstadoTCC/ns27:esSubEstado"/>
                                                </ns27:esSubEstado>
                                             </xsl:if>
                                          </ns3:subEstadoTCC>
                                          <ns3:fechaRegistro>
                                             <xsl:value-of select="ns3:fechaRegistro"/>
                                          </ns3:fechaRegistro>
                                          <ns3:estado>
                                             <xsl:value-of select="ns3:estado"/>
                                          </ns3:estado>
                                          <ns3:terminoEnmendado>
                                             <xsl:value-of select="ns3:terminoEnmendado"/>
                                          </ns3:terminoEnmendado>
                                          <ns3:fechaEnmienda>
                                             <xsl:value-of select="ns3:fechaEnmienda"/>
                                          </ns3:fechaEnmienda>
                                          <ns3:porcentajeModificacion>
                                             <xsl:value-of select="ns3:porcentajeModificacion"/>
                                          </ns3:porcentajeModificacion>
                                          <ns3:idTcaTipoAvance>
                                             <xsl:value-of select="ns3:idTcaTipoAvance"/>
                                          </ns3:idTcaTipoAvance>
                                          <ns3:idTcaTipoPorcentaje>
                                             <xsl:value-of select="ns3:idTcaTipoPorcentaje"/>
                                          </ns3:idTcaTipoPorcentaje>
                                          <ns3:porcentaje>
                                             <xsl:value-of select="ns3:porcentaje"/>
                                          </ns3:porcentaje>
                                          <ns3:porcentajeInicial>
                                             <xsl:value-of select="ns3:porcentajeInicial"/>
                                          </ns3:porcentajeInicial>
                                          <ns3:porcentajeFinal>
                                             <xsl:value-of select="ns3:porcentajeFinal"/>
                                          </ns3:porcentajeFinal>
                                          <xsl:if test="ns3:requiereFondoPresupuestario">
                                             <ns3:requiereFondoPresupuestario>
                                                <xsl:value-of select="ns3:requiereFondoPresupuestario"/>
                                             </ns3:requiereFondoPresupuestario>
                                          </xsl:if>
                                          <xsl:for-each select="ns3:configAtributo">
                                             <ns3:configAtributo>
                                                <xsl:if test="ns27:id">
                                                   <ns27:id>
                                                      <xsl:value-of select="ns27:id"/>
                                                   </ns27:id>
                                                </xsl:if>
                                                <ns27:columna>
                                                   <xsl:value-of select="ns27:columna"/>
                                                </ns27:columna>
                                                <xsl:if test="ns27:ordenamiento">
                                                   <ns27:ordenamiento>
                                                      <xsl:value-of select="ns27:ordenamiento"/>
                                                   </ns27:ordenamiento>
                                                </xsl:if>
                                                <xsl:if test="ns27:soloLectura">
                                                   <ns27:soloLectura>
                                                      <xsl:value-of select="ns27:soloLectura"/>
                                                   </ns27:soloLectura>
                                                </xsl:if>
                                                <xsl:if test="ns27:esObligatorio">
                                                   <ns27:esObligatorio>
                                                      <xsl:value-of select="ns27:esObligatorio"/>
                                                   </ns27:esObligatorio>
                                                </xsl:if>
                                                <xsl:if test="ns27:etiqueta">
                                                   <ns27:etiqueta>
                                                      <xsl:value-of select="ns27:etiqueta"/>
                                                   </ns27:etiqueta>
                                                </xsl:if>
                                                <xsl:for-each select="ns27:valor">
                                                   <ns27:valor>
                                                      <xsl:value-of select="."/>
                                                   </ns27:valor>
                                                </xsl:for-each>
                                                <xsl:if test="ns27:tipoValor">
                                                   <ns27:tipoValor>
                                                      <xsl:value-of select="ns27:tipoValor"/>
                                                   </ns27:tipoValor>
                                                </xsl:if>
                                                <xsl:for-each select="ns27:catalogo">
                                                   <ns27:catalogo>
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
                                                   </ns27:catalogo>
                                                </xsl:for-each>
                                             </ns3:configAtributo>
                                          </xsl:for-each>
                                          <xsl:if test="ns3:Accion">
                                             <ns3:Accion>
                                                <xsl:value-of select="ns3:Accion"/>
                                             </ns3:Accion>
                                          </xsl:if>
                                          <xsl:for-each select="ns3:Contraparte">
                                             <ns3:Contraparte>
                                                <xsl:if test="ns27:id">
                                                   <ns27:id>
                                                      <xsl:value-of select="ns27:id"/>
                                                   </ns27:id>
                                                </xsl:if>
                                                <xsl:if test="ns27:descripcion">
                                                   <ns27:descripcion>
                                                      <xsl:value-of select="ns27:descripcion"/>
                                                   </ns27:descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns27:estatus">
                                                   <ns27:estatus>
                                                      <xsl:value-of select="ns27:estatus"/>
                                                   </ns27:estatus>
                                                </xsl:if>
                                             </ns3:Contraparte>
                                          </xsl:for-each>
                                       </ns10:Termino>
                                    </xsl:for-each>
                                    <xsl:for-each select="ns10:Comision">
                                       <ns10:Comision>
                                          <ns21:idComision>
                                             <xsl:value-of select="ns21:idComision"/>
                                          </ns21:idComision>
                                          <ns21:idOperacion>
                                             <xsl:value-of select="ns21:idOperacion"/>
                                          </ns21:idOperacion>
                                          <xsl:if test="ns21:nombre">
                                             <ns21:nombre>
                                                <xsl:value-of select="ns21:nombre"/>
                                             </ns21:nombre>
                                          </xsl:if>
                                          <xsl:if test="ns21:descripcion">
                                             <ns21:descripcion>
                                                <xsl:value-of select="ns21:descripcion"/>
                                             </ns21:descripcion>
                                          </xsl:if>
                                          <xsl:if test="ns21:tipoComision">
                                             <ns21:tipoComision>
                                                <xsl:if test="ns21:tipoComision/ns21:idCatComision">
                                                   <ns21:idCatComision>
                                                      <xsl:value-of select="ns21:tipoComision/ns21:idCatComision"/>
                                                   </ns21:idCatComision>
                                                </xsl:if>
                                                <ns21:descripcion>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:descripcion"/>
                                                </ns21:descripcion>
                                                <ns21:descripcionCorta>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:descripcionCorta"/>
                                                </ns21:descripcionCorta>
                                                <xsl:if test="ns21:tipoComision/ns21:idTipoComision">
                                                   <ns21:idTipoComision>
                                                      <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns21:idTipoComision>
                                                </xsl:if>
                                                <ns21:esEditableEnFormalizacion>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:esEditableEnFormalizacion"/>
                                                </ns21:esEditableEnFormalizacion>
                                                <ns21:requiereValidarCOFI>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:requiereValidarCOFI"/>
                                                </ns21:requiereValidarCOFI>
                                                <ns21:sePuedeDispensar>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:sePuedeDispensar"/>
                                                </ns21:sePuedeDispensar>
                                                <ns21:seRegistraEnFlexCube>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:seRegistraEnFlexCube"/>
                                                </ns21:seRegistraEnFlexCube>
                                                <ns21:esPlantilla>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:esPlantilla"/>
                                                </ns21:esPlantilla>
                                                <ns21:idComisionPrecarga>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:idComisionPrecarga"/>
                                                </ns21:idComisionPrecarga>
                                                <ns21:fechaRegistro>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:fechaRegistro"/>
                                                </ns21:fechaRegistro>
                                                <ns21:estado>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:estado"/>
                                                </ns21:estado>
                                                <ns21:codigoExterno>
                                                   <xsl:value-of select="ns21:tipoComision/ns21:codigoExterno"/>
                                                </ns21:codigoExterno>
                                             </ns21:tipoComision>
                                          </xsl:if>
                                          <xsl:if test="ns21:moneda">
                                             <ns21:moneda>
                                                <xsl:if test="ns21:moneda/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns21:moneda/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns21:moneda/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns21:moneda/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns21:moneda/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns21:moneda/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns21:moneda/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns21:moneda/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns21:moneda/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns21:moneda/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns21:moneda>
                                          </xsl:if>
                                          <xsl:if test="ns21:montoComision">
                                             <ns21:montoComision>
                                                <xsl:value-of select="ns21:montoComision"/>
                                             </ns21:montoComision>
                                          </xsl:if>
                                          <xsl:if test="ns21:montoBase">
                                             <ns21:montoBase>
                                                <xsl:if test="ns21:montoBase/ns21:idMontoBase">
                                                   <ns21:idMontoBase>
                                                      <xsl:value-of select="ns21:montoBase/ns21:idMontoBase"/>
                                                   </ns21:idMontoBase>
                                                </xsl:if>
                                                <xsl:if test="ns21:montoBase/ns21:valorMontoBase">
                                                   <ns21:valorMontoBase>
                                                      <xsl:value-of select="ns21:montoBase/ns21:valorMontoBase"/>
                                                   </ns21:valorMontoBase>
                                                </xsl:if>
                                                <xsl:if test="ns21:montoBase/ns21:porcentajeMontoBase">
                                                   <ns21:porcentajeMontoBase>
                                                      <xsl:value-of select="ns21:montoBase/ns21:porcentajeMontoBase"/>
                                                   </ns21:porcentajeMontoBase>
                                                </xsl:if>
                                                <xsl:if test="ns21:montoBase/ns21:descripcionMontoBase">
                                                   <ns21:descripcionMontoBase>
                                                      <xsl:value-of select="ns21:montoBase/ns21:descripcionMontoBase"/>
                                                   </ns21:descripcionMontoBase>
                                                </xsl:if>
                                             </ns21:montoBase>
                                          </xsl:if>
                                          <xsl:if test="ns21:fechaValor">
                                             <ns21:fechaValor>
                                                <xsl:value-of select="ns21:fechaValor"/>
                                             </ns21:fechaValor>
                                          </xsl:if>
                                          <xsl:if test="ns21:fechaVencimiento">
                                             <ns21:fechaVencimiento>
                                                <xsl:value-of select="ns21:fechaVencimiento"/>
                                             </ns21:fechaVencimiento>
                                          </xsl:if>
                                          <xsl:if test="ns21:fechaInicioCapital">
                                             <ns21:fechaInicioCapital>
                                                <xsl:value-of select="ns21:fechaInicioCapital"/>
                                             </ns21:fechaInicioCapital>
                                          </xsl:if>
                                          <xsl:if test="ns21:fechaInicioComision">
                                             <ns21:fechaInicioComision>
                                                <xsl:value-of select="ns21:fechaInicioComision"/>
                                             </ns21:fechaInicioComision>
                                          </xsl:if>
                                          <xsl:if test="ns21:tipoFrecuencia">
                                             <ns21:tipoFrecuencia>
                                                <xsl:if test="ns21:tipoFrecuencia/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns21:tipoFrecuencia/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoFrecuencia/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns21:tipoFrecuencia/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoFrecuencia/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns21:tipoFrecuencia/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoFrecuencia/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns21:tipoFrecuencia/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoFrecuencia/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns21:tipoFrecuencia/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns21:tipoFrecuencia>
                                          </xsl:if>
                                          <xsl:if test="ns21:frecuenciaPago">
                                             <ns21:frecuenciaPago>
                                                <xsl:value-of select="ns21:frecuenciaPago"/>
                                             </ns21:frecuenciaPago>
                                          </xsl:if>
                                          <xsl:if test="ns21:fondo">
                                             <ns21:fondo>
                                                <xsl:value-of select="ns21:fondo"/>
                                             </ns21:fondo>
                                          </xsl:if>
                                          <xsl:if test="ns21:comisionCompartida">
                                             <ns21:comisionCompartida>
                                                <xsl:value-of select="ns21:comisionCompartida"/>
                                             </ns21:comisionCompartida>
                                          </xsl:if>
                                          <xsl:if test="ns21:codigoDesembolso">
                                             <ns21:codigoDesembolso>
                                                <xsl:value-of select="ns21:codigoDesembolso"/>
                                             </ns21:codigoDesembolso>
                                          </xsl:if>
                                          <xsl:if test="ns21:numeroTesoreria">
                                             <ns21:numeroTesoreria>
                                                <xsl:value-of select="ns21:numeroTesoreria"/>
                                             </ns21:numeroTesoreria>
                                          </xsl:if>
                                          <xsl:if test="ns21:codigoContrato">
                                             <ns21:codigoContrato>
                                                <xsl:value-of select="ns21:codigoContrato"/>
                                             </ns21:codigoContrato>
                                          </xsl:if>
                                          <xsl:if test="ns21:momentoCobro">
                                             <ns21:momentoCobro>
                                                <xsl:if test="ns21:momentoCobro/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns21:momentoCobro/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns21:momentoCobro/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns21:momentoCobro/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns21:momentoCobro/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns21:momentoCobro/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns21:momentoCobro/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns21:momentoCobro/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns21:momentoCobro/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns21:momentoCobro/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns21:momentoCobro>
                                          </xsl:if>
                                          <xsl:if test="ns21:tipoTasa">
                                             <ns21:tipoTasa>
                                                <xsl:if test="ns21:tipoTasa/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns21:tipoTasa/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoTasa/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns21:tipoTasa/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoTasa/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns21:tipoTasa/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoTasa/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns21:tipoTasa/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoTasa/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns21:tipoTasa/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns21:tipoTasa>
                                          </xsl:if>
                                          <xsl:if test="ns21:baseCalculo">
                                             <ns21:baseCalculo>
                                                <xsl:if test="ns21:baseCalculo/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns21:baseCalculo/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns21:baseCalculo/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns21:baseCalculo/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns21:baseCalculo/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns21:baseCalculo/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns21:baseCalculo/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns21:baseCalculo/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns21:baseCalculo/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns21:baseCalculo/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns21:baseCalculo>
                                          </xsl:if>
                                          <xsl:if test="ns21:responsableComision">
                                             <ns21:responsableComision>
                                                <xsl:value-of select="ns21:responsableComision"/>
                                             </ns21:responsableComision>
                                          </xsl:if>
                                          <ns21:estadoTCC>
                                             <xsl:if test="ns21:estadoTCC/ns27:id">
                                                <ns27:id>
                                                   <xsl:value-of select="ns21:estadoTCC/ns27:id"/>
                                                </ns27:id>
                                             </xsl:if>
                                             <xsl:if test="ns21:estadoTCC/ns27:descripcion">
                                                <ns27:descripcion>
                                                   <xsl:value-of select="ns21:estadoTCC/ns27:descripcion"/>
                                                </ns27:descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns21:estadoTCC/ns27:descripcionCorta">
                                                <ns27:descripcionCorta>
                                                   <xsl:value-of select="ns21:estadoTCC/ns27:descripcionCorta"/>
                                                </ns27:descripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns21:estadoTCC/ns27:estatus">
                                                <ns27:estatus>
                                                   <xsl:value-of select="ns21:estadoTCC/ns27:estatus"/>
                                                </ns27:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns21:estadoTCC/ns27:codigoExterno">
                                                <ns27:codigoExterno>
                                                   <xsl:value-of select="ns21:estadoTCC/ns27:codigoExterno"/>
                                                </ns27:codigoExterno>
                                             </xsl:if>
                                             <xsl:if test="ns21:estadoTCC/ns27:esSubEstado">
                                                <ns27:esSubEstado>
                                                   <xsl:value-of select="ns21:estadoTCC/ns27:esSubEstado"/>
                                                </ns27:esSubEstado>
                                             </xsl:if>
                                          </ns21:estadoTCC>
                                          <ns21:subEstadoTCC>
                                             <xsl:if test="ns21:subEstadoTCC/ns27:id">
                                                <ns27:id>
                                                   <xsl:value-of select="ns21:subEstadoTCC/ns27:id"/>
                                                </ns27:id>
                                             </xsl:if>
                                             <xsl:if test="ns21:subEstadoTCC/ns27:descripcion">
                                                <ns27:descripcion>
                                                   <xsl:value-of select="ns21:subEstadoTCC/ns27:descripcion"/>
                                                </ns27:descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns21:subEstadoTCC/ns27:descripcionCorta">
                                                <ns27:descripcionCorta>
                                                   <xsl:value-of select="ns21:subEstadoTCC/ns27:descripcionCorta"/>
                                                </ns27:descripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns21:subEstadoTCC/ns27:estatus">
                                                <ns27:estatus>
                                                   <xsl:value-of select="ns21:subEstadoTCC/ns27:estatus"/>
                                                </ns27:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns21:subEstadoTCC/ns27:codigoExterno">
                                                <ns27:codigoExterno>
                                                   <xsl:value-of select="ns21:subEstadoTCC/ns27:codigoExterno"/>
                                                </ns27:codigoExterno>
                                             </xsl:if>
                                             <xsl:if test="ns21:subEstadoTCC/ns27:esSubEstado">
                                                <ns27:esSubEstado>
                                                   <xsl:value-of select="ns21:subEstadoTCC/ns27:esSubEstado"/>
                                                </ns27:esSubEstado>
                                             </xsl:if>
                                          </ns21:subEstadoTCC>
                                          <ns21:fechaRegistro>
                                             <xsl:value-of select="ns21:fechaRegistro"/>
                                          </ns21:fechaRegistro>
                                          <ns21:estado>
                                             <xsl:value-of select="ns21:estado"/>
                                          </ns21:estado>
                                          <ns21:comisionEnmendada>
                                             <xsl:value-of select="ns21:comisionEnmendada"/>
                                          </ns21:comisionEnmendada>
                                          <ns21:fechaEnmienda>
                                             <xsl:value-of select="ns21:fechaEnmienda"/>
                                          </ns21:fechaEnmienda>
                                          <xsl:if test="ns21:banSugerida">
                                             <ns21:banSugerida>
                                                <xsl:value-of select="ns21:banSugerida"/>
                                             </ns21:banSugerida>
                                          </xsl:if>
                                          <xsl:if test="ns21:numeroCuentaCliente">
                                             <ns21:numeroCuentaCliente>
                                                <xsl:value-of select="ns21:numeroCuentaCliente"/>
                                             </ns21:numeroCuentaCliente>
                                          </xsl:if>
                                          <xsl:if test="ns21:observaciones">
                                             <ns21:observaciones>
                                                <xsl:value-of select="ns21:observaciones"/>
                                             </ns21:observaciones>
                                          </xsl:if>
                                          <xsl:for-each select="ns21:configAtributo">
                                             <ns21:configAtributo>
                                                <xsl:if test="ns27:id">
                                                   <ns27:id>
                                                      <xsl:value-of select="ns27:id"/>
                                                   </ns27:id>
                                                </xsl:if>
                                                <ns27:columna>
                                                   <xsl:value-of select="ns27:columna"/>
                                                </ns27:columna>
                                                <xsl:if test="ns27:ordenamiento">
                                                   <ns27:ordenamiento>
                                                      <xsl:value-of select="ns27:ordenamiento"/>
                                                   </ns27:ordenamiento>
                                                </xsl:if>
                                                <xsl:if test="ns27:soloLectura">
                                                   <ns27:soloLectura>
                                                      <xsl:value-of select="ns27:soloLectura"/>
                                                   </ns27:soloLectura>
                                                </xsl:if>
                                                <xsl:if test="ns27:esObligatorio">
                                                   <ns27:esObligatorio>
                                                      <xsl:value-of select="ns27:esObligatorio"/>
                                                   </ns27:esObligatorio>
                                                </xsl:if>
                                                <xsl:if test="ns27:etiqueta">
                                                   <ns27:etiqueta>
                                                      <xsl:value-of select="ns27:etiqueta"/>
                                                   </ns27:etiqueta>
                                                </xsl:if>
                                                <xsl:for-each select="ns27:valor">
                                                   <ns27:valor>
                                                      <xsl:value-of select="."/>
                                                   </ns27:valor>
                                                </xsl:for-each>
                                                <xsl:if test="ns27:tipoValor">
                                                   <ns27:tipoValor>
                                                      <xsl:value-of select="ns27:tipoValor"/>
                                                   </ns27:tipoValor>
                                                </xsl:if>
                                                <xsl:for-each select="ns27:catalogo">
                                                   <ns27:catalogo>
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
                                                   </ns27:catalogo>
                                                </xsl:for-each>
                                             </ns21:configAtributo>
                                          </xsl:for-each>
                                          <xsl:if test="ns21:Accion">
                                             <ns21:Accion>
                                                <xsl:value-of select="ns21:Accion"/>
                                             </ns21:Accion>
                                          </xsl:if>
                                       </ns10:Comision>
                                    </xsl:for-each>
                                    <xsl:for-each select="ns10:Fuente">
                                       <ns10:Fuente>
                                          <ns10:idFuente>
                                             <xsl:value-of select="ns10:idFuente"/>
                                          </ns10:idFuente>
                                          <ns10:idLineaCredito>
                                             <xsl:value-of select="ns10:idLineaCredito"/>
                                          </ns10:idLineaCredito>
                                          <ns10:idLineaPasiva>
                                             <xsl:value-of select="ns10:idLineaPasiva"/>
                                          </ns10:idLineaPasiva>
                                          <ns10:codigoLineaPasiva>
                                             <xsl:value-of select="ns10:codigoLineaPasiva"/>
                                          </ns10:codigoLineaPasiva>
                                          <ns10:idFacturadorLineaPasiva>
                                             <xsl:value-of select="ns10:idFacturadorLineaPasiva"/>
                                          </ns10:idFacturadorLineaPasiva>
                                          <ns10:idFondo>
                                             <xsl:value-of select="ns10:idFondo"/>
                                          </ns10:idFondo>
                                          <ns10:Descripcion>
                                             <xsl:value-of select="ns10:Descripcion"/>
                                          </ns10:Descripcion>
                                          <ns10:FechaObtenido>
                                             <xsl:value-of select="ns10:FechaObtenido"/>
                                          </ns10:FechaObtenido>
                                          <ns10:MontoAsignado>
                                             <xsl:value-of select="ns10:MontoAsignado"/>
                                          </ns10:MontoAsignado>
                                          <ns10:montoDisponible>
                                             <xsl:value-of select="ns10:montoDisponible"/>
                                          </ns10:montoDisponible>
                                          <xsl:for-each select="ns10:Monto">
                                             <ns10:Monto>
                                                <ns7:tipo>
                                                   <xsl:if test="ns7:tipo/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns7:tipo/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns7:tipo/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns7:tipo/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns7:tipo/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns7:tipo/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns7:tipo/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns7:tipo/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns7:tipo/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns7:tipo/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns7:tipo>
                                                <xsl:if test="ns7:importe">
                                                   <ns7:importe>
                                                      <xsl:value-of select="ns7:importe"/>
                                                   </ns7:importe>
                                                </xsl:if>
                                                <xsl:if test="ns7:moneda">
                                                   <ns7:moneda>
                                                      <xsl:if test="ns7:moneda/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns7:moneda/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns7:moneda/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns7:moneda/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns7:moneda/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns7:moneda/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns7:moneda/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns7:moneda/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns7:moneda/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns7:moneda/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns7:moneda>
                                                </xsl:if>
                                             </ns10:Monto>
                                          </xsl:for-each>
                                          <ns10:NumeroAsignacion>
                                             <xsl:value-of select="ns10:NumeroAsignacion"/>
                                          </ns10:NumeroAsignacion>
                                          <ns10:Comentario>
                                             <xsl:value-of select="ns10:Comentario"/>
                                          </ns10:Comentario>
                                          <ns10:idContrato>
                                             <xsl:value-of select="ns10:idContrato"/>
                                          </ns10:idContrato>
                                          <ns10:FechaRegistro>
                                             <xsl:value-of select="ns10:FechaRegistro"/>
                                          </ns10:FechaRegistro>
                                          <ns10:Estado>
                                             <xsl:value-of select="ns10:Estado"/>
                                          </ns10:Estado>
                                          <ns10:esExterna>
                                             <xsl:value-of select="ns10:esExterna"/>
                                          </ns10:esExterna>
                                       </ns10:Fuente>
                                    </xsl:for-each>
                                    <xsl:for-each select="ns10:ContratoDesembolso">
                                       <ns10:ContratoDesembolso>
                                          <ns23:idDesembolso>
                                             <xsl:value-of select="ns23:idDesembolso"/>
                                          </ns23:idDesembolso>
                                          <xsl:if test="ns23:idFacturador">
                                             <ns23:idFacturador>
                                                <xsl:value-of select="ns23:idFacturador"/>
                                             </ns23:idFacturador>
                                          </xsl:if>
                                          <xsl:if test="ns23:producto">
                                             <ns23:producto>
                                                <ns15:idProducto>
                                                   <xsl:value-of select="ns23:producto/ns15:idProducto"/>
                                                </ns15:idProducto>
                                                <ns15:descripcion>
                                                   <xsl:value-of select="ns23:producto/ns15:descripcion"/>
                                                </ns15:descripcion>
                                                <ns15:descripcionCorta>
                                                   <xsl:value-of select="ns23:producto/ns15:descripcionCorta"/>
                                                </ns15:descripcionCorta>
                                                <ns15:fechaRegistro>
                                                   <xsl:value-of select="ns23:producto/ns15:fechaRegistro"/>
                                                </ns15:fechaRegistro>
                                                <ns15:codExterno>
                                                   <xsl:value-of select="ns23:producto/ns15:codExterno"/>
                                                </ns15:codExterno>
                                                <xsl:for-each select="ns23:producto/ns23:Componente">
                                                   <ns23:Componente>
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
                                                      <ns23:TipoComponente>
                                                         <xsl:if test="ns23:TipoComponente/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns23:TipoComponente/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoComponente/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns23:TipoComponente/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoComponente/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns23:TipoComponente/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoComponente/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns23:TipoComponente/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoComponente/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns23:TipoComponente/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns23:TipoComponente>
                                                      <ns23:TipoTasa>
                                                         <xsl:if test="ns23:TipoTasa/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns23:TipoTasa/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoTasa/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns23:TipoTasa/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoTasa/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns23:TipoTasa/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoTasa/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns23:TipoTasa/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoTasa/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns23:TipoTasa/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns23:TipoTasa>
                                                      <ns23:esPrincipal>
                                                         <xsl:value-of select="ns23:esPrincipal"/>
                                                      </ns23:esPrincipal>
                                                      <xsl:if test="ns23:Plazo">
                                                         <ns23:Plazo>
                                                            <ns7:Tipo>
                                                               <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Plazo/ns7:Tipo/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns23:Plazo/ns7:Tipo/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns7:Tipo>
                                                            <ns7:Valor>
                                                               <xsl:value-of select="ns23:Plazo/ns7:Valor"/>
                                                            </ns7:Valor>
                                                         </ns23:Plazo>
                                                      </xsl:if>
                                                   </ns23:Componente>
                                                </xsl:for-each>
                                             </ns23:producto>
                                          </xsl:if>
                                          <xsl:if test="ns23:referencia">
                                             <ns23:referencia>
                                                <xsl:value-of select="ns23:referencia"/>
                                             </ns23:referencia>
                                          </xsl:if>
                                          <xsl:for-each select="ns23:monto">
                                             <ns23:monto>
                                                <ns7:tipo>
                                                   <xsl:if test="ns7:tipo/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns7:tipo/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns7:tipo/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns7:tipo/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns7:tipo/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns7:tipo/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns7:tipo/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns7:tipo/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns7:tipo/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns7:tipo/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns7:tipo>
                                                <xsl:if test="ns7:importe">
                                                   <ns7:importe>
                                                      <xsl:value-of select="ns7:importe"/>
                                                   </ns7:importe>
                                                </xsl:if>
                                                <xsl:if test="ns7:moneda">
                                                   <ns7:moneda>
                                                      <xsl:if test="ns7:moneda/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns7:moneda/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns7:moneda/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns7:moneda/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns7:moneda/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns7:moneda/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns7:moneda/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns7:moneda/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns7:moneda/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns7:moneda/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns7:moneda>
                                                </xsl:if>
                                             </ns23:monto>
                                          </xsl:for-each>
                                          <ns23:estado>
                                             <xsl:if test="ns23:estado/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns23:estado/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns23:estado/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns23:estado/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns23:estado/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns23:estado/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns23:estado/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns23:estado/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns23:estado/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns23:estado/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns23:estado>
                                          <xsl:if test="ns23:programado">
                                             <ns23:programado>
                                                <xsl:value-of select="ns23:programado"/>
                                             </ns23:programado>
                                          </xsl:if>
                                          <xsl:if test="ns23:fechaEstimadaDesembolsar">
                                             <ns23:fechaEstimadaDesembolsar>
                                                <xsl:value-of select="ns23:fechaEstimadaDesembolsar"/>
                                             </ns23:fechaEstimadaDesembolsar>
                                          </xsl:if>
                                          <xsl:if test="ns23:fechaEfectiva">
                                             <ns23:fechaEfectiva>
                                                <xsl:value-of select="ns23:fechaEfectiva"/>
                                             </ns23:fechaEfectiva>
                                          </xsl:if>
                                          <xsl:if test="ns23:fechaAsignacion">
                                             <ns23:fechaAsignacion>
                                                <xsl:value-of select="ns23:fechaAsignacion"/>
                                             </ns23:fechaAsignacion>
                                          </xsl:if>
                                          <xsl:if test="ns23:fechaRegistro">
                                             <ns23:fechaRegistro>
                                                <xsl:value-of select="ns23:fechaRegistro"/>
                                             </ns23:fechaRegistro>
                                          </xsl:if>
                                          <xsl:if test="ns23:fechaVencimiento">
                                             <ns23:fechaVencimiento>
                                                <xsl:value-of select="ns23:fechaVencimiento"/>
                                             </ns23:fechaVencimiento>
                                          </xsl:if>
                                          <xsl:if test="ns23:estatus">
                                             <ns23:estatus>
                                                <xsl:value-of select="ns23:estatus"/>
                                             </ns23:estatus>
                                          </xsl:if>
                                          <xsl:if test="ns23:condicionesFinancieras">
                                             <ns23:condicionesFinancieras>
                                                <ns23:idCondicionFinanciera>
                                                   <xsl:value-of select="ns23:condicionesFinancieras/ns23:idCondicionFinanciera"/>
                                                </ns23:idCondicionFinanciera>
                                                <ns23:tasa>
                                                   <ns23:tipo>
                                                      <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:tipo/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns23:tipo>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:fija">
                                                      <ns23:fija>
                                                         <ns23:valor>
                                                            <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:fija/ns23:valor"/>
                                                         </ns23:valor>
                                                      </ns23:fija>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable">
                                                      <ns23:variable>
                                                         <ns23:tasaReferencia>
                                                            <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:Id">
                                                               <ns22:Id>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:Id"/>
                                                               </ns22:Id>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:Descripcion">
                                                               <ns22:Descripcion>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:Descripcion"/>
                                                               </ns22:Descripcion>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:DescripcionCorta">
                                                               <ns22:DescripcionCorta>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:DescripcionCorta"/>
                                                               </ns22:DescripcionCorta>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:estatus">
                                                               <ns22:estatus>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:estatus"/>
                                                               </ns22:estatus>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:codigoExterno">
                                                               <ns22:codigoExterno>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns22:codigoExterno"/>
                                                               </ns22:codigoExterno>
                                                            </xsl:if>
                                                            <ns23:valor>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:tasaReferencia/ns23:valor"/>
                                                            </ns23:valor>
                                                         </ns23:tasaReferencia>
                                                         <ns23:spread>
                                                            <ns23:valor>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:valor"/>
                                                            </ns23:valor>
                                                            <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:codigo">
                                                               <ns23:codigo>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:codigo"/>
                                                               </ns23:codigo>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision">
                                                               <ns23:revision>
                                                                  <ns23:FechaInicio>
                                                                     <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:FechaInicio"/>
                                                                  </ns23:FechaInicio>
                                                                  <ns23:Frecuencia>
                                                                     <ns7:Tipo>
                                                                        <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                                           <ns22:Id>
                                                                              <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                                           </ns22:Id>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                                           <ns22:Descripcion>
                                                                              <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                                           </ns22:Descripcion>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                                           <ns22:DescripcionCorta>
                                                                              <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                           </ns22:DescripcionCorta>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                                           <ns22:estatus>
                                                                              <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                                           </ns22:estatus>
                                                                        </xsl:if>
                                                                        <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                                           <ns22:codigoExterno>
                                                                              <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                                           </ns22:codigoExterno>
                                                                        </xsl:if>
                                                                     </ns7:Tipo>
                                                                     <ns7:Valor>
                                                                        <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:spread/ns23:revision/ns23:Frecuencia/ns7:Valor"/>
                                                                     </ns7:Valor>
                                                                  </ns23:Frecuencia>
                                                               </ns23:revision>
                                                            </xsl:if>
                                                         </ns23:spread>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision">
                                                            <ns23:revision>
                                                               <ns23:FechaInicio>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:FechaInicio"/>
                                                               </ns23:FechaInicio>
                                                               <ns23:Frecuencia>
                                                                  <ns7:Tipo>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns7:Tipo>
                                                                  <ns7:Valor>
                                                                     <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:revision/ns23:Frecuencia/ns7:Valor"/>
                                                                  </ns7:Valor>
                                                               </ns23:Frecuencia>
                                                            </ns23:revision>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:limite">
                                                            <ns23:limite>
                                                               <ns7:maximo>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:limite/ns7:maximo"/>
                                                               </ns7:maximo>
                                                               <ns7:minimo>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:variable/ns23:limite/ns7:minimo"/>
                                                               </ns7:minimo>
                                                            </ns23:limite>
                                                         </xsl:if>
                                                      </ns23:variable>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:especial">
                                                      <ns23:especial>
                                                         <ns23:valor>
                                                            <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:especial/ns23:valor"/>
                                                         </ns23:valor>
                                                      </ns23:especial>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora">
                                                      <ns23:spreadMora>
                                                         <ns23:valor>
                                                            <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:valor"/>
                                                         </ns23:valor>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:codigo">
                                                            <ns23:codigo>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:codigo"/>
                                                            </ns23:codigo>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision">
                                                            <ns23:revision>
                                                               <ns23:FechaInicio>
                                                                  <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:FechaInicio"/>
                                                               </ns23:FechaInicio>
                                                               <ns23:Frecuencia>
                                                                  <ns7:Tipo>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                                        <ns22:Id>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                                        </ns22:Id>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                                        <ns22:Descripcion>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                                        </ns22:Descripcion>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                                        <ns22:DescripcionCorta>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                        </ns22:DescripcionCorta>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                                        <ns22:estatus>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                                        </ns22:estatus>
                                                                     </xsl:if>
                                                                     <xsl:if test="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                                        <ns22:codigoExterno>
                                                                           <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                                        </ns22:codigoExterno>
                                                                     </xsl:if>
                                                                  </ns7:Tipo>
                                                                  <ns7:Valor>
                                                                     <xsl:value-of select="ns23:condicionesFinancieras/ns23:tasa/ns23:spreadMora/ns23:revision/ns23:Frecuencia/ns7:Valor"/>
                                                                  </ns7:Valor>
                                                               </ns23:Frecuencia>
                                                            </ns23:revision>
                                                         </xsl:if>
                                                      </ns23:spreadMora>
                                                   </xsl:if>
                                                </ns23:tasa>
                                                <ns23:fondo>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:fondo/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:fondo/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:fondo/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:fondo/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:fondo/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:fondo/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:fondo/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:fondo/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:fondo/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:fondo/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                   <xsl:for-each select="ns23:condicionesFinancieras/ns23:fondo/ns23:Validador">
                                                      <ns23:Validador>
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
                                                      </ns23:Validador>
                                                   </xsl:for-each>
                                                </ns23:fondo>
                                                <ns23:baseCalculo>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:baseCalculo/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns23:baseCalculo>
                                                <ns23:principal>
                                                   <ns23:FechaInicio>
                                                      <xsl:value-of select="ns23:condicionesFinancieras/ns23:principal/ns23:FechaInicio"/>
                                                   </ns23:FechaInicio>
                                                   <ns23:Frecuencia>
                                                      <ns7:Tipo>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns7:Tipo>
                                                      <ns7:Valor>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:principal/ns23:Frecuencia/ns7:Valor"/>
                                                      </ns7:Valor>
                                                   </ns23:Frecuencia>
                                                   <ns23:fechaVencimiento>
                                                      <xsl:value-of select="ns23:condicionesFinancieras/ns23:principal/ns23:fechaVencimiento"/>
                                                   </ns23:fechaVencimiento>
                                                </ns23:principal>
                                                <ns23:interes>
                                                   <ns23:FechaInicio>
                                                      <xsl:value-of select="ns23:condicionesFinancieras/ns23:interes/ns23:FechaInicio"/>
                                                   </ns23:FechaInicio>
                                                   <ns23:Frecuencia>
                                                      <ns7:Tipo>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns7:Tipo>
                                                      <ns7:Valor>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:interes/ns23:Frecuencia/ns7:Valor"/>
                                                      </ns7:Valor>
                                                   </ns23:Frecuencia>
                                                </ns23:interes>
                                                <ns23:periodoGracia>
                                                   <ns23:FechaInicio>
                                                      <xsl:value-of select="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:FechaInicio"/>
                                                   </ns23:FechaInicio>
                                                   <ns23:Frecuencia>
                                                      <ns7:Tipo>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns7:Tipo>
                                                      <ns7:Valor>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:periodoGracia/ns23:Frecuencia/ns7:Valor"/>
                                                      </ns7:Valor>
                                                   </ns23:Frecuencia>
                                                </ns23:periodoGracia>
                                                <ns23:tratamientoDiasFeriados>
                                                   <xsl:value-of select="ns23:condicionesFinancieras/ns23:tratamientoDiasFeriados"/>
                                                </ns23:tratamientoDiasFeriados>
                                                <ns23:tipoCalendario>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:condicionesFinancieras/ns23:tipoCalendario/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns23:tipoCalendario>
                                                <ns23:moverEntreMeses>
                                                   <xsl:value-of select="ns23:condicionesFinancieras/ns23:moverEntreMeses"/>
                                                </ns23:moverEntreMeses>
                                                <xsl:for-each select="ns23:condicionesFinancieras/ns23:calendarioComplejo">
                                                   <ns23:calendarioComplejo>
                                                      <ns23:TipoPlan>
                                                         <xsl:if test="ns23:TipoPlan/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns23:TipoPlan/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoPlan/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns23:TipoPlan/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoPlan/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns23:TipoPlan/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoPlan/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns23:TipoPlan/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:TipoPlan/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns23:TipoPlan/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns23:TipoPlan>
                                                      <ns23:Frecuencia>
                                                         <ns23:FechaInicio>
                                                            <xsl:value-of select="ns23:Frecuencia/ns23:FechaInicio"/>
                                                         </ns23:FechaInicio>
                                                         <ns23:Frecuencia>
                                                            <ns7:Tipo>
                                                               <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Tipo/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns7:Tipo>
                                                            <ns7:Valor>
                                                               <xsl:value-of select="ns23:Frecuencia/ns23:Frecuencia/ns7:Valor"/>
                                                            </ns7:Valor>
                                                         </ns23:Frecuencia>
                                                      </ns23:Frecuencia>
                                                      <ns23:Monto>
                                                         <ns7:tipo>
                                                            <xsl:if test="ns23:Monto/ns7:tipo/ns22:Id">
                                                               <ns22:Id>
                                                                  <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Id"/>
                                                               </ns22:Id>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:Monto/ns7:tipo/ns22:Descripcion">
                                                               <ns22:Descripcion>
                                                                  <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Descripcion"/>
                                                               </ns22:Descripcion>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:Monto/ns7:tipo/ns22:DescripcionCorta">
                                                               <ns22:DescripcionCorta>
                                                                  <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:DescripcionCorta"/>
                                                               </ns22:DescripcionCorta>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:Monto/ns7:tipo/ns22:estatus">
                                                               <ns22:estatus>
                                                                  <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:estatus"/>
                                                               </ns22:estatus>
                                                            </xsl:if>
                                                            <xsl:if test="ns23:Monto/ns7:tipo/ns22:codigoExterno">
                                                               <ns22:codigoExterno>
                                                                  <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:codigoExterno"/>
                                                               </ns22:codigoExterno>
                                                            </xsl:if>
                                                         </ns7:tipo>
                                                         <xsl:if test="ns23:Monto/ns7:importe">
                                                            <ns7:importe>
                                                               <xsl:value-of select="ns23:Monto/ns7:importe"/>
                                                            </ns7:importe>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda">
                                                            <ns7:moneda>
                                                               <xsl:if test="ns23:Monto/ns7:moneda/ns22:Id">
                                                                  <ns22:Id>
                                                                     <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Id"/>
                                                                  </ns22:Id>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Monto/ns7:moneda/ns22:Descripcion">
                                                                  <ns22:Descripcion>
                                                                     <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Descripcion"/>
                                                                  </ns22:Descripcion>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Monto/ns7:moneda/ns22:DescripcionCorta">
                                                                  <ns22:DescripcionCorta>
                                                                     <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                                  </ns22:DescripcionCorta>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Monto/ns7:moneda/ns22:estatus">
                                                                  <ns22:estatus>
                                                                     <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:estatus"/>
                                                                  </ns22:estatus>
                                                               </xsl:if>
                                                               <xsl:if test="ns23:Monto/ns7:moneda/ns22:codigoExterno">
                                                                  <ns22:codigoExterno>
                                                                     <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:codigoExterno"/>
                                                                  </ns22:codigoExterno>
                                                               </xsl:if>
                                                            </ns7:moneda>
                                                         </xsl:if>
                                                      </ns23:Monto>
                                                      <ns23:NumeroCuotas>
                                                         <xsl:value-of select="ns23:NumeroCuotas"/>
                                                      </ns23:NumeroCuotas>
                                                   </ns23:calendarioComplejo>
                                                </xsl:for-each>
                                             </ns23:condicionesFinancieras>
                                          </xsl:if>
                                          <xsl:if test="ns23:idLinea">
                                             <ns23:idLinea>
                                                <xsl:value-of select="ns23:idLinea"/>
                                             </ns23:idLinea>
                                          </xsl:if>
                                          <xsl:if test="ns23:recursosExternos">
                                             <ns23:recursosExternos>
                                                <xsl:value-of select="ns23:recursosExternos"/>
                                             </ns23:recursosExternos>
                                          </xsl:if>
                                          <xsl:if test="ns23:cuentaCliente">
                                             <ns23:cuentaCliente>
                                                <xsl:value-of select="ns23:cuentaCliente"/>
                                             </ns23:cuentaCliente>
                                          </xsl:if>
                                          <xsl:if test="ns23:usuario">
                                             <ns23:usuario>
                                                <xsl:value-of select="ns23:usuario"/>
                                             </ns23:usuario>
                                          </xsl:if>
                                          <xsl:if test="ns23:fechaDisponibilidadFondos">
                                             <ns23:fechaDisponibilidadFondos>
                                                <xsl:value-of select="ns23:fechaDisponibilidadFondos"/>
                                             </ns23:fechaDisponibilidadFondos>
                                          </xsl:if>
                                          <xsl:if test="ns23:origenTransferenciaCte">
                                             <ns23:origenTransferenciaCte>
                                                <xsl:value-of select="ns23:origenTransferenciaCte"/>
                                             </ns23:origenTransferenciaCte>
                                          </xsl:if>
                                          <xsl:if test="ns23:Programa">
                                             <ns23:Programa>
                                                <ns20:LineaFinanciera>
                                                   <xsl:if test="ns23:Programa/ns20:LineaFinanciera/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:Programa/ns20:LineaFinanciera/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:LineaFinanciera/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:Programa/ns20:LineaFinanciera/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:LineaFinanciera/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:Programa/ns20:LineaFinanciera/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:LineaFinanciera/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:Programa/ns20:LineaFinanciera/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:LineaFinanciera/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:Programa/ns20:LineaFinanciera/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns20:LineaFinanciera>
                                                <ns20:DestinoFinanciamiento>
                                                   <xsl:if test="ns23:Programa/ns20:DestinoFinanciamiento/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:Programa/ns20:DestinoFinanciamiento/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:DestinoFinanciamiento/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:Programa/ns20:DestinoFinanciamiento/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:DestinoFinanciamiento/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:Programa/ns20:DestinoFinanciamiento/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:DestinoFinanciamiento/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:Programa/ns20:DestinoFinanciamiento/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:DestinoFinanciamiento/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:Programa/ns20:DestinoFinanciamiento/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns20:DestinoFinanciamiento>
                                                <ns20:ModalidadFinanciamiento>
                                                   <xsl:if test="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:Programa/ns20:ModalidadFinanciamiento/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns20:ModalidadFinanciamiento>
                                                <ns20:HerramientaCE>
                                                   <ns20:ActividadEconomica>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns20:ActividadEconomica>
                                                   <ns20:EjeEstrategico>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns20:EjeEstrategico>
                                                   <ns20:AreaFocalizacion>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns23:Programa/ns20:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns20:AreaFocalizacion>
                                                </ns20:HerramientaCE>
                                                <ns20:ClasificacionGeneral>
                                                   <ns20:SectorMercado>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorMercado/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns20:SectorMercado>
                                                   <ns20:SectorInstitucional>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns23:Programa/ns20:ClasificacionGeneral/ns20:SectorInstitucional/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns20:SectorInstitucional>
                                                </ns20:ClasificacionGeneral>
                                             </ns23:Programa>
                                          </xsl:if>
                                          <xsl:if test="ns23:EstimadoDesembolso">
                                             <ns23:EstimadoDesembolso>
                                                <ns23:Plazo>
                                                   <xsl:value-of select="ns23:EstimadoDesembolso/ns23:Plazo"/>
                                                </ns23:Plazo>
                                                <ns23:Frecuencia>
                                                   <xsl:if test="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:EstimadoDesembolso/ns23:Frecuencia/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns23:Frecuencia>
                                             </ns23:EstimadoDesembolso>
                                          </xsl:if>
                                          <xsl:if test="ns23:fechaInicioProceso">
                                             <ns23:fechaInicioProceso>
                                                <xsl:value-of select="ns23:fechaInicioProceso"/>
                                             </ns23:fechaInicioProceso>
                                          </xsl:if>
                                          <xsl:if test="ns23:fechaEstimadaDisponibilidad">
                                             <ns23:fechaEstimadaDisponibilidad>
                                                <xsl:value-of select="ns23:fechaEstimadaDisponibilidad"/>
                                             </ns23:fechaEstimadaDisponibilidad>
                                          </xsl:if>
                                          <xsl:for-each select="ns23:Utilizacion">
                                             <ns23:Utilizacion>
                                                <ns10:idFuente>
                                                   <xsl:value-of select="ns10:idFuente"/>
                                                </ns10:idFuente>
                                                <ns10:idLineaCredito>
                                                   <xsl:value-of select="ns10:idLineaCredito"/>
                                                </ns10:idLineaCredito>
                                                <ns10:idLineaPasiva>
                                                   <xsl:value-of select="ns10:idLineaPasiva"/>
                                                </ns10:idLineaPasiva>
                                                <ns10:codigoLineaPasiva>
                                                   <xsl:value-of select="ns10:codigoLineaPasiva"/>
                                                </ns10:codigoLineaPasiva>
                                                <ns10:idFacturadorLineaPasiva>
                                                   <xsl:value-of select="ns10:idFacturadorLineaPasiva"/>
                                                </ns10:idFacturadorLineaPasiva>
                                                <ns10:idFondo>
                                                   <xsl:value-of select="ns10:idFondo"/>
                                                </ns10:idFondo>
                                                <ns10:Descripcion>
                                                   <xsl:value-of select="ns10:Descripcion"/>
                                                </ns10:Descripcion>
                                                <ns10:FechaObtenido>
                                                   <xsl:value-of select="ns10:FechaObtenido"/>
                                                </ns10:FechaObtenido>
                                                <ns10:MontoAsignado>
                                                   <xsl:value-of select="ns10:MontoAsignado"/>
                                                </ns10:MontoAsignado>
                                                <ns10:montoDisponible>
                                                   <xsl:value-of select="ns10:montoDisponible"/>
                                                </ns10:montoDisponible>
                                                <xsl:for-each select="ns10:Monto">
                                                   <ns10:Monto>
                                                      <ns7:tipo>
                                                         <xsl:if test="ns7:tipo/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns7:tipo/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns7:tipo/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns7:tipo/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns7:tipo/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns7:tipo/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns7:tipo/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns7:tipo/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns7:tipo/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns7:tipo/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns7:tipo>
                                                      <xsl:if test="ns7:importe">
                                                         <ns7:importe>
                                                            <xsl:value-of select="ns7:importe"/>
                                                         </ns7:importe>
                                                      </xsl:if>
                                                      <xsl:if test="ns7:moneda">
                                                         <ns7:moneda>
                                                            <xsl:if test="ns7:moneda/ns22:Id">
                                                               <ns22:Id>
                                                                  <xsl:value-of select="ns7:moneda/ns22:Id"/>
                                                               </ns22:Id>
                                                            </xsl:if>
                                                            <xsl:if test="ns7:moneda/ns22:Descripcion">
                                                               <ns22:Descripcion>
                                                                  <xsl:value-of select="ns7:moneda/ns22:Descripcion"/>
                                                               </ns22:Descripcion>
                                                            </xsl:if>
                                                            <xsl:if test="ns7:moneda/ns22:DescripcionCorta">
                                                               <ns22:DescripcionCorta>
                                                                  <xsl:value-of select="ns7:moneda/ns22:DescripcionCorta"/>
                                                               </ns22:DescripcionCorta>
                                                            </xsl:if>
                                                            <xsl:if test="ns7:moneda/ns22:estatus">
                                                               <ns22:estatus>
                                                                  <xsl:value-of select="ns7:moneda/ns22:estatus"/>
                                                               </ns22:estatus>
                                                            </xsl:if>
                                                            <xsl:if test="ns7:moneda/ns22:codigoExterno">
                                                               <ns22:codigoExterno>
                                                                  <xsl:value-of select="ns7:moneda/ns22:codigoExterno"/>
                                                               </ns22:codigoExterno>
                                                            </xsl:if>
                                                         </ns7:moneda>
                                                      </xsl:if>
                                                   </ns10:Monto>
                                                </xsl:for-each>
                                                <ns10:NumeroAsignacion>
                                                   <xsl:value-of select="ns10:NumeroAsignacion"/>
                                                </ns10:NumeroAsignacion>
                                                <ns10:Comentario>
                                                   <xsl:value-of select="ns10:Comentario"/>
                                                </ns10:Comentario>
                                                <ns10:idContrato>
                                                   <xsl:value-of select="ns10:idContrato"/>
                                                </ns10:idContrato>
                                                <ns10:FechaRegistro>
                                                   <xsl:value-of select="ns10:FechaRegistro"/>
                                                </ns10:FechaRegistro>
                                                <ns10:Estado>
                                                   <xsl:value-of select="ns10:Estado"/>
                                                </ns10:Estado>
                                                <ns10:esExterna>
                                                   <xsl:value-of select="ns10:esExterna"/>
                                                </ns10:esExterna>
                                             </ns23:Utilizacion>
                                          </xsl:for-each>
                                          <xsl:for-each select="ns23:Cargo">
                                             <ns23:Cargo>
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
                                                <ns23:Monto>
                                                   <ns7:tipo>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns7:tipo>
                                                   <xsl:if test="ns23:Monto/ns7:importe">
                                                      <ns7:importe>
                                                         <xsl:value-of select="ns23:Monto/ns7:importe"/>
                                                      </ns7:importe>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Monto/ns7:moneda">
                                                      <ns7:moneda>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns7:moneda>
                                                   </xsl:if>
                                                </ns23:Monto>
                                                <ns23:FechaRegistro>
                                                   <xsl:value-of select="ns23:FechaRegistro"/>
                                                </ns23:FechaRegistro>
                                                <xsl:if test="ns23:Status">
                                                   <ns23:Status>
                                                      <xsl:value-of select="ns23:Status"/>
                                                   </ns23:Status>
                                                </xsl:if>
                                                <xsl:if test="ns23:SoloLectura">
                                                   <ns23:SoloLectura>
                                                      <xsl:value-of select="ns23:SoloLectura"/>
                                                   </ns23:SoloLectura>
                                                </xsl:if>
                                                <xsl:if test="ns23:TCC">
                                                   <ns23:TCC>
                                                      <ns11:id>
                                                         <xsl:value-of select="ns23:TCC/ns11:id"/>
                                                      </ns11:id>
                                                      <ns11:tipo>
                                                         <xsl:value-of select="ns23:TCC/ns11:tipo"/>
                                                      </ns11:tipo>
                                                      <ns11:estado>
                                                         <xsl:value-of select="ns23:TCC/ns11:estado"/>
                                                      </ns11:estado>
                                                      <ns11:subEstado>
                                                         <xsl:value-of select="ns23:TCC/ns11:subEstado"/>
                                                      </ns11:subEstado>
                                                   </ns23:TCC>
                                                </xsl:if>
                                             </ns23:Cargo>
                                          </xsl:for-each>
                                          <xsl:for-each select="ns23:Comision">
                                             <ns23:Comision>
                                                <ns21:idComision>
                                                   <xsl:value-of select="ns21:idComision"/>
                                                </ns21:idComision>
                                                <ns21:idOperacion>
                                                   <xsl:value-of select="ns21:idOperacion"/>
                                                </ns21:idOperacion>
                                                <xsl:if test="ns21:nombre">
                                                   <ns21:nombre>
                                                      <xsl:value-of select="ns21:nombre"/>
                                                   </ns21:nombre>
                                                </xsl:if>
                                                <xsl:if test="ns21:descripcion">
                                                   <ns21:descripcion>
                                                      <xsl:value-of select="ns21:descripcion"/>
                                                   </ns21:descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoComision">
                                                   <ns21:tipoComision>
                                                      <xsl:if test="ns21:tipoComision/ns21:idCatComision">
                                                         <ns21:idCatComision>
                                                            <xsl:value-of select="ns21:tipoComision/ns21:idCatComision"/>
                                                         </ns21:idCatComision>
                                                      </xsl:if>
                                                      <ns21:descripcion>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:descripcion"/>
                                                      </ns21:descripcion>
                                                      <ns21:descripcionCorta>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:descripcionCorta"/>
                                                      </ns21:descripcionCorta>
                                                      <xsl:if test="ns21:tipoComision/ns21:idTipoComision">
                                                         <ns21:idTipoComision>
                                                            <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:Id">
                                                               <ns22:Id>
                                                                  <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:Id"/>
                                                               </ns22:Id>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:Descripcion">
                                                               <ns22:Descripcion>
                                                                  <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:Descripcion"/>
                                                               </ns22:Descripcion>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:DescripcionCorta">
                                                               <ns22:DescripcionCorta>
                                                                  <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:DescripcionCorta"/>
                                                               </ns22:DescripcionCorta>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:estatus">
                                                               <ns22:estatus>
                                                                  <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:estatus"/>
                                                               </ns22:estatus>
                                                            </xsl:if>
                                                            <xsl:if test="ns21:tipoComision/ns21:idTipoComision/ns22:codigoExterno">
                                                               <ns22:codigoExterno>
                                                                  <xsl:value-of select="ns21:tipoComision/ns21:idTipoComision/ns22:codigoExterno"/>
                                                               </ns22:codigoExterno>
                                                            </xsl:if>
                                                         </ns21:idTipoComision>
                                                      </xsl:if>
                                                      <ns21:esEditableEnFormalizacion>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:esEditableEnFormalizacion"/>
                                                      </ns21:esEditableEnFormalizacion>
                                                      <ns21:requiereValidarCOFI>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:requiereValidarCOFI"/>
                                                      </ns21:requiereValidarCOFI>
                                                      <ns21:sePuedeDispensar>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:sePuedeDispensar"/>
                                                      </ns21:sePuedeDispensar>
                                                      <ns21:seRegistraEnFlexCube>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:seRegistraEnFlexCube"/>
                                                      </ns21:seRegistraEnFlexCube>
                                                      <ns21:esPlantilla>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:esPlantilla"/>
                                                      </ns21:esPlantilla>
                                                      <ns21:idComisionPrecarga>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:idComisionPrecarga"/>
                                                      </ns21:idComisionPrecarga>
                                                      <ns21:fechaRegistro>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:fechaRegistro"/>
                                                      </ns21:fechaRegistro>
                                                      <ns21:estado>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:estado"/>
                                                      </ns21:estado>
                                                      <ns21:codigoExterno>
                                                         <xsl:value-of select="ns21:tipoComision/ns21:codigoExterno"/>
                                                      </ns21:codigoExterno>
                                                   </ns21:tipoComision>
                                                </xsl:if>
                                                <xsl:if test="ns21:moneda">
                                                   <ns21:moneda>
                                                      <xsl:if test="ns21:moneda/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns21:moneda/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:moneda/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns21:moneda/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:moneda/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns21:moneda/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:moneda/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns21:moneda/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:moneda/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns21:moneda/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns21:moneda>
                                                </xsl:if>
                                                <xsl:if test="ns21:montoComision">
                                                   <ns21:montoComision>
                                                      <xsl:value-of select="ns21:montoComision"/>
                                                   </ns21:montoComision>
                                                </xsl:if>
                                                <xsl:if test="ns21:montoBase">
                                                   <ns21:montoBase>
                                                      <xsl:if test="ns21:montoBase/ns21:idMontoBase">
                                                         <ns21:idMontoBase>
                                                            <xsl:value-of select="ns21:montoBase/ns21:idMontoBase"/>
                                                         </ns21:idMontoBase>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:montoBase/ns21:valorMontoBase">
                                                         <ns21:valorMontoBase>
                                                            <xsl:value-of select="ns21:montoBase/ns21:valorMontoBase"/>
                                                         </ns21:valorMontoBase>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:montoBase/ns21:porcentajeMontoBase">
                                                         <ns21:porcentajeMontoBase>
                                                            <xsl:value-of select="ns21:montoBase/ns21:porcentajeMontoBase"/>
                                                         </ns21:porcentajeMontoBase>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:montoBase/ns21:descripcionMontoBase">
                                                         <ns21:descripcionMontoBase>
                                                            <xsl:value-of select="ns21:montoBase/ns21:descripcionMontoBase"/>
                                                         </ns21:descripcionMontoBase>
                                                      </xsl:if>
                                                   </ns21:montoBase>
                                                </xsl:if>
                                                <xsl:if test="ns21:fechaValor">
                                                   <ns21:fechaValor>
                                                      <xsl:value-of select="ns21:fechaValor"/>
                                                   </ns21:fechaValor>
                                                </xsl:if>
                                                <xsl:if test="ns21:fechaVencimiento">
                                                   <ns21:fechaVencimiento>
                                                      <xsl:value-of select="ns21:fechaVencimiento"/>
                                                   </ns21:fechaVencimiento>
                                                </xsl:if>
                                                <xsl:if test="ns21:fechaInicioCapital">
                                                   <ns21:fechaInicioCapital>
                                                      <xsl:value-of select="ns21:fechaInicioCapital"/>
                                                   </ns21:fechaInicioCapital>
                                                </xsl:if>
                                                <xsl:if test="ns21:fechaInicioComision">
                                                   <ns21:fechaInicioComision>
                                                      <xsl:value-of select="ns21:fechaInicioComision"/>
                                                   </ns21:fechaInicioComision>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoFrecuencia">
                                                   <ns21:tipoFrecuencia>
                                                      <xsl:if test="ns21:tipoFrecuencia/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns21:tipoFrecuencia/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoFrecuencia/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns21:tipoFrecuencia/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoFrecuencia/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns21:tipoFrecuencia/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoFrecuencia/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns21:tipoFrecuencia/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoFrecuencia/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns21:tipoFrecuencia/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns21:tipoFrecuencia>
                                                </xsl:if>
                                                <xsl:if test="ns21:frecuenciaPago">
                                                   <ns21:frecuenciaPago>
                                                      <xsl:value-of select="ns21:frecuenciaPago"/>
                                                   </ns21:frecuenciaPago>
                                                </xsl:if>
                                                <xsl:if test="ns21:fondo">
                                                   <ns21:fondo>
                                                      <xsl:value-of select="ns21:fondo"/>
                                                   </ns21:fondo>
                                                </xsl:if>
                                                <xsl:if test="ns21:comisionCompartida">
                                                   <ns21:comisionCompartida>
                                                      <xsl:value-of select="ns21:comisionCompartida"/>
                                                   </ns21:comisionCompartida>
                                                </xsl:if>
                                                <xsl:if test="ns21:codigoDesembolso">
                                                   <ns21:codigoDesembolso>
                                                      <xsl:value-of select="ns21:codigoDesembolso"/>
                                                   </ns21:codigoDesembolso>
                                                </xsl:if>
                                                <xsl:if test="ns21:numeroTesoreria">
                                                   <ns21:numeroTesoreria>
                                                      <xsl:value-of select="ns21:numeroTesoreria"/>
                                                   </ns21:numeroTesoreria>
                                                </xsl:if>
                                                <xsl:if test="ns21:codigoContrato">
                                                   <ns21:codigoContrato>
                                                      <xsl:value-of select="ns21:codigoContrato"/>
                                                   </ns21:codigoContrato>
                                                </xsl:if>
                                                <xsl:if test="ns21:momentoCobro">
                                                   <ns21:momentoCobro>
                                                      <xsl:if test="ns21:momentoCobro/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns21:momentoCobro/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:momentoCobro/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns21:momentoCobro/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:momentoCobro/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns21:momentoCobro/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:momentoCobro/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns21:momentoCobro/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:momentoCobro/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns21:momentoCobro/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns21:momentoCobro>
                                                </xsl:if>
                                                <xsl:if test="ns21:tipoTasa">
                                                   <ns21:tipoTasa>
                                                      <xsl:if test="ns21:tipoTasa/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns21:tipoTasa/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoTasa/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns21:tipoTasa/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoTasa/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns21:tipoTasa/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoTasa/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns21:tipoTasa/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:tipoTasa/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns21:tipoTasa/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns21:tipoTasa>
                                                </xsl:if>
                                                <xsl:if test="ns21:baseCalculo">
                                                   <ns21:baseCalculo>
                                                      <xsl:if test="ns21:baseCalculo/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns21:baseCalculo/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:baseCalculo/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns21:baseCalculo/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:baseCalculo/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns21:baseCalculo/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:baseCalculo/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns21:baseCalculo/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns21:baseCalculo/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns21:baseCalculo/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns21:baseCalculo>
                                                </xsl:if>
                                                <xsl:if test="ns21:responsableComision">
                                                   <ns21:responsableComision>
                                                      <xsl:value-of select="ns21:responsableComision"/>
                                                   </ns21:responsableComision>
                                                </xsl:if>
                                                <ns21:estadoTCC>
                                                   <xsl:if test="ns21:estadoTCC/ns27:id">
                                                      <ns27:id>
                                                         <xsl:value-of select="ns21:estadoTCC/ns27:id"/>
                                                      </ns27:id>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:estadoTCC/ns27:descripcion">
                                                      <ns27:descripcion>
                                                         <xsl:value-of select="ns21:estadoTCC/ns27:descripcion"/>
                                                      </ns27:descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:estadoTCC/ns27:descripcionCorta">
                                                      <ns27:descripcionCorta>
                                                         <xsl:value-of select="ns21:estadoTCC/ns27:descripcionCorta"/>
                                                      </ns27:descripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:estadoTCC/ns27:estatus">
                                                      <ns27:estatus>
                                                         <xsl:value-of select="ns21:estadoTCC/ns27:estatus"/>
                                                      </ns27:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:estadoTCC/ns27:codigoExterno">
                                                      <ns27:codigoExterno>
                                                         <xsl:value-of select="ns21:estadoTCC/ns27:codigoExterno"/>
                                                      </ns27:codigoExterno>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:estadoTCC/ns27:esSubEstado">
                                                      <ns27:esSubEstado>
                                                         <xsl:value-of select="ns21:estadoTCC/ns27:esSubEstado"/>
                                                      </ns27:esSubEstado>
                                                   </xsl:if>
                                                </ns21:estadoTCC>
                                                <ns21:subEstadoTCC>
                                                   <xsl:if test="ns21:subEstadoTCC/ns27:id">
                                                      <ns27:id>
                                                         <xsl:value-of select="ns21:subEstadoTCC/ns27:id"/>
                                                      </ns27:id>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:subEstadoTCC/ns27:descripcion">
                                                      <ns27:descripcion>
                                                         <xsl:value-of select="ns21:subEstadoTCC/ns27:descripcion"/>
                                                      </ns27:descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:subEstadoTCC/ns27:descripcionCorta">
                                                      <ns27:descripcionCorta>
                                                         <xsl:value-of select="ns21:subEstadoTCC/ns27:descripcionCorta"/>
                                                      </ns27:descripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:subEstadoTCC/ns27:estatus">
                                                      <ns27:estatus>
                                                         <xsl:value-of select="ns21:subEstadoTCC/ns27:estatus"/>
                                                      </ns27:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:subEstadoTCC/ns27:codigoExterno">
                                                      <ns27:codigoExterno>
                                                         <xsl:value-of select="ns21:subEstadoTCC/ns27:codigoExterno"/>
                                                      </ns27:codigoExterno>
                                                   </xsl:if>
                                                   <xsl:if test="ns21:subEstadoTCC/ns27:esSubEstado">
                                                      <ns27:esSubEstado>
                                                         <xsl:value-of select="ns21:subEstadoTCC/ns27:esSubEstado"/>
                                                      </ns27:esSubEstado>
                                                   </xsl:if>
                                                </ns21:subEstadoTCC>
                                                <ns21:fechaRegistro>
                                                   <xsl:value-of select="ns21:fechaRegistro"/>
                                                </ns21:fechaRegistro>
                                                <ns21:estado>
                                                   <xsl:value-of select="ns21:estado"/>
                                                </ns21:estado>
                                                <ns21:comisionEnmendada>
                                                   <xsl:value-of select="ns21:comisionEnmendada"/>
                                                </ns21:comisionEnmendada>
                                                <ns21:fechaEnmienda>
                                                   <xsl:value-of select="ns21:fechaEnmienda"/>
                                                </ns21:fechaEnmienda>
                                                <xsl:if test="ns21:banSugerida">
                                                   <ns21:banSugerida>
                                                      <xsl:value-of select="ns21:banSugerida"/>
                                                   </ns21:banSugerida>
                                                </xsl:if>
                                                <xsl:if test="ns21:numeroCuentaCliente">
                                                   <ns21:numeroCuentaCliente>
                                                      <xsl:value-of select="ns21:numeroCuentaCliente"/>
                                                   </ns21:numeroCuentaCliente>
                                                </xsl:if>
                                                <xsl:if test="ns21:observaciones">
                                                   <ns21:observaciones>
                                                      <xsl:value-of select="ns21:observaciones"/>
                                                   </ns21:observaciones>
                                                </xsl:if>
                                                <xsl:for-each select="ns21:configAtributo">
                                                   <ns21:configAtributo>
                                                      <xsl:if test="ns27:id">
                                                         <ns27:id>
                                                            <xsl:value-of select="ns27:id"/>
                                                         </ns27:id>
                                                      </xsl:if>
                                                      <ns27:columna>
                                                         <xsl:value-of select="ns27:columna"/>
                                                      </ns27:columna>
                                                      <xsl:if test="ns27:ordenamiento">
                                                         <ns27:ordenamiento>
                                                            <xsl:value-of select="ns27:ordenamiento"/>
                                                         </ns27:ordenamiento>
                                                      </xsl:if>
                                                      <xsl:if test="ns27:soloLectura">
                                                         <ns27:soloLectura>
                                                            <xsl:value-of select="ns27:soloLectura"/>
                                                         </ns27:soloLectura>
                                                      </xsl:if>
                                                      <xsl:if test="ns27:esObligatorio">
                                                         <ns27:esObligatorio>
                                                            <xsl:value-of select="ns27:esObligatorio"/>
                                                         </ns27:esObligatorio>
                                                      </xsl:if>
                                                      <xsl:if test="ns27:etiqueta">
                                                         <ns27:etiqueta>
                                                            <xsl:value-of select="ns27:etiqueta"/>
                                                         </ns27:etiqueta>
                                                      </xsl:if>
                                                      <xsl:for-each select="ns27:valor">
                                                         <ns27:valor>
                                                            <xsl:value-of select="."/>
                                                         </ns27:valor>
                                                      </xsl:for-each>
                                                      <xsl:if test="ns27:tipoValor">
                                                         <ns27:tipoValor>
                                                            <xsl:value-of select="ns27:tipoValor"/>
                                                         </ns27:tipoValor>
                                                      </xsl:if>
                                                      <xsl:for-each select="ns27:catalogo">
                                                         <ns27:catalogo>
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
                                                         </ns27:catalogo>
                                                      </xsl:for-each>
                                                   </ns21:configAtributo>
                                                </xsl:for-each>
                                                <xsl:if test="ns21:Accion">
                                                   <ns21:Accion>
                                                      <xsl:value-of select="ns21:Accion"/>
                                                   </ns21:Accion>
                                                </xsl:if>
                                             </ns23:Comision>
                                          </xsl:for-each>
                                          <xsl:for-each select="ns23:Transferencia">
                                             <ns23:Transferencia>
                                                <ns23:idTransferencia>
                                                   <xsl:value-of select="ns23:idTransferencia"/>
                                                </ns23:idTransferencia>
                                                <ns23:idDesembolso>
                                                   <xsl:value-of select="ns23:idDesembolso"/>
                                                </ns23:idDesembolso>
                                                <xsl:if test="ns23:idFacturador">
                                                   <ns23:idFacturador>
                                                      <xsl:value-of select="ns23:idFacturador"/>
                                                   </ns23:idFacturador>
                                                </xsl:if>
                                                <ns23:Monto>
                                                   <ns7:tipo>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns7:tipo>
                                                   <xsl:if test="ns23:Monto/ns7:importe">
                                                      <ns7:importe>
                                                         <xsl:value-of select="ns23:Monto/ns7:importe"/>
                                                      </ns7:importe>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Monto/ns7:moneda">
                                                      <ns7:moneda>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns7:moneda>
                                                   </xsl:if>
                                                </ns23:Monto>
                                                <ns23:FormaTransferencia>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns23:FormaTransferencia>
                                                <xsl:if test="ns23:referenciaMensaje">
                                                   <ns23:referenciaMensaje>
                                                      <xsl:value-of select="ns23:referenciaMensaje"/>
                                                   </ns23:referenciaMensaje>
                                                </xsl:if>
                                                <xsl:if test="ns23:esConsolidada">
                                                   <ns23:esConsolidada>
                                                      <xsl:value-of select="ns23:esConsolidada"/>
                                                   </ns23:esConsolidada>
                                                </xsl:if>
                                                <xsl:if test="ns23:esConsolidacionPadre">
                                                   <ns23:esConsolidacionPadre>
                                                      <xsl:value-of select="ns23:esConsolidacionPadre"/>
                                                   </ns23:esConsolidacionPadre>
                                                </xsl:if>
                                                <xsl:if test="ns23:idConsolidacionPadre">
                                                   <ns23:idConsolidacionPadre>
                                                      <xsl:value-of select="ns23:idConsolidacionPadre"/>
                                                   </ns23:idConsolidacionPadre>
                                                </xsl:if>
                                                <xsl:if test="ns23:bhqTransferencia">
                                                   <ns23:bhqTransferencia>
                                                      <xsl:value-of select="ns23:bhqTransferencia"/>
                                                   </ns23:bhqTransferencia>
                                                </xsl:if>
                                                <xsl:if test="ns23:NumeroReserva">
                                                   <ns23:NumeroReserva>
                                                      <xsl:value-of select="ns23:NumeroReserva"/>
                                                   </ns23:NumeroReserva>
                                                </xsl:if>
                                                <xsl:if test="ns23:idBancoTransferencia">
                                                   <ns23:idBancoTransferencia>
                                                      <xsl:value-of select="ns23:idBancoTransferencia"/>
                                                   </ns23:idBancoTransferencia>
                                                </xsl:if>
                                                <xsl:if test="ns23:numeroCuenta">
                                                   <ns23:numeroCuenta>
                                                      <xsl:value-of select="ns23:numeroCuenta"/>
                                                   </ns23:numeroCuenta>
                                                </xsl:if>
                                                <ns23:nombreCuenta>
                                                   <xsl:value-of select="ns23:nombreCuenta"/>
                                                </ns23:nombreCuenta>
                                                <xsl:if test="ns23:nombreBanco">
                                                   <ns23:nombreBanco>
                                                      <xsl:value-of select="ns23:nombreBanco"/>
                                                   </ns23:nombreBanco>
                                                </xsl:if>
                                                <ns23:idOperacion>
                                                   <xsl:value-of select="ns23:idOperacion"/>
                                                </ns23:idOperacion>
                                                <ns23:tipoMensaje>
                                                   <xsl:value-of select="ns23:tipoMensaje"/>
                                                </ns23:tipoMensaje>
                                                <xsl:if test="ns23:estado">
                                                   <ns23:estado>
                                                      <xsl:value-of select="ns23:estado"/>
                                                   </ns23:estado>
                                                </xsl:if>
                                                <ns23:fechaRegistro>
                                                   <xsl:value-of select="ns23:fechaRegistro"/>
                                                </ns23:fechaRegistro>
                                                <xsl:if test="ns23:Beneficiario">
                                                   <ns23:Beneficiario>
                                                      <xsl:if test="ns23:Beneficiario/ns23:tipoOpcion">
                                                         <ns23:tipoOpcion>
                                                            <xsl:value-of select="ns23:Beneficiario/ns23:tipoOpcion"/>
                                                         </ns23:tipoOpcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Beneficiario/ns23:numeroCta">
                                                         <ns23:numeroCta>
                                                            <xsl:value-of select="ns23:Beneficiario/ns23:numeroCta"/>
                                                         </ns23:numeroCta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Beneficiario/ns23:identificador">
                                                         <ns23:identificador>
                                                            <xsl:value-of select="ns23:Beneficiario/ns23:identificador"/>
                                                         </ns23:identificador>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Beneficiario/ns23:beneficiario">
                                                         <ns23:beneficiario>
                                                            <xsl:value-of select="ns23:Beneficiario/ns23:beneficiario"/>
                                                         </ns23:beneficiario>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Beneficiario/ns23:direccion">
                                                         <ns23:direccion>
                                                            <xsl:value-of select="ns23:Beneficiario/ns23:direccion"/>
                                                         </ns23:direccion>
                                                      </xsl:if>
                                                   </ns23:Beneficiario>
                                                </xsl:if>
                                                <xsl:if test="ns23:Banco">
                                                   <ns23:Banco>
                                                      <xsl:if test="ns23:Banco/ns23:tipoOpcion">
                                                         <ns23:tipoOpcion>
                                                            <xsl:value-of select="ns23:Banco/ns23:tipoOpcion"/>
                                                         </ns23:tipoOpcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Banco/ns23:numeroCta">
                                                         <ns23:numeroCta>
                                                            <xsl:value-of select="ns23:Banco/ns23:numeroCta"/>
                                                         </ns23:numeroCta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Banco/ns23:identificador">
                                                         <ns23:identificador>
                                                            <xsl:value-of select="ns23:Banco/ns23:identificador"/>
                                                         </ns23:identificador>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Banco/ns23:beneficiario">
                                                         <ns23:beneficiario>
                                                            <xsl:value-of select="ns23:Banco/ns23:beneficiario"/>
                                                         </ns23:beneficiario>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Banco/ns23:direccion">
                                                         <ns23:direccion>
                                                            <xsl:value-of select="ns23:Banco/ns23:direccion"/>
                                                         </ns23:direccion>
                                                      </xsl:if>
                                                   </ns23:Banco>
                                                </xsl:if>
                                                <xsl:if test="ns23:Intermediario">
                                                   <ns23:Intermediario>
                                                      <xsl:if test="ns23:Intermediario/ns23:tipoOpcion">
                                                         <ns23:tipoOpcion>
                                                            <xsl:value-of select="ns23:Intermediario/ns23:tipoOpcion"/>
                                                         </ns23:tipoOpcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Intermediario/ns23:numeroCta">
                                                         <ns23:numeroCta>
                                                            <xsl:value-of select="ns23:Intermediario/ns23:numeroCta"/>
                                                         </ns23:numeroCta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Intermediario/ns23:identificador">
                                                         <ns23:identificador>
                                                            <xsl:value-of select="ns23:Intermediario/ns23:identificador"/>
                                                         </ns23:identificador>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Intermediario/ns23:beneficiario">
                                                         <ns23:beneficiario>
                                                            <xsl:value-of select="ns23:Intermediario/ns23:beneficiario"/>
                                                         </ns23:beneficiario>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Intermediario/ns23:direccion">
                                                         <ns23:direccion>
                                                            <xsl:value-of select="ns23:Intermediario/ns23:direccion"/>
                                                         </ns23:direccion>
                                                      </xsl:if>
                                                   </ns23:Intermediario>
                                                </xsl:if>
                                             </ns23:Transferencia>
                                          </xsl:for-each>
                                          <xsl:if test="ns23:TransferenciaFT05">
                                             <ns23:TransferenciaFT05>
                                                <ns23:idTransferenciaFT05>
                                                   <xsl:value-of select="ns23:TransferenciaFT05/ns23:idTransferenciaFT05"/>
                                                </ns23:idTransferenciaFT05>
                                                <ns23:idDesembolso>
                                                   <xsl:value-of select="ns23:TransferenciaFT05/ns23:idDesembolso"/>
                                                </ns23:idDesembolso>
                                                <xsl:if test="ns23:TransferenciaFT05/ns23:idFacturador">
                                                   <ns23:idFacturador>
                                                      <xsl:value-of select="ns23:TransferenciaFT05/ns23:idFacturador"/>
                                                   </ns23:idFacturador>
                                                </xsl:if>
                                                <xsl:if test="ns23:TransferenciaFT05/ns23:FechaEfectiva">
                                                   <ns23:FechaEfectiva>
                                                      <xsl:value-of select="ns23:TransferenciaFT05/ns23:FechaEfectiva"/>
                                                   </ns23:FechaEfectiva>
                                                </xsl:if>
                                             </ns23:TransferenciaFT05>
                                          </xsl:if>
                                          <xsl:for-each select="ns23:TransferenciaRecursos">
                                             <ns23:TransferenciaRecursos>
                                                <ns23:idTransferencia>
                                                   <xsl:value-of select="ns23:idTransferencia"/>
                                                </ns23:idTransferencia>
                                                <ns23:idDesembolso>
                                                   <xsl:value-of select="ns23:idDesembolso"/>
                                                </ns23:idDesembolso>
                                                <xsl:if test="ns23:idFacturador">
                                                   <ns23:idFacturador>
                                                      <xsl:value-of select="ns23:idFacturador"/>
                                                   </ns23:idFacturador>
                                                </xsl:if>
                                                <ns23:idLineaPasiva>
                                                   <xsl:value-of select="ns23:idLineaPasiva"/>
                                                </ns23:idLineaPasiva>
                                                <ns23:Monto>
                                                   <ns7:tipo>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:Id">
                                                         <ns22:Id>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Id"/>
                                                         </ns22:Id>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:Descripcion">
                                                         <ns22:Descripcion>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:Descripcion"/>
                                                         </ns22:Descripcion>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:DescripcionCorta">
                                                         <ns22:DescripcionCorta>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:DescripcionCorta"/>
                                                         </ns22:DescripcionCorta>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:estatus">
                                                         <ns22:estatus>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:estatus"/>
                                                         </ns22:estatus>
                                                      </xsl:if>
                                                      <xsl:if test="ns23:Monto/ns7:tipo/ns22:codigoExterno">
                                                         <ns22:codigoExterno>
                                                            <xsl:value-of select="ns23:Monto/ns7:tipo/ns22:codigoExterno"/>
                                                         </ns22:codigoExterno>
                                                      </xsl:if>
                                                   </ns7:tipo>
                                                   <xsl:if test="ns23:Monto/ns7:importe">
                                                      <ns7:importe>
                                                         <xsl:value-of select="ns23:Monto/ns7:importe"/>
                                                      </ns7:importe>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:Monto/ns7:moneda">
                                                      <ns7:moneda>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:Id">
                                                            <ns22:Id>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Id"/>
                                                            </ns22:Id>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:Descripcion">
                                                            <ns22:Descripcion>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:Descripcion"/>
                                                            </ns22:Descripcion>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:DescripcionCorta">
                                                            <ns22:DescripcionCorta>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                            </ns22:DescripcionCorta>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:estatus">
                                                            <ns22:estatus>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:estatus"/>
                                                            </ns22:estatus>
                                                         </xsl:if>
                                                         <xsl:if test="ns23:Monto/ns7:moneda/ns22:codigoExterno">
                                                            <ns22:codigoExterno>
                                                               <xsl:value-of select="ns23:Monto/ns7:moneda/ns22:codigoExterno"/>
                                                            </ns22:codigoExterno>
                                                         </xsl:if>
                                                      </ns7:moneda>
                                                   </xsl:if>
                                                </ns23:Monto>
                                                <ns23:fecha>
                                                   <xsl:value-of select="ns23:fecha"/>
                                                </ns23:fecha>
                                                <ns23:fechaEfectiva>
                                                   <xsl:value-of select="ns23:fechaEfectiva"/>
                                                </ns23:fechaEfectiva>
                                                <ns23:FormaTransferencia>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:FormaTransferencia/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:FormaTransferencia/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns23:FormaTransferencia>
                                                <xsl:if test="ns23:idBanco">
                                                   <ns23:idBanco>
                                                      <xsl:value-of select="ns23:idBanco"/>
                                                   </ns23:idBanco>
                                                </xsl:if>
                                                <xsl:if test="ns23:nombreBanco">
                                                   <ns23:nombreBanco>
                                                      <xsl:value-of select="ns23:nombreBanco"/>
                                                   </ns23:nombreBanco>
                                                </xsl:if>
                                                <xsl:if test="ns23:numeroCuenta">
                                                   <ns23:numeroCuenta>
                                                      <xsl:value-of select="ns23:numeroCuenta"/>
                                                   </ns23:numeroCuenta>
                                                </xsl:if>
                                                <xsl:if test="ns23:nombreCuenta">
                                                   <ns23:nombreCuenta>
                                                      <xsl:value-of select="ns23:nombreCuenta"/>
                                                   </ns23:nombreCuenta>
                                                </xsl:if>
                                                <xsl:if test="ns23:estatus">
                                                   <ns23:estatus>
                                                      <xsl:value-of select="ns23:estatus"/>
                                                   </ns23:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns23:fechaRegistro">
                                                   <ns23:fechaRegistro>
                                                      <xsl:value-of select="ns23:fechaRegistro"/>
                                                   </ns23:fechaRegistro>
                                                </xsl:if>
                                             </ns23:TransferenciaRecursos>
                                          </xsl:for-each>
                                          <xsl:if test="ns23:HerramientaCE">
                                             <ns23:HerramientaCE>
                                                <ns20:ActividadEconomica>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns20:ActividadEconomica>
                                                <ns20:EjeEstrategico>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns20:EjeEstrategico>
                                                <ns20:AreaFocalizacion>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:Id">
                                                      <ns22:Id>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:Id"/>
                                                      </ns22:Id>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion">
                                                      <ns22:Descripcion>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion"/>
                                                      </ns22:Descripcion>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta">
                                                      <ns22:DescripcionCorta>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta"/>
                                                      </ns22:DescripcionCorta>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus">
                                                      <ns22:estatus>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus"/>
                                                      </ns22:estatus>
                                                   </xsl:if>
                                                   <xsl:if test="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno">
                                                      <ns22:codigoExterno>
                                                         <xsl:value-of select="ns23:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno"/>
                                                      </ns22:codigoExterno>
                                                   </xsl:if>
                                                </ns20:AreaFocalizacion>
                                             </ns23:HerramientaCE>
                                          </xsl:if>
                                          <xsl:if test="ns23:modalidadFinan">
                                             <ns23:modalidadFinan>
                                                <xsl:if test="ns23:modalidadFinan/ns22:Id">
                                                   <ns22:Id>
                                                      <xsl:value-of select="ns23:modalidadFinan/ns22:Id"/>
                                                   </ns22:Id>
                                                </xsl:if>
                                                <xsl:if test="ns23:modalidadFinan/ns22:Descripcion">
                                                   <ns22:Descripcion>
                                                      <xsl:value-of select="ns23:modalidadFinan/ns22:Descripcion"/>
                                                   </ns22:Descripcion>
                                                </xsl:if>
                                                <xsl:if test="ns23:modalidadFinan/ns22:DescripcionCorta">
                                                   <ns22:DescripcionCorta>
                                                      <xsl:value-of select="ns23:modalidadFinan/ns22:DescripcionCorta"/>
                                                   </ns22:DescripcionCorta>
                                                </xsl:if>
                                                <xsl:if test="ns23:modalidadFinan/ns22:estatus">
                                                   <ns22:estatus>
                                                      <xsl:value-of select="ns23:modalidadFinan/ns22:estatus"/>
                                                   </ns22:estatus>
                                                </xsl:if>
                                                <xsl:if test="ns23:modalidadFinan/ns22:codigoExterno">
                                                   <ns22:codigoExterno>
                                                      <xsl:value-of select="ns23:modalidadFinan/ns22:codigoExterno"/>
                                                   </ns22:codigoExterno>
                                                </xsl:if>
                                             </ns23:modalidadFinan>
                                          </xsl:if>
                                       </ns10:ContratoDesembolso>
                                    </xsl:for-each>
                                    <xsl:for-each select="ns10:atributos">
                                       <ns10:atributos>
                                          <ns7:name>
                                             <xsl:value-of select="ns7:name"/>
                                          </ns7:name>
                                          <ns7:value>
                                             <xsl:value-of select="ns7:value"/>
                                          </ns7:value>
                                       </ns10:atributos>
                                    </xsl:for-each>
                                    <xsl:if test="ns10:HerramientaCE">
                                       <ns10:HerramientaCE>
                                          <ns20:ActividadEconomica>
                                             <xsl:if test="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:ActividadEconomica/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns20:ActividadEconomica>
                                          <ns20:EjeEstrategico>
                                             <xsl:if test="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:EjeEstrategico/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns20:EjeEstrategico>
                                          <ns20:AreaFocalizacion>
                                             <xsl:if test="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns10:HerramientaCE/ns20:AreaFocalizacion/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns20:AreaFocalizacion>
                                       </ns10:HerramientaCE>
                                    </xsl:if>
                                 </ns25:LineaCredito>
                              </xsl:for-each>
                           </ns5:contrato>
                        </xsl:for-each>
                     </ns24:operacion>
                     <ns24:cliente>
                        <ns26:idCliente>
                           <xsl:value-of select="ns24:cliente/ns26:idCliente"/>
                        </ns26:idCliente>
                        <xsl:if test="ns24:cliente/ns26:idFacturador">
                           <ns26:idFacturador>
                              <xsl:value-of select="ns24:cliente/ns26:idFacturador"/>
                           </ns26:idFacturador>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:razonSocial">
                           <ns26:razonSocial>
                              <xsl:value-of select="ns24:cliente/ns26:razonSocial"/>
                           </ns26:razonSocial>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:abreviatura">
                           <ns26:abreviatura>
                              <xsl:value-of select="ns24:cliente/ns26:abreviatura"/>
                           </ns26:abreviatura>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:tipoPersona">
                           <ns26:tipoPersona>
                              <xsl:if test="ns24:cliente/ns26:tipoPersona/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoPersona/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoPersona/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoPersona/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoPersona/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoPersona/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoPersona/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoPersona/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoPersona/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoPersona/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns26:tipoPersona>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:tipoCliente">
                           <ns26:tipoCliente>
                              <xsl:if test="ns24:cliente/ns26:tipoCliente/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoCliente/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoCliente/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoCliente/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoCliente/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoCliente/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoCliente/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoCliente/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoCliente/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoCliente/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns26:tipoCliente>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:sector">
                           <ns26:sector>
                              <xsl:if test="ns24:cliente/ns26:sector/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:cliente/ns26:sector/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:sector/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:cliente/ns26:sector/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:sector/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:cliente/ns26:sector/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:sector/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:cliente/ns26:sector/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:sector/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:cliente/ns26:sector/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns26:sector>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:tipoInstitucion">
                           <ns26:tipoInstitucion>
                              <xsl:if test="ns24:cliente/ns26:tipoInstitucion/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoInstitucion/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoInstitucion/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoInstitucion/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoInstitucion/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoInstitucion/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoInstitucion/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoInstitucion/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:tipoInstitucion/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:cliente/ns26:tipoInstitucion/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns26:tipoInstitucion>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:pais">
                           <ns26:pais>
                              <xsl:if test="ns24:cliente/ns26:pais/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:cliente/ns26:pais/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:pais/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:cliente/ns26:pais/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:pais/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:cliente/ns26:pais/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:pais/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:cliente/ns26:pais/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:pais/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:cliente/ns26:pais/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns26:pais>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:grupoEconomico">
                           <ns26:grupoEconomico>
                              <xsl:if test="ns24:cliente/ns26:grupoEconomico/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:cliente/ns26:grupoEconomico/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:grupoEconomico/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:cliente/ns26:grupoEconomico/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:grupoEconomico/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:cliente/ns26:grupoEconomico/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:grupoEconomico/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:cliente/ns26:grupoEconomico/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:grupoEconomico/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:cliente/ns26:grupoEconomico/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns26:grupoEconomico>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:tipoIdentificacion">
                           <ns26:tipoIdentificacion>
                              <xsl:value-of select="ns24:cliente/ns26:tipoIdentificacion"/>
                           </ns26:tipoIdentificacion>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:numeroIdentificacion">
                           <ns26:numeroIdentificacion>
                              <xsl:value-of select="ns24:cliente/ns26:numeroIdentificacion"/>
                           </ns26:numeroIdentificacion>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:direccion">
                           <ns26:direccion>
                              <xsl:value-of select="ns24:cliente/ns26:direccion"/>
                           </ns26:direccion>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:telefono">
                           <ns26:telefono>
                              <xsl:value-of select="ns24:cliente/ns26:telefono"/>
                           </ns26:telefono>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:fax">
                           <ns26:fax>
                              <xsl:value-of select="ns24:cliente/ns26:fax"/>
                           </ns26:fax>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:email">
                           <ns26:email>
                              <xsl:value-of select="ns24:cliente/ns26:email"/>
                           </ns26:email>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:ciudad">
                           <ns26:ciudad>
                              <xsl:value-of select="ns24:cliente/ns26:ciudad"/>
                           </ns26:ciudad>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:oficina">
                           <ns26:oficina>
                              <xsl:if test="ns24:cliente/ns26:oficina/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:cliente/ns26:oficina/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:oficina/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:cliente/ns26:oficina/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:oficina/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:cliente/ns26:oficina/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:oficina/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:cliente/ns26:oficina/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:cliente/ns26:oficina/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:cliente/ns26:oficina/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns26:oficina>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:grupoEmpresarial">
                           <ns26:grupoEmpresarial>
                              <xsl:value-of select="ns24:cliente/ns26:grupoEmpresarial"/>
                           </ns26:grupoEmpresarial>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:fechaRegistro">
                           <ns26:fechaRegistro>
                              <xsl:value-of select="ns24:cliente/ns26:fechaRegistro"/>
                           </ns26:fechaRegistro>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:fechaAprobacion">
                           <ns26:fechaAprobacion>
                              <xsl:value-of select="ns24:cliente/ns26:fechaAprobacion"/>
                           </ns26:fechaAprobacion>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:ejecutivo">
                           <ns26:ejecutivo>
                              <xsl:value-of select="ns24:cliente/ns26:ejecutivo"/>
                           </ns26:ejecutivo>
                        </xsl:if>
                        <xsl:if test="ns24:cliente/ns26:responsableCliente">
                           <ns26:responsableCliente>
                              <xsl:value-of select="ns24:cliente/ns26:responsableCliente"/>
                           </ns26:responsableCliente>
                        </xsl:if>
                     </ns24:cliente>
                     <ns24:normativaAplicada>
                        <xsl:if test="ns24:normativaAplicada/ns22:Id">
                           <ns22:Id>
                              <xsl:value-of select="ns24:normativaAplicada/ns22:Id"/>
                           </ns22:Id>
                        </xsl:if>
                        <xsl:if test="ns24:normativaAplicada/ns22:Descripcion">
                           <ns22:Descripcion>
                              <xsl:value-of select="ns24:normativaAplicada/ns22:Descripcion"/>
                           </ns22:Descripcion>
                        </xsl:if>
                        <xsl:if test="ns24:normativaAplicada/ns22:DescripcionCorta">
                           <ns22:DescripcionCorta>
                              <xsl:value-of select="ns24:normativaAplicada/ns22:DescripcionCorta"/>
                           </ns22:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns24:normativaAplicada/ns22:estatus">
                           <ns22:estatus>
                              <xsl:value-of select="ns24:normativaAplicada/ns22:estatus"/>
                           </ns22:estatus>
                        </xsl:if>
                        <xsl:if test="ns24:normativaAplicada/ns22:codigoExterno">
                           <ns22:codigoExterno>
                              <xsl:value-of select="ns24:normativaAplicada/ns22:codigoExterno"/>
                           </ns22:codigoExterno>
                        </xsl:if>
                     </ns24:normativaAplicada>
                     <xsl:if test="ns24:normativaEspecifica">
                        <ns24:normativaEspecifica>
                           <xsl:value-of select="ns24:normativaEspecifica"/>
                        </ns24:normativaEspecifica>
                     </xsl:if>
                     <xsl:if test="ns24:adquisicionPrevia">
                        <ns24:adquisicionPrevia>
                           <xsl:value-of select="ns24:adquisicionPrevia"/>
                        </ns24:adquisicionPrevia>
                     </xsl:if>
                     <xsl:if test="ns24:numero">
                        <ns24:numero>
                           <xsl:value-of select="ns24:numero"/>
                        </ns24:numero>
                     </xsl:if>
                     <ns24:tipoAdquisicion>
                        <xsl:if test="ns24:tipoAdquisicion/ns22:Id">
                           <ns22:Id>
                              <xsl:value-of select="ns24:tipoAdquisicion/ns22:Id"/>
                           </ns22:Id>
                        </xsl:if>
                        <xsl:if test="ns24:tipoAdquisicion/ns22:Descripcion">
                           <ns22:Descripcion>
                              <xsl:value-of select="ns24:tipoAdquisicion/ns22:Descripcion"/>
                           </ns22:Descripcion>
                        </xsl:if>
                        <xsl:if test="ns24:tipoAdquisicion/ns22:DescripcionCorta">
                           <ns22:DescripcionCorta>
                              <xsl:value-of select="ns24:tipoAdquisicion/ns22:DescripcionCorta"/>
                           </ns22:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="ns24:tipoAdquisicion/ns22:estatus">
                           <ns22:estatus>
                              <xsl:value-of select="ns24:tipoAdquisicion/ns22:estatus"/>
                           </ns22:estatus>
                        </xsl:if>
                        <xsl:if test="ns24:tipoAdquisicion/ns22:codigoExterno">
                           <ns22:codigoExterno>
                              <xsl:value-of select="ns24:tipoAdquisicion/ns22:codigoExterno"/>
                           </ns22:codigoExterno>
                        </xsl:if>
                     </ns24:tipoAdquisicion>
                     <xsl:if test="ns24:montoPresupuestado">
                        <ns24:montoPresupuestado>
                           <ns7:tipo>
                              <xsl:if test="ns24:montoPresupuestado/ns7:tipo/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:montoPresupuestado/ns7:tipo/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:montoPresupuestado/ns7:tipo/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:montoPresupuestado/ns7:tipo/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:montoPresupuestado/ns7:tipo/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:montoPresupuestado/ns7:tipo/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:montoPresupuestado/ns7:tipo/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:montoPresupuestado/ns7:tipo/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:montoPresupuestado/ns7:tipo/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:montoPresupuestado/ns7:tipo/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns7:tipo>
                           <xsl:if test="ns24:montoPresupuestado/ns7:importe">
                              <ns7:importe>
                                 <xsl:value-of select="ns24:montoPresupuestado/ns7:importe"/>
                              </ns7:importe>
                           </xsl:if>
                           <xsl:if test="ns24:montoPresupuestado/ns7:moneda">
                              <ns7:moneda>
                                 <xsl:if test="ns24:montoPresupuestado/ns7:moneda/ns22:Id">
                                    <ns22:Id>
                                       <xsl:value-of select="ns24:montoPresupuestado/ns7:moneda/ns22:Id"/>
                                    </ns22:Id>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoPresupuestado/ns7:moneda/ns22:Descripcion">
                                    <ns22:Descripcion>
                                       <xsl:value-of select="ns24:montoPresupuestado/ns7:moneda/ns22:Descripcion"/>
                                    </ns22:Descripcion>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoPresupuestado/ns7:moneda/ns22:DescripcionCorta">
                                    <ns22:DescripcionCorta>
                                       <xsl:value-of select="ns24:montoPresupuestado/ns7:moneda/ns22:DescripcionCorta"/>
                                    </ns22:DescripcionCorta>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoPresupuestado/ns7:moneda/ns22:estatus">
                                    <ns22:estatus>
                                       <xsl:value-of select="ns24:montoPresupuestado/ns7:moneda/ns22:estatus"/>
                                    </ns22:estatus>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoPresupuestado/ns7:moneda/ns22:codigoExterno">
                                    <ns22:codigoExterno>
                                       <xsl:value-of select="ns24:montoPresupuestado/ns7:moneda/ns22:codigoExterno"/>
                                    </ns22:codigoExterno>
                                 </xsl:if>
                              </ns7:moneda>
                           </xsl:if>
                        </ns24:montoPresupuestado>
                     </xsl:if>
                     <xsl:if test="ns24:montoContratado">
                        <ns24:montoContratado>
                           <ns7:tipo>
                              <xsl:if test="ns24:montoContratado/ns7:tipo/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:montoContratado/ns7:tipo/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:montoContratado/ns7:tipo/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:montoContratado/ns7:tipo/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:montoContratado/ns7:tipo/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:montoContratado/ns7:tipo/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:montoContratado/ns7:tipo/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:montoContratado/ns7:tipo/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:montoContratado/ns7:tipo/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:montoContratado/ns7:tipo/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns7:tipo>
                           <xsl:if test="ns24:montoContratado/ns7:importe">
                              <ns7:importe>
                                 <xsl:value-of select="ns24:montoContratado/ns7:importe"/>
                              </ns7:importe>
                           </xsl:if>
                           <xsl:if test="ns24:montoContratado/ns7:moneda">
                              <ns7:moneda>
                                 <xsl:if test="ns24:montoContratado/ns7:moneda/ns22:Id">
                                    <ns22:Id>
                                       <xsl:value-of select="ns24:montoContratado/ns7:moneda/ns22:Id"/>
                                    </ns22:Id>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoContratado/ns7:moneda/ns22:Descripcion">
                                    <ns22:Descripcion>
                                       <xsl:value-of select="ns24:montoContratado/ns7:moneda/ns22:Descripcion"/>
                                    </ns22:Descripcion>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoContratado/ns7:moneda/ns22:DescripcionCorta">
                                    <ns22:DescripcionCorta>
                                       <xsl:value-of select="ns24:montoContratado/ns7:moneda/ns22:DescripcionCorta"/>
                                    </ns22:DescripcionCorta>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoContratado/ns7:moneda/ns22:estatus">
                                    <ns22:estatus>
                                       <xsl:value-of select="ns24:montoContratado/ns7:moneda/ns22:estatus"/>
                                    </ns22:estatus>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoContratado/ns7:moneda/ns22:codigoExterno">
                                    <ns22:codigoExterno>
                                       <xsl:value-of select="ns24:montoContratado/ns7:moneda/ns22:codigoExterno"/>
                                    </ns22:codigoExterno>
                                 </xsl:if>
                              </ns7:moneda>
                           </xsl:if>
                        </ns24:montoContratado>
                     </xsl:if>
                     <xsl:if test="ns24:montoAsociado">
                        <ns24:montoAsociado>
                           <ns7:tipo>
                              <xsl:if test="ns24:montoAsociado/ns7:tipo/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:montoAsociado/ns7:tipo/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:montoAsociado/ns7:tipo/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:montoAsociado/ns7:tipo/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:montoAsociado/ns7:tipo/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:montoAsociado/ns7:tipo/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:montoAsociado/ns7:tipo/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:montoAsociado/ns7:tipo/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:montoAsociado/ns7:tipo/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:montoAsociado/ns7:tipo/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns7:tipo>
                           <xsl:if test="ns24:montoAsociado/ns7:importe">
                              <ns7:importe>
                                 <xsl:value-of select="ns24:montoAsociado/ns7:importe"/>
                              </ns7:importe>
                           </xsl:if>
                           <xsl:if test="ns24:montoAsociado/ns7:moneda">
                              <ns7:moneda>
                                 <xsl:if test="ns24:montoAsociado/ns7:moneda/ns22:Id">
                                    <ns22:Id>
                                       <xsl:value-of select="ns24:montoAsociado/ns7:moneda/ns22:Id"/>
                                    </ns22:Id>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoAsociado/ns7:moneda/ns22:Descripcion">
                                    <ns22:Descripcion>
                                       <xsl:value-of select="ns24:montoAsociado/ns7:moneda/ns22:Descripcion"/>
                                    </ns22:Descripcion>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoAsociado/ns7:moneda/ns22:DescripcionCorta">
                                    <ns22:DescripcionCorta>
                                       <xsl:value-of select="ns24:montoAsociado/ns7:moneda/ns22:DescripcionCorta"/>
                                    </ns22:DescripcionCorta>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoAsociado/ns7:moneda/ns22:estatus">
                                    <ns22:estatus>
                                       <xsl:value-of select="ns24:montoAsociado/ns7:moneda/ns22:estatus"/>
                                    </ns22:estatus>
                                 </xsl:if>
                                 <xsl:if test="ns24:montoAsociado/ns7:moneda/ns22:codigoExterno">
                                    <ns22:codigoExterno>
                                       <xsl:value-of select="ns24:montoAsociado/ns7:moneda/ns22:codigoExterno"/>
                                    </ns22:codigoExterno>
                                 </xsl:if>
                              </ns7:moneda>
                           </xsl:if>
                        </ns24:montoAsociado>
                     </xsl:if>
                     <xsl:if test="ns24:tipoAlcance">
                        <ns24:tipoAlcance>
                           <xsl:if test="ns24:tipoAlcance/ns22:Id">
                              <ns22:Id>
                                 <xsl:value-of select="ns24:tipoAlcance/ns22:Id"/>
                              </ns22:Id>
                           </xsl:if>
                           <xsl:if test="ns24:tipoAlcance/ns22:Descripcion">
                              <ns22:Descripcion>
                                 <xsl:value-of select="ns24:tipoAlcance/ns22:Descripcion"/>
                              </ns22:Descripcion>
                           </xsl:if>
                           <xsl:if test="ns24:tipoAlcance/ns22:DescripcionCorta">
                              <ns22:DescripcionCorta>
                                 <xsl:value-of select="ns24:tipoAlcance/ns22:DescripcionCorta"/>
                              </ns22:DescripcionCorta>
                           </xsl:if>
                           <xsl:if test="ns24:tipoAlcance/ns22:estatus">
                              <ns22:estatus>
                                 <xsl:value-of select="ns24:tipoAlcance/ns22:estatus"/>
                              </ns22:estatus>
                           </xsl:if>
                           <xsl:if test="ns24:tipoAlcance/ns22:codigoExterno">
                              <ns22:codigoExterno>
                                 <xsl:value-of select="ns24:tipoAlcance/ns22:codigoExterno"/>
                              </ns22:codigoExterno>
                           </xsl:if>
                        </ns24:tipoAlcance>
                     </xsl:if>
                     <xsl:if test="ns24:fechaContrato">
                        <ns24:fechaContrato>
                           <xsl:value-of select="ns24:fechaContrato"/>
                        </ns24:fechaContrato>
                     </xsl:if>
                     <xsl:if test="ns24:tipoModalidad">
                        <ns24:tipoModalidad>
                           <xsl:if test="ns24:tipoModalidad/ns22:Id">
                              <ns22:Id>
                                 <xsl:value-of select="ns24:tipoModalidad/ns22:Id"/>
                              </ns22:Id>
                           </xsl:if>
                           <xsl:if test="ns24:tipoModalidad/ns22:Descripcion">
                              <ns22:Descripcion>
                                 <xsl:value-of select="ns24:tipoModalidad/ns22:Descripcion"/>
                              </ns22:Descripcion>
                           </xsl:if>
                           <xsl:if test="ns24:tipoModalidad/ns22:DescripcionCorta">
                              <ns22:DescripcionCorta>
                                 <xsl:value-of select="ns24:tipoModalidad/ns22:DescripcionCorta"/>
                              </ns22:DescripcionCorta>
                           </xsl:if>
                           <xsl:if test="ns24:tipoModalidad/ns22:estatus">
                              <ns22:estatus>
                                 <xsl:value-of select="ns24:tipoModalidad/ns22:estatus"/>
                              </ns22:estatus>
                           </xsl:if>
                           <xsl:if test="ns24:tipoModalidad/ns22:codigoExterno">
                              <ns22:codigoExterno>
                                 <xsl:value-of select="ns24:tipoModalidad/ns22:codigoExterno"/>
                              </ns22:codigoExterno>
                           </xsl:if>
                        </ns24:tipoModalidad>
                     </xsl:if>
                     <xsl:if test="ns24:Adjudicatario">
                        <ns24:Adjudicatario>
                           <ns24:idConcursante>
                              <xsl:value-of select="ns24:Adjudicatario/ns24:idConcursante"/>
                           </ns24:idConcursante>
                           <ns24:idNoObjecion>
                              <xsl:value-of select="ns24:Adjudicatario/ns24:idNoObjecion"/>
                           </ns24:idNoObjecion>
                           <ns24:tipoPerfil>
                              <xsl:if test="ns24:Adjudicatario/ns24:tipoPerfil/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:tipoPerfil/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:Adjudicatario/ns24:tipoPerfil/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:tipoPerfil/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:Adjudicatario/ns24:tipoPerfil/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:tipoPerfil/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:Adjudicatario/ns24:tipoPerfil/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:tipoPerfil/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:Adjudicatario/ns24:tipoPerfil/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:tipoPerfil/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns24:tipoPerfil>
                           <ns24:nombre>
                              <xsl:value-of select="ns24:Adjudicatario/ns24:nombre"/>
                           </ns24:nombre>
                           <ns24:nacionalidad>
                              <xsl:if test="ns24:Adjudicatario/ns24:nacionalidad/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:nacionalidad/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:Adjudicatario/ns24:nacionalidad/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:nacionalidad/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:Adjudicatario/ns24:nacionalidad/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:nacionalidad/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:Adjudicatario/ns24:nacionalidad/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:nacionalidad/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:Adjudicatario/ns24:nacionalidad/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:Adjudicatario/ns24:nacionalidad/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                           </ns24:nacionalidad>
                           <xsl:if test="ns24:Adjudicatario/ns24:monto">
                              <ns24:monto>
                                 <ns7:tipo>
                                    <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:tipo/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns7:tipo>
                                 <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:importe">
                                    <ns7:importe>
                                       <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:importe"/>
                                    </ns7:importe>
                                 </xsl:if>
                                 <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:moneda">
                                    <ns7:moneda>
                                       <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:Id">
                                          <ns22:Id>
                                             <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:Id"/>
                                          </ns22:Id>
                                       </xsl:if>
                                       <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:Descripcion">
                                          <ns22:Descripcion>
                                             <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:Descripcion"/>
                                          </ns22:Descripcion>
                                       </xsl:if>
                                       <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:DescripcionCorta">
                                          <ns22:DescripcionCorta>
                                             <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:DescripcionCorta"/>
                                          </ns22:DescripcionCorta>
                                       </xsl:if>
                                       <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:estatus">
                                          <ns22:estatus>
                                             <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:estatus"/>
                                          </ns22:estatus>
                                       </xsl:if>
                                       <xsl:if test="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:codigoExterno">
                                          <ns22:codigoExterno>
                                             <xsl:value-of select="ns24:Adjudicatario/ns24:monto/ns7:moneda/ns22:codigoExterno"/>
                                          </ns22:codigoExterno>
                                       </xsl:if>
                                    </ns7:moneda>
                                 </xsl:if>
                              </ns24:monto>
                           </xsl:if>
                           <xsl:if test="ns24:Adjudicatario/ns24:instanciaAprobacion">
                              <ns24:instanciaAprobacion>
                                 <xsl:if test="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:Id">
                                    <ns22:Id>
                                       <xsl:value-of select="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:Id"/>
                                    </ns22:Id>
                                 </xsl:if>
                                 <xsl:if test="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:Descripcion">
                                    <ns22:Descripcion>
                                       <xsl:value-of select="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:Descripcion"/>
                                    </ns22:Descripcion>
                                 </xsl:if>
                                 <xsl:if test="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:DescripcionCorta">
                                    <ns22:DescripcionCorta>
                                       <xsl:value-of select="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:DescripcionCorta"/>
                                    </ns22:DescripcionCorta>
                                 </xsl:if>
                                 <xsl:if test="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:estatus">
                                    <ns22:estatus>
                                       <xsl:value-of select="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:estatus"/>
                                    </ns22:estatus>
                                 </xsl:if>
                                 <xsl:if test="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:codigoExterno">
                                    <ns22:codigoExterno>
                                       <xsl:value-of select="ns24:Adjudicatario/ns24:instanciaAprobacion/ns22:codigoExterno"/>
                                    </ns22:codigoExterno>
                                 </xsl:if>
                              </ns24:instanciaAprobacion>
                           </xsl:if>
                           <ns24:fechaRegistro>
                              <xsl:value-of select="ns24:Adjudicatario/ns24:fechaRegistro"/>
                           </ns24:fechaRegistro>
                        </ns24:Adjudicatario>
                     </xsl:if>
                     <xsl:if test="ns24:titulo">
                        <ns24:titulo>
                           <xsl:value-of select="ns24:titulo"/>
                        </ns24:titulo>
                     </xsl:if>
                     <xsl:if test="ns24:objetivo">
                        <ns24:objetivo>
                           <xsl:value-of select="ns24:objetivo"/>
                        </ns24:objetivo>
                     </xsl:if>
                     <xsl:if test="ns24:instanciaProceso">
                        <ns24:instanciaProceso>
                           <xsl:value-of select="ns24:instanciaProceso"/>
                        </ns24:instanciaProceso>
                     </xsl:if>
                     <ns24:estado>
                        <xsl:value-of select="ns24:estado"/>
                     </ns24:estado>
                     <ns24:fechaRegistro>
                        <xsl:value-of select="ns24:fechaRegistro"/>
                     </ns24:fechaRegistro>
                     <xsl:for-each select="ns24:noObjecion">
                        <ns24:noObjecion>
                           <xsl:if test="ns24:idNoObjecion">
                              <ns24:idNoObjecion>
                                 <xsl:value-of select="ns24:idNoObjecion"/>
                              </ns24:idNoObjecion>
                           </xsl:if>
                           <ns24:idAdquisicion>
                              <xsl:value-of select="ns24:idAdquisicion"/>
                           </ns24:idAdquisicion>
                           <ns24:tipoNoObjecion>
                              <xsl:if test="ns24:tipoNoObjecion/ns22:Id">
                                 <ns22:Id>
                                    <xsl:value-of select="ns24:tipoNoObjecion/ns22:Id"/>
                                 </ns22:Id>
                              </xsl:if>
                              <xsl:if test="ns24:tipoNoObjecion/ns22:Descripcion">
                                 <ns22:Descripcion>
                                    <xsl:value-of select="ns24:tipoNoObjecion/ns22:Descripcion"/>
                                 </ns22:Descripcion>
                              </xsl:if>
                              <xsl:if test="ns24:tipoNoObjecion/ns22:DescripcionCorta">
                                 <ns22:DescripcionCorta>
                                    <xsl:value-of select="ns24:tipoNoObjecion/ns22:DescripcionCorta"/>
                                 </ns22:DescripcionCorta>
                              </xsl:if>
                              <xsl:if test="ns24:tipoNoObjecion/ns22:estatus">
                                 <ns22:estatus>
                                    <xsl:value-of select="ns24:tipoNoObjecion/ns22:estatus"/>
                                 </ns22:estatus>
                              </xsl:if>
                              <xsl:if test="ns24:tipoNoObjecion/ns22:codigoExterno">
                                 <ns22:codigoExterno>
                                    <xsl:value-of select="ns24:tipoNoObjecion/ns22:codigoExterno"/>
                                 </ns22:codigoExterno>
                              </xsl:if>
                              <xsl:for-each select="ns24:tipoNoObjecion/ns24:NoObjecionRelacion">
                                 <ns24:NoObjecionRelacion>
                                    <xsl:value-of select="."/>
                                 </ns24:NoObjecionRelacion>
                              </xsl:for-each>
                           </ns24:tipoNoObjecion>
                           <xsl:if test="ns24:fechaPublicacion">
                              <ns24:fechaPublicacion>
                                 <xsl:value-of select="ns24:fechaPublicacion"/>
                              </ns24:fechaPublicacion>
                           </xsl:if>
                           <xsl:if test="ns24:fechaInicioDoctoBase">
                              <ns24:fechaInicioDoctoBase>
                                 <xsl:value-of select="ns24:fechaInicioDoctoBase"/>
                              </ns24:fechaInicioDoctoBase>
                           </xsl:if>
                           <xsl:if test="ns24:fechaFinDoctoBase">
                              <ns24:fechaFinDoctoBase>
                                 <xsl:value-of select="ns24:fechaFinDoctoBase"/>
                              </ns24:fechaFinDoctoBase>
                           </xsl:if>
                           <xsl:if test="ns24:fechaRecepcionPropuesta">
                              <ns24:fechaRecepcionPropuesta>
                                 <xsl:value-of select="ns24:fechaRecepcionPropuesta"/>
                              </ns24:fechaRecepcionPropuesta>
                           </xsl:if>
                           <xsl:if test="ns24:lugarObtenerDoctoBase">
                              <ns24:lugarObtenerDoctoBase>
                                 <xsl:value-of select="ns24:lugarObtenerDoctoBase"/>
                              </ns24:lugarObtenerDoctoBase>
                           </xsl:if>
                           <xsl:if test="ns24:correoInfoAdicional">
                              <ns24:correoInfoAdicional>
                                 <xsl:value-of select="ns24:correoInfoAdicional"/>
                              </ns24:correoInfoAdicional>
                           </xsl:if>
                           <xsl:if test="ns24:direccionPresentacionPropuesta">
                              <ns24:direccionPresentacionPropuesta>
                                 <xsl:value-of select="ns24:direccionPresentacionPropuesta"/>
                              </ns24:direccionPresentacionPropuesta>
                           </xsl:if>
                           <xsl:if test="ns24:resultadoProceso">
                              <ns24:resultadoProceso>
                                 <xsl:if test="ns24:resultadoProceso/ns22:Id">
                                    <ns22:Id>
                                       <xsl:value-of select="ns24:resultadoProceso/ns22:Id"/>
                                    </ns22:Id>
                                 </xsl:if>
                                 <xsl:if test="ns24:resultadoProceso/ns22:Descripcion">
                                    <ns22:Descripcion>
                                       <xsl:value-of select="ns24:resultadoProceso/ns22:Descripcion"/>
                                    </ns22:Descripcion>
                                 </xsl:if>
                                 <xsl:if test="ns24:resultadoProceso/ns22:DescripcionCorta">
                                    <ns22:DescripcionCorta>
                                       <xsl:value-of select="ns24:resultadoProceso/ns22:DescripcionCorta"/>
                                    </ns22:DescripcionCorta>
                                 </xsl:if>
                                 <xsl:if test="ns24:resultadoProceso/ns22:estatus">
                                    <ns22:estatus>
                                       <xsl:value-of select="ns24:resultadoProceso/ns22:estatus"/>
                                    </ns22:estatus>
                                 </xsl:if>
                                 <xsl:if test="ns24:resultadoProceso/ns22:codigoExterno">
                                    <ns22:codigoExterno>
                                       <xsl:value-of select="ns24:resultadoProceso/ns22:codigoExterno"/>
                                    </ns22:codigoExterno>
                                 </xsl:if>
                              </ns24:resultadoProceso>
                           </xsl:if>
                           <xsl:if test="ns24:revisadoPublicacion">
                              <ns24:revisadoPublicacion>
                                 <xsl:value-of select="ns24:revisadoPublicacion"/>
                              </ns24:revisadoPublicacion>
                           </xsl:if>
                           <xsl:if test="ns24:publicado">
                              <ns24:publicado>
                                 <xsl:value-of select="ns24:publicado"/>
                              </ns24:publicado>
                           </xsl:if>
                           <xsl:if test="ns24:numeroCorrespondencia">
                              <ns24:numeroCorrespondencia>
                                 <xsl:value-of select="ns24:numeroCorrespondencia"/>
                              </ns24:numeroCorrespondencia>
                           </xsl:if>
                           <xsl:if test="ns24:otorgoNoObjecion">
                              <ns24:otorgoNoObjecion>
                                 <xsl:value-of select="ns24:otorgoNoObjecion"/>
                              </ns24:otorgoNoObjecion>
                           </xsl:if>
                           <xsl:if test="ns24:fechaFirmaDocto">
                              <ns24:fechaFirmaDocto>
                                 <xsl:value-of select="ns24:fechaFirmaDocto"/>
                              </ns24:fechaFirmaDocto>
                           </xsl:if>
                           <ns24:estado>
                              <xsl:value-of select="ns24:estado"/>
                           </ns24:estado>
                           <ns24:fechaRegistro>
                              <xsl:value-of select="ns24:fechaRegistro"/>
                           </ns24:fechaRegistro>
                           <xsl:if test="ns24:enProcesoBpm">
                              <ns24:enProcesoBpm>
                                 <xsl:value-of select="ns24:enProcesoBpm"/>
                              </ns24:enProcesoBpm>
                           </xsl:if>
                           <xsl:for-each select="ns24:concursante">
                              <ns24:concursante>
                                 <ns24:idConcursante>
                                    <xsl:value-of select="ns24:idConcursante"/>
                                 </ns24:idConcursante>
                                 <ns24:idNoObjecion>
                                    <xsl:value-of select="ns24:idNoObjecion"/>
                                 </ns24:idNoObjecion>
                                 <ns24:tipoPerfil>
                                    <xsl:if test="ns24:tipoPerfil/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:tipoPerfil/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:tipoPerfil/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:tipoPerfil/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:tipoPerfil/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:tipoPerfil/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:tipoPerfil/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:tipoPerfil/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:tipoPerfil/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:tipoPerfil/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns24:tipoPerfil>
                                 <ns24:nombre>
                                    <xsl:value-of select="ns24:nombre"/>
                                 </ns24:nombre>
                                 <ns24:nacionalidad>
                                    <xsl:if test="ns24:nacionalidad/ns22:Id">
                                       <ns22:Id>
                                          <xsl:value-of select="ns24:nacionalidad/ns22:Id"/>
                                       </ns22:Id>
                                    </xsl:if>
                                    <xsl:if test="ns24:nacionalidad/ns22:Descripcion">
                                       <ns22:Descripcion>
                                          <xsl:value-of select="ns24:nacionalidad/ns22:Descripcion"/>
                                       </ns22:Descripcion>
                                    </xsl:if>
                                    <xsl:if test="ns24:nacionalidad/ns22:DescripcionCorta">
                                       <ns22:DescripcionCorta>
                                          <xsl:value-of select="ns24:nacionalidad/ns22:DescripcionCorta"/>
                                       </ns22:DescripcionCorta>
                                    </xsl:if>
                                    <xsl:if test="ns24:nacionalidad/ns22:estatus">
                                       <ns22:estatus>
                                          <xsl:value-of select="ns24:nacionalidad/ns22:estatus"/>
                                       </ns22:estatus>
                                    </xsl:if>
                                    <xsl:if test="ns24:nacionalidad/ns22:codigoExterno">
                                       <ns22:codigoExterno>
                                          <xsl:value-of select="ns24:nacionalidad/ns22:codigoExterno"/>
                                       </ns22:codigoExterno>
                                    </xsl:if>
                                 </ns24:nacionalidad>
                                 <xsl:if test="ns24:monto">
                                    <ns24:monto>
                                       <ns7:tipo>
                                          <xsl:if test="ns24:monto/ns7:tipo/ns22:Id">
                                             <ns22:Id>
                                                <xsl:value-of select="ns24:monto/ns7:tipo/ns22:Id"/>
                                             </ns22:Id>
                                          </xsl:if>
                                          <xsl:if test="ns24:monto/ns7:tipo/ns22:Descripcion">
                                             <ns22:Descripcion>
                                                <xsl:value-of select="ns24:monto/ns7:tipo/ns22:Descripcion"/>
                                             </ns22:Descripcion>
                                          </xsl:if>
                                          <xsl:if test="ns24:monto/ns7:tipo/ns22:DescripcionCorta">
                                             <ns22:DescripcionCorta>
                                                <xsl:value-of select="ns24:monto/ns7:tipo/ns22:DescripcionCorta"/>
                                             </ns22:DescripcionCorta>
                                          </xsl:if>
                                          <xsl:if test="ns24:monto/ns7:tipo/ns22:estatus">
                                             <ns22:estatus>
                                                <xsl:value-of select="ns24:monto/ns7:tipo/ns22:estatus"/>
                                             </ns22:estatus>
                                          </xsl:if>
                                          <xsl:if test="ns24:monto/ns7:tipo/ns22:codigoExterno">
                                             <ns22:codigoExterno>
                                                <xsl:value-of select="ns24:monto/ns7:tipo/ns22:codigoExterno"/>
                                             </ns22:codigoExterno>
                                          </xsl:if>
                                       </ns7:tipo>
                                       <xsl:if test="ns24:monto/ns7:importe">
                                          <ns7:importe>
                                             <xsl:value-of select="ns24:monto/ns7:importe"/>
                                          </ns7:importe>
                                       </xsl:if>
                                       <xsl:if test="ns24:monto/ns7:moneda">
                                          <ns7:moneda>
                                             <xsl:if test="ns24:monto/ns7:moneda/ns22:Id">
                                                <ns22:Id>
                                                   <xsl:value-of select="ns24:monto/ns7:moneda/ns22:Id"/>
                                                </ns22:Id>
                                             </xsl:if>
                                             <xsl:if test="ns24:monto/ns7:moneda/ns22:Descripcion">
                                                <ns22:Descripcion>
                                                   <xsl:value-of select="ns24:monto/ns7:moneda/ns22:Descripcion"/>
                                                </ns22:Descripcion>
                                             </xsl:if>
                                             <xsl:if test="ns24:monto/ns7:moneda/ns22:DescripcionCorta">
                                                <ns22:DescripcionCorta>
                                                   <xsl:value-of select="ns24:monto/ns7:moneda/ns22:DescripcionCorta"/>
                                                </ns22:DescripcionCorta>
                                             </xsl:if>
                                             <xsl:if test="ns24:monto/ns7:moneda/ns22:estatus">
                                                <ns22:estatus>
                                                   <xsl:value-of select="ns24:monto/ns7:moneda/ns22:estatus"/>
                                                </ns22:estatus>
                                             </xsl:if>
                                             <xsl:if test="ns24:monto/ns7:moneda/ns22:codigoExterno">
                                                <ns22:codigoExterno>
                                                   <xsl:value-of select="ns24:monto/ns7:moneda/ns22:codigoExterno"/>
                                                </ns22:codigoExterno>
                                             </xsl:if>
                                          </ns7:moneda>
                                       </xsl:if>
                                    </ns24:monto>
                                 </xsl:if>
                                 <xsl:if test="ns24:instanciaAprobacion">
                                    <ns24:instanciaAprobacion>
                                       <xsl:if test="ns24:instanciaAprobacion/ns22:Id">
                                          <ns22:Id>
                                             <xsl:value-of select="ns24:instanciaAprobacion/ns22:Id"/>
                                          </ns22:Id>
                                       </xsl:if>
                                       <xsl:if test="ns24:instanciaAprobacion/ns22:Descripcion">
                                          <ns22:Descripcion>
                                             <xsl:value-of select="ns24:instanciaAprobacion/ns22:Descripcion"/>
                                          </ns22:Descripcion>
                                       </xsl:if>
                                       <xsl:if test="ns24:instanciaAprobacion/ns22:DescripcionCorta">
                                          <ns22:DescripcionCorta>
                                             <xsl:value-of select="ns24:instanciaAprobacion/ns22:DescripcionCorta"/>
                                          </ns22:DescripcionCorta>
                                       </xsl:if>
                                       <xsl:if test="ns24:instanciaAprobacion/ns22:estatus">
                                          <ns22:estatus>
                                             <xsl:value-of select="ns24:instanciaAprobacion/ns22:estatus"/>
                                          </ns22:estatus>
                                       </xsl:if>
                                       <xsl:if test="ns24:instanciaAprobacion/ns22:codigoExterno">
                                          <ns22:codigoExterno>
                                             <xsl:value-of select="ns24:instanciaAprobacion/ns22:codigoExterno"/>
                                          </ns22:codigoExterno>
                                       </xsl:if>
                                    </ns24:instanciaAprobacion>
                                 </xsl:if>
                                 <ns24:fechaRegistro>
                                    <xsl:value-of select="ns24:fechaRegistro"/>
                                 </ns24:fechaRegistro>
                              </ns24:concursante>
                           </xsl:for-each>
                        </ns24:noObjecion>
                     </xsl:for-each>
                  </ns24:Adquisicion>
               </xsl:for-each>
            </ns2:Adquisiciones>
         </tns:configuracionReasignarOperacion>
      </tns:configuracionReasignarOperacionResponse>
   </xsl:template>
</xsl:stylesheet>
