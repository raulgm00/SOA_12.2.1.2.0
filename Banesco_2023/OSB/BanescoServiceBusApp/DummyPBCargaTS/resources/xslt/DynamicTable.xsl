<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="DummyPBCarga" tipo="atomic">
        <operation>DummyPBCarga</operation>
        <schema>http://xmlns.banesco.com/eopt/DummyPBCarga_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/DummyPBCarga/DummyPBCarga_V1.0</route_schema>
        <transformation_canonical_to_backend>DummyPBCargaTS/resources/xslt/DummyPBCarga/xslt_in_DummyPBCargaRq_to_PAPrbCargaRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>DummyPBCargaTS/resources/xslt/DummyPBCarga/xslt_out_PAPrbCargaRs_to_DummyPBCargaRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/DUMMYPBCARGA/business/BS_DUMMYPBCARGA_V1.0</bs_backend>
        <backend_operation>DummyPBCarga</backend_operation>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>