<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:con1="http://www.bea.com/wli/sb/stages/transform/config" version="2.0">
  <xsl:output encoding="UTF-8"/>
  <xsl:template name="Amt12">
    <xsl:param name="Amt"/>
    <xsl:variable name="cadenaAmt12">000000000000</xsl:variable>
    <xsl:variable name="countAmt">
      <xsl:value-of select="12 - string-length($Amt)"/>
    </xsl:variable>
    <xsl:value-of select="concat(substring($cadenaAmt12,1,number($countAmt)),$Amt)"/>        
  </xsl:template>
  <xsl:template name="User10">
    <xsl:param name="UserId"/>
    <xsl:variable name="cadenaUser10"><xsl:text>          </xsl:text></xsl:variable>
    <xsl:variable name="countUser">
      <xsl:value-of select="10 - string-length($UserId)"/>
    </xsl:variable>
    <xsl:value-of select="concat($UserId,substring($cadenaUser10,1,number($countUser)))"/>        
  </xsl:template>
</xsl:stylesheet>
