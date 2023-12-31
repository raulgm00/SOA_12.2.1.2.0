<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:ns0="http://xmlns.banesco.com/appopt/WFAcctAppSvc/AcctSignatureGrpAdd_V1.0" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:tns="http://xmlns.banesco.com/eopt/AcctSignatureGrpAdd_V1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:appoptcommon="http://xmlns.banesco.com/appopt/AOptCommon_V1.0"
                xmlns:ns1="http://xmlns.banesco.com/eo/Banking_V1.0" xmlns:ns3="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:ns2="http://xmlns.banesco.com/eo/Common_V1.0"
                xmlns:eoSignGrp="http://xmlns.banesco.com/eo/SignatureGrp_V1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/AppOpt/WFAcct/AcctSignatureGrpAdd/AcctSignatureGrpAdd_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="AcctSignatureGrpAddRq" namespace="http://xmlns.banesco.com/appopt/WFAcctAppSvc/AcctSignatureGrpAdd_V1.0"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Account/AcctSignatureGrpAdd/AcctSignatureGrpAdd_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="AcctSignatureGrpAddRq" namespace="http://xmlns.banesco.com/eopt/AcctSignatureGrpAdd_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.3.0(XSLT Build 170820.1700.2557) AT [TUE MAY 08 10:04:14 COT 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:AcctSignatureGrpAddRq>
         <tns:SignatureGrp>
            <xsl:if test="/ns0:AcctSignatureGrpAddRq/ns0:SignatureGrp/ns0:Desc/text()">
               <eoSignGrp:Desc>
                  <xsl:value-of select="/ns0:AcctSignatureGrpAddRq/ns0:SignatureGrp/ns0:Desc"/>
               </eoSignGrp:Desc>
            </xsl:if>
            <eoSignGrp:SignGrpKey>
               <xsl:if test="/ns0:AcctSignatureGrpAddRq/ns0:SignatureGrp/ns0:SignGrpKey/ns0:SignGrpId/text()">
                  <eoSignGrp:SignGrpId>
                     <xsl:value-of select="/ns0:AcctSignatureGrpAddRq/ns0:SignatureGrp/ns0:SignGrpKey/ns0:SignGrpId"/>
                  </eoSignGrp:SignGrpId>
               </xsl:if>
            </eoSignGrp:SignGrpKey>
            <xsl:if test="/ns0:AcctSignatureGrpAddRq/ns0:SignatureGrp/ns0:Signatories">
               <eoSignGrp:Signatories>
                  <xsl:for-each select="/ns0:AcctSignatureGrpAddRq/ns0:SignatureGrp/ns0:Signatories/ns0:Signatory">
                     <eoSignGrp:Signatory>
                        <xsl:if test="ns0:EndDt/text()">
                           <eoSignGrp:EndDt>
                              <xsl:value-of select="ns0:EndDt"/>
                           </eoSignGrp:EndDt>
                        </xsl:if>
                        <xsl:if test="ns0:PartyKey/text()">
                           <eoSignGrp:PartyKey>
                              <xsl:if test="ns0:PartyKey/ns0:PartyId/text()">
                                 <ns3:PartyId>
                                    <xsl:value-of select="ns0:PartyKey/ns0:PartyId"/>
                                 </ns3:PartyId>
                              </xsl:if>
                           </eoSignGrp:PartyKey>
                        </xsl:if>
                        <xsl:if test="ns0:StartDt/text()">
                           <eoSignGrp:StartDt>
                              <xsl:value-of select="ns0:StartDt"/>
                           </eoSignGrp:StartDt>
                        </xsl:if>
                     </eoSignGrp:Signatory>
                  </xsl:for-each>
               </eoSignGrp:Signatories>
            </xsl:if>
         </tns:SignatureGrp>
      </tns:AcctSignatureGrpAddRq>
   </xsl:template>
</xsl:stylesheet>
