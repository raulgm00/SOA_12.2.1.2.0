<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="CardAcctRelInq" tipo="atomic">
        <component_target>CardTS/proxy/PXL_Card_V1.0</component_target>
        <backend_operation>CardAcctRelInq</backend_operation>
      </rule>
      <rule id="CardStatusMod" tipo="atomic">
        <component_target>CardTS/proxy/PXL_Card_V1.0</component_target>
        <backend_operation>CardStatusMod</backend_operation>
      </rule>
      <rule id="CardActAdd" tipo="atomic">
        <component_target>CardTS/proxy/PXL_Card_V1.0</component_target>
        <backend_operation>CardActAdd</backend_operation>
      </rule>
      <rule id="CardAcctTrnInq" tipo="atomic">
        <component_target>CardTS/proxy/PXL_Card_V1.0</component_target>
        <backend_operation>CardAcctTrnInq</backend_operation>
      </rule>
      <rule id="CardCrStatusMod" tipo="atomic">
        <auditRecord>false</auditRecord>
        <component_target>CardTS/proxy/PXL_CardCr_V1.0</component_target>
        <backend_operation>CardCrStatusMod</backend_operation>
      </rule>
      <rule id="CardDbAdd" tipo="composed">
        <auditRecord>false</auditRecord>
        <component_target>CardSvc/pipeline/PL_CardDbAdd_V1.0</component_target>
        <backend_operation>CardDbAdd</backend_operation>
      </rule>
      <rule id="CardCrTrnInq" tipo="composed">
        <component_target>CardSvc/pipeline/PL_CardCrTrnInq_V1.0</component_target>
        <backend_operation>CardCrTrnInq</backend_operation>
        <op_mov>CardCrTrnInq</op_mov>
        <op_mov_component_target>CardTS/proxy/PX_CardCr_V1.0</op_mov_component_target>
        <op_trn>CardCrTrnTransitInq</op_trn>
        <op_trn_component_target>CardTS/proxy/PX_CardCr_V1.0</op_trn_component_target>
      </rule>
      <rule id="CardCrInq" tipo="composed">
        <component_target>CardSvc/pipeline/PL_CardCrInfoInq_V1.0</component_target>
        <backend_operation>CardCrInq</backend_operation>
        <schema>http://xmlns.banesco.com/eopt/CardCrInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrInq/CardCrInq_V1.0</route_schema>
        <auditRecord>false</auditRecord>
        <transformation_biz_to_tech>CardSvc/resources/xslt/CardCrInq/xslt_in_CardCrInqRq_to_CardCrInqRq</transformation_biz_to_tech>
        <transformation_tech_to_biz>CardSvc/resources/xslt/CardCrInq/xslt_out_CardCrInqRs_to_CardCrInqRs</transformation_tech_to_biz>
        <component_target_backend>CardTS/proxy/PX_CardCrInfoInq_V1.0</component_target_backend>
      </rule>
      <rule id="CardCrInfoInq" tipo="composed">
        <component_target>CardSvc/pipeline/PL_CardCrInfoInq_V1.0</component_target>
        <backend_operation>CardCrInfoInq</backend_operation>
        <schema>http://xmlns.banesco.com/eopt/CardCrInfoInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/CardCr/CardCrInfoInq/CardCrInfoInq_V1.0</route_schema>
        <auditRecord>false</auditRecord>
        <transformation_biz_to_tech>CardSvc/resources/xslt/CardCrInfoInq/xslt_in_CardCrInfoInqRq_to_CardCrInfoInqRq</transformation_biz_to_tech>
        <transformation_tech_to_biz>CardSvc/resources/xslt/CardCrInfoInq/xslt_out_CardCrInfoInqRs_to_CardCrInfoInqRs</transformation_tech_to_biz>
        <component_target_backend>CardTS/proxy/PX_CardCrInfoInq_V1.0</component_target_backend>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>