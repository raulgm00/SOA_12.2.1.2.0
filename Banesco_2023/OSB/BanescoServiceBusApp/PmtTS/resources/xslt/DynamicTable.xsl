<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="PmtCardAdd" tipo="atomic">
        <operation>PmtCardAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/PmtCardAdd_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Payment/PmtCardAdd/PmtCardAdd_V1.0</route_schema>
        <requiresHeader>true</requiresHeader>
      </rule>
      <rule id="PmtCardCan" tipo="atomic">
        <operation>PmtCardCan</operation>
        <schema>http://xmlns.banesco.com/eopt/PmtCardCan_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Payment/PmtCardCan/PmtCardCan_V1.0</route_schema>
        <requiresHeader>true</requiresHeader>
      </rule>
      <rule id="PmtCardRev" tipo="atomic">
        <operation>PmtCardRev</operation>
        <schema>http://xmlns.banesco.com/eopt/PmtCardRev_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Payment/PmtCardRev/PmtCardRev_V1.0</route_schema>
        <transformation_canonical_to_backend>PmtTS/resources/xslt/PmtCardRev/xslt_in_PmtCardRevRq_to_tdcPmtRevRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PmtTS/resources/xslt/PmtCardRev/xslt_out_tdcPmtRevRs_to_PmtCardRevRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/WAY4/business/BS_WAY4_V1.0</bs_backend>
        <backend_operation>tdcPmtRev</backend_operation>
      </rule>
      <rule id="BillersRulesInq" tipo="atomic">
        <operation>BillersRulesInq</operation>
        <schema>http://xmlns.banesco.com/eopt/BillersRulesInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Payment/BillersRulesInq/BillersRulesInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PmtTS/resources/xslt/BillersRulesInq/xslt_in_BillersRulesInqRq_to_PAFacturadoresRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PmtTS/resources/xslt/BillersRulesInq/xslt_out_PAFacturadoresRs_to_BillersRulesInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/ICS/business/BS_ICSDB_V1.0</bs_backend>
        <backend_operation>PAFacturadores</backend_operation>
      </rule>
      <rule id="BillersInfoInq" tipo="atomic">
        <operation>BillersInfoInq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/BillersInfoInq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Payment/BillersInfoInq/BillersInfoInq_V1.0</route_schema>
        <transformation_canonical_to_backend>PmtTS/resources/xslt/BillersInfoInq/xslt_in_BillersInfoInqRq_to_PBillerListRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PmtTS/resources/xslt/BillersInfoInq/xslt_out_PBillerListRs_to_BillersInfoInqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/ICS/business/BS_ICSDB_V1.0</bs_backend>
        <backend_operation>PBillerList</backend_operation>
      </rule>
      <rule id="BillersInfoT24Inq" tipo="atomic">
        <operation>BillersInfoT24Inq</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/BillersInfoT24Inq_V1.0</schema>
        <route_schema>Commons/xsd/EOpt/Payment/BillersInfoT24Inq/BillersInfoT24Inq_V1.0</route_schema>
        <transformation_canonical_to_backend>PmtTS/resources/xslt/BillersInfoT24Inq/xslt_in_BillersInfoT24InqRq_to_BpaEnqListARPCategRq</transformation_canonical_to_backend>
        <transformation_backend_to_canonical>PmtTS/resources/xslt/BillersInfoT24Inq/xslt_out_BpaEnqListARPCategRs_to_BillersInfoT24InqRs</transformation_backend_to_canonical>
        <bs_backend>Commons/backends/T24/business/BS_T24_BpaEnqListARPCateg_V1.0</bs_backend>
        <backend_operation>Outbound</backend_operation>
      </rule>
      <rule id="PmtUtilAdd" tipo="atomic">
        <operation>PmtUtilAdd</operation>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/eopt/PmtUtilAdd_V1.0</schema>   
        <route_schema>Commons/xsd/EOpt/Payment/PmtUtilAdd/PmtUtilAdd_V1.0</route_schema>
      </rule>
      <rule id="BillInq" tipo="atomic">
        <operation>BillInq</operation>
        <schema>http://xmlns.banesco.com/eopt/BillCPagInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Payment/BillCPagInq/BillCPagInq_V1.0</route_schema>
      </rule>
      <rule id="PmtCardCrFinancingAdd" tipo="atomic">
        <operation>PmtCardCrFinancingAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/PmtCardCrFinancingAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Payment/PmtCardCrFinancingAdd/PmtCardCrFinancingAdd_V1.0</route_schema>
        <requiresHeader>true</requiresHeader>
      </rule>
      <rule id="PmtCardCrFinancingCan" tipo="atomic">
        <operation>PmtCardCrFinancingCan</operation>
        <schema>http://xmlns.banesco.com/eopt/PmtCardCrFinancingCan_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Payment/PmtCardCrFinancingCan/PmtCardCrFinancingCan_V1.0</route_schema>
        <requiresHeader>true</requiresHeader>
      </rule>
      <rule id="PmtCardCrPrepAdd" tipo="atomic">
        <operation>PmtCardCrPrepAdd</operation>
        <schema>http://xmlns.banesco.com/eopt/PmtCardCrPrepAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/EOpt/Payment/PmtCardCrPrepAdd/PmtCardCrPrepAdd_V1.0</route_schema>
        <requiresHeader>true</requiresHeader>
      </rule>
    </dinamicRule>
  </xsl:template>
</xsl:stylesheet>