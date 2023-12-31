<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction"
                xmlns:head="http://xmlns.banesco.com/appopt/AOptCommon_V1.0"
                xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions"
                xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ns0="http://xmlns.banesco.com/eopt/AcctLoanLedgerAdd_V1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction"
                xmlns:tns="http://www.temenos.com/T24/ofs/Request"
                exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl head ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns4="http://xmlns.banesco.com/eo/Product_V1.0" xmlns:ns5="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:ns9="http://xmlns.banesco.com/eo/Chk_V1.0" xmlns:ns2="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:eoTran="http://xmlns.banesco.com/eo/Transfer_V1.0"
                xmlns:ns3="http://xmlns.banesco.com/eo/Fee_V1.0" xmlns:ns6="http://xmlns.banesco.com/eo/Card_V1.0"
                xmlns:ns7="http://xmlns.banesco.com/eo/Payee_V1.0" xmlns:ns8="http://xmlns.banesco.com/eo/Common_V1.0"
                xmlns:head0="http://www.temenos.com/T24/ofs/FUNDSTRANSFERBPAWSACCOUNTINGType"
                xmlns:reqcns="http://www.temenos.com/T24/ofs/RequestCommon">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/AccountLoan/AcctLoanLedgerAdd/AcctLoanLedgerAdd_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="AcctLoanLedgerAddRq"
                                       namespace="http://xmlns.banesco.com/eopt/AcctLoanLedgerAdd_V1.0"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/AppOpt/AOptCommon/AOptCommon_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="MsgHdrRq" namespace="http://xmlns.banesco.com/appopt/AOptCommon_V1.0"/>
        <oracle-xsl-mapper:param name="varHeader"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/backends/T24/resources/xsd/WSBPACREATEFTACCOUNTING/CreaciondeentradascontablesRequest.xsd"/>
        <oracle-xsl-mapper:rootElement name="FUNDSTRANSFERBPAWSACCOUNTINGRequest"
                                       namespace="http://www.temenos.com/T24/ofs/Request"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [MON MAY 14 09:07:43 GMT-05:00 2018].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:param name="varHeader"/>
  <xsl:template match="/">
    <opaq:opaqueElement xmlns:opaq="http://xmlns.oracle.com/pcbpel/adapter/opaque/">
      <tns:FUNDSTRANSFERBPAWSACCOUNTINGRequest>
        <RequestCommon>
          <UserName>
            <xsl:value-of select="$varHeader//head:ClientApp/head:UserId"/>
          </UserName>
        </RequestCommon>
        <FUNDSTRANSFERBPAWSACCOUNTING>
          <xsl:if test="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:TransferType/text()">
            <TRANSACTIONTYPE>
              <xsl:value-of select="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:TransferType"/>
            </TRANSACTIONTYPE>
          </xsl:if>
          <xsl:if test="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:FromAcctRef/ns5:AcctKey/ns5:AcctId/text()">
            <DEBITACCTNO>
              <xsl:value-of select="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:FromAcctRef/ns5:AcctKey/ns5:AcctId"/>
            </DEBITACCTNO>
          </xsl:if>
          <xsl:if test="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:FromCurAmt/ns8:CurCode/text()">
            <DEBITCURRENCY>
              <xsl:value-of select="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:FromCurAmt/ns8:CurCode"/>
            </DEBITCURRENCY>
          </xsl:if>
          <xsl:if test="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:FromCurAmt/ns8:Amt/text()">
            <DEBITAMOUNT>
              <xsl:value-of select="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:FromCurAmt/ns8:Amt"/>
            </DEBITAMOUNT>
          </xsl:if>
          <xsl:if test="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:EffDt/text()">
            <DEBITVALUEDATE>
              <xsl:value-of select="xp20:format-dateTime(/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:EffDt,'[YYYY]-[M01]-[D01]')"/>
            </DEBITVALUEDATE>
          </xsl:if>
          <xsl:if test="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:XferRef/text()">
            <DEBITTHEIRREF>
              <xsl:value-of select="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:XferRef"/>
            </DEBITTHEIRREF>
          </xsl:if>
          <xsl:if test="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:ToAcctRef/ns5:AcctKey/ns5:AcctId/text()">
            <CREDITACCTNO>
              <xsl:value-of select="/ns0:AcctLoanLedgerAddRq/ns0:TransferInfo/eoTran:ToAcctRef/ns5:AcctKey/ns5:AcctId"/>
            </CREDITACCTNO>
          </xsl:if>
          <gORDERINGCUST>
            <xsl:for-each select="/ns0:AcctLoanLedgerAddRq/ns0:PartyKey">
              <xsl:if test="eoPar:PartyId/text()">
                <ORDERINGCUST>
                  <xsl:value-of select="eoPar:PartyId"/>
                </ORDERINGCUST>
              </xsl:if>
            </xsl:for-each>
          </gORDERINGCUST>
        </FUNDSTRANSFERBPAWSACCOUNTING>
      </tns:FUNDSTRANSFERBPAWSACCOUNTINGRequest>
    </opaq:opaqueElement>
  </xsl:template>
</xsl:stylesheet>
