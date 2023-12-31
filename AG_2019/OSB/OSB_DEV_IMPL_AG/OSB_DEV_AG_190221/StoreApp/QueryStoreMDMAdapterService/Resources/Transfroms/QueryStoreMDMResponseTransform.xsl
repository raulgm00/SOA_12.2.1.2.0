<xsl:stylesheet version="1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:tns="http://www.agarcia.mx/EnterpriseObjects/Store" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/top/QueryStoreMDM" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/Custom">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../Resources/QueryStoreMDM_table.xsd"/>
            <oracle-xsl-mapper:rootElement name="MdmWarehouseCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/QueryStoreMDM"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/Store/StoreEBM.xsd"/>
            <oracle-xsl-mapper:rootElement name="QueryStoreResponseEBMList" namespace="http://www.agarcia.mx/EnterpriseObjects/Store"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [FRI OCT 09 09:41:48 CDT 2020].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:QueryStoreResponseEBMList>
         <xsl:for-each select="/ns0:MdmWarehouseCollection/ns0:MdmWarehouse">
            <tns:QueryStoreReference>
               <com:Identification>
                  <com:ID>
                     <xsl:value-of select="ns0:idRms"/>
                  </com:ID>
               </com:Identification>
               <Name>
                  <xsl:value-of select="ns0:storeName"/>
               </Name>
               <Description>
                  <xsl:value-of select="ns0:description"/>
               </Description>
               <TypeCode>
                  <xsl:value-of select="ns0:storeType"/>
               </TypeCode>
               <Portage>
                  <com:ID>
                     <xsl:value-of select="ns0:portage"/>
                  </com:ID>
               </Portage>
               <Carrier>
                  <com:ID>
                     <xsl:value-of select="ns0:folioEntrega"/>
                  </com:ID>
                  <com:BusinessComponentID>
                     <xsl:value-of select="ns0:folioEntregaDhl"/>
                  </com:BusinessComponentID>
               </Carrier>
               <Address>
                  <com:LineOne>
                     <xsl:value-of select="ns0:address"/>
                  </com:LineOne>
                  <com:CityName>
                     <xsl:value-of select="ns0:city"/>
                  </com:CityName>
                  <com:StateName>
                     <xsl:value-of select="ns0:state"/>
                  </com:StateName>
                  <com:PostalCode>
                     <xsl:value-of select="ns0:postalCode"/>
                  </com:PostalCode>
               </Address>
               <Contact>
                  <com:ContactPhoneCommunication>
                     <com:DialNumber>
                        <xsl:value-of select="ns0:phone"/>
                     </com:DialNumber>
                  </com:ContactPhoneCommunication>
                  <com:ContactFaxCommunication>
                     <xsl:value-of select="ns0:fax"/>
                  </com:ContactFaxCommunication>
                  <com:ContactEmailCommunication>
                     <xsl:value-of select="ns0:email"/>
                  </com:ContactEmailCommunication>
               </Contact>
               <Status>
                  <com:Code>
                     <xsl:value-of select="ns0:status"/>
                  </com:Code>
               </Status>
               <Custom>
                  <ns1:StoreValueSet>
                     <com:Identification>
                        <com:ID>
                           <xsl:value-of select="ns0:codeRm"/>
                        </com:ID>
                     </com:Identification>
                  </ns1:StoreValueSet>
               </Custom>
            </tns:QueryStoreReference>
         </xsl:for-each>
      </tns:QueryStoreResponseEBMList>
   </xsl:template>
</xsl:stylesheet>