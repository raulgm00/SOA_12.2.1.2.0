<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="PartyInfoInq" tipo="atomic">
        <service>Way4PartyAppSvc</service>
        <operation>PartyInfoInq</operation>
        <schema>http://xmlns.banesco.com/appopt/Way4PartyAppSvc/PartyInfoInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/Way4Party/PartyInfoInq/PartyInfoInq_V1.0</route_schema>
        <transformation_reception_to_canonical>Way4AppRS/resources/xslt/PartyInfoInq/xslt_in_PartyInfoInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>Way4AppRS/resources/xslt/PartyInfoInq/xslt_out_PartyInfoInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>
