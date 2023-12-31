<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:tns="http://xmlns.banesco.com/appopt/TCIBCardCrAppSvc/CardCrTransferAdd_V1.0" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:ns0="http://xmlns.banesco.com/eopt/CardCrTransferAdd_V1.0" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns2="http://xmlns.banesco.com/eo/Product_V1.0" xmlns:ns3="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:eoTrn="http://xmlns.banesco.com/eo/Trn_V1.0" xmlns:ns7="http://xmlns.banesco.com/eo/Chk_V1.0"
                xmlns:eoBank="http://xmlns.banesco.com/eo/Banking_V1.0" xmlns:ns1="http://xmlns.banesco.com/eo/Fee_V1.0"
                xmlns:ns4="http://xmlns.banesco.com/eo/Card_V1.0" xmlns:ns5="http://xmlns.banesco.com/eo/Payee_V1.0"
                xmlns:eoBill="http://xmlns.banesco.com/eo/Bill_V1.0"
                xmlns:ns6="http://xmlns.banesco.com/eo/Common_V1.0"
                xmlns:appoptcommon="http://xmlns.banesco.com/appopt/AOptCommon_V1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/CardCr/CardCrTransferAdd/CardCrTransferAdd_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="CardCrTransferAddRs" namespace="http://xmlns.banesco.com/eopt/CardCrTransferAdd_V1.0"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/AppOpt/TCIBCardCr/CardCrTransferAdd/CardCrTransferAdd_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="CardCrTransferAddRs" namespace="http://xmlns.banesco.com/appopt/TCIBCardCrAppSvc/CardCrTransferAdd_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.2.0(XSLT Build 161003.0739.0018) AT [THU JUL 19 08:24:02 GMT-05:00 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:CardCrTransferAddRs>
         <xsl:if test="/ns0:CardCrTransferAddRs/ns0:FIData">
            <tns:FIData>
               <xsl:if test="/ns0:CardCrTransferAddRs/ns0:FIData/eoBank:FIIdent/text()">
                  <tns:FIIdent>
                     <xsl:value-of select="/ns0:CardCrTransferAddRs/ns0:FIData/eoBank:FIIdent"/>
                  </tns:FIIdent>
               </xsl:if>
               <tns:Agency>
                  <xsl:if test="/ns0:CardCrTransferAddRs/ns0:FIData/eoBank:Agency/eoBank:AgencyIdent/text()">
                     <tns:AgencyIdent>
                        <xsl:value-of select="/ns0:CardCrTransferAddRs/ns0:FIData/eoBank:Agency/eoBank:AgencyIdent"/>
                     </tns:AgencyIdent>
                  </xsl:if>
               </tns:Agency>
            </tns:FIData>
         </xsl:if>
         <xsl:if test="/ns0:CardCrTransferAddRs/ns0:PartyKey">
            <tns:Party>
               <tns:PartyKey>
                  <xsl:if test="/ns0:CardCrTransferAddRs/ns0:PartyKey/eoPar:PartyId/text()">
                     <tns:PartyId>
                        <xsl:value-of select="/ns0:CardCrTransferAddRs/ns0:PartyKey/eoPar:PartyId"/>
                     </tns:PartyId>
                  </xsl:if>
               </tns:PartyKey>
            </tns:Party>
         </xsl:if>
         <tns:Status>
            <appoptcommon:StatusCode>
               <xsl:value-of select="/ns0:CardCrTransferAddRs/ns0:Status/eoStatus:StatusCode"/>
            </appoptcommon:StatusCode>
            <appoptcommon:StatusDesc>
               <xsl:value-of select="/ns0:CardCrTransferAddRs/ns0:Status/eoStatus:StatusDesc"/>
            </appoptcommon:StatusDesc>
         </tns:Status>
         <xsl:if test="/ns0:CardCrTransferAddRs/ns0:Trn">
            <tns:Trn>
               <tns:CardKey>
                  <tns:CardNum>
                     <xsl:value-of select="/ns0:CardCrTransferAddRs/ns0:Trn/eoTrn:CardKey/ns4:CardNum"/>
                  </tns:CardNum>
               </tns:CardKey>
            </tns:Trn>
         </xsl:if>
      </tns:CardCrTransferAddRs>
   </xsl:template>
</xsl:stylesheet>
