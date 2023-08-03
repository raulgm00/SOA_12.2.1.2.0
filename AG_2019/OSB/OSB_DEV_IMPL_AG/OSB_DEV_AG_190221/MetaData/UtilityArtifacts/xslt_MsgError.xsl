<xsl:stylesheet version="2.0" exclude-result-prefixes="common con" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:common="http://xmlns.oracle.com/EnterpriseObjects/Common" xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" xmlns:con="http://www.bea.com/wli/sb/context">
  <xsl:include href="template_MsgError.xsl"/>
  <xsl:template match="/">
  <xsl:variable name="Code">
    <xsl:call-template name="Code">
        <xsl:with-param name="OSBCode" select="/con:fault/con:errorCode"/>
      </xsl:call-template>
  </xsl:variable>
  <common:FaultMessage>
      <xsl:copy-of select="$Code/FaultMessage/*"/>
      <xsl:choose>
        <xsl:when test="/con:fault/*:details/*:ValidationFailureDetail">
          <common:Stack languageCode="original">
            <xsl:value-of select="/con:fault/*:details/*:ValidationFailureDetail/*:message"/>
          </common:Stack>
        </xsl:when>
        <xsl:when test="/con:fault/*:details/*:ReceivedFaultDetail/*:detail/*:jca-runtime-fault-detail">
          <common:Stack languageCode="original">
            <xsl:value-of select="/con:fault/*:details/*:ReceivedFaultDetail/*:detail/*:jca-runtime-fault-detail/*:eis-error-message"/>
          </common:Stack>
        </xsl:when>
        <xsl:when test="/con:fault/*:details/*:PayloadDetail">
          <common:Stack languageCode="original">
            <xsl:value-of select="/con:fault/*:details/*:PayloadDetail/*:text"/>
          </common:Stack>
        </xsl:when>
        <xsl:when test="starts-with(/con:fault/*:Code,'G') or starts-with(/con:fault/*:errorCode,'I') or starts-with(/con:fault/*:Code,'O0')or starts-with(/con:fault/*:Code,'Ord')">
          <common:Stack languageCode="original">
            <xsl:value-of select="DVMFunctions:lookupValue('MetaData/dvm/ErrorCode','ERROR_CODE',/con:fault/*:errorCode,'USER_MESSAGE','Error')"/>
          </common:Stack>
        </xsl:when>
        <xsl:otherwise>
          <common:Stack languageCode="original">
            <xsl:value-of select="substring(/con:fault/*:reason,1,1000)"/>
          </common:Stack>
        </xsl:otherwise>
      </xsl:choose>
      <common:ApplicationFaultData>
        <xsl:choose>
          <xsl:when test="/con:fault/*:details/*:ReceivedFaultDetail/*:detail/*:jca-runtime-fault-detail">
            <common:Code>
              <xsl:value-of select="/con:fault/*:details/*:ReceivedFaultDetail/*:detail/*:jca-runtime-fault-detail/*:eis-error-code"/>
            </common:Code>        
          </xsl:when>
          <xsl:otherwise>
            <common:Code>
              <xsl:value-of select="/con:fault/*:errorCode"/>
            </common:Code>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:choose>
          <xsl:when test="/con:fault/*:details/*:ValidationFailureDetail">
          <common:Text>
              <xsl:value-of select="/con:fault/*:details/*:ValidationFailureDetail/*:message"/>
            </common:Text>
        </xsl:when>
          <xsl:when test="/con:fault/*:details/*:ReceivedFaultDetail/*:detail/*:jca-runtime-fault-detail">
          <common:Text>
              <xsl:value-of select="/con:fault/*:details/*:ReceivedFaultDetail/*:detail/*:jca-runtime-fault-detail/*:eis-error-message"/>
            </common:Text>
        </xsl:when>
          <xsl:when test="/con:fault/*:details/*:PayloadDetail">
          <common:Text>
              <xsl:value-of select="/con:fault/*:details/*:PayloadDetail/*:text"/>
            </common:Text>
        </xsl:when>
          <xsl:when test="starts-with(/con:fault/*:Code,'G') or starts-with(/con:fault/*:errorCode,'I') or starts-with(/con:fault/*:Code,'O0')or starts-with(/con:fault/*:Code,'Ord')">
          <common:Text>
              <xsl:value-of select="DVMFunctions:lookupValue('MetaData/dvm/ErrorCode','ERROR_CODE',/con:fault/*:errorCode,'USER_MESSAGE','Error')"/>
            </common:Text>
        </xsl:when>
          <xsl:otherwise>
          <common:Text>
              <xsl:value-of select="substring(/con:fault/*:reason,1,1000)"/>
            </common:Text>
        </xsl:otherwise>
        </xsl:choose>
      </common:ApplicationFaultData>
    </common:FaultMessage>
  </xsl:template>
</xsl:stylesheet>