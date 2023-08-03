<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"

    xmlns:ags="http://mx.agarcia.ea/Commons/Schemas/AGStandardHeader"

    xmlns:cre="http://mx.agarcia.ea/Applications/JDA/Items/CreateItem" 
    xmlns:v1="http://mx.agarcia.ea/Canonical/Commons/Items/v1" 
    xmlns:v11="http://mx.agarcia.ea/Canonical/Commons/Suppliers/v1"
>
  <xsl:template match="/">
  <Request>
    <Parameters id="item.createStyle">
        <Parameter name="ZMSTYN"><xsl:value-of select="//*:createItemRequest/*:item/*:id[@Type='Style']/text()" /></Parameter>
        <Parameter name="ZMDESC"><xsl:value-of select="//*:createItemRequest/*:item/*:description/text()"/></Parameter>
        <Parameter name="ZMSHOR"><xsl:value-of select="//*:createItemRequest/*:item/*:shortDescription/text()"/></Parameter>

    </Parameters>
    <Source>
        <xsl:copy-of select="/"/>
    </Source>
  </Request>
  
  </xsl:template>
</xsl:stylesheet>
