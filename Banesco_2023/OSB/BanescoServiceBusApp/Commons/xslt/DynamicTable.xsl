<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="AuditAdd" tipo="atomic">
        <operation>AuditAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/AuditAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Common/AuditAdd/AuditAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>CanonicalService/TechnicalLayer/Common/resources/xslt/AuditAdd/xslt_in_AuditAddRq_to_AuditAddRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CanonicalService/TechnicalLayer/Card/resources/xslt/CardAcctRelInq/xslt_out_tdcCardAcctRelInqRs_to_CardAcctRelInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/business/TechLayer/Common/BS_AuditAddService_V1.0</bs_backend>
        <backend_operation>AuditAdd</backend_operation>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>