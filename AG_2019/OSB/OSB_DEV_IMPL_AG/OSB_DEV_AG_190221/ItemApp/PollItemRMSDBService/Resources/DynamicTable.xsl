<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="CREATE" type="composed">
        <component_target>ItemEBS</component_target>
        <bs_backend>ProcessItem</bs_backend>
        <component_target_backend>ItemApp/PollItemRMSDBService/BusinessService/ProcessItemEBS</component_target_backend>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>