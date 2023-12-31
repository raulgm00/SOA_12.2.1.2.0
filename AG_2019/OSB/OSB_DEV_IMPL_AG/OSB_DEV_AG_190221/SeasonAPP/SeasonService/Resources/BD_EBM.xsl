<xsl:stylesheet version="1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:tns="http://www.agarcia.mx/EnterpriseObjects/Season" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/top/DBSeasonRMS" xmlns:com="http://www.agarcia.mx/EnterpriseObjects/Common" xmlns:ns1="http://www.agarcia.mx/EnterpriseObjects/Custom">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="DBSeasonRMS_table.xsd"/>
            <oracle-xsl-mapper:rootElement name="XxSoaSeasonsVwCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/DBSeasonRMS"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../MetaData/Components/EnterpriseObjectLibrary/EBO/Season/SeasonEBM.xsd"/>
            <oracle-xsl-mapper:rootElement name="CreateSeasonEBM" namespace="http://www.agarcia.mx/EnterpriseObjects/Season"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [WED SEP 23 15:27:27 CDT 2020].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:param name="UUID"/>
   <xsl:template match="/">
      <tns:CreateSeasonEBM>
         <tns:Header>
            <com:TrackingID>
               <xsl:value-of select="xp20:current-date ( )"/>
            </com:TrackingID>
            <com:InstanceID>
               <xsl:value-of select="$UUID"/>
            </com:InstanceID>
         </tns:Header>
         <tns:DataArea>
            <xsl:for-each select="//*:XxSoaSeasonsVwCollection/*:XxSoaSeasonsVw">
               <tns:CreateSeason>
                  <tns:Identification>
                     <com:ID>
                        <xsl:value-of select="ns0:seasonId"/>
                     </com:ID>
                     <com:ApplicationObjectReference>
                        <com:ApplicationName>
                           <xsl:value-of select="ns0:seasonDesc"/>
                        </com:ApplicationName>
                        <com:ApplicationObjectID>
                           <xsl:value-of select="ns0:phaseId"/>
                        </com:ApplicationObjectID>
                     </com:ApplicationObjectReference>
                  </tns:Identification>
                  <tns:Description>
                     <xsl:value-of select="ns0:phaseDesc"/>
                  </tns:Description>
                  <tns:EffectiveTimePeriod>
                     <com:StartDateTime>
                        <xsl:value-of select="ns0:seasonStartDate"/>
                     </com:StartDateTime>
                     <com:EndDateTime>
                        <xsl:value-of select="ns0:seasonEndDate"/>
                     </com:EndDateTime>
                  </tns:EffectiveTimePeriod>
                  <tns:EntityHistoryReferenceType>
                     <com:CreationDate>
                        <xsl:value-of select="ns0:phaseStartDate"/>
                     </com:CreationDate>
                     <com:LastUpdateDate>
                        <xsl:value-of select="ns0:phaseEndDate"/>
                     </com:LastUpdateDate>
                  </tns:EntityHistoryReferenceType>
               </tns:CreateSeason>
            </xsl:for-each>
            <tns:SeasonRecordHistory>
               <com:LastUpdateDate>
                  <xsl:value-of select="//*:XxSoaSeasonsVwCollection/*:XxSoaSeasonsVw/*:lastUpdateDate"/>
               </com:LastUpdateDate>
            </tns:SeasonRecordHistory>
         </tns:DataArea>
      </tns:CreateSeasonEBM>
   </xsl:template>
</xsl:stylesheet>