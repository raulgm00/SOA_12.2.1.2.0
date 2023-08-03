<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0"  
  xmlns:tns="http://xmlns.banesco.com/appopt/AOptCommon_V1.0"  
   xmlns:UUIDUserFunction="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.stages.functions.UUIDUserFunction"
  xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
  xmlns:DVMFunctions="http://www.oracle.com/XSL/Transform/java/com.bea.wli.sb.functions.dvm.DVMFunctions" 
  xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
  xmlns:fn="http://www.w3.org/2005/xpath-functions" exclude-result-prefixes="xsl tns xp20 DVMFunctions UUIDUserFunction">      
  <xsl:output indent="yes"></xsl:output>
  <xsl:param name="ServiceName"/>
  <xsl:param name="OperationName"/>
  <xsl:template match="/"> 
  <soapenv:Header>
    <tns:MsgHdrRq>
      <xsl:choose>
        <xsl:when test="soapenv:Header/tns:MsgHdrRq/tns:ClientDt/text()">
          <tns:ClientDt>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:ClientDt"/>
          </tns:ClientDt>
        </xsl:when>
        <xsl:otherwise>
          <tns:ClientDt>
            <xsl:value-of select='xp20:format-dateTime (xp20:current-dateTime ( ), "[Y0001]-[M01]-[D01]T[H01]:[m01]:[s01]" )'/>
          </tns:ClientDt>
        </xsl:otherwise>
      </xsl:choose>
      <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:Destination/text()">
        <tns:Destination>
          <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:Destination"/>
        </tns:Destination>        
      </xsl:if>
      <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:MsgPriority/text()">
        <tns:MsgPriority>
          <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:MsgPriority"/>
        </tns:MsgPriority>        
      </xsl:if>  
      <xsl:choose>
        <xsl:when test="soapenv:Header/tns:MsgHdrRq/tns:RequestId/text()">
          <tns:RequestId>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:RequestId"/>
          </tns:RequestId>
        </xsl:when>
        <xsl:otherwise>
          <tns:RequestId>
            <xsl:value-of select="UUIDUserFunction:uuid( )"/>
          </tns:RequestId>
        </xsl:otherwise>
      </xsl:choose>      
      <tns:RqdOper>
        <xsl:value-of select="$OperationName"/>
      </tns:RqdOper>
      <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:RqdOperType/text()">
        <tns:RqdOperType>
          <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:RqdOperType"/>
        </tns:RqdOperType>        
      </xsl:if>
      <xsl:variable name="service">
        <xsl:value-of select="substring-before(substring-after($ServiceName/schema,'http://xmlns.banesco.com/appopt/'),'/')">          
        </xsl:value-of>
      </xsl:variable>
      <xsl:choose>
        <xsl:when test="$service != ''">
          <tns:RqdService>
            <xsl:value-of select="$service"/>
          </tns:RqdService>
        </xsl:when>
        <xsl:otherwise>
          <tns:RqdService>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:RqdService"/>
          </tns:RqdService>
        </xsl:otherwise>
      </xsl:choose>
      <tns:ClientApp>
        <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:ChannelId/text()">
          <tns:ChannelId>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:ChannelId"/>
          </tns:ChannelId>        
        </xsl:if> 
        <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:Name/text()">
          <tns:Name>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:Name"/>
          </tns:Name>        
        </xsl:if> 
        <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:Org/text()">
          <tns:Org>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:Org"/>
          </tns:Org>        
        </xsl:if>
        <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:UserId/text()">
          <tns:UserId>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:UserId"/>
          </tns:UserId>        
        </xsl:if> 
        <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:UserType/text()">
          <tns:UserType>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:ClientApp/tns:UserType"/>
          </tns:UserType>        
        </xsl:if>        
      </tns:ClientApp>
      <tns:NetworkTrnData>
        <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:NetworkTrnData/tns:HostName/text()">
          <tns:HostName>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:NetworkTrnData/tns:HostName"/>
          </tns:HostName>        
        </xsl:if> 
        <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:NetworkTrnData/tns:IpAddress/text()">
          <tns:IpAddress>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:NetworkTrnData/tns:IpAddress"/>
          </tns:IpAddress>        
        </xsl:if> 
        <xsl:if test="soapenv:Header/tns:MsgHdrRq/tns:NetworkTrnData/tns:MacAddress/text()">
          <tns:MacAddress>
            <xsl:value-of select="soapenv:Header/tns:MsgHdrRq/tns:NetworkTrnData/tns:MacAddress"/>
          </tns:MacAddress>        
        </xsl:if>        
      </tns:NetworkTrnData>
    </tns:MsgHdrRq>
    </soapenv:Header>
  </xsl:template>
</xsl:stylesheet>
