<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:tns="http://xmlns.banesco.com/appopt/WFAcctDepAppSvc/AcctFixDepInq_V1.0" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:ns0="http://xmlns.banesco.com/eopt/AcctFixDepInq_V1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns6="http://xmlns.banesco.com/eo/Product_V1.0"
                xmlns:eoAcct="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:ns7="http://xmlns.banesco.com/eo/Chk_V1.0" xmlns:ns3="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:ns5="http://xmlns.banesco.com/eo/Fee_V1.0" xmlns:ns1="http://xmlns.banesco.com/eo/Card_V1.0"
                xmlns:ns2="http://xmlns.banesco.com/eo/Payee_V1.0" xmlns:ns4="http://xmlns.banesco.com/eo/Common_V1.0"
                xmlns:appoptcommon="http://xmlns.banesco.com/appopt/AOptCommon_V1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/AccountDep/AcctFixDepInq/AcctFixDepInq_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="AcctFixDepInqRs" namespace="http://xmlns.banesco.com/eopt/AcctFixDepInq_V1.0"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/AppOpt/WFAcctDep/AcctFixDepInq/AcctFixDepInq_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="AcctFixDepInqRs" namespace="http://xmlns.banesco.com/appopt/WFAcctDepAppSvc/AcctFixDepInq_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [THU MAY 10 17:49:09 COT 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:AcctFixDepInqRs>
         <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct">
            <tns:Acct>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctSubtype/ns4:Cod/text()">
                  <tns:AcctSubtype>
                     <ns4:Cod>
                        <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctSubtype/ns4:Cod"/>
                     </ns4:Cod>
                  </tns:AcctSubtype>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctTitle/text()">
                  <tns:AcctTitle>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctTitle"/>
                  </tns:AcctTitle>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctType/ns4:Cod/text()">
                  <tns:AcctType>
                     <ns4:Cod>
                        <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctType/ns4:Cod"/>
                     </ns4:Cod>
                  </tns:AcctType>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:Activity/text()">
                  <tns:Activity>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:Activity"/>
                  </tns:Activity>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:Category/text()">
                  <tns:Category>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:Category"/>
                  </tns:Category>
               </xsl:if>               
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:ClosedDt">
                  <tns:CloseDt>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:ClosedDt"/>
                  </tns:CloseDt>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:ClubBanesco">
                  <tns:ClubBanesco>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:ClubBanesco"/>
                  </tns:ClubBanesco>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:CurCode/text()">
                  <tns:CurCode>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:CurCode"/>
                  </tns:CurCode>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:MovType/text()">
                  <tns:MovType>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:MovType"/>
                  </tns:MovType>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:Narrative/text()">
                  <tns:Narrative>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:Narrative"/>
                  </tns:Narrative>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:ProductLine/text()">
                  <tns:ProductLine>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:ProductLine"/>
                  </tns:ProductLine>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:SubCategory/text()">
                  <tns:SubCategory>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:SubCategory"/>
                  </tns:SubCategory>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:Variation/ns4:Cod/text()">
                  <tns:Variation>
                     <ns4:Cod>
                        <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:Variation/ns4:Cod"/>
                     </ns4:Cod>
                  </tns:Variation>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo">
                  <tns:AcctOpeningInfo>
                   <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:BusinessUnit/text()">
                     <tns:BusinessUnit>
                        <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:BusinessUnit"/>
                     </tns:BusinessUnit>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:EffDt/text()">
                        <tns:EffDt>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:EffDt"/>
                        </tns:EffDt>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:InitialAmt">
                        <tns:InitialAmt>
                           <xsl:if test='/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:InitialAmt/ns4:Amt/text() != ""'>
                              <appoptcommon:Amt>
                                 <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:InitialAmt/ns4:Amt"/>
                              </appoptcommon:Amt>
                           </xsl:if>
                           <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:InitialAmt/ns4:CurCode/text()">
                              <appoptcommon:CurCode>
                                 <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:InitialAmt/ns4:CurCode"/>
                              </appoptcommon:CurCode>
                           </xsl:if>
                        </tns:InitialAmt>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:OpenDt/text()">
                        <tns:OpenDt>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:OpenDt"/>
                        </tns:OpenDt>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:RqReason/text()">
                        <tns:RqReason>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:RqReason"/>
                        </tns:RqReason>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:SaleOfficer/text()">
                        <tns:SaleOfficer>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctOpeningInfo/eoAcct:SaleOfficer"/>
                        </tns:SaleOfficer>
                     </xsl:if>
                  </tns:AcctOpeningInfo>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData">
                  <tns:DepAcctData>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:ChangeActivity/text()">
                        <tns:ChangeActivity>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:ChangeActivity"/>
                        </tns:ChangeActivity>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:ChangeDateType/text()">
                        <tns:ChangeDateType>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:ChangeDateType"/>
                        </tns:ChangeDateType>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:ChangeDt/text()">
                        <tns:ChangeDt>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:ChangeDt"/>
                        </tns:ChangeDt>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:ChangePeriod/text()">
                        <tns:ChangePeriod>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:ChangePeriod"/>
                        </tns:ChangePeriod>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:PayInAcct/eoAcct:AcctId/text()">
                        <tns:PayInAcct>
                           <tns:AcctId>
                              <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:PayInAcct/eoAcct:AcctId"/>
                           </tns:AcctId>
                        </tns:PayInAcct>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:PayOutAcct/eoAcct:AcctId/text()">
                        <tns:PayOutAcct>
                           <tns:AcctId>
                              <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:PayOutAcct/eoAcct:AcctId"/>
                           </tns:AcctId>
                        </tns:PayOutAcct>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:RenewalOption/text()">
                        <tns:RenewalOption>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:RenewalOption"/>
                        </tns:RenewalOption>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:Term/eoAcct:ProposedTerm/text()">
                        <tns:Term>
                           <tns:ProposedTerm>
                              <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:Term/eoAcct:ProposedTerm"/>
                           </tns:ProposedTerm>
                        </tns:Term>
                     </xsl:if>
                     <xsl:for-each select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:DepAcctData/eoAcct:DepInterestPmt">
                        <tns:DepInterestPmt>
                           <xsl:if test="eoAcct:Freq/ns4:FreqValue/text()">
                              <tns:Freq>
                                 <appoptcommon:Every>
                                    <xsl:value-of select="eoAcct:Freq/ns4:Every"/>
                                 </appoptcommon:Every>
                                 <appoptcommon:FreqValue>
                                    <xsl:value-of select="eoAcct:Freq/ns4:FreqValue"/>
                                 </appoptcommon:FreqValue>
                                 <appoptcommon:OnDay>
                                    <xsl:value-of select="eoAcct:Freq/ns4:OnDay"/>
                                 </appoptcommon:OnDay>
                                 <appoptcommon:OnDayNumber>
                                    <xsl:value-of select="eoAcct:Freq/ns4:OnDayNumber"/>
                                 </appoptcommon:OnDayNumber>
                                 <appoptcommon:OnWeekDay>
                                    <xsl:value-of select="eoAcct:Freq/ns4:OnWeekDay"/>
                                 </appoptcommon:OnWeekDay>
                              </tns:Freq>
                           </xsl:if>
                           <xsl:if test="eoAcct:PmtClass/text()">
                              <tns:PmtClass>
                                 <xsl:value-of select="eoAcct:PmtClass"/>
                              </tns:PmtClass>
                           </xsl:if>
                           <xsl:if test="eoAcct:PmtType/text()">
                              <tns:PmtType>
                                 <xsl:value-of select="eoAcct:PmtType"/>
                              </tns:PmtType>
                           </xsl:if>
                           <xsl:if test="eoAcct:PmtDetail/eoAcct:PmtTarget/text()">
                              <tns:PmtDetail>
                                 <tns:PmtTarget>
                                    <xsl:value-of select="eoAcct:PmtDetail/eoAcct:PmtTarget"/>
                                 </tns:PmtTarget>
                              </tns:PmtDetail>
                           </xsl:if>
                        </tns:DepInterestPmt>
                     </xsl:for-each>
                  </tns:DepAcctData>
               </xsl:if>
               <xsl:for-each select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctMember">
                  <xsl:if test="eoAcct:PartyKey/eoPar:PartyId/text()">
                     <tns:AcctMember>
                        <xsl:if test="eoAcct:PartyRole/ns4:Cod/text()">
                           <tns:PartyRole>
                              <appoptcommon:Cod>
                                 <xsl:value-of select="eoAcct:PartyRole/ns4:Cod"/>
                              </appoptcommon:Cod>
                              <xsl:if test="eoAcct:PartyRole/ns4:Desc/text()">
                                 <appoptcommon:Desc>
                                    <xsl:value-of select="eoAcct:PartyRole/ns4:Desc"/>
                                 </appoptcommon:Desc>
                              </xsl:if>                              
                           </tns:PartyRole>
                        </xsl:if>
                           <tns:PartyKey>
                              <tns:PartyId>
                                 <xsl:value-of select="eoAcct:PartyKey/eoPar:PartyId"/>
                              </tns:PartyId>
                           </tns:PartyKey>                    
                     </tns:AcctMember>
                  </xsl:if>                  
               </xsl:for-each>               
               <xsl:for-each select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctBal">
                  <tns:BalType>
                     <xsl:if test="eoAcct:BalType/text()">
                        <tns:BalType>
                           <xsl:value-of select="eoAcct:BalType"/>
                        </tns:BalType>
                     </xsl:if>
                     <xsl:if test="eoAcct:CurAmt">
                        <tns:CurAmt>
                           <xsl:if test="eoAcct:CurAmt/ns4:Amt/text()">
                              <appoptcommon:Amt>
                                 <xsl:value-of select="eoAcct:CurAmt/ns4:Amt"/>
                              </appoptcommon:Amt>
                           </xsl:if>
                           <xsl:if test="eoAcct:CurAmt/ns4:CurCode/text()">
                              <appoptcommon:CurCode>
                                 <xsl:value-of select="eoAcct:CurAmt/ns4:CurCode"/>
                              </appoptcommon:CurCode>
                           </xsl:if>                           
                        </tns:CurAmt>
                     </xsl:if>
                  </tns:BalType>
               </xsl:for-each>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctKey/eoAcct:AcctReference">
                  <tns:AcctKey>
                     <tns:AcctReference>
                        <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:AcctKey/eoAcct:AcctReference"/>
                     </tns:AcctReference>
                  </tns:AcctKey>
               </xsl:if>
               <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:IntRateData">
                  <tns:IntRateData>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:IntRateData/eoAcct:EffRate/text()">
                        <tns:EffRate>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:IntRateData/eoAcct:EffRate"/>
                        </tns:EffRate>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:IntRateData/eoAcct:IntRate/text()">
                        <tns:IntRate>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:IntRateData/eoAcct:IntRate"/>
                        </tns:IntRate>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:IntRateData/eoAcct:PeriodicIndex/text()">
                        <tns:PeriodicIndex>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:IntRateData/eoAcct:PeriodicIndex"/>
                        </tns:PeriodicIndex>
                     </xsl:if>
                     <xsl:if test="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:IntRateData/eoAcct:SurTax/text()">
                        <tns:SurTax>
                           <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Acct/eoAcct:IntRateData/eoAcct:SurTax"/>
                        </tns:SurTax>
                     </xsl:if>
                  </tns:IntRateData>
               </xsl:if>
            </tns:Acct>
         </xsl:if>
         <tns:Status>
            <appoptcommon:StatusCode>
               <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Status/eoStatus:StatusCode"/>
            </appoptcommon:StatusCode>
            <xsl:choose>
               <xsl:when test="/ns0:AcctFixDepInqRs/ns0:Status/eoStatus:StatusCode = &quot;M0000&quot;">
                  <appoptcommon:StatusDesc>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Status/eoStatus:StatusDesc"/>
                  </appoptcommon:StatusDesc>
               </xsl:when>
               <xsl:otherwise>
                  <appoptcommon:StatusDesc>
                     <xsl:value-of select="/ns0:AcctFixDepInqRs/ns0:Status/eoStatus:AdditionalStatus/eoStatus:StatusDesc"/>
                  </appoptcommon:StatusDesc>
               </xsl:otherwise>
            </xsl:choose>
         </tns:Status>
      </tns:AcctFixDepInqRs>
   </xsl:template>
</xsl:stylesheet>
