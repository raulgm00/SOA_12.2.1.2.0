<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction"
                xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions"
                xmlns:tns="http://xmlns.banesco.com/appopt/T24CardCrAppSvc/CardCrFinancingAdd_V1.0"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions"
                xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions"
                xmlns:ns0="http://xmlns.banesco.com/eopt/CardCrFinancingAdd_V1.0"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction"
                exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns5="http://xmlns.banesco.com/eo/Product_V1.0"
                xmlns:eoAcct="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:ns6="http://xmlns.banesco.com/eo/Chk_V1.0" xmlns:ns2="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:ns4="http://xmlns.banesco.com/eo/Fee_V1.0" xmlns:eoCard="http://xmlns.banesco.com/eo/Card_V1.0"
                xmlns:ns1="http://xmlns.banesco.com/eo/Payee_V1.0" xmlns:ns3="http://xmlns.banesco.com/eo/Common_V1.0"
                xmlns:appoptcommon="http://xmlns.banesco.com/appopt/AOptCommon_V1.0">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/CardCr/CardCrFinancingAdd/CardCrFinancingAdd_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="CardCrFinancingAddRs"
                                       namespace="http://xmlns.banesco.com/eopt/CardCrFinancingAdd_V1.0"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/AppOpt/T24CardCr/CardCrFinancingAdd/CardCrFinancingAdd_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="CardCrFinancingAddRs"
                                       namespace="http://xmlns.banesco.com/appopt/T24CardCrAppSvc/CardCrFinancingAdd_V1.0"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.3.0(XSLT Build 170820.1700.2557) AT [MON JUL 09 20:49:40 CDT 2018].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:CardCrFinancingAddRs>
      <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Status/eoStatus:StatusCode = 'M0000'">
        <tns:Card>
          <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:CardKey">
            <tns:CardKey>
              <tns:CardNum>
                <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:CardKey/eoCard:CardNum"/>
              </tns:CardNum>
            </tns:CardKey>
          </xsl:if>
          <tns:FinancingInfo>
            <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:ContractNum">
              <tns:ContractNum>
                <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:ContractNum"/>
              </tns:ContractNum>
            </xsl:if>
            <tns:FinancingPlan>
              <appoptcommon:Cod>
                <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:FinancingPlan/ns3:Cod"/>
              </appoptcommon:Cod>
              <appoptcommon:Desc>
                <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:FinancingPlan/ns3:Desc"/>
              </appoptcommon:Desc>
            </tns:FinancingPlan>
            <tns:FinancingTerm>
              <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:FinancingTerm"/>
            </tns:FinancingTerm>
            <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:MonthlyFee">
              <tns:MonthlyFee>
                <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:MonthlyFee/ns3:Amt">
                  <appoptcommon:Amt>
                    <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:MonthlyFee/ns3:Amt"/>
                  </appoptcommon:Amt>
                </xsl:if>
                <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:MonthlyFee/ns3:CurCode">
                  <appoptcommon:CurCode>
                    <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FinancingInfo/eoCard:MonthlyFee/ns3:CurCode"/>
                  </appoptcommon:CurCode>
                </xsl:if>
              </tns:MonthlyFee>
            </xsl:if>
          </tns:FinancingInfo>
        </tns:Card>
      </xsl:if>
      <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Status/eoStatus:StatusCode = 'M0000'">
        <tns:FIData>
          <tns:FIIdent>
            <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FIData/ns2:FIIdent"/>
          </tns:FIIdent>
          <tns:Agency>
            <tns:AgencyIdent>
              <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Card/eoCard:FIData/ns2:Agency/ns2:AgencyIdent"/>
            </tns:AgencyIdent>
          </tns:Agency>
        </tns:FIData>
      </xsl:if>
      <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Status/eoStatus:StatusCode = 'M0000'">
        <tns:Party>
          <tns:PartyKey>
            <tns:IssuedIdentType>
              <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Party/eoPar:PartyKey/eoPar:IssuedIdentType"/>
            </tns:IssuedIdentType>
            <tns:IssuedIdentValue>
              <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Party/eoPar:PartyKey/eoPar:IssuedIdentValue"/>
            </tns:IssuedIdentValue>
            <tns:PartyId>
              <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Party/eoPar:PartyKey/eoPar:PartyId"/>
            </tns:PartyId>
          </tns:PartyKey>
        </tns:Party>
      </xsl:if>
      <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Status/eoStatus:StatusCode = 'M0000'">
        <xsl:if test="/ns0:CardCrFinancingAddRs/ns0:Party/eoPar:PersonPartyInfo/eoPar:PersonData/eoPar:PersonName/eoPar:FullName">
          <tns:PersonName>
            <tns:FullName>
              <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Party/eoPar:PersonPartyInfo/eoPar:PersonData/eoPar:PersonName/eoPar:FullName"/>
            </tns:FullName>
          </tns:PersonName>
        </xsl:if>
      </xsl:if>
      <tns:Status>
        <appoptcommon:StatusCode>
          <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Status/eoStatus:StatusCode"/>
        </appoptcommon:StatusCode>
        <xsl:choose>
          <xsl:when test="/ns0:CardCrFinancingAddRs/ns0:Status/eoStatus:StatusCode = 'M0000'">
            <appoptcommon:StatusDesc>
              <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Status/eoStatus:AdditionalStatus/eoStatus:StatusDesc"/>
            </appoptcommon:StatusDesc>
          </xsl:when>
          <xsl:otherwise>
            <appoptcommon:StatusDesc>
              <xsl:value-of select="/ns0:CardCrFinancingAddRs/ns0:Status/eoStatus:StatusDesc"/>
            </appoptcommon:StatusDesc>
          </xsl:otherwise>
        </xsl:choose>
      </tns:Status>
    </tns:CardCrFinancingAddRs>
  </xsl:template>
</xsl:stylesheet>
