<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue">
  <xsl:template name="DepIntPmtFreq">
    <xsl:param name="every"/>
    <xsl:param name="onDay"/>
    <xsl:param name="onDayNumber"/>
    <xsl:param name="onWeekDay"/>
    <xsl:if test="$every != ''">
      <xsl:choose>
        <xsl:when test="$onDay = '' and $onDayNumber = '' and $onWeekDay = ''"><xsl:value-of select="concat ('e0Y e', $every, 'M e0W e0D e0F')"/></xsl:when>
        <xsl:when test="$onDay != '' and $onDayNumber = '' and $onWeekDay = ''"><xsl:value-of select="concat ('e0Y e', $every, 'M e0W o', $onDay,'D e0F')"/></xsl:when>
        <xsl:when test="$onDay = '' and $onDayNumber != '' and $onWeekDay != ''"><xsl:value-of select="concat ('e0Y e', $every, 'M o', $onWeekDay, 'W o', $onDayNumber,'D e0F')"/></xsl:when>
      </xsl:choose>
    </xsl:if>
  </xsl:template>
  
  <xsl:template name="DepIntPmtFreqDetail">
    <xsl:param name="pmtFreq"/>
    <xsl:if test="$pmtFreq != ''">
      <xsl:choose>
        <xsl:when test="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'W'), 1 ) != '0'">
          <freq>
            <every>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'M' ), 1 )"/>
            </every>
            <onDay>0</onDay>
            <onDayNumber>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'D' ), 1 )"/>
            </onDayNumber>
            <onWeekDay>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'W' ), 1 )"/>
            </onWeekDay>
          </freq>
        </xsl:when>
        <xsl:when test="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'W' ), 1 ) = '0' and substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'D' ), 1 ) != '0'">
          <freq>
            <every>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'M' ), 1 )"/>
            </every>
            <onDay>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'D' ), 1 )"/>
            </onDay>
            <onDayNumber>0</onDayNumber>
            <onWeekDay>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'W'), 1 )"/>
            </onWeekDay>
          </freq>
        </xsl:when>
        <xsl:otherwise>
          <freq>
            <every>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'M' ), 1 )"/>
            </every>
            <onDay>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'D' ), 1 )"/>
            </onDay>
            <onDayNumber>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'D' ), 1 )"/>
            </onDayNumber>
            <onWeekDay>
              <xsl:value-of select="substring ($pmtFreq, oraext:index-within-string ($pmtFreq, 'W' ), 1 )"/>
            </onWeekDay>
          </freq>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:if>
  </xsl:template>
  
  <xsl:template name="CreditPmtFreq">
    <xsl:param name="noPaymentMonth"/>
    <xsl:param name="onDayNumber"/>
    <!--xsl:variable name="configFreq" select="DVMFunctions:lookupValue ('Commons/dvm/ServiceProperties.dvm', 'CODE', FreqNoPaymentMonth.1, 'VALUE', 'NOT FOUND' )" /-->
    <xsl:if test="$onDayNumber/text()">
      <xsl:choose>
        <xsl:when test="not($noPaymentMonth/text())">
          <xsl:value-of select="concat('e1Y o1,2,3,4,5,6,7,8,9,10,11,12M e0W', ' o', $onDayNumber, 'D e0F')"/>
        </xsl:when>
        <xsl:when test="$noPaymentMonth = '0'">
          <xsl:value-of select="concat('e1Y o1,2,3,4,5,6,7,8,9,10,11,12M e0W', ' o', $onDayNumber, 'D e0F')"/>
        </xsl:when>
        <xsl:when test="$noPaymentMonth = '1'">
          <xsl:value-of select="concat('e1Y o2,3,4,5,6,7,8,9,10,11,12M e0W', ' o', $onDayNumber, 'D e0F')"/>
        </xsl:when>
        <xsl:when test="$noPaymentMonth = '12'">
          <xsl:value-of select="concat('e1Y o1,2,3,4,5,6,7,8,9,10,11M e0W', ' o', $onDayNumber, 'D e0F')"/>
        </xsl:when>
      </xsl:choose>
    </xsl:if>
  </xsl:template>
</xsl:stylesheet>
