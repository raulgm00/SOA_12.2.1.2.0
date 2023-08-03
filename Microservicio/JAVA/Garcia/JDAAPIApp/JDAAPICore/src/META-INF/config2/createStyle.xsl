<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
  <Request>
    <Parameters id="item.createStyle">
        <Parameter name="ZMSEQ"><xsl:value-of select="//*:createItemRequest/*:item/*:internalID" /></Parameter>
        <Parameter name="ZMDESC"><xsl:value-of select="//*:createItemRequest/*:item/*:name"/></Parameter>
        <Parameter name="ZMSHOR"><xsl:value-of select="//*:createItemRequest/*:item/*:shortDescription"/></Parameter>
        <Parameter name="ZMVEND"><xsl:value-of select="//*:createItemRequest/*:item/*:itemDetailInfo/*:supplier/*:supplierID"/></Parameter>
        

    </Parameters>
    <Source>
        <xsl:value-of select="/"/>
    </Source>
  </Request>
  
  </xsl:template>
</xsl:stylesheet>
