<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:con1="http://www.bea.com/wli/sb/stages/transform/config"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions"
                xmlns:common="http://xmlns.oracle.com/EnterpriseObjects/Common"
                exclude-result-prefixes="con1 xp20 Common DVMFunctions" version="2.0">
  <xsl:output encoding="UTF-8"/>
  <xsl:template name="Code">
    <xsl:param name="OSBCode"/>
    <xsl:variable name="code">
       <xsl:value-of select="number(substring-after($OSBCode,'OSB-'))"/>
    </xsl:variable>
    <xsl:variable name="DVMCode">
       <xsl:value-of select="number(substring-after($OSBCode,'OSB-'))"/>
    </xsl:variable>
    <xsl:choose>
      <!--Errores de validación y de negocio 0001-0999 -->
      <xsl:when test="$code = 382505 or $code=382571">
        <FaultMessage>
          <common:Code>G0004</common:Code>
          <common:Text>
            <xsl:value-of select="DVMFunctions:lookupValue('MetaData/dvm/ErrorCode','ERROR_CODE','G0004','USER_MESSAGE','Technical Error')"/>
          </common:Text>
        </FaultMessage> 
      </xsl:when>
      <!--Errores de conexión 1000-1999 -->
      <xsl:when test="$code = 380002">
        <FaultMessage>
          <common:Code>G0300</common:Code>
          <common:Text>
            <xsl:value-of select="DVMFunctions:lookupValue('MetaData/dvm/ErrorCode','ERROR_CODE','G0300','USER_MESSAGE','Technical Error')"/>
          </common:Text>
        </FaultMessage> 
      </xsl:when>
      <xsl:when test="$code = 380001">
        <FaultMessage>
          <common:Code>G0011</common:Code>
          <common:Text>
            <xsl:value-of select="DVMFunctions:lookupValue('MetaData/dvm/ErrorCode','ERROR_CODE','G0011','USER_MESSAGE','Technical Error')"/>
          </common:Text>
        </FaultMessage> 
      </xsl:when>
      <!--Errores de conexión 2000-2999 -->
      <xsl:when test="$code = 380000">
        <FaultMessage>
          <common:Code>G2001</common:Code>
          <common:Text>
            <xsl:value-of select="DVMFunctions:lookupValue('MetaData/dvm/ErrorCode','ERROR_CODE','G2001','USER_MESSAGE','Technical Error')"/>
          </common:Text>
        </FaultMessage> 
      </xsl:when>
      <xsl:when test="$DVMCode >= 1 or $DVMCode &lt; 1000">
        <FaultMessage>
          <common:Code>
            <xsl:value-of select="$OSBCode"/>
          </common:Code>
          <common:Text>Business Error</common:Text>
        </FaultMessage> 
      </xsl:when>
      <!--Otros errores 9000-9999 -->
      <xsl:otherwise>
        <FaultMessage>
          <common:Code>G9000</common:Code>
          <common:Text>
            <xsl:value-of select="DVMFunctions:lookupValue('MetaData/dvm/ErrorCode','ERROR_CODE','G9000','USER_MESSAGE','Technical Error')"/>
          </common:Text>
        </FaultMessage>
      </xsl:otherwise>
    </xsl:choose>     
  </xsl:template>
</xsl:stylesheet>