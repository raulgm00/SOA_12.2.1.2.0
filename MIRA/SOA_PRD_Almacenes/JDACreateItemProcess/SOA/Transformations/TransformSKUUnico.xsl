<?xml version = '1.0' encoding = 'UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" exclude-result-prefixes=" oracle-xsl-mapper xsi xsd xsl ns0 socket dvm mhdr oraxsl oraext xp20 xref" xmlns:ns3="http://mx.agarcia.ea/Canonical/Commons/Languages/v1" xmlns:ns4="http://mx.agarcia.ea/Canonical/Commons/Types/v1" xmlns:ns7="http://mx.agarcia.ea/Canonical/Commons/Currencies/v1" xmlns:ns8="http://mx.agarcia.ea/Canonical/Commons/Countries/v1" xmlns:AGSH="http://mx.agarcia.ea/Commons/Schemas/AGStandardHeader" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:plnk="http://docs.oasis-open.org/wsbpel/2.0/plnktype" xmlns:ns1="http://mx.agarcia.ea/Canonical/Commons/Enums/v1" xmlns:client="http://mx.agarcia.ea/Capabilities/Core/Items/JDACreateItemProcessSvc/v1.0" xmlns:ns2="http://mx.agarcia.ea/Canonical/Commons/LocationsCommon/v1" xmlns:ns5="http://mx.agarcia.ea/Canonical/Commons/Items/v1" xmlns:ns6="http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:AGSM="http://mx.agarcia.ea/Commons/Schemas/AGStandardMessage">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="../WSDLs/JDACreateItemProcess.wsdl"/>
            <oracle-xsl-mapper:rootElement name="createItemRequest" namespace="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="WSDL">
            <oracle-xsl-mapper:schema location="../WSDLs/JDACreateItemProcess.wsdl"/>
            <oracle-xsl-mapper:rootElement name="createItemRequest" namespace="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [THU JUN 20 18:42:02 CDT 2019].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <ns0:createItemRequest>
         <ns0:items>
            <xsl:for-each select="/*:createItemRequest/*:items/*:item[1]">
               <ns5:item>
                  <ns5:id Type="{ns5:id/@Type}" Name="{ns5:id/@Name}">
                     <xsl:value-of select="ns5:id"/>
                  </ns5:id>
                  <ns5:name>
                     <xsl:value-of select="ns5:name"/>
                  </ns5:name>
                  <ns5:description>
                     <xsl:value-of select="ns5:description"/>
                  </ns5:description>
                  <ns5:shortDescription>
                     <xsl:value-of select="ns5:shortDescription"/>
                  </ns5:shortDescription>
                  <ns5:itemHierarchyInfo>
                     <xsl:for-each select="ns5:itemHierarchyInfo/ns5:hierarchy">
                        <ns5:hierarchy Type="{@Type}">
                           <ns5:id>
                              <xsl:value-of select="ns5:id"/>
                           </ns5:id>
                        </ns5:hierarchy>
                     </xsl:for-each>
                  </ns5:itemHierarchyInfo>
                  <ns5:owner>
                     <xsl:value-of select="ns5:owner"/>
                  </ns5:owner>
                  <ns5:price>
                     <xsl:value-of select="ns5:price"/>
                  </ns5:price>
                  <ns5:cost>
                     <xsl:value-of select="ns5:cost"/>
                  </ns5:cost>
                  <ns5:itemDetailInfo>
                  
                      <ns5:colorGroup>
                        <ns6:id>
                           <xsl:value-of select="ns5:itemDetailInfo/ns5:colorGroup/ns6:id"/>
                        </ns6:id>
                     </ns5:colorGroup>
                  
                  
                     <ns5:sizeGroup>
                        <ns6:id>
                           <xsl:value-of select="ns5:itemDetailInfo/ns5:sizeGroup/ns6:id"/>
                        </ns6:id>
                     </ns5:sizeGroup>
                     
                      <ns5:colors>
                          <ns5:color>
                        <ns5:id>
                              <xsl:value-of select="/*:createItemRequest/*:items/*:item/*:itemDetailInfo/*:colors/*:color/*:id"/>
                           </ns5:id>
                        </ns5:color>
                     </ns5:colors>
                     
                     <ns5:sizes>
                          <ns5:size>
                        <ns6:id>
                              <xsl:value-of select="/*:createItemRequest/*:items/*:item/*:itemDetailInfo/*:colors/*:color/*:id"/>
                           </ns6:id>
                        </ns5:size>
                     </ns5:sizes>
                     
                     
                     
                     
                  
                  
                     <ns5:supplier>
                        <ns6:supplierID>
                           <xsl:value-of select="ns5:itemDetailInfo/ns5:supplier/ns6:supplierID"/>
                        </ns6:supplierID>
                     </ns5:supplier>
                     <ns5:brandOwner>
                        <xsl:value-of select="/*:createItemRequest/*:items/ns5:item/*:itemDetailInfo/*:brandOwner"/>
                     </ns5:brandOwner>
                     <ns5:packaging>
                        <xsl:value-of select="/*:createItemRequest/*:items/ns5:item/*:itemDetailInfo/*:packaging"/>
                     </ns5:packaging>
                     <ns5:packType/>
                  </ns5:itemDetailInfo>
                 
                     <ns5:catalogs>
                        <ns5:catalog>
                           <ns5:id>
                             
                           </ns5:id>
                        </ns5:catalog>
                     </ns5:catalogs>
                 
                  <ns5:parent>
                     <ns5:id>
                        <xsl:value-of select="ns5:parent/ns5:id"/>
                     </ns5:id>
                     <ns5:itemDetailInfo>
                        <ns5:colorGroup>
                           <ns5:id>
                              <xsl:value-of select="ns5:parent/ns5:itemDetailInfo/ns5:colorGroup/ns5:id"/>
                           </ns5:id>
                        </ns5:colorGroup>
                        <ns5:sizeGroup>
                           <ns5:id>
                              <xsl:value-of select="ns5:parent/ns5:itemDetailInfo/ns5:sizeGroup/ns5:id"/>
                           </ns5:id>
                        </ns5:sizeGroup>
                     </ns5:itemDetailInfo>
                  </ns5:parent>
                  <ns5:children>
                     <ns5:item>
                        <ns5:id Type="UPC">
                           <xsl:value-of select="ns5:children/ns5:id"/>
                        </ns5:id>
                        <ns5:internalType>
                           <xsl:value-of select="/*:createItemRequest/*:items/*:item/*:children/*:item/*:internalType"/>
                        </ns5:internalType>
                     </ns5:item>
                  </ns5:children>
                  <ns5:images>
                     <ns5:itemImage>
                        <ns5:url>
                           <xsl:value-of select="ns5:images/ns5:itemImage/ns5:url"/>
                        </ns5:url>
                     </ns5:itemImage>
                  </ns5:images>
               </ns5:item>
            </xsl:for-each>
         </ns0:items>
      </ns0:createItemRequest>
   </xsl:template>
</xsl:stylesheet>