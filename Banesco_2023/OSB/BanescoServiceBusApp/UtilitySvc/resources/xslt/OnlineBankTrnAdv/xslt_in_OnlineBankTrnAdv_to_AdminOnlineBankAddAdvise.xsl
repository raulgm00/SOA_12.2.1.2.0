<xsl:stylesheet version="1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:ns0="http://xmlns.banesco.com/eopt/OnlineBankTrnAdv_V1.0" xmlns:tns="http://xmlns.banesco.com/eopt/AdminOnlineBankAddAdvise_V1.0" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:ns1="http://xmlns.banesco.com/eo/Banking_V1.0" xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0" xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0" xmlns:eoCom="http://xmlns.banesco.com/eo/Common_V1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Utility/OnlineBankTrnAdv/OnlineBankTrnAdv_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="OnlineBankTrnAdvRq" namespace="http://xmlns.banesco.com/eopt/OnlineBankTrnAdv_V1.0"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Utility/AdminOnlineBankAddAdvise/AdminOnlineBankAddAdvise_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="AdminOnlineBankAddAdviseRq" namespace="http://xmlns.banesco.com/eopt/AdminOnlineBankAddAdvise_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [WED MAY 23 14:00:51 COT 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:AdminOnlineBankAddAdviseRq>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:ConfNumber">
            <tns:ConfNumber>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:ConfNumber"/>
            </tns:ConfNumber>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:CreditAcctId">
            <tns:CreditAcctId>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:CreditAcctId"/>
            </tns:CreditAcctId>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:CreditAmt">
            <tns:CreditAmt>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:CreditAmt/eoCom:Amt">
                  <eoCom:Amt>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:CreditAmt/eoCom:Amt"/>
                  </eoCom:Amt>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:CreditAmt/eoCom:CurCode">
                  <eoCom:CurCode>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:CreditAmt/eoCom:CurCode"/>
                  </eoCom:CurCode>
               </xsl:if>
            </tns:CreditAmt>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:DebitAcctId">
            <tns:DebitAcctId>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:DebitAcctId"/>
            </tns:DebitAcctId>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:DebitAmt">
            <tns:DebitAmt>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:DebitAmt/eoCom:Amt">
                  <eoCom:Amt>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:DebitAmt/eoCom:Amt"/>
                  </eoCom:Amt>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:DebitAmt/eoCom:CurCode">
                  <eoCom:CurCode>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:DebitAmt/eoCom:CurCode"/>
                  </eoCom:CurCode>
               </xsl:if>
            </tns:DebitAmt>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:EffDt">
            <tns:EffDt>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:EffDt"/>
            </tns:EffDt>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:EventNum">
            <tns:EventNum>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:EventNum"/>
            </tns:EventNum>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:IdApp">
            <tns:IdApp>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:IdApp"/>
            </tns:IdApp>
         </xsl:if>
         <tns:IdRef>
            <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:IdRef"/>
         </tns:IdRef>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:OrigDt">
            <tns:OrigDt>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:OrigDt"/>
            </tns:OrigDt>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:OrigHr">
            <tns:OrigHr>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:OrigHr"/>
            </tns:OrigHr>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo">
            <tns:SessionInfo>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:AccesDt">
                  <eoCom:AccesDt>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:AccesDt"/>
                  </eoCom:AccesDt>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:AccesHr">
                  <eoCom:AccesHr>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:AccesHr"/>
                  </eoCom:AccesHr>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:ActionType">
                  <eoCom:ActionType>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:ActionType"/>
                  </eoCom:ActionType>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Browser">
                  <eoCom:Browser>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Browser"/>
                  </eoCom:Browser>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:BrowserVersion">
                  <eoCom:BrowserVersion>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:BrowserVersion"/>
                  </eoCom:BrowserVersion>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Channel">
                  <eoCom:Channel>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Channel"/>
                  </eoCom:Channel>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:City">
                  <eoCom:City>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:City"/>
                  </eoCom:City>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Country">
                  <eoCom:Country>
                     <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Country/eoCom:CountryCode">
                        <eoCom:CountryCode>
                           <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Country/eoCom:CountryCode"/>
                        </eoCom:CountryCode>
                     </xsl:if>
                     <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Country/eoCom:CountryName">
                        <eoCom:CountryName>
                           <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Country/eoCom:CountryName"/>
                        </eoCom:CountryName>
                     </xsl:if>
                  </eoCom:Country>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:DNADevice">
                  <eoCom:DNADevice>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:DNADevice"/>
                  </eoCom:DNADevice>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:FailedAttempts">
                  <eoCom:FailedAttempts>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:FailedAttempts"/>
                  </eoCom:FailedAttempts>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:IBUserId">
                  <eoCom:IBUserId>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:IBUserId"/>
                  </eoCom:IBUserId>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:isNotified">
                  <eoCom:isNotified>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:isNotified"/>
                  </eoCom:isNotified>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:ISPCode">
                  <eoCom:ISPCode>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:ISPCode"/>
                  </eoCom:ISPCode>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Latitude">
                  <eoCom:Latitude>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Latitude"/>
                  </eoCom:Latitude>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Longitude">
                  <eoCom:Longitude>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Longitude"/>
                  </eoCom:Longitude>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NetworkTrnData">
                  <eoCom:NetworkTrnData>
                     <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NetworkTrnData/eoCom:HostName">
                        <eoCom:HostName>
                           <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NetworkTrnData/eoCom:HostName"/>
                        </eoCom:HostName>
                     </xsl:if>
                     <eoCom:IpAddress>
                        <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NetworkTrnData/eoCom:IpAddress"/>
                     </eoCom:IpAddress>
                     <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NetworkTrnData/eoCom:MacAddress">
                        <eoCom:MacAddress>
                           <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NetworkTrnData/eoCom:MacAddress"/>
                        </eoCom:MacAddress>
                     </xsl:if>
                  </eoCom:NetworkTrnData>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NotificationDt">
                  <eoCom:NotificationDt>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NotificationDt"/>
                  </eoCom:NotificationDt>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NotificationHr">
                  <eoCom:NotificationHr>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NotificationHr"/>
                  </eoCom:NotificationHr>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NotificationMethod">
                  <eoCom:NotificationMethod>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:NotificationMethod"/>
                  </eoCom:NotificationMethod>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Prefix">
                  <eoCom:Prefix>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Prefix"/>
                  </eoCom:Prefix>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:PrivateIp">
                  <eoCom:PrivateIp>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:PrivateIp"/>
                  </eoCom:PrivateIp>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Region">
                  <eoCom:Region>
                     <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Region/eoCom:Cod">
                        <eoCom:Cod>
                           <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Region/eoCom:Cod"/>
                        </eoCom:Cod>
                     </xsl:if>
                     <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Region/eoCom:Desc">
                        <eoCom:Desc>
                           <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:Region/eoCom:Desc"/>
                        </eoCom:Desc>
                     </xsl:if>
                  </eoCom:Region>
               </xsl:if>
               <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:UserStatus">
                  <eoCom:UserStatus>
                     <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:SessionInfo/eoCom:UserStatus"/>
                  </eoCom:UserStatus>
               </xsl:if>
            </tns:SessionInfo>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:TrnCode">
            <tns:TrnCode>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:TrnCode"/>
            </tns:TrnCode>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:TrnDesc">
            <tns:TrnDesc>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:TrnDesc"/>
            </tns:TrnDesc>
         </xsl:if>
         <xsl:if test="/ns0:OnlineBankTrnAdvRq/ns0:TrnRef">
            <tns:TrnRef>
               <xsl:value-of select="/ns0:OnlineBankTrnAdvRq/ns0:TrnRef"/>
            </tns:TrnRef>
         </xsl:if>
      </tns:AdminOnlineBankAddAdviseRq>
   </xsl:template>
</xsl:stylesheet>