<?xml version = '1.0' encoding = 'UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:tns="http://mx.agarcia.ea/Applications/JDA/Items/CreateItem" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:ns3="http://mx.agarcia.ea/Applications/JDA/Buyer" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity" xmlns:ns2="http://mx.agarcia.ea/Capabilities/Support/Commons/MerchandiseHierarchyEntity" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns3 ns0 ns2 tns mhdr oraext xp20 xref socket dvm oraxsl" xmlns:ns5="http://mx.agarcia.ea/Canonical/Commons/Languages/v1" xmlns:ns6="http://mx.agarcia.ea/Canonical/Commons/Types/v1" xmlns:ns9="http://mx.agarcia.ea/Canonical/Commons/Currencies/v1" xmlns:ns10="http://mx.agarcia.ea/Canonical/Commons/Countries/v1" xmlns:AGSH="http://mx.agarcia.ea/Commons/Schemas/AGStandardHeader" xmlns:ns11="http://mx.agarcia.ea/Capabilities/Support/Commons/MerchandiseHierarchyEntitySvc/v1.0" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:plnk="http://docs.oasis-open.org/wsbpel/2.0/plnktype" xmlns:ns1="http://mx.agarcia.ea/Canonical/Commons/Enums/v1" xmlns:client="http://mx.agarcia.ea/Capabilities/Core/Items/JDACreateItemProcessSvc/v1.0" xmlns:ns4="http://mx.agarcia.ea/Canonical/Commons/LocationsCommon/v1" xmlns:ns7="http://mx.agarcia.ea/Canonical/Commons/Items/v1" xmlns:ns8="http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:ns12="http://mx.agarcia.ea/Applications/JDA/Buyer/FindBuyer/v1.0" xmlns:AGSM="http://mx.agarcia.ea/Commons/Schemas/AGStandardMessage" xmlns:ns13="http://mx.agarcia.ea/Applications/JDA/Items/CreateItem/v1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="../WSDLs/JDACreateItemProcess.wsdl"/>
            <oracle-xsl-mapper:rootElement name="createItemRequest" namespace="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity"/>
         </oracle-xsl-mapper:source>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="../WSDLs/JDACreateItemProcess.wsdl"/>
            <oracle-xsl-mapper:rootElement name="createItemRequest" namespace="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity"/>
            <oracle-xsl-mapper:param name="SKUUnicoVar"/>
         </oracle-xsl-mapper:source>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="http://uat-osb.agarcia.com.mx:7004/RetailOperations/Make/ProductDevelopment/MerchandiseHierarchyTaskSvc/v1.0?wsdl"/>
            <oracle-xsl-mapper:rootElement name="translateResponse" namespace="http://mx.agarcia.ea/Capabilities/Support/Commons/MerchandiseHierarchyEntity"/>
            <oracle-xsl-mapper:param name="InvokeTranslateHerarchy_OutputVariable.translateResponseMessage"/>
         </oracle-xsl-mapper:source>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="http://uat-osb.agarcia.com.mx:7004/Applications/JDA/JDABuyer/PS/JDAFindBuyerPXY?wsdl"/>
            <oracle-xsl-mapper:rootElement name="findBuyerResponse" namespace="http://mx.agarcia.ea/Applications/JDA/Buyer"/>
            <oracle-xsl-mapper:param name="InvokeFindBuyer_OutputVariable.findBuyerResponseMessage"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="WSDL">
            <oracle-xsl-mapper:schema location="http://uat-osb.agarcia.com.mx:7004/Applications/JDA/JDAItem/PS/JDACreateStylePXY/v1.0?wsdl"/>
            <oracle-xsl-mapper:rootElement name="createItemRequest" namespace="http://mx.agarcia.ea/Applications/JDA/Items/CreateItem"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [TUE OCT 08 11:25:56 CDT 2019].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:param name="SKUUnicoVar"/>
   <xsl:param name="InvokeTranslateHerarchy_OutputVariable.translateResponseMessage"/>
   <xsl:param name="InvokeFindBuyer_OutputVariable.findBuyerResponseMessage"/>
   <xsl:template match="/">
      <tns:createItemRequest>
         <tns:item>
            <ns7:id Name="{$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:id/@Name}" Type="SKU">
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:id"/>
            </ns7:id>
            <ns7:internalID>
               <xsl:value-of select="$SKUUnicoVar/*:createItemRequest/*:items/*:item/*:internalID"/>
            </ns7:internalID>
            <ns7:description>
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:description"/>
            </ns7:description>
            <ns7:shortDescription>
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:shortDescription"/>
            </ns7:shortDescription>
            <ns7:itemHierarchyInfo>
               <xsl:for-each select="$InvokeTranslateHerarchy_OutputVariable.translateResponseMessage/ns2:translateResponse/ns2:itemHierarchyInfo/ns7:hierarchy">
                  <ns7:hierarchy Type="{@Type}">
                     <ns7:id>
                        <xsl:value-of select="ns7:id"/>
                     </ns7:id>
                  </ns7:hierarchy>
               </xsl:for-each>
            </ns7:itemHierarchyInfo>
            <ns7:owner>userRMS</ns7:owner>
            <ns7:price>
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:price"/>
            </ns7:price>
            <ns7:cost>
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:cost"/>
            </ns7:cost>
            <ns7:itemDetailInfo>
               <ns7:colors>
                  <ns7:color>
                     <ns7:id>
                        <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:itemDetailInfo/ns7:colors/ns7:color/ns7:id"/>
                     </ns7:id>
                  </ns7:color>
               </ns7:colors>
               <ns7:supplier>
                  <ns8:supplierID>
                     <xsl:value-of select="/*:createItemRequest/ns0:items/*:item/*:itemDetailInfo/*:supplier/*:supplierID"/>
                  </ns8:supplierID>
                  <ns8:shortDescription>
                     <xsl:value-of select="$InvokeFindBuyer_OutputVariable.findBuyerResponseMessage/*:findBuyerResponse/*:data/*:supplierID"/>
                  </ns8:shortDescription>
               </ns7:supplier>
               <ns7:brandOwner>
                  <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:itemDetailInfo/ns7:brandOwner"/>
               </ns7:brandOwner>
               <ns7:packaging>
                  <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:itemDetailInfo/ns7:packaging"/>
               </ns7:packaging>
               <ns7:minOrderQty>0</ns7:minOrderQty>
            </ns7:itemDetailInfo>
            <ns7:catalogs>
               <xsl:for-each select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:catalogs/ns7:catalog">
                  <ns7:catalog Type="{@Type}">
                     <ns7:id>
                        <xsl:value-of select="ns7:id"/>
                     </ns7:id>
                  </ns7:catalog>
               </xsl:for-each>
            </ns7:catalogs>
            <ns7:parent>
               <ns7:id>
                  <xsl:value-of select="/*:createItemRequest/*:items/*:item/*:parent/*:id"/>
               </ns7:id>
            </ns7:parent>
            <ns7:children>
               <ns7:item>
                  <ns7:id>
                     <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:children/ns7:item/ns7:id"/>
                  </ns7:id>
                  <ns7:internalID>
                     <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns7:item/ns7:children/ns7:item/ns7:internalID"/>
                  </ns7:internalID>
               </ns7:item>
            </ns7:children>
         </tns:item>
      </tns:createItemRequest>
   </xsl:template>
</xsl:stylesheet>