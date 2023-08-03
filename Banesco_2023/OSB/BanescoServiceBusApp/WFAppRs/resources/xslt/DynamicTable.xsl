<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <dinamicRule>
      <rule id="PartyInfoInq" tipo="atomic">
        <operation>PartyInfoInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyInfoInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyInfoInq/PartyInfoInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyInfoInq/xslt_in_PartyInfoInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyInfoInq/xslt_out_PartyInfoInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="PartyInq" tipo="atomic">
        <operation>PartyInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyInq/PartyInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyInq/xslt_in_PartyInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyInq/xslt_out_PartyInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="PartyStaffInq" tipo="atomic">
        <operation>PartyStaffInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyStaffInq_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyStaffInq/PartyStaffInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyStaffInq/xslt_in_PartyStaffInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyStaffInq/xslt_out_PartyStaffInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="PartyStaffAdd" tipo="atomic">
        <operation>PartyStaffAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyStaffAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyStaffAdd/PartyStaffAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyStaffAdd/xslt_in_PartyStaffAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyStaffAdd/xslt_out_PartyStaffAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
      </rule>
      <rule id="PartyStaffMod" tipo="atomic">
        <operation>PartyStaffMod</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyStaffMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyStaffMod/PartyStaffMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyStaffMod/xslt_in_PartyStaffMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyStaffMod/xslt_out_PartyStaffMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
      </rule>
      <rule id="PartyRefInq" tipo="atomic">
        <operation>PartyRefInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyRefInq_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyRefInq/PartyRefInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyRefInq/xslt_in_PartyRefInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyRefInq/xslt_out_PartyRefInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="PartyRefAdd" tipo="atomic">
        <operation>PartyRefAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyRefAdd_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyRefAdd/PartyRefAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyRefAdd/xslt_in_PartyRefAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyRefAdd/xslt_out_PartyRefAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="PartyRefMod" tipo="atomic">
        <operation>PartyRefMod</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyRefMod_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyRefMod/PartyRefMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyRefMod/xslt_in_PartyRefMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyRefMod/xslt_out_PartyRefMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="CardDbAdd" tipo="atomic">
        <operation>CardDbAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFCardDbAppSvc/CardDbAdd_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFCardDb/CardDbAdd/CardDbAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/CardDbAdd/xslt_in_CardDbAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/CardDbAdd/xslt_out_CardDbAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardSvc/proxy/PX_CardDbSvc_V1.0</component_target>
      </rule>
      <rule id="PartyMod" tipo="atomic">
        <operation>PartyMod</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyMod_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyMod/PartyMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyMod/xslt_in_PartyMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyMod/xslt_out_PartyMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="PartyAdd" tipo="atomic">
        <operation>PartyAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFPartyAppSvc/PartyAdd_V1.0</schema>
        <auditRecord>false</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFParty/PartyAdd/PartyAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/PartyAdd/xslt_in_PartyAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/PartyAdd/xslt_out_PartyAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>PartySvc/proxy/PXL_PartySvc_V1.0</component_target>
      </rule>
      <rule id="AcctLoanCalcAdd" tipo="atomic">
        <operation>AcctLoanCalcAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanCalcAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanCalcAdd/AcctLoanCalcAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanCalcAdd/xslt_in_AcctLoanCalcAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanCalcAdd/xslt_out_AcctLoanCalcAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanCalcInq" tipo="atomic">
        <operation>AcctLoanCalcInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanCalcInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanCalcInq/AcctLoanCalcInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanCalcInq/xslt_in_AcctLoanCalcInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanCalcInq/xslt_out_AcctLoanCalcInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctSignatureGrpAdd" tipo="atomic">
        <operation>AcctSignatureGrpAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctAppSvc/AcctSignatureGrpAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcct/AcctSignatureGrpAdd/AcctSignatureGrpAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctSignatureGrpAdd/xslt_in_AcctSignatureGrpAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctSignatureGrpAdd/xslt_out_AcctSignatureGrpAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
      </rule>
      <rule id="AcctFixDepAdd" tipo="atomic">
        <operation>AcctFixDepAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctDepAppSvc/AcctFixDepAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctDep/AcctFixDepAdd/AcctFixDepAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctFixDepAdd/xslt_in_AcctFixDepAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctFixDepAdd/xslt_out_AcctFixDepAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctDep_V1.0</component_target>
      </rule>
      <rule id="AcctFixDepInq" tipo="atomic">
        <operation>AcctFixDepInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctDepAppSvc/AcctFixDepInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctDep/AcctFixDepInq/AcctFixDepInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctFixDepInq/xslt_in_AcctFixDepInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctFixDepInq/xslt_out_AcctFixDepInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctDepSvc_V1.0</component_target>
      </rule>
      <rule id="AcctAdd" tipo="atomic">
        <operation>AcctAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctAppSvc/AcctAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcct/AcctAdd/AcctAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctAdd/xslt_in_AcctAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctAdd/xslt_out_AcctAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctSvc_V1.0</component_target>
      </rule>
      <rule id="AcctLoanLedgerAdd" tipo="atomic">
        <operation>AcctLoanLedgerAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanLedgerAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanLedgerAdd/AcctLoanLedgerAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanLedgerAdd/xslt_in_AcctLoanLedgerAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanLedgerAdd/xslt_out_AcctLoanLedgerAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanStatusMod" tipo="atomic">
        <operation>AcctLoanStatusMod</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanStatusMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanStatusMod/AcctLoanStatusMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanStatusMod/xslt_in_AcctLoanStatusMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanStatusMod/xslt_out_AcctLoanStatusMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanPartyRelAdd" tipo="atomic">
        <operation>AcctLoanPartyRelAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanPartyRelAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanPartyRelAdd/AcctLoanPartyRelAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanPartyRelAdd/xslt_in_AcctLoanPartyRelAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanPartyRelAdd/xslt_out_AcctLoanPartyRelAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="ChkOrdAdd" tipo="atomic">
        <operation>ChkOrdAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFChkAppSvc/ChkOrdAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFChk/ChkOrdAdd/ChkOrdAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/ChkOrdAdd/xslt_in_ChkOrdAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/ChkOrdAdd/xslt_out_ChkOrdAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_Chk_V1.0</component_target>
      </rule>
      <rule id="AcctLoanAgreeInq" tipo="atomic">
        <operation>AcctLoanAgreeInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanAgreeInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanAgreeInq/AcctLoanAgreeInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanAgreeInq/xslt_in_AcctLoanAgreeInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanAgreeInq/xslt_out_AcctLoanAgreeInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="ChkOrdMod" tipo="atomic">
        <operation>ChkOrdMod</operation>
        <schema>http://xmlns.banesco.com/appopt/WFChkAppSvc/ChkOrdMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFChk/ChkOrdMod/ChkOrdMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/ChkOrdMod/xslt_in_ChkOrdMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/ChkOrdMod/xslt_out_ChkOrdMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_Chk_V1.0</component_target>
      </rule>
      <rule id="AcctLoanAgreeAdd" tipo="atomic">
        <operation>AcctLoanAgreeAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanAgreeAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanAgreeAdd/AcctLoanAgreeAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanAgreeAdd/xslt_in_AcctLoanAgreeAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanAgreeAdd/xslt_out_AcctLoanAgreeAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanCollateralAdd" tipo="atomic">
        <operation>AcctLoanCollateralAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanCollateralAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanCollateralAdd/AcctLoanCollateralAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanCollateralAdd/xslt_in_AcctLoanCollateralAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanCollateralAdd/xslt_out_AcctLoanCollateralAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanCollateralMod" tipo="atomic">
        <operation>AcctLoanCollateralMod</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanCollateralMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanCollateralMod/AcctLoanCollateralMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanCollateralMod/xslt_in_AcctLoanCollateralMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanCollateralMod/xslt_out_AcctLoanCollateralMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanAgreeMod" tipo="atomic">
        <operation>AcctLoanAgreeMod</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanAgreeMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanAgreeMod/AcctLoanAgreeMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanAgreeMod/xslt_in_AcctLoanAgreeMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanAgreeMod/xslt_out_AcctLoanAgreeMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanPartyRelMod" tipo="atomic">
        <operation>AcctLoanPartyRelMod</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanPartyRelMod_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanPartyRelMod/AcctLoanPartyRelMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanPartyRelMod/xslt_in_AcctLoanPartyRelMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanPartyRelMod/xslt_out_AcctLoanPartyRelMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanPartyInq" tipo="atomic">
        <operation>AcctLoanPartyInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanPartyInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanPartyInq/AcctLoanPartyInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanPartyInq/xslt_in_AcctLoanPartyInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanPartyInq/xslt_out_AcctLoanPartyInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanInq" tipo="atomic">
        <operation>AcctLoanInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanInq/AcctLoanInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanInq/xslt_in_AcctLoanInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanInq/xslt_out_AcctLoanInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctChargeAdd" tipo="atomic">
        <operation>AcctChargeAdd</operation>
        <service>WFAcctAppSvc</service>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/appopt/WFAcctAppSvc/AcctChargeAdd_V1.0</schema>
        <route_schema>Commons/xsd/AppOpt/WFAcct/AcctChargeAdd/AcctChargeAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctChargeAdd/xslt_in_AcctChargeAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctChargeAdd/xslt_out_AcctChargeAdd_reception_to_canonical</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctSvc_V1.0</component_target>
      </rule>
      <rule id="AcctCondMobilAdd" tipo="atomic">
        <operation>AcctCondMobilAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctAppSvc/AcctCondMobilAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcct/AcctCondMobilAdd/AcctCondMobilAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctCondMobilAdd/xslt_in_AcctCondMobilAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctCondMobilAdd/xslt_out_AcctCondMobilAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
      </rule>
      <rule id="AcctInq" tipo="atomic">
        <operation>AcctInq</operation>
        <service>WFAcctAppSvc</service>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/appopt/WFAcctAppSvc/AcctInq_V1.0</schema>
        <route_schema>Commons/xsd/AppOpt/WFAcct/AcctInq/AcctInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctInq/xslt_in_AcctInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctInq/xslt_out_AcctInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_Acct_V1.0</component_target>
      </rule>
      <rule id="AcctInfoInq" tipo="atomic">
        <operation>AcctInfoInq</operation>
        <service>WFAcctAppSvc</service>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/appopt/WFAcctAppSvc/AcctInfoInq_V1.0</schema>
        <route_schema>Commons/xsd/AppOpt/WFAcct/AcctInfoInq/AcctInfoInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctInfoInq/xslt_in_AcctInfoInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctInfoInq/xslt_out_AcctInfoInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctSvc_V1.0</component_target>
      </rule>
      <rule id="AcctLoanPrLetterAdd" tipo="atomic">
        <operation>AcctLoanPrLetterAdd</operation>
        <service>WFAcctAppSvc</service>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanPrLetterAdd_V1.0</schema>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanPrLetterAdd/AcctLoanPrLetterAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanPrLetterAdd/xslt_in_AcctLoanPrLetterAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanPrLetterAdd/xslt_out_AcctLoanPrLetterAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
        <rule id="CardCrPledgeAdd" tipo="atomic">
        <operation>CardCrPledgeAdd</operation>
        <service>WFCardCrAppSvc</service>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/appopt/WFCardCrAppSvc/CardCrPledgeAdd_V1.0</schema>
        <route_schema>Commons/xsd/AppOpt/WFCardCr/CardCrPledgeAdd/CardCrPledgeAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/CardCrPledgeAdd/xslt_in_CardCrPledgeAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/CardCrPledgeAdd/xslt_out_CardCrPledgeAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardTS/proxy/PX_CardCr_V1.0</component_target>
      </rule>
      <rule id="AcctLoanPrLetterMod" tipo="atomic">
        <operation>AcctLoanPrLetterMod</operation>
        <service>WFAcctAppSvc</service>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanPrLetterMod_V1.0</schema>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanPrLetterMod/AcctLoanPrLetterMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanPrLetterMod/xslt_in_AcctLoanPrLetterMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanPrLetterMod/xslt_out_AcctLoanPrLetterMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="AcctLoanInfoInq" tipo="atomic">
        <operation>AcctLoanInfoInq</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanInfoInq_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanInfoInq/AcctLoanInfoInq_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanInfoInq/xslt_in_AcctLoanInfoInq_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanInfoInq/xslt_out_AcctLoanInfoInq_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctTS/proxy/PX_AcctLoan_V1.0</component_target>
      </rule>
      <rule id="CardCrPledgeMod" tipo="atomic">
        <operation>CardCrPledgeMod</operation>
        <service>WFCardCrAppSvc</service>
        <auditRecord>true</auditRecord>
        <schema>http://xmlns.banesco.com/appopt/WFCardCrAppSvc/CardCrPledgeMod_V1.0</schema>
        <route_schema>Commons/xsd/AppOpt/WFCardCr/CardCrPledgeMod/CardCrPledgeMod_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/CardCrPledgeMod/xslt_in_CardCrPledgeMod_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/CardCrPledgeMod/xslt_out_CardCrPledgeMod_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>CardTS/proxy/PX_CardCr_V1.0</component_target>
      </rule>
      <rule id="AcctLoanAdd" tipo="atomic">
        <operation>AcctLoanAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanAdd/AcctLoanAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanAdd/xslt_in_AcctLoanAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanAdd/xslt_out_AcctLoanAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctLoanSvc_V1.0</component_target>
      </rule>
      <rule id="AcctLoanAutoAdd" tipo="atomic">
        <operation>AcctLoanAutoAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanAutoAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanAutoAdd/AcctLoanAutoAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanAutoAdd/xslt_in_AcctLoanAutoAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanAutoAdd/xslt_out_AcctLoanAutoAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctLoanSvc_V1.0</component_target>
      </rule>
      <rule id="AcctLoanMortAdd" tipo="atomic">
        <operation>AcctLoanMortAdd</operation>
        <schema>http://xmlns.banesco.com/appopt/WFAcctLoanAppSvc/AcctLoanMortAdd_V1.0</schema>
        <auditRecord>true</auditRecord>
        <route_schema>Commons/xsd/AppOpt/WFAcctLoan/AcctLoanMortAdd/AcctLoanMortAdd_V1.0</route_schema>
        <transformation_reception_to_canonical>WFAppRs/resources/xslt/AcctLoanMortAdd/xslt_in_AcctLoanMortAdd_reception_to_canonical</transformation_reception_to_canonical>
        <transformation_canonical_to_reception>WFAppRs/resources/xslt/AcctLoanMortAdd/xslt_out_AcctLoanMortAdd_canonical_to_reception</transformation_canonical_to_reception>
        <component_target>AcctSvc/proxy/PX_AcctLoanSvc_V1.0</component_target>
      </rule>
    </dinamicRule>    
  </xsl:template>
</xsl:stylesheet>
