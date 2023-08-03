<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="ChkOrdImgInq" tipo="atomic">
        <component_target>BankingTS/proxy/PXL_Banking_V1.0</component_target>
        <backend_operation>ChkOrdImgInq</backend_operation>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>