<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
    <rule id="PersonPartyInfoInq" tipo="atomic">
        <operation>PersonPartyInfoInq</operation>
        <schema>http://xmlns.banesco.com/eopt/PersonPartyInfoInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PersonPartyInfoInq/PersonPartyInfoInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PersonPartyInfoInq/xslt_in_PersonPartyInfoInqRq_To_BpaEnqCustomerPnRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PersonPartyInfoInq/xslt_out_BpaEnqCustomerPnRs_To_PersonPartyInfoInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAENQCUSTOMERPN_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="OrgPartyInfoInq" tipo="atomic">
        <operation>OrgPartyInfoInq</operation>
        <schema>http://xmlns.banesco.com/eopt/OrgPartyInfoInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/OrgPartyInfoInq/OrgPartyInfoInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/OrgPartyInfoInq/xslt_in_OrgPartyInfoInqRq_To_BpaEnqCustomerPjRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/OrgPartyInfoInq/xslt_out_BpaEnqCustomerPjRs_To_OrgPartyInfoInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAENQCUSTOMERPJ_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PersonPartyRiskInq" tipo="atomic">
        <operation>PersonPartyRiskInq</operation>
        <schema>http://xmlns.banesco.com/eopt/PersonPartyRiskInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PersonPartyRiskInq/PersonPartyRiskInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PersonPartyRiskInq/xslt_in_PersonPartyRiskInqRq_to_calculoRiesgoN</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PersonPartyRiskInq/xslt_out_calculoRiesgoNResponse_to_PersonPartyRiskInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/SICG/business/BS_Sicg_V1.0</bs_backend>
        <backend_operation>calculoRiesgoN</backend_operation>
      </rule>
      <rule id="OrgPartyRiskInq" tipo="atomic">
        <operation>OrgPartyRiskInq</operation>
        <schema>http://xmlns.banesco.com/eopt/OrgPartyRiskInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/OrgPartyRiskInq/OrgPartyRiskInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/OrgPartyRiskInq/xslt_in_OrgPartyRiskInqRq_to_calculoRiesgoJ</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/OrgPartyRiskInq/xslt_out_calculoRiesgoJResponse_to_OrgPartyRiskInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/SICG/business/BS_Sicg_V1.0</bs_backend>
        <backend_operation>calculoRiesgoJ</backend_operation>
      </rule>
      <rule id="EmpPartyInq" tipo="atomic">
        <operation>EmpPartyInq</operation>
        <schema>http://xmlns.banesco.com/eopt/EmpPartyInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/EmpPartyInq/EmpPartyInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/EmpPartyInq/xslt_in_EmpPartyInqRq_to_ObtenerUsuarioActiveDirectoryRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/EmpPartyInq/xslt_out_ObtenerUsuarioActiveDirectoryRs_to_EmpPartyInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/AD/business/BS_AD_V1.0</bs_backend>
        <backend_operation>ObtenerUsuarioAD</backend_operation>
      </rule>
       <rule id="EmpPartyStatusMod" tipo="atomic">
        <operation>EmpPartyStatusMod</operation>
        <schema>http://xmlns.banesco.com/eopt/EmpPartyStatusMod_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/EmpPartyStatusMod/EmpPartyStatusMod_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/EmpPartyStatusMod/xslt_in_EmpPartyStatusModRq_to_UserBanModifRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/EmpPartyStatusMod/xslt_out_UserBanModifRs_to_EmpPartyStatusModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_UserBanModif_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PartyStaffInq" tipo="atomic">
        <operation>PartyStaffInq</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyStaffInq_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PartyStaffInq/PartyStaffInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PartyStaffInq/xslt_in_PartyStaffInqRq_To_BpaEnqStaffPJRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PartyStaffInq/xslt_out_BpaEnqStaffPJRs_To_PartyStaffInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaEnqStaffPJ_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PartyStaffAdd" tipo="atomic">
        <operation>PartyStaffAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyStaffAdd_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PartyStaffAdd/PartyStaffAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PartyStaffAdd/xslt_in_PartyStaffAddRq_To_BPAPRESTWFCUSTOMERRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PartyStaffAdd/xslt_out_BPAPRESTWFCUSTOMERRs_To_PartyStaffAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAPRESTWFCUSTOMER_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PartyStaffMod" tipo="atomic">
        <operation>PartyStaffMod</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyStaffMod_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PartyStaffMod/PartyStaffMod_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PartyStaffMod/xslt_in_PartyStaffModRq_To_BPAPRESTWFCUSTOMERRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PartyStaffMod/xslt_out_BPAPRESTWFCUSTOMERRs_To_PartyStaffModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAPRESTWFCUSTOMER_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PartyInq" tipo="atomic">
        <operation>PartyInq</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PartyInq/PartyInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PartyInq/xslt_in_PartyInqRq_To_BpaEnqListCustomerRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PartyInq/xslt_out_BpaEnqListCustomerRs_To_PartyInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaEnqListCustomer_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PartyRefInq" tipo="atomic">
        <operation>PartyRefInq</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyRefInq_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PartyRefInq/PartyRefInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PartyRefInq/xslt_in_PartyRefInqRq_To_BpaEnqReferencePNRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PartyRefInq/xslt_out_BpaEnqReferencePNRs_To_PartyRefInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaEnqReferencePN_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PartyRefAdd" tipo="atomic">
        <operation>PartyRefAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyRefAdd_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PartyRefAdd/PartyRefAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PartyRefAdd/xslt_in_PartyRefAddRq_To_BPAPRESTWFCUSTOMERRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PartyRefAdd/xslt_out_BPAPRESTWFCUSTOMERRs_To_PartyRefAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAPRESTWFCUSTOMER_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PartyRefMod" tipo="atomic">
        <operation>PartyRefMod</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyRefMod_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PartyRefMod/PartyRefMod_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PartyRefMod/xslt_in_PartyRefModRq_To_BPAPRESTWFCUSTOMERRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PartyRefMod/xslt_out_BPAPRESTWFCUSTOMERRs_To_PartyRefModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAPRESTWFCUSTOMER_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
       <rule id="PartyAdd" tipo="atomic">
        <operation>PartyAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyAdd_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PartyAdd/PartyAdd_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PartyAdd/xslt_in_PartyAddRq_To_BpaPrestWFCustomerRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PartyAdd/xslt_out_BpaPrestWFCustomerRs_To_PartyAddRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAPRESTWFCUSTOMER_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PartyMod" tipo="atomic">
        <operation>PartyMod</operation>
        <schema>http://xmlns.banesco.com/eopt/PartyMod_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/EOpt/Party/PartyMod/PartyMod_V1.0</route_schema>
        <transformation_canonical_to_backend>PartyTS/resources/xslt/PartyMod/xslt_in_PartyModRq_To_BpaPrestWFCustomerRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PartyTS/resources/xslt/PartyMod/xslt_out_BpaPrestWFCustomerRs_To_PartyModRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BPAPRESTWFCUSTOMER_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>