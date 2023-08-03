<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="AcctStmtImgInq" tipo="atomic">
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
        <backend_operation>AcctStmtImgInq</backend_operation>
      </rule>
      <rule id="AcctTrnInq" tipo="atomic">
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
        <backend_operation>AcctTrnInq</backend_operation>
      </rule>
      <rule id="AcctStmtInq" tipo="composed">
        <component_target>AcctSvc/pipeline/PL_AcctStmtInq</component_target>
        <backend_operation>AcctStmtInq</backend_operation>
      </rule>
      <rule id="AcctChargeAdd" tipo="atomic">
        <auditRecord>true</auditRecord>
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
        <backend_operation>AcctChargeAdd</backend_operation>
      </rule>
      <rule id="AcctInfoInq" tipo="composed">
        <auditRecord>true</auditRecord>
        <component_target>AcctSvc/pipeline/PL_AcctInfoInq_V1.0</component_target>
        <backend_operation>AcctInfoInq</backend_operation>
        <type_pn>Natural</type_pn>
        <type_pj>Juridico</type_pj>
        <op_pn>PersonAcctInfoInq</op_pn>
        <op_pj>OrgAcctInfoInq</op_pj>
      </rule>
      <rule id="PersonAcctInfoInq" tipo="atomic">
        <auditRecord>true</auditRecord>
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
        <backend_operation>PersonAcctInfoInq</backend_operation>
      </rule>
      <rule id="OrgAcctInfoInq" tipo="composed">
        <auditRecord>true</auditRecord>
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
        <backend_operation>OrgAcctInfoInq</backend_operation>
      </rule>
      <rule id="AcctAdd" tipo="composed">
        <auditRecord>true</auditRecord>
        <component_target>AcctSvc/pipeline/PL_AcctAdd_V1.0</component_target>
        <backend_operation>AcctAdd</backend_operation>
      </rule>
      <rule id="AcctFixDepInq" tipo="composed">
        <auditRecord>true</auditRecord>
        <component_target>AcctSvc/pipeline/PL_AcctFixDepInq_V1.0</component_target>
        <backend_operation>AcctFixDepInq</backend_operation>
        <type_pn>Natural</type_pn>
        <type_pj>Juridico</type_pj>
        <op_pn>PersonAcctFixDepInq</op_pn>
        <op_pn_component_target>AcctTS/proxy/PX_AcctDep_V1.0</op_pn_component_target>
        <op_pj>OrgAcctFixDepInq</op_pj>
        <op_pj_component_target>AcctTS/proxy/PX_AcctDep_V1.0</op_pj_component_target>
      </rule>
      <rule id="AcctLoanAdd" tipo="composed">
        <auditRecord>true</auditRecord>
        <component_target>AcctSvc/pipeline/PL_AcctLoanAdd_V1.0</component_target>
        <backend_operation>AcctLoanAdd</backend_operation>
      </rule>
      <rule id="AcctLoanAutoAdd" tipo="composed">
        <auditRecord>true</auditRecord>
        <component_target>AcctSvc/pipeline/PL_AcctLoanAutoAdd_V1.0</component_target>
        <backend_operation>AcctLoanAutoAdd</backend_operation>
      </rule>
      <rule id="AcctAddAdv" tipo="atomic">
        <auditRecord>true</auditRecord>
        <component_target_tech>AcctTS/proxy/PX_AcctAdv_V1.0</component_target_tech>
      </rule>
      <rule id="AdminTrnAdv" tipo="atomic">
        <auditRecord>true</auditRecord>
        <component_target_tech>AcctTS/proxy/PX_AcctAdv_V1.0</component_target_tech>
      </rule>
      <rule id="AcctLoanMortAdd" tipo="composed">
        <auditRecord>true</auditRecord>
        <component_target>AcctSvc/pipeline/PL_AcctLoanMortAdd_V1.0</component_target>
        <backend_operation>AcctLoanMortAdd</backend_operation>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>