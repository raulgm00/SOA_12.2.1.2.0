<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0"
    xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
    xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:ns0="http://xmlns.banesco.com/appopt/AOptCommon_V1.0"
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    exclude-result-prefixes=" xsi xsl ns0 dvm xp20 oraext">
    <xsl:template match="/">
        <ns0:MsgHdr>
            <ns0:ClientDt>
                <xsl:value-of select="xp20:format-dateTime (xp20:current-dateTime(),'[Y0001]-[M01]-[D01]T[H01]:[m01]:[s01]')"/>
            </ns0:ClientDt>
            <ns0:Destination>T24</ns0:Destination>
            <ns0:MsgPriority>0</ns0:MsgPriority>
            <ns0:RequestId>
                <xsl:value-of select="oraext:generate-guid ( )"/>
            </ns0:RequestId>
            <ns0:RqdOper>EmpPartyStatusMod</ns0:RqdOper>
            <!--RqdOperType></RqdOperType>
            <RqdService></RqdService-->
            <ns0:ServerDt>
                <xsl:value-of select="xp20:format-dateTime (xp20:current-dateTime(),'[Y0001]-[M01]-[D01]T[H01]:[m01]:[s01]')"/>
            </ns0:ServerDt>
            <ns0:NetworkTrnData>
                <!--HostName></HostName-->
                <ns0:IpAddress>172.30.2.35</ns0:IpAddress>
                <!--MacAddress></MacAddress-->
            </ns0:NetworkTrnData>
            <ns0:ClientApp>
                <ns0:ChannelId>
                  <xsl:value-of select="dvm:lookupValue('DVM/HdrValues.dvm','OperationName','EmpPartyStatusMod','ChannelId','T24User')"/>
                </ns0:ChannelId>
                <ns0:Name>
                  <xsl:value-of select="dvm:lookupValue('DVM/HdrValues.dvm','OperationName','EmpPartyStatusMod','ChannelId','T24User')"/>
                </ns0:Name>
                <ns0:Org>Banesco</ns0:Org>
                <ns0:UserId>
                     <xsl:value-of select="dvm:lookupValue('DVM/HdrValues.dvm','OperationName','EmpPartyStatusMod','UserId','T24User')"/>
                </ns0:UserId>
                <ns0:UserType>Servicio</ns0:UserType>
                <ns0:Version>1.0</ns0:Version>
            </ns0:ClientApp>
        </ns0:MsgHdr>        
    </xsl:template>
</xsl:stylesheet>
