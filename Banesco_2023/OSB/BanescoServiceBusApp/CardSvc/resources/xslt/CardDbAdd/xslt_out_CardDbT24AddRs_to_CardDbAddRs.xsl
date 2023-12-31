<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction" xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction" xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc" xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:ns0="http://xmlns.banesco.com/eopt/CardDbT24Add_V1.0" xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas" xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java" xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions" xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction" xmlns:tns="http://xmlns.banesco.com/eopt/CardDbAdd_V1.0" exclude-result-prefixes=" xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns3="http://xmlns.banesco.com/eo/Product_V1.0" xmlns:ns4="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:ns5="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:eoCardAcctRel="http://xmlns.banesco.com/eo/CardAcctRel_V1.0"
                xmlns:ns9="http://xmlns.banesco.com/eo/Chk_V1.0" xmlns:ns1="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:ns2="http://xmlns.banesco.com/eo/Fee_V1.0" xmlns:ns6="http://xmlns.banesco.com/eo/Card_V1.0"
                xmlns:ns7="http://xmlns.banesco.com/eo/Payee_V1.0" xmlns:ns8="http://xmlns.banesco.com/eo/Common_V1.0">
   <oracle-xsl-mapper:schema>
      <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
      <oracle-xsl-mapper:mapSources>
         <oracle-xsl-mapper:source type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/CardDb/CardDbT24Add/CardDbT24Add_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="CardDbT24AddRs" namespace="http://xmlns.banesco.com/eopt/CardDbT24Add_V1.0"/>
         </oracle-xsl-mapper:source>
      </oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:mapTargets>
         <oracle-xsl-mapper:target type="XSD">
            <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/CardDb/CardDbAdd/CardDbAdd_V1.0.xsd"/>
            <oracle-xsl-mapper:rootElement name="CardDbAddRs" namespace="http://xmlns.banesco.com/eopt/CardDbAdd_V1.0"/>
         </oracle-xsl-mapper:target>
      </oracle-xsl-mapper:mapTargets>
      <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.3.0(XSLT Build 170820.1700.2557) AT [TUE APR 10 18:14:30 COT 2018].-->
   </oracle-xsl-mapper:schema>
   <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
   <xsl:template match="/">
      <tns:CardDbAddRs>
         <tns:Card>
            <ns6:PlasticInfo>
               <xsl:if test="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:PlasticInfo/ns6:CardEmbossName/text()">
                  <ns6:CardEmbossName>
                     <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:PlasticInfo/ns6:CardEmbossName"/>
                  </ns6:CardEmbossName>
               </xsl:if>
               <xsl:if test="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:PlasticInfo/ns6:ExpDt/text()">
                  <ns6:ExpDt>
                     <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:PlasticInfo/ns6:ExpDt"/>
                  </ns6:ExpDt>
               </xsl:if>
               <xsl:if test="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:PlasticInfo/ns6:IssueDt/text()">
                  <ns6:IssueDt>
                     <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:PlasticInfo/ns6:IssueDt"/>
                  </ns6:IssueDt>
               </xsl:if>
               <ns6:PayRoll>
                  <xsl:if test="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:PlasticInfo/ns6:PayRoll/ns6:Detail/text()">
                     <ns6:Detail>
                        <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:PlasticInfo/ns6:PayRoll/ns6:Detail"/>
                     </ns6:Detail>
                  </xsl:if>
               </ns6:PayRoll>
            </ns6:PlasticInfo>
            <ns6:CardStatus>
               <xsl:if test="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:CardStatus/ns6:CardStatusCode/text()">
                  <ns6:CardStatusCode>
                     <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:CardStatus/ns6:CardStatusCode"/>
                  </ns6:CardStatusCode>
               </xsl:if>
               <xsl:if test="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:CardStatus/ns6:EffDt/text()">
                  <ns6:EffDt>
                     <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:CardStatus/ns6:EffDt"/>
                  </ns6:EffDt>
               </xsl:if>
               <xsl:if test="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:CardStatus/ns6:StatusReason/text()">
                  <ns6:StatusReason>
                     <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:CardAcctRel/eoCardAcctRel:Card/ns6:CardStatus/ns6:StatusReason"/>
                  </ns6:StatusReason>
               </xsl:if>
            </ns6:CardStatus>
         </tns:Card>
         <tns:Status>
            <xsl:if test="/ns0:CardDbHTAddRs/ns0:Status/eoStatus:StatusCode/text()">
               <eoStatus:StatusCode>
                  <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:Status/eoStatus:StatusCode"/>
               </eoStatus:StatusCode>
            </xsl:if>
            <xsl:if test="/ns0:CardDbHTAddRs/ns0:Status/eoStatus:StatusDesc/text()">
               <eoStatus:StatusDesc>
                  <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:Status/eoStatus:StatusDesc"/>
               </eoStatus:StatusDesc>
            </xsl:if>
            <eoStatus:AdditionalStatus>
               <xsl:if test="/ns0:CardDbHTAddRs/ns0:Status/eoStatus:AdditionalStatus/eoStatus:StatusCode/text()">
                  <eoStatus:StatusCode>
                     <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:Status/eoStatus:AdditionalStatus/eoStatus:StatusCode"/>
                  </eoStatus:StatusCode>
               </xsl:if>
               <xsl:if test="/ns0:CardDbHTAddRs/ns0:Status/eoStatus:AdditionalStatus/eoStatus:StatusDesc/text()">
                  <eoStatus:StatusDesc>
                     <xsl:value-of select="/ns0:CardDbHTAddRs/ns0:Status/eoStatus:AdditionalStatus/eoStatus:StatusDesc"/>
                  </eoStatus:StatusDesc>
               </xsl:if>
            </eoStatus:AdditionalStatus>
         </tns:Status>
      </tns:CardDbAddRs>
   </xsl:template>
</xsl:stylesheet>
