<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:ns0="http://www.bcie.org/AdquisicionMO" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 oraxsl xp20 xref mhdr oraext dvm socket" xmlns:ns1="http://www.bcie.org/ConfiguracionProcesosMO" xmlns:ns2="http://www.bcie.org/ConfiguracionProcesosBO" xmlns:ns3="http://www.bcie.org/TerminoBO" xmlns:bpws="http://docs.oasis-open.org/wsbpel/2.0/process/executable" xmlns:ns4="http://www.bcie.org/ReglaBO" xmlns:ns5="http://www.bcie.org/OperacionBO" xmlns:ns6="http://www.bcie.org/ResultBO" xmlns:ns7="http://www.bcie.org/CommonBO" xmlns:ns8="http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1" xmlns:ns11="http://www.bcie.org/MatrizTCCBO" xmlns:ns10="http://www.bcie.org/LineaCreditoBO" xmlns:ns9="http://www.bcie.org/CrearBitacoraProcesoBO" xmlns:ns12="http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:ns13="http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1" xmlns:ns15="http://www.bcie.org/ProductoBO" xmlns:ns14="http://www.bcie.org/CondicionBO" xmlns:ns16="http://www.bcie.org/DeclaracionJuradaBO" xmlns:ns17="http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1" xmlns:ns18="http://www.bcie.org/ImplementacionPctBO" xmlns:ns19="http://www.bcie.org/DocumentoBO" xmlns:ns20="http://www.bcie.org/HerramientaCEBO" xmlns:plnk="http://docs.oasis-open.org/wsbpel/2.0/plnktype" xmlns:ns21="http://www.bcie.org/ComisionBO" xmlns:tns="http://xmlns.bcie.com/ConfiguracionProcesosService" xmlns:ns22="http://www.bcie.org/CatalogoBO" xmlns:ns23="http://www.bcie.org/DesembolsoBO" xmlns:ns24="http://www.bcie.org/AdquisicionBO" xmlns:ns25="http://www.bcie.org/ContratoBO" xmlns:ns26="http://www.bcie.org/ClienteBO" xmlns:ns27="http://www.bcie.org/AtributoBO" xmlns:ns29="http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1" xmlns:ns28="http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1" xmlns:ns30="http://www.bcie.org/ErrorBO">
   <oracle-xsl-mapper:schema>
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="../../WSDLs/ConfiguracionReasignarOperacionSORWrapper.wsdl"/>
            <oracle-xsl-mapper:rootElement name="ConsultarNoObjecionResponse" namespace="http://www.bcie.org/AdquisicionMO"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="WSDL">
            <oracle-xsl-mapper:schema location="../../WSDLs/ConfiguracionReasignarOperacionSORWrapper.wsdl"/>
            <oracle-xsl-mapper:rootElement name="ConsultarAdquisicionResponse" namespace="http://www.bcie.org/AdquisicionMO"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
   </oracle-xsl-mapper:schema>
   <xsl:template match="/">
      <ns0:ConsultarAdquisicionResponse>
         <ns0:Adquisicion>
            <xsl:for-each select="/ns24:ConsultarNoObjecionResponse/ns0:NoObjecion">
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
         </ns0:Adquisicion>
      </ns0:ConsultarAdquisicionResponse>
   </xsl:template>
</xsl:stylesheet>
