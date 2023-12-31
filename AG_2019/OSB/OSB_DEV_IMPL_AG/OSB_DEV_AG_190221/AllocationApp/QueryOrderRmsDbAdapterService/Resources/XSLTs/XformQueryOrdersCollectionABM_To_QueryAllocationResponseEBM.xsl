<xsl:stylesheet version="1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:tns="http://www.agarcia.mx/EnterpriseObjects/Allocation" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/top/QueryOrderRMSDBReference" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/PurchaseOrder" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns2="http://www.agarcia.mx/EnterpriseObjects/Custom" xmlns:ns3="http://xmlns.oracle.com/pcbpel/adapter/db/top/QueryAllocationRMSDbReference">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../XSDs/QueryOrderRMSDBReference_table.xsd"/>
            <oracle-xsl-mapper:rootElement name="XxSoaOrdersVwCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/QueryOrderRMSDBReference"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../MetaData/Components/EnterpriseObjectLibrary/EBO/Allocation/AllocationEBM.xsd"/>
            <oracle-xsl-mapper:rootElement name="QueryAllocationResponseEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/Allocation"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [MON OCT 19 17:46:43 CDT 2020].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:param name="Code"/>
   <xsl:param name="Message"/>
   <xsl:param name="Detail"/>
   <xsl:param name="TrackingID"/>
   <xsl:param name="InstanceID"/>
   <xsl:template match="/">
      <tns:QueryAllocationResponseEBM>
         <tns:Response>
            <com:Code>
               <xsl:value-of select="$Code"/>
            </com:Code>
            <com:Message>
               <xsl:value-of select="$Message"/>
            </com:Message>
            <com:Detail>
               <xsl:value-of select="$Detail"/>
            </com:Detail>
            <com:InstanceID>
               <xsl:value-of select="$InstanceID"/>
            </com:InstanceID>
            <com:TrackingID>
               <xsl:value-of select="$TrackingID"/>
            </com:TrackingID>
         </tns:Response>
         <tns:QueryAllocationReference>
            <tns:POAllocationEBO>
               <ns1:Promotion>
                  <xsl:value-of select="/ns0:XxSoaOrdersVwCollection/ns0:XxSoaOrdersVw/ns0:promotion"/>
               </ns1:Promotion>
               <ns1:EffectiveTimePeriod>
                  <com:StartDateTime>
                     <xsl:value-of select="xp20:add-dayTimeDuration-to-dateTime(/ns0:XxSoaOrdersVwCollection/ns0:XxSoaOrdersVw/ns0:notAfterDate,&quot;P1D&quot;)"/>
                  </com:StartDateTime>
                  <com:EndDateTime>
                     <xsl:value-of select="/ns0:XxSoaOrdersVwCollection/ns0:XxSoaOrdersVw/ns0:notAfterDate"/>
                  </com:EndDateTime>
               </ns1:EffectiveTimePeriod>
               <ns1:BudgetDate>
                  <xsl:value-of select="/ns0:XxSoaOrdersVwCollection/ns0:XxSoaOrdersVw/ns0:otbEowDate"/>
               </ns1:BudgetDate>
            </tns:POAllocationEBO>
         </tns:QueryAllocationReference>
      </tns:QueryAllocationResponseEBM>
   </xsl:template>
</xsl:stylesheet>