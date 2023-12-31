<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:ns0="http://xmlns.banesco.com/eopt/tdcCardAcctRelInq" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:tns="http://xmlns.banesco.com/eopt/CardAcctRelInq_V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:eoptTdcCardAcctTrnInq="http://xmlns.banesco.com/eopt/tdcCardAcctTrnInq"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:eoptTdcPmtRev="http://xmlns.banesco.com/eopt/tdcPmtRev"
                xmlns:eoptTdcPmtAdd="http://xmlns.banesco.com/eopt/tdcPmtAdd"
                xmlns:ns1="http://xmlns.banesco.com/service/WAY4Svc" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:eoAcct="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:eoCardAcctRel="http://xmlns.banesco.com/eo/CardAcctRel_V1.0"
                xmlns:eoBank="http://xmlns.banesco.com/eo/Banking_V1.0" xmlns:ns3="http://xmlns.banesco.com/eo/Fee_V1.0"
                xmlns:eoCard="http://xmlns.banesco.com/eo/Card_V1.0"
                xmlns:ns2="http://xmlns.banesco.com/eo/Common_V1.0"
                xmlns:eoptCambiarEstatusTDD="http://xmlns.banesco.com/eopt/CambiarEstatusTDD">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="WSDL">
            <oracle-xsl-mapper:schema location="../../../../Commons/backends/WAY4/resources/wsdl/WebServiceWAY4SvcMock.wsdl"/>
            <oracle-xsl-mapper:rootElement name="tdcCardAcctRelInqRs" namespace="http://xmlns.banesco.com/eopt/tdcCardAcctRelInq"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Card/CardAcctRelInq/CardAcctRelInq_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="CardAcctRelInqRs" namespace="http://xmlns.banesco.com/eopt/CardAcctRelInq_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.1.0(XSLT Build 160608.1900.0023) AT [FRI AUG 19 00:01:42 COT 2016].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:CardAcctRelInqRs>
         <tns:Status>
            <eoStatus:StatusCode>
               <xsl:value-of select="DVMFunctions:lookupValue (&quot;Commons/dvm/StatusMappingBcknd&quot;, &quot;WAY4&quot;, /ns0:tdcCardAcctRelInqRs/ns0:Status/ns0:StatusCode, &quot;CNCODE&quot;, &quot;CouldNotBeFound&quot; )"/>
            </eoStatus:StatusCode>
            <eoStatus:StatusDesc>
               <xsl:value-of select="DVMFunctions:lookupValue (&quot;Commons/dvm/StatusMappingBcknd&quot;, &quot;WAY4&quot;, /ns0:tdcCardAcctRelInqRs/ns0:Status/ns0:StatusCode, &quot;CNDESC&quot;, &quot;CouldNotBeFound&quot; )"/>
            </eoStatus:StatusDesc>
            <eoStatus:AdditionalStatus>
               <eoStatus:StatusCode>
                  <xsl:value-of select="/ns0:tdcCardAcctRelInqRs/ns0:Status/ns0:StatusCode"/>
               </eoStatus:StatusCode>
               <eoStatus:StatusDesc>
                  <xsl:value-of select="/ns0:tdcCardAcctRelInqRs/ns0:Status/ns0:StatusDesc"/>
               </eoStatus:StatusDesc>
            </eoStatus:AdditionalStatus>
         </tns:Status>
         <xsl:for-each select="/ns0:tdcCardAcctRelInqRs/ns0:CardAcctRel">
            <tns:CardAcctRel>
               <eoCardAcctRel:Acct>
                  <eoAcct:CurCode>
                     <ns2:Value>
                        <xsl:value-of select="ns0:Card/ns0:CurCodeValue"/>
                     </ns2:Value>
                  </eoAcct:CurCode>
                  <xsl:for-each select="ns0:CardBalances">
                     <eoAcct:AcctBal>
                        <eoAcct:BalType>
                           <xsl:value-of select="DVMFunctions:lookupValue (&quot;Commons/dvm/AttrValues&quot;, &quot;WAY4&quot;, ns0:BalType, &quot;CANONICAL&quot;, &quot;CouldNotBeFound&quot; )"/>
                        </eoAcct:BalType>
                        <eoAcct:CurAmt>
                           <ns2:Amt>
                              <xsl:value-of select="ns0:CurAmt/ns0:Amt"/>
                           </ns2:Amt>
                        </eoAcct:CurAmt>
                     </eoAcct:AcctBal>
                  </xsl:for-each>
                  <eoAcct:IntRateData>
                     <eoAcct:RateAmt>
                        <ns2:Amt>
                           <xsl:value-of select="ns0:Card/ns0:IntRateData/ns0:RateAmt/ns0:Amt"/>
                        </ns2:Amt>
                     </eoAcct:RateAmt>
                  </eoAcct:IntRateData>
               </eoCardAcctRel:Acct>
               <eoCardAcctRel:Card>
                  <eoCard:Brand>
                     <xsl:value-of select="ns0:Card/ns0:Brand"/>
                  </eoCard:Brand>
                  <eoCard:CardNum>
                     <xsl:value-of select="ns0:Card/ns0:CardNum"/>
                  </eoCard:CardNum>
                  <eoCard:CardRel>
                     <xsl:value-of select="ns0:Card/ns0:CardRel"/>
                  </eoCard:CardRel>
                  <eoCard:CutDt>
                     <xsl:value-of select="concat (xp20:format-dateTime (ns0:Card/ns0:CutDt, &quot;[Y0001]-[M01]-[D01]&quot; ), xp20:timezone-from-dateTime (xp20:current-dateTime ( ) ) )"/>
                  </eoCard:CutDt>
                  <eoCard:DueFees>
                     <xsl:value-of select="ns0:Card/ns0:DueFees"/>
                  </eoCard:DueFees>
                  <eoCard:ExpDt>
                     <xsl:value-of select="concat(xp20:format-dateTime(ns0:Card/ns0:ExpDt,'[Y0001]-[M01]-[D01]T[H01]:[m01]:[s01]'),xp20:timezone-from-dateTime(xp20:current-dateTime()))"/>
                  </eoCard:ExpDt>
                  <eoCard:LastPmtDt>
                     <xsl:value-of select="concat (xp20:format-dateTime (ns0:Card/ns0:LastPmtDt, &quot;[Y0001]-[M01]-[D01]&quot; ), xp20:timezone-from-dateTime (xp20:current-dateTime ( ) ) )"/>
                  </eoCard:LastPmtDt>
                  <eoCard:NextPmtDt>
                     <xsl:value-of select="concat (xp20:format-dateTime (ns0:Card/ns0:NextPmtDt, &quot;[Y0001]-[M01]-[D01]&quot; ), xp20:timezone-from-dateTime (xp20:current-dateTime ( ) ) )"/>
                  </eoCard:NextPmtDt>
                  <eoCard:PmtLimitDt>
                     <xsl:value-of select="concat (xp20:format-dateTime (ns0:Card/ns0:PmtLimitDt, &quot;[Y0001]-[M01]-[D01]&quot; ), xp20:timezone-from-dateTime (xp20:current-dateTime ( ) ) )"/>
                  </eoCard:PmtLimitDt>
                  <eoCard:CardStatus>
                     <eoCard:CardStatusCode>
                        <xsl:value-of select="ns0:Card/ns0:CardStatusCode"/>
                     </eoCard:CardStatusCode>
                  </eoCard:CardStatus>
                  <xsl:for-each select="ns0:Card/ns0:ExtLimit">
                     <eoCard:ExtLimit>
                        <eoCard:ChargedFees>
                           <xsl:value-of select="ns0:ChargedFees"/>
                        </eoCard:ChargedFees>
                        <eoCard:ChargedFeesAmt>
                           <!--xsl:value-of select="ns0:ExtLimitBalances[BalType='cuotas-cobradas']/ns0:CurAmt/ns0:Amt"/-->
                           <xsl:for-each select="ns0:ExtLimitBalances">
                            <xsl:if test="ns0:BalType='cuotas-cobradas'">
                              <xsl:value-of select="ns0:CurAmt/ns0:Amt"/>
                            </xsl:if>
                           </xsl:for-each>
                        </eoCard:ChargedFeesAmt>
                        <eoCard:ContractNum>
                           <xsl:value-of select="ns0:ContractNum"/>
                        </eoCard:ContractNum>
                        <eoCard:CreditLimitAmt>
                           <!--xsl:value-of select="ns0:ExtLimitBalances[BalType='total-extrafinanciamiento']/ns0:CurAmt/ns0:Amt"/-->
                           <xsl:for-each select="ns0:ExtLimitBalances">
                            <xsl:if test="ns0:BalType='total-extrafinanciamiento'">
                              <xsl:value-of select="ns0:CurAmt/ns0:Amt"/>
                            </xsl:if>
                           </xsl:for-each>
                        </eoCard:CreditLimitAmt>
                        <eoCard:CurBalance>
                           <!--xsl:value-of select="ns0:ExtLimitBalances[BalType='saldo-actual']/ns0:CurAmt/ns0:Amt"/-->
                           <xsl:for-each select="ns0:ExtLimitBalances">
                            <xsl:if test="ns0:BalType='saldo-actual'">
                              <xsl:value-of select="ns0:CurAmt/ns0:Amt"/>
                            </xsl:if>
                           </xsl:for-each>
                        </eoCard:CurBalance>
                        <eoCard:CutBalance>
                           <!--xsl:value-of select="ns0:ExtLimitBalances[BalType='saldo-ultima-facturacion']/ns0:CurAmt/ns0:Amt"/-->
                           <xsl:for-each select="ns0:ExtLimitBalances">
                            <xsl:if test="ns0:BalType='saldo-ultima-facturacion'">
                              <xsl:value-of select="ns0:CurAmt/ns0:Amt"/>
                            </xsl:if>
                           </xsl:for-each>
                        </eoCard:CutBalance>
                        <eoCard:FinancingType>
                           <xsl:value-of select="ns0:FinancingType"/>
                        </eoCard:FinancingType>
                        <eoCard:GrantDt>
                           <xsl:value-of select="concat (xp20:format-dateTime (ns0:GrantDt, &quot;[Y0001]-[M01]-[D01]&quot; ), xp20:timezone-from-dateTime (xp20:current-dateTime ( ) ) )"/>
                        </eoCard:GrantDt>
                        <eoCard:PrevBalance>
                           <!--xsl:value-of select="ns0:ExtLimitBalances[BalType='saldo-anterior']/ns0:CurAmt/ns0:Amt"/-->
                           <xsl:for-each select="ns0:ExtLimitBalances">
                            <xsl:if test="ns0:BalType='saldo-anterior'">
                              <xsl:value-of select="ns0:CurAmt/ns0:Amt"/>
                            </xsl:if>
                           </xsl:for-each>
                        </eoCard:PrevBalance>
                     </eoCard:ExtLimit>
                  </xsl:for-each>
               </eoCardAcctRel:Card>
            </tns:CardAcctRel>
         </xsl:for-each>
      </tns:CardAcctRelInqRs>
   </xsl:template>
</xsl:stylesheet>
