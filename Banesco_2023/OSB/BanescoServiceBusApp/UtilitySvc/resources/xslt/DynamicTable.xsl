<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="OnlineBankTrnAdv" tipo="composed">
        <component_target>UtilitySvc/pipeline/PL_OnlineBankTrnAdv_V1.0</component_target>
        <backend_operation>OnlineBankTrnAdv</backend_operation>
        <eventNum_trn>315</eventNum_trn>
        <eventNum_admin>392</eventNum_admin>
        <op_trn>OnlineBankTrnAddAdvise</op_trn>
        <op_trn_component_target>UtilityTS/proxy/PX_Utility_V1.0</op_trn_component_target>
        <op_admin>AdminOnlineBankAddAdvise</op_admin>
        <op_admin_component_target>UtilityTS/proxy/PX_Utility_V1.0</op_admin_component_target>
      </rule>
      <rule id="AlertAdv" tipo="composed">
        <component_target>UtilitySvc/pipeline/PL_AlertAdv_V1.0</component_target>
        <backend_operation>AlertAdv</backend_operation>
      </rule>
      <rule id="AlertSmsTTextoAdv" tipo="atomic">
        <operation>AlertSmsTTextoAdv</operation>
        <transformation_biz_to_tech>UtilitySvc/resources/xslt/AlertSmsTTextoAdv/xslt_in_AlertAdvRq_to_AlertSmsTTextoAdvRq</transformation_biz_to_tech>
        <transformation_tech_to_biz>UtilitySvc/resources/xslt/AlertSmsTTextoAdv/xslt_in_AlertSmsTTextoAdvRs_to_AlertAdvRs</transformation_tech_to_biz>
        <component_target>UtilityTS/proxy/PX_Utility_V1.0</component_target>
        <backend_operation>AlertSmsTTextoAdv</backend_operation>
      </rule>
      <rule id="AlertSmsQuanticAdv" tipo="atomic">
        <operation>AlertSmsQuanticAdv</operation>
        <transformation_biz_to_tech>UtilitySvc/resources/xslt/AlertSmsQuanticAdv/xslt_in_AlertAdvRq_to_AlertSmsQuanticAdvRq</transformation_biz_to_tech>
        <transformation_tech_to_biz>UtilitySvc/resources/xslt/AlertSmsQuanticAdv/xslt_in_AlertSmsQuanticAdvRs_to_AlertAdvRs</transformation_tech_to_biz>
        <component_target>UtilityTS/proxy/PX_Utility_V1.0</component_target>
        <backend_operation>AlertSmsQuanticAdv</backend_operation>
      </rule>
      <rule id="AlertEmailAdv" tipo="atomic">
        <operation>AlertEmailAdv</operation>
        <transformation_biz_to_tech>UtilitySvc/resources/xslt/AlertEmailAdv/xslt_in_AlertAdvRq_to_AlertEmailAdvRq</transformation_biz_to_tech>
        <transformation_tech_to_biz>UtilitySvc/resources/xslt/AlertEmailAdv/xslt_in_AlertEmailAdvRs_to_AlertAdvRs</transformation_tech_to_biz>
        <component_target>UtilityTS/proxy/PX_Utility_V1.0</component_target>
        <backend_operation>AlertEmailAdv</backend_operation>
      </rule>
      <rule id="AlertPersonPartyInfoInqAdv" tipo="atomic">
        <backend_operation>PersonPartyInfoInq</backend_operation>
        <transformation_biz_to_tech>UtilitySvc/resources/xslt/AlertPersonPartyInfoInqAdv/xslt_in_AlertAdvRq_to_PersonPartyInfoInqRq</transformation_biz_to_tech>
        <transformation_biz_to_biz>UtilitySvc/resources/xslt/AlertPersonPartyInfoInqAdv/xslt_out_PersonPartyInfoInqRs_to_AlertAdvRs</transformation_biz_to_biz>
        <transformation_tech_to_biz>UtilitySvc/resources/xslt/AlertPersonPartyInfoInqAdv/xslt_out_PersonPartyInfoInqRs_to_AlertAdvRq</transformation_tech_to_biz>
      </rule>
      <rule id="AlertOrgPartyInfoInqAdv" tipo="atomic">
        <backend_operation>OrgPartyInfoInq</backend_operation>
        <transformation_biz_to_tech>UtilitySvc/resources/xslt/AlertOrgPartyInfoInqAdv/xslt_in_AlertAdvRq_to_OrgPartyInfoInqRq</transformation_biz_to_tech>
        <transformation_biz_to_biz>UtilitySvc/resources/xslt/AlertOrgPartyInfoInqAdv/xslt_out_OrgPartyInfoInqRs_to_AlertAdvRs</transformation_biz_to_biz>
        <transformation_tech_to_biz>UtilitySvc/resources/xslt/AlertOrgPartyInfoInqAdv/xslt_out_OrgPartyInfoInqRs_to_AlertAdvRq</transformation_tech_to_biz>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>