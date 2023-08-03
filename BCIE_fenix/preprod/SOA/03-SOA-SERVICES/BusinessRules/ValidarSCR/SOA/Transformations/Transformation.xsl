<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:ns0="http://www.bcie.org/ClienteMO" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" exclude-result-prefixes="xsd xsi oracle-xsl-mapper xsl ns0 oraxsl xp20 xref mhdr oraext dvm socket" xmlns:ns3="http://www.bcie.org/TerminoBO" xmlns:ns5="http://www.bcie.org/OperacionBO" xmlns:ns7="http://www.bcie.org/ResultBO" xmlns:ns8="http://www.bcie.org/CommonBO" xmlns:ns12="http://www.bcie.org/LineaCreditoBO" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:ns1="http://www.bcie.org/ProductoBO" xmlns:ns2="http://www.bcie.org/CondicionBO" xmlns:ns4="http://www.bcie.org/ValidarSCR/types" xmlns:ns6="http://www.bcie.org/DeclaracionJuradaBO" xmlns:tns="http://www.bcie.org/ValidarSCR" xmlns:ns9="http://www.bcie.org/DocumentoBO" xmlns:ns10="http://www.bcie.org/HerramientaCEBO" xmlns:ns11="http://www.bcie.org/ComisionBO" xmlns:ns13="http://www.bcie.org/CatalogoBO" xmlns:ns14="http://www.bcie.org/DesembolsoBO" xmlns:ns15="http://www.bcie.org/ContratoBO" xmlns:ns16="http://www.bcie.org/ClienteBO" xmlns:ns17="http://www.bcie.org/AtributoBO" xmlns:ns18="http://www.bcie.org/ErrorBO">
   <oracle-xsl-mapper:schema>
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Wsdl/BPEL/ValidarSCRSOR.wsdl"/>
            <oracle-xsl-mapper:rootElement name="ConsultarClienteResponse" namespace="http://www.bcie.org/ClienteMO"/>
         </oracle-xsl-mapper:source>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Wsdl/BPEL/ValidarSCRSOR.wsdl"/>
            <oracle-xsl-mapper:rootElement name="ValidarSCRResponse" namespace="http://www.bcie.org/ClienteMO"/>
            <oracle-xsl-mapper:param name="outputVariable.response"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="WSDL">
            <oracle-xsl-mapper:schema location="oramds:/apps/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Wsdl/BPEL/ValidarSCRSOR.wsdl"/>
            <oracle-xsl-mapper:rootElement name="ValidarSCRResponse" namespace="http://www.bcie.org/ClienteMO"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
   </oracle-xsl-mapper:schema>
   <xsl:param name="outputVariable.response"/>
   <xsl:template match="/">
      <ns0:ValidarSCRResponse>
         <ns0:Cliente>
            <ns16:idCliente>
               <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:idCliente"/>
            </ns16:idCliente>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:idFacturador">
               <ns16:idFacturador>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:idFacturador"/>
               </ns16:idFacturador>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:razonSocial">
               <ns16:razonSocial>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:razonSocial"/>
               </ns16:razonSocial>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:abreviatura">
               <ns16:abreviatura>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:abreviatura"/>
               </ns16:abreviatura>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona">
               <ns16:tipoPersona>
                  <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id">
                     <ns13:Id>
                        <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id"/>
                     </ns13:Id>
                  </xsl:if>
                  <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion">
                     <ns13:Descripcion>
                        <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion"/>
                     </ns13:Descripcion>
                  </xsl:if>
                  <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta">
                     <ns13:DescripcionCorta>
                        <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta"/>
                     </ns13:DescripcionCorta>
                  </xsl:if>
                  <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus">
                     <ns13:estatus>
                        <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus"/>
                     </ns13:estatus>
                  </xsl:if>
                  <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno">
                     <ns13:codigoExterno>
                        <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno"/>
                     </ns13:codigoExterno>
                  </xsl:if>
               </ns16:tipoPersona>
            </xsl:if>
            <ns16:tipoCliente>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id">
                  <ns13:Id>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id"/>
                  </ns13:Id>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion">
                  <ns13:Descripcion>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion"/>
                  </ns13:Descripcion>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta">
                  <ns13:DescripcionCorta>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta"/>
                  </ns13:DescripcionCorta>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus">
                  <ns13:estatus>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus"/>
                  </ns13:estatus>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno">
                  <ns13:codigoExterno>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno"/>
                  </ns13:codigoExterno>
               </xsl:if>
            </ns16:tipoCliente>
            <ns16:sector>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id">
                  <ns13:Id>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id"/>
                  </ns13:Id>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion">
                  <ns13:Descripcion>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion"/>
                  </ns13:Descripcion>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta">
                  <ns13:DescripcionCorta>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta"/>
                  </ns13:DescripcionCorta>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus">
                  <ns13:estatus>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus"/>
                  </ns13:estatus>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno">
                  <ns13:codigoExterno>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno"/>
                  </ns13:codigoExterno>
               </xsl:if>
            </ns16:sector>
            <ns16:tipoInstitucion>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id">
                  <ns13:Id>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id"/>
                  </ns13:Id>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion">
                  <ns13:Descripcion>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion"/>
                  </ns13:Descripcion>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta">
                  <ns13:DescripcionCorta>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta"/>
                  </ns13:DescripcionCorta>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus">
                  <ns13:estatus>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus"/>
                  </ns13:estatus>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno">
                  <ns13:codigoExterno>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno"/>
                  </ns13:codigoExterno>
               </xsl:if>
            </ns16:tipoInstitucion>
            <ns16:pais>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id">
                  <ns13:Id>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id"/>
                  </ns13:Id>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion">
                  <ns13:Descripcion>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion"/>
                  </ns13:Descripcion>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta">
                  <ns13:DescripcionCorta>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta"/>
                  </ns13:DescripcionCorta>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus">
                  <ns13:estatus>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus"/>
                  </ns13:estatus>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno">
                  <ns13:codigoExterno>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno"/>
                  </ns13:codigoExterno>
               </xsl:if>
            </ns16:pais>
            <ns16:grupoEconomico>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id">
                  <ns13:Id>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id"/>
                  </ns13:Id>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion">
                  <ns13:Descripcion>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion"/>
                  </ns13:Descripcion>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta">
                  <ns13:DescripcionCorta>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta"/>
                  </ns13:DescripcionCorta>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus">
                  <ns13:estatus>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus"/>
                  </ns13:estatus>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno">
                  <ns13:codigoExterno>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno"/>
                  </ns13:codigoExterno>
               </xsl:if>
            </ns16:grupoEconomico>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoIdentificacion">
               <ns16:tipoIdentificacion>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoIdentificacion"/>
               </ns16:tipoIdentificacion>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:numeroIdentificacion">
               <ns16:numeroIdentificacion>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:numeroIdentificacion"/>
               </ns16:numeroIdentificacion>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:direccion">
               <ns16:direccion>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:direccion"/>
               </ns16:direccion>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:telefono">
               <ns16:telefono>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:telefono"/>
               </ns16:telefono>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:fax">
               <ns16:fax>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:fax"/>
               </ns16:fax>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:email">
               <ns16:email>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:email"/>
               </ns16:email>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:ciudad">
               <ns16:ciudad>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:ciudad"/>
               </ns16:ciudad>
            </xsl:if>
            <ns16:oficina>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id">
                  <ns13:Id>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id"/>
                  </ns13:Id>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion">
                  <ns13:Descripcion>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion"/>
                  </ns13:Descripcion>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta">
                  <ns13:DescripcionCorta>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta"/>
                  </ns13:DescripcionCorta>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus">
                  <ns13:estatus>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus"/>
                  </ns13:estatus>
               </xsl:if>
               <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno">
                  <ns13:codigoExterno>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno"/>
                  </ns13:codigoExterno>
               </xsl:if>
            </ns16:oficina>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:grupoEmpresarial">
               <ns16:grupoEmpresarial>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:grupoEmpresarial"/>
               </ns16:grupoEmpresarial>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:fechaRegistro">
               <ns16:fechaRegistro>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:fechaRegistro"/>
               </ns16:fechaRegistro>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:fechaAprobacion">
               <ns16:fechaAprobacion>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:fechaAprobacion"/>
               </ns16:fechaAprobacion>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:ejecutivo">
               <ns16:ejecutivo>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:ejecutivo"/>
               </ns16:ejecutivo>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:responsableCliente">
               <ns16:responsableCliente>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:responsableCliente"/>
               </ns16:responsableCliente>
            </xsl:if>
            <xsl:for-each select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:Contactos">
               <ns16:Contactos>
                  <xsl:for-each select="ns16:Contacto">
                     <ns16:Contacto>
                        <ns16:idContacto>
                           <xsl:value-of select="ns16:idContacto"/>
                        </ns16:idContacto>
                        <ns16:idCliente>
                           <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:idCliente"/>
                        </ns16:idCliente>
                        <ns16:nombre>
                           <xsl:value-of select="ns16:nombre"/>
                        </ns16:nombre>
                        <ns16:telefono>
                           <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:telefono"/>
                        </ns16:telefono>
                        <ns16:correoElectronico>
                           <xsl:value-of select="ns16:correoElectronico"/>
                        </ns16:correoElectronico>
                        <ns16:cargo>
                           <xsl:value-of select="ns16:cargo"/>
                        </ns16:cargo>
                        <ns16:tipo>
                           <xsl:value-of select="ns16:tipo"/>
                        </ns16:tipo>
                        <ns16:fechaRegistro>
                           <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:fechaRegistro"/>
                        </ns16:fechaRegistro>
                        <ns16:idContactosClientes>
                           <xsl:value-of select="ns16:idContactosClientes"/>
                        </ns16:idContactosClientes>
                     </ns16:Contacto>
                  </xsl:for-each>
               </ns16:Contactos>
            </xsl:for-each>
            <ns16:InformacionCorrecta>
               <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:InformacionCorrecta"/>
            </ns16:InformacionCorrecta>
            <ns16:ActualizacionPermitida>
               <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:ActualizacionPermitida"/>
            </ns16:ActualizacionPermitida>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:comentarioAprobacion">
               <ns16:comentarioAprobacion>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:comentarioAprobacion"/>
               </ns16:comentarioAprobacion>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:revisadoAprobacion">
               <ns16:revisadoAprobacion>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:revisadoAprobacion"/>
               </ns16:revisadoAprobacion>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:status">
               <ns16:status>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:status"/>
               </ns16:status>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:fechaBaja">
               <ns16:fechaBaja>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:fechaBaja"/>
               </ns16:fechaBaja>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:diaPago">
               <ns16:diaPago>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:diaPago"/>
               </ns16:diaPago>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:enMora">
               <ns16:enMora>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:enMora"/>
               </ns16:enMora>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:mora">
               <ns16:mora>
                  <ns16:dias>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:mora/ns16:dias"/>
                  </ns16:dias>
                  <ns16:monto>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:mora/ns16:monto"/>
                  </ns16:monto>
                  <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona">
                     <ns16:tipo>
                        <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id">
                           <ns13:Id>
                              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Id"/>
                           </ns13:Id>
                        </xsl:if>
                        <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion">
                           <ns13:Descripcion>
                              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:Descripcion"/>
                           </ns13:Descripcion>
                        </xsl:if>
                        <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta">
                           <ns13:DescripcionCorta>
                              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:DescripcionCorta"/>
                           </ns13:DescripcionCorta>
                        </xsl:if>
                        <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus">
                           <ns13:estatus>
                              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:estatus"/>
                           </ns13:estatus>
                        </xsl:if>
                        <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno">
                           <ns13:codigoExterno>
                              <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:tipoPersona/ns13:codigoExterno"/>
                           </ns13:codigoExterno>
                        </xsl:if>
                     </ns16:tipo>
                  </xsl:if>
               </ns16:mora>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:deteriorado">
               <ns16:deteriorado>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:deteriorado"/>
               </ns16:deteriorado>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:reserva">
               <ns16:reserva>
                  <ns16:importeReserva>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:reserva/ns16:importeReserva"/>
                  </ns16:importeReserva>
                  <ns16:tipoReserva>
                     <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:reserva/ns16:tipoReserva"/>
                  </ns16:tipoReserva>
               </ns16:reserva>
            </xsl:if>
            <xsl:if test="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:requiereEnvioAutomatico">
               <ns16:requiereEnvioAutomatico>
                  <xsl:value-of select="/ns0:ConsultarClienteResponse/ns0:Cliente/ns16:requiereEnvioAutomatico"/>
               </ns16:requiereEnvioAutomatico>
            </xsl:if>
            <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR">
               <ns16:detalleSCR>
                  <ns16:scrFuente>
                     <ns16:idCliente>
                        <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:idCliente"/>
                     </ns16:idCliente>
                     <xsl:if test="">
                        <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:idFacturador">
                           <ns16:idFacturador>
                              <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:idFacturador"/>
                           </ns16:idFacturador>
                        </xsl:if>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:scr">
                        <ns16:scr>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:scr"/>
                        </ns16:scr>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:estadoScr">
                        <ns16:estadoScr>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:estadoScr"/>
                        </ns16:estadoScr>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:observacion">
                        <ns16:observacion>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:observacion"/>
                        </ns16:observacion>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:fecha">
                        <ns16:fecha>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:fecha"/>
                        </ns16:fecha>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:usuarioModifico">
                        <ns16:usuarioModifico>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:usuarioModifico"/>
                        </ns16:usuarioModifico>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:usuarioValido">
                        <ns16:usuarioValido>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:usuarioValido"/>
                        </ns16:usuarioValido>
                     </xsl:if>
                  </ns16:scrFuente>
                  <ns16:scrReferencia>
                     <ns16:idCliente>
                        <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:idCliente"/>
                     </ns16:idCliente>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:idFacturador">
                        <ns16:idFacturador>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:idFacturador"/>
                        </ns16:idFacturador>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:scr">
                        <ns16:scr>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:scr"/>
                        </ns16:scr>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:estadoScr">
                        <ns16:estadoScr>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:estadoScr"/>
                        </ns16:estadoScr>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:observacion">
                        <ns16:observacion>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:observacion"/>
                        </ns16:observacion>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:fecha">
                        <ns16:fecha>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:fecha"/>
                        </ns16:fecha>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:usuarioModifico">
                        <ns16:usuarioModifico>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:usuarioModifico"/>
                        </ns16:usuarioModifico>
                     </xsl:if>
                     <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:usuarioValido">
                        <ns16:usuarioValido>
                           <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:scrFuente/ns16:usuarioValido"/>
                        </ns16:usuarioValido>
                     </xsl:if>
                  </ns16:scrReferencia>
                  <ns16:estatusComparacion>
                     <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Cliente/ns16:detalleSCR/ns16:estatusComparacion"/>
                  </ns16:estatusComparacion>
               </ns16:detalleSCR>
            </xsl:if>
         </ns0:Cliente>
         <ns0:Resultado>
            <ns7:result>
               <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Resultado/ns7:result"/>
            </ns7:result>
            <ns7:message>
               <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Resultado/ns7:message"/>
            </ns7:message>
            <xsl:if test="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Resultado/ns7:error">
               <ns7:error>
                  <ns18:errorCode>
                     <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Resultado/ns7:error/ns18:errorCode"/>
                  </ns18:errorCode>
                  <ns18:errorDescription>
                     <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Resultado/ns7:error/ns18:errorDescription"/>
                  </ns18:errorDescription>
                  <ns18:errorType>
                     <xsl:value-of select="$outputVariable.response/ns0:ValidarSCRResponse/ns0:Resultado/ns7:error/ns18:errorType"/>
                  </ns18:errorType>
               </ns7:error>
            </xsl:if>
         </ns0:Resultado>
      </ns0:ValidarSCRResponse>
   </xsl:template>
</xsl:stylesheet>
