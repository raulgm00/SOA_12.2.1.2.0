<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:tns="http://xmlns.banesco.com/eopt/AcctTrnAddAdv_V1.0" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:ns1="http://xmlns.banesco.com/eopt/Acct345Adv_V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ns0="http://xmlns.banesco.com/eopt/AcctTrnAddAdvInq_V1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns1 ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Account/AcctTrnAddAdvInq/AcctTrnAddAdvInq_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="AcctTrnAddAdvACHInqRs" namespace="http://xmlns.banesco.com/eopt/AcctTrnAddAdvInq_V1.0"/>
         </oracle-xsl-mapper:source>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Account/Acct345Adv/Acct345Adv_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="Acct345AdvRq" namespace="http://xmlns.banesco.com/eopt/Acct345Adv_V1.0"/>
            <oracle-xsl-mapper:param name="varRq"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Account/AcctTrnAddAdv/AcctTrnAddAdv_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="AcctTrnAddAdvRq" namespace="http://xmlns.banesco.com/eopt/AcctTrnAddAdv_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [TUE SEP 25 15:43:39 GMT-05:00 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:param name="varRq"/>
   <xsl:template match="/">
      <tns:AcctTrnAddAdvRq>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:AcctStatus/text()">
            <tns:AcctStatus>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:AcctStatus"/>
            </tns:AcctStatus>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:AvailAmt/text()">
            <tns:AvailAmt>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:AvailAmt"/>
            </tns:AvailAmt>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:AvailAmtSign/text()">
            <tns:AvailAmtSign>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:AvailAmtSign"/>
            </tns:AvailAmtSign>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:BankDes/text()">
            <tns:BankDes>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:BankDes"/>
            </tns:BankDes>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:BillerId/text()">
            <tns:BillerId>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:BillerId"/>
            </tns:BillerId>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:BranchRegion/text()">
            <tns:BranchRegion>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:BranchRegion"/>
            </tns:BranchRegion>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:CashAmt/text()">
            <tns:CashAmt>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:CashAmt"/>
            </tns:CashAmt>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:Channel/text()">
            <tns:Channel>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:Channel"/>
            </tns:Channel>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkBookFinalNum/text()">
            <tns:ChkBookFinalNum>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkBookFinalNum"/>
            </tns:ChkBookFinalNum>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkBookInitialNum/text()">
            <tns:ChkBookInitialNum>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkBookInitialNum"/>
            </tns:ChkBookInitialNum>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkBookType/text()">
            <tns:ChkBookType>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkBookType"/>
            </tns:ChkBookType>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkNumber/text()">
            <tns:ChkNumber>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkNumber"/>
            </tns:ChkNumber>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkOtherAmt/text()">
            <tns:ChkOtherAmt>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkOtherAmt"/>
            </tns:ChkOtherAmt>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkPropAmt/text()">
            <tns:ChkPropAmt>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ChkPropAmt"/>
            </tns:ChkPropAmt>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:CompensationDt/text()">
            <tns:CompensationDt>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:CompensationDt"/>
            </tns:CompensationDt>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerIdDes/text()">
            <tns:CustomerIdDes>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerIdDes"/>
            </tns:CustomerIdDes>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerIdOr/text()">
            <tns:CustomerIdOr>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerIdOr"/>
            </tns:CustomerIdOr>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerNameDes/text()">
            <tns:CustomerNameDes>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerNameDes"/>
            </tns:CustomerNameDes>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerNameOr/text()">
            <tns:CustomerNameOr>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerNameOr"/>
            </tns:CustomerNameOr>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerSince/text()">
            <tns:CustomerSince>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:CustomerSince"/>
            </tns:CustomerSince>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:DebitCredit/text()">
            <tns:DebitCredit>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:DebitCredit"/>
            </tns:DebitCredit>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:Email/text()">
            <tns:Email>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:Email"/>
            </tns:Email>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:EmployeeAcctDes/text()">
            <tns:EmployeeAcctDes>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:EmployeeAcctDes"/>
            </tns:EmployeeAcctDes>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:EmployeeAcctOr/text()">
            <tns:EmployeeAcctOr>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:EmployeeAcctOr"/>
            </tns:EmployeeAcctOr>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:EmployeeId/text()">
            <tns:EmployeeId>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:EmployeeId"/>
            </tns:EmployeeId>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:EntityCode/text()">
            <tns:EntityCode>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:EntityCode"/>
            </tns:EntityCode>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:FlagVip/text()">
            <tns:FlagVip>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:FlagVip"/>
            </tns:FlagVip>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:HousePhone/text()">
            <tns:HousePhone>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:HousePhone"/>
            </tns:HousePhone>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:LastMovDt/text()">
            <tns:LastMovDt>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:LastMovDt"/>
            </tns:LastMovDt>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:MessageType/text()">
            <tns:MessageType>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:MessageType"/>
            </tns:MessageType>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:OfficePhone/text()">
            <tns:OfficePhone>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:OfficePhone"/>
            </tns:OfficePhone>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:OpenAcctDate/text()">
            <tns:OpenAcctDate>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:OpenAcctDate"/>
            </tns:OpenAcctDate>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:OpenBranchDes/text()">
            <tns:OpenBranchDes>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:OpenBranchDes"/>
            </tns:OpenBranchDes>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:OpenBranchOr/text()">
            <tns:OpenBranchOr>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:OpenBranchOr"/>
            </tns:OpenBranchOr>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:OtherPhone/text()">
            <tns:OtherPhone>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:OtherPhone"/>
            </tns:OtherPhone>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:OverdueAmt/text()">
            <tns:OverdueAmt>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:OverdueAmt"/>
            </tns:OverdueAmt>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:OverdueIndicator/text()">
            <tns:OverdueIndicator>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:OverdueIndicator"/>
            </tns:OverdueIndicator>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:PersonAddr/text()">
            <tns:PersonAddr>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:PersonAddr"/>
            </tns:PersonAddr>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:PersonalId/text()">
            <tns:PersonalId>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:PersonalId"/>
            </tns:PersonalId>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:PersonalIdDes/text()">
            <tns:PersonalIdDes>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:PersonalIdDes"/>
            </tns:PersonalIdDes>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:PersonType/text()">
            <tns:PersonType>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:PersonType"/>
            </tns:PersonType>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ProdTypeDes/text()">
            <tns:ProdTypeDes>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ProdTypeDes"/>
            </tns:ProdTypeDes>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ProductCode/text()">
            <tns:ProductCode>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ProductCode"/>
            </tns:ProductCode>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:RecordStatus/text()">
            <tns:RecordStatus>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:RecordStatus"/>
            </tns:RecordStatus>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ResponseCode/text()">
            <tns:ResponseCode>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ResponseCode"/>
            </tns:ResponseCode>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:Segment/text()">
            <tns:Segment>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:Segment"/>
            </tns:Segment>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:SerProvId/text()">
            <tns:SerProvId>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:SerProvId"/>
            </tns:SerProvId>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:ServName/text()">
            <tns:ServName>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:ServName"/>
            </tns:ServName>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:SubProdCode/text()">
            <tns:SubProdCode>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:SubProdCode"/>
            </tns:SubProdCode>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:SvcPaymentType/text()">
            <tns:SvcPaymentType>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:SvcPaymentType"/>
            </tns:SvcPaymentType>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:TransferKey/text()">
            <tns:TransferKey>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:TransferKey"/>
            </tns:TransferKey>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnAccountDes/text()">
            <tns:TxnAccountDes>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnAccountDes"/>
            </tns:TxnAccountDes>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnAccountOr/text()">
            <tns:TxnAccountOr>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnAccountOr"/>
            </tns:TxnAccountOr>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnBranch/text()">
            <tns:TxnBranch>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnBranch"/>
            </tns:TxnBranch>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnCurrency/text()">
            <tns:TxnCurrency>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnCurrency"/>
            </tns:TxnCurrency>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnDate/text()">
            <tns:TxnDate>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnDate"/>
            </tns:TxnDate>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnRef/text()">
            <tns:TxnRef>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnRef"/>
            </tns:TxnRef>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnTime/text()">
            <tns:TxnTime>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnTime"/>
            </tns:TxnTime>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnTotalAmt/text()">
            <tns:TxnTotalAmt>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:TxnTotalAmt"/>
            </tns:TxnTotalAmt>
         </xsl:if>
         <xsl:if test="/ns0:AcctTrnAddAdvACHInqRs/ns0:UserType/text()">
            <tns:UserType>
               <xsl:value-of select="/ns0:AcctTrnAddAdvACHInqRs/ns0:UserType"/>
            </tns:UserType>
         </xsl:if>
      </tns:AcctTrnAddAdvRq>
   </xsl:template>
</xsl:stylesheet>
