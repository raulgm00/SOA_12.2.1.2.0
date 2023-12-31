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
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction"
                xmlns:ns0="http://xmlns.banesco.com/eopt/CardCrInq_V1.0"
                xmlns:tns="http://xmlns.banesco.com/appopt/T24CardCrAppSvc/CardCrInq_V1.0"
                exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns4="http://xmlns.banesco.com/eo/Product_V1.0" xmlns:ns5="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:eoPar="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:ns6="http://xmlns.banesco.com/eo/Chk_V1.0" xmlns:eoBank="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:ns3="http://xmlns.banesco.com/eo/Fee_V1.0" xmlns:eoCard="http://xmlns.banesco.com/eo/Card_V1.0"
                xmlns:ns1="http://xmlns.banesco.com/eo/Payee_V1.0" xmlns:ns2="http://xmlns.banesco.com/eo/Common_V1.0"
                xmlns:appoptcommon="http://xmlns.banesco.com/appopt/AOptCommon_V1.0">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/CardCr/CardCrInq/CardCrInq_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="CardCrInqRs" namespace="http://xmlns.banesco.com/eopt/CardCrInq_V1.0"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/AppOpt/T24CardCr/CardCrInq/CardCrInq_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="CardCrInqRs"
                                       namespace="http://xmlns.banesco.com/appopt/T24CardCrAppSvc/CardCrInq_V1.0"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.3.0(XSLT Build 170820.1700.2557) AT [WED SEP 05 13:24:07 CDT 2018].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:CardCrInqRs>
      <xsl:if test="/ns0:CardCrInqRs/ns0:Status/eoStatus:StatusCode != 'M0006'">
        <xsl:if test="/ns0:CardCrInqRs/ns0:FIData">
          <tns:FIData>
            <tns:FIIdent>
              <xsl:value-of select="/ns0:CardCrInqRs/ns0:FIData/eoBank:FIIdent"/>
            </tns:FIIdent>
          </tns:FIData>
        </xsl:if>
      </xsl:if>
      <xsl:if test="/ns0:CardCrInqRs/ns0:Status/eoStatus:StatusCode != 'M0006'">
        <xsl:if test="/ns0:CardCrInqRs/ns0:Party">
          <tns:Party>
            <xsl:if test="/ns0:CardCrInqRs/ns0:Party/eoPar:PartyKey">
              <tns:PartyKey>
                <xsl:if test="/ns0:CardCrInqRs/ns0:Party/eoPar:PartyKey/eoPar:IssuedIdentType">
                  <tns:IssuedIdentType>
                    <xsl:value-of select="/ns0:CardCrInqRs/ns0:Party/eoPar:PartyKey/eoPar:IssuedIdentType"/>
                  </tns:IssuedIdentType>
                </xsl:if>
                <xsl:if test="/ns0:CardCrInqRs/ns0:Party/eoPar:PartyKey/eoPar:IssuedIdentValue">
                  <tns:IssuedIdentValue>
                    <xsl:value-of select="/ns0:CardCrInqRs/ns0:Party/eoPar:PartyKey/eoPar:IssuedIdentValue"/>
                  </tns:IssuedIdentValue>
                </xsl:if>
                <xsl:if test="/ns0:CardCrInqRs/ns0:Party/eoPar:PartyKey/eoPar:PartyId">
                  <tns:PartyId>
                    <xsl:value-of select="/ns0:CardCrInqRs/ns0:Party/eoPar:PartyKey/eoPar:PartyId"/>
                  </tns:PartyId>
                </xsl:if>
              </tns:PartyKey>
            </xsl:if>
          </tns:Party>
        </xsl:if>
      </xsl:if>
      <xsl:if test="/ns0:CardCrInqRs/ns0:Status/eoStatus:StatusCode != 'M0006'">
        <xsl:if test="/ns0:CardCrInqRs/ns0:PersonName">
          <tns:PersonName>
            <xsl:if test="/ns0:CardCrInqRs/ns0:PersonName/eoPar:FullName">
              <tns:FullName>
                <xsl:value-of select="/ns0:CardCrInqRs/ns0:PersonName/eoPar:FullName"/>
              </tns:FullName>
            </xsl:if>
          </tns:PersonName>
        </xsl:if>
      </xsl:if>
      <tns:Status>
        <appoptcommon:StatusCode>
          <xsl:value-of select="/ns0:CardCrInqRs/ns0:Status/eoStatus:StatusCode"/>
        </appoptcommon:StatusCode>
        <xsl:choose>
          <xsl:when test="/ns0:CardCrInqRs/ns0:Status/eoStatus:StatusCode != 'M0006'">
            <!--<xsl:when test="/ns0:CardCrInqRs/ns0:Status/eoStatus:AdditionalStatus/eoStatus:StatusCode = '00' and /ns0:CardCrInqRs/ns0:Cards/ns0:Card[1]">-->
            <appoptcommon:StatusDesc>
              <xsl:value-of select="/ns0:CardCrInqRs/ns0:Status/eoStatus:StatusDesc"/>
            </appoptcommon:StatusDesc>
          </xsl:when>
          <xsl:otherwise>
            <appoptcommon:StatusDesc>
              <xsl:value-of select="/ns0:CardCrInqRs/ns0:Status/eoStatus:AdditionalStatus/eoStatus:StatusDesc"/>
            </appoptcommon:StatusDesc>
          </xsl:otherwise>
        </xsl:choose>
      </tns:Status>
      <xsl:if test="/ns0:CardCrInqRs/ns0:Status/eoStatus:StatusCode != 'M0006'">
        <tns:Cards>
          <xsl:if test="/ns0:CardCrInqRs/ns0:Cards/ns0:Card">
            <xsl:for-each select="/ns0:CardCrInqRs/ns0:Cards/ns0:Card">
              <tns:Card>
                <xsl:if test="eoCard:CardRel">
                  <tns:CardRel>
                    <xsl:if test="eoCard:CardRel/ns2:Cod">
                      <appoptcommon:Cod>
                        <xsl:value-of select="eoCard:CardRel/ns2:Cod"/>
                      </appoptcommon:Cod>
                    </xsl:if>
                    <xsl:if test="eoCard:CardRel/ns2:Desc">
                      <appoptcommon:Desc>
                        <xsl:value-of select="eoCard:CardRel/ns2:Desc"/>
                      </appoptcommon:Desc>
                    </xsl:if>
                  </tns:CardRel>
                </xsl:if>
                <xsl:if test="eoCard:CardRelId">
                  <tns:CardRelId>
                    <xsl:value-of select="eoCard:CardRelId"/>
                  </tns:CardRelId>
                </xsl:if>
                <xsl:if test="ns4:ProdType">
                  <tns:CardSubType>
                    <xsl:if test="ns4:ProdType/ns2:Cod">
                      <appoptcommon:Cod>
                        <xsl:value-of select="ns4:ProdType/ns2:Cod"/>
                      </appoptcommon:Cod>
                    </xsl:if>
                    <xsl:if test="ns4:ProdType/ns2:Desc">
                      <appoptcommon:Desc>
                        <xsl:value-of select="ns4:ProdType/ns2:Desc"/>
                      </appoptcommon:Desc>
                    </xsl:if>
                  </tns:CardSubType>
                </xsl:if>
                <xsl:if test="eoCard:CardType">
                  <tns:CardType>
                    <xsl:if test="eoCard:CardType/ns2:Cod">
                      <appoptcommon:Cod>
                        <xsl:value-of select="eoCard:CardType/ns2:Cod"/>
                      </appoptcommon:Cod>
                    </xsl:if>
                    <xsl:if test="eoCard:CardType/ns2:Desc">
                      <appoptcommon:Desc>
                        <xsl:value-of select="eoCard:CardType/ns2:Desc"/>
                      </appoptcommon:Desc>
                    </xsl:if>
                  </tns:CardType>
                </xsl:if>
                <xsl:if test="eoCard:PmtLimitDt">
                  <tns:PmtLimitDt>
                    <xsl:value-of select="eoCard:PmtLimitDt"/>
                  </tns:PmtLimitDt>
                </xsl:if>
                <xsl:if test="eoCard:FinancialData">
                  <tns:FinancialData>
                    <tns:AvailablePurchaseAmt>
                      <xsl:if test="eoCard:FinancialData/eoCard:AvailablePurchaseAmt/ns2:Amt">
                        <appoptcommon:Amt>
                          <xsl:value-of select="eoCard:FinancialData/eoCard:AvailablePurchaseAmt/ns2:Amt"/>
                        </appoptcommon:Amt>
                      </xsl:if>
                      <xsl:if test="eoCard:FinancialData/eoCard:AvailablePurchaseAmt/ns2:CurCode">
                        <appoptcommon:CurCode>
                          <xsl:value-of select="eoCard:FinancialData/eoCard:AvailablePurchaseAmt/ns2:CurCode"/>
                        </appoptcommon:CurCode>
                      </xsl:if>
                    </tns:AvailablePurchaseAmt>
                    <tns:CashPmtAmt>
                      <xsl:if test="eoCard:FinancialData/eoCard:CashPmtAmt/ns2:Amt">
                        <appoptcommon:Amt>
                          <xsl:value-of select="eoCard:FinancialData/eoCard:CashPmtAmt/ns2:Amt"/>
                        </appoptcommon:Amt>
                      </xsl:if>
                      <xsl:if test="eoCard:FinancialData/eoCard:CashPmtAmt/ns2:CurCode">
                        <appoptcommon:CurCode>
                          <xsl:value-of select="eoCard:FinancialData/eoCard:CashPmtAmt/ns2:CurCode"/>
                        </appoptcommon:CurCode>
                      </xsl:if>
                    </tns:CashPmtAmt>
                    <tns:CurrentBalance>
                      <xsl:if test="eoCard:FinancialData/eoCard:CurrentBalance/ns2:Amt">
                        <appoptcommon:Amt>
                          <xsl:value-of select="eoCard:FinancialData/eoCard:CurrentBalance/ns2:Amt"/>
                        </appoptcommon:Amt>
                      </xsl:if>
                      <xsl:if test="eoCard:FinancialData/eoCard:CurrentBalance/ns2:CurCode">
                        <appoptcommon:CurCode>
                          <xsl:value-of select="eoCard:FinancialData/eoCard:CurrentBalance/ns2:CurCode"/>
                        </appoptcommon:CurCode>
                      </xsl:if>
                    </tns:CurrentBalance>
                    <xsl:if test="eoCard:FinancialData/eoCard:FinancingStatus">
                      <tns:FinancingStatus>
                        <xsl:value-of select="eoCard:FinancialData/eoCard:FinancingStatus"/>
                      </tns:FinancingStatus>
                    </xsl:if>
                    <xsl:if test="eoCard:FinancialData/eoCard:RepayAcct">
                      <tns:RepayAcct>
                        <xsl:if test="eoCard:FinancialData/eoCard:RepayAcct/ns5:AcctId">
                          <tns:AcctId>
                            <xsl:value-of select="eoCard:FinancialData/eoCard:RepayAcct/ns5:AcctId"/>
                          </tns:AcctId>
                        </xsl:if>
                      </tns:RepayAcct>
                    </xsl:if>
                  </tns:FinancialData>
                </xsl:if>
                <xsl:if test="eoCard:CardStatus">
                  <tns:CardStatus>
                    <xsl:if test="eoCard:CardStatus/eoCard:CardStatusCode">
                      <tns:CardStatusCode>
                        <xsl:value-of select="eoCard:CardStatus/eoCard:CardStatusCode"/>
                      </tns:CardStatusCode>
                    </xsl:if>
                    <xsl:if test="eoCard:CardStatus/eoCard:StatusDesc">
                      <tns:StatusDesc>
                        <xsl:value-of select="eoCard:CardStatus/eoCard:StatusDesc"/>
                      </tns:StatusDesc>
                    </xsl:if>
                  </tns:CardStatus>
                </xsl:if>
                <xsl:if test="eoCard:CardKey">
                  <tns:CardKey>
                    <tns:CardNum>
                      <xsl:value-of select="eoCard:CardKey/eoCard:CardNum"/>
                    </tns:CardNum>
                  </tns:CardKey>
                </xsl:if>
                <xsl:if test="eoCard:PlasticInfo">
                  <tns:PlasticInfo>
                    <xsl:if test="eoCard:PlasticInfo/eoCard:ExpDt">
                      <tns:ExpDt>
                        <xsl:value-of select="eoCard:PlasticInfo/eoCard:ExpDt"/>
                      </tns:ExpDt>
                    </xsl:if>
                  </tns:PlasticInfo>
                </xsl:if>
                <xsl:if test="eoCard:CardPeriodData">
                  <tns:CardPeriodData>
                    <tns:MinimumPmt>
                      <xsl:if test="eoCard:CardPeriodData/eoCard:MinimumPmt/ns2:Amt">
                        <appoptcommon:Amt>
                          <xsl:value-of select="eoCard:CardPeriodData/eoCard:MinimumPmt/ns2:Amt"/>
                        </appoptcommon:Amt>
                      </xsl:if>
                      <xsl:if test="eoCard:CardPeriodData/eoCard:MinimumPmt/ns2:CurCode">
                        <appoptcommon:CurCode>
                          <xsl:value-of select="eoCard:CardPeriodData/eoCard:MinimumPmt/ns2:CurCode"/>
                        </appoptcommon:CurCode>
                      </xsl:if>
                    </tns:MinimumPmt>
                  </tns:CardPeriodData>
                </xsl:if>
              </tns:Card>
            </xsl:for-each>
          </xsl:if>
        </tns:Cards>
      </xsl:if>
    </tns:CardCrInqRs>
  </xsl:template>
</xsl:stylesheet>