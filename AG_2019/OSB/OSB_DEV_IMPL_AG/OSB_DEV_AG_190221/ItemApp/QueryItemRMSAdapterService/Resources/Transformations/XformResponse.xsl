<xsl:stylesheet version="1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:tns="http://www.agarcia.mx/EnterpriseObjects/Item" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/top/QueryItemRMSReference" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/Custom">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../Schema/QueryItemRMSReference_table.xsd"/>
            <oracle-xsl-mapper:rootElement name="XxSoaItemVwCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/QueryItemRMSReference"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/Item/ItemEBM.xsd"/>
            <oracle-xsl-mapper:rootElement name="QueryItemResponseEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/Item"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [MON JAN 18 15:01:36 CST 2021].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:param name="code"/>
   <xsl:param name="message"/>
   <xsl:param name="detail"/>
   <xsl:param name="instanceId"/>
   <xsl:param name="trackingId"/>
   <xsl:template match="/">
      <tns:QueryItemResponseEBM>
         <tns:Response>
            <com:Code>
               <xsl:value-of select="$code"/>
            </com:Code>
            <com:Message>
               <xsl:value-of select="$message"/>
            </com:Message>
            <com:Detail>
               <xsl:value-of select="$detail"/>
            </com:Detail>
            <com:InstanceID>
               <xsl:value-of select="$instanceId"/>
            </com:InstanceID>
            <com:TrackingID>
               <xsl:value-of select="$trackingId"/>
            </com:TrackingID>
            <com:ValueSet>
               <com:Identification>
                  <com:ID>
                     <xsl:value-of select="/ns0:XxSoaItemVwCollection/ns0:XxSoaItemVw/ns0:supplier"/>
                  </com:ID>
               </com:Identification>
            </com:ValueSet>
         </tns:Response>
      </tns:QueryItemResponseEBM>
   </xsl:template>
</xsl:stylesheet>