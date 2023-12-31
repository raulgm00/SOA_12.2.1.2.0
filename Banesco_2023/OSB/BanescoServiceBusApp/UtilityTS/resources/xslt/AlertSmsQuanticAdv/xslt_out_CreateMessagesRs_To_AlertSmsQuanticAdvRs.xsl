<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction"
                xmlns:IsUserInGroupFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInGroupFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:IsUserInRoleFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.IsUserInRoleFunction"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions"
                xmlns:tns="http://xmlns.banesco.com/eopt/AlertSmsQuanticAdv_V1.0"
                xmlns:oracle-xsl-mapper="http://www.oracle.com/xsl/mapper/schemas"
                xmlns:ns0="http://connections.quanticvision.com/banescobl/public/soap"
                xmlns:oraxsl="http://www.oracle.com/XSL/Transform/java"
                xmlns:RuntimeTypeConversionFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.RuntimeTypeConversionFunctions"
                xmlns:XrefFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.xref.XrefFunctions"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:BasicCredentialsUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.BasicCredentialsUserFunction"
                exclude-result-prefixes="xsd oracle-xsl-mapper xsi xsl ns0 tns UUIDUserFunction IsUserInGroupFunction oraext IsUserInRoleFunction xp20 DVMFunctions oraxsl RuntimeTypeConversionFunctions XrefFunctions BasicCredentialsUserFunction"
                xmlns:ns3="http://xmlns.banesco.com/eo/Product_V1.0" xmlns:ns4="http://xmlns.banesco.com/eo/Acct_V1.0"
                xmlns:ns5="http://xmlns.banesco.com/eo/Party_V1.0"
                xmlns:eoStatus="http://xmlns.banesco.com/eo/Status_V1.0"
                xmlns:ns9="http://xmlns.banesco.com/eo/Chk_V1.0" xmlns:ns1="http://xmlns.banesco.com/eo/Banking_V1.0"
                xmlns:eoAlert="http://xmlns.banesco.com/eo/Alert_V1.0" xmlns:ns2="http://xmlns.banesco.com/eo/Fee_V1.0"
                xmlns:ns6="http://xmlns.banesco.com/eo/Card_V1.0" xmlns:ns7="http://xmlns.banesco.com/eo/Payee_V1.0"
                xmlns:ns8="http://xmlns.banesco.com/eo/Common_V1.0">
  <oracle-xsl-mapper:schema>
    <!--SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY.-->
    <oracle-xsl-mapper:mapSources>
      <oracle-xsl-mapper:source type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/backends/QUANTIC/resources/xsd/CreateMessagesResponse.xsd"/>
        <oracle-xsl-mapper:rootElement name="createMessagesResponse"
                                       namespace="http://connections.quanticvision.com/banescobl/public/soap"/>
      </oracle-xsl-mapper:source>
    </oracle-xsl-mapper:mapSources>
    <oracle-xsl-mapper:mapTargets>
      <oracle-xsl-mapper:target type="XSD">
        <oracle-xsl-mapper:schema location="../../../../Commons/xsd/EOpt/Utility/AlertSmsQuanticAdv/AlertSmsQuanticAdv_V1.0.xsd"/>
        <oracle-xsl-mapper:rootElement name="AlertSmsQuanticAdvRs"
                                       namespace="http://xmlns.banesco.com/eopt/AlertSmsQuanticAdv_V1.0"/>
      </oracle-xsl-mapper:target>
    </oracle-xsl-mapper:mapTargets>
    <!--GENERATED BY ORACLE XSL MAPPER 12.2.1.3.0(XSLT Build 170820.1700.2557) AT [TUE JUN 05 16:59:48 COT 2018].-->
  </oracle-xsl-mapper:schema>
  <!--User Editing allowed BELOW this line - DO NOT DELETE THIS LINE-->
  <xsl:template match="/">
    <tns:AlertSmsQuanticAdvRs>
      <tns:Status>
        <xsl:choose>
          <xsl:when test="/ns0:createMessagesResponse/return/item/status_id/text()">
            <eoStatus:StatusCode>
              <xsl:value-of select="DVMFunctions:lookupValue ('Commons/dvm/StatusMappingBcknd', 'QUANTIC', /ns0:createMessagesResponse/return/item/status_id/text(), 'CNCODE', 'M0006' )"/>
            </eoStatus:StatusCode>
            <eoStatus:StatusDesc>
              <xsl:value-of select="DVMFunctions:lookupValue ('Commons/dvm/StatusMappingBcknd', 'QUANTIC', /ns0:createMessagesResponse/return/item/status_id/text(), 'CNDESC', 'FALLO EN LA OPERACION' )"/>
            </eoStatus:StatusDesc>
          </xsl:when>
          <xsl:otherwise>
            <eoStatus:StatusCode>M0006</eoStatus:StatusCode>
            <eoStatus:StatusDesc>FALLO EN LA OPERACION</eoStatus:StatusDesc>
          </xsl:otherwise>
        </xsl:choose>
        <eoStatus:AdditionalStatus>
          <xsl:choose>
            <xsl:when test="/ns0:createMessagesResponse/return/item/status_id/text()">
              <eoStatus:StatusCode>
                <xsl:value-of select="/ns0:createMessagesResponse/return/item/status_id"/>
              </eoStatus:StatusCode>
            </xsl:when>
            <xsl:otherwise>
              <eoStatus:StatusCode>
                <xsl:value-of select="/ns0:createMessagesResponse/return/item/error"/>
              </eoStatus:StatusCode>
            </xsl:otherwise>
          </xsl:choose>
          <xsl:if test="/ns0:createMessagesResponse/return/item/content/text()">
            <eoStatus:StatusDesc>
              <xsl:value-of select="/ns0:createMessagesResponse/return/item/content"/>
            </eoStatus:StatusDesc>
          </xsl:if>
        </eoStatus:AdditionalStatus>
      </tns:Status>
      <tns:Alert>
      <xsl:choose>
        <xsl:when test="/ns0:createMessagesResponse/return/item/last_change/text()">
          <eoAlert:LastUpdateDt>
            <xsl:variable name="in" select="/ns0:createMessagesResponse/return/item/last_change/text()"/>
            <xsl:variable name="dateTime" select="concat(substring($in,1,10),'T',substring($in,12))"/> 
            <xsl:value-of select="$dateTime"/>
          </eoAlert:LastUpdateDt>
        </xsl:when>
        <xsl:otherwise>
        <eoAlert:LastUpdateDt>
            <xsl:value-of select="xp20:format-dateTime (xp20:current-dateTime ( ), '[Y0001]-[M01]-[D01]T[H01]:[m01]:[s01]' )"/>
          </eoAlert:LastUpdateDt>
        </xsl:otherwise>
        </xsl:choose>
      </tns:Alert>
    </tns:AlertSmsQuanticAdvRs>
  </xsl:template>
</xsl:stylesheet>
