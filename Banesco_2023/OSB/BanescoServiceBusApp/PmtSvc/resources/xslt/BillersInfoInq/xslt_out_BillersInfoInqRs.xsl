<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0"
                xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction"
                xmlns:ns0="http://xmlns.banesco.com/eopt/BillersInfoInq_V1.0"
                xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions"
                xmlns:ns1="http://xmlns.banesco.com/eopt/BillersInfoT24Inq_V1.0"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions"
                xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction"
                exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 ns1 UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns2="http://xmlns.banesco.com/eo/Banking_V1.0" xmlns:ns4="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:eoBill="http://xmlns.banesco.com/eo/Bill_V1.0"
                xmlns:ns3="http://xmlns.banesco.com/eo/Common_V1.0">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Payment/BillersInfoInq/BillersInfoInq_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="BillersInfoInqRs"
                                       namespace="http://xmlns.banesco.com/eopt/BillersInfoInq_V1.0"/>
      </oracle-xsl-mapper:source>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Payment/BillersInfoT24Inq/BillersInfoT24Inq_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="BillersInfoT24InqRs"
                                       namespace="http://xmlns.banesco.com/eopt/BillersInfoT24Inq_V1.0"/>
        <oracle-xsl-mapper:param name="BillersInfoT24InqRs"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Payment/BillersInfoInq/BillersInfoInq_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="BillersInfoInqRs"
                                       namespace="http://xmlns.banesco.com/eopt/BillersInfoInq_V1.0"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.3.0(XSLT Build 170820.1700.2557) AT [THU MAY 10 14:55:43 COT 2018].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:include href="../../../../Commons/xslt/Template/TemplateServiceUtil.xsl"/>
  <xsl:param name="BillersInfoT24InqRs"/>
  <xsl:template match="/">
    <ns0:BillersInfoInqRs>
      <xsl:variable name="BILLERS">
        <ns0:Billers>
          <xsl:for-each select="/ns0:BillersInfoInqRs/ns0:Billers/ns0:Biller">
            <ns0:Biller>
              <xsl:if test="eoBill:AcctNum/text()">
                <eoBill:AcctNum>
                  <xsl:value-of select="eoBill:AcctNum"/>
                </eoBill:AcctNum>
              </xsl:if>
              <xsl:if test="eoBill:BillerId/text()">
                <eoBill:BillerId>
                  <xsl:value-of select="eoBill:BillerId"/>
                </eoBill:BillerId>
              </xsl:if>
              <xsl:if test="eoBill:Category/text()">
                <eoBill:Category>
                  <xsl:value-of select="eoBill:Category"/>
                </eoBill:Category>
              </xsl:if>
              <xsl:if test="eoBill:Desc/text()">
                <eoBill:Desc>
                  <xsl:value-of select="eoBill:Desc"/>
                </eoBill:Desc>
              </xsl:if>
              <xsl:if test="eoBill:HasQuery/text()">
                <eoBill:HasQuery>
                  <xsl:call-template name="Boolean_Regex">
                    <xsl:with-param name="operation">INQ</xsl:with-param>
                    <xsl:with-param name="parametro">
                    <xsl:value-of select="eoBill:HasQuery"></xsl:value-of>
                    </xsl:with-param>
                  </xsl:call-template>
                </eoBill:HasQuery>
              </xsl:if>
              <xsl:if test="eoBill:HasTax/text()">
                <eoBill:HasTax>
                  <xsl:call-template name="Boolean_Regex">
                    <xsl:with-param name="operation">INQ</xsl:with-param>
                    <xsl:with-param name="parametro">
                       <xsl:value-of select="eoBill:HasTax"></xsl:value-of>
                    </xsl:with-param>
                  </xsl:call-template>
                </eoBill:HasTax>
              </xsl:if>
              <xsl:if test="eoBill:Name/text()">
                <eoBill:Name>
                  <xsl:value-of select="eoBill:Name"/>
                </eoBill:Name>
              </xsl:if>
              <xsl:if test="eoBill:PmtType/text()">
                <eoBill:PmtType>
                  <xsl:value-of select="eoBill:PmtType"/>
                </eoBill:PmtType>
              </xsl:if>
              <xsl:if test="eoBill:Tax/text()">
                <eoBill:Tax>
                  <xsl:value-of select="eoBill:Tax"/>
                </eoBill:Tax>
              </xsl:if>
              <xsl:if test="eoBill:SvcProvider">
                <eoBill:SvcProvider>
                  <xsl:if test="eoBill:SvcProvider/eoBill:LegalName/text()">
                    <eoBill:LegalName>
                      <xsl:value-of select="eoBill:SvcProvider/eoBill:LegalName"/>
                    </eoBill:LegalName>
                  </xsl:if>
                  <xsl:if test="eoBill:SvcProvider/eoBill:SvcProviderId/text()">
                    <eoBill:SvcProviderId>
                      <xsl:value-of select="eoBill:SvcProvider/eoBill:SvcProviderId"/>
                    </eoBill:SvcProviderId>
                  </xsl:if>
                </eoBill:SvcProvider>
              </xsl:if>
            </ns0:Biller>
          </xsl:for-each>
          <xsl:for-each select="$BillersInfoT24InqRs/ns1:BillersInfoT24InqRs/ns1:Billers/ns1:Biller">
            <ns0:Biller>
              <xsl:if test="eoBill:AcctNum/text()">
                <eoBill:AcctNum>
                  <xsl:value-of select="eoBill:AcctNum"/>
                </eoBill:AcctNum>
              </xsl:if>
              <xsl:if test="eoBill:BillerId/text()">
                <eoBill:BillerId>
                  <xsl:value-of select="eoBill:BillerId"/>
                </eoBill:BillerId>
              </xsl:if>
              <xsl:if test="eoBill:Category/text()">
                <eoBill:Category>
                  <xsl:value-of select="eoBill:Category"/>
                </eoBill:Category>
              </xsl:if>
              <xsl:if test="eoBill:Desc/text()">
                <eoBill:Desc>
                  <xsl:value-of select="eoBill:Desc"/>
                </eoBill:Desc>
              </xsl:if>
              <xsl:if test="eoBill:HasQuery/text()">
                <eoBill:HasQuery>
                  <xsl:call-template name="Boolean_Regex">
                    <xsl:with-param name="operation">INQ</xsl:with-param>
                    <xsl:with-param name="parametro">
                    <xsl:value-of select="eoBill:HasQuery"></xsl:value-of>
                    </xsl:with-param>
                  </xsl:call-template>
                </eoBill:HasQuery>
              </xsl:if>
              <xsl:if test="eoBill:HasTax/text()">
                <eoBill:HasTax>
                  <xsl:call-template name="Boolean_Regex">
                    <xsl:with-param name="operation">INQ</xsl:with-param>
                    <xsl:with-param name="parametro">
                       <xsl:value-of select="eoBill:HasTax"></xsl:value-of>
                    </xsl:with-param>
                  </xsl:call-template>
                </eoBill:HasTax>
              </xsl:if>
              <xsl:if test="eoBill:Name/text()">
                <eoBill:Name>
                  <xsl:value-of select="eoBill:Name"/>
                </eoBill:Name>
              </xsl:if>
              <xsl:if test="eoBill:PmtType/text()">
                <eoBill:PmtType>
                  <xsl:value-of select="eoBill:PmtType"/>
                </eoBill:PmtType>
              </xsl:if>
              <xsl:if test="eoBill:Tax/text()">
                <eoBill:Tax>
                  <xsl:value-of select="eoBill:Tax"/>
                </eoBill:Tax>
              </xsl:if>
              <xsl:if test="eoBill:SvcProvider">
                <eoBill:SvcProvider>
                  <xsl:if test="eoBill:SvcProvider/eoBill:LegalName/text()">
                    <eoBill:LegalName>
                      <xsl:value-of select="eoBill:SvcProvider/eoBill:LegalName"/>
                    </eoBill:LegalName>
                  </xsl:if>
                  <xsl:if test="eoBill:SvcProvider/eoBill:SvcProviderId/text()">
                    <eoBill:SvcProviderId>
                      <xsl:value-of select="eoBill:SvcProvider/eoBill:SvcProviderId"/>
                    </eoBill:SvcProviderId>
                  </xsl:if>
                </eoBill:SvcProvider>
              </xsl:if>
            </ns0:Biller>
          </xsl:for-each>
        </ns0:Billers>
      </xsl:variable>
      <xsl:choose>
        <xsl:when test="count($BILLERS/ns0:Billers/ns0:Biller) > 0">
          <ns0:Status>
            <eoStatus:StatusCode>M000</eoStatus:StatusCode>
            <eoStatus:StatusDesc>SUCCESS</eoStatus:StatusDesc>
          </ns0:Status>
          <xsl:copy-of select="$BILLERS"/>
        </xsl:when>
        <xsl:otherwise>
          <ns0:Status>
            <eoStatus:StatusCode>M002</eoStatus:StatusCode>
            <eoStatus:StatusDesc>SIN DATA ASOCIADA A LA CONSULTA</eoStatus:StatusDesc>
          </ns0:Status>
        </xsl:otherwise>
      </xsl:choose>
      <!--<xsl:if test="count($BILLERS/ns0:Billers/ns0:Biller) > 0">
        <xsl:copy-of select="$BILLERS"/>
      </xsl:if>-->
    </ns0:BillersInfoInqRs>
  </xsl:template>
</xsl:stylesheet>