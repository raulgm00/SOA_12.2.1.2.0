<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="ChkOrdImgInq" tipo="atomic">
        <operation>ChkOrdImgInq</operation>
        <schema>http://xmlns.banesco.com/eopt/ChkOrdImgInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Banking/ChkOrdImgInq/ChkOrdImgInq_V1.0</route_schema>
        <transformation_canonical_to_backend>CanonicalService/TechnicalLayer/Banking/resources/xslt/ChkOrdImgInq/xslt_in_ChkOrdImgInqRq_to_consultarImagenChequeOnbaseRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>CanonicalService/TechnicalLayer/Banking/resources/xslt/ChkOrdImgInq/xslt_out_consultarImagenChequeOnbaseRs_to_ChkOrdImgInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/ONBASE/business/BS_Onbase_V1.0</bs_backend>
        <backend_operation>consultarImagenChequeOnbase</backend_operation>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>