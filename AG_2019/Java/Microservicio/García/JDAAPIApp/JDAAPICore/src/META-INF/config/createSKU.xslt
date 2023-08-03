<?xml version="1.0" encoding="windows-1252" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
  <Request>
    <Parameters id="create">
        <Parameter name="supplierId">1026</Parameter>
        <Parameter name="SKUID">1026423423432</Parameter>
    </Parameters>
    <Source>
        <xsl:value-of select="/"/>
    </Source>
  </Request>
  
  </xsl:template>
</xsl:stylesheet>
