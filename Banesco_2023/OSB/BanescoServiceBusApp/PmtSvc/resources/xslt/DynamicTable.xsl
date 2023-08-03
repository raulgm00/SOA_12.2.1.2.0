<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="BillersInfoInq" tipo="composed">
        <component_target>PmtSvc/pipeline/PL_BillersInfoInq_V1.0</component_target>
        <backend_operation>BillersInfoInq</backend_operation>
      </rule>
      <rule id="PmtUtilAdd" tipo="composed">
        <auditRecord>true</auditRecord>
        <component_target>PmtSvc/pipeline/PL_PmtUtilAdd_V1.0</component_target>
        <backend_operation>PmtUtilAdd</backend_operation>
      </rule>
      <rule id="BillInq" tipo="composed">
        <component_target>PmtSvc/pipeline/PL_BillInq_V1.0</component_target>
        <backend_operation>BillInq</backend_operation>
      </rule>
      <rule id="PmtCardAdd" tipo="composed">
        <auditRecord>true</auditRecord>
        <component_target>PmtSvc/pipeline/PL_PmtCardAdd_V1.0</component_target>
        <backend_operation>PmtCardAdd</backend_operation>
        <component_target_tech>PmtTS/proxy/PX_Pmt_V1.1</component_target_tech>
        <transformation_canonical_to_technical>PmtSvc/resources/xslt/PmtCardAdd/xslt_in_PmtCardAddRq_To_PmtCardAddRq</transformation_canonical_to_technical>
        <transformation_technical_to_canonical>PmtSvc/resources/xslt/PmtCardAdd/xslt_out_PmtCardAddRs_To_PmtCardAddRs</transformation_technical_to_canonical>
      </rule>
      <rule id="PmtCardCan" tipo="atomic">
        <auditRecord>true</auditRecord>
        <component_target_tech>PmtTS/proxy/PX_Pmt_V1.1</component_target_tech>       
        <transformation_canonical_to_technical>PmtSvc/resources/xslt/PmtCardCan/xslt_in_PmtCardAddRq_To_PmtCardCanRq</transformation_canonical_to_technical>
        <transformation_technical_to_canonical>PmtSvc/resources/xslt/PmtCardCan/xslt_out_PmtCardCanRs_To_PmtCardAddRs</transformation_technical_to_canonical>
      </rule>
      <rule id="PmtCardCrFinancingAdd" tipo="atomic">
        <auditRecord>true</auditRecord>
        <component_target_tech>PmtTS/proxy/PX_Pmt_V1.1</component_target_tech>       
        <transformation_canonical_to_technical>PmtSvc/resources/xslt/PmtCardCrFinancingAdd/xslt_in_PmtCardAddRq_To_PmtCardCrFinancingAddRq</transformation_canonical_to_technical>
        <transformation_technical_to_canonical>PmtSvc/resources/xslt/PmtCardCrFinancingAdd/xslt_out_PmtCardCrFinancingAddRs_To_PmtCardAddRs</transformation_technical_to_canonical>
      </rule>
      <rule id="PmtCardCrFinancingCan" tipo="atomic">
        <auditRecord>true</auditRecord>
        <component_target_tech>PmtTS/proxy/PX_Pmt_V1.1</component_target_tech>       
        <transformation_canonical_to_technical>PmtSvc/resources/xslt/PmtCardCrFinancingCan/xslt_in_PmtCardAddRq_To_PmtCardCrFinancingCanRq</transformation_canonical_to_technical>
        <transformation_technical_to_canonical>PmtSvc/resources/xslt/PmtCardCrFinancingCan/xslt_out_PmtCardCrFinancingCanRs_To_PmtCardAddRs</transformation_technical_to_canonical>
      </rule>
      <rule id="PmtCardCrPrepAdd" tipo="atomic">
        <auditRecord>true</auditRecord>
        <component_target_tech>PmtTS/proxy/PX_Pmt_V1.1</component_target_tech>       
        <transformation_canonical_to_technical>PmtSvc/resources/xslt/PmtCardCrPrepAdd/xslt_in_PmtCardAddRq_To_PmtCardCrPrepAddRq</transformation_canonical_to_technical>
        <transformation_technical_to_canonical>PmtSvc/resources/xslt/PmtCardCrPrepAdd/xslt_out_PmtCardCrPrepAddRs_To_PmtCardAddRs</transformation_technical_to_canonical>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>