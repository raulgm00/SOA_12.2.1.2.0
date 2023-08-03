<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction"
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
                xmlns:ns0="http://sms.totaltexto.com/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction"
                xmlns:tns="http://xmlns.banesco.com/eopt/AlertSmsTTextoAdv_V1.0"
                exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsp="http://www.w3.org/ns/ws-policy"
                xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata"
                xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:eoptPartyOnlineBankInq="http://xmlns.banesco.com/eopt/PartyOnlineBankInq_V1.0"
                xmlns:ns1="http://xmlns.banesco.com/TechLayer/UtilityTechSvc_V1.0"
                xmlns:ns6="http://xmlns.banesco.com/eo/Product_V1.0"
                xmlns:eoptAdminOnlineBankAddAdvise="http://xmlns.banesco.com/eopt/AdminOnlineBankAddAdvise_V1.0"
                xmlns:eoptPmtSessionInq="http://xmlns.banesco.com/eopt/PmtSessionInq_V1.0"
                xmlns:ns7="http://xmlns.banesco.com/eo/Acct_V1.0" xmlns:ns8="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:eoptChannelTrnAudHistInq="http://xmlns.banesco.com/eopt/ChannelTrnAudHistInq_V1.0"
                xmlns:eoptAlertEmailAdv="http://xmlns.banesco.com/eopt/AlertEmailAdv_V1.0"
                xmlns:ns12="http://xmlns.banesco.com/eo/Chk_V1.0"
                xmlns:eoptTrnPmtInq="http://xmlns.banesco.com/eopt/TrnPmtInq_V1.0"
                xmlns:ns2="http://xmlns.banesco.com/appopt/AOptCommon_V1.0"
                xmlns:ns3="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:eoptOnlineBankTrnAddAdvise="http://xmlns.banesco.com/eopt/OnlineBankTrnAddAdvise_V1.0"
                xmlns:ns4="http://xmlns.banesco.com/eo/Alert_V1.0"
                xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:ns5="http://xmlns.banesco.com/eo/Fee_V1.0"
                xmlns:eoptAlertSmsQuanticAdv="http://xmlns.banesco.com/eopt/AlertSmsQuanticAdv_V1.0"
                xmlns:ns9="http://xmlns.banesco.com/eo/Card_V1.0" xmlns:ns10="http://xmlns.banesco.com/eo/Payee_V1.0"
                xmlns:ns11="http://xmlns.banesco.com/eo/Common_V1.0" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/"
                xmlns:eoptChannelTrnAudHistAdd="http://xmlns.banesco.com/eopt/ChannelTrnAudHistAdd_V1.0">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="WSDL">
        <oracle-xsl-mapper:schema location="../../../../Commons/backends/TTEXTO/resources/wsdl/SmsDispatcher.wsdl"/>
        <oracle-xsl-mapper:rootElement name="sendResponse" namespace="http://sms.totaltexto.com/"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="WSDL">
        <oracle-xsl-mapper:schema location="../../../../Commons/wsdl/TechLayer/Common/UtilityTechSvc_V1.0.wsdl"/>
        <oracle-xsl-mapper:rootElement name="AlertSmsTTextoAdvRs"
                                       namespace="http://xmlns.banesco.com/eopt/AlertSmsTTextoAdv_V1.0"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.3.0(XSLT Build 170820.1700.2557) AT [MON MAY 28 17:48:10 COT 2018].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <xsl:variable name="statusCode"
                  select="DVMFunctions:lookupValue ('Commons/dvm/StatusMappingBcknd', 'TTEXTO', /ns0:sendResponse/return/codError/text(), 'CNCODE', 'M0006' )"/>
    <tns:AlertSmsTTextoAdvRs>
      <tns:Status>
        <eoStatus:StatusCode>
          <xsl:value-of select="$statusCode"/>
        </eoStatus:StatusCode>
        <eoStatus:StatusDesc>
          <xsl:value-of select="DVMFunctions:lookupValue ('Commons/dvm/StatusMappingBcknd', 'TTEXTO', /ns0:sendResponse/return/codError/text(), 'CNDESC', 'FALLO EN LA OPERACION' )"/>
        </eoStatus:StatusDesc>
        <eoStatus:AdditionalStatus>
          <xsl:if test="/ns0:sendResponse/return/codError/text()">
            <eoStatus:StatusCode>
              <xsl:value-of select="/ns0:sendResponse/return/codError"/>
            </eoStatus:StatusCode>
          </xsl:if>
          <xsl:if test="/ns0:sendResponse/return/desError/text()">
            <eoStatus:StatusDesc>
              <xsl:value-of select="/ns0:sendResponse/return/desError"/>
            </eoStatus:StatusDesc>
          </xsl:if>
        </eoStatus:AdditionalStatus>
      </tns:Status>
    </tns:AlertSmsTTextoAdvRs>
  </xsl:template>
</xsl:stylesheet>
