<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
    <rule id="DummyPBCarga" tipo="atomic">
        <operation>DummyPBCarga</operation>
        <service>DummyPBCargaRS</service>
        <schema>http://xmlns.banesco.com/appopt/DummyPBCargaAppSvc/DummyPBCarga_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/DummyPBCarga/DummyPBCarga_V1.0</route_schema>
        <transformation_reception_to_canonical>DummyPBCargaRS/resources/xslt/DummyPBCarga/xslt_in_DummyPBCarga_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>DummyPBCargaRS/resources/xslt/DummyPBCarga/xslt_out_DummyPBCarga_canonical_to_reception</transformation_canonical_to_reception>
        <bs_backend>Commons/backends/T24/business/BS_PBCARGA_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
        <component_target>DummyPBCargaTS/proxy/PX_DummyPBCarga_V1.0</component_target>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>