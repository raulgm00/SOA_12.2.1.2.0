<?xml version = '1.0' encoding = 'UTF-8'?>
<xsl:stylesheet version="1.0" xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction" xmlns:tns="http://mx.agarcia.ea/Applications/JDA/Items/CreateItem" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:ns4="http://mx.agarcia.ea/Capabilities/Support/Commons/ItemCatalogEntitySvc" xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions" xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator" xmlns:ns3="http://mx.agarcia.ea/Applications/JDA/Buyer" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://mx.agarcia.ea/Capabilities/Core/Items/CreateItemEntity" xmlns:ns2="http://mx.agarcia.ea/Capabilities/Support/Commons/MerchandiseHierarchyEntity" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns4 ns3 ns0 ns2 tns mhdr oraext xp20 xref socket dvm oraxsl" xmlns:ns6="http://mx.agarcia.ea/Canonical/Commons/Languages/v1" xmlns:ns7="http://mx.agarcia.ea/Canonical/Commons/Types/v1" xmlns:ns10="http://mx.agarcia.ea/Canonical/Commons/Currencies/v1" xmlns:ns11="http://mx.agarcia.ea/Canonical/Commons/Countries/v1" xmlns:AGSH="http://mx.agarcia.ea/Commons/Schemas/AGStandardHeader" xmlns:ns12="http://mx.agarcia.ea/Capabilities/Support/Commons/MerchandiseHierarchyEntitySvc/v1.0" xmlns:ns15="http://mx.agarcia.ea/Capabilities/Support/Commons/ItemsCatalogs" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:fcon="http://mx.agarcia.ea/Capabilities/Support/ItemCatalogEntitySvc/Schema" xmlns:ns16="http://mx.agarcia.ea/Capabilities/Support/Commons/ItemApplications" xmlns:plnk="http://docs.oasis-open.org/wsbpel/2.0/plnktype" xmlns:ns1="http://mx.agarcia.ea/Canonical/Commons/Enums/v1" xmlns:client="http://mx.agarcia.ea/Capabilities/Core/Items/JDACreateItemProcessSvc/v1.0" xmlns:ns5="http://mx.agarcia.ea/Canonical/Commons/LocationsCommon/v1" xmlns:ns8="http://mx.agarcia.ea/Canonical/Commons/Items/v1" xmlns:ns9="http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:ns13="http://mx.agarcia.ea/Applications/JDA/Buyer/FindBuyer/v1.0" xmlns:ns14="http://mx.agarcia.ea/Capabilities/Support/Commons/ItemCatalogEntitySvcWSDL" xmlns:AGSM="http://mx.agarcia.ea/Commons/Schemas/AGStandardMessage" xmlns:ns17="http://mx.agarcia.ea/Applications/JDA/Items/CreateItem/v1.0" xmlns:ns20="http://xmlns.oracle.com/pcbpel/adapter/db/top/ItemHierarchyTranslate" xmlns:ns18="http://xmlns.oracle.com/pcbpel/adapter/db/FindAllHierarchySvc" xmlns:ns19="http://xmlns.oracle.com/pcbpel/adapter/db/FindByFilterHierarchySvc">
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
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="http://uat-osb.agarcia.com.mx:7004/Capabilities/Support/Commons/ItemCatalogEntitySvc/v1.0?wsdl"/>
            <oracle-xsl-mapper:rootElement name="translateResponse" namespace="http://mx.agarcia.ea/Capabilities/Support/Commons/ItemCatalogEntitySvc"/>
            <oracle-xsl-mapper:param name="InvokeTranslateColor_translate_OutputVariable.response"/>
         </oracle-xsl-mapper:source>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="http://uat-osb.agarcia.com.mx:7004/Capabilities/Support/Commons/ItemCatalogEntitySvc/v1.0?wsdl"/>
            <oracle-xsl-mapper:rootElement name="translateResponse" namespace="http://mx.agarcia.ea/Capabilities/Support/Commons/ItemCatalogEntitySvc"/>
            <oracle-xsl-mapper:param name="InvokeTranslateSize_translate_OutputVariable.response"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="WSDL">
            <oracle-xsl-mapper:schema location="http://uat-osb.agarcia.com.mx:7004/Applications/JDA/JDAItem/PS/JDACreateStylePXY/v1.0?wsdl"/>
            <oracle-xsl-mapper:rootElement name="createItemRequest" namespace="http://mx.agarcia.ea/Applications/JDA/Items/CreateItem"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [WED OCT 09 11:42:13 CDT 2019].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:param name="SKUUnicoVar"/>
   <xsl:param name="InvokeTranslateHerarchy_OutputVariable.translateResponseMessage"/>
   <xsl:param name="InvokeFindBuyer_OutputVariable.findBuyerResponseMessage"/>
   <xsl:param name="InvokeTranslateColor_translate_OutputVariable.response"/>
   <xsl:param name="InvokeTranslateSize_translate_OutputVariable.response"/>
   <xsl:template match="/">
      <tns:createItemRequest>
         <tns:item>
            <ns8:id Name="{$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:id/@Name}" Type="SKU">
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:id"/>
            </ns8:id>
            <ns8:internalID>
               <xsl:value-of select="$SKUUnicoVar/*:createItemRequest/*:items/*:item/*:internalID"/>
            </ns8:internalID>
            <ns8:description>
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:description"/>
            </ns8:description>
            <ns8:shortDescription>
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:shortDescription"/>
            </ns8:shortDescription>
            <ns8:itemHierarchyInfo>
               <xsl:for-each select="$InvokeTranslateHerarchy_OutputVariable.translateResponseMessage/ns2:translateResponse/ns2:itemHierarchyInfo/ns8:hierarchy">
                  <ns8:hierarchy Type="{@Type}">
                     <ns8:id>
                        <xsl:value-of select="ns8:id"/>
                     </ns8:id>
                  </ns8:hierarchy>
               </xsl:for-each>
            </ns8:itemHierarchyInfo>
            <ns8:owner>userRMS</ns8:owner>
            <ns8:price>
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:price"/>
            </ns8:price>
            <ns8:cost>
               <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:cost"/>
            </ns8:cost>
            <ns8:itemDetailInfo>
               <ns8:colors>
                  <ns8:color>
                     <ns8:id>
                        <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:itemDetailInfo/ns8:colors/ns8:color/ns8:id"/>
                     </ns8:id>
                  </ns8:color>
               </ns8:colors>
               <ns8:sizes>
                  <ns8:size>
                     <ns8:id>
                        <xsl:value-of select="$InvokeTranslateSize_translate_OutputVariable.response/ns4:translateResponse/ns4:catalogInfo/ns8:catalog/ns8:id"/>
                     </ns8:id>
                  </ns8:size>
               </ns8:sizes>
               <ns8:supplier>
                  <ns9:supplierID>
                     <xsl:value-of select="/*:createItemRequest/*:items/*:item/*:itemDetailInfo/*:supplier/*:supplierID"/>
                  </ns9:supplierID>
                  <ns9:shortDescription>
                     <xsl:value-of select="$InvokeFindBuyer_OutputVariable.findBuyerResponseMessage/*:findBuyerResponse/*:data/*:supplierID"/>
                  </ns9:shortDescription>
               </ns8:supplier>
               <ns8:brandOwner>
                  <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:itemDetailInfo/ns8:brandOwner"/>
               </ns8:brandOwner>
               <ns8:organicCharacteristics/>
               <ns8:packaging>
                  <xsl:value-of select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:itemDetailInfo/ns8:packaging"/>
               </ns8:packaging>
               <ns8:minOrderQty>0</ns8:minOrderQty>
            </ns8:itemDetailInfo>
            <ns8:catalogs>
               <xsl:for-each select="$SKUUnicoVar/ns0:createItemRequest/ns0:items/ns8:item/ns8:catalogs/ns8:catalog">
                  <ns8:catalog Type="{@Type}">
                     <ns8:id>
                        <xsl:value-of select="ns8:id"/>
                     </ns8:id>
                  </ns8:catalog>
               </xsl:for-each>
            </ns8:catalogs>
            <ns8:parent>
               <ns8:id>
                  <xsl:value-of select="/*:createItemRequest/*:items/*:item/*:parent/*:id"/>
               </ns8:id>
            </ns8:parent>
            <ns8:children>
               <ns8:item>
                  <ns8:id Type="UPC">
                     <xsl:value-of select="$SKUUnicoVar/*:createItemRequest/*:items/*:item/*:children/*:item/*:id"/>
                  </ns8:id>
                  <ns8:internalID>
                     <xsl:value-of select="$SKUUnicoVar/*:createItemRequest/*:items/*:item/*:children/*:item/*:internalID"/>
                  </ns8:internalID>
               </ns8:item>
            </ns8:children>
         </tns:item>
      </tns:createItemRequest>
   </xsl:template>
</xsl:stylesheet>